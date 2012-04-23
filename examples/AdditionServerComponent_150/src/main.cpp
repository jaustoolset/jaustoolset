

#include <iostream>
#include <signal.h>
#include "Transport/OS.h"
#include "AdditionServerComponent.h"

// Create a static signal to catch interrupts
static DeVivo::Junior::JrSignal exit_signal;
static void handle_exit_signal( int signum )
{
    exit_signal.signal();
}

int main(int argc, char *argv[])
{
    // Instantiate the component and start it.
    AdditionServerComponent* cmpt = new AdditionServerComponent(126, 1, 150);
	
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
