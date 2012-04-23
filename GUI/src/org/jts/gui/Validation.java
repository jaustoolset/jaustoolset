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
 * Validation.java
 *
 * This class contains static methods to which the auto-generated gui code delegates its validation work.
 */

package org.jts.gui;


import javax.xml.bind.JAXB;
import org.jts.validator.*;

import com.u2d.model.*;
import java.util.List;


public  class Validation {

  private static boolean DEBUG = false;
  private static boolean checkingDuplicates = false;
  
  public static int validateAndDuplicateCheck( ComplexEObject modelObj )
  {
	  checkingDuplicates = true;
	  int result = validate( modelObj );
	  checkingDuplicates = false;
	  
	  return result;
  }
  
  public static int validate( ComplexEObject modelObj ) {
    if( modelObj instanceof com.u2d.generated.ServiceSet )
      return validateServiceSet( (com.u2d.generated.ServiceSet)modelObj );
    else if( modelObj instanceof com.u2d.generated.ServiceDef )
      return validateServiceDef( (com.u2d.generated.ServiceDef)modelObj );
    else if( modelObj instanceof com.u2d.generated.ProtocolBehavior )
      return validateProtocolBehavior( (com.u2d.generated.ProtocolBehavior)modelObj );
    else if( modelObj instanceof com.u2d.generated.MessageDef )
      return validateMessageDef( (com.u2d.generated.MessageDef)modelObj );
    else if( modelObj instanceof com.u2d.generated.EventDef )
      return validateEventDef( (com.u2d.generated.EventDef)modelObj );
    else if( modelObj instanceof com.u2d.generated.Header )
      return validateHeader( (com.u2d.generated.Header)modelObj );
    else if( modelObj instanceof com.u2d.generated.Body )
      return validateBody( (com.u2d.generated.Body)modelObj );
    else if( modelObj instanceof com.u2d.generated.Footer )
      return validateFooter( (com.u2d.generated.Footer)modelObj );
    else if( modelObj instanceof com.u2d.generated.Array )
      return validateArray( (com.u2d.generated.Array)modelObj );
    else if( modelObj instanceof com.u2d.generated.Dimension )
      return validateDimension( (com.u2d.generated.Dimension)modelObj );
    else if( modelObj instanceof com.u2d.generated.Record )
      return validateRecord( (com.u2d.generated.Record)modelObj );
    else if( modelObj instanceof com.u2d.generated.List )
      return validateList( (com.u2d.generated.List)modelObj );
    else if( modelObj instanceof com.u2d.generated.Variant )
      return validateVariant( (com.u2d.generated.Variant)modelObj );
    else if( modelObj instanceof com.u2d.generated.Sequence )
      return validateSequence( (com.u2d.generated.Sequence)modelObj );
    else if( modelObj instanceof com.u2d.generated.FixedField )
      return validateFixedField( (com.u2d.generated.FixedField)modelObj );
    else if( modelObj instanceof com.u2d.generated.ValueSet )
      return validateValueSet( (com.u2d.generated.ValueSet)modelObj );
    else if( modelObj instanceof com.u2d.generated.ValueRange )
      return validateValueRange( (com.u2d.generated.ValueRange)modelObj );
    else if( modelObj instanceof com.u2d.generated.ValueEnum )
      return validateValueEnum( (com.u2d.generated.ValueEnum)modelObj );
    else if( modelObj instanceof com.u2d.generated.VariableField )
      return validateVariableField( (com.u2d.generated.VariableField)modelObj );
    else if( modelObj instanceof com.u2d.generated.TypeAndUnitsEnum )
      return validateTypeAndUnitsEnum( (com.u2d.generated.TypeAndUnitsEnum)modelObj );
    else if( modelObj instanceof com.u2d.generated.BitField )
      return validateBitField( (com.u2d.generated.BitField)modelObj );
    else if( modelObj instanceof com.u2d.generated.SubField )
      return validateSubField( (com.u2d.generated.SubField)modelObj );
    else if( modelObj instanceof com.u2d.generated.FixedLengthString )
      return validateFixedLengthString( (com.u2d.generated.FixedLengthString)modelObj ); 
    else if( modelObj instanceof com.u2d.generated.VariableLengthString )
      return validateVariableLengthString( (com.u2d.generated.VariableLengthString)modelObj );
    else if( modelObj instanceof com.u2d.generated.VariableLengthField )
      return validateVariableLengthField( (com.u2d.generated.VariableLengthField)modelObj );
    else if( modelObj instanceof com.u2d.generated.VariableFormatField )
      return validateVariableFormatField( (com.u2d.generated.VariableFormatField)modelObj );
   else if( modelObj instanceof com.u2d.generated.FormatField )
      return validateFormatField( (com.u2d.generated.FormatField)modelObj );
    else if( modelObj instanceof com.u2d.generated.FormatEnum )
      return validateFormatEnum( (com.u2d.generated.FormatEnum)modelObj );
    else if( modelObj instanceof com.u2d.generated.Component )
      return validateComponent( (com.u2d.generated.Component)modelObj );
    else if( modelObj instanceof com.u2d.generated.InputSet )
      return validateInputSet( (com.u2d.generated.InputSet)modelObj );
    else if( modelObj instanceof com.u2d.generated.OutputSet )
      return validateOutputSet( (com.u2d.generated.OutputSet)modelObj );
    else if( modelObj instanceof com.u2d.generated.Constant )
      return validateConstant( (com.u2d.generated.Constant)modelObj );
    else if( modelObj instanceof com.u2d.generated.ConstantSet )
      return validateConstantSet( (com.u2d.generated.ConstantSet)modelObj );
    else 
      throw new RuntimeException("Unknown field. Unable to validate: "+modelObj.toString());
  }

  /** ServiceSet
  */
  private static int validateServiceSet( com.u2d.generated.ServiceSet serviceSet ) {

    try
    {
    	// adding the base services in to in the list if left off of user input
    	org.jts.validator.ServiceSet.tryToAddBaseServices( serviceSet );

    	org.jts.jsidl.binding.ServiceSet ss = 
    		org.jts.gui.jmatterToJAXB.ServiceSet.convert(serviceSet);

    	org.jts.validator.ServiceSet.validate( ss );
    }
    catch( ValidatorException ve ) 
    {
      serviceSet.fireValidationException( ve.getMessage() );
      return 1;
    }
    
    return 0;
  }
  
  /** ServiceDef
  */
  private static int validateServiceDef( com.u2d.generated.ServiceDef serviceDef ) {
    org.jts.jsidl.binding.ServiceDef sd = 
    	org.jts.gui.jmatterToJAXB.ServiceDef.convert(serviceDef);
    try {
    	org.jts.validator.ServiceDef.validate( sd );
    } catch( ValidatorException ve ) {
      serviceDef.fireValidationException( ve.getMessage() );
      return 1;
    }
    
    return 0;
  }
  
  /** ProtocolBehavior
  */
  private static int validateProtocolBehavior( com.u2d.generated.ProtocolBehavior protocolBehavior ) {
    org.jts.jsidl.binding.ProtocolBehavior ph = 
    	org.jts.gui.jmatterToJAXB.ProtocolBehavior.convert(protocolBehavior);
    
    try {
      // todo
    	//org.jts.validator.ProtocolBehavior.validate( ph );
    } catch( ValidatorException ve ) {
    	protocolBehavior.fireValidationException( ve.getMessage() );
      return 1;
    }
   // [tbd]: perform name checks for start element
    
    // isStateless [future tbd]: needs additional language syntax to describe data storage in order to validate this attribute against 
    // protocol behavior.
    
    // start [future tbd]: when graphics version of protocol behavior gui is made, the base state machines can be automatically 
    // imported when inheritance is specified. This operation itself can ensure valid namespaced naming of start states per state machine.
    // A validation check will still have to be made to ensure that all state machines have a start state specified. 
    
    // state_machine [future tbd]: Valid name correspondance between the start element and the state machine elements 
    // must be made. When graphics version of protocol behavior gui is made, the base state machines can be automatically 
    // imported when inheritance is specified. This operation itself can ensure valid name correspondence
    
    return 0;
  }
    
  /** MessageDef
  */
  private static int validateMessageDef( com.u2d.generated.MessageDef messageDef ) {
    org.jts.jsidl.binding.MessageDef md = 
    	org.jts.gui.jmatterToJAXB.MessageDef.convert(messageDef);
     
    try {
      org.jts.validator.MessageDef.validate(md);
      org.jts.validator.MessageDef.replaceIfDuplicate(messageDef);
    } catch( ValidatorException ve ) {
      messageDef.fireValidationException( ve.getMessage() );
      return 1;
    }
    
    return 0;
  }
  
  /** EventDef
  */
  private static int validateEventDef( com.u2d.generated.EventDef eventDef ) {
    org.jts.jsidl.binding.EventDef ed = 
    	org.jts.gui.jmatterToJAXB.EventDef.convert(eventDef);
    
    try {
        org.jts.validator.EventDef.validate(ed);

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.EventDef.getStored( ed, eventDef.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "EventDef" );
        	}
        }

    } catch( ValidatorException ve ) {
      eventDef.fireValidationException( ve.getMessage() );
      return 1;
    } 
    
    return 0;
  }
  
  /** Header
  */
  private static int validateHeader( com.u2d.generated.Header header ) {
    org.jts.jsidl.binding.Header  hh = 
    	org.jts.gui.jmatterToJAXB.Header.convert(header);
    
    try {
        org.jts.validator.Header.validate(hh);

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.Header.getStored( hh, header.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "Header" );
        	}
        }

    } catch( ValidatorException ve ) {
      header.fireValidationException( ve.getMessage() );
      return 1;
    } 
      
    return 0;
  }
  
  /** Body
  */
  private static int validateBody( com.u2d.generated.Body body ) {
    org.jts.jsidl.binding.Body  bb = 
    	org.jts.gui.jmatterToJAXB.Body.convert(body);
    
    try {
        org.jts.validator.Body.validate(bb);

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.Body.getStored( bb, body.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "Body" );
        	}
        }

    } catch( ValidatorException ve ) {
      body.fireValidationException( ve.getMessage() );
      return 1;
    }
    
    return 0;
  }
  
  /** Footer
  */
  private static int validateFooter( com.u2d.generated.Footer footer ) {
    org.jts.jsidl.binding.Footer ff = 
    	org.jts.gui.jmatterToJAXB.Footer.convert(footer);
    
     try {
         org.jts.validator.Footer.validate(ff);

         if( checkingDuplicates )
         {
         	if( org.jts.gui.JAXBtoJmatter.Footer.getStored( ff, footer.getID() ) != null )
         	{
         		DuplicateCheckPrompt.runDuplicateCheckPrompt( "Footer" );
         	}
         }

    } catch( ValidatorException ve ) {
      footer.fireValidationException( ve.getMessage() );
      return 1;
    }
    
    return 0;
  }
  
  /** Array
  */
  private static int validateArray( com.u2d.generated.Array array ) {
    org.jts.jsidl.binding.Array aa = 
    	org.jts.gui.jmatterToJAXB.Array.convert(array);
     
    try {
        org.jts.validator.Array.validate(aa);
        
        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.Array.getStored( aa, array.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "Array" );
        	}
        }
        
    } catch( ValidatorException ve ) {
      array.fireValidationException( ve.getMessage() );
      return 1;
    }
    
    return 0;
  }
  
  /** Dimension
  */
  private static int validateDimension( com.u2d.generated.Dimension dimension ) {
    org.jts.jsidl.binding.Dimension dd = 
    	org.jts.gui.jmatterToJAXB.Dimension.convert(dimension);
 
    try {
        org.jts.validator.Dimension.validate(dd);

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.Dimension.getStored( dd, dimension.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "Dimension" );
        	}
        }

    } catch( ValidatorException ve ) {
      dimension.fireValidationException( ve.getMessage() );
      return 1;
    }

    return 0;
  }
  
  /** Record
  */
  private static int validateRecord( com.u2d.generated.Record record ) {
    org.jts.jsidl.binding.Record rr = 
    	org.jts.gui.jmatterToJAXB.Record.convert(record);
     
    try {
        org.jts.validator.Record.validate(rr);

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.Record.getStored( rr, record.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "Record" );
        	}
        }

    } catch( ValidatorException ve ) {
      record.fireValidationException( ve.getMessage() );
      return 1;
    }
    
    return 0;
  }
  
  /** List
  */
  private static int validateList( com.u2d.generated.List list ) {
    org.jts.jsidl.binding.List ll = 
    	org.jts.gui.jmatterToJAXB.List.convert(list);
    
    try {
        org.jts.validator.List.validate(ll);
        org.jts.validator.CountField.validate(ll.getCountField());

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.List.getStored( ll, list.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "List" );
        	}
        }

    } catch( ValidatorException ve ) {
      list.fireValidationException( ve.getMessage() );
      return 1;
    }
    
    return 0;
  }
  
  /** Variant
  */
  private static int validateVariant( com.u2d.generated.Variant variant ) {
    org.jts.jsidl.binding.Variant vv = 
    	org.jts.gui.jmatterToJAXB.Variant.convert(variant);
    
    try {
        org.jts.validator.Variant.validate(vv);

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.Variant.getStored( vv, variant.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "Variant" );
        	}
        }

    } catch( ValidatorException ve ) {
      variant.fireValidationException( ve.getMessage() );
      return 1;
    }
    
    return 0;
  }
  
  /** Sequence
  */
  private static int validateSequence( com.u2d.generated.Sequence sequence ) {
    org.jts.jsidl.binding.Sequence ss = 
    	org.jts.gui.jmatterToJAXB.Sequence.convert(sequence);
    
    try {
        org.jts.validator.Sequence.validate(ss);

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.Sequence.getStored( ss, sequence.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "Sequence" );
        	}
        }

    } catch( ValidatorException ve ) {
      sequence.fireValidationException( ve.getMessage() );
      return 1;
    }
    
    return 0;
  }
  
  /** FixedField
  */
  private static int validateFixedField( com.u2d.generated.FixedField fixedField ) {
    org.jts.jsidl.binding.FixedField ff = 
    	org.jts.gui.jmatterToJAXB.FixedField.convert(fixedField);
    
    try {
        org.jts.validator.FixedField.validate(ff);
        if(ff.getScaleRange() != null)
        {
        	org.jts.validator.ScaleRange.validate(ff.getScaleRange());
        }
        

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.FixedField.getStored( ff, fixedField.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "FixedField" );
        	}
        }

        
    } catch( ValidatorException ve ) {
      fixedField.fireValidationException( ve.getMessage() );
      return 1;
    }

    return 0;
  }
  
  /** ValueSet
  */
  private static int validateValueSet( com.u2d.generated.ValueSet valueSet ) {
    org.jts.jsidl.binding.ValueSet vs = 
    	org.jts.gui.jmatterToJAXB.ValueSet.convert(valueSet);
                
      try {
          org.jts.validator.ValueSet.validate(vs);
      } catch( ValidatorException ve ) {
        valueSet.fireValidationException( ve.getMessage() );
        return 1;
      }
    
    return 0;
  }
  
  /** ValueRange
  */
  private static int validateValueRange( com.u2d.generated.ValueRange valueRange ) {
    org.jts.jsidl.binding.ValueRange vr = 
    	org.jts.gui.jmatterToJAXB.ValueRange.convert(valueRange);
    
    try {
        org.jts.validator.ValueRange.validate(vr);

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.ValueRange.getStored( vr, valueRange.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "ValueRange" );
        	}
        }

      } catch( ValidatorException ve ) {
        valueRange.fireValidationException( ve.getMessage() );
        return 1;
      }
    
    return 0;
  }
  
  /** ValueEnum
  */
  private static int validateValueEnum( com.u2d.generated.ValueEnum valueEnum ) {
    org.jts.jsidl.binding.ValueEnum ve = 
    	org.jts.gui.jmatterToJAXB.ValueEnum.convert(valueEnum);
   
    try {
        org.jts.validator.ValueEnum.validate(ve);

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.ValueEnum.getStored( ve, valueEnum.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "ValueEnum" );
        	}
        }

      } catch( ValidatorException vex ) {
        valueEnum.fireValidationException( vex.getMessage() );
        return 1;
      }
    
    return 0;
  }
  
  
  /** VariableField
  */
  private static int validateVariableField( com.u2d.generated.VariableField variableField ) {
    org.jts.jsidl.binding.VariableField vf = 
    	org.jts.gui.jmatterToJAXB.VariableField.convert(variableField);
     
    try {
        org.jts.validator.VariableField.validate(vf);

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.VariableField.getStored( vf, variableField.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "VariableField" );
        	}
        }

      } catch( ValidatorException vex ) {
        variableField.fireValidationException( vex.getMessage() );
        return 1;
      }
       
    return 0;
  }
  
  /** TypeAndUnitsEnum
  */
  private static int validateTypeAndUnitsEnum( com.u2d.generated.TypeAndUnitsEnum typeAndUnitsEnum ) {
    org.jts.jsidl.binding.TypeAndUnitsEnum tue = 
    	org.jts.gui.jmatterToJAXB.TypeAndUnitsEnum.convert(typeAndUnitsEnum);
    
    try {
        org.jts.validator.TypeAndUnitsEnum.validate(tue);
        if(tue.getScaleRange() != null)
        {
        	org.jts.validator.ScaleRange.validate(tue.getScaleRange());
        }
      } catch( ValidatorException vex ) {
        typeAndUnitsEnum.fireValidationException( vex.getMessage() );
        return 1;
      }
    
    if(DEBUG)
      JAXB.marshal( tue, System.out );  
    
    return 0;
  }
  
  /** BitField
  */
  private static int validateBitField( com.u2d.generated.BitField bitField ) {
    org.jts.jsidl.binding.BitField bf = 
    	org.jts.gui.jmatterToJAXB.BitField.convert(bitField);
    
    try {
        org.jts.validator.BitField.validate(bf);

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.BitField.getStored( bf, bitField.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "BitField" );
        	}
        }

      } catch( ValidatorException vex ) {
        bitField.fireValidationException( vex.getMessage() );
        return 1;
      }
    
     if(DEBUG)
      JAXB.marshal( bf, System.out );  
    
    return 0;
  }
  
  
  /** SubField
  */
  private static int validateSubField( com.u2d.generated.SubField subField ) {
    org.jts.jsidl.binding.SubField sf = 
    	org.jts.gui.jmatterToJAXB.SubField.convert(subField);
     
    try {
        org.jts.validator.SubField.validate(sf);
    	org.jts.validator.BitRange.validate(sf.getBitRange());
      } catch( ValidatorException vex ) {
        subField.fireValidationException( vex.getMessage() );
        return 1;
      }
      
    if(DEBUG)
      JAXB.marshal( sf, System.out );  
    
    return 0;
  }
  
  /** FixedLengthString
  */
  private static int validateFixedLengthString( com.u2d.generated.FixedLengthString fixedLengthString ) {
    org.jts.jsidl.binding.FixedLengthString fls = 
    	org.jts.gui.jmatterToJAXB.FixedLengthString.convert(fixedLengthString);
    
    try {
        org.jts.validator.FixedLengthString.validate(fls);

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.FixedLengthString.getStored( fls, fixedLengthString.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "FixedLengthString" );
        	}
        }

      } catch( ValidatorException vex ) {
        fixedLengthString.fireValidationException( vex.getMessage() );
        return 1;
      }
      
    if(DEBUG)
      JAXB.marshal( fls, System.out );  
    
    return 0;
  }
  
  /** VariableLengthString
  */
  private static int validateVariableLengthString( com.u2d.generated.VariableLengthString variableLengthString ) {
    org.jts.jsidl.binding.VariableLengthString vls = 
    	org.jts.gui.jmatterToJAXB.VariableLengthString.convert(variableLengthString);
    
    try {
        org.jts.validator.VariableLengthString.validate(vls);
        org.jts.validator.CountField.validate(vls.getCountField());

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.VariableLengthString.getStored( vls, variableLengthString.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "VariableLengthString" );
        	}
        }

      } catch( ValidatorException vex ) {
        variableLengthString.fireValidationException( vex.getMessage() );
        return 1;
      }
      
     if(DEBUG)
      JAXB.marshal( vls, System.out );  
    
    return 0;
  }
  
  /** VariableLengthField
  */
  private static int validateVariableLengthField( com.u2d.generated.VariableLengthField variableLengthField ) {
    org.jts.jsidl.binding.VariableLengthField vlf = 
    	org.jts.gui.jmatterToJAXB.VariableLengthField.convert(variableLengthField);
    
    try {
        org.jts.validator.VariableLengthField.validate(vlf);
        org.jts.validator.CountField.validate(vlf.getCountField());

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.VariableLengthField.getStored( vlf, variableLengthField.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "VariableLengthField" );
        	}
        }

      } catch( ValidatorException vex ) {
        variableLengthField.fireValidationException( vex.getMessage() );
        return 1;
      }
    
    if(DEBUG)
      JAXB.marshal( vlf, System.out );  
    
    return 0;
  }
  
  /** VariableFormatField
  */
  private static int validateVariableFormatField( com.u2d.generated.VariableFormatField variableFormatField ) {
    org.jts.jsidl.binding.VariableFormatField vff = 
    	org.jts.gui.jmatterToJAXB.VariableFormatField.convert(variableFormatField);
     
    try {
        org.jts.validator.VariableFormatField.validate(vff);
        org.jts.validator.CountField.validate(vff.getCountField());

        if( checkingDuplicates )
        {
        	if( org.jts.gui.JAXBtoJmatter.VariableFormatField.getStored( vff, variableFormatField.getID() ) != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "VariableFormatField" );
        	}
        }

      } catch( ValidatorException vex ) {
        variableFormatField.fireValidationException( vex.getMessage() );
        return 1;
      }
    
     if(DEBUG)
      JAXB.marshal( vff, System.out );  
    
    return 0;
  }
  
  /** FormatField
  */
  private static int validateFormatField( com.u2d.generated.FormatField formatField ) {
    
    // formatField needs to be removed from model
    
    return 0;
  }
  
  /** FormatEnum
  */
  private static int validateFormatEnum( com.u2d.generated.FormatEnum formatEnum ) {
    org.jts.jsidl.binding.FormatEnum fe = 
    	org.jts.gui.jmatterToJAXB.FormatEnum.convert(formatEnum);
     
    try {
        org.jts.validator.FormatEnum.validate(fe);
      } catch( ValidatorException vex ) {
        formatEnum.fireValidationException( vex.getMessage() );
        return 1;
      }
      
    if(DEBUG)
      JAXB.marshal( fe, System.out );  
    
    return 0;
  }
  
  /** Component
  */
  private static int validateComponent( com.u2d.generated.Component component ) 
  {

	// Validate the name
	try
	{
		org.jts.validator.Validator.validateName(component.getName().toString());
	}
	catch (ValidatorException vex)
	{
		component.fireValidationException(vex.getMessage());
		return 1;
	}

	// Component ID must be between 1 and 254, inclusive
	if ((component.getComponentId().intValue() < 1) ||
			(component.getComponentId().intValue() > 254))
	{
		component.fireValidationException("ID must be between 1 and 254...");
		return 1;
	}
		
    try {
    	// validate against database
    	com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) com.u2d.app.Context.getInstance().getPersistenceMechanism(); 
    	org.hibernate.Session hibernateSession = persistenceMechanism.getSession();
    		
    	// Form our Hibernate Query Language (hql) query into the hibernate database
    	// This will test if any Service Set in the db is equal to our jxServiceSet
    	String hql = "from Component cmp where cmp.name=:name and cmp.componentId=:id";
    	
    	org.hibernate.Query hibernateQuery = hibernateSession.createQuery(hql);
    	hibernateQuery.setParameter("name", component.getName().toString() );
    	hibernateQuery.setParameter("id", component.getComponentId() );

        if( checkingDuplicates )
        {
        	if( hibernateQuery.uniqueResult() != null )
        	{
        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "Component" );
        	}
        }

      } catch( ValidatorException vex ) {
    	  component.fireValidationException( vex.getMessage() );
        return 1;
      }
	
    List ssList =  component.getServiceSets().getItems();

    if( ssList == null || ssList.size() == 0 ) 
    {
      component.fireValidationException( "At least one service set is required..." );
      return 1;
    }
    	
	// A service definition cannot be used twice as a common parent
	// in the same component.  Store a list of all service defs 
	// used as a parent, and check it later.
	List<String> mParentList = new java.util.ArrayList();

	// loop through each service set...
    for(int kk=0; kk< ssList.size(); kk++) 
    {
		com.u2d.generated.ServiceSet serviceSet = (com.u2d.generated.ServiceSet)ssList.get(kk);
     
		if( serviceSet == null ) 
		{
			component.fireValidationException( "An empty service set found..." );
			return 1;
		}
      
		// check if all inherited service definitions are part of the service set
		List list = serviceSet.getServiceDefs().getItems();
		for(int ii=0; ii<list.size(); ii++) 
		{
			String str = validateInheritance( (com.u2d.generated.ServiceDef)list.get(ii), list, mParentList );
			if( str != null ) 
			{
				component.fireValidationException( str );
				return 1;
			}
		}    
    }
    
    // Check the parent list message to make sure two services don't have the same parent
	//Object [] mpl = mParentList.toArray();
	//java.util.Arrays.sort( mpl );
	//for(int ii=0; ii<mpl.length-1; ii++) 
	//{
	//	if( ((String)mpl[ii]).compareTo( (String)mpl[ii+1] ) == 0 ) 
	//	{
	//		component.fireValidationException( "multiple services not permited to inherit from: "+mpl[ii] );
	//		return 1;
	//	}
	//}    
                
    return 0;
  }
  
  private static String validateInheritance( com.u2d.generated.ServiceDef serviceDef, List list, List parentList ) 
  {
    com.u2d.generated.ServiceDef inheritsFrom = serviceDef.getInheritsFrom();
    if( inheritsFrom == null )
      return null;
    
    String serviceId = inheritsFrom.getServiceId().toString();
    String version = inheritsFrom.get_version().toString();
    parentList.add(serviceId + ":" + version);
    
    for(int ii=0; ii<list.size(); ii++) {
      com.u2d.generated.ServiceDef sd = ((com.u2d.generated.ServiceDef)list.get(ii));
      String sid = sd.getServiceId().toString();
	  String vv = sd.get_version().toString();
      
      if( serviceId.compareTo(sid) == 0 && version.compareTo(vv) == 0 ) 
        return null;
    }
    
    return new String("ServiceDef with service ID: "+ serviceId+ ", version: "+version+" must also be included in service set");
  }
  
  /** InputSet
  */
  private static int validateInputSet( com.u2d.generated.InputSet inputSet ) {
    return 0;
  }
  
  /** OutputSet
  */
  private static int validateOutputSet( com.u2d.generated.OutputSet outputSet ) {
    return 0;
  }
  
  /** Constant
  */
  private static int validateConstant( com.u2d.generated.Constant constant )
  {
	    org.jts.jsidl.binding.ConstDef cnst = 
	    	org.jts.gui.jmatterToJAXB.Constant.convert(constant);
	     
	    try
	    {
	    	org.jts.validator.ConstDef.validate(cnst);

	        if( checkingDuplicates )
	        {
	        	if( org.jts.gui.JAXBtoJmatter.Constant.getStored( cnst, constant.getID() ) != null )
	        	{
	        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "Constant" );
	        	}
	        }

	    } 
	    catch( ValidatorException vex ) 
	    {
	        constant.fireValidationException( vex.getMessage() );
	        return 1;
	    }
	    
	     if(DEBUG)
	      JAXB.marshal( cnst, System.out );  
	    
	    return 0;
  }
  
  /** ConstantSet
  */
  private static int validateConstantSet( com.u2d.generated.ConstantSet constantSet )
  {	    
	    org.jts.jsidl.binding.DeclaredConstSet cnstSet = 
	    	org.jts.gui.jmatterToJAXB.ConstantSet.convert(constantSet);
	     
	    try
	    {
	    	org.jts.validator.DeclaredConstSet.validate(cnstSet);

	        if( checkingDuplicates )
	        {
	        	if( org.jts.gui.JAXBtoJmatter.ConstantSet.getStored( cnstSet, constantSet.getID() ) != null )
	        	{
	        		DuplicateCheckPrompt.runDuplicateCheckPrompt( "ConstantSet" );
	        	}
	        }

	    } 
	    catch( ValidatorException vex ) 
	    {
	    	constantSet.fireValidationException( vex.getMessage() );
	        return 1;
	    }
	    
	     if(DEBUG)
	      JAXB.marshal( cnstSet, System.out );  
	    
	    return 0;
  }

	private static class DuplicateCheckPrompt
	{
		private static int userOption = -1;
		private static String currentName;
		
		public static void runDuplicateCheckPrompt( String name )
		{
			currentName = name;

			java.awt.EventQueue.invokeLater( new Runnable()
			{
				public void run()
				{
					int rval = javax.swing.JOptionPane.showConfirmDialog( null,
							"A " + currentName + " already exists in the database. \n Do you want to create a new instance of this object?",
							"JTS Confirm Dialog", javax.swing.JOptionPane.YES_NO_OPTION) ;
					setUserOption( rval );
				}
			});

			while( userOption == -1 ) 
			{
				try
				{
					Thread.sleep(500);
				}
				catch(InterruptedException ie) { }
			}
			if( userOption == 0 )
			{
				userOption = -1;
				return;
			}
			else if( userOption == 1 || userOption == 2 )  // no, cancel
			{
				userOption = -1;
				throw new ValidatorException("Item not saved");
			}
		}
		
		private static void setUserOption( int val )
		{
			userOption = val;
		}
	}
	
}
