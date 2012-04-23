cjsidlTools - Tools for working with Compact JSIDL for AS-4 JAUS
Copyright 2011 Jim Albers

Last updated: Tue Mar 15 12:43:59 PDT 2011


Help
============

If your installation is successful and you are having issues, please
take a close look at the contents of the ./test directory.  Make sure
you can run the unittest.sh script and get the results shown in
test/README.txt.

Developers
============

Do not commit any changes to the cjsidlTools repository unless your
code passes the ./test/unittest.sh.

If you discover and fix any bugs, make sure to add to the unit test a
.jaus file that demonstrates the bug, and shows that it is resolved.

Known Issues
============

1. If the CJSIDL_INCLUDE path includes the current directory, and an
include file is found in the current directory, it will be included
twice.

Workaround: keep all include files in as separate ./include subdirectory.
See how the ./test unit test subdir is set up.

2. The Translator does not yet handle namespaces completely, so type
references like 'CoreTypes.TimeStamp' are not (yet) properly resolved.

Workaround: See how the .jaus files in ./test use the include file
./test/include/BasicTypes.jaus.

In particular, for the AS5710 core types, you should use
BasicTypes.jaus as shown in the test .jaus files.


Installation
============

Prerequisites:

Python 2.5/2.6
Java 1.5 or later

Python lxml etree package (for DOM processing)

Install setuptools (http://pypi.python.org/pypi/setuptools), then:

$ easy_install lxml

You also need to install the Antlr3 Python runtime from the runtime/antlr-3.1.1 directory
in this package:

$ cd runtime/antlr-3.1.1/runtime/Python/
$ python setup.py install


Create the Parser/Translator Utility
====================================

To create the jaus2jsidl translator, run the antlr Tool on the grammar file.  The warning is expected in this version.

$ . setupjava.sh
$ cd grammar
$ java org.antlr.Tool jaus2jsidl.g
ANTLR Parser Generator  Version 3.1.1
warning(200): jaus2jsidl.g:1936:66: Decision can match input such as "SL_COMMENT" using multiple alternatives: 1, 2
As a result, alternative(s) 2 were disabled for that input


This will produce the three files: 
     jaus2jsidl.tokens
     jaus2jsidlLexer.py
     jaus2jsidlParser.py

Translating CJSIDL to JSIDL -- Using the Parser/Translator directly
===================================================================

Run the translator by specifying the input and output:

$ python jaus2jsidlParser.py --rule=jaus < CJSIDL FILE > JSIDL_FILE

For example:

$ python jaus2jsidlParser.py --rule=jaus < ../test/6091_AckermanDriver.jaus > ../test/6091_AckermanDriver.xml

Translating CJSIDL to JSIDL -- Using the jaus2jsidl wrapper
===========================================================

The jaus2jsidl wrapper is used exactly like the Parser/Translator jaus2jsidlParser.py.

However, this wrapper will include referenced text files in-line
whenever it sees a directive like:

// #include <filename>

The environment variable CJSIDL_INCLUDE optionally provides a search
path for <filename>.  For example, with a current checkout, you can
run the following test:

$ export CJSIDL_INCLUDE=../test:/home/jalbers/git/neya/as6091
$ python jaus2jsidl --rule=jaus < ../test/include_test_1.jaus > ../test/out.xml
Processing #include include_test_1_1.jaus
Found include file=..\test\include_test_1_1.jaus
Processing #include include_test_1_1_1.jaus
Found include file=..\test\include_test_1_1_1.jaus

The CJSIDL_INCLUDE can be either in DOS or UNIX format, so this method
should work under bash/linux, bash/cygwin, or cmd.exe.

This particular test used bash/cygwin with sys.platform == 'win32'
(hence os.sep == '\\').

Translating from JSIDL XML to CJSIDL
====================================

To reverse-engineer a collection of XML, go to the root of the XML
directory, and run combine_jsidl.py.  This will process all the .xml
files in the directory and its subdirectories, and for each
<service_def/> found, will create a file named
<service_name>Combined.xml.

For example:

$ cd $AS4DOCS/AS5710_JSS/urn.jaus.jss.core
$ python combine_jsidl.py .
... diagnostic/progress output & error messages ...
$ ls -l *Combined*
-rwxr-xr-x 1 jalbers None 30721 2011-01-06 08:59 AccessControlCombined.xml
-rwxr-xr-x 1 jalbers None 30067 2011-01-06 08:59 DiscoveryCombined.xml
-rwxr-xr-x 1 jalbers None 27059 2011-01-06 08:59 EventsCombined.xml
-rwxr-xr-x 1 jalbers None 23807 2011-01-06 08:59 ListManagerCombined.xml
-rwxr-xr-x 1 jalbers None  2623 2011-01-06 08:59 LivenessCombined.xml
-rwxr-xr-x 1 jalbers None 23479 2011-01-06 08:59 ManagementCombined.xml
-rwxr-xr-x 1 jalbers None  6365 2011-01-06 08:59 TimeCombined.xml
-rwxr-xr-x 1 jalbers None 13133 2011-01-06 08:59 TransportCombined.xml


Each of these files can be converted into a standalone .jaus file, thus:

$ for i in *Combined.xml; do jausn=`echo $i | sed 's/.xml/.jaus/'`; python jsidl2jausy.py --in $i --out $jausn; done

$ ls -l *.jaus
-rwxr-xr-x 1 jalbers None 15031 2011-01-06 09:02 AccessControlCombined.jaus
-rwxr-xr-x 1 jalbers None 18051 2011-01-06 09:02 DiscoveryCombined.jaus
-rwxr-xr-x 1 jalbers None 14958 2011-01-06 09:02 EventsCombined.jaus
-rwxr-xr-x 1 jalbers None 13864 2011-01-06 09:02 ListManagerCombined.jaus
-rwxr-xr-x 1 jalbers None  1504 2011-01-06 09:02 LivenessCombined.jaus
-rwxr-xr-x 1 jalbers None 11741 2011-01-06 09:02 ManagementCombined.jaus
-rwxr-xr-x 1 jalbers None  3520 2011-01-06 09:02 TimeCombined.jaus
-rwxr-xr-x 1 jalbers None  7201 2011-01-06 09:02 TransportCombined.jaus


IMPORTANT NOTE: Round-tripping CJSIDL files does not preserve the multi-file, hierarchical organization of the JSIDL XML filesets.

Future versions of CJSIDL will support declared_type_sets.

Validating JSIDL XML
====================

This package includes an RNG schema taken from a recent JTS release.  The files schema/jsidl_plus.rnc is the master, schema/jsidl_plus.rng is the XML formatted version of same created using the trang utility.

To validate a JSIDL XML file, make sure that you have a working xmllint utility installed, then:

$ xmllint --relaxng schema/jsidl_plus.rng <myfile>

The xmllint utility dumps a pretty-printed output of the XML and reports on its validity.  To get just the result:

$ xmllint --relaxng schema/jsidl_plus.rng <myfile> | grep <myfile>

For example:

$ xmllint --relaxng ../schema/jsidl_plus.rng AS5710BasicTypes.xml | grep AS5710BasicTypes.xml
AS5710BasicTypes.xml validates





