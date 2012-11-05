/*! 
 ***********************************************************************
 * @file      OS.h
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
int DllExport JrSpawnThread(void*(*func_ptr)(void*), void* func_arg);
void DllExport JrKillThread(int thread);
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


