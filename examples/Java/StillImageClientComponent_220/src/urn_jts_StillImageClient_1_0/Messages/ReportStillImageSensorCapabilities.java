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

public class ReportStillImageSensorCapabilities extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0x4812;
	
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
			
			public ReportStillImageSensorCapabilities.AppHeader.HeaderRec assign(HeaderRec value)
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
				m_MessageID = 0x4812;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0x4812;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRec m_HeaderRec;
	
		public ReportStillImageSensorCapabilities.AppHeader.HeaderRec getHeaderRec()
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
		
		public ReportStillImageSensorCapabilities.AppHeader assign(AppHeader value)
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
		public static class  StillImageSensorList
		{
			public static class  StillImageSensorCapabilitiesRec
			{
				public static class  SupportedFrameSizes
				{
				
					protected StillImageSensorCapabilitiesRec m_parent;
					protected long m_SubFields;
				
					public void setParent(StillImageSensorCapabilitiesRec parent)
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
					
					public long getSqcif_128x96()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 0; index <= 0; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setSqcif_128x96(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 0; index <= 0; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getQcif_176x144()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 1; index <= 1; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setQcif_176x144(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 1; index <= 1; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getCif_352x288()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 2; index <= 2; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setCif_352x288(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 2; index <= 2; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getCif4_704x576()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 3; index <= 3; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setCif4_704x576(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 3; index <= 3; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getCif16_1408x1152()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 4; index <= 4; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setCif16_1408x1152(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 4; index <= 4; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getQqvga_160x120()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 5; index <= 5; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setQqvga_160x120(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 5; index <= 5; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getQvga_320x240()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 6; index <= 6; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setQvga_320x240(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 6; index <= 6; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getVga_640x480()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 7; index <= 7; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setVga_640x480(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 7; index <= 7; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getSvga_800x600()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 8; index <= 8; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setSvga_800x600(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 8; index <= 8; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getXga_1024x768()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 9; index <= 9; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setXga_1024x768(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 9; index <= 9; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getUxga_1600x1200()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 10; index <= 10; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setUxga_1600x1200(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 10; index <= 10; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getQxga_2048x1536()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 11; index <= 11; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setQxga_2048x1536(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 11; index <= 11; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getSxga_1280x1024()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 12; index <= 12; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setSxga_1280x1024(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 12; index <= 12; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getQsxga_2560x2048()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 13; index <= 13; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setQsxga_2560x2048(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 13; index <= 13; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getHsxga_5120x4096()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 14; index <= 14; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setHsxga_5120x4096(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 14; index <= 14; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getWvga_852x480()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 15; index <= 15; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setWvga_852x480(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 15; index <= 15; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getWxga_1366x768()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 16; index <= 16; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setWxga_1366x768(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 16; index <= 16; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getWsxga_1600x1024()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 17; index <= 17; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setWsxga_1600x1024(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 17; index <= 17; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getWuxga_1920x1200()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 18; index <= 18; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setWuxga_1920x1200(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 18; index <= 18; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getWoxga_2560x1600()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 19; index <= 19; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setWoxga_2560x1600(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 19; index <= 19; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getWqsxga_3200x2048()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 20; index <= 20; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setWqsxga_3200x2048(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 20; index <= 20; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getWquxga_3840x2400()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 21; index <= 21; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setWquxga_3840x2400(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 21; index <= 21; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getWhsxga_6400x4096()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 22; index <= 22; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setWhsxga_6400x4096(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 22; index <= 22; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getWhuxga_7680x4800()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 23; index <= 23; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setWhuxga_7680x4800(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 23; index <= 23; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getCga_320x200()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 24; index <= 24; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setCga_320x200(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 24; index <= 24; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getEga_640x350()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 25; index <= 25; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setEga_640x350(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 25; index <= 25; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getHd480_852x480()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 26; index <= 26; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setHd480_852x480(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 26; index <= 26; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getHd720_1280x720()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 27; index <= 27; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setHd720_1280x720(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 27; index <= 27; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (long) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public long getHd1080_1920x1080()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 28; index <= 28; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (long)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setHd1080_1920x1080(long value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 28; index <= 28; index++)
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
					
					public ReportStillImageSensorCapabilities.Body.StillImageSensorList.StillImageSensorCapabilitiesRec.SupportedFrameSizes assign(SupportedFrameSizes value)
					{
						this.m_SubFields = value.m_SubFields;
						
						return this;
					}
					
					public boolean isEqual(SupportedFrameSizes value)
					{
						return this.m_SubFields == value.getHd1080_1920x1080();
					}
					
					public boolean notEquals(SupportedFrameSizes value)
					{
						return !this.isEqual(value);
					}
					
					public SupportedFrameSizes()
					{
						m_parent = null;
						m_SubFields = 0;
					}
					
					public SupportedFrameSizes(SupportedFrameSizes value)
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
				public static class  SupportedImageFormats
				{
				
					protected StillImageSensorCapabilitiesRec m_parent;
					protected int m_SubFields;
				
					public void setParent(StillImageSensorCapabilitiesRec parent)
					{
						m_parent = parent;
					}
					
					public void setParentPresenceVector()
					{
						if(m_parent != null )
						{
							m_parent.setPresenceVector(1);
							m_parent.setParentPresenceVector();
						}
					}
					
					public int getJPEG()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 0; index <= 0; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (int)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setJPEG(int value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 0; index <= 0; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (int) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public int getGIF()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 1; index <= 1; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (int)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setGIF(int value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 1; index <= 1; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (int) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public int getPNG()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 2; index <= 2; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (int)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setPNG(int value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 2; index <= 2; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (int) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public int getBMP()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 3; index <= 3; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (int)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setBMP(int value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 3; index <= 3; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (int) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public int getTIFF()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 4; index <= 4; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (int)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setTIFF(int value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 4; index <= 4; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (int) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public int getPPM()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 5; index <= 5; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (int)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setPPM(int value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 5; index <= 5; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (int) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public int getPGM()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 6; index <= 6; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (int)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setPGM(int value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 6; index <= 6; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (int) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public int getPNM()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 7; index <= 7; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (int)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setPNM(int value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 7; index <= 7; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (int) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public int getNEF_Nikon_RAW()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 8; index <= 8; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (int)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setNEF_Nikon_RAW(int value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 8; index <= 8; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (int) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public int getCR2_Canon_RAW()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 9; index <= 9; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (int)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setCR2_Canon_RAW(int value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 9; index <= 9; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (int) JausUtils.getPVLong(bfbs);
							setParentPresenceVector();
							return 0;
						}
						return 1;
					}
					
					public int getDNG_Adobe_RAW()
					{
						BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
						BitSet subFieldBitSet = new BitSet(1);
						int i = 0;
						
						for (int index = 10; index <= 10; index++)
						{
						    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
						}
						
						return (int)JausUtils.getPVLong(subFieldBitSet);
					}
					
					public int setDNG_Adobe_RAW(int value)
					{
						if ((value == 0) || (value == 1))
						{
							BitSet bfbs = JausUtils.setPV(m_SubFields);
							BitSet sfbs = new BitSet(1);
							sfbs = JausUtils.setPV(value);
							int i = 0;
							
							for (int index = 10; index <= 10; index++)
							{
								bfbs.set(index, sfbs.get(i++));
							}
							
							m_SubFields = (int) JausUtils.getPVLong(bfbs);
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
						
						
						bytes.putShort(pos, (short) m_SubFields);
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
						
						m_SubFields = bytes.getShort(pos) & 0xffff;
						pos += JausUtils.getNumBytes("short");
					}
					
					public ReportStillImageSensorCapabilities.Body.StillImageSensorList.StillImageSensorCapabilitiesRec.SupportedImageFormats assign(SupportedImageFormats value)
					{
						this.m_SubFields = value.m_SubFields;
						
						return this;
					}
					
					public boolean isEqual(SupportedImageFormats value)
					{
						return this.m_SubFields == value.getDNG_Adobe_RAW();
					}
					
					public boolean notEquals(SupportedImageFormats value)
					{
						return !this.isEqual(value);
					}
					
					public SupportedImageFormats()
					{
						m_parent = null;
						m_SubFields = 0;
					}
					
					public SupportedImageFormats(SupportedImageFormats value)
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
			
			
				protected StillImageSensorList m_parent;
				protected BitSet m_PresenceVector;
				protected short m_PresenceVectorTemp;
				protected int m_SensorID;
				protected SupportedFrameSizes m_SupportedFrameSizes;
				protected SupportedImageFormats m_SupportedImageFormats;
			
				public void setParent(StillImageSensorList parent)
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
				
				public boolean isSupportedFrameSizesValid()
				{
					if (checkPresenceVector(0))
					{
						return true;
					}
					return false;
				}
				
				public ReportStillImageSensorCapabilities.Body.StillImageSensorList.StillImageSensorCapabilitiesRec.SupportedFrameSizes getSupportedFrameSizes()
				{
					return m_SupportedFrameSizes;
				}
				
				public void setSupportedFrameSizes(SupportedFrameSizes value)
				{
					m_SupportedFrameSizes = value;
					setPresenceVector(0);
					setParentPresenceVector();
				}
				
				public boolean isSupportedImageFormatsValid()
				{
					if (checkPresenceVector(1))
					{
						return true;
					}
					return false;
				}
				
				public ReportStillImageSensorCapabilities.Body.StillImageSensorList.StillImageSensorCapabilitiesRec.SupportedImageFormats getSupportedImageFormats()
				{
					return m_SupportedImageFormats;
				}
				
				public void setSupportedImageFormats(SupportedImageFormats value)
				{
					m_SupportedImageFormats = value;
					setPresenceVector(1);
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
					if (checkPresenceVector(0))
					{
						size += m_SupportedFrameSizes.getSize();
					}
					if (checkPresenceVector(1))
					{
						size += m_SupportedImageFormats.getSize();
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
						m_SupportedFrameSizes.encode(bytes, pos);
						pos += m_SupportedFrameSizes.getSize();
					}
					if (checkPresenceVector(1))
					{
						m_SupportedImageFormats.encode(bytes, pos);
						pos += m_SupportedImageFormats.getSize();
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
						m_SupportedFrameSizes.decode(bytes, pos);
						pos += m_SupportedFrameSizes.getSize();
					}
					if (checkPresenceVector(1))
					{
						m_SupportedImageFormats.decode(bytes, pos);
						pos += m_SupportedImageFormats.getSize();
					}
				}
				
				public ReportStillImageSensorCapabilities.Body.StillImageSensorList.StillImageSensorCapabilitiesRec assign(StillImageSensorCapabilitiesRec value)
				{
					m_PresenceVector = value.m_PresenceVector;
					m_SensorID = value.m_SensorID;
					m_SupportedFrameSizes = value.m_SupportedFrameSizes;
					m_SupportedImageFormats = value.m_SupportedImageFormats;
					
					return this;
				}
				
				public boolean isEqual(StillImageSensorCapabilitiesRec value)
				{
					if (!m_PresenceVector.equals(value.m_PresenceVector))
					{
						return false;
					}
					if (m_SensorID != value.getSensorID())
					{
						return false;
					}
					
					if (!m_SupportedFrameSizes.isEqual(value.getSupportedFrameSizes()))
					{
						return false;
					}
					
					if (!m_SupportedImageFormats.isEqual(value.getSupportedImageFormats()))
					{
						return false;
					}
					
					return true;
				}
				
				public boolean notEquals(StillImageSensorCapabilitiesRec value)
				{
					return !this.isEqual(value);
				}
				
				public StillImageSensorCapabilitiesRec()
				{
					m_parent = null;
					m_PresenceVector = new BitSet();
					m_PresenceVectorTemp = 0;
					m_SensorID = 0;
					m_SupportedFrameSizes = new SupportedFrameSizes();
					m_SupportedFrameSizes.setParent(this);
					m_SupportedImageFormats = new SupportedImageFormats();
					m_SupportedImageFormats.setParent(this);
				}
				
				public StillImageSensorCapabilitiesRec(StillImageSensorCapabilitiesRec value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_PresenceVector = new BitSet();
					m_PresenceVectorTemp = 0;
					m_SensorID = 0;
					m_SupportedFrameSizes = new SupportedFrameSizes();
					m_SupportedFrameSizes.setParent(this);
					m_SupportedImageFormats = new SupportedImageFormats();
					m_SupportedImageFormats.setParent(this);
					
					/// Copy the values
					m_PresenceVector = value.m_PresenceVector;
					m_SensorID = value.m_SensorID;
					m_SupportedFrameSizes = value.m_SupportedFrameSizes;
					m_SupportedImageFormats = value.m_SupportedImageFormats;
				}
				
				public void finalize()
				{
				}
				
			}
		
		
			protected Body m_parent;
			protected ArrayList<StillImageSensorCapabilitiesRec> m_StillImageSensorCapabilitiesRec;
		
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
				return (long) m_StillImageSensorCapabilitiesRec.size();
			}
			
			public ReportStillImageSensorCapabilities.Body.StillImageSensorList.StillImageSensorCapabilitiesRec getElement(int index)
			{
				return m_StillImageSensorCapabilitiesRec.get(index);
			}
			
			public void setElement(int index, StillImageSensorCapabilitiesRec value)
			{
				if(m_StillImageSensorCapabilitiesRec.size()-1 < index)
				{
					return;
				}
				
				m_StillImageSensorCapabilitiesRec.set(index, value);
				m_StillImageSensorCapabilitiesRec.get(index).setParent(this);
				setParentPresenceVector();
			}
			
			public void addElement(StillImageSensorCapabilitiesRec value)
			{
				m_StillImageSensorCapabilitiesRec.add(value);
				m_StillImageSensorCapabilitiesRec.get(m_StillImageSensorCapabilitiesRec.size() -1 ).setParent(this);
				setParentPresenceVector();
			}
			
			public int deleteElement(int index)
			{
				if(m_StillImageSensorCapabilitiesRec.size()-1 < index)
				{
					return 1;
				}
				
				m_StillImageSensorCapabilitiesRec.remove(index);
				return 0;
			}
			
			public int deleteLastElement()
			{
				m_StillImageSensorCapabilitiesRec.remove(m_StillImageSensorCapabilitiesRec.size()-1);
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
				for (int i = 0; i < m_StillImageSensorCapabilitiesRec.size(); i++)
				{
					size += m_StillImageSensorCapabilitiesRec.get(i).getSize();
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
				
				int size = (int) m_StillImageSensorCapabilitiesRec.size();
				bytes.putShort(pos, (short) size);
				pos += JausUtils.getNumBytes("short");
				for (int i = 0; i < m_StillImageSensorCapabilitiesRec.size(); i++)
				{
					m_StillImageSensorCapabilitiesRec.get(i).encode(bytes, pos);
					pos += m_StillImageSensorCapabilitiesRec.get(i).getSize();
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
				m_StillImageSensorCapabilitiesRec = new ArrayList<StillImageSensorCapabilitiesRec>();
				for (int i = 0; i <  size; i++)
				{
					StillImageSensorCapabilitiesRec item = new StillImageSensorCapabilitiesRec();
					item.decode(bytes, pos);
					m_StillImageSensorCapabilitiesRec.add(item);
					pos += item.getSize();
				}
			}
			
			public ReportStillImageSensorCapabilities.Body.StillImageSensorList assign(StillImageSensorList value)
			{
				m_StillImageSensorCapabilitiesRec.clear();
				
				for (int i = 0; i < value.m_StillImageSensorCapabilitiesRec.size(); i++)
				{
					m_StillImageSensorCapabilitiesRec.add(value.m_StillImageSensorCapabilitiesRec.get(i));
					m_StillImageSensorCapabilitiesRec.get(i).setParent(this);
				}
				
				return this;
			}
			
			public boolean isEqual(StillImageSensorList value)
			{
				for (int i = 0; i < m_StillImageSensorCapabilitiesRec.size(); i++)
				{
					if (!m_StillImageSensorCapabilitiesRec.get(i).isEqual(value.getElement(i)))
					{
						return false;
					}
				}
				
				return true;
			}
			
			public boolean notEquals(StillImageSensorList value)
			{
				return !this.isEqual(value);
			}
			
			public StillImageSensorList()
			{
				m_parent = null;
				m_StillImageSensorCapabilitiesRec = new ArrayList<StillImageSensorCapabilitiesRec>();
				for (int i = 0; i < m_StillImageSensorCapabilitiesRec.size(); i++)
				{
					m_StillImageSensorCapabilitiesRec.get(i).setParent(this);
				}
			}
			
			public StillImageSensorList(StillImageSensorList value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_StillImageSensorCapabilitiesRec = new ArrayList<StillImageSensorCapabilitiesRec>();
				for (int i = 0; i < m_StillImageSensorCapabilitiesRec.size(); i++)
				{
					m_StillImageSensorCapabilitiesRec.get(i).setParent(this);
				}
				
				/// Copy the values
				m_StillImageSensorCapabilitiesRec.clear();
				
				for (int i = 0; i < value.m_StillImageSensorCapabilitiesRec.size(); i++)
				{
					m_StillImageSensorCapabilitiesRec.add(value.m_StillImageSensorCapabilitiesRec.get(i));
					m_StillImageSensorCapabilitiesRec.get(i).setParent(this);
				}
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected StillImageSensorList m_StillImageSensorList;
	
		public ReportStillImageSensorCapabilities.Body.StillImageSensorList getStillImageSensorList()
		{
			return m_StillImageSensorList;
		}
		
		public void setStillImageSensorList(StillImageSensorList value)
		{
			m_StillImageSensorList = value;
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
			
			size += m_StillImageSensorList.getSize();
			
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
			
			m_StillImageSensorList.encode(bytes, pos);
			pos += m_StillImageSensorList.getSize();
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
			
			m_StillImageSensorList.decode(bytes, pos);
			pos += m_StillImageSensorList.getSize();
		}
		
		public ReportStillImageSensorCapabilities.Body assign(Body value)
		{
			m_StillImageSensorList = value.m_StillImageSensorList;
			m_StillImageSensorList.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(Body value)
		{
			if (!m_StillImageSensorList.isEqual(value.m_StillImageSensorList))
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
			m_StillImageSensorList = new StillImageSensorList();
			m_StillImageSensorList.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_StillImageSensorList = new StillImageSensorList();
			m_StillImageSensorList.setParent(this);
			
			/// Copy the values
			m_StillImageSensorList = value.m_StillImageSensorList;
			m_StillImageSensorList.setParent(this);
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
	public ReportStillImageSensorCapabilities.AppHeader getAppHeader()
	{
		return m_AppHeader;
	}
	
	public void setAppHeader(AppHeader value)
	{
		m_AppHeader = value;
	}
	
	public ReportStillImageSensorCapabilities.Body getBody()
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
	
	public ReportStillImageSensorCapabilities setAs(ReportStillImageSensorCapabilities value)
	{
		m_AppHeader = value.m_AppHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(ReportStillImageSensorCapabilities value)
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
	
	public boolean notEquals(ReportStillImageSensorCapabilities value)
	{
		return !isEqual(value);
	}
	
	public ReportStillImageSensorCapabilities()
	{
		m_AppHeader = new AppHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  ReportStillImageSensorCapabilities(ReportStillImageSensorCapabilities value)
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
