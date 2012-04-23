using JTS;
using System;
using System.Collections;

namespace urn_jaus_jss_core_AccessControl_1_1
{

public class BroadcastLocal : InternalEvent
{
	public class Body
	{
		public class BroadcastRec
		{
			public class DestinationID
			{
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
				
				public uint getComponentID()
				{
					BitArray bitFieldBitSet = JausUtils.setPV(m_SubFields);
					BitArray subFieldBitSet = new BitArray(8);
					int i = 0;
					
					for (int index = 0; index <= 7; index++)
					{
					    subFieldBitSet.Set(i++, bitFieldBitSet.Get(index));
					}
					
					return (uint) JausUtils.getPVint(subFieldBitSet);
				}
				
				public int setComponentID(uint value)
				{
					if (((value >= 1) && (value <= 255)))
					{
						BitArray bfbs = JausUtils.setPV(m_SubFields);
						BitArray sfbs = new BitArray(8);
						sfbs = JausUtils.setPV(value);
						int i = 0;
						
						for (int index = 0; index <= 7; index++)
						{
						    bfbs.Set(index, sfbs.Get(i++));
						}
						
						m_SubFields = (uint) JausUtils.getPVint(bfbs);
						setParentPresenceVector();
						return 0;
					}
					return 1;
				}
				
				public uint getNodeID()
				{
					BitArray bitFieldBitSet = JausUtils.setPV(m_SubFields);
					BitArray subFieldBitSet = new BitArray(8);
					int i = 0;
					
					for (int index = 8; index <= 15; index++)
					{
					    subFieldBitSet.Set(i++, bitFieldBitSet.Get(index));
					}
					
					return (uint) JausUtils.getPVint(subFieldBitSet);
				}
				
				public int setNodeID(uint value)
				{
					if (((value >= 1) && (value <= 255)))
					{
						BitArray bfbs = JausUtils.setPV(m_SubFields);
						BitArray sfbs = new BitArray(8);
						sfbs = JausUtils.setPV(value);
						int i = 0;
						
						for (int index = 8; index <= 15; index++)
						{
						    bfbs.Set(index, sfbs.Get(i++));
						}
						
						m_SubFields = (uint) JausUtils.getPVint(bfbs);
						setParentPresenceVector();
						return 0;
					}
					return 1;
				}
				
				public uint getSubsystemID()
				{
					BitArray bitFieldBitSet = JausUtils.setPV(m_SubFields);
					BitArray subFieldBitSet = new BitArray(16);
					int i = 0;
					
					for (int index = 16; index <= 31; index++)
					{
					    subFieldBitSet.Set(i++, bitFieldBitSet.Get(index));
					}
					
					return (uint) JausUtils.getPVint(subFieldBitSet);
				}
				
				public int setSubsystemID(uint value)
				{
					if (((value >= 1) && (value <= 65535)))
					{
						BitArray bfbs = JausUtils.setPV(m_SubFields);
						BitArray sfbs = new BitArray(16);
						sfbs = JausUtils.setPV(value);
						int i = 0;
						
						for (int index = 16; index <= 31; index++)
						{
						    bfbs.Set(index, sfbs.Get(i++));
						}
						
						m_SubFields = (uint) JausUtils.getPVint(bfbs);
						setParentPresenceVector();
						return 0;
					}
					return 1;
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
					
					size += JausUtils.getNumBytes("uint");
					
					return size;
				}
				
				public void encode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					uint m_SubFieldsTemp;
					
					bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((uint)m_SubFields), pos, (int)JausUtils.UINT_BYTES, false);
					pos += JausUtils.UINT_BYTES;
				}
				
				public void decode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					m_SubFields = BitConverter.ToUInt32(JausUtils.getFromBuffer(bytes, pos, JausUtils.UINT_BYTES, false), 0);
					pos += JausUtils.UINT_BYTES;
				}
				
				public BroadcastLocal.Body.BroadcastRec.DestinationID  setDestinationID(DestinationID value)
				{
					this.m_SubFields = value.m_SubFields;
					
					return this;
				}
				
				public bool isEqual(DestinationID value)
				{
					return (this.m_SubFields == value.getSubsystemID());
				}
				
				public bool notEquals(DestinationID value)
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
				
				 ~DestinationID()
				{
				}
				
			
				BroadcastRec m_parent;
				protected uint m_SubFields;
			}
			public class SourceID
			{
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
				
				public uint getComponentID()
				{
					BitArray bitFieldBitSet = JausUtils.setPV(m_SubFields);
					BitArray subFieldBitSet = new BitArray(8);
					int i = 0;
					
					for (int index = 0; index <= 7; index++)
					{
					    subFieldBitSet.Set(i++, bitFieldBitSet.Get(index));
					}
					
					return (uint) JausUtils.getPVint(subFieldBitSet);
				}
				
				public int setComponentID(uint value)
				{
					if (((value >= 1) && (value <= 255)))
					{
						BitArray bfbs = JausUtils.setPV(m_SubFields);
						BitArray sfbs = new BitArray(8);
						sfbs = JausUtils.setPV(value);
						int i = 0;
						
						for (int index = 0; index <= 7; index++)
						{
						    bfbs.Set(index, sfbs.Get(i++));
						}
						
						m_SubFields = (uint) JausUtils.getPVint(bfbs);
						setParentPresenceVector();
						return 0;
					}
					return 1;
				}
				
				public uint getNodeID()
				{
					BitArray bitFieldBitSet = JausUtils.setPV(m_SubFields);
					BitArray subFieldBitSet = new BitArray(8);
					int i = 0;
					
					for (int index = 8; index <= 15; index++)
					{
					    subFieldBitSet.Set(i++, bitFieldBitSet.Get(index));
					}
					
					return (uint) JausUtils.getPVint(subFieldBitSet);
				}
				
				public int setNodeID(uint value)
				{
					if (((value >= 1) && (value <= 255)))
					{
						BitArray bfbs = JausUtils.setPV(m_SubFields);
						BitArray sfbs = new BitArray(8);
						sfbs = JausUtils.setPV(value);
						int i = 0;
						
						for (int index = 8; index <= 15; index++)
						{
						    bfbs.Set(index, sfbs.Get(i++));
						}
						
						m_SubFields = (uint) JausUtils.getPVint(bfbs);
						setParentPresenceVector();
						return 0;
					}
					return 1;
				}
				
				public uint getSubsystemID()
				{
					BitArray bitFieldBitSet = JausUtils.setPV(m_SubFields);
					BitArray subFieldBitSet = new BitArray(16);
					int i = 0;
					
					for (int index = 16; index <= 31; index++)
					{
					    subFieldBitSet.Set(i++, bitFieldBitSet.Get(index));
					}
					
					return (uint) JausUtils.getPVint(subFieldBitSet);
				}
				
				public int setSubsystemID(uint value)
				{
					if (((value >= 1) && (value <= 65535)))
					{
						BitArray bfbs = JausUtils.setPV(m_SubFields);
						BitArray sfbs = new BitArray(16);
						sfbs = JausUtils.setPV(value);
						int i = 0;
						
						for (int index = 16; index <= 31; index++)
						{
						    bfbs.Set(index, sfbs.Get(i++));
						}
						
						m_SubFields = (uint) JausUtils.getPVint(bfbs);
						setParentPresenceVector();
						return 0;
					}
					return 1;
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
					
					size += JausUtils.getNumBytes("uint");
					
					return size;
				}
				
				public void encode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					uint m_SubFieldsTemp;
					
					bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((uint)m_SubFields), pos, (int)JausUtils.UINT_BYTES, false);
					pos += JausUtils.UINT_BYTES;
				}
				
				public void decode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					m_SubFields = BitConverter.ToUInt32(JausUtils.getFromBuffer(bytes, pos, JausUtils.UINT_BYTES, false), 0);
					pos += JausUtils.UINT_BYTES;
				}
				
				public BroadcastLocal.Body.BroadcastRec.SourceID  setSourceID(SourceID value)
				{
					this.m_SubFields = value.m_SubFields;
					
					return this;
				}
				
				public bool isEqual(SourceID value)
				{
					return (this.m_SubFields == value.getSubsystemID());
				}
				
				public bool notEquals(SourceID value)
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
				
				 ~SourceID()
				{
				}
				
			
				BroadcastRec m_parent;
				protected uint m_SubFields;
			}
			public class MessagePayload
			{
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
				
				public uint getLength()
				{
					return m_Length;
				}
				
				public byte[]  getData()
				{
					return m_Data;
				}
				
				public void set(uint length, byte[] data)
				{
					m_Length = length;
					
					m_Data = null;
					
					if (m_Length > 0)
					{
						m_Data = new byte[length];
						for(int i = 0; i< m_Length; i++)
							m_Data[i] = data[i];
					}
					
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
					
					size += JausUtils.getNumBytes("uint");
					size += (int)m_Length;
					
					return size;
				}
				
				public void encode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((uint)m_Length), pos, (int)JausUtils.UINT_BYTES, false);
					pos += JausUtils.UINT_BYTES;
					
					bytes = JausUtils.addToBuffer(bytes, m_Data, pos, (int)m_Length, true);
					
					pos += (int)m_Length;
				}
				
				public void decode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					
					m_Length = BitConverter.ToUInt32(JausUtils.getFromBuffer(bytes, pos, JausUtils.UINT_BYTES, false), 0);
					pos += JausUtils.UINT_BYTES;
					
					m_Data = null;
					
					if (m_Length > 0)
					{
						m_Data = new byte[(int)m_Length];
						m_Data = JausUtils.getFromBuffer(bytes, pos, (int)m_Length, true);
						pos += (int)m_Length;
					}
				}
				
				public BroadcastLocal.Body.BroadcastRec.MessagePayload  setMessagePayload(MessagePayload value)
				{
					this.m_Length = value.m_Length;
					
					m_Data = null;
					
					if (m_Length > 0)
					{
						m_Data = new byte[(int)m_Length];
						this.m_Data = value.m_Data;
					}
					
					return this;
				}
				
				public bool isEqual(MessagePayload value)
				{
					if (this.m_Length != value.m_Length)
					{
						return false;
					}
					
					if ((this.m_Data != null) && (value.m_Data!= null))
					{
						for(int i = 0; i < value.m_Length; i++)
						{
							if (this.m_Data[i] != value.m_Data[i])
							{
								return false;
							}
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
				
				public bool notEquals(MessagePayload value)
				{
					return !this.isEqual(value);
				}
				
				public MessagePayload()
				{
					m_parent = null;
					m_Length = 0;
					m_Data = null;
				}
				
				public MessagePayload(MessagePayload value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_Length = 0;
					m_Data = null;
					
					/// Copy the values
					this.m_Length = value.m_Length;
					
					m_Data = null;
					
					if (m_Length > 0)
					{
						m_Data = new byte[(int)m_Length];
						this.m_Data = value.m_Data;
					}
				}
				
				 ~MessagePayload()
				{
				}
				
			
				BroadcastRec m_parent;
				uint m_Length;
				byte[] m_Data;
			}
		
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
			
			public byte getPresenceVector()
			{
				return m_PresenceVector;
			}
			
			public int setPresenceVector(int index)
			{
				BitArray pvBitSet = JausUtils.setPV((uint) m_PresenceVector);
				
				pvBitSet.Set(index, true);
				m_PresenceVector = (byte)JausUtils.getPVint(pvBitSet);
				return 0;
			}
			
			public bool checkPresenceVector(int index)
			{
				BitArray pvBitSet = JausUtils.setPV(m_PresenceVector);
				
				return pvBitSet.Get(index);
			}
			
			public BroadcastLocal.Body.BroadcastRec.DestinationID getDestinationID()
			{
				return m_DestinationID;
			}
			
			public void setDestinationID(DestinationID value)
			{
				m_DestinationID = value;
				setParentPresenceVector();
			}
			
			public bool isSourceIDValid()
			{
				if (checkPresenceVector(0))
				{
					return true;
				}
				return false;
			}
			
			public BroadcastLocal.Body.BroadcastRec.SourceID getSourceID()
			{
				return m_SourceID;
			}
			
			public void setSourceID(SourceID value)
			{
				m_SourceID = value;
				setPresenceVector(0);
				setParentPresenceVector();
			}
			
			public bool isPriorityValid()
			{
				if (checkPresenceVector(1))
				{
					return true;
				}
				return false;
			}
			
			public byte getPriority()
			{
				return m_Priority;
			}
			
			public void setPriority(byte value)
			{
				if (((value >= 0) && (value <= 3)) || (value == 0) || (value == 1) || (value == 2) || (value == 3))
				{
					m_Priority = value;
					setPresenceVector(1);
					setParentPresenceVector();
				}
				return;
			}
			
			public BroadcastLocal.Body.BroadcastRec.MessagePayload getMessagePayload()
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
			 * C Sharp, but the bytes expected on the wire.
			 * 
			 * @return
			 */
			public int getSize()
			{
				int size = 0;
				
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
			
			public void encode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				bytes[pos] = (byte)m_PresenceVector;
				pos += JausUtils.BYTE_BYTES;
				m_DestinationID.encode(bytes, pos);
				pos += m_DestinationID.getSize();
				if (checkPresenceVector(0))
				{
					m_SourceID.encode(bytes, pos);
					pos += m_SourceID.getSize();
				}
				if (checkPresenceVector(1))
				{
					bytes[pos] = (byte)m_Priority;
					pos += JausUtils.BYTE_BYTES;
				}
				m_MessagePayload.encode(bytes, pos);
				pos += m_MessagePayload.getSize();
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_PresenceVector = bytes[pos];
				pos += JausUtils.BYTE_BYTES;
				m_DestinationID.decode(bytes, pos);
				pos += m_DestinationID.getSize();
				if (checkPresenceVector(0))
				{
					m_SourceID.decode(bytes, pos);
					pos += m_SourceID.getSize();
				}
				if (checkPresenceVector(1))
				{
					m_Priority = bytes[pos];
					pos += JausUtils.BYTE_BYTES;
				}
				m_MessagePayload.decode(bytes, pos);
				pos += m_MessagePayload.getSize();
			}
			
			public BroadcastLocal.Body.BroadcastRec  setBroadcastRec(BroadcastRec value)
			{
				m_PresenceVector = value.m_PresenceVector;
				m_DestinationID = value.getDestinationID();
				m_SourceID = value.getSourceID();
				m_Priority = value.m_Priority;
				m_MessagePayload = value.getMessagePayload();
				
				return this;
			}
			
			public bool isEqual(BroadcastRec value)
			{
				if (m_PresenceVector != value.m_PresenceVector)
				{
					return false;
				}
				
				if (!this.getDestinationID().isEqual(value.getDestinationID()))
				{
					return false;
				}
				
				if (!this.getSourceID().isEqual(value.getSourceID()))
				{
					return false;
				}
				if (this.getPriority() != value.getPriority())
				{
					return false;
				}
				
				if (!this.getMessagePayload().isEqual(value.getMessagePayload()))
				{
					return false;
				}
				
				return true;
			}
			
			public bool notEquals(BroadcastRec value)
			{
				return !this.isEqual(value);
			}
			
			public BroadcastRec()
			{
				m_parent = null;
				m_PresenceVector = 0;
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
				m_PresenceVector = 0;
				m_DestinationID = new DestinationID();
				m_DestinationID.setParent(this);
				m_SourceID = new SourceID();
				m_SourceID.setParent(this);
				m_Priority = 0;
					m_MessagePayload = new MessagePayload();
				m_MessagePayload.setParent(this);
				
				/// Copy the values
				m_PresenceVector = value.m_PresenceVector;
				m_DestinationID = value.getDestinationID();
				m_SourceID = value.getSourceID();
				m_Priority = value.m_Priority;
				m_MessagePayload = value.getMessagePayload();
			}
			
			 ~BroadcastRec()
			{
			}
			
		
			Body m_parent;
			byte m_PresenceVector;
			protected DestinationID m_DestinationID = new  DestinationID();
			protected SourceID m_SourceID = new  SourceID();
			protected byte m_Priority;
			protected MessagePayload m_MessagePayload = new  MessagePayload();
		}
	
		public BroadcastLocal.Body.BroadcastRec getBroadcastRec()
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
		 * C Sharp, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public int getSize()
		{
			int size = 0;
			
			size += m_BroadcastRec.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_BroadcastRec.encode(bytes, pos);
			pos += m_BroadcastRec.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_BroadcastRec.decode(bytes, pos);
			pos += m_BroadcastRec.getSize();
		}
		
		public BroadcastLocal.Body  setBody(Body value)
		{
			m_BroadcastRec = value.getBroadcastRec();
			m_BroadcastRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public bool isEqual(Body value)
		{
			if (!this.getBroadcastRec().isEqual(value.getBroadcastRec()))
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
			m_BroadcastRec = new BroadcastRec();
			m_BroadcastRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_BroadcastRec = new BroadcastRec();
			m_BroadcastRec.setParent(this);
			
			/// Copy the values
			m_BroadcastRec = value.getBroadcastRec();
			m_BroadcastRec.setParent(this);
			/// This code is currently not supported
		}
		
		 ~Body()
		{
		}
		
	
		protected BroadcastRec m_BroadcastRec = new  BroadcastRec();
	}
	protected Body m_Body = new  Body();
	public BroadcastLocal.Body getBody()
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
		
		size += m_Body.getSize();
		
		return size;
	}
	
	public override void encode(byte[] bytes, int pos)
	{
		
		if (bytes == null)
		{
			return;
		}
		
		m_Body.encode(bytes, pos);
		pos += m_Body.getSize();
		
	}
	
	public override void decode(byte[] bytes, int pos)
	{
		
		if (bytes == null)
		{
			return;
		}
		
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
	
	public BroadcastLocal setAs(BroadcastLocal value)
	{
		m_Body = value.m_Body;
		
		return this;
	}
	
	public bool  isEqual(BroadcastLocal value)
	{
		if (!this.getBody().isEqual(value.getBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public bool  notEquals(BroadcastLocal value)
	{
		return !this.isEqual(value);
	}
	
	public BroadcastLocal()
	{
		m_Body = new Body();
		m_Name = "BroadcastLocal";
	}
	
	public  BroadcastLocal(BroadcastLocal value)
	{
		/// Initiliaze the protected variables
		m_Body = new Body();
		m_Name = "BroadcastLocal";
		
		/// Copy the values
		m_Body = value.m_Body;
	}
	
}

}
