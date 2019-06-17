

#include <iostream>
#include <signal.h>
#include "Transport/OS.h"
#include "Discovery.h"

// Create a static signal to catch interrupts
static DeVivo::Junior::JrSignal exit_signal;
static void handle_exit_signal( int signum )
{
    exit_signal.signal();
}

int main(int argc, char *argv[])
{
	// Pull the JAUS IDs, if specified on the command line
    int ssid = 0, nid = 100, cid = 100;
    if (argc > 1) sscanf(argv[1], "%d", &ssid);
    if (argc > 2) sscanf(argv[2], "%d", &nid);
    if (argc > 3) sscanf(argv[3], "%d", &cid);
    printf("Starting with JAUS ID: %d:%d:%d\n", ssid, nid, cid);

    // Instantiate the component and start it.  By default, JAUS (and JTS)
	// don't allow 0 or 255 as part of the JAUS ID.  However, both IOP and 
	// AEODRS make use of those values as part of their dynamic ID discovery 
	// process.  To enable that process, we need to force the JausRouter and
	// underlying JrMiddleware layers to accept wildcards in the IDs
	// by using the optional boolean argument.
    Discovery* cmpt = new Discovery(ssid, nid, cid, true);
	
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
