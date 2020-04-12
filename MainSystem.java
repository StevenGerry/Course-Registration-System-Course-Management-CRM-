import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;


public class MainSystem {

    public static void main(String[] args) throws IOException,ClassNotFoundException {

        ArrayList <Course> CourseList = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        File file= new File("CourseSerialCache.csv");
        try {//serialization file read
            if(file.exists()){
                FileInputStream fis = new FileInputStream("CourseSerialCache.csv");
                ObjectInputStream ois = new ObjectInputStream(fis);
                CourseList = (ArrayList) ois.readObject();
                ois.close();
                fis.close();
            }
            else
            {
                try{//no file so that to read original CSV file
                    BufferedReader csvReader = new BufferedReader(new FileReader("MyUniversityCourses.csv"));
                    String line = "";
                    line= csvReader.readLine();
                    while((line=csvReader.readLine())!=null) {
                        String item[] = line.split(",");
                        String CourseName = item[0];
                        String CourseId = item[1];
                        int Maximum = Integer.parseInt(item[2]);
                        int Current = Integer.parseInt(item[3]);
                        String ListName = item[4];
                        String CourseInst = item[5];
                        int CourseSelectionNum = Integer.parseInt(item[6]);
                        String CourseLocation = item[7];
                        Course course = new Course(CourseName,CourseId,Maximum,Current,ListName,CourseInst,CourseSelectionNum,CourseLocation);
                        CourseList.add(course);
                    }
                }
                catch(IOException ioe)
                {
                    System.out.println("FILE NOT FOUND");
                    ioe.printStackTrace();
                    System.exit(0);
                }

            }

        }
        catch(IOException ioe){
            ioe.printStackTrace();
            return;
        }catch(ClassNotFoundException c){
            System.out.println("CLASS NOT FOUND");
            c.printStackTrace();
            return;
        }

        while(1==1) {//doing repeatedly until get the exit choice
        	
            DisplayWelcome();

            System.out.print("Choice: ");
            String choose = input.nextLine();
            int whereexit=0;
            switch (choose) {
                case "1": {//process for administrator
                    System.out.print("Username: ");
                    String username = input.nextLine();
                    System.out.print("Password: ");
                    String password = input.nextLine();
                    String Ans="";
                    Ans = CheckAdmin(username,password);
                    if (!Ans.equals("Admin"))
                    {
                        System.out.println(Ans);
                        break;
                    }

                    Admin admin = new Admin(username, password, CourseList);

                    while(1==1) {
                        DisplayMenuAdmin();
                        System.out.print("Select: ");
                        String selection = input.nextLine();

                        if (selection.equals("1")) {
                            admin.createCourse();
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                        if (selection.equals("2")) {
                            admin.removeCourse();
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                        if (selection.equals("3")) {
                            admin.editCourse();
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                        if (selection.equals("4")) {
                            admin.displayByCID();
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                        if (selection.equals("5")) {
                            admin.registerStudent();
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                        if (selection.equals("6"))
                        {
                            SaveData(admin.getAllCourses());
                            CourseList = admin.getAllCourses();
                            break;
                        }
                        if (selection.equals("11")) {
                            admin.viewAllCourses();
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                        if (selection.equals("12")) {
                            admin.viewFull();
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                        if (selection.equals("13")) {
                            admin.writeFull2File();
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                        if (selection.equals("14")) {
                            admin.viewStuByCourse();
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                        if (selection.equals("15")) {
                            admin.viewCourseByStu();
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                        if (selection.equals("16")) {
                            admin.sortByNumber(CourseList);
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                    }
                    break;
                }
                case "2": {//process for the student user

                    System.out.println("Enter Your netID");
                    String netID = input.nextLine();
                    System.out.println("Enter Your firtName");
                    String firstName = input.nextLine();
                    System.out.println("Enter Your lastName");
                    String lastName = input.nextLine();
                    
                	//check if the student is registered
                    int indic = -1;
                    if (CourseList.size() == 0) {
                    	System.out.println("There are no course on the system for you to register.");
                    	break;
                    } else {
                    	for (int i = 0; i < CourseList.get(0).RStudent.size(); i++) {
                        	if (firstName.equals(CourseList.get(0).RStudent.get(i).getFirstName()) && lastName.equals(CourseList.get(0).RStudent.get(i).getLastName()) && netID.equals(CourseList.get(0).RStudent.get(i).getNetID())) {
                        		indic = i;
                        		break;
                        	}
                    	}
                    }
                    if (indic == -1) {
                    	System.out.println("You are not in the system, or your information is wrong.");
                    	break;
                    }
                    Student student = CourseList.get(0).RStudent.get(indic);
                    student.setCourseList(CourseList);
                    String username;
                    String password;
                    //set username and password for first-time user - Student
                    if (student.getUsername() == null && student.getPassword() == null) {
                    	System.out.println("You have not set your username and password!");
                    	System.out.print("Username: ");
                    	student.setUsername(input.nextLine());
                    	System.out.print("Password: ");
                    	student.setPassword(input.nextLine());
                    	System.out.println("Username and password are set!\n");
                    	for (int i = 0; i < student.getAllCourses().size(); i++) {
                    		student.getAllCourses().get(i).RStudent.set(indic, student);
                    	}
                    }
                    //check username and password
                    System.out.println("Please enter your username and password!");
                	System.out.print("Username: ");
                    username = input.nextLine();
                    System.out.print("Password: ");
                    password = input.nextLine();
                    String Ans="";
                    Ans = CheckStudent(username,password, student);
                    if (!Ans.equals("Student"))
                    {
                        System.out.println(Ans);
                        break;
                    }

                    while(1==1) {
                        DisplayMenuStudent();
                        System.out.print("Select: ");
                        String selection = input.nextLine();

                        if (selection.equals("1")) {
                            student.viewAllCourses();
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                        else if (selection.equals("2")) {
                            student.viewNotFullCourse();
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                        else if (selection.equals("3")) {
                            student.registerACourse(username,password);
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                        else if (selection.equals("4")) {
                            student.withdrawCourse(firstName,lastName);
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                        else if (selection.equals("5")) {
                            student.viewNowCourse(firstName,lastName);
                            System.out.println("Press Enter to Continue");
                            input.nextLine();
                        }
                        else if (selection.equals("6")) {
                            SaveData(student.getAllCourses());
                            indic = -1;
                            CourseList = student.getAllCourses();
                            break;
                        }
                    }
                    break;
                }
                case "3": {
                    System.exit(0);
                    break;
                }
            }
        }
    }

    //check the Student's username and password
    public static String CheckStudent(String username, String passowrd, Student stu)
    {
        String Ans="WRONG USERNAME or PASSWORD";
        if (username.equals(stu.getUsername()) && passowrd.equals(stu.getPassword()))
            Ans="Student";
        return Ans;
    }

    //check the Admin's username and password
    public static String CheckAdmin(String username, String passowrd)
    {
        String Ans="WRONG USERNAME or PASSWORD";
        if (username.equals("Admin") && passowrd.equals("Admin001"))
            Ans="Admin";
        return Ans;
    }

    //Method to save the updated course data by using the serialization
    public static void SaveData(ArrayList<Course> NewCourseData)
    {
        try{
            FileOutputStream fos= new FileOutputStream("CourseSerialCache.csv");
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(NewCourseData);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }catch(Exception e){
            System.out.print(e.toString());
        }
    }

    public static void cls(){   //clear screen
        for(int i=0;i<50;i++)
            System.out.println(" ");
    }

    //show the welcome menu to choose the user mode.
    public static void DisplayWelcome()
    {
        cls();
        System.out.println("===  Course Registration System  ===");
        System.out.println("====================================");
        System.out.println("|                                  |");
        System.out.println("|                                  |");
        System.out.println("|              Welcome             |");
        System.out.println("|                                  |");
        System.out.println("|             1.Admin              |");
        System.out.println("|             2.Student            |");
        System.out.println("|             3.Exit               |");
        System.out.println("|                                  |");
        System.out.println("====================================");
    }

    //show the menu for administrator user
    public static void DisplayMenuAdmin()
    {
        System.out.println("===  Course Registration System  ===");
        System.out.println("===============Admin================");
        System.out.println("==========Course Management=========");
        System.out.println("| 1. Create New Course             |");
        System.out.println("| 2. Delete Course                 |");
        System.out.println("| 3. Edit Course                   |");
        System.out.println("| 4. Display Info (by Course ID)   |");
        System.out.println("| 5. Register Student              |");
        System.out.println("| 6. Exit                          |");
        System.out.println("==============Reports===============");
        System.out.println("| 11.View All Courses              |");
        System.out.println("| 12.View All Full Courses         |");
        System.out.println("| 13.Write Full Courses to File    |");
        System.out.println("| 14.View Course Registered Student|");
        System.out.println("| 15.View Registered by StudentName|");
        System.out.println("| 16.Sort by Current Registered    |");
        System.out.println("====================================");
    }

    //show the menu for student user
    public static void DisplayMenuStudent()
    {
        System.out.println("===  Course Registration System  ===");
        System.out.println("==============Student===============");
        System.out.println("| 1. View All Courses              |");
        System.out.println("| 2. View Empty Courses            |");
        System.out.println("| 3. Register Course               |");
        System.out.println("| 4. Withdraw Course               |");
        System.out.println("| 5. View My Courses               |");
        System.out.println("| 6. Exit                          |");
        System.out.println("====================================");
    }


}
