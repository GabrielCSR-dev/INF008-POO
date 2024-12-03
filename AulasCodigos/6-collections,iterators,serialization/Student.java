import java.lang.Comparable;
import java.io.Serializable;

public class Student implements Comparable<Student>, Serializable
{
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void display() {
        System.out.println(name + " - " + age);
    }

    public boolean equals(Student student) {
        return name == student.name && age == student.age;
    }

    @Override
    public int compareTo(Student student) {
        return this == student ? 0 : -1;
    }
}
