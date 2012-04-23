/*
 * Created on Mar 9, 2004
 */
package com.u2d.generated;

import com.u2d.model.ComplexEObject;
import com.u2d.view.EView;
import com.u2d.view.ListEView;
import com.u2d.field.Association;
import com.u2d.field.IndexedField;
import com.u2d.find.Searchable;
import com.u2d.find.inequalities.ContainsInequality;

import java.util.Collections;
import java.util.List;

/**
 * @author Eitan Suez
 */
public class RelationalListNoEdit extends com.u2d.list.RelationalList
             implements Searchable
{
   public RelationalListNoEdit(Class clazz) { super(clazz); }

   public EView getView() { return getAlternateView(); }
   public EView getMainView() { return getView(); }
   
   public ListEView getAlternateView()
   {
	      return vmech().getAlternateListView(this,
	                                          new String[]
	            {"listview"});
				// listtreeview good size, expands, no scroll
				// listiconsview icons no scroll
				//listview  scroll but no colllapse
				//listtableview no scroll
	  // return vmech().getListView(this);  // no edit, good size, no scroll
	  // return vmech().getListViewAsTable(this);  // no edit, no scroll, autoexpands
	  // return vmech().getListViewAsTree(this); // no edit, good size, no scroll
	  // return vmech().getOmniListView(this);   // no edit, but too large, no scroll
	  // return vmech().getRelationalListView(this);  // no edit, reverse order(up),  no scroll bar
	  // return vmech().getPickView(this);   // no edit but also no scroll bar
   }

}
