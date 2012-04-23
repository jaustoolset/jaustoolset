from __future__ import print_function

import os
import sys
import subprocess

from optparse import OptionParser
from optparse import OptionGroup

def main():

    parser = OptionParser(usage="Usage: %prog [options] arg1 arg2 ...\n\nNote: All relative paths must be specified " \
        "relative to JTS' GUI\\ base directory!")

    parser.add_option("-o", "--output", dest="output_dir", \
        help="Path to output directory, required.", metavar="OUTPUT_DIR")
    parser.add_option("--styleCust", dest="style_cust_dir", \
        help="Path to custom stylization directory, optional.", metavar="STYLE_CUST_DIR")
    parser.add_option("--keepIntermeds", dest="keep_intermeds", action="store_true", default=False, \
        help="Don't delete the intermediate files produced during document generation. " \
            "Optional; has no effect on Framed HTML output."\
        )

    output_group = OptionGroup(parser, "Output Format Options", "These options are mutually exclusive of one another" \
        " and control the output format of generated documentation.  One and only one must be specified.")
    output_group.add_option("--lhtml", dest="lhtml_output", action="store_true", default=False, \
        help="Produce output in Linear HTML format.")
    output_group.add_option("--fhtml", dest="fhtml_output", action="store_true", default=False, \
        help="Produce output in Framed HTML format.")
    output_group.add_option("--word", dest="word_output", action="store_true", default=False, \
        help="Produce output in Word .docx format.")
    parser.add_option_group(output_group)


    (options, args) = parser.parse_args()

    print("Selected options:", options)

    print("Provided args: ", args)

    num_output_type_opts = 0;
    if options.lhtml_output:
        num_output_type_opts += 1
    if options.fhtml_output:
        num_output_type_opts += 1
    if options.word_output:
        num_output_type_opts += 1

    # find out what required options are missing or invalid and print errors

    if num_output_type_opts != 1 :
        print ('One and only one output format option must be specified.')
        parser.print_help()
        sys.exit(0)

    if not options.output_dir:
        print ('The output directory must be specified.')
        parser.print_help()
        sys.exit(0)

    # assemble the options string.
    opts_string = ""

    if options.lhtml_output:
        opts_string = ' '.join([opts_string, '--lhtml'])

    if options.fhtml_output:
        opts_string = ' '.join([opts_string, '--fhtml'])

    if options.word_output:
        opts_string = ' '.join([opts_string, '--word'])

    if options.output_dir:
        opts_string = ' '.join([opts_string, ''.join(['--output=', options.output_dir])])

    if options.style_cust_dir:
        opts_string = ' '.join([opts_string, '--styleCust', options.style_cust_dir])
        
    if options.keep_intermeds:
        opts_string = ' '.join([opts_string, '--keepIntermeds'])

    # assemble the positional arguments string
    args_string = " ".join(args)

    # command string: string that will be invoked at command line
    command_string = 'ant -Ddoc.generator.args="{0} {1}" run-doc-generator'.format(opts_string, args_string)

    print (command_string)

    # assuming this script is being run from the directory where it is located, which is in scripts/ relative to
    # the base GUI/ directory.
    os.chdir("../")
    execObj = subprocess.Popen(command_string, shell=True)
    execObj.wait()

#------------------------------------
# stub to trigger execution of main
#------------------------------------
if __name__ == "__main__":
    main()
