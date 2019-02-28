/***********           LICENSE HEADER   *******************************
JR Middleware
Copyright (c)  2008-2019, DeVivo AST, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the copyright holder nor the names of 
its contributors may be used to endorse or promote products derived from 
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
*********************  END OF LICENSE ***********************************/

#include "Transport/JrLogger.h"

using namespace DeVivo::Junior;

//
// Return a reference to the active stream.
//
std::ostream& Logger::getStream(enum LogMsgType type)
{
    // Check the message type against the current level.
    // If the message type is too high for the current level,
    // return a closed stream (no output).
    if (type > _level) return nullstream;

    // If the file stream is not open, just use standard out
    return (filestream.is_open() ? filestream : std::cout);
}

//
// Start a new enty on the active stream
//
std::ostream& Logger::startMsg(std::string filename, int line, enum LogMsgType type)
{
    // get the desired stream
    std::ostream& stream = getStream(type);

    // flush any previous messages
    stream.flush();

    // Insert formatted text
    stream << "(" << filename << ", line " << line << ") " << enum2Str(type) << ": ";
    return stream;
}

//
// helper function to convert the numberation to a string literal
//
std::string Logger::enum2Str(enum LogMsgType type)
{
    if (type==error) return "ERROR";
    if (type==info) return "INFO";
    if (type==warning) return "WARNING";
    if (type==debug) return "DEBUG";
    if (type==full) return "FULL";
    return "UNKNOWN";
}

//
// Open the given log file for output
//
void Logger::openOutputFile(std::string filename)
{
    // If the stream is current open, close it.
    closeOutputFile();

    // Open the given filename
    filestream.open(filename.c_str(), std::fstream::out | std::fstream::app);
    if (!filestream.is_open()) 
        JrError << "Unable to open log file: " << filename << std::endl;
}

// 
// closes the log file.  Subsequent log outputs
// will be forced to standard out.
//
void Logger::closeOutputFile()
{
    if (filestream.is_open())
    {
        filestream.flush();
        filestream.close();
    }
}



