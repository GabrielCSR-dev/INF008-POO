package ads.iiisemestre.inf009;

public class Student{
    private String name;
    private int age;
    private int ID;
    private float[] grades = new float[3];

    public Student(){
    }
    public Student(String name, int age, float grade1, float grade2, float grade3, int ID){
        System.out.println("Hello world, I've just been born");
        this.name = name;
        this.age = age;
        this.grades[0] = grade1;
        this.grades[1] = grade2;
        this.grades[2] = grade3;
        this.ID = ID;
    }

    public void study(){
        System.out.println(name + " is studying now...");
    }
    public void sleep(){
        System.out.println(name + " is too tired, sleeping now...");    
    }
}