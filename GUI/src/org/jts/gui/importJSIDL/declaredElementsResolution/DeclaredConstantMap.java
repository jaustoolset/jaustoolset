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

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;


public class DeclaredConstantMap
{
    private static DeclaredConstantMap instance = new DeclaredConstantMap();
    private HashMap<String, Object> typesMap;
    private HashMap<String, Object> currentMap;
    private ArrayDeque<HashMap<String, Object>> previousMap;
    
    public static DeclaredConstantMap getInstance() 
    {
          return instance;
    }

    /**
     * Clear out all contents of the DeclaredConstantMap.  Needed in case of errors in resolving constant references.
     */
    public void clear() {
        typesMap.clear();
        currentMap = typesMap;
        previousMap.clear();
    }
    
    public boolean addType(String name, Object obj)
    {
    	if(name == null || obj == null)
    	{
    		return false;
    	}
    	
    	currentMap.put(name, obj);
    	return true;
    }
    
    public boolean setPackage(String name)
    {
    	if(name == null && currentMap != typesMap)
    	{
    		currentMap = previousMap.pop();
    		return true;
    	}
    	else
    	{
	    	HashMap<String, Object> tempMap = findPackage(typesMap, name);
	    	if(tempMap != null)
	    	{
	    		previousMap.push(currentMap);
	    		currentMap = tempMap;
	    		return true;
	    	}
	    	else
	    	{
	    		return false;
	    	}
    	}
    }
    
    public boolean isEmpty()
    {
    	return typesMap.isEmpty();
    }
    
    @SuppressWarnings("unchecked")
	public Object getType(String name)
    {
    	if(name == null)
    	{
    		return null;
    	}

    	// Parse the name string for the first package name
    	HashMap<String, Object> map = currentMap;
    	
    	String[] names = name.split("\\.");

    	// Find the starting package for this element (if possible)
    	for(int i = 0; i < names.length-1; i++)
    	{
    		map = findPackage(map, names[i]);
    		
    		if(map == null)
	    	{
    			// ERROR: Package not found
	    		return null;
	    	}
    	}
    	
    	return map.get(names[names.length-1]); 
    }
    
    public void print()
    {
    	for(String key: currentMap.keySet())
    	{
    		if(currentMap.get(key) instanceof HashMap)
    		{

    		}
    		else if(currentMap.get(key) instanceof org.jts.jsidl.binding.ConstDef)
    		{
    			org.jts.jsidl.binding.ConstDef constantDef = (org.jts.jsidl.binding.ConstDef) currentMap.get(key);
    			
    			//if(input.contains(key))
    			//{
    			//	input = input.replace(key, constantDef.getConstValue());
    			//}
    			
    	    	//org.nfunk.jep.JEP myParser = new org.nfunk.jep.JEP();
    	    	//myParser.parseExpression(input);

    			//return Double.toString(myParser.getValue());
    		}
    	}
    }
    
    public String getReplacementForInput(String input, HashMap<String, Object> map)
    {
    	if(input == null)
    	{
    		return null;
    	}

    	for(String key: map.keySet())
    	{
    		if(map.get(key) instanceof HashMap)
    		{
    			input = getReplacementForInput(input, (HashMap<String, Object>) map.get(key));
    		}
    		else if(map.get(key) instanceof org.jts.jsidl.binding.ConstDef)
    		{
    			org.jts.jsidl.binding.ConstDef constantDef = (org.jts.jsidl.binding.ConstDef) map.get(key);

    			// if matching, return computed constant expression value
    			if(input.contains(key))
    			{
    				input = input.replace(key, constantDef.getConstValue());
    			
    				org.nfunk.jep.JEP myParser = new org.nfunk.jep.JEP();
    				myParser.parseExpression(input);

    				return Double.toString(myParser.getValue());
    			}
    		}
    	}
    	
    	return input;
    }
    
    public void startPackage(String name)
    {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	currentMap.put(name, map);

    	previousMap.push(currentMap);
    	currentMap = map;
    }
    
    public boolean endPackage(String name)
    {
    	if(previousMap.isEmpty() || previousMap.peek().get(name) == null)
    	{
    		return false;
    	}
    	
    	currentMap = previousMap.pop();
    	return true; 
    }
    
    private DeclaredConstantMap()
    {
          typesMap = new HashMap<String, Object>();
          currentMap = typesMap;
          previousMap = new ArrayDeque<HashMap<String, Object>>();
    }
    
    private static HashMap<String, Object> findPackage(HashMap<String, Object> map, String name)
    {
    	// Input null, output null
    	if(map == null)
    	{
    		return map;
    	}
    	
    	// Found our sub-reference
    	if(map.get(name) != null)
    	{
    		return (HashMap<String, Object>) map.get(name);
    	}
    	
    	// Hashmap Iterator
    	Iterator itr = map.keySet().iterator();
    	
    	//iterate through HashMap values iterator
    	while(itr.hasNext())
    	{
    		Object obj = itr.next(); 

    		if( map.get(obj) instanceof HashMap)
    		{
    			// This element is another map, recurse through that looking for our package
    			HashMap<String, Object> ret = findPackage((HashMap<String, Object>) map.get(obj), name);
    			if(ret != null)
    			{
    				// Found it
    				return ret;
    			}
    		}
    	}
    	
    	// Package not found, no more sub-references
    	return null;
    }
    
    public static void lookupCountField(org.jts.jsidl.binding.CountField countField)
    {
    	DeclaredConstantMap declaredConstantMap = DeclaredConstantMap.getInstance();
    	
    	if(countField.getMaxCount() != null)
    	{
    		String value = declaredConstantMap.getReplacementForInput(countField.getMaxCount(), instance.currentMap);
    		countField.setMaxCount(value);
    	}
    	
    	if(countField.getMinCount() != null)
    	{
    		String value = declaredConstantMap.getReplacementForInput(countField.getMinCount(), instance.currentMap);
    		countField.setMinCount(value);
    	}
    }
    
    public static void lookupScaleRange(org.jts.jsidl.binding.ScaleRange scaleRange)
    {
    	DeclaredConstantMap declaredConstantMap = DeclaredConstantMap.getInstance();

		if(scaleRange.getRealLowerLimit() != null)
		{
			String value = declaredConstantMap.getReplacementForInput(scaleRange.getRealLowerLimit(), 
                                instance.currentMap);
			scaleRange.setRealLowerLimit(value);
		}
		
		if(scaleRange.getRealUpperLimit() != null)
		{
			String value = declaredConstantMap.getReplacementForInput(scaleRange.getRealUpperLimit(), 
                                instance.currentMap);
			scaleRange.setRealUpperLimit(value);
		}
    }
   
    public static void lookupTypeAndUnitsEnum(org.jts.jsidl.binding.TypeAndUnitsEnum typeAndUnitsEnum)
    {
    	DeclaredConstantMap declaredConstantMap = DeclaredConstantMap.getInstance();
    	
		if(typeAndUnitsEnum.getScaleRange() != null)
		{
			org.jts.jsidl.binding.ScaleRange scaleRange = typeAndUnitsEnum.getScaleRange();
			
			if(scaleRange.getRealLowerLimit() != null)
			{
				String value = declaredConstantMap.getReplacementForInput(scaleRange.getRealLowerLimit(), instance.currentMap);
				scaleRange.setRealLowerLimit(value);
			}
			
			if(scaleRange.getRealUpperLimit() != null)
			{
				String value = declaredConstantMap.getReplacementForInput(scaleRange.getRealUpperLimit(), instance.currentMap);
				scaleRange.setRealUpperLimit(value);
			}
		}
    }
    
    public static void lookupValueRange(org.jts.jsidl.binding.ValueRange valueRange)
    {
    	DeclaredConstantMap declaredConstantMap = DeclaredConstantMap.getInstance();
    	
    	if(valueRange.getLowerLimit() != null)
		{
			String value = declaredConstantMap.getReplacementForInput(valueRange.getLowerLimit(), instance.currentMap);
			valueRange.setLowerLimit(value);
		}
		
		if(valueRange.getUpperLimit() != null)
		{
			String value = declaredConstantMap.getReplacementForInput(valueRange.getUpperLimit(), instance.currentMap);
			valueRange.setUpperLimit(value);
		}
    }
    
    public static void lookupValueSet(org.jts.jsidl.binding.ValueSet valueSet)
    {
		if(valueSet.getValueRangeOrValueEnum() != null)
		{
			for(Object obj:valueSet.getValueRangeOrValueEnum())
			{
				if(obj instanceof org.jts.jsidl.binding.ValueRange)
				{
					org.jts.jsidl.binding.ValueRange valueRange = (org.jts.jsidl.binding.ValueRange)obj;
					
					lookupValueRange(valueRange);
				}
			}
		}
    }
    
    public static void lookupSubField(org.jts.jsidl.binding.SubField subField)
    {	    	
    	if(subField.getValueSet() != null)
		{
    		lookupValueSet(subField.getValueSet());
		}
    }
    
    public static void lookupTypeAndUnitsField(org.jts.jsidl.binding.TypeAndUnitsField typeAndUnitsField)
    {
		if(typeAndUnitsField.getTypeAndUnitsEnum() != null)
		{
			for(org.jts.jsidl.binding.TypeAndUnitsEnum typeAndUnitsEnum:typeAndUnitsField.getTypeAndUnitsEnum())
			{
				lookupTypeAndUnitsEnum(typeAndUnitsEnum);
			}
		}
    }
       
    public static void lookupFixedField(org.jts.jsidl.binding.FixedField fixedField)
    {
		if(fixedField.getScaleRange() != null)
		{
			lookupScaleRange(fixedField.getScaleRange());
		}
    }
    
    public static void lookupVariableField(org.jts.jsidl.binding.VariableField variableField)
    {
		if(variableField.getTypeAndUnitsField() != null)
		{
			lookupTypeAndUnitsField(variableField.getTypeAndUnitsField());
		}
    }
    
    public static void lookupBitField(org.jts.jsidl.binding.BitField bitField)
    {
		if(bitField.getSubField() != null)
		{
			for(org.jts.jsidl.binding.SubField subField:bitField.getSubField())
			{
				lookupSubField(subField);
			}
		}
    }
    
    public static void lookupFixedLengthString(org.jts.jsidl.binding.FixedLengthString fixedLengthString)
    {
    	DeclaredConstantMap declaredConstantMap = DeclaredConstantMap.getInstance();
    	
		String value = declaredConstantMap.getReplacementForInput(fixedLengthString.getStringLength(), instance.currentMap);
		fixedLengthString.setStringLength(value);
    }
    
    public static void lookupVariableLengthString(org.jts.jsidl.binding.VariableLengthString variableLengthString)
    {
    	if(variableLengthString.getCountField() != null)
    	{
    		lookupCountField(variableLengthString.getCountField());
    	}
    }
    
    public static void lookupVariableLengthField(org.jts.jsidl.binding.VariableLengthField variableLengthField)
    {
    	if(variableLengthField.getCountField() != null)
    	{
    		lookupCountField(variableLengthField.getCountField());
    	}
    }
    
    public static void lookupVariableFormatField(org.jts.jsidl.binding.VariableFormatField variableFormatField)
    {
    	if(variableFormatField.getCountField() != null)
    	{
    		lookupCountField(variableFormatField.getCountField());
    	}
    }
    	
}
