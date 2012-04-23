using JTS;
using System;
using System.Collections;
using System.Linq;
using System.Collections;
using System.Runtime.InteropServices;
using System.Collections.Generic;

namespace urn_jaus_jss_core_Events_1_1
{

public class ConfirmEventRequest : JTS.Message
{
	protected ushort ID = 0x01f3;
	
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
			
			public ConfirmEventRequest.MsgHeader.HeaderRec  setHeaderRec(HeaderRec value)
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
				m_MessageID = 0x01f3;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x01f3;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			 ~HeaderRec()
			{
			}
			
		
			MsgHeader m_parent;
			protected ushort m_MessageID;
		}
	
		public ConfirmEventRequest.MsgHeader.HeaderRec getHeaderRec()
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
		
		public ConfirmEventRequest.MsgHeader  setMsgHeader(MsgHeader value)
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
		public class ConfirmEventRequestRec
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
			
			public byte getRequestID()
			{
				return m_RequestID;
			}
			
			public void setRequestID(byte value)
			{
				m_RequestID = value;
				setParentPresenceVector();
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
			
			public double getConfirmedPeriodicRate()
			{
				double value;
				
				double scaleFactor = ( 1092 - 0 ) / 65535.0;
				double bias = 0;
				
				value = m_ConfirmedPeriodicRate * scaleFactor + bias;
				
				return value;
			}
			
			public void setConfirmedPeriodicRate(double value)
			{
				if ((value >= 0) && (value <= 1092))
				{
					double scaleFactor = ( 1092 - 0 ) / 65535.0;
					double bias = 0;
					
					m_ConfirmedPeriodicRate= (ushort)((value - bias) / scaleFactor);
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
				size += JausUtils.getNumBytes("byte");
				size += JausUtils.getNumBytes("ushort");
				
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
				bytes[pos] = (byte)m_EventID;
				pos += JausUtils.BYTE_BYTES;
				bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((ushort)m_ConfirmedPeriodicRate), pos, (int)JausUtils.USHORT_BYTES, false);
				pos += JausUtils.USHORT_BYTES;
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_RequestID = bytes[pos];
				pos += JausUtils.BYTE_BYTES;
				m_EventID = bytes[pos];
				pos += JausUtils.BYTE_BYTES;
				m_ConfirmedPeriodicRate = BitConverter.ToUInt16(JausUtils.getFromBuffer(bytes, pos, JausUtils.USHORT_BYTES, false), 0);
				pos += JausUtils.USHORT_BYTES;
			}
			
			public ConfirmEventRequest.Body.ConfirmEventRequestRec  setConfirmEventRequestRec(ConfirmEventRequestRec value)
			{
				m_RequestID = value.m_RequestID;
				m_EventID = value.m_EventID;
				m_ConfirmedPeriodicRate = value.m_ConfirmedPeriodicRate;
				
				return this;
			}
			
			public bool isEqual(ConfirmEventRequestRec value)
			{
				if (this.getRequestID() != value.getRequestID())
				{
					return false;
				}
				if (this.getEventID() != value.getEventID())
				{
					return false;
				}
				if (this.getConfirmedPeriodicRate() != value.getConfirmedPeriodicRate())
				{
					return false;
				}
				
				return true;
			}
			
			public bool notEquals(ConfirmEventRequestRec value)
			{
				return !this.isEqual(value);
			}
			
			public ConfirmEventRequestRec()
			{
				m_parent = null;
				m_RequestID = 0;
				m_EventID = 0;
				m_ConfirmedPeriodicRate = 0;
			}
			
			public ConfirmEventRequestRec(ConfirmEventRequestRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_RequestID = 0;
				m_EventID = 0;
				m_ConfirmedPeriodicRate = 0;
				
				/// Copy the values
				m_RequestID = value.m_RequestID;
				m_EventID = value.m_EventID;
				m_ConfirmedPeriodicRate = value.m_ConfirmedPeriodicRate;
			}
			
			 ~ConfirmEventRequestRec()
			{
			}
			
		
			Body m_parent;
			protected byte m_RequestID;
			protected byte m_EventID;
			protected ushort m_ConfirmedPeriodicRate;
		}
	
		public ConfirmEventRequest.Body.ConfirmEventRequestRec getConfirmEventRequestRec()
		{
			return m_ConfirmEventRequestRec;
		}
		
		public void setConfirmEventRequestRec(ConfirmEventRequestRec value)
		{
			m_ConfirmEventRequestRec = value;
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
			
			size += m_ConfirmEventRequestRec.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_ConfirmEventRequestRec.encode(bytes, pos);
			pos += m_ConfirmEventRequestRec.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_ConfirmEventRequestRec.decode(bytes, pos);
			pos += m_ConfirmEventRequestRec.getSize();
		}
		
		public ConfirmEventRequest.Body  setBody(Body value)
		{
			m_ConfirmEventRequestRec = value.getConfirmEventRequestRec();
			m_ConfirmEventRequestRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public bool isEqual(Body value)
		{
			if (!this.getConfirmEventRequestRec().isEqual(value.getConfirmEventRequestRec()))
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
			m_ConfirmEventRequestRec = new ConfirmEventRequestRec();
			m_ConfirmEventRequestRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_ConfirmEventRequestRec = new ConfirmEventRequestRec();
			m_ConfirmEventRequestRec.setParent(this);
			
			/// Copy the values
			m_ConfirmEventRequestRec = value.getConfirmEventRequestRec();
			m_ConfirmEventRequestRec.setParent(this);
			/// This code is currently not supported
		}
		
		 ~Body()
		{
		}
		
	
		protected ConfirmEventRequestRec m_ConfirmEventRequestRec = new  ConfirmEventRequestRec();
	}
	protected MsgHeader m_MsgHeader = new  MsgHeader();
	protected Body m_Body = new  Body();
	public override ushort getID()
	{
		return ID;
	}
	public ConfirmEventRequest.MsgHeader getMsgHeader()
	{
		return m_MsgHeader;
	}
	
	public void setMsgHeader(MsgHeader value)
	{
		m_MsgHeader = value;
	}
	
	public ConfirmEventRequest.Body getBody()
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
	
	public ConfirmEventRequest setAs(ConfirmEventRequest value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public bool  isEqual(ConfirmEventRequest value)
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
	
	public bool  notEquals(ConfirmEventRequest value)
	{
		return !this.isEqual(value);
	}
	
	public ConfirmEventRequest()
	{
		m_MsgHeader = new MsgHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  ConfirmEventRequest(ConfirmEventRequest value)
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
