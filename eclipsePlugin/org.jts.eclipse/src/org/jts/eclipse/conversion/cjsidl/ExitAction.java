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
public class ExitAction extends ConversionUtil{
	/**
	 * Converts CJSIDL exit actions to JSIDL exit actions
	 * @param exitAction - input CJSIDL object
	 * @return - resulting JSIDL object
	 * @throws ConversionException 
	 */
	public static org.jts.jsidl.binding.Exit convert(org.jts.eclipse.cjsidl.exit exitAction) throws ConversionException {
		org.jts.jsidl.binding.Exit newExit = new org.jts.jsidl.binding.Exit();
		newExit.setInterpretation(CJSIDLCommentToJSIDLInterp(exitAction.getComment()));

		java.util.List<Object> newactions = newExit.getActionOrSendAction();
		if(exitAction.getActions() != null){
			EList<org.jts.eclipse.cjsidl.action> actions = exitAction.getActions().getActions();
			for(org.jts.eclipse.cjsidl.action action: actions){
				newactions.add(Action.convert(action));
			}
		}
		if(exitAction.getSendActions() != null){
			EList<org.jts.eclipse.cjsidl.action> actions = exitAction.getSendActions().getActions();
			for(org.jts.eclipse.cjsidl.action action: actions){
				newactions.add(SendAction.convert(action));
			}
		}
		
		return newExit;
	}
	/** 
	 * Converts JSIDL exit actions to CJSIDL exit actions
	 * @param exitAction - input JSIDL exit action
	 * @return - resulting CJSIDL exit action
	 */
	public static org.jts.eclipse.cjsidl.exit convert(org.jts.jsidl.binding.Exit exitAction) {
		CjsidlFactoryImpl factory = new CjsidlFactoryImpl();
		org.jts.eclipse.cjsidl.exit exitactions = factory.createexit();
		org.jts.eclipse.cjsidl.actionList exitactionlist = factory.createactionList();
		org.jts.eclipse.cjsidl.sendActionList exitsendactionlist = factory.createsendActionList();

		exitactions.setComment(JSIDLInterpToCJSIDLComment(exitAction.getInterpretation()));
		java.util.List<Object> tmpactions = exitAction.getActionOrSendAction();
		for(Object tmpobj: tmpactions){
			if(tmpobj instanceof org.jts.jsidl.binding.Action){
				exitactionlist.getActions().add(Action.convert((org.jts.jsidl.binding.Action) tmpobj));
			} else {
				exitsendactionlist.getActions().add(SendAction.convert((org.jts.jsidl.binding.SendAction) tmpobj));
			}
		}
		
		if(exitactionlist.getActions().size() > 0){
			exitactions.setActions(exitactionlist);
		}
		if(exitsendactionlist.getActions().size() > 0){
			exitactions.setSendActions(exitsendactionlist);
		}
		if(exitsendactionlist.getActions().size() == 0 && exitsendactionlist.getActions().size()== 0){
			// don't need to clutter up our document with unused structures
			exitactions = null;
		}
		
		return exitactions;

	}

}
