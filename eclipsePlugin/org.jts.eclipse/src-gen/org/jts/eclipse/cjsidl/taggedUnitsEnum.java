/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>tagged Units Enum</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getConst_tag <em>Const tag</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getTag <em>Tag</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getScopedTag <em>Scoped Tag</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getName <em>Name</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getType <em>Type</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getFieldUnit <em>Field Unit</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getValueSetDef <em>Value Set Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getScaledRangeDef <em>Scaled Range Def</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettaggedUnitsEnum()
 * @model
 * @generated
 */
public interface taggedUnitsEnum extends EObject
{
  /**
   * Returns the value of the '<em><b>Const tag</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Const tag</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Const tag</em>' attribute.
   * @see #setConst_tag(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettaggedUnitsEnum_Const_tag()
   * @model
   * @generated
   */
  String getConst_tag();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getConst_tag <em>Const tag</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Const tag</em>' attribute.
   * @see #getConst_tag()
   * @generated
   */
  void setConst_tag(String value);

  /**
   * Returns the value of the '<em><b>Tag</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tag</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tag</em>' containment reference.
   * @see #setTag(constReference)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettaggedUnitsEnum_Tag()
   * @model containment="true"
   * @generated
   */
  constReference getTag();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getTag <em>Tag</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tag</em>' containment reference.
   * @see #getTag()
   * @generated
   */
  void setTag(constReference value);

  /**
   * Returns the value of the '<em><b>Scoped Tag</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scoped Tag</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scoped Tag</em>' containment reference.
   * @see #setScopedTag(scopedConstId)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettaggedUnitsEnum_ScopedTag()
   * @model containment="true"
   * @generated
   */
  scopedConstId getScopedTag();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getScopedTag <em>Scoped Tag</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Scoped Tag</em>' containment reference.
   * @see #getScopedTag()
   * @generated
   */
  void setScopedTag(scopedConstId value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettaggedUnitsEnum_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(simpleNumericType)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettaggedUnitsEnum_Type()
   * @model containment="true"
   * @generated
   */
  simpleNumericType getType();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(simpleNumericType value);

  /**
   * Returns the value of the '<em><b>Field Unit</b></em>' attribute.
   * The literals are from the enumeration {@link org.jts.eclipse.cjsidl.UNIT}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Field Unit</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Field Unit</em>' attribute.
   * @see org.jts.eclipse.cjsidl.UNIT
   * @see #setFieldUnit(UNIT)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettaggedUnitsEnum_FieldUnit()
   * @model
   * @generated
   */
  UNIT getFieldUnit();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getFieldUnit <em>Field Unit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Field Unit</em>' attribute.
   * @see org.jts.eclipse.cjsidl.UNIT
   * @see #getFieldUnit()
   * @generated
   */
  void setFieldUnit(UNIT value);

  /**
   * Returns the value of the '<em><b>Value Set Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value Set Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value Set Def</em>' containment reference.
   * @see #setValueSetDef(valueSetDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettaggedUnitsEnum_ValueSetDef()
   * @model containment="true"
   * @generated
   */
  valueSetDef getValueSetDef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getValueSetDef <em>Value Set Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value Set Def</em>' containment reference.
   * @see #getValueSetDef()
   * @generated
   */
  void setValueSetDef(valueSetDef value);

  /**
   * Returns the value of the '<em><b>Scaled Range Def</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scaled Range Def</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scaled Range Def</em>' containment reference.
   * @see #setScaledRangeDef(scaledRangeDef)
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#gettaggedUnitsEnum_ScaledRangeDef()
   * @model containment="true"
   * @generated
   */
  scaledRangeDef getScaledRangeDef();

  /**
   * Sets the value of the '{@link org.jts.eclipse.cjsidl.taggedUnitsEnum#getScaledRangeDef <em>Scaled Range Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Scaled Range Def</em>' containment reference.
   * @see #getScaledRangeDef()
   * @generated
   */
  void setScaledRangeDef(scaledRangeDef value);

} // taggedUnitsEnum
