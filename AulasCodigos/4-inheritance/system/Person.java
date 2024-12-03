package system;

public class Person
{
    private String name;
    private int id;
    private String address;
    private int age;

    public Person() {
        System.out.println("Person's constructor");
    }

    public Person(String name, int id, String address, int age) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.age = age;
    }

    public void becomeOlder() {
        age++;
        System.out.println("I'm getting older, I'm now " + age);
    }
}

