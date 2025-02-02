/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 1.3.40
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package framework.transport;

public class JausAddress extends Address {
  private long swigCPtr;

  protected JausAddress(long cPtr, boolean cMemoryOwn) {
    super(JausAddressJNI.SWIGJausAddressUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(JausAddress obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        JausAddressJNI.delete_JausAddress(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public JausAddress() {
    this(JausAddressJNI.new_JausAddress__SWIG_0(), true);
  }

  public JausAddress(int subsystemID, short nodeID, short componentID) {
    this(JausAddressJNI.new_JausAddress__SWIG_1(subsystemID, nodeID, componentID), true);
  }

  public JausAddress(long value) {
    this(JausAddressJNI.new_JausAddress__SWIG_2(value), true);
  }

  public boolean containsWildcards() {
    return ((getSubsystemID() == 0xFFFF) ||
            (getSubsystemID() == 0) ||
            (getNodeID() == 0xFF) ||
            (getNodeID() == 0) ||
            (getComponentID() == 0xFF) ||
            (getComponentID() == 0));
  }

  public int getSubsystemID() {
    return JausAddressJNI.JausAddress_getSubsystemID(swigCPtr, this);
  }

  public int setSubsystemID(int value) {
    return JausAddressJNI.JausAddress_setSubsystemID(swigCPtr, this, value);
  }

  public short getNodeID() {
    return JausAddressJNI.JausAddress_getNodeID(swigCPtr, this);
  }

  public int setNodeID(short value) {
    return JausAddressJNI.JausAddress_setNodeID(swigCPtr, this, value);
  }

  public short getComponentID() {
    return JausAddressJNI.JausAddress_getComponentID(swigCPtr, this);
  }

  public int setComponentID(short value) {
    return JausAddressJNI.JausAddress_setComponentID(swigCPtr, this, value);
  }

  public long get() {
    return JausAddressJNI.JausAddress_get(swigCPtr, this);
  }

  public boolean isLocalSubsystem(int sID) {
    return JausAddressJNI.JausAddress_isLocalSubsystem__SWIG_0(swigCPtr, this, sID);
  }

  public boolean isLocalSubsystem(JausAddress address) {
    return JausAddressJNI.JausAddress_isLocalSubsystem__SWIG_1(swigCPtr, this, JausAddress.getCPtr(address), address);
  }

  public boolean isLocalNode(int sID, short nID) {
    return JausAddressJNI.JausAddress_isLocalNode__SWIG_0(swigCPtr, this, sID, nID);
  }

  public boolean isLocalNode(JausAddress address) {
    return JausAddressJNI.JausAddress_isLocalNode__SWIG_1(swigCPtr, this, JausAddress.getCPtr(address), address);
  }

  public boolean isLocalComponent(int sID, short nID, short cID) {
    return JausAddressJNI.JausAddress_isLocalComponent__SWIG_0(swigCPtr, this, sID, nID, cID);
  }

  public boolean isLocalComponent(JausAddress address) {
    return JausAddressJNI.JausAddress_isLocalComponent__SWIG_1(swigCPtr, this, JausAddress.getCPtr(address), address);
  }

}
