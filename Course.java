import java.io.Serializable;
import java.util.ArrayList;


public class Course implements Serializable, Comparable<Course> {

    private String CourseName;
    private String CourseId;
    private int Maximum;
    private int Current;
    private String ListName;
    private String CourseInst;
    private int CourseSelectionNum;
    private String CourseLocation;
    protected ArrayList<Student> Student = new ArrayList<>();
    protected ArrayList<Student> RStudent = new ArrayList<>();
    
    //constructor to add a course
    public Course(String CourseName, String CourseId, int Maximum, int  Current, String ListNames, String CourseInst, int CourseSectionNum , String CourseLocation, ArrayList<Student> RStudent)
    {
        this.CourseName=CourseName;
        this.CourseId=CourseId;
        this.Maximum=Maximum;
        this.Current=Current;
        this.ListName=ListNames;
        this.CourseInst=CourseInst;
        this.CourseSelectionNum=CourseSectionNum;
        this.CourseLocation=CourseLocation;
        this.RStudent = RStudent;
    }
    
    //constructor to read CSV for the first time
    public Course(String CourseName, String CourseId, int Maximum, int  Current, String ListNames, String CourseInst, int CourseSectionNum , String CourseLocation)
    {
        this.CourseName=CourseName;
        this.CourseId=CourseId;
        this.Maximum=Maximum;
        this.Current=Current;
        this.ListName=ListNames;
        this.CourseInst=CourseInst;
        this.CourseSelectionNum=CourseSectionNum;
        this.CourseLocation=CourseLocation;
    }
    
    //method to add student
    public void addStudent(Student Stu, Course Cou){
        ++Cou.Current;
        Cou.Student.add(Stu);
    }
    
    //method to delete student
    public void delStudent(Student Stu, Course Cou){
        --Cou.Current;
        Cou.Student.remove(Stu);
    }
    
    // getters and setters
    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(String courseId) {
        CourseId = courseId;
    }

    public int getMaximum() {
        return Maximum;
    }

    public void setMaximum(int maximum) {
        Maximum = maximum;
    }

    public int getCurrent() {
        return Current;
    }

    public void setCurrent(int current) {
        Current = current;
    }

    public String getListName() {
        return ListName;
    }

    public void setListName(String listName) {
        ListName = listName;
    }

    public String getCourseInst() {
        return CourseInst;
    }

    public void setCourseInst(String courseInst) {
        CourseInst = courseInst;
    }

    public int getCourseSelectionNum() {
        return CourseSelectionNum;
    }

    public void setCourseSelectionNum(int courseSelectionNum) {
        CourseSelectionNum = courseSelectionNum;
    }

    public String getCourseLocation() {
        return CourseLocation;
    }

    public void setCourseLocation(String courseLocation) {
        CourseLocation = courseLocation;
    }

    public ArrayList<Student> getStudent() {
        return Student;
    }

    public void setStudent(ArrayList<Student> student) {
        Student = student;
    }

    @Override
    public int compareTo(Course c) {
        return 0;
    }
}