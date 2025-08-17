public class Course implements FileStorable {
    private String courseName;
    private int courseId;
    private String Instructor;
    public Course() {

    }
    public String getCourseName() {
        return courseName;
    }
    public int getCourseId(){
        return courseId;

    }
    public String getInstructor(){
        return Instructor;
    }
    @Override
    public String toString(){
        return "Course Name: " + courseName + ", Course ID: " + courseId + ", Instructor: " + Instructor;
    }
    @Override
    public String toFileString() {
    return courseName + "," + courseId + "," + Instructor ;
}
    public void addCourse(String courseName, int courseId, String Instructor) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.Instructor = Instructor;
    }
}
