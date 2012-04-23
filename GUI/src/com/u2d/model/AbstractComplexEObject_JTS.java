package com.u2d.model;

import com.u2d.reflection.Cmd;
import com.u2d.element.CommandInfo;
import com.u2d.type.composite.LoggedEvent;

public abstract class AbstractComplexEObject_JTS extends AbstractComplexEObject {
	
  // make items sortable by their name in lists
  public static String sortBy = "name";
  
  private static String DELETE_CONSTRAINT_ERROR_MSG =
         "Cannot delete objects still referenced by other objects in the system";

  public AbstractComplexEObject_JTS() {
    super();
  }

  @Cmd(sensitive = true, iconref = "delete")
  public String Delete(CommandInfo cmdInfo) {
    try {
      delete();
      log(LoggedEvent.INFO, cmdInfo.getCommand(),
              "Deleted " + type() + " object - " + title());
      return null;
    } catch (org.hibernate.exception.ConstraintViolationException ex) {
      log(LoggedEvent.ERROR, cmdInfo.getCommand(), DELETE_CONSTRAINT_ERROR_MSG);
      return DELETE_CONSTRAINT_ERROR_MSG;
    } catch (org.hibernate.LazyInitializationException ex) {
      return null;
    }
  }
  @Override
  public int validate() { 
	   return org.jts.gui.Validation.validateAndDuplicateCheck( this );
	 }

}
