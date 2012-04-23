using JTS;
using System;
using System.Collections;
using System.Linq;
using System.Collections;
using System.Runtime.InteropServices;
using System.Collections.Generic;

namespace urn_jaus_jss_core_Management_1_1
{

public class ReportEvents : JTS.Message
{
	protected ushort ID = 0x41f0;
	
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
			
			public ReportEvents.MsgHeader.HeaderRec  setHeaderRec(HeaderRec value)
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
			
			 ~HeaderRec()
			{
			}
			
		
			MsgHeader m_parent;
			protected ushort m_MessageID;
		}
	
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
		
		public ReportEvents.MsgHeader  setMsgHeader(MsgHeader value)
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
		public class EventList
		{
			public class ReportEventRec
			{
				public class QueryMessage
				{
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
					
					public ReportEvents.Body.EventList.ReportEventRec.QueryMessage  setQueryMessage(QueryMessage value)
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
					
					public bool isEqual(QueryMessage value)
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
					
					public bool notEquals(QueryMessage value)
					{
						return !this.isEqual(value);
					}
					
					public QueryMessage()
					{
						m_parent = null;
						m_Length = 0;
						m_Data = null;
					}
					
					public QueryMessage(QueryMessage value)
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
					
					 ~QueryMessage()
					{
					}
					
				
					ReportEventRec m_parent;
					uint m_Length;
					byte[] m_Data;
				}
			
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
				
				public byte getEventType()
				{
					return m_EventType;
				}
				
				public void setEventType(byte value)
				{
					if ((value == 1) || (value == 0))
					{
						m_EventType = value;
						setParentPresenceVector();
					}
					return;
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
				 * C Sharp, but the bytes expected on the wire.
				 * 
				 * @return
				 */
				public int getSize()
				{
					int size = 0;
					
					size += JausUtils.getNumBytes("byte");
					size += JausUtils.getNumBytes("byte");
					size += m_QueryMessage.getSize();
					
					return size;
				}
				
				public void encode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					bytes[pos] = (byte)m_EventType;
					pos += JausUtils.BYTE_BYTES;
					bytes[pos] = (byte)m_EventID;
					pos += JausUtils.BYTE_BYTES;
					m_QueryMessage.encode(bytes, pos);
					pos += m_QueryMessage.getSize();
				}
				
				public void decode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					m_EventType = bytes[pos];
					pos += JausUtils.BYTE_BYTES;
					m_EventID = bytes[pos];
					pos += JausUtils.BYTE_BYTES;
					m_QueryMessage.decode(bytes, pos);
					pos += m_QueryMessage.getSize();
				}
				
				public ReportEvents.Body.EventList.ReportEventRec  setReportEventRec(ReportEventRec value)
				{
					m_EventType = value.m_EventType;
					m_EventID = value.m_EventID;
					m_QueryMessage = value.getQueryMessage();
					
					return this;
				}
				
				public bool isEqual(ReportEventRec value)
				{
					if (this.getEventType() != value.getEventType())
					{
						return false;
					}
					if (this.getEventID() != value.getEventID())
					{
						return false;
					}
					
					if (!this.getQueryMessage().isEqual(value.getQueryMessage()))
					{
						return false;
					}
					
					return true;
				}
				
				public bool notEquals(ReportEventRec value)
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
					m_QueryMessage = value.getQueryMessage();
				}
				
				 ~ReportEventRec()
				{
				}
				
			
				EventList m_parent;
				protected byte m_EventType;
				protected byte m_EventID;
				protected QueryMessage m_QueryMessage = new  QueryMessage();
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
				return (int) m_ReportEventRec.Count;
			}
			
			public ReportEvents.Body.EventList.ReportEventRec getElement(int index)
			{
				return m_ReportEventRec[index];
			}
			
			public void setElement(int index, ReportEventRec value)
			{
				if(m_ReportEventRec.Count-1 < index)
				{
					return;
				}
				
				m_ReportEventRec[index] = value;
				m_ReportEventRec[index].setParent(this);
				setParentPresenceVector();
			}
			
			public void addElement(ReportEventRec value)
			{
				m_ReportEventRec.Add(value);
				m_ReportEventRec[m_ReportEventRec.Count-1].setParent(this);
				setParentPresenceVector();
			}
			
			public void deleteElement(int index)
			{
				if(m_ReportEventRec.Count-1 < index)
				{
					return;
				}
				
				m_ReportEventRec.RemoveAt(index);
			}
			
			public void deleteLastElement()
			{
				m_ReportEventRec.RemoveAt(m_ReportEventRec.Count-1);
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
				for (int i = 0; i < m_ReportEventRec.Count; i++)
				{
					size += m_ReportEventRec[i].getSize();
				}
				
				return size;
			}
			
			public void encode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				byte size = (byte) m_ReportEventRec.Count;
				bytes[pos] = (byte)size;
				pos += JausUtils.BYTE_BYTES;
				for (int i = 0; i < m_ReportEventRec.Count; i++)
				{
					m_ReportEventRec[i].encode(bytes, pos);
					pos += m_ReportEventRec[i].getSize();
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
				m_ReportEventRec = new List<ReportEventRec>();
				for (int i = 0; i < size; i++)
				{
					m_ReportEventRec.Add( new ReportEventRec());
					m_ReportEventRec[i].decode(bytes, pos);
					pos += m_ReportEventRec[i].getSize();
				}
			}
			
			public ReportEvents.Body.EventList  setEventList(EventList value)
			{
				m_ReportEventRec.Clear();
				
				for (int i = 0; i < value.m_ReportEventRec.Count; i++)
				{
					m_ReportEventRec.Add(value.m_ReportEventRec[i]);
					m_ReportEventRec[i].setParent(this);
				}
				
				return this;
			}
			
			public bool isEqual(EventList value)
			{
				for (int i = 0; i < value.m_ReportEventRec.Count; i++)
				{
					if (!this.m_ReportEventRec[i].isEqual(value.m_ReportEventRec[i]))
					{
						return false;
					}
				}
				
				return true;
			}
			
			public bool notEquals(EventList value)
			{
				return !this.isEqual(value);
				}
				
			public EventList()
			{
				m_parent = null;
				m_ReportEventRec = new List<ReportEventRec>();
				for (int i = 0; i < m_ReportEventRec.Count; i++)
				{
					m_ReportEventRec[i].setParent(this);
				}
			}
			
			public EventList(EventList value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_ReportEventRec = new List<ReportEventRec>();
				for (int i = 0; i < m_ReportEventRec.Count; i++)
				{
					m_ReportEventRec[i].setParent(this);
				}
				
				/// Copy the values
				m_ReportEventRec.Clear();
				
				for (int i = 0; i < value.m_ReportEventRec.Count; i++)
				{
					m_ReportEventRec.Add(value.m_ReportEventRec[i]);
					m_ReportEventRec[i].setParent(this);
				}
			}
			
			 ~EventList()
			{
			}
			
		
			Body m_parent;
			protected List<ReportEventRec> m_ReportEventRec = new  List<ReportEventRec>();
		}
	
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
		 * C Sharp, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public int getSize()
		{
			int size = 0;
			
			size += m_EventList.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_EventList.encode(bytes, pos);
			pos += m_EventList.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_EventList.decode(bytes, pos);
			pos += m_EventList.getSize();
		}
		
		public ReportEvents.Body  setBody(Body value)
		{
			m_EventList = value.m_EventList;
			m_EventList.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public bool isEqual(Body value)
		{
			if (!this.m_EventList.isEqual(value.m_EventList))
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
		
		 ~Body()
		{
		}
		
	
		protected EventList m_EventList = new  EventList();
	}
	protected MsgHeader m_MsgHeader = new  MsgHeader();
	protected Body m_Body = new  Body();
	public override ushort getID()
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
	
	public ReportEvents setAs(ReportEvents value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public bool  isEqual(ReportEvents value)
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
	
	public bool  notEquals(ReportEvents value)
	{
		return !this.isEqual(value);
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

}
