using JTS;
using System;
using System.Collections;
using System.Linq;
using System.Collections;
using System.Runtime.InteropServices;
using System.Collections.Generic;

namespace urn_jaus_example_addition_server_1_0
{

public class QueryAddition : JTS.Message
{
	protected ushort ID = 0xf010;
	
	public class Header
	{
		public class HeaderRecord
		{
			public void setParent(Header parent)
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
			
			public ushort getMessageIDHeader()
			{
				return m_MessageIDHeader;
			}
			
			public void setMessageIDHeader(ushort value)
			{
				m_MessageIDHeader = value;
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
				
				bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((ushort)m_MessageIDHeader), pos, (int)JausUtils.USHORT_BYTES, false);
				pos += JausUtils.USHORT_BYTES;
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_MessageIDHeader = BitConverter.ToUInt16(JausUtils.getFromBuffer(bytes, pos, JausUtils.USHORT_BYTES, false), 0);
				pos += JausUtils.USHORT_BYTES;
			}
			
			public QueryAddition.Header.HeaderRecord  setHeaderRecord(HeaderRecord value)
			{
				m_MessageIDHeader = value.m_MessageIDHeader;
				
				return this;
			}
			
			public bool isEqual(HeaderRecord value)
			{
				if (this.getMessageIDHeader() != value.getMessageIDHeader())
				{
					return false;
				}
				
				return true;
			}
			
			public bool notEquals(HeaderRecord value)
			{
				return !this.isEqual(value);
				}
				
			public HeaderRecord()
			{
				m_parent = null;
				m_MessageIDHeader = 0xf010;
			}
			
			public HeaderRecord(HeaderRecord value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageIDHeader = 0xf010;
				
				/// Copy the values
				m_MessageIDHeader = value.m_MessageIDHeader;
			}
			
			 ~HeaderRecord()
			{
			}
			
		
			Header m_parent;
			protected ushort m_MessageIDHeader;
		}
	
		public QueryAddition.Header.HeaderRecord getHeaderRecord()
		{
			return m_HeaderRecord;
		}
		
		public void setHeaderRecord(HeaderRecord value)
		{
			m_HeaderRecord = value;
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
			
			size += m_HeaderRecord.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_HeaderRecord.encode(bytes, pos);
			pos += m_HeaderRecord.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_HeaderRecord.decode(bytes, pos);
			pos += m_HeaderRecord.getSize();
		}
		
		public QueryAddition.Header  setHeader(Header value)
		{
			m_HeaderRecord = value.getHeaderRecord();
			m_HeaderRecord.setParent(this);
			
			return this;
		}
		
		public bool isEqual(Header value)
		{
			if (!this.getHeaderRecord().isEqual(value.getHeaderRecord()))
			{
				return false;
			}
			return true;
		}
		
		public bool notEquals(Header value)
		{
			return !this.isEqual(value);
			}
		
		public Header()
		{
			m_HeaderRecord = new HeaderRecord();
			m_HeaderRecord.setParent(this);
		}
		
		public Header(Header value)
		{
			/// Initiliaze the protected variables
			m_HeaderRecord = new HeaderRecord();
			m_HeaderRecord.setParent(this);
			
			/// Copy the values
			m_HeaderRecord = value.getHeaderRecord();
			m_HeaderRecord.setParent(this);
		}
		
		 ~Header()
		{
		}
		
	
		protected HeaderRecord m_HeaderRecord = new  HeaderRecord();
	}
	public class AdditionInputBody
	{
		public class AdditionInput
		{
			public void setParent(AdditionInputBody parent)
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
			
			public uint getA1()
			{
				return m_A1;
			}
			
			public void setA1(uint value)
			{
				m_A1 = value;
				setParentPresenceVector();
			}
			
			public uint getA2()
			{
				return m_A2;
			}
			
			public void setA2(uint value)
			{
				m_A2 = value;
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
				size += JausUtils.getNumBytes("uint");
				
				return size;
			}
			
			public void encode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((uint)m_A1), pos, (int)JausUtils.UINT_BYTES, false);
				pos += JausUtils.UINT_BYTES;
				bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((uint)m_A2), pos, (int)JausUtils.UINT_BYTES, false);
				pos += JausUtils.UINT_BYTES;
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_A1 = BitConverter.ToUInt32(JausUtils.getFromBuffer(bytes, pos, JausUtils.UINT_BYTES, false), 0);
				pos += JausUtils.UINT_BYTES;
				m_A2 = BitConverter.ToUInt32(JausUtils.getFromBuffer(bytes, pos, JausUtils.UINT_BYTES, false), 0);
				pos += JausUtils.UINT_BYTES;
			}
			
			public QueryAddition.AdditionInputBody.AdditionInput  setAdditionInput(AdditionInput value)
			{
				m_A1 = value.m_A1;
				m_A2 = value.m_A2;
				
				return this;
			}
			
			public bool isEqual(AdditionInput value)
			{
				if (this.getA1() != value.getA1())
				{
					return false;
				}
				if (this.getA2() != value.getA2())
				{
					return false;
				}
				
				return true;
			}
			
			public bool notEquals(AdditionInput value)
			{
				return !this.isEqual(value);
				}
				
			public AdditionInput()
			{
				m_parent = null;
				m_A1 = 0;
				m_A2 = 0;
			}
			
			public AdditionInput(AdditionInput value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_A1 = 0;
				m_A2 = 0;
				
				/// Copy the values
				m_A1 = value.m_A1;
				m_A2 = value.m_A2;
			}
			
			 ~AdditionInput()
			{
			}
			
		
			AdditionInputBody m_parent;
			protected uint m_A1;
			protected uint m_A2;
		}
	
		public QueryAddition.AdditionInputBody.AdditionInput getAdditionInput()
		{
			return m_AdditionInput;
		}
		
		public void setAdditionInput(AdditionInput value)
		{
			m_AdditionInput = value;
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
			
			size += m_AdditionInput.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_AdditionInput.encode(bytes, pos);
			pos += m_AdditionInput.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_AdditionInput.decode(bytes, pos);
			pos += m_AdditionInput.getSize();
		}
		
		public QueryAddition.AdditionInputBody  setAdditionInputBody(AdditionInputBody value)
		{
			m_AdditionInput = value.getAdditionInput();
			m_AdditionInput.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public bool isEqual(AdditionInputBody value)
		{
			if (!this.getAdditionInput().isEqual(value.getAdditionInput()))
			{
				return false;
			}
			/// This code is currently not supported
			return true;
		}
		
		public bool notEquals(AdditionInputBody value)
		{
			return !this.isEqual(value);
			}
		
		public AdditionInputBody()
		{
			m_AdditionInput = new AdditionInput();
			m_AdditionInput.setParent(this);
		}
		
		public AdditionInputBody(AdditionInputBody value)
		{
			/// Initiliaze the protected variables
			m_AdditionInput = new AdditionInput();
			m_AdditionInput.setParent(this);
			
			/// Copy the values
			m_AdditionInput = value.getAdditionInput();
			m_AdditionInput.setParent(this);
			/// This code is currently not supported
		}
		
		 ~AdditionInputBody()
		{
		}
		
	
		protected AdditionInput m_AdditionInput = new  AdditionInput();
	}
	protected Header m_Header = new  Header();
	protected AdditionInputBody m_AdditionInputBody = new  AdditionInputBody();
	public override ushort getID()
	{
		return ID;
	}
	public QueryAddition.Header getHeader()
	{
		return m_Header;
	}
	
	public void setHeader(Header value)
	{
		m_Header = value;
	}
	
	public QueryAddition.AdditionInputBody getAdditionInputBody()
	{
		return m_AdditionInputBody;
	}
	
	public void setAdditionInputBody(AdditionInputBody value)
	{
		m_AdditionInputBody = value;
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
		
		size += m_Header.getSize();
		size += m_AdditionInputBody.getSize();
		
		return size;
	}
	
	public override void encode(byte[] bytes, int pos)
	{
		
		if (bytes == null)
		{
			return;
		}
		
		m_Header.encode(bytes, pos);
		pos += m_Header.getSize();
		m_AdditionInputBody.encode(bytes, pos);
		pos += m_AdditionInputBody.getSize();
		
	}
	
	public override void decode(byte[] bytes, int pos)
	{
		
		if (bytes == null)
		{
			return;
		}
		
		m_Header.decode(bytes, pos);
		pos += m_Header.getSize();
		m_AdditionInputBody.decode(bytes, pos);
		pos += m_AdditionInputBody.getSize();
		if(pos < bytes.Length)
		{
			for(int i = pos; i<bytes.Length; i++)
			{
				bytes[i] = 0;
			}
		}
	}
	
	public QueryAddition setAs(QueryAddition value)
	{
		m_Header = value.m_Header;
		m_AdditionInputBody = value.m_AdditionInputBody;
		
		return this;
	}
	
	public bool  isEqual(QueryAddition value)
	{
		if (!this.getHeader().isEqual(value.getHeader()))
		{
			return false;
		}
		if (!this.getAdditionInputBody().isEqual(value.getAdditionInputBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public bool  notEquals(QueryAddition value)
	{
		return !this.isEqual(value);
		}
		
	public QueryAddition()
	{
		m_Header = new Header();
		m_AdditionInputBody = new AdditionInputBody();
		m_IsCommand = false;
	}
	
	public  QueryAddition(QueryAddition value)
	{
		/// Initiliaze the protected variables
		m_Header = new Header();
		m_AdditionInputBody = new AdditionInputBody();
		m_IsCommand = false;
		
		/// Copy the values
		m_Header = value.m_Header;
		m_AdditionInputBody = value.m_AdditionInputBody;
	}
	
}

}
