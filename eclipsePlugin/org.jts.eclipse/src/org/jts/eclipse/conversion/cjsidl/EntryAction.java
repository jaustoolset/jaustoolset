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
import org.jts.eclipse.cjsidl.impl.CjsidlFactoryImpl;

/**
 * @author cmessmer
 *
 */
public class EntryAction extends ConversionUtil{
	/**
	 * Converts CJSIDL entry actions to JSIDL entry actions
	 * @param entryAction - input CJSIDL object
	 * @return - resulting JSIDL object
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.Entry convert(org.jts.eclipse.cjsidl.entry entryAction) throws ConversionException {
		org.jts.jsidl.binding.Entry newExit = new org.jts.jsidl.binding.Entry();
		newExit.setInterpretation(CJSIDLCommentToJSIDLInterp(entryAction.getComment()));

		java.util.List<Object> newactions = newExit.getActionOrSendAction();
		if(entryAction.getActions() != null){
			EList<org.jts.eclipse.cjsidl.action> actions = entryAction.getActions().getActions();
			for(org.jts.eclipse.cjsidl.action action: actions){
				newactions.add(Action.convert(action));
			}
		}
		if(entryAction.getSendActions() != null){
			EList<org.jts.eclipse.cjsidl.action> actions = entryAction.getSendActions().getActions();
			for(org.jts.eclipse.cjsidl.action action: actions){
				newactions.add(SendAction.convert(action));
			}
		}
		
		return newExit;
	}
	
	/** 
	 * Converts JSIDL entry actions to CJSIDL entry actions
	 * @param entryAction - input JSIDL entry action
	 * @return - resulting CJSIDL entry action
	 */
	public static org.jts.eclipse.cjsidl.entry convert(org.jts.jsidl.binding.Entry entryAction) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.entry entryactions = factory.createentry();

		org.jts.eclipse.cjsidl.actionList entryactionlist = factory.createactionList();
		org.jts.eclipse.cjsidl.sendActionList entrysendactionlist = factory.createsendActionList();
		
		entryactions.setComment(JSIDLInterpToCJSIDLComment(entryAction.getInterpretation()));
		java.util.List<Object> tmpactions = entryAction.getActionOrSendAction();
		for(Object tmpobj: tmpactions){
			if(tmpobj instanceof org.jts.jsidl.binding.Action){
				entryactionlist.getActions().add(Action.convert((org.jts.jsidl.binding.Action) tmpobj));
			} else {
				entrysendactionlist.getActions().add(SendAction.convert((org.jts.jsidl.binding.SendAction) tmpobj));
			}
		}
		if(entryactionlist.getActions().size() > 0){
			entryactions.setActions(entryactionlist);
		}
		if(entrysendactionlist.getActions().size() > 0){
			entryactions.setSendActions(entrysendactionlist);
		}
		if(entryactionlist.getActions().size() == 0 && entrysendactionlist.getActions().size()== 0){
			// don't need to clutter up our document with unused structures
			entryactions = null;
		}
		return entryactions;
		

	}

}
