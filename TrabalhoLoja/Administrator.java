class Administrator extends User{
    public Administrator(String name, String email, String password) throws Exception{
        super(name, email, password);
    }
    public void test(){
        System.out.println("I'm an administrator!");
    }
}