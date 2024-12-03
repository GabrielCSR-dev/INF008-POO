// Os valores atuais dos atributos definem o ESTADO do objeto
// O conjunto de métodos públicos define a API público da classe

class Student {
   public String name;
   public int age;
   public float[] grades = new float[3];

   public Student() {
   }

   public Student(String name, int age, float grade1, float grade2, float grade3) {
      System.out.println("Hello world, I've just been born");
      this.name = name;
      this.age = age;
      this.grades[0] = grade1;
      this.grades[1] = grade2;
      this.grades[2] = grade3;
   }

   public void study() {
      System.out.println(name + " is studying now...");
   }
   public void sleep() {
      System.out.println(name + " is too tired, sleeping now...");
   }
}

class School {
    public static void main(String args[]) {
        Student student0 = new Student();
        Student student1 = new Student("JOAO", 21, 7.0f, 8.0f, 5.5f);
        student1.study();
    }
}
