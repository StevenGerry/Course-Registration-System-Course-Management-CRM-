import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

//Student inherit from a class named User.
public class Student extends User implements Serializable, StudentInterface{
	private String netID;
	
    //constructor with all parameter
    public Student(String Username, String Password, String FirstName, String LastName, ArrayList<Course> CourseList, String netID) {
        super(Username, Password, FirstName, LastName, CourseList);
        this.netID = netID;
    }
    
    //constructor when Admin register a Student
    public Student(String FirstName, String LastName, String netID) {
        super(FirstName, LastName);
        this.netID = netID;
    }

    //method to view all courses and related information. It use getAllCourses to get all courses information and objects.
    @Override
    public void viewAllCourses() {
        int i;
        for(i=0; i<getAllCourses().size();i++)
        {
            System.out.println(getAllCourses().get(i).getCourseName() + ", Section: "+getAllCourses().get(i).getCourseSelectionNum()+ ", Instructor: " + getAllCourses().get(i).getCourseInst() + ", Location: " + getAllCourses().get(i).getCourseLocation());
        }
        System.out.println("================END=================");
    }
    
    //like the viewAllCourse() method, before showing the course list, check full or not by if.
    public void viewNotFullCourse() {
        int i;
        for(i=0; i<getAllCourses().size();i++)
        {
            if (getAllCourses().get(i).getCurrent()<getAllCourses().get(i).getMaximum()) {
                System.out.println(getAllCourses().get(i).getCourseName()+",Section:"+getAllCourses().get(i).getCourseSelectionNum());
            }
        }
        System.out.println("================END=================");
    }

    //Method to register oneself into a course (in this case the student must enter the course name, section). Use Scanner to get information and use method of addStudent() to add a student to a course.
    public void registerACourse(String Username,String Password) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Course Name: ");
            String addcoursename = input.nextLine();
            System.out.print("Course Section: ");
            String addcoursesection = input.nextLine();
            int SectionNumber = Integer.parseInt(addcoursesection);
            int i;
            int flag = 0;
            int registered = 0;
            for (i = 0; i < getAllCourses().size(); i++) {
                if (getAllCourses().get(i).getCourseName().equals(addcoursename) && getAllCourses().get(i).getCourseSelectionNum() == SectionNumber) {
                    //check full or not and the same section
                    for (int j=0;j<getAllCourses().get(i).Student.size();j++) {
                        if (getAllCourses().get(i).Student.get(j).getFirstName().equals(this.getFirstName()) && getAllCourses().get(i).Student.get(j).getLastName().equals(this.getLastName())) {
                            registered=1;
                        }
                    }
                    if (registered == 1) {
                        getAllCourses().get(i).addStudent(this, getAllCourses().get(i));
                        System.out.println("   You Have Registered  Course!!   ");
                        if (getAllCourses().get(i).getCurrent() >= getAllCourses().get(i).getMaximum()) {
                            System.out.println("      This Course is FULL!!!!      ");}
                        break;
                    }
                    if (getAllCourses().get(i).getCurrent() >= getAllCourses().get(i).getMaximum()) {
                        System.out.println("      This Course is FULL!!!!      ");
                        break;
                    }
                    else if (registered != 1){//success
                        getAllCourses().get(i).addStudent(this, getAllCourses().get(i));
                        System.out.println("         Register SUCCESS!         ");
                        System.out.println("Course Name: " + addcoursename);
                        System.out.println("Course Section: " + addcoursesection);
                        System.out.println("First Name: " + this.getFirstName());
                        System.out.println("Last Name: " + this.getLastName());
                        flag = 1;
                    }
                }
            }
            if (flag == 0) {
                System.out.println("         Register Failed!!!        ");
            }
            System.out.println("================END=================");
        }catch (Exception e)
        {
            System.out.println("--INPUT ERROR!!--");
        }
    }

    //Method to withdraw from a course(in this case the Student will be asked to enter the course name, then the Student will be taken off from the given course’s list)
    public void withdrawCourse(String firstName, String lastName) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("WithDraw Course Name: ");
            String delcoursename = input.nextLine();
            System.out.print("Course Section: ");
            String delcoursesection = input.nextLine();
            int SectionNumber = Integer.parseInt(delcoursesection);
            int i;
            int flag = 0;
            for (i = 0; i < getAllCourses().size(); i++) {
                if (getAllCourses().get(i).getCourseName().equals(delcoursename) && getAllCourses().get(i).getCourseSelectionNum() == SectionNumber) {
                    for (int j = 0; j < getAllCourses().get(i).Student.size(); j++) {
                        if (getAllCourses().get(i).Student.get(j).getFirstName().equals(firstName) && getAllCourses().get(i).Student.get(j).getLastName().equals(lastName)) {
                            getAllCourses().get(i).delStudent(getAllCourses().get(i).Student.get(j), getAllCourses().get(i));
                            System.out.println("WithDraw SUCCESS!");
                            System.out.println("Course Name: " + delcoursename);
                            System.out.println("Course Section: " + delcoursesection);
                            System.out.println("First Name: " + firstName);
                            System.out.println("Last Name: " + lastName);
                            flag = 1;
                        } else {
                            System.out.println("Withdraw FAILED! Please Check!");
                        }
                        System.out.println("================END=================");
                    }
                }
            }
            if (flag == 0) {
                System.out.println("Withdraw FAILED! Please Check!");
            }
        }
        catch (Exception e)
        {
            System.out.println("--INPUT ERROR!!--");
        }

    }

    //Method to view all courses that the current Student is registered in. Use getAllCourses().Student to get the Student ArrayList.
    public void viewNowCourse(String firstName, String lastName) {
        System.out.println("============ YOUR COURSE ===========");
        int flag=0;
        int num=0;
        for(int i=0; i<getAllCourses().size();i++)
        {

            for (int j=0;j<getAllCourses().get(i).Student.size();j++)
            {
                if (getAllCourses().get(i).Student.get(j).getFirstName().equals(firstName)&&getAllCourses().get(i).Student.get(j).getLastName().equals(lastName))
                {
                    num++;
                    System.out.println(num+"."+getAllCourses().get(i).getCourseName());
                    flag=1;
                }
//                else
//                {
//                    System.out.println("============ NO COURSE ============");
//                }
            }
        }
        if (flag==0)
        {
            System.out.println("             NO COURSE            ");
            System.out.println("==================================");
        }
    }
    
    //getter and setter
    public String getNetID(){
    	return this.netID;
    }
    
    public void setNetID(String NewID) {
    	this.netID = NewID;
    }

}
