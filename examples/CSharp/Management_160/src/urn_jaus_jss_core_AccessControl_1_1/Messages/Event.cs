using JTS;
using System;
using System.Collections;
using System.Linq;
using System.Collections;
using System.Runtime.InteropServices;
using System.Collections.Generic;

namespace urn_jaus_jss_core_AccessControl_1_1
{

public class Event : JTS.Message
{
	protected ushort ID = 0x41f1;
	
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
			
			public Event.MsgHeader.HeaderRec  setHeaderRec(HeaderRec value)
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
			
			 ~HeaderRec()
			{
			}
			
		
			MsgHeader m_parent;
			protected ushort m_MessageID;
		}
	
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
		
		public Event.MsgHeader  setMsgHeader(MsgHeader value)
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
		public class EventRec
		{
			public class ReportMessage
			{
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
				
				public uint getLength()
				{
					return m_Length;
				}
				
				public byte[]  getData()
				{
					return m_Data;
				}
				
				public void set(uint length, byte[] data)
				{
					m_Length = length;
					
					m_Data = null;
					
					if (m_Length > 0)
					{
						m_Data = new byte[length];
						for(int i = 0; i< m_Length; i++)
							m_Data[i] = data[i];
					}
					
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
					
					size += JausUtils.getNumBytes("uint");
					size += (int)m_Length;
					
					return size;
				}
				
				public void encode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((uint)m_Length), pos, (int)JausUtils.UINT_BYTES, false);
					pos += JausUtils.UINT_BYTES;
					
					bytes = JausUtils.addToBuffer(bytes, m_Data, pos, (int)m_Length, true);
					
					pos += (int)m_Length;
				}
				
				public void decode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					
					m_Length = BitConverter.ToUInt32(JausUtils.getFromBuffer(bytes, pos, JausUtils.UINT_BYTES, false), 0);
					pos += JausUtils.UINT_BYTES;
					
					m_Data = null;
					
					if (m_Length > 0)
					{
						m_Data = new byte[(int)m_Length];
						m_Data = JausUtils.getFromBuffer(bytes, pos, (int)m_Length, true);
						pos += (int)m_Length;
					}
				}
				
				public Event.Body.EventRec.ReportMessage  setReportMessage(ReportMessage value)
				{
					this.m_Length = value.m_Length;
					
					m_Data = null;
					
					if (m_Length > 0)
					{
						m_Data = new byte[(int)m_Length];
						this.m_Data = value.m_Data;
					}
					
					return this;
				}
				
				public bool isEqual(ReportMessage value)
				{
					if (this.m_Length != value.m_Length)
					{
						return false;
					}
					
					if ((this.m_Data != null) && (value.m_Data!= null))
					{
						for(int i = 0; i < value.m_Length; i++)
						{
							if (this.m_Data[i] != value.m_Data[i])
							{
								return false;
							}
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
				
				public bool notEquals(ReportMessage value)
				{
					return !this.isEqual(value);
				}
				
				public ReportMessage()
				{
					m_parent = null;
					m_Length = 0;
					m_Data = null;
				}
				
				public ReportMessage(ReportMessage value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_Length = 0;
					m_Data = null;
					
					/// Copy the values
					this.m_Length = value.m_Length;
					
					m_Data = null;
					
					if (m_Length > 0)
					{
						m_Data = new byte[(int)m_Length];
						this.m_Data = value.m_Data;
					}
				}
				
				 ~ReportMessage()
				{
				}
				
			
				EventRec m_parent;
				uint m_Length;
				byte[] m_Data;
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
			
			public byte getEventID()
			{
				return m_EventID;
			}
			
			public void setEventID(byte value)
			{
				m_EventID = value;
				setParentPresenceVector();
			}
			
			public byte getSequenceNumber()
			{
				return m_SequenceNumber;
			}
			
			public void setSequenceNumber(byte value)
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
			 * C Sharp, but the bytes expected on the wire.
			 * 
			 * @return
			 */
			public int getSize()
			{
				int size = 0;
				
				size += JausUtils.getNumBytes("byte");
				size += JausUtils.getNumBytes("byte");
				size += m_ReportMessage.getSize();
				
				return size;
			}
			
			public void encode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				bytes[pos] = (byte)m_EventID;
				pos += JausUtils.BYTE_BYTES;
				bytes[pos] = (byte)m_SequenceNumber;
				pos += JausUtils.BYTE_BYTES;
				m_ReportMessage.encode(bytes, pos);
				pos += m_ReportMessage.getSize();
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_EventID = bytes[pos];
				pos += JausUtils.BYTE_BYTES;
				m_SequenceNumber = bytes[pos];
				pos += JausUtils.BYTE_BYTES;
				m_ReportMessage.decode(bytes, pos);
				pos += m_ReportMessage.getSize();
			}
			
			public Event.Body.EventRec  setEventRec(EventRec value)
			{
				m_EventID = value.m_EventID;
				m_SequenceNumber = value.m_SequenceNumber;
				m_ReportMessage = value.getReportMessage();
				
				return this;
			}
			
			public bool isEqual(EventRec value)
			{
				if (this.getEventID() != value.getEventID())
				{
					return false;
				}
				if (this.getSequenceNumber() != value.getSequenceNumber())
				{
					return false;
				}
				
				if (!this.getReportMessage().isEqual(value.getReportMessage()))
				{
					return false;
				}
				
				return true;
			}
			
			public bool notEquals(EventRec value)
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
				m_ReportMessage = value.getReportMessage();
			}
			
			 ~EventRec()
			{
			}
			
		
			Body m_parent;
			protected byte m_EventID;
			protected byte m_SequenceNumber;
			protected ReportMessage m_ReportMessage = new  ReportMessage();
		}
	
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
		 * C Sharp, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public int getSize()
		{
			int size = 0;
			
			size += m_EventRec.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_EventRec.encode(bytes, pos);
			pos += m_EventRec.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_EventRec.decode(bytes, pos);
			pos += m_EventRec.getSize();
		}
		
		public Event.Body  setBody(Body value)
		{
			m_EventRec = value.getEventRec();
			m_EventRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public bool isEqual(Body value)
		{
			if (!this.getEventRec().isEqual(value.getEventRec()))
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
			m_EventRec = new EventRec();
			m_EventRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_EventRec = new EventRec();
			m_EventRec.setParent(this);
			
			/// Copy the values
			m_EventRec = value.getEventRec();
			m_EventRec.setParent(this);
			/// This code is currently not supported
		}
		
		 ~Body()
		{
		}
		
	
		protected EventRec m_EventRec = new  EventRec();
	}
	protected MsgHeader m_MsgHeader = new  MsgHeader();
	protected Body m_Body = new  Body();
	public override ushort getID()
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
	
	public Event setAs(Event value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public bool  isEqual(Event value)
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
	
	public bool  notEquals(Event value)
	{
		return !this.isEqual(value);
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

}
