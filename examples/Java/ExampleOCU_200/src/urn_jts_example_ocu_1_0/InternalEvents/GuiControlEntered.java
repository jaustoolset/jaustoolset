package src.urn_jts_example_ocu_1_0.InternalEvents;

import framework.internalEvents.InternalEvent;
import framework.JausUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.logging.Logger;
import java.util.logging.Level;

public class GuiControlEntered extends InternalEvent
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static class  GuiControlEnteredBody
	{
		public static class  GuiControlEnteredRecord
		{
		
			protected GuiControlEnteredBody m_parent;
			protected BitSet m_PresenceVector;
			protected short m_PresenceVectorTemp;
			protected int m_Command;
			protected float m_Value;
		
			public void setParent(GuiControlEnteredBody parent)
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
			
			public short getPresenceVector()
			{
				return m_PresenceVectorTemp;
			}
			
			protected void setPresenceVector(int index)
			{
				
				m_PresenceVector.set(index);
				m_PresenceVectorTemp = (short) JausUtils.getPVInt(m_PresenceVector);
			}
			
			public boolean checkPresenceVector(int index)
			{
				
				return m_PresenceVector.get(index);
			}
			
			public int getCommand()
			{
				return m_Command;
			}
			
			public void setCommand(int value)
			{
				m_Command = value;
				setParentPresenceVector();
			}
			
			public boolean isValueValid()
			{
				if (checkPresenceVector(0))
				{
					return true;
				}
				return false;
			}
			
			public float getValue()
			{
				return m_Value;
			}
			
			public void setValue(float value)
			{
				m_Value = value;
				setPresenceVector(0);
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
				
				size += JausUtils.getNumBytes("byte");
				size += JausUtils.getNumBytes("int");
				if (checkPresenceVector(0))
				{
					size += JausUtils.getNumBytes("int");
				}
				
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
				
				try
				{
					short m_PresenceVectorTemp = (short) JausUtils.getPVInt(m_PresenceVector);
				bytes.put(pos, (byte) m_PresenceVectorTemp);
				pos += JausUtils.getNumBytes("byte");
				}
				catch(Exception e)
				{
					logger.log(Level.SEVERE, null, e);
				}
				bytes.putInt(pos, m_Command);
				pos += JausUtils.getNumBytes("int");
				if (checkPresenceVector(0))
				{
					bytes.putFloat(pos, m_Value);
					pos += JausUtils.getNumBytes("int");
				}
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
				
				try
				{
					short m_PresenceVectorTemp = 0;
				m_PresenceVectorTemp = (short) (bytes.get(pos) & 0xff);
				pos += JausUtils.getNumBytes("byte");
				m_PresenceVector = JausUtils.setPV(m_PresenceVectorTemp);
				}
				catch(Exception e)
				{
					logger.log(Level.SEVERE, null, e);
				}
				m_Command = bytes.getInt(pos);
				pos += JausUtils.getNumBytes("int");
				if (checkPresenceVector(0))
				{
					m_Value = bytes.getFloat(pos);
					pos += JausUtils.getNumBytes("int");
				}
			}
			
			public GuiControlEntered.GuiControlEnteredBody.GuiControlEnteredRecord assign(GuiControlEnteredRecord value)
			{
				m_PresenceVector = value.m_PresenceVector;
				m_Command = value.m_Command;
				m_Value = value.m_Value;
				
				return this;
			}
			
			public boolean isEqual(GuiControlEnteredRecord value)
			{
				if (!m_PresenceVector.equals(value.m_PresenceVector))
				{
					return false;
				}
				if (m_Command != value.getCommand())
				{
					return false;
				}
				if (m_Value != value.getValue())
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(GuiControlEnteredRecord value)
			{
				return !this.isEqual(value);
			}
			
			public GuiControlEnteredRecord()
			{
				m_parent = null;
				m_PresenceVector = new BitSet();
				m_PresenceVectorTemp = 0;
				m_Command = 0;
				m_Value = 0;
			}
			
			public GuiControlEnteredRecord(GuiControlEnteredRecord value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_PresenceVector = new BitSet();
				m_PresenceVectorTemp = 0;
				m_Command = 0;
				m_Value = 0;
				
				/// Copy the values
				m_PresenceVector = value.m_PresenceVector;
				m_Command = value.m_Command;
				m_Value = value.m_Value;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected GuiControlEnteredRecord m_GuiControlEnteredRecord;
	
		public GuiControlEntered.GuiControlEnteredBody.GuiControlEnteredRecord getGuiControlEnteredRecord()
		{
			return m_GuiControlEnteredRecord;
		}
		
		public void setGuiControlEnteredRecord(GuiControlEnteredRecord value)
		{
			m_GuiControlEnteredRecord = value;
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
			
			size += m_GuiControlEnteredRecord.getSize();
			
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
			
			m_GuiControlEnteredRecord.encode(bytes, pos);
			pos += m_GuiControlEnteredRecord.getSize();
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
			
			m_GuiControlEnteredRecord.decode(bytes, pos);
			pos += m_GuiControlEnteredRecord.getSize();
		}
		
		public GuiControlEntered.GuiControlEnteredBody assign(GuiControlEnteredBody value)
		{
			m_GuiControlEnteredRecord = value.m_GuiControlEnteredRecord;
			m_GuiControlEnteredRecord.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(GuiControlEnteredBody value)
		{
			if (!m_GuiControlEnteredRecord.isEqual(value.getGuiControlEnteredRecord()))
			{
				return false;
			}
			/// This code is currently not supported
			return true;
		}
		
		public boolean notEquals(GuiControlEnteredBody value)
		{
			return !this.isEqual(value);
		}
		
		public GuiControlEnteredBody()
		{
			m_GuiControlEnteredRecord = new GuiControlEnteredRecord();
			m_GuiControlEnteredRecord.setParent(this);
		}
		
		public GuiControlEnteredBody(GuiControlEnteredBody value)
		{
			/// Initiliaze the protected variables
			m_GuiControlEnteredRecord = new GuiControlEnteredRecord();
			m_GuiControlEnteredRecord.setParent(this);
			
			/// Copy the values
			m_GuiControlEnteredRecord = value.m_GuiControlEnteredRecord;
			m_GuiControlEnteredRecord.setParent(this);
			/// This code is currently not supported
		}
		
		public void finalize()
		{
		}
		
	}
	protected GuiControlEnteredBody m_GuiControlEnteredBody;
	public GuiControlEntered.GuiControlEnteredBody getGuiControlEnteredBody()
	{
		return m_GuiControlEnteredBody;
	}
	
	public int setGuiControlEnteredBody(GuiControlEnteredBody value)
	{
		m_GuiControlEnteredBody = value;
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
		
		size += m_GuiControlEnteredBody.getSize();
		
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
		
		m_GuiControlEnteredBody.encode(bytes, pos);
		pos += m_GuiControlEnteredBody.getSize();
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
		
		m_GuiControlEnteredBody.decode(bytes, pos);
		pos += m_GuiControlEnteredBody.getSize();
	}
	
	public GuiControlEntered setAs(GuiControlEntered value)
	{
		m_GuiControlEnteredBody = value.m_GuiControlEnteredBody;
		
		return this;
	}
	
	public boolean isEqual(GuiControlEntered value)
	{
		if (!m_GuiControlEnteredBody.isEqual(value.getGuiControlEnteredBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public boolean notEquals(GuiControlEntered value)
	{
		return !isEqual(value);
	}
	
	public GuiControlEntered()
	{
		m_GuiControlEnteredBody = new GuiControlEnteredBody();
		m_Name = "GuiControlEntered";
	}
	
	public  GuiControlEntered(GuiControlEntered value)
	{
		/// Initiliaze the protected variables
		m_GuiControlEnteredBody = new GuiControlEnteredBody();
		m_Name = "GuiControlEntered";
		
		/// Copy the values
		m_GuiControlEnteredBody = value.m_GuiControlEnteredBody;
	}
	
}
