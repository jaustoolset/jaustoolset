using JTS;
using System;
using System.Collections;
using System.Linq;
using System.Collections;
using System.Runtime.InteropServices;
using System.Collections.Generic;

namespace urn_jaus_jss_core_Events_1_1
{

public class QueryEvents : JTS.Message
{
	protected ushort ID = 0x21f0;
	
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
			
			public QueryEvents.MsgHeader.HeaderRec  setHeaderRec(HeaderRec value)
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
				m_MessageID = 0x21f0;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x21f0;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			 ~HeaderRec()
			{
			}
			
		
			MsgHeader m_parent;
			protected ushort m_MessageID;
		}
	
		public QueryEvents.MsgHeader.HeaderRec getHeaderRec()
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
		
		public QueryEvents.MsgHeader  setMsgHeader(MsgHeader value)
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
		public class QueryEventsVar
		{
			public class MessageIDRec
			{
				public void setParent(QueryEventsVar parent)
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
				
				public ushort getMessageCode()
				{
					return m_MessageCode;
				}
				
				public void setMessageCode(ushort value)
				{
					m_MessageCode = value;
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
					
					bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((ushort)m_MessageCode), pos, (int)JausUtils.USHORT_BYTES, false);
					pos += JausUtils.USHORT_BYTES;
				}
				
				public void decode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					m_MessageCode = BitConverter.ToUInt16(JausUtils.getFromBuffer(bytes, pos, JausUtils.USHORT_BYTES, false), 0);
					pos += JausUtils.USHORT_BYTES;
				}
				
				public QueryEvents.Body.QueryEventsVar.MessageIDRec  setMessageIDRec(MessageIDRec value)
				{
					m_MessageCode = value.m_MessageCode;
					
					return this;
				}
				
				public bool isEqual(MessageIDRec value)
				{
					if (this.getMessageCode() != value.getMessageCode())
					{
						return false;
					}
					
					return true;
				}
				
				public bool notEquals(MessageIDRec value)
				{
					return !this.isEqual(value);
				}
				
				public MessageIDRec()
				{
					m_parent = null;
					m_MessageCode = 0;
				}
				
				public MessageIDRec(MessageIDRec value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_MessageCode = 0;
					
					/// Copy the values
					m_MessageCode = value.m_MessageCode;
				}
				
				 ~MessageIDRec()
				{
				}
				
			
				QueryEventsVar m_parent;
				protected ushort m_MessageCode;
			}
			public class EventTypeRec
			{
				public void setParent(QueryEventsVar parent)
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
					if ((value == 0) || (value == 1))
					{
						m_EventType = value;
						setParentPresenceVector();
					}
					return;
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
					
					bytes[pos] = (byte)m_EventType;
					pos += JausUtils.BYTE_BYTES;
				}
				
				public void decode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					m_EventType = bytes[pos];
					pos += JausUtils.BYTE_BYTES;
				}
				
				public QueryEvents.Body.QueryEventsVar.EventTypeRec  setEventTypeRec(EventTypeRec value)
				{
					m_EventType = value.m_EventType;
					
					return this;
				}
				
				public bool isEqual(EventTypeRec value)
				{
					if (this.getEventType() != value.getEventType())
					{
						return false;
					}
					
					return true;
				}
				
				public bool notEquals(EventTypeRec value)
				{
					return !this.isEqual(value);
				}
				
				public EventTypeRec()
				{
					m_parent = null;
					m_EventType = 0;
				}
				
				public EventTypeRec(EventTypeRec value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_EventType = 0;
					
					/// Copy the values
					m_EventType = value.m_EventType;
				}
				
				 ~EventTypeRec()
				{
				}
				
			
				QueryEventsVar m_parent;
				protected byte m_EventType;
			}
			public class EventIDRec
			{
				public void setParent(QueryEventsVar parent)
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
					
					bytes[pos] = (byte)m_EventID;
					pos += JausUtils.BYTE_BYTES;
				}
				
				public void decode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					m_EventID = bytes[pos];
					pos += JausUtils.BYTE_BYTES;
				}
				
				public QueryEvents.Body.QueryEventsVar.EventIDRec  setEventIDRec(EventIDRec value)
				{
					m_EventID = value.m_EventID;
					
					return this;
				}
				
				public bool isEqual(EventIDRec value)
				{
					if (this.getEventID() != value.getEventID())
					{
						return false;
					}
					
					return true;
				}
				
				public bool notEquals(EventIDRec value)
				{
					return !this.isEqual(value);
				}
				
				public EventIDRec()
				{
					m_parent = null;
					m_EventID = 0;
				}
				
				public EventIDRec(EventIDRec value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_EventID = 0;
					
					/// Copy the values
					m_EventID = value.m_EventID;
				}
				
				 ~EventIDRec()
				{
				}
				
			
				QueryEventsVar m_parent;
				protected byte m_EventID;
			}
			public class AllEventsRec
			{
				public void setParent(QueryEventsVar parent)
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
				
				public byte getAllEvents()
				{
					return m_AllEvents;
				}
				
				public void setAllEvents(byte value)
				{
					if (((value >= 0) && (value <= 0)))
					{
						m_AllEvents = value;
						setParentPresenceVector();
					}
					return;
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
					
					bytes[pos] = (byte)m_AllEvents;
					pos += JausUtils.BYTE_BYTES;
				}
				
				public void decode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					m_AllEvents = bytes[pos];
					pos += JausUtils.BYTE_BYTES;
				}
				
				public QueryEvents.Body.QueryEventsVar.AllEventsRec  setAllEventsRec(AllEventsRec value)
				{
					m_AllEvents = value.m_AllEvents;
					
					return this;
				}
				
				public bool isEqual(AllEventsRec value)
				{
					if (this.getAllEvents() != value.getAllEvents())
					{
						return false;
					}
					
					return true;
				}
				
				public bool notEquals(AllEventsRec value)
				{
					return !this.isEqual(value);
				}
				
				public AllEventsRec()
				{
					m_parent = null;
					m_AllEvents = 0;
				}
				
				public AllEventsRec(AllEventsRec value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_AllEvents = 0;
					
					/// Copy the values
					m_AllEvents = value.m_AllEvents;
				}
				
				 ~AllEventsRec()
				{
				}
				
			
				QueryEventsVar m_parent;
				protected byte m_AllEvents;
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
			
			public QueryEvents.Body.QueryEventsVar.MessageIDRec getMessageIDRec()
			{
				return m_MessageIDRec;
			}
			
			public void setMessageIDRec(MessageIDRec value)
			{
				m_MessageIDRec = value;
				setParentPresenceVector();
			}
			
			public QueryEvents.Body.QueryEventsVar.EventTypeRec getEventTypeRec()
			{
				return m_EventTypeRec;
			}
			
			public void setEventTypeRec(EventTypeRec value)
			{
				m_EventTypeRec = value;
				setParentPresenceVector();
			}
			
			public QueryEvents.Body.QueryEventsVar.EventIDRec getEventIDRec()
			{
				return m_EventIDRec;
			}
			
			public void setEventIDRec(EventIDRec value)
			{
				m_EventIDRec = value;
				setParentPresenceVector();
			}
			
			public QueryEvents.Body.QueryEventsVar.AllEventsRec getAllEventsRec()
			{
				return m_AllEventsRec;
			}
			
			public void setAllEventsRec(AllEventsRec value)
			{
				m_AllEventsRec = value;
				setParentPresenceVector();
			}
			
			public byte getFieldValue()
			{
				return m_FieldValue;
			}
			
			public void setFieldValue(byte fieldValue)
			{
				if(fieldValue > 3)
				{
					return;
				}
				
				m_FieldValue = fieldValue;
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
				
				size += sizeof(byte);
				
				switch(m_FieldValue)
				{
					case 0:
						size += m_MessageIDRec.getSize();
						break;
					case 1:
						size += m_EventTypeRec.getSize();
						break;
					case 2:
						size += m_EventIDRec.getSize();
						break;
					case 3:
						size += m_AllEventsRec.getSize();
						break;
				}
				
				return size;
			}
			
			public void encode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				bytes[pos] = (byte)m_FieldValue;
				pos += JausUtils.BYTE_BYTES;
				switch(m_FieldValue)
				{
					case 0:
						m_MessageIDRec.encode(bytes, pos);
						pos += m_MessageIDRec.getSize();
						break;
					case 1:
						m_EventTypeRec.encode(bytes, pos);
						pos += m_EventTypeRec.getSize();
						break;
					case 2:
						m_EventIDRec.encode(bytes, pos);
						pos += m_EventIDRec.getSize();
						break;
					case 3:
						m_AllEventsRec.encode(bytes, pos);
						pos += m_AllEventsRec.getSize();
						break;
				}
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_FieldValue = bytes[pos];
				pos += JausUtils.BYTE_BYTES;
				switch(m_FieldValue)
				{
					case 0:
						m_MessageIDRec.decode(bytes, pos);
						pos += m_MessageIDRec.getSize();
						break;
					case 1:
						m_EventTypeRec.decode(bytes, pos);
						pos += m_EventTypeRec.getSize();
						break;
					case 2:
						m_EventIDRec.decode(bytes, pos);
						pos += m_EventIDRec.getSize();
						break;
					case 3:
						m_AllEventsRec.decode(bytes, pos);
						pos += m_AllEventsRec.getSize();
						break;
				}
			}
			
			public QueryEvents.Body.QueryEventsVar  setQueryEventsVar(QueryEventsVar value)
			{
				m_FieldValue = value.m_FieldValue;
				m_MessageIDRec = value.getMessageIDRec();
				m_MessageIDRec.setParent(this);
				m_EventTypeRec = value.getEventTypeRec();
				m_EventTypeRec.setParent(this);
				m_EventIDRec = value.getEventIDRec();
				m_EventIDRec.setParent(this);
				m_AllEventsRec = value.getAllEventsRec();
				m_AllEventsRec.setParent(this);
				
				return this;
			}
			
			public bool isEqual(QueryEventsVar value)
			{
				if (this.m_FieldValue != value.m_FieldValue)
				{
					return false;
				}
				if (!this.getMessageIDRec().isEqual(value.getMessageIDRec()))
				{
					return false;
				}
				if (!this.getEventTypeRec().isEqual(value.getEventTypeRec()))
				{
					return false;
				}
				if (!this.getEventIDRec().isEqual(value.getEventIDRec()))
				{
					return false;
				}
				if (!this.getAllEventsRec().isEqual(value.getAllEventsRec()))
				{
					return false;
				}
				
				return true;
			}
			
			public bool notEquals(QueryEventsVar value)
			{
				return !this.isEqual(value);
			}
			
			public QueryEventsVar()
			{
				m_parent = null;
				m_MessageIDRec = new MessageIDRec();
				m_MessageIDRec.setParent(this);
				m_EventTypeRec = new EventTypeRec();
				m_EventTypeRec.setParent(this);
				m_EventIDRec = new EventIDRec();
				m_EventIDRec.setParent(this);
				m_AllEventsRec = new AllEventsRec();
				m_AllEventsRec.setParent(this);
			}
			
			public QueryEventsVar(QueryEventsVar value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageIDRec = new MessageIDRec();
				m_MessageIDRec.setParent(this);
				m_EventTypeRec = new EventTypeRec();
				m_EventTypeRec.setParent(this);
				m_EventIDRec = new EventIDRec();
				m_EventIDRec.setParent(this);
				m_AllEventsRec = new AllEventsRec();
				m_AllEventsRec.setParent(this);
				
				/// Copy the values
				m_FieldValue = value.m_FieldValue;
				m_MessageIDRec = value.getMessageIDRec();
				m_MessageIDRec.setParent(this);
				m_EventTypeRec = value.getEventTypeRec();
				m_EventTypeRec.setParent(this);
				m_EventIDRec = value.getEventIDRec();
				m_EventIDRec.setParent(this);
				m_AllEventsRec = value.getAllEventsRec();
				m_AllEventsRec.setParent(this);
			}
			
			 ~QueryEventsVar()
			{
			}
			
		
			Body m_parent;
			byte m_FieldValue;
			protected MessageIDRec m_MessageIDRec = new  MessageIDRec();
			protected EventTypeRec m_EventTypeRec = new  EventTypeRec();
			protected EventIDRec m_EventIDRec = new  EventIDRec();
			protected AllEventsRec m_AllEventsRec = new  AllEventsRec();
		}
	
		public QueryEvents.Body.QueryEventsVar getQueryEventsVar()
		{
			return m_QueryEventsVar;
		}
		
		public void setQueryEventsVar(QueryEventsVar value)
		{
			m_QueryEventsVar = value;
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
			
			size += m_QueryEventsVar.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_QueryEventsVar.encode(bytes, pos);
			pos += m_QueryEventsVar.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_QueryEventsVar.decode(bytes, pos);
			pos += m_QueryEventsVar.getSize();
		}
		
		public QueryEvents.Body  setBody(Body value)
		{
			m_QueryEventsVar = value.m_QueryEventsVar;
			m_QueryEventsVar.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public bool isEqual(Body value)
		{
			if (!m_QueryEventsVar.isEqual(value.m_QueryEventsVar))
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
			m_QueryEventsVar = new QueryEventsVar();
			m_QueryEventsVar.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_QueryEventsVar = new QueryEventsVar();
			m_QueryEventsVar.setParent(this);
			
			/// Copy the values
			m_QueryEventsVar = value.m_QueryEventsVar;
			m_QueryEventsVar.setParent(this);
			/// This code is currently not supported
		}
		
		 ~Body()
		{
		}
		
	
		protected QueryEventsVar m_QueryEventsVar = new  QueryEventsVar();
	}
	protected MsgHeader m_MsgHeader = new  MsgHeader();
	protected Body m_Body = new  Body();
	public override ushort getID()
	{
		return ID;
	}
	public QueryEvents.MsgHeader getMsgHeader()
	{
		return m_MsgHeader;
	}
	
	public void setMsgHeader(MsgHeader value)
	{
		m_MsgHeader = value;
	}
	
	public QueryEvents.Body getBody()
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
	
	public QueryEvents setAs(QueryEvents value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public bool  isEqual(QueryEvents value)
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
	
	public bool  notEquals(QueryEvents value)
	{
		return !this.isEqual(value);
	}
	
	public QueryEvents()
	{
		m_MsgHeader = new MsgHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  QueryEvents(QueryEvents value)
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
