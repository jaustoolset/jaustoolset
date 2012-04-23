package com.u2d.field;

import com.u2d.model.*;
import com.u2d.element.Field;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: eitan
 * Date: Nov 16, 2006
 * Time: 1:18:13 PM
 */
public class DynaAssociationStrategy extends AbstractComplexEObject implements AssociationStrategy
{
   private ComplexType _type;
   private ComplexEObject _item;
   
   public DynaAssociationStrategy(ComplexType type)
   {
      _type = type;
      setState(_transientState, true);
   }
   
   public Title title()
   {
      return _type.title().append("association");
   }
   
   public ComplexEObject getItem() { return _item; }
   public void setItem(ComplexEObject item)
   {
      ComplexEObject oldItem = _item;
      _item = item;
      firePropertyChange("item", oldItem, _item);
   }
   
   // conveniences..

   public ComplexEObject get() { return _item; }

   public void set(final ComplexEObject value)
   {
      setItem(value);
   }

   public void associate(ComplexEObject value) { set(value); }
   public void dissociate() { set(null); }

   public boolean isEmpty() { return _item == null; }
   public ComplexType type() { return _type; }
   
   public String getName() { return "item"; }
   public ComplexEObject parent() { return this; }


   // no list support yet.  not sure if needed even..
   public AbstractListEO getAsList()
   {
      return null;
   }

   public void associateList(List value)
   {
      // noop;
   }

   public void dissociateItem(ComplexEObject eo)
   {
      // noop;
   }


   public Field field()
   {
      return super.type().field("item");
   }
}
