package system;

public class Professor extends Person
{
    private String degree;

    public Professor(String name, int id, String address, int age, String degree) {
        super(name, id, address, age);
        this.degree = degree;
    }
    public void becomeOlder() {
        System.out.println("I'm a professor getting older...");
    }
    public void registerGrades() {
        System.out.println("I'm registering grades now...");
    }
}

