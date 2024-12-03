package main;

import system.Person;
import system.Student;
import system.Professor;

public class Main
{
    public static void main(String[] args)
    {
	    Person p = new Person("JOAO", 123, "Rua tal", 20);
        p.becomeOlder();

        Student joao = new Student("JOAO", 123, "Rua tal", 20, "ADS");
        joao.becomeOlder();

//        System.out.println(joao.name);
        Professor ze = new Professor("Zé", 123, "Rua tal", 50, "CC");
        ze.becomeOlder();
    }
}
