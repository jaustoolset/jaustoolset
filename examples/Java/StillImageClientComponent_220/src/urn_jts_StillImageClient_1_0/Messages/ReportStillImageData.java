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

public class ReportStillImageData extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x4814;
	
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
			
			public ReportStillImageData.AppHeader.HeaderRec assign(HeaderRec value)
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
				m_MessageID = 0x4814;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x4814;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
		public ReportStillImageData.AppHeader.HeaderRec getHeaderRec()
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
		
		public ReportStillImageData.AppHeader assign(AppHeader value)
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
		public static class  StillImageDataList
		{
			public static class  StillImageDataRec
			{
				public static class  TimeStamp
				{
				
					protected StillImageDataRec m_parent;
					protected long m_SubFields;
				
					public void setParent(StillImageDataRec parent)
					{
						m_parent = parent;
					}
					
					public void setParentPresenceVector()
					{
						if(m_parent != null )
						{
							m_parent.setPresenceVector(0);
							m_parent.setParentPresenceVector();
						}
					}
					
					public long getMilliseconds()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(10);
						int i = 0;
						
						for (int index = 0; index <= 9; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setMilliseconds(long value)
					{
						if (((value >= 0) && (value <= 999)))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(10);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 0; index <= 9; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getSeconds()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(6);
						int i = 0;
						
						for (int index = 10; index <= 15; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setSeconds(long value)
					{
						if (((value >= 0) && (value <= 59)))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(6);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 10; index <= 15; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getMinutes()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(6);
						int i = 0;
						
						for (int index = 16; index <= 21; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setMinutes(long value)
					{
						if (((value >= 0) && (value <= 59)))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(6);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 16; index <= 21; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getHour()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(5);
						int i = 0;
						
						for (int index = 22; index <= 26; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setHour(long value)
					{
						if (((value >= 0) && (value <= 23)))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(5);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 22; index <= 26; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getDay()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(5);
						int i = 0;
						
						for (int index = 27; index <= 31; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setDay(long value)
					{
						if (((value >= 1) && (value <= 31)))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(5);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 27; index <= 31; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
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
						
						size += JausUtils.getNumBytes("int");
						
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
						
						
						bytes.putInt(pos, (int) m_SubFields);
						pos += JausUtils.getNumBytes("int");
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
						
						m_SubFields = bytes.getInt(pos) & 0xffffffffL;
						pos += JausUtils.getNumBytes("int");
					}
					
					public ReportStillImageData.Body.StillImageDataList.StillImageDataRec.TimeStamp assign(TimeStamp value)
					{
						this.m_SubFields = value.m_SubFields;
						
						return this;
					}
					
					public boolean isEqual(TimeStamp value)
					{
						return this.m_SubFields == value.getDay();
					}
					
					public boolean notEquals(TimeStamp value)
					{
						return !this.isEqual(value);
					}
					
					public TimeStamp()
					{
						m_parent = null;
						m_SubFields = 0;
					}
					
					public TimeStamp(TimeStamp value)
					{
						/// Initiliaze the protected variables
						m_parent = null;
						m_SubFields = 0;
						
						/// Copy the values
						this.m_SubFields = value.m_SubFields;
					}
					
					public void finalize()
					{
					}
					
				}
				public static class  ImageFrame
				{
				
					protected StillImageDataRec m_parent;
					protected short m_Format;
					protected long m_Length;
					protected ByteBuffer m_Data;
				
					public void setParent(StillImageDataRec parent)
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
					
					public short getFormat()
					{
						return m_Format;
					}
					
					public long getLength()
					{
						return m_Length;
					}
					
					public ByteBuffer getData()
					{
						m_Data.rewind();
						ByteBuffer ret = ByteBuffer.allocate(m_Data.array().length);
						ret.order(ByteOrder.LITTLE_ENDIAN);
						ret.put(m_Data);
						return ret;
					}
					
					public void set(short format, long length, ByteBuffer data)
					{
						if ((format == 0)||(format == 1)||(format == 2)||(format == 3)||(format == 4)||(format == 5)||(format == 6)||(format == 7)||(format == 8)||(format == 9)||(format == 10))
						{
							m_Format = format;
							m_Length = length;
						
							m_Data.clear();
						
							data.rewind();
							m_Data = ByteBuffer.allocate(data.array().length);
							m_Data.order(ByteOrder.LITTLE_ENDIAN);
							m_Data.put(data);
						
						setParentPresenceVector();
						
							return;
						}
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
						size += JausUtils.getNumBytes("int");
						size += m_Length;
						
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
						
						bytes.put(pos, (byte) m_Format);
						pos += JausUtils.getNumBytes("byte");
						
						bytes.putInt(pos, (int) m_Length);
						pos += JausUtils.getNumBytes("int");
						
						byte[] dataBytes = m_Data.array();
						
						for(int i = 0; i<(int) m_Length; i++)
						{
							bytes.put(pos, dataBytes[i]);
							pos++;
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
						
						m_Format = (short) (bytes.get(pos) & 0xff);
						pos += JausUtils.getNumBytes("byte");
						
						m_Length = bytes.getInt(pos) & 0xffffffffL;
						pos += JausUtils.getNumBytes("int");
						
						byte[] dataBytes = new byte[(int)m_Length];
						
						int i;
						for(i=0; i<(int)m_Length; i++)
						{
							dataBytes[i] = bytes.get(pos);
							pos++;
						}
						
						m_Data = ByteBuffer.allocate((int)m_Length).order(ByteOrder.LITTLE_ENDIAN);
						m_Data.put(dataBytes);
					}
					
					public ReportStillImageData.Body.StillImageDataList.StillImageDataRec.ImageFrame assign(ImageFrame value)
					{
						this.m_Format = value.m_Format;
						this.m_Length = value.m_Length;
						
						m_Data.clear();
						
						if (m_Length > 0)
						{
						this.m_Data = ByteBuffer.wrap(value.m_Data.array()).order(ByteOrder.LITTLE_ENDIAN);
						}
						
						return this;
					}
					
					public boolean isEqual(ImageFrame value)
					{
						if (this.m_Format != value.m_Format)
						{
							return false;
						}
						
						if (this.m_Length != value.m_Length)
						{
							return false;
						}
						
						if ((this.m_Data.array() != null) && (value.m_Data.array() != null))
						{
							if(!Arrays.equals(this.m_Data.array(), value.m_Data.array()))
							{
								return false;
							}
						}
						// This case should never be true since it should not be possible
						// for the two variables to have equal lengths but one has no data.
						// This check is placed here as a secondary check.
						else if ((this.m_Data.array() != null) || (value.m_Data.array() != null))
						{
							return false;
						}
						
						return true;
					}
					
					public boolean notEquals(ImageFrame value)
					{
						return !this.isEqual(value);
					}
					
					public ImageFrame()
					{
						m_parent = null;
						m_Length = MAX_JTS_MESSAGE_SIZE;
						m_Data = ByteBuffer.allocate((int)m_Length);
						m_Data.order(ByteOrder.LITTLE_ENDIAN);
						m_Format = 0;
					}
					
					public ImageFrame(ImageFrame value)
					{
						/// Initiliaze the protected variables
						m_parent = null;
						m_Length = MAX_JTS_MESSAGE_SIZE;
						m_Data = ByteBuffer.allocate((int)m_Length);
						m_Data.order(ByteOrder.LITTLE_ENDIAN);
						m_Format = 0;
						
						/// Copy the values
						this.m_Format = value.m_Format;
						this.m_Length = value.m_Length;
						
						m_Data.clear();
						
						if (m_Length > 0)
						{
						this.m_Data = ByteBuffer.wrap(value.m_Data.array()).order(ByteOrder.LITTLE_ENDIAN);
						}
					}
					
					public void finalize()
					{
					}
					
				}
			
			
				protected StillImageDataList m_parent;
				protected BitSet m_PresenceVector;
				protected short m_PresenceVectorTemp;
				protected int m_SensorID;
				protected short m_ReportCoordinateSystem;
				protected TimeStamp m_TimeStamp;
				protected ImageFrame m_ImageFrame;
			
				public void setParent(StillImageDataList parent)
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
				
				public short getReportCoordinateSystem()
				{
					return m_ReportCoordinateSystem;
				}
				
				public void setReportCoordinateSystem(short value)
				{
					if ((value == 0) || (value == 1))
					{
						m_ReportCoordinateSystem = value;
						setParentPresenceVector();
					}
					return;
				}
				
				public boolean isTimeStampValid()
				{
					if (checkPresenceVector(0))
					{
						return true;
					}
					return false;
				}
				
				public ReportStillImageData.Body.StillImageDataList.StillImageDataRec.TimeStamp getTimeStamp()
				{
					return m_TimeStamp;
				}
				
				public void setTimeStamp(TimeStamp value)
				{
					m_TimeStamp = value;
					setPresenceVector(0);
					setParentPresenceVector();
				}
				
				public ReportStillImageData.Body.StillImageDataList.StillImageDataRec.ImageFrame getImageFrame()
				{
					return m_ImageFrame;
				}
				
				public int setImageFrame(ImageFrame value)
				{
					m_ImageFrame = value;
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
					size += JausUtils.getNumBytes("short");
					size += JausUtils.getNumBytes("byte");
					if (checkPresenceVector(0))
					{
						size += m_TimeStamp.getSize();
					}
					size += m_ImageFrame.getSize();
					
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
					bytes.put(pos, (byte) m_ReportCoordinateSystem);
					pos += JausUtils.getNumBytes("byte");
					if (checkPresenceVector(0))
					{
						m_TimeStamp.encode(bytes, pos);
						pos += m_TimeStamp.getSize();
					}
					m_ImageFrame.encode(bytes, pos);
					pos += m_ImageFrame.getSize();
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
					m_ReportCoordinateSystem = (short) (bytes.get(pos) & 0xff);
					pos += JausUtils.getNumBytes("byte");
					if (checkPresenceVector(0))
					{
						m_TimeStamp.decode(bytes, pos);
						pos += m_TimeStamp.getSize();
					}
					m_ImageFrame.decode(bytes, pos);
					pos += m_ImageFrame.getSize();
				}
				
				public ReportStillImageData.Body.StillImageDataList.StillImageDataRec assign(StillImageDataRec value)
				{
					m_PresenceVector = value.m_PresenceVector;
					m_SensorID = value.m_SensorID;
					m_ReportCoordinateSystem = value.m_ReportCoordinateSystem;
					m_TimeStamp = value.m_TimeStamp;
					m_ImageFrame = value.m_ImageFrame;
					
					return this;
				}
				
				public boolean isEqual(StillImageDataRec value)
				{
					if (!m_PresenceVector.equals(value.m_PresenceVector))
					{
						return false;
					}
					if (m_SensorID != value.getSensorID())
					{
						return false;
					}
					if (m_ReportCoordinateSystem != value.getReportCoordinateSystem())
					{
						return false;
					}
					
					if (!m_TimeStamp.isEqual(value.getTimeStamp()))
					{
						return false;
					}
					
					if (!m_ImageFrame.isEqual(value.getImageFrame()))
					{
						return false;
					}
					
					return true;
				}
				
				public boolean notEquals(StillImageDataRec value)
				{
					return !this.isEqual(value);
				}
				
				public StillImageDataRec()
				{
					m_parent = null;
					m_PresenceVector = new BitSet();
					m_PresenceVectorTemp = 0;
					m_SensorID = 0;
					m_ReportCoordinateSystem = 0;
					m_TimeStamp = new TimeStamp();
					m_TimeStamp.setParent(this);
					m_ImageFrame = new ImageFrame();
					m_ImageFrame.setParent(this);
				}
				
				public StillImageDataRec(StillImageDataRec value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_PresenceVector = new BitSet();
					m_PresenceVectorTemp = 0;
					m_SensorID = 0;
					m_ReportCoordinateSystem = 0;
					m_TimeStamp = new TimeStamp();
					m_TimeStamp.setParent(this);
					m_ImageFrame = new ImageFrame();
					m_ImageFrame.setParent(this);
					
					/// Copy the values
					m_PresenceVector = value.m_PresenceVector;
					m_SensorID = value.m_SensorID;
					m_ReportCoordinateSystem = value.m_ReportCoordinateSystem;
					m_TimeStamp = value.m_TimeStamp;
					m_ImageFrame = value.m_ImageFrame;
				}
				
				public void finalize()
				{
				}
				
			}
		
		
			protected Body m_parent;
			protected ArrayList<StillImageDataRec> m_StillImageDataRec;
		
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
				return (long) m_StillImageDataRec.size();
			}
			
			public ReportStillImageData.Body.StillImageDataList.StillImageDataRec getElement(int index)
			{
				return m_StillImageDataRec.get(index);
			}
			
			public void setElement(int index, StillImageDataRec value)
			{
				if(m_StillImageDataRec.size()-1 < index)
				{
					return;
				}
				
				m_StillImageDataRec.set(index, value);
				m_StillImageDataRec.get(index).setParent(this);
				setParentPresenceVector();
			}
			
			public void addElement(StillImageDataRec value)
			{
				m_StillImageDataRec.add(value);
				m_StillImageDataRec.get(m_StillImageDataRec.size() -1 ).setParent(this);
				setParentPresenceVector();
			}
			
			public int deleteElement(int index)
			{
				if(m_StillImageDataRec.size()-1 < index)
				{
					return 1;
				}
				
				m_StillImageDataRec.remove(index);
				return 0;
			}
			
			public int deleteLastElement()
			{
				m_StillImageDataRec.remove(m_StillImageDataRec.size()-1);
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
				for (int i = 0; i < m_StillImageDataRec.size(); i++)
				{
					size += m_StillImageDataRec.get(i).getSize();
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
				
				int size = (int) m_StillImageDataRec.size();
				bytes.putShort(pos, (short) size);
				pos += JausUtils.getNumBytes("short");
				for (int i = 0; i < m_StillImageDataRec.size(); i++)
				{
					m_StillImageDataRec.get(i).encode(bytes, pos);
					pos += m_StillImageDataRec.get(i).getSize();
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
				m_StillImageDataRec = new ArrayList<StillImageDataRec>();
				for (int i = 0; i <  size; i++)
				{
					StillImageDataRec item = new StillImageDataRec();
					item.decode(bytes, pos);
					m_StillImageDataRec.add(item);
					pos += item.getSize();
				}
			}
			
			public ReportStillImageData.Body.StillImageDataList assign(StillImageDataList value)
			{
				m_StillImageDataRec.clear();
				
				for (int i = 0; i < value.m_StillImageDataRec.size(); i++)
				{
					m_StillImageDataRec.add(value.m_StillImageDataRec.get(i));
					m_StillImageDataRec.get(i).setParent(this);
				}
				
				return this;
			}
			
			public boolean isEqual(StillImageDataList value)
			{
				for (int i = 0; i < m_StillImageDataRec.size(); i++)
				{
					if (!m_StillImageDataRec.get(i).isEqual(value.getElement(i)))
					{
						return false;
					}
				}
				
				return true;
			}
			
			public boolean notEquals(StillImageDataList value)
			{
				return !this.isEqual(value);
			}
			
			public StillImageDataList()
			{
				m_parent = null;
				m_StillImageDataRec = new ArrayList<StillImageDataRec>();
				for (int i = 0; i < m_StillImageDataRec.size(); i++)
				{
					m_StillImageDataRec.get(i).setParent(this);
				}
			}
			
			public StillImageDataList(StillImageDataList value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_StillImageDataRec = new ArrayList<StillImageDataRec>();
				for (int i = 0; i < m_StillImageDataRec.size(); i++)
				{
					m_StillImageDataRec.get(i).setParent(this);
				}
				
				/// Copy the values
				m_StillImageDataRec.clear();
				
				for (int i = 0; i < value.m_StillImageDataRec.size(); i++)
				{
					m_StillImageDataRec.add(value.m_StillImageDataRec.get(i));
					m_StillImageDataRec.get(i).setParent(this);
				}
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected StillImageDataList m_StillImageDataList;
	
		public ReportStillImageData.Body.StillImageDataList getStillImageDataList()
		{
			return m_StillImageDataList;
		}
		
		public void setStillImageDataList(StillImageDataList value)
		{
			m_StillImageDataList = value;
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
			
			size += m_StillImageDataList.getSize();
			
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
			
			m_StillImageDataList.encode(bytes, pos);
			pos += m_StillImageDataList.getSize();
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
			
			m_StillImageDataList.decode(bytes, pos);
			pos += m_StillImageDataList.getSize();
		}
		
		public ReportStillImageData.Body assign(Body value)
		{
			m_StillImageDataList = value.m_StillImageDataList;
			m_StillImageDataList.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(Body value)
		{
			if (!m_StillImageDataList.isEqual(value.m_StillImageDataList))
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
			m_StillImageDataList = new StillImageDataList();
			m_StillImageDataList.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_StillImageDataList = new StillImageDataList();
			m_StillImageDataList.setParent(this);
			
			/// Copy the values
			m_StillImageDataList = value.m_StillImageDataList;
			m_StillImageDataList.setParent(this);
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
	public ReportStillImageData.AppHeader getAppHeader()
	{
		return m_AppHeader;
	}
	
	public void setAppHeader(AppHeader value)
	{
		m_AppHeader = value;
	}
	
	public ReportStillImageData.Body getBody()
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
	
	public ReportStillImageData setAs(ReportStillImageData value)
	{
		m_AppHeader = value.m_AppHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(ReportStillImageData value)
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
	
	public boolean notEquals(ReportStillImageData value)
	{
		return !isEqual(value);
	}
	
	public ReportStillImageData()
	{
		m_AppHeader = new AppHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  ReportStillImageData(ReportStillImageData value)
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
