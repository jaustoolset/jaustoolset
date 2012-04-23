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

public class QueryServices extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x2b03;
	
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
			
			public QueryServices.JAUSApplicationLayerHeader.HeaderRec assign(HeaderRec value)
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
				m_MessageID = 0x2b03;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x2b03;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
		public QueryServices.JAUSApplicationLayerHeader.HeaderRec getHeaderRec()
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
		
		public QueryServices.JAUSApplicationLayerHeader assign(JAUSApplicationLayerHeader value)
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
					
					public QueryServices.Body.NodeList.NodeSeq.NodeRec assign(NodeRec value)
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
					public static class  ComponentRec
					{
					
						protected ComponentList m_parent;
						protected short m_ComponentID;
					
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
						
						public short getComponentID()
						{
							return m_ComponentID;
						}
						
						public void setComponentID(short value)
						{
							m_ComponentID = value;
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
							
							bytes.put(pos, (byte) m_ComponentID);
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
						}
						
						public QueryServices.Body.NodeList.NodeSeq.ComponentList.ComponentRec assign(ComponentRec value)
						{
							m_ComponentID = value.m_ComponentID;
							
							return this;
						}
						
						public boolean isEqual(ComponentRec value)
						{
							if (m_ComponentID != value.getComponentID())
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
						}
						
						public ComponentRec(ComponentRec value)
						{
							/// Initiliaze the protected variables
							m_parent = null;
							m_ComponentID = 0;
							
							/// Copy the values
							m_ComponentID = value.m_ComponentID;
						}
						
						public void finalize()
						{
						}
						
					}
				
				
					protected NodeSeq m_parent;
					protected ArrayList<ComponentRec> m_ComponentRec;
				
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
					
					public long getNumberOfElements()
					{
						return (long) m_ComponentRec.size();
					}
					
					public QueryServices.Body.NodeList.NodeSeq.ComponentList.ComponentRec getElement(int index)
					{
						return m_ComponentRec.get(index);
					}
					
					public void setElement(int index, ComponentRec value)
					{
						if(m_ComponentRec.size()-1 < index)
						{
							return;
						}
						
						m_ComponentRec.set(index, value);
						m_ComponentRec.get(index).setParent(this);
						setParentPresenceVector();
					}
					
					public void addElement(ComponentRec value)
					{
						m_ComponentRec.add(value);
						m_ComponentRec.get(m_ComponentRec.size() -1 ).setParent(this);
						setParentPresenceVector();
					}
					
					public int deleteElement(int index)
					{
						if(m_ComponentRec.size()-1 < index)
						{
							return 1;
						}
						
						m_ComponentRec.remove(index);
						return 0;
					}
					
					public int deleteLastElement()
					{
						m_ComponentRec.remove(m_ComponentRec.size()-1);
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
						for (int i = 0; i < m_ComponentRec.size(); i++)
						{
							size += m_ComponentRec.get(i).getSize();
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
						
						short size = (short) m_ComponentRec.size();
						bytes.put(pos, (byte) size);
						pos += JausUtils.getNumBytes("byte");
						for (int i = 0; i < m_ComponentRec.size(); i++)
						{
							m_ComponentRec.get(i).encode(bytes, pos);
							pos += m_ComponentRec.get(i).getSize();
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
						m_ComponentRec = new ArrayList<ComponentRec>();
						for (int i = 0; i <  size; i++)
						{
							ComponentRec item = new ComponentRec();
							item.decode(bytes, pos);
							m_ComponentRec.add(item);
							pos += item.getSize();
						}
					}
					
					public QueryServices.Body.NodeList.NodeSeq.ComponentList assign(ComponentList value)
					{
						m_ComponentRec.clear();
						
						for (int i = 0; i < value.m_ComponentRec.size(); i++)
						{
							m_ComponentRec.add(value.m_ComponentRec.get(i));
							m_ComponentRec.get(i).setParent(this);
						}
						
						return this;
					}
					
					public boolean isEqual(ComponentList value)
					{
						for (int i = 0; i < m_ComponentRec.size(); i++)
						{
							if (!m_ComponentRec.get(i).isEqual(value.getElement(i)))
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
						m_ComponentRec = new ArrayList<ComponentRec>();
						for (int i = 0; i < m_ComponentRec.size(); i++)
						{
							m_ComponentRec.get(i).setParent(this);
						}
					}
					
					public ComponentList(ComponentList value)
					{
						/// Initiliaze the protected variables
						m_parent = null;
						m_ComponentRec = new ArrayList<ComponentRec>();
						for (int i = 0; i < m_ComponentRec.size(); i++)
						{
							m_ComponentRec.get(i).setParent(this);
						}
						
						/// Copy the values
						m_ComponentRec.clear();
						
						for (int i = 0; i < value.m_ComponentRec.size(); i++)
						{
							m_ComponentRec.add(value.m_ComponentRec.get(i));
							m_ComponentRec.get(i).setParent(this);
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
				
				public QueryServices.Body.NodeList.NodeSeq.NodeRec getNodeRec()
				{
					return m_NodeRec;
				}
				
				public void setNodeRec(NodeRec value)
				{
					m_NodeRec = value;
					setParentPresenceVector();
				}
				
				public QueryServices.Body.NodeList.NodeSeq.ComponentList getComponentList()
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
				
				public QueryServices.Body.NodeList.NodeSeq assign(NodeSeq value)
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
			
			public QueryServices.Body.NodeList.NodeSeq getElement(int index)
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
			
			public QueryServices.Body.NodeList assign(NodeList value)
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
	
		public QueryServices.Body.NodeList getNodeList()
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
		
		public QueryServices.Body assign(Body value)
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
	public QueryServices.JAUSApplicationLayerHeader getJAUSApplicationLayerHeader()
	{
		return m_JAUSApplicationLayerHeader;
	}
	
	public void setJAUSApplicationLayerHeader(JAUSApplicationLayerHeader value)
	{
		m_JAUSApplicationLayerHeader = value;
	}
	
	public QueryServices.Body getBody()
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
	
	public QueryServices setAs(QueryServices value)
	{
		m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(QueryServices value)
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
	
	public boolean notEquals(QueryServices value)
	{
		return !isEqual(value);
	}
	
	public QueryServices()
	{
		m_JAUSApplicationLayerHeader = new JAUSApplicationLayerHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  QueryServices(QueryServices value)
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
