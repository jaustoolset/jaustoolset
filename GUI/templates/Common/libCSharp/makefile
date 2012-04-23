
SOURCES = ../src/Transport/JausAddress.cpp ../src/Transport/OS.cpp

OBJ = OS.o JuniorAPI.o JausAddress.o

WRAP_OBJ = OS_wrap.o JuniorAPI_wrap.o JausAddress_wrap.o

INCLUDE = -I../include -I../include/Transport -I../include/InternalEvents -I../include/Messages



all: swig build
	g++ -shared -fPIC JuniorAPI.o JuniorAPI_wrap.o -o libJuniorAPI.so
	g++ -shared -fPIC JausAddress.o JausAddress_wrap.o -o libJausAddress.so
	g++ -shared -fPIC Address.o Address_wrap.o -o libAddress.so
	mv lib/wrappers/*.cs libCommon_CSharp/framework/transport/
	mv *o lib/

swig:
	# launch swig to generate wrappers
	#swig -csharp -c++ $(INCLUDE) interfaces/OS.i
	swig -csharp -c++ -namespace JTS $(INCLUDE) lib/wrappers/JuniorAPI.i
	swig -csharp -c++ -namespace JTS $(INCLUDE) lib/wrappers/Address.i
	swig -csharp -c++ -namespace JTS $(INCLUDE) lib/wrappers/JausAddress.i

	

build:
	# compile the wrappers
	g++ -fPIC -c lib/wrappers/Address_wrap.cxx ../src/Transport/Address.cpp $(INCLUDE)
	g++ -fPIC -c lib/wrappers/JausAddress_wrap.cxx ../src/Transport/JausAddress.cpp $(INCLUDE)
	g++ -fPIC -c lib/wrappers/JuniorAPI_wrap.cxx ../src/Transport/JuniorAPI.cpp $(INCLUDE)


clean:
	rm lib/wrappers/*_wrap.cxx
	rm libCommon_CSharp/framework/transport/SWIGTYPE*
	rm libCommon_CSharp/framework/transport/Address*
	rm libCommon_CSharp/framework/transport/JausAddress*
	rm libCommon_CSharp/framework/transport/JuniorAPI*
	rm libCommon_CSharp/framework/transport/JrErrorCode.java
	rm *o
