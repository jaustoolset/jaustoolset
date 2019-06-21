By default, the generated code on which these examples are based builds an executable and several dynamic (shared) libraries.  To run the executable, the shared libraries must be available at run-time.  

The build scripts automatically copy the shared libraries to the bin directory, such that they are co-located with the executable.  

For Windows environments, including Cygwin, this is sufficient and no additional user action is required.

For many flavors of Linux, however, a co-located shared library may still not be found at run-time.  For this reason, we recommend modifying the library path using the $LD_LIBRARY_PATH environment variable:

		export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:'.'

Otherwise, all shared libraries must be copied to a run-time accessible location (such as /lib or /usr/lib) to execute the generated code.

Please see Section 4.1.9 of the User's Guide for additional information.