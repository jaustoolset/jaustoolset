/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.jts.eclipse.cjsidl.CjsidlPackage;
import org.jts.eclipse.cjsidl.arrayDef;
import org.jts.eclipse.cjsidl.bitfieldDef;
import org.jts.eclipse.cjsidl.fixedFieldDef;
import org.jts.eclipse.cjsidl.fixedLenString;
import org.jts.eclipse.cjsidl.recordDef;
import org.jts.eclipse.cjsidl.scopedTypeId;
import org.jts.eclipse.cjsidl.typeReference;
import org.jts.eclipse.cjsidl.varField;
import org.jts.eclipse.cjsidl.varFormatField;
import org.jts.eclipse.cjsidl.varLenField;
import org.jts.eclipse.cjsidl.varLenString;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>record Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.recordDefImpl#getArrayDef <em>Array Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.recordDefImpl#getFieldDef <em>Field Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.recordDefImpl#getVariableFieldDef <em>Variable Field Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.recordDefImpl#getBitfieldDef <em>Bitfield Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.recordDefImpl#getFixedLengthStringDef <em>Fixed Length String Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.recordDefImpl#getVariableLengthStringDef <em>Variable Length String Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.recordDefImpl#getVariableLengthFieldDef <em>Variable Length Field Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.recordDefImpl#getVarFormatField <em>Var Format Field</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.recordDefImpl#getTypeReference <em>Type Reference</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.recordDefImpl#getScopedRef <em>Scoped Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class recordDefImpl extends containerDefImpl implements recordDef
{
  /**
   * The cached value of the '{@link #getArrayDef() <em>Array Def</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArrayDef()
   * @generated
   * @ordered
   */
  protected EList<arrayDef> arrayDef;

  /**
   * The cached value of the '{@link #getFieldDef() <em>Field Def</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFieldDef()
   * @generated
   * @ordered
   */
  protected EList<fixedFieldDef> fieldDef;

  /**
   * The cached value of the '{@link #getVariableFieldDef() <em>Variable Field Def</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariableFieldDef()
   * @generated
   * @ordered
   */
  protected EList<varField> variableFieldDef;

  /**
   * The cached value of the '{@link #getBitfieldDef() <em>Bitfield Def</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBitfieldDef()
   * @generated
   * @ordered
   */
  protected EList<bitfieldDef> bitfieldDef;

  /**
   * The cached value of the '{@link #getFixedLengthStringDef() <em>Fixed Length String Def</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFixedLengthStringDef()
   * @generated
   * @ordered
   */
  protected EList<fixedLenString> fixedLengthStringDef;

  /**
   * The cached value of the '{@link #getVariableLengthStringDef() <em>Variable Length String Def</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariableLengthStringDef()
   * @generated
   * @ordered
   */
  protected EList<varLenString> variableLengthStringDef;

  /**
   * The cached value of the '{@link #getVariableLengthFieldDef() <em>Variable Length Field Def</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariableLengthFieldDef()
   * @generated
   * @ordered
   */
  protected EList<varLenField> variableLengthFieldDef;

  /**
   * The cached value of the '{@link #getVarFormatField() <em>Var Format Field</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVarFormatField()
   * @generated
   * @ordered
   */
  protected EList<varFormatField> varFormatField;

  /**
   * The cached value of the '{@link #getTypeReference() <em>Type Reference</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeReference()
   * @generated
   * @ordered
   */
  protected EList<typeReference> typeReference;

  /**
   * The cached value of the '{@link #getScopedRef() <em>Scoped Ref</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScopedRef()
   * @generated
   * @ordered
   */
  protected EList<scopedTypeId> scopedRef;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected recordDefImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return CjsidlPackage.eINSTANCE.getrecordDef();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<arrayDef> getArrayDef()
  {
    if (arrayDef == null)
    {
      arrayDef = new EObjectContainmentEList<arrayDef>(arrayDef.class, this, CjsidlPackage.RECORD_DEF__ARRAY_DEF);
    }
    return arrayDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<fixedFieldDef> getFieldDef()
  {
    if (fieldDef == null)
    {
      fieldDef = new EObjectContainmentEList<fixedFieldDef>(fixedFieldDef.class, this, CjsidlPackage.RECORD_DEF__FIELD_DEF);
    }
    return fieldDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<varField> getVariableFieldDef()
  {
    if (variableFieldDef == null)
    {
      variableFieldDef = new EObjectContainmentEList<varField>(varField.class, this, CjsidlPackage.RECORD_DEF__VARIABLE_FIELD_DEF);
    }
    return variableFieldDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<bitfieldDef> getBitfieldDef()
  {
    if (bitfieldDef == null)
    {
      bitfieldDef = new EObjectContainmentEList<bitfieldDef>(bitfieldDef.class, this, CjsidlPackage.RECORD_DEF__BITFIELD_DEF);
    }
    return bitfieldDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<fixedLenString> getFixedLengthStringDef()
  {
    if (fixedLengthStringDef == null)
    {
      fixedLengthStringDef = new EObjectContainmentEList<fixedLenString>(fixedLenString.class, this, CjsidlPackage.RECORD_DEF__FIXED_LENGTH_STRING_DEF);
    }
    return fixedLengthStringDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<varLenString> getVariableLengthStringDef()
  {
    if (variableLengthStringDef == null)
    {
      variableLengthStringDef = new EObjectContainmentEList<varLenString>(varLenString.class, this, CjsidlPackage.RECORD_DEF__VARIABLE_LENGTH_STRING_DEF);
    }
    return variableLengthStringDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<varLenField> getVariableLengthFieldDef()
  {
    if (variableLengthFieldDef == null)
    {
      variableLengthFieldDef = new EObjectContainmentEList<varLenField>(varLenField.class, this, CjsidlPackage.RECORD_DEF__VARIABLE_LENGTH_FIELD_DEF);
    }
    return variableLengthFieldDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<varFormatField> getVarFormatField()
  {
    if (varFormatField == null)
    {
      varFormatField = new EObjectContainmentEList<varFormatField>(varFormatField.class, this, CjsidlPackage.RECORD_DEF__VAR_FORMAT_FIELD);
    }
    return varFormatField;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<typeReference> getTypeReference()
  {
    if (typeReference == null)
    {
      typeReference = new EObjectContainmentEList<typeReference>(typeReference.class, this, CjsidlPackage.RECORD_DEF__TYPE_REFERENCE);
    }
    return typeReference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<scopedTypeId> getScopedRef()
  {
    if (scopedRef == null)
    {
      scopedRef = new EObjectContainmentEList<scopedTypeId>(scopedTypeId.class, this, CjsidlPackage.RECORD_DEF__SCOPED_REF);
    }
    return scopedRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case CjsidlPackage.RECORD_DEF__ARRAY_DEF:
        return ((InternalEList<?>)getArrayDef()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.RECORD_DEF__FIELD_DEF:
        return ((InternalEList<?>)getFieldDef()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.RECORD_DEF__VARIABLE_FIELD_DEF:
        return ((InternalEList<?>)getVariableFieldDef()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.RECORD_DEF__BITFIELD_DEF:
        return ((InternalEList<?>)getBitfieldDef()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.RECORD_DEF__FIXED_LENGTH_STRING_DEF:
        return ((InternalEList<?>)getFixedLengthStringDef()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.RECORD_DEF__VARIABLE_LENGTH_STRING_DEF:
        return ((InternalEList<?>)getVariableLengthStringDef()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.RECORD_DEF__VARIABLE_LENGTH_FIELD_DEF:
        return ((InternalEList<?>)getVariableLengthFieldDef()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.RECORD_DEF__VAR_FORMAT_FIELD:
        return ((InternalEList<?>)getVarFormatField()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.RECORD_DEF__TYPE_REFERENCE:
        return ((InternalEList<?>)getTypeReference()).basicRemove(otherEnd, msgs);
      case CjsidlPackage.RECORD_DEF__SCOPED_REF:
        return ((InternalEList<?>)getScopedRef()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case CjsidlPackage.RECORD_DEF__ARRAY_DEF:
        return getArrayDef();
      case CjsidlPackage.RECORD_DEF__FIELD_DEF:
        return getFieldDef();
      case CjsidlPackage.RECORD_DEF__VARIABLE_FIELD_DEF:
        return getVariableFieldDef();
      case CjsidlPackage.RECORD_DEF__BITFIELD_DEF:
        return getBitfieldDef();
      case CjsidlPackage.RECORD_DEF__FIXED_LENGTH_STRING_DEF:
        return getFixedLengthStringDef();
      case CjsidlPackage.RECORD_DEF__VARIABLE_LENGTH_STRING_DEF:
        return getVariableLengthStringDef();
      case CjsidlPackage.RECORD_DEF__VARIABLE_LENGTH_FIELD_DEF:
        return getVariableLengthFieldDef();
      case CjsidlPackage.RECORD_DEF__VAR_FORMAT_FIELD:
        return getVarFormatField();
      case CjsidlPackage.RECORD_DEF__TYPE_REFERENCE:
        return getTypeReference();
      case CjsidlPackage.RECORD_DEF__SCOPED_REF:
        return getScopedRef();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case CjsidlPackage.RECORD_DEF__ARRAY_DEF:
        getArrayDef().clear();
        getArrayDef().addAll((Collection<? extends arrayDef>)newValue);
        return;
      case CjsidlPackage.RECORD_DEF__FIELD_DEF:
        getFieldDef().clear();
        getFieldDef().addAll((Collection<? extends fixedFieldDef>)newValue);
        return;
      case CjsidlPackage.RECORD_DEF__VARIABLE_FIELD_DEF:
        getVariableFieldDef().clear();
        getVariableFieldDef().addAll((Collection<? extends varField>)newValue);
        return;
      case CjsidlPackage.RECORD_DEF__BITFIELD_DEF:
        getBitfieldDef().clear();
        getBitfieldDef().addAll((Collection<? extends bitfieldDef>)newValue);
        return;
      case CjsidlPackage.RECORD_DEF__FIXED_LENGTH_STRING_DEF:
        getFixedLengthStringDef().clear();
        getFixedLengthStringDef().addAll((Collection<? extends fixedLenString>)newValue);
        return;
      case CjsidlPackage.RECORD_DEF__VARIABLE_LENGTH_STRING_DEF:
        getVariableLengthStringDef().clear();
        getVariableLengthStringDef().addAll((Collection<? extends varLenString>)newValue);
        return;
      case CjsidlPackage.RECORD_DEF__VARIABLE_LENGTH_FIELD_DEF:
        getVariableLengthFieldDef().clear();
        getVariableLengthFieldDef().addAll((Collection<? extends varLenField>)newValue);
        return;
      case CjsidlPackage.RECORD_DEF__VAR_FORMAT_FIELD:
        getVarFormatField().clear();
        getVarFormatField().addAll((Collection<? extends varFormatField>)newValue);
        return;
      case CjsidlPackage.RECORD_DEF__TYPE_REFERENCE:
        getTypeReference().clear();
        getTypeReference().addAll((Collection<? extends typeReference>)newValue);
        return;
      case CjsidlPackage.RECORD_DEF__SCOPED_REF:
        getScopedRef().clear();
        getScopedRef().addAll((Collection<? extends scopedTypeId>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case CjsidlPackage.RECORD_DEF__ARRAY_DEF:
        getArrayDef().clear();
        return;
      case CjsidlPackage.RECORD_DEF__FIELD_DEF:
        getFieldDef().clear();
        return;
      case CjsidlPackage.RECORD_DEF__VARIABLE_FIELD_DEF:
        getVariableFieldDef().clear();
        return;
      case CjsidlPackage.RECORD_DEF__BITFIELD_DEF:
        getBitfieldDef().clear();
        return;
      case CjsidlPackage.RECORD_DEF__FIXED_LENGTH_STRING_DEF:
        getFixedLengthStringDef().clear();
        return;
      case CjsidlPackage.RECORD_DEF__VARIABLE_LENGTH_STRING_DEF:
        getVariableLengthStringDef().clear();
        return;
      case CjsidlPackage.RECORD_DEF__VARIABLE_LENGTH_FIELD_DEF:
        getVariableLengthFieldDef().clear();
        return;
      case CjsidlPackage.RECORD_DEF__VAR_FORMAT_FIELD:
        getVarFormatField().clear();
        return;
      case CjsidlPackage.RECORD_DEF__TYPE_REFERENCE:
        getTypeReference().clear();
        return;
      case CjsidlPackage.RECORD_DEF__SCOPED_REF:
        getScopedRef().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case CjsidlPackage.RECORD_DEF__ARRAY_DEF:
        return arrayDef != null && !arrayDef.isEmpty();
      case CjsidlPackage.RECORD_DEF__FIELD_DEF:
        return fieldDef != null && !fieldDef.isEmpty();
      case CjsidlPackage.RECORD_DEF__VARIABLE_FIELD_DEF:
        return variableFieldDef != null && !variableFieldDef.isEmpty();
      case CjsidlPackage.RECORD_DEF__BITFIELD_DEF:
        return bitfieldDef != null && !bitfieldDef.isEmpty();
      case CjsidlPackage.RECORD_DEF__FIXED_LENGTH_STRING_DEF:
        return fixedLengthStringDef != null && !fixedLengthStringDef.isEmpty();
      case CjsidlPackage.RECORD_DEF__VARIABLE_LENGTH_STRING_DEF:
        return variableLengthStringDef != null && !variableLengthStringDef.isEmpty();
      case CjsidlPackage.RECORD_DEF__VARIABLE_LENGTH_FIELD_DEF:
        return variableLengthFieldDef != null && !variableLengthFieldDef.isEmpty();
      case CjsidlPackage.RECORD_DEF__VAR_FORMAT_FIELD:
        return varFormatField != null && !varFormatField.isEmpty();
      case CjsidlPackage.RECORD_DEF__TYPE_REFERENCE:
        return typeReference != null && !typeReference.isEmpty();
      case CjsidlPackage.RECORD_DEF__SCOPED_REF:
        return scopedRef != null && !scopedRef.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //recordDefImpl
