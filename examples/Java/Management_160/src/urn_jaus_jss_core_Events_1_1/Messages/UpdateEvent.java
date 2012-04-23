package src.urn_jaus_jss_core_Events_1_1.Messages;

import framework.messages.Message;
import framework.JausUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateEvent extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x01f1;
	
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
			
			public UpdateEvent.MsgHeader.HeaderRec assign(HeaderRec value)
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
				m_MessageID = 0x01f1;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x01f1;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
		public UpdateEvent.MsgHeader.HeaderRec getHeaderRec()
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
		
		public UpdateEvent.MsgHeader assign(MsgHeader value)
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
		public static class  UpdateEventRec
		{
			public static class  QueryMessage
			{
			
				protected UpdateEventRec m_parent;
				protected long m_Length;
				protected ByteBuffer m_Data;
			
				public void setParent(UpdateEventRec parent)
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
				
				public UpdateEvent.Body.UpdateEventRec.QueryMessage assign(QueryMessage value)
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
		
		
			protected Body m_parent;
			protected short m_RequestID;
			protected short m_EventType;
			protected int m_RequestedPeriodicRate;
			protected short m_EventID;
			protected QueryMessage m_QueryMessage;
		
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
			
			public short getRequestID()
			{
				return m_RequestID;
			}
			
			public void setRequestID(short value)
			{
				m_RequestID = value;
				setParentPresenceVector();
			}
			
			public short getEventType()
			{
				return m_EventType;
			}
			
			public void setEventType(short value)
			{
				if ((value == 0) || (value == 1))
				{
					m_EventType = value;
					setParentPresenceVector();
				}
				return;
			}
			
			public double getRequestedPeriodicRate()
			{
				double value;
				
				double scaleFactor = ( 1092 - 0 ) / 65535.0;
				double bias = 0;
				
				value = m_RequestedPeriodicRate * scaleFactor + bias;
				
				return value;
			}
			
			public void setRequestedPeriodicRate(double value)
			{
				if ((value >= 0) && (value <= 1092))
				{
					double scaleFactor = ( 1092 - 0 ) / 65535.0;
					double bias = 0;
					
					m_RequestedPeriodicRate= (int)((value - bias) / scaleFactor);
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
			
			public UpdateEvent.Body.UpdateEventRec.QueryMessage getQueryMessage()
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
				size += JausUtils.getNumBytes("short");
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
				
				bytes.put(pos, (byte) m_RequestID);
				pos += JausUtils.getNumBytes("byte");
				bytes.put(pos, (byte) m_EventType);
				pos += JausUtils.getNumBytes("byte");
				bytes.putShort(pos, (short) m_RequestedPeriodicRate);
				pos += JausUtils.getNumBytes("short");
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
				
				m_RequestID = (short) (bytes.get(pos) & 0xff);
				pos += JausUtils.getNumBytes("byte");
				m_EventType = (short) (bytes.get(pos) & 0xff);
				pos += JausUtils.getNumBytes("byte");
				m_RequestedPeriodicRate = bytes.getShort(pos) & 0xffff;
				pos += JausUtils.getNumBytes("short");
				m_EventID = (short) (bytes.get(pos) & 0xff);
				pos += JausUtils.getNumBytes("byte");
				m_QueryMessage.decode(bytes, pos);
				pos += m_QueryMessage.getSize();
			}
			
			public UpdateEvent.Body.UpdateEventRec assign(UpdateEventRec value)
			{
				m_RequestID = value.m_RequestID;
				m_EventType = value.m_EventType;
				m_RequestedPeriodicRate = value.m_RequestedPeriodicRate;
				m_EventID = value.m_EventID;
				m_QueryMessage = value.m_QueryMessage;
				
				return this;
			}
			
			public boolean isEqual(UpdateEventRec value)
			{
				if (m_RequestID != value.getRequestID())
				{
					return false;
				}
				if (m_EventType != value.getEventType())
				{
					return false;
				}
				if (m_RequestedPeriodicRate != value.getRequestedPeriodicRate())
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
			
			public boolean notEquals(UpdateEventRec value)
			{
				return !this.isEqual(value);
			}
			
			public UpdateEventRec()
			{
				m_parent = null;
				m_RequestID = 0;
				m_EventType = 0;
				m_RequestedPeriodicRate = 0;
				m_EventID = 0;
					m_QueryMessage = new QueryMessage();
				m_QueryMessage.setParent(this);
			}
			
			public UpdateEventRec(UpdateEventRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_RequestID = 0;
				m_EventType = 0;
				m_RequestedPeriodicRate = 0;
				m_EventID = 0;
					m_QueryMessage = new QueryMessage();
				m_QueryMessage.setParent(this);
				
				/// Copy the values
				m_RequestID = value.m_RequestID;
				m_EventType = value.m_EventType;
				m_RequestedPeriodicRate = value.m_RequestedPeriodicRate;
				m_EventID = value.m_EventID;
				m_QueryMessage = value.m_QueryMessage;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected UpdateEventRec m_UpdateEventRec;
	
		public UpdateEvent.Body.UpdateEventRec getUpdateEventRec()
		{
			return m_UpdateEventRec;
		}
		
		public void setUpdateEventRec(UpdateEventRec value)
		{
			m_UpdateEventRec = value;
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
			
			size += m_UpdateEventRec.getSize();
			
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
			
			m_UpdateEventRec.encode(bytes, pos);
			pos += m_UpdateEventRec.getSize();
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
			
			m_UpdateEventRec.decode(bytes, pos);
			pos += m_UpdateEventRec.getSize();
		}
		
		public UpdateEvent.Body assign(Body value)
		{
			m_UpdateEventRec = value.m_UpdateEventRec;
			m_UpdateEventRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(Body value)
		{
			if (!m_UpdateEventRec.isEqual(value.getUpdateEventRec()))
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
			m_UpdateEventRec = new UpdateEventRec();
			m_UpdateEventRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_UpdateEventRec = new UpdateEventRec();
			m_UpdateEventRec.setParent(this);
			
			/// Copy the values
			m_UpdateEventRec = value.m_UpdateEventRec;
			m_UpdateEventRec.setParent(this);
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
	public UpdateEvent.MsgHeader getMsgHeader()
	{
		return m_MsgHeader;
	}
	
	public void setMsgHeader(MsgHeader value)
	{
		m_MsgHeader = value;
	}
	
	public UpdateEvent.Body getBody()
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
	
	public UpdateEvent setAs(UpdateEvent value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(UpdateEvent value)
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
	
	public boolean notEquals(UpdateEvent value)
	{
		return !isEqual(value);
	}
	
	public UpdateEvent()
	{
		m_MsgHeader = new MsgHeader();
		m_Body = new Body();
		m_IsCommand = true;
	}
	
	public  UpdateEvent(UpdateEvent value)
	{
		/// Initiliaze the protected variables
		m_MsgHeader = new MsgHeader();
		m_Body = new Body();
		m_IsCommand = true;
		
		/// Copy the values
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
	}
	
}
