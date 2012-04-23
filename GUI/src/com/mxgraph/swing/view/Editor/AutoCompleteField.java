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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.ArrayList;

import javax.swing.JTextField;

import com.mxgraph.util.mxUtils;


public class AutoCompleteField extends JTextField 
{
	private boolean popUpShowing;
	private FieldPopup popup;
	private int desiredCaretPosition;
	// hashmap or arraylist of strings
	private Object fieldItems;
	private String fieldTitle;
	
	enum Direction { None, Left, Right };

	public AutoCompleteField( com.u2d.generated.ProtocolBehavior pb )
	{		
		popUpShowing = false;

		popup = new FieldPopup( this );
		
		addKeyListener( getHandleKeyPressed() );
	}
	
	public boolean isPopupShowing()
	{
		return popUpShowing;
	}
	
	private KeyAdapter getHandleKeyPressed()
	{
		return new KeyAdapter()
		{
			boolean lastKeyDelete = false;
			
			// keyTyped used to filter out keys like SHIFT as being events needing to update autocomplete
			public void keyTyped( KeyEvent e )
			{
				// only show popup when popup mode is on
				if( popUpShowing )
				{
					// KeyEvent.KEY_TYPED check not used because it wasn't working for valid
					String lastLetter = "";
					
					// Special case for the delete key, don't use the interpretation of the key as the last letter
					if( !lastKeyDelete )
					{
						lastLetter += e.getKeyChar();
					}

					showPopup( getCompareString( Direction.None, lastLetter ) );
				}
			}

			// use key pressed instead of key released because
			// cell editor will consume release event 
			public void keyPressed( KeyEvent e )
			{
				// toggle popup when you press the ctrl key
				if( e.getKeyCode() == KeyEvent.VK_CONTROL )
				{
					if( popUpShowing )
					{
						popUpShowing = false;
					}
					else
					{
						popUpShowing = true;
						
						// initialize the popup as showing when switching states
						showPopup( getCompareString( Direction.None, "" ) );
					}
				}
				else if( e.getKeyCode() == KeyEvent.VK_ESCAPE )
				{
					popUpShowing = false;
				}

				// only show popup when popup mode is on
				if( popUpShowing )
				{
					// deal with up/down after we have drawn the popup
					// control up/down when up/down is pressed
					if( e.getKeyCode() == KeyEvent.VK_UP )
					{
						popup.decreaseListIndex();
						e.consume();
					}
					else if( e.getKeyCode() == KeyEvent.VK_DOWN )
					{
						popup.increaseListIndex();
						e.consume();
					}
					else if( e.getKeyCode() == KeyEvent.VK_ENTER )
					{
						popup.selectListIndexKeyEntry();
						//e.consume();
					}
					else if( e.getKeyCode() == KeyEvent.VK_RIGHT )
					{
						popup.resetSelection();
						
						// reinitialize popup when moving in field
						// caret will actually be 1 to the left when we are moving right
						// pass along next character after checking for end of string
						showPopup( getCompareString( Direction.Right, "" ) );
					}
					else if( e.getKeyCode() == KeyEvent.VK_LEFT )
					{
						popup.resetSelection();
						
						// reinitialize popup when moving in field
						// caret will actually be 1 to the right when we are moving right
						// pass along next character after checking for end of string
						showPopup( getCompareString( Direction.Left, "" ) );
					}

					// hack for the delete key handling, keyTyped doesn't have access to the key type
					// and when the delte key is pressed, the string gets appended with a blank space
					if( e.getKeyCode() == KeyEvent.VK_DELETE )
					{
						lastKeyDelete = true;
					}
					else
					{
						lastKeyDelete = false;
					}

				}
				else
				{
					// change visibility of box
					hidePopup();
				}
			}
		};
	}
	
	public void setItems( String title, Object items )
	{
		fieldTitle = title;
		fieldItems = items;
	}
	
	public String getCompareString( Direction direction, String lastLetter )
	{		
		String currentText = getText();
		int currentCaret = getCurrentCaret();
		int offset = 0;
		
		if( direction == Direction.Left )
		{
			if( currentCaret > 0 )
			{
				offset = -1;
			}
		}
		else if( direction == Direction.Right )
		{
			if( currentCaret < currentText.length() )
			{
				offset = 1;
			}
		}
		
		// we only need to know up to caret to determine where we are in the editing process
		String upToCaret = currentText.substring(0, currentCaret + offset);
		
		// need to add the last input letter to the string already in the field
		upToCaret += lastLetter;

		return upToCaret;
	}
	
	public void showPopup( String stringToCompare )
	{
		// remove previous items from box
		popup.removeAllItems();

		// revalidate because we are removing components
		popup.revalidate();

		popup.create( fieldTitle );
		popup.addItems( getReplacemets( stringToCompare, fieldItems ) );
		//popup.addItems( fieldItems );

		// show autocomplete popup with offset from text area		
		setPopupPosition(getText(), getCaretPosition());
		
		// return focus to the textArea
		requestFocusInWindow();
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
		popupXOffset += tabCount * tabWidth;
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
		
		popUpShowing = false;
	}
	
	public void setCaretToDesiredPosition()
	{
		setCaretPosition(desiredCaretPosition);
	}
	
	public void replaceWithPopupItem( String replacement )
	{
		// trigger names will be messages.  Since some of these messages will be namespaced
		// we need to replace the text appropriately
		if( fieldTitle.equalsIgnoreCase( "Messages" ) )
		{
			// fieldItems will be a hashmap of popupname->message replacement name
    		// get our mapped transition name
    		String temp = ((HashMap<String,String>)fieldItems).get(replacement);
    		
    		if(temp != null)
    		{
    			replacement = temp;
    		}
		}

		setText( replacement );
	}

	private ArrayList< String > getReplacemets( String comparing, Object obj )
	{
		ArrayList< String > values = new ArrayList< String >();
		
		if( comparing == null || obj == null )
		{
			return values;
		}
		
		// remove backspace case(when using backspace, string will end with '\b')
		if( comparing.endsWith( "\b" ) )
		{
			comparing = comparing.substring( 0, comparing.length() - 1 );
		}

		for( int i = comparing.length()-1; i >= 0; i-- )
		{
			String c = Character.toString(comparing.charAt(i));
			if(c.matches("[^a-zA-Z0-9.]"))
			{
				comparing = comparing.substring(i+1);
				break;
			}
		}

		// when no popup mapping exists
		if( obj instanceof ArrayList<?> )
		{
			ArrayList< String > stored = ( ArrayList< String > )obj;
			
			// display all in list when nothing compare
			if( comparing.compareTo("") == 0 )
			{
				values.addAll( stored );
			}
			else
			{		
				// go through and try to match guards based on names
		    	for( String item : stored )
		    	{	
		    		// compare item with current string
		    		if( item.startsWith( comparing ) )
		    		{
		    			values.add( item );
		    		}
		    	}
			}
		}
		// when displayed popup values are mapped to other values
		// abbreviation -> keys // full name -> values
		else if( obj instanceof HashMap< ?, ? > )
		{
			HashMap< String, String > map = ( HashMap< String, String > )obj;
			
			// display all in list when nothing compare
			if( comparing.compareTo("") == 0 )
			{
				values.addAll( map.keySet() );
			}
			else
			{		
				// go through and try to match guards based on names
		    	for( String item : map.keySet() )
		    	{	
		    		// compare item with current string
		    		if( item.startsWith( comparing ) )
		    		{
		    			values.add( item );
		    		}
		    	}
			}			
		}
		
		return values;
	}

	/**
	 * returns the current offset position of the caret within the current trigger (after the last ";" in the string)
	 * @return
	 */
	private int getCurrentCaret()
	{		
		return getCaretPosition();
	}
	
}
