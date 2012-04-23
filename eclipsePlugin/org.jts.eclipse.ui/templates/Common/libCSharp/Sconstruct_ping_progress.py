import os
if ('JTS_COMMON_PATH' in os.environ):
	common_dir = os.environ['JTS_COMMON_PATH']
else:
	print 'Must define JTS_COMMON_PATH before building'
	exit(-1)


VariantDir('Build', 'src', duplicate=0)
include = [ '#.', common_dir + '/include']
libpath = ['#lib', '#./lib/lib', common_dir + '/libCSharp/lib', common_dir + '/lib', './bin/']


# Generate the environment
###############################################
#env = Environment(ENV=os.environ, TOOLS = [default, 'mcs', 'mscs', 'msvs',]) #mcs = mono, mscs/msvs = win32
	
env = Environment (
	tools = [ 'default', 'mscs', 'msvs', 'mcs'],
	toolpath = ['.'],
	ENV=os.environ,
	MSVS_IGNORE_IDE_PATHS=1
	)
env.Append( CPPPATH = include)
env.Append( LIBPATH = libpath)
env.Append( INSTALL_LIB = '#./lib')
env.Append( BINPATH = '#./bin')


# Platform specific info, primarily for the C++ library
###############################################
swigflag = []
if env['PLATFORM'] == 'cygwin':
	print 'scons: Building for CYGWIN...'
	env.Append( CCFLAGS = ['-D__CYGWIN__' ] )
	env.Append( LINKFLAGS = ['-Wl,--enable-auto-import'] )

elif os.name == 'nt':
	print 'scons: Building for Windows...'
	env.Append( CCFLAGS = ['-DWIN32', '-DWINDOWS', '/MD', '-EHsc','-D_CRT_SECURE_NO_DEPRECATE'])
	env.Append( LINKFLAGS = ['/DEFAULTLIB:"WSock32.Lib"'] )
	swigflag = env['LINKFLAGS']

elif env['PLATFORM'] == 'darwin':
	env.Append( LINKFLAGS = ['-lpthread'] )
	env.Append( CPPFLAGS = ['-g', '-Wno-write-strings'])
	env.Append( CCFLAGS = ['-D__MAC__'] )

elif os.name == 'posix':
	env.Append( LINKFLAGS = ['-lpthread', '-lrt'] )
	env.Append( CPPFLAGS = ['-g', '-Wno-write-strings'])
	env.Replace(CC = 'g++')
	swigflag = ['-fPIC']

# Install and build Build C++ and SWIG libraries.
###############################################

# The C++ library
Export('env')
SConscript([common_dir + '/Sconstruct', common_dir + '/libCSharp/Sconstruct'])

# The Swig libraries
env.Append(CPPPATH = [common_dir + '/include', common_dir + '/include/Transport', common_dir + '/include/InternalEvents', common_dir + '/Messages'])

Swig1 = env.SharedLibrary(target = './bin/JuniorAPI', source = [common_dir + '/libCSharp/lib/wrappers/JuniorAPI_wrap.cxx'], LIBS=['Common'], LINKFLAGS = swigflag)

Swig2 =env.SharedLibrary(target = './bin/JausAddress', source = [common_dir + '/libCSharp/lib/wrappers/JausAddress_wrap.cxx'], LIBS=['Common'], LINKFLAGS = swigflag)
Swig3 = env.SharedLibrary(target = './bin/Address', source =[common_dir + '/libCSharp/lib/wrappers/Address_wrap.cxx'], LIBS=['Common'], LINKFLAGS = swigflag)

env.AddToRefPaths(Swig1)
env.AddToRefPaths(Swig2)
env.AddToRefPaths(Swig3)

# Build
###############################################

sources = Split("""
	src/Main.cs
	src/PingCmpt.cs
	src/urn_jts_PingServer_1_0/PingServer_PingFSM.cs
	src/urn_jts_PingServer_1_0/PingServer_PingFSM_sm.cs
	src/urn_jts_PingServer_1_0/PingServerService.cs
	src/urn_jts_PingServer_1_0/Messages/QueryHeartbeatPulse.cs
	src/urn_jts_PingServer_1_0/Messages/ReportHeartbeatPulse.cs
	src/urn_jts_PingClient_1_0/PingClient_PingClientFSM.cs
	src/urn_jts_PingClient_1_0/PingClient_PingClientFSM_sm.cs
	src/urn_jts_PingClient_1_0/PingClientService.cs
	src/urn_jts_PingClient_1_0/Messages/QueryHeartbeatPulse.cs
	src/urn_jts_PingClient_1_0/Messages/ReportHeartbeatPulse.cs
	""")

# Refs += libcommon - cscommon already in in path.

prog = env.CLIProgram('./bin/PingCmpt', sources)


# Specify dependancies
env.Requires(prog, [Swig1, Swig2, Swig3])
