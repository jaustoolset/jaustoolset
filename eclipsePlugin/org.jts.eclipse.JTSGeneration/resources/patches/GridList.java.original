package com.u2d.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Jan 11, 2007
 * Time: 3:10:58 PM
 */
public class GridList extends JList
{
   private static final String uiClassID = "BasicGridListUI";
   static
   {
      UIManager.getDefaults().put(uiClassID, BasicGridListUI.class.getName());
   }
   
   public GridList() { }
   public GridList(Object[] listdata) { super(listdata); }
   public String getUIClassID() { return uiClassID; }


   public Component renderedComponent(int index)
   {
      Object value = getModel().getElementAt(index);
      return getCellRenderer().getListCellRendererComponent(this, value, index, false, false);
   }
   // the alternative of course is to revise the gridlistlayout layout manager
   // to work with a parent that is a jlist.  i prefer this at the moment.
   
   
   
   // =======  ============
   // scrollable interface:
   // ===========  =======
   
   private BasicGridListUI.GridListLayout gridlistlayout()
   {
      return (BasicGridListUI.GridListLayout) getLayout();
   }
   
   public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction)
   {
      int count = getModel().getSize();
      if (count == 0)
         return gridlistlayout().getDefaultHeight();
			
      // never scrolls horizontally, so don't need to inspect orientation
      Component comp = renderedComponent(0);
      return comp.getPreferredSize().height + gridlistlayout().getVgap();
   }
	
   public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction)
   {
      int rowHeight = getScrollableUnitIncrement(visibleRect, orientation, direction);
      int numRows = visibleRect.height / rowHeight;
      return  rowHeight * numRows;
   }
	
   public boolean getScrollableTracksViewportHeight()
   {
      if (getParent() instanceof JViewport)
      {
         JViewport viewport = (JViewport) getParent();
         return (viewport.getHeight() > getPreferredSize().height);
      }
      return false;
   }
   public boolean getScrollableTracksViewportWidth()
   {
      return true;
   }
	
   public Dimension getPreferredScrollableViewportSize()
   {
      return gridlistlayout().getPreferredSize(this);
   }
	
   public Dimension getPreferredSize()
   {
      return gridlistlayout().preferredLayoutSize(this);
   }
   public Dimension getMaximumSize()
   {
      return getPreferredSize();
   }
   public Dimension getMinimumSize()
   {
      Component comp = renderedComponent(0);
      if (comp == null)
         return new Dimension(32, 32);
      return comp.getSize();
   }
   
   
}
