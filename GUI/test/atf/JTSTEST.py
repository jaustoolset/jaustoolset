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
# JTS test module
#------------------------------------------------------------
# Description:  This module builds and runs JTS unit testing
#------------------------------------------------------------

import os
import os.path
import shutil
import JTSutst
from optparse import OptionParser
from optparse import OptionGroup

CPP_BLD_OUTPUT_PATH = "./cpp_bldOutput.txt"
CPP_TST_OUTPUT_PATH = "./cpp_tstOutput.txt"
JAVA_BLD_OUTPUT_PATH = "./java_bldOutput.txt"
JAVA_TST_OUTPUT_PATH = "./java_tstOutput.txt"
CSHARP_BLD_OUTPUT_PATH = "./csharp_bldOutput.txt"
CSHARP_TST_OUTPUT_PATH = "./csharp_tstOutput.txt"

base_dir = ""
do_generation = True
do_build = True
do_run = True
junit_jar_path = "./junit-3.8.2.jar"
nunit_assembly_path = "./nunit-thingy.dll"

def run_cpp_test(test_info, build_output_file, test_output_file):
    """Run a test case, test_name, using the C++ code generator and cppunit test file for that
    test case.  Append output from the build process to build_output_file and append output from
    the test run to test_output_file (both must be already-opened file objects)"""
    global base_dir
    global do_generation
    os.chdir(base_dir)

    (test_name, test_namespace) = test_info

    print ""
    print "Processing test, C++ version:", test_name
    print "*************************************************"
    print ""

    exe_name = test_name.lower() + "_utst.exe"

    # C++ unit test .cpp file names are inconsistent in capitalization.
    # most are in CamelCase but others are not.  check if the camel case filename is not found
    # and adjust filename used when building test if necessary.
    cpp_name = test_name + "_utst.cpp"

    test_dir = "./" + test_name + "/" + test_name + "_cmpt_1"
    cpp_path = test_dir + "/" + cpp_name

    if (not os.path.isfile(cpp_path)):
        cpp_name = test_name.lower() + "_utst.cpp"

    if (do_generation and JTSutst.Run_CodeGenerator(test_name, 'c++', build_output_file)!=0):
        print "--- Errors encountered in Code Generation phase! ( see", CPP_BLD_OUTPUT_PATH, ")"

    os.chdir(test_dir)

    # we need to find the absolute path to the bin directory so the location of native
    # shared libraries can be specified to csharp runtime via the PATH env. variable.
    absolute_bin_path = os.getcwd() + os.sep + "bin"

    if (do_build and JTSutst.Build_Test_Cpp(cpp_name, exe_name, build_output_file)!=0):
        print "--- Errors encountered in test compilation phase (see ", CPP_BLD_OUTPUT_PATH, ")"
    if (do_run and JTSutst.Run_Test_Cpp("./bin/" + exe_name, test_output_file,absolute_bin_path)!=0):
        print "---Errors encountered in test execution phase (see ", CPP_TST_OUTPUT_PATH, ")"

def run_java_test(test_info, build_output_file, test_output_file):
    """Run a test case using the Java code generator and Junit test file for that
    test case.  test_info must be a tupled containing both the test's name and
    the test class' package (or None if it's in the global package)
    Appends output from the build process to build_output_file and appends output from
    the test run to test_output_file (both must be already-opened file objects)"""
    global base_dir
    global junit_jar_path
    global do_generation
    os.chdir(base_dir)

    (test_name, test_namespace) = test_info

    print ""
    print "Processing test, Java version:", test_name
    print "*************************************************"
    print ""

    test_dir = "./" + test_name + "/" + test_name + "_java_1"
    test_source_name = test_name + "_utst.java"
    test_jar_path = "./bin/" + test_name + "_java_1.jar"
    relative_junit_jar_path = "../../" + junit_jar_path

    if (test_namespace):
        test_exe = test_namespace + "." + test_name + "_utst"
    else:
        test_exe = test_name + "_utst"

    if (do_generation and JTSutst.Run_CodeGenerator(test_name, 'java', build_output_file) != 0):
        print "--- Errors encountered in code generation phase! ( see", JAVA_BLD_OUTPUT_PATH, ")"
    os.chdir(test_dir)

    # we need to find the absolute path to the bin directory so the location of native
    # shared libraries can be specified to java via the PATH env. variable.
    absolute_bin_path = os.getcwd() + os.sep + "bin"

    if (do_build and JTSutst.Build_Test_Java(test_source_name, build_output_file, relative_junit_jar_path)!=0):
        print "--- Errors encountered in test build phase (see ", JAVA_BLD_OUTPUT_PATH, ")"
    if (do_run and JTSutst.Run_Test_Java(test_jar_path, test_exe, test_name, absolute_bin_path, test_output_file, relative_junit_jar_path)!=0):
        print "---Errors encountered in test execution phase (see ", JAVA_TST_OUTPUT_PATH, ")"

def run_csharp_test(test_info, build_output_file, test_output_file):
    """Run a test case, test_name, using the C# code generator and Nunit test file for that
    test case.  Append output from the build process to build_output_file and append output from
    the test run to test_output_file (both must be already-opened file objects)"""
    global base_dir
    global do_generation
    global nunit_assembly_path
    os.chdir(base_dir)

    (test_name, test_namespace) = test_info

    print ""
    print "Processing test, C# version:", test_name
    print "*************************************************"
    print ""

    test_dir = "./" + test_name + "/" + test_name + "_csharp_1"
    test_exe_path = "./bin/" + test_name + "_csharp.exe"
    relative_nunit_assembly_path = "../../" + nunit_assembly_path

    if (do_generation and JTSutst.Run_CodeGenerator(test_name, 'cs', build_output_file) != 0):
        print "--- Errors encountered in code generation phase! ( see", CSHARP_BLD_OUTPUT_PATH, ")"

    os.chdir(test_dir)

    # we need to find the absolute path to the bin directory so the location of native
    # shared libraries can be specified to csharp runtime via the PATH env. variable.
    absolute_bin_path = os.getcwd() + os.sep + "bin"

    if (do_build and JTSutst.Build_Test_Csharp(test_name, test_exe_path, \
            build_output_file, relative_nunit_assembly_path) != 0):
        print "--- Errors encountered in test build phase (see ", CSHARP_BLD_OUTPUT_PATH, ")"
    if do_run and JTSutst.Run_Test_Csharp(test_exe_path, test_name, absolute_bin_path, test_output_file) != 0:
        print "---Errors encountered in test execution phase (see ", CSHARP_TST_OUTPUT_PATH, ")"


def run_test_set_with_runner(runner_fn, test_set, message, build_output_file, test_output_file):
    """ Run a set of tests, test_set, with the given runner function runner_fn (which should be
    set to one of run_cpp_test, run_java_test, or run_csharp_test).  Append output from the build
    process to build_output_file and append output from the test run to test_output_file (both
    must be already-opened file objects)"""

    print ""
    print "*************************************************"
    print "Running Test Set: ", message
    print "*************************************************"
    print ""
    for test_info in test_set:
        runner_fn(test_info, build_output_file, test_output_file)


#------------------------------------
# run References (client-of & inheritence)
#------------------------------------
def runReferences():

    baseworkdir = os.getcwd()
    print "Processing References..."
    if (JTSutst.Run_CodeGenerator("References1","References1",baseworkdir + "/bldOutput.txt")!=0):
        print("---Errors encountered in Code Generation phase (see bldOutput.txt)")
    os.chdir("./References1/References1_cmpt_1")
    if (JTSutst.Build_Test("References1_utst.cpp","References1_utst.exe",baseworkdir + "/bldOutput.txt")!=0):
        print("---Errors encountered in test compilation phase (see bldOutput.txt)")
    if (JTSutst.Run_Test("./bin/References1_utst.exe",baseworkdir + "/tstOutput.txt")!=0):
        print("---Errors encountered in test execution phase (see tstOutput.txt)")
    os.chdir(baseworkdir)

    baseworkdir = os.getcwd()
    print "Processing Core..."
    shutil.rmtree("./Core1", True)
    if (JTSutst.Run_CodeGenerator("Core1","Core1",baseworkdir + "/bldOutput.txt")!=0):
        print("---Errors encountered in Code Generation phase (see bldOutput.txt)")
    os.chdir("./Core1/Core1_cmpt_1")
    if (JTSutst.Build_Test("","Core1_cmpt_1.exe",baseworkdir + "/bldOutput.txt")!=0):
        print("---Errors encountered in test compilation phase (see bldOutput.txt)")
    os.chdir(baseworkdir)

    baseworkdir = os.getcwd()
    print "Processing Inheritence..."
    shutil.rmtree("./Inheritence1/Inheritence1_cmpt_1/urn_org_jts_test_Child_1_0", True)
    if (JTSutst.Run_CodeGenerator("Inheritence1","Inheritence1",baseworkdir + "/bldOutput.txt")!=0):
        print("---Errors encountered in Code Generation phase (see bldOutput.txt)")
    os.chdir("./Inheritence1/Inheritence1_cmpt_1")
    if (JTSutst.Build_Test("Inheritence1_utst.cpp","Inheritence1_utst.exe",baseworkdir + "/bldOutput.txt")!=0):
        print("---Errors encountered in test compilation phase (see bldOutput.txt)")
    if (JTSutst.Run_Test("./bin/Inheritence1_utst.exe",baseworkdir + "/tstOutput.txt")!=0):
        print("---Errors encountered in test execution phase (see tstOutput.txt)")
    os.chdir(baseworkdir)


    return 0


def main():
    """ main entry point function"""
    global base_dir
    global do_generation
    global do_build
    global do_run
    base_dir = os.getcwd()

    parser = OptionParser(usage="Usage: %prog [options]\n\nRun JTSTEST.py --help for info about the options."\
        "\n\nNote this script must be run from directory GUI/test/atf/ to succeed!")

    langs_group = OptionGroup(parser, "Language Testing Options", "These options allow code generation in" \
        " specific languages to be tested.  If no language testing option is selected, all languages are" \
        " are tested.  Multiple languages can be specified, for example by providing both" \
        "'--test_cpp' and '--test_java'.")

    langs_group.add_option("--test_cpp", dest="test_cpp", action="store_true", default=False,\
        help="Generate, build, and/or run C++ unit tests.")
    langs_group.add_option("--test_java", dest="test_java", action="store_true", default=False,\
        help="Generate, build, and/or run Java unit tests.")
    langs_group.add_option("--test_csharp", dest="test_csharp", action="store_true", default=False,\
        help="Generate, build, and/or run C# unit tests.")
    parser.add_option_group(langs_group)

    parser.add_option("--skip_gen", dest="skip_gen", action="store_true", default=False,\
        help="Skips the code-generation step to save time or maintain alterations to generated code.")
    parser.add_option("--skip_build", dest="skip_build", action="store_true", default=False,\
        help="Skips the test code build step, for faster code-generation testing.")
    parser.add_option("--skip_run", dest="skip_run", action="store_true", default=False,\
        help="Skips the test run step, for testing build issues more quickly.")

    (options, discarded) = parser.parse_args()

    # determine which test steps we should perform
    if options.skip_gen:
        do_generation = False

    if options.skip_build:
        do_build = False

    if options.skip_run:
        do_run = False

    # determine which language to use
    # if any of the --test_X options are invoked, test only in the language(s) invoked.
    # if none are invoked, test all languages.
    num_language_opts = 0;
    run_cpp_tests = run_java_tests = run_csharp_tests = False

    if options.test_cpp:
        run_cpp_tests = True;
        num_language_opts += 1

    if options.test_java:
        run_java_tests = True;
        num_language_opts += 1

    if options.test_csharp:
        run_csharp_tests = True;
        num_language_opts += 1

    if num_language_opts <= 0:
        run_cpp_tests = run_java_tests = run_csharp_tests = True

    array_tests = [
        ('Array1', None),
        ('Array2', None),
        ('Array3', None),
        ('Array4', None),
        ('Array5', None),
        ('Array6', None),
        ('Array7', None),
        ('Array8', None),
        ('Array9', None),
        ('Array10', None),
    ]
    bitfield_tests = [('BitField1', None)]

    body_tests = [
        ('Body1', None),
        ('Body2', None),
        ('Body3', None),
        ('Body4', None),
        ('Body5', None),
        ('Body6', None),
        ('Body7', None),
        ('Body8', None),
        ('Body9', None),
    ]

    # the FixedField1 test is identical to Body1 test, therefore it was removed
    fixedfield_tests = [
        ('FixedField2', None),
        ('FixedField3', None)
    ]

    header_tests = [
        ('Header1', None),
        ('Header2', None),
        ('Header3', None),
        ('Header4', None),
        ('Header5', None),
        ('Header6', None),
    ]

    list_tests = [
        ('List1', None),
        ('List2', None),
        ('List3', None),
        ('List4', None),
    ]

    record_tests = [
        ('Record10', None),
        ('Record11', None),
        ('Record12', None),
        ('Record15', None),
        ('Record16', None),
    ]

    sequence_tests = [
        ('Sequence1', None),
        ('Sequence2', None),
        ('Sequence3', None),
    ]

    variant_tests = [
        ('Variant1', None),
        ('Variant2', None),
        ('Variant3', None),
        ('Variant4', None),
    ]

    varlength_tests = [
        ('VariableLengthStuff1', None),
    ]
    
    optionality_tests = [
        ('Optional1', None),
    ]

    simpleset_tests = [('SimpleSet', 'src.urn_DeVivo_jaus_services_SimpleDef_1_0'),
        ('DefaultTransitionSet', 'src.urn_DeVivo_jaus_services_DefaultTransDef_1_0')]
    loopback_tests = [('Loopback1', 'src.urn_DeVivo_jaus_services_LoopbackDef_1_0'),
        ('Loopback2', 'src.urn_DeVivo_jaus_services_LoopbackDef_1_0')]
    references_tests = [('References1', None)]
    inheritance_tests = [('Inheritence1', 'src')]

    nestedset_tests = [
        ('NestedSet', 'src.urn_DeVivo_jaus_services_NestedDef_1_0'),
        ('NestedSet2', 'src.urn_DeVivo_jaus_services_NestedDef_1_0'),
    ]

    per_language_elements = []

    if run_cpp_tests:
        cpp_build_output_file = open(CPP_BLD_OUTPUT_PATH, 'w')
        cpp_test_output_file = open(CPP_TST_OUTPUT_PATH, 'w')
        per_language_elements.append(("C++", run_cpp_test, cpp_build_output_file, cpp_test_output_file))

    if run_java_tests:
        java_build_output_file = open(JAVA_BLD_OUTPUT_PATH, 'w')
        java_test_output_file = open(JAVA_TST_OUTPUT_PATH, 'w')
        per_language_elements.append(("Java", run_java_test, java_build_output_file, java_test_output_file))

    if run_csharp_tests:
        csharp_build_output_file = open(CSHARP_BLD_OUTPUT_PATH, 'w')
        csharp_test_output_file = open(CSHARP_TST_OUTPUT_PATH, 'w')
        per_language_elements.append(("C#", run_csharp_test, csharp_build_output_file, csharp_test_output_file))


    for (language, test_runner, build_output_file, test_output_file) in per_language_elements:
        run_test_set_with_runner(test_runner, array_tests, language + " Array Tests", \
            build_output_file, test_output_file)
        run_test_set_with_runner(test_runner, bitfield_tests, language + " BitField Tests", \
            build_output_file, test_output_file)
        run_test_set_with_runner(test_runner, body_tests, language + " Body Tests", \
            build_output_file, test_output_file)
        run_test_set_with_runner(test_runner, fixedfield_tests, language + " FixedField Tests", \
            build_output_file, test_output_file)
        run_test_set_with_runner(test_runner, header_tests, language + " Header Tests", \
            build_output_file, test_output_file)
        run_test_set_with_runner(test_runner, list_tests, language + " List Tests", \
            build_output_file, test_output_file)
        run_test_set_with_runner(test_runner, record_tests, language + " Record Tests", \
            build_output_file, test_output_file)
        run_test_set_with_runner(test_runner, sequence_tests, language + " Sequence Tests", \
            build_output_file, test_output_file)
        run_test_set_with_runner(test_runner, variant_tests, language + " Variant Tests", \
            build_output_file, test_output_file)
        run_test_set_with_runner(test_runner, optionality_tests, language + " Optionality Tests", \
            build_output_file, test_output_file)

        run_test_set_with_runner(test_runner, simpleset_tests, language + " Simple Set Tests", \
            build_output_file, test_output_file)
        run_test_set_with_runner(test_runner, nestedset_tests, language + " Nested Set Tests", \
            build_output_file, test_output_file)
        run_test_set_with_runner(test_runner, loopback_tests, language + " Loopback Tests", \
            build_output_file, test_output_file)
        run_test_set_with_runner(test_runner, references_tests, language + " References Tests", \
            build_output_file, test_output_file)
        run_test_set_with_runner(test_runner, inheritance_tests, language + " Inheritance Tests", \
            build_output_file, test_output_file)

# TODO: 'core1' tests, 'variable length stuff' test - doesn't exist.

    if run_cpp_tests:
        cpp_build_output_file.close();
        cpp_test_output_file.close();

    if run_java_tests:
        java_build_output_file.close();
        java_test_output_file.close();

    if run_csharp_tests:
        csharp_build_output_file.close();
        csharp_test_output_file.close();

    return 0
#------------------------------------



#------------------------------------
# stub to trigger execution of main
#------------------------------------
if __name__ == "__main__":
    main()

#------------------------------------
