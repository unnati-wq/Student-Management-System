package roles;

import java.util.ArrayList;

public class Student {
	
	private String stuID;
	
	private String stuName;
	
	private String stuUserName;
	
	private String stuPassword;
	
	private ArrayList <String> coursesTaken = new ArrayList <String> ();
	
	
	public Student(String name, String stuID, String user, String password) {
		
		this.stuID = stuID;
		
		this.stuName = name;
		
		this.stuUserName = user;
		
		this.stuPassword = password;
		
		
		}
	
	public String getID() {
		return stuID;
	}
	
	public String getName() {
		return stuName;
	}

	
	public String getUserName() {
		return stuUserName;
	}
	
	public String getPassword() {
		return stuPassword;
	}
	
	public ArrayList<String> getCoursesTaken() {
		return coursesTaken;
	}
	
	public String toString() {
		return "ID: " + stuID + " Name: " + stuName + " UserName: " + stuUserName + " Password: " + stuPassword;
	}

}
