package system;

public class Professor extends Person
{
    private String degree;

    public Professor(String name, int id, String address, int age, String degree) {
        super(name, id, address, age);
        this.degree = degree;
    }
    public void publishGrades() {
    }
}

