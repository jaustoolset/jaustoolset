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

public class ReportLocalPose extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x4403;
	
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
			
			public ReportLocalPose.AppHeader.HeaderRec assign(HeaderRec value)
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
				m_MessageID = 0x4403;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x4403;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
		public ReportLocalPose.AppHeader.HeaderRec getHeaderRec()
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
		
		public ReportLocalPose.AppHeader assign(AppHeader value)
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
		public static class  LocalPoseRec
		{
			public static class  TimeStamp
			{
			
				protected LocalPoseRec m_parent;
				protected long m_SubFields;
			
				public void setParent(LocalPoseRec parent)
				{
					m_parent = parent;
				}
				
				public void setParentPresenceVector()
				{
					if(m_parent != null )
					{
						m_parent.setPresenceVector(8);
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
				
				public ReportLocalPose.Body.LocalPoseRec.TimeStamp assign(TimeStamp value)
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
		
		
			protected Body m_parent;
			protected BitSet m_PresenceVector;
			protected int m_PresenceVectorTemp;
			protected long m_X;
			protected long m_Y;
			protected long m_Z;
			protected long m_Position_RMS;
			protected int m_Roll;
			protected int m_Pitch;
			protected int m_Yaw;
			protected int m_Attitude_RMS;
			protected TimeStamp m_TimeStamp;
		
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
			
			public int getPresenceVector()
			{
				return m_PresenceVectorTemp;
			}
			
			protected void setPresenceVector(int index)
			{
				
				m_PresenceVector.set(index);
				m_PresenceVectorTemp = (int) JausUtils.getPVInt(m_PresenceVector);
			}
			
			public boolean checkPresenceVector(int index)
			{
				
				return m_PresenceVector.get(index);
			}
			
			public boolean isXValid()
			{
				if (checkPresenceVector(0))
				{
					return true;
				}
				return false;
			}
			
			public double getX()
			{
				double value;
				
				double scaleFactor = ( 100000 - -100000 ) / 4.294967295E9;
				double bias = -100000;
				
				value = m_X * scaleFactor + bias;
				
				return value;
			}
			
			public void setX(double value)
			{
				if ((value >= -100000) && (value <= 100000))
				{
					double scaleFactor = ( 100000 - -100000 ) / 4.294967295E9;
					double bias = -100000;
					
					m_X= (long)((value - bias) / scaleFactor);
					setPresenceVector(0);
					setParentPresenceVector();
				}
				return;
			}
			
			public boolean isYValid()
			{
				if (checkPresenceVector(1))
				{
					return true;
				}
				return false;
			}
			
			public double getY()
			{
				double value;
				
				double scaleFactor = ( 100000 - -100000 ) / 4.294967295E9;
				double bias = -100000;
				
				value = m_Y * scaleFactor + bias;
				
				return value;
			}
			
			public void setY(double value)
			{
				if ((value >= -100000) && (value <= 100000))
				{
					double scaleFactor = ( 100000 - -100000 ) / 4.294967295E9;
					double bias = -100000;
					
					m_Y= (long)((value - bias) / scaleFactor);
					setPresenceVector(1);
					setParentPresenceVector();
				}
				return;
			}
			
			public boolean isZValid()
			{
				if (checkPresenceVector(2))
				{
					return true;
				}
				return false;
			}
			
			public double getZ()
			{
				double value;
				
				double scaleFactor = ( 100000 - -100000 ) / 4.294967295E9;
				double bias = -100000;
				
				value = m_Z * scaleFactor + bias;
				
				return value;
			}
			
			public void setZ(double value)
			{
				if ((value >= -100000) && (value <= 100000))
				{
					double scaleFactor = ( 100000 - -100000 ) / 4.294967295E9;
					double bias = -100000;
					
					m_Z= (long)((value - bias) / scaleFactor);
					setPresenceVector(2);
					setParentPresenceVector();
				}
				return;
			}
			
			public boolean isPosition_RMSValid()
			{
				if (checkPresenceVector(3))
				{
					return true;
				}
				return false;
			}
			
			public double getPosition_RMS()
			{
				double value;
				
				double scaleFactor = ( 100 - 0 ) / 4.294967295E9;
				double bias = 0;
				
				value = m_Position_RMS * scaleFactor + bias;
				
				return value;
			}
			
			public void setPosition_RMS(double value)
			{
				if ((value >= 0) && (value <= 100))
				{
					double scaleFactor = ( 100 - 0 ) / 4.294967295E9;
					double bias = 0;
					
					m_Position_RMS= (long)((value - bias) / scaleFactor);
					setPresenceVector(3);
					setParentPresenceVector();
				}
				return;
			}
			
			public boolean isRollValid()
			{
				if (checkPresenceVector(4))
				{
					return true;
				}
				return false;
			}
			
			public double getRoll()
			{
				double value;
				
				double scaleFactor = ( 3.14159265358979323846 - -3.14159265358979323846 ) / 65535.0;
				double bias = -3.14159265358979323846;
				
				value = m_Roll * scaleFactor + bias;
				
				return value;
			}
			
			public void setRoll(double value)
			{
				if ((value >= -3.14159265358979323846) && (value <= 3.14159265358979323846))
				{
					double scaleFactor = ( 3.14159265358979323846 - -3.14159265358979323846 ) / 65535.0;
					double bias = -3.14159265358979323846;
					
					m_Roll= (int)((value - bias) / scaleFactor);
					setPresenceVector(4);
					setParentPresenceVector();
				}
				return;
			}
			
			public boolean isPitchValid()
			{
				if (checkPresenceVector(5))
				{
					return true;
				}
				return false;
			}
			
			public double getPitch()
			{
				double value;
				
				double scaleFactor = ( 3.14159265358979323846 - -3.14159265358979323846 ) / 65535.0;
				double bias = -3.14159265358979323846;
				
				value = m_Pitch * scaleFactor + bias;
				
				return value;
			}
			
			public void setPitch(double value)
			{
				if ((value >= -3.14159265358979323846) && (value <= 3.14159265358979323846))
				{
					double scaleFactor = ( 3.14159265358979323846 - -3.14159265358979323846 ) / 65535.0;
					double bias = -3.14159265358979323846;
					
					m_Pitch= (int)((value - bias) / scaleFactor);
					setPresenceVector(5);
					setParentPresenceVector();
				}
				return;
			}
			
			public boolean isYawValid()
			{
				if (checkPresenceVector(6))
				{
					return true;
				}
				return false;
			}
			
			public double getYaw()
			{
				double value;
				
				double scaleFactor = ( 3.14159265358979323846 - -3.14159265358979323846 ) / 65535.0;
				double bias = -3.14159265358979323846;
				
				value = m_Yaw * scaleFactor + bias;
				
				return value;
			}
			
			public void setYaw(double value)
			{
				if ((value >= -3.14159265358979323846) && (value <= 3.14159265358979323846))
				{
					double scaleFactor = ( 3.14159265358979323846 - -3.14159265358979323846 ) / 65535.0;
					double bias = -3.14159265358979323846;
					
					m_Yaw= (int)((value - bias) / scaleFactor);
					setPresenceVector(6);
					setParentPresenceVector();
				}
				return;
			}
			
			public boolean isAttitude_RMSValid()
			{
				if (checkPresenceVector(7))
				{
					return true;
				}
				return false;
			}
			
			public double getAttitude_RMS()
			{
				double value;
				
				double scaleFactor = ( 3.14159265358979323846 - 0 ) / 65535.0;
				double bias = 0;
				
				value = m_Attitude_RMS * scaleFactor + bias;
				
				return value;
			}
			
			public void setAttitude_RMS(double value)
			{
				if ((value >= 0) && (value <= 3.14159265358979323846))
				{
					double scaleFactor = ( 3.14159265358979323846 - 0 ) / 65535.0;
					double bias = 0;
					
					m_Attitude_RMS= (int)((value - bias) / scaleFactor);
					setPresenceVector(7);
					setParentPresenceVector();
				}
				return;
			}
			
			public boolean isTimeStampValid()
			{
				if (checkPresenceVector(8))
				{
					return true;
				}
				return false;
			}
			
			public ReportLocalPose.Body.LocalPoseRec.TimeStamp getTimeStamp()
			{
				return m_TimeStamp;
			}
			
			public void setTimeStamp(TimeStamp value)
			{
				m_TimeStamp = value;
				setPresenceVector(8);
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
				if (checkPresenceVector(0))
				{
					size += JausUtils.getNumBytes("int");
				}
				if (checkPresenceVector(1))
				{
					size += JausUtils.getNumBytes("int");
				}
				if (checkPresenceVector(2))
				{
					size += JausUtils.getNumBytes("int");
				}
				if (checkPresenceVector(3))
				{
					size += JausUtils.getNumBytes("int");
				}
				if (checkPresenceVector(4))
				{
					size += JausUtils.getNumBytes("short");
				}
				if (checkPresenceVector(5))
				{
					size += JausUtils.getNumBytes("short");
				}
				if (checkPresenceVector(6))
				{
					size += JausUtils.getNumBytes("short");
				}
				if (checkPresenceVector(7))
				{
					size += JausUtils.getNumBytes("short");
				}
				if (checkPresenceVector(8))
				{
					size += m_TimeStamp.getSize();
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
					int m_PresenceVectorTemp = (int) JausUtils.getPVInt(m_PresenceVector);
				bytes.putShort(pos, (short) m_PresenceVectorTemp);
				pos += JausUtils.getNumBytes("short");
				}
				catch(Exception e)
				{
					logger.log(Level.SEVERE, null, e);
				}
				if (checkPresenceVector(0))
				{
					bytes.putInt(pos, (int) m_X);
					pos += JausUtils.getNumBytes("int");
				}
				if (checkPresenceVector(1))
				{
					bytes.putInt(pos, (int) m_Y);
					pos += JausUtils.getNumBytes("int");
				}
				if (checkPresenceVector(2))
				{
					bytes.putInt(pos, (int) m_Z);
					pos += JausUtils.getNumBytes("int");
				}
				if (checkPresenceVector(3))
				{
					bytes.putInt(pos, (int) m_Position_RMS);
					pos += JausUtils.getNumBytes("int");
				}
				if (checkPresenceVector(4))
				{
					bytes.putShort(pos, (short) m_Roll);
					pos += JausUtils.getNumBytes("short");
				}
				if (checkPresenceVector(5))
				{
					bytes.putShort(pos, (short) m_Pitch);
					pos += JausUtils.getNumBytes("short");
				}
				if (checkPresenceVector(6))
				{
					bytes.putShort(pos, (short) m_Yaw);
					pos += JausUtils.getNumBytes("short");
				}
				if (checkPresenceVector(7))
				{
					bytes.putShort(pos, (short) m_Attitude_RMS);
					pos += JausUtils.getNumBytes("short");
				}
				if (checkPresenceVector(8))
				{
					m_TimeStamp.encode(bytes, pos);
					pos += m_TimeStamp.getSize();
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
					int m_PresenceVectorTemp = 0;
				m_PresenceVectorTemp = bytes.getShort(pos) & 0xffff;
				pos += JausUtils.getNumBytes("short");
				m_PresenceVector = JausUtils.setPV(m_PresenceVectorTemp);
				}
				catch(Exception e)
				{
					logger.log(Level.SEVERE, null, e);
				}
				if (checkPresenceVector(0))
				{
					m_X = bytes.getInt(pos) & 0xffffffffL;
					pos += JausUtils.getNumBytes("int");
				}
				if (checkPresenceVector(1))
				{
					m_Y = bytes.getInt(pos) & 0xffffffffL;
					pos += JausUtils.getNumBytes("int");
				}
				if (checkPresenceVector(2))
				{
					m_Z = bytes.getInt(pos) & 0xffffffffL;
					pos += JausUtils.getNumBytes("int");
				}
				if (checkPresenceVector(3))
				{
					m_Position_RMS = bytes.getInt(pos) & 0xffffffffL;
					pos += JausUtils.getNumBytes("int");
				}
				if (checkPresenceVector(4))
				{
					m_Roll = bytes.getShort(pos) & 0xffff;
					pos += JausUtils.getNumBytes("short");
				}
				if (checkPresenceVector(5))
				{
					m_Pitch = bytes.getShort(pos) & 0xffff;
					pos += JausUtils.getNumBytes("short");
				}
				if (checkPresenceVector(6))
				{
					m_Yaw = bytes.getShort(pos) & 0xffff;
					pos += JausUtils.getNumBytes("short");
				}
				if (checkPresenceVector(7))
				{
					m_Attitude_RMS = bytes.getShort(pos) & 0xffff;
					pos += JausUtils.getNumBytes("short");
				}
				if (checkPresenceVector(8))
				{
					m_TimeStamp.decode(bytes, pos);
					pos += m_TimeStamp.getSize();
				}
			}
			
			public ReportLocalPose.Body.LocalPoseRec assign(LocalPoseRec value)
			{
				m_PresenceVector = value.m_PresenceVector;
				m_X = value.m_X;
				m_Y = value.m_Y;
				m_Z = value.m_Z;
				m_Position_RMS = value.m_Position_RMS;
				m_Roll = value.m_Roll;
				m_Pitch = value.m_Pitch;
				m_Yaw = value.m_Yaw;
				m_Attitude_RMS = value.m_Attitude_RMS;
				m_TimeStamp = value.m_TimeStamp;
				
				return this;
			}
			
			public boolean isEqual(LocalPoseRec value)
			{
				if (!m_PresenceVector.equals(value.m_PresenceVector))
				{
					return false;
				}
				if (m_X != value.getX())
				{
					return false;
				}
				if (m_Y != value.getY())
				{
					return false;
				}
				if (m_Z != value.getZ())
				{
					return false;
				}
				if (m_Position_RMS != value.getPosition_RMS())
				{
					return false;
				}
				if (m_Roll != value.getRoll())
				{
					return false;
				}
				if (m_Pitch != value.getPitch())
				{
					return false;
				}
				if (m_Yaw != value.getYaw())
				{
					return false;
				}
				if (m_Attitude_RMS != value.getAttitude_RMS())
				{
					return false;
				}
				
				if (!m_TimeStamp.isEqual(value.getTimeStamp()))
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(LocalPoseRec value)
			{
				return !this.isEqual(value);
			}
			
			public LocalPoseRec()
			{
				m_parent = null;
				m_PresenceVector = new BitSet();
				m_PresenceVectorTemp = 0;
				m_X = 0;
				m_Y = 0;
				m_Z = 0;
				m_Position_RMS = 0;
				m_Roll = 0;
				m_Pitch = 0;
				m_Yaw = 0;
				m_Attitude_RMS = 0;
				m_TimeStamp = new TimeStamp();
				m_TimeStamp.setParent(this);
			}
			
			public LocalPoseRec(LocalPoseRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_PresenceVector = new BitSet();
				m_PresenceVectorTemp = 0;
				m_X = 0;
				m_Y = 0;
				m_Z = 0;
				m_Position_RMS = 0;
				m_Roll = 0;
				m_Pitch = 0;
				m_Yaw = 0;
				m_Attitude_RMS = 0;
				m_TimeStamp = new TimeStamp();
				m_TimeStamp.setParent(this);
				
				/// Copy the values
				m_PresenceVector = value.m_PresenceVector;
				m_X = value.m_X;
				m_Y = value.m_Y;
				m_Z = value.m_Z;
				m_Position_RMS = value.m_Position_RMS;
				m_Roll = value.m_Roll;
				m_Pitch = value.m_Pitch;
				m_Yaw = value.m_Yaw;
				m_Attitude_RMS = value.m_Attitude_RMS;
				m_TimeStamp = value.m_TimeStamp;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected LocalPoseRec m_LocalPoseRec;
	
		public ReportLocalPose.Body.LocalPoseRec getLocalPoseRec()
		{
			return m_LocalPoseRec;
		}
		
		public void setLocalPoseRec(LocalPoseRec value)
		{
			m_LocalPoseRec = value;
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
			
			size += m_LocalPoseRec.getSize();
			
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
			
			m_LocalPoseRec.encode(bytes, pos);
			pos += m_LocalPoseRec.getSize();
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
			
			m_LocalPoseRec.decode(bytes, pos);
			pos += m_LocalPoseRec.getSize();
		}
		
		public ReportLocalPose.Body assign(Body value)
		{
			m_LocalPoseRec = value.m_LocalPoseRec;
			m_LocalPoseRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(Body value)
		{
			if (!m_LocalPoseRec.isEqual(value.getLocalPoseRec()))
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
			m_LocalPoseRec = new LocalPoseRec();
			m_LocalPoseRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_LocalPoseRec = new LocalPoseRec();
			m_LocalPoseRec.setParent(this);
			
			/// Copy the values
			m_LocalPoseRec = value.m_LocalPoseRec;
			m_LocalPoseRec.setParent(this);
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
	public ReportLocalPose.AppHeader getAppHeader()
	{
		return m_AppHeader;
	}
	
	public void setAppHeader(AppHeader value)
	{
		m_AppHeader = value;
	}
	
	public ReportLocalPose.Body getBody()
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
	
	public ReportLocalPose setAs(ReportLocalPose value)
	{
		m_AppHeader = value.m_AppHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(ReportLocalPose value)
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
	
	public boolean notEquals(ReportLocalPose value)
	{
		return !isEqual(value);
	}
	
	public ReportLocalPose()
	{
		m_AppHeader = new AppHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  ReportLocalPose(ReportLocalPose value)
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
