import os
if ('JTS_COMMON_PATH' in os.environ):
	common_dir = os.environ['JTS_COMMON_PATH']
else:
	print ('Must define JTS_COMMON_PATH before building')
	exit(-1)

VariantDir('Build', 'src', duplicate=0)
include = [ '#.', common_dir + '/include']
libpath = ['#lib', '#./lib/lib', common_dir + '/libJava/lib', common_dir + '/lib', './bin/']

# Copy the framework to the local ./lib directory
# in advance of actual compilation
##################################################
if not os.path.exists('lib'):
	Execute( Mkdir('lib') )
if not os.path.isfile('lib/Manifest.txt'):
	Execute( Copy( 'lib/Manifest.txt', common_dir + '/libJava/Manifest.txt' ))
if not os.path.isfile('lib/statemap.jar'):
	Execute( Copy( 'lib/statemap.jar', common_dir + '/libJava/statemap.jar' ))
if not os.path.exists('lib/framework'):
	Execute( Copy( 'framework', common_dir + '/libJava/framework/%transport_version%/framework' ))

# Generate the environment
###############################################
env = Environment(ENV=os.environ, tools = ['jar', 'javah', 'default'])
env.Append( JAVACLASSPATH = ['./lib/statemap.jar'])
env.Append( CPPPATH = include)
env.Append( LIBPATH = libpath)
env.Append( INSTALL_LIB = '#./lib')
env.Append( BINPATH = '#./bin')

# when scons issues a 'jar' command, it will use the -C command-line option to jar to force the jar executable to
# change to the directory where the .class files are located that must be added to the jar file.  This ensures the
# classes in the class files end up at paths in the jarfile matching their packages.
env.Append( JARCHDIR = './Build') #

# Platform specific info, primarily for the C++ library
###############################################
swigflag = []
if env['PLATFORM'] == 'cygwin':
	print ('scons: Building for CYGWIN...')
	env.Append( CCFLAGS = ['-D__CYGWIN__'] )
	env.Append( CPPPATH = [ os.environ['JAVA_HOME'] + '/include', os.environ['JAVA_HOME'] + '/include/win32' ] )
	
elif os.name == 'nt':
	print ('scons: Building for Windows...')
	env.Append( CCFLAGS = ['-DWIN32', '-DWINDOWS', '/MD', '-EHsc','-D_CRT_SECURE_NO_DEPRECATE'])
	env.Append( LINKFLAGS = ['/MANIFEST', '/DEFAULTLIB:"WSock32.Lib"'] )
	env.Append( CPPPATH = [ os.environ['JAVA_HOME'] + '/include', os.environ['JAVA_HOME'] + '/include/win32' ] )
	swigflag = env['LINKFLAGS']

elif env['PLATFORM'] == 'darwin':
	env.Append( LINKFLAGS = ['-lpthread'] )
	env.Append( CPPFLAGS = ['-g', '-Wno-write-strings'])
	env.Append( CCFLAGS = ['-D__MAC__'] )
	
elif os.name == 'posix':
	env.Append( LINKFLAGS = ['-lpthread', '-lrt'] )
	env.Append( CPPFLAGS = ['-g', '-Wno-write-strings'])
	env.Append( CPPPATH = [os.environ['JAVA_HOME'] + '/include',  os.environ['JAVA_HOME'] + '/include/linux'])
	env.Replace(CC = 'g++')
	swigflag = ['-fPIC']
	

# Install and build Build C++ and SWIG libraries.
###############################################

# The C++ library
Export('env')
SConscript(common_dir + '/Sconstruct')

# The Swig libraries
env.Append(CPPPATH = [common_dir + '/include', common_dir + '/include/Transport', common_dir + '/include/InternalEvents', common_dir + '/Messages'])

Swig1 = env.SharedLibrary(target = './bin/JuniorAPI', source = [common_dir + '/libJava/lib/wrappers/JuniorAPI_wrap.cxx'], LIBS=['Common'], LINKFLAGS = swigflag)

Swig2 =env.SharedLibrary(target = './bin/JausAddress', source = [common_dir + '/libJava/lib/wrappers/JausAddress_wrap.cxx',  common_dir + '/libJava/lib/wrappers/Address_wrap.cxx'], LIBS=['Common'], LINKFLAGS = swigflag)

# Build
###############################################

classes = env.Java('./Build', '#.')

env.Jar('./bin/%file_name%.jar', classes + ['./lib/Manifest.txt'] )

env.Install('#./bin', '#./lib/statemap.jar')

# One more Windows hoop to jump through.  Since we are using the shared
# C runtime, we need to include a manifest in our exe.
# Add a post-build step to embed the manifest using mt.exe
# The number at the end of the line indicates the file type (1: EXE; 2:DLL).
if os.name == "nt":
   env.AddPostAction(Swig1, 'mt.exe -nologo -manifest ${TARGET}.manifest -outputresource:$TARGET;2')
   env.AddPostAction(Swig2, 'mt.exe -nologo -manifest ${TARGET}.manifest -outputresource:$TARGET;2')
