/**
 * <copyright>
 * </copyright>
 *

 */
package org.jts.eclipse.cjsidl.impl;

import java.io.IOException;

import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import org.jts.eclipse.cjsidl.CjsidlFactory;
import org.jts.eclipse.cjsidl.CjsidlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CjsidlPackageImpl extends EPackageImpl implements CjsidlPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected String packageFilename = "cjsidl.ecore";

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass jausEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass serviceDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass descriptionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass referencesEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass refAttrEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass declaredConstSetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass declaredConstSetRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass declaredTypeSetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass messageSetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass messagesEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass internalEventSetEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass eventDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass messageRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass protocolBehaviorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass startStateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass stateMachineEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass stateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass defaultStateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass entryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass exitEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass transParamsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass transParamEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass transitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass defaultTransitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass internalTransitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass simpleTransitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pushTransitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass popTransitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nextStateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass guardEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass guardActionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass guardParamEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass actionListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sendActionListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass actionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass constDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass declaredTypeSetRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass typeDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass messageDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass headerDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass bodyDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass footerDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass headerRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass bodyRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass footerRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass headerScopedRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass bodyScopedRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass footerScopedRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass containerDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass containerRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass declaredEventDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass simpleNumericTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fixedLenStringEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass varLenStringEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fixedFieldDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass varFieldEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass varLenFieldEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass taggedUnitsEnumEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass varFormatFieldEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass formatEnumDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass valueSetDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass bitfieldDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass valueRangeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass valueSpecEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass scaledRangeDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass subFieldEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass listDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variantDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass taggedItemDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sequenceDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass recordDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass constReferenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass typeReferenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass arrayDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass messageScopedRefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass scopedTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass scopedEventTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass scopedTypeIdEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass scopedConstIdEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum fielD_FORMATEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum unitEEnum = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.jts.eclipse.cjsidl.CjsidlPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private CjsidlPackageImpl()
  {
    super(eNS_URI, CjsidlFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link CjsidlPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @generated
   */
  public static CjsidlPackage init()
  {
    if (isInited) return (CjsidlPackage)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI);

    // Obtain or create and register package
    CjsidlPackageImpl theCjsidlPackage = (CjsidlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CjsidlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CjsidlPackageImpl());

    isInited = true;

    // Load packages
    theCjsidlPackage.loadPackage();

    // Fix loaded packages
    theCjsidlPackage.fixPackageContents();

    // Mark meta-data to indicate it can't be changed
    theCjsidlPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(CjsidlPackage.eNS_URI, theCjsidlPackage);
    return theCjsidlPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getjaus()
  {
    if (jausEClass == null)
    {
      jausEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(0);
    }
    return jausEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getjaus_Set()
  {
        return (EReference)getjaus().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getserviceDef()
  {
    if (serviceDefEClass == null)
    {
      serviceDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(1);
    }
    return serviceDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getserviceDef_ServiceName()
  {
        return (EAttribute)getserviceDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getserviceDef_Name()
  {
        return (EAttribute)getserviceDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getserviceDef_ServiceVersion()
  {
        return (EAttribute)getserviceDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getserviceDef_Descr()
  {
        return (EReference)getserviceDef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getserviceDef_Assumpt()
  {
        return (EAttribute)getserviceDef().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getserviceDef_Refs()
  {
        return (EReference)getserviceDef().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getserviceDef_ConstSet()
  {
        return (EReference)getserviceDef().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getserviceDef_TypeSet()
  {
        return (EReference)getserviceDef().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getserviceDef_MessageSet()
  {
        return (EReference)getserviceDef().getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getserviceDef_InternalEventSet()
  {
        return (EReference)getserviceDef().getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getserviceDef_ProtocolBehavior()
  {
        return (EReference)getserviceDef().getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdescription()
  {
    if (descriptionEClass == null)
    {
      descriptionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(2);
    }
    return descriptionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdescription_Content()
  {
        return (EAttribute)getdescription().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getreferences()
  {
    if (referencesEClass == null)
    {
      referencesEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(3);
    }
    return referencesEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getreferences_RefInherit()
  {
        return (EReference)getreferences().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getreferences_RefClient()
  {
        return (EReference)getreferences().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getrefAttr()
  {
    if (refAttrEClass == null)
    {
      refAttrEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(4);
    }
    return refAttrEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getrefAttr_Comment()
  {
        return (EAttribute)getrefAttr().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getrefAttr_ImportedNamespace()
  {
        return (EReference)getrefAttr().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getrefAttr_Name()
  {
        return (EAttribute)getrefAttr().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdeclaredConstSet()
  {
    if (declaredConstSetEClass == null)
    {
      declaredConstSetEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(5);
    }
    return declaredConstSetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdeclaredConstSet_ConstName()
  {
        return (EAttribute)getdeclaredConstSet().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdeclaredConstSet_Name()
  {
        return (EAttribute)getdeclaredConstSet().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdeclaredConstSet_ConstSetVersion()
  {
        return (EAttribute)getdeclaredConstSet().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdeclaredConstSet_DeclaredConstSetRef()
  {
        return (EReference)getdeclaredConstSet().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdeclaredConstSet_ConstDef()
  {
        return (EReference)getdeclaredConstSet().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdeclaredConstSetRef()
  {
    if (declaredConstSetRefEClass == null)
    {
      declaredConstSetRefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(6);
    }
    return declaredConstSetRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdeclaredConstSetRef_Comment()
  {
        return (EAttribute)getdeclaredConstSetRef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdeclaredConstSetRef_ImportedNamespace()
  {
        return (EReference)getdeclaredConstSetRef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdeclaredConstSetRef_Name()
  {
        return (EAttribute)getdeclaredConstSetRef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdeclaredTypeSet()
  {
    if (declaredTypeSetEClass == null)
    {
      declaredTypeSetEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(7);
    }
    return declaredTypeSetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdeclaredTypeSet_TypeName()
  {
        return (EAttribute)getdeclaredTypeSet().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdeclaredTypeSet_Name()
  {
        return (EAttribute)getdeclaredTypeSet().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdeclaredTypeSet_Version()
  {
        return (EAttribute)getdeclaredTypeSet().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdeclaredTypeSet_DeclaredConstSetRef()
  {
        return (EReference)getdeclaredTypeSet().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdeclaredTypeSet_DeclaredTypeSetRef()
  {
        return (EReference)getdeclaredTypeSet().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdeclaredTypeSet_TypeDef()
  {
        return (EReference)getdeclaredTypeSet().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdeclaredTypeSet_TypeRef()
  {
        return (EReference)getdeclaredTypeSet().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdeclaredTypeSet_ScopedRef()
  {
        return (EReference)getdeclaredTypeSet().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmessageSet()
  {
    if (messageSetEClass == null)
    {
      messageSetEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(8);
    }
    return messageSetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmessageSet_Comment()
  {
        return (EAttribute)getmessageSet().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmessageSet_InputComment()
  {
        return (EAttribute)getmessageSet().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmessageSet_InputSet()
  {
        return (EReference)getmessageSet().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmessageSet_OutputComment()
  {
        return (EAttribute)getmessageSet().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmessageSet_OutputSet()
  {
        return (EReference)getmessageSet().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmessages()
  {
    if (messagesEClass == null)
    {
      messagesEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(9);
    }
    return messagesEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmessages_MessageDefs()
  {
        return (EReference)getmessages().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmessages_TypeRefs()
  {
        return (EReference)getmessages().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmessages_ScopedRefs()
  {
        return (EReference)getmessages().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getinternalEventSet()
  {
    if (internalEventSetEClass == null)
    {
      internalEventSetEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(10);
    }
    return internalEventSetEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getinternalEventSet_Comment()
  {
        return (EAttribute)getinternalEventSet().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getinternalEventSet_Defs()
  {
        return (EReference)getinternalEventSet().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass geteventDef()
  {
    if (eventDefEClass == null)
    {
      eventDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(11);
    }
    return eventDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute geteventDef_Name()
  {
        return (EAttribute)geteventDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference geteventDef_Descr()
  {
        return (EReference)geteventDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference geteventDef_Header()
  {
        return (EReference)geteventDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference geteventDef_Body()
  {
        return (EReference)geteventDef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference geteventDef_Footer()
  {
        return (EReference)geteventDef().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmessageRef()
  {
    if (messageRefEClass == null)
    {
      messageRefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(12);
    }
    return messageRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmessageRef_Comment()
  {
        return (EAttribute)getmessageRef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmessageRef_Ref()
  {
        return (EReference)getmessageRef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmessageRef_Name()
  {
        return (EAttribute)getmessageRef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getprotocolBehavior()
  {
    if (protocolBehaviorEClass == null)
    {
      protocolBehaviorEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(13);
    }
    return protocolBehaviorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getprotocolBehavior_Comment()
  {
        return (EAttribute)getprotocolBehavior().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getprotocolBehavior_Stateless()
  {
        return (EAttribute)getprotocolBehavior().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getprotocolBehavior_StateMachine()
  {
        return (EReference)getprotocolBehavior().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getstartState()
  {
    if (startStateEClass == null)
    {
      startStateEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(14);
    }
    return startStateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getstartState_Comment()
  {
        return (EAttribute)getstartState().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstartState_Scoped()
  {
        return (EReference)getstartState().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstartState_State()
  {
        return (EReference)getstartState().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getstateMachine()
  {
    if (stateMachineEClass == null)
    {
      stateMachineEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(15);
    }
    return stateMachineEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getstateMachine_Comment()
  {
        return (EAttribute)getstateMachine().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstateMachine_Scoped()
  {
        return (EReference)getstateMachine().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getstateMachine_Name()
  {
        return (EAttribute)getstateMachine().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstateMachine_StartState()
  {
        return (EReference)getstateMachine().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstateMachine_DefaultState()
  {
        return (EReference)getstateMachine().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstateMachine_States()
  {
        return (EReference)getstateMachine().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getstate()
  {
    if (stateEClass == null)
    {
      stateEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(16);
    }
    return stateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getstate_Comment()
  {
        return (EAttribute)getstate().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getstate_Initial()
  {
        return (EAttribute)getstate().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getstate_Name()
  {
        return (EAttribute)getstate().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstate_EntryAction()
  {
        return (EReference)getstate().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstate_ExitAction()
  {
        return (EReference)getstate().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstate_Transitions()
  {
        return (EReference)getstate().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstate_DefaultTransition()
  {
        return (EReference)getstate().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstate_DefaultState()
  {
        return (EReference)getstate().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstate_SubState()
  {
        return (EReference)getstate().getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdefaultState()
  {
    if (defaultStateEClass == null)
    {
      defaultStateEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(17);
    }
    return defaultStateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdefaultState_Comment()
  {
        return (EAttribute)getdefaultState().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdefaultState_Transition()
  {
        return (EReference)getdefaultState().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdefaultState_DefaultTransition()
  {
        return (EReference)getdefaultState().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getentry()
  {
    if (entryEClass == null)
    {
      entryEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(18);
    }
    return entryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getentry_Comment()
  {
        return (EAttribute)getentry().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getentry_Actions()
  {
        return (EReference)getentry().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getentry_SendActions()
  {
        return (EReference)getentry().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getexit()
  {
    if (exitEClass == null)
    {
      exitEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(19);
    }
    return exitEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getexit_Comment()
  {
        return (EAttribute)getexit().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getexit_Actions()
  {
        return (EReference)getexit().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getexit_SendActions()
  {
        return (EReference)getexit().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass gettransParams()
  {
    if (transParamsEClass == null)
    {
      transParamsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(20);
    }
    return transParamsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettransParams_Params()
  {
        return (EReference)gettransParams().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass gettransParam()
  {
    if (transParamEClass == null)
    {
      transParamEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(21);
    }
    return transParamEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettransParam_Comment()
  {
        return (EAttribute)gettransParam().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettransParam_Type()
  {
        return (EReference)gettransParam().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettransParam_ScopedEventType()
  {
        return (EReference)gettransParam().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettransParam_UnsignedType()
  {
        return (EAttribute)gettransParam().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettransParam_Name()
  {
        return (EAttribute)gettransParam().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass gettransition()
  {
    if (transitionEClass == null)
    {
      transitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(22);
    }
    return transitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettransition_Comment()
  {
        return (EAttribute)gettransition().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettransition_Type()
  {
        return (EAttribute)gettransition().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettransition_Scoped()
  {
        return (EReference)gettransition().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettransition_Name()
  {
        return (EAttribute)gettransition().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettransition_Params()
  {
        return (EReference)gettransition().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettransition_TransGuard()
  {
        return (EReference)gettransition().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettransition_Actions()
  {
        return (EReference)gettransition().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettransition_SendActions()
  {
        return (EReference)gettransition().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettransition_Destination()
  {
        return (EReference)gettransition().getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdefaultTransition()
  {
    if (defaultTransitionEClass == null)
    {
      defaultTransitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(23);
    }
    return defaultTransitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdefaultTransition_Comment()
  {
        return (EAttribute)getdefaultTransition().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdefaultTransition_Type()
  {
        return (EAttribute)getdefaultTransition().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdefaultTransition_TransGuard()
  {
        return (EReference)getdefaultTransition().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdefaultTransition_Actions()
  {
        return (EReference)getdefaultTransition().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdefaultTransition_SendActions()
  {
        return (EReference)getdefaultTransition().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdefaultTransition_Destination()
  {
        return (EReference)getdefaultTransition().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getinternalTransition()
  {
    if (internalTransitionEClass == null)
    {
      internalTransitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(24);
    }
    return internalTransitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getinternalTransition_Comment()
  {
        return (EAttribute)getinternalTransition().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getsimpleTransition()
  {
    if (simpleTransitionEClass == null)
    {
      simpleTransitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(25);
    }
    return simpleTransitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getsimpleTransition_Comment()
  {
        return (EAttribute)getsimpleTransition().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getsimpleTransition_NextState()
  {
        return (EReference)getsimpleTransition().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getpushTransition()
  {
    if (pushTransitionEClass == null)
    {
      pushTransitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(26);
    }
    return pushTransitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getpushTransition_Comment()
  {
        return (EAttribute)getpushTransition().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getpushTransition_NextState()
  {
        return (EReference)getpushTransition().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getpushTransition_SimpleTransition()
  {
        return (EReference)getpushTransition().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getpopTransition()
  {
    if (popTransitionEClass == null)
    {
      popTransitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(27);
    }
    return popTransitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getpopTransition_Comment()
  {
        return (EAttribute)getpopTransition().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getpopTransition_SecondaryTransition()
  {
        return (EReference)getpopTransition().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getpopTransition_Param()
  {
        return (EReference)getpopTransition().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getnextState()
  {
    if (nextStateEClass == null)
    {
      nextStateEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(28);
    }
    return nextStateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getnextState_Comment()
  {
        return (EAttribute)getnextState().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getnextState_FirstState()
  {
        return (EReference)getnextState().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getnextState_Scoped()
  {
        return (EReference)getnextState().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getnextState_NextState()
  {
        return (EReference)getnextState().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getguard()
  {
    if (guardEClass == null)
    {
      guardEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(29);
    }
    return guardEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getguard_Comment()
  {
        return (EAttribute)getguard().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getguard_GuardAction()
  {
        return (EReference)getguard().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getguard_Equiv()
  {
        return (EAttribute)getguard().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getguard_LogicalOperator()
  {
        return (EAttribute)getguard().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getguardAction()
  {
    if (guardActionEClass == null)
    {
      guardActionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(30);
    }
    return guardActionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getguardAction_Not()
  {
        return (EAttribute)getguardAction().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getguardAction_Name()
  {
        return (EAttribute)getguardAction().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getguardAction_Param()
  {
        return (EReference)getguardAction().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getguardParam()
  {
    if (guardParamEClass == null)
    {
      guardParamEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(31);
    }
    return guardParamEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getguardParam_Parameter()
  {
        return (EReference)getguardParam().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getguardParam_GuardConst()
  {
        return (EAttribute)getguardParam().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getactionList()
  {
    if (actionListEClass == null)
    {
      actionListEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(32);
    }
    return actionListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getactionList_Actions()
  {
        return (EReference)getactionList().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getsendActionList()
  {
    if (sendActionListEClass == null)
    {
      sendActionListEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(33);
    }
    return sendActionListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getsendActionList_Actions()
  {
        return (EReference)getsendActionList().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getaction()
  {
    if (actionEClass == null)
    {
      actionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(34);
    }
    return actionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getaction_Comment()
  {
        return (EAttribute)getaction().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getaction_Name()
  {
        return (EAttribute)getaction().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getaction_Param()
  {
        return (EReference)getaction().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getconstDef()
  {
    if (constDefEClass == null)
    {
      constDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(35);
    }
    return constDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getconstDef_Comment()
  {
        return (EAttribute)getconstDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getconstDef_ConstType()
  {
        return (EReference)getconstDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getconstDef_Name()
  {
        return (EAttribute)getconstDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getconstDef_ConstValue()
  {
        return (EAttribute)getconstDef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getconstDef_FieldUnits()
  {
        return (EAttribute)getconstDef().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdeclaredTypeSetRef()
  {
    if (declaredTypeSetRefEClass == null)
    {
      declaredTypeSetRefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(36);
    }
    return declaredTypeSetRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdeclaredTypeSetRef_Comment()
  {
        return (EAttribute)getdeclaredTypeSetRef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdeclaredTypeSetRef_ImportedNamespace()
  {
        return (EReference)getdeclaredTypeSetRef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdeclaredTypeSetRef_Name()
  {
        return (EAttribute)getdeclaredTypeSetRef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass gettypeDef()
  {
    if (typeDefEClass == null)
    {
      typeDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(37);
    }
    return typeDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_MessageDef()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_ArrayDef()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_RecordDef()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_ListDef()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_VariantDef()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_SequenceDef()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_FixedFieldDef()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_VarField()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_BitfieldDef()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_FixedLenString()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_VarLenString()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_VarLenField()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_VarFormatField()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_HeaderDef()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_BodyDef()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeDef_FooterDef()
  {
        return (EReference)gettypeDef().getEStructuralFeatures().get(15);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmessageDef()
  {
    if (messageDefEClass == null)
    {
      messageDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(38);
    }
    return messageDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmessageDef_Command()
  {
        return (EAttribute)getmessageDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmessageDef_MessageID()
  {
        return (EAttribute)getmessageDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmessageDef_Name()
  {
        return (EAttribute)getmessageDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmessageDef_Descr()
  {
        return (EReference)getmessageDef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmessageDef_Header()
  {
        return (EReference)getmessageDef().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmessageDef_Body()
  {
        return (EReference)getmessageDef().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmessageDef_Footer()
  {
        return (EReference)getmessageDef().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getheaderDef()
  {
    if (headerDefEClass == null)
    {
      headerDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(39);
    }
    return headerDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getheaderDef_Comment()
  {
        return (EAttribute)getheaderDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getheaderDef_Name()
  {
        return (EAttribute)getheaderDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getheaderDef_RecordListSequenceVariant()
  {
        return (EReference)getheaderDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getbodyDef()
  {
    if (bodyDefEClass == null)
    {
      bodyDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(40);
    }
    return bodyDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getbodyDef_Comment()
  {
        return (EAttribute)getbodyDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getbodyDef_Name()
  {
        return (EAttribute)getbodyDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getbodyDef_RecordListSequenceVariant()
  {
        return (EReference)getbodyDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getfooterDef()
  {
    if (footerDefEClass == null)
    {
      footerDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(41);
    }
    return footerDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfooterDef_Comment()
  {
        return (EAttribute)getfooterDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfooterDef_Name()
  {
        return (EAttribute)getfooterDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfooterDef_RecordListSequenceVariant()
  {
        return (EReference)getfooterDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getheaderRef()
  {
    if (headerRefEClass == null)
    {
      headerRefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(42);
    }
    return headerRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getheaderRef_Comment()
  {
        return (EAttribute)getheaderRef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getheaderRef_TypeRef()
  {
        return (EReference)getheaderRef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getheaderRef_ScopedRef()
  {
        return (EReference)getheaderRef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getheaderRef_Name()
  {
        return (EAttribute)getheaderRef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getbodyRef()
  {
    if (bodyRefEClass == null)
    {
      bodyRefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(43);
    }
    return bodyRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getbodyRef_Comment()
  {
        return (EAttribute)getbodyRef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getbodyRef_TypeRef()
  {
        return (EReference)getbodyRef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getbodyRef_ScopedRef()
  {
        return (EReference)getbodyRef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getbodyRef_Name()
  {
        return (EAttribute)getbodyRef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getfooterRef()
  {
    if (footerRefEClass == null)
    {
      footerRefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(44);
    }
    return footerRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfooterRef_Comment()
  {
        return (EAttribute)getfooterRef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfooterRef_TypeRef()
  {
        return (EReference)getfooterRef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfooterRef_ScopedRef()
  {
        return (EReference)getfooterRef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfooterRef_Name()
  {
        return (EAttribute)getfooterRef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getheaderScopedRef()
  {
    if (headerScopedRefEClass == null)
    {
      headerScopedRefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(45);
    }
    return headerScopedRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getheaderScopedRef_Name()
  {
        return (EReference)getheaderScopedRef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getheaderScopedRef_Names()
  {
        return (EReference)getheaderScopedRef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getheaderScopedRef_Type()
  {
        return (EReference)getheaderScopedRef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getbodyScopedRef()
  {
    if (bodyScopedRefEClass == null)
    {
      bodyScopedRefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(46);
    }
    return bodyScopedRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getbodyScopedRef_Name()
  {
        return (EReference)getbodyScopedRef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getbodyScopedRef_Names()
  {
        return (EReference)getbodyScopedRef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getbodyScopedRef_Type()
  {
        return (EReference)getbodyScopedRef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getfooterScopedRef()
  {
    if (footerScopedRefEClass == null)
    {
      footerScopedRefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(47);
    }
    return footerScopedRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfooterScopedRef_Name()
  {
        return (EReference)getfooterScopedRef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfooterScopedRef_Names()
  {
        return (EReference)getfooterScopedRef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfooterScopedRef_Type()
  {
        return (EReference)getfooterScopedRef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getcontainerDef()
  {
    if (containerDefEClass == null)
    {
      containerDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(48);
    }
    return containerDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getcontainerDef_Comment()
  {
        return (EAttribute)getcontainerDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getcontainerDef_Optional()
  {
        return (EAttribute)getcontainerDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getcontainerDef_Name()
  {
        return (EAttribute)getcontainerDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getcontainerRef()
  {
    if (containerRefEClass == null)
    {
      containerRefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(49);
    }
    return containerRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getcontainerRef_Comment()
  {
        return (EAttribute)getcontainerRef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getcontainerRef_Optional()
  {
        return (EAttribute)getcontainerRef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getcontainerRef_Type()
  {
        return (EReference)getcontainerRef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getcontainerRef_TypeScoped()
  {
        return (EReference)getcontainerRef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getcontainerRef_Name()
  {
        return (EAttribute)getcontainerRef().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdeclaredEventDef()
  {
    if (declaredEventDefEClass == null)
    {
      declaredEventDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(50);
    }
    return declaredEventDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdeclaredEventDef_Comment()
  {
        return (EAttribute)getdeclaredEventDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdeclaredEventDef_Type()
  {
        return (EReference)getdeclaredEventDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdeclaredEventDef_ScopedEventType()
  {
        return (EReference)getdeclaredEventDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdeclaredEventDef_Name()
  {
        return (EAttribute)getdeclaredEventDef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getsimpleNumericType()
  {
    if (simpleNumericTypeEClass == null)
    {
      simpleNumericTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(51);
    }
    return simpleNumericTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getsimpleNumericType_Type()
  {
        return (EAttribute)getsimpleNumericType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getfixedLenString()
  {
    if (fixedLenStringEClass == null)
    {
      fixedLenStringEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(52);
    }
    return fixedLenStringEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfixedLenString_Comment()
  {
        return (EAttribute)getfixedLenString().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfixedLenString_Optional()
  {
        return (EAttribute)getfixedLenString().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfixedLenString_Name()
  {
        return (EAttribute)getfixedLenString().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfixedLenString_UpperLim()
  {
        return (EAttribute)getfixedLenString().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfixedLenString_UpperLimRef()
  {
        return (EReference)getfixedLenString().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfixedLenString_UpperLimScoped()
  {
        return (EReference)getfixedLenString().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getvarLenString()
  {
    if (varLenStringEClass == null)
    {
      varLenStringEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(53);
    }
    return varLenStringEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarLenString_Comment()
  {
        return (EAttribute)getvarLenString().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarLenString_Optional()
  {
        return (EAttribute)getvarLenString().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarLenString_Name()
  {
        return (EAttribute)getvarLenString().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarLenString_LowerLim()
  {
        return (EAttribute)getvarLenString().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvarLenString_LowerLimRef()
  {
        return (EReference)getvarLenString().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvarLenString_LowerLimScoped()
  {
        return (EReference)getvarLenString().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarLenString_UpperLim()
  {
        return (EAttribute)getvarLenString().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvarLenString_UpperLimRef()
  {
        return (EReference)getvarLenString().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvarLenString_UpperLimScoped()
  {
        return (EReference)getvarLenString().getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getfixedFieldDef()
  {
    if (fixedFieldDefEClass == null)
    {
      fixedFieldDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(54);
    }
    return fixedFieldDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfixedFieldDef_Comment()
  {
        return (EAttribute)getfixedFieldDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfixedFieldDef_Optional()
  {
        return (EAttribute)getfixedFieldDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfixedFieldDef_Type()
  {
        return (EReference)getfixedFieldDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfixedFieldDef_Name()
  {
        return (EAttribute)getfixedFieldDef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfixedFieldDef_FieldUnit()
  {
        return (EAttribute)getfixedFieldDef().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfixedFieldDef_ValueRange()
  {
        return (EReference)getfixedFieldDef().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getvarField()
  {
    if (varFieldEClass == null)
    {
      varFieldEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(55);
    }
    return varFieldEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarField_Comment()
  {
        return (EAttribute)getvarField().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarField_Optional()
  {
        return (EAttribute)getvarField().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarField_Name()
  {
        return (EAttribute)getvarField().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvarField_VtagField()
  {
        return (EReference)getvarField().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getvarLenField()
  {
    if (varLenFieldEClass == null)
    {
      varLenFieldEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(56);
    }
    return varLenFieldEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarLenField_Comment()
  {
        return (EAttribute)getvarLenField().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarLenField_Optional()
  {
        return (EAttribute)getvarLenField().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarLenField_FieldFormat()
  {
        return (EAttribute)getvarLenField().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarLenField_Name()
  {
        return (EAttribute)getvarLenField().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarLenField_CountComment()
  {
        return (EAttribute)getvarLenField().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarLenField_LowerLim()
  {
        return (EAttribute)getvarLenField().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvarLenField_LowerLimRef()
  {
        return (EReference)getvarLenField().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvarLenField_LowerLimScoped()
  {
        return (EReference)getvarLenField().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarLenField_UpperLim()
  {
        return (EAttribute)getvarLenField().getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvarLenField_UpperLimRef()
  {
        return (EReference)getvarLenField().getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvarLenField_UpperLimScoped()
  {
        return (EReference)getvarLenField().getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass gettaggedUnitsEnum()
  {
    if (taggedUnitsEnumEClass == null)
    {
      taggedUnitsEnumEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(57);
    }
    return taggedUnitsEnumEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettaggedUnitsEnum_Const_tag()
  {
        return (EAttribute)gettaggedUnitsEnum().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettaggedUnitsEnum_Tag()
  {
        return (EReference)gettaggedUnitsEnum().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettaggedUnitsEnum_ScopedTag()
  {
        return (EReference)gettaggedUnitsEnum().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettaggedUnitsEnum_Name()
  {
        return (EAttribute)gettaggedUnitsEnum().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettaggedUnitsEnum_Type()
  {
        return (EReference)gettaggedUnitsEnum().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettaggedUnitsEnum_FieldUnit()
  {
        return (EAttribute)gettaggedUnitsEnum().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettaggedUnitsEnum_ValueSetDef()
  {
        return (EReference)gettaggedUnitsEnum().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettaggedUnitsEnum_ScaledRangeDef()
  {
        return (EReference)gettaggedUnitsEnum().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getvarFormatField()
  {
    if (varFormatFieldEClass == null)
    {
      varFormatFieldEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(58);
    }
    return varFormatFieldEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarFormatField_Comment()
  {
        return (EAttribute)getvarFormatField().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarFormatField_Optional()
  {
        return (EAttribute)getvarFormatField().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarFormatField_Name()
  {
        return (EAttribute)getvarFormatField().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarFormatField_CountComment()
  {
        return (EAttribute)getvarFormatField().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvarFormatField_Units()
  {
        return (EAttribute)getvarFormatField().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvarFormatField_CountRange()
  {
        return (EReference)getvarFormatField().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvarFormatField_FormatField()
  {
        return (EReference)getvarFormatField().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getformatEnumDef()
  {
    if (formatEnumDefEClass == null)
    {
      formatEnumDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(59);
    }
    return formatEnumDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getformatEnumDef_Index()
  {
        return (EAttribute)getformatEnumDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getformatEnumDef_ConstRef()
  {
        return (EReference)getformatEnumDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getformatEnumDef_ConstScopedRef()
  {
        return (EReference)getformatEnumDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getformatEnumDef_FieldFormat()
  {
        return (EAttribute)getformatEnumDef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getformatEnumDef_FieldFormatStr()
  {
        return (EAttribute)getformatEnumDef().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getvalueSetDef()
  {
    if (valueSetDefEClass == null)
    {
      valueSetDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(60);
    }
    return valueSetDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvalueSetDef_Value()
  {
        return (EReference)getvalueSetDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvalueSetDef_Offset()
  {
        return (EAttribute)getvalueSetDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getbitfieldDef()
  {
    if (bitfieldDefEClass == null)
    {
      bitfieldDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(61);
    }
    return bitfieldDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getbitfieldDef_Comment()
  {
        return (EAttribute)getbitfieldDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getbitfieldDef_Optional()
  {
        return (EAttribute)getbitfieldDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getbitfieldDef_Type()
  {
        return (EAttribute)getbitfieldDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getbitfieldDef_Name()
  {
        return (EAttribute)getbitfieldDef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getbitfieldDef_SubField()
  {
        return (EReference)getbitfieldDef().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getvalueRange()
  {
    if (valueRangeEClass == null)
    {
      valueRangeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(62);
    }
    return valueRangeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvalueRange_Comment()
  {
        return (EAttribute)getvalueRange().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvalueRange_LowerLimit_type()
  {
        return (EAttribute)getvalueRange().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvalueRange_LowerLim()
  {
        return (EAttribute)getvalueRange().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvalueRange_LowerLimRef()
  {
        return (EReference)getvalueRange().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvalueRange_LowerLimScoped()
  {
        return (EReference)getvalueRange().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvalueRange_UpperLim()
  {
        return (EAttribute)getvalueRange().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvalueRange_UpperLimRef()
  {
        return (EReference)getvalueRange().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvalueRange_UpperLimScoped()
  {
        return (EReference)getvalueRange().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvalueRange_UpperLimit_type()
  {
        return (EAttribute)getvalueRange().getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getvalueSpec()
  {
    if (valueSpecEClass == null)
    {
      valueSpecEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(63);
    }
    return valueSpecEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvalueSpec_Comment()
  {
        return (EAttribute)getvalueSpec().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvalueSpec_Name()
  {
        return (EAttribute)getvalueSpec().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvalueSpec_Value()
  {
        return (EAttribute)getvalueSpec().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getscaledRangeDef()
  {
    if (scaledRangeDefEClass == null)
    {
      scaledRangeDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(64);
    }
    return scaledRangeDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getscaledRangeDef_Interp()
  {
        return (EAttribute)getscaledRangeDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getscaledRangeDef_LowerLim()
  {
        return (EAttribute)getscaledRangeDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getscaledRangeDef_LowerLimRef()
  {
        return (EReference)getscaledRangeDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getscaledRangeDef_LowerLimScoped()
  {
        return (EReference)getscaledRangeDef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getscaledRangeDef_UpperLim()
  {
        return (EAttribute)getscaledRangeDef().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getscaledRangeDef_UpperLimRef()
  {
        return (EReference)getscaledRangeDef().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getscaledRangeDef_UpperLimScoped()
  {
        return (EReference)getscaledRangeDef().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getscaledRangeDef_Function()
  {
        return (EAttribute)getscaledRangeDef().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getsubField()
  {
    if (subFieldEClass == null)
    {
      subFieldEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(65);
    }
    return subFieldEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getsubField_Comment()
  {
        return (EAttribute)getsubField().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getsubField_Name()
  {
        return (EAttribute)getsubField().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getsubField_FromIndex()
  {
        return (EAttribute)getsubField().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getsubField_ToIndex()
  {
        return (EAttribute)getsubField().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getsubField_ValueSet()
  {
        return (EReference)getsubField().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getlistDef()
  {
    if (listDefEClass == null)
    {
      listDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(66);
    }
    return listDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getlistDef_CountComment()
  {
        return (EAttribute)getlistDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getlistDef_MinCount()
  {
        return (EAttribute)getlistDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getlistDef_MinCountRef()
  {
        return (EReference)getlistDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getlistDef_MinCountScoped()
  {
        return (EReference)getlistDef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getlistDef_MaxCount()
  {
        return (EAttribute)getlistDef().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getlistDef_MaxCountRef()
  {
        return (EReference)getlistDef().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getlistDef_MaxCountScoped()
  {
        return (EReference)getlistDef().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getlistDef_ContainerRef()
  {
        return (EReference)getlistDef().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getlistDef_ContainerDef()
  {
        return (EReference)getlistDef().getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getvariantDef()
  {
    if (variantDefEClass == null)
    {
      variantDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(67);
    }
    return variantDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvariantDef_VtagComment()
  {
        return (EAttribute)getvariantDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvariantDef_MinCount()
  {
        return (EAttribute)getvariantDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariantDef_MinCountRef()
  {
        return (EReference)getvariantDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariantDef_MinCountScoped()
  {
        return (EReference)getvariantDef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvariantDef_MaxCount()
  {
        return (EAttribute)getvariantDef().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariantDef_MaxCountRef()
  {
        return (EReference)getvariantDef().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariantDef_MaxCountScoped()
  {
        return (EReference)getvariantDef().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariantDef_ItemList()
  {
        return (EReference)getvariantDef().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass gettaggedItemDef()
  {
    if (taggedItemDefEClass == null)
    {
      taggedItemDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(68);
    }
    return taggedItemDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettaggedItemDef_ContainerDef()
  {
        return (EReference)gettaggedItemDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettaggedItemDef_ContainerRef()
  {
        return (EReference)gettaggedItemDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getsequenceDef()
  {
    if (sequenceDefEClass == null)
    {
      sequenceDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(69);
    }
    return sequenceDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getsequenceDef_ContainerTypeList()
  {
        return (EReference)getsequenceDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getrecordDef()
  {
    if (recordDefEClass == null)
    {
      recordDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(70);
    }
    return recordDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getrecordDef_ArrayDef()
  {
        return (EReference)getrecordDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getrecordDef_FieldDef()
  {
        return (EReference)getrecordDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getrecordDef_VariableFieldDef()
  {
        return (EReference)getrecordDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getrecordDef_BitfieldDef()
  {
        return (EReference)getrecordDef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getrecordDef_FixedLengthStringDef()
  {
        return (EReference)getrecordDef().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getrecordDef_VariableLengthStringDef()
  {
        return (EReference)getrecordDef().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getrecordDef_VariableLengthFieldDef()
  {
        return (EReference)getrecordDef().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getrecordDef_VarFormatField()
  {
        return (EReference)getrecordDef().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getrecordDef_TypeReference()
  {
        return (EReference)getrecordDef().getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getrecordDef_ScopedRef()
  {
        return (EReference)getrecordDef().getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getconstReference()
  {
    if (constReferenceEClass == null)
    {
      constReferenceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(71);
    }
    return constReferenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getconstReference_Comment()
  {
        return (EAttribute)getconstReference().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getconstReference_ConstVal()
  {
        return (EReference)getconstReference().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass gettypeReference()
  {
    if (typeReferenceEClass == null)
    {
      typeReferenceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(72);
    }
    return typeReferenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettypeReference_Comment()
  {
        return (EAttribute)gettypeReference().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettypeReference_Optional()
  {
        return (EAttribute)gettypeReference().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettypeReference_Type()
  {
        return (EReference)gettypeReference().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettypeReference_Name()
  {
        return (EAttribute)gettypeReference().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getarrayDef()
  {
    if (arrayDefEClass == null)
    {
      arrayDefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(73);
    }
    return arrayDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getarrayDef_Comment()
  {
        return (EAttribute)getarrayDef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getarrayDef_Optional()
  {
        return (EAttribute)getarrayDef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getarrayDef_TypeRef()
  {
        return (EReference)getarrayDef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getarrayDef_ScopedType()
  {
        return (EReference)getarrayDef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getarrayDef_Name()
  {
        return (EAttribute)getarrayDef().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getarrayDef_ArraySize()
  {
        return (EAttribute)getarrayDef().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmessageScopedRef()
  {
    if (messageScopedRefEClass == null)
    {
      messageScopedRefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(74);
    }
    return messageScopedRefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmessageScopedRef_Comment()
  {
        return (EAttribute)getmessageScopedRef().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmessageScopedRef_Scope()
  {
        return (EReference)getmessageScopedRef().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmessageScopedRef_Scopes()
  {
        return (EReference)getmessageScopedRef().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmessageScopedRef_Ref()
  {
        return (EReference)getmessageScopedRef().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmessageScopedRef_Name()
  {
        return (EAttribute)getmessageScopedRef().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getscopedType()
  {
    if (scopedTypeEClass == null)
    {
      scopedTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(75);
    }
    return scopedTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getscopedType_Name()
  {
        return (EReference)getscopedType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getscopedType_Names()
  {
        return (EReference)getscopedType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getscopedType_Type()
  {
        return (EReference)getscopedType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getscopedEventType()
  {
    if (scopedEventTypeEClass == null)
    {
      scopedEventTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(76);
    }
    return scopedEventTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getscopedEventType_Name()
  {
        return (EReference)getscopedEventType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getscopedEventType_Names()
  {
        return (EReference)getscopedEventType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getscopedEventType_Type()
  {
        return (EReference)getscopedEventType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getscopedTypeId()
  {
    if (scopedTypeIdEClass == null)
    {
      scopedTypeIdEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(77);
    }
    return scopedTypeIdEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getscopedTypeId_Comment()
  {
        return (EAttribute)getscopedTypeId().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getscopedTypeId_Optional()
  {
        return (EAttribute)getscopedTypeId().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getscopedTypeId_Ref()
  {
        return (EReference)getscopedTypeId().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getscopedTypeId_ScopedName()
  {
        return (EAttribute)getscopedTypeId().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getscopedConstId()
  {
    if (scopedConstIdEClass == null)
    {
      scopedConstIdEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(78);
    }
    return scopedConstIdEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getscopedConstId_Name()
  {
        return (EReference)getscopedConstId().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getscopedConstId_Names()
  {
        return (EReference)getscopedConstId().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getscopedConstId_Type()
  {
        return (EReference)getscopedConstId().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getFIELD_FORMAT()
  {
    if (fielD_FORMATEEnum == null)
    {
      fielD_FORMATEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(79);
    }
    return fielD_FORMATEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getUNIT()
  {
    if (unitEEnum == null)
    {
      unitEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(CjsidlPackage.eNS_URI).getEClassifiers().get(80);
    }
    return unitEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CjsidlFactory getCjsidlFactory()
  {
    return (CjsidlFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isLoaded = false;

  /**
   * Laods the package and any sub-packages from their serialized form.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void loadPackage()
  {
    if (isLoaded) return;
    isLoaded = true;

    URL url = getClass().getResource(packageFilename);
    if (url == null)
    {
      throw new RuntimeException("Missing serialized package: " + packageFilename);
    }
    URI uri = URI.createURI(url.toString());
    Resource resource = new EcoreResourceFactoryImpl().createResource(uri);
    try
    {
      resource.load(null);
    }
    catch (IOException exception)
    {
      throw new WrappedException(exception);
    }
    initializeFromLoadedEPackage(this, (EPackage)resource.getContents().get(0));
    createResource(eNS_URI);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isFixed = false;

  /**
   * Fixes up the loaded package, to make it appear as if it had been programmatically built.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void fixPackageContents()
  {
    if (isFixed) return;
    isFixed = true;
    fixEClassifiers();
  }

  /**
   * Sets the instance class on the given classifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected void fixInstanceClass(EClassifier eClassifier)
  {
    if (eClassifier.getInstanceClassName() == null)
    {
      eClassifier.setInstanceClassName("org.jts.eclipse.cjsidl." + eClassifier.getName());
      setGeneratedClassName(eClassifier);
    }
  }

} //CjsidlPackageImpl
