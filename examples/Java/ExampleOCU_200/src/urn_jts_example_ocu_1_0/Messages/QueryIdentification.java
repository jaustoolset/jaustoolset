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

public class QueryIdentification extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x2b00;
	
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
			
			public QueryIdentification.JAUSApplicationLayerHeader.HeaderRec assign(HeaderRec value)
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
				m_MessageID = 0x2b00;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x2b00;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
		public QueryIdentification.JAUSApplicationLayerHeader.HeaderRec getHeaderRec()
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
		
		public QueryIdentification.JAUSApplicationLayerHeader assign(JAUSApplicationLayerHeader value)
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
		public static class  QueryIdentificationRec
		{
		
			protected Body m_parent;
			protected short m_QueryType;
		
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
				
				bytes.put(pos, (byte) m_QueryType);
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
				
				m_QueryType = (short) (bytes.get(pos) & 0xff);
				pos += JausUtils.getNumBytes("byte");
			}
			
			public QueryIdentification.Body.QueryIdentificationRec assign(QueryIdentificationRec value)
			{
				m_QueryType = value.m_QueryType;
				
				return this;
			}
			
			public boolean isEqual(QueryIdentificationRec value)
			{
				if (m_QueryType != value.getQueryType())
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(QueryIdentificationRec value)
			{
				return !this.isEqual(value);
			}
			
			public QueryIdentificationRec()
			{
				m_parent = null;
				m_QueryType = 0;
			}
			
			public QueryIdentificationRec(QueryIdentificationRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_QueryType = 0;
				
				/// Copy the values
				m_QueryType = value.m_QueryType;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected QueryIdentificationRec m_QueryIdentificationRec;
	
		public QueryIdentification.Body.QueryIdentificationRec getQueryIdentificationRec()
		{
			return m_QueryIdentificationRec;
		}
		
		public void setQueryIdentificationRec(QueryIdentificationRec value)
		{
			m_QueryIdentificationRec = value;
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
			
			size += m_QueryIdentificationRec.getSize();
			
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
			
			m_QueryIdentificationRec.encode(bytes, pos);
			pos += m_QueryIdentificationRec.getSize();
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
			
			m_QueryIdentificationRec.decode(bytes, pos);
			pos += m_QueryIdentificationRec.getSize();
		}
		
		public QueryIdentification.Body assign(Body value)
		{
			m_QueryIdentificationRec = value.m_QueryIdentificationRec;
			m_QueryIdentificationRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(Body value)
		{
			if (!m_QueryIdentificationRec.isEqual(value.getQueryIdentificationRec()))
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
			m_QueryIdentificationRec = new QueryIdentificationRec();
			m_QueryIdentificationRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_QueryIdentificationRec = new QueryIdentificationRec();
			m_QueryIdentificationRec.setParent(this);
			
			/// Copy the values
			m_QueryIdentificationRec = value.m_QueryIdentificationRec;
			m_QueryIdentificationRec.setParent(this);
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
	public QueryIdentification.JAUSApplicationLayerHeader getJAUSApplicationLayerHeader()
	{
		return m_JAUSApplicationLayerHeader;
	}
	
	public void setJAUSApplicationLayerHeader(JAUSApplicationLayerHeader value)
	{
		m_JAUSApplicationLayerHeader = value;
	}
	
	public QueryIdentification.Body getBody()
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
	
	public QueryIdentification setAs(QueryIdentification value)
	{
		m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(QueryIdentification value)
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
	
	public boolean notEquals(QueryIdentification value)
	{
		return !isEqual(value);
	}
	
	public QueryIdentification()
	{
		m_JAUSApplicationLayerHeader = new JAUSApplicationLayerHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  QueryIdentification(QueryIdentification value)
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
