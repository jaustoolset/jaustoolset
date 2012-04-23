/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>type Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getMessageDef <em>Message Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getArrayDef <em>Array Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getRecordDef <em>Record Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getListDef <em>List Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getVariantDef <em>Variant Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getSequenceDef <em>Sequence Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getFixedFieldDef <em>Fixed Field Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getVarField <em>Var Field</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getBitfieldDef <em>Bitfield Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getFixedLenString <em>Fixed Len String</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getVarLenString <em>Var Len String</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getVarLenField <em>Var Len Field</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getVarFormatField <em>Var Format Field</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getHeaderDef <em>Header Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getBodyDef <em>Body Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.typeDef#getFooterDef <em>Footer Def</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef()
 * @model
 * @generated
 */
public interface typeDef extends EObject
{
  /**
   * Returns the value of the '<em><b>Message Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Message Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Message Def</em>' containment reference.
   * @see #setMessageDef(messageDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_MessageDef()
   * @model containment="true"
   * @generated
   */
  messageDef getMessageDef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getMessageDef <em>Message Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Message Def</em>' containment reference.
   * @see #getMessageDef()
   * @generated
   */
  void setMessageDef(messageDef value);

  /**
   * Returns the value of the '<em><b>Array Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Array Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array Def</em>' containment reference.
   * @see #setArrayDef(arrayDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_ArrayDef()
   * @model containment="true"
   * @generated
   */
  arrayDef getArrayDef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getArrayDef <em>Array Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array Def</em>' containment reference.
   * @see #getArrayDef()
   * @generated
   */
  void setArrayDef(arrayDef value);

  /**
   * Returns the value of the '<em><b>Record Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Record Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Record Def</em>' containment reference.
   * @see #setRecordDef(recordDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_RecordDef()
   * @model containment="true"
   * @generated
   */
  recordDef getRecordDef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getRecordDef <em>Record Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Record Def</em>' containment reference.
   * @see #getRecordDef()
   * @generated
   */
  void setRecordDef(recordDef value);

  /**
   * Returns the value of the '<em><b>List Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>List Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>List Def</em>' containment reference.
   * @see #setListDef(listDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_ListDef()
   * @model containment="true"
   * @generated
   */
  listDef getListDef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getListDef <em>List Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>List Def</em>' containment reference.
   * @see #getListDef()
   * @generated
   */
  void setListDef(listDef value);

  /**
   * Returns the value of the '<em><b>Variant Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variant Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variant Def</em>' containment reference.
   * @see #setVariantDef(variantDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_VariantDef()
   * @model containment="true"
   * @generated
   */
  variantDef getVariantDef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getVariantDef <em>Variant Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Variant Def</em>' containment reference.
   * @see #getVariantDef()
   * @generated
   */
  void setVariantDef(variantDef value);

  /**
   * Returns the value of the '<em><b>Sequence Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sequence Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sequence Def</em>' containment reference.
   * @see #setSequenceDef(sequenceDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_SequenceDef()
   * @model containment="true"
   * @generated
   */
  sequenceDef getSequenceDef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getSequenceDef <em>Sequence Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sequence Def</em>' containment reference.
   * @see #getSequenceDef()
   * @generated
   */
  void setSequenceDef(sequenceDef value);

  /**
   * Returns the value of the '<em><b>Fixed Field Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fixed Field Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fixed Field Def</em>' containment reference.
   * @see #setFixedFieldDef(fixedFieldDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_FixedFieldDef()
   * @model containment="true"
   * @generated
   */
  fixedFieldDef getFixedFieldDef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getFixedFieldDef <em>Fixed Field Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fixed Field Def</em>' containment reference.
   * @see #getFixedFieldDef()
   * @generated
   */
  void setFixedFieldDef(fixedFieldDef value);

  /**
   * Returns the value of the '<em><b>Var Field</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Var Field</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Var Field</em>' containment reference.
   * @see #setVarField(varField)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_VarField()
   * @model containment="true"
   * @generated
   */
  varField getVarField();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getVarField <em>Var Field</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var Field</em>' containment reference.
   * @see #getVarField()
   * @generated
   */
  void setVarField(varField value);

  /**
   * Returns the value of the '<em><b>Bitfield Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bitfield Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bitfield Def</em>' containment reference.
   * @see #setBitfieldDef(bitfieldDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_BitfieldDef()
   * @model containment="true"
   * @generated
   */
  bitfieldDef getBitfieldDef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getBitfieldDef <em>Bitfield Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bitfield Def</em>' containment reference.
   * @see #getBitfieldDef()
   * @generated
   */
  void setBitfieldDef(bitfieldDef value);

  /**
   * Returns the value of the '<em><b>Fixed Len String</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fixed Len String</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fixed Len String</em>' containment reference.
   * @see #setFixedLenString(fixedLenString)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_FixedLenString()
   * @model containment="true"
   * @generated
   */
  fixedLenString getFixedLenString();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getFixedLenString <em>Fixed Len String</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fixed Len String</em>' containment reference.
   * @see #getFixedLenString()
   * @generated
   */
  void setFixedLenString(fixedLenString value);

  /**
   * Returns the value of the '<em><b>Var Len String</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Var Len String</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Var Len String</em>' containment reference.
   * @see #setVarLenString(varLenString)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_VarLenString()
   * @model containment="true"
   * @generated
   */
  varLenString getVarLenString();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getVarLenString <em>Var Len String</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var Len String</em>' containment reference.
   * @see #getVarLenString()
   * @generated
   */
  void setVarLenString(varLenString value);

  /**
   * Returns the value of the '<em><b>Var Len Field</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Var Len Field</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Var Len Field</em>' containment reference.
   * @see #setVarLenField(varLenField)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_VarLenField()
   * @model containment="true"
   * @generated
   */
  varLenField getVarLenField();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getVarLenField <em>Var Len Field</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var Len Field</em>' containment reference.
   * @see #getVarLenField()
   * @generated
   */
  void setVarLenField(varLenField value);

  /**
   * Returns the value of the '<em><b>Var Format Field</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Var Format Field</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Var Format Field</em>' containment reference.
   * @see #setVarFormatField(varFormatField)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_VarFormatField()
   * @model containment="true"
   * @generated
   */
  varFormatField getVarFormatField();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getVarFormatField <em>Var Format Field</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var Format Field</em>' containment reference.
   * @see #getVarFormatField()
   * @generated
   */
  void setVarFormatField(varFormatField value);

  /**
   * Returns the value of the '<em><b>Header Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Header Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Header Def</em>' containment reference.
   * @see #setHeaderDef(headerDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_HeaderDef()
   * @model containment="true"
   * @generated
   */
  headerDef getHeaderDef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getHeaderDef <em>Header Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Header Def</em>' containment reference.
   * @see #getHeaderDef()
   * @generated
   */
  void setHeaderDef(headerDef value);

  /**
   * Returns the value of the '<em><b>Body Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Body Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Body Def</em>' containment reference.
   * @see #setBodyDef(bodyDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_BodyDef()
   * @model containment="true"
   * @generated
   */
  bodyDef getBodyDef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getBodyDef <em>Body Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Body Def</em>' containment reference.
   * @see #getBodyDef()
   * @generated
   */
  void setBodyDef(bodyDef value);

  /**
   * Returns the value of the '<em><b>Footer Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Footer Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Footer Def</em>' containment reference.
   * @see #setFooterDef(footerDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettypeDef_FooterDef()
   * @model containment="true"
   * @generated
   */
  footerDef getFooterDef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.typeDef#getFooterDef <em>Footer Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Footer Def</em>' containment reference.
   * @see #getFooterDef()
   * @generated
   */
  void setFooterDef(footerDef value);

} // typeDef
