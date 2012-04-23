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

package org.jts.gui.importJSIDL.declaredElementsResolution;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBContext;

import org.jts.gui.importJSIDL.ImportException;
import java.util.Map;
import java.util.Iterator;

public class DeclaredTypeSet 
{
	// This will populate the DeclaredTypeMap with declared_type_* elements and search the given file path for declared_type_ref elements
	// We will also handle declared constant set resolution because declared type sets can have declared constant sets referenced in their definition
	public static void importDeclaredTypeSet(org.jts.jsidl.binding.DeclaredTypeSet jxTypeSet, Map map) throws ImportException
	{
		DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();
		DeclaredConstantMap declaredConstantMap = DeclaredConstantMap.getInstance();
		
		// Add this TypeSet name to the package list
		declaredTypeMap.startPackage(jxTypeSet.getName());
		declaredConstantMap.startPackage(jxTypeSet.getName());
		
		// First we will do the recursion and resolve any declared_type_ref elements and declared_const_set_ref elements
		java.util.List<org.jts.jsidl.binding.DeclaredTypeSetRef> typeRefList = jxTypeSet.getDeclaredTypeSetRef();
		java.util.List<org.jts.jsidl.binding.DeclaredConstSetRef> constantRefList = jxTypeSet.getDeclaredConstSetRef();
				
		// Create a hashMap for DeclaredTypeSets from the search directory
		HashMap<String, org.jts.jsidl.binding.DeclaredTypeSet> declaredTypeSets = new HashMap<String, org.jts.jsidl.binding.DeclaredTypeSet>();
		HashMap<String, org.jts.jsidl.binding.DeclaredConstSet> declaredConstantSets = new HashMap<String, org.jts.jsidl.binding.DeclaredConstSet>();
		
	    

		for( Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); )
		{
			// Unmarshall the file using JAXB
			try
			{
				String key = iter.next();
				Object obj = map.get(key);

				// test for declared type set first
				if( obj instanceof org.jts.jsidl.binding.DeclaredTypeSet )
				{
					declaredTypeSets.put( key, (org.jts.jsidl.binding.DeclaredTypeSet) obj );
				}
				// also test for declared constant set so already have the file open
				else if (obj instanceof org.jts.jsidl.binding.DeclaredConstSet)
				{
					declaredConstantSets.put( key,  (org.jts.jsidl.binding.DeclaredConstSet) obj );
				}
			}
			catch( Exception ex ) 
			{
				ex.printStackTrace();
			}
		}
		
		// match all declared type set reference with the definition found in a file
		for(org.jts.jsidl.binding.DeclaredTypeSetRef reference:typeRefList)
		{
			// Generate the HashMap key string
			String key = reference.getId() + "-" + reference.getVersion();
			
			// Get object from HashMap
			org.jts.jsidl.binding.DeclaredTypeSet typeSet = declaredTypeSets.get(key);

			// Recursive Import
			if(typeSet != null)
			{
				typeSet.setName(reference.getName());
				DeclaredTypeSet.importDeclaredTypeSet(typeSet, map);
			}
			else
			{
				// Unfound reference!
				throw new ImportException("Declared Type Set resolution error. declared_type_set with name " + reference.getId() + "-v" + reference.getVersion() + " not found. Referenced by " + jxTypeSet.getName());
			}
		}
		
		// match all declared constant set reference with the definition found in a file
		for(org.jts.jsidl.binding.DeclaredConstSetRef reference:constantRefList)
		{
			// Generate the HashMap key string
			String key = reference.getId() + "-" + reference.getVersion();
			
			// Get object from HashMap
			org.jts.jsidl.binding.DeclaredConstSet constantSet = declaredConstantSets.get(key);

			// Recursive Import
			if(constantSet != null)
			{
				constantSet.setName(reference.getName());
				DeclaredConstantSet.resolveReferencedConstantSets(constantSet, map);
				
				for(org.jts.jsidl.binding.ConstDef constantDef:constantSet.getConstDef())
				{
					declaredConstantMap.addType(reference.getName() + "." + constantDef.getName(), constantDef);
				}
				
				declaredConstantMap.print();
			}
			else
			{
				// Unfound reference!
				throw new ImportException("Declared Constant Set resolution error. declared_const_set with name " + reference.getId() + "-v" + reference.getVersion() + " not found. Referenced by " + jxTypeSet.getName());
			}
		}

		// Import the declared types to the DeclaredTypeMap
		java.util.List<Object> declaredTypeList = jxTypeSet.getMessageDefOrHeaderOrBody();		
		
		if(declaredTypeList != null)
		{
			// Iterate on the possible objects
			for(Object obj:declaredTypeList)
			{
				mapElement(obj, declaredTypeMap, jxTypeSet);
			}
		}

		// Remove our package from the list
		declaredTypeMap.endPackage(jxTypeSet.getName());
		declaredConstantMap.endPackage(jxTypeSet.getName());
	}
	
	public static void importDeclaredTypeSetConstantSets(org.jts.jsidl.binding.DeclaredTypeSet jxTypeSet, Map map)
	{
		
	}
	
	public static void mapElement(Object obj, DeclaredTypeMap declaredTypeMap, org.jts.jsidl.binding.DeclaredTypeSet jxTypeSet)
	{
		/*
	     * Objects of the following type(s) are allowed in the list
	     * {@link Body }
	     * {@link org.jts.jsidl.binding.List }
	     * {@link VariableFormatField }
	     * {@link Record }
	     * {@link BitField }
	     * {@link Sequence }
	     * {@link VariableField }
	     * {@link Array }
	     * {@link Footer }
	     * {@link FixedLengthString }
	     * {@link FixedField }
	     * {@link Variant }
	     * {@link VariableLengthString }
	     * {@link MessageDef }
	     * {@link VariableLengthField }
	     * {@link Header }
	     * {@link DeclaredBitField }
	     * {@link DeclaredFixedLengthString }
	     * {@link DeclaredVariableField }
	     * {@link DeclaredBody }
	     * {@link DeclaredHeader }
	     * {@link DeclaredList }
	     * {@link DeclaredVariant }
	     * {@link DeclaredFixedField }
	     * {@link DeclaredSequence }
	     * {@link DeclaredRecord }
	     * {@link DeclaredEventDef }
	     * {@link DeclaredMessageDef }
	     * {@link DeclaredArray }
	     * {@link DeclaredFooter }
	     * {@link DeclaredVariableFormatField }
	     * {@link DeclaredVariableLengthField }
	     * {@link DeclaredVariableLengthString }
	     */	
		
		// Possible types
		if(obj instanceof org.jts.jsidl.binding.Body)
		{
			mapBody(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.Footer)
		{
			mapFooter(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.List)
		{
			mapList(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.VariableFormatField)
		{
			mapVariableFormatField(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.Record)
		{
			mapRecord(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.BitField)
		{
			mapBitField(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.Sequence)
		{
			mapSequence(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.VariableField)
		{
			mapVariableField(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.Array)
		{
			mapArray(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.FixedLengthString)
		{
			mapFixedLengthString(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.FixedField)
		{
			mapFixedField(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.Variant)
		{
			mapVariant(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.VariableLengthString)
		{
			mapVariableLengthString(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.MessageDef)
		{
			mapMessageDef(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.VariableLengthField)
		{
			mapVariableLengthField(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.Header)
		{
			mapHeader(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredBitField)
		{
			mapDeclaredBitField(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredFixedLengthString)
		{
			mapDeclaredFixedLengthString(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredVariableField)
		{
			mapDeclaredVariableField(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredBody)
		{
			mapDeclaredBody(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredHeader)
		{
			mapDeclaredHeader(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredList)
		{
			mapDeclaredList(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredVariant)
		{
			mapDeclaredVariant(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredFixedField)
		{
			mapDeclaredFixedField(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredSequence)
		{
			mapDeclaredSequence(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredRecord)
		{
			mapDeclaredRecord(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredArray)
		{
			mapDeclaredArray(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredEventDef)
		{
			mapDeclaredEventDef(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredMessageDef)
		{
			mapDeclaredMessageDef(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredFooter)
		{
			mapDeclaredFooter(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredVariableFormatField)
		{
			mapDeclaredVariableFormatField(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredVariableLengthField)
		{
			mapDeclaredVariableLengthField(obj, declaredTypeMap);
		}
		else if(obj instanceof org.jts.jsidl.binding.DeclaredVariableLengthString)
		{
			mapDeclaredVariableLengthString(obj, declaredTypeMap);
		}
		else
		{
			// Unknown type
			// Shouldn't happen
			throw new ImportException("Unknown JAXB object type while trying to resolve declared_type_set " + jxTypeSet.getName());
		}
	}
	
	public static void mapBody(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		org.jts.jsidl.binding.Body jxBody = (org.jts.jsidl.binding.Body) obj;
		Body.resolveDeclaredElements(jxBody);
		declaredTypeMap.addType(jxBody.getName(), jxBody);
	}
	
	public static void mapFooter(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		org.jts.jsidl.binding.Footer jxFooter = (org.jts.jsidl.binding.Footer) obj;
		Footer.resolveDeclaredElements(jxFooter);
		declaredTypeMap.addType(jxFooter.getName(), jxFooter);
	}
	
	public static void mapList(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		List.importDeclaredType((org.jts.jsidl.binding.List) obj);
	}
	
	public static void mapVariableFormatField(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// No possible declared types to resolve in VariableFormatField, so add this object to the map
		org.jts.jsidl.binding.VariableFormatField field = (org.jts.jsidl.binding.VariableFormatField) obj;
		declaredTypeMap.addType(field.getName(), field);
	}
	
	public static void mapRecord(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		org.jts.jsidl.binding.Record jxRecord = (org.jts.jsidl.binding.Record) obj;
		Record.resolveDeclaredElements(jxRecord);
		declaredTypeMap.addType(jxRecord.getName(), jxRecord);
	}
	
	public static void mapBitField(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// No possible declared types to resolve in BitField, so add this object to the map
		org.jts.jsidl.binding.BitField field = (org.jts.jsidl.binding.BitField) obj;
		declaredTypeMap.addType(field.getName(), field);
	}
	
	public static void mapSequence(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		Sequence.importDeclaredType((org.jts.jsidl.binding.Sequence) obj);
	}
	
	public static void mapVariableField(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// No possible declared types to resolve in VariableField, so add this object to the map
		org.jts.jsidl.binding.VariableField field = (org.jts.jsidl.binding.VariableField) obj;

		declaredTypeMap.addType(field.getName(), field);
	}
	
	public static void mapArray(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		Array.importDeclaredType((org.jts.jsidl.binding.Array) obj);
	}
	
	public static void mapFixedLengthString(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// No possible declared types to resolve in FixedLengthString, so add this object to the map
		org.jts.jsidl.binding.FixedLengthString field = (org.jts.jsidl.binding.FixedLengthString) obj;
		declaredTypeMap.addType(field.getName(), field);
	}
	
	public static void mapFixedField(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// No possible declared types to resolve in FixedField, so add this object to the map
		org.jts.jsidl.binding.FixedField fixedField = (org.jts.jsidl.binding.FixedField) obj;
		declaredTypeMap.addType(fixedField.getName(), fixedField);
	}
	
	public static void mapVariant(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		Variant.importDeclaredType((org.jts.jsidl.binding.Variant) obj);
	}
	
	public static void mapVariableLengthString(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// No possible declared types to resolve in VariableLengthString, so add this object to the map
		org.jts.jsidl.binding.VariableLengthString field = (org.jts.jsidl.binding.VariableLengthString) obj;
		declaredTypeMap.addType(field.getName(), field);
	}
	
	public static void mapMessageDef(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		org.jts.jsidl.binding.MessageDef jxMessageDef = (org.jts.jsidl.binding.MessageDef) obj;
		MessageDef.resolveDeclaredElements(jxMessageDef);
		declaredTypeMap.addType(jxMessageDef.getName(), jxMessageDef);
	}
	
	public static void mapVariableLengthField(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// No possible declared types to resolve in VariableLengthField, so add this object to the map
		org.jts.jsidl.binding.VariableLengthField field = (org.jts.jsidl.binding.VariableLengthField) obj;
		declaredTypeMap.addType(field.getName(), field);
	}
	
	public static void mapHeader(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		org.jts.jsidl.binding.Header jxHeader = (org.jts.jsidl.binding.Header) obj;
		Header.resolveDeclaredElements(jxHeader);
		declaredTypeMap.addType(jxHeader.getName(), jxHeader);
	}
	
	public static void mapDeclaredBitField(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared type into the actual object
		org.jts.jsidl.binding.DeclaredBitField declaredBitField = (org.jts.jsidl.binding.DeclaredBitField) obj;
		org.jts.jsidl.binding.BitField element = DeclaredTypeMap.lookupDeclaredBitField(declaredBitField);
		
		if(element == null)
		{
			// Error, not found in TypeMap
			throw new ImportException("Declared Bit Field \""+declaredBitField.getName()+"\" with type ref \""+declaredBitField.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredBitField.getName(), element);
		}
	}
	
	public static void mapDeclaredFixedLengthString(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared type into the actual object
		org.jts.jsidl.binding.DeclaredFixedLengthString declaredFixedLengthString = (org.jts.jsidl.binding.DeclaredFixedLengthString) obj;
		org.jts.jsidl.binding.FixedLengthString element = DeclaredTypeMap.lookupDeclaredFixedLengthString(declaredFixedLengthString);
		
		if(element == null)
		{
			// Error, not found in TypeMap
			throw new ImportException("Declared Fixed Length String \""+declaredFixedLengthString.getName()+"\"with type ref \""+declaredFixedLengthString.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredFixedLengthString.getName(), element);
		}
	}
	
	public static void mapDeclaredVariableField(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared type into the actual object
		org.jts.jsidl.binding.DeclaredVariableField declaredVariableField = (org.jts.jsidl.binding.DeclaredVariableField) obj;
		org.jts.jsidl.binding.VariableField element = DeclaredTypeMap.lookupDeclaredVariableField(declaredVariableField);
		
		if(element == null)
		{
			// Error, not found in TypeMap
			throw new ImportException("Declared Variable Field \""+declaredVariableField.getName()+"\"with type ref \""+declaredVariableField.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredVariableField.getName(), element);
		}
	}
	
	public static void mapDeclaredBody(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared type into the actual object
		org.jts.jsidl.binding.DeclaredBody declaredBody = (org.jts.jsidl.binding.DeclaredBody) obj;
		org.jts.jsidl.binding.Body element = DeclaredTypeMap.lookupDeclaredBody(declaredBody);
		
		if(element == null)
		{
			// Error, not found in TypeMap
			throw new ImportException("Declared Body \""+declaredBody.getName()+"\" with type ref \""+declaredBody.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredBody.getName(), element);
		}
	}
	
	public static void mapDeclaredHeader(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared type into the actual object
		org.jts.jsidl.binding.DeclaredHeader declaredHeader = (org.jts.jsidl.binding.DeclaredHeader) obj;
		org.jts.jsidl.binding.Header element = DeclaredTypeMap.lookupDeclaredHeader(declaredHeader);
		
		if(element == null)
		{
			// Error, not found in TypeMap
			throw new ImportException("Declared Header \""+declaredHeader.getName()+"\" with type ref \""+declaredHeader.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredHeader.getName(), element);
		}
	}
	
	public static void mapDeclaredList(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared_variant into the actual variant object
		org.jts.jsidl.binding.DeclaredList declaredList = (org.jts.jsidl.binding.DeclaredList) obj;
		org.jts.jsidl.binding.List element = DeclaredTypeMap.lookupDeclaredList(declaredList);
		
		if(element == null)
		{
			// Error, declared_variant not found in TypeMap
			throw new ImportException("Declared List \""+declaredList.getName()+"\" with type ref \""+declaredList.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredList.getName(), element);
		}
	}
	
	public static void mapDeclaredVariant(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared_variant into the actual variant object
		org.jts.jsidl.binding.DeclaredVariant declaredVariant = (org.jts.jsidl.binding.DeclaredVariant) obj;
		org.jts.jsidl.binding.Variant variant = DeclaredTypeMap.lookupDeclaredVariant(declaredVariant);
		
		if(variant == null)
		{
			// Error, declared_variant not found in TypeMap
			throw new ImportException("Declared Variant \""+declaredVariant.getName()+"\" with type ref \""+declaredVariant.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredVariant.getName(), variant);
		}
	}
	
	public static void mapDeclaredFixedField(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared type into the actual object
		org.jts.jsidl.binding.DeclaredFixedField declaredFixedField = (org.jts.jsidl.binding.DeclaredFixedField) obj;
		org.jts.jsidl.binding.FixedField element = DeclaredTypeMap.lookupDeclaredFixedField(declaredFixedField);
		
		if(element == null)
		{
			// Error, not found in TypeMap
			throw new ImportException("Declared Fixed Field \""+declaredFixedField.getName()+"\" with type ref \""+declaredFixedField.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredFixedField.getName(), element);
		}
	}
	
	public static void mapDeclaredSequence(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared type into the actual object
		org.jts.jsidl.binding.DeclaredSequence declaredSequence = (org.jts.jsidl.binding.DeclaredSequence) obj;
		org.jts.jsidl.binding.Sequence element = DeclaredTypeMap.lookupDeclaredSequence(declaredSequence);
		
		if(element == null)
		{
			// Error, not found in TypeMap
			throw new ImportException("Declared Sequence \""+declaredSequence.getName()+"\" with type ref \""+declaredSequence.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredSequence.getName(), element);
		}
	}
	
	public static void mapDeclaredRecord(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared type into the actual object
		org.jts.jsidl.binding.DeclaredRecord declaredRecord = (org.jts.jsidl.binding.DeclaredRecord) obj;
		org.jts.jsidl.binding.Record element = DeclaredTypeMap.lookupDeclaredRecord(declaredRecord);
		
		if(element == null)
		{
			// Error, not found in TypeMap
			throw new ImportException("Declared Record \""+declaredRecord.getName()+"\" with type ref \""+declaredRecord.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredRecord.getName(), element);
		}
	}
	
	public static void mapDeclaredArray(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared type into the actual object
		org.jts.jsidl.binding.DeclaredArray declaredArray = (org.jts.jsidl.binding.DeclaredArray) obj;
		org.jts.jsidl.binding.Array element = DeclaredTypeMap.lookupDeclaredArray(declaredArray);
		
		if(element == null)
		{
			// Error, not found in TypeMap
			throw new ImportException("Declared Array \""+declaredArray.getName()+"\" with type ref \""+declaredArray.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredArray.getName(), element);
		}
	}
	
	public static void mapDeclaredEventDef(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared type into the actual object
		org.jts.jsidl.binding.DeclaredEventDef declaredEventDef = (org.jts.jsidl.binding.DeclaredEventDef) obj;
		org.jts.jsidl.binding.EventDef element = DeclaredTypeMap.lookupDeclaredEventDef(declaredEventDef);
		
		if(element == null)
		{
			// Error, not found in TypeMap
			throw new ImportException("Declared Event Def \""+declaredEventDef.getName()+"\" with type ref \""+declaredEventDef.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredEventDef.getName(), element);
		}
	}
	
	public static void mapDeclaredMessageDef(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared type into the actual object
		org.jts.jsidl.binding.DeclaredMessageDef declaredMessageDef = (org.jts.jsidl.binding.DeclaredMessageDef) obj;
		org.jts.jsidl.binding.MessageDef element = DeclaredTypeMap.lookupDeclaredMessageDef(declaredMessageDef);
		
		if(element == null)
		{
			// Error, not found in TypeMap
			throw new ImportException("Declared Message Def \""+declaredMessageDef.getName()+"\" with type ref \""+declaredMessageDef.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredMessageDef.getName(), element);
		}
	}
	
	public static void mapDeclaredFooter(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared type into the actual object
		org.jts.jsidl.binding.DeclaredFooter declaredFooter = (org.jts.jsidl.binding.DeclaredFooter) obj;
		org.jts.jsidl.binding.Footer element = DeclaredTypeMap.lookupDeclaredFooter(declaredFooter);
		
		if(element == null)
		{
			// Error, not found in TypeMap
			throw new ImportException("Declared Footer \""+declaredFooter.getName()+"\" with type ref \""+declaredFooter.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredFooter.getName(), element);
		}
	}
	
	public static void mapDeclaredVariableFormatField(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared type into the actual object
		org.jts.jsidl.binding.DeclaredVariableFormatField declaredVariableFormatField = (org.jts.jsidl.binding.DeclaredVariableFormatField) obj;
		org.jts.jsidl.binding.VariableFormatField element = DeclaredTypeMap.lookupDeclaredVariableFormatField(declaredVariableFormatField);
		
		if(element == null)
		{
			// Error, not found in TypeMap
			throw new ImportException("Declared Variable Format Field \""+declaredVariableFormatField.getName()+"\" with type ref \""+declaredVariableFormatField.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredVariableFormatField.getName(), element);
		}
	}
	
	public static void mapDeclaredVariableLengthField(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared type into the actual object
		org.jts.jsidl.binding.DeclaredVariableLengthField declaredVariableLengthField = (org.jts.jsidl.binding.DeclaredVariableLengthField) obj;
		org.jts.jsidl.binding.VariableLengthField element = DeclaredTypeMap.lookupDeclaredVariableLengthField(declaredVariableLengthField);
		
		if(element == null)
		{
			// Error, not found in TypeMap
			throw new ImportException("Declared Variable Length Field \""+declaredVariableLengthField.getName()+"\" with type ref \""+declaredVariableLengthField.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredVariableLengthField.getName(), element);
		}
	}
	
	public static void mapDeclaredVariableLengthString(Object obj, DeclaredTypeMap declaredTypeMap)
	{
		// Resolve the declared type into the actual object
		org.jts.jsidl.binding.DeclaredVariableLengthString declaredVariableLengthString = (org.jts.jsidl.binding.DeclaredVariableLengthString) obj;
		org.jts.jsidl.binding.VariableLengthString element = DeclaredTypeMap.lookupDeclaredVariableLengthString(declaredVariableLengthString);
		
		if(element == null)
		{
			// Error, not found in TypeMap
			throw new ImportException("Declared Variable Length String \""+declaredVariableLengthString.getName()+"\" with type ref \""+declaredVariableLengthString.getDeclaredTypeRef()+"\" not found.");
		}
		else
		{
			// Add to the map with the declared name
			declaredTypeMap.addType(declaredVariableLengthString.getName(), element);
		}
	}
	
	/** gets a list of XML files in the specified path */
	private static java.util.List getFileList( com.u2d.type.atom.FileEO  path ) 
	{
		if( path.fileValue().isFile() ) 
	    {
			path = new com.u2d.type.atom.FileEO(path.fileValue().getParent());
	    }

		return path.listRecursive( 
				new FileFilter() 
				{
					public boolean accept(File ff) 
					{
						String name = ff.getName();
						if( name.endsWith(".xml") ) 
							return true;
						else  
							return false;
					}
				});
	}

	private static Unmarshaller createUnmarshaller() 
	{
		Unmarshaller um = null;
			     
		try 
		{
			JAXBContext jc = JAXBContext.newInstance( "org.jts.jsidl.binding" );
			um = jc.createUnmarshaller();
		} 
		catch( Exception ex ) 
		{
			ex.printStackTrace();
			return null;
		}
		return um;
		}	  
	}
