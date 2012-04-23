/*
 * Created on Jan 26, 2004
 */
package com.u2d.view.swing.list;

import com.u2d.model.AbstractListEO;
import com.u2d.model.ComplexEObject;
import com.u2d.view.swing.dnd.SimpleListTransferHandler;
import com.u2d.list.RelationalList;
import com.u2d.field.ListItemAssociation;
import java.awt.dnd.*;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.*;
import java.io.IOException;
import java.util.TooManyListenersException;

/**
 * The idea is a jlistview whose elements can be reordered via dnd.
 * Useful in the context of the classbar.
 * 
 * @author Eitan Suez
 */
public class ReorderListView extends JListView
{
   private double rowHeight;
   
   public ReorderListView(AbstractListEO leo)
   {
      super(leo);
      init();
   }
   public ReorderListView(AbstractListEO leo, boolean renderCellsAsIcons)
   {
      super(leo, renderCellsAsIcons);
      init();
   }

   private void init()
   {
      if (!_leo.isEmpty())
      {
         int index = 0;
         Component c = getCellRenderer().getListCellRendererComponent(ReorderListView.this,
               _leo.get(index), index, false, false);
         rowHeight = c.getPreferredSize().getHeight();
      }
   }

   private ReorderDropTarget _dropTarget;
   private SimpleListTransferHandler _transferHandler;

   public void setupTransferHandler()
   {
      setDragEnabled(true);
      _transferHandler = new SimpleListTransferHandler(this);
      setTransferHandler(_transferHandler);
      if (_leo instanceof RelationalList)
      {
         RelationalList rl = (RelationalList) _leo;
         _dropTarget = new ReorderDropTarget(rl);
         setDropTarget(_dropTarget);
      }
   }

   public void setLocked(boolean locked)
   {
      if (locked)
      {
         setDragEnabled(false);
         setTransferHandler(null);
         setDropTarget(null);
      }
      else
      {
         setDragEnabled(true);
         setTransferHandler(_transferHandler);
         setDropTarget(_dropTarget);
      }
   }

   public void detach()
   {
      super.detach();
      if (_dropTarget != null) _dropTarget.detach();
      if (_transferHandler != null) _transferHandler.detach();
   }

   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);

      if (dragging)
      {
         Graphics2D g2 = (Graphics2D) g;
         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

         int spotIndex = (int) Math.round(dtde.getLocation().getY() / rowHeight);
         spotIndex = Math.min(spotIndex, _leo.getSize());

         g2.setColor(new Color(0x6786C0));

         int gap = 4;
         int arrowWidth = 5;

         int startLine = (2 * gap) + arrowWidth;
         int endLine = getWidth() - startLine;

         int y = (int) (spotIndex * rowHeight);
         g2.setStroke(new BasicStroke(2));

         g2.drawLine(startLine, y, endLine, y);

         g2.drawLine(gap, y-2, gap+arrowWidth, y);
         g2.drawLine(gap, y+2, gap+arrowWidth, y);
         g2.drawLine(getWidth()-gap, y-2, getWidth()-gap-arrowWidth, y);
         g2.drawLine(getWidth()-gap, y+2, getWidth()-gap-arrowWidth, y);
      }
   }

   boolean dragging = false;
   DropTargetDragEvent dtde;

   /* adapter from relationallistdroptarget */
   class ReorderDropTarget extends DropTarget
   {
      private RelationalList _leo;

      public ReorderDropTarget(RelationalList leo)
      {
         super();
         _leo = leo;
         setup();
      }

      public void dragEnter(DropTargetDragEvent dtde)
      {
         super.dragEnter(dtde);
         dragging = true;
         ReorderListView.this.dtde = dtde;
         ReorderListView.this.repaint();
      }

      public void dragOver(DropTargetDragEvent dtde)
      {
         super.dragOver(dtde);
         dragging = true;
         ReorderListView.this.dtde = dtde;
         ReorderListView.this.repaint();
      }

      public void dragExit(DropTargetEvent dte)
      {
         super.dragExit(dte);
         dragging = false;
         ReorderListView.this.dtde = null;
         ReorderListView.this.repaint();
      }

      public void drop(DropTargetDropEvent dtde)
      {
         super.drop(dtde);
         dragging = false;
         ReorderListView.this.dtde = null;
         ReorderListView.this.repaint();
      }

      private void setup()
      {
         try
         {
            addDropTargetListener(new DropTargetAdapter()
            {
               public void drop(DropTargetDropEvent dtde)
               {
                  Transferable t = dtde.getTransferable();
                  DataFlavor[] flavors = t.getTransferDataFlavors();
                  for (DataFlavor flavor : flavors)
                  {
                     Class droppedType = flavor.getRepresentationClass();
                     Class folderItemsType = _leo.type().getJavaClass();

                     try
                     {
                        Point dropPt = dtde.getLocation();

                        int spotIndex = (int) Math.round(dropPt.getY() / rowHeight);
                        spotIndex = Math.min(spotIndex, _leo.getSize());

                        if (flavor.equals(ListItemAssociation.FLAVOR))
                        {
                           ListItemAssociation lia = (ListItemAssociation) t.getTransferData(flavor);
                           ComplexEObject item = lia.item();
                           int itemIndex = _leo.getItems().indexOf(item);
                           boolean movingdown = itemIndex < spotIndex;
                           if (movingdown)
                           {
                              spotIndex -= 1;
                           }

                           boolean reOrdering = (spotIndex != itemIndex);
                           if (reOrdering)
                           {
                              _leo.remove(item);
                              _leo.add(spotIndex, item);
                              if (!_leo.parentObject().isEditableState())
                                 _leo.parentObject().save();
                           }
                           break;
                        }
                        else if (folderItemsType.isAssignableFrom(droppedType))
                        {
                           ComplexEObject item = (ComplexEObject) t.getTransferData(flavor);
                           _leo.add(spotIndex, item);
                           if (!_leo.parentObject().isEditableState())
                              _leo.parentObject().save();
                           break;
                        }
                     }
                     catch (UnsupportedFlavorException ex)
                     {
                        // should not happen since the flavor comes from
                        // gettransferdataflavors..
                        System.err.println("UnsupportedFlavorException: " + ex.getMessage());
                     }
                     catch (IOException ex)
                     {
                        System.err.println("IO Exception: " + ex.getMessage());
                     }
                  }
               }
            });
         }
         catch (TooManyListenersException ex)
         {
           System.err.println("TooManyListenersException: "+ex.getMessage());
           ex.printStackTrace();
         }
      }

      public void detach()
      {
         _leo = null;
      }
   }
   
}
