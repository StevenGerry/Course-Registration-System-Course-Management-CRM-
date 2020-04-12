import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class User implements Serializable {
	
	private String Username;
	private String Password;
	private String FirstName;
	private String LastName;
	private ArrayList<Course> CourseList;

	//getters and setters
	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public ArrayList<Course> getCourseList() {
		return CourseList;
	}

	public void setCourseList(ArrayList<Course> courseList) {
		CourseList = courseList;
	}

	//constructor for Admin
	public User(String Username, String Password, ArrayList<Course> CourseList)
	{
		this.Username=Username;
		this.Password=Password;
		this.CourseList=CourseList;
	}

	//constructor for Student
	public User( String Username, String Password, String FirstName, String LastName, ArrayList<Course> CourseList)
	{
		this.Username=Username;
		this.Password=Password;
		this.FirstName=FirstName;
		this.LastName=LastName;
		this.CourseList=CourseList;
	}
	
	//constructor for Admin to register Student
	public User(String FirstName, String LastName){
			this.FirstName=FirstName;
			this.LastName=LastName;
	}

	//Method to get all courses
	public ArrayList <Course> getAllCourses() //this method return the latest version of the courselist, which also contains information about registered students.
	{
		return this.CourseList;
	}
	
	//Method to view all courses. Overriden by ones in Admin and Student to provide different information for these two classes.
	public void viewAllCourses() {
        for(int i=0; i<getAllCourses().size();i++)
        {
            System.out.println(getAllCourses().get(i).getCourseName()+",  Section: "+getAllCourses().get(i).getCourseSelectionNum()+",  Max Number: "+getAllCourses().get(i).getMaximum()+",  Current Number: "+getAllCourses().get(i).getCurrent());
        }
        System.out.println("================END=================");
	}
}





