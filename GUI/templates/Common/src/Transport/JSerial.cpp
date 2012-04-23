/*! 
 ***********************************************************************
 * @file      JSerial.cpp
 * @author    Dave Martin, DeVivo AST, Inc.  
 * @date      2008/03/03
 *
 *  Copyright (C) 2008. DeVivo AST, Inc
 *
 *  This file is part of Jr Middleware.
 *
 *  Jr Middleware is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Jr Middleware is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Jr Middleware.  If not, see <http://www.gnu.org/licenses/>.
 *
 ************************************************************************
 */

#include "Transport/JSerial.h"
#include "Transport/OS.h"
#include "Transport/JrLogger.h"
#include <fcntl.h>

using namespace DeVivo::Junior;

// Define JSerial constants
const char JSERIAL_DLE = 0x10;
const char JSERIAL_SOH = 0x01;
const char JSERIAL_STX = 0x02;
const char JSERIAL_ETX = 0x03;

// Some values are different in Linux/Windows environment
#ifdef WINDOWS
#define SERIAL_PATH "//./"
#define getlasterror GetLastError()
#else
#define INVALID_HANDLE_VALUE 0
#define SERIAL_PATH "/dev/"
#define getlasterror errno
#define CloseHandle close
#endif


// For Linux, we also need to convert baud rates
// into the supported enum values.
#ifndef WINDOWS
int baud2Enum(int baud)
{
    switch (baud)
    {
        case 230500: return B230400;
        case 115200: return B115200;
        case  57600: return B57600;
        case  38400: return B38400;
        case  19200: return B19200;
        case   9600: return B9600;
        case   4800: return B4800;
        case   2400: return B2400;
        case   1200: return B1200;
        case      0: return B19200;
        default: 
            JrWarn << "Unsupported baud rate.  Defaulting to 19200\n";
            return B19200;
    }
    return B19200;
}
#endif


// Class definition
JSerial::JSerial():
   hComm(0),
   previousByteWasDLE(false)
{
    my_name = "JSerial";
}

JSerial::~JSerial()
{
    if (hComm > 0) CloseHandle(hComm);
    hComm = 0;
}

Transport::TransportError JSerial::configureLink(ConfigData& config, int index)
{
    unsigned int baudrate = 19200;
    config.getValue(baudrate, "SerialBaudRate", "Serial_Configuration", index);
    std::string parity = "none";
    config.getValue(parity, "SerialParity", "Serial_Configuration", index);
    unsigned int stopbits = 1;
    config.getValue(stopbits, "SerialStopBits", "Serial_Configuration", index);
    unsigned int software_dataflow = 0;
    config.getValue(software_dataflow, "SerialSoftwareFlowControl", "Serial_Configuration", index);


    // Check for valid parameters
    if ( !JrStrCaseCompare(parity, "odd") && 
         !JrStrCaseCompare(parity, "even") &&
         !JrStrCaseCompare(parity, "none") )
    {
        JrWarn << "Invalid serial parity in config file.  Using <none>\n";
        parity = "none";
    }
    if ((stopbits != 1) && (stopbits != 2))
    {
        JrWarn << "Invalid serial stop bits.  Using 1\n";
        stopbits = 1;
    }

    // debug
    JrDebug << "Serial configuration: ByteSize: 8  Parity: " << parity <<
        "   Stop: " << (long) stopbits << "   Baud: " << baudrate << "   FlowControl: " <<
        (software_dataflow ? "software" : "hardware") << std::endl;


#ifdef WINDOWS
    
    // Get the Data Control Block
    DCB dcb = {0};
    if (!GetCommState(hComm, &dcb)) return InitFailed;

    // Set-up for configured parity, baud, and stop
    dcb.ByteSize = 8;
    dcb.BaudRate = baudrate;
    if (JrStrCaseCompare(parity, "odd"))
        dcb.Parity = ODDPARITY;
    else if (JrStrCaseCompare(parity, "even"))
        dcb.Parity = EVENPARITY;
    else
        dcb.Parity = NOPARITY;
    if (stopbits == 2)
        dcb.StopBits = TWOSTOPBITS;
    else
        dcb.StopBits = ONESTOPBIT;
    if (software_dataflow)
    {
        // Configure for software flow control (XOn/XOff)
        dcb.fInX = 1; dcb.fOutX = 1;
        dcb.fOutxCtsFlow = 0;
    }
    else
    {
        // Configure for hardware flow control (RTS-CTS)
        dcb.fInX = 0; dcb.fOutX = 0;
        dcb.fRtsControl = RTS_CONTROL_HANDSHAKE;
        dcb.fOutxCtsFlow = 1;
    }
    SetCommState(hComm, &dcb);
    
    // Get comm timeouts
    COMMTIMEOUTS cto;
    if (GetCommTimeouts(hComm, &cto) == 0)
    {
        JrError << "Failed to configure serial port.  Error: " << getlasterror << std::endl;
        return InitFailed;
    }

    // Set the comm timeouts to values we like
    cto.ReadIntervalTimeout = MAXDWORD;
    cto.ReadTotalTimeoutMultiplier = 0;
    cto.ReadTotalTimeoutConstant = 0;
    cto.WriteTotalTimeoutMultiplier = 0;
    cto.WriteTotalTimeoutConstant = 0;
    SetCommTimeouts(hComm, &cto);

#else

    // Get the current options set
    struct termios options;
    tcgetattr(hComm, &options);

    // set the baud rate
    cfsetispeed(&options, 0);
    cfsetospeed(&options, baud2Enum(baudrate));

    // set the character size
    options.c_cflag &= ~CSIZE;
    options.c_cflag |= CS8;

    // set the parity
    options.c_iflag &= ~(INPCK | ISTRIP | IGNPAR | PARMRK );
    options.c_cflag &= ~(PARENB | PARODD);
    if (JrStrCaseCompare(parity, "odd"))
    {
        options.c_iflag |= INPCK;
        options.c_cflag |= (PARENB | PARODD);
    }
    else if (JrStrCaseCompare(parity, "even"))
    {
        options.c_iflag |= INPCK;
        options.c_cflag |= PARENB;
    }

    // set the stop bits
    options.c_cflag &= ~CSTOPB;
    if (stopbits == 2) options.c_cflag |= CSTOPB;

    // Set receiver and local modes
    options.c_cflag |= (CLOCAL | CREAD);

    // flow control (hardware or software)
    if (software_dataflow)
    {
        // Configure for software flow control (XOn/XOff)
        options.c_cflag &= ~CRTSCTS;
        options.c_iflag |= (IXON | IXOFF | IXANY);
    }
    else
    {
        // Configure for hardware flow control (RTS-CTS)
        options.c_cflag |= CRTSCTS;
        options.c_iflag &= ~(IXON | IXOFF | IXANY);
    }

    // enable raw mode (this prevent interpretation of
    // the data stream for things line CR-LR
    options.c_lflag &= ~(ICANON | ECHO | ECHOE | ISIG | IEXTEN);
    options.c_oflag &= ~(OPOST | ONLCR); 
    options.c_iflag &= ~(IGNBRK | IGNCR | INLCR | BRKINT | ICRNL);

    // set the timing (no wait)
    options.c_cc[VTIME] = 0;
    options.c_cc[VMIN]  = 0;

    // Set the new options
    tcsetattr(hComm, TCSANOW, &options);

#endif

    return Ok;
}

Transport::TransportError JSerial::initialize( ConfigData& config, int index )
{
    // Pull the com port name and compatbility mode
    std::string portname = "COM1";
    config.getValue(portname, "SerialPortName", "Serial_Configuration", index);
    portname = SERIAL_PATH + portname;
    JrInfo << "Serial: Using port " << portname << std::endl;

#ifdef WINDOWS
    // Open a file for reading/writing to the port
    hComm = CreateFile(portname.c_str(), GENERIC_READ | GENERIC_WRITE, 0, 0,
                        OPEN_EXISTING, 0, 0);
#else
    // Open a file descriptor
    hComm = open(portname.c_str(), O_RDWR | O_NOCTTY /*| O_NDELAY*/);
#endif

    // Check for valid response
    if ((hComm == INVALID_HANDLE_VALUE) || (hComm <= 0))
    {
        JrError << "Failed to open serial port " << portname <<
            ".  Error: " << getlasterror << std::endl;
        hComm = 0;
        return InitFailed;
    }

    // Configure the link for parity, baud rate, etc.
    configureLink(config, index);
    return Ok;
}

Transport::TransportError JSerial::sendMsg(Message& msg)
{
    TransportError ret = AddrUnknown; 

    // Only send messages if the destination is known to us
    for (int i = 0; i < _map.getList().size(); i++)
    {
        if ((_map.getList()[i]->getId() == msg.getDestinationId()) &&
            (_map.getList()[i]->getId() != msg.getSourceId()))
        {
            // Send to this entry
            ret = sendMsg(msg, _map.getList()[i]->getAddress());
        }
    }

    return ret;
}

Transport::TransportError JSerial::sendMsg(Message& msg, 
										   HANDLE handle)
{
    // Assume the best...
    Transport::TransportError result = Ok;

    //
    // Now pack the message for network transport 
    //
    JSerialArchive archive;
	archive.pack(msg, msg.getMessageCode() == 0 ? AS5669A : AS5669);

#ifdef WINDOWS

    // Write to the open port
    DWORD bytesWritten;
    bool ret = WriteFile(handle, archive.getArchive(), 
                      archive.getArchiveLength(),
                      &bytesWritten, NULL);
    if (ret == 0) 
    {
        JrError << "Failed to write to serial port.  Error: " << getlasterror << std::endl;
        result = Failed;
    }

#else

    int bytesWritten = write(handle, archive.getArchive(),
                        archive.getArchiveLength());

#endif

    // make sure we wrote the whole packet
    if (bytesWritten != archive.getArchiveLength())
    {
        JrError << "Failed to write full packet on serial port. Wrote " << 
            bytesWritten << " of " << archive.getArchiveLength() << std::endl;
        result = Failed;
    }
    else
        JrDebug << "Serial: Wrote " << bytesWritten << " bytes on serial port\n";

    return result;
}


Transport::TransportError JSerial::recvMsg(MessageList& msglist)
{
    char buffer[5000];
    
    TransportError ret = NoMessages;
 
#ifdef WINDOWS
    // Read from the serial port
    DWORD bytesRead;
    if (!ReadFile(hComm, buffer, 5000, &bytesRead, NULL))
    {
        //JrError << "Failed to read serial port.  Error:" <<  getlasterror << std::endl;
        return Failed;
    }
#else
    int bytesRead = read(hComm, buffer, 5000);
#endif

    // Nothing to do if we didn't read any bytes
    if (bytesRead <= 0) return NoMessages;
    JrDebug << "Read " << bytesRead << " bytes from serial port.\n";

    // We need to process the incoming stream byte-wise, since the 
    // stream may contain DLE-marked instructions.
    for (int i=0; i<bytesRead; i++)
    {
        if (previousByteWasDLE)
        {
            // Previous byte was a DLE marker.  Next byte
            // tells the interpretation of the instruction.
            if (buffer[i] == JSERIAL_DLE)
            {
                // DLE was used to mark a data element that
                // coincidentally had the same value as the
                // DLE marker.  Strip the unnecessary DLE from
                // the packet.
                unusedBytes << buffer[i];
            }
            else
            {
                if (buffer[i] == JSERIAL_SOH)
                {
                    // DLE marks a packet start.  See if the 
                    // unused bytes contain a valid packet
                    ret = extractMsgsFromPacket(msglist);

                    // Update the log if we're discarding 
                    // a non-empty packet
                    if (unusedBytes.getArchiveLength() > 0)
                        JrWarn << "Received new serial packet delineator.  Discarding "
                               << unusedBytes.getArchiveLength() << " unprocessed bytes\n";

                    // Now clear the unused bytes buffer
                    // so we can start the new packet.
                    unusedBytes.clear();
                }

                // Add the DLE marker (from the previous byte)
                // as well as the current byte to the 
                // unusedBytes buffer.
                unusedBytes << JSERIAL_DLE;
                unusedBytes << buffer[i];
            }

            // Reset the flag indicating previous byte was DLE
            previousByteWasDLE = false;
        }
        else if (buffer[i] == JSERIAL_DLE)
        {
            // DLE marker preceding special byte.
            previousByteWasDLE = true;
        }
        else
        {
            // regular data byte
            unusedBytes << buffer[i];
        }
    }

    // Check packet for completeness.  If so, parse message(s).
    ret = extractMsgsFromPacket(msglist);
    
    // done processing this read.  return.
    return ret;
}



Transport::TransportError JSerial::broadcastMsg(Message& msg)
{
	// Broadcast for serial is the same as a send.
	return sendMsg(msg, hComm);
}

Transport::TransportError JSerial::extractMsgsFromPacket(MessageList& msglist)
{
    unsigned short jausMsgLength;

    // Check for trivial case
    if (!unusedBytes.isArchiveValid()) return NoMessages;

    // A single packet may have multiple JAUS messages on it, each
    // with there own header compression flags.  We need to parse through
    // the entire packet, remove each message one at a time and
    // adding it to the return list.
    while (unusedBytes.isArchiveValid())
    {
        // Extract the payload into a message
        Message* msg = new Message();
        unusedBytes.unpack(*msg);

        //
        // Add the source to the transport discovery map.
        //
        _map.addElement( msg->getSourceId(), hComm, unusedBytes.getVersion() );

        // Add the message to the list and change the return value
        JrDebug << "Found valid serial message (size " << msg->getDataLength() << ")\n";
        msglist.push_back(msg);

        // Remove this message from the archive, so
        // we can process the next message in the packet.
        unusedBytes.removeHeadMsg( );
    }

    // Clear the data buffer and return
    unusedBytes.clear();
    return Ok;
}
