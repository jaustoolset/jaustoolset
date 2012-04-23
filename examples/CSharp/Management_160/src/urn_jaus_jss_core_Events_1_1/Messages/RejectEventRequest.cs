using JTS;
using System;
using System.Collections;
using System.Linq;
using System.Collections;
using System.Runtime.InteropServices;
using System.Collections.Generic;

namespace urn_jaus_jss_core_Events_1_1
{

public class RejectEventRequest : JTS.Message
{
	protected ushort ID = 0x01f4;
	
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
			
			public RejectEventRequest.MsgHeader.HeaderRec  setHeaderRec(HeaderRec value)
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
				m_MessageID = 0x01f4;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x01f4;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			 ~HeaderRec()
			{
			}
			
		
			MsgHeader m_parent;
			protected ushort m_MessageID;
		}
	
		public RejectEventRequest.MsgHeader.HeaderRec getHeaderRec()
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
		
		public RejectEventRequest.MsgHeader  setMsgHeader(MsgHeader value)
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
		public class RejectEventRequestRec
		{
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
			
			public byte getPresenceVector()
			{
				return m_PresenceVector;
			}
			
			public int setPresenceVector(int index)
			{
				BitArray pvBitSet = JausUtils.setPV((uint) m_PresenceVector);
				
				pvBitSet.Set(index, true);
				m_PresenceVector = (byte)JausUtils.getPVint(pvBitSet);
				return 0;
			}
			
			public bool checkPresenceVector(int index)
			{
				BitArray pvBitSet = JausUtils.setPV(m_PresenceVector);
				
				return pvBitSet.Get(index);
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
			
			public bool isResponseCodeValid()
			{
				if (checkPresenceVector(0))
				{
					return true;
				}
				return false;
			}
			
			public byte getResponseCode()
			{
				return m_ResponseCode;
			}
			
			public void setResponseCode(byte value)
			{
				if ((value == 1) || (value == 2) || (value == 3) || (value == 4) || (value == 5) || (value == 6))
				{
					m_ResponseCode = value;
					setPresenceVector(0);
					setParentPresenceVector();
				}
				return;
			}
			
			public bool isErrorMessageValid()
			{
				if (checkPresenceVector(1))
				{
					return true;
				}
				return false;
			}
			
			public string getErrorMessage()
			{
				return m_ErrorMessage;
			}
			
			public int setErrorMessage(string value)
			{
				m_ErrorMessage = value;
				setPresenceVector(1);
				setParentPresenceVector();
				return 0;
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
				if (checkPresenceVector(0))
				{
					size += JausUtils.getNumBytes("byte");
				}
				if (checkPresenceVector(1))
				{
					size += (int)80;
				}
				
				return size;
			}
			
			public void encode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				bytes[pos] = (byte)m_PresenceVector;
				pos += JausUtils.BYTE_BYTES;
				bytes[pos] = (byte)m_RequestID;
				pos += JausUtils.BYTE_BYTES;
				if (checkPresenceVector(0))
				{
					bytes[pos] = (byte)m_ResponseCode;
					pos += JausUtils.BYTE_BYTES;
				}
				if (checkPresenceVector(1))
				{
					bytes = JausUtils.addToBuffer(bytes, m_ErrorMessage.ToCharArray(), pos, (int)80);
					pos += (int)80;
				}
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_PresenceVector = bytes[pos];
				pos += JausUtils.BYTE_BYTES;
				m_RequestID = bytes[pos];
				pos += JausUtils.BYTE_BYTES;
				if (checkPresenceVector(0))
				{
					m_ResponseCode = bytes[pos];
					pos += JausUtils.BYTE_BYTES;
				}
				if (checkPresenceVector(1))
				{
					m_ErrorMessage = JausUtils.getFromBuffer(bytes, pos, (int)80, true, false);
					pos += (int)80;
				}
			}
			
			public RejectEventRequest.Body.RejectEventRequestRec  setRejectEventRequestRec(RejectEventRequestRec value)
			{
				m_PresenceVector = value.m_PresenceVector;
				m_RequestID = value.m_RequestID;
				m_ResponseCode = value.m_ResponseCode;
				m_ErrorMessage = value.getErrorMessage();
				
				return this;
			}
			
			public bool isEqual(RejectEventRequestRec value)
			{
				if (m_PresenceVector != value.m_PresenceVector)
				{
					return false;
				}
				if (this.getRequestID() != value.getRequestID())
				{
					return false;
				}
				if (this.getResponseCode() != value.getResponseCode())
				{
					return false;
				}
				if (this.getErrorMessage() != value.getErrorMessage())
				{
					return false;
				}
				
				return true;
			}
			
			public bool notEquals(RejectEventRequestRec value)
			{
				return !this.isEqual(value);
			}
			
			public RejectEventRequestRec()
			{
				m_parent = null;
				m_PresenceVector = 0;
				m_RequestID = 0;
				m_ResponseCode = 0;
				m_ErrorMessage = "";
			}
			
			public RejectEventRequestRec(RejectEventRequestRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_PresenceVector = 0;
				m_RequestID = 0;
				m_ResponseCode = 0;
				m_ErrorMessage = "";
				
				/// Copy the values
				m_PresenceVector = value.m_PresenceVector;
				m_RequestID = value.m_RequestID;
				m_ResponseCode = value.m_ResponseCode;
				m_ErrorMessage = value.getErrorMessage();
			}
			
			 ~RejectEventRequestRec()
			{
			}
			
		
			Body m_parent;
			byte m_PresenceVector;
			protected byte m_RequestID;
			protected byte m_ResponseCode;
			string m_ErrorMessage;
		}
	
		public RejectEventRequest.Body.RejectEventRequestRec getRejectEventRequestRec()
		{
			return m_RejectEventRequestRec;
		}
		
		public void setRejectEventRequestRec(RejectEventRequestRec value)
		{
			m_RejectEventRequestRec = value;
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
			
			size += m_RejectEventRequestRec.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_RejectEventRequestRec.encode(bytes, pos);
			pos += m_RejectEventRequestRec.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_RejectEventRequestRec.decode(bytes, pos);
			pos += m_RejectEventRequestRec.getSize();
		}
		
		public RejectEventRequest.Body  setBody(Body value)
		{
			m_RejectEventRequestRec = value.getRejectEventRequestRec();
			m_RejectEventRequestRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public bool isEqual(Body value)
		{
			if (!this.getRejectEventRequestRec().isEqual(value.getRejectEventRequestRec()))
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
			m_RejectEventRequestRec = new RejectEventRequestRec();
			m_RejectEventRequestRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_RejectEventRequestRec = new RejectEventRequestRec();
			m_RejectEventRequestRec.setParent(this);
			
			/// Copy the values
			m_RejectEventRequestRec = value.getRejectEventRequestRec();
			m_RejectEventRequestRec.setParent(this);
			/// This code is currently not supported
		}
		
		 ~Body()
		{
		}
		
	
		protected RejectEventRequestRec m_RejectEventRequestRec = new  RejectEventRequestRec();
	}
	protected MsgHeader m_MsgHeader = new  MsgHeader();
	protected Body m_Body = new  Body();
	public override ushort getID()
	{
		return ID;
	}
	public RejectEventRequest.MsgHeader getMsgHeader()
	{
		return m_MsgHeader;
	}
	
	public void setMsgHeader(MsgHeader value)
	{
		m_MsgHeader = value;
	}
	
	public RejectEventRequest.Body getBody()
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
	
	public RejectEventRequest setAs(RejectEventRequest value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public bool  isEqual(RejectEventRequest value)
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
	
	public bool  notEquals(RejectEventRequest value)
	{
		return !this.isEqual(value);
	}
	
	public RejectEventRequest()
	{
		m_MsgHeader = new MsgHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  RejectEventRequest(RejectEventRequest value)
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
