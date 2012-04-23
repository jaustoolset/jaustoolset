/*
 * Created on Oct 11, 2004
 */
package com.u2d.list;

import java.util.Iterator;
import javax.swing.event.ListDataListener;
import javax.swing.table.TableModel;
import com.u2d.app.Context;
import com.u2d.model.*;
import com.u2d.view.EView;
import com.u2d.view.ListEView;
import com.u2d.ui.sorttable.SortTableModel;
import com.u2d.element.Field;
import com.u2d.element.CommandInfo;
import com.u2d.find.Query;
import com.u2d.find.QueryReceiver;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.json.JSONObject;
import org.json.JSONArray;
import static com.u2d.pubsub.AppEventType.*;
import com.u2d.type.atom.StringEO;
import com.u2d.type.atom.FileWEO;
import com.u2d.reflection.Cmd;
import com.u2d.reflection.Arg;
import com.u2d.json.JSON;

/**
 * @author Eitan Suez
 */
public class CriteriaListEO extends AbstractListEO implements Paginable, QueryReceiver
{
   private static int PAGE_SIZE = Context.getInstance().getApplication().getPagesize();

   private Query _query;
   private Criteria _criteria;
   private int _position = 0;
   private long _count;

   public CriteriaListEO(Query query)
   {
      this(query, 1);
   }
   public CriteriaListEO(Query query, int pageNum)
   {
      setQuery(query, pageNum);
   }
   
   public Query getQuery() { return _query; }
   
   public void setQuery(Query query)
   {
      setQuery(query, 1);
   }

   public void setQuery(Query query, int pageNum)
   {
      _query = query;
      Field sortField = _query.getQueryType().sortField();
      if (sortField == null)
      {
         setCriteria(_query.getCriteria(), pageNum);
      }
      else
      {
         sort(sortField, true);
      }
   }

   private synchronized void setCriteria(Criteria criteria)
   {
      setCriteria(criteria, 1);
   }
   private synchronized void setCriteria(Criteria criteria, int pageNum)
   {
      _criteria = criteria;
      _criteria.setMaxResults(PAGE_SIZE);

      setCount();
      fetchPage(pageNum);
   }

   private void setCount()
   {
      try
      {
         Criteria ccount = _query.getCriteria().setProjection(Projections.rowCount());
         // believe it or not but ccount.uniqueResult does not always work:
         // e.g. with interfaces:  hibernate issues multiple select count() on multiple
         // tables and does not aggregate the result for you.
         java.util.List list = ccount.list();
         _count = 0;
         for (Iterator itr = list.iterator(); itr.hasNext(); )
         {
            _count += ((Integer) itr.next()).intValue();
         }
      }
      catch (HibernateException ex)
      {
         System.err.println("HibernateException ex: "+ex.getMessage());
         ex.printStackTrace();
      }
   }

   private void sort(Field field, boolean ascending)
   {
      String propertyName = field.getSortPropertyName();
      if (CompositeUserType.class.isAssignableFrom(field.getJavaClass()))
      {
         CompositeUserType instance = (CompositeUserType) field.createInstance();
         propertyName += "." + instance.getPropertyNames()[0];
      }

      Order order;
      if (ascending)
         order = Order.asc(propertyName);
      else
         order = Order.desc(propertyName);

      Criteria criteria = _query.getCriteria().addOrder(order);
      setCriteria(criteria);
   }

   private void fetchCurrentPage()
   {
      _criteria.setFirstResult(_position);
      java.util.List items = _criteria.list();

      // leakage of persistence concerns..
      for (int i=0; i<items.size(); i++)
      {
         ((ComplexEObject) items.get(i)).onLoad();
      }
      setItems(items);
   }

   public void fetchPage(int pageNum)
   {
      _position = PAGE_SIZE * (pageNum - 1);
      fetchCurrentPage();
   }

   public int pageNum()
   {
      return ((int) (_position / PAGE_SIZE)) + 1;
   }

   public int numPages()
   {
      return ((int) ((_count-1) / PAGE_SIZE)) + 1;
   }

   public boolean hasNextPage()
   {
      return ( _position + PAGE_SIZE ) < _count;
   }
   public boolean hasPreviousPage()
   {
      return _position >= PAGE_SIZE;
   }
   
   public void firstPage() { fetchPage(1); }
   public void lastPage() { fetchPage(numPages()); }

   public void nextPage()
   {
      _position += PAGE_SIZE;
      fetchCurrentPage();
   }
   public void previousPage()
   {
      _position -= PAGE_SIZE;
      fetchCurrentPage();
   }

   
   public ComplexType queryType() { return _query.getQueryType(); }
   public ComplexType type() { return  queryType(); }
   public Class getJavaClass() { return type().getJavaClass(); }

   public EObject makeCopy()
   {
      return new CriteriaListEO(_query);
   }

   public boolean isEmpty() { return _count == 0; }
   public synchronized int getSize() { return _items.size(); }
   public synchronized int getTotal() { return (int) _count; }

   public String getPageTitleInfo()
   {
      return "Page "+pageNum()+" of "+numPages();
   }

   @Cmd(description="A potentially lengthy operation")
   public String ExportEntireListingToJSON(CommandInfo cmdInfo, @Arg("Save to:")FileWEO file) throws Exception
   {
      CriteriaListEO leo = (CriteriaListEO) type().Browse(cmdInfo);
      vmech().message("Saving page 1");
      JSONObject json = JSON.json(leo);
      JSONArray ra = json.getJSONArray("items");
      while (leo.hasNextPage())
      {
         vmech().message(String.format("Saving page %d", leo.pageNum()));
         leo.nextPage();
         for (int i=0; i<leo.getSize(); i++)
         {
            ra.put(JSON.json((ComplexEObject) leo.get(i)));
         }
      }
      JSON.writeTextFile(json.toString(2), file.fileValue().getAbsolutePath());
      return file.fileValue().getName() + " created.";
   }

   /* ** ===== View-Related ===== ** */
   
   public EView getView() { return getListView(); }
   public EView getMainView() { return getView(); }
   
   public ListEView getListView()
   {
      ListEView altView = getAlternateView();
      return vmech().getPaginableView(altView);
   }
   public ListEView getAlternateView()
   {
      /* a table view assumes all table elements have the same structure, which is not a correct
        assumption for a list of objects that have a common interface or that are part of a
        class hierarchy.
       */
      if (type().isAbstract())
      {
         return vmech().getAlternateListView(this, 
               new String[] {"listview", "listiconsview", "omnilistview"});
      }
      else
      {
         return vmech().getAlternateListView(this, 
               new String[] {"listview", "listtableview", "listiconsview", "omnilistview"});
      }
   }
   public ListEView getTableView()
   {
      return vmech().getPaginableView(vmech().getListViewAsTable(this));
   }
   public ListEView getAssociationView()
   {
      return vmech().getPaginableView(vmech().getListView(this));
   }

   public ListEView getPickView()
   {
      return vmech().getPaginableView(super.getPickView());
   }

   public void removeListDataListener(ListDataListener l)
   {
      super.removeListDataListener(l);
      if (_listDataListenerList.getListenerCount() == 0)
      {
         synchronized(this)
         {
            // remove ondelete listener from items
            ComplexEObject ceo;
            for (Iterator itr = _items.iterator(); itr.hasNext(); )
            {
               ceo = (ComplexEObject) itr.next();
               ceo.removeAppEventListener(DELETE, this);
            }
         }
      }
   }
   
   /* ** ===== TableModel implementation ===== ** */

   // must use an inner class because i want to extend from AbstractTableModel
   //  because it "provides default implementations for most of the methods
   //  in the TableModel interface."

   public TableModel tableModel()
   {
      if (_tableModel == null)
         _tableModel = new CriteriaListEO.CLEOTableModel();
      return _tableModel;
   }

   class CLEOTableModel extends AbstractListEO.LEOTableModel
                        implements SortTableModel
   {
      public void sort(int colIndex, boolean ascending)
      {
         Field field = _tableFields.get(colIndex - 1);
         CriteriaListEO.this.sort(field, ascending);
      }

      public boolean isColumnSortable(int colIndex)
      {
         if (colIndex == 0) return false;
         Field field = _tableFields.get(colIndex - 1);
         return field.isSortable();
      }
   }

   public void add(ComplexEObject item)
   {
      setCount();
      super.add(item);
   }
   public void remove(ComplexEObject item)
   {
      setCount();
      super.remove(item);
   }

   public Title title()
   {
      StringEO name = _query.getName();
      if (name.isEmpty())
         return super.title();
      return name.title().appendParens(""+getTotal());
   }

}
