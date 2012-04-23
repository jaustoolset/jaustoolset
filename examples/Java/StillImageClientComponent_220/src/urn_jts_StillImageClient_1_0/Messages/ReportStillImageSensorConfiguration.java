package src.urn_jts_StillImageClient_1_0.Messages;

import framework.messages.Message;
import framework.JausUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportStillImageSensorConfiguration extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x4813;
	
	public static class  AppHeader
	{
		public static class  HeaderRec
		{
		
			protected AppHeader m_parent;
			protected int m_MessageID;
		
			public void setParent(AppHeader parent)
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
			
			public ReportStillImageSensorConfiguration.AppHeader.HeaderRec assign(HeaderRec value)
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
				m_MessageID = 0x4813;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x4813;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
		public ReportStillImageSensorConfiguration.AppHeader.HeaderRec getHeaderRec()
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
		
		public ReportStillImageSensorConfiguration.AppHeader assign(AppHeader value)
		{
			m_HeaderRec = value.m_HeaderRec;
			m_HeaderRec.setParent(this);
			
			return this;
		}
		
		public boolean isEqual(AppHeader value)
		{
			if (!m_HeaderRec.isEqual(value.getHeaderRec()))
			{
				return false;
			}
			return true;
		}
		
		public boolean notEquals(AppHeader value)
		{
			return !this.isEqual(value);
		}
		
		public AppHeader()
		{
			m_HeaderRec = new HeaderRec();
			m_HeaderRec.setParent(this);
		}
		
		public AppHeader(AppHeader value)
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
		public static class  StillImageSensorConfigurationList
		{
			public static class  StillImageSensorConfigurationRec
			{
			
				protected StillImageSensorConfigurationList m_parent;
				protected BitSet m_PresenceVector;
				protected short m_PresenceVectorTemp;
				protected int m_SensorID;
				protected short m_FrameSize;
				protected short m_StillImageFormat;
			
				public void setParent(StillImageSensorConfigurationList parent)
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
				
				public int getSensorID()
				{
					return m_SensorID;
				}
				
				public void setSensorID(int value)
				{
					m_SensorID = value;
					setParentPresenceVector();
				}
				
				public boolean isFrameSizeValid()
				{
					if (checkPresenceVector(0))
					{
						return true;
					}
					return false;
				}
				
				public short getFrameSize()
				{
					return m_FrameSize;
				}
				
				public void setFrameSize(short value)
				{
					if ((value == 0) || (value == 1) || (value == 2) || (value == 3) || (value == 4) || (value == 5) || (value == 6) || (value == 7) || (value == 8) || (value == 9) || (value == 10) || (value == 11) || (value == 12) || (value == 13) || (value == 14) || (value == 15) || (value == 16) || (value == 17) || (value == 18) || (value == 19) || (value == 20) || (value == 21) || (value == 22) || (value == 23) || (value == 24) || (value == 25) || (value == 26) || (value == 27) || (value == 28))
					{
						m_FrameSize = value;
						setPresenceVector(0);
						setParentPresenceVector();
					}
					return;
				}
				
				public boolean isStillImageFormatValid()
				{
					if (checkPresenceVector(1))
					{
						return true;
					}
					return false;
				}
				
				public short getStillImageFormat()
				{
					return m_StillImageFormat;
				}
				
				public void setStillImageFormat(short value)
				{
					if ((value == 0) || (value == 1) || (value == 2) || (value == 3) || (value == 4) || (value == 5) || (value == 6) || (value == 7) || (value == 8) || (value == 9) || (value == 10))
					{
						m_StillImageFormat = value;
						setPresenceVector(1);
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
					size += JausUtils.getNumBytes("short");
					if (checkPresenceVector(0))
					{
						size += JausUtils.getNumBytes("byte");
					}
					if (checkPresenceVector(1))
					{
						size += JausUtils.getNumBytes("byte");
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
					bytes.putShort(pos, (short) m_SensorID);
					pos += JausUtils.getNumBytes("short");
					if (checkPresenceVector(0))
					{
						bytes.put(pos, (byte) m_FrameSize);
						pos += JausUtils.getNumBytes("byte");
					}
					if (checkPresenceVector(1))
					{
						bytes.put(pos, (byte) m_StillImageFormat);
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
						short m_PresenceVectorTemp = 0;
					m_PresenceVectorTemp = (short) (bytes.get(pos) & 0xff);
					pos += JausUtils.getNumBytes("byte");
					m_PresenceVector = JausUtils.setPV(m_PresenceVectorTemp);
					}
					catch(Exception e)
					{
						logger.log(Level.SEVERE, null, e);
					}
					m_SensorID = bytes.getShort(pos) & 0xffff;
					pos += JausUtils.getNumBytes("short");
					if (checkPresenceVector(0))
					{
						m_FrameSize = (short) (bytes.get(pos) & 0xff);
						pos += JausUtils.getNumBytes("byte");
					}
					if (checkPresenceVector(1))
					{
						m_StillImageFormat = (short) (bytes.get(pos) & 0xff);
						pos += JausUtils.getNumBytes("byte");
					}
				}
				
				public ReportStillImageSensorConfiguration.Body.StillImageSensorConfigurationList.StillImageSensorConfigurationRec assign(StillImageSensorConfigurationRec value)
				{
					m_PresenceVector = value.m_PresenceVector;
					m_SensorID = value.m_SensorID;
					m_FrameSize = value.m_FrameSize;
					m_StillImageFormat = value.m_StillImageFormat;
					
					return this;
				}
				
				public boolean isEqual(StillImageSensorConfigurationRec value)
				{
					if (!m_PresenceVector.equals(value.m_PresenceVector))
					{
						return false;
					}
					if (m_SensorID != value.getSensorID())
					{
						return false;
					}
					if (m_FrameSize != value.getFrameSize())
					{
						return false;
					}
					if (m_StillImageFormat != value.getStillImageFormat())
					{
						return false;
					}
					
					return true;
				}
				
				public boolean notEquals(StillImageSensorConfigurationRec value)
				{
					return !this.isEqual(value);
				}
				
				public StillImageSensorConfigurationRec()
				{
					m_parent = null;
					m_PresenceVector = new BitSet();
					m_PresenceVectorTemp = 0;
					m_SensorID = 0;
					m_FrameSize = 0;
					m_StillImageFormat = 0;
				}
				
				public StillImageSensorConfigurationRec(StillImageSensorConfigurationRec value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_PresenceVector = new BitSet();
					m_PresenceVectorTemp = 0;
					m_SensorID = 0;
					m_FrameSize = 0;
					m_StillImageFormat = 0;
					
					/// Copy the values
					m_PresenceVector = value.m_PresenceVector;
					m_SensorID = value.m_SensorID;
					m_FrameSize = value.m_FrameSize;
					m_StillImageFormat = value.m_StillImageFormat;
				}
				
				public void finalize()
				{
				}
				
			}
		
		
			protected Body m_parent;
			protected ArrayList<StillImageSensorConfigurationRec> m_StillImageSensorConfigurationRec;
		
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
			
			public long getNumberOfElements()
			{
				return (long) m_StillImageSensorConfigurationRec.size();
			}
			
			public ReportStillImageSensorConfiguration.Body.StillImageSensorConfigurationList.StillImageSensorConfigurationRec getElement(int index)
			{
				return m_StillImageSensorConfigurationRec.get(index);
			}
			
			public void setElement(int index, StillImageSensorConfigurationRec value)
			{
				if(m_StillImageSensorConfigurationRec.size()-1 < index)
				{
					return;
				}
				
				m_StillImageSensorConfigurationRec.set(index, value);
				m_StillImageSensorConfigurationRec.get(index).setParent(this);
				setParentPresenceVector();
			}
			
			public void addElement(StillImageSensorConfigurationRec value)
			{
				m_StillImageSensorConfigurationRec.add(value);
				m_StillImageSensorConfigurationRec.get(m_StillImageSensorConfigurationRec.size() -1 ).setParent(this);
				setParentPresenceVector();
			}
			
			public int deleteElement(int index)
			{
				if(m_StillImageSensorConfigurationRec.size()-1 < index)
				{
					return 1;
				}
				
				m_StillImageSensorConfigurationRec.remove(index);
				return 0;
			}
			
			public int deleteLastElement()
			{
				m_StillImageSensorConfigurationRec.remove(m_StillImageSensorConfigurationRec.size()-1);
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
				
				size += JausUtils.getNumBytes("short");
				for (int i = 0; i < m_StillImageSensorConfigurationRec.size(); i++)
				{
					size += m_StillImageSensorConfigurationRec.get(i).getSize();
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
				
				int size = (int) m_StillImageSensorConfigurationRec.size();
				bytes.putShort(pos, (short) size);
				pos += JausUtils.getNumBytes("short");
				for (int i = 0; i < m_StillImageSensorConfigurationRec.size(); i++)
				{
					m_StillImageSensorConfigurationRec.get(i).encode(bytes, pos);
					pos += m_StillImageSensorConfigurationRec.get(i).getSize();
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
				
				int size;
				size = bytes.getShort(pos) & 0xffff;
				pos += JausUtils.getNumBytes("short");
				m_StillImageSensorConfigurationRec = new ArrayList<StillImageSensorConfigurationRec>();
				for (int i = 0; i <  size; i++)
				{
					StillImageSensorConfigurationRec item = new StillImageSensorConfigurationRec();
					item.decode(bytes, pos);
					m_StillImageSensorConfigurationRec.add(item);
					pos += item.getSize();
				}
			}
			
			public ReportStillImageSensorConfiguration.Body.StillImageSensorConfigurationList assign(StillImageSensorConfigurationList value)
			{
				m_StillImageSensorConfigurationRec.clear();
				
				for (int i = 0; i < value.m_StillImageSensorConfigurationRec.size(); i++)
				{
					m_StillImageSensorConfigurationRec.add(value.m_StillImageSensorConfigurationRec.get(i));
					m_StillImageSensorConfigurationRec.get(i).setParent(this);
				}
				
				return this;
			}
			
			public boolean isEqual(StillImageSensorConfigurationList value)
			{
				for (int i = 0; i < m_StillImageSensorConfigurationRec.size(); i++)
				{
					if (!m_StillImageSensorConfigurationRec.get(i).isEqual(value.getElement(i)))
					{
						return false;
					}
				}
				
				return true;
			}
			
			public boolean notEquals(StillImageSensorConfigurationList value)
			{
				return !this.isEqual(value);
			}
			
			public StillImageSensorConfigurationList()
			{
				m_parent = null;
				m_StillImageSensorConfigurationRec = new ArrayList<StillImageSensorConfigurationRec>();
				for (int i = 0; i < m_StillImageSensorConfigurationRec.size(); i++)
				{
					m_StillImageSensorConfigurationRec.get(i).setParent(this);
				}
			}
			
			public StillImageSensorConfigurationList(StillImageSensorConfigurationList value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_StillImageSensorConfigurationRec = new ArrayList<StillImageSensorConfigurationRec>();
				for (int i = 0; i < m_StillImageSensorConfigurationRec.size(); i++)
				{
					m_StillImageSensorConfigurationRec.get(i).setParent(this);
				}
				
				/// Copy the values
				m_StillImageSensorConfigurationRec.clear();
				
				for (int i = 0; i < value.m_StillImageSensorConfigurationRec.size(); i++)
				{
					m_StillImageSensorConfigurationRec.add(value.m_StillImageSensorConfigurationRec.get(i));
					m_StillImageSensorConfigurationRec.get(i).setParent(this);
				}
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected StillImageSensorConfigurationList m_StillImageSensorConfigurationList;
	
		public ReportStillImageSensorConfiguration.Body.StillImageSensorConfigurationList getStillImageSensorConfigurationList()
		{
			return m_StillImageSensorConfigurationList;
		}
		
		public void setStillImageSensorConfigurationList(StillImageSensorConfigurationList value)
		{
			m_StillImageSensorConfigurationList = value;
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
			
			size += m_StillImageSensorConfigurationList.getSize();
			
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
			
			m_StillImageSensorConfigurationList.encode(bytes, pos);
			pos += m_StillImageSensorConfigurationList.getSize();
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
			
			m_StillImageSensorConfigurationList.decode(bytes, pos);
			pos += m_StillImageSensorConfigurationList.getSize();
		}
		
		public ReportStillImageSensorConfiguration.Body assign(Body value)
		{
			m_StillImageSensorConfigurationList = value.m_StillImageSensorConfigurationList;
			m_StillImageSensorConfigurationList.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(Body value)
		{
			if (!m_StillImageSensorConfigurationList.isEqual(value.m_StillImageSensorConfigurationList))
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
			m_StillImageSensorConfigurationList = new StillImageSensorConfigurationList();
			m_StillImageSensorConfigurationList.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_StillImageSensorConfigurationList = new StillImageSensorConfigurationList();
			m_StillImageSensorConfigurationList.setParent(this);
			
			/// Copy the values
			m_StillImageSensorConfigurationList = value.m_StillImageSensorConfigurationList;
			m_StillImageSensorConfigurationList.setParent(this);
			/// This code is currently not supported
		}
		
		public void finalize()
		{
		}
		
	}
	protected AppHeader m_AppHeader;
	protected Body m_Body;
	public long getID()
	{
	return ID;
 }
	public ReportStillImageSensorConfiguration.AppHeader getAppHeader()
	{
		return m_AppHeader;
	}
	
	public void setAppHeader(AppHeader value)
	{
		m_AppHeader = value;
	}
	
	public ReportStillImageSensorConfiguration.Body getBody()
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
		
		size += m_AppHeader.getSize();
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
		
		m_AppHeader.encode(bytes, pos);
		pos += m_AppHeader.getSize();
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
		
		m_AppHeader.decode(bytes, pos);
		pos += m_AppHeader.getSize();
		m_Body.decode(bytes, pos);
		pos += m_Body.getSize();
	}
	
	public ReportStillImageSensorConfiguration setAs(ReportStillImageSensorConfiguration value)
	{
		m_AppHeader = value.m_AppHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(ReportStillImageSensorConfiguration value)
	{
		if (!m_AppHeader.isEqual(value.getAppHeader()))
		{
			return false;
		}
		if (!m_Body.isEqual(value.getBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public boolean notEquals(ReportStillImageSensorConfiguration value)
	{
		return !isEqual(value);
	}
	
	public ReportStillImageSensorConfiguration()
	{
		m_AppHeader = new AppHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  ReportStillImageSensorConfiguration(ReportStillImageSensorConfiguration value)
	{
		/// Initiliaze the protected variables
		m_AppHeader = new AppHeader();
		m_Body = new Body();
		m_IsCommand = false;
		
		/// Copy the values
		m_AppHeader = value.m_AppHeader;
		m_Body = value.m_Body;
	}
	
}
