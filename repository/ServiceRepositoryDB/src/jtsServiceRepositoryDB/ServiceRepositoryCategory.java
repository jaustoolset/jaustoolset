package jtsServiceRepositoryDB;

/**
 * 
 */

/**
 * @author parag
 *
 */
public class ServiceRepositoryCategory {
	private String title;
	private String description;
	private String iconName;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIconName() {
		return iconName;
	}
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	
	public String getTitle()	
	{ 
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
}


