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


