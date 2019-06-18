using JTS;
using System;
using System.Collections;
using System.Linq;
using System.Collections;
using System.Runtime.InteropServices;
using System.Collections.Generic;

namespace urn_jts_DiscoveryClient_1_0
{

public class ReportIdentification : JTS.Message
{
	protected ushort ID = 0x4b00;
	
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
			
			public ReportIdentification.MsgHeader.HeaderRec  setHeaderRec(HeaderRec value)
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
				m_MessageID = 0x4b00;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x4b00;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			 ~HeaderRec()
			{
			}
			
		
			MsgHeader m_parent;
			protected ushort m_MessageID;
		}
	
		public ReportIdentification.MsgHeader.HeaderRec getHeaderRec()
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
		
		public ReportIdentification.MsgHeader  setMsgHeader(MsgHeader value)
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
		public class ReportIdentificationRec
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
			
			public byte getQueryType()
			{
				return m_QueryType;
			}
			
			public void setQueryType(byte value)
			{
				if (((value >= 5) && (value <= 255)) || (value == 0) || (value == 1) || (value == 2) || (value == 3) || (value == 4))
				{
					m_QueryType = value;
					setParentPresenceVector();
				}
				return;
			}
			
			public ushort getType()
			{
				return m_Type;
			}
			
			public void setType(ushort value)
			{
				if (((value >= 10002) && (value <= 20000)) || ((value >= 20002) && (value <= 30000)) || ((value >= 30002) && (value <= 40000)) || ((value >= 40002) && (value <= 50000)) || ((value >= 50002) && (value <= 60000)) || ((value >= 60002) && (value <= 65535)) || (value == 0) || (value == 10001) || (value == 20001) || (value == 30001) || (value == 40001) || (value == 50001) || (value == 60001))
				{
					m_Type = value;
					setParentPresenceVector();
				}
				return;
			}
			
			public string getIdentification()
			{
				return m_Identification;
			}
			
			public void setIdentification(string value)
			{
				if ( value.Length > 255)
				{
					return;
				}
				
				m_Identification = value;
				if (m_Identification.Length < 0)
				{
					//Resizing a stiring like this is not possible in C#.
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
				
				size += JausUtils.getNumBytes("byte");
				size += JausUtils.getNumBytes("ushort");
				size += JausUtils.getNumBytes("byte");
				size += m_Identification.Length;
				
				return size;
			}
			
			public void encode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				bytes[pos] = (byte)m_QueryType;
				pos += JausUtils.BYTE_BYTES;
				bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((ushort)m_Type), pos, (int)JausUtils.USHORT_BYTES, false);
				pos += JausUtils.USHORT_BYTES;
				
				bytes[pos] = (byte)m_Identification.Length;
				pos += JausUtils.BYTE_BYTES;
				
				bytes = JausUtils.addToBuffer(bytes, m_Identification.ToCharArray(), pos, (int)m_Identification.Length);
				pos += (int)m_Identification.Length;
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_QueryType = bytes[pos];
				pos += JausUtils.BYTE_BYTES;
				m_Type = BitConverter.ToUInt16(JausUtils.getFromBuffer(bytes, pos, JausUtils.USHORT_BYTES, false), 0);
				pos += JausUtils.USHORT_BYTES;
				
				byte m_IdentificationLength = 0;
				m_IdentificationLength = bytes[pos];
				pos += JausUtils.BYTE_BYTES;
				
				m_Identification = JausUtils.getFromBuffer(bytes, pos, (int)m_IdentificationLength, true, false);
				pos += (int)m_IdentificationLength;
			}
			
			public ReportIdentification.Body.ReportIdentificationRec  setReportIdentificationRec(ReportIdentificationRec value)
			{
				m_QueryType = value.m_QueryType;
				m_Type = value.m_Type;
				m_Identification = value.m_Identification;
				
				return this;
			}
			
			public bool isEqual(ReportIdentificationRec value)
			{
				if (this.getQueryType() != value.getQueryType())
				{
					return false;
				}
				if (this.getType() != value.getType())
				{
					return false;
				}
				if ((m_Identification.Length != value.m_Identification.Length) || (m_Identification.CompareTo(value.m_Identification) != 0))
				{
					return false;
				}
				
				return true;
			}
			
			public bool notEquals(ReportIdentificationRec value)
			{
				return !this.isEqual(value);
			}
			
			public ReportIdentificationRec()
			{
				m_parent = null;
				m_QueryType = 0;
				m_Type = 0;
				m_Identification = "";
			}
			
			public ReportIdentificationRec(ReportIdentificationRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_QueryType = 0;
				m_Type = 0;
				m_Identification = "";
				
				/// Copy the values
				m_QueryType = value.m_QueryType;
				m_Type = value.m_Type;
				m_Identification = value.m_Identification;
			}
			
			 ~ReportIdentificationRec()
			{
			}
			
		
			Body m_parent;
			protected byte m_QueryType;
			protected ushort m_Type;
			protected string m_Identification;
		}
	
		public ReportIdentification.Body.ReportIdentificationRec getReportIdentificationRec()
		{
			return m_ReportIdentificationRec;
		}
		
		public void setReportIdentificationRec(ReportIdentificationRec value)
		{
			m_ReportIdentificationRec = value;
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
			
			size += m_ReportIdentificationRec.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_ReportIdentificationRec.encode(bytes, pos);
			pos += m_ReportIdentificationRec.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_ReportIdentificationRec.decode(bytes, pos);
			pos += m_ReportIdentificationRec.getSize();
		}
		
		public ReportIdentification.Body  setBody(Body value)
		{
			m_ReportIdentificationRec = value.getReportIdentificationRec();
			m_ReportIdentificationRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public bool isEqual(Body value)
		{
			if (!this.getReportIdentificationRec().isEqual(value.getReportIdentificationRec()))
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
			m_ReportIdentificationRec = new ReportIdentificationRec();
			m_ReportIdentificationRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_ReportIdentificationRec = new ReportIdentificationRec();
			m_ReportIdentificationRec.setParent(this);
			
			/// Copy the values
			m_ReportIdentificationRec = value.getReportIdentificationRec();
			m_ReportIdentificationRec.setParent(this);
			/// This code is currently not supported
		}
		
		 ~Body()
		{
		}
		
	
		protected ReportIdentificationRec m_ReportIdentificationRec = new  ReportIdentificationRec();
	}
	protected MsgHeader m_MsgHeader = new  MsgHeader();
	protected Body m_Body = new  Body();
	public override ushort getID()
	{
		return ID;
	}
	public ReportIdentification.MsgHeader getMsgHeader()
	{
		return m_MsgHeader;
	}
	
	public void setMsgHeader(MsgHeader value)
	{
		m_MsgHeader = value;
	}
	
	public ReportIdentification.Body getBody()
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
	
	public ReportIdentification setAs(ReportIdentification value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public bool  isEqual(ReportIdentification value)
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
	
	public bool  notEquals(ReportIdentification value)
	{
		return !this.isEqual(value);
	}
	
	public ReportIdentification()
	{
		m_MsgHeader = new MsgHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  ReportIdentification(ReportIdentification value)
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
