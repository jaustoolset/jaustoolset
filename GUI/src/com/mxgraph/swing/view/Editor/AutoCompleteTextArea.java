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

package com.mxgraph.swing.view.Editor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.ArrayList;

import javax.swing.JTextArea;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.proxy.HibernateProxy;

import com.mxgraph.util.mxUtils;
import com.u2d.app.Context;
import com.u2d.list.RelationalList;
import org.jts.codegenerator.CodeLines;

public class AutoCompleteTextArea extends JTextArea
{
	private HashMap<String, String> storedTransitions;
	private ArrayList<String> storedGuards;
	private ArrayList<String> storedActions;
	private ArrayList<String> storedPush;
	private HashMap<String, HashHolder> triggerParameters;
	private ArrayList<String> allMessages;
	
	private boolean popUpShowing;
	private boolean firstPopup;
	
	private com.u2d.generated.ProtocolBehavior protocolBehavior;
	private TextAreaPopup popup;
	
	private int desiredCaretPosition;
	
	private String cellStyle;
	
	public AutoCompleteTextArea( com.u2d.generated.ProtocolBehavior pb )
	{
		storedTransitions = new HashMap<String, String>();
		storedGuards = new ArrayList<String>();
		storedActions = new ArrayList<String>();
		storedPush = new ArrayList<String>();
		triggerParameters = new HashMap<String, HashHolder>();
		
		allMessages = new ArrayList<String>();
		
		popUpShowing = false;
		firstPopup = true;
		
		protocolBehavior = pb;
		popup = new TextAreaPopup( this, getFont().getSize2D() );
		
		cellStyle = "";
		
		addKeyListener( getKeyHandler() );
	}
	
	public void setCurrentCellStyle( String currentStyle )
	{
		cellStyle = currentStyle;
	}
	
	public boolean isPopupShowing()
	{
		return popUpShowing;
	}
	
	public void handleMousePress(MouseEvent e)
	{
		if(popUpShowing)
		{
			showPopup();
		}
		else
		{
			hidePopup();
		}
	}
	
	private KeyAdapter getKeyHandler()
	{
		return new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				// toggle popup when you press the ctrl key
				if(e.getKeyCode() == KeyEvent.VK_CONTROL)
				{
					if(popUpShowing)
					{
						popUpShowing = false;
					}
					else
					{
						popUpShowing = true;
					}
				}
				else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					popUpShowing = false;
				}
				
				// get the protocol behavior only when it is the first time running
				if(firstPopup)
				{
					// set all ArrayList of string variables to match to
					setAutocompleteVariables();
					
					// set first false so we don't try to process everything again
					firstPopup = false;
				}
		
				// only show popup when popup mode is on
				if(popUpShowing)
				{
					showPopup();
					
			    	// deal with up/down after we have drawn the popup
					// control up/down when up/down is pressed
					if(e.getKeyCode() == KeyEvent.VK_UP)
					{
						popup.decreaseListIndex();
						e.consume();
					}
					else if(e.getKeyCode() == KeyEvent.VK_DOWN)
					{
						popup.increaseListIndex();
						e.consume();
					}
					else if(e.getKeyCode() == KeyEvent.VK_ENTER)
					{
						popup.selectListIndexKeyEntry();
						e.consume();
					}
					else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
					{
						popup.resetSelection();
					}
					else if(e.getKeyCode() == KeyEvent.VK_LEFT)
					{
						popup.resetSelection();
					}
				}
				else
				{
					// change visibility of box
					hidePopup();
				}
			}
			
			public void keyPressed(KeyEvent e)
			{
				if(popUpShowing)
				{			
					// consume events so we will don't move the caret in the textbox
					// events also consumed in keyReleased
					if(e.getKeyCode() == KeyEvent.VK_UP)
					{
						e.consume();
					}
					else if(e.getKeyCode() == KeyEvent.VK_DOWN)
					{
						e.consume();
					}
					else if(e.getKeyCode() == KeyEvent.VK_ENTER)
					{
						e.consume();
					}
				}
			}
		};
	}
		
	public void showPopup()
	{
    	// remove previous items from box
		popup.removeAllItems();
		
		// revalidate because we are removing components
		popup.revalidate();

		String currentText = getCurrentText();
		int currentCaret = getCurrentCaret();
		
		// we only need to know up to caret to determine where we are in the editing process
		String upToCaret = currentText.substring(0, currentCaret);
		
    	// set the correct items in the drop down box based on which type of editing is being done
		if(isEditingPushPop(upToCaret))
		{
			popup.addItems( handlePushPop( upToCaret ) );
		}
		else if(isEditingAction(upToCaret))
    	{
			popup.addItems( handleAction( upToCaret ) );
    	}
    	else if(isEditingGuard(upToCaret))
    	{
    		popup.addItems( handleGuard( upToCaret ) );
    	}
    	else if(isEditingTriggerParameters(upToCaret))
    	{
    		popup.addItems( handleTriggerParameters( upToCaret ) );
    	}
    	else
    	{
    		popup.addItems( handleTransition( upToCaret, currentText ) );
    	}

		// show autocomplete popup with offset from text area		
		setPopupPosition( getText(),  getCaretPosition() );
		//setPopupSize(values);
		
		// return focus to the textArea
		requestFocusInWindow();
	}

	private ArrayList< String > handlePushPop( String upToCaret )
	{
		ArrayList< String > values = new ArrayList< String >();

		if( cellStyle.contains( "pushTransition" ) )
		{
			if(!isDoneEditingPushPop(upToCaret))
			{
				//System.out.println("Editing Push");
				popup.create("End State");
	    		values = getReplacemets(upToCaret, storedPush);
			}
		}
		else if( cellStyle.contains( "popTransition" ) )
		{
    		if(isEditingArguments(upToCaret))
    		{
    			//System.out.println("Editing Pop Action Arguments");
	    		popup.create("Argument , or )");
	    		values = getReplacemets(upToCaret, getCurrentTransitionArguments());
    		}
    		else if(!isDoneEditingPushPop(upToCaret))
    		{
	    		//System.out.println("Editing Pop");
	    		popup.create("Transition");
	    		values = getReplacemets(upToCaret, storedTransitions);
    		}
		}

		return values;
	}

	private ArrayList< String > handleAction( String upToCaret )
	{
		ArrayList< String > values = new ArrayList< String >();

		if(isEditingArguments(upToCaret))
		{
			//System.out.println("Editing Guard Arguments");
    		popup.create("Argument , or )");
    		values = getReplacemets(upToCaret, getCurrentTransitionArguments());
		}
		else
		{
    		//System.out.println("Editing Actions");
    		popup.create("Action(");
    		values = getReplacemets(upToCaret, storedActions);
		}

		return values;
	}

	private ArrayList< String > handleGuard( String upToCaret )
	{
		ArrayList< String > values = new ArrayList< String >();

		if(isEditingGuardArguments(upToCaret))
		{
			//System.out.println("Editing Guard Arguments");
    		popup.create("Argument , or )");
    		values = getReplacemets(upToCaret, getCurrentTransitionArguments());
		}
		else if(!isDoneEditingGuard(upToCaret))
		{
    		//System.out.println("Editing Guard");
    		popup.create("Guard(");
    		values = getReplacemets(upToCaret, storedGuards);
		}

		return values;
	}

	private ArrayList< String > handleTriggerParameters( String upToCaret )
	{
		ArrayList< String > values = new ArrayList< String >();

		if(!isDoneEditingTriggerParameterType(upToCaret))
		{
    		//System.out.println("Editing Parameter Type");
    		popup.create("Parameter Type");
    		values = getReplacemets(upToCaret, getCurrentTransitionParameters());
		}
		else if(!isDoneEditingTriggerParameterName(upToCaret))
		{
			//System.out.println("Editing Parameter Value");
			popup.create("Parameter Value");
		}

		return values;
	}

	private ArrayList< String > handleTransition( String upToCaret, String currentText )
	{
		ArrayList< String > values = new ArrayList< String >();

		// don't show trigger options when it is any type of default transition
		if( !( cellStyle.contains( "default" ) ) )
		{
			//System.out.println("Editing Transition");
			popup.create("Transition(");
			
			// we may want to filter transition matches based on what level of inheritance the caret is at
			upToCaret = getRelevantServiceName(currentText, upToCaret);
			
			values = getReplacemets(upToCaret, storedTransitions);
		}

		return values;
	}

	public void setPopupPosition(String text, int caretPosition)
	{
		String upToCaret = text.substring(0, caretPosition);
		int linebreak = upToCaret.lastIndexOf("\n");
		 
        // find line break count
		int lineBreakCount = 1;
		String temp1 = "\n";
		String temp2 = "\r\n";
		for(int i = 0; i < upToCaret.length(); i++)
		{
			char c = upToCaret.charAt(i);
			if(c == temp1.charAt(0) || c == temp2.charAt(0))
			{
				lineBreakCount++;
			}
		}
		
        // only re-sample line if caret is after the last line break in the string
		if(linebreak != -1 && caretPosition > linebreak)
		{
			upToCaret = text.substring(linebreak, caretPosition);
		}
		
        // find tabs count in current line
		int tabCount = 0;
		String temp = "\t";
		for(int i = 0; i < upToCaret.length(); i++)
		{
			char c = upToCaret.charAt(i);
			if(c == temp.charAt(0))
			{
				tabCount++;
			}
		}
		
		// general offsets for popup
		int popupXOffset = 20;
        int popupYOffset = 20;
        
        // get height of single line for multiplication with number of line breaks to get final y location (added "A" in case string is just \n)
        int lineHeight = (int)mxUtils.getSizeForString(upToCaret + "A", getFont().getSize2D()).getHeight();
        
        // since we are using fixed with courier, we need to get the maximimum width of a single character
        int tabWidth = (int)mxUtils.getSizeForString("A", getFont().getSize2D()).getWidth();
		
        // getSizeForString function doesn't like \n
		if(upToCaret.compareTo("\n") != 0)
		{
			popupXOffset += (int)mxUtils.getSizeForString(upToCaret, getFont().getSize2D()).getWidth();
		}
		
		// increment offsets based on the tab size of the text area
		popupXOffset += tabCount * getTabSize()*tabWidth;
		popupYOffset += lineBreakCount*lineHeight;

		popup.showPopup(popupXOffset, popupYOffset);
	}
	
	public void setPopupSize(ArrayList<String> values)
	{
		int maxWidth = 200;
		int minHeight = 100;
		
		if(values == null)
		{
			popup.setSize(maxWidth, minHeight);	
		}
		
		// change width of popup
		for(String value:values)
		{
			// width of current test value
			int valueSize = (int)mxUtils.getSizeForString(value, getFont().getSize2D()).getWidth();
			
			if(valueSize > maxWidth)
			{
				maxWidth = valueSize;
			}
		}

		int numValues = values.size();
		
		// change height of popup
		if(numValues > 0)
		{
			String tempValue = values.get(0);
			
			// height of a single entry
			int valueSize = (int)mxUtils.getSizeForString(tempValue, getFont().getSize2D()).getHeight();

			if(valueSize*numValues < minHeight)
			{
				minHeight = valueSize*numValues + 20;
			}
		}
		
		popup.resetSize(maxWidth, minHeight);		
	}
		
	public void hidePopup()
	{
		// turn off popup 
		popup.setVisible(false);
		
		// redraw popup to make sure it is removed
		popup.revalidate();
		
		// return focus to the textArea
		requestFocusInWindow();
		
		// reset selected index
		popup.resetSelection();
	}
	
	public void setCaretToDesiredPosition()
	{
		setCaretPosition(desiredCaretPosition);
	}
	
	public void replaceWithPopupItem(String replacement)
	{
		String start = "";
		String end = "";
		
		String currentText = getCurrentText();
		int currentCaret = getCurrentCaret();
		
		String upToCaret = currentText.substring(0, currentCaret);
		String caretToEnd = currentText.substring(currentCaret);
		
		start = getStartString(upToCaret);
		end = getEndString(caretToEnd);
		
		
		// find replacement based on what type of editing is being done
    	if(isEditingPushPop(upToCaret))
    	{
    		//start = getPushPopReplacementStartString(currentText, currentCaret);
    		//end = getPushPopReplacementEndString(currentText, currentCaret);
    	}
    	else if(isEditingAction(upToCaret))
    	{
    		//start = getActionReplacementStartString(currentText, currentCaret);
    		//end = getActionReplacementEndString(currentText, currentCaret);
    	}
    	else if(isEditingGuard(upToCaret))
    	{
    		//start = getGuardReplacementStartString(currentText, currentCaret);
    		//end = getGuardReplacementEndString(currentText, currentCaret);
    		if(replacement.indexOf("(") != -1)
    		{
    			replacement = replacement.substring(0, replacement.indexOf("("));
    		}
    	}
    	else if(isEditingTriggerParameters(upToCaret))
    	{
    		//start = getMessageReplacementStartString(currentText, currentCaret);
    		//end = getMessageReplacementEndString(currentText, currentCaret);
    	}
    	else
    	{
    		//start = getTriggerReplacementStartString(currentText, currentCaret);
    		//end = getTriggerReplacementEndString(currentText, currentCaret);
    		
    		// get our mapped transition name
    		String temp = storedTransitions.get(replacement);
    		
    		if(temp != null)
    		{
    			replacement = temp;
    		}
    		
    	}

		//System.out.println(start);
		//System.out.println(end);
		
		setTextArea(start, replacement, end);
	}
	
	private void setTextArea(String localStart, String replacement, String localEnd)
	{
		String finalString = "";
		String firstPart = "";
		String lastPart = "";
		
		String originalText = getText();
		int originalCaret = getCaretPosition();
		
		// set text for viewing
		if(isMultipleTrigger(originalText))
    	{
			String upToCaret = originalText.substring(0, originalCaret);
			String caretToEnd = originalText.substring(originalCaret);
			
			int marker1 = upToCaret.lastIndexOf(";");
			int marker2 = caretToEnd.indexOf(";");
			
			// tigger | ;				marker1 = -1	marker2 = 0
			// tigger ; |				marker1 = 4 	marker2 = -1	
			// trigger;trigger | ;		marker1 = 4		marker2 = 0
			
			if(marker1 == -1 && marker2 != -1)
			{
				firstPart = "";
				lastPart = originalText.substring(originalCaret + marker2);
			}
			
			if(marker1 != -1 && marker2 == -1)
			{
				// +1 to get past ";"
				firstPart = originalText.substring(0, marker1+1);
				lastPart = "";
			}
			
			if(marker1 != -1 && marker2 != -1)
			{
				// +1 to get past ";"
				firstPart = originalText.substring(0, marker1 + 1);

				lastPart = originalText.substring(originalCaret + marker2);
			}

			finalString = firstPart + localStart + replacement + localEnd + lastPart;
			
			desiredCaretPosition = firstPart.length() + localStart.length() + replacement.length();
    	}
		else
		{
			finalString = localStart + replacement + localEnd;
			
			desiredCaretPosition = localStart.length() + replacement.length();
		}
		
		setText(finalString);
	}
	
	private void setAutocompleteVariables()
	{
		// when there is no reference, return
		if( protocolBehavior == null )
			return;
		
		// assume only one referencing service definition per protocolBehavior
		RelationalList rSdf = protocolBehavior.getReferencingServiceDefs();
		
		if(rSdf == null || rSdf.getItems() == null || rSdf.getItems().size() == 0)
		{
			return;
		}
		
		// assume first service def from root is always good
		com.u2d.generated.ServiceDef serviceDef = (com.u2d.generated.ServiceDef)rSdf.getItems().get(0);
		
		// change to jaxb
		//org.jts.jsidl.binding.ServiceDef sd = org.jts.gui.jmatterToJAXB.ServiceDef.convert(serviceDef);

		// recurse through service chain
		recurseVariables(serviceDef, "");
	}
	
	private void recurseVariables(com.u2d.generated.ServiceDef serviceDef, String inheritedName)
	{
		if(serviceDef != null)
		{
			org.jts.jsidl.binding.ProtocolBehavior pb = org.jts.gui.jmatterToJAXB.ProtocolBehavior.convert(serviceDef.getProtocolBehavior());
			
			org.jts.codegenerator.protocolBehavior.Transition.getEndStateNames(pb, storedPush);
			org.jts.codegenerator.protocolBehavior.Guard.getGuards(pb, storedGuards, CodeLines.CodeType.C_PLUS_PLUS);
			org.jts.codegenerator.protocolBehavior.Action.getActions(pb, storedActions);
			
			// input message set
			addInputSet(serviceDef, inheritedName);

			// output message set
			addOutputSet(serviceDef, inheritedName);

			// event def
			addEventDef(serviceDef, inheritedName);

			// recurse through referenced service defs to find all other protocol behaviors along inheritance chain
			//if(serviceDef.getReferences() != null && serviceDef.getReferences().getInheritsFrom() != null)
			if(serviceDef.getInheritsFrom() != null)
			{
				// find the current name of the inheritance
				//inheritedName = inheritedName + serviceDef.getReferences().getInheritsFrom().getName() + ".";
				inheritedName = inheritedName + serviceDef.getInheritsFromName().stringValue() + ".";
				
				// query the database for the reference
				com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
				Session hibernateSession = persistenceMechanism.getSession();

				String id = serviceDef.getInheritsFrom().getServiceId().stringValue();
				String version = serviceDef.getInheritsFrom().get_version().toString();
	
				com.u2d.generated.ServiceDef genServiceDef = org.jts.gui.JAXBtoJmatter.ServiceDef.lookupServiceDef(id, version);

				if(genServiceDef != null)
				{
					//org.jts.jsidl.binding.ServiceDef sd = org.jts.gui.jmatterToJAXB.ServiceDef.convert(genServiceDef);
					
					recurseVariables(genServiceDef, inheritedName);
				}
			}
		}
	}
	
	private void addInputSet(com.u2d.generated.ServiceDef serviceDef, String inheritedName)
	{
		if(serviceDef.getInputSet() != null)
		{					
			com.u2d.generated.InputSet input = serviceDef.getInputSet();
			
			if(input.getMessageDefs() != null && input.getMessageDefs().getItems() != null)
			{
				for(Object obj:input.getMessageDefs().getItems())
				{
					if(obj instanceof com.u2d.generated.MessageDef)
					{
						addMessageDef((com.u2d.generated.MessageDef)obj, inheritedName, serviceDef.getName().stringValue());
					}
				}
			}
		}
	}
	
	private void addOutputSet(com.u2d.generated.ServiceDef serviceDef, String inheritedName)
	{
		if(serviceDef.getOutputSet() != null)
		{					
			com.u2d.generated.OutputSet output = serviceDef.getOutputSet();
			
			if(output.getMessageDefs() != null && output.getMessageDefs().getItems() != null)
			{
				for(Object obj:output.getMessageDefs().getItems())
				{
					if(obj instanceof com.u2d.generated.MessageDef)
					{
						addMessageDef((com.u2d.generated.MessageDef)obj, inheritedName, serviceDef.getName().stringValue());
					}
				}
			}
		}
	}
	
	private void addMessageDef(com.u2d.generated.MessageDef msgDef, String inheritedName, String currentServiceName)
	{
		String messageName = msgDef.getName().toString();
		String transitionName = inheritedName + messageName;
		
		// store transition name for trigger completion
		// we want to abbreviate the full name when showing it in the popup	
		String[] parts = transitionName.split("\\.");
		
		// last is the messageName
		String abbreviation = parts[parts.length-1];
		
		// second to last is the abbreviation if there is any
		if(parts.length > 1)
		{
			abbreviation = parts[parts.length-2] + "." + abbreviation; 
		}
		
		if(inheritedName.compareTo("") == 0)
		{
			// add current service name to transition when we are in top service
			storedTransitions.put(currentServiceName + "." + transitionName, transitionName);
		}
		else
		{
			// store abbreviation mapped to its full name
			storedTransitions.put(abbreviation, transitionName);
		}
		
		// store messageName for all message list
		allMessages.add(messageName);

		HashHolder temp = new HashHolder();
		
		checkSubJausMessage(temp, msgDef, messageName);
		
		if(temp.size() > 0)
		{
			triggerParameters.put(transitionName, temp);
		}
	}

	private void addEventDef(com.u2d.generated.ServiceDef serviceDef, String inheritedName)
	{
		if(serviceDef.getEventDefs() != null && serviceDef.getEventDefs().getItems() != null)
		{
			for(Object obj:serviceDef.getEventDefs().getItems())
			{
				if(obj instanceof com.u2d.generated.EventDef)
				{
					com.u2d.generated.EventDef eventDef = (com.u2d.generated.EventDef) obj;
					
					String eventName = eventDef.getName().stringValue();
					String transitionName = inheritedName + eventName;
					
					// store transition name for trigger completion
					// we want to abbreviate the full name when showing it in the popup	
					String[] parts = transitionName.split("\\.");
					
					// last is the messageName
					String abbreviation = parts[parts.length-1];
					
					// second to last is the abbreviation if there is any
					if(parts.length > 1)
					{
						abbreviation = parts[parts.length-2] + "." + abbreviation; 
					}
					
					if(inheritedName.compareTo("") == 0)
					{
						// add current service name to transition when we are in top service
						storedTransitions.put(serviceDef.getName().stringValue() + "." + transitionName, transitionName);
					}
					else
					{
						// store abbreviation mapped to its full name
						storedTransitions.put(abbreviation, transitionName);
					}
					
					// store messageName for all message list
					allMessages.add(eventName);
					
					HashHolder temp = new HashHolder();
					
					checkSubJausMessage(temp, eventDef, eventName);
					
					if(temp.size() > 0)
					{
						triggerParameters.put(transitionName, temp);
					}
				}
			}
		}
	}
	
	private void checkSubJausMessage(HashHolder current, Object obj, String messageName)
	{
		if(obj != null)
		{
			if(obj instanceof com.u2d.generated.MessageDef)
			{
				com.u2d.generated.MessageDef msgDef = (com.u2d.generated.MessageDef)obj;

				if(msgDef.getHeader() != null && msgDef.getHeader().getComplexField() != null)
				{
				     // Record | List | Variant | Sequence
				     com.u2d.generated.ComplexField complexField = msgDef.getHeader().getComplexField();
				     
				     // if proxy is being used, get the impl behind the proxy
				     if (complexField instanceof HibernateProxy) 
				 	   complexField = (com.u2d.generated.ComplexField)(
				 	                  (HibernateProxy)complexField).getHibernateLazyInitializer().getImplementation();
				     
				     if(complexField instanceof com.u2d.generated.Record)
				     {
						com.u2d.generated.Record record = (com.u2d.generated.Record)complexField;
						
						current.add(messageName + "." + 
									msgDef.getHeader().getName().stringValue() + "." + 
									record.getName().stringValue());
						checkRecordSubJausMessage(current, record);
				     }
				}
				
				if(msgDef.getBody() != null && msgDef.getBody().getComplexField() != null)
				{
				     // Record | List | Variant | Sequence
				     com.u2d.generated.ComplexField complexField = msgDef.getBody().getComplexField();
				     
				     // if proxy is being used, get the impl behind the proxy
				     if (complexField instanceof HibernateProxy) 
				 	   complexField = (com.u2d.generated.ComplexField)(
				 	                  (HibernateProxy)complexField).getHibernateLazyInitializer().getImplementation();
				     
				     if(complexField instanceof com.u2d.generated.Record)
				     {
						com.u2d.generated.Record record = (com.u2d.generated.Record)complexField;
						
						current.add(messageName +  "." + 
									msgDef.getBody().getName().stringValue() + "." + 
									record.getName().stringValue());
						checkRecordSubJausMessage(current, record);
				     }
				}
				
				if(msgDef.getFooter() != null && msgDef.getFooter().getComplexField() != null)
				{
				     // Record | List | Variant | Sequence
				     com.u2d.generated.ComplexField complexField = msgDef.getFooter().getComplexField();
				     
				     // if proxy is being used, get the impl behind the proxy
				     if (complexField instanceof HibernateProxy) 
				 	   complexField = (com.u2d.generated.ComplexField)(
				 	                  (HibernateProxy)complexField).getHibernateLazyInitializer().getImplementation();
				     
				     if(complexField instanceof com.u2d.generated.Record)
				     {
						com.u2d.generated.Record record = (com.u2d.generated.Record)complexField;
						
						current.add(messageName + "." + 
									msgDef.getFooter().getName().stringValue() + "." + 
									record.getName().stringValue());
						checkRecordSubJausMessage(current, record);
				     }
				}
			}
			else if(obj instanceof com.u2d.generated.EventDef)
			{
				com.u2d.generated.EventDef eventDef = (com.u2d.generated.EventDef)obj;
				
				if(eventDef.getHeader() != null && eventDef.getHeader().getComplexField() != null)
				{
				     // Record | List | Variant | Sequence
				     com.u2d.generated.ComplexField complexField = eventDef.getHeader().getComplexField();
				     
				     // if proxy is being used, get the impl behind the proxy
				     if (complexField instanceof HibernateProxy) 
				 	   complexField = (com.u2d.generated.ComplexField)(
				 	                  (HibernateProxy)complexField).getHibernateLazyInitializer().getImplementation();
				     
				     if(complexField instanceof com.u2d.generated.Record)
				     {
						com.u2d.generated.Record record = (com.u2d.generated.Record)complexField;
						
						current.add(messageName + "." + 
									eventDef.getHeader().getName().stringValue() + "." + 
									record.getName().stringValue());
						checkRecordSubJausMessage(current, record);
				     }
				}
				
				if(eventDef.getBody() != null && eventDef.getBody().getComplexField() != null)
				{
				     // Record | List | Variant | Sequence
				     com.u2d.generated.ComplexField complexField = eventDef.getBody().getComplexField();
				     
				     // if proxy is being used, get the impl behind the proxy
				     if (complexField instanceof HibernateProxy) 
				 	   complexField = (com.u2d.generated.ComplexField)(
				 	                  (HibernateProxy)complexField).getHibernateLazyInitializer().getImplementation();
				     
				     if(complexField instanceof com.u2d.generated.Record)
				     {
						com.u2d.generated.Record record = (com.u2d.generated.Record)complexField;
						
						current.add(messageName + "." + 
									eventDef.getBody().getName().stringValue() + "." + 
									record.getName().stringValue());
						checkRecordSubJausMessage(current, record);
				     }
				}
				
				if(eventDef.getFooter() != null && eventDef.getFooter().getComplexField() != null)
				{
				     // Record | List | Variant | Sequence
				     com.u2d.generated.ComplexField complexField = eventDef.getFooter().getComplexField();
				     
				     // if proxy is being used, get the impl behind the proxy
				     if (complexField instanceof HibernateProxy) 
				 	   complexField = (com.u2d.generated.ComplexField)(
				 	                  (HibernateProxy)complexField).getHibernateLazyInitializer().getImplementation();
				     
				     if(complexField instanceof com.u2d.generated.Record)
				     {
						com.u2d.generated.Record record = (com.u2d.generated.Record)complexField;
						
						current.add(messageName + "." + 
									eventDef.getFooter().getName().stringValue() + "." + 
									record.getName().stringValue());
						checkRecordSubJausMessage(current, record);
				     }
				}
			}
		}
	}
	
	private void checkRecordSubJausMessage(HashHolder current, com.u2d.generated.Record record)
	{
		if(record != null && record.getSimpleFields() != null && record.getSimpleFields().getItems() != null)
		{
			for(Object obj:record.getSimpleFields().getItems())
			{
				if (obj instanceof HibernateProxy) 
				{
					obj = (com.u2d.generated.SimpleField)( (HibernateProxy)obj ).getHibernateLazyInitializer().getImplementation();
				}
				
				if(obj instanceof com.u2d.generated.VariableLengthField)
				{
					com.u2d.generated.VariableLengthField field = (com.u2d.generated.VariableLengthField) obj;
					
					if(field.getFieldFormat().toString().compareTo("JAUS MESSAGE") == 0)
					{
						current.setNeedsAllMessages(true);
					}
				}
			}
		}
	}
	
	private String getCurrentTrigger(String currentText, int currentCaret)
	{
		String upToCaret = currentText.substring(0, currentCaret);
		String caretToEnd = currentText.substring(currentCaret);
		
		int marker1 = upToCaret.lastIndexOf(";");
		int marker2 = caretToEnd.indexOf(";");
		
		// tigger | ;				marker1 = -1	marker2 = 0
		// tigger ; |				marker1 = 4 	marker2 = -1	
		// trigger;trigger | ;		marker1 = 4		marker2 = 0
		
		if(marker1 == -1 && marker2 != -1)
		{
			currentText = currentText.substring(0, currentCaret + marker2);
		}
		
		if(marker1 != -1 && marker2 == -1)
		{
			// +1 to get past ";"
			currentText = currentText.substring(marker1+1);
		}
		
		if(marker1 != -1 && marker2 != -1)
		{
			// +1 to get past ";"
			currentText = currentText.substring(marker1 + 1, currentCaret + marker2);
		}
		
		//System.out.println("current   " + currentText);
		
		return currentText;
	}
	
	private int getCurrentCaret(String currentText, int currentCaret)
	{
		String upToCaret = currentText.substring(0, currentCaret);
		String caretToEnd = currentText.substring(currentCaret);
		
		int marker1 = upToCaret.lastIndexOf(";");
		int marker2 = caretToEnd.indexOf(";");
		
		// tigger | ;				marker1 = -1	marker2 = 0
		// tigger ; |				marker1 = 4 	marker2 = -1	
		// trigger;trigger | ;		marker1 = 4		marker2 = 0
		
		if(marker1 == -1 && marker2 != -1)
		{
			//currentCaret = currentCaret;
		}
		
		if(marker1 != -1 && marker2 == -1)
		{
			// -1 to get rid of ";"
			currentCaret = currentCaret - marker1 - 1;
		}
		
		if(marker1 != -1 && marker2 != -1)
		{
			// -1 to get rid of ";"
			currentCaret = currentCaret - marker1 - 1;
		}
		
		//System.out.println(currentCaret);
		
		return currentCaret;
	}
	
	private ArrayList<String> getReplacemets(String comparing, Object obj)
	{
		ArrayList<String> values = new ArrayList<String>();
		String toCompare = comparing;

		for(int i = comparing.length()-1; i >= 0; i--)
		{
			String c = Character.toString(comparing.charAt(i));
			if(c.matches("[^a-zA-Z0-9.]"))
			{
				toCompare = comparing.substring(i+1);
				break;
			}
		}

		// when no popup mapping exists
		if(obj instanceof ArrayList<?>)
		{
			ArrayList<String> stored = (ArrayList<String>)obj;
			
			// display all in list when nothing compare
			if(toCompare.compareTo("") == 0)
			{
				values.addAll(stored);
			}
			else
			{		
				// go through and try to match guards based on names
		    	for(String item:stored)
		    	{	
		    		// compare item with current string
		    		if(item.startsWith(toCompare))
		    		{
		    			values.add(item);
		    		}
		    	}
			}
		}
		// when displayed popup values are mapped to other values
		// abbreviation -> keys // full name -> values
		else if(obj instanceof HashMap<?, ?>)
		{
			HashMap<String, String> map = (HashMap<String, String>)obj;
			
			// display all in list when nothing compare
			if(toCompare.compareTo("") == 0)
			{
				values.addAll(map.keySet());
			}
			else
			{		
				// go through and try to match guards based on names
		    	for(String item:map.keySet())
		    	{	
		    		// compare item with current string
		    		if(item.startsWith(toCompare))
		    		{
		    			values.add(item);
		    		}
		    	}
			}			
		}
		
		return values;
	}

	private boolean isMultipleTrigger(String text)
	{
		int index = text.indexOf(";");
		
		return (index != -1);
	}
	
	private boolean isEditingPushPop(String upToCaret)
	{
		int index = upToCaret.indexOf("{");
		
		return (index != -1);
	}
	
	private boolean isDoneEditingPushPop(String upToCaret)
	{
    	int pushStart = upToCaret.indexOf("{");
    	String push = upToCaret.substring(pushStart);
    	int pushEnd = push.lastIndexOf("}");

    	if(pushStart == -1)
    	{
    		return false;
    	}
    	else if(pushEnd == -1)
    	{
    		return false;
    	}
    	
    	return true;
	}
	
	private boolean isEditingAction(String upToCaret)
	{
    	int index = upToCaret.indexOf("/");

    	return (index != -1);
	}
	
	private boolean isEditingArguments(String upToCaret)
	{
		//int actionStart = upToCaret.indexOf("/");
		//String action = upToCaret.substring(actionStart);
		
		int argumentStart = upToCaret.lastIndexOf("(");
		int argumentEnd = upToCaret.lastIndexOf(")");
		
		if(argumentStart == -1)
		{		
			return false;
		}
		else if(argumentEnd > argumentStart)
		{
			return false;
		}
		
		return true;
	}
	
	private boolean isEditingGuard(String upToCaret)
	{
    	int index = upToCaret.indexOf("[");

    	return (index != -1);
	}
	
	private boolean isDoneEditingGuard(String upToCaret)
	{
    	int guardStart = upToCaret.indexOf("[");
    	String guard = upToCaret.substring(guardStart);
    	int guardEnd = guard.lastIndexOf("]");

    	if(guardStart == -1)
    	{
    		return false;
    	}
    	else if(guardEnd == -1)
    	{
    		return false;
    	}
    	
    	return true;
	}
	
	private boolean isEditingGuardArguments(String upToCaret)
	{
		int guardStart = upToCaret.indexOf("[");
		
		String guard = upToCaret.substring(guardStart);
		int argumentStart = guard.lastIndexOf("(");
		int argumentEnd = guard.lastIndexOf(")");
		
		if(guardStart == -1)
		{
			return false;
		}
		else if(argumentStart == -1)
		{		
			return false;
		}
		else if(argumentEnd > argumentStart)
		{
			return false;
		}
		
		return true;
	}
	
	private boolean isEditingTriggerParameters(String upToCaret)
	{
    	int index = upToCaret.indexOf("(");

    	return (index != -1);
	}
	
	private boolean isDoneEditingTriggerParameterType(String upToCaret)
	{
    	int messageStart = upToCaret.indexOf("(");
    	
    	// need a starting bracket, otherwise we are done
    	if(messageStart == -1)
    	{
    		return true;
    	}
    	
    	String parameters = upToCaret.substring(messageStart + 1);
    	
    	// only concerned with current parameter editing
    	String part;
    	int index = parameters.lastIndexOf(",");
    	if(index != -1)
    	{
   			part = parameters.substring(index + 1);
    	}
    	else
    	{
    		part = parameters;
    	}
		
		// split at grouped alphanumeric
		String[] parameterParts = part.split("\\S+");

		// count how many of the parts are actually alphanumeric
		// more than 1 means we are editing a name and not a type
		if(parameterParts.length > 1)
		{
			return true;
		}

		int messageEnd = parameters.lastIndexOf(")");
		
    	// without an ending bracket, we are not done
    	if(messageEnd == -1)
    	{
    		return false;
    	}
    	
    	return true;
	}
	
	private boolean isDoneEditingTriggerParameterName(String upToCaret)
	{
    	int messageStart = upToCaret.indexOf("(");
    	
    	// need a starting bracket, otherwise we are done
    	if(messageStart == -1)
    	{
    		return true;
    	}
    	
    	String parameters = upToCaret.substring(messageStart + 1);
    	
		int messageEnd = parameters.lastIndexOf(")");
		
    	// without an ending bracket, we are not done
    	if(messageEnd == -1)
    	{
    		return false;
    	}
    	
    	return true;
	}
	
	/**
	 * get the current transition for the text area based on where the caret is
	 * Expected to be called from a transition section! (no guards added as to what state is being edited)
	 * @return
	 */
	private String getCurrentTransition(String currentText)
	{	
		if(currentText.replaceAll("\\s", "").indexOf("(") != -1)
		{
			return currentText.substring(0, currentText.replaceAll("\\s", "").indexOf("("));
		}

		return currentText;
	}
	
	/**
	 * gets the part of the transition to compare to the list of stored transitions
	 * @param transition
	 * @param upToCaret
	 * @return
	 */
	private String getRelevantServiceName(String currentText, String upToCaret)
	{
		String transition = getCurrentTransition(currentText);
		
		String[] parts = transition.split("\\.");
		int partsLength = parts.length;
		
		// 3 or more parts
		if(partsLength > 2)
		{
			String[] caretParts = upToCaret.split("\\."); 
			int caretPartsLength = caretParts.length;
			
			// first
			if(caretPartsLength == 1)
			{
				// upToCaret is already correct
			}
			// last
			else if(caretPartsLength == partsLength)
			{
				upToCaret = parts[partsLength - 2] + "." + caretParts[caretPartsLength - 1];
			}
			// middle
			else
			{
				upToCaret = parts[caretPartsLength - 1];
			}
		}		
		
		return upToCaret;
	}
	
	/**
	 * returns the current trigger that is being edited (between ";"s)
	 * @return
	 */
	private String getCurrentText()
	{	
		String originalText = getText();
		int originalCaret = getCaretPosition();
		
		// first see if there are multiple transitions
		if(isMultipleTrigger(originalText))
    	{
    		return getCurrentTrigger(originalText, originalCaret);
    	}
		
		return originalText;
	}
	
	/**
	 * returns the current offset position of the caret within the current trigger (after the last ";" in the string)
	 * @return
	 */
	private int getCurrentCaret()
	{		
		String originalText = getText();
		int originalCaret = getCaretPosition();
		
		// first see if there are multiple transitions
		if(isMultipleTrigger(originalText))
    	{
    		return getCurrentCaret(originalText, originalCaret);
    	}
		
		return originalCaret;
	}
	
	/**
	 * gets the parameters for the transition of the trigger that is currently being edited
	 * @return
	 */
	private ArrayList<String> getCurrentTransitionArguments()
	{
		ArrayList<String> arguments = new ArrayList<String>();
		String currentText = getCurrentText();
		
		int start = currentText.indexOf("(");
		int end = currentText.indexOf(")");
		
		if(start == -1 || end == -1)
		{
			return arguments;
		}
		
		int guard = currentText.indexOf("[");
		int action = currentText.indexOf("/");
		int pop = currentText.indexOf("{");
		
		if(guard != -1 && start > guard)
		{
			return arguments;
		}
		if(action != -1 && start > action)
		{
			return arguments;
		}
		if(pop != -1 && start > pop)
		{
			return arguments;
		}
		
		if(guard != -1 && end > guard)
		{
			return arguments;
		}
		if(action != -1 && end > action)
		{
			return arguments;
		}
		if(pop != -1 && end > pop)
		{
			return arguments;
		}
		
		String parameters = currentText.substring(start+1, end);
		
		String parameterList[] = parameters.split("[^a-zA-Z0-9.]");
		
		int count = 1;
		for(String parameter:parameterList)
		{
			if(parameter.compareTo("") != 0)
			{
				// parameters will be "type name", we only want the "name"
				if(count%2 == 0)
				{
					arguments.add(parameter);
				}
				count++;
			}
		}
		
		return arguments;
	}
	
	/**
	 * gets the appropriate parameters depending on what the current trigger is
	 * @return
	 */
	private ArrayList<String> getCurrentTransitionParameters()
	{
		ArrayList<String> parameters = new ArrayList<String>();
		String currentText = getCurrentText();
		
		int end = currentText.indexOf("(");
		
		if(end == -1)
		{
			return parameters;
		}
		
		String trigger = currentText.substring(0, end).replaceAll("\\s", "");
		
		HashHolder temp = triggerParameters.get(trigger);
		
		if(temp != null)
		{
			parameters.addAll(temp.getValues());
			
			if(temp.getNeedsAllMessages())
			{
				parameters.addAll(allMessages);
			}
		}
		
		return parameters;
		
	}
	
	private String getStartString(String upToCaret)
	{
		for(int i = upToCaret.length()-1; i >= 0; i--)
		{
			String c = Character.toString(upToCaret.charAt(i));
			if(c.matches("[^a-zA-Z0-9.]"))
			{
				return upToCaret.substring(0, i+1);
			}
		}
		
		// no start to return if we reached the start of the string
		return "";
	}
	
	private String getEndString(String caretToEnd)
	{
		for(int i = 0; i < caretToEnd.length(); i++)
		{
			String c = Character.toString(caretToEnd.charAt(i));
			if(c.matches("[^a-zA-Z0-9.]"))
			{
				return caretToEnd.substring(i);
			}
		}
		
		// no end to return if we reached the end of the string
		return "";
	}

	// we will use a new class to hold the hash definitions so that we can add all the
	// message defs when the subelement of a record is a jaus message so we don't have to 
	// query and loop through every service definition in the inheritance chain twice
	// (we will mark whether the hash needs all of the message for the key)
	private class HashHolder
	{
		private boolean needsAllMessages;
		private ArrayList<String> values;
		
		HashHolder()
		{
			values = new ArrayList<String>();
			needsAllMessages = false;
		}
		
		public void add(String str)
		{
			values.add(str);
		}
		
		public void setNeedsAllMessages(boolean value)
		{
			needsAllMessages = value;
		}
		
		public boolean getNeedsAllMessages()
		{
			return needsAllMessages;
		}
		
		public int size()
		{
			return values.size();
		}
		
		public ArrayList<String> getValues()
		{
			return values;
		}
	}
	
}
