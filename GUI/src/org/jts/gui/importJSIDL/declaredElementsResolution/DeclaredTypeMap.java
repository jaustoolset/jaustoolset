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

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;

public class DeclaredTypeMap {

    private static DeclaredTypeMap instance = new DeclaredTypeMap();
    private HashMap<String, Object> typesMap;
    private HashMap<String, Object> currentMap;
    private ArrayDeque<HashMap<String, Object>> previousMap;

    public static DeclaredTypeMap getInstance() {
        return instance;
    }

    /**
     * Clear out the DeclaredTypeMap, in case of problems resolving declared types in input JSIDL.
     * Subsequent import operations will be forced to reconstruct the DeclaredTypeMap data structure.
     */
    public void clear() {
        previousMap.clear();
        typesMap.clear();
        currentMap = typesMap;
    }

    public boolean addType(String name, Object obj) {
        if (name == null || obj == null) {
            return false;
        }

        currentMap.put(name, obj);
        return true;
    }

    public boolean setPackage(String name) {
        if (name == null && currentMap != typesMap) {
            currentMap = previousMap.pop();
            return true;
        } else {
            HashMap<String, Object> tempMap = findPackage(typesMap, name);
            if (tempMap != null) {
                previousMap.push(currentMap);
                currentMap = tempMap;
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean isEmpty() {
        return typesMap.isEmpty();
    }

    @SuppressWarnings("unchecked")
    public Object getType(String name) {
        if (name == null) {
            return null;
        }

        // Parse the name string for the first package name
        HashMap<String, Object> map = currentMap;

        String[] names = name.split("\\.");

        // Find the starting package for this element (if possible)
        for (int i = 0; i < names.length - 1; i++) {
            map = findPackage(map, names[i]);

            if (map == null) {
                // ERROR: Package not found
                return null;
            }
        }

        return map.get(names[names.length - 1]);
    }

    public void startPackage(String name) {
        // map
        HashMap<String, Object> map = new HashMap<String, Object>();
        currentMap.put(name, map);

        previousMap.push(currentMap);
        currentMap = map;
    }

    public boolean endPackage(String name) {
        if (previousMap.isEmpty() || previousMap.peek().get(name) == null) {
            return false;
        }

        currentMap = previousMap.pop();
        return true;
    }

    private DeclaredTypeMap() {
        typesMap = new HashMap<String, Object>();
        currentMap = typesMap;
        previousMap = new ArrayDeque<HashMap<String, Object>>();
    }

    private static HashMap<String, Object> findPackage(HashMap<String, Object> map, String name) {
        // Input null, output null
        if (map == null) {
            return map;
        }

        // Found our sub-reference
        if (map.get(name) != null) {
            return (HashMap<String, Object>) map.get(name);
        }

        // Hashmap Iterator
        Iterator itr = map.keySet().iterator();

        //iterate through HashMap values iterator
        while (itr.hasNext()) {
            Object obj = itr.next();

            if (map.get(obj) instanceof HashMap) {
                // This element is another map, recurse through that looking for our package
                HashMap<String, Object> ret = findPackage((HashMap<String, Object>) map.get(obj), name);
                if (ret != null) {
                    // Found it
                    return ret;
                }
            }
        }

        // Package not found, no more sub-references
        return null;
    }

    private static void fixOverrides( Object declaredElement, Object fixedElement )
    {
    	// the name, optional, and interpretation attributes of the declared elements 
    	// override the referenced definitions
    	
    	// name
    	fixNameOverride( declaredElement, fixedElement );
    	
    	// is optional
    	fixOptionalOverride( declaredElement, fixedElement );
    	
    	// interpretation
    	fixInterpretationOverride( declaredElement, fixedElement );
    }
    
    private static void fixNameOverride( Object declaredElement, Object fixedElement )
    {
    	Class<?> declaredClass = null;
    	Class<?> fixedClass = null;
    	
    	declaredClass = declaredElement.getClass();
    	fixedClass = fixedElement.getClass();
    	
    	try
    	{
    		Method getMethod = declaredClass.getMethod( "getName" );
    		Method setMethod = fixedClass.getMethod( "setName", String.class );
    		
    		String name = (String)getMethod.invoke( declaredElement );
    		setMethod.invoke( fixedElement, new Object[] {name} );    		
		}
    	catch( Exception e )
    	{
    		// this method doesn't need to do anything when the methods are not supported
    	}
    }

    private static void fixOptionalOverride( Object declaredElement, Object fixedElement )
    {
    	Class<?> declaredClass = null;
    	Class<?> fixedClass = null;
    	
    	declaredClass = declaredElement.getClass();
    	fixedClass = fixedElement.getClass();
    	
    	try
    	{
    		Method isOptionalMethod = declaredClass.getMethod( "isOptional" );
    		Method setOptionalMethod = fixedClass.getMethod( "setOptional", Boolean.class );
    		
    		Boolean optional = (Boolean)isOptionalMethod.invoke( declaredElement );
    		setOptionalMethod.invoke( fixedElement, new Object[] {optional} );    		
		}
    	catch( Exception e )
    	{
    		// this method doesn't need to do anything when the methods are not supported
    	}
    }

    private static void fixInterpretationOverride( Object declaredElement, Object fixedElement )
    {
    	Class<?> declaredClass = null;
    	Class<?> fixedClass = null;
    	
    	declaredClass = declaredElement.getClass();
    	fixedClass = fixedElement.getClass();
    	
    	try
    	{
    		Method getMethod = declaredClass.getMethod( "getInterpretation" );
    		Method setMethod = fixedClass.getMethod( "setInterpretation", String.class );
    		
    		String interpretation = (String)getMethod.invoke( declaredElement );
    		setMethod.invoke( fixedElement, new Object[] {interpretation} );    		
		}
    	catch( Exception e )
    	{
    		// this method doesn't need to do anything when the methods are not supported
    	}
    }
    
    public static org.jts.jsidl.binding.Variant lookupDeclaredVariant(org.jts.jsidl.binding.DeclaredVariant declaredVariant) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredVariant.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.Variant)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.Variant element = (org.jts.jsidl.binding.Variant) obj;

            // Note: A declaredVariant has a parameter for "optional" which overrides the value in the originally defined entity
            // alias name also overrides name
            org.jts.jsidl.binding.Variant element_copy = new org.jts.jsidl.binding.Variant();

            element_copy.setName(declaredVariant.getName());
            
			if (declaredVariant.isOptional() != null) {
                element_copy.setOptional(declaredVariant.isOptional());
            } else {
                element_copy.setOptional(element.isOptional());
            }

            if (declaredVariant.getInterpretation() != null) {
                element_copy.setInterpretation(declaredVariant.getInterpretation());
            } else {
                element_copy.setInterpretation(element.getInterpretation());
            }
            element_copy.setVtagField(element.getVtagField());
            java.util.List list = element.getRecordOrDeclaredRecordOrList();
            if (list != null) {
                java.util.List list_copy = element_copy.getRecordOrDeclaredRecordOrList();
                for (int ii = 0; ii < list.size(); ii++) {
                    list_copy.add(list.get(ii));
                }
            }

            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.Record lookupDeclaredRecord(org.jts.jsidl.binding.DeclaredRecord declaredRecord) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredRecord.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.Record)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.Record element = (org.jts.jsidl.binding.Record) obj;

            // Note: A declaredRecord has a parameter for "optional" which overrides the value in the originally defined entity
            // alias name also overrides name
            org.jts.jsidl.binding.Record element_copy = new org.jts.jsidl.binding.Record();

            element_copy.setName(declaredRecord.getName());
            element_copy.setOptional(declaredRecord.isOptional());
            if (declaredRecord.getInterpretation() != null) {
                element_copy.setInterpretation(declaredRecord.getInterpretation());
            } else {
                element_copy.setInterpretation(element.getInterpretation());
            }
            element_copy.setPresenceVector(element.getPresenceVector());

            java.util.List list = element.getArrayOrFixedFieldOrVariableField();
            if (list != null) {
                java.util.List list_copy = element_copy.getArrayOrFixedFieldOrVariableField();
                for (int ii = 0; ii < list.size(); ii++) {
                    list_copy.add(list.get(ii));
                }
            }

            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.Array lookupDeclaredArray(org.jts.jsidl.binding.DeclaredArray declaredArray) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredArray.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.Array)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.Array element = (org.jts.jsidl.binding.Array) obj;

            org.jts.jsidl.binding.Array element_copy = new org.jts.jsidl.binding.Array();

            element_copy.setName(declaredArray.getName());
            element_copy.setOptional(declaredArray.isOptional());
            if (declaredArray.getInterpretation() != null) {
                element_copy.setInterpretation(declaredArray.getInterpretation());
            } else {
                element_copy.setInterpretation(element.getInterpretation());
            }
            if (element.getFixedField() != null) {
                element_copy.setFixedField(element.getFixedField());
            } else if (element.getVariableField() != null) {
                element_copy.setVariableField(element.getVariableField());
            } else if (element.getBitField() != null) {
                element_copy.setBitField(element.getBitField());
            } else if (element.getFixedLengthString() != null) {
                element_copy.setFixedLengthString(element.getFixedLengthString());
            } else if (element.getVariableLengthString() != null) {
                element_copy.setVariableLengthString(element.getVariableLengthString());
            } else if (element.getVariableLengthField() != null) {
                element_copy.setVariableLengthField(element.getVariableLengthField());
            } else if (element.getVariableFormatField() != null) {
                element_copy.setVariableFormatField(element.getVariableFormatField());
            }

            // Also copy dimension
            for (org.jts.jsidl.binding.Dimension src : element.getDimension()) {
                element_copy.getDimension().add(src);
            }

            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.BitField lookupDeclaredBitField(org.jts.jsidl.binding.DeclaredBitField declaredBitField) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredBitField.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.BitField)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.BitField element = (org.jts.jsidl.binding.BitField) obj;

            // Note: A declaredBitField has a parameter for "optional" which overrides the value in the originally defined entity
            // declared alias overrides name also
            org.jts.jsidl.binding.BitField element_copy = new org.jts.jsidl.binding.BitField();
            element_copy.setName(declaredBitField.getName());
            element_copy.setOptional(declaredBitField.isOptional());
            if (declaredBitField.getInterpretation() != null) {
                element_copy.setInterpretation(declaredBitField.getInterpretation());
            } else {
                element_copy.setInterpretation(element.getInterpretation());
            }
            element_copy.setFieldTypeUnsigned(element.getFieldTypeUnsigned());
            java.util.List<org.jts.jsidl.binding.SubField> list = element.getSubField();
            if (list != null) {
                java.util.List<org.jts.jsidl.binding.SubField> list_copy = element_copy.getSubField();
                for (int ii = 0; ii < list.size(); ii++) {
                    list_copy.add(list.get(ii));
                }
            }

            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.Body lookupDeclaredBody(org.jts.jsidl.binding.DeclaredBody declaredBody) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredBody.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.Body)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.Body element = (org.jts.jsidl.binding.Body) obj;

            org.jts.jsidl.binding.Body element_copy = new org.jts.jsidl.binding.Body();

            element_copy.setName(declaredBody.getName());
            if (declaredBody.getInterpretation() != null) {
                element_copy.setInterpretation(declaredBody.getInterpretation());
            } else {
                element_copy.setInterpretation(element.getInterpretation());
            }
            if (element.getRecord() != null) {
                element_copy.setRecord(element.getRecord());
            } else if (element.getList() != null) {
                element_copy.setList(element.getList());
            } else if (element.getVariant() != null) {
                element_copy.setVariant(element.getVariant());
            } else if (element.getSequence() != null) {
                element_copy.setSequence(element.getSequence());
            }

            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.EventDef lookupDeclaredEventDef(org.jts.jsidl.binding.DeclaredEventDef declaredEventDef) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredEventDef.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.EventDef)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.EventDef element = (org.jts.jsidl.binding.EventDef) obj;

            org.jts.jsidl.binding.EventDef element_copy = new org.jts.jsidl.binding.EventDef();

            element_copy.setName(declaredEventDef.getName());
            element_copy.setDescription(element.getDescription());
            element_copy.setHeader(element.getHeader());
            element_copy.setBody(element.getBody());
            element_copy.setFooter(element.getFooter());
            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.FixedField lookupDeclaredFixedField(org.jts.jsidl.binding.DeclaredFixedField declaredFixedField) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredFixedField.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.FixedField)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.FixedField element = (org.jts.jsidl.binding.FixedField) obj;

            // Note: A declaredFixedField has a parameter for "optional" which overrides the value in the originally defined entity
            org.jts.jsidl.binding.FixedField element_copy = new org.jts.jsidl.binding.FixedField();

            element_copy.setName(declaredFixedField.getName());
            element_copy.setOptional(declaredFixedField.isOptional());
            if (declaredFixedField.getInterpretation() != null) {
                element_copy.setInterpretation(declaredFixedField.getInterpretation());
            } else {
                element_copy.setInterpretation(element.getInterpretation());
            }
            element_copy.setScaleRange(element.getScaleRange());
            element_copy.setValueSet(element.getValueSet());
            element_copy.setFieldType(element.getFieldType());
            element_copy.setFieldUnits(element.getFieldUnits());

            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.FixedLengthString lookupDeclaredFixedLengthString(org.jts.jsidl.binding.DeclaredFixedLengthString declaredFixedLengthString) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredFixedLengthString.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.FixedLengthString)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.FixedLengthString element = (org.jts.jsidl.binding.FixedLengthString) obj;

            // Note: A declaredFixedLengthString has a parameter for "optional" which overrides the value in the originally defined entity
            // declared alias is also overridden
            org.jts.jsidl.binding.FixedLengthString element_copy = new org.jts.jsidl.binding.FixedLengthString();

            element_copy.setName(declaredFixedLengthString.getName());
            element_copy.setOptional(declaredFixedLengthString.isOptional());
            if (declaredFixedLengthString.getInterpretation() != null) {
                element_copy.setInterpretation(declaredFixedLengthString.getInterpretation());
            } else {
                element_copy.setInterpretation(element.getInterpretation());
            }
            element_copy.setStringLength(element.getStringLength());

            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.Footer lookupDeclaredFooter(org.jts.jsidl.binding.DeclaredFooter declaredFooter) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredFooter.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.Footer)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.Footer element = (org.jts.jsidl.binding.Footer) obj;

            org.jts.jsidl.binding.Footer element_copy = new org.jts.jsidl.binding.Footer();

            element_copy.setName(declaredFooter.getName());
            if (declaredFooter.getInterpretation() != null) {
                element_copy.setInterpretation(declaredFooter.getInterpretation());
            } else {
                element_copy.setInterpretation(element.getInterpretation());
            }
            if (element.getRecord() != null) {
                element_copy.setRecord(element.getRecord());
            } else if (element.getList() != null) {
                element_copy.setList(element.getList());
            } else if (element.getVariant() != null) {
                element_copy.setVariant(element.getVariant());
            } else if (element.getSequence() != null) {
                element_copy.setSequence(element.getSequence());
            }

            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.Header lookupDeclaredHeader(org.jts.jsidl.binding.DeclaredHeader declaredHeader) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredHeader.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.Header)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.Header element = (org.jts.jsidl.binding.Header) obj;

            org.jts.jsidl.binding.Header element_copy = new org.jts.jsidl.binding.Header();

            element_copy.setName(declaredHeader.getName());
            if (declaredHeader.getInterpretation() != null) {
                element_copy.setInterpretation(declaredHeader.getInterpretation());
            } else {
                element_copy.setInterpretation(element.getInterpretation());
            }
            if (element.getRecord() != null) {
                element_copy.setRecord(element.getRecord());
            } else if (element.getList() != null) {
                element_copy.setList(element.getList());
            } else if (element.getVariant() != null) {
                element_copy.setVariant(element.getVariant());
            } else if (element.getSequence() != null) {
                element_copy.setSequence(element.getSequence());
            }

            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.List lookupDeclaredList(org.jts.jsidl.binding.DeclaredList declaredList) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredList.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.List)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.List element = (org.jts.jsidl.binding.List) obj;

            // Note: A declaredList has a parameter for "optional" which overrides the value in the originally defined entity
            // alias name also overrides name
            org.jts.jsidl.binding.List element_copy = new org.jts.jsidl.binding.List();

            element_copy.setName(declaredList.getName());
            element_copy.setOptional(declaredList.isOptional());
            if (declaredList.getInterpretation() != null) {
                element_copy.setInterpretation(declaredList.getInterpretation());
            } else {
                element_copy.setInterpretation(element.getInterpretation());
            }
            element_copy.setCountField(element.getCountField());
            if (element.getRecord() != null) {
                element_copy.setRecord(element.getRecord());
            } else if (element.getList() != null) {
                element_copy.setList(element.getList());
            } else if (element.getVariant() != null) {
                element_copy.setVariant(element.getVariant());
            } else if (element.getSequence() != null) {
                element_copy.setSequence(element.getSequence());
            }

            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.MessageDef lookupDeclaredMessageDef(org.jts.jsidl.binding.DeclaredMessageDef declaredMessageDef) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredMessageDef.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.MessageDef)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
        	// we don't have a binding copy method, so copy each part of the element individually
        	// we can do this with subelements because the same copy procedure will be called on them
        	// when references are resolved(so we won't have to do a recursive copy)

        	org.jts.jsidl.binding.MessageDef element = (org.jts.jsidl.binding.MessageDef) obj;
        	org.jts.jsidl.binding.MessageDef element_copy = new org.jts.jsidl.binding.MessageDef();

        	element_copy.setBody( element.getBody() );
        	element_copy.setDeclaredBody( element.getDeclaredBody() );
        	element_copy.setDeclaredFooter( element.getDeclaredFooter() );
        	element_copy.setDeclaredHeader( element.getDeclaredHeader() );
        	element_copy.setDescription( element.getDescription() );
        	element_copy.setFooter( element.getFooter() );
        	element_copy.setHeader( element.getHeader() );
        	element_copy.setIsCommand( element.isIsCommand() );
        	element_copy.setMessageId( element.getMessageId() );

        	fixOverrides(declaredMessageDef, element_copy);
            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.Sequence lookupDeclaredSequence(org.jts.jsidl.binding.DeclaredSequence declaredSequence) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredSequence.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.Sequence)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.Sequence element = (org.jts.jsidl.binding.Sequence) obj;

            // Note: A declaredSequence has a parameter for "optional" which overrides the value in the originally defined entity
            // alias name also overrides name
            org.jts.jsidl.binding.Sequence element_copy = new org.jts.jsidl.binding.Sequence();

            element_copy.setName(declaredSequence.getName());
            element_copy.setOptional(declaredSequence.isOptional());
            if (declaredSequence.getInterpretation() != null) {
                element_copy.setInterpretation(declaredSequence.getInterpretation());
            } else {
                element_copy.setInterpretation(element.getInterpretation());
            }
            element_copy.setPresenceVector(element.getPresenceVector());

            java.util.List list = element.getRecordOrDeclaredRecordOrList();
            if (list != null) {
                java.util.List list_copy = element_copy.getRecordOrDeclaredRecordOrList();
                for (int ii = 0; ii < list.size(); ii++) {
                    list_copy.add(list.get(ii));
                }
            }

            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.VariableField lookupDeclaredVariableField(org.jts.jsidl.binding.DeclaredVariableField declaredVariableField) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredVariableField.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.VariableField)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.VariableField element = (org.jts.jsidl.binding.VariableField) obj;

            // Note: A declaredVariableField has a parameter for "optional" which overrides the value in the originally defined entity
            // declared alias overrides name also
            org.jts.jsidl.binding.VariableField element_copy = new org.jts.jsidl.binding.VariableField();
            element_copy.setName(declaredVariableField.getName());
            element_copy.setOptional(declaredVariableField.isOptional());
            if (declaredVariableField.getInterpretation() != null) {
                element_copy.setInterpretation(declaredVariableField.getInterpretation());
            } else {
                element_copy.setInterpretation(element.getInterpretation());
            }
            element_copy.setTypeAndUnitsField(element.getTypeAndUnitsField());

            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.VariableFormatField lookupDeclaredVariableFormatField(org.jts.jsidl.binding.DeclaredVariableFormatField declaredVariableFormatField) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredVariableFormatField.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.VariableFormatField)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.VariableFormatField element = (org.jts.jsidl.binding.VariableFormatField) obj;

            // Note: A declaredVariableFormatField has a parameter for "optional" which overrides the value in the originally defined entity
            // declared alias is also overridden
            org.jts.jsidl.binding.VariableFormatField element_copy = new org.jts.jsidl.binding.VariableFormatField();

            element_copy.setName(declaredVariableFormatField.getName());
            element_copy.setOptional(declaredVariableFormatField.isOptional());
            if (declaredVariableFormatField.getInterpretation() != null) {
                element_copy.setInterpretation(declaredVariableFormatField.getInterpretation());
            } else {
                element_copy.setInterpretation(element.getInterpretation());
            }
            element_copy.setFormatField(element.getFormatField());
            element_copy.setCountField(element.getCountField());

            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.VariableLengthField lookupDeclaredVariableLengthField(org.jts.jsidl.binding.DeclaredVariableLengthField declaredVariableLengthField) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredVariableLengthField.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.VariableLengthField)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.VariableLengthField element = (org.jts.jsidl.binding.VariableLengthField) obj;

            // Note: A declaredVariableLengthField has a parameter for "optional" which overrides the value in the originally defined entity
            // declared alias is also overridden
            org.jts.jsidl.binding.VariableLengthField element_copy = new org.jts.jsidl.binding.VariableLengthField();

            element_copy.setName(declaredVariableLengthField.getName());
            element_copy.setOptional(declaredVariableLengthField.isOptional());
            if (declaredVariableLengthField.getInterpretation() != null) {
                element_copy.setInterpretation(declaredVariableLengthField.getInterpretation());
            } else {
                element_copy.setInterpretation(element.getInterpretation());
            }
            element_copy.setFieldFormat(element.getFieldFormat());
            element_copy.setCountField(element.getCountField());

            return element_copy;
        }
    }

    public static org.jts.jsidl.binding.VariableLengthString lookupDeclaredVariableLengthString(org.jts.jsidl.binding.DeclaredVariableLengthString declaredVariableLengthString) {
        DeclaredTypeMap declaredTypeMap = DeclaredTypeMap.getInstance();

        Object obj = declaredTypeMap.getType(declaredVariableLengthString.getDeclaredTypeRef());

        if (obj == null) {
            // Error, declared type ref not found in TypeMap
            return null;
        } else if (!(obj instanceof org.jts.jsidl.binding.VariableLengthString)) {
            // Error, declared type ref found but wrong object type
            return null;
        } else {
            org.jts.jsidl.binding.VariableLengthString element = (org.jts.jsidl.binding.VariableLengthString) obj;

            // Note: A declaredVariableLengthString has a parameter for "optional" which overrides the value in the originally defined entity
            // declared alias is also overridden
            org.jts.jsidl.binding.VariableLengthString element_copy = new org.jts.jsidl.binding.VariableLengthString();

            element_copy.setName(declaredVariableLengthString.getName());
            element_copy.setOptional(declaredVariableLengthString.isOptional());
            if (declaredVariableLengthString.getInterpretation() != null) {
                element_copy.setInterpretation(declaredVariableLengthString.getInterpretation());
            } else {
                element_copy.setInterpretation(element.getInterpretation());
            }
            element_copy.setCountField(element.getCountField());

            return element_copy;
        }
    }
}
