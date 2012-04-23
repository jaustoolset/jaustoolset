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

public class RejectEventRequest extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x01f4;
	
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
			
			public RejectEventRequest.MsgHeader.HeaderRec assign(HeaderRec value)
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
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
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
		
		public RejectEventRequest.MsgHeader assign(MsgHeader value)
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
		public static class  RejectEventRequestRec
		{
		
			protected Body m_parent;
			protected BitSet m_PresenceVector;
			protected short m_PresenceVectorTemp;
			protected short m_RequestID;
			protected short m_ResponseCode;
			String m_ErrorMessage;
		
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
			
			public short getPresenceVector()
			{
				return m_PresenceVectorTemp;
			}
			
			protected void setPresenceVector(int index)
			{
				
				m_PresenceVector.set(index);
				m_PresenceVectorTemp = (short) JausUtils.getPVInt(m_PresenceVector);
			}
			
			public boolean checkPresenceVector(int index)
			{
				
				return m_PresenceVector.get(index);
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
			
			public boolean isResponseCodeValid()
			{
				if (checkPresenceVector(0))
				{
					return true;
				}
				return false;
			}
			
			public short getResponseCode()
			{
				return m_ResponseCode;
			}
			
			public void setResponseCode(short value)
			{
				if ((value == 1) || (value == 2) || (value == 3) || (value == 4) || (value == 5) || (value == 6))
				{
					m_ResponseCode = value;
					setPresenceVector(0);
					setParentPresenceVector();
				}
				return;
			}
			
			public boolean isErrorMessageValid()
			{
				if (checkPresenceVector(1))
				{
					return true;
				}
				return false;
			}
			
			public String getErrorMessage()
			{
				return m_ErrorMessage;
			}
			
			public int setErrorMessage(String value)
			{
				m_ErrorMessage = value;
				setPresenceVector(1);
				setParentPresenceVector();
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
				size += JausUtils.getNumBytes("byte");
				if (checkPresenceVector(0))
				{
					size += JausUtils.getNumBytes("byte");
				}
				if (checkPresenceVector(1))
				{
					size += 80;
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
					short m_PresenceVectorTemp = (short) JausUtils.getPVInt(m_PresenceVector);
				bytes.put(pos, (byte) m_PresenceVectorTemp);
				pos += JausUtils.getNumBytes("byte");
				}
				catch(Exception e)
				{
					logger.log(Level.SEVERE, null, e);
				}
				bytes.put(pos, (byte) m_RequestID);
				pos += JausUtils.getNumBytes("byte");
				if (checkPresenceVector(0))
				{
					bytes.put(pos, (byte) m_ResponseCode);
					pos += JausUtils.getNumBytes("byte");
				}
				if (checkPresenceVector(1))
				{
					char[] m_ErrorMessageTemp = new char[80];
					m_ErrorMessageTemp = m_ErrorMessage.toCharArray();
					
					for(int i = 0; i<m_ErrorMessageTemp.length; i++)
					{
						bytes.put(pos, (byte)m_ErrorMessageTemp[i]);
						pos++;
					}
					
					
					for(int i = m_ErrorMessageTemp.length; i<80; i++)
					{
						bytes.put(pos, (byte) 0);
						pos++;
					}
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
					short m_PresenceVectorTemp = 0;
				m_PresenceVectorTemp = (short) (bytes.get(pos) & 0xff);
				pos += JausUtils.getNumBytes("byte");
				m_PresenceVector = JausUtils.setPV(m_PresenceVectorTemp);
				}
				catch(Exception e)
				{
					logger.log(Level.SEVERE, null, e);
				}
				m_RequestID = (short) (bytes.get(pos) & 0xff);
				pos += JausUtils.getNumBytes("byte");
				if (checkPresenceVector(0))
				{
					m_ResponseCode = (short) (bytes.get(pos) & 0xff);
					pos += JausUtils.getNumBytes("byte");
				}
				if (checkPresenceVector(1))
				{
					char[] m_ErrorMessageTemp= new char[(int)80];
					
					int i; byte by; int tmp = pos;
					for(i=0; i<80; i++)
					{
						by = bytes.get(tmp);
						if(by == 0)
						{
							break;
						}
						m_ErrorMessageTemp[i] = (char) (by&0xff);
						tmp++;
					}
					pos += 80;
					
					m_ErrorMessage = new String(m_ErrorMessageTemp);
					if(i<80)
					{
						m_ErrorMessage = m_ErrorMessage.substring(0, i);
					}
				}
			}
			
			public RejectEventRequest.Body.RejectEventRequestRec assign(RejectEventRequestRec value)
			{
				m_PresenceVector = value.m_PresenceVector;
				m_RequestID = value.m_RequestID;
				m_ResponseCode = value.m_ResponseCode;
				m_ErrorMessage = value.m_ErrorMessage;
				
				return this;
			}
			
			public boolean isEqual(RejectEventRequestRec value)
			{
				if (!m_PresenceVector.equals(value.m_PresenceVector))
				{
					return false;
				}
				if (m_RequestID != value.getRequestID())
				{
					return false;
				}
				if (m_ResponseCode != value.getResponseCode())
				{
					return false;
				}
				if (m_ErrorMessage != value.m_ErrorMessage)
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(RejectEventRequestRec value)
			{
				return !this.isEqual(value);
				}
				
			public RejectEventRequestRec()
			{
				m_parent = null;
				m_PresenceVector = new BitSet();
				m_PresenceVectorTemp = 0;
				m_RequestID = 0;
				m_ResponseCode = 0;
				m_ErrorMessage = "";
			}
			
			public RejectEventRequestRec(RejectEventRequestRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_PresenceVector = new BitSet();
				m_PresenceVectorTemp = 0;
				m_RequestID = 0;
				m_ResponseCode = 0;
				m_ErrorMessage = "";
				
				/// Copy the values
				m_PresenceVector = value.m_PresenceVector;
				m_RequestID = value.m_RequestID;
				m_ResponseCode = value.m_ResponseCode;
				m_ErrorMessage = value.m_ErrorMessage;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected RejectEventRequestRec m_RejectEventRequestRec;
	
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
		 * Java, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public long getSize()
		{
			long size = 0;
			
			size += m_RejectEventRequestRec.getSize();
			
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
			
			m_RejectEventRequestRec.encode(bytes, pos);
			pos += m_RejectEventRequestRec.getSize();
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
			
			m_RejectEventRequestRec.decode(bytes, pos);
			pos += m_RejectEventRequestRec.getSize();
		}
		
		public RejectEventRequest.Body assign(Body value)
		{
			m_RejectEventRequestRec = value.m_RejectEventRequestRec;
			m_RejectEventRequestRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(Body value)
		{
			if (!m_RejectEventRequestRec.isEqual(value.getRejectEventRequestRec()))
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
			m_RejectEventRequestRec = new RejectEventRequestRec();
			m_RejectEventRequestRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_RejectEventRequestRec = new RejectEventRequestRec();
			m_RejectEventRequestRec.setParent(this);
			
			/// Copy the values
			m_RejectEventRequestRec = value.m_RejectEventRequestRec;
			m_RejectEventRequestRec.setParent(this);
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
	
	public RejectEventRequest setAs(RejectEventRequest value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(RejectEventRequest value)
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
	
	public boolean notEquals(RejectEventRequest value)
	{
		return !isEqual(value);
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
