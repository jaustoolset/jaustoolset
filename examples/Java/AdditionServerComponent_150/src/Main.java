
package src;

import framework.transport.OS.JrSignal;
import sun.misc.Signal;
import sun.misc.SignalHandler;
import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	private static Logger logger = Logger.getLogger("AdditionServerComponent_log");

	// Create a static signal to catch interrupts
	private static JrSignal exit_signal = new JrSignal();
	private static boolean exit = false;

	static SignalHandler handler = new SignalHandler()
	{
        public void handle(Signal signal)
        {
			exit = true;
        }
	};
	
	public static void main(String[] args)
	{
		try
		{
	    	// Instantiate the component and start it.
	    	AdditionServerComponent cmpt = new AdditionServerComponent(126, (short) 1, (short) 150);
	
			// Catch exit signals
        	Signal.handle(new Signal("INT"), handler);
        	Signal.handle(new Signal("TERM"), handler);
        	Signal.handle(new Signal("ABRT"), handler);
	
			// Start the component and the services
			cmpt.startComponent();
	
			while(!exit)
			{
				// Throttle
				Thread.sleep(1);
			}

			// Shutdown the component and threads, then exit the program.
			cmpt.shutdownComponent();
			System.exit(0);

		}
		catch( Exception e)
		{
			logger.log(Level.SEVERE, null, e);
		}
	}
}
