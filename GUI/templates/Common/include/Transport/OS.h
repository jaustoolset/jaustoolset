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
#ifndef __ABSSTRACT_OS_H
#define __ABSSTRACT_OS_H

#include <string>
#include <list>
#if (defined WINDOWS) || (defined WIN32)
#ifndef WINDOWS
#define WINDOWS
#endif
    #include "Winsock.h"
    typedef int socklen_t;
    typedef signed __int8     int8_t;
    typedef signed __int16    int16_t;
    typedef signed __int32    int32_t;
    typedef unsigned __int8   uint8_t;
    typedef unsigned __int16  uint16_t;
    typedef unsigned __int32  uint32_t;
	typedef signed __int64    int64_t;
    typedef unsigned __int64  uint64_t;
#ifndef DllExport
#define DllExport __declspec( dllexport )
#endif
#else
    #include <sys/socket.h>
    #include <unistd.h>
    #include <netdb.h>
    #include <sys/un.h>
    #include <arpa/inet.h>
    #include <sys/time.h>
    #include <strings.h>
    #include <errno.h>
    #include <termios.h>
    #include <pthread.h>
    #include <stdlib.h>
    #include <cstdlib>
    #include <stdint.h>
	#include <signal.h>
	#include <time.h>
#ifndef NO_IF_ADDRS
#ifndef __CYGWIN__
    #include <ifaddrs.h>
#endif
#endif
#ifndef DllExport
#define DllExport
#endif
#endif

namespace DeVivo {
namespace Junior {

void DllExport JrSleep(unsigned long milliseconds);
void DllExport JrSpawnProcess(std::string path, std::string arg);
int64_t DllExport JrSpawnThread(void*(*func_ptr)(void*), void* func_arg);
void DllExport JrKillThread(int64_t thread);
unsigned long DllExport JrGetTimestamp();
std::list<unsigned int> DllExport JrGetIPAddresses();
bool DllExport JrStrCaseCompare(std::string str1, std::string str2);

class DllExport JrSignal
{
  public:
	JrSignal();
	~JrSignal();
	void wait();
	void signal();

  protected:
#ifdef WINDOWS
    HANDLE condvar;
#else
	pthread_mutex_t mutex;
	pthread_cond_t condvar;
#endif
};

class DllExport JrMutex
{
  public:
	JrMutex();
	~JrMutex();
	void lock();
	void unlock();

  protected:
#ifdef WINDOWS
    HANDLE mutex;
#else
    pthread_mutex_t mutex;
#endif
};

class DllExport JrTimer
{
  public:
	JrTimer(void (*func_ptr)(void*), void* func_arg, unsigned int timeout_ms);
	~JrTimer();
	void start();
	void stop();
	unsigned int getTimeout(){return timeout_ms;};
	void call_user_function();

  protected:
	void (*func_ptr)(void*);
    void* func_arg;
    unsigned int timeout_ms;

#if defined(WINDOWS) 
  HANDLE tHandle;
#elif defined(__MAC__)
  int threadId;
#else
   timer_t timerid;
#endif
};

}} // namespace DeVivo::Junior
#endif


