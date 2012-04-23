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

import org.eclipse.emf.common.util.EList;
import org.jts.eclipse.cjsidl.protocolBehavior;
import org.jts.eclipse.cjsidl.refAttr;
import org.jts.eclipse.cjsidl.serviceDef;
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class ProtocolBehavior extends ConversionUtil{

	/**
	 * Converts a CJSIDL object to JSIDL
	 * @param protocolBehavior -a CJSIDL protocol behavior
	 * @return - resulting JSIDL protocol behavior
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.ProtocolBehavior convert(protocolBehavior protocolBehavior) throws ConversionException {
		org.jts.jsidl.binding.ProtocolBehavior newpb = new org.jts.jsidl.binding.ProtocolBehavior();
		
		String stateless = protocolBehavior.getStateless();

		// if one was specified then set it to whatever it was. default is "stateful"
		if(stateless != null){
			newpb.setIsStateless(true);
		} 
		
		//newpb.getStart().add(StartState.convert(protocolBehavior.getStartState()));
		java.util.List<org.jts.jsidl.binding.Start> newstartList = newpb.getStart();
		java.util.List<org.jts.jsidl.binding.StateMachine> newsmList = newpb.getStateMachine();
		EList<org.jts.eclipse.cjsidl.stateMachine> smList = protocolBehavior.getStateMachine();
		for(org.jts.eclipse.cjsidl.stateMachine sm: smList){
			newsmList.add(StateMachine.convert(sm));
			String smName = "";
			for(refAttr ref: sm.getScoped()){
				smName += ref.getName() + ".";
			}
			smName += sm.getName();
			newstartList.add(StartState.convert(smName, sm.getStartState()));
		}
		
		return newpb;
	}

	/**
	 * Converts a JSIDL protocol behavior to CJSIDL
	 * @param protocolBehavior - a JSIDL protocol
	 * @param svcDef - the parent service def
	 * @return - resulting CJSIDL protocol behavior
	 * @throws ConversionException 
	 */
	public static protocolBehavior convert(org.jts.jsidl.binding.ProtocolBehavior protocolBehavior, serviceDef svcDef) throws ConversionException {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.protocolBehavior output = factory.createprotocolBehavior();

		boolean stateless = false;
		try{
			stateless = protocolBehavior.isIsStateless();
		} catch(Exception ex){
			
		}
		if(stateless){
			output.setStateless("stateless");
		} 

		EList<org.jts.eclipse.cjsidl.stateMachine> newsmList = output.getStateMachine();
		java.util.List<org.jts.jsidl.binding.Start> startList = protocolBehavior.getStart();
		java.util.List<org.jts.jsidl.binding.StateMachine> smList = protocolBehavior.getStateMachine();
		for(org.jts.jsidl.binding.StateMachine sm: smList){
			String FSMName = sm.getName();
			org.jts.jsidl.binding.Start start=null;
			for(org.jts.jsidl.binding.Start st: startList){
				if(st.getStateMachineName().equals(FSMName)){
					start = st;
					break;
				}
			}
			newsmList.add(StateMachine.convert(sm, start, svcDef));
		}
		
		return output;
	}

}
