/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2011, United States Government
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
package org.jts.eclipse.conversion.cjsidl;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.apache.commons.lang.StringUtils;

/**
 * @author cmessmer
 * This utility class is used for various functionality that is reused throughout
 * the conversion process.
 */
public class ConversionUtil {
	// holds a map of units, so we don't have to load these many times
	static Map<String, String> unitMap=null;
	final static String OPTIONAL="optional";
	final static String COMMAND="command";

	// a string that is added to a name that should not be duplicated, but is.
	// this is used for declared types, since core services often defines the type and name
	// using the same string.  since we cannot handle it in CJSIDL, a mangling is added to the 
	// name to make it different from the type.  This is also used for similar purposes elsewhere.
	final static String mangled = "_8A27vDR";

	/**
	 * Checks a string to ensure it is not a key word, and mangles it if necessary.
	 * @param name - string being checked.
	 * @return - the original string with or without the mangling depending on the 
	 * comparison with key words.
	 */
	public static String checkName(String name){
		String result = name;
		
		if(name.equals("service") || name.equals("types") || name.equals("references") ||
				name.equals("input") || name.equals("output") || name.equals("messages") ||
				name.equals("events") || name.equals("message") || name.equals("event") ||
				name.equals("description") || name.equals("inherits_from") || name.equals("client_of") ||
				name.equals("using") || name.equals("protocol") || name.equals("state_machine") ||
				name.equals("state") || name.equals("default") || name.equals("transition") ||
				name.equals("internal") || name.equals("simple") || name.equals("push") ||
				name.equals("pop") || name.equals("actions") || name.equals("guard") ||
				name.equals("entry") || name.equals("exit") ){
			
			result = mangled + result;
		}
		
		return result;
	}
	
	/**
	 * converts a range of values to a JSIDL type.  This is used where the CJSIDL
	 * grammar does not include type in the definition of a range.
	 * @param lowerLim - the lower limit of the range
	 * @param upperLim - the upper limit of the range
	 * @return - a string representing a JSIDL data type that can hold the upper and 
	 * lower limits.
	 */
	public static String rangeToJSIDLType(long lowerLim, long upperLim){
		long range = upperLim - lowerLim;
		String type = "unsigned byte";
		if(range >= Byte.MIN_VALUE && range <= Byte.MAX_VALUE){
			type = "unsigned byte";
		} else if(range >= Short.MIN_VALUE && range <= Short.MAX_VALUE){
			type = "unsigned short integer";
		} else if(range >= Integer.MIN_VALUE && range <= Integer.MAX_VALUE){
			type = "unsigned integer";
		} else{
			type = "unsigned long integer";
		}
			
		return type;	
	}
	
	/**
	 * Converts from a JSIDL numeric type to a CJSIDL numeric type
	 * @param inputType input type must be a valid JSIDL numeric type
	 * @return A corresponding CJSIDL numeric type
	 */
	public static String JSIDLTypeToCJSIDLType(String inputType){
		String outputType="";
		
		if(inputType.equals("byte")){
			outputType = "int8";
		} else if(inputType.equals("unsigned byte")){
			outputType = "uint8";
		}else if(inputType.contains("unsigned short int")){
			outputType = "uint16";
		}else if(inputType.contains("unsigned int")){
			outputType = "uint32";
		}else if(inputType.contains("unsigned long int")){
			outputType = "uint64";
		}else if(inputType.contains("short int")){
			outputType = "int16";
		}else if(inputType.contains("long int")){
			outputType = "int64";
		}else if(inputType.contains("int")){
			outputType = "int32";
		}else if(inputType.equals("float")){
			outputType = "float";
		}else if(inputType.equals("long float")){
			outputType = "double";
		}
	
		return outputType;
	}
	
	/**
	 * Used to fill a gap where a maximum value isn't specified in a range.
	 * @param val - the current value, if there is one.
	 * @param inputType - the input data type that the maximum should fit within
	 * 
	 * @return - the original value if it exists, or the maximum value allowed within
	 * the inputType specified.
	 */
	public static String getCountMax(String val, String inputType){
		String result = val;
		if(result == null || result.isEmpty()){
			if(inputType.equals("byte") || inputType.equals("int8")){
				result = Byte.toString(Byte.MAX_VALUE);
			} else if(inputType.equals("short integer") || inputType.equals("int16")){
				result = Short.toString(Short.MAX_VALUE);
			}else if(inputType.equals("integer") || inputType.equals("int32")){
				result = Integer.toString(Integer.MAX_VALUE);
			}else if(inputType.equals("long integer") || inputType.equals("int64")){
				result = Long.toString(Long.MAX_VALUE);
			}else if(inputType.equals("unsigned byte") || inputType.equals("uint8")){
				result = Short.toString((short)(Byte.MAX_VALUE * 2 + 1));
			}else if(inputType.equals("unsigned short integer") || inputType.equals("uint16")){
				result = Integer.toString((int)(Short.MAX_VALUE * 2 + 1));
			}else if(inputType.equals("unsigned integer") || inputType.equals("uint32")){
				result = Long.toString((long)(Integer.MAX_VALUE * 2 + 1));
			}else if(inputType.equals("unsigned long integer") || inputType.equals("uint64")){
				result = "18446744073709551615";
			}else if(inputType.equals("float") || inputType.equals("float")){
				result = Float.toString(Float.MAX_VALUE);
			}else if(inputType.equals("long float") || inputType.equals("double")){
				result = Double.toString(Double.MAX_VALUE);
			}
		}
		
		return result;
	}
	/**
	 * Used to fill a gap where a minimum value isn't specified in a range.
	 * @param val - the current value, if there is one.
	 * @param inputType - the input data type that the minimum should fit within
	 * 
	 * @return - the original value if it exists, or the minimum value allowed within
	 * the inputType specified.
	 */
	public static String getCountMin(String val, String inputType){
		String result = val;
		if(result == null || result.isEmpty()){
			if(inputType.equals("byte") || inputType.equals("int8")){
				result = Byte.toString(Byte.MIN_VALUE);
			} else if(inputType.equals("short integer") || inputType.equals("int16")){
				result = Short.toString(Short.MIN_VALUE);
			}else if(inputType.equals("integer") || inputType.equals("int32")){
				result = Integer.toString(Integer.MIN_VALUE);
			}else if(inputType.equals("long integer") || inputType.equals("int64")){
				result = Long.toString(Long.MIN_VALUE);
			}else if(inputType.equals("unsigned byte") || inputType.equals("uint8")){
				result = "0";
			}else if(inputType.equals("unsigned short integer") || inputType.equals("uint16")){
				result = "0";
			}else if(inputType.equals("unsigned integer") || inputType.equals("uint32")){
				result = "0";
			}else if(inputType.equals("unsigned long integer") || inputType.equals("uint64")){
				result = "0";
			}else if(inputType.equals("float") || inputType.equals("float")){
				result = Float.toString(Float.MIN_VALUE);
			}else if(inputType.equals("long float") || inputType.equals("double")){
				result = Double.toString(Double.MIN_VALUE);
			}
		}
		
		return result;
	}
	
	/**
	 * Converts from a CJSIDL numeric type to a JSIDL numeric type
	 * @param inputType input type must be a valid CJSIDL numeric type
	 * @return A corresponding JSIDL numeric type
	 */
	public static String CJSIDLTypeToJSIDLType(String inputType){
		String outputType="";
		
		if(inputType.equals("int8")){
			outputType = "byte";
		} else if(inputType.equals("int16")){
			outputType = "short integer";
		}else if(inputType.equals("int32")){
			outputType = "integer";
		}else if(inputType.equals("int64")){
			outputType = "long integer";
		}else if(inputType.equals("uint8")){
			outputType = "unsigned byte";
		}else if(inputType.equals("uint16")){
			outputType = "unsigned short integer";
		}else if(inputType.equals("uint32")){
			outputType = "unsigned integer";
		}else if(inputType.equals("uint64")){
			outputType = "unsigned long integer";
		}else if(inputType.equals("float")){
			outputType = "float";
		}else if(inputType.equals("double")){
			outputType = "long float";
		}
	
		return outputType;
	}
	
	/**
	 * Converts from a CJSIDL numeric type to a JSIDL numeric type
	 * @param inputType input type must be a valid CJSIDL numeric type
	 * @return A corresponding JSIDL numeric type
	 */
	public static String CJSIDLTypeToShortenedJSIDLType(String inputType){
		String outputType="";
		
		if(inputType.equals("int8")){
			outputType = "byte";
		} else if(inputType.equals("int16")){
			outputType = "short int";
		}else if(inputType.equals("int32")){
			outputType = "int";
		}else if(inputType.equals("int64")){
			outputType = "long int";
		}else if(inputType.equals("uint8")){
			outputType = "unsigned byte";
		}else if(inputType.equals("uint16")){
			outputType = "unsigned short int";
		}else if(inputType.equals("uint32")){
			outputType = "unsigned int";
		}else if(inputType.equals("uint64")){
			outputType = "unsigned long int";
		}else if(inputType.equals("float")){
			outputType = "float";
		}else if(inputType.equals("double")){
			outputType = "long float";
		}
	
		return outputType;
	}
	
	/**
	 * Strips comment markers from the string
	 * @param comment - a CJSIDL comment
	 * @return - a string without comment markers
	 */
	public static String CJSIDLCommentToJSIDLInterp(String comment){
		String result= "";
		if(comment == null || comment.isEmpty())
		{
			return null;
		}
		result = comment;
		result = result.replace("##", "");
		return result;
	}
	
	/**
	 * Adds comment markers to a JSIDL interpretation string
	 * @param interp - a JSIDL interpretation string
	 * @return - a string with comment markers
	 */
	public static String JSIDLInterpToCJSIDLComment(String interp){
		String result= "";
        
        if (interp == null || interp.isEmpty()) {
            return null;
        }

        result += "##";
        
        String returnString = "";
        // get rid of some of the whitespace
        String strippedComment = StringUtils.strip(interp);

        // if the comments are too long, then break them up
        // on multiple lines.  at the same time remove additional spaces.
        String[] tmpstr = strippedComment.split("[ \n\t]+");
        for (String word : tmpstr) {
            // many whitespace characters are embedded in these strings
            // make sure we remove them.
            returnString += " " + StringUtils.strip(word);
        }
        result += returnString.trim() + "##";
		
		return result;
	}
	/**
	 * provides access to the units map so that units can be converted from
	 * CJSIDL to JSIDL
	 * @param cjsidlUnit - the input CJSIDL units.
	 * @return - the corresponding JSIDL units
	 */
	public static String JSIDLUnitFromCJSIDLUnit(String cjsidlUnit)
	{
		if(unitMap == null){
			initUnitMap();
		}
		return unitMap.get(cjsidlUnit);
	}
	/**
	 * Converts a JSIDL unit to a CJSIDL unit via map lookup.
	 * @param jsidlUnit - the input JSIDL unit
	 * @return - the corresponding CJSIDL unit
	 */
	public static String CJSIDLUnitFromJSIDLUnit(String jsidlUnit)
	{
		String units = null;
		if(unitMap == null){
			initUnitMap();
		}
		Set<String> keys = unitMap.keySet();
		for(String key: keys){
			if(unitMap.get(key).equals(jsidlUnit)){
				return key;
			}
		}
		
		return units;
	}

	/**
	 * Called to initialize the units map that is used to look up 
	 * units base on the input units of a different specification type 
	 * (eg. JSIDL vs. CJSIDL)
	 */
	public static void initUnitMap(){
		unitMap = new HashMap<String, String>();

		unitMap.put("meter", "meter");
		unitMap.put("kilogram", "kilogram");
		unitMap.put("second", "second");
		unitMap.put("ampere", "ampere");
		unitMap.put("kelvin", "kelvin");
		unitMap.put("mole", "mole");
		unitMap.put("candela", "candela");
		unitMap.put("one", "one");
		
		unitMap.put("square_meter", "square meter");
		unitMap.put("cubic_meter", "cubic meter");
		unitMap.put("meter_per_second", "meter per second");
		unitMap.put("meter_per_second_squared", "meter per second squared");
		unitMap.put("reciprocal_meter", "reciprocal meter");
		unitMap.put("kilogram_per_cubic_meter", "kilogram per cubic meter");
		unitMap.put("cubic_meter_per_kilogram", "cubic meter per kilogram");
		unitMap.put("ampere_per_square_meter", "ampere per square meter");
		unitMap.put("ampere_per_meter", "ampere per meter");
		unitMap.put("mole_per_cubic_meter", "mole per cubic meter");
		unitMap.put("candela_per_square_meter", "candela per square meter");
		
		unitMap.put("radian", "radian");
		unitMap.put("steradian", "steradian");
		unitMap.put("hertz", "hertz");
		unitMap.put("newton", "newton");
		unitMap.put("pascal", "pascal");
		unitMap.put("joule", "joule");
		unitMap.put("watt", "watt");
		unitMap.put("coulomb", "coulomb");
		unitMap.put("volt", "volt");
		unitMap.put("farad", "farad");
		unitMap.put("ohm", "ohm");
		unitMap.put("siemens", "siemens");
		unitMap.put("weber", "weber");
		unitMap.put("tesla", "tesla");
		unitMap.put("henry", "henry");
		unitMap.put("degree_Celsius", "degree Celsius");
		unitMap.put("lumen", "lumen");
		unitMap.put("lux", "lux");
		unitMap.put("becquerel", "becquerel");
		unitMap.put("sievert", "sievert");
		unitMap.put("katal", "katal");
		unitMap.put("pascal_second", "pascal second");
		unitMap.put("newton_meter", "newton meter");
		unitMap.put("newton_per_meter", "newton per meter");
		unitMap.put("radian_per_second", "radian per second");		

		unitMap.put("radian_per_second_squared", "radian per second squared");
		unitMap.put("watt_per_square_meter", "watt per square meter");
		unitMap.put("joule_per_kelvin", "joule per kelvin");
		unitMap.put("joule_per_kilogram", "joule per kilogram");
		unitMap.put("watt_per_meter_kelvin", "watt per meter kelvin");
		unitMap.put("joule_per_cubic_meter", "joule per cubic meter");
		unitMap.put("volt_per_meter", "volt per meter");
		unitMap.put("coulomb_per_cubic_meter", "coulomb per cubic meter");
		unitMap.put("coulomb_per_square_meter", "coulomb per square meter");
		unitMap.put("farad_per_meter", "farad per meter");
		unitMap.put("henry_per_meter", "henry per meter");
		unitMap.put("joule_per_mole", "joule per mole");
		unitMap.put("joule_per_mole_kelvin", "joule per mole kelvin");
		unitMap.put("coulomb_per_kilogram", "coulomb per kilogram");
		unitMap.put("gray_per_second", "gray per second");
		unitMap.put("watt_per_square_meter_steradian", "watt per square meter steradian");
		unitMap.put("katal_per_cubic_meter", "katal per cubic meter");
		
		unitMap.put("minute", "minute");
		unitMap.put("hour", "hour");
		unitMap.put("day", "day");
		unitMap.put("degree", "degree");
		unitMap.put("liter", "liter");
		unitMap.put("metric_ton", "metric ton");
		unitMap.put("neper", "neper");
		unitMap.put("bel", "bel");
		unitMap.put("nautical_mile", "nautical mile");
		unitMap.put("knot", "knot");
		unitMap.put("are", "are");
		unitMap.put("hectare", "hectare");
		unitMap.put("bar", "bar");
		unitMap.put("angstrom", "�ngstro");
		unitMap.put("barn", "barn");
		unitMap.put("curie", "curie");
		unitMap.put("roentgen", "roentgen");
		unitMap.put("rad", "rad");
		unitMap.put("rem", "rem");
		
		// added in initial CJSIDL implementation, but removed
		// since they don't appear in JSIDL definition
//		unitMap.put("percent", "");
//		unitMap.put("pixel", "");
//		unitMap.put("frame", "");
//		unitMap.put("frames_per_second", "");
//		unitMap.put("millisecond", "");
//		unitMap.put("month", "");
//		unitMap.put("year", "");
//		unitMap.put("milliradian", "");
//		unitMap.put("milliradian_per_second", "");
//		unitMap.put("millimeter", "");
//		unitMap.put("millimeter_per_second", "");


	}	
	

	/**
	 * Converts a hex string to a byte array
	 * @param hexString - input hex string
	 * @return - resulting byte array
	 */
	public static byte[] hexStringToByteArray(String hexString) {
	     HexBinaryAdapter adapter = new HexBinaryAdapter();
	     if(hexString.startsWith("0x")){
	    	 hexString = hexString.substring(2, hexString.length());
	     }
	     byte[] bytes = adapter.unmarshal(hexString);
	     return bytes;
	}
	/**
	 * Converts a byte array to a hex string 
	 * @param hexArray - input byte array
	 * @return - resulting hex string
	 */
	public static String byteArrayToHexString(byte[] hexArray) {
	     HexBinaryAdapter adapter = new HexBinaryAdapter();
	     String result = adapter.marshal(hexArray);
	     return result;
	}
	

	/**
	 * Adds single quotes to a string that should be a constant value.
	 * @param const1 - the string representing a constant value
	 * @return - the new string with single quotes added.
	 */
	public static String getStringFromConst(String const1) {
		String output = "";
		output += "'" + const1 + "'";
		return output;
	}
}
