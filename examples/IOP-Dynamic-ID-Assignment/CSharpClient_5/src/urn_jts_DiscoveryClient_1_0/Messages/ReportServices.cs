using JTS;
using System;
using System.Collections;
using System.Linq;
using System.Collections;
using System.Runtime.InteropServices;
using System.Collections.Generic;

namespace urn_jts_DiscoveryClient_1_0
{

public class ReportServices : JTS.Message
{
	protected ushort ID = 0x4b03;
	
	public class MsgHeader
	{
		public class HeaderRec
		{
			public void setParent(MsgHeader parent)
			{
				m_parent = parent;
			}
			
			public void setParentPresenceVector()
			{
				if(m_parent != null )
				{
					m_parent.setParentPresenceVector();
				}
			}
			
			public ushort getMessageID()
			{
				return m_MessageID;
			}
			
			public void setMessageID(ushort value)
			{
				m_MessageID = value;
				setParentPresenceVector();
			}
			
			/**
			 * Returns the number of bytes the used data members of the class occupies
			 * in the buffer. This is not the number of bytes the data type occupies in 
			 * C Sharp, but the bytes expected on the wire.
			 * 
			 * @return
			 */
			public int getSize()
			{
				int size = 0;
				
				size += JausUtils.getNumBytes("ushort");
				
				return size;
			}
			
			public void encode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((ushort)m_MessageID), pos, (int)JausUtils.USHORT_BYTES, false);
				pos += JausUtils.USHORT_BYTES;
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_MessageID = BitConverter.ToUInt16(JausUtils.getFromBuffer(bytes, pos, JausUtils.USHORT_BYTES, false), 0);
				pos += JausUtils.USHORT_BYTES;
			}
			
			public ReportServices.MsgHeader.HeaderRec  setHeaderRec(HeaderRec value)
			{
				m_MessageID = value.m_MessageID;
				
				return this;
			}
			
			public bool isEqual(HeaderRec value)
			{
				if (this.getMessageID() != value.getMessageID())
				{
					return false;
				}
				
				return true;
			}
			
			public bool notEquals(HeaderRec value)
			{
				return !this.isEqual(value);
			}
			
			public HeaderRec()
			{
				m_parent = null;
				m_MessageID = 0x4b03;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x4b03;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			 ~HeaderRec()
			{
			}
			
		
			MsgHeader m_parent;
			protected ushort m_MessageID;
		}
	
		public ReportServices.MsgHeader.HeaderRec getHeaderRec()
		{
			return m_HeaderRec;
		}
		
		public void setHeaderRec(HeaderRec value)
		{
			m_HeaderRec = value;
			setParentPresenceVector();
		}
		
		public void setParentPresenceVector()
		{
			// Nothing needed here, placeholder function
		}
		
		/**
		 * Returns the number of bytes the used data members of the class occupies
		 * in the buffer. This is not the number of bytes the data type occupies in 
		 * C Sharp, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public int getSize()
		{
			int size = 0;
			
			size += m_HeaderRec.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_HeaderRec.encode(bytes, pos);
			pos += m_HeaderRec.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_HeaderRec.decode(bytes, pos);
			pos += m_HeaderRec.getSize();
		}
		
		public ReportServices.MsgHeader  setMsgHeader(MsgHeader value)
		{
			m_HeaderRec = value.getHeaderRec();
			m_HeaderRec.setParent(this);
			
			return this;
		}
		
		public bool isEqual(MsgHeader value)
		{
			if (!this.getHeaderRec().isEqual(value.getHeaderRec()))
			{
				return false;
			}
			return true;
		}
		
		public bool notEquals(MsgHeader value)
		{
			return !this.isEqual(value);
		}
		
		public MsgHeader()
		{
			m_HeaderRec = new HeaderRec();
			m_HeaderRec.setParent(this);
		}
		
		public MsgHeader(MsgHeader value)
		{
			/// Initiliaze the protected variables
			m_HeaderRec = new HeaderRec();
			m_HeaderRec.setParent(this);
			
			/// Copy the values
			m_HeaderRec = value.getHeaderRec();
			m_HeaderRec.setParent(this);
		}
		
		 ~MsgHeader()
		{
		}
		
	
		protected HeaderRec m_HeaderRec = new  HeaderRec();
	}
	public class Body
	{
		public class NodeList
		{
			public class NodeSeq
			{
				public class NodeRec
				{
					public void setParent(NodeSeq parent)
					{
						m_parent = parent;
					}
					
					public void setParentPresenceVector()
					{
						if(m_parent != null )
						{
							m_parent.setParentPresenceVector();
						}
					}
					
					public byte getNodeID()
					{
						return m_NodeID;
					}
					
					public void setNodeID(byte value)
					{
						m_NodeID = value;
						setParentPresenceVector();
					}
					
					/**
					 * Returns the number of bytes the used data members of the class occupies
					 * in the buffer. This is not the number of bytes the data type occupies in 
					 * C Sharp, but the bytes expected on the wire.
					 * 
					 * @return
					 */
					public int getSize()
					{
						int size = 0;
						
						size += JausUtils.getNumBytes("byte");
						
						return size;
					}
					
					public void encode(byte[] bytes, int pos)
					{
						
						if (bytes == null)
						{
							return;
						}
						
						bytes[pos] = (byte)m_NodeID;
						pos += JausUtils.BYTE_BYTES;
					}
					
					public void decode(byte[] bytes, int pos)
					{
						
						if (bytes == null)
						{
							return;
						}
						
						m_NodeID = bytes[pos];
						pos += JausUtils.BYTE_BYTES;
					}
					
					public ReportServices.Body.NodeList.NodeSeq.NodeRec  setNodeRec(NodeRec value)
					{
						m_NodeID = value.m_NodeID;
						
						return this;
					}
					
					public bool isEqual(NodeRec value)
					{
						if (this.getNodeID() != value.getNodeID())
						{
							return false;
						}
						
						return true;
					}
					
					public bool notEquals(NodeRec value)
					{
						return !this.isEqual(value);
					}
					
					public NodeRec()
					{
						m_parent = null;
						m_NodeID = 0;
					}
					
					public NodeRec(NodeRec value)
					{
						/// Initiliaze the protected variables
						m_parent = null;
						m_NodeID = 0;
						
						/// Copy the values
						m_NodeID = value.m_NodeID;
					}
					
					 ~NodeRec()
					{
					}
					
				
					NodeSeq m_parent;
					protected byte m_NodeID;
				}
				public class ComponentList
				{
					public class ComponentSeq
					{
						public class ComponentRec
						{
							public void setParent(ComponentSeq parent)
							{
								m_parent = parent;
							}
							
							public void setParentPresenceVector()
							{
								if(m_parent != null )
								{
									m_parent.setParentPresenceVector();
								}
							}
							
							public byte getComponentID()
							{
								return m_ComponentID;
							}
							
							public void setComponentID(byte value)
							{
								m_ComponentID = value;
								setParentPresenceVector();
							}
							
							public byte getInstanceID()
							{
								return m_InstanceID;
							}
							
							public void setInstanceID(byte value)
							{
								m_InstanceID = value;
								setParentPresenceVector();
							}
							
							/**
							 * Returns the number of bytes the used data members of the class occupies
							 * in the buffer. This is not the number of bytes the data type occupies in 
							 * C Sharp, but the bytes expected on the wire.
							 * 
							 * @return
							 */
							public int getSize()
							{
								int size = 0;
								
								size += JausUtils.getNumBytes("byte");
								size += JausUtils.getNumBytes("byte");
								
								return size;
							}
							
							public void encode(byte[] bytes, int pos)
							{
								
								if (bytes == null)
								{
									return;
								}
								
								bytes[pos] = (byte)m_ComponentID;
								pos += JausUtils.BYTE_BYTES;
								bytes[pos] = (byte)m_InstanceID;
								pos += JausUtils.BYTE_BYTES;
							}
							
							public void decode(byte[] bytes, int pos)
							{
								
								if (bytes == null)
								{
									return;
								}
								
								m_ComponentID = bytes[pos];
								pos += JausUtils.BYTE_BYTES;
								m_InstanceID = bytes[pos];
								pos += JausUtils.BYTE_BYTES;
							}
							
							public ReportServices.Body.NodeList.NodeSeq.ComponentList.ComponentSeq.ComponentRec  setComponentRec(ComponentRec value)
							{
								m_ComponentID = value.m_ComponentID;
								m_InstanceID = value.m_InstanceID;
								
								return this;
							}
							
							public bool isEqual(ComponentRec value)
							{
								if (this.getComponentID() != value.getComponentID())
								{
									return false;
								}
								if (this.getInstanceID() != value.getInstanceID())
								{
									return false;
								}
								
								return true;
							}
							
							public bool notEquals(ComponentRec value)
							{
								return !this.isEqual(value);
							}
							
							public ComponentRec()
							{
								m_parent = null;
								m_ComponentID = 0;
								m_InstanceID = 0;
							}
							
							public ComponentRec(ComponentRec value)
							{
								/// Initiliaze the protected variables
								m_parent = null;
								m_ComponentID = 0;
								m_InstanceID = 0;
								
								/// Copy the values
								m_ComponentID = value.m_ComponentID;
								m_InstanceID = value.m_InstanceID;
							}
							
							 ~ComponentRec()
							{
							}
							
						
							ComponentSeq m_parent;
							protected byte m_ComponentID;
							protected byte m_InstanceID;
						}
						public class ServiceList
						{
							public class ServiceRec
							{
								public void setParent(ServiceList parent)
								{
									m_parent = parent;
								}
								
								public void setParentPresenceVector()
								{
									if(m_parent != null )
									{
										m_parent.setParentPresenceVector();
									}
								}
								
								public string getURI()
								{
									return m_URI;
								}
								
								public void setURI(string value)
								{
									if ( value.Length > 255)
									{
										return;
									}
									
									m_URI = value;
									if (m_URI.Length < 0)
									{
										//Resizing a stiring like this is not possible in C#.
									}
									setParentPresenceVector();
								}
								
								public byte getMajorVersionNumber()
								{
									return m_MajorVersionNumber;
								}
								
								public void setMajorVersionNumber(byte value)
								{
									m_MajorVersionNumber = value;
									setParentPresenceVector();
								}
								
								public byte getMinorVersionNumber()
								{
									return m_MinorVersionNumber;
								}
								
								public void setMinorVersionNumber(byte value)
								{
									m_MinorVersionNumber = value;
									setParentPresenceVector();
								}
								
								/**
								 * Returns the number of bytes the used data members of the class occupies
								 * in the buffer. This is not the number of bytes the data type occupies in 
								 * C Sharp, but the bytes expected on the wire.
								 * 
								 * @return
								 */
								public int getSize()
								{
									int size = 0;
									
									size += JausUtils.getNumBytes("byte");
									size += m_URI.Length;
									size += JausUtils.getNumBytes("byte");
									size += JausUtils.getNumBytes("byte");
									
									return size;
								}
								
								public void encode(byte[] bytes, int pos)
								{
									
									if (bytes == null)
									{
										return;
									}
									
									
									bytes[pos] = (byte)m_URI.Length;
									pos += JausUtils.BYTE_BYTES;
									
									bytes = JausUtils.addToBuffer(bytes, m_URI.ToCharArray(), pos, (int)m_URI.Length);
									pos += (int)m_URI.Length;
									bytes[pos] = (byte)m_MajorVersionNumber;
									pos += JausUtils.BYTE_BYTES;
									bytes[pos] = (byte)m_MinorVersionNumber;
									pos += JausUtils.BYTE_BYTES;
								}
								
								public void decode(byte[] bytes, int pos)
								{
									
									if (bytes == null)
									{
										return;
									}
									
									
									byte m_URILength = 0;
									m_URILength = bytes[pos];
									pos += JausUtils.BYTE_BYTES;
									
									m_URI = JausUtils.getFromBuffer(bytes, pos, (int)m_URILength, true, false);
									pos += (int)m_URILength;
									m_MajorVersionNumber = bytes[pos];
									pos += JausUtils.BYTE_BYTES;
									m_MinorVersionNumber = bytes[pos];
									pos += JausUtils.BYTE_BYTES;
								}
								
								public ReportServices.Body.NodeList.NodeSeq.ComponentList.ComponentSeq.ServiceList.ServiceRec  setServiceRec(ServiceRec value)
								{
									m_URI = value.m_URI;
									m_MajorVersionNumber = value.m_MajorVersionNumber;
									m_MinorVersionNumber = value.m_MinorVersionNumber;
									
									return this;
								}
								
								public bool isEqual(ServiceRec value)
								{
									if ((m_URI.Length != value.m_URI.Length) || (m_URI.CompareTo(value.m_URI) != 0))
									{
										return false;
									}
									if (this.getMajorVersionNumber() != value.getMajorVersionNumber())
									{
										return false;
									}
									if (this.getMinorVersionNumber() != value.getMinorVersionNumber())
									{
										return false;
									}
									
									return true;
								}
								
								public bool notEquals(ServiceRec value)
								{
									return !this.isEqual(value);
								}
								
								public ServiceRec()
								{
									m_parent = null;
									m_URI = "";
									m_MajorVersionNumber = 0;
									m_MinorVersionNumber = 0;
								}
								
								public ServiceRec(ServiceRec value)
								{
									/// Initiliaze the protected variables
									m_parent = null;
									m_URI = "";
									m_MajorVersionNumber = 0;
									m_MinorVersionNumber = 0;
									
									/// Copy the values
									m_URI = value.m_URI;
									m_MajorVersionNumber = value.m_MajorVersionNumber;
									m_MinorVersionNumber = value.m_MinorVersionNumber;
								}
								
								 ~ServiceRec()
								{
								}
								
							
								ServiceList m_parent;
								protected string m_URI;
								protected byte m_MajorVersionNumber;
								protected byte m_MinorVersionNumber;
							}
						
							public void setParent(ComponentSeq parent)
							{
								m_parent = parent;
							}
							
							public void setParentPresenceVector()
							{
								if(m_parent != null )
								{
									m_parent.setParentPresenceVector();
								}
							}
							
							public int getNumberOfElements()
							{
								return (int) m_ServiceRec.Count;
							}
							
							public ReportServices.Body.NodeList.NodeSeq.ComponentList.ComponentSeq.ServiceList.ServiceRec getElement(int index)
							{
								return m_ServiceRec[index];
							}
							
							public void setElement(int index, ServiceRec value)
							{
								if(m_ServiceRec.Count-1 < index)
								{
									return;
								}
								
								m_ServiceRec[index] = value;
								m_ServiceRec[index].setParent(this);
								setParentPresenceVector();
							}
							
							public void addElement(ServiceRec value)
							{
								m_ServiceRec.Add(value);
								m_ServiceRec[m_ServiceRec.Count-1].setParent(this);
								setParentPresenceVector();
							}
							
							public void deleteElement(int index)
							{
								if(m_ServiceRec.Count-1 < index)
								{
									return;
								}
								
								m_ServiceRec.RemoveAt(index);
							}
							
							public void deleteLastElement()
							{
								m_ServiceRec.RemoveAt(m_ServiceRec.Count-1);
							}
							
							/**
							 * Returns the number of bytes the used data members of the class occupies
							 * in the buffer. This is not the number of bytes the data type occupies in 
							 * C Sharp, but the bytes expected on the wire.
							 * 
							 * @return
							 */
							public int getSize()
							{
								int size = 0;
								
								size += JausUtils.BYTE_BYTES;
								for (int i = 0; i < m_ServiceRec.Count; i++)
								{
									size += m_ServiceRec[i].getSize();
								}
								
								return size;
							}
							
							public void encode(byte[] bytes, int pos)
							{
								
								if (bytes == null)
								{
									return;
								}
								
								byte size = (byte) m_ServiceRec.Count;
								bytes[pos] = (byte)size;
								pos += JausUtils.BYTE_BYTES;
								for (int i = 0; i < m_ServiceRec.Count; i++)
								{
									m_ServiceRec[i].encode(bytes, pos);
									pos += m_ServiceRec[i].getSize();
								}
							}
							
							public void decode(byte[] bytes, int pos)
							{
								
								if (bytes == null)
								{
									return;
								}
								
								byte size;
								size = bytes[pos];
								pos += JausUtils.BYTE_BYTES;
								m_ServiceRec = new List<ServiceRec>();
								for (int i = 0; i < size; i++)
								{
									m_ServiceRec.Add( new ServiceRec());
									m_ServiceRec[i].decode(bytes, pos);
									pos += m_ServiceRec[i].getSize();
								}
							}
							
							public ReportServices.Body.NodeList.NodeSeq.ComponentList.ComponentSeq.ServiceList  setServiceList(ServiceList value)
							{
								m_ServiceRec.Clear();
								
								for (int i = 0; i < value.m_ServiceRec.Count; i++)
								{
									m_ServiceRec.Add(value.m_ServiceRec[i]);
									m_ServiceRec[i].setParent(this);
								}
								
								return this;
							}
							
							public bool isEqual(ServiceList value)
							{
								if (m_ServiceRec.Count !=  value.m_ServiceRec.Count)
								{
									return false;
								}
								
								for (int i = 0; i < value.m_ServiceRec.Count; i++)
								{
									if (!this.m_ServiceRec[i].isEqual(value.m_ServiceRec[i]))
									{
										return false;
									}
								}
								
								return true;
							}
							
							public bool notEquals(ServiceList value)
							{
								return !this.isEqual(value);
							}
							
							public ServiceList()
							{
								m_parent = null;
								m_ServiceRec = new List<ServiceRec>();
								for (int i = 0; i < m_ServiceRec.Count; i++)
								{
									m_ServiceRec[i].setParent(this);
								}
							}
							
							public ServiceList(ServiceList value)
							{
								/// Initiliaze the protected variables
								m_parent = null;
								m_ServiceRec = new List<ServiceRec>();
								for (int i = 0; i < m_ServiceRec.Count; i++)
								{
									m_ServiceRec[i].setParent(this);
								}
								
								/// Copy the values
								m_ServiceRec.Clear();
								
								for (int i = 0; i < value.m_ServiceRec.Count; i++)
								{
									m_ServiceRec.Add(value.m_ServiceRec[i]);
									m_ServiceRec[i].setParent(this);
								}
							}
							
							 ~ServiceList()
							{
							}
							
						
							ComponentSeq m_parent;
							protected List<ServiceRec> m_ServiceRec = new  List<ServiceRec>();
						}
					
						public void setParent(ComponentList parent)
						{
							m_parent = parent;
						}
						
						public void setParentPresenceVector()
						{
							if(m_parent != null )
							{
								m_parent.setParentPresenceVector();
							}
						}
						
						public ReportServices.Body.NodeList.NodeSeq.ComponentList.ComponentSeq.ComponentRec getComponentRec()
						{
							return m_ComponentRec;
						}
						
						public void setComponentRec(ComponentRec value)
						{
							m_ComponentRec = value;
							setParentPresenceVector();
						}
						
						public ReportServices.Body.NodeList.NodeSeq.ComponentList.ComponentSeq.ServiceList getServiceList()
						{
							return m_ServiceList;
						}
						
						public void setServiceList(ServiceList value)
						{
							m_ServiceList = value;
							setParentPresenceVector();
						}
						
						/**
						 * Returns the number of bytes the used data members of the class occupies
						 * in the buffer. This is not the number of bytes the data type occupies in 
						 * C Sharp, but the bytes expected on the wire.
						 * 
						 * @return
						 */
						public int getSize()
						{
							int size = 0;
							
							size += m_ComponentRec.getSize();
							size += m_ServiceList.getSize();
							
							return size;
						}
						
						public void encode(byte[] bytes, int pos)
						{
							
							if (bytes == null)
							{
								return;
							}
							
							m_ComponentRec.encode(bytes, pos);
							pos += m_ComponentRec.getSize();
							m_ServiceList.encode(bytes, pos);
							pos += m_ServiceList.getSize();
						}
						
						public void decode(byte[] bytes, int pos)
						{
							
							if (bytes == null)
							{
								return;
							}
							
							m_ComponentRec.decode(bytes, pos);
							pos += m_ComponentRec.getSize();
							m_ServiceList.decode(bytes, pos);
							pos += m_ServiceList.getSize();
						}
						
						public ReportServices.Body.NodeList.NodeSeq.ComponentList.ComponentSeq  setComponentSeq(ComponentSeq value)
						{
							m_ComponentRec = value.getComponentRec();
							m_ComponentRec.setParent(this);
							m_ComponentRec = value.getComponentRec();
							m_ServiceList = value.m_ServiceList;
							m_ServiceList.setParent(this);
							m_ServiceList = value.getServiceList();
							
							return this;
						}
						
						public bool isEqual(ComponentSeq value)
						{
							if (!this.getComponentRec().isEqual(value.getComponentRec()))
							{
								return false;
							}
							
							if (!this.getComponentRec().isEqual(value.getComponentRec()))
							{
								return false;
							}
							if (!this.m_ServiceList.isEqual(value.m_ServiceList))
							{
								return false;
							}
							
							if (!this.getServiceList().isEqual(value.getServiceList()))
							{
								return false;
							}
							
							return true;
						}
						
						public bool notEquals(ComponentSeq value)
						{
							return !this.isEqual(value);
						}
						
						public ComponentSeq()
						{
							m_parent = null;
							m_ComponentRec = new ComponentRec();
							m_ComponentRec.setParent(this);
							m_ServiceList = new ServiceList();
							m_ServiceList.setParent(this);
						}
						
						public ComponentSeq(ComponentSeq value)
						{
							/// Initiliaze the protected variables
							m_parent = null;
							m_ComponentRec = new ComponentRec();
							m_ComponentRec.setParent(this);
							m_ServiceList = new ServiceList();
							m_ServiceList.setParent(this);
							
							/// Copy the values
							m_ComponentRec = value.getComponentRec();
							m_ComponentRec.setParent(this);
							m_ComponentRec = value.getComponentRec();
							m_ServiceList = value.m_ServiceList;
							m_ServiceList.setParent(this);
							m_ServiceList = value.getServiceList();
						}
						
						 ~ComponentSeq()
						{
						}
						
					
						ComponentList m_parent;
						protected ComponentRec m_ComponentRec = new  ComponentRec();
						protected ServiceList m_ServiceList = new  ServiceList();
					}
				
					public void setParent(NodeSeq parent)
					{
						m_parent = parent;
					}
					
					public void setParentPresenceVector()
					{
						if(m_parent != null )
						{
							m_parent.setParentPresenceVector();
						}
					}
					
					public int getNumberOfElements()
					{
						return  m_ComponentSeq.Count;
					}
					
					public ReportServices.Body.NodeList.NodeSeq.ComponentList.ComponentSeq getElement(int index)
					{
						return m_ComponentSeq[index];
					}
					
					public void setElement(int index, ComponentSeq value)
					{
						if(m_ComponentSeq.Count-1 < index)
						{
							return;
						}
						
						m_ComponentSeq[index].setComponentSeq(value);
						m_ComponentSeq[index].setParent(this);
						setParentPresenceVector();
					}
					
					public void addElement(ComponentSeq value)
					{
						m_ComponentSeq.Add(value);
						m_ComponentSeq[m_ComponentSeq.Count-1].setParent(this);
						setParentPresenceVector();
					}
					
					public void deleteElement(int index)
					{
						if(m_ComponentSeq.Count-1 < index)
						{
							return;
						}
						
						m_ComponentSeq.RemoveAt(index);
					}
					
					public void deleteLastElement()
					{
						m_ComponentSeq.RemoveAt(m_ComponentSeq.Count -1);
					}
					
					/**
					 * Returns the number of bytes the used data members of the class occupies
					 * in the buffer. This is not the number of bytes the data type occupies in 
					 * C Sharp, but the bytes expected on the wire.
					 * 
					 * @return
					 */
					public int getSize()
					{
						int size = 0;
						
						size += JausUtils.BYTE_BYTES;
						for (int i = 0; i < m_ComponentSeq.Count; i++)
						{
							size += m_ComponentSeq[i].getSize();
						}
						
						return size;
					}
					
					public void encode(byte[] bytes, int pos)
					{
						
						if (bytes == null)
						{
							return;
						}
						
						byte size = (byte) m_ComponentSeq.Count;
						bytes[pos] = (byte)size;
						pos += JausUtils.BYTE_BYTES;
						for (int i = 0; i < m_ComponentSeq.Count; i++)
						{
							m_ComponentSeq[i].encode(bytes, pos);
							pos += m_ComponentSeq[i].getSize();
						}
					}
					
					public void decode(byte[] bytes, int pos)
					{
						
						if (bytes == null)
						{
							return;
						}
						
						byte size;
						size = bytes[pos];
						pos += JausUtils.BYTE_BYTES;
						m_ComponentSeq = new List<ComponentSeq>();
						
						for (int i = 0; i < size; i++)
						{
							m_ComponentSeq.Add(new ComponentSeq());
							m_ComponentSeq[i].decode(bytes, pos);
							pos += m_ComponentSeq[i].getSize();
						}
					}
					
					public ReportServices.Body.NodeList.NodeSeq.ComponentList  setComponentList(ComponentList value)
					{
						m_ComponentSeq.Clear();
						
						for (int i = 0; i < value.m_ComponentSeq.Count; i++)
						{
							m_ComponentSeq.Add(value.m_ComponentSeq[i]);
							m_ComponentSeq[i].setParent(this);
						}
						
						return this;
					}
					
					public bool isEqual(ComponentList value)
					{
						if (m_ComponentSeq.Count != value.m_ComponentSeq.Count)
						{
							return false;
						}
						
						for (int i = 0; i < m_ComponentSeq.Count; i++)
						{
							if (!this.m_ComponentSeq[i].isEqual(value.m_ComponentSeq[i]))
							{
								return false;
							}
						}
						
						return true;
					}
					
					public bool notEquals(ComponentList value)
					{
						return !this.isEqual(value);
					}
					
					public ComponentList()
					{
						m_parent = null;
						m_ComponentSeq = new List<ComponentSeq>();
						for (int i = 0; i < m_ComponentSeq.Count; i++)
						{
							m_ComponentSeq[i].setParent(this);
						}
					}
					
					public ComponentList(ComponentList value)
					{
						/// Initiliaze the protected variables
						m_parent = null;
						m_ComponentSeq = new List<ComponentSeq>();
						for (int i = 0; i < m_ComponentSeq.Count; i++)
						{
							m_ComponentSeq[i].setParent(this);
						}
						
						/// Copy the values
						m_ComponentSeq.Clear();
						
						for (int i = 0; i < value.m_ComponentSeq.Count; i++)
						{
							m_ComponentSeq.Add(value.m_ComponentSeq[i]);
							m_ComponentSeq[i].setParent(this);
						}
					}
					
					 ~ComponentList()
					{
					}
					
				
					NodeSeq m_parent;
					protected List<ComponentSeq> m_ComponentSeq = new  List<ComponentSeq>();
				}
			
				public void setParent(NodeList parent)
				{
					m_parent = parent;
				}
				
				public void setParentPresenceVector()
				{
					if(m_parent != null )
					{
						m_parent.setParentPresenceVector();
					}
				}
				
				public ReportServices.Body.NodeList.NodeSeq.NodeRec getNodeRec()
				{
					return m_NodeRec;
				}
				
				public void setNodeRec(NodeRec value)
				{
					m_NodeRec = value;
					setParentPresenceVector();
				}
				
				public ReportServices.Body.NodeList.NodeSeq.ComponentList getComponentList()
				{
					return m_ComponentList;
				}
				
				public void setComponentList(ComponentList value)
				{
					m_ComponentList = value;
					setParentPresenceVector();
				}
				
				/**
				 * Returns the number of bytes the used data members of the class occupies
				 * in the buffer. This is not the number of bytes the data type occupies in 
				 * C Sharp, but the bytes expected on the wire.
				 * 
				 * @return
				 */
				public int getSize()
				{
					int size = 0;
					
					size += m_NodeRec.getSize();
					size += m_ComponentList.getSize();
					
					return size;
				}
				
				public void encode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					m_NodeRec.encode(bytes, pos);
					pos += m_NodeRec.getSize();
					m_ComponentList.encode(bytes, pos);
					pos += m_ComponentList.getSize();
				}
				
				public void decode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					m_NodeRec.decode(bytes, pos);
					pos += m_NodeRec.getSize();
					m_ComponentList.decode(bytes, pos);
					pos += m_ComponentList.getSize();
				}
				
				public ReportServices.Body.NodeList.NodeSeq  setNodeSeq(NodeSeq value)
				{
					m_NodeRec = value.getNodeRec();
					m_NodeRec.setParent(this);
					m_NodeRec = value.getNodeRec();
					m_ComponentList = value.m_ComponentList;
					m_ComponentList.setParent(this);
					m_ComponentList = value.getComponentList();
					
					return this;
				}
				
				public bool isEqual(NodeSeq value)
				{
					if (!this.getNodeRec().isEqual(value.getNodeRec()))
					{
						return false;
					}
					
					if (!this.getNodeRec().isEqual(value.getNodeRec()))
					{
						return false;
					}
					if (!this.m_ComponentList.isEqual(value.m_ComponentList))
					{
						return false;
					}
					
					if (!this.getComponentList().isEqual(value.getComponentList()))
					{
						return false;
					}
					
					return true;
				}
				
				public bool notEquals(NodeSeq value)
				{
					return !this.isEqual(value);
				}
				
				public NodeSeq()
				{
					m_parent = null;
					m_NodeRec = new NodeRec();
					m_NodeRec.setParent(this);
					m_ComponentList = new ComponentList();
					m_ComponentList.setParent(this);
				}
				
				public NodeSeq(NodeSeq value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_NodeRec = new NodeRec();
					m_NodeRec.setParent(this);
					m_ComponentList = new ComponentList();
					m_ComponentList.setParent(this);
					
					/// Copy the values
					m_NodeRec = value.getNodeRec();
					m_NodeRec.setParent(this);
					m_NodeRec = value.getNodeRec();
					m_ComponentList = value.m_ComponentList;
					m_ComponentList.setParent(this);
					m_ComponentList = value.getComponentList();
				}
				
				 ~NodeSeq()
				{
				}
				
			
				NodeList m_parent;
				protected NodeRec m_NodeRec = new  NodeRec();
				protected ComponentList m_ComponentList = new  ComponentList();
			}
		
			public void setParent(Body parent)
			{
				m_parent = parent;
			}
			
			public void setParentPresenceVector()
			{
				if(m_parent != null )
				{
					m_parent.setParentPresenceVector();
				}
			}
			
			public int getNumberOfElements()
			{
				return  m_NodeSeq.Count;
			}
			
			public ReportServices.Body.NodeList.NodeSeq getElement(int index)
			{
				return m_NodeSeq[index];
			}
			
			public void setElement(int index, NodeSeq value)
			{
				if(m_NodeSeq.Count-1 < index)
				{
					return;
				}
				
				m_NodeSeq[index].setNodeSeq(value);
				m_NodeSeq[index].setParent(this);
				setParentPresenceVector();
			}
			
			public void addElement(NodeSeq value)
			{
				m_NodeSeq.Add(value);
				m_NodeSeq[m_NodeSeq.Count-1].setParent(this);
				setParentPresenceVector();
			}
			
			public void deleteElement(int index)
			{
				if(m_NodeSeq.Count-1 < index)
				{
					return;
				}
				
				m_NodeSeq.RemoveAt(index);
			}
			
			public void deleteLastElement()
			{
				m_NodeSeq.RemoveAt(m_NodeSeq.Count -1);
			}
			
			/**
			 * Returns the number of bytes the used data members of the class occupies
			 * in the buffer. This is not the number of bytes the data type occupies in 
			 * C Sharp, but the bytes expected on the wire.
			 * 
			 * @return
			 */
			public int getSize()
			{
				int size = 0;
				
				size += JausUtils.BYTE_BYTES;
				for (int i = 0; i < m_NodeSeq.Count; i++)
				{
					size += m_NodeSeq[i].getSize();
				}
				
				return size;
			}
			
			public void encode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				byte size = (byte) m_NodeSeq.Count;
				bytes[pos] = (byte)size;
				pos += JausUtils.BYTE_BYTES;
				for (int i = 0; i < m_NodeSeq.Count; i++)
				{
					m_NodeSeq[i].encode(bytes, pos);
					pos += m_NodeSeq[i].getSize();
				}
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				byte size;
				size = bytes[pos];
				pos += JausUtils.BYTE_BYTES;
				m_NodeSeq = new List<NodeSeq>();
				
				for (int i = 0; i < size; i++)
				{
					m_NodeSeq.Add(new NodeSeq());
					m_NodeSeq[i].decode(bytes, pos);
					pos += m_NodeSeq[i].getSize();
				}
			}
			
			public ReportServices.Body.NodeList  setNodeList(NodeList value)
			{
				m_NodeSeq.Clear();
				
				for (int i = 0; i < value.m_NodeSeq.Count; i++)
				{
					m_NodeSeq.Add(value.m_NodeSeq[i]);
					m_NodeSeq[i].setParent(this);
				}
				
				return this;
			}
			
			public bool isEqual(NodeList value)
			{
				if (m_NodeSeq.Count != value.m_NodeSeq.Count)
				{
					return false;
				}
				
				for (int i = 0; i < m_NodeSeq.Count; i++)
				{
					if (!this.m_NodeSeq[i].isEqual(value.m_NodeSeq[i]))
					{
						return false;
					}
				}
				
				return true;
			}
			
			public bool notEquals(NodeList value)
			{
				return !this.isEqual(value);
			}
			
			public NodeList()
			{
				m_parent = null;
				m_NodeSeq = new List<NodeSeq>();
				for (int i = 0; i < m_NodeSeq.Count; i++)
				{
					m_NodeSeq[i].setParent(this);
				}
			}
			
			public NodeList(NodeList value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_NodeSeq = new List<NodeSeq>();
				for (int i = 0; i < m_NodeSeq.Count; i++)
				{
					m_NodeSeq[i].setParent(this);
				}
				
				/// Copy the values
				m_NodeSeq.Clear();
				
				for (int i = 0; i < value.m_NodeSeq.Count; i++)
				{
					m_NodeSeq.Add(value.m_NodeSeq[i]);
					m_NodeSeq[i].setParent(this);
				}
			}
			
			 ~NodeList()
			{
			}
			
		
			Body m_parent;
			protected List<NodeSeq> m_NodeSeq = new  List<NodeSeq>();
		}
	
		public ReportServices.Body.NodeList getNodeList()
		{
			return m_NodeList;
		}
		
		public void setNodeList(NodeList value)
		{
			m_NodeList = value;
			setParentPresenceVector();
		}
		
		public void setParentPresenceVector()
		{
			// Nothing needed here, placeholder function
		}
		
		/**
		 * Returns the number of bytes the used data members of the class occupies
		 * in the buffer. This is not the number of bytes the data type occupies in 
		 * C Sharp, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public int getSize()
		{
			int size = 0;
			
			size += m_NodeList.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_NodeList.encode(bytes, pos);
			pos += m_NodeList.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_NodeList.decode(bytes, pos);
			pos += m_NodeList.getSize();
		}
		
		public ReportServices.Body  setBody(Body value)
		{
			m_NodeList = value.m_NodeList;
			m_NodeList.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public bool isEqual(Body value)
		{
			if (!this.m_NodeList.isEqual(value.m_NodeList))
			{
				return false;
			}
			/// This code is currently not supported
			return true;
		}
		
		public bool notEquals(Body value)
		{
			return !this.isEqual(value);
		}
		
		public Body()
		{
			m_NodeList = new NodeList();
			m_NodeList.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_NodeList = new NodeList();
			m_NodeList.setParent(this);
			
			/// Copy the values
			m_NodeList = value.m_NodeList;
			m_NodeList.setParent(this);
			/// This code is currently not supported
		}
		
		 ~Body()
		{
		}
		
	
		protected NodeList m_NodeList = new  NodeList();
	}
	protected MsgHeader m_MsgHeader = new  MsgHeader();
	protected Body m_Body = new  Body();
	public override ushort getID()
	{
		return ID;
	}
	public ReportServices.MsgHeader getMsgHeader()
	{
		return m_MsgHeader;
	}
	
	public void setMsgHeader(MsgHeader value)
	{
		m_MsgHeader = value;
	}
	
	public ReportServices.Body getBody()
	{
		return m_Body;
	}
	
	public void setBody(Body value)
	{
		m_Body = value;
	}
	
	/**
	 * Returns the number of bytes the used data members of the class occupies
	 * in the buffer. This is not the number of bytes the data type occupies in 
	 * Java, but the bytes expected on the wire.
	 * 
	 * @return
	 */
	public override int getSize()
	{
		int size = 0;
		
		size += m_MsgHeader.getSize();
		size += m_Body.getSize();
		
		return size;
	}
	
	public override void encode(byte[] bytes, int pos)
	{
		
		if (bytes == null)
		{
			return;
		}
		
		m_MsgHeader.encode(bytes, pos);
		pos += m_MsgHeader.getSize();
		m_Body.encode(bytes, pos);
		pos += m_Body.getSize();
		
	}
	
	public override void decode(byte[] bytes, int pos)
	{
		
		if (bytes == null)
		{
			return;
		}
		
		m_MsgHeader.decode(bytes, pos);
		pos += m_MsgHeader.getSize();
		m_Body.decode(bytes, pos);
		pos += m_Body.getSize();
		if(pos < bytes.Length)
		{
			for(int i = pos; i<bytes.Length; i++)
			{
				bytes[i] = 0;
			}
		}
	}
	
	public ReportServices setAs(ReportServices value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public bool  isEqual(ReportServices value)
	{
		if (!this.getMsgHeader().isEqual(value.getMsgHeader()))
		{
			return false;
		}
		if (!this.getBody().isEqual(value.getBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public bool  notEquals(ReportServices value)
	{
		return !this.isEqual(value);
	}
	
	public ReportServices()
	{
		m_MsgHeader = new MsgHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  ReportServices(ReportServices value)
	{
		/// Initiliaze the protected variables
		m_MsgHeader = new MsgHeader();
		m_Body = new Body();
		m_IsCommand = false;
		
		/// Copy the values
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
	}
	
}

}
