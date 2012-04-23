test/README.txt

Last updated: Tue Mar 15 12:43:40 PDT 2011

This directory contains test files used by the unittest.sh script.

Prerequisites
---------------------------

Make sure your test environment is set up according the README.txt in the parent (install) directory.

Make sure to read the information in this main README.txt.


Test script
---------------------------

unittest.sh translates each .jaus file to a JSIDL v1.1 compliant .xml file, then runs xmllint to validate.


Test JAUS files
----------------------------

The test files ARE NOT consistent with any published specifications.  They are simply sample files used for informal testing.

6091_AckermanDriver.jaus
BasicTypeSet.jaus
DigitalAudioSensor.jaus
MissionSpooler.jaus
MissionManagement.jaus
include_test_1.jaus


Include Files
-----------------------------

The files in the include subdirectory are fragments that are included in-line whereever a "// #include <filename>""
appears on a line by itself. Filenames are resolved by searching the local directory, then the paths in $CJSIDL_INCLUDE.

include/BasicTypes.jaus
include/include_test_1_1.jaus
include/include_test_1_1_1.jaus


You should get the following output when you run the unit test.

$./unittest.sh
------------------------------------------Translating and validating  6091_AckermanDriver.jaus
6091_AckermanDriver.xml validates
------------------------------------------Translating and validating  BasicTypeSet.jaus
BasicTypeSet.xml validates
------------------------------------------Translating and validating  MissionSpooler.jaus
Processing #include BasicTypes.jaus
Found include file=./include/BasicTypes.jaus
MissionSpooler.xml validates
------------------------------------------Translating and validating  include_test_1.jaus
Processing #include include_test_1_1.jaus
Found include file=./include/include_test_1_1.jaus
Processing #include include_test_1_1_1.jaus
Found include file=./include/include_test_1_1_1.jaus
include_test_1.xml validates
------------------------------------------Translating and validating  DigitalAudioSensor.jaus
DigitalAudioSensor.xml validates
------------------------------------------Translating and validating  MissionManagement.jaus
Processing #include BasicTypes.jaus
Found include file=./include/BasicTypes.jaus
MissionManagement.xml validates
