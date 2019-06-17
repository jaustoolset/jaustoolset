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

public class GrantNodeID extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0xfb03;
	
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
			
			public GrantNodeID.JAUSApplicationLayerHeader.HeaderRec assign(HeaderRec value)
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
				m_MessageID = 0xfb03;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0xfb03;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
		public GrantNodeID.JAUSApplicationLayerHeader.HeaderRec getHeaderRec()
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
		
		public GrantNodeID.JAUSApplicationLayerHeader assign(JAUSApplicationLayerHeader value)
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
		public static class  GrantNodeIDRec
		{
			public static class  RequesterID
			{
			
				protected GrantNodeIDRec m_parent;
				int m_OneDimensionArraySize;
				protected short[] m_RequesterIDArrayField = new short[7];
			
				public void setParent(GrantNodeIDRec parent)
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
				
				public int getOneDimensionArraySize()
				{
					return m_OneDimensionArraySize;
				}
				
				public short getRequesterIDArrayField(int OneDimensionArray)
				{
					int index = OneDimensionArray;
					
					return m_RequesterIDArrayField[index];
				}
				
				public void setRequesterIDArrayField(int OneDimensionArray, short value)
				{
					int index = OneDimensionArray;
					
					m_RequesterIDArrayField[index] = value;
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
					
					size += JausUtils.getNumBytes("byte") * 7;
					
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
					
					
					for (int i = 0; i < 7; i++)
					{
					bytes.put(pos, (byte) m_RequesterIDArrayField[i]);
					pos += JausUtils.getNumBytes("byte");
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
						for (int i = 0; i < 7; i++)
						{
					m_RequesterIDArrayField[i] = (short) (bytes.get(pos) & 0xff);
					pos += JausUtils.getNumBytes("byte");
						}
					}
					catch(Exception e)
					{
						logger.log(Level.SEVERE, null, e);
					}
				}
				
				public GrantNodeID.Body.GrantNodeIDRec.RequesterID assign(RequesterID value)
				{
					m_RequesterIDArrayField = value.m_RequesterIDArrayField;
					
					return this;
				}
				
				public boolean isEqual(RequesterID value)
				{
					if(!java.util.Arrays.equals(m_RequesterIDArrayField, value.m_RequesterIDArrayField))
					{
						return false;
					}
					
					return true;
				}
				
				public boolean notEquals(RequesterID value)
				{
					return !this.isEqual(value);
				}
				
				public RequesterID()
				{
					m_parent = null;
					m_OneDimensionArraySize = 7;
				}
				
				public RequesterID(RequesterID value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_OneDimensionArraySize = 7;
					
					/// Copy the values
					m_RequesterIDArrayField = value.m_RequesterIDArrayField;
				}
				
				public void finalize()
				{
				}
				
			}
		
		
			protected Body m_parent;
			protected short m_NodeID;
			protected RequesterID m_RequesterID;
		
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
			
			public short getNodeID()
			{
				return m_NodeID;
			}
			
			public void setNodeID(short value)
			{
				m_NodeID = value;
				setParentPresenceVector();
			}
			
			public GrantNodeID.Body.GrantNodeIDRec.RequesterID getRequesterID()
			{
				return m_RequesterID;
			}
			
			public void setRequesterID(RequesterID value)
			{
				m_RequesterID = value;
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
				size += m_RequesterID.getSize();
				
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
				
				bytes.put(pos, (byte) m_NodeID);
				pos += JausUtils.getNumBytes("byte");
				m_RequesterID.encode(bytes, pos);
				pos += m_RequesterID.getSize();
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
				
				m_NodeID = (short) (bytes.get(pos) & 0xff);
				pos += JausUtils.getNumBytes("byte");
				m_RequesterID.decode(bytes, pos);
				pos += m_RequesterID.getSize();
			}
			
			public GrantNodeID.Body.GrantNodeIDRec assign(GrantNodeIDRec value)
			{
				m_NodeID = value.m_NodeID;
				m_RequesterID = value.m_RequesterID;
				
				return this;
			}
			
			public boolean isEqual(GrantNodeIDRec value)
			{
				if (m_NodeID != value.getNodeID())
				{
					return false;
				}
				
				if (!m_RequesterID.isEqual(value.getRequesterID()))
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(GrantNodeIDRec value)
			{
				return !this.isEqual(value);
			}
			
			public GrantNodeIDRec()
			{
				m_parent = null;
				m_NodeID = 0;
				m_RequesterID = new RequesterID();
				m_RequesterID.setParent(this);
			}
			
			public GrantNodeIDRec(GrantNodeIDRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_NodeID = 0;
				m_RequesterID = new RequesterID();
				m_RequesterID.setParent(this);
				
				/// Copy the values
				m_NodeID = value.m_NodeID;
				m_RequesterID = value.m_RequesterID;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected GrantNodeIDRec m_GrantNodeIDRec;
	
		public GrantNodeID.Body.GrantNodeIDRec getGrantNodeIDRec()
		{
			return m_GrantNodeIDRec;
		}
		
		public void setGrantNodeIDRec(GrantNodeIDRec value)
		{
			m_GrantNodeIDRec = value;
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
			
			size += m_GrantNodeIDRec.getSize();
			
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
			
			m_GrantNodeIDRec.encode(bytes, pos);
			pos += m_GrantNodeIDRec.getSize();
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
			
			m_GrantNodeIDRec.decode(bytes, pos);
			pos += m_GrantNodeIDRec.getSize();
		}
		
		public GrantNodeID.Body assign(Body value)
		{
			m_GrantNodeIDRec = value.m_GrantNodeIDRec;
			m_GrantNodeIDRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(Body value)
		{
			if (!m_GrantNodeIDRec.isEqual(value.getGrantNodeIDRec()))
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
			m_GrantNodeIDRec = new GrantNodeIDRec();
			m_GrantNodeIDRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_GrantNodeIDRec = new GrantNodeIDRec();
			m_GrantNodeIDRec.setParent(this);
			
			/// Copy the values
			m_GrantNodeIDRec = value.m_GrantNodeIDRec;
			m_GrantNodeIDRec.setParent(this);
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
	public GrantNodeID.JAUSApplicationLayerHeader getJAUSApplicationLayerHeader()
	{
		return m_JAUSApplicationLayerHeader;
	}
	
	public void setJAUSApplicationLayerHeader(JAUSApplicationLayerHeader value)
	{
		m_JAUSApplicationLayerHeader = value;
	}
	
	public GrantNodeID.Body getBody()
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
	
	public GrantNodeID setAs(GrantNodeID value)
	{
		m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(GrantNodeID value)
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
	
	public boolean notEquals(GrantNodeID value)
	{
		return !isEqual(value);
	}
	
	public GrantNodeID()
	{
		m_JAUSApplicationLayerHeader = new JAUSApplicationLayerHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  GrantNodeID(GrantNodeID value)
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
