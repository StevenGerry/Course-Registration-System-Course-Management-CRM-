import java.util.ArrayList;

//Interface for Admin class that will have the signatures of the necessary methods that will be used by the Admin.
public interface AdminInterface {
	public void createCourse();
	public void removeCourse();
	public void editCourse();
	public void displayByCID();
	public void registerStudent();
	public void viewAllCourses();
	public void viewFull();
	public void writeFull2File();
	public void viewStuByCourse();
	public void viewCourseByStu();
	public void sortByNumber(ArrayList <Course> CourseList);
}
