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

public class QueryEvents extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x21f0;
	
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
			
			public QueryEvents.MsgHeader.HeaderRec assign(HeaderRec value)
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
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
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
		
		public QueryEvents.MsgHeader assign(MsgHeader value)
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
		public static class  QueryEventsVar
		{
			public static class  MessageIDRec
			{
			
				protected QueryEventsVar m_parent;
				protected int m_MessageCode;
			
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
				
				public int getMessageCode()
				{
					return m_MessageCode;
				}
				
				public void setMessageCode(int value)
				{
					m_MessageCode = value;
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
					
					bytes.putShort(pos, (short) m_MessageCode);
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
					
					m_MessageCode = bytes.getShort(pos) & 0xffff;
					pos += JausUtils.getNumBytes("short");
				}
				
				public QueryEvents.Body.QueryEventsVar.MessageIDRec assign(MessageIDRec value)
				{
					m_MessageCode = value.m_MessageCode;
					
					return this;
				}
				
				public boolean isEqual(MessageIDRec value)
				{
					if (m_MessageCode != value.getMessageCode())
					{
						return false;
					}
					
					return true;
				}
				
				public boolean notEquals(MessageIDRec value)
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
				
				public void finalize()
				{
				}
				
			}
			public static class  EventTypeRec
			{
			
				protected QueryEventsVar m_parent;
				protected short m_EventType;
			
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
					
					bytes.put(pos, (byte) m_EventType);
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
					
					m_EventType = (short) (bytes.get(pos) & 0xff);
					pos += JausUtils.getNumBytes("byte");
				}
				
				public QueryEvents.Body.QueryEventsVar.EventTypeRec assign(EventTypeRec value)
				{
					m_EventType = value.m_EventType;
					
					return this;
				}
				
				public boolean isEqual(EventTypeRec value)
				{
					if (m_EventType != value.getEventType())
					{
						return false;
					}
					
					return true;
				}
				
				public boolean notEquals(EventTypeRec value)
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
				
				public void finalize()
				{
				}
				
			}
			public static class  EventIDRec
			{
			
				protected QueryEventsVar m_parent;
				protected short m_EventID;
			
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
				
				public short getEventID()
				{
					return m_EventID;
				}
				
				public void setEventID(short value)
				{
					m_EventID = value;
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
					
					bytes.put(pos, (byte) m_EventID);
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
					
					m_EventID = (short) (bytes.get(pos) & 0xff);
					pos += JausUtils.getNumBytes("byte");
				}
				
				public QueryEvents.Body.QueryEventsVar.EventIDRec assign(EventIDRec value)
				{
					m_EventID = value.m_EventID;
					
					return this;
				}
				
				public boolean isEqual(EventIDRec value)
				{
					if (m_EventID != value.getEventID())
					{
						return false;
					}
					
					return true;
				}
				
				public boolean notEquals(EventIDRec value)
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
				
				public void finalize()
				{
				}
				
			}
			public static class  AllEventsRec
			{
			
				protected QueryEventsVar m_parent;
				protected short m_AllEvents;
			
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
				
				public short getAllEvents()
				{
					return m_AllEvents;
				}
				
				public void setAllEvents(short value)
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
					
					bytes.put(pos, (byte) m_AllEvents);
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
					
					m_AllEvents = (short) (bytes.get(pos) & 0xff);
					pos += JausUtils.getNumBytes("byte");
				}
				
				public QueryEvents.Body.QueryEventsVar.AllEventsRec assign(AllEventsRec value)
				{
					m_AllEvents = value.m_AllEvents;
					
					return this;
				}
				
				public boolean isEqual(AllEventsRec value)
				{
					if (m_AllEvents != value.getAllEvents())
					{
						return false;
					}
					
					return true;
				}
				
				public boolean notEquals(AllEventsRec value)
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
				
				public void finalize()
				{
				}
				
			}
		
		
			protected Body m_parent;
			protected short m_FieldValue;
			protected MessageIDRec m_MessageIDRec;
			protected EventTypeRec m_EventTypeRec;
			protected EventIDRec m_EventIDRec;
			protected AllEventsRec m_AllEventsRec;
		
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
			
			public short getFieldValue()
			{
				return m_FieldValue;
			}
			
			public void setFieldValue(short fieldValue)
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
			 * Java, but the bytes expected on the wire.
			 * 
			 * @return
			 */
			public long getSize()
			{
				long size = 0;
				
				size += JausUtils.getNumBytes("byte");
				
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
				bytes.put(pos, (byte) m_FieldValue);
				pos += JausUtils.getNumBytes("byte");
				}
				catch (Exception e)
				{
					logger.log(Level.SEVERE, null, e);
				}
				
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
				
				
				try
				{
				m_FieldValue = (short) (bytes.get(pos) & 0xff);
				pos += JausUtils.getNumBytes("byte");
				}
				catch (Exception e)
				{
					logger.log(Level.SEVERE, null, e);
				}
				
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
			
			public QueryEvents.Body.QueryEventsVar assign(QueryEventsVar value)
			{
				m_FieldValue = value.m_FieldValue;
				m_MessageIDRec = value.m_MessageIDRec;
				m_MessageIDRec.setParent(this);
				m_EventTypeRec = value.m_EventTypeRec;
				m_EventTypeRec.setParent(this);
				m_EventIDRec = value.m_EventIDRec;
				m_EventIDRec.setParent(this);
				m_AllEventsRec = value.m_AllEventsRec;
				m_AllEventsRec.setParent(this);
				
				return this;
			}
			
			public boolean isEqual(QueryEventsVar value)
			{
				if (m_FieldValue != value.m_FieldValue)
				{
					return false;
				}
				if (!m_MessageIDRec.isEqual(value.getMessageIDRec()))
				{
					return false;
				}
				if (!m_EventTypeRec.isEqual(value.getEventTypeRec()))
				{
					return false;
				}
				if (!m_EventIDRec.isEqual(value.getEventIDRec()))
				{
					return false;
				}
				if (!m_AllEventsRec.isEqual(value.getAllEventsRec()))
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(QueryEventsVar value)
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
				m_MessageIDRec = value.m_MessageIDRec;
				m_MessageIDRec.setParent(this);
				m_EventTypeRec = value.m_EventTypeRec;
				m_EventTypeRec.setParent(this);
				m_EventIDRec = value.m_EventIDRec;
				m_EventIDRec.setParent(this);
				m_AllEventsRec = value.m_AllEventsRec;
				m_AllEventsRec.setParent(this);
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected QueryEventsVar m_QueryEventsVar;
	
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
		 * Java, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public long getSize()
		{
			long size = 0;
			
			size += m_QueryEventsVar.getSize();
			
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
			
			m_QueryEventsVar.encode(bytes, pos);
			pos += m_QueryEventsVar.getSize();
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
			
			m_QueryEventsVar.decode(bytes, pos);
			pos += m_QueryEventsVar.getSize();
		}
		
		public QueryEvents.Body assign(Body value)
		{
			m_QueryEventsVar = value.m_QueryEventsVar;
			m_QueryEventsVar.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(Body value)
		{
			if (!m_QueryEventsVar.isEqual(value.m_QueryEventsVar))
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
	
	public QueryEvents setAs(QueryEvents value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(QueryEvents value)
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
	
	public boolean notEquals(QueryEvents value)
	{
		return !isEqual(value);
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
