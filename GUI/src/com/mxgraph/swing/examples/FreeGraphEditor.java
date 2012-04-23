package com.mxgraph.swing.examples;

import java.awt.Color;
import java.awt.Point;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

import org.w3c.dom.Document;

import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.examples.editor.EditorMenuBar;
import com.mxgraph.swing.examples.editor.EditorPalette;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

//JTS
//import com.u2d.view.View;
//import javax.swing.Icon;
import com.u2d.generated.ServiceDef;
import com.u2d.generated.ProtocolBehavior;
import com.u2d.view.ComplexEView;
import com.u2d.model.Editor;
import com.u2d.model.EObject;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeEvent;
import javax.swing.JComponent;
import com.u2d.app.Context;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import org.w3c.dom.Document;
import com.mxgraph.io.mxCodec;
import javax.xml.bind.JAXBException;
import org.jts.gui.JAXBtomxGraph.jxProtocolBehavior;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import org.hibernate.Session;
import org.hibernate.Query;
import java.awt.image.BufferedImage;
import com.mxgraph.util.mxCellRenderer;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


//import org.jts.gui.JAXBtomxGraph.CustomCharacterEscapeHandler;

public class FreeGraphEditor extends BasicGraphEditor implements ComplexEView, Editor
{
	private ProtocolBehavior jmBehavior = null;
	private com.u2d.type.atom.TextEO jmBehaviorView = null;
	private boolean editable = true;
	
	public EObject getEObject() { return jmBehavior; }
 
	// as a composite view, this particular class may not
	// necessarily be interested in binding to the model
	// and listen to changes.  to the extent that i use
	// the jmatter views for the subparts of the state,
	// they will be listening directly to the parts.
	public void detach() { }
	public void stateChanged(ChangeEvent e) { }
	public void propertyChange(PropertyChangeEvent evt) { }
	public boolean isMinimized() { return false; }
	public int transferValue()
	{
		int result = 0;
		//result += ((Editor) jmBehaviorView).transferValue();
		return result;
	}
	
	public void setEditable(boolean editable)
	{
		this.editable = true;  // always set editable to true
	}

	public boolean isEditable()
	{
		return true;  // is always editable
	}
	
	public int validateValue()
	{
		// todo: need to bypass validator
		return jmBehavior.validate();
	}

	public int saveProtocolBehavior( org.jts.jsidl.binding.ProtocolBehavior jxpb )
	{
		List fsmList = jxpb.getStateMachine();

		if( fsmList == null || fsmList.size() == 0 ) 
			return -1;
    
		// save xml
		java.io.StringWriter outStr = new java.io.StringWriter( );

		try
		{
			JAXBContext jsidlJc = JAXBContext.newInstance( "org.jts.jsidl.binding" );
			Marshaller jsidlMarshaller = jsidlJc.createMarshaller();
			jsidlMarshaller.marshal(jxpb, outStr);
		} catch(JAXBException jaxbe) { jaxbe.printStackTrace(); }
  
		jmBehaviorView.setValue( outStr.toString() );

		// save image
		jxProtocolBehavior jxConverter = new jxProtocolBehavior( jxpb );
		/*Document document = jxConverter.convert();

		mxCodec codec = new mxCodec(document);

		codec.decode(document.getDocumentElement(),
    	   graphComponent.getGraph().getModel());*/

		Color bg = null;

		BufferedImage image = mxCellRenderer
  				.createBufferedImage(graphComponent.getGraph(), null, 1, bg,
  								graphComponent.isAntiAlias(), null,
  								graphComponent.getCanvas());

		if (image != null)
		{    
			jmBehavior.getImage().setValue( new ImageIcon( image ) ); 
		}
		else
		{
			System.err.println("Error: Protocol behavior Image data could not be generated for documentation purposes");
		}

		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 
		persistenceMechanism.save(jmBehavior);

		updateAssociations();

		return 0;
	}

	/** this is required when a protocol behavior is being created for a derived service for the first time and the referencing
	service def has just been saved, but still open.
	*/
	private void updateAssociations()
	{

		com.u2d.persist.HBMSingleSession persistenceMechanism = (com.u2d.persist.HBMSingleSession) Context.getInstance().getPersistenceMechanism(); 

		Session hibernateSession = persistenceMechanism.getSession();

		// pull a fresh object of the referencing servicedef from the db
		String hql = "from ServiceDef sd where sd.serviceId=:id and sd._version=:version";

		java.util.List sdList = jmBehavior.getReferencingServiceDefs().getItems();

		for(int ii=0; ii<sdList.size(); ii++)
		{
			String id = ((ServiceDef)sdList.get(ii)).getServiceId().toString();
			String version = ((ServiceDef)sdList.get(ii)).get_version().toString();

			Query hibernateQuery = hibernateSession.createQuery(hql);
			hibernateQuery.setParameter("id", id );
			hibernateQuery.setParameter("version", version );

			com.u2d.generated.ServiceDef jmServiceDef = (com.u2d.generated.ServiceDef) hibernateQuery.uniqueResult();

			if( jmServiceDef != null )
			{
				jmServiceDef.setProtocolBehavior( jmBehavior );
				persistenceMechanism.save(jmServiceDef);
			}
		}
	}

	//jts

	/**
	 * Holds the shared number formatter.
	 * 
	 * @see NumberFormat#getInstance()
	 */
	public static final NumberFormat numberFormat = NumberFormat.getInstance();

	/**
	 * Holds the URL for the icon to be used as a handle for creating new
	 * connections. This is currently unused.
	 */
	public static URL url = null;

	//FreeGraphEditor.class.getResource("/com/mxgraph/swing/examples/images/connector.gif");
	//JTS     
       public FreeGraphEditor(ProtocolBehavior jmBehavior)
	{
        this();	
        this.jmBehavior = jmBehavior;
        jmBehaviorView =  jmBehavior.getProtocolBehavior();
               
        // create base protocol behavior shell if required
        
        /* multiple serviceDefs can reference the same protocol behavior. Since it is safe to assume that all serviceDefs
        that reference this protocol behavior will inherit from the same base service, we can simply use the first service def */
        ServiceDef referencingServiceDef = null;
        
        //referencingServiceDef = (ServiceDef)jmBehavior.getReferencingServiceDefs().getItems().get(0);
        if( jmBehavior != null && jmBehavior.getReferencingServiceDefs() != null && jmBehavior.getReferencingServiceDefs().getItems().size() > 0 )
        {
        	referencingServiceDef = (ServiceDef)jmBehavior.getReferencingServiceDefs().getItems().get(0);
        }
        
        
        if( referencingServiceDef != null && referencingServiceDef.getInheritsFrom() != null && jmBehavior.getProtocolBehavior().isEmpty() ) {        
          // check name dependency
          try {
            org.jts.validator.Validator.validateName( referencingServiceDef.getName().stringValue() );
          } catch( org.jts.validator.ValidatorException ve ) {            
            return;
          } 

          // clear the exception message (if any)
          referencingServiceDef.fireValidationException( "" );  

          /* tbd: since multiple servicedefs can access the same behavior, need to add referencing servicedef list to the view and 
             update it each time the behavior is opened in the view. 
          */
          org.jts.jsidl.binding.ProtocolBehavior baseShell = 
        	  org.jts.gui.Inheritance.getBaseBehaviorShellAsJaxb( referencingServiceDef.getInheritsFrom().getProtocolBehavior() );
          
          // NOTE: JTS pb nameing CONVENTION will need to have the current sdef name prepended to the sm names
          org.jts.gui.Inheritance.prependServiceName( referencingServiceDef.getName().toString(), baseShell );

          String baseShellString =  org.jts.gui.Inheritance.getAsString( baseShell );

          jmBehavior.getProtocolBehavior().setValue( baseShellString );
        }
        
        // read value and populate view here
        try
        {
           String xmlStr =  jmBehaviorView.stringValue();
           
           if( xmlStr.length() == 0 )
             return;

           JAXBContext jc = JAXBContext.newInstance( "org.jts.jsidl.binding" );
           Unmarshaller u = jc.createUnmarshaller();

           org.jts.jsidl.binding.ProtocolBehavior jxBehavior = 
             (org.jts.jsidl.binding.ProtocolBehavior)u.unmarshal( 
                 new StreamSource( new StringReader( xmlStr ) ) );

           jxProtocolBehavior jxConverter = new jxProtocolBehavior( jxBehavior );
           Document document = jxConverter.convert();
           
           mxCodec codec = new mxCodec(document);
        	codec.decode(document.getDocumentElement(),
        		graphComponent.getGraph().getModel());
        
           setModified(false);
           jmBehavior.title();
           
        } catch (JAXBException jaxbe)
        {
           jaxbe.printStackTrace();
        }
        
        // set accessible reference for trigger auto completion
        graphComponent.protocolBehavior = jmBehavior;
      }
    //jts

	/**
	 * 
	 */
	public FreeGraphEditor()
	{
		super("JTS FSM Editor", new CustomGraphComponent(new CustomGraph()));
		final mxGraph graph = graphComponent.getGraph();

        //JTS
        graph.setAllowLoops(true);
        //jts

		// Creates the shapes palette
		EditorPalette shapesPalette = insertPalette("Behavior Elements");

		// Sets the edge template to be used for creating new edges if an edge
		// is clicked in the shape palette
		shapesPalette.addListener(EditorPalette.EVENT_SELECT,
				new mxIEventListener()
				{
					public void invoke(Object sender, Object[] args)
					{
						if (args != null && args.length > 0
								&& args[1] instanceof mxGraphTransferable)
						{
							mxGraphTransferable t = (mxGraphTransferable) args[1];
							Object cell = t.getCells()[0];

							if (graph.getModel().isEdge(cell))
							{
								((CustomGraph) graph).setEdgeTemplate(cell);
							}
						}
					}

				});

		// Adds some template cells for dropping into the graph
        // JTS
		shapesPalette
		.addTemplate(
				"Finite State Machine",
				new ImageIcon(
						FreeGraphEditor.class
								.getResource("/com/mxgraph/swing/examples/images/finite_state_machine.png")),
				"finiteStateMachine", 160, 120, "");
		
		shapesPalette
				.addTemplate(
						"Pseudo Start State",
						new ImageIcon(
								FreeGraphEditor.class
										.getResource("/com/mxgraph/swing/examples/images/pseudo_start_state.png")),
						"pseudoStartState", 20, 20, "");

        shapesPalette
				.addTemplate(
						"State",
						new ImageIcon(
								FreeGraphEditor.class
										.getResource("/com/mxgraph/swing/examples/images/state.png")),
						"state", 160, 120, "");

        shapesPalette
				.addTemplate(
						"Default State",
						new ImageIcon(
								FreeGraphEditor.class
										.getResource("/com/mxgraph/swing/examples/images/default_state.png")),
						"defaultState", 160, 120, "default_state");

        shapesPalette
                .addEdgeTemplate(
                                "Internal Transition",
                                new ImageIcon(
                                        FreeGraphEditor.class
                                                .getResource("/com/mxgraph/swing/examples/images/internal_transition.png")),
                                "internalTransition", 100, 100, "");

        shapesPalette
                .addEdgeTemplate(
                                "Simple Transition",
                                new ImageIcon(
                                        FreeGraphEditor.class
                                                .getResource("/com/mxgraph/swing/examples/images/simple_transition.png")),
                                "simpleTransition", 100, 100, "");



        shapesPalette
				.addEdgeTemplate(
						"Push Transition",
						new ImageIcon(
								FreeGraphEditor.class
										.getResource("/com/mxgraph/swing/examples/images/push_transition.png")),
						"pushTransition", 100, 100, "");

        shapesPalette
				.addEdgeTemplate(
						"Pop Transition",
						new ImageIcon(
								FreeGraphEditor.class
										.getResource("/com/mxgraph/swing/examples/images/pop_transition.png")),
						"popTransition", 100, 100, "");

        shapesPalette
				.addEdgeTemplate(
						"Default Internal Transition",
						new ImageIcon(
								FreeGraphEditor.class
										.getResource("/com/mxgraph/swing/examples/images/default_internal_transition.png")),
						"defaultInternalTransition", 100, 100, "");
						
        shapesPalette
				.addEdgeTemplate(
						"Default Simple Transition",
						new ImageIcon(
								FreeGraphEditor.class
										.getResource("/com/mxgraph/swing/examples/images/default_simple_transition.png")),
						"defaultSimpleTransition", 100, 100, "");

        shapesPalette
				.addEdgeTemplate(
						"Default Push Transition",
						new ImageIcon(
								FreeGraphEditor.class
										.getResource("/com/mxgraph/swing/examples/images/default_push_transition.png")),
						"defaultPushTransition", 100, 100, "");

        shapesPalette
				.addEdgeTemplate(
						"Default Pop Transition",
						new ImageIcon(
								FreeGraphEditor.class
										.getResource("/com/mxgraph/swing/examples/images/default_pop_transition.png")),
						"defaultPopTransition", 100, 100, "");
         // jts
	}

	/**
	 * 
	 */
	public static class CustomGraphComponent extends mxGraphComponent
	{

		/**
		 * 
		 * @param graph
		 */
		public CustomGraphComponent(mxGraph graph)
		{
			super(graph);

			// Sets switches typically used in an editor
			setPageVisible(true);
			setGridVisible(true);
			setAntiAlias(false);
			setToolTips(true);
			getConnectionHandler().setCreateTarget(true);

			// Loads the defalt stylesheet from an external file
			mxCodec codec = new mxCodec();
			Document doc = mxUtils
					.loadDocument(FreeGraphEditor.class
							.getResource(
									"/com/mxgraph/swing/examples/resources/default-style.xml")
							.toString());
			codec.decode(doc.getDocumentElement(), graph.getStylesheet());

			// Sets the background to white
			getViewport().setOpaque(false);
			setBackground(Color.WHITE);
		}

		/**
		 * Overrides drop behaviour to set the cell style if the target
		 * is not a valid drop target and the cells are of the same
		 * type (eg. both vertices or both edges). 
		 */
		public Object[] importCells(Object[] cells, double dx, double dy,
				Object target, Point location)
		{
			if (target == null && cells.length == 1 && location != null)
			{
				target = getCellAt(location.x, location.y);

				if (target instanceof mxICell && cells[0] instanceof mxICell)
				{
					mxICell targetCell = (mxICell) target;
					mxICell dropCell = (mxICell) cells[0];

					if (targetCell.isVertex() == dropCell.isVertex()
							|| targetCell.isEdge() == dropCell.isEdge())
					{
						mxIGraphModel model = graph.getModel();
						model.setStyle(target, model.getStyle(cells[0]));
						graph.setSelectionCell(target);

						return null;
					}
				}
			}

			return super.importCells(cells, dx, dy, target, location);
		}

	}

	/**
	 * A graph that creates new edges from a given template edge.
	 */
	public static class CustomGraph extends mxGraph
	{
		/**
		 * Holds the edge to be used as a template for inserting new edges.
		 */
		protected Object edgeTemplate;

		/**
		 * Custom graph that defines the alternate edge style to be used when
		 * the middle control point of edges is double clicked (flipped).
		 */
		public CustomGraph()
		{
			setAlternateEdgeStyle("edgeStyle=mxEdgeStyle.ElbowConnector;elbow=vertical");
		}

		/**
		 * Sets the edge template to be used to inserting edges.
		 */
		public void setEdgeTemplate(Object template)
		{
			edgeTemplate = template;
		}

		/**
		 * Prints out some useful information about the cell in the tooltip.
		 */
		public String getToolTipForCell(Object cell)
		{
		  /* JTS */
		  if( cell != null )
		    return ((mxCell)cell).getToolTip();
		  else 
		    return "no tool tip available for this entity...";
		  /* jts */
		  
			/*String tip = "<html>";
			
			if (getModel().isEdge(cell))
			{
				tip += "points={";

				if (geo != null)
				{
					List points = geo.getPoints();

					if (points != null)
					{
						Iterator it = points.iterator();

						while (it.hasNext())
						{
							mxPoint point = (mxPoint) it.next();
							tip += "[x=" + numberFormat.format(point.getX())
									+ ",y=" + numberFormat.format(point.getY())
									+ "],";
						}

						tip = tip.substring(0, tip.length() - 1);
					}
				}

				tip += "}<br>";
				tip += "absPoints={";

				if (state != null)
				{

					for (int i = 0; i < state.getAbsolutePointCount(); i++)
					{
						mxPoint point = state.getAbsolutePoint(i);
						tip += "[x=" + numberFormat.format(point.getX())
								+ ",y=" + numberFormat.format(point.getY())
								+ "],";
					}

					tip = tip.substring(0, tip.length() - 1);
				}

				tip += "}";
			}
			else
			{
				tip += "geo=[";

				if (geo != null)
				{
					tip += "x=" + numberFormat.format(geo.getX()) + ",y="
							+ numberFormat.format(geo.getY()) + ",width="
							+ numberFormat.format(geo.getWidth()) + ",height="
							+ numberFormat.format(geo.getHeight());
				}

				tip += "]<br>";
				tip += "state=[";

				if (state != null)
				{
					tip += "x=" + numberFormat.format(state.getX()) + ",y="
							+ numberFormat.format(state.getY()) + ",width="
							+ numberFormat.format(state.getWidth())
							+ ",height="
							+ numberFormat.format(state.getHeight());
				}

				tip += "]";
			}

			mxPoint trans = getView().getTranslate();

			tip += "<br>scale=" + numberFormat.format(getView().getScale())
					+ ", translate=[x=" + numberFormat.format(trans.getX())
					+ ",y=" + numberFormat.format(trans.getY()) + "]";
			tip += "</html>";

			return tip;*/
		}

		/**
		 * Overrides the method to use the currently selected edge template for
		 * new edges.
		 * 
		 * @param graph
		 * @param parent
		 * @param id
		 * @param value
		 * @param source
		 * @param target
		 * @param style
		 * @return
		 */
		public Object createEdge(Object parent, String id, Object value,
				Object source, Object target, String style)
		{
			if (edgeTemplate != null)
			{
				mxCell edge = (mxCell) cloneCells(new Object[] { edgeTemplate })[0];
				edge.setId(id);

				return edge;
			}

			// JTS
			// add a default edge type based on source and target of edge
			com.mxgraph.model.mxCell sourceCell = ( com.mxgraph.model.mxCell )source;
			com.mxgraph.model.mxCell targetCell = ( com.mxgraph.model.mxCell )target;
			if( ( sourceCell.getStyle().startsWith( "state" ) || sourceCell.getStyle().startsWith( "defaultState" ) ) && 
					( targetCell.getStyle().startsWith( "state" ) || targetCell.getStyle().startsWith( "defaultState" ) ) )   
			{
				// make an internal transition if source and target are the same
				if( sourceCell.getId().compareTo( targetCell.getId() ) == 0 )
				{
					style = "internalTransition;";
				}
				// otherwise assume a simple transition
				else
				{
					style = "simpleTransition;";
				}				
			}
			else if( sourceCell.getStyle().startsWith( "pseudoStartState" ) && targetCell.getStyle().startsWith( "state" ) )
			{
				// pseudo start states need to be connected to a state with a simple transition
				style = "simpleTransition;";
			}
			else
			{
				// there are no other valid 'pull' connections we want to make 
				return null;
			}
			// jts

			return super.createEdge(parent, id, value, source, target, style);
		}

	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}

		mxConstants.SHADOW_COLOR = Color.LIGHT_GRAY;
		FreeGraphEditor editor = new FreeGraphEditor();
		editor.createFrame(new EditorMenuBar(editor)).setVisible(true);
	}

}
