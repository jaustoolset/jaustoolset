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

import org.jts.gui.importJSIDL.ImportException;


//
// This class 'fixes' a JAXB count field so it's internally consistent.
// Because of JAVA limitations, maximum count can never exceed that stored
// by a signed 4 byte integer.  Furthermore, any unspecified fields are
// filled in.
//
public class CountField
{
	public static void fix( org.jts.jsidl.binding.CountField jxCountField ) 
	{
		if (jxCountField == null) return;	   
		
		// Due to JAVA limits, restrict large maximum counts
		if ( jxCountField.getFieldTypeUnsigned().equals("unsigned long integer") )
		{
		    jxCountField.setFieldTypeUnsigned("unsigned integer");
		}
	    	 
	    // Set min count, if not specified
	    if(jxCountField.getMinCount() == null)
	    {
	    	jxCountField.setMinCount("0");
	    }

        // Set max count, if not specified		    
	    if(jxCountField.getMaxCount() == null)
	    {
	    	if(jxCountField.getFieldTypeUnsigned().equals("unsigned byte"))
	    	{
		    	jxCountField.setMaxCount("255");
	    	}
	    	else if(jxCountField.getFieldTypeUnsigned().equals("unsigned short integer"))
	    	{
	    	    jxCountField.setMaxCount("65535");
	    	}
	    	else if(jxCountField.getFieldTypeUnsigned().equals("unsigned integer"))
	    	{
	    	    jxCountField.setMaxCount("2147483647");
	    	}
	    	else
	    	{
	    		// Unknown type
	    		// This shouldn't happen after the XML is validated
	    		throw new ImportException("Unknown jxCountField type (" + jxCountField.getFieldTypeUnsigned() + ")");
	    	}
	    }
	    else
	    {
			// Still need to clamp maximum value to something we can represent in a signed integer.
			if (Long.parseLong( jxCountField.getMaxCount() ) > 2147483647L)
			{
				jxCountField.setMaxCount("2147483647");
			}
		}	
	}
	
	public static void fix( org.jts.jsidl.binding.VariableFormatField jxVariableFormatField ) 
	{
		if (jxVariableFormatField == null) return;
	    fix( jxVariableFormatField.getCountField() );
	}		
	public static void fix( org.jts.jsidl.binding.VariableLengthField jxVariableLengthField ) 
	{
		if (jxVariableLengthField == null) return;
	    fix( jxVariableLengthField.getCountField() );
	}		
	public static void fix( org.jts.jsidl.binding.VariableLengthString jxVariableLengthString ) 
	{
		if (jxVariableLengthString == null) return;
	    fix( jxVariableLengthString.getCountField() );
	}			
	public static void fix( org.jts.jsidl.binding.Sequence jxSequence ) 
	{
		if (jxSequence == null) return;
		
		for(Object field : jxSequence.getRecordOrDeclaredRecordOrList())
        {
        	if (field instanceof org.jts.jsidl.binding.Variant) 
        		fix( (org.jts.jsidl.binding.Variant)field );
            else if (field instanceof org.jts.jsidl.binding.Record) 
				fix( (org.jts.jsidl.binding.Record)field );
            else if (field instanceof org.jts.jsidl.binding.List) 
				fix( (org.jts.jsidl.binding.List)field );
            else if (field instanceof org.jts.jsidl.binding.Sequence)
				fix( (org.jts.jsidl.binding.Sequence)field );
        }
	}		
	public static void fix( org.jts.jsidl.binding.Record jxRecord ) 
	{
		if (jxRecord == null) return;
		
		for(Object field : jxRecord.getArrayOrFixedFieldOrVariableField())
        {
        	if (field instanceof org.jts.jsidl.binding.Array) 
        		fix( (org.jts.jsidl.binding.Array)field );
            else if (field instanceof org.jts.jsidl.binding.VariableFormatField) 
				fix( (org.jts.jsidl.binding.VariableFormatField)field );
            else if (field instanceof org.jts.jsidl.binding.VariableLengthField) 
				fix( (org.jts.jsidl.binding.VariableLengthField)field );
            else if (field instanceof org.jts.jsidl.binding.VariableLengthString) 
				fix( (org.jts.jsidl.binding.VariableLengthString)field );
        }
	}
	public static void fix( org.jts.jsidl.binding.Variant jxVariant ) 
	{
		if (jxVariant == null) return;
		
        for(Object field : jxVariant.getRecordOrDeclaredRecordOrList())
        {
        	if (field instanceof org.jts.jsidl.binding.Record) 
        		fix( (org.jts.jsidl.binding.Record)field );
            else if (field instanceof org.jts.jsidl.binding.Sequence) 
				fix( (org.jts.jsidl.binding.Sequence)field );
            else if (field instanceof org.jts.jsidl.binding.Variant) 
				fix( (org.jts.jsidl.binding.Variant)field );
            else if (field instanceof org.jts.jsidl.binding.List) 
				fix( (org.jts.jsidl.binding.List)field );
         }
	}
	public static void fix( org.jts.jsidl.binding.List jxList ) 
	{
	    if (jxList == null) return;
	    fix( jxList.getCountField() );
	    
	    // List may also embed elements that in turn contain a count_field
	    fix( jxList.getRecord() );
	    fix( jxList.getList() );
	    fix( jxList.getSequence() );
	    fix( jxList.getVariant() );	    
	}				
	public static void fix( org.jts.jsidl.binding.Header jxHeader ) 
	{
		if (jxHeader == null) return;
	    fix( jxHeader.getRecord() );
	    fix( jxHeader.getList() );
	    fix( jxHeader.getSequence() );
	    fix( jxHeader.getVariant() );		
	}		
	public static void fix( org.jts.jsidl.binding.Footer jxFooter ) 
	{
		if (jxFooter == null) return;
	    fix( jxFooter.getRecord() );
	    fix( jxFooter.getList() );
	    fix( jxFooter.getSequence() );
	    fix( jxFooter.getVariant() );		
	}		
	public static void fix( org.jts.jsidl.binding.EventDef jxEventDef ) 
	{
		if (jxEventDef == null) return;
	    fix( jxEventDef.getHeader() );
	    fix( jxEventDef.getBody() );
	    fix( jxEventDef.getFooter() );
	}		
	public static void fix( org.jts.jsidl.binding.Body jxBody ) 
	{
		if (jxBody == null) return;
	    fix( jxBody.getRecord() );
	    fix( jxBody.getList() );
	    fix( jxBody.getSequence() );
	    fix( jxBody.getVariant() );		
	}		
	public static void fix( org.jts.jsidl.binding.Array jxArray ) 
	{
		if (jxArray == null) return;
		fix(jxArray.getVariableFormatField());
        fix(jxArray.getVariableLengthField());
        fix(jxArray.getVariableLengthString());
	}

	public static void fix( Object jaxbObject )
	{
		if( jaxbObject instanceof org.jts.jsidl.binding.CountField )
		{
			fix( (org.jts.jsidl.binding.CountField)jaxbObject );
		}
		else if( jaxbObject instanceof org.jts.jsidl.binding.VariableFormatField )
		{
			fix( (org.jts.jsidl.binding.VariableFormatField)jaxbObject );
		}
		else if( jaxbObject instanceof org.jts.jsidl.binding.VariableLengthField )
		{
			fix( (org.jts.jsidl.binding.VariableLengthField)jaxbObject );
		}
		else if( jaxbObject instanceof org.jts.jsidl.binding.VariableLengthString )
		{
			fix( (org.jts.jsidl.binding.VariableLengthString)jaxbObject );
		}
		else if( jaxbObject instanceof org.jts.jsidl.binding.Sequence )
		{
			fix( (org.jts.jsidl.binding.Sequence)jaxbObject );
		}
		else if( jaxbObject instanceof org.jts.jsidl.binding.Record )
		{
			fix( (org.jts.jsidl.binding.Record)jaxbObject );
		}
		else if( jaxbObject instanceof org.jts.jsidl.binding.Variant )
		{
			fix( (org.jts.jsidl.binding.Variant)jaxbObject );
		}
		else if( jaxbObject instanceof org.jts.jsidl.binding.List )
		{
			fix( (org.jts.jsidl.binding.List)jaxbObject );
		}
		else if( jaxbObject instanceof org.jts.jsidl.binding.Header )
		{
			fix( (org.jts.jsidl.binding.Header)jaxbObject );
		}
		else if( jaxbObject instanceof org.jts.jsidl.binding.Footer )
		{
			fix( (org.jts.jsidl.binding.Footer)jaxbObject );
		}
		else if( jaxbObject instanceof org.jts.jsidl.binding.EventDef )
		{
			fix( (org.jts.jsidl.binding.EventDef)jaxbObject );
		}
		else if( jaxbObject instanceof org.jts.jsidl.binding.Body )
		{
			fix( (org.jts.jsidl.binding.Body)jaxbObject );
		}
		else if( jaxbObject instanceof org.jts.jsidl.binding.Array )
		{
			fix( (org.jts.jsidl.binding.Array)jaxbObject );
		}
	}
}
