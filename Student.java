public class Student implements FileStorable {
    private String name;
    private int age;
    private int studentid;
    private String email;
    
    //constructor
 public  Student(){
    
  }
  public int getid(){
    return studentid;

  }
  public String getName(){
    return name;
  }
  public int getAge(){
    return age;

  }
  public String getEmail(){
    return email;
  
  }
@Override
public String toFileString() {
    return name + "," + age + "," + studentid + "," + email;
}
  @Override
public String toString(){
    return "Student Name :"+name+" Age :"+age+" Student ID :"+studentid+" Email :"+email;
  }
  
    public void addStudent(String name, int age, int studentid, String email){
          this.name = name;
          this.age=age;
          this.studentid=studentid;
          this.email=email;
    }

}