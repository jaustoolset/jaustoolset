package jtsServiceRepositoryDB;
/**
 * Basic class to hold a repository entry
 */

/**
 * @author parag
 *
 */
public class ServiceRepositoryEntry {
	private String URI;
	private String name;
	private String author;
	private String briefDescription;
	private String verboseDescription;
	private String version;
	private int serviceEntryId;
	
	public String getURI() {
		return URI;
	}
	
	public void setURI(String URI)  {
		this.URI = URI;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBriefDescription() {
		return briefDescription;
	}
	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}
	public String getVerboseDescription() {
		return verboseDescription;
	}
	public void setVerboseDescription(String verboseDescription) {
		this.verboseDescription = verboseDescription;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getServiceEntryId() {
		return serviceEntryId;
	}
	public void setServiceEntryId(int serviceEntryId) {
		this.serviceEntryId = serviceEntryId;
	}

}
