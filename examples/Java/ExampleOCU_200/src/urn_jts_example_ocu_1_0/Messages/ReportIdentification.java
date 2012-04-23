package src.urn_jts_example_ocu_1_0.Messages;

import framework.messages.Message;
import framework.JausUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportIdentification extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x4b00;
	
	public static class  JAUSApplicationLayerHeader
	{
		public static class  HeaderRec
		{
		
			protected JAUSApplicationLayerHeader m_parent;
			protected int m_MessageID;
		
			public void setParent(JAUSApplicationLayerHeader parent)
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
			
			public ReportIdentification.JAUSApplicationLayerHeader.HeaderRec assign(HeaderRec value)
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
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
		public ReportIdentification.JAUSApplicationLayerHeader.HeaderRec getHeaderRec()
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
		
		public ReportIdentification.JAUSApplicationLayerHeader assign(JAUSApplicationLayerHeader value)
		{
			m_HeaderRec = value.m_HeaderRec;
			m_HeaderRec.setParent(this);
			
			return this;
		}
		
		public boolean isEqual(JAUSApplicationLayerHeader value)
		{
			if (!m_HeaderRec.isEqual(value.getHeaderRec()))
			{
				return false;
			}
			return true;
		}
		
		public boolean notEquals(JAUSApplicationLayerHeader value)
		{
			return !this.isEqual(value);
		}
		
		public JAUSApplicationLayerHeader()
		{
			m_HeaderRec = new HeaderRec();
			m_HeaderRec.setParent(this);
		}
		
		public JAUSApplicationLayerHeader(JAUSApplicationLayerHeader value)
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
		public static class  ReportIdentificationRec
		{
		
			protected Body m_parent;
			protected short m_QueryType;
			protected int m_Type;
			protected String m_Identification;
		
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
			
			public short getQueryType()
			{
				return m_QueryType;
			}
			
			public void setQueryType(short value)
			{
				if (((value >= 5.00) && (value <= 255.00)) || (value == 1) || (value == 2) || (value == 3) || (value == 4))
				{
					m_QueryType = value;
					setParentPresenceVector();
				}
				return;
			}
			
			public int getType()
			{
				return m_Type;
			}
			
			public void setType(int value)
			{
				if (((value >= 10002.00) && (value <= 20000.00)) || ((value >= 20002.00) && (value <= 30000.00)) || ((value >= 30002.00) && (value <= 40000.00)) || ((value >= 40002.00) && (value <= 50000.00)) || ((value >= 50002.00) && (value <= 60000.00)) || ((value >= 60002.00) && (value <= 65535.00)) || (value == 0) || (value == 10001) || (value == 20001) || (value == 30001) || (value == 40001) || (value == 50001) || (value == 60001))
				{
					m_Type = value;
					setParentPresenceVector();
				}
				return;
			}
			
			public String getIdentification()
			{
				return m_Identification;
			}
			
			public void setIdentification(String value)
			{
				if ( value.length() > 255)
				{
					return;
				}
				
				m_Identification = value;
				if (m_Identification.length() < 0)
				{
					m_Identification = m_Identification.substring(0, 0);
				}
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
				size += JausUtils.getNumBytes("short");
				size += JausUtils.getNumBytes("byte");
				size += m_Identification.length();
				
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
				
				bytes.put(pos, (byte) m_QueryType);
				pos += JausUtils.getNumBytes("byte");
				bytes.putShort(pos, (short) m_Type);
				pos += JausUtils.getNumBytes("short");
				
				try
				{
				bytes.put(pos, (byte) m_Identification.length());
				pos += JausUtils.getNumBytes("byte");
				
				char[] m_IdentificationBytes = m_Identification.toCharArray();
				for(int str_pos = 0; str_pos<m_IdentificationBytes.length; str_pos++)
				{
					bytes.put(pos, (byte) m_IdentificationBytes[str_pos]);
					pos++;
				}
				}
				catch (Exception e)
				{
					
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
				
				m_QueryType = (short) (bytes.get(pos) & 0xff);
				pos += JausUtils.getNumBytes("byte");
				m_Type = bytes.getShort(pos) & 0xffff;
				pos += JausUtils.getNumBytes("short");
				
				short m_IdentificationLength = 0;
				m_IdentificationLength = (short) (bytes.get(pos) & 0xff);
				pos += JausUtils.getNumBytes("byte");
				
				
				// Decoding the string m_Identificationfrom the buffer.
				char[] m_IdentificationBytes = new char[(int)m_IdentificationLength];
				for(int decode_pos = 0; decode_pos<(int)m_IdentificationLength;decode_pos++)
				{
					m_IdentificationBytes[decode_pos] = (char) (bytes.get(pos) &0xff);
					pos++;
				}
				m_Identification = new String(m_IdentificationBytes);
			}
			
			public ReportIdentification.Body.ReportIdentificationRec assign(ReportIdentificationRec value)
			{
				m_QueryType = value.m_QueryType;
				m_Type = value.m_Type;
				m_Identification = value.m_Identification;
				
				return this;
			}
			
			public boolean isEqual(ReportIdentificationRec value)
			{
				if (m_QueryType != value.getQueryType())
				{
					return false;
				}
				if (m_Type != value.getType())
				{
					return false;
				}
				if ((m_Identification.length() != value.m_Identification.length()) || (m_Identification.compareTo(value.m_Identification) != 0))
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(ReportIdentificationRec value)
			{
				return !this.isEqual(value);
			}
			
			public ReportIdentificationRec()
			{
				m_parent = null;
				m_QueryType = 0;
				m_Type = 0;
				m_Identification = new String();
			}
			
			public ReportIdentificationRec(ReportIdentificationRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_QueryType = 0;
				m_Type = 0;
				m_Identification = new String();
				
				/// Copy the values
				m_QueryType = value.m_QueryType;
				m_Type = value.m_Type;
				m_Identification = value.m_Identification;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected ReportIdentificationRec m_ReportIdentificationRec;
	
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
		 * Java, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public long getSize()
		{
			long size = 0;
			
			size += m_ReportIdentificationRec.getSize();
			
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
			
			m_ReportIdentificationRec.encode(bytes, pos);
			pos += m_ReportIdentificationRec.getSize();
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
			
			m_ReportIdentificationRec.decode(bytes, pos);
			pos += m_ReportIdentificationRec.getSize();
		}
		
		public ReportIdentification.Body assign(Body value)
		{
			m_ReportIdentificationRec = value.m_ReportIdentificationRec;
			m_ReportIdentificationRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(Body value)
		{
			if (!m_ReportIdentificationRec.isEqual(value.getReportIdentificationRec()))
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
			m_ReportIdentificationRec = new ReportIdentificationRec();
			m_ReportIdentificationRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_ReportIdentificationRec = new ReportIdentificationRec();
			m_ReportIdentificationRec.setParent(this);
			
			/// Copy the values
			m_ReportIdentificationRec = value.m_ReportIdentificationRec;
			m_ReportIdentificationRec.setParent(this);
			/// This code is currently not supported
		}
		
		public void finalize()
		{
		}
		
	}
	protected JAUSApplicationLayerHeader m_JAUSApplicationLayerHeader;
	protected Body m_Body;
	public long getID()
	{
	return ID;
 }
	public ReportIdentification.JAUSApplicationLayerHeader getJAUSApplicationLayerHeader()
	{
		return m_JAUSApplicationLayerHeader;
	}
	
	public void setJAUSApplicationLayerHeader(JAUSApplicationLayerHeader value)
	{
		m_JAUSApplicationLayerHeader = value;
	}
	
	public ReportIdentification.Body getBody()
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
		
		size += m_JAUSApplicationLayerHeader.getSize();
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
		
		m_JAUSApplicationLayerHeader.encode(bytes, pos);
		pos += m_JAUSApplicationLayerHeader.getSize();
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
		
		m_JAUSApplicationLayerHeader.decode(bytes, pos);
		pos += m_JAUSApplicationLayerHeader.getSize();
		m_Body.decode(bytes, pos);
		pos += m_Body.getSize();
	}
	
	public ReportIdentification setAs(ReportIdentification value)
	{
		m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(ReportIdentification value)
	{
		if (!m_JAUSApplicationLayerHeader.isEqual(value.getJAUSApplicationLayerHeader()))
		{
			return false;
		}
		if (!m_Body.isEqual(value.getBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public boolean notEquals(ReportIdentification value)
	{
		return !isEqual(value);
	}
	
	public ReportIdentification()
	{
		m_JAUSApplicationLayerHeader = new JAUSApplicationLayerHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  ReportIdentification(ReportIdentification value)
	{
		/// Initiliaze the protected variables
		m_JAUSApplicationLayerHeader = new JAUSApplicationLayerHeader();
		m_Body = new Body();
		m_IsCommand = false;
		
		/// Copy the values
		m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
		m_Body = value.m_Body;
	}
	
}
