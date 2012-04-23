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

/*
 * @(#)MessageSetGenerator.java		0.1 2008/09/03
 * 
 * Copyright
 */

package org.jts.codegenerator;

import org.jts.jsidl.binding.*;
import java.util.Vector;

/**
 * This class will generate C++ code from a JSIDL Message Set 
 *
 * @version		0.1	3 Sept 2008
 * @author		Nicholas M. Johnson
 * @author		Gregory Garcia
 * @author		Jean-Francois Kamath
 * @author              Gina Nearing
 *
 */
public class MessageSetGenerator
{
	private CodeLines.CodeType codeType;
	private MessageSet msgSet;
	private Vector<String> inputMsgNameList;
	private Vector<String> outputMsgNameList;


	public MessageSetGenerator(CodeLines.CodeType codeType, MessageSet msgSet)
	{
		this.codeType = codeType;
		this.msgSet = msgSet;

		inputMsgNameList = new Vector<String>();
		outputMsgNameList = new Vector<String>();
	}
	
	/**
	 * This method goes through the message set and calls the appropriate
	 * helper functions
	 * 
	 * Assumptions:	The object has been created.
	 * 				messageSet is non-NULL
	 *  
	 */
	public void run(String namespace, String outDir)
	{
		Vector<String> baseClassList = new Vector<String>();

		baseClassList.add("public Message");

		processInputSet(namespace, msgSet.getInputSet(), outDir);
		processOutputSet(namespace, msgSet.getOutputSet(), outDir);
	}
	
	/**
	 * @param inSet	the Binding.InputSet that will be processed
         * @param namespace
         * @param outDir
	 * 
	 */
	private void processInputSet(String namespace, InputSet inSet, String outDir)
	{		
		for (Object object:inSet.getMessageDefOrDeclaredMessageDef())
		{
			if (object instanceof MessageDef)
			{
				MessageDef msgDef = (MessageDef)object;
				MessageDefGenerator mdGen = new MessageDefGenerator(codeType, msgDef);

				mdGen.run(namespace, outDir);

				inputMsgNameList.add(msgDef.getName());
			}
			else if (object instanceof DeclaredMessageDef)
			{
				DeclaredMessageDef dMsgDef = (DeclaredMessageDef)object;
				// TODO?, Declared messages should be resolved before this class is called
			}
		}
	}
	

	/**
	 *@param outSet	the Binding.OutputSet that will be processed
	 *
	 */
	private void processOutputSet(String namespace, OutputSet outSet, String outDir)
	{
		for (Object object:outSet.getMessageDefOrDeclaredMessageDef())
		{
			if (object instanceof MessageDef)
			{
				MessageDef msgDef = (MessageDef)object;
				MessageDefGenerator mdGen = new MessageDefGenerator(codeType, msgDef);
				
				mdGen.run(namespace, outDir);
				outputMsgNameList.add(msgDef.getName());
			}
			else if (object instanceof DeclaredMessageDef)
			{
				DeclaredMessageDef dMsgDef = (DeclaredMessageDef)object;
				// TODO?, Declared messages should be resolved before this class is called
			}
		}
	}
	
	
	public Vector<String> getInputMsgNameList()
	{
		return inputMsgNameList;
	}
	
	public Vector<String> getOutputMsgNameList()
	{
		return outputMsgNameList;
	}

}
