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

public class SetElement extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x041a;
	
	public static class  AppHeader
	{
		public static class  HeaderRec
		{
		
			protected AppHeader m_parent;
			protected int m_MessageID;
		
			public void setParent(AppHeader parent)
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
			
			public SetElement.AppHeader.HeaderRec assign(HeaderRec value)
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
				m_MessageID = 0x041a;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x041a;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
		public SetElement.AppHeader.HeaderRec getHeaderRec()
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
		
		public SetElement.AppHeader assign(AppHeader value)
		{
			m_HeaderRec = value.m_HeaderRec;
			m_HeaderRec.setParent(this);
			
			return this;
		}
		
		public boolean isEqual(AppHeader value)
		{
			if (!m_HeaderRec.isEqual(value.getHeaderRec()))
			{
				return false;
			}
			return true;
		}
		
		public boolean notEquals(AppHeader value)
		{
			return !this.isEqual(value);
		}
		
		public AppHeader()
		{
			m_HeaderRec = new HeaderRec();
			m_HeaderRec.setParent(this);
		}
		
		public AppHeader(AppHeader value)
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
		public static class  SetElementSeq
		{
			public static class  RequestIDRec
			{
			
				protected SetElementSeq m_parent;
				protected short m_RequestID;
			
				public void setParent(SetElementSeq parent)
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
				
				public short getRequestID()
				{
					return m_RequestID;
				}
				
				public void setRequestID(short value)
				{
					m_RequestID = value;
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
					
					bytes.put(pos, (byte) m_RequestID);
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
					
					m_RequestID = (short) (bytes.get(pos) & 0xff);
					pos += JausUtils.getNumBytes("byte");
				}
				
				public SetElement.Body.SetElementSeq.RequestIDRec assign(RequestIDRec value)
				{
					m_RequestID = value.m_RequestID;
					
					return this;
				}
				
				public boolean isEqual(RequestIDRec value)
				{
					if (m_RequestID != value.getRequestID())
					{
						return false;
					}
					
					return true;
				}
				
				public boolean notEquals(RequestIDRec value)
				{
					return !this.isEqual(value);
				}
				
				public RequestIDRec()
				{
					m_parent = null;
					m_RequestID = 0;
				}
				
				public RequestIDRec(RequestIDRec value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_RequestID = 0;
					
					/// Copy the values
					m_RequestID = value.m_RequestID;
				}
				
				public void finalize()
				{
				}
				
			}
			public static class  ElementList
			{
				public static class  ElementRec
				{
					public static class  ElementData
					{
					
						protected ElementRec m_parent;
						protected short m_Format;
						protected int m_Length;
						protected ByteBuffer m_Data;
					
						public void setParent(ElementRec parent)
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
						
						public short getFormat()
						{
							return m_Format;
						}
						
						public int getLength()
						{
							return m_Length;
						}
						
						public ByteBuffer getData()
						{
							m_Data.rewind();
							ByteBuffer ret = ByteBuffer.allocate(m_Data.array().length);
							ret.order(ByteOrder.LITTLE_ENDIAN);
							ret.put(m_Data);
							return ret;
						}
						
						public void set(short format, int length, ByteBuffer data)
						{
							if ((format == 0)||(format == 1))
							{
								m_Format = format;
								m_Length = length;
							
								m_Data.clear();
							
								data.rewind();
								m_Data = ByteBuffer.allocate(data.array().length);
								m_Data.order(ByteOrder.LITTLE_ENDIAN);
								m_Data.put(data);
							
							setParentPresenceVector();
							
								return;
							}
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
							size += JausUtils.getNumBytes("short");
							size += m_Length;
							
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
							
							bytes.put(pos, (byte) m_Format);
							pos += JausUtils.getNumBytes("byte");
							
							bytes.putShort(pos, (short) m_Length);
							pos += JausUtils.getNumBytes("short");
							
							byte[] dataBytes = m_Data.array();
							
							for(int i = 0; i<(int) m_Length; i++)
							{
								bytes.put(pos, dataBytes[i]);
								pos++;
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
							
							m_Format = (short) (bytes.get(pos) & 0xff);
							pos += JausUtils.getNumBytes("byte");
							
							m_Length = bytes.getShort(pos) & 0xffff;
							pos += JausUtils.getNumBytes("short");
							
							byte[] dataBytes = new byte[(int)m_Length];
							
							int i;
							for(i=0; i<(int)m_Length; i++)
							{
								dataBytes[i] = bytes.get(pos);
								pos++;
							}
							
							m_Data = ByteBuffer.allocate((int)m_Length).order(ByteOrder.LITTLE_ENDIAN);
							m_Data.put(dataBytes);
						}
						
						public SetElement.Body.SetElementSeq.ElementList.ElementRec.ElementData assign(ElementData value)
						{
							this.m_Format = value.m_Format;
							this.m_Length = value.m_Length;
							
							m_Data.clear();
							
							if (m_Length > 0)
							{
							this.m_Data = ByteBuffer.wrap(value.m_Data.array()).order(ByteOrder.LITTLE_ENDIAN);
							}
							
							return this;
						}
						
						public boolean isEqual(ElementData value)
						{
							if (this.m_Format != value.m_Format)
							{
								return false;
							}
							
							if (this.m_Length != value.m_Length)
							{
								return false;
							}
							
							if ((this.m_Data.array() != null) && (value.m_Data.array() != null))
							{
								if(!Arrays.equals(this.m_Data.array(), value.m_Data.array()))
								{
									return false;
								}
							}
							// This case should never be true since it should not be possible
							// for the two variables to have equal lengths but one has no data.
							// This check is placed here as a secondary check.
							else if ((this.m_Data.array() != null) || (value.m_Data.array() != null))
							{
								return false;
							}
							
							return true;
						}
						
						public boolean notEquals(ElementData value)
						{
							return !this.isEqual(value);
						}
						
						public ElementData()
						{
							m_parent = null;
							m_Length = MAX_JTS_MESSAGE_SIZE;
							m_Data = ByteBuffer.allocate((int)m_Length);
							m_Data.order(ByteOrder.LITTLE_ENDIAN);
							m_Format = 0;
						}
						
						public ElementData(ElementData value)
						{
							/// Initiliaze the protected variables
							m_parent = null;
							m_Length = MAX_JTS_MESSAGE_SIZE;
							m_Data = ByteBuffer.allocate((int)m_Length);
							m_Data.order(ByteOrder.LITTLE_ENDIAN);
							m_Format = 0;
							
							/// Copy the values
							this.m_Format = value.m_Format;
							this.m_Length = value.m_Length;
							
							m_Data.clear();
							
							if (m_Length > 0)
							{
							this.m_Data = ByteBuffer.wrap(value.m_Data.array()).order(ByteOrder.LITTLE_ENDIAN);
							}
						}
						
						public void finalize()
						{
						}
						
					}
				
				
					protected ElementList m_parent;
					protected int m_ElementUID;
					protected int m_PreviousUID;
					protected int m_NextUID;
					protected ElementData m_ElementData;
				
					public void setParent(ElementList parent)
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
					
					public int getElementUID()
					{
						return m_ElementUID;
					}
					
					public void setElementUID(int value)
					{
						m_ElementUID = value;
						setParentPresenceVector();
					}
					
					public int getPreviousUID()
					{
						return m_PreviousUID;
					}
					
					public void setPreviousUID(int value)
					{
						m_PreviousUID = value;
						setParentPresenceVector();
					}
					
					public int getNextUID()
					{
						return m_NextUID;
					}
					
					public void setNextUID(int value)
					{
						m_NextUID = value;
						setParentPresenceVector();
					}
					
					public SetElement.Body.SetElementSeq.ElementList.ElementRec.ElementData getElementData()
					{
						return m_ElementData;
					}
					
					public int setElementData(ElementData value)
					{
						m_ElementData = value;
						setParentPresenceVector();
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
						
						size += JausUtils.getNumBytes("short");
						size += JausUtils.getNumBytes("short");
						size += JausUtils.getNumBytes("short");
						size += m_ElementData.getSize();
						
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
						
						bytes.putShort(pos, (short) m_ElementUID);
						pos += JausUtils.getNumBytes("short");
						bytes.putShort(pos, (short) m_PreviousUID);
						pos += JausUtils.getNumBytes("short");
						bytes.putShort(pos, (short) m_NextUID);
						pos += JausUtils.getNumBytes("short");
						m_ElementData.encode(bytes, pos);
						pos += m_ElementData.getSize();
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
						
						m_ElementUID = bytes.getShort(pos) & 0xffff;
						pos += JausUtils.getNumBytes("short");
						m_PreviousUID = bytes.getShort(pos) & 0xffff;
						pos += JausUtils.getNumBytes("short");
						m_NextUID = bytes.getShort(pos) & 0xffff;
						pos += JausUtils.getNumBytes("short");
						m_ElementData.decode(bytes, pos);
						pos += m_ElementData.getSize();
					}
					
					public SetElement.Body.SetElementSeq.ElementList.ElementRec assign(ElementRec value)
					{
						m_ElementUID = value.m_ElementUID;
						m_PreviousUID = value.m_PreviousUID;
						m_NextUID = value.m_NextUID;
						m_ElementData = value.m_ElementData;
						
						return this;
					}
					
					public boolean isEqual(ElementRec value)
					{
						if (m_ElementUID != value.getElementUID())
						{
							return false;
						}
						if (m_PreviousUID != value.getPreviousUID())
						{
							return false;
						}
						if (m_NextUID != value.getNextUID())
						{
							return false;
						}
						
						if (!m_ElementData.isEqual(value.getElementData()))
						{
							return false;
						}
						
						return true;
					}
					
					public boolean notEquals(ElementRec value)
					{
						return !this.isEqual(value);
					}
					
					public ElementRec()
					{
						m_parent = null;
						m_ElementUID = 0;
						m_PreviousUID = 0;
						m_NextUID = 0;
						m_ElementData = new ElementData();
						m_ElementData.setParent(this);
					}
					
					public ElementRec(ElementRec value)
					{
						/// Initiliaze the protected variables
						m_parent = null;
						m_ElementUID = 0;
						m_PreviousUID = 0;
						m_NextUID = 0;
						m_ElementData = new ElementData();
						m_ElementData.setParent(this);
						
						/// Copy the values
						m_ElementUID = value.m_ElementUID;
						m_PreviousUID = value.m_PreviousUID;
						m_NextUID = value.m_NextUID;
						m_ElementData = value.m_ElementData;
					}
					
					public void finalize()
					{
					}
					
				}
			
			
				protected SetElementSeq m_parent;
				protected ArrayList<ElementRec> m_ElementRec;
			
				public void setParent(SetElementSeq parent)
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
					return (long) m_ElementRec.size();
				}
				
				public SetElement.Body.SetElementSeq.ElementList.ElementRec getElement(int index)
				{
					return m_ElementRec.get(index);
				}
				
				public void setElement(int index, ElementRec value)
				{
					if(m_ElementRec.size()-1 < index)
					{
						return;
					}
					
					m_ElementRec.set(index, value);
					m_ElementRec.get(index).setParent(this);
					setParentPresenceVector();
				}
				
				public void addElement(ElementRec value)
				{
					m_ElementRec.add(value);
					m_ElementRec.get(m_ElementRec.size() -1 ).setParent(this);
					setParentPresenceVector();
				}
				
				public int deleteElement(int index)
				{
					if(m_ElementRec.size()-1 < index)
					{
						return 1;
					}
					
					m_ElementRec.remove(index);
					return 0;
				}
				
				public int deleteLastElement()
				{
					m_ElementRec.remove(m_ElementRec.size()-1);
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
					for (int i = 0; i < m_ElementRec.size(); i++)
					{
						size += m_ElementRec.get(i).getSize();
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
					
					short size = (short) m_ElementRec.size();
					bytes.put(pos, (byte) size);
					pos += JausUtils.getNumBytes("byte");
					for (int i = 0; i < m_ElementRec.size(); i++)
					{
						m_ElementRec.get(i).encode(bytes, pos);
						pos += m_ElementRec.get(i).getSize();
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
					m_ElementRec = new ArrayList<ElementRec>();
					for (int i = 0; i <  size; i++)
					{
						ElementRec item = new ElementRec();
						item.decode(bytes, pos);
						m_ElementRec.add(item);
						pos += item.getSize();
					}
				}
				
				public SetElement.Body.SetElementSeq.ElementList assign(ElementList value)
				{
					m_ElementRec.clear();
					
					for (int i = 0; i < value.m_ElementRec.size(); i++)
					{
						m_ElementRec.add(value.m_ElementRec.get(i));
						m_ElementRec.get(i).setParent(this);
					}
					
					return this;
				}
				
				public boolean isEqual(ElementList value)
				{
					for (int i = 0; i < m_ElementRec.size(); i++)
					{
						if (!m_ElementRec.get(i).isEqual(value.getElement(i)))
						{
							return false;
						}
					}
					
					return true;
				}
				
				public boolean notEquals(ElementList value)
				{
					return !this.isEqual(value);
				}
				
				public ElementList()
				{
					m_parent = null;
					m_ElementRec = new ArrayList<ElementRec>();
					for (int i = 0; i < m_ElementRec.size(); i++)
					{
						m_ElementRec.get(i).setParent(this);
					}
				}
				
				public ElementList(ElementList value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_ElementRec = new ArrayList<ElementRec>();
					for (int i = 0; i < m_ElementRec.size(); i++)
					{
						m_ElementRec.get(i).setParent(this);
					}
					
					/// Copy the values
					m_ElementRec.clear();
					
					for (int i = 0; i < value.m_ElementRec.size(); i++)
					{
						m_ElementRec.add(value.m_ElementRec.get(i));
						m_ElementRec.get(i).setParent(this);
					}
				}
				
				public void finalize()
				{
				}
				
			}
		
		
			protected Body m_parent;
			protected RequestIDRec m_RequestIDRec;
			protected ElementList m_ElementList;
		
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
			
			public SetElement.Body.SetElementSeq.RequestIDRec getRequestIDRec()
			{
				return m_RequestIDRec;
			}
			
			public void setRequestIDRec(RequestIDRec value)
			{
				m_RequestIDRec = value;
				setParentPresenceVector();
			}
			
			public SetElement.Body.SetElementSeq.ElementList getElementList()
			{
				return m_ElementList;
			}
			
			public void setElementList(ElementList value)
			{
				m_ElementList = value;
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
				
				size += m_RequestIDRec.getSize();
				size += m_ElementList.getSize();
				
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
				
				m_RequestIDRec.encode(bytes, pos);
				pos += m_RequestIDRec.getSize();
				m_ElementList.encode(bytes, pos);
				pos += m_ElementList.getSize();
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
				
				m_RequestIDRec.decode(bytes, pos);
				pos += m_RequestIDRec.getSize();
				m_ElementList.decode(bytes, pos);
				pos += m_ElementList.getSize();
			}
			
			public SetElement.Body.SetElementSeq assign(SetElementSeq value)
			{
				m_RequestIDRec = value.m_RequestIDRec;
				m_RequestIDRec.setParent(this);
				m_RequestIDRec = value.m_RequestIDRec;
				m_ElementList = value.m_ElementList;
				m_ElementList.setParent(this);
				m_ElementList = value.m_ElementList;
				
				return this;
			}
			
			public boolean isEqual(SetElementSeq value)
			{
				if (!m_RequestIDRec.isEqual(value.getRequestIDRec()))
				{
					return false;
				}
				
				if (!m_RequestIDRec.isEqual(value.getRequestIDRec()))
				{
					return false;
				}
				if (!m_ElementList.isEqual(value.m_ElementList))
				{
					return false;
				}
				
				if (!m_ElementList.isEqual(value.getElementList()))
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(SetElementSeq value)
			{
				return !this.isEqual(value);
			}
			
			public SetElementSeq()
			{
				m_parent = null;
				m_RequestIDRec = new RequestIDRec();
				m_RequestIDRec.setParent(this);
				m_ElementList = new ElementList();
				m_ElementList.setParent(this);
			}
			
			public SetElementSeq(SetElementSeq value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_RequestIDRec = new RequestIDRec();
				m_RequestIDRec.setParent(this);
				m_ElementList = new ElementList();
				m_ElementList.setParent(this);
				
				/// Copy the values
				m_RequestIDRec = value.m_RequestIDRec;
				m_RequestIDRec.setParent(this);
				m_RequestIDRec = value.m_RequestIDRec;
				m_ElementList = value.m_ElementList;
				m_ElementList.setParent(this);
				m_ElementList = value.m_ElementList;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected SetElementSeq m_SetElementSeq;
	
		public SetElement.Body.SetElementSeq getSetElementSeq()
		{
			return m_SetElementSeq;
		}
		
		public int setSetElementSeq(SetElementSeq value)
		{
			m_SetElementSeq = value;
			setParentPresenceVector();
			return 0;
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
			
			size += m_SetElementSeq.getSize();
			
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
			
			m_SetElementSeq.encode(bytes, pos);
			pos += m_SetElementSeq.getSize();
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
			
			m_SetElementSeq.decode(bytes, pos);
			pos += m_SetElementSeq.getSize();
		}
		
		public SetElement.Body assign(Body value)
		{
			m_SetElementSeq = value.m_SetElementSeq;
			m_SetElementSeq.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(Body value)
		{
			if (!m_SetElementSeq.isEqual(value.getSetElementSeq()))
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
			m_SetElementSeq = new SetElementSeq();
			m_SetElementSeq.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_SetElementSeq = new SetElementSeq();
			m_SetElementSeq.setParent(this);
			
			/// Copy the values
			m_SetElementSeq = value.m_SetElementSeq;
			m_SetElementSeq.setParent(this);
			/// This code is currently not supported
		}
		
		public void finalize()
		{
		}
		
	}
	protected AppHeader m_AppHeader;
	protected Body m_Body;
	public long getID()
	{
	return ID;
 }
	public SetElement.AppHeader getAppHeader()
	{
		return m_AppHeader;
	}
	
	public void setAppHeader(AppHeader value)
	{
		m_AppHeader = value;
	}
	
	public SetElement.Body getBody()
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
		
		size += m_AppHeader.getSize();
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
		
		m_AppHeader.encode(bytes, pos);
		pos += m_AppHeader.getSize();
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
		
		m_AppHeader.decode(bytes, pos);
		pos += m_AppHeader.getSize();
		m_Body.decode(bytes, pos);
		pos += m_Body.getSize();
	}
	
	public SetElement setAs(SetElement value)
	{
		m_AppHeader = value.m_AppHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(SetElement value)
	{
		if (!m_AppHeader.isEqual(value.getAppHeader()))
		{
			return false;
		}
		if (!m_Body.isEqual(value.getBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public boolean notEquals(SetElement value)
	{
		return !isEqual(value);
	}
	
	public SetElement()
	{
		m_AppHeader = new AppHeader();
		m_Body = new Body();
		m_IsCommand = true;
	}
	
	public  SetElement(SetElement value)
	{
		/// Initiliaze the protected variables
		m_AppHeader = new AppHeader();
		m_Body = new Body();
		m_IsCommand = true;
		
		/// Copy the values
		m_AppHeader = value.m_AppHeader;
		m_Body = value.m_Body;
	}
	
}
