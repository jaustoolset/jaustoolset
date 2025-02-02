﻿/***********           LICENSE HEADER   *******************************
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

/*
* This class wraps message data to be sent throught the node manager.
*
* @version 				04/01/2011
* @author				Gina Nearing
*
*/
namespace JTS
{

    public class Send_1_1 : InternalEvent
    {
        private const int INT_BYTES = 4;
        private const int SHORT_BYTES = 2;
        private const int BYTE_BYTES = 1;

        public class Body
        {
            public class SendRec
            {

                public class DestinationID
                {
                    protected uint m_SubFields;

                    /*
                    * @return the SubField to make checking equality of a protected variable easier.
                    */
                    public uint getSubFields()
                    {
                        return m_SubFields;
                    }

                    /*
                    * Pulls the correct bits out of m_SubFields, then converts 
                    * 	them into a long.
                    * @return the value of the destination component ID.
                    */
                    public uint getComponentID()
                    {
                        BitArray bfbs = JausUtils.setPV(m_SubFields);
                        BitArray sfbs = new BitArray(8);
                        int i = 0;

                        for (int index = 0; index <= 7; index++)
                        {
                            sfbs.Set(i++, bfbs.Get(index));
                        }

                        return JausUtils.getPVint(sfbs);
                    }

                    /*
                    * Converts the value of the component ID into a BitSet, then
                    * 	adds it to m_SubFields.
                    * @param the value of the destination component ID.
                    */
                    public void setComponentID(uint value)
                    {
                        if ((value >= 1) && (value <= 255))
                        {
                            BitArray bfbs = JausUtils.setPV(m_SubFields);
                            BitArray sfbs = JausUtils.setPV(value);
                            int i = 0;

                            for (int index = 0; index <= 7; index++)
                            {
                                bfbs.Set(index, sfbs.Get(i++));
                            }

                            m_SubFields = JausUtils.getPVint(bfbs);
                        }
                    }

                    /*
                    * Pulls the correct bits out of m_SubFields, then converts 
                    * 	them into a long.
                    * @return the value of the destination node ID.
                    */
                    public long getNodeID()
                    {
                        BitArray bfbs = JausUtils.setPV(m_SubFields);
                        BitArray sfbs = new BitArray(8);
                        int i = 0;

                        for (int index = 8; index <= 15; index++)
                        {
                            sfbs.Set(i++, bfbs.Get(index));
                        }

                        return JausUtils.getPVint(sfbs);
                    }

                    /*
                    * Converts the value of the node ID into a BitSet, then
                    * 	adds it to m_SubFields.
                    * @param the value of the destination node ID.
                    */
                    public void setNodeID(uint value)
                    {
                        if ((value >= 1) && (value <= 255))
                        {
                            BitArray bfbs = JausUtils.setPV(m_SubFields);
                            BitArray sfbs = JausUtils.setPV(value);
                            int i = 0;

                            for (int index = 8; index <= 15; index++)
                            {
                                bfbs.Set(index, sfbs.Get(i++));
                            }

                            m_SubFields = JausUtils.getPVint(bfbs);
                        }
                    }

                    /*
                    * Pulls the correct bits out of m_SubFields, then converts 
                    * 	them into a long.
                    * @return the value of the subsystem node ID.
                    */
                    public long getSubsystemID()
                    {
                        BitArray bfbs = JausUtils.setPV(m_SubFields);
                        BitArray sfbs = new BitArray(16);
                        int i = 0;
                        for (int index = 16; index <= 31; index++)
                        {

                            sfbs.Set(i++, bfbs.Get(index));
                        }

                        return JausUtils.getPVint(sfbs);
                    }

                    /*
                    * Converts the value of the subsystem ID into a BitSet, then
                    * 	adds it to m_SubFields.
                    * @param the value of the destination subsystem ID.
                    */
                    public void setSubsystemID(uint value)
                    {
                        if ((value >= 1) && (value <= 65535))
                        {
                            BitArray bfbs = JausUtils.setPV(m_SubFields);
                            BitArray sfbs = JausUtils.setPV(value);
                            int i = 0;

                            for (int index = 16; index <= 31; index++)
                            {
                                bfbs.Set(index, sfbs.Get(i++));
                            }

                            m_SubFields = JausUtils.getPVint(bfbs);
                        }
                    }

                    /*
                    * @return the number of bytes the DestinationID will take in the byte[].
                    */
                    public int getSize()
                    {
                        return INT_BYTES;
                    }

                    /*
                    * @param bytes the container of bytes to be sent.
                    * @param pos the position in the buffer to begin encoding.
                    *
                    * Wraps the DestinationID into the byte buffer.The number of bytes
                    * 	each item occupies is based on the objects size in the JSIDL schema.
                    */
                    public void encode(byte[] bytes, int pos)
                    {
                        if (bytes == null)
                        {
                            return;
                        }
                        bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes(m_SubFields), pos, INT_BYTES, false);
                        pos += INT_BYTES;
                    }

                    /*
                    * @param bytes the container of bytes to be decoded.
                    * @param pos the position in the buffer to begin decoding.
                    *
                    * Pulls the DestinationID from the byte buffer.The number of bytes
                    * 	each item occupies is based on the objects size in the JSIDL schema.
                    */
                    public void decode(byte[] bytes, int pos)
                    {

                        if (bytes == null)
                        {
                            return;
                        }


                        m_SubFields = BitConverter.ToUInt32(JausUtils.getFromBuffer(bytes, pos, INT_BYTES, false), 0);
                        pos += INT_BYTES;
                    }

                    /*
                    * @param value - the value this ID should be set to.
                    */
                    public void setAs(DestinationID value)
                    {
                        this.m_SubFields = value.getSubFields();
                    }

                    /*
                    * @param value - the ID this should be compared to.
                    */
                    public bool isEquals(DestinationID value)
                    {
                        return (m_SubFields == value.getSubFields());
                    }

                    /*
                    * @param value - the ID this should be compared to.
                    */
                    public bool notEquals(DestinationID value)
                    {
                        return (m_SubFields != value.getSubFields());
                    }

                    public DestinationID()
                    {
                        m_SubFields = 0;
                    }

                    public DestinationID(DestinationID value)
                    {
                        m_SubFields = value.getSubFields();
                    }

                }

                public class SourceID
                {
                    protected uint m_SubFields;

                    /*
                    * @return the SubField to make checking equality of a protected variable easier.
                    */
                    public uint getSubFields()
                    {
                        return m_SubFields;
                    }

                    /*
                    * Pulls the correct bits out of m_SubFields, then converts 
                    * 	them into a long.
                    * @return the value of the destination component ID.
                    */
                    public uint getComponentID()
                    {
                        BitArray bfbs = JausUtils.setPV(m_SubFields);
                        BitArray sfbs = new BitArray(8);
                        int i = 0;

                        for (int index = 0; index <= 7; index++)
                        {
                            sfbs.Set(i++, bfbs.Get(index));
                        }

                        return JausUtils.getPVint(sfbs);
                    }

                    /*
                    * Converts the value of the component ID into a BitSet, then
                    * 	adds it to m_SubFields.
                    * @param the value of the destination component ID.
                    */
                    public void setComponentID(uint value)
                    {
                        if ((value >= 1) && (value <= 255))
                        {
                            BitArray bfbs = JausUtils.setPV(m_SubFields);
                            BitArray sfbs = JausUtils.setPV(value);
                            int i = 0;

                            for (int index = 0; index <= 7; index++)
                            {
                                bfbs.Set(index, sfbs.Get(i++));
                            }

                            m_SubFields = JausUtils.getPVint(bfbs);
                        }
                    }

                    /*
                    * Pulls the correct bits out of m_SubFields, then converts 
                    * 	them into a long.
                    * @return the value of the destination node ID.
                    */
                    public long getNodeID()
                    {
                        BitArray bfbs = JausUtils.setPV(m_SubFields);
                        BitArray sfbs = new BitArray(8);
                        int i = 0;

                        for (int index = 8; index <= 15; index++)
                        {
                            sfbs.Set(i++, bfbs.Get(index));
                        }

                        return JausUtils.getPVint(sfbs);
                    }

                    /*
                    * Converts the value of the node ID into a BitSet, then
                    * 	adds it to m_SubFields.
                    * @param the value of the destination node ID.
                    */
                    public void setNodeID(uint value)
                    {
                        if ((value >= 1) && (value <= 255))
                        {
                            BitArray bfbs = JausUtils.setPV(m_SubFields);
                            BitArray sfbs = JausUtils.setPV(value);
                            int i = 0;

                            for (int index = 8; index <= 15; index++)
                            {
                                bfbs.Set(index, sfbs.Get(i++));
                            }

                            m_SubFields = JausUtils.getPVint(bfbs);
                        }
                    }

                    /*
                    * Pulls the correct bits out of m_SubFields, then converts 
                    * 	them into a long.
                    * @return the value of the subsystem node ID.
                    */
                    public long getSubsystemID()
                    {
                        BitArray bfbs = JausUtils.setPV(m_SubFields);
                        BitArray sfbs = new BitArray(8);
                        int i = 0;

                        for (int index = 16; index <= 31; index++)
                        {
                            sfbs.Set(i++, bfbs.Get(index));
                        }

                        return JausUtils.getPVint(sfbs);
                    }

                    /*
                    * Converts the value of the subsystem ID into a BitSet, then
                    * 	adds it to m_SubFields.
                    * @param the value of the destination subsystem ID.
                    */
                    public void setSubsystemID(uint value)
                    {
                        if ((value >= 1) && (value <= 65535))
                        {
                            BitArray bfbs = JausUtils.setPV(m_SubFields);
                            BitArray sfbs = JausUtils.setPV(value);
                            int i = 0;

                            for (int index = 16; index <= 31; index++)
                            {
                                bfbs.Set(index, sfbs.Get(i++));
                            }

                            m_SubFields = JausUtils.getPVint(bfbs);
                        }
                    }

                    /*
                    * @return the number of bytes the DestinationID will take in the byte[].
                    */
                    public int getSize()
                    {
                        return INT_BYTES;
                    }

                    /*
                    * @param bytes the container of bytes to be sent.
                    * @param pos the position in the buffer to begin encoding.
                    *
                    * Wraps the DestinationID into the byte buffer.The number of bytes
                    * 	each item occupies is based on the objects size in the JSIDL schema.
                    */
                    public void encode(byte[] bytes, int pos)
                    {
                        if (bytes == null)
                        {
                            return;
                        }
                        bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes(m_SubFields), pos, INT_BYTES, false);
                        pos += INT_BYTES;
                    }

                    /*
                    * @param bytes the container of bytes to be decoded.
                    * @param pos the position in the buffer to begin decoding.
                    *
                    * Pulls the DestinationID from the byte buffer.The number of bytes
                    * 	each item occupies is based on the objects size in the JSIDL schema.
                    */
                    public void decode(byte[] bytes, int pos)
                    {

                        if (bytes == null)
                        {
                            return;
                        }


                        m_SubFields = BitConverter.ToUInt32(JausUtils.getFromBuffer(bytes, pos, INT_BYTES, false), 0);
                        pos += INT_BYTES;
                    }

                    /*
                    * @param value - the value this ID should be set to.
                    */
                    public void setAs(SourceID value)
                    {
                        this.m_SubFields = value.getSubFields();
                    }

                    /*
                    * @param value - the ID this should be compared to.
                    */
                    public bool isEquals(SourceID value)
                    {
                        return (m_SubFields == value.getSubFields());
                    }

                    /*
                    * @param value - the ID this should be compared to.
                    */
                    public bool notEquals(SourceID value)
                    {
                        return (m_SubFields != value.getSubFields());
                    }

                    public SourceID()
                    {
                        m_SubFields = 0;
                    }

                    public SourceID(SourceID value)
                    {
                        m_SubFields = value.getSubFields();
                    }
                }

                /*
                * Subclass containing the message data
                * to be sent.
                */
                public class MessagePayload
                {
                    protected int m_Length;
                    protected byte[] m_Data;
                    /*
                    *
                    * @return m_Length the lenght of the byte buffer
                    *
                    */
                    public int getLength()
                    {
                        return m_Length;
                    }

                    /*
                    *
                    * @return returns a copy of the byte buffer with the position at 0.
                    *
                    */
                    public byte[] getData()
                    {
                        byte[] tmp = new byte[m_Length];
                        for (int i = 0; i < m_Length; i++)
                        {
                            tmp[i] = m_Data[i];
                        }
                        return tmp;
                    }

                    /*
                    * @param length the lenght of the buffer
                    * @param data the byte buffer
                    *
                    */
                    public void set(int length, byte[] data)
                    {
                        m_Length = length;
                        for (int i = 0; i < (int)m_Length; i++)
                        {
                            m_Data[i] = data[i];
                        }
                    }

                    /*
                    *
                    * @return the number of bytes taken up by the byte buffer and m_Length
                    *
                    */
                    public int getSize()
                    {
                        // m_Length + m_Data
                        return INT_BYTES + m_Length;
                    }

                    /*
                    * @param bytes buffer
                    * @param pos the position in the buffer
                    *
                    * Adds MessagePayload data to the buffer starting at the given position.
                    */
                    public void encode(byte[] bytes, int pos)
                    {
                        if (bytes == null)
                        {
                            return;
                        }

                        try
                        {
                            bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes(m_Length), pos, INT_BYTES, false);
                            pos += INT_BYTES;

                            bytes = JausUtils.addToBuffer(bytes, m_Data, pos, m_Length, true);
                            pos += m_Length;
                        }
                        catch (Exception e)
                        {
                        }
                    }

                    /*
                    * @param bytes buffer
                    * @param pos the current position in the buffer
                    *
                    * Removes the MessagePayload data from the buffer starting at the given position.
                    */
                    public void decode(byte[] bytes, int pos)
                    {
                        if (bytes == null)
                        {
                            return;
                        }

                        try
                        {

                            m_Length = BitConverter.ToInt32(JausUtils.getFromBuffer(bytes, pos, INT_BYTES, false), 0);
                            pos += JausUtils.getNumBytes("int");

                            m_Data = JausUtils.getFromBuffer(bytes, pos, m_Length, true);
                            pos += m_Length;
                        }
                        catch (Exception e)
                        {

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
                    public bool equals(MessagePayload value)
                    {
                        if (this.m_Length != value.m_Length)
                        {
                            return false;
                        }

                        if ((this.m_Data != null) && (value.m_Data != null))
                        {
                            for (int i = 0; i < m_Length; i++)
                            {
                                if (this.m_Data[i] == value.m_Data[i])
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

                    /*
                    * @param value the MessagePayload to check against.
                    * Determines inequality of this object and the value.
                    *
                    */
                    public bool notEquals(MessagePayload value)
                    {
                        return !equals(value);
                    }

                    /*
                    * Default constructor.
                    * 
                    */
                    public MessagePayload()
                    {
                        m_Length = (int)MAX_JTS_MESSAGE_SIZE;
                        m_Data = new byte[(int)m_Length];
                    }

                    /*
                    *
                    * Copy constructor.
                    *
                    */
                    public MessagePayload(MessagePayload value)
                    {
                        m_Length = value.m_Length;
                        m_Data = new byte[m_Length];
                        if (m_Length > 0)
                        {
                            //m_Data = value.m_Data;
                            for (int i = 0; i < m_Length; i++)
                            {
                                m_Data[i] = value.getData()[i];
                            }
                        }
                        else
                        {
                            m_Length = (int)MAX_JTS_MESSAGE_SIZE;
                            m_Data = new byte[(int)m_Length];
                        }
                    }
                }


                /*
                *
                * @return the numeric representation of the presence vector.
                *
                */
                public short getPresenceVector()
                {
                    return m_PresenceVector;
                }

                /*
                * @param index the index of the presence vector to check.
                * @return the bool value of the presence vector at the index.
                *
                */
                public bool checkPresenceVector(int index)
                {
                    return JausUtils.setPV((uint)m_PresenceVector).Get(index);
                }

                /*
                * @return the short that indicates if a service was delivered correctly.
                */
                public short getReliableDelivery()
                {
                    return m_ReliableDelivery;
                }

                /*
                * @param value is a value of 1 or 0 that indicates if a service was delivered correctly.
                */
                public void setReliableDeliver(short value)
                {
                    if (((value >= 0) && (value <= 1)) || (value == 0) || (value == 1))
                    {
                        m_ReliableDelivery = value;
                    }
                }

                /*
                * @return the DestinationID
                */
                public DestinationID getDestinationID()
                {
                    return m_DestinationID;
                }

                /*
                * @param the value the destination should be set to.
                */
                public void setDestinationID(DestinationID value)
                {
                    m_DestinationID.setAs(value);
                }

                /*
                * @bool if the source will be encoded into the buffer.
                */
                public bool isSourceIDValid()
                {
                    if (checkPresenceVector(0))
                    {
                        return true;
                    }
                    return false;
                }

                /*
                * @return the SourceID
                */
                public SourceID getSourceID()
                {
                    return m_SourceID;
                }

                /*
                * @param the value the SourceID will be set to.
                */
                public void setSourceID(SourceID value)
                {
                    m_SourceID.setAs(value);
                }

                /*
                * @bool if the source will be encoded into the buffer.
                */
                public bool isPriorityValid()
                {
                    if (checkPresenceVector(1))
                    {
                        return true;
                    }
                    return false;
                }

                /*
                * @return the priority of the message.
                */
                public short getPriority()
                {
                    return m_Priority;
                }

                /*
                * @value the value of the priority.
                */
                public void setPriority(short value)
                {
                    if (((value >= 0) && (value <= 3)) || (value == 0) || (value == 1) || (value == 2) || (value == 3))
                    {
                        m_Priority = value;
                        setPresenceVector(1);
                        return;
                    }
                }

                /*
                * @return the 
                */
                public MessagePayload getMessagePayload()
                {
                    return m_MessagePayload;
                }

                public void setMessagePayload(MessagePayload value)
                {
                    m_MessagePayload.setAs(value);
                }

                public int getSize()
                {
                    int size = 0;

                    size += SHORT_BYTES;				//m_PresenceVector
                    size += SHORT_BYTES;				//m_ReliableDelivery
                    size += m_DestinationID.getSize();  //m_DestinationID
                    if (checkPresenceVector(0))
                    {
                        size += m_SourceID.getSize();	//m_SourceID
                    }
                    if (checkPresenceVector(1))
                    {
                        size += SHORT_BYTES;	//m_Priority
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

                    // m_PresenceVector
                    bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes(m_PresenceVector), pos, SHORT_BYTES, false);
                    pos += SHORT_BYTES;

                    //m_ReliableDelivery
                    bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes(m_ReliableDelivery), pos, SHORT_BYTES, false);
                    pos += SHORT_BYTES;

                    //m_DestinationID
                    m_DestinationID.encode(bytes, pos);
                    pos += m_DestinationID.getSize();

                    //m_SourceID
                    if (checkPresenceVector(0))
                    {
                        m_SourceID.encode(bytes, pos);
                        pos += m_SourceID.getSize();
                    }

                    //m_Priority
                    if (checkPresenceVector(1))
                    {
                        bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes(m_Priority), pos, SHORT_BYTES, false);
                        pos += SHORT_BYTES;
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

                    // m_PresenceVector
                    m_PresenceVector = BitConverter.ToInt16(JausUtils.getFromBuffer(bytes, pos, SHORT_BYTES, false), 0);
                    pos += SHORT_BYTES;

                    //m_ReliableDelivery
                    m_ReliableDelivery = BitConverter.ToInt16(JausUtils.getFromBuffer(bytes, pos, SHORT_BYTES, false), 0);
                    pos += SHORT_BYTES;

                    //m_DestinationID
                    m_DestinationID.decode(bytes, pos);
                    pos += m_DestinationID.getSize();

                    //m_SourceID
                    if (checkPresenceVector(0))
                    {
                        m_SourceID.decode(bytes, pos);
                        pos += m_SourceID.getSize();
                    }

                    //m_Priority
                    if (checkPresenceVector(1))
                    {
                        m_ReliableDelivery = BitConverter.ToInt16(JausUtils.getFromBuffer(bytes, pos, SHORT_BYTES, false), 0);
                        pos += SHORT_BYTES;
                    }

                    m_MessagePayload.decode(bytes, pos);
                    pos += m_MessagePayload.getSize();
                }

                public void setAs(SendRec value)
                {
                    m_PresenceVector = value.getPresenceVector();
                    m_ReliableDelivery = value.getReliableDelivery();
                    m_Priority = value.getPriority();
                    m_DestinationID.setAs(value.getDestinationID());
                    m_SourceID.setAs(value.getSourceID());
                    m_MessagePayload.setAs(value.getMessagePayload());

                }

                public bool isEquals(SendRec value)
                {
                    if ((m_PresenceVector != value.getPresenceVector()) ||
                        (m_ReliableDelivery != value.getReliableDelivery()) ||
                        (m_Priority != value.getPriority()) ||
                        (m_DestinationID.notEquals(value.getDestinationID())) ||
                        (m_SourceID.notEquals(value.getSourceID())) ||
                        (m_MessagePayload.notEquals(value.getMessagePayload())))
                    {
                        return false;
                    }
                    return true;



                }

                public bool notEquals(SendRec value)
                {
                    return !(this.isEquals(value));
                }

                public SendRec()
                {
                    m_PresenceVector = 0;
                    m_ReliableDelivery = 0;
                    m_DestinationID = new DestinationID();
                    m_SourceID = new SourceID();
                    m_Priority = 0;
                    m_MessagePayload = new MessagePayload();

                }

                public SendRec(SendRec value)
                {
                    m_DestinationID = new DestinationID();
                    m_SourceID = new SourceID();
                    m_MessagePayload = new MessagePayload();

                    // Copy the values
                    m_PresenceVector = value.getPresenceVector();
                    m_ReliableDelivery = value.getReliableDelivery();
                    m_DestinationID = value.getDestinationID();
                    m_SourceID = value.getSourceID();
                    m_Priority = value.getPriority();
                    m_MessagePayload = value.getMessagePayload();

                }

                /*
                *
                * @param pv the numeric representation of a bitset.
                *
                */
                public void setPresenceVector(int pv)
                {
                    //m_PresenceVector = JausUtils.setPV(pv);
                    BitArray tmp = JausUtils.setPV((uint)m_PresenceVector);
                    tmp.Set(pv, true);
                    m_PresenceVector = (short)JausUtils.getPVint(tmp);
                }

                protected short m_PresenceVector;
                protected short m_ReliableDelivery;
                protected DestinationID m_DestinationID;
                protected SourceID m_SourceID;
                protected short m_Priority;
                protected MessagePayload m_MessagePayload;

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
            public int getSize()
            {
                return m_SendRec.getSize();
            }
            /*
            * @param bytes buffer
            * @param pos the current position in the buffer
            *
            * encode the m_ReceiveRec into the buffer at the given position.
            */
            public void encode(byte[] bytes, int pos)
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
            public void decode(byte[] bytes, int pos)
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
            */
            public void setAs(Body value)
            {
                m_SendRec.setAs(value.getSendRec());
            }
            /*
            * @param value
            * 
            * This code is not currently supported.
            */
            public bool equals(Body value)
            {
                return false;
            }
            /*
            * @param value
            * 
            * This code is not currently supported.
            */
            public bool notEquals(Body value)
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
            public Body(Body value)
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
        public override int getSize()
        {
            return m_Body.getSize();
        }
        /*
        * @param bytes buffer
        * @param pos the current position in the buffer
        *
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
        * @param bytes buffer
        * @param pos the current position in the buffer
        *
        * Retrieves the Body from the buffer at the given location.
        */
        public override void decode(byte[] bytes, int pos)
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
        public Send_1_1 setAs(Send_1_1 value)
        {
            m_Body = value.m_Body;
            return this;
        }
        /*
        *
        * @param value the value to compare with to determine equality
        *
        */
        public bool equals(Send_1_1 value)
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
        public bool notEquals(Send_1_1 value)
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
        public Send_1_1()
        {
            m_Body = new Body();
            m_Name = "Send";
        }
        /*
        *
        * Copy Constructor
        *
        */
        public Send_1_1(Send_1_1 value)
        {
            m_Name = "Send";

            // Copy the values
            m_Body.setAs(value.getBody());
        }
    }
}