using JTS;
using System;
using System.Collections;
using System.Linq;
using System.Collections;
using System.Runtime.InteropServices;
using System.Collections.Generic;

namespace urn_jaus_example_addition_client_1_0
{

public class ReportAddition : JTS.Message
{
	protected ushort ID = 0xf011;
	
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
			
			public ReportAddition.Header.HeaderRecord  setHeaderRecord(HeaderRecord value)
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
				m_MessageIDHeader = 0xf011;
			}
			
			public HeaderRecord(HeaderRecord value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_MessageIDHeader = 0xf011;
				
				/// Copy the values
				m_MessageIDHeader = value.m_MessageIDHeader;
			}
			
			 ~HeaderRecord()
			{
			}
			
		
			Header m_parent;
			protected ushort m_MessageIDHeader;
		}
	
		public ReportAddition.Header.HeaderRecord getHeaderRecord()
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
		
		public ReportAddition.Header  setHeader(Header value)
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
	public class AdditionOutputBody
	{
		public class AdditionOutput
		{
			public void setParent(AdditionOutputBody parent)
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
			
			public uint getAdditionResult()
			{
				return m_AdditionResult;
			}
			
			public void setAdditionResult(uint value)
			{
				m_AdditionResult = value;
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
				
				return size;
			}
			
			public void encode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((uint)m_AdditionResult), pos, (int)JausUtils.UINT_BYTES, false);
				pos += JausUtils.UINT_BYTES;
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_AdditionResult = BitConverter.ToUInt32(JausUtils.getFromBuffer(bytes, pos, JausUtils.UINT_BYTES, false), 0);
				pos += JausUtils.UINT_BYTES;
			}
			
			public ReportAddition.AdditionOutputBody.AdditionOutput  setAdditionOutput(AdditionOutput value)
			{
				m_AdditionResult = value.m_AdditionResult;
				
				return this;
			}
			
			public bool isEqual(AdditionOutput value)
			{
				if (this.getAdditionResult() != value.getAdditionResult())
				{
					return false;
				}
				
				return true;
			}
			
			public bool notEquals(AdditionOutput value)
			{
				return !this.isEqual(value);
				}
				
			public AdditionOutput()
			{
				m_parent = null;
				m_AdditionResult = 0;
			}
			
			public AdditionOutput(AdditionOutput value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_AdditionResult = 0;
				
				/// Copy the values
				m_AdditionResult = value.m_AdditionResult;
			}
			
			 ~AdditionOutput()
			{
			}
			
		
			AdditionOutputBody m_parent;
			protected uint m_AdditionResult;
		}
	
		public ReportAddition.AdditionOutputBody.AdditionOutput getAdditionOutput()
		{
			return m_AdditionOutput;
		}
		
		public void setAdditionOutput(AdditionOutput value)
		{
			m_AdditionOutput = value;
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
			
			size += m_AdditionOutput.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_AdditionOutput.encode(bytes, pos);
			pos += m_AdditionOutput.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_AdditionOutput.decode(bytes, pos);
			pos += m_AdditionOutput.getSize();
		}
		
		public ReportAddition.AdditionOutputBody  setAdditionOutputBody(AdditionOutputBody value)
		{
			m_AdditionOutput = value.getAdditionOutput();
			m_AdditionOutput.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public bool isEqual(AdditionOutputBody value)
		{
			if (!this.getAdditionOutput().isEqual(value.getAdditionOutput()))
			{
				return false;
			}
			/// This code is currently not supported
			return true;
		}
		
		public bool notEquals(AdditionOutputBody value)
		{
			return !this.isEqual(value);
			}
		
		public AdditionOutputBody()
		{
			m_AdditionOutput = new AdditionOutput();
			m_AdditionOutput.setParent(this);
		}
		
		public AdditionOutputBody(AdditionOutputBody value)
		{
			/// Initiliaze the protected variables
			m_AdditionOutput = new AdditionOutput();
			m_AdditionOutput.setParent(this);
			
			/// Copy the values
			m_AdditionOutput = value.getAdditionOutput();
			m_AdditionOutput.setParent(this);
			/// This code is currently not supported
		}
		
		 ~AdditionOutputBody()
		{
		}
		
	
		protected AdditionOutput m_AdditionOutput = new  AdditionOutput();
	}
	protected Header m_Header = new  Header();
	protected AdditionOutputBody m_AdditionOutputBody = new  AdditionOutputBody();
	public override ushort getID()
	{
		return ID;
	}
	public ReportAddition.Header getHeader()
	{
		return m_Header;
	}
	
	public void setHeader(Header value)
	{
		m_Header = value;
	}
	
	public ReportAddition.AdditionOutputBody getAdditionOutputBody()
	{
		return m_AdditionOutputBody;
	}
	
	public void setAdditionOutputBody(AdditionOutputBody value)
	{
		m_AdditionOutputBody = value;
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
		size += m_AdditionOutputBody.getSize();
		
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
		m_AdditionOutputBody.encode(bytes, pos);
		pos += m_AdditionOutputBody.getSize();
		
	}
	
	public override void decode(byte[] bytes, int pos)
	{
		
		if (bytes == null)
		{
			return;
		}
		
		m_Header.decode(bytes, pos);
		pos += m_Header.getSize();
		m_AdditionOutputBody.decode(bytes, pos);
		pos += m_AdditionOutputBody.getSize();
		if(pos < bytes.Length)
		{
			for(int i = pos; i<bytes.Length; i++)
			{
				bytes[i] = 0;
			}
		}
	}
	
	public ReportAddition setAs(ReportAddition value)
	{
		m_Header = value.m_Header;
		m_AdditionOutputBody = value.m_AdditionOutputBody;
		
		return this;
	}
	
	public bool  isEqual(ReportAddition value)
	{
		if (!this.getHeader().isEqual(value.getHeader()))
		{
			return false;
		}
		if (!this.getAdditionOutputBody().isEqual(value.getAdditionOutputBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public bool  notEquals(ReportAddition value)
	{
		return !this.isEqual(value);
		}
		
	public ReportAddition()
	{
		m_Header = new Header();
		m_AdditionOutputBody = new AdditionOutputBody();
		m_IsCommand = false;
	}
	
	public  ReportAddition(ReportAddition value)
	{
		/// Initiliaze the protected variables
		m_Header = new Header();
		m_AdditionOutputBody = new AdditionOutputBody();
		m_IsCommand = false;
		
		/// Copy the values
		m_Header = value.m_Header;
		m_AdditionOutputBody = value.m_AdditionOutputBody;
	}
	
}

}
