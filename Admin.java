import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

//Admin inherit from a class named User.
public class Admin extends User implements Serializable, AdminInterface{

    //constructor
    public Admin(String Username, String Password, ArrayList<Course> CourseList) {
        super(Username, Password, CourseList);
    }

    //Method to Create a new course.
    //Use scanner to get information and put into the new course object, then use getAllCourses().add() to add the new course. Default, Current is zero and the name list is NULL.
    public void createCourse() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Course Name: ");
            String C_CourseName = input.nextLine();
            System.out.print("Course ID: ");
            String C_CourseID = input.nextLine();
            System.out.print("Maximum Student: ");
            String C_Max = input.nextLine();
            int C_CourseMax = Integer.parseInt(C_Max);
            int C_CourseCurrent = 0;
            String C_Name = "NULL";
            System.out.print("Course Instructor: ");
            String C_CourseInst = input.nextLine();
            System.out.print("Course Section Number: ");
            String C_Selection = input.nextLine();
            int C_SelectionNum = Integer.parseInt(C_Selection);
            System.out.print("Course Location: ");
            String C_Location = input.nextLine();
            ArrayList<Student> C_RS;
            if (getAllCourses().size() == 0) {
            	C_RS = new ArrayList<Student>();
            } else {
            	C_RS = new ArrayList<Student>();
            	C_RS.addAll(getAllCourses().get(0).RStudent);
            }
            Course NewCourse = new Course(C_CourseName, C_CourseID, C_CourseMax, C_CourseCurrent, C_Name, C_CourseInst, C_SelectionNum, C_Location, C_RS);
            getAllCourses().add(NewCourse);
            System.out.println("=======Create Course Success!======");
        }
        catch (Exception e)
        {
            System.out.println("--INPUT ERROR!!--");
        }
    }

    //Method to remove a course through input the course name and the section.
    public void removeCourse() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Remove Course Name: ");
            String rmcoursename = input.nextLine();
            System.out.print("Remove Course Section: ");
            String rmcoursesection = input.nextLine();
            int rmsection = Integer.parseInt(rmcoursesection);
            for (int i = 0; i < getAllCourses().size(); i++) {
                if (getAllCourses().get(i).getCourseName().equals(rmcoursename) && getAllCourses().get(i).getCourseSelectionNum() == rmsection) {
                    getAllCourses().remove(i);
                    System.out.println("=========Remove SUCCESS!!========");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("--INPUT ERROR!!--");
        }
    }

    //Method to edit a course (this will allow the Admin to edit any information on the course except for course ID and name)
    public void editCourse() {
        try {
            int flag = 0;
            Scanner input = new Scanner(System.in);
            System.out.print("Edit Course Name: ");
            String edcoursename = input.nextLine();
            System.out.print("Edit Course Section: ");
            String edcoursesection = input.nextLine();
            int edsection = Integer.parseInt(edcoursesection);
            for (int i = 0; i < getAllCourses().size(); i++) {
                if (getAllCourses().get(i).getCourseName().equals(edcoursename) && getAllCourses().get(i).getCourseSelectionNum() == edsection) {

                    //first show the original data then input the rewrite data.
                    System.out.println("Maximun Num: " + getAllCourses().get(i).getMaximum());
                    System.out.print("ReWrite: ");
                    String recoursemax = input.nextLine();
                    int remax = Integer.parseInt(recoursemax);
                    getAllCourses().get(i).setMaximum(remax);

                    System.out.println("Course Instructor: " + getAllCourses().get(i).getCourseInst());
                    System.out.print("ReWrite: ");
                    String recourseinst = input.nextLine();
                    getAllCourses().get(i).setCourseInst(recourseinst);

                    System.out.println("Course Location: " + getAllCourses().get(i).getCourseLocation());
                    System.out.print("ReWrite: ");
                    String recourselocation = input.nextLine();
                    getAllCourses().get(i).setCourseLocation(recourselocation);
                    flag = 1;
                    System.out.println("========== Edit SUCCESS =========");
                }
            }
            if (flag == 0) {
                System.out.println("========== Edit FAILED ==========");
            }
        }
        catch (Exception e)
        {
            System.out.println("--INPUT ERROR!!--");
        }
    }

    //Method to display information for a given course by course ID. Use Student.getFirstName() and Student.getLastName() to get the full name.
    public void displayByCID() {
        int flag = 0;
        Scanner input=new Scanner(System.in);
        System.out.println("Enter Course ID");
        String DID= input.nextLine();
        for (int i=0;i<getAllCourses().size();i++)
        {
            if (getAllCourses().get(i).getCourseId().equals(DID))
            {
                System.out.println("Course Name: "+getAllCourses().get(i).getCourseName());
                System.out.println("Max StudentNum: " +getAllCourses().get(i).getMaximum());
                System.out.println("Current StudentNum: "+ getAllCourses().get(i).getCurrent());
                System.out.println("Student Name: ");
                for(int j=0;j<getAllCourses().get(i).Student.size();j++) {
                    System.out.print(getAllCourses().get(i).Student.get(j).getFirstName()+" ");
                    System.out.print(getAllCourses().get(i).Student.get(j).getLastName()+",");
                }
                System.out.println("");
                System.out.println("Instructor: "+ getAllCourses().get(i).getCourseInst());
                System.out.println("Location: "+getAllCourses().get(i).getCourseLocation());
                System.out.println("================END=================");
                flag=1;
            }
        }
        if (flag==0)
        {
            System.out.println("--ERROR: CAN NOT FIND!!!--");
        }
    }

    //Method to register a student. This option will allow the Admin to add a student into the system.
    public void registerStudent() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the FIRST NAME");
        String firstName = input.nextLine();
        System.out.println("Enter the LAST NAME");
        String lastName = input.nextLine();
        System.out.println("Enter the netID");
        String netID = input.nextLine();
        Student newSt = new Student(firstName, lastName, netID);
        for (int i = 0; i < getAllCourses().size(); i++) {
        	getAllCourses().get(i).RStudent.add(newSt);
        }
        System.out.println("      Success Register Student!     ");
        System.out.println("================END=================");
    }

    //Method to view all courses. For every course the Admin should be able to see the number of students registered, and the maximum number of students allowed to be registered.
    @Override
    public void viewAllCourses() {
        int i;
        for(i=0; i<getAllCourses().size();i++)
        {
            System.out.println(getAllCourses().get(i).getCourseName()+", Section: "+getAllCourses().get(i).getCourseSelectionNum()+", Instructor: "+ getAllCourses().get(i).getCourseInst() + ", Location: " + getAllCourses().get(i).getCourseLocation() +",  Max Number: "+getAllCourses().get(i).getMaximum()+", Current Number: "+getAllCourses().get(i).getCurrent());
        }
        System.out.println("================END=================");
    }

    //like the NOT Full Method in Student.
    public void viewFull() {
        int flag = 0;
        for(int i = 0; i<getAllCourses().size();i++)
        {
            if (getAllCourses().get(i).getCurrent()>=getAllCourses().get(i).getMaximum()) {
                System.out.println(getAllCourses().get(i).getCourseName()+", Section: "+getAllCourses().get(i).getCourseSelectionNum());
                flag++;
            }
        }
        if (flag == 0) {
        	System.out.println("         No Course is Full!         ");
        }
        System.out.println("================END=================");
    }

    //Method to write to a file the list of course that are full. Use the BufferedWriter to save the .csv file. Use write to write information and use newLine(); to realize the 'Enter'.
    public void writeFull2File() {
        String fileName="FullCourses.csv";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < getAllCourses().size(); i++) {
                if (getAllCourses().get(i).getCurrent() >= getAllCourses().get(i).getMaximum()) {
                        bw.write(getAllCourses().get(i).getCourseName()+","+getAllCourses().get(i).getCourseSelectionNum());
                        bw.newLine();
                }
            }
            System.out.println("       DATA  FILE  SAVED        ");
            bw.close();
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }

        System.out.println("================END=================");
    }

    //Method to view the names of the students that are registered in a specific course.
    //Overload
    public void viewStuByCourse() {
        Scanner input =new Scanner(System.in);
        System.out.print("Enter CourseName: ");
        String CourseName= input.nextLine();
        System.out.print("Enter Section: ");
        int secNumber= Integer.parseInt(input.nextLine());
        int i;
        for(i=0; i<getAllCourses().size();i++)
        {
            if (getAllCourses().get(i).getCourseName().equals(CourseName) && getAllCourses().get(i).getCourseSelectionNum() == secNumber)
            {
                for (int j = 0; j < getAllCourses().get(i).Student.size(); j++) {
                    System.out.print(getAllCourses().get(i).Student.get(j).getFirstName() + " ");
                    System.out.println(getAllCourses().get(i).Student.get(j).getLastName());
                }
            }
        }
        System.out.println("================END=================");

    }

    //Method to show the ALL NAME By Course. Used by Method:"viewAllCourses()"
    //Overload
    public String viewStuByCourse(String CourseName, int CourseSection) {
        int i;
        String rrr="";
        for(i=0; i<getAllCourses().size();i++) {
            if (getAllCourses().get(i).getCourseName().equals(CourseName)&&getAllCourses().get(i).getCourseSelectionNum()==CourseSection)
            {
                for (int j = 0; j < getAllCourses().get(i).Student.size(); j++) {
                    rrr = rrr + getAllCourses().get(i).Student.get(j).getFirstName() + " " + getAllCourses().get(i).Student.get(j).getLastName() + ", ";
                }
            }
        }
        return rrr;
    }

    //Method to view the list of courses that a given student is registered in. Given a student's first name and last name the system will display all the courses that student is registered in.
    public void viewCourseByStu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Student's FIRST NAME");
        String firstName = input.nextLine();
        System.out.println("Enter Student's LAST NAME");
        String lastName = input.nextLine();
        System.out.println("===========Student Course===========");
        int flag=0;
        int num=1;
        for(int i=0; i<getAllCourses().size();i++)
        {
            for (int j=0;j<getAllCourses().get(i).Student.size();j++)
            {

                if (getAllCourses().get(i).Student.get(j).getFirstName().equals(firstName)&&getAllCourses().get(i).Student.get(j).getLastName().equals(lastName))
                {
                    System.out.println(num+"."+getAllCourses().get(i).getCourseName());
                    flag=1;
                    num++;
                }
            }
        }
        if (flag==0)
        {
            System.out.println("             NO COURSE!!            ");
            System.out.println("====================================");
        }
        System.out.println("================END=================");
    }

    //Method to use sort methods to sort the list. And the list is sorted by Current number. Get Current Number by Course.getCurrent().
    public void sortByNumber(ArrayList <Course> CourseList) {
        System.out.println("= SortBy Current Registered Number =");
        CourseList.sort(Comparator.comparing(Course -> Course.getCurrent()));
        for(int i=CourseList.size()-1;i>=0;i--)
        {
            System.out.println(CourseList.get(i).getCurrent()+","+CourseList.get(i).getCourseName());//the most to the least
        }
//        for(Course crs:CourseList)
//        {
//            System.out.println(crs.getCurrent()+","+crs.getCourseName());             //the least to the most
//        }
        System.out.println("================END=================");
    }

}