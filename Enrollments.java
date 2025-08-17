public class Enrollments implements FileStorable{
    private int studentId;
    private int courseId;
    private String date;

    public Enrollments() {
    }

    public int getstudentId() {
        return studentId;
    }

    public int getcourseId() {
        return courseId;
    }

    public String getDate() {
        return date;
    }

    @override
    public String toString() {
        return "Enrollments [studentId =" + studentId + ", courseId =" + courseId + ", date of enrollment =" + date
                + "]";
    }
    @Override
    public String toFileString() {
        return studentId + "," + courseId + "," + date;
    }
    public void addEnrollment(int studentId, int courseId, String date) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.date = date;
    }
}
