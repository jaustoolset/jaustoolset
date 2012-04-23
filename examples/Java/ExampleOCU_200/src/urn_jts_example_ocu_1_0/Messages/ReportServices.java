package src.urn_jts_example_ocu_1_0.Messages;

import framework.messages.Message;
import framework.JausUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportServices extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x4b03;
	
	public static class  JAUSApplicationLayerHeader
	{
		public static class  HeaderRec
		{
		
			protected JAUSApplicationLayerHeader m_parent;
			protected int m_MessageID;
		
			public void setParent(JAUSApplicationLayerHeader parent)
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
			
			public int getMessageID()
			{
				return m_MessageID;
			}
			
			public void setMessageID(int value)
			{
				m_MessageID = value;
				setParentPresenceVector();
			}
			
			/**
			 * Returns the number of bytes the used data members of the class occupies
			 * in the buffer. This is not the number of bytes the data type occupies in 
			 * Java, but the bytes expected on the wire.
			 * 
			 * @return
			 */
			public long getSize()
			{
				long size = 0;
				
				size += JausUtils.getNumBytes("short");
				
				return size;
			}
			
			public void encode(ByteBuffer bytes, int pos)
			{
				
				if (bytes.array() == null)
				{
					return;
				}
				
				if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
				{
					bytes.order(ByteOrder.LITTLE_ENDIAN);
				}
				
				bytes.putShort(pos, (short) m_MessageID);
				pos += JausUtils.getNumBytes("short");
			}
			
			public void decode(ByteBuffer bytes, int pos)
			{
				
				if (bytes.array() == null)
				{
					return;
				}
				if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
				{
					bytes.order(ByteOrder.LITTLE_ENDIAN);
				}
				
				m_MessageID = bytes.getShort(pos) & 0xffff;
				pos += JausUtils.getNumBytes("short");
			}
			
			public ReportServices.JAUSApplicationLayerHeader.HeaderRec assign(HeaderRec value)
			{
				m_MessageID = value.m_MessageID;
				
				return this;
			}
			
			public boolean isEqual(HeaderRec value)
			{
				if (m_MessageID != value.getMessageID())
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(HeaderRec value)
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
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
		public ReportServices.JAUSApplicationLayerHeader.HeaderRec getHeaderRec()
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
		 * Java, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public long getSize()
		{
			long size = 0;
			
			size += m_HeaderRec.getSize();
			
			return size;
		}
		
		public void encode(ByteBuffer bytes, int pos)
		{
			
			if (bytes.array() == null)
			{
				return;
			}
			
			if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
			{
				bytes.order(ByteOrder.LITTLE_ENDIAN);
			}
			
			m_HeaderRec.encode(bytes, pos);
			pos += m_HeaderRec.getSize();
		}
		
		public void decode(ByteBuffer bytes, int pos)
		{
			
			if (bytes.array() == null)
			{
				return;
			}
			if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
			{
				bytes.order(ByteOrder.LITTLE_ENDIAN);
			}
			
			m_HeaderRec.decode(bytes, pos);
			pos += m_HeaderRec.getSize();
		}
		
		public ReportServices.JAUSApplicationLayerHeader assign(JAUSApplicationLayerHeader value)
		{
			m_HeaderRec = value.m_HeaderRec;
			m_HeaderRec.setParent(this);
			
			return this;
		}
		
		public boolean isEqual(JAUSApplicationLayerHeader value)
		{
			if (!m_HeaderRec.isEqual(value.getHeaderRec()))
			{
				return false;
			}
			return true;
		}
		
		public boolean notEquals(JAUSApplicationLayerHeader value)
		{
			return !this.isEqual(value);
		}
		
		public JAUSApplicationLayerHeader()
		{
			m_HeaderRec = new HeaderRec();
			m_HeaderRec.setParent(this);
		}
		
		public JAUSApplicationLayerHeader(JAUSApplicationLayerHeader value)
		{
			/// Initiliaze the protected variables
			m_HeaderRec = new HeaderRec();
			m_HeaderRec.setParent(this);
			
			/// Copy the values
			m_HeaderRec = value.m_HeaderRec;
			m_HeaderRec.setParent(this);
		}
		
		public void finalize()
		{
		}
		
	}
	public static class  Body
	{
		public static class  NodeList
		{
			public static class  NodeSeq
			{
				public static class  NodeRec
				{
				
					protected NodeSeq m_parent;
					protected short m_NodeID;
				
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
					
					public short getNodeID()
					{
						return m_NodeID;
					}
					
					public void setNodeID(short value)
					{
						m_NodeID = value;
						setParentPresenceVector();
					}
					
					/**
					 * Returns the number of bytes the used data members of the class occupies
					 * in the buffer. This is not the number of bytes the data type occupies in 
					 * Java, but the bytes expected on the wire.
					 * 
					 * @return
					 */
					public long getSize()
					{
						long size = 0;
						
						size += JausUtils.getNumBytes("byte");
						
						return size;
					}
					
					public void encode(ByteBuffer bytes, int pos)
					{
						
						if (bytes.array() == null)
						{
							return;
						}
						
						if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
						{
							bytes.order(ByteOrder.LITTLE_ENDIAN);
						}
						
						bytes.put(pos, (byte) m_NodeID);
						pos += JausUtils.getNumBytes("byte");
					}
					
					public void decode(ByteBuffer bytes, int pos)
					{
						
						if (bytes.array() == null)
						{
							return;
						}
						if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
						{
							bytes.order(ByteOrder.LITTLE_ENDIAN);
						}
						
						m_NodeID = (short) (bytes.get(pos) & 0xff);
						pos += JausUtils.getNumBytes("byte");
					}
					
					public ReportServices.Body.NodeList.NodeSeq.NodeRec assign(NodeRec value)
					{
						m_NodeID = value.m_NodeID;
						
						return this;
					}
					
					public boolean isEqual(NodeRec value)
					{
						if (m_NodeID != value.getNodeID())
						{
							return false;
						}
						
						return true;
					}
					
					public boolean notEquals(NodeRec value)
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
					
					public void finalize()
					{
					}
					
				}
				public static class  ComponentList
				{
					public static class  ComponentSeq
					{
						public static class  ComponentRec
						{
						
							protected ComponentSeq m_parent;
							protected short m_ComponentID;
							protected short m_InstanceID;
						
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
							
							public short getComponentID()
							{
								return m_ComponentID;
							}
							
							public void setComponentID(short value)
							{
								m_ComponentID = value;
								setParentPresenceVector();
							}
							
							public short getInstanceID()
							{
								return m_InstanceID;
							}
							
							public void setInstanceID(short value)
							{
								m_InstanceID = value;
								setParentPresenceVector();
							}
							
							/**
							 * Returns the number of bytes the used data members of the class occupies
							 * in the buffer. This is not the number of bytes the data type occupies in 
							 * Java, but the bytes expected on the wire.
							 * 
							 * @return
							 */
							public long getSize()
							{
								long size = 0;
								
								size += JausUtils.getNumBytes("byte");
								size += JausUtils.getNumBytes("byte");
								
								return size;
							}
							
							public void encode(ByteBuffer bytes, int pos)
							{
								
								if (bytes.array() == null)
								{
									return;
								}
								
								if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
								{
									bytes.order(ByteOrder.LITTLE_ENDIAN);
								}
								
								bytes.put(pos, (byte) m_ComponentID);
								pos += JausUtils.getNumBytes("byte");
								bytes.put(pos, (byte) m_InstanceID);
								pos += JausUtils.getNumBytes("byte");
							}
							
							public void decode(ByteBuffer bytes, int pos)
							{
								
								if (bytes.array() == null)
								{
									return;
								}
								if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
								{
									bytes.order(ByteOrder.LITTLE_ENDIAN);
								}
								
								m_ComponentID = (short) (bytes.get(pos) & 0xff);
								pos += JausUtils.getNumBytes("byte");
								m_InstanceID = (short) (bytes.get(pos) & 0xff);
								pos += JausUtils.getNumBytes("byte");
							}
							
							public ReportServices.Body.NodeList.NodeSeq.ComponentList.ComponentSeq.ComponentRec assign(ComponentRec value)
							{
								m_ComponentID = value.m_ComponentID;
								m_InstanceID = value.m_InstanceID;
								
								return this;
							}
							
							public boolean isEqual(ComponentRec value)
							{
								if (m_ComponentID != value.getComponentID())
								{
									return false;
								}
								if (m_InstanceID != value.getInstanceID())
								{
									return false;
								}
								
								return true;
							}
							
							public boolean notEquals(ComponentRec value)
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
							
							public void finalize()
							{
							}
							
						}
						public static class  ServiceList
						{
							public static class  ServiceRec
							{
							
								protected ServiceList m_parent;
								protected String m_URI;
								protected short m_MajorVersionNumber;
								protected short m_MinorVersionNumber;
							
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
								
								public String getURI()
								{
									return m_URI;
								}
								
								public void setURI(String value)
								{
									if ( value.length() > 255)
									{
										return;
									}
									
									m_URI = value;
									if (m_URI.length() < 0)
									{
										m_URI = m_URI.substring(0, 0);
									}
									setParentPresenceVector();
								}
								
								public short getMajorVersionNumber()
								{
									return m_MajorVersionNumber;
								}
								
								public void setMajorVersionNumber(short value)
								{
									m_MajorVersionNumber = value;
									setParentPresenceVector();
								}
								
								public short getMinorVersionNumber()
								{
									return m_MinorVersionNumber;
								}
								
								public void setMinorVersionNumber(short value)
								{
									m_MinorVersionNumber = value;
									setParentPresenceVector();
								}
								
								/**
								 * Returns the number of bytes the used data members of the class occupies
								 * in the buffer. This is not the number of bytes the data type occupies in 
								 * Java, but the bytes expected on the wire.
								 * 
								 * @return
								 */
								public long getSize()
								{
									long size = 0;
									
									size += JausUtils.getNumBytes("byte");
									size += m_URI.length();
									size += JausUtils.getNumBytes("byte");
									size += JausUtils.getNumBytes("byte");
									
									return size;
								}
								
								public void encode(ByteBuffer bytes, int pos)
								{
									
									if (bytes.array() == null)
									{
										return;
									}
									
									if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
									{
										bytes.order(ByteOrder.LITTLE_ENDIAN);
									}
									
									
									try
									{
									bytes.put(pos, (byte) m_URI.length());
									pos += JausUtils.getNumBytes("byte");
									
									char[] m_URIBytes = m_URI.toCharArray();
									for(int str_pos = 0; str_pos<m_URIBytes.length; str_pos++)
									{
										bytes.put(pos, (byte) m_URIBytes[str_pos]);
										pos++;
									}
									}
									catch (Exception e)
									{
										
									}
									bytes.put(pos, (byte) m_MajorVersionNumber);
									pos += JausUtils.getNumBytes("byte");
									bytes.put(pos, (byte) m_MinorVersionNumber);
									pos += JausUtils.getNumBytes("byte");
								}
								
								public void decode(ByteBuffer bytes, int pos)
								{
									
									if (bytes.array() == null)
									{
										return;
									}
									if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
									{
										bytes.order(ByteOrder.LITTLE_ENDIAN);
									}
									
									
									short m_URILength = 0;
									m_URILength = (short) (bytes.get(pos) & 0xff);
									pos += JausUtils.getNumBytes("byte");
									
									
									// Decoding the string m_URIfrom the buffer.
									char[] m_URIBytes = new char[(int)m_URILength];
									for(int decode_pos = 0; decode_pos<(int)m_URILength;decode_pos++)
									{
										m_URIBytes[decode_pos] = (char) (bytes.get(pos) &0xff);
										pos++;
									}
									m_URI = new String(m_URIBytes);
									m_MajorVersionNumber = (short) (bytes.get(pos) & 0xff);
									pos += JausUtils.getNumBytes("byte");
									m_MinorVersionNumber = (short) (bytes.get(pos) & 0xff);
									pos += JausUtils.getNumBytes("byte");
								}
								
								public ReportServices.Body.NodeList.NodeSeq.ComponentList.ComponentSeq.ServiceList.ServiceRec assign(ServiceRec value)
								{
									m_URI = value.m_URI;
									m_MajorVersionNumber = value.m_MajorVersionNumber;
									m_MinorVersionNumber = value.m_MinorVersionNumber;
									
									return this;
								}
								
								public boolean isEqual(ServiceRec value)
								{
									if ((m_URI.length() != value.m_URI.length()) || (m_URI.compareTo(value.m_URI) != 0))
									{
										return false;
									}
									if (m_MajorVersionNumber != value.getMajorVersionNumber())
									{
										return false;
									}
									if (m_MinorVersionNumber != value.getMinorVersionNumber())
									{
										return false;
									}
									
									return true;
								}
								
								public boolean notEquals(ServiceRec value)
								{
									return !this.isEqual(value);
								}
								
								public ServiceRec()
								{
									m_parent = null;
									m_URI = new String();
									m_MajorVersionNumber = 0;
									m_MinorVersionNumber = 0;
								}
								
								public ServiceRec(ServiceRec value)
								{
									/// Initiliaze the protected variables
									m_parent = null;
									m_URI = new String();
									m_MajorVersionNumber = 0;
									m_MinorVersionNumber = 0;
									
									/// Copy the values
									m_URI = value.m_URI;
									m_MajorVersionNumber = value.m_MajorVersionNumber;
									m_MinorVersionNumber = value.m_MinorVersionNumber;
								}
								
								public void finalize()
								{
								}
								
							}
						
						
							protected ComponentSeq m_parent;
							protected ArrayList<ServiceRec> m_ServiceRec;
						
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
							
							public long getNumberOfElements()
							{
								return (long) m_ServiceRec.size();
							}
							
							public ReportServices.Body.NodeList.NodeSeq.ComponentList.ComponentSeq.ServiceList.ServiceRec getElement(int index)
							{
								return m_ServiceRec.get(index);
							}
							
							public void setElement(int index, ServiceRec value)
							{
								if(m_ServiceRec.size()-1 < index)
								{
									return;
								}
								
								m_ServiceRec.set(index, value);
								m_ServiceRec.get(index).setParent(this);
								setParentPresenceVector();
							}
							
							public void addElement(ServiceRec value)
							{
								m_ServiceRec.add(value);
								m_ServiceRec.get(m_ServiceRec.size() -1 ).setParent(this);
								setParentPresenceVector();
							}
							
							public int deleteElement(int index)
							{
								if(m_ServiceRec.size()-1 < index)
								{
									return 1;
								}
								
								m_ServiceRec.remove(index);
								return 0;
							}
							
							public int deleteLastElement()
							{
								m_ServiceRec.remove(m_ServiceRec.size()-1);
								return 0;
							}
							
							/**
							 * Returns the number of bytes the used data members of the class occupies
							 * in the buffer. This is not the number of bytes the data type occupies in 
							 * Java, but the bytes expected on the wire.
							 * 
							 * @return
							 */
							public long getSize()
							{
								long size = 0;
								
								size += JausUtils.getNumBytes("byte");
								for (int i = 0; i < m_ServiceRec.size(); i++)
								{
									size += m_ServiceRec.get(i).getSize();
								}
								
								return size;
							}
							
							public void encode(ByteBuffer bytes, int pos)
							{
								
								if (bytes.array() == null)
								{
									return;
								}
								
								if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
								{
									bytes.order(ByteOrder.LITTLE_ENDIAN);
								}
								
								short size = (short) m_ServiceRec.size();
								bytes.put(pos, (byte) size);
								pos += JausUtils.getNumBytes("byte");
								for (int i = 0; i < m_ServiceRec.size(); i++)
								{
									m_ServiceRec.get(i).encode(bytes, pos);
									pos += m_ServiceRec.get(i).getSize();
								}
							}
							
							public void decode(ByteBuffer bytes, int pos)
							{
								
								if (bytes.array() == null)
								{
									return;
								}
								if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
								{
									bytes.order(ByteOrder.LITTLE_ENDIAN);
								}
								
								short size;
								size = (short) (bytes.get(pos) & 0xff);
								pos += JausUtils.getNumBytes("byte");
								m_ServiceRec = new ArrayList<ServiceRec>();
								for (int i = 0; i <  size; i++)
								{
									ServiceRec item = new ServiceRec();
									item.decode(bytes, pos);
									m_ServiceRec.add(item);
									pos += item.getSize();
								}
							}
							
							public ReportServices.Body.NodeList.NodeSeq.ComponentList.ComponentSeq.ServiceList assign(ServiceList value)
							{
								m_ServiceRec.clear();
								
								for (int i = 0; i < value.m_ServiceRec.size(); i++)
								{
									m_ServiceRec.add(value.m_ServiceRec.get(i));
									m_ServiceRec.get(i).setParent(this);
								}
								
								return this;
							}
							
							public boolean isEqual(ServiceList value)
							{
								for (int i = 0; i < m_ServiceRec.size(); i++)
								{
									if (!m_ServiceRec.get(i).isEqual(value.getElement(i)))
									{
										return false;
									}
								}
								
								return true;
							}
							
							public boolean notEquals(ServiceList value)
							{
								return !this.isEqual(value);
							}
							
							public ServiceList()
							{
								m_parent = null;
								m_ServiceRec = new ArrayList<ServiceRec>();
								for (int i = 0; i < m_ServiceRec.size(); i++)
								{
									m_ServiceRec.get(i).setParent(this);
								}
							}
							
							public ServiceList(ServiceList value)
							{
								/// Initiliaze the protected variables
								m_parent = null;
								m_ServiceRec = new ArrayList<ServiceRec>();
								for (int i = 0; i < m_ServiceRec.size(); i++)
								{
									m_ServiceRec.get(i).setParent(this);
								}
								
								/// Copy the values
								m_ServiceRec.clear();
								
								for (int i = 0; i < value.m_ServiceRec.size(); i++)
								{
									m_ServiceRec.add(value.m_ServiceRec.get(i));
									m_ServiceRec.get(i).setParent(this);
								}
							}
							
							public void finalize()
							{
							}
							
						}
					
					
						protected ComponentList m_parent;
						protected ComponentRec m_ComponentRec;
						protected ServiceList m_ServiceList;
					
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
						 * Java, but the bytes expected on the wire.
						 * 
						 * @return
						 */
						public long getSize()
						{
							long size = 0;
							
							size += m_ComponentRec.getSize();
							size += m_ServiceList.getSize();
							
							return size;
						}
						
						public void encode(ByteBuffer bytes, int pos)
						{
							
							if (bytes.array() == null)
							{
								return;
							}
							
							if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
							{
								bytes.order(ByteOrder.LITTLE_ENDIAN);
							}
							
							m_ComponentRec.encode(bytes, pos);
							pos += m_ComponentRec.getSize();
							m_ServiceList.encode(bytes, pos);
							pos += m_ServiceList.getSize();
						}
						
						public void decode(ByteBuffer bytes, int pos)
						{
							
							if (bytes.array() == null)
							{
								return;
							}
							if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
							{
								bytes.order(ByteOrder.LITTLE_ENDIAN);
							}
							
							m_ComponentRec.decode(bytes, pos);
							pos += m_ComponentRec.getSize();
							m_ServiceList.decode(bytes, pos);
							pos += m_ServiceList.getSize();
						}
						
						public ReportServices.Body.NodeList.NodeSeq.ComponentList.ComponentSeq assign(ComponentSeq value)
						{
							m_ComponentRec = value.m_ComponentRec;
							m_ComponentRec.setParent(this);
							m_ComponentRec = value.m_ComponentRec;
							m_ServiceList = value.m_ServiceList;
							m_ServiceList.setParent(this);
							m_ServiceList = value.m_ServiceList;
							
							return this;
						}
						
						public boolean isEqual(ComponentSeq value)
						{
							if (!m_ComponentRec.isEqual(value.getComponentRec()))
							{
								return false;
							}
							
							if (!m_ComponentRec.isEqual(value.getComponentRec()))
							{
								return false;
							}
							if (!m_ServiceList.isEqual(value.m_ServiceList))
							{
								return false;
							}
							
							if (!m_ServiceList.isEqual(value.getServiceList()))
							{
								return false;
							}
							
							return true;
						}
						
						public boolean notEquals(ComponentSeq value)
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
							m_ComponentRec = value.m_ComponentRec;
							m_ComponentRec.setParent(this);
							m_ComponentRec = value.m_ComponentRec;
							m_ServiceList = value.m_ServiceList;
							m_ServiceList.setParent(this);
							m_ServiceList = value.m_ServiceList;
						}
						
						public void finalize()
						{
						}
						
					}
				
				
					protected NodeSeq m_parent;
					protected ArrayList<ComponentSeq> m_ComponentSeq;
				
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
						return m_ComponentSeq.size();
					}
					
					public ReportServices.Body.NodeList.NodeSeq.ComponentList.ComponentSeq getElement(int index)
					{
						return m_ComponentSeq.get(index);
					}
					
					public void setElement(int index, ComponentSeq value)
					{
						if(m_ComponentSeq.size()-1 < index)
						{
							return;
						}
						
						m_ComponentSeq.set(index, value);
						m_ComponentSeq.get(index).setParent(this);
						setParentPresenceVector();
					}
					
					public void addElement(ComponentSeq value)
					{
						m_ComponentSeq.add(value);
						m_ComponentSeq.get(m_ComponentSeq.size() -1).setParent(this);
						setParentPresenceVector();
					}
					
					public int deleteElement(int index)
					{
						if(m_ComponentSeq.size()-1 < index)
						{
							return 1;
						}
						
						m_ComponentSeq.remove(index);
						return 0;
					}
					
					public int deleteLastElement()
					{
						m_ComponentSeq.remove(m_ComponentSeq.size() -1);
						return 0;
					}
					
					/**
					 * Returns the number of bytes the used data members of the class occupies
					 * in the buffer. This is not the number of bytes the data type occupies in 
					 * Java, but the bytes expected on the wire.
					 * 
					 * @return
					 */
					public long getSize()
					{
						long size = 0;
						
						size += JausUtils.getNumBytes("byte");
						for (int i = 0; i < m_ComponentSeq.size(); i++)
						{
							size += m_ComponentSeq.get(i).getSize();
						}
						
						return size;
					}
					
					public void encode(ByteBuffer bytes, int pos)
					{
						
						if (bytes.array() == null)
						{
							return;
						}
						
						if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
						{
							bytes.order(ByteOrder.LITTLE_ENDIAN);
						}
						
						short size = (short) m_ComponentSeq.size();
						bytes.put(pos, (byte) size);
						pos += JausUtils.getNumBytes("byte");
						for (int i = 0; i < m_ComponentSeq.size(); i++)
						{
							m_ComponentSeq.get(i).encode(bytes, pos);
							pos += m_ComponentSeq.get(i).getSize();
						}
					}
					
					public void decode(ByteBuffer bytes, int pos)
					{
						
						if (bytes.array() == null)
						{
							return;
						}
						if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
						{
							bytes.order(ByteOrder.LITTLE_ENDIAN);
						}
						
						short size;
						size = (short) (bytes.get(pos) & 0xff);
						pos += JausUtils.getNumBytes("byte");
						m_ComponentSeq = new ArrayList<ComponentSeq>();
						for (int i = 0; i <  size; i++)
						{
							ComponentSeq item = new ComponentSeq();
							item.decode(bytes, pos);
							m_ComponentSeq.add(item);
							pos += item.getSize();
						}
					}
					
					public ReportServices.Body.NodeList.NodeSeq.ComponentList assign(ComponentList value)
					{
						m_ComponentSeq.clear();
						
						for (int i = 0; i < value.m_ComponentSeq.size(); i++)
						{
							m_ComponentSeq.add(value.m_ComponentSeq.get(i));
							m_ComponentSeq.get(i).setParent(this);
						}
						
						return this;
					}
					
					public boolean isEqual(ComponentList value)
					{
						for (int i = 0; i < m_ComponentSeq.size(); i++)
						{
							if (!m_ComponentSeq.get(i).isEqual(value.m_ComponentSeq.get(i)))
							{
								return false;
							}
						}
						
						return true;
					}
					
					public boolean notEquals(ComponentList value)
					{
						return !this.isEqual(value);
					}
					
					public ComponentList()
					{
						m_parent = null;
						m_ComponentSeq = new ArrayList<ComponentSeq>();
						for (int i = 0; i < m_ComponentSeq.size(); i++)
						{
							m_ComponentSeq.get(i).setParent(this);
						}
					}
					
					public ComponentList(ComponentList value)
					{
						/// Initiliaze the protected variables
						m_parent = null;
						m_ComponentSeq = new ArrayList<ComponentSeq>();
						for (int i = 0; i < m_ComponentSeq.size(); i++)
						{
							m_ComponentSeq.get(i).setParent(this);
						}
						
						/// Copy the values
						m_ComponentSeq.clear();
						
						for (int i = 0; i < value.m_ComponentSeq.size(); i++)
						{
							m_ComponentSeq.add(value.m_ComponentSeq.get(i));
							m_ComponentSeq.get(i).setParent(this);
						}
					}
					
					public void finalize()
					{
					}
					
				}
			
			
				protected NodeList m_parent;
				protected NodeRec m_NodeRec;
				protected ComponentList m_ComponentList;
			
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
				 * Java, but the bytes expected on the wire.
				 * 
				 * @return
				 */
				public long getSize()
				{
					long size = 0;
					
					size += m_NodeRec.getSize();
					size += m_ComponentList.getSize();
					
					return size;
				}
				
				public void encode(ByteBuffer bytes, int pos)
				{
					
					if (bytes.array() == null)
					{
						return;
					}
					
					if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
					{
						bytes.order(ByteOrder.LITTLE_ENDIAN);
					}
					
					m_NodeRec.encode(bytes, pos);
					pos += m_NodeRec.getSize();
					m_ComponentList.encode(bytes, pos);
					pos += m_ComponentList.getSize();
				}
				
				public void decode(ByteBuffer bytes, int pos)
				{
					
					if (bytes.array() == null)
					{
						return;
					}
					if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
					{
						bytes.order(ByteOrder.LITTLE_ENDIAN);
					}
					
					m_NodeRec.decode(bytes, pos);
					pos += m_NodeRec.getSize();
					m_ComponentList.decode(bytes, pos);
					pos += m_ComponentList.getSize();
				}
				
				public ReportServices.Body.NodeList.NodeSeq assign(NodeSeq value)
				{
					m_NodeRec = value.m_NodeRec;
					m_NodeRec.setParent(this);
					m_NodeRec = value.m_NodeRec;
					m_ComponentList = value.m_ComponentList;
					m_ComponentList.setParent(this);
					m_ComponentList = value.m_ComponentList;
					
					return this;
				}
				
				public boolean isEqual(NodeSeq value)
				{
					if (!m_NodeRec.isEqual(value.getNodeRec()))
					{
						return false;
					}
					
					if (!m_NodeRec.isEqual(value.getNodeRec()))
					{
						return false;
					}
					if (!m_ComponentList.isEqual(value.m_ComponentList))
					{
						return false;
					}
					
					if (!m_ComponentList.isEqual(value.getComponentList()))
					{
						return false;
					}
					
					return true;
				}
				
				public boolean notEquals(NodeSeq value)
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
					m_NodeRec = value.m_NodeRec;
					m_NodeRec.setParent(this);
					m_NodeRec = value.m_NodeRec;
					m_ComponentList = value.m_ComponentList;
					m_ComponentList.setParent(this);
					m_ComponentList = value.m_ComponentList;
				}
				
				public void finalize()
				{
				}
				
			}
		
		
			protected Body m_parent;
			protected ArrayList<NodeSeq> m_NodeSeq;
		
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
				return m_NodeSeq.size();
			}
			
			public ReportServices.Body.NodeList.NodeSeq getElement(int index)
			{
				return m_NodeSeq.get(index);
			}
			
			public void setElement(int index, NodeSeq value)
			{
				if(m_NodeSeq.size()-1 < index)
				{
					return;
				}
				
				m_NodeSeq.set(index, value);
				m_NodeSeq.get(index).setParent(this);
				setParentPresenceVector();
			}
			
			public void addElement(NodeSeq value)
			{
				m_NodeSeq.add(value);
				m_NodeSeq.get(m_NodeSeq.size() -1).setParent(this);
				setParentPresenceVector();
			}
			
			public int deleteElement(int index)
			{
				if(m_NodeSeq.size()-1 < index)
				{
					return 1;
				}
				
				m_NodeSeq.remove(index);
				return 0;
			}
			
			public int deleteLastElement()
			{
				m_NodeSeq.remove(m_NodeSeq.size() -1);
				return 0;
			}
			
			/**
			 * Returns the number of bytes the used data members of the class occupies
			 * in the buffer. This is not the number of bytes the data type occupies in 
			 * Java, but the bytes expected on the wire.
			 * 
			 * @return
			 */
			public long getSize()
			{
				long size = 0;
				
				size += JausUtils.getNumBytes("byte");
				for (int i = 0; i < m_NodeSeq.size(); i++)
				{
					size += m_NodeSeq.get(i).getSize();
				}
				
				return size;
			}
			
			public void encode(ByteBuffer bytes, int pos)
			{
				
				if (bytes.array() == null)
				{
					return;
				}
				
				if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
				{
					bytes.order(ByteOrder.LITTLE_ENDIAN);
				}
				
				short size = (short) m_NodeSeq.size();
				bytes.put(pos, (byte) size);
				pos += JausUtils.getNumBytes("byte");
				for (int i = 0; i < m_NodeSeq.size(); i++)
				{
					m_NodeSeq.get(i).encode(bytes, pos);
					pos += m_NodeSeq.get(i).getSize();
				}
			}
			
			public void decode(ByteBuffer bytes, int pos)
			{
				
				if (bytes.array() == null)
				{
					return;
				}
				if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
				{
					bytes.order(ByteOrder.LITTLE_ENDIAN);
				}
				
				short size;
				size = (short) (bytes.get(pos) & 0xff);
				pos += JausUtils.getNumBytes("byte");
				m_NodeSeq = new ArrayList<NodeSeq>();
				for (int i = 0; i <  size; i++)
				{
					NodeSeq item = new NodeSeq();
					item.decode(bytes, pos);
					m_NodeSeq.add(item);
					pos += item.getSize();
				}
			}
			
			public ReportServices.Body.NodeList assign(NodeList value)
			{
				m_NodeSeq.clear();
				
				for (int i = 0; i < value.m_NodeSeq.size(); i++)
				{
					m_NodeSeq.add(value.m_NodeSeq.get(i));
					m_NodeSeq.get(i).setParent(this);
				}
				
				return this;
			}
			
			public boolean isEqual(NodeList value)
			{
				for (int i = 0; i < m_NodeSeq.size(); i++)
				{
					if (!m_NodeSeq.get(i).isEqual(value.m_NodeSeq.get(i)))
					{
						return false;
					}
				}
				
				return true;
			}
			
			public boolean notEquals(NodeList value)
			{
				return !this.isEqual(value);
			}
			
			public NodeList()
			{
				m_parent = null;
				m_NodeSeq = new ArrayList<NodeSeq>();
				for (int i = 0; i < m_NodeSeq.size(); i++)
				{
					m_NodeSeq.get(i).setParent(this);
				}
			}
			
			public NodeList(NodeList value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_NodeSeq = new ArrayList<NodeSeq>();
				for (int i = 0; i < m_NodeSeq.size(); i++)
				{
					m_NodeSeq.get(i).setParent(this);
				}
				
				/// Copy the values
				m_NodeSeq.clear();
				
				for (int i = 0; i < value.m_NodeSeq.size(); i++)
				{
					m_NodeSeq.add(value.m_NodeSeq.get(i));
					m_NodeSeq.get(i).setParent(this);
				}
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected NodeList m_NodeList;
	
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
		 * Java, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public long getSize()
		{
			long size = 0;
			
			size += m_NodeList.getSize();
			
			return size;
		}
		
		public void encode(ByteBuffer bytes, int pos)
		{
			
			if (bytes.array() == null)
			{
				return;
			}
			
			if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
			{
				bytes.order(ByteOrder.LITTLE_ENDIAN);
			}
			
			m_NodeList.encode(bytes, pos);
			pos += m_NodeList.getSize();
		}
		
		public void decode(ByteBuffer bytes, int pos)
		{
			
			if (bytes.array() == null)
			{
				return;
			}
			if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
			{
				bytes.order(ByteOrder.LITTLE_ENDIAN);
			}
			
			m_NodeList.decode(bytes, pos);
			pos += m_NodeList.getSize();
		}
		
		public ReportServices.Body assign(Body value)
		{
			m_NodeList = value.m_NodeList;
			m_NodeList.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(Body value)
		{
			if (!m_NodeList.isEqual(value.m_NodeList))
			{
				return false;
			}
			/// This code is currently not supported
			return true;
		}
		
		public boolean notEquals(Body value)
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
		
		public void finalize()
		{
		}
		
	}
	protected JAUSApplicationLayerHeader m_JAUSApplicationLayerHeader;
	protected Body m_Body;
	public long getID()
	{
	return ID;
 }
	public ReportServices.JAUSApplicationLayerHeader getJAUSApplicationLayerHeader()
	{
		return m_JAUSApplicationLayerHeader;
	}
	
	public void setJAUSApplicationLayerHeader(JAUSApplicationLayerHeader value)
	{
		m_JAUSApplicationLayerHeader = value;
	}
	
	public ReportServices.Body getBody()
	{
		return m_Body;
	}
	
	public int setBody(Body value)
	{
		m_Body = value;
		return 0;
	}
	
	/**
	 * Returns the number of bytes the used data members of the class occupies
	 * in the buffer. This is not the number of bytes the data type occupies in 
	 * Java, but the bytes expected on the wire.
	 * 
	 * @return
	 */
	public long getSize()
	{
		int size = 0;
		
		size += m_JAUSApplicationLayerHeader.getSize();
		size += m_Body.getSize();
		
		return size;
	}
	
	public void encode(ByteBuffer bytes, int pos)
	{
		
		if (bytes.array() == null)
		{
			return;
		}
		if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
		{
			bytes.order(ByteOrder.LITTLE_ENDIAN);
		}
		
		m_JAUSApplicationLayerHeader.encode(bytes, pos);
		pos += m_JAUSApplicationLayerHeader.getSize();
		m_Body.encode(bytes, pos);
		pos += m_Body.getSize();
	}
	
	public void decode(ByteBuffer bytes, int pos)
	{
		
		if (bytes.array() == null)
		{
			return;
		}
		if(bytes.order() != ByteOrder.LITTLE_ENDIAN)
		{
			bytes.order(ByteOrder.LITTLE_ENDIAN);
		}
		
		m_JAUSApplicationLayerHeader.decode(bytes, pos);
		pos += m_JAUSApplicationLayerHeader.getSize();
		m_Body.decode(bytes, pos);
		pos += m_Body.getSize();
	}
	
	public ReportServices setAs(ReportServices value)
	{
		m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(ReportServices value)
	{
		if (!m_JAUSApplicationLayerHeader.isEqual(value.getJAUSApplicationLayerHeader()))
		{
			return false;
		}
		if (!m_Body.isEqual(value.getBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public boolean notEquals(ReportServices value)
	{
		return !isEqual(value);
	}
	
	public ReportServices()
	{
		m_JAUSApplicationLayerHeader = new JAUSApplicationLayerHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  ReportServices(ReportServices value)
	{
		/// Initiliaze the protected variables
		m_JAUSApplicationLayerHeader = new JAUSApplicationLayerHeader();
		m_Body = new Body();
		m_IsCommand = false;
		
		/// Copy the values
		m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
		m_Body = value.m_Body;
	}
	
}
