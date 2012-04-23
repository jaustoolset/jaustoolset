# for mass alteration of C# and/or Java ATF unit test classes
# run this from /GUI/tests/atf, or all bets are off.

import sys
import shutil
import os
import scriptutil

# visual studio regexes for reuse
# data{:Nd+}_{:c+}\[{:Nd+}\] =  / data\1_\2 = new byte[\3] 
#

java_exprs = [
    (r'class', r'public class', None),
    (r': public .* {', r'extends TestCase {', None),
    (r"CPPUNIT_ASSERT\(\s*([ a-zA-Z0-9()._>,\[\]+\-*/]+)\s*==\s*([ a-zA-Z0-9()._,>\[\]+\-*/]+)\s*\)", r"assertEquals(\1, \2)", None),
    (r"->", ".", None),
    (r"private:", "", None),
    (r"public:", "", None),
    (r"void", "public void", None),
]
csharp_exprs = [
    (r'unsigned char', r'byte', None),
    (r'class', '[TestFixture]\npublic class', None),
    (r': public .* {', r'extends TestCase {', None),
    (r"CPPUNIT_ASSERT\(\s*([ a-zA-Z0-9()._>,\[\]+\-*/]+)\s*==\s*([ a-zA-Z0-9()._,>\[\]+\-*/]+)\s*\)", r"assertEquals(\1, \2)", None),
    (r"->", ".", None),
    (r"private:", "", None),
    (r"public:", "", None),
    (r"void test", "    [Test]\n    public void test", None),
    (r"  void setUp", "    [SetUp]\n    public void setUp", None),
]

def main():
    do_java = False;
    do_csharp = True;

    # get list of test directories
    test_dirs = os.listdir('./')

    cpp_files = [
        'Array8/Array8_cmpt_1/array8_utst.cpp',
        'Array9/Array9_cmpt_1/array9_utst.cpp',
        'Array10/Array10_cmpt_1/array10_utst.cpp',
        'Variant2/Variant2_cmpt_1/variant2_utst.cpp',
        'Variant3/Variant3_cmpt_1/variant3_utst.cpp',
        'Variant4/Variant4_cmpt_1/variant4_utst.cpp'
    ]
    java_files = [
        'Array8/Array8_java_1/Array8_utst.java',
        'Array9/Array9_java_1/Array9_utst.java',
        'Array10/Array10_java_1/Array10_utst.java',
        'Variant2/Variant2_java_1/Variant2_utst.java',
        'Variant3/Variant3_java_1/Variant3_utst.java',
        'Variant4/Variant4_java_1/Variant4_utst.java'
    ]
    csharp_files = [
        'Array8/Array8_csharp_1/src/Array8_utst.cs',
        'Array9/Array9_csharp_1/src/Array9_utst.cs',
        'Array10/Array10_csharp_1/src/Array10_utst.cs',
        'Variant2/Variant2_csharp_1/src/Variant2_utst.cs',
        'Variant3/Variant3_csharp_1/src/Variant3_utst.cs',
        'Variant4/Variant4_csharp_1/src/Variant4_utst.cs'
    ]

    java_filenames = [
        'Array8_utst.java',
        'Array9_utst.java',
        'Array10_utst.java',
        'Variant2_utst.java',
        'Variant3_utst.java',
        'Variant4_utst.java',
    ]
    csharp_filenames = [
        'Array8_utst.cs',
        'Array9_utst.cs',
        'Array10_utst.cs',
        'Variant2_utst.cs',
        'Variant3_utst.cs',
        'Variant4_utst.cs',
    ]

    if do_java:
        # start with copies of the C++ files
        for (cpp_file, java_file) in zip(cpp_files, java_files):
            shutil.copy(cpp_file, java_file)
        scriptutil.freplace(path='./', regexl=java_exprs, shellglobs=java_filenames)
        bak_files = scriptutil.ffind(path='./', shellglobs=['*_utst.java.bak'])

    if do_csharp:
        # start with copies of the C++ files
        for (cpp_file, csharp_file) in zip(cpp_files, csharp_files):
            shutil.copy(cpp_file, csharp_file)
            
        scriptutil.freplace(path='./', regexl=csharp_exprs, shellglobs=csharp_filenames)
        
        bak_files = scriptutil.ffind(path='./', shellglobs=['*_utst.cs.bak'])
        
#    if do_java:
#        if len(java_files) > 0:
#            scriptutil.freplace(path='./', regexl=java_exprs, shellglobs=[java_files])
#        else:
#            scriptutil.freplace(path='./', regexl=java_exprs, shellglobs=['*_utst.java'])
#        bak_files = scriptutil.ffind(path='./', shellglobs=['*_utst.java.bak'])
#        for bak_file in bak_files: os.remove(bak_file)
#
#    if do_csharp:
#        scriptutil.freplace(path='./', regexl=csharp_exprs, shellglobs=['*_utst.cs'])
#        bak_files = scriptutil.ffind(path='./', shellglobs=['*_utst.cs.bak'])
#        for bak_file in bak_files: os.remove(bak_file)



if __name__ == "__main__":
    main()
