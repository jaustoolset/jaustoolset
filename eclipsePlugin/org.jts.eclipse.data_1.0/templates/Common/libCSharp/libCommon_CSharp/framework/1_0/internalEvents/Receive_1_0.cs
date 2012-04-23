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
/**
 * <summary>The message wrapper for incoming messages</summary>
 * <author>Gina Nearing</author>
 * <date>4/1/11</date>
 */
namespace JTS
{
	public class Receive_1_0 : InternalEvent
	{		
		public class Body
		{
			public class ReceiveRec
			{
				public class MessagePayload
				{
					protected uint m_Length;
					protected byte[] m_Data;
					
					/*
					 * Return the length of the byte buffer.
					 */
					public uint getLength()
					{
						return m_Length;
					}
					
					/*
					 * Return a copy of the byte buffer.
					 */
					public byte[] getData()
					{
						return m_Data;
					}
					
					/*
					 * Sets the lenght and the size of the byte buffer to that length.
					 * 
					 */
					public void set(uint length, byte[] data)
					{
						m_Length = length;
                        m_Data = new byte[(int)m_Length];
                        for (int i = 0; i < (int)m_Length; i++)
                        {
                            m_Data[i] = data[i];
                        }
						//m_Data = data;
					}
					
					/*
					 * Return the number of bytes m_Data and m_Length will take
					 *  when encoded onto a byte buffer.
					 * 
					 */
					public int getSize()
					{
						// bytes in m_Length + bytes in buffer
						return JausUtils.UINT_BYTES + (int) m_Length;
					}
					 
					
					/*
					 * Encodes the MessagePayload data into the given buffer.
					 * 
					 */
					public void encode(byte[] bytes, int pos)
					{
						if(bytes == null)
						{
							return;
						}
						
                        bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes(m_Length), pos, JausUtils.UINT_BYTES, false);
						pos += JausUtils.UINT_BYTES;
						
                        bytes = JausUtils.addToBuffer(bytes, m_Data, pos, (int)m_Length, true);
						pos += (int) m_Length;
						
					}
					
					/*
					 * Removes the MessagePayload data from the given buffer.
					 * 
					 */
					public void decode(byte[] bytes, int pos)
					{
                        if (bytes == null)
                        {
                            return;
                        }
                        m_Length = BitConverter.ToUInt32(JausUtils.getFromBuffer(bytes, pos, JausUtils.UINT_BYTES, false), 0);
						pos += JausUtils.UINT_BYTES;

                        m_Data = JausUtils.getFromBuffer(bytes, pos, (int)m_Length, true);
						pos += JausUtils.UINT_BYTES;
					}
					
					/*
					 * Operator overload =
					 */
					public MessagePayload setAs(MessagePayload value)
					{
						this.m_Length = value.m_Length;
						this.m_Data = value.m_Data;
						
						return this;
					}
					
					/*
					 *  Operator overload ==
					 */
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
			
				protected ushort m_SrcSubsystemID;
				protected byte m_SrcNodeID;
				protected byte m_SrcComponentID;
				protected MessagePayload m_MessagePayload;
				
				/*
				 * returns the source subsystem id - a portion of the message id.
				 */
				public ushort getSrcSubsystemID()
				{
					return m_SrcSubsystemID;
				}
				
				/*
				 * sets the source subsystem id - a portion of the message id.
				 */ 
				public void setSrcSubsystemID(ushort value)
				{
					m_SrcSubsystemID = value;
				}
				
				/*
				 * returns the source node id - a portion of the message id.
				 */
				public byte getSrcNodeID()
				{
					return m_SrcNodeID;
				}
				
				/*
				 * sets the source node id - a portion of the message id.
				 */ 
				public void setSrcNodeID(byte value)
				{
					m_SrcNodeID = value;
				}				
				/*
				 * returns the source component id - a portion of the message id.
				 */
				public byte getSrcComponentID()
				{
					return m_SrcComponentID;
				}
				
				/*
				 * sets the source component id - a portion of the message id.
				 */ 
				public void setSrcComponentID(byte value)
				{
					m_SrcComponentID = value;
				}				
				/*
				 * returns the message payload.
				 */
				public MessagePayload getMessagePayload()
				{
					return m_MessagePayload;
				}
				
				/*
				 * sets the message payload.
				 */ 
				public void setMessagePayload(MessagePayload value)
				{
					m_MessagePayload.setAs(value);
				}
				/*
				* Returns the sum of the bytes of all of the variables stored
				* 	in this class will take in the buffer.
				*/
				public int getSize()
				{
					// m_SrcSubsystem + m_SrcNodeID + m_SrcComponentID + m_MessagePayload		
                    return JausUtils.USHORT_BYTES 
                        + JausUtils.BYTE_BYTES 
                        + JausUtils.BYTE_BYTES 
                        + m_MessagePayload.getSize();
				}		
				
				/*
				 * Wraps the class data into the buffer.
				 */
				public void encode(byte[] bytes, int pos)
				{
					//bytes[pos] = BitConverter.GetBytes(m_SrcSubsystemID);
                    bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes(m_SrcSubsystemID), pos, JausUtils.USHORT_BYTES, false);
                    pos += JausUtils.USHORT_BYTES;
					
                    bytes[pos] = m_SrcNodeID;
                    pos += JausUtils.BYTE_BYTES;
					
					bytes[pos] = m_SrcComponentID;
                    pos += JausUtils.BYTE_BYTES;				
					
				}
				
				/*
				 *  Removes the necessary data from the buffer begining at the given position.
				 */
				public void decode(byte[] bytes, int pos)
				{
					if (bytes == null)
					{
						return;
					}
                    m_SrcSubsystemID = BitConverter.ToUInt16(JausUtils.getFromBuffer(bytes, pos, JausUtils.USHORT_BYTES, false), 0);
                    pos += JausUtils.USHORT_BYTES;
					
					m_SrcNodeID = bytes[pos];
                    pos += JausUtils.BYTE_BYTES;
					
					m_SrcComponentID = bytes[pos];
                    pos += JausUtils.BYTE_BYTES;				
					
				}
				
				/*
				 * Set two ReceiveRecs equal to eachother.
				 */
				public ReceiveRec setAs(ReceiveRec value)
				{
					m_SrcSubsystemID = value.m_SrcSubsystemID;
					m_SrcNodeID = value.m_SrcNodeID;
					m_SrcComponentID = value.m_SrcComponentID;
					m_MessagePayload.setAs(value.m_MessagePayload);
					
					return this;
				}
				
				/*
				 * Operator overload ==
				 */
				public static bool operator ==(ReceiveRec a, ReceiveRec b)
				{
					if((a.m_SrcSubsystemID != b.m_SrcSubsystemID) ||
					   (a.m_SrcNodeID != b.m_SrcNodeID) ||
					   (a.m_SrcComponentID != b.m_SrcComponentID) ||
					   (a.m_MessagePayload != b.m_MessagePayload))
					{
						return false;
					}
					return true;
				}
				
				/*
				 *  Operator overload !=
				 */
				public static bool operator !=(ReceiveRec a, ReceiveRec b)
				{
					return !(a == b);
				}
					
				/*
				 * Default constructor
				 */
				public ReceiveRec()
				{
					m_SrcSubsystemID = 0;
					m_SrcNodeID = 0;
					m_SrcComponentID = 0;
					m_MessagePayload = new MessagePayload();
				}
				
				/*
				 * Copy constructor
				 */
				public ReceiveRec(ReceiveRec value)
				{
					m_SrcSubsystemID = value.m_SrcSubsystemID;
					m_SrcNodeID = value.m_SrcNodeID;
					m_SrcComponentID = value.m_SrcComponentID;
					m_MessagePayload.setAs(value.m_MessagePayload);
				}
			}
		
			protected ReceiveRec m_ReceiveRec;
			
			/*
			 * Returns the stored receive record.
			 */
			public ReceiveRec getReceiveRec()
			{
				return m_ReceiveRec;
			}
			
			/*
			 * Set the receive record to the given value.
			 */
			public void setReceiveRec(ReceiveRec value)
			{
				m_ReceiveRec.setAs(value);
			}
			
			/*
			 * returns the number of bytes the stored data will take on the buffer.
			 */
			public int getSize()
			{
				return m_ReceiveRec.getSize();
			}
			
			/*
			 * Encode the receive record onto the buffer.
			 */
			public void encode(byte[] bytes, int pos)
			{
				if(bytes == null)
				{
					return;
				}
				m_ReceiveRec.encode(bytes, pos);
			}
			
			/*
			 * Retrieves the receive record from the buffer.
			 */
			public void decode(byte[] bytes, int pos)
			{
				if(bytes == null)
				{
					return;
				}
				m_ReceiveRec.decode(bytes,pos);
			}
			
			/*
			 * Set the Body as the given value.
			 * This code is not currently supported.
			 */
			public Body setAs(Body value)
			{
				m_ReceiveRec.setAs(value.getReceiveRec());
				return this;
			}
			
			/*
			 * Operator overload ==
			 * This code is not currently supported.
			 */
			public static bool operator ==(Body a, Body b)
			{
				return a.m_ReceiveRec == b.m_ReceiveRec;
			}
			
			/*
			 * Operator overload !=
			 * This code is not currently supported.
			 */
			public static bool operator !=(Body a, Body b)
			{
				return !(a == b);
			}
			
			/*
			 * Default constructor
			 */
			public Body()
			{
				m_ReceiveRec = new ReceiveRec();
			}
			
			/*
			 * Copy constructor
			 */
			public Body(Body value)
			{
				m_ReceiveRec.setAs(value.getReceiveRec());
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
		* Copy the given value.
		*/
		public void setBody( Body value)
		{
			m_Body.setAs(value);
		}
		
		/*
		*
		* retreive the size of m_Body in bytes.
		*
		*/
		public override int getSize()
		{
			return m_Body.getSize();
		}
		/*
		* Encodes the body into the buffer at the given position. 
		*/
		public override void encode(byte[] bytes, int pos)
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
		public override void decode( byte[] bytes, int pos)
		{
			if (bytes == null)
			{
				return;
			}
			
			m_Body.decode(bytes, pos);
			pos += m_Body.getSize();
		}
		/*
		* Copies the given value.
		*
		*/
        public Receive_1_0 setAs(Receive_1_0 value)
		{
			m_Body.setAs(value.m_Body);
			return this;
		}
		/*
		* Operator overload ==
		*/
        public static bool operator ==(Receive_1_0 a, Receive_1_0 b)
		{
			return a.m_Body == b.m_Body;
		}
		/*
		* Operator overload !=
		*/
        public static bool operator !=(Receive_1_0 a, Receive_1_0 b)
		{
			return !(a == b);
		}
		/*
		*
		* Constructor
		*
		*/
        public Receive_1_0()
		{
			m_Body = new Body();
			m_Name = "Receive";
		}
		/*
		*
		* Copy Constructor
		*
		*/
        public Receive_1_0(Receive_1_0 value)
		{
			m_Name = "Receive";
			m_Body.setAs( value.getBody());
		}
	}
}

