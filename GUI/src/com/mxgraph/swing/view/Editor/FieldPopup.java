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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TreeSet;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import com.mxgraph.util.mxUtils;

class FieldPopup extends JPopupMenu
{
	private AutoCompleteField autoComplete;
	
	private MutableList itemList;
	private MutableList header;
	private JScrollPane scrollPane;

	private int maxWidth;
	private int minHeight;
	private float fontSize;
	
	private int selectedIndex;
	
	public FieldPopup( AutoCompleteField parent )
	{
		this.autoComplete = parent;
		
		if( autoComplete != null )
		{
			this.fontSize = autoComplete.getFont().getSize2D();
		}
		
		selectedIndex = -1;
		
		setLayout(new BorderLayout());
	}
	
	public void addItem(String item)
	{		
		JTextField menuItem = new JTextField(item);
		
		// don't allow editing of text field
		menuItem.setEditable(false);
		
		// set full name as tooltip
		menuItem.setToolTipText(item);
		
		// add mouse listener to modify trigger string
		menuItem.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) 
			{
				Object source = arg0.getSource();
				
				// make sure click was on a JTextField 
				if(source instanceof JTextField)
				{
					JTextField item = (JTextField)source;
					autoComplete.replaceWithPopupItem(item.getText());
					
					// don't show popup anymore
					autoComplete.hidePopup();
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// add item to popup
		this.add(menuItem);

		// resize the popup when the current item has larger bounds than the previous max
		int currentStringWidth = (int)mxUtils.getSizeForString( item, this.fontSize ).getWidth();
		if( currentStringWidth > this.maxWidth )
		{
			this.maxWidth = currentStringWidth;
			resetSize( this.maxWidth, this.minHeight );
		}
	}
	
	public void resetSelection()
	{
		selectedIndex = -1;
	}
	
	// mutable list so we can add or remove items from the list dynamically
	class MutableList extends JList
	{
	    MutableList()
	    {
	    	super(new DefaultListModel());
	    	
	    	setBackground( new Color( 234, 236, 238 ) );
	    }
	    DefaultListModel getContents()
	    {
	    	return (DefaultListModel)getModel();
	    }
	    
	    public String getToolTipText(MouseEvent evt)
	    {
	    	int index = locationToIndex(evt.getPoint());
	    	
	    	if(index > -1)
	    	{
	    		return (String)getModel().getElementAt(index); 
	    	}
 
	    	return "";
	    }
	}
	
	 class MyCellRenderer extends JLabel implements ListCellRenderer {
	     public MyCellRenderer() {
	         setOpaque(true);
	     }
	     public Component getListCellRendererComponent(
	         JList list,
	         Object value,
	         int index,
	         boolean isSelected,
	         boolean cellHasFocus)
	     {
	         setText(value.toString());

	         return this;
	     }
	 }

	
	public void increaseListIndex()
	{
		if(itemList != null)
		{
	    	if((selectedIndex + 1) < itemList.getContents().getSize())
	    	{
	    		selectedIndex++;
	    	}
	    	
	    	itemList.setSelectedIndex(selectedIndex);
	    	itemList.ensureIndexIsVisible(selectedIndex);
		}
	}
	
	public void decreaseListIndex()
	{
		if(itemList != null)
		{
	    	if((selectedIndex - 1) >= 0)
	    	{
	    		selectedIndex--;
	    	}

	    	itemList.setSelectedIndex(selectedIndex);
	    	itemList.ensureIndexIsVisible(selectedIndex);
		}
	}
	
	public void selectListIndexKeyEntry()
	{
		if(selectedIndex >= 0)
		{
			if(itemList != null && itemList.getModel().getSize() > 0)
			{
				listItemSelected((String)itemList.getModel().getElementAt(selectedIndex));
			}
		}
		
		// don't show popup anymore
		autoComplete.hidePopup();
		
		// move caret to position after replaced text
		autoComplete.setCaretToDesiredPosition();
		
		// reset selected index
		selectedIndex = -1;
	}
	
	public void selectListIndex()
	{
		if(itemList != null && itemList.getSelectedIndex() != -1)
		{
			listItemSelected((String)itemList.getSelectedValue());
		}
		
		// don't show popup anymore
		autoComplete.hidePopup();
		
		// move caret to position after replaced text
		autoComplete.setCaretToDesiredPosition();
		
		// reset selected index
		selectedIndex = -1;
	}
	
	private void listItemSelected(String item)
	{
		if(item == null)
		{
			return;
		}
		
		autoComplete.replaceWithPopupItem(item);
	}

	public void create(String title)
	{		
		removeAllItems();
		
		JLabel headerLabel = new JLabel(" " + title + " ");
		
        java.awt.Font currentFont = getFont();
        headerLabel.setFont( currentFont.deriveFont( currentFont.getStyle() ^ Font.BOLD ) );
        headerLabel.setBackground( new Color( 207, 208, 210 ) );
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setOpaque( true );
        
        this.add(headerLabel, BorderLayout.NORTH);
		
		// create a scrolling pane to display the items in the list
		scrollPane = new JScrollPane();

		// add scrolling pane to the popup
		this.add(scrollPane);
		
		// make a new list to store the items
		itemList = new MutableList();
	}
	
	public void addItems( ArrayList<String> items )
	{
		if( items == null )
		{
			return;
		}

		// make a treeset to eliminate duplicates
		TreeSet<String> tree = new TreeSet<String>( items );
		
		// add each item from the tree to the list
		for(String item:tree)
		{
			itemList.getContents().addElement(item);
		}
		
		// add mouse listener to modify trigger string
		itemList.addMouseListener(new MouseAdapter()
		{
			public void mouseReleased(MouseEvent arg0)
			{
				Object source = arg0.getSource();

				// make sure click was on a JTextField 
				if(source instanceof MutableList)
				{
					MutableList list = (MutableList)source;
					listItemSelected((String)list.getSelectedValue());
					
					// don't show popup anymore
					autoComplete.hidePopup();
				}
			}
		});
		
		scrollPane.getViewport().add(itemList, 1);
		
		// don't allow user to stay in popup
		this.setFocusable(false);
		
		// set a default size for the popup
		//this.setPreferredSize(new Dimension(200,100));
	}
	
	public void resetSize(int width, int height)
	{
		//this.setPreferredSize(new Dimension(width, height));
		this.setSize(new Dimension(width, height));
	}
	
	public void removeAllItems()
	{
		// reset popup sizes to default whenever we remove all the items
		maxWidth = 200;
		minHeight = 100;

		this.removeAll();
	}
	
	public void showPopup(int x, int y)
	{
		if( x < 0 || y < 0 )
		{
			return;
		}

		// show the popup with relative offset from the textArea 
		this.show(autoComplete, x, y);
		
		// make sure it is visible
		this.setVisible(true);
	}

}
