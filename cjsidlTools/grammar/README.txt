To create the working parser/translator in this directory:

$ cd ..
$ . setupjava.sh
$ cd grammar
$ java org.antlr.Tool jaus2jsidl.g

After a succesful parser generation, you will see three more files:

      ./jaus2jsidl.tokens
      ./jaus2jsidlLexer.py
      ./jaus2jsidlParser.py

Now you can run the jaus2jsidl parser/translator as follows.

Suppose your .jaus files are in a directory named $CJD. Set
CJSIDL_INCLUDE to a path that includes all the directories for all
#include files.

$ CJSIDL_INCLUDE=$CJD python jaus2jsidl --rule=jaus < $CJD/MissionManagement.jaus

This will create an output file in the current directory, its name
determined by the service name attribute (which is usually the same as
the filename without .jaus extension).

    MissionManagement.xml

You will also see a declared_type_set file for each type set #included
in the main .jaus file, in this case:

   MMSTypes.xml

The main service definition file and its attendent type files should
be moved to a newly created directory where they are the only files.

In JTS, right click on the Service Defs icon, right click, and select
Import from JSIDL.

Select your new directory, and click OK.


