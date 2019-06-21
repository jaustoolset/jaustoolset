using JTS;
using System;
using System.Collections;
using System.Linq;
using System.Collections;
using System.Runtime.InteropServices;
using System.Collections.Generic;

namespace urn_jts_DiscoveryClient_1_0
{

public class RequestNodeID : JTS.Message
{
	protected ushort ID = 0xdb03;
	
	public class JAUSApplicationLayerHeader
	{
		public class HeaderRec
		{
			public void setParent(JAUSApplicationLayerHeader parent)
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
			
			public ushort getMessageID()
			{
				return m_MessageID;
			}
			
			public void setMessageID(ushort value)
			{
				m_MessageID = value;
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
				
				size += JausUtils.getNumBytes("ushort");
				
				return size;
			}
			
			public void encode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((ushort)m_MessageID), pos, (int)JausUtils.USHORT_BYTES, false);
				pos += JausUtils.USHORT_BYTES;
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_MessageID = BitConverter.ToUInt16(JausUtils.getFromBuffer(bytes, pos, JausUtils.USHORT_BYTES, false), 0);
				pos += JausUtils.USHORT_BYTES;
			}
			
			public RequestNodeID.JAUSApplicationLayerHeader.HeaderRec  setHeaderRec(HeaderRec value)
			{
				m_MessageID = value.m_MessageID;
				
				return this;
			}
			
			public bool isEqual(HeaderRec value)
			{
				if (this.getMessageID() != value.getMessageID())
				{
					return false;
				}
				
				return true;
			}
			
			public bool notEquals(HeaderRec value)
			{
				return !this.isEqual(value);
			}
			
			public HeaderRec()
			{
				m_parent = null;
				m_MessageID = 0xdb03;
			}
			
			public HeaderRec(HeaderRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageID = 0xdb03;
				
				/// Copy the values
				m_MessageID = value.m_MessageID;
			}
			
			 ~HeaderRec()
			{
			}
			
		
			JAUSApplicationLayerHeader m_parent;
			protected ushort m_MessageID;
		}
	
		public RequestNodeID.JAUSApplicationLayerHeader.HeaderRec getHeaderRec()
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
		 * C Sharp, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public int getSize()
		{
			int size = 0;
			
			size += m_HeaderRec.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_HeaderRec.encode(bytes, pos);
			pos += m_HeaderRec.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_HeaderRec.decode(bytes, pos);
			pos += m_HeaderRec.getSize();
		}
		
		public RequestNodeID.JAUSApplicationLayerHeader  setJAUSApplicationLayerHeader(JAUSApplicationLayerHeader value)
		{
			m_HeaderRec = value.getHeaderRec();
			m_HeaderRec.setParent(this);
			
			return this;
		}
		
		public bool isEqual(JAUSApplicationLayerHeader value)
		{
			if (!this.getHeaderRec().isEqual(value.getHeaderRec()))
			{
				return false;
			}
			return true;
		}
		
		public bool notEquals(JAUSApplicationLayerHeader value)
		{
			return !this.isEqual(value);
		}
		
		public JAUSApplicationLayerHeader()
		{
			m_HeaderRec = new HeaderRec();
			m_HeaderRec.setParent(this);
		}
		
		public JAUSApplicationLayerHeader(JAUSApplicationLayerHeader value)
		{
			/// Initiliaze the protected variables
			m_HeaderRec = new HeaderRec();
			m_HeaderRec.setParent(this);
			
			/// Copy the values
			m_HeaderRec = value.getHeaderRec();
			m_HeaderRec.setParent(this);
		}
		
		 ~JAUSApplicationLayerHeader()
		{
		}
		
	
		protected HeaderRec m_HeaderRec = new  HeaderRec();
	}
	public class Body
	{
		public class ReviewNodeIDRec
		{
			public class RequesterID
			{
				public void setParent(ReviewNodeIDRec parent)
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
				
				public  int getOneDimensionArraySize()
				{
					return m_OneDimensionArraySize;
				}
				
				public byte getRequesterIDArrayField(int OneDimensionArray)
				{
					int index = OneDimensionArray;
					
					return m_RequesterIDArrayField[index];
				}
				
				public void setRequesterIDArrayField(int OneDimensionArray, byte value)
				{
					int index = OneDimensionArray;
					
					m_RequesterIDArrayField[index] = value;
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
					
					size += JausUtils.getNumBytes("byte") * 7;
					
					return size;
				}
				
				public void encode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					
					for (uint i = 0; i < 7; i++)
					{
					bytes[pos] = (byte)m_RequesterIDArrayField[i];
					pos += JausUtils.BYTE_BYTES;
					}
				}
				
				public void decode(byte[] bytes, int pos)
				{
					
					if (bytes == null)
					{
						return;
					}
					
					
					for (int i = 0; i < 7; i++)
					{
					m_RequesterIDArrayField[i] = bytes[pos];
					pos += JausUtils.BYTE_BYTES;
					}
				}
				
				public RequestNodeID.Body.ReviewNodeIDRec.RequesterID  setRequesterID(RequesterID value)
				{
					m_RequesterIDArrayField = value.m_RequesterIDArrayField;
					
					return this;
				}
				
				public bool isEqual(RequesterID value)
				{
					if(!this.m_RequesterIDArrayField.SequenceEqual(value.m_RequesterIDArrayField))
					{
						return false;
					}
					
					return true;
				}
				
				public bool notEquals(RequesterID value)
				{
					return !this.isEqual(value);
				}
				
				public RequesterID()
				{
					m_parent = null;
					m_OneDimensionArraySize = 7;
				}
				
				public RequesterID(RequesterID value)
				{
					/// Initiliaze the protected variables
					m_parent = null;
					m_OneDimensionArraySize = 7;
					
					/// Copy the values
					m_RequesterIDArrayField = value.m_RequesterIDArrayField;
				}
				
				 ~RequesterID()
				{
				}
				
			
				ReviewNodeIDRec m_parent;
				int m_OneDimensionArraySize;
				protected byte[] m_RequesterIDArrayField = new byte[7];
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
			
			public RequestNodeID.Body.ReviewNodeIDRec.RequesterID getRequesterID()
			{
				return m_RequesterID;
			}
			
			public void setRequesterID(RequesterID value)
			{
				m_RequesterID = value;
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
				
				size += m_RequesterID.getSize();
				
				return size;
			}
			
			public void encode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_RequesterID.encode(bytes, pos);
				pos += m_RequesterID.getSize();
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_RequesterID.decode(bytes, pos);
				pos += m_RequesterID.getSize();
			}
			
			public RequestNodeID.Body.ReviewNodeIDRec  setReviewNodeIDRec(ReviewNodeIDRec value)
			{
				m_RequesterID = value.getRequesterID();
				
				return this;
			}
			
			public bool isEqual(ReviewNodeIDRec value)
			{
				if (!this.getRequesterID().isEqual(value.getRequesterID()))
				{
					return false;
				}
				
				return true;
			}
			
			public bool notEquals(ReviewNodeIDRec value)
			{
				return !this.isEqual(value);
			}
			
			public ReviewNodeIDRec()
			{
				m_parent = null;
				m_RequesterID = new RequesterID();
				m_RequesterID.setParent(this);
			}
			
			public ReviewNodeIDRec(ReviewNodeIDRec value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_RequesterID = new RequesterID();
				m_RequesterID.setParent(this);
				
				/// Copy the values
				m_RequesterID = value.getRequesterID();
			}
			
			 ~ReviewNodeIDRec()
			{
			}
			
		
			Body m_parent;
			protected RequesterID m_RequesterID = new  RequesterID();
		}
	
		public RequestNodeID.Body.ReviewNodeIDRec getReviewNodeIDRec()
		{
			return m_ReviewNodeIDRec;
		}
		
		public void setReviewNodeIDRec(ReviewNodeIDRec value)
		{
			m_ReviewNodeIDRec = value;
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
			
			size += m_ReviewNodeIDRec.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_ReviewNodeIDRec.encode(bytes, pos);
			pos += m_ReviewNodeIDRec.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_ReviewNodeIDRec.decode(bytes, pos);
			pos += m_ReviewNodeIDRec.getSize();
		}
		
		public RequestNodeID.Body  setBody(Body value)
		{
			m_ReviewNodeIDRec = value.getReviewNodeIDRec();
			m_ReviewNodeIDRec.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public bool isEqual(Body value)
		{
			if (!this.getReviewNodeIDRec().isEqual(value.getReviewNodeIDRec()))
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
			m_ReviewNodeIDRec = new ReviewNodeIDRec();
			m_ReviewNodeIDRec.setParent(this);
		}
		
		public Body(Body value)
		{
			/// Initiliaze the protected variables
			m_ReviewNodeIDRec = new ReviewNodeIDRec();
			m_ReviewNodeIDRec.setParent(this);
			
			/// Copy the values
			m_ReviewNodeIDRec = value.getReviewNodeIDRec();
			m_ReviewNodeIDRec.setParent(this);
			/// This code is currently not supported
		}
		
		 ~Body()
		{
		}
		
	
		protected ReviewNodeIDRec m_ReviewNodeIDRec = new  ReviewNodeIDRec();
	}
	protected JAUSApplicationLayerHeader m_JAUSApplicationLayerHeader = new  JAUSApplicationLayerHeader();
	protected Body m_Body = new  Body();
	public override ushort getID()
	{
		return ID;
	}
	public RequestNodeID.JAUSApplicationLayerHeader getJAUSApplicationLayerHeader()
	{
		return m_JAUSApplicationLayerHeader;
	}
	
	public void setJAUSApplicationLayerHeader(JAUSApplicationLayerHeader value)
	{
		m_JAUSApplicationLayerHeader = value;
	}
	
	public RequestNodeID.Body getBody()
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
		
		size += m_JAUSApplicationLayerHeader.getSize();
		size += m_Body.getSize();
		
		return size;
	}
	
	public override void encode(byte[] bytes, int pos)
	{
		
		if (bytes == null)
		{
			return;
		}
		
		m_JAUSApplicationLayerHeader.encode(bytes, pos);
		pos += m_JAUSApplicationLayerHeader.getSize();
		m_Body.encode(bytes, pos);
		pos += m_Body.getSize();
		
	}
	
	public override void decode(byte[] bytes, int pos)
	{
		
		if (bytes == null)
		{
			return;
		}
		
		m_JAUSApplicationLayerHeader.decode(bytes, pos);
		pos += m_JAUSApplicationLayerHeader.getSize();
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
	
	public RequestNodeID setAs(RequestNodeID value)
	{
		m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
		m_Body = value.m_Body;
		
		return this;
	}
	
	public bool  isEqual(RequestNodeID value)
	{
		if (!this.getJAUSApplicationLayerHeader().isEqual(value.getJAUSApplicationLayerHeader()))
		{
			return false;
		}
		if (!this.getBody().isEqual(value.getBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public bool  notEquals(RequestNodeID value)
	{
		return !this.isEqual(value);
	}
	
	public RequestNodeID()
	{
		m_JAUSApplicationLayerHeader = new JAUSApplicationLayerHeader();
		m_Body = new Body();
		m_IsCommand = false;
	}
	
	public  RequestNodeID(RequestNodeID value)
	{
		/// Initiliaze the protected variables
		m_JAUSApplicationLayerHeader = new JAUSApplicationLayerHeader();
		m_Body = new Body();
		m_IsCommand = false;
		
		/// Copy the values
		m_JAUSApplicationLayerHeader = value.m_JAUSApplicationLayerHeader;
		m_Body = value.m_Body;
	}
	
}

}
