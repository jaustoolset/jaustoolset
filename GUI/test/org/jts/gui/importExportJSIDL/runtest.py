import os
import subprocess

print ("Running Import-Export test for JSIDL")

# Clean the output_dir by removing all XML files
for file in os.listdir("output_xml"):
    if (file <> ".svn"):
        os.remove("output_xml/"+file)

# Run the unit-test ANT target
baseworkdir = os.getcwd();
f = open("./build-results.txt", "w")
os.chdir("../../../../..")
execObj = subprocess.Popen("ant unit-test", stdout=f, stderr=f, shell=True)
execObj.wait()
f.close()
os.chdir(baseworkdir)

# Compare output to the golden set
f = open("./test-results.txt", "w")
execObj = subprocess.Popen("diff output_xml golden_xml", stdout=f, stderr=f, shell=True)
execObj.wait()
f.close()

# Print out success or failure
retval = execObj.returncode
if (retval==0):
    print ("Success!  Generated files equal to golden set")
else:
    print ("Errors found!  Check test-results.txt for details")


