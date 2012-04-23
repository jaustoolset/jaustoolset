/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, United States Government
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the United States Government nor the names of 
its contributors may be used to endorse or promote products derived from 
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
*********************  END OF LICENSE ***********************************/


%module JuniorAPI
%{
#include "JuniorAPI.h"
%}

%include "typemaps.i"
%include "various.i"
%include "arrays_java.i"

/* Define the behavior for converting a byte[] to and from a char** */
%typemap(jni) char **BYTE "jbyteArray"
%typemap(jtype) char **BYTE "byte[]"
%typemap(jstype) char **BYTE "byte[]"
%typemap(in) char **BYTE {

	char* temp = (char*) JCALL2(GetByteArrayElements, jenv, $input, 0);
	$1 = &temp;
}

%typemap(argout) char **BYTE {
    JCALL3(ReleaseByteArrayElements, jenv, $input, ((jbyte *) *$1), 0); 
}

%typemap(javain) char **BYTE "$javainput"

/* Prevent default freearg typemap from being used */
%typemap(freearg) char **BYTE ""

/* Manually define functions and input */

extern JrErrorCode JrConnect(long id, char *BYTE, long *INOUT);

extern JrErrorCode JrSend(long handle, unsigned int dest, unsigned int size, char *BYTE, int priority, int flags, unsigned short msg_id);
extern JrErrorCode JrSend(long handle, unsigned int dest, unsigned int size, char *BYTE, int priority, int flags);
extern JrErrorCode JrSend(long handle, unsigned int dest, unsigned int size, char *BYTE, int priority);
extern JrErrorCode JrSend(long handle, unsigned int dest, unsigned int size, char *BYTE);

extern JrErrorCode JrReceive(long handle, unsigned int *INOUT, unsigned int *INOUT, char **BYTE, int *INOUT, int *INOUT, unsigned short *INOUT);

%import "JausUtils.h"
%import "OS.h"
%include "JuniorAPI.h"



