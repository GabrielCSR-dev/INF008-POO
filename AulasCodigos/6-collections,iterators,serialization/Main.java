import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.HashMap;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main
{
    public static void main(String[] args) throws Exception {
        ArrayList<Student> students = new ArrayList<Student>();
        Student student = new Student("JOAO", 20);
        students.add(student);
        students.add(new Student("JOAO", 20));
        students.add(new Student("JOAO", 20));
        students.add(new Student("JOAO", 20));
        students.add(new Student("JOAO", 20));
        System.out.println("ArrayList size: " + students.size());
        System.out.println(students.contains(student));
        System.out.println(students.get(0).equals(students.get(1)));
        // Varredura convencional via for
        for(int i = 0; i < students.size(); i++) {
            students.get(i).display();
        }
        System.out.println("");
        // Varredura com iterators
        Iterator<Student> i = students.iterator();
        while(i.hasNext())
            i.next().display();
        System.out.println("");
        // Range-based for
        for(Student myStudent : students)
            myStudent.display();

        Professor professor1 = new Professor("ZÉ");
        Professor professor2 = new Professor("TIÃO");

        //HashMap
        HashMap<Student, Professor> studentsAdvisors = new HashMap<Student, Professor>();
        studentsAdvisors.put(students.get(0), professor1);
        studentsAdvisors.put(students.get(1), professor2);
        studentsAdvisors.put(students.get(2), professor1);
        System.out.println("TreeMap size: " + studentsAdvisors.size());
        studentsAdvisors.get(students.get(1)).display();

        // FileOutputStream fos = new FileOutputStream("students.dat");
        // ObjectOutputStream oos = new ObjectOutputStream(fos);
        // oos.writeObject(students);
        // fos.close();

        FileInputStream fis = new FileInputStream("students.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Student> myStudents = (ArrayList<Student>) ois.readObject();
        fis.close();
        System.out.println(myStudents.size());

    }
}
