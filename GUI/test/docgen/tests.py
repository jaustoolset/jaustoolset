from __future__ import print_function

import os
import sys
import subprocess

# construct a classpath string for the java executable with appropriate separator characters
def build_classpath(path_list):
    if sys.platform == 'win32':
        pathsep = ';'
    else:
        pathsep = ':'

    return pathsep.join(path_list)

#------------------------------------
# run a single test
#------------------------------------
def run_doc_generator(output_file, input_files, docs_output_dir, other_args):

    command_string = 'ant -Ddoc.generator.args="{0} -o {1} {2}" run-doc-generator'.format(other_args, \
            docs_output_dir, input_files)


    print ("Running command string: ", command_string)

    # must flush the output file to ensure Python script output is properly interwoven with
    # Java program's output.
    output_file.flush()
    
    execObj = subprocess.Popen(command_string, stdout=output_file, stderr=output_file, shell=True)
    execObj.wait()
    
def print_stars_title(title_string):
    print ("*******************************************")
    print ("**", title_string)
    print ("*******************************************")
    print ()

def print_testinfo(test_num, test_info):
    print ("* Test # ", test_num, " *  ", test_info)
    print()

# tests with incorrect arguments/options
def run_syntax_error_tests(outfile):
    print_stars_title("Test Set 1:  tests stimulating errors")
    
    print_testinfo(1, "Blank output file, no args, no options")
    run_doc_generator(outfile, "", "", "")

    print_testinfo(2, "Output file provided, no args, no options")
    run_doc_generator(outfile, "", "test/docgen/output/Array1", "")

    print_testinfo(3, "Output file provided, one arg, invalid options")
    run_doc_generator(outfile, "test/xml/Array1.xml", "test/docgen/output/Array1", "--foo --bar")

    print_testinfo(4, "Output provided, one arg, missing any output format")
    run_doc_generator(outfile, "test/xml/Array1.xml", "test/docgen/output/Array1", "")

    print_testinfo(5, "Output provided, several args, multiple output formats specified.")
    run_doc_generator(outfile, "test/xml/Array1.xml", "test/docgen/output/Array1", "--lhtml --fhtml --word")

# tests where doc generation should succeed.
def run_success_tests(outfile):
    print_stars_title ("Test Set 2: tests with one or more service definitions as input.")

    print_testinfo(1, "Single service def, linear HTML output.")
    run_doc_generator(outfile, "resources/xml/urn.jaus.jss.core-v1.0/AccessControl.xml", \
        "test/docgen/output/single-def-html", "--lhtml")

    print_testinfo(2, "Multiple service defs, linear HTML output.")
    run_doc_generator(outfile, "resources/xml/urn.jaus.jss.core-v1.0/AccessControl.xml " \
        "resources/xml/urn.jaus.jss.core-v1.0/Discovery.xml " \
        "resources/xml/urn.jaus.jss.core-v1.0/Events.xml", \
        "test/docgen/output/multi-def-html", " --lhtml")

    print_testinfo(3, "Single service def, Word output.")
    run_doc_generator(outfile, "resources/xml/urn.jaus.jss.core-v1.0/AccessControl.xml", \
        "test/docgen/output/single-def-word", "--word")

    print_testinfo(4, "Multiple service defs, Word docx output.")
    run_doc_generator(outfile, "resources/xml/urn.jaus.jss.core-v1.0/AccessControl.xml " \
        "resources/xml/urn.jaus.jss.core-v1.0/Discovery.xml " \
        "resources/xml/urn.jaus.jss.core-v1.0/Events.xml", \
        "test/docgen/output/multi-def-word", "--word")

    print_testinfo(5, "Multiple service defs, Word docx output, test keep intermediates toggle")
    run_doc_generator(outfile, "resources/xml/urn.jaus.jss.core-v1.0/AccessControl.xml " \
        "resources/xml/urn.jaus.jss.core-v1.0/Discovery.xml " \
        "resources/xml/urn.jaus.jss.core-v1.0/Events.xml", \
        "test/docgen/output/multi-def-word-with-intermeds", "--word --keepIntermeds")

# tests where doc generation should not succeed
def run_expected_fail_tests(outfile):
    print_testinfo(1, "Several invalid service definitions, attempt Word docx output.")
    run_doc_generator(outfile, "test/xml/FSMTransitionTest1.xml test/xml/FSMTransitionTest2.xml " \
        "test/xml/FSMTransitionTest3.xml", "test/docgen/output/import-fail-word", "--word")


#------------------------------------
# main entry point
#------------------------------------
def main():
    # setup output directory, output file
    abs_output_path = os.path.abspath("./output/")
    
    if not os.path.isdir(abs_output_path):
        os.mkdir(abs_output_path)
    
    outfile = open("./results.txt", "w")
    sys.stdout = outfile

    # change working directory to JTS base dir so we can execute DocGeneratorCLI class.
    os.chdir("../..")

    # tests covering cases where arguments are incorrect.
    run_syntax_error_tests(outfile)

    # tests covering doc generation for service definitions
    run_success_tests(outfile)

    # tests with invalid service definitions that should fail..
    run_expected_fail_tests(outfile)
    
    outfile.close()

    return 0
#------------------------------------



#------------------------------------
# stub to trigger execution of main
#------------------------------------
if __name__ == "__main__":
    main()
