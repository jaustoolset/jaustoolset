# Run this file with '.' from the cjsidlTools root directory
#
# This is for a cygwin environment.  Modify if you are running on linux or under cmd.exe
#
CLSPATH=".:`pwd`/runtime/antlr-3.1.1/lib/antlr-3.1.1.jar:`pwd`/runtime/antlr-3.1.1/lib/antlr-runtime-3.1.1.jar:`pwd`/runtime/antlr-3.1.1/lib/gunit.jar:`pwd`/runtime/antlr-3.1.1/lib/stringtemplate-3.2.jar"

if [ "x$OSTYPE" = "xcygwin" ]; then
    export CLASSPATH=`cygpath -w -p $CLSPATH`
else
    export CLASSPATH=$CLSPATH
fi