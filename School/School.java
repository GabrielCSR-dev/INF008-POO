// Os valores atuais dos atributos definem o ESTADO do objeto
// O conjunto de métodos públicos definem a API pública da classe
package ads.iiisemestre.inf009;

public class School{
    private int qtdStudent;
    private Student[] group = new Student[3];
    private String name;

    public School(String name){
        this.name = name;
        qtdStudent = 0;
    }

    public void enroll(Student s1){
        if(qtdStudent < 3){
            group[qtdStudent] = s1;
            qtdStudent++; 
        } else{
            System.out.println("Sorry, the class is full!");
        }
    }

    public void welcome(){
        System.out.println("Welcome to " + name + "!");
    }
}