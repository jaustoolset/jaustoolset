package src.urn_jaus_jss_core_Events_1_1.InternalEvents;

import framework.internalEvents.InternalEvent;
import framework.JausUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.logging.Logger;
import java.util.logging.Level;

public class BroadcastGlobal extends InternalEvent
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static class  Body
	{
		public static class  BroadcastRec
		{
			public static class  DestinationID
			{
			
				protected BroadcastRec m_parent;
				protected long m_SubFields;
			
				public void setParent(BroadcastRec parent)
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
				
				public long getComponentID()
				{
					BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
					BitSet subFieldBitSet = new BitSet(8);
					int i = 0;
					
					for (int index = 0; index <= 7; index++)
					{
					    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
					}
					
					return (long)JausUtils.getPVLong(subFieldBitSet);
				}
				
				public int setComponentID(long value)
				{
					if (((value >= 1) && (value <= 255)))
					{
						BitSet bfbs = JausUtils.setPV(m_SubFields);
						BitSet sfbs = new BitSet(8);
						sfbs = JausUtils.setPV(value);
						int i = 0;
						
						for (int index = 0; index <= 7; index++)
						{
							bfbs.set(index, sfbs.get(i++));
						}
						
						m_SubFields = (long) JausUtils.getPVLong(bfbs);
						setParentPresenceVector();
						return 0;
					}
					return 1;
				}
				
				public long getNodeID()
				{
					BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
					BitSet subFieldBitSet = new BitSet(8);
					int i = 0;
					
					for (int index = 8; index <= 15; index++)
					{
					    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
					}
					
					return (long)JausUtils.getPVLong(subFieldBitSet);
				}
				
				public int setNodeID(long value)
				{
					if (((value >= 1) && (value <= 255)))
					{
						BitSet bfbs = JausUtils.setPV(m_SubFields);
						BitSet sfbs = new BitSet(8);
						sfbs = JausUtils.setPV(value);
						int i = 0;
						
						for (int index = 8; index <= 15; index++)
						{
							bfbs.set(index, sfbs.get(i++));
						}
						
						m_SubFields = (long) JausUtils.getPVLong(bfbs);
						setParentPresenceVector();
						return 0;
					}
					return 1;
				}
				
				public long getSubsystemID()
				{
					BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
					BitSet subFieldBitSet = new BitSet(16);
					int i = 0;
					
					for (int index = 16; index <= 31; index++)
					{
					    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
					}
					
					return (long)JausUtils.getPVLong(subFieldBitSet);
				}
				
				public int setSubsystemID(long value)
				{
					if (((value >= 1) && (value <= 65535)))
					{
						BitSet bfbs = JausUtils.setPV(m_SubFields);
						BitSet sfbs = new BitSet(16);
						sfbs = JausUtils.setPV(value);
						int i = 0;
						
						for (int index = 16; index <= 31; index++)
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
				
				public BroadcastGlobal.Body.BroadcastRec.DestinationID assign(DestinationID value)
				{
					this.m_SubFields = value.m_SubFields;
					
					return this;
				}
				
				public boolean isEqual(DestinationID value)
				{
					return this.m_SubFields == value.getSubsystemID();
				}
				
				public boolean notEquals(DestinationID value)
				{
					return !this.isEqual(value);
				}
				
				public DestinationID()
				{
					m_parent = null;
					m_SubFields = 0;
				}
				
				public DestinationID(DestinationID value)
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
			public static class  SourceID
			{
			
				protected BroadcastRec m_parent;
				protected long m_SubFields;
			
				public void setParent(BroadcastRec parent)
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
				
				public long getComponentID()
				{
					BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
					BitSet subFieldBitSet = new BitSet(8);
					int i = 0;
					
					for (int index = 0; index <= 7; index++)
					{
					    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
					}
					
					return (long)JausUtils.getPVLong(subFieldBitSet);
				}
				
				public int setComponentID(long value)
				{
					if (((value >= 1) && (value <= 255)))
					{
						BitSet bfbs = JausUtils.setPV(m_SubFields);
						BitSet sfbs = new BitSet(8);
						sfbs = JausUtils.setPV(value);
						int i = 0;
						
						for (int index = 0; index <= 7; index++)
						{
							bfbs.set(index, sfbs.get(i++));
						}
						
						m_SubFields = (long) JausUtils.getPVLong(bfbs);
						setParentPresenceVector();
						return 0;
					}
					return 1;
				}
				
				public long getNodeID()
				{
					BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
					BitSet subFieldBitSet = new BitSet(8);
					int i = 0;
					
					for (int index = 8; index <= 15; index++)
					{
					    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
					}
					
					return (long)JausUtils.getPVLong(subFieldBitSet);
				}
				
				public int setNodeID(long value)
				{
					if (((value >= 1) && (value <= 255)))
					{
						BitSet bfbs = JausUtils.setPV(m_SubFields);
						BitSet sfbs = new BitSet(8);
						sfbs = JausUtils.setPV(value);
						int i = 0;
						
						for (int index = 8; index <= 15; index++)
						{
							bfbs.set(index, sfbs.get(i++));
						}
						
						m_SubFields = (long) JausUtils.getPVLong(bfbs);
						setParentPresenceVector();
						return 0;
					}
					return 1;
				}
				
				public long getSubsystemID()
				{
					BitSet bitFieldBitSet = JausUtils.setPV(m_SubFields);
					BitSet subFieldBitSet = new BitSet(16);
					int i = 0;
					
					for (int index = 16; index <= 31; index++)
					{
					    subFieldBitSet.set(i++, bitFieldBitSet.get(index));
					}
					
					return (long)JausUtils.getPVLong(subFieldBitSet);
				}
				
				public int setSubsystemID(long value)
				{
					if (((value >= 1) && (value <= 65535)))
					{
						BitSet bfbs = JausUtils.setPV(m_SubFields);
						BitSet sfbs = new BitSet(16);
						sfbs = JausUtils.setPV(value);
						int i = 0;
						
						for (int index = 16; index <= 31; index++)
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
				
				public BroadcastGlobal.Body.BroadcastRec.SourceID assign(SourceID value)
				{
					this.m_SubFields = value.m_SubFields;
					
					return this;
				}
				
				public boolean isEqual(SourceID value)
				{
					return this.m_SubFields == value.getSubsystemID();
				}
				
				public boolean notEquals(SourceID value)
				{
					return !this.isEqual(value);
				}
				
				public SourceID()
				{
					m_parent = null;
					m_SubFields = 0;
				}
				
				public SourceID(SourceID value)
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
			public static class  MessagePayload
			{
			
				protected BroadcastRec m_parent;
				protected long m_Length;
				protected ByteBuffer m_Data;
			
				public void setParent(BroadcastRec parent)
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
				
				public void set(long length, ByteBuffer data)
				{
					m_Length = length;
					
					m_Data.clear();
					
					if (m_Length > 0)
					{
						data.rewind();
						m_Data = ByteBuffer.allocate(data.array().length);
						m_Data.order(ByteOrder.LITTLE_ENDIAN);
						m_Data.put(data);
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
					
					
					bytes.putInt(pos, (int) m_Length);
					pos += JausUtils.getNumBytes("int");
					
					byte[] byteArray = m_Data.array();
					
					for(int i = 0; i<(int) m_Length; i++)
					{
						bytes.put(pos, byteArray[i]);
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
					
					
					m_Length = bytes.getInt(pos) & 0xffffffffL;
					pos += JausUtils.getNumBytes("int");
					byte[] byteArray = new byte[(int)m_Length];
					
					int i;
					for(i=0; i<(int) m_Length; i++)
					{
						byteArray[i] = bytes.get(pos);
						pos++;
					}
					
					m_Data = ByteBuffer.allocate((int)m_Length).order(ByteOrder.LITTLE_ENDIAN);
					m_Data.put(byteArray);
				}
				
				public BroadcastGlobal.Body.BroadcastRec.MessagePayload assign(MessagePayload value)
				{
					this.m_Length = value.m_Length;
					
					m_Data.clear();
					
					if (m_Length > 0)
					{
						this.m_Data.put(value.m_Data);
					}
					
					return this;
				}
				
				public boolean isEqual(MessagePayload value)
				{
					if (this.m_Length != value.getLength())
					{
						return false;
					}
					
					if ((this.m_Data != null) && (value.m_Data!= null))
					{
						if(!Arrays.equals(this.m_Data.array(), value.m_Data.array()))
						{
							return false;
						}
					}
					// This case should never be true since it should not be possible
					// for the two variables to have equal lengths but one has no data.
					// This check is placed here as a secondary check.
					else if ((this.m_Data != null) || (value.m_Data != null))
					{
						return false;
					}
					
					return true;
				}
				
				public boolean notEquals(MessagePayload value)
				{
					return !this.isEqual(value);
				}
				
				public MessagePayload()
				{
					m_parent = null;
					m_Length = MAX_JTS_MESSAGE_SIZE;
					m_Data = ByteBuffer.allocate((int)m_Length);
					m_Data.order(ByteOrder.LITTLE_ENDIAN); 
				}
				
				public MessagePayload(MessagePayload value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_Length = MAX_JTS_MESSAGE_SIZE;
					m_Data = ByteBuffer.allocate((int)m_Length);
					m_Data.order(ByteOrder.LITTLE_ENDIAN); 
					
					/// Copy the values
					this.m_Length = value.m_Length;
					
					m_Data.clear();
					
					if (m_Length > 0)
					{
						this.m_Data.put(value.m_Data);
					}
				}
				
				public void finalize()
				{
				}
				
			}
		
		
			protected Body m_parent;
			protected BitSet m_PresenceVector;
			protected short m_PresenceVectorTemp;
			protected DestinationID m_DestinationID;
			protected SourceID m_SourceID;
			protected short m_Priority;
			protected MessagePayload m_MessagePayload;
		
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
			
			public BroadcastGlobal.Body.BroadcastRec.DestinationID getDestinationID()
			{
				return m_DestinationID;
			}
			
			public void setDestinationID(DestinationID value)
			{
				m_DestinationID = value;
				setParentPresenceVector();
			}
			
			public boolean isSourceIDValid()
			{
				if (checkPresenceVector(0))
				{
					return true;
				}
				return false;
			}
			
			public BroadcastGlobal.Body.BroadcastRec.SourceID getSourceID()
			{
				return m_SourceID;
			}
			
			public void setSourceID(SourceID value)
			{
				m_SourceID = value;
				setPresenceVector(0);
				setParentPresenceVector();
			}
			
			public boolean isPriorityValid()
			{
				if (checkPresenceVector(1))
				{
					return true;
				}
				return false;
			}
			
			public short getPriority()
			{
				return m_Priority;
			}
			
			public void setPriority(short value)
			{
				if (((value >= 0) && (value <= 3)) || (value == 0) || (value == 1) || (value == 2) || (value == 3))
				{
					m_Priority = value;
					setPresenceVector(1);
					setParentPresenceVector();
				}
				return;
			}
			
			public BroadcastGlobal.Body.BroadcastRec.MessagePayload getMessagePayload()
			{
				return m_MessagePayload;
			}
			
			public void setMessagePayload(MessagePayload value)
			{
				m_MessagePayload = value;
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
				size += m_DestinationID.getSize();
				if (checkPresenceVector(0))
				{
					size += m_SourceID.getSize();
				}
				if (checkPresenceVector(1))
				{
					size += JausUtils.getNumBytes("byte");
				}
				size += m_MessagePayload.getSize();
				
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
				m_DestinationID.encode(bytes, pos);
				pos += m_DestinationID.getSize();
				if (checkPresenceVector(0))
				{
					m_SourceID.encode(bytes, pos);
					pos += m_SourceID.getSize();
				}
				if (checkPresenceVector(1))
				{
					bytes.put(pos, (byte) m_Priority);
					pos += JausUtils.getNumBytes("byte");
				}
				m_MessagePayload.encode(bytes, pos);
				pos += m_MessagePayload.getSize();
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
				m_DestinationID.decode(bytes, pos);
				pos += m_DestinationID.getSize();
				if (checkPresenceVector(0))
				{
					m_SourceID.decode(bytes, pos);
					pos += m_SourceID.getSize();
				}
				if (checkPresenceVector(1))
				{
					m_Priority = (short) (bytes.get(pos) & 0xff);
					pos += JausUtils.getNumBytes("byte");
				}
				m_MessagePayload.decode(bytes, pos);
				pos += m_MessagePayload.getSize();
			}
			
			public BroadcastGlobal.Body.BroadcastRec assign(BroadcastRec value)
			{
				m_PresenceVector = value.m_PresenceVector;
				m_DestinationID = value.m_DestinationID;
				m_SourceID = value.m_SourceID;
				m_Priority = value.m_Priority;
				m_MessagePayload = value.m_MessagePayload;
				
				return this;
			}
			
			public boolean isEqual(BroadcastRec value)
			{
				if (!m_PresenceVector.equals(value.m_PresenceVector))
				{
					return false;
				}
				
				if (!m_DestinationID.isEqual(value.getDestinationID()))
				{
					return false;
				}
				
				if (!m_SourceID.isEqual(value.getSourceID()))
				{
					return false;
				}
				if (m_Priority != value.getPriority())
				{
					return false;
				}
				
				if (!m_MessagePayload.isEqual(value.getMessagePayload()))
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(BroadcastRec value)
			{
				return !this.isEqual(value);
			}
			
			public BroadcastRec()
			{
				m_parent = null;
				m_PresenceVector = new BitSet();
				m_PresenceVectorTemp = 0;
				m_DestinationID = new DestinationID();
				m_DestinationID.setParent(this);
				m_SourceID = new SourceID();
				m_SourceID.setParent(this);
				m_Priority = 0;
					m_MessagePayload = new MessagePayload();
				m_MessagePayload.setParent(this);
			}
			
			public BroadcastRec(BroadcastRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_PresenceVector = new BitSet();
				m_PresenceVectorTemp = 0;
				m_DestinationID = new DestinationID();
				m_DestinationID.setParent(this);
				m_SourceID = new SourceID();
				m_SourceID.setParent(this);
				m_Priority = 0;
					m_MessagePayload = new MessagePayload();
				m_MessagePayload.setParent(this);
				
				/// Copy the values
				m_PresenceVector = value.m_PresenceVector;
				m_DestinationID = value.m_DestinationID;
				m_SourceID = value.m_SourceID;
				m_Priority = value.m_Priority;
				m_MessagePayload = value.m_MessagePayload;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected BroadcastRec m_BroadcastRec;
	
		public BroadcastGlobal.Body.BroadcastRec getBroadcastRec()
		{
			return m_BroadcastRec;
		}
		
		public void setBroadcastRec(BroadcastRec value)
		{
			m_BroadcastRec = value;
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
			
			size += m_BroadcastRec.getSize();
			
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
			
			m_BroadcastRec.encode(bytes, pos);
			pos += m_BroadcastRec.getSize();
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
			
			m_BroadcastRec.decode(bytes, pos);
			pos += m_BroadcastRec.getSize();
		}
		
		public BroadcastGlobal.Body assign(Body value)
		{
			m_BroadcastRec = value.m_BroadcastRec;
			m_BroadcastRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(Body value)
		{
			if (!m_BroadcastRec.isEqual(value.getBroadcastRec()))
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
			m_BroadcastRec = new BroadcastRec();
			m_BroadcastRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_BroadcastRec = new BroadcastRec();
			m_BroadcastRec.setParent(this);
			
			/// Copy the values
			m_BroadcastRec = value.m_BroadcastRec;
			m_BroadcastRec.setParent(this);
			/// This code is currently not supported
		}
		
		public void finalize()
		{
		}
		
	}
	protected Body m_Body;
	public BroadcastGlobal.Body getBody()
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
		
		m_Body.decode(bytes, pos);
		pos += m_Body.getSize();
	}
	
	public BroadcastGlobal setAs(BroadcastGlobal value)
	{
		m_Body = value.m_Body;
		
		return this;
	}
	
	public boolean isEqual(BroadcastGlobal value)
	{
		if (!m_Body.isEqual(value.getBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public boolean notEquals(BroadcastGlobal value)
	{
		return !isEqual(value);
	}
	
	public BroadcastGlobal()
	{
		m_Body = new Body();
		m_Name = "BroadcastGlobal";
	}
	
	public  BroadcastGlobal(BroadcastGlobal value)
	{
		/// Initiliaze the protected variables
		m_Body = new Body();
		m_Name = "BroadcastGlobal";
		
		/// Copy the values
		m_Body = value.m_Body;
	}
	
}
