
DatabaseConnection -> MySqlDatabaseConnection

Authentication -> FacebookAuthentication
                  GoogleAuthentication


package main;

import system.Person;
import system.Student;
import system.Professor;

public class Main
{
    public static void execBirthday(Person person)
    {
        person.becomeOlder(); // LIGACAO DINAMICA - late binding
        // ...
        // ...
        // ...
        // ...
        // ...
        // ...
        // ...
        // ...
    }

    public static void main(String[] args)
    {
	    Person p = new Person("JOAO", 123, "Rua tal", 20);
        p.becomeOlder();

        Student joao = new Student("JOAO", 123, "Rua tal", 20, "ADS");
        joao.sleep(8);

//        System.out.println(joao.name);
        Person ze = new Professor("Zé", 123, "Rua tal", 50, "CC");
        execBirthday(ze);
        execBirthday(joao);
        if (ze instanceof Professor) {
            Professor zeProf = (Professor)ze;
            ((Professor)ze).registerGrades();
            zeProf.registerGrades();
        }
    }
}
