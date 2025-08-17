import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //lists to store objects

    ArrayList<Student> student = new ArrayList<>();
    ArrayList<Course> course = new ArrayList<>();
    ArrayList<Enrollments> enrollments = new ArrayList<>();
    // flag to control the loop
    static boolean alive = true;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        while (alive) {
            //menu driven for choices 

            System.out.println("          Welcome to the Student Management System");
            System.out.println("............................................................");
            System.out.println("............................................................");
            System.out.println("please enter the choice\n");
            System.out.println("............................\n\n");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll a Student in a Course");
            System.out.println("4. View All Students");
            System.out.println("5. View All Courses");
            System.out.println("6. View All Enrollments");
            System.out.println("7. Exit\n\n");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:

                    //adding a student
                    System.out.println("Enter Student Name:");
                    String name = sc.nextLine();
                    System.out.println("Enter Student Age:");
                    int age = sc.nextInt();
                    System.out.println("Enter Student ID:");
                    int studentid = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Student Email:");
                    String email = sc.nextLine();

                    if (main.addStu(name, age, studentid, email))
                        System.out.println("Student added successfully!\n");
                    System.out.println("press 0 to exit..");
                    int exitChoice = sc.nextInt();
                    if (exitChoice == 0) {
                        alive = false;
                    }
                    break;

                case 2:

                    //adding a course
                    System.out.println("Enter Course Name:");
                    String courseName = sc.nextLine();
                    System.out.println("Enter Course ID:");
                    int courseId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Instructor Name:");
                    String instructor = sc.nextLine();
                   if( main.addCourse(courseName, courseId, instructor))
                    System.out.println("Course added successfully!\n");
                    System.out.println("press 0 to exit..");
                    exitChoice = sc.nextInt();
                    if (exitChoice == 0) {
                        alive = false;
                    }
                    break;

                case 3:
                    // adding a enrollment
                    System.out.println("Enter Student ID to enroll:");
                    int enrollStudentId = sc.nextInt();
                    System.out.println("Enter Course ID to enroll in:");
                    int enrollCourseId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Enrollment Date (YYYY-MM-DD):");
                    String date = sc.nextLine();
                    if (main.addenrollment(enrollStudentId, enrollCourseId, date))
                        System.out.println("Enrollment added successfully!\n");

                    System.out.println("press 0 to exit..");
                    exitChoice = sc.nextInt();
                    if (exitChoice == 0) {
                        alive = false;
                    }
                    break;
                case 4:
                    // displaying all students
                    System.out.println(".......List of Students added NOW......:\n");
                    for (Student s : main.student) {
                        System.out.println(s.toString());
                    }
                    System.out.println("\n");
                    System.out.println("........List of all the Students added till now.........:\n ");
                    loadstudentfromfile();
                    System.out.println("press 0 to exit..\n");
                    exitChoice = sc.nextInt();
                    if (exitChoice == 0) {
                        alive = false;
                    }
                    break;

                case 5:
                //display all courses
                 System.out.println(".......List of courses added NOW......:\n");
                    for (Course c : main.course) {
                        System.out.println(c.toString());
                    }
                    System.out.println("");
                     System.out.println("........List of all the courses added till now.........:\n ");
                    loadcoursefromfile();
                    System.out.println("press 0 to exit..");
                    exitChoice = sc.nextInt();
                    if (exitChoice == 0) {
                        alive = false;
                    }
                    break;

                case 6:
                // display all enrollments
                    if (main.enrollments.isEmpty()) {
                        System.out.println("No enrollments found.");
                        break;
                    }
                 System.out.println(".......List of enrollments added NOW......:\n");
                    for (Enrollments e : main.enrollments) {
                        System.out.println(e.toString());
                    }
                    System.out.println("");
                      System.out.println("........List of all the enrollments added till now.........:\n ");
                    loadenrollmentfromfile();
                    System.out.println("press 0 to exit..");
                    exitChoice = sc.nextInt();
                    if (exitChoice == 0) {
                        alive = false;
                    }
                    break;

                default:
                    // invalid choice

                    System.out.println("Invalid choice");
                    System.out.println("press 0 to start over again");
                    System.out.println("press 1 to exit");
                    exitChoice = sc.nextInt();
                    if (exitChoice == 0) {
                        alive = true;
                    } else {
                        alive = false;
                    }
            }
        }
        sc.close();
    }
    // methods to add student, course, and enrollment
    public boolean addStu(String name, int age, int studentid, String email) {
        Student newStudent = new Student();
        newStudent.addStudent(name, age, studentid, email);
        student.add(newStudent);
        return saveobjecttofile(newStudent, "students.txt");
    }

    public boolean addCourse(String courseName, int courseId, String Instructor) {
        Course newCourse = new Course();
        newCourse.addCourse(courseName, courseId, Instructor);
        course.add(newCourse);
        return saveobjecttofile(newCourse, "courses.txt");
    }

    public boolean addenrollment(int studentId, int courseId, String Date) {
        if (studentId <= 0 || courseId <= 0 || Date == null || Date.isEmpty()) {
            System.out.println("Invalid enrollment details provided.");
            return false;
        }
        if (student.stream().noneMatch(s -> s.getid() == studentId)) {
            System.out.println("Student with ID " + studentId + " does not exist.");
            return false;

        }
        if (course.stream().noneMatch(c -> c.getCourseId() == courseId)) {
            System.out.println("Course with ID " + courseId + " does not exist.");
            return false;
        }

        Enrollments newEnrollment = new Enrollments();
        newEnrollment.addEnrollment(studentId, courseId, Date);
        enrollments.add(newEnrollment);
        if (!saveobjecttofile(newEnrollment, "enrollments.txt")) {
            System.out.println("Failed to save enrollment data.");
            return false;
        }
        return true;
    }
  // generic function to save any object in file
    public boolean saveobjecttofile(FileStorable  obj, String Filename) {
        try {
            FileWriter writer = new FileWriter(Filename, true);
            writer.write(obj.toFileString()+ "\n");
            writer.close();
            System.out.println(obj.getClass()+" saved to file successfully!");
            return true;
        } catch (Exception e) {
            System.out.println("Error saving "+obj.getClass()+ "data: " + e.getMessage());
            return false;
        }

    }
    // methods to load data from files
    public static void loadstudentfromfile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    int id = Integer.parseInt(parts[2]);
                    String email = parts[3];
                    Student s= new Student();
                    s.addStudent(name, age, id, email);
                    System.out.println(s.toString());
                }


            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }

    }

    
    public static void loadcoursefromfile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("courses.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String coursename = parts[0];
                    int id = Integer.parseInt(parts[1]);
                    String instructor = parts[2];
                    Course c= new Course();
                    c.addCourse(coursename, id, instructor);
                    System.out.println(c.toString());
                }


            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading course data: " + e.getMessage());
        }

    }
 public static void loadenrollmentfromfile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("enrollments.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    int sid = Integer.parseInt(parts[0]);
                    int cid = Integer.parseInt(parts[1]);
                    String date = parts[2];
                    Enrollments e= new Enrollments();
                    e.addEnrollment(sid, cid, date);
                    System.out.println(e.toString());
                }


            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading enrollment data: " + e.getMessage());
        }

    }

}