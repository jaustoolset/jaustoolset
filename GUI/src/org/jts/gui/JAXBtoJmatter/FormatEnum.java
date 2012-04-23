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

package org.jts.gui.JAXBtoJmatter;

import com.u2d.app.Context;

// This class converts a JAXB FormatEnum to a JMatter FormatEnum
public class FormatEnum
{
	// This static method does the conversion from JAXB Binding to JMatter object 
	public static com.u2d.generated.FormatEnum lookupOrCreate( org.jts.jsidl.binding.FormatEnum jxFormatEnum ) 
	{
		// Get and handle on a hibernate session
		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		
		// NOTE: There is nothing significant in a FormatEnum's definition that would allow it to be looked up in the db
		com.u2d.generated.FormatEnum jmFormatEnum = new com.u2d.generated.FormatEnum();
		
		jmFormatEnum.getName().setValue("MUST_RENAME");
		
		// Index
		jmFormatEnum.getIndex().setValue(jxFormatEnum.getIndex());
		
	    // Field Format
	    jmFormatEnum.getFieldFormat().getCode().setValue(jxFormatEnum.getFieldFormat());
	    jmFormatEnum.getFieldFormat().getCaption().setValue(jxFormatEnum.getFieldFormat()); 
	    
		// Saves the FormatEnum
		persistenceMechanism.save(jmFormatEnum);

		jmFormatEnum.firePropertyChange("FormatEnum", jmFormatEnum, jmFormatEnum);

		return jmFormatEnum;
	}	
}
