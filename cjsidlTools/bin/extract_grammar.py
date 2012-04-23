# extract_grammar.py
#

#!/usr/bin/env python
# jsidl2jaus.py
#
import sys
import optparse
import codecs
import re

inline_block_pat = re.compile(r'^.*{.*}.*')
code_sect_pat = re.compile(r'^.*(@init|@header).*{.*$')
open_block_pat = re.compile(r'^.*({)|([^\\]{).*')
close_block_pat = re.compile(r'^.*(})|([^\\]}).*')
comment_pat = re.compile(r'.*//.*{.*')
quoted_pat = re.compile(r".*'[{}]'.*")

def debug(level,msg):
    DEBUG = 1
    if level > DEBUG:
        sys.stderr.write(msg)

def clean_blocks(line):
    """ Simple function to clean Python code from a single
    line of input. Assumes no escaped quote chars."""
    outl = []
    block_depth = 0
    in_quote = None
    for c in line:
        if not in_quote and c in ['"', "'"]:
            in_quote = c
        elif in_quote and c == in_quote:
            in_quote = None
        if not in_quote:
            if c == '{':
                block_depth += 1
                continue
            elif c == '}':
                block_depth -= 1
                continue
        if block_depth == 0:
            outl.append(c)

    return ''.join(outl)
        
def process(inf,outf):
    data = inf.readlines()
    block_depth = 0
    for line in data:
        if block_depth == 0:
            mc = comment_pat.match(line)
            mcs = code_sect_pat.match(line)
            mob = open_block_pat.match(line)
            ib = inline_block_pat.match(line)
            qp = quoted_pat.match(line)
            if not (mc or ib or qp) and (mcs or mob):
                block_depth += 1
                debug(0,line.strip())
                debug(0,"block +, %d"%block_depth)
            elif ib:
                outf.write( clean_blocks(line) )
            else:
                outf.write(line)
        else:
            # in a block
            ib = inline_block_pat.match(line)
            mob = open_block_pat.match(line)
            mcb = close_block_pat.match(line)
            if not ib:
                if mob:
                    block_depth += 1
                    debug(0,line.strip())
                    debug(0,"block +, %d"%block_depth)
                elif mcb:
                    block_depth -= 1
                    debug(0,line.strip())
                    debug(0,"block -, %d"%block_depth)
            
def print_usage(f):
    f.write("python extract_grammar.py OPTIONS\n")
    f.write("   -i file \n")
    f.write("   --in=file    file containing JSIDL parser w/ inline code, defaults to stdin.\n")
    f.write("   -o file\n")
    f.write("   --out=file   file for extracted grammar output, defaults to stdout.\n")

if __name__=='__main__':
    p = optparse.OptionParser()
    p.add_option("-i", action="store", dest="infile")
    p.add_option("--in", action="store", dest="infile")
    p.add_option("-o", action="store", dest="outfile")
    p.add_option("--out", action="store", dest="outfile")

    # Get args and input/output files set up.
    opts, args = p.parse_args()
    if opts.infile:
        inf = file(opts.infile,'rb')
    else:
        inf = sys.stdin
    if opts.outfile:
        try:
            outf = file(opts.outfile, 'wb')
        except IOError, e:
            print "Can't open %s for write, %s"%(e.filename, e.strerror)
            raise SystemExit
    else:
        outf = sys.stdout
    # Do it
    process(inf,outf)
    outf.close()
