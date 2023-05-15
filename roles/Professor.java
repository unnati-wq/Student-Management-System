package roles;

public class Professor {
	
	
	private String profID;
	
	private String profName;
	
	private String profUserName;
	
	private String profPassword;
	
	
	public Professor(String name, String profID, String user, String password) {
		
		this.profID = profID;
		
		this.profName = name;
		
		this.profUserName = user;
		
		this.profPassword = password;
		
		
		}
	
	public String getID() {
		return profID;
	}
	
	public String getName() {
		return profName;
	}

	
	public String getUserName() {
		return profUserName;
	}
	
	public String getPassword() {
		return profPassword;
	}
	
	public String toString() {
		return "ID: " + profID + " Name: " + profName + " UserName: " + profUserName + " Password: " + profPassword;
	}
	
}
