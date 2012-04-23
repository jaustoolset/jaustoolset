/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, United States Government
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the United States Government nor the names of 
its contributors may be used to endorse or promote products derived from 
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
*********************  END OF LICENSE ***********************************/

using System;
using System.Collections;

/**
 * <summary>The wrapper class for all outgoing messages.</summary>
 * <author>Gina Nearing</author>
 * <date>4/1/11</date>
 */
namespace JTS
{
	public class Send_1_0 : InternalEvent
	{
		protected const int UINT_BYTES = 4;
		protected const int USHORT_BYTES = 2;
		protected const int BYTE_BYTES = 1;
        protected const int DEFAULT_BIT_ARRAY_LEN = 10;
		public class Body
		{
			public class SendRec
			{
				
				public class MessagePayload
				{
					protected uint m_Length;
					protected byte[] m_Data;

                    /// <summary>
                    /// Retrieve the length of the stored array of data.
                    /// </summary>
                    /// <returns>length of m_Data</returns>
					public uint getLength()
					{
						return m_Length;
					}
					
                    /// <summary>
                    /// Retrieves the message data.
                    /// </summary>
                    /// <returns>m_Data</returns>
					public byte[] getData()
					{
						return m_Data;
					}
					
                    /// <summary>
                    /// Sets the payload to the given data.
                    /// </summary>
                    /// <param name="length">the number of bytes in the data</param>
                    /// <param name="data">the encoded message data</param>
					public void set(uint length, byte[] data)
					{
						m_Length = length;
						m_Data = data;
					}
					
                    /// <summary>
                    /// Provides the combined number of bytes that the
                    /// message data and uint storing the number of bytes
                    /// int the message data contain.
                    /// </summary>
                    /// <returns>The number of bytes in the message payload.</returns>
					public int getSize()
					{
						// bytes in m_Length + bytes in buffer
						return UINT_BYTES + (int) m_Length;
					}
					 
					
                    /// <summary>
                    /// Encodes the message payload in the given buffer.
                    /// </summary>
                    /// <param name="bytes">The buffer to contain the payload data</param>
                    /// <param name="pos">The position in the buffer to begin encoding at.</param>
					public void encode(byte[] bytes, int pos)
					{
						if(bytes == null)
						{
							return;
						}

                        bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes(m_Length), pos, JausUtils.UINT_BYTES, false);
                        pos += JausUtils.UINT_BYTES;

                        bytes = JausUtils.addToBuffer(bytes, m_Data, pos, (int)m_Length, true);
                        pos += (int)m_Length;
						
					}
					
					/// <summary>
					/// Removes the message payload from the given buffer.
					/// </summary>
					/// <param name="bytes">The buffer containing the payload data.</param>
					/// <param name="pos">The position in the buffer where the data starts.</param>
					public void decode(byte[] bytes, int pos)
					{
                        if (bytes == null)
                        {
                            return;
                        }

                        m_Length = BitConverter.ToUInt32(JausUtils.getFromBuffer(bytes, pos, JausUtils.UINT_BYTES, false), 0);
                        pos += JausUtils.UINT_BYTES;

                        m_Data = JausUtils.getFromBuffer(bytes, pos, (int) m_Length, true);
                        pos += JausUtils.UINT_BYTES;
					}
					
					/// <summary>
					/// Set this payload equal to the given payload.
					/// </summary>
					/// <param name="value">The value that this payload will be set to.</param>
					/// <returns>this payload.</returns>
					public MessagePayload setAs(MessagePayload value)
					{
						this.m_Length = value.m_Length;
						this.m_Data = value.m_Data;
						
						return this;
					}
					
                    /// <summary>
                    /// Overload the == operator.
                    /// </summary>
                    /// <param name="a">First value to compare.</param>
                    /// <param name="b">Second value to compare.</param>
                    /// <returns></returns>
					public static bool operator ==(MessagePayload a, MessagePayload b)
					{
						if(a.m_Length != b.m_Length)
						{
							return false;
						}
						
						if((a.m_Data != null) && (b.m_Data != null))
						{
							for(int i = 0; i<a.m_Length; i++)
							{
								if(a.m_Data[i] != b.m_Data[i])
								{	
									return false;
								}
							}
						}
						// This case should never be true since it should not be possible
						// for the two variables to have equal lengths but one has no data.
						// This check is placed here as a secondary check.
						else if((a.m_Data != null) || (b.m_Data != null))
						{
							return false;
						}

						return true;
					}
					
					/*
					 * Operator overload !=
					 */ 
					public static bool operator !=(MessagePayload a, MessagePayload b)
					{
						return !(a == b);
					}
					
					/*
					 * Default constructor
					 */
					public MessagePayload()
					{
						m_Length = MAX_JTS_MESSAGE_SIZE;
						m_Data = new byte[m_Length];
					}
					
					/*
					 * Copy constructor
					 */
					public MessagePayload(MessagePayload value)
					{
						m_Length = value.m_Length;
						m_Data = value.m_Data;
					}
				}
				
				protected BitArray m_PresenceVector;
				protected ushort m_DestSubsystemID;
				protected byte m_DestNodeID;
				protected byte m_DestComponentID;
				protected ushort m_SrcSubsystemID;
				protected byte m_SrcNodeID;
				protected byte m_SrcComponentID;
				protected byte m_Priority;
				protected MessagePayload m_MessagePayload;

				/*
				 * set index in presence vector.
				 */
				public void setPresenceVector(int index)
				{
					m_PresenceVector.Set(index, true);
				}
				
				/*
				 * get presence vector from the given integer representatio
				 */
				public uint getPresenceVector()
				{
					return JausUtils.getPVint(m_PresenceVector);
				}
				
				/*
				 * 
				 */
				public bool checkPresenceVector(int index)
				{
					return m_PresenceVector.Get(index);
				}
				/*
				 * set destination subystem ID - part of the message ID
				 */
				public void setDestSubsystemID(ushort value)
				{
					m_DestSubsystemID = value;
				}
				
				/*
				 * get destination subystem ID - part of the message ID
				 */
				public ushort getDestSubsystemID()
				{
					return m_DestSubsystemID;
				}
				
				/*
				 * set destination node ID - part of the message ID
				 */
				public void setDestNodeID(byte value)
				{
					m_DestNodeID = value;
				}
				
				/*
				 * get destination node ID - part of the message ID
				 */
				public byte getDestNodeID()
				{
					return m_DestNodeID;
				}
				
				/*
				 * set destination component ID - part of the message ID
				 */
				public void setDestComponentID(byte value)
				{
					m_DestComponentID = value;
				}
				
				/*
				 * get destination component ID - part of the message ID
				 */
				public byte getDestComponentID()
				{
					return m_DestComponentID;
				}
				
				/*
				 * Check presence vector to see if there is a source subsystem ID to use. 
				 */
				public bool isSrcSubsystemIDValid()
				{
					if(checkPresenceVector(0))
					{
						return true;
					}
					return false;
				}
				
				/*
				 * set source subystem ID - part of the message ID
				 */
				public void setSrcSubsystemID(ushort value)
				{
					m_SrcSubsystemID = value;
					setPresenceVector(0);
				}
				
				/*
				 * get source subystem ID - part of the message ID
				 */
				public ushort getSrcSubsystemID()
				{
					return m_SrcSubsystemID;
				}
				
				/*
				 * Check presence vector to see if there is a source node ID to use. 
				 */
				public bool isSrcNodeIDValid()
				{
					if(checkPresenceVector(1))
					{
						return true;
					}
					return false;
				}
				
				/*
				 * set source node ID - part of the message ID
				 */
				public void setSrcNodeID(byte value)
				{
					m_SrcNodeID = value;
					setPresenceVector(1);
				}
				
				/*
				 * get source node ID - part of the message ID
				 */
				public byte getSrcNodeID()
				{
					return m_SrcNodeID;
				}			
				
				
				/*
				 * set source component ID - part of the message ID
				 */
				public void setSrcComponentID(byte value)
				{
					m_SrcComponentID = value;
				}
				
				/*
				 * get source component ID - part of the message ID
				 */
				public byte getSrcComponentID()
				{
					return m_SrcComponentID;
				}	
				
				/*
				* Check the presence vector to see if there is a priority
				*		to use.
				*/
				public bool isPriorityValid()
				{
					if(checkPresenceVector(2))
					{
						return true;
					}
					return false;
				}				
				
				/*
				 * set the priority
				 */
				public void setPriority(byte value)
				{
					if (((value >= 0) && (value <= 3)) || (value == 0) || (value == 1) || (value == 2) || (value == 3)){
						m_Priority = value;
						setPresenceVector(2);
					}
				}
				
				/*
				 * get the priority
				 */
				public byte getPriority()
				{
					return m_Priority;
				}
				
				/*
				 * return the message payload
				 */
				public MessagePayload getMessagePayload()
				{
					return m_MessagePayload;
				}
				
				/*
				 * Set the message payload using the given value.
				 */
				public void setMessagePayload(MessagePayload value)
				{
					m_MessagePayload.setAs(value);
				}
				
				/*
				 * returns the sum of the bytes of all of the variables stored
		     	 * 	in this class will take in the buffer.
				 */
				public int getSize()
				{
					int size = 0;
					
					// DestSubsystemID + DestNodeID + Dest ComponentID + BitSet + SrcComponentID
                    size += JausUtils.USHORT_BYTES 
                        + JausUtils.BYTE_BYTES 
                        + JausUtils.BYTE_BYTES 
                        + JausUtils.UINT_BYTES
                        + JausUtils.USHORT_BYTES;
					
					if(isSrcSubsystemIDValid())
					{
                        size += JausUtils.USHORT_BYTES;
					}
					
					if(isSrcNodeIDValid())
					{
                        size += JausUtils.BYTE_BYTES;
					}
					
					if(isPriorityValid())
					{
                        size += JausUtils.BYTE_BYTES;
					}
					
					size += m_MessagePayload.getSize();
					
					return size;					   
				}
				
				/*
				 * Wraps all of the data in the class into the buffer. The number of bytes
				 * 	each item occupies is based on the objects size in the JSIDL schema.
				 */
				public void encode(byte[] bytes, int pos)
				{
                    if (bytes == null)
                    {
                        return;
                    }

                    bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes(JausUtils.getPVint(m_PresenceVector)), pos, JausUtils.UINT_BYTES, false);
                    pos += JausUtils.UINT_BYTES;

                    bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes(m_DestSubsystemID), pos, JausUtils.USHORT_BYTES, false);
                    pos += JausUtils.USHORT_BYTES;

                    bytes[pos] = m_DestComponentID;
                    pos += JausUtils.BYTE_BYTES;

                    bytes[pos] = m_DestComponentID;
                    pos += JausUtils.BYTE_BYTES;

                    if (isSrcSubsystemIDValid())
                    {
                        bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes(m_SrcSubsystemID), pos, JausUtils.USHORT_BYTES, false);
                        pos += JausUtils.USHORT_BYTES;
                    }

                    bytes[pos] = m_SrcComponentID;
                    pos += JausUtils.BYTE_BYTES;

                    if (isSrcNodeIDValid())
                    {
                        bytes[pos] = m_SrcNodeID;
                        pos += JausUtils.BYTE_BYTES;
                    }

                    m_MessagePayload.encode(bytes, pos);
                    pos += m_MessagePayload.getSize();


				}
				
				
				/*
				 * Retrieves all of the data in the class from the buffer. The number of bytes
				 * 	each item occupies is based on the objects size in the JSIDL schema.
				 */
				public void decode(byte[] bytes, int pos)
				{
                    if (bytes == null)
                    {
                        return;
                    }

                    m_PresenceVector = JausUtils.setPV(BitConverter.ToUInt32(JausUtils.getFromBuffer(bytes, pos, JausUtils.ULONG_BYTES, false), 0));
                    pos += JausUtils.UINT_BYTES;

                    m_DestSubsystemID = BitConverter.ToUInt16(JausUtils.getFromBuffer(bytes, pos, JausUtils.USHORT_BYTES, false), 0);
                    pos += JausUtils.USHORT_BYTES;

                    m_DestNodeID = bytes[pos];
                    pos += JausUtils.BYTE_BYTES;

                    m_DestComponentID = bytes[pos];
                    pos += JausUtils.BYTE_BYTES;

                    if (isSrcSubsystemIDValid())
                    {
                        m_SrcSubsystemID = BitConverter.ToUInt16(JausUtils.getFromBuffer(bytes, pos, JausUtils.USHORT_BYTES, false), 0);
                        pos += JausUtils.USHORT_BYTES;
                    }
                    if (isSrcNodeIDValid())
                    {
                        m_SrcNodeID = bytes[pos];
                        pos += JausUtils.BYTE_BYTES;
                    }
                    if (isPriorityValid())
                    {
                        m_Priority = bytes[pos];
                        pos += JausUtils.BYTE_BYTES;
                    }

                    m_MessagePayload.decode(bytes, pos);
                    pos += m_MessagePayload.getSize();

				}		
				
				/*
				 * Sets this SendRec with the values in the given SendRec
				 */
				public SendRec setAs(SendRec value)
				{
					m_PresenceVector = value.m_PresenceVector;
					m_DestSubsystemID = value.m_DestSubsystemID;
					m_DestNodeID = value.m_DestNodeID;
					m_DestComponentID = value.m_DestComponentID;
					m_SrcSubsystemID = value.m_SrcSubsystemID;
					m_SrcNodeID = value.m_SrcNodeID;
					m_SrcComponentID = value.m_SrcComponentID;
					m_Priority = value.m_Priority;
					m_MessagePayload.setAs(value.getMessagePayload());
		
					return this;
				}	
				
				/*
				 * Operator overload ==
				 */
				public static bool operator == (SendRec a, SendRec b)
				{
					if ((a.m_DestSubsystemID != b.m_DestSubsystemID) ||
					(a.m_DestNodeID != b.m_DestNodeID) ||
					(a.m_DestComponentID != b.m_DestComponentID) ||
					(a.m_SrcSubsystemID != b.m_SrcSubsystemID) ||
					(a.m_SrcNodeID != b.m_SrcNodeID) ||
					(a.m_SrcComponentID != b.m_SrcComponentID) ||
					(a.m_Priority != b.m_Priority) ||
					(a.m_MessagePayload != b.m_MessagePayload))
					{
						return false;
					}
	
					return true;
				}
				
				/*
				 * Operator overload !=
				 */
				public static bool operator != (SendRec a, SendRec b)
				{
					return !(a == b);
				}
				
				/*
				 * 
				 */
				public SendRec()
				{
					m_PresenceVector = new BitArray(DEFAULT_BIT_ARRAY_LEN);
					m_DestSubsystemID = 0;
					m_DestNodeID = 0;
					m_DestComponentID = 0;
					m_SrcSubsystemID = 0;
					m_SrcNodeID = 0;
					m_SrcComponentID = 0;
					m_Priority = 0;
					m_MessagePayload = new MessagePayload();					
				}
				
				/*
				 * 
				 */
				public SendRec(SendRec value)
				{
					m_PresenceVector 	= JausUtils.setPV(value.getPresenceVector());
					m_DestSubsystemID 	= value.getDestSubsystemID();
					m_DestNodeID 		= value.getDestNodeID();
					m_DestComponentID 	= value.getDestComponentID();
					m_SrcSubsystemID 	= value.getSrcSubsystemID();
					m_SrcNodeID 		= value.getSrcNodeID();
					m_SrcComponentID 	= value.getSrcComponentID();
					m_Priority 			= value.getPriority();
					m_MessagePayload.setAs(value.getMessagePayload());					
				}				
			}
			
			protected SendRec m_SendRec;
			
			/*
			 * return the SendRec stored in the body.
			 */
			public SendRec getSendRec()
			{
				return m_SendRec;
			}
			
			/*
			 * set the SendRec based on the given value
			 */
			public void setSendRec(SendRec value)
			{
				m_SendRec.setAs(value);
			}
			
			/*
			 * retrieve the number of bytes teh SendRec object will take on the buffer.
			 */
			public int getSize()
			{
				return m_SendRec.getSize();
			}
			
			/*
			 * encode the m_ReceiveRec into the buffer at the given position.
			 */
			public void encode(byte[] bytes, int pos)
			{
				if(bytes == null)
				{
					return;
				}
				m_SendRec.encode(bytes, pos);
				pos += m_SendRec.getSize();				
			}
			
			/*
			 * Retrieves the m_ReceiveRec from the buffer at the given position.
			 */
			public void decode(byte[] bytes, int pos)
			{
				if(bytes == null)
				{
					return;
				}
				
				m_SendRec.decode(bytes, pos);
				pos += m_SendRec.getSize();					
			}
			
			/*
			 * This code is not currently supported.
			 */
			public void setBody( Body value)
			{
							
			}
			
			/*
			 * Operator overload ==
			 * Currently not supported
			 */
			public static bool operator == (Body a, Body b)
			{
				return false;
			}
			
			/*
			 * Operator overload !=
			 * Currently not supported
			 */
			public static bool operator != (Body a, Body b)
			{
				return !(a == b);
			}			
		
			/*
			 * Constructor
			 */
			public Body()
			{
				m_SendRec = new SendRec();
			}
			
			/*
			 * Copy constructor - unsupported
			 */
			public Body (Body value)
			{
				
			}
		}
			
		protected Body m_Body;
				
		/*
		* return m_Body
		*/
		public Body getBody()
		{
			return m_Body;
		}
		/*
		* set the Body to the given value.
		*/
		public void setBody(Body value)
		{
			m_Body.setBody(value);
		}
	
		/*
		* return the number of bytes the body will take in the buffer.
		*/
		public int getSize()
		{
			return m_Body.getSize();
		}
		/*
		* Encodes the body into the buffer at the given position. 
		*/
		public void encode(byte[] bytes, int pos)
		{
			if (bytes == null)
			{
				return;
			}
		
			m_Body.encode(bytes, pos);
			pos += m_Body.getSize();		
		}
		/*
		* Retrieves the Body from the buffer at the given location.
		*/
		public void decode(byte[] bytes, int pos)
		{
			if (bytes == null)
			{
				return;
			}
		
			m_Body.decode(bytes, pos);
			pos += m_Body.getSize();	
		}

		/*
		* Operator overload == 
		*/
        public static bool operator ==(Send_1_0 a, Send_1_0 b)
		{
			if (a.m_Body != b.m_Body)
			{
				return false;
			}
			return true;
		}
		/*
		* Operator overload !=
		*/
        public static bool operator !=(Send_1_0 a, Send_1_0 b)
		{
			return ! (a == b);
		}
		/*
		* Constructor
		*/
		public Send_1_0()
		{
			m_Body = new Body();
			m_Name = "Send";
		}
		/*
		* Copy Constructor
		*/
		public Send_1_0(Send_1_0 value)
		{
			m_Name = "Send";
			
			/// Copy the values
			m_Body.setBody(value.getBody());
		}
	}
}

