/*! 
 ***********************************************************************
 * @file      JrLogger.h
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
#ifndef  __JR_LOGGER_H
#define  __JR_LOGGER_H

#include <string>
#include <fstream>
#include <iostream>


// USE THESE MACROS FOR ALL LOG OUTPUT
#define JrError (Logger::get()->startMsg(__FILE__, __LINE__, Logger::error))
#define JrInfo (Logger::get()->startMsg(__FILE__, __LINE__, Logger::info))
#define JrWarn (Logger::get()->startMsg(__FILE__, __LINE__, Logger::warning))
#define JrDebug (Logger::get()->startMsg(__FILE__, __LINE__, Logger::debug))
#define JrFull (Logger::get()->startMsg(__FILE__, __LINE__, Logger::full))

namespace DeVivo {
namespace Junior {

class Logger
{
public:
    Logger(){_level=none;}
    ~Logger(){closeOutputFile();}

    // Define enumeration for debug levels
    enum LogMsgType {none = 0, error, info, warning, debug, full};
    std::string enum2Str(enum LogMsgType type);

    // Logger is a static class (one per process).  We 
    // supply a function to get the only instance.
    static Logger* get();

    // Functions for getting the active stream
    std::ostream& getStream(enum LogMsgType type);

    // Function to start a new log entry.  This 
    // return the stream, but also inserts standard debugging text
    std::ostream& startMsg(std::string filename, int line, enum LogMsgType type);

    // Functions to change the debug level
    enum LogMsgType getMsgLevel(){return _level;}
    void setMsgLevel(enum LogMsgType level){_level = level;}

    // Functions to open and close the output file
    void openOutputFile(std::string filename);
    void closeOutputFile();

protected:

    // Current debug level
    enum LogMsgType _level;

    // We use both a real file stream and a "null stream"
    // that doesn't output anything
    std::fstream filestream, nullstream;
};


// Define an inlines to get the static Logger object
inline Logger* Logger::get()
{
    static Logger logger;
    return &logger;
}

}} // namespace DeVivo::Junior
#endif


