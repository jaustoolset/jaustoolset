using JTS;
using System;
using System.Collections;
using System.Linq;
using System.Collections;
using System.Runtime.InteropServices;
using System.Collections.Generic;

namespace urn_jaus_jss_core_AccessControl_1_1
{

public class CreateCommandEvent : JTS.Message
{
	protected ushort ID = 0x01f6;
	
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
			
			public CreateCommandEvent.MsgHeader.HeaderRec  setHeaderRec(HeaderRec value)
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
				m_MessageID = 0x01f6;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x01f6;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			 ~HeaderRec()
			{
			}
			
		
			MsgHeader m_parent;
			protected ushort m_MessageID;
		}
	
		public CreateCommandEvent.MsgHeader.HeaderRec getHeaderRec()
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
		
		public CreateCommandEvent.MsgHeader  setMsgHeader(MsgHeader value)
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
		public class CreateEventRec
		{
			public class CommandMessage
			{
				public void setParent(CreateEventRec parent)
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
				
				public CreateCommandEvent.Body.CreateEventRec.CommandMessage  setCommandMessage(CommandMessage value)
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
				
				public bool isEqual(CommandMessage value)
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
				
				public bool notEquals(CommandMessage value)
				{
					return !this.isEqual(value);
				}
				
				public CommandMessage()
				{
					m_parent = null;
					m_Length = 0;
					m_Data = null;
				}
				
				public CommandMessage(CommandMessage value)
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
				
				 ~CommandMessage()
				{
				}
				
			
				CreateEventRec m_parent;
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
			
			public byte getRequestID()
			{
				return m_RequestID;
			}
			
			public void setRequestID(byte value)
			{
				m_RequestID = value;
				setParentPresenceVector();
			}
			
			public uint getMaximumAllowedDuration()
			{
				return m_MaximumAllowedDuration;
			}
			
			public void setMaximumAllowedDuration(uint value)
			{
				m_MaximumAllowedDuration = value;
				setParentPresenceVector();
			}
			
			public CreateCommandEvent.Body.CreateEventRec.CommandMessage getCommandMessage()
			{
				return m_CommandMessage;
			}
			
			public void setCommandMessage(CommandMessage value)
			{
				m_CommandMessage = value;
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
				size += JausUtils.getNumBytes("uint");
				size += m_CommandMessage.getSize();
				
				return size;
			}
			
			public void encode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				bytes[pos] = (byte)m_RequestID;
				pos += JausUtils.BYTE_BYTES;
				bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((uint)m_MaximumAllowedDuration), pos, (int)JausUtils.UINT_BYTES, false);
				pos += JausUtils.UINT_BYTES;
				m_CommandMessage.encode(bytes, pos);
				pos += m_CommandMessage.getSize();
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_RequestID = bytes[pos];
				pos += JausUtils.BYTE_BYTES;
				m_MaximumAllowedDuration = BitConverter.ToUInt32(JausUtils.getFromBuffer(bytes, pos, JausUtils.UINT_BYTES, false), 0);
				pos += JausUtils.UINT_BYTES;
				m_CommandMessage.decode(bytes, pos);
				pos += m_CommandMessage.getSize();
			}
			
			public CreateCommandEvent.Body.CreateEventRec  setCreateEventRec(CreateEventRec value)
			{
				m_RequestID = value.m_RequestID;
				m_MaximumAllowedDuration = value.m_MaximumAllowedDuration;
				m_CommandMessage = value.getCommandMessage();
				
				return this;
			}
			
			public bool isEqual(CreateEventRec value)
			{
				if (this.getRequestID() != value.getRequestID())
				{
					return false;
				}
				if (this.getMaximumAllowedDuration() != value.getMaximumAllowedDuration())
				{
					return false;
				}
				
				if (!this.getCommandMessage().isEqual(value.getCommandMessage()))
				{
					return false;
				}
				
				return true;
			}
			
			public bool notEquals(CreateEventRec value)
			{
				return !this.isEqual(value);
			}
			
			public CreateEventRec()
			{
				m_parent = null;
				m_RequestID = 0;
				m_MaximumAllowedDuration = 0;
					m_CommandMessage = new CommandMessage();
				m_CommandMessage.setParent(this);
			}
			
			public CreateEventRec(CreateEventRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_RequestID = 0;
				m_MaximumAllowedDuration = 0;
					m_CommandMessage = new CommandMessage();
				m_CommandMessage.setParent(this);
				
				/// Copy the values
				m_RequestID = value.m_RequestID;
				m_MaximumAllowedDuration = value.m_MaximumAllowedDuration;
				m_CommandMessage = value.getCommandMessage();
			}
			
			 ~CreateEventRec()
			{
			}
			
		
			Body m_parent;
			protected byte m_RequestID;
			protected uint m_MaximumAllowedDuration;
			protected CommandMessage m_CommandMessage = new  CommandMessage();
		}
	
		public CreateCommandEvent.Body.CreateEventRec getCreateEventRec()
		{
			return m_CreateEventRec;
		}
		
		public void setCreateEventRec(CreateEventRec value)
		{
			m_CreateEventRec = value;
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
			
			size += m_CreateEventRec.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_CreateEventRec.encode(bytes, pos);
			pos += m_CreateEventRec.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_CreateEventRec.decode(bytes, pos);
			pos += m_CreateEventRec.getSize();
		}
		
		public CreateCommandEvent.Body  setBody(Body value)
		{
			m_CreateEventRec = value.getCreateEventRec();
			m_CreateEventRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public bool isEqual(Body value)
		{
			if (!this.getCreateEventRec().isEqual(value.getCreateEventRec()))
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
			m_CreateEventRec = new CreateEventRec();
			m_CreateEventRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_CreateEventRec = new CreateEventRec();
			m_CreateEventRec.setParent(this);
			
			/// Copy the values
			m_CreateEventRec = value.getCreateEventRec();
			m_CreateEventRec.setParent(this);
			/// This code is currently not supported
		}
		
		 ~Body()
		{
		}
		
	
		protected CreateEventRec m_CreateEventRec = new  CreateEventRec();
	}
	protected MsgHeader m_MsgHeader = new  MsgHeader();
	protected Body m_Body = new  Body();
	public override ushort getID()
	{
		return ID;
	}
	public CreateCommandEvent.MsgHeader getMsgHeader()
	{
		return m_MsgHeader;
	}
	
	public void setMsgHeader(MsgHeader value)
	{
		m_MsgHeader = value;
	}
	
	public CreateCommandEvent.Body getBody()
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
	
	public CreateCommandEvent setAs(CreateCommandEvent value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public bool  isEqual(CreateCommandEvent value)
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
	
	public bool  notEquals(CreateCommandEvent value)
	{
		return !this.isEqual(value);
	}
	
	public CreateCommandEvent()
	{
		m_MsgHeader = new MsgHeader();
		m_Body = new Body();
		m_IsCommand = true;
	}
	
	public  CreateCommandEvent(CreateCommandEvent value)
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

}
