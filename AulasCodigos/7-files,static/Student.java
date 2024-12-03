import java.io.Serializable;

public class Student implements Serializable
{
    private int id;
    private String name;
    private int age;
    private static int numberOfStudents = 0;

    public Student(String name, int age) {
        this.id = numberOfStudents++;
        this.name = name;
        this.age = age;
    }

    public void display() {
        System.out.println(name + " - " + age);
    }
}
