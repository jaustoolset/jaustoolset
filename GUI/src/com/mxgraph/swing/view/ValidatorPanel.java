package com.mxgraph.swing.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mxgraph.io.mxCodec;
import com.mxgraph.swing.mxGraphComponent;
import com.u2d.list.RelationalList;



public class ValidatorPanel extends JPanel {

	private JTree tree;
	private mxGraphComponent graphComponent;

	public ValidatorPanel( mxGraphComponent graphComponent )
	{
		this.setVisible(false);
		this.setPreferredSize( new Dimension(100, 100));
		this.setBackground(Color.red);
		this.setName("Validator");
		this.setLayout(new BorderLayout());

		this.addCloseButton();
		this.addErrorTable();

		this.graphComponent = graphComponent;
	}

	private void addCloseButton()
	{
		JButton button = new JButton("close");
		this.add(button, BorderLayout.NORTH);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				toggleVisisble();
			}
		}
		);
	}

	private void addErrorTable()
	{
		JScrollPane pane = new JScrollPane();
		tree = new JTree();
		tree.setPreferredSize(new Dimension(500, 500));

		pane.setViewportView(tree);

		this.add(pane, BorderLayout.CENTER);
	}

	public void toggleVisisble()
	{
		if(this.isVisible())
		this.setVisible(false);
		else
		this.setVisible(true);
	}

	public boolean validateCells( org.w3c.dom.NodeList cells, Object[] selectedCells )
	{
		int errorCount = 0;
		int warningCount = 0;

		DefaultMutableTreeNode root = (DefaultMutableTreeNode)tree.getModel().getRoot();
		root.removeAllChildren();

		if(selectedCells == null || selectedCells.length == 0)
		{
			root.setUserObject( new DefaultMutableTreeNode( "Please select objects to validate..." ) );
			tree.updateUI();
			return false;
		}
		
		List<org.jts.jsidl.binding.ServiceDef> sdefs = new ArrayList<org.jts.jsidl.binding.ServiceDef>();

		// when there is no reference
		if(graphComponent.protocolBehavior == null)
		{
			// continue if not using previously defined service defs
		}
		else
		{
			// assume only one referencing service definition per protocolBehavior
			RelationalList rSdf = graphComponent.protocolBehavior.getReferencingServiceDefs();

			if(rSdf != null && rSdf.getItems() != null && !rSdf.getItems().isEmpty())
			{
				// assume first service def from root is always good
				com.u2d.generated.ServiceDef serviceDef = (com.u2d.generated.ServiceDef)rSdf.getItems().get(0);
				org.jts.jsidl.binding.ServiceDef def = org.jts.gui.jmatterToJAXB.ServiceDef.convert(serviceDef);
	
				// add the current service def
				sdefs.add( def );
				
				// recurse through all inherited service defs
				org.jts.pbValidator.ValidatorUtils.getInheritedServiceDefList( serviceDef, sdefs );
			}
		}

		for(Object obj:selectedCells)
		{
			if(obj == null)
			continue;

			if(obj instanceof com.mxgraph.model.mxCell)
			{
				com.mxgraph.model.mxCell cell = (com.mxgraph.model.mxCell)obj;
				org.jts.pbValidator.Validator validate = new org.jts.pbValidator.Validator();

				try
				{
					validate.validate( cell );
				}
				catch( org.jts.pbValidator.ValidatorException e )
				{
					root.setUserObject( new DefaultMutableTreeNode( "Validation Unsuccessful!" ) );
					tree.updateUI();

					List< org.jts.pbValidator.ValidatorResult > displayedErrors = new ArrayList< org.jts.pbValidator.ValidatorResult >();

					// add message for user to fix
					for( org.jts.pbValidator.ValidatorResult result : e.getResults() )
					{
						if( result.isError() )
						{
							errorCount++;
						}
						else if( result.isWarning() )
						{
							warningCount++;
						}

						// only display each error once
						boolean repeatFound = false;

						for( org.jts.pbValidator.ValidatorResult repeat : displayedErrors )
						{
							repeatFound = result.same( repeat );

							if( repeatFound )
							{
								break;
							}
						}

						if( !repeatFound )
						{
							DefaultMutableTreeNode level = new DefaultMutableTreeNode( result.getType() );
							level.add( new DefaultMutableTreeNode( result.getPath() ) );
							level.add( new DefaultMutableTreeNode( result.getDescription() ) );
							root.add( level );

							// keep track of results that were displayed
							displayedErrors.add( result );
						}
					}

					// try to validate other selected cells
					continue;
				}

				Object jaxbObject = null;

				// convert each cell to jaxb
				try
				{
					jaxbObject = org.jts.gui.mxGraphtoJAXB.mxGraphToJAXBUtils.convert( cells, cell );
				}
				catch(Exception e)
				{
					// add message for user to fix

					root.setUserObject( new DefaultMutableTreeNode( "Validation Unsuccessful!" ) );
					tree.updateUI();
					DefaultMutableTreeNode level = new DefaultMutableTreeNode("Conversion Error");
					level.add( new DefaultMutableTreeNode( e.getMessage() ) );
					root.add( level );

					// try to validate other selected cells
					continue;
				}

				// try to validate the jaxb object
				try
				{
					validate.validate(sdefs, jaxbObject);
				}
				catch( org.jts.pbValidator.ValidatorException e )
				{
					root.setUserObject( new DefaultMutableTreeNode( "Validation Unsuccessful!" ) );
					tree.updateUI();

					// add message for user to fix
					for( org.jts.pbValidator.ValidatorResult result : e.getResults() )
					{
						if( result.isError() )
						{
							errorCount++;
						}
						else if( result.isWarning() )
						{
							warningCount++;
						}

						DefaultMutableTreeNode level = new DefaultMutableTreeNode( result.getType() );
						level.add( new DefaultMutableTreeNode( result.getPath() ) );
						level.add( new DefaultMutableTreeNode( result.getDescription() ) );
						root.add( level );
					}

					// try to validate other selected cells
					continue;
				}

				root.setUserObject( new DefaultMutableTreeNode( "Validation Successful!" ) );
				tree.updateUI();
			}
		}

		tree.updateUI();

		// only return false when there were errors
		// allow warnings to allow the pb to be saved
		if( errorCount > 0 )
		{
			return false;
		}

		return true;
	}
        
}
