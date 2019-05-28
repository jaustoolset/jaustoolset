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
#include "Transport/OS.h"
#include "Transport/JrLogger.h"

using namespace DeVivo::Junior;


void DeVivo::Junior::JrSleep(unsigned long milliseconds)
{
#ifdef WINDOWS
    Sleep(milliseconds);
#else
    usleep(milliseconds * 1000);
#endif
}


void DeVivo::Junior::JrSpawnProcess(std::string path, std::string arg)
{
#ifdef WINDOWS
    // Windows gives us the createProcess function.  We just need to
    // make sure it doesn't already exist.
    std::string cmd = "tasklist | findstr " + path + " > nul\0";
    int ret = system(cmd.c_str());

    if (ret != 0)
    {
        JrInfo << "Trying to CreateProcess " << path << std::endl;
        STARTUPINFO si;
        PROCESS_INFORMATION pi;
        memset(&si, 0, sizeof(STARTUPINFO)); si.cb = sizeof(si);
        memset(&pi, 0, sizeof(PROCESS_INFORMATION));

        // turn off the window
        si.dwFlags = STARTF_USESHOWWINDOW;
        si.wShowWindow = SW_HIDE;

        // Now create the process that points to the path
        cmd = path + " " + arg + "\0";
        BOOL result = CreateProcess(  NULL, LPSTR(cmd.c_str()), NULL, NULL, FALSE, 
            HIGH_PRIORITY_CLASS | CREATE_NEW_PROCESS_GROUP, NULL,  NULL,  &si, &pi);
        if(result == 0)  
            JrError << "Could not create process " << path << std::endl;

        // Wait for it to spool up before returning
        JrSleep(2000);
    }

#else

    // For Unix, we need to fork and manually start the new process
    // First check for existence of the process
    std::string cmd = "ps -e | grep '" + path + "' | grep -v 'grep' >> /dev/null\0";
    int ret = system(cmd.c_str());
    if (ret != 0)
    {
        // Not found.  Start a new process...
        JrInfo << "Starting " << path << std::endl;
        if (fork()==0)
        {
           ret = execl(path.c_str(), path.c_str(), arg.c_str(), NULL);
           if (ret == -1) exit(0); // Getting here means we failed to start the process
        }


        // Wait for it to spool up before returning
        JrSleep(2000);
    }

#endif
}

// Return the current time (in milliseconds)
unsigned long DeVivo::Junior::JrGetTimestamp()
{
#ifdef WINDOWS
    return (unsigned long)(GetTickCount());
#else
    struct timeval tv; struct timezone tz;
    gettimeofday(&tv, &tz);
    return (tv.tv_sec*1000 + (unsigned long)(tv.tv_usec/1000));
#endif
}

// Return a list of IP addresses with all the NIC associate with this host
std::list<unsigned int> DeVivo::Junior::JrGetIPAddresses()
{
    std::list<unsigned int> addresses;

#if defined(WINDOWS) || defined(__CYGWIN__) || defined(NO_IF_ADDRS)

    // Windows doesn't support ioctl calls, and the gethostbyname is a 
    // better method anyway....
    char ac[80];
    if (gethostname(ac, sizeof(ac)) == 0)
    {
        struct hostent *phe = gethostbyname(ac);
        if (phe != 0) 
		{
            for (int i = 0; phe->h_addr_list[i] != 0; ++i) 
                addresses.push_back(((in_addr*)phe->h_addr_list[i])->s_addr);
    }
		else
		{
			JrError
				<< "on '" << ac << "', gethostbyname fails"
				<< " (so no port identification/searching)"
				<< std::endl;
		}
    }
	else
	{
		JrError
			<< "could not determine host's name (so no port identification/searching)"
			<< std::endl;
	}

#else

    // On Linux, we can use getifaddrs supported by BSD libraries.
    struct ifaddrs *ifap, *next;
    if (getifaddrs(&ifap) != 0) return addresses;
    if (ifap == NULL) return addresses;

    // Loop through each interface, adding the address to the list
    next = ifap;
    do
    {
        if ( (next->ifa_addr != NULL) && (next->ifa_addr->sa_family == AF_INET)  &&
             ((((sockaddr_in*) next->ifa_addr)->sin_addr.s_addr) !=
                        inet_addr("127.0.0.1")))
            addresses.push_back(((sockaddr_in*) next->ifa_addr)->sin_addr.s_addr);
        next = next->ifa_next;
    } while (next != NULL);

    // We need to free the memory allocated by getifaddrs
    freeifaddrs(ifap);

#endif

   return addresses;
}

bool DeVivo::Junior::JrStrCaseCompare(std::string str1, std::string str2)
{
#ifdef WINDOWS
    return 
        (CompareString(LOCALE_SYSTEM_DEFAULT, NORM_IGNORECASE,
          str1.c_str(), str1.size(), str2.c_str(), str2.size())==2);
#else
    if (str1.size() != str2.size()) return false;
    return ((bool)(!strncasecmp(str1.c_str(), str2.c_str(), str1.size())));
#endif
}

int DeVivo::Junior::JrSpawnThread(void*(*func_ptr)(void*), void* func_arg)
{
#ifdef WINDOWS
    return (int) CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE) func_ptr, func_arg, 0, NULL);
#else
    pthread_t thread_info;
    pthread_attr_t attr;
    pthread_attr_init(&attr);
    pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_DETACHED);    
    if(pthread_create(&thread_info, &attr, func_ptr, func_arg) != 0)
        JrError << "Could not create thread\n";
    pthread_attr_destroy(&attr);
#ifdef __MAC__
    return 0;
#else
    return (int) thread_info;
#endif
#endif
}

void DeVivo::Junior::JrKillThread(int thread)
{
#ifdef WINDOWS
    TerminateThread((HANDLE) thread, 0);
    CloseHandle((HANDLE) thread);
#else
    pthread_join((pthread_t) thread, NULL);
#endif
}

DeVivo::Junior::JrMutex::JrMutex()
{
#ifdef WINDOWS
    mutex = CreateMutex(NULL, false, NULL);
#else
    pthread_mutex_init(&mutex, NULL);
#endif
}

DeVivo::Junior::JrMutex::~JrMutex()
{
#ifdef WINDOWS
    if (mutex) CloseHandle(mutex);
#else
    pthread_mutex_destroy(&mutex);
#endif
}

void DeVivo::Junior::JrMutex::lock()
{
#ifdef WINDOWS
    WaitForSingleObject(mutex, INFINITE);
#else
    pthread_mutex_lock(&mutex);
#endif
}

void DeVivo::Junior::JrMutex::unlock()
{
#ifdef WINDOWS
    ReleaseMutex(mutex);
#else
    pthread_mutex_unlock(&mutex);
#endif
}

DeVivo::Junior::JrSignal::JrSignal()
{
#ifdef WINDOWS
    condvar = CreateSemaphore(NULL, 0, 0x7fffffff, NULL);
#else
    pthread_mutex_init(&mutex, NULL);
    pthread_cond_init(&condvar, NULL);
#endif
}

DeVivo::Junior::JrSignal::~JrSignal()
{
#ifdef WINDOWS
    if (condvar) CloseHandle(condvar);
#else
    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&condvar);
#endif
}

void DeVivo::Junior::JrSignal::wait()
{
#ifdef WINDOWS
    WaitForSingleObject(condvar, INFINITE);
#else
    pthread_mutex_lock(&mutex);
    pthread_cond_wait(&condvar, &mutex);
    pthread_mutex_unlock(&mutex);
#endif
}

void DeVivo::Junior::JrSignal::signal()
{
#ifdef WINDOWS
    ReleaseSemaphore(condvar, 1, NULL);
#else
    pthread_mutex_lock(&mutex);
    pthread_cond_signal(&condvar);
    pthread_mutex_unlock(&mutex);
#endif
}

#if defined(WINDOWS) 
VOID CALLBACK TimerRoutine(PVOID arg, BOOLEAN TimerOrWaitFired)
{
    ((DeVivo::Junior::JrTimer*) arg)->call_user_function();
}
#elif defined(__MAC__)
static void* watchForTimeout(void* arg)
{
    // Brute force.  Sleep until we pass the elapsed time.
    unsigned long start = JrGetTimestamp();
    while ((JrGetTimestamp() - start) < ((DeVivo::Junior::JrTimer*) arg)->getTimeout())
        DeVivo::Junior::JrSleep(1);
    ((DeVivo::Junior::JrTimer*) arg)->call_user_function();
    return 0;
}
#else
static void handleTimeout(sigval_t sig)
{
    // We don't want the user to have to mess with the
    // stupid signal_t stuff, so provide translation.
    ((DeVivo::Junior::JrTimer*) sig.sival_ptr)->call_user_function();
}
#endif


DeVivo::Junior::JrTimer::JrTimer(void (*func_ptr)(void*), 
                                 void* func_arg, 
                                 unsigned int timeout)
{
    this->func_ptr = func_ptr;
    this->func_arg = func_arg;
    timeout_ms = timeout;

#if defined(WINDOWS) 
    tHandle = 0;
#elif defined(__MAC__)
    threadId = 0;
#else
    timerid = 0;
    struct sigevent sev;
    sev.sigev_notify = SIGEV_THREAD;
    sev.sigev_notify_function = handleTimeout;
    sev.sigev_value.sival_ptr = this;
    sev.sigev_notify_attributes = NULL;
    int result = timer_create(CLOCK_REALTIME, &sev, &timerid);
#endif
}

DeVivo::Junior::JrTimer::~JrTimer()
{
#if defined(WINDOWS) || defined(__MAC__)
    stop();
#else
    timer_delete(timerid);
#endif
}

void DeVivo::Junior::JrTimer::start()
{
    // stop any existing timers...
    stop();

#if defined(WINDOWS)
    CreateTimerQueueTimer( &tHandle, NULL, TimerRoutine, this, timeout_ms, 0, 0);
#elif defined(__MAC__)
    // Spawn a thread that sleeps for the requested duration.
    // We do this since the Mac API doesn't have a lot
    // of good options for asynchronous timers.
    threadId = DeVivo::Junior::JrSpawnThread(watchForTimeout, this);
#else
    // posix timers.
    struct itimerspec interval;
    interval.it_value.tv_sec = timeout_ms / 1000;
    interval.it_value.tv_nsec = (timeout_ms % 1000) * 1000000;
    interval.it_interval.tv_sec = 0;
    interval.it_interval.tv_nsec = 0;

    timer_settime(timerid, 0, &interval, NULL);
#endif
}

void DeVivo::Junior::JrTimer::stop()
{
#if defined(WINDOWS) 
    if (tHandle != 0) 
    {
        DeleteTimerQueueTimer(NULL, tHandle, NULL);
        CloseHandle(tHandle);
        tHandle = 0;
    }
#elif defined(__MAC__)
    if (threadId != 0) 
    {
        JrKillThread(threadId);
        threadId = 0;
    }
#else
    struct itimerspec interval;
    interval.it_value.tv_sec = 0;
    interval.it_value.tv_nsec = 0;
    interval.it_interval.tv_sec = 0;
    interval.it_interval.tv_nsec = 0;

    timer_settime(timerid, 0, &interval, NULL);
#endif
}

void DeVivo::Junior::JrTimer::call_user_function()
{
#if defined(__MAC__)
    // thread died on run to completion.
    threadId = 0;
#endif
    func_ptr(func_arg);
}
