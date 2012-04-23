/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.jts.eclipse.cjsidl.CjsidlPackage;
import org.jts.eclipse.cjsidl.arrayDef;
import org.jts.eclipse.cjsidl.bitfieldDef;
import org.jts.eclipse.cjsidl.bodyDef;
import org.jts.eclipse.cjsidl.fixedFieldDef;
import org.jts.eclipse.cjsidl.fixedLenString;
import org.jts.eclipse.cjsidl.footerDef;
import org.jts.eclipse.cjsidl.headerDef;
import org.jts.eclipse.cjsidl.listDef;
import org.jts.eclipse.cjsidl.messageDef;
import org.jts.eclipse.cjsidl.recordDef;
import org.jts.eclipse.cjsidl.sequenceDef;
import org.jts.eclipse.cjsidl.typeDef;
import org.jts.eclipse.cjsidl.varField;
import org.jts.eclipse.cjsidl.varFormatField;
import org.jts.eclipse.cjsidl.varLenField;
import org.jts.eclipse.cjsidl.varLenString;
import org.jts.eclipse.cjsidl.variantDef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>type Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getMessageDef <em>Message Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getArrayDef <em>Array Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getRecordDef <em>Record Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getListDef <em>List Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getVariantDef <em>Variant Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getSequenceDef <em>Sequence Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getFixedFieldDef <em>Fixed Field Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getVarField <em>Var Field</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getBitfieldDef <em>Bitfield Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getFixedLenString <em>Fixed Len String</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getVarLenString <em>Var Len String</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getVarLenField <em>Var Len Field</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getVarFormatField <em>Var Format Field</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getHeaderDef <em>Header Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getBodyDef <em>Body Def</em>}</li>
 *   <li>{@link org.jts.eclipse.cjsidl.impl.typeDefImpl#getFooterDef <em>Footer Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class typeDefImpl extends MinimalEObjectImpl.Container implements typeDef
{
  /**
   * The cached value of the '{@link #getMessageDef() <em>Message Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessageDef()
   * @generated
   * @ordered
   */
  protected messageDef messageDef;

  /**
   * The cached value of the '{@link #getArrayDef() <em>Array Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArrayDef()
   * @generated
   * @ordered
   */
  protected arrayDef arrayDef;

  /**
   * The cached value of the '{@link #getRecordDef() <em>Record Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRecordDef()
   * @generated
   * @ordered
   */
  protected recordDef recordDef;

  /**
   * The cached value of the '{@link #getListDef() <em>List Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getListDef()
   * @generated
   * @ordered
   */
  protected listDef listDef;

  /**
   * The cached value of the '{@link #getVariantDef() <em>Variant Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariantDef()
   * @generated
   * @ordered
   */
  protected variantDef variantDef;

  /**
   * The cached value of the '{@link #getSequenceDef() <em>Sequence Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSequenceDef()
   * @generated
   * @ordered
   */
  protected sequenceDef sequenceDef;

  /**
   * The cached value of the '{@link #getFixedFieldDef() <em>Fixed Field Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFixedFieldDef()
   * @generated
   * @ordered
   */
  protected fixedFieldDef fixedFieldDef;

  /**
   * The cached value of the '{@link #getVarField() <em>Var Field</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVarField()
   * @generated
   * @ordered
   */
  protected varField varField;

  /**
   * The cached value of the '{@link #getBitfieldDef() <em>Bitfield Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBitfieldDef()
   * @generated
   * @ordered
   */
  protected bitfieldDef bitfieldDef;

  /**
   * The cached value of the '{@link #getFixedLenString() <em>Fixed Len String</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFixedLenString()
   * @generated
   * @ordered
   */
  protected fixedLenString fixedLenString;

  /**
   * The cached value of the '{@link #getVarLenString() <em>Var Len String</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVarLenString()
   * @generated
   * @ordered
   */
  protected varLenString varLenString;

  /**
   * The cached value of the '{@link #getVarLenField() <em>Var Len Field</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVarLenField()
   * @generated
   * @ordered
   */
  protected varLenField varLenField;

  /**
   * The cached value of the '{@link #getVarFormatField() <em>Var Format Field</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVarFormatField()
   * @generated
   * @ordered
   */
  protected varFormatField varFormatField;

  /**
   * The cached value of the '{@link #getHeaderDef() <em>Header Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHeaderDef()
   * @generated
   * @ordered
   */
  protected headerDef headerDef;

  /**
   * The cached value of the '{@link #getBodyDef() <em>Body Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBodyDef()
   * @generated
   * @ordered
   */
  protected bodyDef bodyDef;

  /**
   * The cached value of the '{@link #getFooterDef() <em>Footer Def</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFooterDef()
   * @generated
   * @ordered
   */
  protected footerDef footerDef;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected typeDefImpl()
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
    return CjsidlPackage.eINSTANCE.gettypeDef();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public messageDef getMessageDef()
  {
    return messageDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMessageDef(messageDef newMessageDef, NotificationChain msgs)
  {
    messageDef oldMessageDef = messageDef;
    messageDef = newMessageDef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__MESSAGE_DEF, oldMessageDef, newMessageDef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMessageDef(messageDef newMessageDef)
  {
    if (newMessageDef != messageDef)
    {
      NotificationChain msgs = null;
      if (messageDef != null)
        msgs = ((InternalEObject)messageDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__MESSAGE_DEF, null, msgs);
      if (newMessageDef != null)
        msgs = ((InternalEObject)newMessageDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__MESSAGE_DEF, null, msgs);
      msgs = basicSetMessageDef(newMessageDef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__MESSAGE_DEF, newMessageDef, newMessageDef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public arrayDef getArrayDef()
  {
    return arrayDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetArrayDef(arrayDef newArrayDef, NotificationChain msgs)
  {
    arrayDef oldArrayDef = arrayDef;
    arrayDef = newArrayDef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__ARRAY_DEF, oldArrayDef, newArrayDef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArrayDef(arrayDef newArrayDef)
  {
    if (newArrayDef != arrayDef)
    {
      NotificationChain msgs = null;
      if (arrayDef != null)
        msgs = ((InternalEObject)arrayDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__ARRAY_DEF, null, msgs);
      if (newArrayDef != null)
        msgs = ((InternalEObject)newArrayDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__ARRAY_DEF, null, msgs);
      msgs = basicSetArrayDef(newArrayDef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__ARRAY_DEF, newArrayDef, newArrayDef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public recordDef getRecordDef()
  {
    return recordDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRecordDef(recordDef newRecordDef, NotificationChain msgs)
  {
    recordDef oldRecordDef = recordDef;
    recordDef = newRecordDef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__RECORD_DEF, oldRecordDef, newRecordDef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRecordDef(recordDef newRecordDef)
  {
    if (newRecordDef != recordDef)
    {
      NotificationChain msgs = null;
      if (recordDef != null)
        msgs = ((InternalEObject)recordDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__RECORD_DEF, null, msgs);
      if (newRecordDef != null)
        msgs = ((InternalEObject)newRecordDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__RECORD_DEF, null, msgs);
      msgs = basicSetRecordDef(newRecordDef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__RECORD_DEF, newRecordDef, newRecordDef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public listDef getListDef()
  {
    return listDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetListDef(listDef newListDef, NotificationChain msgs)
  {
    listDef oldListDef = listDef;
    listDef = newListDef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__LIST_DEF, oldListDef, newListDef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setListDef(listDef newListDef)
  {
    if (newListDef != listDef)
    {
      NotificationChain msgs = null;
      if (listDef != null)
        msgs = ((InternalEObject)listDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__LIST_DEF, null, msgs);
      if (newListDef != null)
        msgs = ((InternalEObject)newListDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__LIST_DEF, null, msgs);
      msgs = basicSetListDef(newListDef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__LIST_DEF, newListDef, newListDef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public variantDef getVariantDef()
  {
    return variantDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVariantDef(variantDef newVariantDef, NotificationChain msgs)
  {
    variantDef oldVariantDef = variantDef;
    variantDef = newVariantDef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__VARIANT_DEF, oldVariantDef, newVariantDef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVariantDef(variantDef newVariantDef)
  {
    if (newVariantDef != variantDef)
    {
      NotificationChain msgs = null;
      if (variantDef != null)
        msgs = ((InternalEObject)variantDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__VARIANT_DEF, null, msgs);
      if (newVariantDef != null)
        msgs = ((InternalEObject)newVariantDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__VARIANT_DEF, null, msgs);
      msgs = basicSetVariantDef(newVariantDef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__VARIANT_DEF, newVariantDef, newVariantDef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public sequenceDef getSequenceDef()
  {
    return sequenceDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSequenceDef(sequenceDef newSequenceDef, NotificationChain msgs)
  {
    sequenceDef oldSequenceDef = sequenceDef;
    sequenceDef = newSequenceDef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__SEQUENCE_DEF, oldSequenceDef, newSequenceDef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSequenceDef(sequenceDef newSequenceDef)
  {
    if (newSequenceDef != sequenceDef)
    {
      NotificationChain msgs = null;
      if (sequenceDef != null)
        msgs = ((InternalEObject)sequenceDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__SEQUENCE_DEF, null, msgs);
      if (newSequenceDef != null)
        msgs = ((InternalEObject)newSequenceDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__SEQUENCE_DEF, null, msgs);
      msgs = basicSetSequenceDef(newSequenceDef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__SEQUENCE_DEF, newSequenceDef, newSequenceDef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public fixedFieldDef getFixedFieldDef()
  {
    return fixedFieldDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFixedFieldDef(fixedFieldDef newFixedFieldDef, NotificationChain msgs)
  {
    fixedFieldDef oldFixedFieldDef = fixedFieldDef;
    fixedFieldDef = newFixedFieldDef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__FIXED_FIELD_DEF, oldFixedFieldDef, newFixedFieldDef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFixedFieldDef(fixedFieldDef newFixedFieldDef)
  {
    if (newFixedFieldDef != fixedFieldDef)
    {
      NotificationChain msgs = null;
      if (fixedFieldDef != null)
        msgs = ((InternalEObject)fixedFieldDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__FIXED_FIELD_DEF, null, msgs);
      if (newFixedFieldDef != null)
        msgs = ((InternalEObject)newFixedFieldDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__FIXED_FIELD_DEF, null, msgs);
      msgs = basicSetFixedFieldDef(newFixedFieldDef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__FIXED_FIELD_DEF, newFixedFieldDef, newFixedFieldDef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public varField getVarField()
  {
    return varField;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVarField(varField newVarField, NotificationChain msgs)
  {
    varField oldVarField = varField;
    varField = newVarField;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__VAR_FIELD, oldVarField, newVarField);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVarField(varField newVarField)
  {
    if (newVarField != varField)
    {
      NotificationChain msgs = null;
      if (varField != null)
        msgs = ((InternalEObject)varField).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__VAR_FIELD, null, msgs);
      if (newVarField != null)
        msgs = ((InternalEObject)newVarField).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__VAR_FIELD, null, msgs);
      msgs = basicSetVarField(newVarField, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__VAR_FIELD, newVarField, newVarField));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public bitfieldDef getBitfieldDef()
  {
    return bitfieldDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBitfieldDef(bitfieldDef newBitfieldDef, NotificationChain msgs)
  {
    bitfieldDef oldBitfieldDef = bitfieldDef;
    bitfieldDef = newBitfieldDef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__BITFIELD_DEF, oldBitfieldDef, newBitfieldDef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBitfieldDef(bitfieldDef newBitfieldDef)
  {
    if (newBitfieldDef != bitfieldDef)
    {
      NotificationChain msgs = null;
      if (bitfieldDef != null)
        msgs = ((InternalEObject)bitfieldDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__BITFIELD_DEF, null, msgs);
      if (newBitfieldDef != null)
        msgs = ((InternalEObject)newBitfieldDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__BITFIELD_DEF, null, msgs);
      msgs = basicSetBitfieldDef(newBitfieldDef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__BITFIELD_DEF, newBitfieldDef, newBitfieldDef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public fixedLenString getFixedLenString()
  {
    return fixedLenString;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFixedLenString(fixedLenString newFixedLenString, NotificationChain msgs)
  {
    fixedLenString oldFixedLenString = fixedLenString;
    fixedLenString = newFixedLenString;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__FIXED_LEN_STRING, oldFixedLenString, newFixedLenString);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFixedLenString(fixedLenString newFixedLenString)
  {
    if (newFixedLenString != fixedLenString)
    {
      NotificationChain msgs = null;
      if (fixedLenString != null)
        msgs = ((InternalEObject)fixedLenString).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__FIXED_LEN_STRING, null, msgs);
      if (newFixedLenString != null)
        msgs = ((InternalEObject)newFixedLenString).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__FIXED_LEN_STRING, null, msgs);
      msgs = basicSetFixedLenString(newFixedLenString, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__FIXED_LEN_STRING, newFixedLenString, newFixedLenString));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public varLenString getVarLenString()
  {
    return varLenString;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVarLenString(varLenString newVarLenString, NotificationChain msgs)
  {
    varLenString oldVarLenString = varLenString;
    varLenString = newVarLenString;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__VAR_LEN_STRING, oldVarLenString, newVarLenString);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVarLenString(varLenString newVarLenString)
  {
    if (newVarLenString != varLenString)
    {
      NotificationChain msgs = null;
      if (varLenString != null)
        msgs = ((InternalEObject)varLenString).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__VAR_LEN_STRING, null, msgs);
      if (newVarLenString != null)
        msgs = ((InternalEObject)newVarLenString).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__VAR_LEN_STRING, null, msgs);
      msgs = basicSetVarLenString(newVarLenString, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__VAR_LEN_STRING, newVarLenString, newVarLenString));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public varLenField getVarLenField()
  {
    return varLenField;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVarLenField(varLenField newVarLenField, NotificationChain msgs)
  {
    varLenField oldVarLenField = varLenField;
    varLenField = newVarLenField;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__VAR_LEN_FIELD, oldVarLenField, newVarLenField);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVarLenField(varLenField newVarLenField)
  {
    if (newVarLenField != varLenField)
    {
      NotificationChain msgs = null;
      if (varLenField != null)
        msgs = ((InternalEObject)varLenField).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__VAR_LEN_FIELD, null, msgs);
      if (newVarLenField != null)
        msgs = ((InternalEObject)newVarLenField).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__VAR_LEN_FIELD, null, msgs);
      msgs = basicSetVarLenField(newVarLenField, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__VAR_LEN_FIELD, newVarLenField, newVarLenField));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public varFormatField getVarFormatField()
  {
    return varFormatField;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVarFormatField(varFormatField newVarFormatField, NotificationChain msgs)
  {
    varFormatField oldVarFormatField = varFormatField;
    varFormatField = newVarFormatField;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__VAR_FORMAT_FIELD, oldVarFormatField, newVarFormatField);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVarFormatField(varFormatField newVarFormatField)
  {
    if (newVarFormatField != varFormatField)
    {
      NotificationChain msgs = null;
      if (varFormatField != null)
        msgs = ((InternalEObject)varFormatField).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__VAR_FORMAT_FIELD, null, msgs);
      if (newVarFormatField != null)
        msgs = ((InternalEObject)newVarFormatField).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__VAR_FORMAT_FIELD, null, msgs);
      msgs = basicSetVarFormatField(newVarFormatField, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__VAR_FORMAT_FIELD, newVarFormatField, newVarFormatField));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public headerDef getHeaderDef()
  {
    return headerDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetHeaderDef(headerDef newHeaderDef, NotificationChain msgs)
  {
    headerDef oldHeaderDef = headerDef;
    headerDef = newHeaderDef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__HEADER_DEF, oldHeaderDef, newHeaderDef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHeaderDef(headerDef newHeaderDef)
  {
    if (newHeaderDef != headerDef)
    {
      NotificationChain msgs = null;
      if (headerDef != null)
        msgs = ((InternalEObject)headerDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__HEADER_DEF, null, msgs);
      if (newHeaderDef != null)
        msgs = ((InternalEObject)newHeaderDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__HEADER_DEF, null, msgs);
      msgs = basicSetHeaderDef(newHeaderDef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__HEADER_DEF, newHeaderDef, newHeaderDef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public bodyDef getBodyDef()
  {
    return bodyDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBodyDef(bodyDef newBodyDef, NotificationChain msgs)
  {
    bodyDef oldBodyDef = bodyDef;
    bodyDef = newBodyDef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__BODY_DEF, oldBodyDef, newBodyDef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBodyDef(bodyDef newBodyDef)
  {
    if (newBodyDef != bodyDef)
    {
      NotificationChain msgs = null;
      if (bodyDef != null)
        msgs = ((InternalEObject)bodyDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__BODY_DEF, null, msgs);
      if (newBodyDef != null)
        msgs = ((InternalEObject)newBodyDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__BODY_DEF, null, msgs);
      msgs = basicSetBodyDef(newBodyDef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__BODY_DEF, newBodyDef, newBodyDef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public footerDef getFooterDef()
  {
    return footerDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFooterDef(footerDef newFooterDef, NotificationChain msgs)
  {
    footerDef oldFooterDef = footerDef;
    footerDef = newFooterDef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__FOOTER_DEF, oldFooterDef, newFooterDef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFooterDef(footerDef newFooterDef)
  {
    if (newFooterDef != footerDef)
    {
      NotificationChain msgs = null;
      if (footerDef != null)
        msgs = ((InternalEObject)footerDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__FOOTER_DEF, null, msgs);
      if (newFooterDef != null)
        msgs = ((InternalEObject)newFooterDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CjsidlPackage.TYPE_DEF__FOOTER_DEF, null, msgs);
      msgs = basicSetFooterDef(newFooterDef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CjsidlPackage.TYPE_DEF__FOOTER_DEF, newFooterDef, newFooterDef));
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
      case CjsidlPackage.TYPE_DEF__MESSAGE_DEF:
        return basicSetMessageDef(null, msgs);
      case CjsidlPackage.TYPE_DEF__ARRAY_DEF:
        return basicSetArrayDef(null, msgs);
      case CjsidlPackage.TYPE_DEF__RECORD_DEF:
        return basicSetRecordDef(null, msgs);
      case CjsidlPackage.TYPE_DEF__LIST_DEF:
        return basicSetListDef(null, msgs);
      case CjsidlPackage.TYPE_DEF__VARIANT_DEF:
        return basicSetVariantDef(null, msgs);
      case CjsidlPackage.TYPE_DEF__SEQUENCE_DEF:
        return basicSetSequenceDef(null, msgs);
      case CjsidlPackage.TYPE_DEF__FIXED_FIELD_DEF:
        return basicSetFixedFieldDef(null, msgs);
      case CjsidlPackage.TYPE_DEF__VAR_FIELD:
        return basicSetVarField(null, msgs);
      case CjsidlPackage.TYPE_DEF__BITFIELD_DEF:
        return basicSetBitfieldDef(null, msgs);
      case CjsidlPackage.TYPE_DEF__FIXED_LEN_STRING:
        return basicSetFixedLenString(null, msgs);
      case CjsidlPackage.TYPE_DEF__VAR_LEN_STRING:
        return basicSetVarLenString(null, msgs);
      case CjsidlPackage.TYPE_DEF__VAR_LEN_FIELD:
        return basicSetVarLenField(null, msgs);
      case CjsidlPackage.TYPE_DEF__VAR_FORMAT_FIELD:
        return basicSetVarFormatField(null, msgs);
      case CjsidlPackage.TYPE_DEF__HEADER_DEF:
        return basicSetHeaderDef(null, msgs);
      case CjsidlPackage.TYPE_DEF__BODY_DEF:
        return basicSetBodyDef(null, msgs);
      case CjsidlPackage.TYPE_DEF__FOOTER_DEF:
        return basicSetFooterDef(null, msgs);
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
      case CjsidlPackage.TYPE_DEF__MESSAGE_DEF:
        return getMessageDef();
      case CjsidlPackage.TYPE_DEF__ARRAY_DEF:
        return getArrayDef();
      case CjsidlPackage.TYPE_DEF__RECORD_DEF:
        return getRecordDef();
      case CjsidlPackage.TYPE_DEF__LIST_DEF:
        return getListDef();
      case CjsidlPackage.TYPE_DEF__VARIANT_DEF:
        return getVariantDef();
      case CjsidlPackage.TYPE_DEF__SEQUENCE_DEF:
        return getSequenceDef();
      case CjsidlPackage.TYPE_DEF__FIXED_FIELD_DEF:
        return getFixedFieldDef();
      case CjsidlPackage.TYPE_DEF__VAR_FIELD:
        return getVarField();
      case CjsidlPackage.TYPE_DEF__BITFIELD_DEF:
        return getBitfieldDef();
      case CjsidlPackage.TYPE_DEF__FIXED_LEN_STRING:
        return getFixedLenString();
      case CjsidlPackage.TYPE_DEF__VAR_LEN_STRING:
        return getVarLenString();
      case CjsidlPackage.TYPE_DEF__VAR_LEN_FIELD:
        return getVarLenField();
      case CjsidlPackage.TYPE_DEF__VAR_FORMAT_FIELD:
        return getVarFormatField();
      case CjsidlPackage.TYPE_DEF__HEADER_DEF:
        return getHeaderDef();
      case CjsidlPackage.TYPE_DEF__BODY_DEF:
        return getBodyDef();
      case CjsidlPackage.TYPE_DEF__FOOTER_DEF:
        return getFooterDef();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case CjsidlPackage.TYPE_DEF__MESSAGE_DEF:
        setMessageDef((messageDef)newValue);
        return;
      case CjsidlPackage.TYPE_DEF__ARRAY_DEF:
        setArrayDef((arrayDef)newValue);
        return;
      case CjsidlPackage.TYPE_DEF__RECORD_DEF:
        setRecordDef((recordDef)newValue);
        return;
      case CjsidlPackage.TYPE_DEF__LIST_DEF:
        setListDef((listDef)newValue);
        return;
      case CjsidlPackage.TYPE_DEF__VARIANT_DEF:
        setVariantDef((variantDef)newValue);
        return;
      case CjsidlPackage.TYPE_DEF__SEQUENCE_DEF:
        setSequenceDef((sequenceDef)newValue);
        return;
      case CjsidlPackage.TYPE_DEF__FIXED_FIELD_DEF:
        setFixedFieldDef((fixedFieldDef)newValue);
        return;
      case CjsidlPackage.TYPE_DEF__VAR_FIELD:
        setVarField((varField)newValue);
        return;
      case CjsidlPackage.TYPE_DEF__BITFIELD_DEF:
        setBitfieldDef((bitfieldDef)newValue);
        return;
      case CjsidlPackage.TYPE_DEF__FIXED_LEN_STRING:
        setFixedLenString((fixedLenString)newValue);
        return;
      case CjsidlPackage.TYPE_DEF__VAR_LEN_STRING:
        setVarLenString((varLenString)newValue);
        return;
      case CjsidlPackage.TYPE_DEF__VAR_LEN_FIELD:
        setVarLenField((varLenField)newValue);
        return;
      case CjsidlPackage.TYPE_DEF__VAR_FORMAT_FIELD:
        setVarFormatField((varFormatField)newValue);
        return;
      case CjsidlPackage.TYPE_DEF__HEADER_DEF:
        setHeaderDef((headerDef)newValue);
        return;
      case CjsidlPackage.TYPE_DEF__BODY_DEF:
        setBodyDef((bodyDef)newValue);
        return;
      case CjsidlPackage.TYPE_DEF__FOOTER_DEF:
        setFooterDef((footerDef)newValue);
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
      case CjsidlPackage.TYPE_DEF__MESSAGE_DEF:
        setMessageDef((messageDef)null);
        return;
      case CjsidlPackage.TYPE_DEF__ARRAY_DEF:
        setArrayDef((arrayDef)null);
        return;
      case CjsidlPackage.TYPE_DEF__RECORD_DEF:
        setRecordDef((recordDef)null);
        return;
      case CjsidlPackage.TYPE_DEF__LIST_DEF:
        setListDef((listDef)null);
        return;
      case CjsidlPackage.TYPE_DEF__VARIANT_DEF:
        setVariantDef((variantDef)null);
        return;
      case CjsidlPackage.TYPE_DEF__SEQUENCE_DEF:
        setSequenceDef((sequenceDef)null);
        return;
      case CjsidlPackage.TYPE_DEF__FIXED_FIELD_DEF:
        setFixedFieldDef((fixedFieldDef)null);
        return;
      case CjsidlPackage.TYPE_DEF__VAR_FIELD:
        setVarField((varField)null);
        return;
      case CjsidlPackage.TYPE_DEF__BITFIELD_DEF:
        setBitfieldDef((bitfieldDef)null);
        return;
      case CjsidlPackage.TYPE_DEF__FIXED_LEN_STRING:
        setFixedLenString((fixedLenString)null);
        return;
      case CjsidlPackage.TYPE_DEF__VAR_LEN_STRING:
        setVarLenString((varLenString)null);
        return;
      case CjsidlPackage.TYPE_DEF__VAR_LEN_FIELD:
        setVarLenField((varLenField)null);
        return;
      case CjsidlPackage.TYPE_DEF__VAR_FORMAT_FIELD:
        setVarFormatField((varFormatField)null);
        return;
      case CjsidlPackage.TYPE_DEF__HEADER_DEF:
        setHeaderDef((headerDef)null);
        return;
      case CjsidlPackage.TYPE_DEF__BODY_DEF:
        setBodyDef((bodyDef)null);
        return;
      case CjsidlPackage.TYPE_DEF__FOOTER_DEF:
        setFooterDef((footerDef)null);
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
      case CjsidlPackage.TYPE_DEF__MESSAGE_DEF:
        return messageDef != null;
      case CjsidlPackage.TYPE_DEF__ARRAY_DEF:
        return arrayDef != null;
      case CjsidlPackage.TYPE_DEF__RECORD_DEF:
        return recordDef != null;
      case CjsidlPackage.TYPE_DEF__LIST_DEF:
        return listDef != null;
      case CjsidlPackage.TYPE_DEF__VARIANT_DEF:
        return variantDef != null;
      case CjsidlPackage.TYPE_DEF__SEQUENCE_DEF:
        return sequenceDef != null;
      case CjsidlPackage.TYPE_DEF__FIXED_FIELD_DEF:
        return fixedFieldDef != null;
      case CjsidlPackage.TYPE_DEF__VAR_FIELD:
        return varField != null;
      case CjsidlPackage.TYPE_DEF__BITFIELD_DEF:
        return bitfieldDef != null;
      case CjsidlPackage.TYPE_DEF__FIXED_LEN_STRING:
        return fixedLenString != null;
      case CjsidlPackage.TYPE_DEF__VAR_LEN_STRING:
        return varLenString != null;
      case CjsidlPackage.TYPE_DEF__VAR_LEN_FIELD:
        return varLenField != null;
      case CjsidlPackage.TYPE_DEF__VAR_FORMAT_FIELD:
        return varFormatField != null;
      case CjsidlPackage.TYPE_DEF__HEADER_DEF:
        return headerDef != null;
      case CjsidlPackage.TYPE_DEF__BODY_DEF:
        return bodyDef != null;
      case CjsidlPackage.TYPE_DEF__FOOTER_DEF:
        return footerDef != null;
    }
    return super.eIsSet(featureID);
  }

} //typeDefImpl
