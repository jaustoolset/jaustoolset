package src.urn_jaus_example_addition_server_1_0.Messages;

import framework.messages.Message;
import framework.JausUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueryAddition extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0xf010;
	
	public static class  Header
	{
		public static class  HeaderRecord
		{
		
			protected Header m_parent;
			protected int m_MessageIDHeader;
		
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
			
			public int getMessageIDHeader()
			{
				return m_MessageIDHeader;
			}
			
			public void setMessageIDHeader(int value)
			{
				m_MessageIDHeader = value;
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
				
				size += JausUtils.getNumBytes("short");
				
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
				
				bytes.putShort(pos, (short) m_MessageIDHeader);
				pos += JausUtils.getNumBytes("short");
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
				
				m_MessageIDHeader = bytes.getShort(pos) & 0xffff;
				pos += JausUtils.getNumBytes("short");
			}
			
			public QueryAddition.Header.HeaderRecord assign(HeaderRecord value)
			{
				m_MessageIDHeader = value.m_MessageIDHeader;
				
				return this;
			}
			
			public boolean isEqual(HeaderRecord value)
			{
				if (m_MessageIDHeader != value.getMessageIDHeader())
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(HeaderRecord value)
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
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRecord m_HeaderRecord;
	
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
		 * Java, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public long getSize()
		{
			long size = 0;
			
			size += m_HeaderRecord.getSize();
			
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
			
			m_HeaderRecord.encode(bytes, pos);
			pos += m_HeaderRecord.getSize();
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
			
			m_HeaderRecord.decode(bytes, pos);
			pos += m_HeaderRecord.getSize();
		}
		
		public QueryAddition.Header assign(Header value)
		{
			m_HeaderRecord = value.m_HeaderRecord;
			m_HeaderRecord.setParent(this);
			
			return this;
		}
		
		public boolean isEqual(Header value)
		{
			if (!m_HeaderRecord.isEqual(value.getHeaderRecord()))
			{
				return false;
			}
			return true;
		}
		
		public boolean notEquals(Header value)
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
			m_HeaderRecord = value.m_HeaderRecord;
			m_HeaderRecord.setParent(this);
		}
		
		public void finalize()
		{
		}
		
	}
	public static class  AdditionInputBody
	{
		public static class  AdditionInput
		{
		
			protected AdditionInputBody m_parent;
			protected long m_A1;
			protected long m_A2;
		
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
			
			public long getA1()
			{
				return m_A1;
			}
			
			public void setA1(long value)
			{
				m_A1 = value;
				setParentPresenceVector();
			}
			
			public long getA2()
			{
				return m_A2;
			}
			
			public void setA2(long value)
			{
				m_A2 = value;
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
				
				bytes.putInt(pos, (int) m_A1);
				pos += JausUtils.getNumBytes("int");
				bytes.putInt(pos, (int) m_A2);
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
				
				m_A1 = bytes.getInt(pos) & 0xffffffffL;
				pos += JausUtils.getNumBytes("int");
				m_A2 = bytes.getInt(pos) & 0xffffffffL;
				pos += JausUtils.getNumBytes("int");
			}
			
			public QueryAddition.AdditionInputBody.AdditionInput assign(AdditionInput value)
			{
				m_A1 = value.m_A1;
				m_A2 = value.m_A2;
				
				return this;
			}
			
			public boolean isEqual(AdditionInput value)
			{
				if (m_A1 != value.getA1())
				{
					return false;
				}
				if (m_A2 != value.getA2())
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(AdditionInput value)
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
			
			public void finalize()
			{
			}
			
		}
	
	
		protected AdditionInput m_AdditionInput;
	
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
		 * Java, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public long getSize()
		{
			long size = 0;
			
			size += m_AdditionInput.getSize();
			
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
			
			m_AdditionInput.encode(bytes, pos);
			pos += m_AdditionInput.getSize();
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
			
			m_AdditionInput.decode(bytes, pos);
			pos += m_AdditionInput.getSize();
		}
		
		public QueryAddition.AdditionInputBody assign(AdditionInputBody value)
		{
			m_AdditionInput = value.m_AdditionInput;
			m_AdditionInput.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(AdditionInputBody value)
		{
			if (!m_AdditionInput.isEqual(value.getAdditionInput()))
			{
				return false;
			}
			/// This code is currently not supported
			return true;
		}
		
		public boolean notEquals(AdditionInputBody value)
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
			m_AdditionInput = value.m_AdditionInput;
			m_AdditionInput.setParent(this);
			/// This code is currently not supported
		}
		
		public void finalize()
		{
		}
		
	}
	protected Header m_Header;
	protected AdditionInputBody m_AdditionInputBody;
	public long getID()
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
	
	public int setAdditionInputBody(AdditionInputBody value)
	{
		m_AdditionInputBody = value;
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
		
		size += m_Header.getSize();
		size += m_AdditionInputBody.getSize();
		
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
		
		m_Header.encode(bytes, pos);
		pos += m_Header.getSize();
		m_AdditionInputBody.encode(bytes, pos);
		pos += m_AdditionInputBody.getSize();
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
		
		m_Header.decode(bytes, pos);
		pos += m_Header.getSize();
		m_AdditionInputBody.decode(bytes, pos);
		pos += m_AdditionInputBody.getSize();
	}
	
	public QueryAddition setAs(QueryAddition value)
	{
		m_Header = value.m_Header;
		m_AdditionInputBody = value.m_AdditionInputBody;
		
		return this;
	}
	
	public boolean isEqual(QueryAddition value)
	{
		if (!m_Header.isEqual(value.getHeader()))
		{
			return false;
		}
		if (!m_AdditionInputBody.isEqual(value.getAdditionInputBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public boolean notEquals(QueryAddition value)
	{
		return !isEqual(value);
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
