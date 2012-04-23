package com.u2d.field;

import com.u2d.validation.ValidationNotifier;
import com.u2d.validation.ValidationEvent;
import com.u2d.validation.ValidationListener;
import com.u2d.element.Field;
import com.u2d.model.*;

import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.List;
import java.beans.PropertyChangeListener;

/**
 * @author Eitan Suez
 */
public class Association
      implements Transferable, java.io.Serializable, ValidationNotifier, Dissociable
{
   protected AssociationStrategy _as;
   
   public Association(Field field, ComplexEObject parent)
   {
     this(new BasicAssociationStrategy(field, parent));
   }
   public Association(Field field, ComplexEObject parent, Field otherSide)
   {
      this(new BidiAssociationStrategy(field, parent, otherSide));
   }
   
   public Association(AssociationStrategy as) { _as = as; }
   
   public AssociationStrategy getStrategy() { return _as; }

   public Title title() { return _as.title(); }
   public String toString() { return title().toString(); }

   public ComplexEObject get()
   {
      ComplexEObject eo = _as.get();
      if (eo == null)
      {
         return new NullAssociation(this);
      }
      else
      {
         return eo;
      }
   }

   public AbstractListEO getAsList() { return _as.getAsList(); }

   public void set(final ComplexEObject value) { _as.set(value); }
   
   public void associateList(final List value) { _as.associateList(value); }
   public void associate(ComplexEObject value) { _as.associate(value); }
   
   public void dissociate() { _as.dissociate(); }
   
   public void dissociateItem(ComplexEObject eo) { _as.dissociateItem(eo); }
   
   public Field field() { return _as.field(); }
   public ComplexEObject parent() { return _as.parent(); }
   
   public javax.swing.Icon iconSm() { return _as.iconSm(); }
   public boolean isEmpty() { return _as.isEmpty(); }
   public ComplexType type() { return _as.type(); }

   public boolean isReadOnly()
   {
      return (_as.field() == null) ? false : _as.field().isReadOnly();
   }

   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      parent().addPropertyChangeListener(listener);
   }
   public void removePropertyChangeListener(PropertyChangeListener listener)
   {
      parent().removePropertyChangeListener(listener);
   }
   public void addChangeListener(ChangeListener l)
   {
      parent().addChangeListener(l);
   }
   public void removeChangeListener(ChangeListener l)
   {
      parent().removeChangeListener(l);
   }
   
   
   public boolean isEditableState() { return _as.isEditableState(); }

   public String getName() { return _as.getName(); }
   
   
   // ========== implementation of Transferrable Interface  ===============
   
   public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
   {
      if (!isDataFlavorSupported(flavor))
          throw new UnsupportedFlavorException(flavor);
      return this;
   }
   
   public DataFlavor[] getTransferDataFlavors()
   {
      DataFlavor typeFlavor = makeFlavor(type().getJavaClass());
      return new DataFlavor[] {FLAVOR, typeFlavor };
   }
   
   public boolean isDataFlavorSupported(DataFlavor f)
   {
      return f.equals(FLAVOR);
   }

   public static DataFlavor FLAVOR;
   static
   {
      FLAVOR = makeFlavor(Association.class);
   }
   
   private static DataFlavor makeFlavor(Class cls)
   {
      try
      {
         String flavorType = DataFlavor.javaJVMLocalObjectMimeType + 
         ";class="+cls.getName();
         return new DataFlavor(flavorType);
      }
      catch (ClassNotFoundException ex)
      {
         System.err.println("ClassNotFoundException: "+ex.getMessage());
         throw new RuntimeException("Failed to find class while attempting "+ 
               "to construct a data flavor for it! ("+cls+")");
      }
   }

   /* ** Validation Exception Notification Code ** */
   protected transient ValidationEvent _validationEvent = null;
   protected transient EventListenerList _validationListenerList = new EventListenerList();
   public void addValidationListener(ValidationListener l)
   {
     _validationListenerList.add(ValidationListener.class, l);
   }
   public void removeValidationListener(ValidationListener l)
   {
     _validationListenerList.remove(ValidationListener.class, l);
   }
   public void fireValidationException(String errorMsg, boolean statusType)
   {
      Object[] listeners = _validationListenerList.getListenerList();
      for (int i = listeners.length-2; i>=0; i-=2)
      {
         if (listeners[i]==ValidationListener.class)
         {
            _validationEvent = new ValidationEvent(this, errorMsg, statusType);
            ((ValidationListener)listeners[i+1]).validationException(_validationEvent);
         }
      }
   }
   public void fireValidationException(String errorMsg)
   {
      fireValidationException(errorMsg, false);
   }
   
}
