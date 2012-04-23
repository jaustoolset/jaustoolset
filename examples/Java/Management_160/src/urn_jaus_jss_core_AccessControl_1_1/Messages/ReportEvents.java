package src.urn_jaus_jss_core_AccessControl_1_1.Messages;

import framework.messages.Message;
import framework.JausUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportEvents extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x41f0;
	
	public static class  MsgHeader
	{
		public static class  HeaderRec
		{
		
			protected MsgHeader m_parent;
			protected int m_MessageID;
		
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
			
			public ReportEvents.MsgHeader.HeaderRec assign(HeaderRec value)
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
				m_MessageID = 0x41f0;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x41f0;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
		public ReportEvents.MsgHeader.HeaderRec getHeaderRec()
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
		
		public ReportEvents.MsgHeader assign(MsgHeader value)
		{
			m_HeaderRec = value.m_HeaderRec;
			m_HeaderRec.setParent(this);
			
			return this;
		}
		
		public boolean isEqual(MsgHeader value)
		{
			if (!m_HeaderRec.isEqual(value.getHeaderRec()))
			{
				return false;
			}
			return true;
		}
		
		public boolean notEquals(MsgHeader value)
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
			m_HeaderRec = value.m_HeaderRec;
			m_HeaderRec.setParent(this);
		}
		
		public void finalize()
		{
		}
		
	}
	public static class  Body
	{
		public static class  EventList
		{
			public static class  ReportEventRec
			{
				public static class  QueryMessage
				{
				
					protected ReportEventRec m_parent;
					protected long m_Length;
					protected ByteBuffer m_Data;
				
					public void setParent(ReportEventRec parent)
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
					
					public long getLength()
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
					
					public void set(long length, ByteBuffer data)
					{
						m_Length = length;
						
						m_Data.clear();
						
						if (m_Length > 0)
						{
							data.rewind();
							m_Data = ByteBuffer.allocate(data.array().length);
							m_Data.order(ByteOrder.LITTLE_ENDIAN);
							m_Data.put(data);
						}
						
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
						
						size += JausUtils.getNumBytes("int");
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
						
						
						bytes.putInt(pos, (int) m_Length);
						pos += JausUtils.getNumBytes("int");
						
						byte[] byteArray = m_Data.array();
						
						for(int i = 0; i<(int) m_Length; i++)
						{
							bytes.put(pos, byteArray[i]);
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
						
						
						m_Length = bytes.getInt(pos) & 0xffffffffL;
						pos += JausUtils.getNumBytes("int");
						byte[] byteArray = new byte[(int)m_Length];
						
						int i;
						for(i=0; i<(int) m_Length; i++)
						{
							byteArray[i] = bytes.get(pos);
							pos++;
						}
						
						m_Data = ByteBuffer.allocate((int)m_Length).order(ByteOrder.LITTLE_ENDIAN);
						m_Data.put(byteArray);
					}
					
					public ReportEvents.Body.EventList.ReportEventRec.QueryMessage assign(QueryMessage value)
					{
						this.m_Length = value.m_Length;
						
						m_Data.clear();
						
						if (m_Length > 0)
						{
							this.m_Data.put(value.m_Data);
						}
						
						return this;
					}
					
					public boolean isEqual(QueryMessage value)
					{
						if (this.m_Length != value.getLength())
						{
							return false;
						}
						
						if ((this.m_Data != null) && (value.m_Data!= null))
						{
							if(!Arrays.equals(this.m_Data.array(), value.m_Data.array()))
							{
								return false;
							}
						}
						// This case should never be true since it should not be possible
						// for the two variables to have equal lengths but one has no data.
						// This check is placed here as a secondary check.
						else if ((this.m_Data != null) || (value.m_Data != null))
						{
							return false;
						}
						
						return true;
					}
					
					public boolean notEquals(QueryMessage value)
					{
						return !this.isEqual(value);
					}
					
					public QueryMessage()
					{
						m_parent = null;
						m_Length = MAX_JTS_MESSAGE_SIZE;
						m_Data = ByteBuffer.allocate((int)m_Length);
						m_Data.order(ByteOrder.LITTLE_ENDIAN); 
					}
					
					public QueryMessage(QueryMessage value)
					{
						/// Initiliaze the protected variables
						m_parent = null;
						m_Length = MAX_JTS_MESSAGE_SIZE;
						m_Data = ByteBuffer.allocate((int)m_Length);
						m_Data.order(ByteOrder.LITTLE_ENDIAN); 
						
						/// Copy the values
						this.m_Length = value.m_Length;
						
						m_Data.clear();
						
						if (m_Length > 0)
						{
							this.m_Data.put(value.m_Data);
						}
					}
					
					public void finalize()
					{
					}
					
				}
			
			
				protected EventList m_parent;
				protected short m_EventType;
				protected short m_EventID;
				protected QueryMessage m_QueryMessage;
			
				public void setParent(EventList parent)
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
				
				public short getEventType()
				{
					return m_EventType;
				}
				
				public void setEventType(short value)
				{
					if ((value == 1) || (value == 0))
					{
						m_EventType = value;
						setParentPresenceVector();
					}
					return;
				}
				
				public short getEventID()
				{
					return m_EventID;
				}
				
				public void setEventID(short value)
				{
					m_EventID = value;
					setParentPresenceVector();
				}
				
				public ReportEvents.Body.EventList.ReportEventRec.QueryMessage getQueryMessage()
				{
					return m_QueryMessage;
				}
				
				public void setQueryMessage(QueryMessage value)
				{
					m_QueryMessage = value;
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
					size += m_QueryMessage.getSize();
					
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
					
					bytes.put(pos, (byte) m_EventType);
					pos += JausUtils.getNumBytes("byte");
					bytes.put(pos, (byte) m_EventID);
					pos += JausUtils.getNumBytes("byte");
					m_QueryMessage.encode(bytes, pos);
					pos += m_QueryMessage.getSize();
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
					
					m_EventType = (short) (bytes.get(pos) & 0xff);
					pos += JausUtils.getNumBytes("byte");
					m_EventID = (short) (bytes.get(pos) & 0xff);
					pos += JausUtils.getNumBytes("byte");
					m_QueryMessage.decode(bytes, pos);
					pos += m_QueryMessage.getSize();
				}
				
				public ReportEvents.Body.EventList.ReportEventRec assign(ReportEventRec value)
				{
					m_EventType = value.m_EventType;
					m_EventID = value.m_EventID;
					m_QueryMessage = value.m_QueryMessage;
					
					return this;
				}
				
				public boolean isEqual(ReportEventRec value)
				{
					if (m_EventType != value.getEventType())
					{
						return false;
					}
					if (m_EventID != value.getEventID())
					{
						return false;
					}
					
					if (!m_QueryMessage.isEqual(value.getQueryMessage()))
					{
						return false;
					}
					
					return true;
				}
				
				public boolean notEquals(ReportEventRec value)
				{
					return !this.isEqual(value);
				}
				
				public ReportEventRec()
				{
					m_parent = null;
					m_EventType = 0;
					m_EventID = 0;
						m_QueryMessage = new QueryMessage();
					m_QueryMessage.setParent(this);
				}
				
				public ReportEventRec(ReportEventRec value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_EventType = 0;
					m_EventID = 0;
						m_QueryMessage = new QueryMessage();
					m_QueryMessage.setParent(this);
					
					/// Copy the values
					m_EventType = value.m_EventType;
					m_EventID = value.m_EventID;
					m_QueryMessage = value.m_QueryMessage;
				}
				
				public void finalize()
				{
				}
				
			}
		
		
			protected Body m_parent;
			protected ArrayList<ReportEventRec> m_ReportEventRec;
		
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
			
			public long getNumberOfElements()
			{
				return (long) m_ReportEventRec.size();
			}
			
			public ReportEvents.Body.EventList.ReportEventRec getElement(int index)
			{
				return m_ReportEventRec.get(index);
			}
			
			public void setElement(int index, ReportEventRec value)
			{
				if(m_ReportEventRec.size()-1 < index)
				{
					return;
				}
				
				m_ReportEventRec.set(index, value);
				m_ReportEventRec.get(index).setParent(this);
				setParentPresenceVector();
			}
			
			public void addElement(ReportEventRec value)
			{
				m_ReportEventRec.add(value);
				m_ReportEventRec.get(m_ReportEventRec.size() -1 ).setParent(this);
				setParentPresenceVector();
			}
			
			public int deleteElement(int index)
			{
				if(m_ReportEventRec.size()-1 < index)
				{
					return 1;
				}
				
				m_ReportEventRec.remove(index);
				return 0;
			}
			
			public int deleteLastElement()
			{
				m_ReportEventRec.remove(m_ReportEventRec.size()-1);
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
				for (int i = 0; i < m_ReportEventRec.size(); i++)
				{
					size += m_ReportEventRec.get(i).getSize();
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
				
				short size = (short) m_ReportEventRec.size();
				bytes.put(pos, (byte) size);
				pos += JausUtils.getNumBytes("byte");
				for (int i = 0; i < m_ReportEventRec.size(); i++)
				{
					m_ReportEventRec.get(i).encode(bytes, pos);
					pos += m_ReportEventRec.get(i).getSize();
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
				m_ReportEventRec = new ArrayList<ReportEventRec>();
				for (int i = 0; i <  size; i++)
				{
					ReportEventRec item = new ReportEventRec();
					item.decode(bytes, pos);
					m_ReportEventRec.add(item);
					pos += item.getSize();
				}
			}
			
			public ReportEvents.Body.EventList assign(EventList value)
			{
				m_ReportEventRec.clear();
				
				for (int i = 0; i < value.m_ReportEventRec.size(); i++)
				{
					m_ReportEventRec.add(value.m_ReportEventRec.get(i));
					m_ReportEventRec.get(i).setParent(this);
				}
				
				return this;
			}
			
			public boolean isEqual(EventList value)
			{
				for (int i = 0; i < m_ReportEventRec.size(); i++)
				{
					if (!m_ReportEventRec.get(i).isEqual(value.getElement(i)))
					{
						return false;
					}
				}
				
				return true;
			}
			
			public boolean notEquals(EventList value)
			{
				return !this.isEqual(value);
			}
			
			public EventList()
			{
				m_parent = null;
				m_ReportEventRec = new ArrayList<ReportEventRec>();
				for (int i = 0; i < m_ReportEventRec.size(); i++)
				{
					m_ReportEventRec.get(i).setParent(this);
				}
			}
			
			public EventList(EventList value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_ReportEventRec = new ArrayList<ReportEventRec>();
				for (int i = 0; i < m_ReportEventRec.size(); i++)
				{
					m_ReportEventRec.get(i).setParent(this);
				}
				
				/// Copy the values
				m_ReportEventRec.clear();
				
				for (int i = 0; i < value.m_ReportEventRec.size(); i++)
				{
					m_ReportEventRec.add(value.m_ReportEventRec.get(i));
					m_ReportEventRec.get(i).setParent(this);
				}
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected EventList m_EventList;
	
		public ReportEvents.Body.EventList getEventList()
		{
			return m_EventList;
		}
		
		public void setEventList(EventList value)
		{
			m_EventList = value;
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
			
			size += m_EventList.getSize();
			
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
			
			m_EventList.encode(bytes, pos);
			pos += m_EventList.getSize();
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
			
			m_EventList.decode(bytes, pos);
			pos += m_EventList.getSize();
		}
		
		public ReportEvents.Body assign(Body value)
		{
			m_EventList = value.m_EventList;
			m_EventList.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(Body value)
		{
			if (!m_EventList.isEqual(value.m_EventList))
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
			m_EventList = new EventList();
			m_EventList.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_EventList = new EventList();
			m_EventList.setParent(this);
			
			/// Copy the values
			m_EventList = value.m_EventList;
			m_EventList.setParent(this);
			/// This code is currently not supported
		}
		
		public void finalize()
		{
		}
		
	}
	protected MsgHeader m_MsgHeader;
	protected Body m_Body;
	public long getID()
	{
	return ID;
 }
	public ReportEvents.MsgHeader getMsgHeader()
	{
		return m_MsgHeader;
	}
	
	public void setMsgHeader(MsgHeader value)
	{
		m_MsgHeader = value;
	}
	
	public ReportEvents.Body getBody()
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
		
		size += m_MsgHeader.getSize();
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
		
		m_MsgHeader.encode(bytes, pos);
		pos += m_MsgHeader.getSize();
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
		
		m_MsgHeader.decode(bytes, pos);
		pos += m_MsgHeader.getSize();
		m_Body.decode(bytes, pos);
		pos += m_Body.getSize();
	}
	
	public ReportEvents setAs(ReportEvents value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(ReportEvents value)
	{
		if (!m_MsgHeader.isEqual(value.getMsgHeader()))
		{
			return false;
		}
		if (!m_Body.isEqual(value.getBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public boolean notEquals(ReportEvents value)
	{
		return !isEqual(value);
	}
	
	public ReportEvents()
	{
		m_MsgHeader = new MsgHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  ReportEvents(ReportEvents value)
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
