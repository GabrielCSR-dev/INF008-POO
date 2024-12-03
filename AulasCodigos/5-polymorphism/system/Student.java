package system;

public class Student extends Person
{
    private String program;

    public Student(String name, int id, String address, int age, String program) {
        // chame o construtor de Person que recebe name, id, address e age
        super(name, id, address, age);
        this.program = program;
    }

    @Override
    public void sleep(int hour) {
        super.sleep(hour);
        System.out.println("I'm a student, I'm sleeping now...");
    }
}
