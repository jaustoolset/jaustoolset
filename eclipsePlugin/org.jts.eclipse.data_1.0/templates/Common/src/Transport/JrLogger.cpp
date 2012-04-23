/*! 
 ***********************************************************************
 * @file      JrLogger.cpp
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



