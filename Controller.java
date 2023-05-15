import java.util.ArrayList;
import java.util.Scanner;

import courses.Course;
import files.FileInfoReader;
import roles.Admin;
import roles.Professor;
import roles.Student;

public class Controller {

	
	FileInfoReader filereader = new FileInfoReader();
	
		
	public static void main(String args[]) {
		
		Controller co = new Controller();
		
		co.setUp("adminInfo.txt", "courseInfo.txt", "profInfo.txt", "studentInfo.txt");
		
	}
	
	public void setUp(String admin, String course, String prof, String student) {
		//load movie database files
		filereader.setup(admin, course, prof, student);
		
			
		this.login();
	}
	
	
	public void login() {
		
		Scanner scanner = new Scanner(System.in);
		String option;
		while(true) {
			
			System.out.println("  --------------------------  ");
			System.out.println("  Students Management System  ");
			System.out.println("  --------------------------  ");
			System.out.println();
			System.out.println("   1 -- Login as a student  ");
			System.out.println("   2 -- Login as a professor  ");
			System.out.println("   3 -- Login as an admin  ");
			System.out.println("   4 -- Quit the system  ");
			System.out.println();
			System.out.println("  Please enter your option, eg. '1'. ");
			
			option = scanner.next();
			if(option.equals("1")) {
				System.out.println("Please Enter username or type q to quit");
				String username = scanner.next();
				if(username.equals("q")){
						continue;
					}
				if(!validstudentusername(username, filereader.getstuInfo())) {
					System.out.println("Invalid username");
					continue;
				}
				else{
					System.out.println("Please Enter password or type q to quit");
					String password = scanner.next();
					if(password.equals("q")){
						continue;
					}
					String v = this.validstudentpassword(password, filereader.getstuInfo());
					if(!v.equals("f")) {
						while(true) {
							System.out.println("---------------------------------");
							System.out.println("       Welcome, " + v);
							System.out.println("---------------------------------");
							System.out.println("  1 -- View all Courses ");
							System.out.println("  2 -- Add Courses to your list ");
							System.out.println("  3 -- View enrolled Courses ");
							System.out.println("  4 -- Drop Courses in your list ");
							System.out.println("  5 -- Viw grades ");
							System.out.println("  6 -- Return to previous menu ");
							String stuchoice = scanner.next();
							if(stuchoice.equals("1")) {
								this.viewallcourses(filereader.getcourseInfo());
							}
							else if(stuchoice.equals("2")) {
								boolean val = false;
								while(!val) {
									System.out.println("Please select the course ID you want to add to your list, eg. 'CIT590'. ");
									String course = scanner.next();
									val = this.addcourses(course, v, filereader.getstuInfo(), filereader.getcourseInfo());
								}
							}
							else if(stuchoice.equals("3")) {
								System.out.println("The courses in your list:");
								this.viewenrolledcourses(v, filereader.getstuInfo(), filereader.getcourseInfo());
							}
							else if(stuchoice.equals("4")) {
								boolean flag = false;
								while(!flag) {
									System.out.println("The courses in your list");
									this.viewenrolledcourses(v, filereader.getstuInfo(), filereader.getcourseInfo());
									System.out.println();
									System.out.println("Please enter the ID of the course which you want to drop. eg: 'CIT591'. ");
									String course = scanner.next();
									if(course.equals("q")) {
										break;
									}
									flag = this.dropCourse(v, course, filereader.getstuInfo());
									this.viewenrolledcourses(v, filereader.getstuInfo(), filereader.getcourseInfo());
								}
								
							}
							else if(stuchoice.equals("5")) {
								System.out.println("Here are the courses you have already, with your grade in a letter format. ");
								this.viewGrades(v, filereader.getstuInfo(), filereader.getcourseInfo());
								}
							else if(stuchoice.equals("6")) {
								break;
							}
						}
					}
					else {
						continue;
					}
				}
	}
			
			else if(option.equals("2")) {
				System.out.println("Please enter username or type q to quit");
				String username = scanner.next();
				if(username.equals("q")){
						continue;
					}
				if(!validprofusername(username, filereader.getProfInfo())) {
					System.out.println("Invalid username");
					continue;
				}
				else{
					System.out.println("Please Enter password or type q to quit");
					String password = scanner.next();
					if(password.equals("q")){
						continue;
					}
					String v = this.validprofpassword(password, filereader.getProfInfo());
					if(!v.equals("f")) {
						while(true) {
							System.out.println("---------------------------------");
							System.out.println("       Welcome, " + v);
							System.out.println("---------------------------------");
							System.out.println("  1 -- View given Courses ");
							System.out.println("  2 -- View student list of given courses. ");
							System.out.println("  3 -- Return to previous menu ");
							String profchoice = scanner.next();
							if(profchoice.equals("1")) {
								this.viewGivenCourses(v, filereader.getcourseInfo());
							}
							else if(profchoice.equals("2")) {
								this.viewGivenCourses(v, filereader.getcourseInfo());
								System.out.println();
								System.out.println("Please enter the course ID, eg 'CIT591'. ");
								System.out.println("or type q to quit.");
								String course = scanner.next();
								if(course.equals("q")) {
									break;
								}
								this.viewStudentList(course, filereader.getstuInfo(), filereader.getcourseInfo());
								
							}
							else if(profchoice.equals("3")) {
								break;
							}

							
							
						}
					}
					else {
						continue;
					}
				
				}
			}
			
			else if(option.equals("3")) {
				System.out.println("Please enter username or type q to quit");
				String username = scanner.next();
				if(username.equals("q")){
						continue;
					}
				if(!validadminusername(username, filereader.getAdminInfo())) {
					System.out.println("Invalid username");
					continue;
				}
				else{
					System.out.println("Please Enter password or type q to quit");
					String password = scanner.next();
					if(password.equals("q")){
						continue;
					}
					String v = this.validadminpassword(password, filereader.getAdminInfo());
					if(!v.equals("f")) {
						while(true) {
							System.out.println("---------------------------------");
							System.out.println("       Welcome, " + v);
							System.out.println("---------------------------------");
							System.out.println("  1 -- View all Courses ");
							System.out.println("  2 -- Add new courses. ");
							System.out.println("  3 -- Delete courses. ");
							System.out.println("  4 -- Add new Professor. ");
							System.out.println("  5 -- Delete Professor. ");
							System.out.println("  6 -- Add new Student. ");
							System.out.println("  7 -- Delete Student. ");
							System.out.println("  8 -- Return to previous menu ");
							String adminchoice = scanner.next();
							if(adminchoice.equals("1")) {
								this.viewallcourses(filereader.getcourseInfo());
							}
							else if(adminchoice.equals("2")) {
								this.addCourse(scanner, filereader.getcourseInfo(), filereader.getProfInfo());
								
							}
							else if(adminchoice.equals("3")) {
								System.out.println("Please enter the course ID to delete or type q to quit.");
								String course = scanner.next();
								if(course.equals("q")){
									break;
								}
								else {
									this.deleteCourse(course, filereader.getcourseInfo());
								}
								
						
							}
							else if(adminchoice.equals("4")) {
								this.addProfessor(scanner, filereader.getProfInfo());
								
							}
							else if(adminchoice.equals("5")) {
								System.out.println("Please enter the Professor ID to delete or type q to quit.");
								String prof = scanner.next();
								if(prof.equals("q")){
									break;
								}
								else {
									this.deleteProfessor(prof, filereader.getProfInfo());
								}
							}
							else if(adminchoice.equals("6")) {
								this.addStudent(scanner, filereader.getstuInfo());
								
							}
							else if(adminchoice.equals("7")) {
								System.out.println("Please enter the student ID to delete or type q to quit.");
								String student = scanner.next();
								if(student.equals("q")){
									break;
								}
								else {
									this.deleteStudent(student, filereader.getstuInfo());
								}
								
							}
							else if(adminchoice.equals("8")) {
								break;
							}
						}
					}
					else {
						continue;
					}
				
				}
			}
			
			else if(option.equals("4")) {
				break;
			}
			
		}
		
		
		
		

		
	}
	
	public boolean validstudentusername(String name, ArrayList <Student> studentInfo) {
		
		String u;
		for(int i = 0; i<studentInfo.size(); i++) {
			u = studentInfo.get(i).getUserName();
			if(u.equals(name)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public String validstudentpassword(String pass, ArrayList <Student> studentInfo) {
		
		String p;
		for(int i = 0; i<studentInfo.size(); i++) {
			p = studentInfo.get(i).getPassword();
			if(p.equals(pass)){
				return studentInfo.get(i).getName();
			}
		}
		
		return "f";
		
	}
	
	public void viewallcourses(ArrayList <Course> courseInfo) {
		
		for(int i = 0; i<courseInfo.size(); i++) {
			System.out.println(courseInfo.get(i));
		}
	}
	
	public boolean addcourses(String course, String name, ArrayList <Student> studentInfo, ArrayList <Course> courseInfo ) {
		
		String s;
		int flag = -1;
		String ost;
		String oet;
		String nst;
		String net;
		ArrayList <String> courses = new ArrayList <String>();
		for(int i = 0; i<studentInfo.size(); i++) {
			s = studentInfo.get(i).getName();
			if(s.equals(name)) {
				flag = i;
				courses = studentInfo.get(i).getCoursesTaken();
			}
		}
		for(int k = 0; k<courses.size(); k++) {
			String [] line = courses.get(k).split(":");
			if(line[0].trim().equals(course)) {

				System.out.println("The course you selected is already in the list.");
				return false;
			}
			else {
				flag = k;
				
			}
		}
		boolean result = false;
		if(flag != -1) {
			nst = this.starttime(course, filereader.getcourseInfo());
			net = this.endtime(course, filereader.getcourseInfo());
			for( int i = 0; i<courses.size(); i++) {
				String [] line = courses.get(i).split(":");
				String c = line[0].trim();
				System.out.println(c);
				ost = this.starttime(c, filereader.getcourseInfo());
				oet = this.endtime(c, filereader.getcourseInfo());
				
				result = this.time(ost, oet, nst, net);
			}
			if(result) {
				studentInfo.get(flag).getCoursesTaken().add(course + ": N/A");
				System.out.println("Course added successfully.");
				return true;
			}
			else {
				return false;
			}
		}
		return false;
		
	}
	
	public String starttime(String course, ArrayList <Course> courseInfo) {
		
		String s;
		String time = " ";
		for(int i = 0; i<courseInfo.size(); i++) {
			s = courseInfo.get(i).getID();
			if(s.equals(course)) {
				time = courseInfo.get(i).getStartTime();
				break;
			}
			
		}
		return time;
	}
	
	public String endtime(String course, ArrayList <Course> courseInfo) {
		
		String s;
		String time = " ";
		for(int i = 0; i<courseInfo.size(); i++) {
			s = courseInfo.get(i).getID();
			if(s.equals(course)) {
				time = courseInfo.get(i).getEndTime();
				break;
			}
			
		}
		return time;
	}
	
	public boolean time(String start, String end, String start1, String end2) {
		
		String [] array1 = start.trim().split(":");
		String [] array2 = end.trim().split(":");
		String [] array3 = start1.trim().split(":");
		String [] array4 = end2.trim().split(":");
		int h1 = Integer.parseInt(array1[0]);
		int m1 = Integer.parseInt(array1[1]);
		int h2 = Integer.parseInt(array2[0]);
		int m2 = Integer.parseInt(array2[1]);
		int h3 = Integer.parseInt(array3[0]);
		int m3 = Integer.parseInt(array3[1]);
		int h4 = Integer.parseInt(array4[0]);
		int m4 = Integer.parseInt(array4[1]);
		
		if(h3>h1 && h3<h2) {
			return false;
		}
		else if(h4>h1 && h4<h2) {
			return false;
		}
		else if(h3 == h2) {
			if(m3<m2) {
				return false;
			}
		}
		else if(h3 == h1) {
			return false;
		}
		else if(h3<h1 && h4>h1) {
			return false;
		}
		else if(h4>=h1 && h4<=h2) {
			if(h4 == h1 && m4>m2) {
				return false;
			}
			
		}
		
		return true;
		
		
	}
	
	public void viewenrolledcourses(String name,  ArrayList <Student> studentInfo, ArrayList <Course> courseInfo ) {
		
		ArrayList <String> course = new ArrayList <String>();
		for(int i = 0; i<studentInfo.size(); i++) {
			String s = studentInfo.get(i).getName();
			if(s.equals(name)) {
				course = studentInfo.get(i).getCoursesTaken();
				break;
			}
			
		}
		for(int k = 0; k<course.size(); k++) {
			String [] line = course.get(k).split(":");
			String c_name = line[0].trim();
			for(int i = 0; i<courseInfo.size(); i++) {
				if(c_name.equals(courseInfo.get(i).getID())) {
					System.out.println(courseInfo.get(i));
				}
			}
		}
		
	}
	
	public boolean dropCourse(String name, String course, ArrayList <Student> studentInfo) {
		
		ArrayList <String> courses = new ArrayList <String>();
		int val = -1;
		boolean flag = false;
		for(int i = 0; i<studentInfo.size(); i++) {
			String s = studentInfo.get(i).getName();
			if(s.equals(name)) {
				courses = studentInfo.get(i).getCoursesTaken();
				val = i;
				break;
			}
			
		}
		for(int k = 0; k<courses.size(); k++) {
			String [] line = courses.get(k).split(":");
			String c_name = line[0].trim();
			if(c_name.equals(course)) {
				studentInfo.get(val).getCoursesTaken().remove(k);
				System.out.println("Course dropped successfully.");
				flag = true;
			}
		
		}
		if(!flag) {
			System.out.println("The course isn't in your schedule.");
			System.out.println();
		}
		return flag;
	}
	
	public void viewGrades(String name, ArrayList <Student> studentInfo, ArrayList <Course> courseInfo ) {
		
		ArrayList <String> courses = new ArrayList <String>();
		for(int i = 0; i<studentInfo.size(); i++) {
			String s = studentInfo.get(i).getName();
			if(s.equals(name)) {
				courses = studentInfo.get(i).getCoursesTaken();
				break;
			}
			
		}
		for(int k = 0; k<courses.size(); k++) {
			String [] line = courses.get(k).split(":");
			String c_name = line[0].trim();
			for(int i = 0; i<courseInfo.size(); i++) {
				if(c_name.equals(courseInfo.get(i).getID())) {
					System.out.println("Grade of " + c_name + " " + courseInfo.get(i).getName() + ": " +line[1]);
				}
			}
		
		}
	}
	
	public boolean validprofusername(String name, ArrayList <Professor> profInfo) {
		
		String u;
		for(int i = 0; i<profInfo.size(); i++) {
			u = profInfo.get(i).getUserName();
			if(u.equals(name)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public String validprofpassword(String pass, ArrayList <Professor> profInfo) {
		
		String p;
		for(int i = 0; i<profInfo.size(); i++) {
			p = profInfo.get(i).getPassword();
			if(p.equals(pass)){
				return profInfo.get(i).getName();
			}
		}
		
		return "f";
		
	}
	
	public void viewGivenCourses(String name, ArrayList <Course> courseInfo) {
		
		System.out.println("---------------The Course List--------------");
		for(int i = 0; i<courseInfo.size(); i++) {
			String n = courseInfo.get(i).getLecturer();
			if(name.equals(n)) {
				System.out.println(courseInfo.get(i));
			}
		}
	}
	
	public void viewStudentList(String course, ArrayList <Student> studentInfo, ArrayList <Course> courseInfo) {
		
		for(int k = 0; k<courseInfo.size(); k++) {
			if(course.equals(courseInfo.get(k).getID())) {
				System.out.println("Students in your course " + course + " " + courseInfo.get(k).getName());
			}
		}
		for(int i = 0; i<studentInfo.size(); i++) {
			ArrayList <String> courses = new ArrayList <String>();
			courses = studentInfo.get(i).getCoursesTaken();
			for(int m = 0; m<courses.size(); m++) {
				String [] array = courses.get(m).split(":");
				
				if(array[0].trim().equals(course)) {
					System.out.println(studentInfo.get(i).getID() + " " + studentInfo.get(i).getName());
				}
			}
			
		}
	}
	
	public boolean validadminusername(String name, ArrayList <Admin> adminInfo) {
		
		String u;
		for(int i = 0; i<adminInfo.size(); i++) {
			u = adminInfo.get(i).getUserName();
			if(u.equals(name)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public String validadminpassword(String pass, ArrayList <Admin> adminInfo) {
		
		String p;
		for(int i = 0; i<adminInfo.size(); i++) {
			p = adminInfo.get(i).getPassword();
			if(p.equals(pass)){
				return adminInfo.get(i).getName();
			}
		}
		
		return "f";
		
	}
	
	public void deleteCourse(String course, ArrayList <Course> courseInfo) {
		int flag = -1;
		for(int i = 0; i<courseInfo.size(); i++) {
			if(course.equals(courseInfo.get(i).getID())) {
				courseInfo.remove(i);
				flag = i;
				break;
			}
		}
		if(flag == -1) {
			System.out.println("The course does not exist.");
		}
	}
	
	public void deleteStudent(String student, ArrayList <Student> studentInfo) {
		int flag = -1;
		for(int i = 0; i<studentInfo.size(); i++) {
			if(student.equals(studentInfo.get(i).getID())) {
				studentInfo.remove(i);
				flag = i;
				break;
			}
		}
		if(flag == -1) {
			System.out.println("The student does not exist.");
		}
	}
	
	public void deleteProfessor(String professor, ArrayList <Professor> profInfo) {
		int flag = -1;
		for(int i = 0; i<profInfo.size(); i++) {
			if(professor.equals(profInfo.get(i).getID())) {
				profInfo.remove(i);
				flag = i;
				break;
			}
		}
		if(flag == -1) {
			System.out.println("The professor does not exist.");
		}
	}
	
	public void addProfessor(Scanner scanner, ArrayList <Professor> profInfo) {
		
		String id = "";
		int flag = 0;
		boolean val = true;
		while(val) {
			System.out.println("Please enter the professor's ID or type q to quit.");
			id = scanner.next();
			if(id.equals("q")) {
				return;
			}
			for(int i = 0; i<profInfo.size(); i++) {
				if(id.equals(profInfo.get(i).getID())) {
					System.out.println("The ID already exists.");
					flag = -1;
					break;
				}
			}
		
			if(flag == -1) {
				continue;
			}
			break;
		}
		
		System.out.println("Please enter the professor's name or type q to quit.");
		String name = scanner.next();
		if(name.equals("q")) {
			return;
		}
		String user = "";
		flag = 0;
		val = true;
		while(true){
			System.out.println("Please enter a username .");
			user = scanner.next();
			
			for(int i = 0; i<profInfo.size(); i++) {
				if(user.equals(profInfo.get(i).getUserName())) {
					System.out.println("The username you entered is not available.");
					flag = -1;
					break;
				}
			}
			if(flag == -1) {
				continue;
			}
		  break;
		}
		
		System.out.println("Please enter a password.");
		String pass = scanner.next();
		Professor prof = new Professor(name,id, user, pass);
		profInfo.add(prof);
		System.out.println("Successfully added new professor: " + id + " " + name);
		

	}
	
	public void addStudent(Scanner scanner, ArrayList <Student> studentInfo) {
		
		String id = "";
		while(true) {
			System.out.println("Please enter the student's ID or type q to quit.");
			id = scanner.next();
			if(id.equals("q")) {
				return;
			}
			for(int i = 0; i<studentInfo.size(); i++) {
				if(id.equals(studentInfo.get(i).getID())) {
					System.out.println("The ID already exists.");
					continue;
				}
			}
			break;
			
		}
		
		System.out.println("Please enter the student's name or type q to quit.");
		String name = scanner.next();
		if(name.equals("q")) {
			return;
		}
		String user = "";
		while(true) {
			System.out.println("Please enter a username .");
			user = scanner.next();
			
			for(int i = 0; i<studentInfo.size(); i++) {
				if(user.equals(studentInfo.get(i).getUserName())) {
					System.out.println("The username you entered is not available.");
					continue;
				}
			}
			break;
			
		}
		System.out.println("Please enter a password.");
		String pass = scanner.next();
		Student student = new Student(name, id, user, pass);
		String course = " ";
		while(!course.equals("n")) {
			System.out.println("Please enter the ID of a course which the student already took one in a time");
			System.out.println("type q to quit type n to stop adding.");
			course = scanner.next();
			if(course.equals("q")) {
				return;
			}
			else if (course.equals("n")) {
				break;
			}
			else {
				System.out.println("Please enter the grade, eg 'A' ");
				String grade = scanner.next();
				student.getCoursesTaken().add(course + ": " + grade);
			}
			
			
		}
		studentInfo.add(student);
		System.out.println("Successfully added the student: " + id + " " + name);
		
		
	}
	
	public void addCourse(Scanner scanner, ArrayList <Course> courseInfo, ArrayList <Professor> profInfo ) {
		int flag = -1;
		String c_id = "";
		while(true) {
			System.out.println("Please enter course ID or type q to end.");
			c_id = scanner.next();
			if(c_id.equals("q")) {
				return;
			}
			for(int i = 0; i<courseInfo.size(); i++) {
				if(c_id.equals(courseInfo.get(i).getID())) {
					System.out.println("The course already exists. ");
					flag = i;
					break;
				}
			}
			if(flag == -1) {
				break;
			}
			else {
				continue;
			}
		}
		System.out.println("Please enter the course name, or type q to end.");
		String name = scanner.next();
		if(name.equals("q")) {
			return;
		}
		System.out.println("Please enter course start time, or type q to end. eg'19:08'. ");
		String st = scanner.next();
		if(st.equals("q")) {
			return;
		}
		System.out.println("Please enter course end time, or type q to end. eg'19:08'. ");
		String et = scanner.next();
		if(et.equals("q")) {
			return;
		}
		System.out.println("Please enter course date, or type q to end. eg'MW'. ");
		String cd = scanner.next();
		if(cd.equals("q")) {
			return;
		}
		System.out.println("Please enter course capacity, or type q to end. eg 72. ");
		String capacity = scanner.next();
		if(capacity.equals("q")) {
			return;
		}
		System.out.println("Please enter course lecturer's ID, or type q to end. eg'001'. ");
		String l_id = scanner.next();
		if(l_id.equals("q")) {
			return;
		}
		
		String prof_name = "";
		for(int i = 0; i<profInfo.size(); i++) {
			if(l_id.equals(profInfo.get(i).getID())){
				
				prof_name = profInfo.get(i).getName();
				break;
			}
		}
		if(flag == -1) {
			System.out.println("The professor is not in the system. Please add this professor first.");
			this.addProfessor(scanner, profInfo);
			for(int i = 0; i<profInfo.size(); i++) {
				if(l_id.equals(profInfo.get(i).getID())){
					
					prof_name = profInfo.get(i).getName();
					break;
				}
			}
		}
		else {
			ArrayList <String> courses_taught = new ArrayList<String>();
			boolean val = false;
			courses_taught = this.getCoursesTaught(prof_name, courseInfo);
			for(int i = 0; i<courses_taught.size(); i++) {
				String line = courses_taught.get(i).trim();
				String time1 = this.starttime(line, courseInfo);
				String time2 = this.endtime(line, courseInfo);
				val = this.time(time1, time2, st, et);
			}
			if(!val) {
				System.out.println("Professor time overlaps. Cannot assign course.");
				return;
			}
		}
		Course newcourse = new Course(c_id, name);
		newcourse.setStartTime(st);
		newcourse.setEndTime(et);
		newcourse.setDays(cd);
		newcourse.setCapacity(Integer.parseInt(capacity));
		newcourse.setLecturer(prof_name);
		courseInfo.add(newcourse);
		System.out.println("New course successfully added.");
		
		
	}
	
	public ArrayList <String> getCoursesTaught(String name, ArrayList <Course> courseInfo){
		
		ArrayList <String> c = new ArrayList <String>();
		for(int i = 0; i<courseInfo.size(); i++) {
			String n = courseInfo.get(i).getLecturer();
			if(name.equals(n)) {
				c.add(courseInfo.get(i).getID());
			}
		}
		return c;
	}
	
	
}
