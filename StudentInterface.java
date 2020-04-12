import java.util.ArrayList;

//Interface for a Student class that will have the signatures of the necessary methods that will be used by the Student.
public interface StudentInterface {
	public void viewAllCourses();
	public void viewNotFullCourse();
	public void registerACourse(String Username,String Password);
	public void withdrawCourse(String firstName, String lastName);
	public void viewNowCourse(String firstName, String lastName);
}
