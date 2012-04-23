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
* This is the wrapper that will hold any message received by the node manager.
*
* @version 			04/01/2011
* @author			Gina Nearing
*
*
*/

import framework.JausUtils;
import framework.transport.OS;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Receive_1_0 extends InternalEvent
{
	protected ByteArrayOutputStream baos;
	protected ByteArrayInputStream bais;
	protected ObjectOutputStream oos;
	protected ObjectInputStream ois;

	protected static final int INT_BYTES = JausUtils.getNumBytes("long");
	protected static final int SHORT_BYTES = JausUtils.getNumBytes("short");
	protected static final int BYTE_BYTES = JausUtils.getNumBytes("byte");

	protected static Logger logger = Logger.getLogger("framework.logger");

	public class Body
	{
		public class ReceiveRec
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
				public void set( long length,  ByteBuffer data)
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
					// bytes in m_Length + bytes in buffer
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
					if (bytes == null)
					{
						return;
					}

					try
					{
						bytes.putInt(pos, (int) m_Length);
						pos += INT_BYTES;
	
						bytes.put(m_Data.array(), pos, (int) m_Length);
						pos += (int) m_Length;
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
				* Removes the MessagePayload data from the buffer starting at the given position.
				*/
				public void decode( ByteBuffer bytes, int pos)
				{
					if (bytes == null)
					{
						return;
					}

					try
					{
					m_Length = bytes.getInt(pos) & 0xffff;
					pos += JausUtils.getNumBytes("int");

					byte[] temp = new byte[(int) m_Length];
					bytes.get(temp, pos, (int) m_Length);
					m_Data.put(temp);
					pos += (int) m_Length;
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
				public MessagePayload setAs( MessagePayload value)
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
				public boolean equals( MessagePayload value)
				{
					if(this.m_Length != value.m_Length)
					{
						return false;
					}
					
					if((this.m_Data != null) && (value.m_Data != null))
					{
						if(!this.m_Data.equals(value.m_Data))
						{
							return false;
						}
					}
					// This case should never be true since it should not be possible
					// for the two variables to have equal lengths but one has no data.
					// This check is placed here as a secondary check.
					else if((this.m_Data != null) || (value.m_Data != null))
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
				public boolean notEquals( MessagePayload value)
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
				public MessagePayload( MessagePayload value)
				{
					m_Length = value.m_Length;
					//m_Data = ByteBuffer.allocate((int) m_Length);
					m_Data = ByteBuffer.wrap(value.m_Data.array()).order(ByteOrder.LITTLE_ENDIAN);

				}

			}
		
			/*
			*
			* @return m_SrcSubsystemID - a portion of the messageID
			*
			*/
			public int getSrcSubsystemID()
			{
				return m_SrcSubsystemID;
			}
			/*
			*
			* @param value the new value of m_SrcSubsystemID - a portion of the messageID
			*
			*/
			public void setSrcSubsystemID(int value)
			{
				m_SrcSubsystemID = value;
			}
			/*
			*
			* @return m_SrcNodeID - a portion of the messageID
			*
			*/
			public short getSrcNodeID()
			{
				return m_SrcNodeID;
			}
			/*
			*
			* @param value the new value of m_SrcNodeID - a portion of the messageID
			*
			*/
			public void setSrcNodeID(short value)
			{
				m_SrcNodeID = value;
			}
			/*
			*
			* @return m_SrcComponentID - a portion of the messageID
			*
			*/
			public short getSrcComponentID()
			{
				return m_SrcComponentID;
			}
			/*
			*
			* @param value the new value of m_SrcComponentID - a portion of the messageID
			*
			*/
			public void setSrcComponentID(short value)
			{
				m_SrcComponentID = value;
			}
			/*
			*
			* @return the MessagePayload.
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
				// m_SrcSubsystem + m_SrcNodeID + m_SrcComponentID + m_MessagePayload		
				return SHORT_BYTES + BYTE_BYTES + BYTE_BYTES + m_MessagePayload.getSize();
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
				try
				{
				bytes.putInt(pos, (short) m_SrcSubsystemID);
				pos += SHORT_BYTES;

				bytes.put(pos, (byte) m_SrcNodeID);
				pos += BYTE_BYTES;

				bytes.put(pos, (byte) m_SrcComponentID);
				pos += BYTE_BYTES;
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
			public void decode( ByteBuffer bytes, int pos)
			{
				if (bytes == null)
				{
					return;
				}

				try
				{
				m_SrcSubsystemID = bytes.getShort(pos) & 0xffff;
				pos += SHORT_BYTES;

				m_SrcNodeID = (short) (bytes.get(pos) & 0xff);
				pos += BYTE_BYTES;

				m_SrcComponentID = (short) (bytes.get(pos) & 0xff);
				pos += BYTE_BYTES;
				}
				catch(Exception e)
				{
					logger.log(Level.SEVERE, null, e);
				}
			}
			/*
			* 
			* @param value the ReceiveRec containig the values to be set.
			*
			*/
			public ReceiveRec setAs( ReceiveRec value)
			{
				m_SrcSubsystemID = value.m_SrcSubsystemID;
				m_SrcNodeID = value.m_SrcNodeID;
				m_SrcComponentID = value.m_SrcComponentID;
				m_MessagePayload = value.m_MessagePayload;
			
				return this;
			}
			/*
			*
			* @param value to be compared to this object to determine equality.
			*
			*/
			public boolean equals( ReceiveRec value)
			{
				if((m_SrcSubsystemID != value.m_SrcSubsystemID) ||
					(m_SrcNodeID != value.m_SrcNodeID)||
					(m_SrcComponentID != value.m_SrcComponentID) ||
					(!m_MessagePayload.equals(value.m_MessagePayload)))
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
			public boolean notEquals( ReceiveRec value)
			{
				return !equals(value);
			}
			/*
			*
			* Default constructor.
			*
			*/
			public ReceiveRec()
			{
				m_SrcSubsystemID = 0;
				m_SrcNodeID = 0;
				m_SrcComponentID = 0;
				m_MessagePayload = new MessagePayload();
			}
			/*
			*
			* Copy constructor
			*
			*/
			public ReceiveRec( ReceiveRec value)
			{
				m_SrcSubsystemID = value.m_SrcSubsystemID;
				m_SrcNodeID = value.m_SrcNodeID;
				m_SrcComponentID = value.m_SrcComponentID;
				m_MessagePayload = value.m_MessagePayload;
			}
		
			protected int m_SrcSubsystemID;
			protected short m_SrcNodeID;
			protected short m_SrcComponentID;
			protected MessagePayload m_MessagePayload;
		}

		protected ReceiveRec m_ReceiveRec;

		/*
		*
		* @return m_ReceiveRec the ReceiveRec stored in the body.
		*
		*/
		public ReceiveRec  getReceiveRec()
		{
			return m_ReceiveRec;
		}

		/*
		*
		* @param value the ReceiveRec data to be stored in this object.
		*
		*/
		public void setReceiveRec( ReceiveRec value)
		{
			m_ReceiveRec = value;
		}
		/*
		*
		* @return m_ReceiveRec.getSize() gets the number of bytes the ReceiveRec will take in the buffer.
		*
		*/
		public long getSize()
		{
			return m_ReceiveRec.getSize();
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
			m_ReceiveRec.encode(bytes, pos);

		}
		/*
		* @param bytes buffer
		* @param pos the current position in the buffer
		*
		* Retrieves the m_ReceiveRec from the buffer at the given position.
		*/
		public void decode( ByteBuffer bytes, int pos)
		{
			if (bytes == null)
			{
				return;
			}
			m_ReceiveRec.decode(bytes, pos);
		}
		/*
		* @param value
		* 
		* This code is not currently supported.
		*/
		public Body setAs( Body value)
		{
			m_ReceiveRec = value.m_ReceiveRec;
			return this;
		}
		/*
		* @param value
		*
		*This code is currently not supported
		*/
		public boolean equals( Body value)
		{		
			return m_ReceiveRec.equals(value.m_ReceiveRec);
		}
		/*
		* @param value
		*
		* This code is currently not supported
		*/
		public boolean notEquals( Body value)
		{
			return !m_ReceiveRec.equals(value.m_ReceiveRec);
		}
		/*
		*
		* Constructor
		*
		*/
		public Body()
		{
			m_ReceiveRec = new ReceiveRec();
		}
		/*
		* @param value
		* Copy Constructor
		*
		*/
		public Body( Body value)
		{
			m_ReceiveRec = value.m_ReceiveRec;
		}
	

	}

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
	public void setBody( Body value)
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
	public void decode( ByteBuffer bytes, int pos)
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
	public Receive_1_0 setAs( Receive_1_0 value)
	{
		m_Body = value.m_Body;
		return this;
	}
	/*
	*
	* @param value the value to compare with to determine equality
	*
	*/
	public boolean equals( Receive_1_0 value)
	{
		return m_Body.equals(value.m_Body);
	}
	/*
	*
	* @param value the value to compare with to determine inequality
	*
	*/
	public boolean notEquals( Receive_1_0 value)
	{
		return !m_Body.equals(value.m_Body);
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
	public Receive_1_0( Receive_1_0 value)
	{
		m_Name = "Receive";
		m_Body = value.m_Body;
	}

	protected Body m_Body;
	

}

