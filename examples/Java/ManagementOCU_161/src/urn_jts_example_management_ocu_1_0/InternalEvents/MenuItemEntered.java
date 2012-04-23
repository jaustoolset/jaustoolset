package src.urn_jts_example_management_ocu_1_0.InternalEvents;

import framework.internalEvents.InternalEvent;
import framework.JausUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.logging.Logger;
import java.util.logging.Level;

public class MenuItemEntered extends InternalEvent
{
	protected static Logger logger = Logger.getLogger("framework.logger");
	public static class  MenuItemEnteredBody
	{
		public static class  MenuItemEnteredRecord
		{
		
			protected MenuItemEnteredBody m_parent;
			protected int m_Selection;
		
			public void setParent(MenuItemEnteredBody parent)
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
			
			public int getSelection()
			{
				return m_Selection;
			}
			
			public void setSelection(int value)
			{
				m_Selection = value;
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
				
				bytes.putInt(pos, m_Selection);
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
				
				m_Selection = bytes.getInt(pos);
				pos += JausUtils.getNumBytes("int");
			}
			
			public MenuItemEntered.MenuItemEnteredBody.MenuItemEnteredRecord assign(MenuItemEnteredRecord value)
			{
				m_Selection = value.m_Selection;
				
				return this;
			}
			
			public boolean isEqual(MenuItemEnteredRecord value)
			{
				if (m_Selection != value.getSelection())
				{
					return false;
				}
				
				return true;
			}
			
			public boolean notEquals(MenuItemEnteredRecord value)
			{
				return !this.isEqual(value);
				}
				
			public MenuItemEnteredRecord()
			{
				m_parent = null;
				m_Selection = 0;
			}
			
			public MenuItemEnteredRecord(MenuItemEnteredRecord value)
			{
				/// Initiliaze the protected variables
				m_parent = null;
				m_Selection = 0;
				
				/// Copy the values
				m_Selection = value.m_Selection;
			}
			
			public void finalize()
			{
			}
			
		}
	
	
		protected MenuItemEnteredRecord m_MenuItemEnteredRecord;
	
		public MenuItemEntered.MenuItemEnteredBody.MenuItemEnteredRecord getMenuItemEnteredRecord()
		{
			return m_MenuItemEnteredRecord;
		}
		
		public void setMenuItemEnteredRecord(MenuItemEnteredRecord value)
		{
			m_MenuItemEnteredRecord = value;
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
			
			size += m_MenuItemEnteredRecord.getSize();
			
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
			
			m_MenuItemEnteredRecord.encode(bytes, pos);
			pos += m_MenuItemEnteredRecord.getSize();
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
			
			m_MenuItemEnteredRecord.decode(bytes, pos);
			pos += m_MenuItemEnteredRecord.getSize();
		}
		
		public MenuItemEntered.MenuItemEnteredBody assign(MenuItemEnteredBody value)
		{
			m_MenuItemEnteredRecord = value.m_MenuItemEnteredRecord;
			m_MenuItemEnteredRecord.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public boolean isEqual(MenuItemEnteredBody value)
		{
			if (!m_MenuItemEnteredRecord.isEqual(value.getMenuItemEnteredRecord()))
			{
				return false;
			}
			/// This code is currently not supported
			return true;
		}
		
		public boolean notEquals(MenuItemEnteredBody value)
		{
			return !this.isEqual(value);
			}
		
		public MenuItemEnteredBody()
		{
			m_MenuItemEnteredRecord = new MenuItemEnteredRecord();
			m_MenuItemEnteredRecord.setParent(this);
		}
		
		public MenuItemEnteredBody(MenuItemEnteredBody value)
		{
			/// Initiliaze the protected variables
			m_MenuItemEnteredRecord = new MenuItemEnteredRecord();
			m_MenuItemEnteredRecord.setParent(this);
			
			/// Copy the values
			m_MenuItemEnteredRecord = value.m_MenuItemEnteredRecord;
			m_MenuItemEnteredRecord.setParent(this);
			/// This code is currently not supported
		}
		
		public void finalize()
		{
		}
		
	}
	protected MenuItemEnteredBody m_MenuItemEnteredBody;
	public MenuItemEntered.MenuItemEnteredBody getMenuItemEnteredBody()
	{
		return m_MenuItemEnteredBody;
	}
	
	public int setMenuItemEnteredBody(MenuItemEnteredBody value)
	{
		m_MenuItemEnteredBody = value;
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
		
		size += m_MenuItemEnteredBody.getSize();
		
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
		
		m_MenuItemEnteredBody.encode(bytes, pos);
		pos += m_MenuItemEnteredBody.getSize();
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
		
		m_MenuItemEnteredBody.decode(bytes, pos);
		pos += m_MenuItemEnteredBody.getSize();
	}
	
	public MenuItemEntered setAs(MenuItemEntered value)
	{
		m_MenuItemEnteredBody = value.m_MenuItemEnteredBody;
		
		return this;
	}
	
	public boolean isEqual(MenuItemEntered value)
	{
		if (!m_MenuItemEnteredBody.isEqual(value.getMenuItemEnteredBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public boolean notEquals(MenuItemEntered value)
	{
		return !isEqual(value);
		}
		
	public MenuItemEntered()
	{
		m_MenuItemEnteredBody = new MenuItemEnteredBody();
		m_Name = "MenuItemEntered";
	}
	
	public  MenuItemEntered(MenuItemEntered value)
	{
		/// Initiliaze the protected variables
		m_MenuItemEnteredBody = new MenuItemEnteredBody();
		m_Name = "MenuItemEntered";
		
		/// Copy the values
		m_MenuItemEnteredBody = value.m_MenuItemEnteredBody;
	}
	
}
