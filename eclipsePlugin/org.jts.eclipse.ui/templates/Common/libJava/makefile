
SOURCES = ../src/Transport/JausAddress.cpp ../src/Transport/OS.cpp

OBJ = OS.o JuniorAPI.o JausAddress.o

WRAP_OBJ = OS_wrap.o JuniorAPI_wrap.o JausAddress_wrap.o

INCLUDE = -I../include -I../include/Transport -I../include/InternalEvents -I../include/Messages

JAVA_INCLUDE = -I/usr/java/jdk1.6.0_21/include -I/usr/java/jdk1.6.0_21/include/linux $(INCLUDE)

all: swig build
	g++ -shared -fPIC JuniorAPI.o JuniorAPI_wrap.o -o libJuniorAPI.so
	g++ -shared -fPIC JausAddress.o JausAddress_wrap.o Address.o Address_wrap.o -o libJausAddress.so
	mv lib/wrappers/*.java framework/transport/
	mv *o lib/


swig:
	# launch swig to generate wrappers
	#swig -java -c++ $(INCLUDE) interfaces/OS.i
	swig -java -c++ -package framework.transport $(INCLUDE) lib/wrappers/JuniorAPI.i
	swig -java -c++ -package framework.transport $(INCLUDE) lib/wrappers/Address.i
	swig -java -c++ -package framework.transport $(INCLUDE) lib/wrappers/JausAddress.i

build:
	# compile the wrappers
	#g++ -fpic -c interfaces/OS_wrap.cxx ../src/Transport/OS.cpp $(JAVA_INCLUDE)
	g++ -fPIC -c lib/wrappers/Address_wrap.cxx ../src/Transport/Address.cpp $(JAVA_INCLUDE)
	g++ -fPIC -c lib/wrappers/JausAddress_wrap.cxx ../src/Transport/JausAddress.cpp $(JAVA_INCLUDE)
	g++ -fPIC -c lib/wrappers/JuniorAPI_wrap.cxx ../src/Transport/JuniorAPI.cpp $(JAVA_INCLUDE)


clean:
	rm lib/wrappers/*_wrap.cxx
	rm framework/transport/SWIGTYPE*
	rm framework/transport/Address*
	rm framework/transport/JausAddress*
	rm framework/transport/JuniorAPI*
	rm framework/transport/JrErrorCode.java
	rm lib/*.so
	rm lib/*.o

