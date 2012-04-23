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

import java.io.UnsupportedEncodingException;

import org.jts.eclipse.cjsidl.description;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;


/**
 * @author cmessmer
 *
 */
public class Description {

	/**
	 * Converts a CJSIDL description to a JSIDL description
	 * @param descr - string from the CJSIDL description
	 * @return - resulting JSIDL description
	 */
	public static org.jts.jsidl.binding.Description convert(String descr) {
		org.jts.jsidl.binding.Description newdescr = new org.jts.jsidl.binding.Description();
		// remove double quotes from string
		descr = descr.replace("\"", "");
		descr = descr.replace("'", "\"");
		
		// used to remove some strange conversions from UTF8 to wide chars that xtext is doing.
		try {
			byte[] utf8Bytes = descr.getBytes("UTF8");
			descr = new String(utf8Bytes);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		newdescr.setContent(descr);
		newdescr.setSpace("preserve");
		
		return newdescr;
	}
	
	/**
	 * Converts a JSIDL description to a CJSIDL object
	 * @param descr - input JSIDL description
	 * @return - resulting CJSIDL description
	 */
	public static description convert(org.jts.jsidl.binding.Description descr) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.description output = factory.createdescription();
		
		String content = descr.getContent().trim();
		content = content.replace("\"", "'");
		// add double quotes to make a valid string
		output.setContent("\"" + content + "\"");
		
		return output;
	}

}
