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

package framework.internalEvents;

/*
* This class wraps message data to be sent throught the node manager.
*
* @version 				04/01/2011
* @author				Gina Nearing
*
*/

import framework.JausUtils;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Send_1_0 extends InternalEvent
{
	protected ByteArrayOutputStream baos;
	protected ByteArrayInputStream bais;
	protected ObjectOutputStream oos;
	protected ObjectInputStream ois;

	private static final int INT_BYTES = JausUtils.getNumBytes("int");
	private static final int SHORT_BYTES = JausUtils.getNumBytes("short");
	private static final int BYTE_BYTES = JausUtils.getNumBytes("byte");

	protected static Logger logger = Logger.getLogger("framework.logger");

	public class Body
	{
		public class SendRec
		{
			public class MessagePayload
			{
				protected long m_Length;
				protected ByteBuffer m_Data;
				/*
				*
				* @return m_Length the lenght of the byte buffer
				*
				*/
				public long getLength()
				{
					return m_Length;
				}

				/*
				*
				* @return returns a copy of the byte buffer with the position at 0.
				*
				*/
				public ByteBuffer getData()
				{
					m_Data.rewind();
					return ByteBuffer.wrap(m_Data.array()).order(ByteOrder.LITTLE_ENDIAN);
				}

				/*
				* @param length the lenght of the buffer
				* @param data the byte buffer
				*
				*/
				public void set(long length, ByteBuffer data)
				{
					m_Length = length;
					m_Data = ByteBuffer.wrap(data.array()).order(ByteOrder.LITTLE_ENDIAN);
				}

				/*
				*
				* @return the number of bytes taken up by the byte buffer and m_Length
				*
				*/
				public int getSize()
				{
					// m_Length + m_Data
					return INT_BYTES + (int) m_Length;
				}

				/*
				* @param bytes buffer
				* @param pos the position in the buffer
				*
				* Adds MessagePayload data to the buffer starting at the given position.
				*/
				public void encode(ByteBuffer bytes, int pos)
				{
					if(bytes == null)
					{
						return;
					}

					try
					{
					bytes.putInt(pos, (int) m_Length);
					pos += INT_BYTES;

					bytes.put(bytes.array(), pos, (int) m_Length);
					pos += m_Length;
					}
					catch (Exception e)
					{
						logger.log(Level.SEVERE, null, e);
					}
				}

				/*
				* @param bytes buffer
				* @param pos the current position in the buffer
				*
				* Removes the MessagePayload data from the buffer starting at the given position.
				*/
				public void decode(ByteBuffer bytes, int pos)
				{
					if(bytes == null)
					{
						return;
					}

					try
					{
					m_Length = bytes.getInt(pos) &0xffff;
					pos += JausUtils.getNumBytes("int");

					bais = new ByteArrayInputStream(bytes.array(), pos, (int) m_Length);
					ois = new ObjectInputStream(bais);
					m_Data = (ByteBuffer) ois.readObject();
					//m_Data = bytes.
					pos += m_Length;
					}
					catch(Exception e)
					{
						logger.log(Level.SEVERE, null, e);
					}
				}

				/*
				* @param value the MessagePayload whose data will be copied to this instance.
				* @return this
				*
				*/
				public MessagePayload setAs(MessagePayload value)
				{
					this.m_Length = value.m_Length;
					this.m_Data = value.m_Data;
					return this;
				}

				/*
				* @param value the MessagePayload to check against.
				* Determines equality of this object and the value.
				*
				*/
				public boolean equals(MessagePayload value)
				{
					if (this.m_Length != value.m_Length)
					{
						return false;
					}

					if ((this.m_Data != null) && (value.m_Data != null))
					{
						if(!this.m_Data.equals(value.m_Data))
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

				/*
				* @param value the MessagePayload to check against.
				* Determines inequality of this object and the value.
				*
				*/
				public boolean notEquals(MessagePayload value)
				{
					return !equals(value);
				}

				/*
				* Default constructor.
				* 
				*/
				public MessagePayload()
				{
					m_Length = MAX_JTS_MESSAGE_SIZE;
					m_Data = ByteBuffer.allocate((int) m_Length);

					m_Data.order(ByteOrder.LITTLE_ENDIAN);
				}

				/*
				*
				* Copy constructor.
				*
				*/
				public MessagePayload(MessagePayload value)
				{
					m_Data.clear();

					m_Length = value.m_Length;

					if(m_Length>0)
					{
						//m_Data = value.m_Data;
						m_Data = ByteBuffer.wrap(value.m_Data.array()).order(ByteOrder.LITTLE_ENDIAN);
					}
					else
					{
						m_Length = MAX_JTS_MESSAGE_SIZE;
						m_Data = ByteBuffer.allocate((int) m_Length);
						m_Data.order(ByteOrder.LITTLE_ENDIAN);
					}
				}

			}
			
			protected BitSet m_PresenceVector;
			protected int m_DestSubsystemID;
			protected short m_DestNodeID;
			protected short m_DestComponentID;
			protected int m_SrcSubsystemID;
			protected short m_SrcNodeID;
			protected short m_SrcComponentID;
			protected short m_Priority;
			protected MessagePayload m_MessagePayload;

			/*
			*
			* @param pv the long representation of a bitset.
			*
			*/
			public void setPresenceVector(int pv)
			{
				//m_PresenceVector = JausUtils.setPV(pv);
				m_PresenceVector.set(pv);
			}

			/*
			*
			* @return the long representation of the presence vector.
			*
			*/
			public int getPresenceVector()
			{
				return JausUtils.getPVInt(m_PresenceVector);
			}

			/*
			* @param index the index of the presence vector to check.
			* @return the boolean value of the presence vector at the index.
			*
			*/
			public boolean checkPresenceVector(int index)
			{
				return m_PresenceVector.get(index);
			}
			/*
			* 
			* @return the Destination Subsystem ID.
			*
			*/
			public int getDestSubsystemID()
			{
				return m_DestSubsystemID;
			}
			/*
			* 
			* @param value the Destination Subsystem ID.
			*
			*/
			public void setDestSubsystemID(int value)
			{
				m_DestSubsystemID = value;
			}
			/*
			* 
			* @return the Destination Node ID.
			*
			*/
			public short getDestNodeID()
			{
				return m_DestNodeID;
			}
			/*
			* 
			* @param short the Destination Node ID.
			*
			*/
			public void setDestNodeID(short value)
			{
				m_DestNodeID = value;
			}
			/*
			* 
			* @return the Destination Component ID.
			*
			*/
			public short getDestComponentID()
			{
				return m_DestComponentID;
			}
			/*
			* 
			* @param the Destination Component ID.
			*
			*/
			public void setDestComponentID(short value)
			{
				m_DestComponentID = value;
			}
			/*
			* 
			* @return Check the presence vector to see if there is a Source Subsystem
			*		ID to use.
			*
			*/
			public boolean isSrcSubsystemIDValid()
			{
				if(checkPresenceVector(0))
				{
					return true;
				}
				return false;
			}
			/*
			* 
			* @return the Source Subsystem ID.
			*
			*/
			public int getSrcSubsystemID()
			{
				return m_SrcSubsystemID;
			}
			/*
			* 
			* @param the Source Subsystem ID.
			*
			*/
			public void setSrcSubsystemID(int value)
			{
				m_SrcSubsystemID = value;
				setPresenceVector(0);
			}
			/*
			* 
			* @return Check the presence vector to see if there is a Source Node
			*		ID to use.
			*
			*/
			public boolean isSrcNodeIDValid()
			{
				if (checkPresenceVector(1))
				{
					return true;
				}
				return false;
			}
			/*
			* 
			* @return the Source Node ID.
			*
			*/
			public short getSrcNodeID()
			{
				return m_SrcNodeID;
			}
			/*
			* 
			* @param the Source Node ID.
			*
			*/
			public void setSrcNodeID(short value)
			{
				m_SrcNodeID = value;
				setPresenceVector(1);
			}
			/*
			* 
			* @return the Source Component ID.
			*
			*/
			public short getSrcComponentID()
			{
				return m_SrcComponentID;
			}
			/*
			* 
			* @param the Source Component ID.
			*
			*/
			public void setSrcComponentID(short value)
			{
				m_SrcComponentID = value;				
			}
			/*
			* 
			* @return Check the presence vector to see if there is a priority
			*		to use.
			*
			*/
			public boolean isPriorityValid()
			{
				if(checkPresenceVector(2))
				{
					return true;
				}
				return false;
			}
			/*
			* 
			* @return the priority
			*
			*/
			public short getPriority()
			{
				return m_Priority;
			}
			/*
			* 
			* @param the priority and checks to make sure it is in a valid range.
			*
			*/
			public void setPriority(short value)
			{
				if (((value >= 0) && (value <= 3)) || (value == 0) || (value == 1) || (value == 2) || (value == 3)){
					m_Priority = value;
					setPresenceVector(2);
				}
			}
			/*
			* 
			* @return the Message Payload
			*
			*/
			public MessagePayload getMessagePayload()
			{
				return m_MessagePayload;
			}
			/*
			*
			* @param value the new value for the MessagePayload
			*
			*/
			public void setMessagePayload( MessagePayload value)
			{
				m_MessagePayload = value;
			}

			/*
			*
			* @return the sum of the bytes of all of the variables stored
			* 	in this class will take in the buffer.
			*
			*/
			public int getSize()
			{
				int size = 0;

				// DestSubsystemID + DestNodeID + Dest ComponentID + BitSet
				size += BYTE_BYTES + SHORT_BYTES + BYTE_BYTES + INT_BYTES;

				if (isSrcSubsystemIDValid() )
				{
					size += SHORT_BYTES;
				}
				if (isSrcNodeIDValid() )
				{
					size += BYTE_BYTES;
				}

				// SrcComponentID
				size += BYTE_BYTES;

				if (isPriorityValid() )
				{
					size += BYTE_BYTES;
				}

				size += m_MessagePayload.getSize();

				return size;
			}
			/*
			* @param bytes buffer
			* @param pos the current position in the buffer
			*
			* Wraps all of the data in the class into the buffer. The number of bytes
			* 	each item occupies is based on the objects size in the JSIDL schema.
			*/
			public void encode(ByteBuffer bytes, int pos)
			{
				if (bytes == null)
				{
					return;
				}

				try
				{
				bytes.putShort(pos, (short) JausUtils.getPVInt(m_PresenceVector));
				pos += INT_BYTES;

				bytes.putShort(pos, (short) m_DestSubsystemID);
				pos += SHORT_BYTES;

				bytes.put(pos, (byte) m_DestNodeID);
				pos += BYTE_BYTES;

				bytes.put(pos, (byte) m_DestComponentID);
				pos += BYTE_BYTES;
				
				if( isSrcSubsystemIDValid())
				{
					bytes.putShort(pos, (short) m_SrcSubsystemID);
					pos += SHORT_BYTES;
				}

				bytes.put(pos, (byte) m_SrcComponentID);
				pos += BYTE_BYTES;

				if( isSrcNodeIDValid())
				{
					bytes.put(pos, (byte) m_SrcNodeID);
					pos += BYTE_BYTES;
				}

				if( isPriorityValid())
				{
					bytes.put(pos, (byte)m_Priority);
					pos += BYTE_BYTES;
				}

				m_MessagePayload.encode(bytes, pos);
				pos += m_MessagePayload.getSize();
				}
				catch(Exception e)
				{
					logger.log(Level.SEVERE, null, e);
				}
			}

			/*
			* @param bytes buffer
			* @param pos the current position in the buffer
			*
			* Removes the necessary data from the buffer and preforms a bitshift on
			*	the data to convert from the unsigned type to the signed Java type.
			*
			*/
			public void decode(ByteBuffer bytes, int pos)
			{
				if(bytes == null) 
				{
					return;
				}

				try
				{

				m_PresenceVector = JausUtils.setPV(bytes.getShort(pos) & 0xffff);
				pos += SHORT_BYTES;
				
				

				m_DestSubsystemID = bytes.getShort(pos) & 0xffff;
				pos += SHORT_BYTES;

				m_DestNodeID = (short) (bytes.get(pos) & 0xff);
				pos += BYTE_BYTES;

				m_DestComponentID = (short) (bytes.get(pos) & 0xff);
				pos += BYTE_BYTES;

				if (isSrcSubsystemIDValid())
				{
					m_SrcSubsystemID = bytes.getShort(pos) & 0xffff;
					pos +=  SHORT_BYTES;
				}

				if (isSrcNodeIDValid())
				{
					m_SrcNodeID = (short) (bytes.get(pos) & 0xff);
					pos += BYTE_BYTES;
				}

				if (isPriorityValid())
				{
					m_Priority = (short) (bytes.get(pos) & 0xff);
					pos += BYTE_BYTES;
				}
				m_MessagePayload.decode(bytes, pos);
				pos += m_MessagePayload.getSize();
				}
				catch(Exception e)
				{
					logger.log(Level.SEVERE, null, e);
				}
			}
			/*
			* 
			* @param value the SendRec containig the values to be set.
			*
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
				m_MessagePayload = value.m_MessagePayload;
	
				return this;
			}
			/*
			*
			* @param value to be compared to this object to determine equality.
			*
			*/
			public boolean equals(SendRec value)
			{
				if ((m_DestSubsystemID != value.m_DestSubsystemID) ||
					(m_DestNodeID != value.m_DestNodeID) ||
					(m_DestComponentID != value.m_DestComponentID) ||
					(m_SrcSubsystemID != value.m_SrcSubsystemID) ||
					(m_SrcNodeID != value.m_SrcNodeID) ||
					(m_SrcComponentID != value.m_SrcComponentID) ||
					(m_Priority != value.m_Priority) ||
					(m_MessagePayload.notEquals(value.m_MessagePayload)))
				{
					return false;
				}
	
				return true;
			}
			/*
			*
			* @param value to be compared to this object to determine inequality.
			*
			*/
			public boolean notEquals(SendRec value)
			{
				return !equals(value);
			}
			/*
			*
			* Default constructor.
			*
			*/
			public SendRec()
			{
				m_PresenceVector = new BitSet();
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
			* Copy constructor
			*
			*/
			public SendRec(SendRec value)
			{
				m_PresenceVector = value.m_PresenceVector;
				m_DestSubsystemID = value.m_DestSubsystemID;
				m_DestNodeID = value.m_DestNodeID;
				m_DestComponentID = value.m_DestComponentID;
				m_SrcSubsystemID = value.m_SrcSubsystemID;
				m_SrcNodeID = value.m_SrcNodeID;
				m_SrcComponentID = value.m_SrcComponentID;	
				m_Priority = value.m_Priority;
				m_MessagePayload = value.m_MessagePayload;
			}
		}

		protected SendRec m_SendRec;
		
		/*
		*
		* @return m_SendRec the SendRec stored in the body.
		*
		*/
		public SendRec getSendRec()
		{
			return m_SendRec;
		}

		/*
		*
		* @param value the ReceiveRec data to be stored in this object.
		*
		*/
		public void setSendRec(SendRec value)
		{
			m_SendRec = value;
		}
		/*
		*
		* @return m_ReceiveRec.getSize() gets the number of bytes the ReceiveRec will take in the buffer.
		*
		*/
		public long getSize()
		{
			return m_SendRec.getSize();
		}
		/*
		* @param bytes buffer
		* @param pos the current position in the buffer
		*
		* encode the m_ReceiveRec into the buffer at the given position.
		*/
		public void encode(ByteBuffer bytes, int pos)
		{
			if (bytes == null)
			{
				return;
			}

			m_SendRec.encode(bytes, pos);
			pos += m_SendRec.getSize();
		}
		/*
		* @param bytes buffer
		* @param pos the current position in the buffer
		*
		* Retrieves the m_ReceiveRec from the buffer at the given position.
		*/
		public void decode(ByteBuffer bytes, int pos)
		{
			if (bytes == null)
			{
				return;
			}

			m_SendRec.decode(bytes, pos);
			pos += m_SendRec.getSize();
		}
		/*
		* @param value
		* 
		* This code is not currently supported.
		*/
		public void setBody( Body value)
		{
			
		}
		/*
		* @param value
		* 
		* This code is not currently supported.
		*/
		public boolean equals( Body value)
		{
			return false;
		}
		/*
		* @param value
		* 
		* This code is not currently supported.
		*/
		public boolean notEquals( Body value)
		{
			return false;
		}
		/*
		*
		* Constructor
		*
		*/
		public Body()
		{
			m_SendRec = new SendRec();
		}
		/*
		*
		* Copy Constructor - unsupported
		*
		*/
		public Body( Body value)
		{
			/// This code is currently not supported.
		}
	}
	protected Body m_Body;

	/*
	*
	* @return m_Body
	*
	*/
	public Body getBody()
	{
		return m_Body;
	}
	/*
	*
	* @param value the Body to be copied
	*
	*/
	public void setBody(Body value)
	{
		m_Body = value;
	}

	/*
	*
	* @return m_Body.getSize()
	*
	*/
	public long getSize()
	{
		return m_Body.getSize();
	}
	/*
	* @param bytes buffer
	* @param pos the current position in the buffer
	*
	* Encodes the body into the buffer at the given position. 
	*/
	public void encode(ByteBuffer bytes, int pos)
	{
		if (bytes == null)
		{
			return;
		}
	
		m_Body.encode(bytes, pos);
		pos += m_Body.getSize();		
	}
	/*
	* @param bytes buffer
	* @param pos the current position in the buffer
	*
	* Retrieves the Body from the buffer at the given location.
	*/
	public void decode(ByteBuffer bytes, int pos)
	{
		if (bytes == null)
		{
			return;
		}
	
		m_Body.decode(bytes, pos);
		pos += m_Body.getSize();	
	}
	/*
	*
	* @param value the value to be copied
	*
	*/
	public Send_1_0 setAs(Send_1_0 value)
	{
		m_Body = value.m_Body;
		return this;
	}
	/*
	*
	* @param value the value to compare with to determine equality
	*
	*/
	public boolean equals(Send_1_0 value)
	{
		if (m_Body.notEquals(value.m_Body))
		{
			return false;
		}
		return true;
	}
	/*
	*
	* @param value the value to compare with to determine inequality
	*
	*/
	public boolean notEquals(Send_1_0 value)
	{
		if (m_Body.equals(value.m_Body))
		{
			return false;
		}
		return true;
	}
	/*
	*
	* Constructor
	*
	*/
	public Send_1_0()
	{
		m_Body = new Body();
		m_Name = "Send";
	}
	/*
	*
	* Copy Constructor
	*
	*/
	public Send_1_0(Send_1_0 value)
	{
		m_Name = "Send";
		
		/// Copy the values
		m_Body = value.m_Body;
	}
}
