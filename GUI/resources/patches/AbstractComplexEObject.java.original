/*
 * Created on Jan 19, 2004
 */
package com.u2d.model;

import java.util.*;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.event.*;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.datatransfer.*;
import java.awt.*;
import com.u2d.element.*;
import com.u2d.field.*;
import com.u2d.find.inequalities.IdentityInequality;
import com.u2d.pattern.*;
import com.u2d.pubsub.*;
import static com.u2d.pubsub.AppEventType.*;
import com.u2d.type.Choice;
import com.u2d.type.composite.LoggedEvent;
import com.u2d.type.atom.DateTime;
import com.u2d.type.atom.FileWEO;
import com.u2d.view.*;
import com.u2d.reflection.Cmd;
import com.u2d.reflection.Arg;
import com.u2d.json.JSON;
import com.u2d.list.CompositeList;
import com.u2d.app.Tracing;
import org.hibernate.NonUniqueObjectException;

/**
 * @author Eitan Suez
 */
public abstract class AbstractComplexEObject extends AbstractEObject
            implements ComplexEObject
{
   protected ComplexType _type = null;

   protected transient State _transientState, _editState, _readState, _nullState;
   protected transient Map<String, State> _stateMap = new HashMap<String, State>();
   {
      _transientState = new TransientState();
      _stateMap.put(_transientState.getName(), _transientState);

      _editState = new EditState();
      _stateMap.put(_editState.getName(), _editState);

      _readState = new ReadState();
      _stateMap.put(_readState.getName(), _readState);

      _nullState = new NullState();
      _stateMap.put(_nullState.getName(), _nullState);
   }

   protected transient State _currentState;

   public static String[] readOnly = {"createdOn"};


   public AbstractComplexEObject()
   {
      _currentState = _nullState;
   }

   public void initialize()
   {
      Field field;
      for (int i=0; i<childFields().size(); i++)
      {
         field = (Field) childFields().get(i);
         if (field.isAggregate())
         {
            ((AbstractComplexEObject) field.get(this)).initialize();
         }
      }
   }

   public ComplexType type()
   {
      if (_type == null)
         _type = ComplexType.forObject(this);
      return _type;
   }

   private List<ChangeListener> derivedFieldUpdaters = new ArrayList<ChangeListener>();
   protected void configureAsDerivedField(final EObject targetObject, EObject[] inputs, final Updater updater)
   {
      for (EObject eo : inputs)
      {
         ChangeListener listener = new ChangeListener()
         {
            public void stateChanged(ChangeEvent e)
            {
               targetObject.setValue(updater.update());
            }
         };
         eo.addChangeListener(listener);
         derivedFieldUpdaters.add(listener);
      }
   }

   public void onLoad()
   {
      restoreState();
      
      Field child;
      for (int i=0; i<childFields().size(); i++)
      {
         child = (Field) childFields().get(i);
         if (child.isComposite())
         {
            EObject eo = child.get(this);
            if (eo instanceof AbstractComplexEObject)
            {
               ((AbstractComplexEObject) eo).onLoad();
            }
            else if (eo instanceof CompositeList)
            {
               ((CompositeList) eo).onLoad();
            }
         }
      }

      for (ChangeListener l : derivedFieldUpdaters)
      {
         l.stateChanged(null);
      }
   }

   public void onDelete()
   {
      vmech().message(ComplexType.localeLookupStatic("deleted")+" "+title());
      setNullState();
      fireAppEventNotification(DELETE, this);
   }
   public void onBeforeSave()
   {
      Field child;
      for (int i=0; i<childFields().size(); i++)
      {
         child = (Field) childFields().get(i);
         if (child.isComposite() && child.isIndexed())
         {
            CompositeList list = (CompositeList) child.get(this);
            list.onBeforeSave();
         }
      }
      fireAppEventNotification(BEFORESAVE);
   }
   public void onSave()
   {
      vmech().message(ComplexType.localeLookupStatic("saved")+" "+title());
      // dillema here:  don't really know you're coming from an EditState Exit:
      popState();
      fireAppEventNotification(SAVE);
      type().fireAppEventNotification(SAVE, this);
   }
   public void onBeforeCreate()
   {
      _createdOn.setValue(new Date());
      fireAppEventNotification(BEFORECREATE, this);
   }
   public void onCreate()
   {
      if ( ! (this instanceof LoggedEvent) )
      {
         vmech().message(ComplexType.localeLookupStatic("created")+" "+title());
      }
      setStartState();
      fireAppEventNotification(CREATE, this);
      type().fireAppEventNotification(CREATE, this);
   }

   // state-related methods

   public void setStartState()
   {
      setState(startState(), true);

      for (Iterator itr = childFields().iterator(); itr.hasNext(); )
      {
         Field field = (Field) itr.next();
         field.setStartState(this);
      }

   }
   public void restoreState()
   {
      setState(restoredState(), true /* shallow */);
   }

   // subclasses should override this:
   public State startState() { return _readState; }
   public State restoredState() { return _readState; }

   public void setReadState() { setState(_readState, true); }

   public boolean isEditState() { return _currentState == _editState; }
   public void setEditState() { setState(_editState); }

   public boolean isTransientState() { return _currentState == _transientState; }
   public void setTransientState() { setState(_transientState); }

   public boolean isEditableState()
   {
      return (_currentState instanceof EditableState);
   }
   public boolean isReadableState()
   {
      return (_currentState instanceof ReadState);
   }
   
   public boolean isNullState() { return _currentState == _nullState; }
   public void setNullState() { setState(_nullState); }

   public State getState() { return _currentState; }

   public void setState(State state) { setState(state, false); }

   protected void setState(State state, boolean shallow)
   {
      _currentState = _stateMap.get(state.getName());
      if (_currentState == null)
         // an indication that state is a readstate subclass (as in user.lockedstate)
         // which will not exist on its fields when doing a deep setstate invocation.
         // in this case, the field's state should be set to readstate
      {
         _currentState = _readState;
      }
      fireStateChanged();

      if (!isEditableState()) clearEditor();
      
      if (shallow) return;

      for (Iterator itr = childFields().iterator(); itr.hasNext(); )
      {
         Field field = (Field) itr.next();
         field.setState(this, state);
      }
   }
   private Stack<State> _stateStack = new Stack<State>();

   /**
    * comment:  this implementation could clearly be refactored since the logic is the same,
    * and the one thing that varies is the message: setState, pushState, popState.
    * i.e. using a higher order function.
    */
   public void pushState(State state)
   {
      _stateStack.push(_currentState);
      setState(state, true);
      for (Iterator itr = childFields().iterator(); itr.hasNext(); )
      {
         Field field = (Field) itr.next();
         field.pushState(this, state);
      }
   }
   public void popState()
   {
      if (_stateStack.isEmpty()) return;
      State popped = _stateStack.pop();
      setState(popped, true);
      for (Iterator itr = childFields().iterator(); itr.hasNext(); )
      {
         Field field = (Field) itr.next();
         field.popState(this);
      }
   }


   public void cancelTransition()
   {
      if (isEditableState())
         ((EditableState) getState()).Cancel(null);
   }


   private FieldParent fieldParent()
   {
      Field field = field();
      return (field instanceof FieldParent) ? (FieldParent) field : type();
   }
   public List childFields() { return fieldParent().fields(); }
   public Field field(String propName) { return fieldParent().field(propName); }

   // convenience for retrieving a list of fields by name..
   public List<Field> fieldSublist(String[] fieldnameList)
   {
      List<Field> sublist = new ArrayList<Field>();
      for (int i=0; i<fieldnameList.length; i++)
      {
         sublist.add(field(fieldnameList[i]));
      }
      return sublist;
   }


   private Map<String, Association> _associations = new HashMap<String, Association>();
   public synchronized Association association(String propName)
   {
      Field field = field(propName);
      if (field == null)
      {
         throw new IllegalArgumentException("No such property: "+propName+ " on field parent "+fieldParent());
      }
      if ( !field.isAssociable() )
      {
         throw new IllegalArgumentException("Property "+propName+" is not associable.");
      }

      if (_associations.containsKey(propName))
      {
         return _associations.get(propName);
      }
      else
      {
         Association association = ((Associable) field).association(this);
         _associations.put(propName, association);
         return association;
      }
   }

   public Command command(String commandName)
   {
      return command(commandName, startState());
   }
   public Command command(String commandName, State state)
   {
      Onion commands = type().commands(state);
      if (commands == null) return null;
      return (Command) commands.find(Command.finder(commandName));
   }
   public Onion commands()
   {
      return type().commands(_currentState);
   }
   public Onion filteredCommands()
   {
      return type().filteredCommands(this, _currentState.getClass());
   }
   
   
   protected String _defaultCmdName = "Open";
   public String defaultCommandName() { return _defaultCmdName; }
   public void setDefaultCommandName(String name) { _defaultCmdName = name; }

   public Command defaultCommand() { return command(defaultCommandName()); }


   protected transient Editor _editor = null;

   public void setEditor(Editor editor)
   {
      _editor = editor;
      vmech().setEditable(_editor, true);
   }

   public void clearEditor()
   {
      if (_editor == null) return;
      vmech().setEditable(_editor, false);
      _editor = null;
   }


   public String iconSmResourceRef() { return IconResolver.stateIconRef(this, "16"); }
   public String iconLgResourceRef() { return IconResolver.stateIconRef(this, "32"); }

   public javax.swing.Icon iconSm() { return IconLoader.loadIcon(iconSmResourceRef()); }
   public javax.swing.Icon iconLg() { return IconLoader.loadIcon(iconLgResourceRef()); }

   public abstract Title title();

   public String toString()
   {
      return title().toString();
   }
   
   public String viewTitle()
   {
      if (isTransientState())
      {
         if (field() == null)
         {
            return ComplexType.localeLookupStatic("new")+" "+type().getNaturalName();
         }
         else
         {
            return field().label();
         }
      }
      else
      {
         return title().toString();
      }
   }
   
   public String debugString()
   {
      List fields = childFields();
      Field field;
      StringBuffer text = new StringBuffer("");
      for (int i=0; i<fields.size(); i++)
      {
         field = (Field) fields.get(i);
         text.append(field.getPath()).append(": ").append(field.get(this).toString()).append("; ");
      }
      return text.toString();
   }

   public boolean isEmpty()
   {
      Field field;
      for (Iterator itr = childFields().iterator(); itr.hasNext(); )
      {
         field = (Field) itr.next();
         
         if ( "createdOn".equals(field.name()) || "deleted".equals(field.name())
               || "deletedOn".equals(field.name()))
            continue;

         if (!field.isEmpty(this))
            return false;
      }
      return true;
   }

   public boolean equals(Object obj)
   {
      if (obj == null) return false;
      if (obj == this) return true;
      if (!(obj instanceof AbstractComplexEObject)) return false;
      if (!sameClassOrProxy(obj)) return false;

      ComplexEObject ceo = (ComplexEObject) obj;

      // favor comparison by identity fields (if any)
      if (type().hasIdentityFields())
      {
         for (Iterator itr = type().identityFields().iterator(); itr.hasNext(); )
         {
            Field field = (Field) itr.next();
            EObject value = field.get(this);
            EObject other = field.get(ceo);
            if (!value.equals(other))
            {
               return false;
            }
         }
         return true;
      }

      // otherwise fall back to pk, with realization/awareness that not complying exactly with contract
      if (getID() != null)
      {
         return getID().equals(ceo.getID());
      }
      if (ceo.getID() == null) return false;

      return true;  // both transient and of the same type..
   }

   public int hashCode()
   {
      if (type().hasIdentityFields())
      {
         int hashCode = 13;
         for (Iterator itr = type().identityFields().iterator(); itr.hasNext(); )
         {
            Field field = (Field) itr.next();
            EObject value = field.get(this);
            hashCode = hashCode + 31 * value.hashCode();
         }
         return hashCode;
      }

      if (getID()!=null)
      {
         return 13 + 31 * (getID().hashCode() + 31 * getCreatedOn().hashCode());
      }

      // otherwise you have a problem..
      return 13 + 31 * getClass().hashCode();
   }

   protected boolean sameClassOrProxy(Object obj)
   {
      String clsName1 = cleanCGLibEnhancer(obj);
      String clsName2 = cleanCGLibEnhancer(this);
      return (clsName1.equals(clsName2));
   }
   public static String cleanCGLibEnhancer(Object obj)
   {
      return cleanCGLIBEnhancer(obj.getClass().getName());
   }
   static String CGLIBKey = "$$EnhancerByCGLIB$$";
   public static boolean isCGLIBEnhanced(String clsName)
   {
      return clsName.indexOf(CGLIBKey) > 0;
   }
   public static String cleanCGLIBEnhancer(String clsName)
   {
      int idx = clsName.indexOf(CGLIBKey);
      if (idx > 0) clsName = clsName.substring(0, idx);
      return clsName;
   }


   /* ** View-Related ** */
   public ComplexEView getIconView() { return vmech().getIconView(this); }
   public ComplexEView getListItemView() { return vmech().getListItemView(this); }
   public ComplexEView getFormView() { return vmech().getFormView(this); }
   public ComplexEView getTabBodyView() { return vmech().getTabBodyView(this); }
   public ComplexEView getExpandableView() { return vmech().getExpandableView(this); }
   public ComplexEView getTreeView() { return vmech().getTreeView(this); }

   public EView getView() { return getListItemView(); }

   public EView getMainView()
   {
      return vmech().getAlternateView(this, new String[] {"formview", "omniview"});
   }

   /**
    * to partially customized a multi-tabbed view;  specifically to cuztomize the panel for the main tab,
    * override this:
    */
   public boolean hasCustomMainTabPanel() { return false; }
   public EView mainTabPanel() { return null; }

   /*
   * note this code is from the perspective of the ui
   * it gives editor (ui) a chance to transfer entered
   * data to the object and then validates the data.
   * cancel restores a saved copy.
   * 
   * @return success
   */
   public boolean doSave()
   {
      int errorCount = _editor.transferValue();
      if (errorCount > 0)
      {
         String plural = (errorCount == 1) ? "" : "s";
         fireValidationException("["+ComplexType.localeLookupStatic("error.syntax")+" "+errorCount+" "+ComplexType.localeLookupStatic("form_field"+plural)+".]", true);
         return false;
      }

      fireValidationException("");  // reset any validation messages from last attempt
      errorCount = validate();
      if (errorCount > 0)
      {
         String plural = (errorCount == 1) ? "" : "s";
         fireValidationException("["+errorCount+" "+ComplexType.localeLookupStatic("validation.error"+plural) +".]", true);
         return false;
      }

      fireValidationException("");  // reset any validation messages from last attempt

      // now we can do the actual save against the persistence mechanism..
      persistor().save(this);
      return true;
   }

   public int validate()
   {
      if (field() != null && field().isComposite())
      {
         return 0;
      }
      return type().validate(this);
   }


   // conveniences..
   public void save() { persistor().save(this); }
   public void delete() { persistor().delete(this); }

   public void refresh()
   {
      hbmPersistor().refresh(this);
      restoreState();
   }

   public AbstractListEO list() { return persistor().list(type()); }
   public ComplexEObject lookup(String uniqueFieldName, String value)
   {
      return hbmPersistor().lookup(type().getJavaClass(), uniqueFieldName, value);
   }


   private transient ComplexEObject _copy;
   public void saveCopy()
   {
      _copy = (ComplexEObject) makeCopy();
   }

   // replaces values (deep) but not references (to preserve view integrity)
   public void restoreCopy()
   {
      transferCopy(this, _copy, true);
   }
   
   protected void transferCopy(ComplexEObject target, ComplexEObject copy, boolean forCopy)
   {
      List fields = target.type().fields();
      for (int i=0; i<fields.size(); i++)
      {
         Field field = (Field) fields.get(i);
         EObject valueCopy = field.get(copy);

         if (field.isAggregate())
         {
            ComplexEObject value = (ComplexEObject) field.get(target);
            transferCopy(value, (ComplexEObject) valueCopy, forCopy);
         }
         else
         {
            if (forCopy && (field.isAssociation() || (field.isIndexed() && !field.isComposite())) )
               continue;  // omit associations because in copy operations
                          // causes original to lose association

            field.set(target, valueCopy);
         }
      }
   }

   // deep copy
   public EObject makeCopy()
   {
      ComplexEObject target = type().instance();
      target.setValue(this, true);
      return target;
   }

   public void setValue(EObject value)
   {
      setValue(value, false);
   }

   public void setValue(EObject value, boolean forCopy)
   {
      if (value == null)
         throw new IllegalArgumentException("setValue does not accept null");
      if (!(value instanceof ComplexEObject))
         throw new IllegalArgumentException("Invalid type on set (" +
               value.getClass().getName() + "); " +
               "must be ComplexEObject");

      ComplexEObject ceo = (ComplexEObject) value;
      Class fromClass = value.getClass();
      Class thisClass = this.getClass();
      if (!thisClass.isAssignableFrom(fromClass))
      {
         throw new IllegalArgumentException("Cannot set value of type "+fromClass.getName() +
               " onto an object of type "+thisClass.getName());
      }

      transferCopy(this, ceo, forCopy);
   }

   /* ** State Related Code ** */

   public class TransientState extends EditableState
   {
      @Cmd(mnemonic='s', blocks=true, iconref="save")
      public String Save(CommandInfo cmdInfo)
      {
         try
         {
            if (doSave())
               log(LoggedEvent.INFO, cmdInfo.getCommand(), 
                   "Created new " + type() + " object - " + title());
            return null;
         }
         catch (NonUniqueObjectException ex)
         {
            log(LoggedEvent.ERROR, cmdInfo.getCommand(), DUPLICATE_KEY_CONSTRAINT_ERROR_MSG);
            fireValidationException(DUPLICATE_KEY_CONSTRAINT_ERROR_MSG);
            // solves a weird issue with postgresql where subsequent saving, after this error occurs, 
            //   claims that the id is not null and so it thinks the object is stale.  ditto for next exception
            if (isTransientState()) setID(null);
            return DUPLICATE_KEY_CONSTRAINT_ERROR_MSG;
         }
         catch (org.hibernate.exception.ConstraintViolationException ex)
         {
            log(LoggedEvent.ERROR, cmdInfo.getCommand(), DUPLICATE_KEY_CONSTRAINT_ERROR_MSG);
            fireValidationException(DUPLICATE_KEY_CONSTRAINT_ERROR_MSG);
            if (isTransientState()) setID(null);
            return DUPLICATE_KEY_CONSTRAINT_ERROR_MSG;
         }
         catch (org.hibernate.StaleObjectStateException ex)
         {
            fireValidationException(STALE_OBJECT_MSG);
            return null;
         }
      }
      @Cmd(mnemonic='c')
      public void Cancel(CommandInfo cmdInfo)
      {
         setNullState();
      }
      @Cmd(iconref="copy")
      public void Copy(CommandInfo cmdInfo)
      {
         ComplexEObject copy = (ComplexEObject) makeCopy();
         type().bufferCopy(copy);
      }
      @Cmd
      public void Paste(CommandInfo cmdInfo)
      {
         ComplexEObject copy = type().bufferCopy();
         if (copy == null)
         {
            return;
         }
         transferCopy(AbstractComplexEObject.this, copy, true);
      }
      @Cmd(mnemonic='l', blocks=true)
      public void SaveAndClose(CommandInfo cmdInfo)
      {
         if (doSave())
         {
            log(LoggedEvent.INFO, cmdInfo.getCommand(), "New Object Created/Persisted");
            vmech().dismiss(cmdInfo.getSource());
         }
      }
   }

   private static String DELETE_CONSTRAINT_ERROR_MSG =
         "Cannot delete objects still referenced by other objects in the system";
   private static String DUPLICATE_KEY_CONSTRAINT_ERROR_MSG =
         "Cannot save object:  duplicate value for a unique field";
   private static String STALE_OBJECT_MSG = 
         "Object has been modified by another user;  please refresh object";


   public class ReadState extends State
   {
      @Cmd
      public ComplexEObject Open(CommandInfo cmdInfo)
      {
         refresh();
         return AbstractComplexEObject.this;
      }
      
      @Cmd(iconref="copy")
      public void Copy(CommandInfo cmdInfo)
      {
         ComplexEObject copy = (ComplexEObject) makeCopy();
         type().bufferCopy(copy);
      }

      @Cmd(mnemonic='e', iconref="pencil")
      public ComplexEObject Edit(CommandInfo cmdInfo)
      {
         refresh();

         saveCopy();
         pushState(_editState);

         if (cmdInfo.getSource() instanceof Editor)
         {
            setEditor((Editor) cmdInfo.getSource());
            return null;
         }
         return AbstractComplexEObject.this;
      }

      @Cmd(sensitive = true, iconref="delete")
      public String Delete(CommandInfo cmdInfo)
      {
         try
         {
            delete();
            log(LoggedEvent.INFO, cmdInfo.getCommand(), 
                "Deleted " + type() + " object - " + title());
            return null;
         }
         catch (org.hibernate.exception.ConstraintViolationException ex)
         {
            log(LoggedEvent.ERROR, cmdInfo.getCommand(), DELETE_CONSTRAINT_ERROR_MSG);
            return DELETE_CONSTRAINT_ERROR_MSG;
         }
      }

      @Cmd
      public String ExportToJSON(CommandInfo cmdInfo, @Arg("Save to:") FileWEO file) throws Exception
      {
         JSON.writeJson(file.fileValue(), AbstractComplexEObject.this);
         return file.fileValue().getName() + " created.";
      }
      
      @Cmd(mnemonic='r', iconref="refresh")
      public void Refresh(CommandInfo cmdInfo)
      {
         refresh();
      }
      
   }


   public static String[] commandOrderEditState =
                           {"Save", "Cancel", "Copy", "Paste"};
   public static String[] commandOrderTransientState =
                           {"Save", "Cancel", "Copy", "Paste"};
   public static String[] commandOrderReadState =
                           {"Open", "Edit", "Delete", "Copy"};


   public class EditState extends EditableState
   {
      @Cmd(mnemonic='s', blocks=true, iconref="save")
      public String Save(CommandInfo cmdInfo)
      {
         try
         {
            if (doSave())
               log(LoggedEvent.INFO, cmdInfo.getCommand(), 
                   "Updated " + type() + " object - " + title());
            return null;
         }
         catch (org.hibernate.exception.ConstraintViolationException ex)
         {
            log(LoggedEvent.ERROR, cmdInfo.getCommand(), DUPLICATE_KEY_CONSTRAINT_ERROR_MSG);
            return DUPLICATE_KEY_CONSTRAINT_ERROR_MSG;
         }
         catch (org.hibernate.StaleObjectStateException ex)
         {
            fireValidationException(STALE_OBJECT_MSG);
            return null;
         }
      }
      @Cmd(mnemonic='c')
      public void Cancel(CommandInfo cmdInfo)
      {
         restoreCopy();
         popState();
      }
      @Cmd
      public void Copy(CommandInfo cmdInfo)
      {
         ComplexEObject copy = (ComplexEObject) makeCopy();
         type().bufferCopy(copy);
      }
      @Cmd
      public void Paste(CommandInfo cmdInfo)
      {
         ComplexEObject copy = type().bufferCopy();
         if (copy == null) return;
         transferCopy(AbstractComplexEObject.this, copy, true);
      }
      @Cmd(mnemonic='l', blocks=true)
      public void SaveAndClose(CommandInfo cmdInfo)
      {
         if (doSave())
         {
            log(LoggedEvent.INFO, cmdInfo.getCommand(), "Object updated");
            vmech().dismiss(cmdInfo.getSource());
         }
      }
   }


   public class NullState extends State {}


   // Part of contract with persistence mechanism (should be formalized via an interface):
   private Long _id;
   public Long getID() { return _id; }
   public void setID(Long id) { _id = id; }
   
   // Hibernate version field, for Optimistic Locking
   private Long _version;
   public Long getVersion() { return _version; }
   public void setVersion(Long version) { _version = version; }
   

   protected final DateTime _createdOn = new DateTime(new Date());
   public DateTime getCreatedOn() { return _createdOn; }

   // removing these for now as the feature I plan to introduce
   // which relies on them is not yet constructed
   // (the idea is not to actually delete the records, but to
   // mark them as deleted so can provide an undelete feature):
   
//   private final BooleanEO _deleted = new BooleanEO(false);
//   public BooleanEO getDeleted() { return _deleted; }
//
//   private final DateTime _deletedOn = new DateTime(new Date());
//   public DateTime getDeletedOn() { return _deletedOn; }

   //  Deleted Data Storage Feature:
   //  =============================
   // - filter out deleted items in listings, search fields, etc..
   // - special folder:  deleted items (ordered by deletion date)
   // - an undelete feature (removes deleted flag)
   // - rework 'delete' implementation
   // - a script to really delete deleted items, flexible enough where
   //     can use deletion date as a cutoff mechanism (e.g. "really delete all 
   //     deleted items that were deleted more than a week ago")
   //     


   // ========== implementation of Transferrable interface for dnd ===============
   public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException
   {
      if (!isDataFlavorSupported(flavor))
          throw new UnsupportedFlavorException(flavor);
      return this;
   }

   public DataFlavor[] getTransferDataFlavors()
   {
      return new DataFlavor[] { type().getFlavor() };
   }

   public boolean isDataFlavorSupported(DataFlavor f)
   {
      // was:
      //  return f.equals(type().getFlavor());
      
      // now: make sure that type() is assignable from flavor's type
      return type().getJavaClass().isAssignableFrom(f.getRepresentationClass());
   }



   // ====  app event support ...

   private transient AppEventSupport _support = new AppEventSupport(this);
   public void addAppEventListener(AppEventType evtType, AppEventListener l)
   {
      _support.addAppEventListener(evtType, l);
   }
   public void addAppEventListener(AppEventListener l, AppEventType... evtTypes)
   {
      for (AppEventType evtType : evtTypes)
      {
         addAppEventListener(evtType, l);
      }
   }
   public void removeAppEventListener(AppEventType evtType, AppEventListener l)
   {
      _support.removeAppEventListener(evtType, l);
   }
   public void fireAppEventNotification(AppEventType evtType)
   {
      _support.fireAppEventNotification(evtType);
   }
   public void fireAppEventNotification(AppEventType evtType, Object target)
   {
      _support.fireAppEventNotification(evtType, target);
   }



   // == searchable implementation..
   public List getInequalities()
   {
      if (field() == null)
         return new IdentityInequality(type()).getInequalities();
      return new IdentityInequality(field()).getInequalities();
   }


   // == misc..
   public ComplexEObject createInstance(Class typeClass)
   {
      return ComplexType.forClass(typeClass).instance();
   }


   // ==== tree model..

   private TreeModel _treeModel;
   public TreeModel treeModel()
   {
      if (_treeModel == null) _treeModel = new ETreeModel();
      return _treeModel;
   }

   class ETreeModel implements TreeModel
   {
      public Object getRoot()
      {
         return AbstractComplexEObject.this;
      }
      public Object getChild(Object parent, int index)
      {
         if (parent instanceof AbstractListEO)
         {
            AbstractListEO leo = (AbstractListEO) parent;
            return leo.getElementAt(index);
         }

         ComplexEObject ceo = (ComplexEObject) parent;
         Field field;
         int count = 0;
         for (int i=0; i<ceo.childFields().size(); i++)
         {
            field = (Field) ceo.childFields().get(i);
            if (field.isAtomic() || field.isChoice())
               continue;
            if (count == index)
            {
               return field.get(ceo);
            }
            count++;
         }

         throw new RuntimeException("EObject TreeModel Failed to find child for parent: "
               + parent + " at index " + index);

      }
      public int getChildCount(Object parent)
      {
         if (parent instanceof AbstractListEO)
         {
            AbstractListEO leo = (AbstractListEO) parent;
            return leo.getSize();
         }

         ComplexEObject ceo = (ComplexEObject) parent;

         int count = 0;
         Field field;
         for (int i=0; i<ceo.childFields().size(); i++)
         {
            field = (Field) ceo.childFields().get(i);
            if (field.isAtomic() || field.isChoice())
               continue;
            count++;
         }
         return count;

      }
      public boolean isLeaf(Object node)
      {
         if (node instanceof AbstractListEO) return false;
         if (!(node instanceof ComplexEObject)) return true;
         if (node instanceof Choice) return true;
         if (node instanceof NullComplexEObject) return true;
         return (getChildCount(node) == 0);
      }

      public int getIndexOfChild(Object parent, Object child)
      {
         if (parent instanceof AbstractListEO)
         {
            AbstractListEO leo = (AbstractListEO) parent;
            for (int i=0; i<leo.getSize(); i++)
            {
               if (child == leo.getElementAt(i))
                  return i;
            }
            return -1;
         }

         ComplexEObject ceo = (ComplexEObject) parent;

         int index = 0;
         Field field;
         for (int i=0; i<ceo.childFields().size(); i++)
         {
            field = (Field) ceo.childFields().get(i);
            if (field.isAtomic() || field.isChoice())
               continue;

            if (child == field.get(ceo))
               return index;

            index++;
         }
         return -1;
      }

      protected EventListenerList listenerList = new EventListenerList();

      public void addTreeModelListener(TreeModelListener listener)
      {
         listenerList.add(TreeModelListener.class, listener);
      }
      public void removeTreeModelListener(TreeModelListener listener)
      {
         listenerList.remove(TreeModelListener.class, listener);
      }
      public void valueForPathChanged(TreePath path, Object newValue)
      {
         ComplexEObject ceo;
         for (int i=0; i<path.getPathCount(); i++)
         {
            ceo = (ComplexEObject) path.getPathComponent(i);
            ceo.fireStateChanged();
         }
      }
   }

   public boolean isMeta() { return false; }

   // convenience..
   public void log(String typeString, EOCommand cmd, String msg)
   {
      app().log(typeString, cmd, msg);
   }
   public Logger tracer() { return Tracing.tracer(); }

   public Color colorCode()
   {
      return type().colorCode();
   }
}
