using JTS;
using System;
using System.Collections;

namespace urn_jts_example_management_ocu_1_0
{

public class MenuItemEntered : InternalEvent
{
	public class MenuItemEnteredBody
	{
		public class MenuItemEnteredRecord
		{
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
			 * C Sharp, but the bytes expected on the wire.
			 * 
			 * @return
			 */
			public int getSize()
			{
				int size = 0;
				
				size += JausUtils.getNumBytes("int");
				
				return size;
			}
			
			public void encode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				bytes = JausUtils.addToBuffer(bytes, BitConverter.GetBytes((int)m_Selection), pos, (int)JausUtils.INT_BYTES, false);
				pos += JausUtils.INT_BYTES;
			}
			
			public void decode(byte[] bytes, int pos)
			{
				
				if (bytes == null)
				{
					return;
				}
				
				m_Selection = BitConverter.ToInt32(JausUtils.getFromBuffer(bytes, pos, JausUtils.INT_BYTES, false), 0);
				pos += JausUtils.INT_BYTES;
			}
			
			public MenuItemEntered.MenuItemEnteredBody.MenuItemEnteredRecord  setMenuItemEnteredRecord(MenuItemEnteredRecord value)
			{
				m_Selection = value.m_Selection;
				
				return this;
			}
			
			public bool isEqual(MenuItemEnteredRecord value)
			{
				if (this.getSelection() != value.getSelection())
				{
					return false;
				}
				
				return true;
			}
			
			public bool notEquals(MenuItemEnteredRecord value)
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
			
			 ~MenuItemEnteredRecord()
			{
			}
			
		
			MenuItemEnteredBody m_parent;
			protected int m_Selection;
		}
	
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
		 * C Sharp, but the bytes expected on the wire.
		 * 
		 * @return
		 */
		public int getSize()
		{
			int size = 0;
			
			size += m_MenuItemEnteredRecord.getSize();
			
			return size;
		}
		
		public void encode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_MenuItemEnteredRecord.encode(bytes, pos);
			pos += m_MenuItemEnteredRecord.getSize();
		}
		
		public void decode(byte[] bytes, int pos)
		{
			
			if (bytes == null)
			{
				return;
			}
			
			m_MenuItemEnteredRecord.decode(bytes, pos);
			pos += m_MenuItemEnteredRecord.getSize();
		}
		
		public MenuItemEntered.MenuItemEnteredBody  setMenuItemEnteredBody(MenuItemEnteredBody value)
		{
			m_MenuItemEnteredRecord = value.getMenuItemEnteredRecord();
			m_MenuItemEnteredRecord.setParent(this);
			/// This code is currently not supported
			
			return this;
		}
		
		public bool isEqual(MenuItemEnteredBody value)
		{
			if (!this.getMenuItemEnteredRecord().isEqual(value.getMenuItemEnteredRecord()))
			{
				return false;
			}
			/// This code is currently not supported
			return true;
		}
		
		public bool notEquals(MenuItemEnteredBody value)
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
			m_MenuItemEnteredRecord = value.getMenuItemEnteredRecord();
			m_MenuItemEnteredRecord.setParent(this);
			/// This code is currently not supported
		}
		
		 ~MenuItemEnteredBody()
		{
		}
		
	
		protected MenuItemEnteredRecord m_MenuItemEnteredRecord = new  MenuItemEnteredRecord();
	}
	protected MenuItemEnteredBody m_MenuItemEnteredBody = new  MenuItemEnteredBody();
	public MenuItemEntered.MenuItemEnteredBody getMenuItemEnteredBody()
	{
		return m_MenuItemEnteredBody;
	}
	
	public void setMenuItemEnteredBody(MenuItemEnteredBody value)
	{
		m_MenuItemEnteredBody = value;
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
		
		size += m_MenuItemEnteredBody.getSize();
		
		return size;
	}
	
	public override void encode(byte[] bytes, int pos)
	{
		
		if (bytes == null)
		{
			return;
		}
		
		m_MenuItemEnteredBody.encode(bytes, pos);
		pos += m_MenuItemEnteredBody.getSize();
		
	}
	
	public override void decode(byte[] bytes, int pos)
	{
		
		if (bytes == null)
		{
			return;
		}
		
		m_MenuItemEnteredBody.decode(bytes, pos);
		pos += m_MenuItemEnteredBody.getSize();
		if(pos < bytes.Length)
		{
			for(int i = pos; i<bytes.Length; i++)
			{
				bytes[i] = 0;
			}
		}
	}
	
	public MenuItemEntered setAs(MenuItemEntered value)
	{
		m_MenuItemEnteredBody = value.m_MenuItemEnteredBody;
		
		return this;
	}
	
	public bool  isEqual(MenuItemEntered value)
	{
		if (!this.getMenuItemEnteredBody().isEqual(value.getMenuItemEnteredBody()))
		{
			return false;
		}
		
		return true;
	}
	
	public bool  notEquals(MenuItemEntered value)
	{
		return !this.isEqual(value);
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

}
