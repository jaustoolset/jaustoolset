using JTS;
using System;
using System.Collections;
using System.Linq;
using System.Collections;
using System.Runtime.InteropServices;
using System.Collections.Generic;

namespace urn_jaus_jss_core_Management_1_1
{

public class ReportControl : JTS.Message
{
	protected ushort ID = 0x400d;
	
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
			
			public ReportControl.MsgHeader.HeaderRec  setHeaderRec(HeaderRec value)
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
				m_MessageID = 0x400d;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x400d;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			 ~HeaderRec()
			{
			}
			
		
			MsgHeader m_parent;
			protected ushort m_MessageID;
		}
	
		public ReportControl.MsgHeader.HeaderRec getHeaderRec()
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
		
		public ReportControl.MsgHeader  setMsgHeader(MsgHeader value)
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
		public class ReportControlRec
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
			
			public ushort getSubsystemID()
			{
				return m_SubsystemID;
			}
			
			public void setSubsystemID(ushort value)
			{
				m_SubsystemID = value;
				setParentPresenceVector();
			}
			
			public byte getNodeID()
			{
				return m_NodeID;
			}
			
			public void setNodeID(byte value)
			{
				m_NodeID = value;
				setParentPresenceVector();
			}
			
			public byte getComponentID()
			{
				return m_ComponentID;
			}
			
			public void setComponentID(byte value)
			{
				m_ComponentID = value;
				setParentPresenceVector();
			}
			
			public byte getAuthorityCode()
			{
				return m_AuthorityCode;
			}
			
			public void setAuthorityCode(byte value)
			{
				if (((value >= 0) && (value <= 255)))
				{
					m_AuthorityCode = value;
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
				
				size += JausUtils.getNumBytes("ushort");
				size += JausUtils.getNumBytes("byte");
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
				
				bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((ushort)m_SubsystemID), pos, (int)JausUtils.USHORT_BYTES, false);
				pos += JausUtils.USHORT_BYTES;
				bytes[pos] = (byte)m_NodeID;
				pos += JausUtils.BYTE_BYTES;
				bytes[pos] = (byte)m_ComponentID;
				pos += JausUtils.BYTE_BYTES;
				bytes[pos] = (byte)m_AuthorityCode;
				pos += JausUtils.BYTE_BYTES;
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_SubsystemID = BitConverter.ToUInt16(JausUtils.getFromBuffer(bytes, pos, JausUtils.USHORT_BYTES, false), 0);
				pos += JausUtils.USHORT_BYTES;
				m_NodeID = bytes[pos];
				pos += JausUtils.BYTE_BYTES;
				m_ComponentID = bytes[pos];
				pos += JausUtils.BYTE_BYTES;
				m_AuthorityCode = bytes[pos];
				pos += JausUtils.BYTE_BYTES;
			}
			
			public ReportControl.Body.ReportControlRec  setReportControlRec(ReportControlRec value)
			{
				m_SubsystemID = value.m_SubsystemID;
				m_NodeID = value.m_NodeID;
				m_ComponentID = value.m_ComponentID;
				m_AuthorityCode = value.m_AuthorityCode;
				
				return this;
			}
			
			public bool isEqual(ReportControlRec value)
			{
				if (this.getSubsystemID() != value.getSubsystemID())
				{
					return false;
				}
				if (this.getNodeID() != value.getNodeID())
				{
					return false;
				}
				if (this.getComponentID() != value.getComponentID())
				{
					return false;
				}
				if (this.getAuthorityCode() != value.getAuthorityCode())
				{
					return false;
				}
				
				return true;
			}
			
			public bool notEquals(ReportControlRec value)
			{
				return !this.isEqual(value);
				}
				
			public ReportControlRec()
			{
				m_parent = null;
				m_SubsystemID = 0;
				m_NodeID = 0;
				m_ComponentID = 0;
				m_AuthorityCode = 0;
			}
			
			public ReportControlRec(ReportControlRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_SubsystemID = 0;
				m_NodeID = 0;
				m_ComponentID = 0;
				m_AuthorityCode = 0;
				
				/// Copy the values
				m_SubsystemID = value.m_SubsystemID;
				m_NodeID = value.m_NodeID;
				m_ComponentID = value.m_ComponentID;
				m_AuthorityCode = value.m_AuthorityCode;
			}
			
			 ~ReportControlRec()
			{
			}
			
		
			Body m_parent;
			protected ushort m_SubsystemID;
			protected byte m_NodeID;
			protected byte m_ComponentID;
			protected byte m_AuthorityCode;
		}
	
		public ReportControl.Body.ReportControlRec getReportControlRec()
		{
			return m_ReportControlRec;
		}
		
		public void setReportControlRec(ReportControlRec value)
		{
			m_ReportControlRec = value;
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
			
			size += m_ReportControlRec.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_ReportControlRec.encode(bytes, pos);
			pos += m_ReportControlRec.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_ReportControlRec.decode(bytes, pos);
			pos += m_ReportControlRec.getSize();
		}
		
		public ReportControl.Body  setBody(Body value)
		{
			m_ReportControlRec = value.getReportControlRec();
			m_ReportControlRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public bool isEqual(Body value)
		{
			if (!this.getReportControlRec().isEqual(value.getReportControlRec()))
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
			m_ReportControlRec = new ReportControlRec();
			m_ReportControlRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_ReportControlRec = new ReportControlRec();
			m_ReportControlRec.setParent(this);
			
			/// Copy the values
			m_ReportControlRec = value.getReportControlRec();
			m_ReportControlRec.setParent(this);
			/// This code is currently not supported
		}
		
		 ~Body()
		{
		}
		
	
		protected ReportControlRec m_ReportControlRec = new  ReportControlRec();
	}
	protected MsgHeader m_MsgHeader = new  MsgHeader();
	protected Body m_Body = new  Body();
	public override ushort getID()
	{
		return ID;
	}
	public ReportControl.MsgHeader getMsgHeader()
	{
		return m_MsgHeader;
	}
	
	public void setMsgHeader(MsgHeader value)
	{
		m_MsgHeader = value;
	}
	
	public ReportControl.Body getBody()
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
	
	public ReportControl setAs(ReportControl value)
	{
		m_MsgHeader = value.m_MsgHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public bool  isEqual(ReportControl value)
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
	
	public bool  notEquals(ReportControl value)
	{
		return !this.isEqual(value);
		}
		
	public ReportControl()
	{
		m_MsgHeader = new MsgHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  ReportControl(ReportControl value)
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
