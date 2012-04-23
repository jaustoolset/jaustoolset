package src.urn_jaus_example_addition_client_1_0.Messages;

import framework.messages.Message;
import framework.JausUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.BitSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportAddition extends Message
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static int ID = 0xf011;
	
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
			
			public ReportAddition.Header.HeaderRecord assign(HeaderRecord value)
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
			
			public void finalize()
			{
			}
			
		}
	
	
		protected HeaderRecord m_HeaderRecord;
	
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
		
		public ReportAddition.Header assign(Header value)
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
	public static class  AdditionOutputBody
	{
		public static class  AdditionOutput
		{
		
			protected AdditionOutputBody m_parent;
			protected long m_AdditionResult;
		
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
			
			public long getAdditionResult()
			{
				return m_AdditionResult;
			}
			
			public void setAdditionResult(long value)
			{
				m_AdditionResult = value;
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
				
				bytes.putInt(pos, (int) m_AdditionResult);
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
				
				m_AdditionResult = bytes.getInt(pos) & 0xffffffffL;
				pos += JausUtils.getNumBytes("int");
			}
			
			public ReportAddition.AdditionOutputBody.AdditionOutput assign(AdditionOutput value)
			{
				m_AdditionResult = value.m_AdditionResult;
				
				return this;
			}
			
			public boolean isEqual(AdditionOutput value)
			{
				if (m_AdditionResult != value.getAdditionResult())
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(AdditionOutput value)
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
			
			public void finalize()
			{
			}
			
		}
	
	
		protected AdditionOutput m_AdditionOutput;
	
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
		 * Java, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public long getSize()
		{
			long size = 0;
			
			size += m_AdditionOutput.getSize();
			
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
			
			m_AdditionOutput.encode(bytes, pos);
			pos += m_AdditionOutput.getSize();
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
			
			m_AdditionOutput.decode(bytes, pos);
			pos += m_AdditionOutput.getSize();
		}
		
		public ReportAddition.AdditionOutputBody assign(AdditionOutputBody value)
		{
			m_AdditionOutput = value.m_AdditionOutput;
			m_AdditionOutput.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(AdditionOutputBody value)
		{
			if (!m_AdditionOutput.isEqual(value.getAdditionOutput()))
			{
				return false;
			}
			/// This code is currently not supported
			return true;
		}
		
		public boolean notEquals(AdditionOutputBody value)
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
			m_AdditionOutput = value.m_AdditionOutput;
			m_AdditionOutput.setParent(this);
			/// This code is currently not supported
		}
		
		public void finalize()
		{
		}
		
	}
	protected Header m_Header;
	protected AdditionOutputBody m_AdditionOutputBody;
	public long getID()
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
	
	public int setAdditionOutputBody(AdditionOutputBody value)
	{
		m_AdditionOutputBody = value;
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
		size += m_AdditionOutputBody.getSize();
		
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
		m_AdditionOutputBody.encode(bytes, pos);
		pos += m_AdditionOutputBody.getSize();
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
		m_AdditionOutputBody.decode(bytes, pos);
		pos += m_AdditionOutputBody.getSize();
	}
	
	public ReportAddition setAs(ReportAddition value)
	{
		m_Header = value.m_Header;
		m_AdditionOutputBody = value.m_AdditionOutputBody;
		
		return this;
	}
	
	public boolean isEqual(ReportAddition value)
	{
		if (!m_Header.isEqual(value.getHeader()))
		{
			return false;
		}
		if (!m_AdditionOutputBody.isEqual(value.getAdditionOutputBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public boolean notEquals(ReportAddition value)
	{
		return !isEqual(value);
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
