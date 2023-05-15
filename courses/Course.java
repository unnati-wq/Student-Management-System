package courses;


public class Course {

	
	private String courseID;
	
	private String courseName;
	
	private String lecturer;
	
	private String days;
	
	private String starttime;
	
	private String endtime;
	
	private int capacity;
	
	private int students = 0;
	
	
	public Course(String id, String name){
		
		this.courseID = id;
		
		this.courseName = name;
		
		}
	
	public String getID() {
		return courseID;
	}
	
	public String getName() {
		return courseName;
	}

	public void setLecturer(String l) {
		this.lecturer = l;
	}
	
	public void setDays(String d) {
		this.days = d;
	}
	
	public void setStartTime(String st) {
		this.starttime = st;
	}
	
	public void setEndTime(String et) {
		this.endtime = et;
	}
	
	public void setCapacity(int c) {
		this.capacity = c;
	}
	
	public String getLecturer() {
		return this.lecturer;
	}
	
	public String getDays() {
		return this.days;
	}
	
	public String getStartTime() {
		return this.starttime;
	}
	
	public String getEndTime() {
		return this.endtime;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public void setStudent() {
		this.students = students + 1;
	}
	
	public int getStudent() {
		return this.students;
	}
	
	public String toString() {
		return courseID + "|" + courseName + ", " + starttime + "-" + endtime + " on " + days + ", " + "with course capacity: " + capacity + ", students: " + students + ", lecturer: Professor " + lecturer;
	}

}
