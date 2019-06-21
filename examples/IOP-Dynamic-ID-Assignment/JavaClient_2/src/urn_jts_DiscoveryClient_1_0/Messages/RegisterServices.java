package src.urn_jts_DiscoveryClient_1_0.Messages;

import framework.messages.Message;
import framework.JausUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterServices extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x0b00;
	
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
			
			public RegisterServices.MsgHeader.HeaderRec assign(HeaderRec value)
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
				m_MessageID = 0x0b00;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x0b00;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
		public RegisterServices.MsgHeader.HeaderRec getHeaderRec()
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
		
		public RegisterServices.MsgHeader assign(MsgHeader value)
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
	public static class  RegisterServicesBody
	{
		public static class  ServiceList
		{
			public static class  ServiceRec
			{
			
				protected ServiceList m_parent;
				protected String m_URI;
				protected short m_MajorVersionNumber;
				protected short m_MinorVersionNumber;
			
				public void setParent(ServiceList parent)
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
				
				public String getURI()
				{
					return m_URI;
				}
				
				public void setURI(String value)
				{
					if ( value.length() > 255)
					{
						return;
					}
					
					m_URI = value;
					if (m_URI.length() < 0)
					{
						m_URI = m_URI.substring(0, 0);
					}
					setParentPresenceVector();
				}
				
				public short getMajorVersionNumber()
				{
					return m_MajorVersionNumber;
				}
				
				public void setMajorVersionNumber(short value)
				{
					m_MajorVersionNumber = value;
					setParentPresenceVector();
				}
				
				public short getMinorVersionNumber()
				{
					return m_MinorVersionNumber;
				}
				
				public void setMinorVersionNumber(short value)
				{
					m_MinorVersionNumber = value;
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
					size += m_URI.length();
					size += JausUtils.getNumBytes("byte");
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
					
					
					try
					{
					bytes.put(pos, (byte) m_URI.length());
					pos += JausUtils.getNumBytes("byte");
					
					char[] m_URIBytes = m_URI.toCharArray();
					for(int str_pos = 0; str_pos<m_URIBytes.length; str_pos++)
					{
						bytes.put(pos, (byte) m_URIBytes[str_pos]);
						pos++;
					}
					}
					catch (Exception e)
					{
						
					}
					bytes.put(pos, (byte) m_MajorVersionNumber);
					pos += JausUtils.getNumBytes("byte");
					bytes.put(pos, (byte) m_MinorVersionNumber);
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
					
					
					short m_URILength = 0;
					m_URILength = (short) (bytes.get(pos) & 0xff);
					pos += JausUtils.getNumBytes("byte");
					
					
					// Decoding the string m_URIfrom the buffer.
					char[] m_URIBytes = new char[(int)m_URILength];
					for(int decode_pos = 0; decode_pos<(int)m_URILength;decode_pos++)
					{
						m_URIBytes[decode_pos] = (char) (bytes.get(pos) &0xff);
						pos++;
					}
					m_URI = new String(m_URIBytes);
					m_MajorVersionNumber = (short) (bytes.get(pos) & 0xff);
					pos += JausUtils.getNumBytes("byte");
					m_MinorVersionNumber = (short) (bytes.get(pos) & 0xff);
					pos += JausUtils.getNumBytes("byte");
				}
				
				public RegisterServices.RegisterServicesBody.ServiceList.ServiceRec assign(ServiceRec value)
				{
					m_URI = value.m_URI;
					m_MajorVersionNumber = value.m_MajorVersionNumber;
					m_MinorVersionNumber = value.m_MinorVersionNumber;
					
					return this;
				}
				
				public boolean isEqual(ServiceRec value)
				{
					if ((m_URI.length() != value.m_URI.length()) || (m_URI.compareTo(value.m_URI) != 0))
					{
						return false;
					}
					if (m_MajorVersionNumber != value.getMajorVersionNumber())
					{
						return false;
					}
					if (m_MinorVersionNumber != value.getMinorVersionNumber())
					{
						return false;
					}
					
					return true;
				}
				
				public boolean notEquals(ServiceRec value)
				{
					return !this.isEqual(value);
				}
				
				public ServiceRec()
				{
					m_parent = null;
					m_URI = new String();
					m_MajorVersionNumber = 0;
					m_MinorVersionNumber = 0;
				}
				
				public ServiceRec(ServiceRec value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_URI = new String();
					m_MajorVersionNumber = 0;
					m_MinorVersionNumber = 0;
					
					/// Copy the values
					m_URI = value.m_URI;
					m_MajorVersionNumber = value.m_MajorVersionNumber;
					m_MinorVersionNumber = value.m_MinorVersionNumber;
				}
				
				public void finalize()
				{
				}
				
			}
		
		
			protected RegisterServicesBody m_parent;
			protected ArrayList<ServiceRec> m_ServiceRec;
		
			public void setParent(RegisterServicesBody parent)
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
			
			public long getNumberOfElements()
			{
				return (long) m_ServiceRec.size();
			}
			
			public RegisterServices.RegisterServicesBody.ServiceList.ServiceRec getElement(int index)
			{
				return m_ServiceRec.get(index);
			}
			
			public void setElement(int index, ServiceRec value)
			{
				if(m_ServiceRec.size()-1 < index)
				{
					return;
				}
				
				m_ServiceRec.set(index, value);
				m_ServiceRec.get(index).setParent(this);
				setParentPresenceVector();
			}
			
			public void addElement(ServiceRec value)
			{
				m_ServiceRec.add(value);
				m_ServiceRec.get(m_ServiceRec.size() -1 ).setParent(this);
				setParentPresenceVector();
			}
			
			public int deleteElement(int index)
			{
				if(m_ServiceRec.size()-1 < index)
				{
					return 1;
				}
				
				m_ServiceRec.remove(index);
				return 0;
			}
			
			public int deleteLastElement()
			{
				m_ServiceRec.remove(m_ServiceRec.size()-1);
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
				for (int i = 0; i < m_ServiceRec.size(); i++)
				{
					size += m_ServiceRec.get(i).getSize();
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
				
				short size = (short) m_ServiceRec.size();
				bytes.put(pos, (byte) size);
				pos += JausUtils.getNumBytes("byte");
				for (int i = 0; i < m_ServiceRec.size(); i++)
				{
					m_ServiceRec.get(i).encode(bytes, pos);
					pos += m_ServiceRec.get(i).getSize();
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
				
				short size;
				size = (short) (bytes.get(pos) & 0xff);
				pos += JausUtils.getNumBytes("byte");
				m_ServiceRec = new ArrayList<ServiceRec>();
				for (int i = 0; i <  size; i++)
				{
					ServiceRec item = new ServiceRec();
					item.decode(bytes, pos);
					m_ServiceRec.add(item);
					pos += item.getSize();
				}
			}
			
			public RegisterServices.RegisterServicesBody.ServiceList assign(ServiceList value)
			{
				m_ServiceRec.clear();
				
				for (int i = 0; i < value.m_ServiceRec.size(); i++)
				{
					m_ServiceRec.add(value.m_ServiceRec.get(i));
					m_ServiceRec.get(i).setParent(this);
				}
				
				return this;
			}
			
			public boolean isEqual(ServiceList value)
			{
				if (m_ServiceRec.size() !=  value.m_ServiceRec.size())
				{
					return false;
				}
				
				for (int i = 0; i < m_ServiceRec.size(); i++)
				{
					if (!m_ServiceRec.get(i).isEqual(value.getElement(i)))
					{
						return false;
					}
				}
				
				return true;
			}
			
			public boolean notEquals(ServiceList value)
			{
				return !this.isEqual(value);
			}
			
			public ServiceList()
			{
				m_parent = null;
				m_ServiceRec = new ArrayList<ServiceRec>();
				for (int i = 0; i < m_ServiceRec.size(); i++)
				{
					m_ServiceRec.get(i).setParent(this);
				}
			}
			
			public ServiceList(ServiceList value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_ServiceRec = new ArrayList<ServiceRec>();
				for (int i = 0; i < m_ServiceRec.size(); i++)
				{
					m_ServiceRec.get(i).setParent(this);
				}
				
				/// Copy the values
				m_ServiceRec.clear();
				
				for (int i = 0; i < value.m_ServiceRec.size(); i++)
				{
					m_ServiceRec.add(value.m_ServiceRec.get(i));
					m_ServiceRec.get(i).setParent(this);
				}
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected ServiceList m_ServiceList;
	
		public RegisterServices.RegisterServicesBody.ServiceList getServiceList()
		{
			return m_ServiceList;
		}
		
		public void setServiceList(ServiceList value)
		{
			m_ServiceList = value;
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
			
			size += m_ServiceList.getSize();
			
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
			
			m_ServiceList.encode(bytes, pos);
			pos += m_ServiceList.getSize();
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
			
			m_ServiceList.decode(bytes, pos);
			pos += m_ServiceList.getSize();
		}
		
		public RegisterServices.RegisterServicesBody assign(RegisterServicesBody value)
		{
			m_ServiceList = value.m_ServiceList;
			m_ServiceList.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(RegisterServicesBody value)
		{
			if (!m_ServiceList.isEqual(value.m_ServiceList))
			{
				return false;
			}
			/// This code is currently not supported
			return true;
		}
		
		public boolean notEquals(RegisterServicesBody value)
		{
			return !this.isEqual(value);
		}
		
		public RegisterServicesBody()
		{
			m_ServiceList = new ServiceList();
			m_ServiceList.setParent(this);
		}
		
		public RegisterServicesBody(RegisterServicesBody value)
		{
			/// Initiliaze the protected variables
			m_ServiceList = new ServiceList();
			m_ServiceList.setParent(this);
			
			/// Copy the values
			m_ServiceList = value.m_ServiceList;
			m_ServiceList.setParent(this);
			/// This code is currently not supported
		}
		
		public void finalize()
		{
		}
		
	}
	protected MsgHeader m_MsgHeader;
	protected RegisterServicesBody m_RegisterServicesBody;
	public long getID()
	{
	return ID;
 }
	public RegisterServices.MsgHeader getMsgHeader()
	{
		return m_MsgHeader;
	}
	
	public void setMsgHeader(MsgHeader value)
	{
		m_MsgHeader = value;
	}
	
	public RegisterServices.RegisterServicesBody getRegisterServicesBody()
	{
		return m_RegisterServicesBody;
	}
	
	public int setRegisterServicesBody(RegisterServicesBody value)
	{
		m_RegisterServicesBody = value;
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
		size += m_RegisterServicesBody.getSize();
		
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
		m_RegisterServicesBody.encode(bytes, pos);
		pos += m_RegisterServicesBody.getSize();
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
		m_RegisterServicesBody.decode(bytes, pos);
		pos += m_RegisterServicesBody.getSize();
	}
	
	public RegisterServices setAs(RegisterServices value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_RegisterServicesBody = value.m_RegisterServicesBody;
		
		return this;
	}
	
	public boolean isEqual(RegisterServices value)
	{
		if (!m_MsgHeader.isEqual(value.getMsgHeader()))
		{
			return false;
		}
		if (!m_RegisterServicesBody.isEqual(value.getRegisterServicesBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public boolean notEquals(RegisterServices value)
	{
		return !isEqual(value);
	}
	
	public RegisterServices()
	{
		m_MsgHeader = new MsgHeader();
		m_RegisterServicesBody = new RegisterServicesBody();
		m_IsCommand = true;
	}
	
	public  RegisterServices(RegisterServices value)
	{
		/// Initiliaze the protected variables
		m_MsgHeader = new MsgHeader();
		m_RegisterServicesBody = new RegisterServicesBody();
		m_IsCommand = true;
		
		/// Copy the values
		m_MsgHeader = value.m_MsgHeader;
		m_RegisterServicesBody = value.m_RegisterServicesBody;
	}
	
}
