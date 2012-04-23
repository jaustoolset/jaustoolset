/*
 * $Id: EditorActions.java,v 1.13 2009/03/09 13:56:07 gaudenz Exp $
 * Copyright (c) 2001-2005, Gaudenz Alder
 * 
 * All rights reserved.
 * 
 * See LICENSE file for license details. If you are unable to locate
 * this file please contact info (at) jgraph (dot) com.
 */
package com.mxgraph.swing.examples.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.w3c.dom.Document;

import com.mxgraph.algebra.mxDistanceCostFunction;
import com.mxgraph.algebra.mxGraphAlgebra;
import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.mxGraphOutline;
import com.mxgraph.swing.examples.BasicGraphEditor;
//JTS
import com.mxgraph.swing.examples.FreeGraphEditor;
// jts
import com.mxgraph.swing.handler.mxConnectionHandler;
import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.swing.view.mxCellEditor;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraph;

/**
 * @author Administrator
 * 
 */
public class EditorActions
{

	/**
	 * 
	 * @param e
	 * @return Returns the graph for the given action event.
	 */
	public static final BasicGraphEditor getEditor(ActionEvent e)
	{
		if (e.getSource() instanceof Component)
		{
			Component component = (Component) e.getSource();

			while (component != null
					&& !(component instanceof BasicGraphEditor))
			{
				component = component.getParent();
			}

			return (BasicGraphEditor) component;
		}

		return null;
	}
	
	// JTS
	
	/**
	 * 
	 * @param e
	 * @return Returns the graph for the given action event.
	 */
	public static final FreeGraphEditor getFreeGraphEditor(ActionEvent e)
	{
		if (e.getSource() instanceof Component)
		{
			Component component = (Component) e.getSource();

			while (component != null
					&& !(component instanceof FreeGraphEditor))
			{
				component = component.getParent();
			}

			return (FreeGraphEditor) component;
		}

		return null;
	}
	// jts

	/**
	 *
	 */
	public static class ToggleRulersItem extends JCheckBoxMenuItem
	{
		/**
		 * 
		 */
		public ToggleRulersItem(final BasicGraphEditor editor, String name)
		{
			super(name);
			setSelected(editor.getGraphComponent().getColumnHeader() != null);

			addActionListener(new ActionListener()
			{
				/**
				 * 
				 */
				public void actionPerformed(ActionEvent e)
				{
					mxGraphComponent graphComponent = editor
							.getGraphComponent();

					if (graphComponent.getColumnHeader() != null)
					{
						graphComponent.setColumnHeader(null);
						graphComponent.setRowHeader(null);
					}
					else
					{
						graphComponent.setColumnHeaderView(new EditorRuler(
								graphComponent,
								EditorRuler.ORIENTATION_HORIZONTAL));
						graphComponent.setRowHeaderView(new EditorRuler(
								graphComponent,
								EditorRuler.ORIENTATION_VERTICAL));
					}
				}
			});
		}

	}

	/**
	 *
	 */
	public static class ToggleGridItem extends JCheckBoxMenuItem
	{
		/**
		 * 
		 */
		public ToggleGridItem(final BasicGraphEditor editor, String name)
		{
			super(name);
			setSelected(true);

			addActionListener(new ActionListener()
			{
				/**
				 * 
				 */
				public void actionPerformed(ActionEvent e)
				{
					mxGraphComponent graphComponent = editor
							.getGraphComponent();
					mxGraph graph = graphComponent.getGraph();
					boolean enabled = !graph.isGridEnabled();

					graph.setGridEnabled(enabled);
					graphComponent.setGridVisible(enabled);
					graphComponent.repaint();
					setSelected(enabled);
				}
			});
		}
	}

	/**
	 *
	 */
	public static class ToggleOutlineItem extends JCheckBoxMenuItem
	{
		/**
		 * 
		 */
		public ToggleOutlineItem(final BasicGraphEditor editor, String name)
		{
			super(name);
			setSelected(true);

			addActionListener(new ActionListener()
			{
				/**
				 * 
				 */
				public void actionPerformed(ActionEvent e)
				{
					final mxGraphOutline outline = editor.getGraphOutline();
					outline.setVisible(!outline.isVisible());
					outline.revalidate();

					SwingUtilities.invokeLater(new Runnable()
					{
						/*
						 * (non-Javadoc)
						 * @see java.lang.Runnable#run()
						 */
						public void run()
						{
							if (outline.getParent() instanceof JSplitPane)
							{
								if (outline.isVisible())
								{
									((JSplitPane) outline.getParent())
											.setDividerLocation(editor
													.getHeight() - 300);
									((JSplitPane) outline.getParent())
											.setDividerSize(6);
								}
								else
								{
									((JSplitPane) outline.getParent())
											.setDividerSize(0);
								}
							}
						}
					});
				}
			});
		}
	}

	/**
	 *
	 */
	public static class ExitAction extends AbstractAction
	{

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			BasicGraphEditor editor = getEditor(e);

			if (editor != null)
			{
				editor.exit();
			}
		}

	}

	/**
	 *
	 */
	public static class StylesheetAction extends AbstractAction
	{

		/**
		 * 
		 */
		protected String stylesheet;

		/**
		 * 
		 */
		public StylesheetAction(String stylesheet)
		{
			this.stylesheet = stylesheet;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				mxGraph graph = graphComponent.getGraph();
				mxCodec codec = new mxCodec();
				Document doc = mxUtils.loadDocument(EditorActions.class
						.getResource(stylesheet).toString());

				if (doc != null)
				{
					codec.decode(doc.getDocumentElement(), graph
							.getStylesheet());
					graph.refresh();
				}
			}
		}

	}

	/**
	 *
	 */
	public static class ZoomPolicyAction extends AbstractAction
	{
		/**
		 * 
		 */
		protected int zoomPolicy;

		/**
		 * 
		 */
		public ZoomPolicyAction(int zoomPolicy)
		{
			this.zoomPolicy = zoomPolicy;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				graphComponent.setPageVisible(true);
				graphComponent.setZoomPolicy(zoomPolicy);
			}
		}

	}

	/**
	 *
	 */
	public static class GridStyleAction extends AbstractAction
	{
		/**
		 * 
		 */
		protected int style;

		/**
		 * 
		 */
		public GridStyleAction(int style)
		{
			this.style = style;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				graphComponent.setGridStyle(style);
				graphComponent.repaint();
			}
		}

	}

	/**
	 *
	 */
	public static class GridColorAction extends AbstractAction
	{

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				mxGraph graph = graphComponent.getGraph();
				Color newColor = JColorChooser.showDialog(graphComponent,
						mxResources.get("gridColor"), graphComponent
								.getGridColor());

				if (newColor != null)
				{
					graphComponent.setGridColor(newColor);
					graphComponent.repaint();
				}
			}
		}

	}

	/**
	 *
	 */
	public static class ScaleAction extends AbstractAction
	{

		/**
		 * 
		 */
		protected double scale;

		/**
		 * 
		 */
		public ScaleAction(double scale)
		{
			this.scale = scale;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				mxGraph graph = graphComponent.getGraph();
				double scale = this.scale;

				if (scale == 0)
				{
					String value = (String) JOptionPane.showInputDialog(
							graphComponent, mxResources.get("value"),
							mxResources.get("scale") + " (%)",
							JOptionPane.PLAIN_MESSAGE, null, null, "");

					if (value != null)
					{
						scale = Double.parseDouble(value.replace("%", "")) / 100;
					}
				}

				if (scale > 0)
				{
					graphComponent.zoomTo(scale, graphComponent.isCenterZoom());
				}
			}
		}

	}

	/**
	 *
	 */
	public static class PageSetupAction extends AbstractAction
	{

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				PrinterJob pj = PrinterJob.getPrinterJob();
				PageFormat format = pj.pageDialog(graphComponent
						.getPageFormat());

				if (format != null)
				{
					graphComponent.setPageFormat(format);
					graphComponent.zoomAndCenter();
				}
			}
		}

	}

	/**
	 *
	 */
	public static class PrintAction extends AbstractAction
	{

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				PrinterJob pj = PrinterJob.getPrinterJob();

				if (pj.printDialog())
				{
					PageFormat pf = pj.defaultPage();
					Paper paper = new Paper();
					double margin = 36;
					paper.setImageableArea(margin, margin, paper.getWidth()
							- margin * 2, paper.getHeight() - margin * 2);
					pf.setPaper(paper);
					pj.setPrintable(graphComponent, pf);

					try
					{
						pj.print();
					}
					catch (PrinterException e2)
					{
						System.out.println(e2);
					}
				}
			}
		}

	}

	/**
	 *
	 */
	public static class SaveAction extends AbstractAction
	{

		/**
		 * 
		 */
		protected boolean showDialog;

		/**
		 * 
		 */
		protected String lastDir = null;

		/**
		 * 
		 */
		public SaveAction(boolean showDialog)
		{
			this.showDialog = showDialog;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			BasicGraphEditor editor = getEditor(e);

			if (editor != null)
			{
				mxGraphComponent graphComponent = editor.getGraphComponent();
				mxGraph graph = graphComponent.getGraph();
				FileFilter selectedFilter = null;
				FileFilter vmlFileFilter = new DefaultFileFilter(".html",
						"VML " + mxResources.get("file") + " (.html)");
				String filename = null;

				if (showDialog || editor.getCurrentFile() == null)
				{
					String wd;

					if (lastDir != null)
					{
						wd = lastDir;
					}
					else if (editor.getCurrentFile() != null)
					{
						wd = editor.getCurrentFile().getParent();
					}
					else
					{
						wd = System.getProperty("user.dir");
					}

					JFileChooser fc = new JFileChooser(wd);

					// Adds the default file format
					FileFilter defaultFilter = new DefaultFileFilter(".mxe",
							"mxGraph Editor " + mxResources.get("file")
									+ " (.mxe)");
					fc.addChoosableFileFilter(defaultFilter);

					// Adds special vector graphics formats and HTML
					fc.addChoosableFileFilter(new DefaultFileFilter(".svg",
							"SVG " + mxResources.get("file") + " (.svg)"));
					fc.addChoosableFileFilter(vmlFileFilter);
					fc.addChoosableFileFilter(new DefaultFileFilter(".html",
							"HTML " + mxResources.get("file") + " (.html)"));

					// Adds a filter for each supported image format
					Object[] imageFormats = ImageIO.getReaderFormatNames();

					// Finds all distinct extensions
					HashSet formats = new HashSet();

					for (int i = 0; i < imageFormats.length; i++)
					{
						String ext = imageFormats[i].toString().toLowerCase();
						formats.add(ext);
					}

					imageFormats = formats.toArray();

					for (int i = 0; i < imageFormats.length; i++)
					{
						String ext = imageFormats[i].toString();
						fc.addChoosableFileFilter(new DefaultFileFilter("."
								+ ext, ext.toUpperCase() + " "
								+ mxResources.get("file") + " (." + ext + ")"));
					}

					// Adds filter that accepts all supported image formats
					fc.addChoosableFileFilter(new DefaultFileFilter.ImageFileFilter(
									mxResources.get("allImages")));
					fc.setFileFilter(defaultFilter);
					int rc = fc.showDialog(null, mxResources.get("save"));

					if (rc != JFileChooser.APPROVE_OPTION)
					{
						return;
					}
					else
					{
						lastDir = fc.getSelectedFile().getParent();
					}

					filename = fc.getSelectedFile().getAbsolutePath();
					selectedFilter = fc.getFileFilter();

					if (selectedFilter instanceof DefaultFileFilter)
					{
						String ext = ((DefaultFileFilter) selectedFilter)
								.getExtension();

						if (!filename.toLowerCase().endsWith(ext))
						{
							filename += ext;
						}
					}

					if (new File(filename).exists()
							&& JOptionPane.showConfirmDialog(graphComponent,
									mxResources.get("overwriteExistingFile")) != JOptionPane.YES_OPTION)
					{
						return;
					}
				}
				else
				{
					filename = editor.getCurrentFile().getAbsolutePath();
				}

				// must pass validation before saving
				if( !editor.callValidator() )
				{
					// signal user with popup to fix errors
					JOptionPane.showMessageDialog( graphComponent, "Protocol Behavior not saved!  Please correct errors and try again." );
					return;
				}

				try
				{
					String ext = filename
							.substring(filename.lastIndexOf('.') + 1);

					if (ext.equalsIgnoreCase("svg"))
					{
						mxUtils.writeFile(mxUtils.getXml(mxCellRenderer
								.createSvgDocument(graph, null, 1, null, null)
								.getDocumentElement()), filename);
					}
					else if (selectedFilter == vmlFileFilter)
					{
						mxUtils.writeFile(mxUtils.getXml(mxCellRenderer
								.createVmlDocument(graph, null, 1, null, null)
								.getDocumentElement()), filename);
					}
					else if (ext.equalsIgnoreCase("html"))
					{
						mxUtils.writeFile(mxUtils.getXml(mxCellRenderer
								.createHtmlDocument(graph, null, 1, null, null)
								.getDocumentElement()), filename);
					}
					else if (ext.equalsIgnoreCase("mxe")
							|| ext.equalsIgnoreCase("xml"))
					{				
						mxCodec codec = new mxCodec();
						//JTS
						org.w3c.dom.Node node = codec.encode(graph.getModel());
						String xml = mxUtils.getXml(node);
								
						FreeGraphEditor freeEditor = getFreeGraphEditor( e );
						mxUtils.writeJmatter(node, freeEditor);
						//jts
						
						//String xml = mxUtils.getXml(codec.encode(graph.getModel()));
                                    
						mxUtils.writeFile(xml, filename);
                           
						editor.setModified(false);
						editor.setCurrentFile(new File(filename));
					}
					else
					{
						Color bg = null;

						if ((!ext.equalsIgnoreCase("gif") && !ext
								.equalsIgnoreCase("png"))
								|| JOptionPane.showConfirmDialog(
										graphComponent, mxResources
												.get("transparentBackground")) != JOptionPane.YES_OPTION)
						{
							bg = graphComponent.getBackground();
						}

						BufferedImage image = mxCellRenderer
								.createBufferedImage(graph, null, 1, bg,
										graphComponent.isAntiAlias(), null,
										graphComponent.getCanvas());

						if (image != null)
						{
							ImageIO.write(image, ext, new File(filename));
						}
						else
						{
							JOptionPane.showMessageDialog(graphComponent,
									mxResources.get("noImageData"));
						}
					}
				}

				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 *
	 */
	public static class SelectShortestPathAction extends AbstractAction
	{

		/**
		 * 
		 */
		protected boolean directed;

		/**
		 * 
		 */
		public SelectShortestPathAction(boolean directed)
		{
			this.directed = directed;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				mxGraph graph = graphComponent.getGraph();
				mxIGraphModel model = graph.getModel();

				Object source = null;
				Object target = null;

				Object[] cells = graph.getSelectionCells();

				for (int i = 0; i < cells.length; i++)
				{
					if (model.isVertex(cells[i]))
					{
						if (source == null)
						{
							source = cells[i];
						}
						else if (target == null)
						{
							target = cells[i];
						}
					}

					if (source != null && target != null)
					{
						break;
					}
				}

				if (source != null && target != null)
				{
					int steps = graph.getChildEdges(graph.getDefaultParent()).length;
					Object[] path = mxGraphAlgebra.getInstance()
							.getShortestPath(graph, source, target,
									new mxDistanceCostFunction(), steps,
									directed);
					graph.setSelectionCells(path);
				}
				else
				{
					JOptionPane.showMessageDialog(graphComponent, mxResources
							.get("noSourceAndTargetSelected"));
				}
			}
		}

	}

	/**
	 *
	 */
	public static class SelectSpanningTreeAction extends AbstractAction
	{

		/**
		 * 
		 */
		protected boolean directed;

		/**
		 * 
		 */
		public SelectSpanningTreeAction(boolean directed)
		{
			this.directed = directed;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				mxGraph graph = graphComponent.getGraph();
				mxIGraphModel model = graph.getModel();

				Object parent = graph.getDefaultParent();
				Object[] cells = graph.getSelectionCells();

				for (int i = 0; i < cells.length; i++)
				{
					if (model.getChildCount(cells[i]) > 0)
					{
						parent = cells[i];
						break;
					}
				}

				Object[] v = graph.getChildVertices(parent);
				Object[] mst = mxGraphAlgebra.getInstance()
						.getMinimumSpanningTree(graph, v,
								new mxDistanceCostFunction(), directed);
				graph.setSelectionCells(mst);
			}
		}

	}

	/**
	 *
	 */
	public static class ToggleDirtyAction extends AbstractAction
	{

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				graphComponent.showDirtyRectangle = !graphComponent.showDirtyRectangle;
			}
		}

	}

	/**
	 *
	 */
	public static class ToggleImagePreviewAction extends AbstractAction
	{

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				graphComponent.getGraphHandler().setImagePreview(
						!graphComponent.getGraphHandler().isImagePreview());
			}
		}

	}

	/**
	 *
	 */
	public static class ToggleConnectModeAction extends AbstractAction
	{

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				mxConnectionHandler handler = graphComponent
						.getConnectionHandler();
				handler.setHandleEnabled(!handler.isHandleEnabled());
			}
		}

	}

	/**
	 *
	 */
	public static class ToggleCreateTargetItem extends JCheckBoxMenuItem
	{
		/**
		 * 
		 */
		public ToggleCreateTargetItem(final BasicGraphEditor editor, String name)
		{
			super(name);
			setSelected(true);

			addActionListener(new ActionListener()
			{
				/**
				 * 
				 */
				public void actionPerformed(ActionEvent e)
				{
					mxGraphComponent graphComponent = editor
							.getGraphComponent();

					if (graphComponent != null)
					{
						mxConnectionHandler handler = graphComponent
								.getConnectionHandler();
						handler.setCreateTarget(!handler.isCreateTarget());
						setSelected(handler.isCreateTarget());
					}
				}
			});
		}
	}

	/**
	 *
	 */
	public static class PromptPropertyAction extends AbstractAction
	{
		/**
		 * 
		 */
		protected Object target;

		/**
		 * 
		 */
		protected String fieldname, message;

		/**
		 * 
		 */
		public PromptPropertyAction(Object target, String message)
		{
			this(target, message, message);
		}

		/**
		 * 
		 */
		public PromptPropertyAction(Object target, String message,
				String fieldname)
		{
			this.target = target;
			this.message = message;
			this.fieldname = fieldname;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof Component)
			{
				try
				{
					Method getter = target.getClass().getMethod(
							"get" + fieldname);
					Object current = getter.invoke(target);

					// TODO: Support other atomic types
					if (current instanceof Integer)
					{
						Method setter = target.getClass().getMethod(
								"set" + fieldname, new Class[] { int.class });

						String value = (String) JOptionPane.showInputDialog(
								(Component) e.getSource(), "Value", message,
								JOptionPane.PLAIN_MESSAGE, null, null, current);

						if (value != null)
						{
							setter.invoke(target, Integer.parseInt(value));
						}
					}
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}

			// Repaints the graph component
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				graphComponent.repaint();
			}
		}

	}

	/**
	 *
	 */
	public static class TogglePropertyItem extends JCheckBoxMenuItem
	{

		/**
		 * 
		 */
		public TogglePropertyItem(Object target, String name, String fieldname)
		{
			this(target, name, fieldname, false);
		}

		/**
		 * 
		 */
		public TogglePropertyItem(Object target, String name, String fieldname,
				boolean refresh)
		{
			this(target, name, fieldname, refresh, null);
		}

		/**
		 * 
		 */
		public TogglePropertyItem(final Object target, String name,
				final String fieldname, final boolean refresh,
				ActionListener listener)
		{
			super(name);

			// Since action listeners are processed last to first we add the given
			// listener here which means it will be processed after the one below
			if (listener != null)
			{
				addActionListener(listener);
			}

			addActionListener(new ActionListener()
			{
				/**
				 * 
				 */
				public void actionPerformed(ActionEvent e)
				{
					execute(target, fieldname, refresh);
				}
			});

			PropertyChangeListener propertyChangeListener = new PropertyChangeListener()
			{

				/*
				 * (non-Javadoc)
				 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
				 */
				public void propertyChange(PropertyChangeEvent evt)
				{
					if (evt.getPropertyName().equalsIgnoreCase(fieldname))
					{
						update(target, fieldname);
					}
				}

			};

			if (target instanceof mxGraphComponent)
			{
				((mxGraphComponent) target)
						.addPropertyChangeListener(propertyChangeListener);
			}
			else if (target instanceof mxGraph)
			{
				((mxGraph) target)
						.addPropertyChangeListener(propertyChangeListener);
			}

			update(target, fieldname);
		}

		/**
		 * 
		 */
		public void update(Object target, String fieldname)
		{
			try
			{
				Method getter = target.getClass().getMethod("is" + fieldname);
				Object current = getter.invoke(target);

				if (current instanceof Boolean)
				{
					setSelected(((Boolean) current).booleanValue());
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		/**
		 * 
		 */
		public void execute(Object target, String fieldname, boolean refresh)
		{
			try
			{
				Method getter = target.getClass().getMethod("is" + fieldname);
				Method setter = target.getClass().getMethod("set" + fieldname,
						new Class[] { boolean.class });

				Object current = getter.invoke(target);

				if (current instanceof Boolean)
				{
					boolean value = !((Boolean) current).booleanValue();
					setter.invoke(target, value);
					setSelected(value);
				}

				if (refresh)
				{
					mxGraph graph = null;

					if (target instanceof mxGraph)
					{
						graph = (mxGraph) target;
					}
					else if (target instanceof mxGraphComponent)
					{
						graph = ((mxGraphComponent) target).getGraph();
					}

					graph.refresh();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}

	/**
	 *
	 */
	public static class HistoryAction extends AbstractAction
	{

		/**
		 * 
		 */
		protected boolean undo;

		/**
		 * 
		 */
		public HistoryAction(boolean undo)
		{
			this.undo = undo;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			BasicGraphEditor editor = getEditor(e);
			
			if (editor != null)
			{
				if (undo)
				{
					editor.getUndoManager().undo();
				}
				else
				{
					editor.getUndoManager().redo();
				}
			}
		}
	}

	/**
	 *
	 */
	public static class FontStyleAction extends AbstractAction
	{
		/**
		 * 
		 */
		protected boolean bold;

		/**
		 * 
		 */
		public FontStyleAction(boolean bold)
		{
			this.bold = bold;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				Component editorComponent = null;

				if (graphComponent.getCellEditor() instanceof mxCellEditor)
				{
					editorComponent = ((mxCellEditor) graphComponent
							.getCellEditor()).getEditor();
				}

				if (editorComponent instanceof JEditorPane)
				{
					JEditorPane editorPane = (JEditorPane) editorComponent;
					int start = editorPane.getSelectionStart();
					int ende = editorPane.getSelectionEnd();
					String text = editorPane.getSelectedText();

					if (text == null)
					{
						text = "";
					}

					try
					{
						HTMLEditorKit editorKit = new HTMLEditorKit();
						HTMLDocument document = (HTMLDocument) editorPane
								.getDocument();
						document.remove(start, (ende - start));
						editorKit.insertHTML(document, start, ((bold) ? "<b>"
								: "<i>")
								+ text + ((bold) ? "</b>" : "</i>"), 0, 0,
								(bold) ? HTML.Tag.B : HTML.Tag.I);
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}

					editorPane.requestFocus();
					editorPane.select(start, ende);
				}
				else
				{
					mxIGraphModel model = graphComponent.getGraph().getModel();
					model.beginUpdate();
					try
					{
						graphComponent.stopEditing(false);
						graphComponent.getGraph().toggleCellStyleFlags(
								mxConstants.STYLE_FONTSTYLE,
								(bold) ? mxConstants.FONT_BOLD
										: mxConstants.FONT_ITALIC);
					}
					finally
					{
						model.endUpdate();
					}
				}
			}
		}
	}

	/**
	 *
	 */
	public static class WarningAction extends AbstractAction
	{

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				Object[] cells = graphComponent.getGraph().getSelectionCells();

				if (cells != null && cells.length > 0)
				{
					String warning = JOptionPane.showInputDialog(mxResources
							.get("enterWarningMessage"));

					for (int i = 0; i < cells.length; i++)
					{
						graphComponent.setCellWarning(cells[i], warning);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(graphComponent, mxResources
							.get("noCellSelected"));
				}
			}
		}
	}

	/**
	 *
	 */
	public static class NewAction extends AbstractAction
	{

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			BasicGraphEditor editor = getEditor(e);

			if (editor != null)
			{
				if (!editor.isModified()
						|| JOptionPane.showConfirmDialog(editor, mxResources
								.get("loseChanges")) == JOptionPane.YES_OPTION)
				{
					mxGraph graph = editor.getGraphComponent().getGraph();
					// Check modified flag and display save dialog
					mxCell root = new mxCell();
					root.insert(new mxCell());
					graph.getModel().setRoot(root);

					editor.setModified(false);
					editor.setCurrentFile(null);
				}
			}
		}
	}

	/**
	 *
	 */
	public static class OpenAction extends AbstractAction
	{
		/**
		 * 
		 */
		protected String lastDir;

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			BasicGraphEditor editor = getEditor(e);

			if (editor != null)
			{
				if (!editor.isModified()
						|| JOptionPane.showConfirmDialog(editor, mxResources
								.get("loseChanges")) == JOptionPane.YES_OPTION)
				{
					mxGraph graph = mxGraphActions.getGraph(e);

					if (graph != null)
					{
						String wd = (lastDir != null) ? lastDir : System
								.getProperty("user.dir");

						JFileChooser fc = new JFileChooser(wd);

						// Adds file filter for supported file format
						FileFilter defaultFilter = new DefaultFileFilter(
								".mxe", "mxGraph Editor "
										+ mxResources.get("file") + " (.mxe)");
						fc.addChoosableFileFilter(defaultFilter);

						int rc = fc.showDialog(null, mxResources
								.get("openFile"));

						if (rc == JFileChooser.APPROVE_OPTION)
						{
							lastDir = fc.getSelectedFile().getParent();

							try
							{
								Document document = mxUtils.parse(mxUtils
										.readFile(fc.getSelectedFile()
												.getAbsolutePath()));

								mxCodec codec = new mxCodec(document);
								codec.decode(document.getDocumentElement(),
										graph.getModel());

								editor.setModified(false);
								editor.setCurrentFile(fc.getSelectedFile());
							}
							catch (IOException e1)
							{
								e1.printStackTrace();
							}

						}
					}
				}
			}
		}
	}

	/**
	 *
	 */
	public static class ToggleAction extends AbstractAction
	{

		/**
		 * 
		 */
		protected String key;

		/**
		 * 
		 */
		protected boolean defaultValue;

		/**
		 * 
		 * @param key
		 */
		public ToggleAction(String key)
		{
			this(key, false);
		}

		/**
		 * 
		 * @param key
		 */
		public ToggleAction(String key, boolean defaultValue)
		{
			this.key = key;
			this.defaultValue = defaultValue;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			mxGraph graph = mxGraphActions.getGraph(e);

			if (graph != null)
			{
				graph.toggleCellStyles(key, defaultValue);
			}
		}
	}

	/**
	 *
	 */
	public static class SetLabelPositionAction extends AbstractAction
	{

		/**
		 * 
		 */
		protected String labelPosition, alignment;

		/**
		 * 
		 * @param key
		 */
		public SetLabelPositionAction(String labelPosition, String alignment)
		{
			this.labelPosition = labelPosition;
			this.alignment = alignment;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			mxGraph graph = mxGraphActions.getGraph(e);

			if (graph != null && !graph.isSelectionEmpty())
			{
				graph.getModel().beginUpdate();
				try
				{
					// Checks the orientation of the alignment to use the correct constants
					if (labelPosition.equals(mxConstants.ALIGN_LEFT)
							|| labelPosition.equals(mxConstants.ALIGN_CENTER)
							|| labelPosition.equals(mxConstants.ALIGN_RIGHT))
					{
						graph.setCellStyles(mxConstants.STYLE_LABEL_POSITION,
								labelPosition);
						graph.setCellStyles(mxConstants.STYLE_ALIGN, alignment);
					}
					else
					{
						graph.setCellStyles(
								mxConstants.STYLE_VERTICAL_LABEL_POSITION,
								labelPosition);
						graph.setCellStyles(mxConstants.STYLE_VERTICAL_ALIGN,
								alignment);
					}
				}
				finally
				{
					graph.getModel().endUpdate();
				}
			}
		}
	}

	/**
	 *
	 */
	public static class SetStyleAction extends AbstractAction
	{

		/**
		 * 
		 */
		protected String value;

		/**
		 * 
		 * @param key
		 */
		public SetStyleAction(String value)
		{
			this.value = value;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			mxGraph graph = mxGraphActions.getGraph(e);

			if (graph != null && !graph.isSelectionEmpty())
			{
				graph.setCellStyle(value);
			}
		}
	}

	/**
	 *
	 */
	public static class KeyValueAction extends AbstractAction
	{

		/**
		 * 
		 */
		protected String key, value;

		/**
		 * 
		 * @param key
		 */
		public KeyValueAction(String key)
		{
			this(key, null);
		}

		/**
		 * 
		 * @param key
		 */
		public KeyValueAction(String key, String value)
		{
			this.key = key;
			this.value = value;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			mxGraph graph = mxGraphActions.getGraph(e);

			if (graph != null && !graph.isSelectionEmpty())
			{
				graph.setCellStyles(key, value);
			}
		}
	}

	/**
	 *
	 */
	public static class PromptValueAction extends AbstractAction
	{

		/**
		 * 
		 */
		protected String key, message;

		/**
		 * 
		 * @param key
		 */
		public PromptValueAction(String key, String message)
		{
			this.key = key;
			this.message = message;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof Component)
			{
				mxGraph graph = mxGraphActions.getGraph(e);

				if (graph != null && !graph.isSelectionEmpty())
				{
					String value = (String) JOptionPane.showInputDialog(
							(Component) e.getSource(),
							mxResources.get("value"), message,
							JOptionPane.PLAIN_MESSAGE, null, null, "");

					if (value != null)
					{
						if (value.equals(mxConstants.NONE))
						{
							value = null;
						}

						graph.setCellStyles(key, value);
					}
				}
			}
		}
	}

	/**
	 *
	 */
	public static class AlignCellsAction extends AbstractAction
	{

		/**
		 * 
		 */
		protected String align;

		/**
		 * 
		 * @param key
		 */
		public AlignCellsAction(String align)
		{
			this.align = align;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			mxGraph graph = mxGraphActions.getGraph(e);

			if (graph != null && !graph.isSelectionEmpty())
			{
				graph.alignCells(align);
			}
		}
	}

	/**
	 *
	 */
	public static class AutosizeAction extends AbstractAction
	{

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			mxGraph graph = mxGraphActions.getGraph(e);

			if (graph != null && !graph.isSelectionEmpty())
			{
				graph.updateCellSize(graph.getSelectionCell());
			}
		}
	}

	/**
	 *
	 */
	public static class ColorAction extends AbstractAction
	{

		/**
		 * 
		 */
		protected String name, key;

		/**
		 * 
		 * @param key
		 */
		public ColorAction(String name, String key)
		{
			this.name = name;
			this.key = key;
		}

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				mxGraph graph = graphComponent.getGraph();

				if (!graph.isSelectionEmpty())
				{
					Color newColor = JColorChooser.showDialog(graphComponent,
							name, null);

					if (newColor != null)
					{
						graph.setCellStyles(key, mxUtils.hexString(newColor));
					}
				}
			}
		}
	}

	/**
	 *
	 */
	public static class BackgroundImageAction extends AbstractAction
	{

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				String value = (String) JOptionPane.showInputDialog(
						graphComponent, mxResources.get("backgroundImage"),
						"URL", JOptionPane.PLAIN_MESSAGE, null, null,
						"http://www.callatecs.com/images/background2.JPG");

				if (value != null)
				{
					if (value.length() == 0)
					{
						graphComponent.setBackgroundImage(null);
					}
					else
					{
						graphComponent.setBackgroundImage(new ImageIcon(mxUtils
								.loadImage(value)));
					}

					// Forces a repaint of the outline
					graphComponent.getGraph().repaint();
				}
			}
		}
	}

	/**
	 *
	 */
	public static class BackgroundAction extends AbstractAction
	{

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				Color newColor = JColorChooser.showDialog(graphComponent,
						mxResources.get("background"), null);

				if (newColor != null)
				{
					graphComponent.getViewport().setOpaque(false);
					graphComponent.setBackground(newColor);
				}

				// Forces a repaint of the outline
				graphComponent.getGraph().repaint();
			}
		}
	}

	/**
	 *
	 */
	public static class PageBackgroundAction extends AbstractAction
	{

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				Color newColor = JColorChooser.showDialog(graphComponent,
						mxResources.get("pageBackground"), null);

				if (newColor != null)
				{
					graphComponent.setPageBackgroundColor(newColor);
				}

				// Forces a repaint of the component
				graphComponent.repaint();
			}
		}
	}

	/**
	 *
	 */
	public static class StyleAction extends AbstractAction
	{

		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof mxGraphComponent)
			{
				mxGraphComponent graphComponent = (mxGraphComponent) e
						.getSource();
				mxGraph graph = graphComponent.getGraph();
				String initial = graph.getModel().getStyle(
						graph.getSelectionCell());
				String value = (String) JOptionPane.showInputDialog(
						graphComponent, mxResources.get("style"), mxResources
								.get("style"), JOptionPane.PLAIN_MESSAGE, null,
						null, initial);

				if (value != null)
				{
					graph.setCellStyle(value);
				}
			}
		}
	}

    // JTS
	public static class ValidateAction extends AbstractAction
	{
		public void actionPerformed(ActionEvent e)
		{
			BasicGraphEditor editor = getEditor(e);

			if( editor != null )
			{
				editor.callSecletedValidator();
			}
		}
	}

	// this action only saves the protocol to the database, not a file like the other save button
	public static class SaveDefinitionAction extends AbstractAction
	{
		public void actionPerformed( ActionEvent e )
		{
			BasicGraphEditor editor = getEditor(e);

			if( editor != null )
			{
				// must pass validation before saving
				if( !editor.callValidator() )
				{
					// signal user with popup to fix errors
					JOptionPane.showMessageDialog( editor.getGraphComponent(), "Protocol Behavior not saved!  Please correct errors and try again." );
					return;
				}
			}

			// Save the protocol
			mxCodec codec = new mxCodec();

			org.w3c.dom.Node node = codec.encode( editor.getGraphComponent().getGraph().getModel() );
					
			FreeGraphEditor freeEditor = getFreeGraphEditor( e );
			mxUtils.writeJmatter( node, freeEditor );
		}
	}
    // jts

}
