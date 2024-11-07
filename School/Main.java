import ads.iiisemestre.inf009.*;

public class Main{
    public static void main(String[] args) {
        School IFBA = new School("IFBA");
        Student[] students = new Student[4];
        int ID = 1;
        students[0] = new Student("JOAO", 21, 7.0f, 8.0f, 5.5f, 1);
        students[1] = new Student("Gabriel", 18, 6.5f, 10.0f, 8.0f, 2);
        students[2] = new Student();
        students[3] = new Student();
        students[0].study();
        students[1].sleep();
        IFBA.welcome();
        
        for(int i = 0; i < 4; i++){
            IFBA.enroll(students[i]);
        }
    }
}