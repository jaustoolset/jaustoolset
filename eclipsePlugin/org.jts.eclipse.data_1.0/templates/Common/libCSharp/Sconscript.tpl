Import('env')

sources = Split("""
%source_list%	""")

libs = ['statemap', 'CSharpCommon' %service_libs%]
pathlibs = env.CLIRefs(libs)

lib = env.CLILibrary('./lib/%service_name%', sources, ASSEMBLYREFS=pathlibs)
env.Install( env['INSTALL_LIB'], lib )
env.Install( env['BINPATH'], lib )
env.AddToRefPaths(lib)
Return('lib')