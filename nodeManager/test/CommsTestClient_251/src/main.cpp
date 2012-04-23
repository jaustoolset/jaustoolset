/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, United States Government
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the United States Government nor the names of 
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



#include <iostream>
#include <signal.h>
#include "OS.h"
#include "CommsTestClient.h"

using namespace std;

// Create a static signal to catch interrupts
static DeVivo::Junior::JrSignal exit_signal;
static void handle_exit_signal( int signum )
{
    exit_signal.signal();
}

int main(int argc, char *argv[])
{
    // Instantiate the component and start it.
    CommsTestClient* cmpt = new CommsTestClient(126, 1, 251);
	
	// Catch exit signals
	signal( SIGINT, handle_exit_signal );
    signal( SIGTERM, handle_exit_signal );
    signal( SIGABRT, handle_exit_signal );
	
	// Start the component and the services
    cmpt->startComponent();
	
	// Wait until signaled to exit
    exit_signal.wait();

	// Shutdown the component and threads
    cmpt->shutdownComponent();

	// Give a little time for proper shutdown
	DeVivo::Junior::JrSleep(100);

	// Free the component
	delete cmpt;
}
