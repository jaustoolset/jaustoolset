/*!
 ***********************************************************************
 * @file      JuniorRTE.cpp
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
#include <stdio.h>
#include <stdlib.h>
#include <algorithm>
#include <signal.h>
#include "Transport/XmlConfig.h"
#include "Transport/JrSockets.h"
#include "Transport/Transport.h"
#include "Transport/JUDPTransport.h"
#include "Transport/JUDPTransportLB.h"
#include "Transport/JSerial.h"
#include "Transport/JTCPTransport.h"
#include "Transport/Types.h"
#include "Transport/OS.h"
#include "Transport/JrLogger.h"

using namespace DeVivo::Junior;

// Define a signal handler, so we can clean-up properly
static int exit_flag = 0;
static void handle_exit_signal( int signum )
{
    exit_flag = 1;
}

// Main loop
int DllExport RunJuniorRTE(std::string config_file)
{

    // This is the main entry point for hte Junior Run-Time engine.
    // First thing we need to do is initialize the log, but we can't
    // do that until we read in the log file name from the configuration
    // file.  So we start with opening and parsing the config file...
    XmlConfig config;
    config.parseFile(config_file);
    std::string logfile;
    config.getValue(logfile, "LogFileName", "Log_Configuration");
    int debug_level = 3;
    config.getValue(debug_level, "LogMsgLevel", "Log_Configuration");

	// Now set-up the data logger
    if (debug_level > (int) Logger::full) debug_level = (int) Logger::full;
    Logger::get()->setMsgLevel((enum Logger::LogMsgType) debug_level);
    if (!logfile.empty()) Logger::get()->openOutputFile(logfile);

	// And now we can read the rest of the config file, with the benefit
	// of logging....
    int allowRelay = 0;
    config.getValue(allowRelay, "AllowRelay", "RTE_Configuration");
    int delay = 1;
    config.getValue(delay ,"RTE_CycleTime", "RTE_Configuration");
    int use_udp = 1;
    config.getValue(use_udp, "EnableUDPInterface", "RTE_Configuration");
    int use_tcp = 0;
    config.getValue(use_tcp, "EnableTCPInterface", "RTE_Configuration");
    int use_serial = 0;
    config.getValue(use_serial, "NumSerialInterfaces", "RTE_Configuration");
    int repeater_mode = 0;
    config.getValue(repeater_mode, "EnableRepeaterMode", "RTE_Configuration");
	int use_udpLB = 0;
    config.getValue(use_udpLB, "EnableLoopback", "UDP_Loopback_Configuration");

    // We can finally output some proof-of-life info
    JrInfo << "Hello, and welcome to the JuniorRTE" << std::endl;
    JrInfo << "Using config file: " << config_file << std::endl;

    // For linux, we need to break from the parent's signals
#ifndef WINDOWS
    setsid();
#endif

    // Create the public socket that allows APIs to find us.
    JrSocket* publicSocket = new JrSocket(std::string("JuniorRTE"));
    if ((publicSocket == NULL) || (publicSocket->initialize(config) != Transport::Ok))
    {
        JrError << "Unable to initialize internal socket.  Exiting ...\n";
        exit(1);
    }

    // Catch the termination signals
    signal( SIGINT, handle_exit_signal );
    signal( SIGTERM, handle_exit_signal );
    signal( SIGABRT, handle_exit_signal );

    // Maintain a list of connected clients.  Note that we store the
    // raw unsigned long, rather than the JAUS_ID, so that operator== means
    // "strictly equal to".  This allows us to detected when a message is for a local
    // client, and a local client only (it contains no wildcard characters).
	std::list<unsigned int> _clients;

    // Create a list of all supported transports.
    std::list<Transport*> _transports;
    std::list<Transport*>::iterator _iter;
    _transports.push_back(publicSocket);

    // Add UDP, if selected
    if (use_udp)
    {
		// Create the transport object, initialize it, then add it to the list.
		JUDPTransport *udp = new JUDPTransport;
		if (udp != NULL)
		{
			if (udp->initialize(config) != Transport::Ok)
			{
				JrInfo << "Unable to initialize UDP communications.\n";
			}
			else
				_transports.push_back(udp);
		}
    }
    else
    {
        JrInfo << "UDP communication deactivated in configuration file\n";
    }

    // Add UDP Loopback, if selected
    JUDPTransportLB *udpLB = NULL;
    if ( use_udpLB )
    {
        // Create the transport object, initialize it
        udpLB = new JUDPTransportLB;
        if (udpLB != NULL)
        {
            if (udpLB->initialize(config) != Transport::Ok)
            {
                JrInfo << "Unable to initialize UDP Loopback communications.\n";
            }
			else
				_transports.push_back(udpLB);
        }
    }
    else
    {
        JrInfo << "UDP Loopback communication deactivated in configuration file\n";
    }

    // Add TCP, if selected
    if (use_tcp)
    {
		JTCPTransport *tcp = new JTCPTransport;
		if (tcp != NULL)
		{
			if (tcp->initialize(config) != Transport::Ok)
			{
				JrInfo << "Unable to initialize TCP communications.\n";
			}
			else
				_transports.push_back(tcp);
		}
    }
    else
    {
        JrInfo << "TCP communication deactivated in configuration file\n";
    }

    // Add Serial, if selected
	if (use_serial == 0)
        JrInfo << "Serial communication deactivated in configuration file\n";
    while (use_serial > 0)
    {
		use_serial--; // decrement the count for the next loop

		// instantiate a new serial transport.
		JSerial *serial = new JSerial;
		if (serial == NULL) continue;

		// Since we can support multiple serial connections, each
		// one must have an zero-based index associated with it.
		JrDebug << "Initializing serial interface #" << use_serial << "\n";
        if (serial->initialize(config, use_serial) != Transport::Ok)
        {
            JrInfo << "Unable to initialize serial communications.\n";
        }
        else
            _transports.push_back(serial);
    }

    // Predefine a list of messages we receive from the transports.
    MessageList msglist;
    Message* msg;

    // Process messages.
    while(!exit_flag)
    {
        // Wait a bit so we don't hog the CPU
        JrSleep(delay);

        // Check the public socket for outgoing requests
        publicSocket->recvMsg(msglist);
        while (!msglist.empty())
        {
            // Get the first message from the list
            msg = msglist.front();
            msglist.pop_front();

            // Process the message request
            if ((msg->getDestinationId().val == 0) &&
                (msg->getMessageCode() == Connect))
            {
                // Connection request from client.
				JrDebug << "RTE got connection request from " << msg->getSourceId().val << std::endl;
                Message response;
                response.setSourceId(0);
                response.setDestinationId(msg->getSourceId());
                response.setMessageCode(Accept);
                publicSocket->sendMsg(response);
                _clients.push_back(msg->getSourceId().val);
            }
            else if ((msg->getDestinationId().val == 0) &&
                     (msg->getMessageCode() == Cancel))
            {
                // Disconnect client.
				JrDebug << "RTE got disconnect request from " << msg->getSourceId().val << std::endl;
                _clients.remove(msg->getSourceId().val);
                publicSocket->removeDestination(msg->getSourceId());
            }
            else
            {
                // If the destination contains no wildcards, try to send it
                // as a point-to-point message.
                bool matchFound = false;
                if (!msg->getDestinationId().containsWildcards())
                {
                    // Send this message to the recipients on any transport.
                    for (_iter = _transports.begin(); _iter != _transports.end(); ++_iter)
                    {
                        if ((*_iter)->getName() != "JUDP-LB") // skip if the loopback transport
                        {
                            Transport::TransportError result = (*_iter)->sendMsg(*msg);
                            if (result != Transport::AddrUnknown)
                            {
                                matchFound = true;
                                // send out the loopback if JSockets or JSerial transport matchFound to capture with Wireshark
                                if (use_udpLB) {
                                    if ((*_iter)->getName() == "JSocket" || (*_iter)->getName() == "JSerial") {
                                        JrDebug << "Match found on " << (*_iter)->getName() << " - Sending out loopback" << std::endl;
                                        if ( use_udpLB == 1) udpLB->sendMsg(*msg);
                                        else if ( use_udpLB == 2) udpLB->broadcastMsg(*msg);
                                    }
                                }
                            }
                        }
                    }

                    // If we don't have an entry in our address book, broadcast this message.
                    // First set the ack/nak bit, though, so that if we do find the endpoint
                    // we can learn its address.
                    if (!matchFound)
                    {
                        JrInfo << "Destination id (" << msg->getDestinationId().val <<
                            ") unknown.  Switching to broadcast.\n";
                        msg->setAckNakFlag(1);
                    }
                }

                // If the destination contains wildcards, or we didn't find
                // a match, broadcast it.
                if (msg->getDestinationId().containsWildcards() || !matchFound)
                {
                    // Send this message to all recipients on all transports.
					msg->setBroadcast(2);
                    for (_iter = _transports.begin(); _iter != _transports.end(); ++_iter)
                        if ((*_iter)->getName() != "JUDP-LB") (*_iter)->broadcastMsg(*msg);
                }
            }

            // Done with this message.
            delete msg;
        }

        // Now check receive messages on all other transports
        for (_iter = _transports.begin(); _iter != _transports.end(); ++_iter)
        {
            // Don't check the socket in this loop, since we already did it.
            if (_iter == _transports.begin()) continue;
            (*_iter)->recvMsg(msglist);
            while (!msglist.empty())
            {
                // Get the first message from the list
                msg = msglist.front();
                msglist.pop_front();

                // In repeater mode, the Junior RTE will broadcast any incoming message
                // on all interfaces.  THIS MODE SHOULD BE USED WITH CAUTION!!!
                // If multiple junior instances are set to repeater mode, network traffic
                // will continuous bounce between them until the end of time.
                if (repeater_mode)
                {
                    JrDebug << "Repeating message from " << msg->getSourceId().val <<
                        " to " << msg->getDestinationId().val << " (seq " <<
                        msg->getSequenceNumber() << ")\n";

                    for ( std::list<Transport*>::iterator tport = _transports.begin();
                        tport != _transports.end(); ++tport)
                        if ((*tport)->getName() != "JUDP-LB") (*tport)->broadcastMsg(*msg);
                }
                // If relay is off, or this message is intended for a local client (and a
                // local client only), send it only on the socket interface.
                else if (!allowRelay ||
                    (std::find(_clients.begin(), _clients.end(), msg->getDestinationId().val) !=
                    _clients.end()))
                {
                    // Match found.  Send to the socket interface.
                    publicSocket->sendMsg(*msg);
                }
                // Otherwise, forward this message on all channels (unless it originated locally)
                else if (std::find(_clients.begin(), _clients.end(), msg->getSourceId().val) ==
                          _clients.end())
                {
                    JrDebug << "Trying to forward message from " << msg->getSourceId().val <<
                        " to " << msg->getDestinationId().val << " (seq " <<
                        msg->getSequenceNumber() << ")\n";
                    for ( std::list<Transport*>::iterator tport = _transports.begin();
                        tport != _transports.end(); ++tport)
                        if ((*tport)->getName() != "JUDP-LB") (*tport)->sendMsg(*msg);
                }
                
                // If the transport is JSerial, forword the recived msg out the loopback port
                if ((*_iter)->getName() == "JSerial")
                {
                    if ( use_udpLB == 1) udpLB->sendMsg(*msg);
                    else if ( use_udpLB == 2) udpLB->broadcastMsg(*msg);
                }

                // Done processing this message
                delete msg;
            }
        }
    }

    // Received termination signal.  Clean-up
    JrInfo << "Shutting down Junior RTE...\n";
    for (_iter = _transports.begin(); _iter != _transports.end(); ++_iter)
        delete (*_iter);
}
