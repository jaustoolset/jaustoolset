package src.urn_jaus_jss_core_Management_1_1.Messages;

import framework.messages.Message;
import framework.JausUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Event extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x41f1;
	
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
			
			public Event.MsgHeader.HeaderRec assign(HeaderRec value)
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
				m_MessageID = 0x41f1;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x41f1;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
		public Event.MsgHeader.HeaderRec getHeaderRec()
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
		
		public Event.MsgHeader assign(MsgHeader value)
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
		public static class  EventRec
		{
			public static class  ReportMessage
			{
			
				protected EventRec m_parent;
				protected long m_Length;
				protected ByteBuffer m_Data;
			
				public void setParent(EventRec parent)
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
				
				public Event.Body.EventRec.ReportMessage assign(ReportMessage value)
				{
					this.m_Length = value.m_Length;
					
					m_Data.clear();
					
					if (m_Length > 0)
					{
						this.m_Data.put(value.m_Data);
					}
					
					return this;
				}
				
				public boolean isEqual(ReportMessage value)
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
				
				public boolean notEquals(ReportMessage value)
				{
					return !this.isEqual(value);
				}
				
				public ReportMessage()
				{
					m_parent = null;
					m_Length = MAX_JTS_MESSAGE_SIZE;
					m_Data = ByteBuffer.allocate((int)m_Length);
					m_Data.order(ByteOrder.LITTLE_ENDIAN); 
				}
				
				public ReportMessage(ReportMessage value)
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
			protected short m_EventID;
			protected short m_SequenceNumber;
			protected ReportMessage m_ReportMessage;
		
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
			
			public short getEventID()
			{
				return m_EventID;
			}
			
			public void setEventID(short value)
			{
				m_EventID = value;
				setParentPresenceVector();
			}
			
			public short getSequenceNumber()
			{
				return m_SequenceNumber;
			}
			
			public void setSequenceNumber(short value)
			{
				m_SequenceNumber = value;
				setParentPresenceVector();
			}
			
			public Event.Body.EventRec.ReportMessage getReportMessage()
			{
				return m_ReportMessage;
			}
			
			public void setReportMessage(ReportMessage value)
			{
				m_ReportMessage = value;
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
				size += m_ReportMessage.getSize();
				
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
				
				bytes.put(pos, (byte) m_EventID);
				pos += JausUtils.getNumBytes("byte");
				bytes.put(pos, (byte) m_SequenceNumber);
				pos += JausUtils.getNumBytes("byte");
				m_ReportMessage.encode(bytes, pos);
				pos += m_ReportMessage.getSize();
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
				
				m_EventID = (short) (bytes.get(pos) & 0xff);
				pos += JausUtils.getNumBytes("byte");
				m_SequenceNumber = (short) (bytes.get(pos) & 0xff);
				pos += JausUtils.getNumBytes("byte");
				m_ReportMessage.decode(bytes, pos);
				pos += m_ReportMessage.getSize();
			}
			
			public Event.Body.EventRec assign(EventRec value)
			{
				m_EventID = value.m_EventID;
				m_SequenceNumber = value.m_SequenceNumber;
				m_ReportMessage = value.m_ReportMessage;
				
				return this;
			}
			
			public boolean isEqual(EventRec value)
			{
				if (m_EventID != value.getEventID())
				{
					return false;
				}
				if (m_SequenceNumber != value.getSequenceNumber())
				{
					return false;
				}
				
				if (!m_ReportMessage.isEqual(value.getReportMessage()))
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(EventRec value)
			{
				return !this.isEqual(value);
				}
				
			public EventRec()
			{
				m_parent = null;
				m_EventID = 0;
				m_SequenceNumber = 0;
					m_ReportMessage = new ReportMessage();
				m_ReportMessage.setParent(this);
			}
			
			public EventRec(EventRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_EventID = 0;
				m_SequenceNumber = 0;
					m_ReportMessage = new ReportMessage();
				m_ReportMessage.setParent(this);
				
				/// Copy the values
				m_EventID = value.m_EventID;
				m_SequenceNumber = value.m_SequenceNumber;
				m_ReportMessage = value.m_ReportMessage;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected EventRec m_EventRec;
	
		public Event.Body.EventRec getEventRec()
		{
			return m_EventRec;
		}
		
		public void setEventRec(EventRec value)
		{
			m_EventRec = value;
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
			
			size += m_EventRec.getSize();
			
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
			
			m_EventRec.encode(bytes, pos);
			pos += m_EventRec.getSize();
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
			
			m_EventRec.decode(bytes, pos);
			pos += m_EventRec.getSize();
		}
		
		public Event.Body assign(Body value)
		{
			m_EventRec = value.m_EventRec;
			m_EventRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(Body value)
		{
			if (!m_EventRec.isEqual(value.getEventRec()))
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
			m_EventRec = new EventRec();
			m_EventRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_EventRec = new EventRec();
			m_EventRec.setParent(this);
			
			/// Copy the values
			m_EventRec = value.m_EventRec;
			m_EventRec.setParent(this);
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
	public Event.MsgHeader getMsgHeader()
	{
		return m_MsgHeader;
	}
	
	public void setMsgHeader(MsgHeader value)
	{
		m_MsgHeader = value;
	}
	
	public Event.Body getBody()
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
	
	public Event setAs(Event value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(Event value)
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
	
	public boolean notEquals(Event value)
	{
		return !isEqual(value);
		}
		
	public Event()
	{
		m_MsgHeader = new MsgHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  Event(Event value)
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
