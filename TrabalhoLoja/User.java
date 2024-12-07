import java.util.HashMap;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class User{
    private int ID;
    private static int numberOfUsers = 0;
    private String name;
    private String email = null;
    private static HashMap<String, User> userMap = new HashMap<String, User>();
    private byte[] password;
    private Scanner scanner = new Scanner(System.in);

    private User(){
    }
    public User(String name, String email, String password) throws Exception {
        if(userMap.putIfAbsent(email, this) == null){
            this.ID = numberOfUsers++;
            this.name = name;
            this.email = email;
            this.password = passwordHashing(password);
        }else{
            System.out.println("This email has already been registered!");
        }
    }

    public static void login(){
        while(true){
            String[2] login = UIController.loginMenu();
            User loggedUser = userMap.get(login[0]);
            loginValidation(login[1], loggedUser);
        }
    }
    private void loginValidation(String passwordTest, User userTest){
        byte[] passwordTestHashed = passwordHashing(passwordTest);
        if(userTest != null && (passwordTestHashed == userTest.password)){
            if(userTest instanceof Customer)
                ((Customer)userTest).chooseOption();
            else(userTest instanceof Administrator{
                ((Administrator)userTest).chooseOption();
        } else System.out.println("Error: Invalid user or password.");
    }
    private byte[] passwordHashing(String passwordToHash) throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hashedPassword = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
        return hashedPassword;
    }

    public boolean isEmailValid(){
        return email != null;
    }

    public void displayName(){
        System.out.print(name);
    }
    public void display(){
        System.out.print("ID " + ID + ") Name: " + name + " | Email: " + email);
    }

    public static void displayAll(){ //Unecessary, delete later
        for(String indexEmail : userMap.keySet()){
            User user = userMap.get(indexEmail);
            System.out.println(user.name + " - " + user.email + " - " + user.ID);
        }
    }
}
