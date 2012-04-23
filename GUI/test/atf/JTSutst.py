#***********           LICENSE HEADER   *******************************
#JAUS Tool Set
#Copyright (c)  2010, United States Government
#All rights reserved.
#
#Redistribution and use in source and binary forms, with or without
#modification, are permitted provided that the following conditions are met:
#
#       Redistributions of source code must retain the above copyright notice,
#this list of conditions and the following disclaimer.
#
#       Redistributions in binary form must reproduce the above copyright
#notice, this list of conditions and the following disclaimer in the
#documentation and/or other materials provided with the distribution.
#
#       Neither the name of the United States Government nor the names of
#its contributors may be used to endorse or promote products derived from
#this software without specific prior written permission.
#
#THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
#AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
#IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
#ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
#LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
#CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
#SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
#INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
#CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
#ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
#POSSIBILITY OF SUCH DAMAGE.
#*********************  END OF LICENSE ***********************************

#------------------------------------------------------------
# JTS_UTST module  -- Mark Bofill 041509
# Rewritten by Ian Durkan, 03/04/2011
#------------------------------------------------------------
# JTS unit test support module
#------------------------------------------------------------
# Description:  This module provides support functions 
#    for building and running cppunit tests around the
#    JTS code generator.
#------------------------------------------------------------

import os
import os.path
import re
import sys
import subprocess
import shutil
import time
import datetime
import tempfile
import stat

classpath_re = re.compile("JAVACLASSPATH = \[.*'(\]\))")

def on_rmtree_error(func, path, exc_info):
    """
    Error handler for shutil.rmtree.  on Windows XP system I found that
    some contents of .svn directories are read-only, and shutil.rmtree throws an
    exception when trying to delete them even though it runs as 'me', and I have permission
    to delete those directories.
    """
    if not os.access(path, os.W_OK):
        # Is the error an access error?  grant Python-user write access to the file being deleted.
        os.chmod(path, stat.S_IWUSR)
        func(path)
    else:
        raise

#------------------------------------------------------------
# function localprivate_FileExists(fname)
#
# returns true if a file can be opened for reading
# (test to see if it exists)
#------------------------------------------------------------

def localprivate_FileExists(fname):
    try:
        f = open(fname,"r")
        f.close()
        retval = True
    except IOError:
        retval = False
        
    return retval

#------------------------------------------------------------
# function localprivate_HasUnitTestMods(r)
#
# determine if Sconstruct has already
# been modified for unit test builds
#------------------------------------------------------------
def localprivate_HasUnitTestMods(r):
    """determine if Sconstruct has already 
       been modified for unit test builds"""
    
    s=r[0]
    if (len(s)>=54) and (s[0:54]=="#UnitTestFixed041509(Do not edit or remove this line!)"): 
       retval = True
    else:
       retval = False
    return retval
    
#------------------------------------------------------------
# function localprivate_fixSStr_main2utst(s,utf)
#
# replaces main.cpp with sourcefile utf
#------------------------------------------------------------
def localprivate_fixSStr_main2utst(s,utf):
    """replaces main.cpp with sourcefile utf argument"""
    if (len(s)>=8):
       for i in range(0,len(s)):
          if (s[i:i+8]=="main.cpp"):
             j=i
             while s[j:j+1]!="'":
                 j=j-1
             r=s[:j+1] + utf + s[i+8:]
             s=r
    return s

#------------------------------------------------------------
# function localprivate_fixSStr_libpath(s)
#
# adds /usr/local/lib to libpath
#------------------------------------------------------------
def localprivate_fixSStr_libpath(s):
    """adds /usr/local/lib to libpath for libcppunit.a"""
    if (s[0:7]=="libpath"):
        i=len(s)-1
        while s[i:i+1]!="]":
            i=i-1           
        r = s[0:i] + ", '/usr/local/lib/']\n"
        s = r
    return s  
    
#------------------------------------------------------------
# function localprivate_fixSStr_defines(s)
#
# adds /usr/local/lib to libpath
#------------------------------------------------------------
def localprivate_fixSStr_defines(s):
    """adds -DCPPUNIT_DLL to compile defines for Windows"""
    if os.name == 'nt':
        s = s.replace("['-DWIN32'", "['-DCPPUNIT_DLL', '-DWIN32'")
    return s    
      

#------------------------------------------------------------
# function localprivate_fixSStr_envProgram(s,utt)
#
# adds libcppunit to env.Program libs & corrects target name
#------------------------------------------------------------
def localprivate_fixSStr_envProgram(s,utt):
    """adds libcppunit to env.Program libs, corrects 
       exe target name"""
    if (s[0:11]=="env.Program"):
        i = 11
        while s[i]!="'":
          i=i+1
        i=i+1
        while s[i]!="'":
          i=i+1
        j=i-1
        while (s[j]!="/") and (s[j]!="'"):
          j=j-1
        r = s[:j+1] + utt + s[i:]
        s = r
        if os.name == 'nt':
            r = s[0:len(s)-2] + ", 'cppunit_dll'])\n"
        else:
            r = s[0:len(s)-2] + ", 'cppunit'])\n"
        s = r
    return s    

#------------------------------------------------------------
# function localprivate_fixstrSconstructRewrite(s,utf,utt)
#
# corrects strings from original 
# Sconstruct file using the fixSStr
# functions defined in this module.
#------------------------------------------------------------
      
def localprivate_fixstrSconstructRewrite(s,utf,utt):
    """corrects strings from original 
       Sconstruct file using the fixSStr 
       functions defined in this module."""
    s1 = localprivate_fixSStr_main2utst(s,utf)
    s2 = localprivate_fixSStr_libpath(s1)
    s3 = localprivate_fixSStr_envProgram(s2,utt)
    s4 = localprivate_fixSStr_defines(s3)
    return s4
            
#------------------------------------------------------------
# function localprivate_rewriteSconstructForUnitTestBuild(r,utf,utt)
#
# rewrites Sconstruct with build options for 
# cppunit testing.  Standard scons invocation
# of 'scons' still produces original live build,
# command line invocation using 'scons ' 
# Sconstruct file using the fixSStr
# functions defined in this module.
#------------------------------------------------------------
def localprivate_rewriteSconstructForUnitTestBuild(r,utf,utt):
   
#save original Sconstruct:
    f = open("./Sconstruct_original","w")
    f.writelines(r)
    f.close()
    
#generate new file data:    
    o=[]
    o.append("#UnitTestFixed041509(Do not edit or remove this line!)\n")
    o.append("mymode = ARGUMENTS.get('mode','release')\n")
    o.append("if (mymode != 'unittest'):\n")   
    for s in r:
       o.append("    "+s)
    o.append("\nelse:\n")
    for s in r:
       s2 = localprivate_fixstrSconstructRewrite(s,utf,utt)
       o.append("    "+s2)
       
#write new Sconstruct file       
    f = open("./Sconstruct","w")
    f.writelines(o)
    f.close()
    
    return 0
          
#------------------------------------------------------------
# function localprivate_readSconstructFile(fname)
#
# reads sConstruct file and returns it as a 
# list of strings
#------------------------------------------------------------
def localprivate_readSconstructFile(fname = "./Sconstruct"):
    """ reads sConstruct file and returns it as
    a list of strings """
    f = open(fname,"r+")
    result = f.readlines()
    f.close()
    return result

#------------------------------------------------------------
# function localprivate_DeleteObjFile(srcFile)
#
# deletes the object file if it exists in the directory
#------------------------------------------------------------

def localprivate_DeleteObjFile(srcFile):
    """ deletes the object file if it exists in the directory """
    oFile = []
    oFile = srcFile[:len(srcFile)-4] + ".o"
    if os.path.isfile(oFile):
        os.remove(oFile)
    return 0
    

#------------------------------------------------------------
# function Make_Client_Invoke_StartTrigger(clfilename,msgName)
#
# modifies client cpp file to invoke a trigger message
# to start the test ball rolling. 
#------------------------------------------------------------
def Make_Client_Invoke_StartTrigger(clfilename,IEName): 
    """modifies client cpp file to invoke a trigger message
    to start the test ball rolling."""
    f_in = localprivate_readSconstructFile(clfilename)
    f_outt=[]
    for s in f_in:
       if s[:23]=="	/// Run Helper Threads":
          s2 = "  ieHandler->invoke(new " + IEName + " );"
       else: 
          s2 = s
#       print "-->" + s2 + "<--"
       f_outt.append(s2)  
#    print("now displaying f_out")
#    for s in f_outt:
#       print "--)" + s + "(="    
    f = open(clfilename,"w")
    f.writelines(f_outt)
    f.close()
      
          
    return 0
    
#def localprivate_StripLeadingWhitespace(s)
        
    
def localprivate_fixBuildXML(targFile,cmptFile):
    """modifies Build.xml file to target the appropriate
       test xml file and produce the appropriate directory
       and file names for the generated code."""
       
       
    f_in = localprivate_readSconstructFile("./build.xml")
    
    f_save = open("./build.xml original","w")
    f_save.writelines(f_in)
    f_save.close()
    
    f_outt=[]
    skipping = 0
    for s in f_in:
       s2 = s.strip()
# "    <target name=/"run/""    
       if s2[:20]=="<target name=\"runATF":
          skipping = 1
          print "localprivate_fixBuildXML found \runATF"
       if skipping == 0:
          f_outt.append(s)
       else:
          if s2[:9]=="</target>":
             print "localprivate_fixBuildXML modifying build.xml to target " + targFile
             skipping = 0
             f_outt.append("    <target name=\"runATF\">") 
#"    <target name=\"runATF\" depends=\"make\">"             
             f_outt.append("      <java classname=\"${main.class}\" fork=\"true\">")
             f_outt.append("            <classpath refid=\"classpath\"/>")
             f_outt.append("<arg line=\"-i test/xml/" + targFile + ".xml -o test/atf/" + targFile + " -n " + cmptFile + "_cmpt --id 1\"/>")
             f_outt.append("      </java>")
             f_outt.append("    </target>")

    f = open("./build.xml","w")
    f.writelines(f_outt)
    f.close()
      
          
    return 0

#------------------------------------------------------------
# function Make_Sconstruct_Support_UnitTest(utfilename="utst.cpp",uttargname="UnitTest.exe")
#
# modifies Sconstruct file to support
# compilation of unit test version of app. 
#------------------------------------------------------------
def Make_Sconstruct_Support_UnitTest(utfilename="utst.cpp",uttargname="UnitTest.exe"):
    """modifies Sconstruct file to support
    compilation of unit test version of app. pass in 
    unit test source file name and unit test executable 
    target name"""
    #read in the Sconstruct file
    r=localprivate_readSconstructFile()
    
    #if it's already been modified, restore it
    if (localprivate_HasUnitTestMods(r)):
        Restore_Original_Sconstruct()
        r=localprivate_readSconstructFile() 
           
    #modify Sconstruct accordingly    
    localprivate_rewriteSconstructForUnitTestBuild(r,utfilename,uttargname)
    
    return 0
    
#------------------------------------------------------------
# function Restore_Original_Sconstruct()
#
# restores Sconstruct file to its original state
#------------------------------------------------------------
def Restore_Original_Sconstruct():
    """restores Sconstruct file to its original state"""
    
    #only proceed if Sconstruct_original exists
    if (localprivate_FileExists("./Sconstruct_original")):
        os.remove("./Sconstruct")
        os.rename("./Sconstruct_original","./Sconstruct")
    else:
        print "ERROR - missing original Sconstruct file"
        print "Script terminating with exit code -1"
        exit(-1)
    return 0

def alter_csharp_sconstruct_for_unit_test(scons_file_path, bld_output_file, test_name):
    """ Removes the generated main program source file from a C# generated Sconstruct file's
    sources, so the _utst.cs source file, which contains a Main function, will be used as the
    output executable's entry point."""

    sconstruct_file = open(scons_file_path, "r")
    sconstruct_lines = sconstruct_file.readlines()
    sconstruct_file.close()

    deletion_idx = None
    # find the line with the path to main.cs, we need to remove it from build.
    path_to_remove = 'src\\main.cs'
    for idx in xrange(len(sconstruct_lines)):
        if sconstruct_lines[idx].strip() == path_to_remove:
            deletion_idx = idx
            break

    # drop the line with the path to remove
    if not deletion_idx is None:
        sconstruct_lines = sconstruct_lines[:deletion_idx] + sconstruct_lines[deletion_idx+1:]
    else:
        bld_output_file.write("Couldn't find the *_csharp.cs file in C# test's Sconstruct!\n")

    copylines_idx = None
    # add directive to copy nunit.framework.dll into ./bin
    copy_commands_next_line = "# Platform specific info"
    for idx in xrange(len(sconstruct_lines)):
        if sconstruct_lines[idx].startswith(copy_commands_next_line):
            copylines_idx = idx
            break

    if not copylines_idx is None:
        sconstruct_lines = sconstruct_lines[:copylines_idx] \
            + ["if not os.path.isfile('bin/nunit.framework.dll'):\n", \
                "        Execute(Copy('bin/nunit.framework.dll', '../../nunit.framework.dll'))\n"] \
            + sconstruct_lines[copylines_idx:]
    else:
        bld_output_file.write("Couldn't find line for adding Copy command to C# test's Sconstruct!")

    addref_idx = None
    # add directives to add nunit.framework.dll to reference paths.
    add_to_ref_paths_next_line = "# Build"
    for idx in xrange(len(sconstruct_lines)):
        if sconstruct_lines[idx].strip() == add_to_ref_paths_next_line:
            addref_idx = idx
            break

    if not addref_idx is None:
        sconstruct_lines = sconstruct_lines[:addref_idx] \
            + ["nunit_framework = env.Install('#./bin', '#../../nunit.framework.dll')\n", \
                "env.AddToRefPaths(nunit_framework)\n"] \
            + sconstruct_lines[addref_idx:]
    else:
        bld_output_file.write("Couldn't find line for adding Copy command to C# test's Sconstruct!")

    libs_line_idx = None
    # change the list of libraries provided to env.CLIProgram directive
    libs_line_start = 'libs = ['
    for idx in xrange(len(sconstruct_lines)):
        if sconstruct_lines[idx].startswith(libs_line_start):
            libs_line_idx = idx
            break

    if not libs_line_idx is None:
        # the line to change declares a python list.  find the last "]" and replace
        # it with ", 'nunit.framework']"
        original_line = sconstruct_lines[libs_line_idx]
        bracket_idx = original_line.rfind(']')
        sconstruct_lines[libs_line_idx] = original_line[:bracket_idx] + ", 'nunit.framework']\n"
    else:
        bld_output_file.write("Couldn't find line with CLIProgram libraries list!")

    # add /debug option to CSCFLAGS scons env. var.
    cscflags_prev_line = "env.Append( BINPATH = '#./bin')"
    for idx in xrange(len(sconstruct_lines)):
        if sconstruct_lines[idx].strip() == cscflags_prev_line:
            win_cscflags_line_idx = idx
            break

    if win_cscflags_line_idx:
        sconstruct_lines = sconstruct_lines[:win_cscflags_line_idx+1] \
            + ["env.Append( CSCFLAGS = ['/debug'])\n"] \
            + sconstruct_lines[win_cscflags_line_idx+1:]
    else:
        bld_output_file.write("Couldn't find line for adding /debug option to CSCFLAGS!")


    # re-output the Sconstruct file.
    sconstruct_new_file = open(scons_file_path, "w")
    sconstruct_new_file.writelines(sconstruct_lines)
    sconstruct_new_file.close()

    return 0

    
def Build_Test_Csharp(test_name, testExePath, bldOutputFile, nunitAssemblyFile):
    """ Something something.
    """
    bldOutputFile.write("\n----PERFORMING C# BUILD FOR " + test_name + " ----------\n")

    # collect output from scons in buffer
    str_buffer = tempfile.TemporaryFile()

    # Remove previous build files
    if os.path.isdir("./lib"):
        shutil.rmtree("./lib", ignore_errors=False, onerror=on_rmtree_error)
    if os.path.isdir("./bin"):
        shutil.rmtree("./bin", ignore_errors=False, onerror=on_rmtree_error)


    # fix the Sconstruct file to target the _utst.cs file for executable creation,
    # instead of the generated _main.cs file.
    # add junit .jar file to classpath in Sconstruct
    if alter_csharp_sconstruct_for_unit_test("./Sconstruct", bldOutputFile,test_name) != 0:
        return 1

    #run scons to build the test, wait for completion
    execObj = subprocess.Popen("scons",stdout=str_buffer,stderr=str_buffer,shell=True)
    execObj.wait()
    retval = execObj.returncode

    # return output file to start and get contents
    str_buffer.seek(0)
    scons_output_lines = str_buffer.readlines()

    # close the temporary output file
    str_buffer.close()

    # write the results, close the result file.
    bldOutputFile.writelines(scons_output_lines)
    bldOutputFile.write("\n----BUILD COMPLETE FOR " + test_name + " ----------\n")

    return retval

def alter_java_sconstruct_for_unit_test(scons_file_path, bld_output_file, junit_jar_path):
    """ adds given path to junit jar to a Java generated Sconstruct file's classpath. """

    # read in the Sconstruct file
    sconstruct_file = open(scons_file_path, "r")
    sconstruct_lines = sconstruct_file.readlines()
    sconstruct_file.close()

    change_idx = None
    # find the line with the classpath.
    for idx in xrange(len(sconstruct_lines)):
        if sconstruct_lines[idx].startswith("env.Append( JAVACLASSPATH"):
            change_idx = idx
            break

    # replace the line with classpath with a classpath line including junit .jar
    if not change_idx is None:
        classpath_line = sconstruct_lines[change_idx]

        re_result = classpath_re.search(classpath_line)

        # abnormal result of RE?
        if not re_result or re_result.start(1) == -1:
            bld_output_file.write("Couldn't parse the classpath line in Sconstruct file for Java build!\n")
            return 1

        # there should be one group in the results.  start index of this group is the last single-quote
        # on the classpath line.
        line_to_quote = classpath_line[:re_result.start(1)]

        # insert new jarfile path after last single-quote.
        new_line = "".join([line_to_quote, ", '", junit_jar_path, "'])", os.linesep])

        sconstruct_lines[change_idx] = new_line
    else:
        bld_output_file.write("Sconstruct file for Java unit test had no classpath line!\n")
        return 1

    # re-output the Sconstruct file.
    sconstruct_new_file = open(scons_file_path, "w")
    sconstruct_new_file.writelines(sconstruct_lines)
    sconstruct_new_file.close()

    return 0

def Build_Test_Java(srcFile,bldOutputFile,junit_jar_path):
    """ Alters the generated Sconstruct file in the current working directory
    to add classpath to the JUnit jarfile, then runs scons in the current working
    directory.
    """
    bldOutputFile.write("\n----PERFORMING JAVA BUILD FOR " + srcFile + " ----------\n")

    # collect output from scons in buffer
    str_buffer = tempfile.TemporaryFile()

    # Remove previous build files
    if os.path.isdir("./lib"):
        shutil.rmtree("./lib", ignore_errors=False, onerror=on_rmtree_error)
    if os.path.isdir("./bin"):
        shutil.rmtree("./bin", ignore_errors=False, onerror=on_rmtree_error)

    # add junit .jar file to classpath in Sconstruct
    if (srcFile != ""):
        if alter_java_sconstruct_for_unit_test("./Sconstruct", bldOutputFile,junit_jar_path) != 0:
            return 1

    #run scons to build the test, wait for completion
    execObj = subprocess.Popen("scons",stdout=str_buffer,stderr=str_buffer,shell=True)
    execObj.wait()
    retval = execObj.returncode

    # return output file to start and get contents
    str_buffer.seek(0)
    scons_output_lines = str_buffer.readlines()

    # close the temporary output file
    str_buffer.close()

    # write the results, close the result file.
    bldOutputFile.writelines(scons_output_lines)
    bldOutputFile.write("\n----BUILD COMPLETE FOR " + srcFile + " ----------\n")

    return retval

def Build_Test_Cpp(srcFile,targFile,bldOutputFile):
    """ Alters the Sconstruct file in current working directory to add
    dependencies, then invokes scons to build srcFile into an executable at
    targFile.
    """
    bldOutputFile.write("\n----PERFORMING C++ BUILD FOR " + srcFile + " ----------\n")
    
    # collect output from scons in buffer
    str_buffer = tempfile.TemporaryFile()

    # Remove previous build files
    if os.path.isdir("./lib"):
        shutil.rmtree("./lib", ignore_errors=False, onerror=on_rmtree_error)
    if os.path.isdir("./bin"):
        shutil.rmtree("./bin", ignore_errors=False, onerror=on_rmtree_error)

    #fix the sconstruct file to build the test
    if (srcFile != ""):
        Make_Sconstruct_Support_UnitTest(srcFile,targFile)
    
    #run scons to build the test, wait for completion     
    execObj = subprocess.Popen("scons mode=unittest",stdout=str_buffer,stderr=str_buffer,shell=True)
    execObj.wait()
    retval = execObj.returncode

    # return output file to start and get contents
    str_buffer.seek(0)
    scons_output_lines = str_buffer.readlines()
    
    # close the temporary output file
    str_buffer.close()
    
    # write the results, close the result file.
    bldOutputFile.writelines(scons_output_lines)
    bldOutputFile.write("\n----BUILD OUTPUT OF " + srcFile + " COMPILED TO " + targFile +" ----------\n")
       
    return retval
    
def Run_Test_Java(test_jar_path, test_executable, test_name, absolute_bin_path, output_file, junit_jar_path):
    """ Invokes the runnable class test_executable found in the jarfile at test_jar_path.
    If test_executable is under a package in the jarfile, test_executable must be decorated
    with the package name.
    Also adds /usr/share/java/junit.jar to the classpath when running test_executable, as it is
    assumed to use the junit library."""

    # collect output from executable target in temp file
    str_buffer = tempfile.TemporaryFile()

    current_env = add_lib_to_libpath(os.environ.copy(), absolute_bin_path)

    # verify jarfile exists
    if not os.path.isfile(test_jar_path):
        str_buffer.write("ERROR - " + test_jar_path + " does not exist.\n")
        retval = 1
    else:
        if sys.platform == "cygwin":
            execObj = subprocess.Popen("java -cp 'cygpath -wp " + junit_jar_path + ":" + test_jar_path + "' " + test_executable, \
                stdout=str_buffer,stderr=str_buffer,shell=True,env=current_env)
        elif os.name == 'nt':
            java_call_text = "java -cp " + junit_jar_path + ";" + test_jar_path + " " + test_executable
            execObj = subprocess.Popen(java_call_text, stdout=str_buffer,stderr=str_buffer,shell=True,env=current_env)
            str_buffer.write("java call text = " + java_call_text)
        else:
            execObj = subprocess.Popen("java -cp " + junit_jar_path + ":" + test_jar_path + " " + test_executable, \
                stdout=str_buffer,stderr=str_buffer,shell=True,env=current_env)

        start = datetime.datetime.now()
        while execObj.poll() is None:
          time.sleep(1)
          now = datetime.datetime.now()
          if (now - start).seconds > 60:
            execObj.kill()
            print " subprocess failed to terminate properly.  Closing..."
            break

        retval = execObj.returncode

        # get contents of temp file
        str_buffer.seek(0)
        exec_results = str_buffer.readlines()

        str_buffer.close()

        #write the results to file
        output_file.write("\n\n----EXECUTION OUTPUT OF **->" + test_name  +"<-** ----------\n")
        output_file.writelines(exec_results)

        return retval

def Run_Test_Csharp(test_executable, test_name, absolute_bin_path, output_file):
    """ Calls nunit_console, passing test_executable as the test fixture to run.
    Console output is directed to output file, including output from nunit_console.
    """
    # collect output from executable target in temp file
    str_buffer = tempfile.TemporaryFile()

    current_env = add_lib_to_libpath(os.environ.copy(), absolute_bin_path)

    # verify executable exists.
    if not os.path.isfile(test_executable):
        print >> output_file, "ERROR - test executable " + test_executable + " does not exist.\n"
        retval = 1
    else:
        if sys.platform == "cygwin":
            execObj = subprocess.Popen("nunit-console " + test_executable, \
                stdout=str_buffer,stderr=str_buffer,shell=True,env=current_env)
        elif os.name == 'nt':
            java_call_text = "nunit-console /trace=Verbose " + test_executable
            execObj = subprocess.Popen(java_call_text, stdout=str_buffer,stderr=str_buffer,shell=True,env=current_env)
        else:
            execObj = subprocess.Popen("nunit-console " + test_executable, \
                stdout=str_buffer,stderr=str_buffer,shell=True,env=current_env)

        start = datetime.datetime.now()
        while execObj.poll() is None:
          time.sleep(1)
          now = datetime.datetime.now()
          if (now - start).seconds > 60:
            execObj.kill()
            print " subprocess failed to terminate properly.  Closing..."
            break

        retval = execObj.returncode

        # get contents of temp file
        str_buffer.seek(0)
        exec_results = str_buffer.readlines()

        str_buffer.close()

        #write the results to file
        output_file.write("\n\n----EXECUTION OUTPUT OF **->" + test_name  +"<-** ----------\n")
        output_file.writelines(exec_results)

        return retval

def add_lib_to_libpath(env, new_path):
    """ Given an object like os.environ, adds new_path to PATH or LD_LIBRARY_PATH as befits
    the executing operating system """
    # alter appropriate path so runtime libraries may be found.
    if os.name == 'nt':
        # ensure that the test's bin directory is in _PATH env. var so C# runtime finds the
        # native .dll located there.
        if 'PATH' in env:
            env['PATH'] = env['PATH'] + os.pathsep + new_path
        else:
            env['PATH'] = new_path
    else:
        # unix-like environment:
        # ensure that the test's bin directory is in LD_LIBRARY_PATH env. var so
        # C# runtime finds the native .sos located there.
        if 'LD_LIBRARY_PATH' in env:
            env['LD_LIBRARY_PATH'] = env['LD_LIBRARY_PATH'] + os.pathsep + new_path
        else:
            env['LD_LIBRARY_PATH'] = new_path
            
    return env

def Run_Test_Cpp(targFile,tstOutputFile, absolute_bin_path):
    """ Invokes targetFile, an executable, and appends its output in tstOutputFile. """
    
    # collect output from executable target in temp file
    str_buffer = tempfile.TemporaryFile()

    current_env = add_lib_to_libpath(os.environ.copy(), absolute_bin_path)

    #verify target exists
    if not os.path.isfile(targFile):
        str_buffer.write("ERROR - " + targFile + " does not exist.\n")
        return 1
    else:
        execObj = subprocess.Popen(targFile,stdout=str_buffer,stderr=str_buffer,env=current_env)

        start = datetime.datetime.now()
        while execObj.poll() is None:
            time.sleep(1)
            now = datetime.datetime.now()
            if (now - start).seconds > 60:
                execObj.kill()
                print " subprocess failed to terminate properly.  Closing..."
                break

        retval = execObj.returncode

        # get contents of temp file
        str_buffer.seek(0)
        exec_results = str_buffer.readlines()

        str_buffer.close()

        #write the results to file
        tstOutputFile.write("\n----EXECUTION OUTPUT OF **->" + targFile  +"<-** ----------\n")
        tstOutputFile.writelines(exec_results)

        return retval


#------------------------------------------------------------
# function Replace_File(file,dir, bldOutput,append=True)
# 
#------------------------------------------------------------
def Replace_File(file,dir,bldOutput,append=True):

   # Open the bldOutput file
		if ((append) and (localprivate_FileExists(bldOutput))):
		 out = open(bldOutput,"r+")
		 out.seek(0,2)
		else:
		 out = open(bldOutput,"w")

		out.write("\n----FILE REPLACEMENT FOR " + file +" ----------\n")
       
    #Make sure we can find the original file
		if (localprivate_FileExists(file+".original")==False):
				out.write("Can't open source file: " + file + ".original" + "\n")
				out.close
				return False
        
    #See if the "new" file exists in the given dir
		generated_file = file
		if (localprivate_FileExists(dir + generated_file + ".new")==True):
				generated_file = generated_file + ".new"
		elif (localprivate_FileExists(dir + generated_file)==False):
				out.write("Can't find generated file in directory")
				out.close
				return False
		out.write("Found generated file: " + generated_file + "\n")

    #open a temporary file to collect this test's output
                f = open("./tmpout.txt","w")		

    # use command line diff
                execObj = subprocess.Popen("diff --strip-trailing-cr " + file+ ".original" + " " + dir+generated_file,stdout=f,stderr=f,shell=True)
                execObj.wait()
                retval = execObj.returncode

    # close and remove the temp file
                f.close()
                os.remove("./tmpout.txt")

    # if the retval is 1, the original and generated files are different, return failure
                if (retval == 1):
                    out.write("Generated file is different than expected.  Returning prematurely...\n")
                    return False
               
    # Looks like generated file still has the right form.  Move the completed
		shutil.copy(file, dir+file)
		out.write("Done with copy\n")
		out.close    
		return True

language_specific_subdirs = \
    {'c++' : '_cmpt', "java" : "_java", "cs" : "_csharp"}

def Run_CodeGenerator(testName, language, bldOutputFile):
    """Invokes org.jts.codegenerator.CodeGenerator on a JSIDL file named <testName>.xml in test/xml,
    placing the output under test/atf in a directory <testName>/<testName><language_suffix>.  <language_suffix> is
    selected based on the language setting:
        c++ => _cmpt
        java => _java
        cs => _csharp"""
    
    #save the start directory so we can return to it when done
    baseworkdir = os.getcwd()
    
    #go to the code generator directory
    os.chdir("../../")

    # set up subdirectory name based on output language.
    subdir_name = testName + language_specific_subdirs[language]
        
    # collect output from scons in temporary file. 
    str_buffer = tempfile.TemporaryFile()

    #run the code generator, wait for completion
    if sys.platform == "cygwin":
    	execObj = subprocess.Popen("java -cp 'cygpath -wp .;build/classes;lib/jargs-1.0/jargs.jar;lib/jaxb-plugins/commons-lang-2.5.jar;lib/smc/Smc.jar;lib/smc' org.jts.codegenerator.CodeGenerator " \
            + "-i test/xml/" + testName + ".xml -o test/atf/" + testName + " -n " + subdir_name + " --id 1 --" + language,\
            stdout=str_buffer,stderr=str_buffer,shell=True)
    elif os.name == 'nt':
        execObj = subprocess.Popen("java -cp '.;build/classes;lib/jargs-1.0/jargs.jar;lib/jaxb-plugins/commons-lang-2.5.jar;lib/smc/Smc.jar;lib/smc' org.jts.codegenerator.CodeGenerator " \
            + "-i test/xml/" + testName + ".xml -o test/atf/" + testName + " -n " + subdir_name + " --id 1 --" + language,\
            stdout=str_buffer,stderr=str_buffer,shell=True)
    else:
    	execObj = subprocess.Popen("java -cp .:build/classes:lib/jargs-1.0/jargs.jar:lib/jaxb-plugins/commons-lang-2.5.jar:lib/smc/Smc.jar:lib/smc org.jts.codegenerator.CodeGenerator " \
            + "-i test/xml/" + testName + ".xml -o test/atf/" + testName + " -n " + subdir_name + " --id 1 --" + language,\
            stdout=str_buffer,stderr=str_buffer,shell=True)

    execObj.wait()

    retval = execObj.returncode

    # return to start of temp file and read contents
    str_buffer.seek(0)
    codegen_output = str_buffer.readlines()
    
    # close the output buffer
    str_buffer.close()

    #write the results, close the result file.   
    bldOutputFile.write("\n----CODE GENERATOR OUTPUT FOR " + testName +" ----------\n")
    bldOutputFile.writelines(codegen_output)

    #restore the original working directory
    os.chdir(baseworkdir)
        
    return retval
    
