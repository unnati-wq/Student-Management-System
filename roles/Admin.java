package roles;

public class Admin{
	
	
	private String AdminID;
	
	private String AdminName;
	
	private String AdminUserName;
	
	private String AdminPassword;
	
	
	public Admin (String AdminID, String name, String user, String password) {
		
		this.AdminID = AdminID;
		
		this.AdminName = name;
		
		this.AdminUserName = user;
		
		this.AdminPassword = password;
		
		
		}
	
	public String getID() {
		return AdminID;
	}
	
	public String getName() {
		return AdminName;
	}

	
	public String getUserName() {
		return AdminUserName;
	}
	
	public String getPassword() {
		return AdminPassword;
	}
	
	public String toString() {
		return "ID: " + AdminID + " Name: " + AdminName + " UserName: " + AdminUserName + " Password: " + AdminPassword;
	}
	
}
