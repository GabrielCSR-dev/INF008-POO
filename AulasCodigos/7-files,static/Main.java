import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main
{
    public static void main(String[] args) throws Exception {
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("JOAO", 20));
        students.add(new Student("JOSÉ", 21));
        students.add(new Student("MARIA", 22));
        students.add(new Student("EDUARDO", 23));
        students.add(new Student("LUZIA", 24));

        FileOutputStream fos = new FileOutputStream("students.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(students);
        fos.close();

        // FileInputStream fis = new FileInputStream("students.dat");
        // ObjectInputStream ois = new ObjectInputStream(fis);
        // ArrayList<Student> students = (ArrayList<Student>) ois.readObject();
        // fis.close();

        for(Student student : students)
            student.display();

        System.out.println("Area of circle with radius = 3: " + Math.circleArea(3.0));
    }
}
