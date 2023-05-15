package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import courses.Course;
import roles.Admin;
import roles.Professor;
import roles.Student;



public class FileInfoReader{
	
	
	private ArrayList<Admin> adminInfo = new ArrayList<Admin>();
	
	private ArrayList<Course> courseInfo = new ArrayList<Course>();
	
	private ArrayList<Professor> profInfo = new ArrayList<Professor>();
	
	private ArrayList<Student> studentInfo = new ArrayList<Student>();
	
	public void setup(String admin, String course, String prof, String student) {
		
		
		try {
			File f = new File (admin);
			FileReader fd = new FileReader(f);
			BufferedReader br = new BufferedReader(fd);
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String [] array = line.trim().split(";");
				Admin newAdmin = new Admin(array[0].trim(), array[1].trim(),array[2].trim(), array[3].trim());
				
				adminInfo.add(newAdmin);
			}
			fd.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			File f = new File (prof);
			FileReader fd = new FileReader(f);
			BufferedReader br = new BufferedReader(fd);
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String [] array = line.trim().split(";");
				Professor newprof = new Professor(array[0].trim(), array[1].trim(),array[2].trim(), array[3].trim());
				
				profInfo.add(newprof);
			}
			fd.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			File f = new File (student);
			FileReader fd = new FileReader(f);
			BufferedReader br = new BufferedReader(fd);
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String [] array = line.trim().split(";");
				Student newstu = new Student(array[1].trim(), array[0].trim(),array[2].trim(), array[3].trim());
				String [] array1 = array[4].trim().split(",");
				for(int i = 0; i<array1.length; i++) {
					newstu.getCoursesTaken().add(array1[i].trim());
				}
				
				studentInfo.add(newstu);
			}
			fd.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			File f = new File (course);
			FileReader fd = new FileReader(f);
			BufferedReader br = new BufferedReader(fd);
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String [] array = line.trim().split(";");
				Course newcourse = new Course(array[0].trim(), array[1].trim());
				newcourse.setLecturer(array[2].trim());
				newcourse.setDays(array[3].trim());
				newcourse.setStartTime(array[4].trim());
				newcourse.setEndTime(array[5].trim());
				newcourse.setCapacity(Integer.parseInt(array[6].trim()));
				
				courseInfo.add(newcourse);
			}
			fd.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public ArrayList<Admin> getAdminInfo() {
		return this.adminInfo;
	}
	
	public ArrayList<Professor> getProfInfo() {
		return this.profInfo;
	}
	
	public ArrayList<Student> getstuInfo() {
		return this.studentInfo;
	}
	
	public ArrayList<Course> getcourseInfo() {
		return this.courseInfo;
	}

}
