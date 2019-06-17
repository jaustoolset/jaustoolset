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

public class ReportHeartbeatPulse extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x4202;
	
	public static class  JTS_DefaultHeader
	{
		public static class  DefaultHeaderRec
		{
		
			protected JTS_DefaultHeader m_parent;
			protected int m_MessageID;
		
			public void setParent(JTS_DefaultHeader parent)
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
			
			public ReportHeartbeatPulse.JTS_DefaultHeader.DefaultHeaderRec assign(DefaultHeaderRec value)
			{
				m_MessageID = value.m_MessageID;
				
				return this;
			}
			
			public boolean isEqual(DefaultHeaderRec value)
			{
				if (m_MessageID != value.getMessageID())
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(DefaultHeaderRec value)
			{
				return !this.isEqual(value);
			}
			
			public DefaultHeaderRec()
			{
				m_parent = null;
				m_MessageID = 0x4202;
			}
			
			public DefaultHeaderRec(DefaultHeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x4202;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected DefaultHeaderRec m_DefaultHeaderRec;
	
		public ReportHeartbeatPulse.JTS_DefaultHeader.DefaultHeaderRec getDefaultHeaderRec()
		{
			return m_DefaultHeaderRec;
		}
		
		public void setDefaultHeaderRec(DefaultHeaderRec value)
		{
			m_DefaultHeaderRec = value;
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
			
			size += m_DefaultHeaderRec.getSize();
			
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
			
			m_DefaultHeaderRec.encode(bytes, pos);
			pos += m_DefaultHeaderRec.getSize();
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
			
			m_DefaultHeaderRec.decode(bytes, pos);
			pos += m_DefaultHeaderRec.getSize();
		}
		
		public ReportHeartbeatPulse.JTS_DefaultHeader assign(JTS_DefaultHeader value)
		{
			m_DefaultHeaderRec = value.m_DefaultHeaderRec;
			m_DefaultHeaderRec.setParent(this);
			
			return this;
		}
		
		public boolean isEqual(JTS_DefaultHeader value)
		{
			if (!m_DefaultHeaderRec.isEqual(value.getDefaultHeaderRec()))
			{
				return false;
			}
			return true;
		}
		
		public boolean notEquals(JTS_DefaultHeader value)
		{
			return !this.isEqual(value);
		}
		
		public JTS_DefaultHeader()
		{
			m_DefaultHeaderRec = new DefaultHeaderRec();
			m_DefaultHeaderRec.setParent(this);
		}
		
		public JTS_DefaultHeader(JTS_DefaultHeader value)
		{
			/// Initiliaze the protected variables
			m_DefaultHeaderRec = new DefaultHeaderRec();
			m_DefaultHeaderRec.setParent(this);
			
			/// Copy the values
			m_DefaultHeaderRec = value.m_DefaultHeaderRec;
			m_DefaultHeaderRec.setParent(this);
		}
		
		public void finalize()
		{
		}
		
	}
	protected JTS_DefaultHeader m_JTS_DefaultHeader;
	public long getID()
	{
	return ID;
 }
	public ReportHeartbeatPulse.JTS_DefaultHeader getJTS_DefaultHeader()
	{
		return m_JTS_DefaultHeader;
	}
	
	public void setJTS_DefaultHeader(JTS_DefaultHeader value)
	{
		m_JTS_DefaultHeader = value;
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
		
		size += m_JTS_DefaultHeader.getSize();
		
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
		
		m_JTS_DefaultHeader.encode(bytes, pos);
		pos += m_JTS_DefaultHeader.getSize();
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
		
		m_JTS_DefaultHeader.decode(bytes, pos);
		pos += m_JTS_DefaultHeader.getSize();
	}
	
	public ReportHeartbeatPulse setAs(ReportHeartbeatPulse value)
	{
		m_JTS_DefaultHeader = value.m_JTS_DefaultHeader;
		
		return this;
	}
	
	public boolean isEqual(ReportHeartbeatPulse value)
	{
		if (!m_JTS_DefaultHeader.isEqual(value.getJTS_DefaultHeader()))
		{
			return false;
		}
		
		return true;
	}
	
	public boolean notEquals(ReportHeartbeatPulse value)
	{
		return !isEqual(value);
	}
	
	public ReportHeartbeatPulse()
	{
		m_JTS_DefaultHeader = new JTS_DefaultHeader();
		m_IsCommand = false;
	}
	
	public  ReportHeartbeatPulse(ReportHeartbeatPulse value)
	{
		/// Initiliaze the protected variables
		m_JTS_DefaultHeader = new JTS_DefaultHeader();
		m_IsCommand = false;
		
		/// Copy the values
		m_JTS_DefaultHeader = value.m_JTS_DefaultHeader;
	}
	
}
