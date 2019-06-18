using JTS;
using System;
using System.Collections;
using System.Linq;
using System.Collections;
using System.Runtime.InteropServices;
using System.Collections.Generic;

namespace urn_jts_DiscoveryClient_1_0
{

public class RegisterServices : JTS.Message
{
	protected ushort ID = 0x0b00;
	
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
			
			public RegisterServices.MsgHeader.HeaderRec  setHeaderRec(HeaderRec value)
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
			
			 ~HeaderRec()
			{
			}
			
		
			MsgHeader m_parent;
			protected ushort m_MessageID;
		}
	
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
		
		public RegisterServices.MsgHeader  setMsgHeader(MsgHeader value)
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
	public class RegisterServicesBody
	{
		public class ServiceList
		{
			public class ServiceRec
			{
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
				
				public string getURI()
				{
					return m_URI;
				}
				
				public void setURI(string value)
				{
					if ( value.Length > 255)
					{
						return;
					}
					
					m_URI = value;
					if (m_URI.Length < 0)
					{
						//Resizing a stiring like this is not possible in C#.
					}
					setParentPresenceVector();
				}
				
				public byte getMajorVersionNumber()
				{
					return m_MajorVersionNumber;
				}
				
				public void setMajorVersionNumber(byte value)
				{
					m_MajorVersionNumber = value;
					setParentPresenceVector();
				}
				
				public byte getMinorVersionNumber()
				{
					return m_MinorVersionNumber;
				}
				
				public void setMinorVersionNumber(byte value)
				{
					m_MinorVersionNumber = value;
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
					size += m_URI.Length;
					size += JausUtils.getNumBytes("byte");
					size += JausUtils.getNumBytes("byte");
					
					return size;
				}
				
				public void encode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					
					bytes[pos] = (byte)m_URI.Length;
					pos += JausUtils.BYTE_BYTES;
					
					bytes = JausUtils.addToBuffer(bytes, m_URI.ToCharArray(), pos, (int)m_URI.Length);
					pos += (int)m_URI.Length;
					bytes[pos] = (byte)m_MajorVersionNumber;
					pos += JausUtils.BYTE_BYTES;
					bytes[pos] = (byte)m_MinorVersionNumber;
					pos += JausUtils.BYTE_BYTES;
				}
				
				public void decode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					
					byte m_URILength = 0;
					m_URILength = bytes[pos];
					pos += JausUtils.BYTE_BYTES;
					
					m_URI = JausUtils.getFromBuffer(bytes, pos, (int)m_URILength, true, false);
					pos += (int)m_URILength;
					m_MajorVersionNumber = bytes[pos];
					pos += JausUtils.BYTE_BYTES;
					m_MinorVersionNumber = bytes[pos];
					pos += JausUtils.BYTE_BYTES;
				}
				
				public RegisterServices.RegisterServicesBody.ServiceList.ServiceRec  setServiceRec(ServiceRec value)
				{
					m_URI = value.m_URI;
					m_MajorVersionNumber = value.m_MajorVersionNumber;
					m_MinorVersionNumber = value.m_MinorVersionNumber;
					
					return this;
				}
				
				public bool isEqual(ServiceRec value)
				{
					if ((m_URI.Length != value.m_URI.Length) || (m_URI.CompareTo(value.m_URI) != 0))
					{
						return false;
					}
					if (this.getMajorVersionNumber() != value.getMajorVersionNumber())
					{
						return false;
					}
					if (this.getMinorVersionNumber() != value.getMinorVersionNumber())
					{
						return false;
					}
					
					return true;
				}
				
				public bool notEquals(ServiceRec value)
				{
					return !this.isEqual(value);
				}
				
				public ServiceRec()
				{
					m_parent = null;
					m_URI = "";
					m_MajorVersionNumber = 0;
					m_MinorVersionNumber = 0;
				}
				
				public ServiceRec(ServiceRec value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_URI = "";
					m_MajorVersionNumber = 0;
					m_MinorVersionNumber = 0;
					
					/// Copy the values
					m_URI = value.m_URI;
					m_MajorVersionNumber = value.m_MajorVersionNumber;
					m_MinorVersionNumber = value.m_MinorVersionNumber;
				}
				
				 ~ServiceRec()
				{
				}
				
			
				ServiceList m_parent;
				protected string m_URI;
				protected byte m_MajorVersionNumber;
				protected byte m_MinorVersionNumber;
			}
		
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
			
			public int getNumberOfElements()
			{
				return (int) m_ServiceRec.Count;
			}
			
			public RegisterServices.RegisterServicesBody.ServiceList.ServiceRec getElement(int index)
			{
				return m_ServiceRec[index];
			}
			
			public void setElement(int index, ServiceRec value)
			{
				if(m_ServiceRec.Count-1 < index)
				{
					return;
				}
				
				m_ServiceRec[index] = value;
				m_ServiceRec[index].setParent(this);
				setParentPresenceVector();
			}
			
			public void addElement(ServiceRec value)
			{
				m_ServiceRec.Add(value);
				m_ServiceRec[m_ServiceRec.Count-1].setParent(this);
				setParentPresenceVector();
			}
			
			public void deleteElement(int index)
			{
				if(m_ServiceRec.Count-1 < index)
				{
					return;
				}
				
				m_ServiceRec.RemoveAt(index);
			}
			
			public void deleteLastElement()
			{
				m_ServiceRec.RemoveAt(m_ServiceRec.Count-1);
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
				for (int i = 0; i < m_ServiceRec.Count; i++)
				{
					size += m_ServiceRec[i].getSize();
				}
				
				return size;
			}
			
			public void encode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				byte size = (byte) m_ServiceRec.Count;
				bytes[pos] = (byte)size;
				pos += JausUtils.BYTE_BYTES;
				for (int i = 0; i < m_ServiceRec.Count; i++)
				{
					m_ServiceRec[i].encode(bytes, pos);
					pos += m_ServiceRec[i].getSize();
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
				m_ServiceRec = new List<ServiceRec>();
				for (int i = 0; i < size; i++)
				{
					m_ServiceRec.Add( new ServiceRec());
					m_ServiceRec[i].decode(bytes, pos);
					pos += m_ServiceRec[i].getSize();
				}
			}
			
			public RegisterServices.RegisterServicesBody.ServiceList  setServiceList(ServiceList value)
			{
				m_ServiceRec.Clear();
				
				for (int i = 0; i < value.m_ServiceRec.Count; i++)
				{
					m_ServiceRec.Add(value.m_ServiceRec[i]);
					m_ServiceRec[i].setParent(this);
				}
				
				return this;
			}
			
			public bool isEqual(ServiceList value)
			{
				if (m_ServiceRec.Count !=  value.m_ServiceRec.Count)
				{
					return false;
				}
				
				for (int i = 0; i < value.m_ServiceRec.Count; i++)
				{
					if (!this.m_ServiceRec[i].isEqual(value.m_ServiceRec[i]))
					{
						return false;
					}
				}
				
				return true;
			}
			
			public bool notEquals(ServiceList value)
			{
				return !this.isEqual(value);
			}
			
			public ServiceList()
			{
				m_parent = null;
				m_ServiceRec = new List<ServiceRec>();
				for (int i = 0; i < m_ServiceRec.Count; i++)
				{
					m_ServiceRec[i].setParent(this);
				}
			}
			
			public ServiceList(ServiceList value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_ServiceRec = new List<ServiceRec>();
				for (int i = 0; i < m_ServiceRec.Count; i++)
				{
					m_ServiceRec[i].setParent(this);
				}
				
				/// Copy the values
				m_ServiceRec.Clear();
				
				for (int i = 0; i < value.m_ServiceRec.Count; i++)
				{
					m_ServiceRec.Add(value.m_ServiceRec[i]);
					m_ServiceRec[i].setParent(this);
				}
			}
			
			 ~ServiceList()
			{
			}
			
		
			RegisterServicesBody m_parent;
			protected List<ServiceRec> m_ServiceRec = new  List<ServiceRec>();
		}
	
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
		 * C Sharp, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public int getSize()
		{
			int size = 0;
			
			size += m_ServiceList.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_ServiceList.encode(bytes, pos);
			pos += m_ServiceList.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_ServiceList.decode(bytes, pos);
			pos += m_ServiceList.getSize();
		}
		
		public RegisterServices.RegisterServicesBody  setRegisterServicesBody(RegisterServicesBody value)
		{
			m_ServiceList = value.m_ServiceList;
			m_ServiceList.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public bool isEqual(RegisterServicesBody value)
		{
			if (!this.m_ServiceList.isEqual(value.m_ServiceList))
			{
				return false;
			}
			/// This code is currently not supported
			return true;
		}
		
		public bool notEquals(RegisterServicesBody value)
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
		
		 ~RegisterServicesBody()
		{
		}
		
	
		protected ServiceList m_ServiceList = new  ServiceList();
	}
	protected MsgHeader m_MsgHeader = new  MsgHeader();
	protected RegisterServicesBody m_RegisterServicesBody = new  RegisterServicesBody();
	public override ushort getID()
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
	
	public void setRegisterServicesBody(RegisterServicesBody value)
	{
		m_RegisterServicesBody = value;
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
		size += m_RegisterServicesBody.getSize();
		
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
		m_RegisterServicesBody.encode(bytes, pos);
		pos += m_RegisterServicesBody.getSize();
		
	}
	
	public override void decode(byte[] bytes, int pos)
	{
		
		if (bytes == null)
		{
			return;
		}
		
		m_MsgHeader.decode(bytes, pos);
		pos += m_MsgHeader.getSize();
		m_RegisterServicesBody.decode(bytes, pos);
		pos += m_RegisterServicesBody.getSize();
		if(pos < bytes.Length)
		{
			for(int i = pos; i<bytes.Length; i++)
			{
				bytes[i] = 0;
			}
		}
	}
	
	public RegisterServices setAs(RegisterServices value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_RegisterServicesBody = value.m_RegisterServicesBody;
		
		return this;
	}
	
	public bool  isEqual(RegisterServices value)
	{
		if (!this.getMsgHeader().isEqual(value.getMsgHeader()))
		{
			return false;
		}
		if (!this.getRegisterServicesBody().isEqual(value.getRegisterServicesBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public bool  notEquals(RegisterServices value)
	{
		return !this.isEqual(value);
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

}
