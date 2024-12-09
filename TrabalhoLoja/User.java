import java.util.HashMap;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.util.Arrays;

class User{
    private int ID;
    private static int numberOfUsers = 0;
    private String name;
    private String email = null;
    private static HashMap<String, User> userMap = new HashMap<String, User>();
    private byte[] salt = new byte[16];
    private byte[] password;

    private User(){
    }
    public User(String name, String email, String password) throws Exception {
        if(userMap.putIfAbsent(email, this) == null){
            this.ID = numberOfUsers++;
            this.name = name;
            this.email = email;
            SecureRandom random = new SecureRandom();
            random.nextBytes(this.salt);
            this.password = passwordHashing(salt, password);
        }else System.out.println("This email has already been registered!");
    }

    public static void login() throws Exception {
        while(true){
<<<<<<< HEAD
            String[] login = UIController.loginMenu();
=======
            String[2] login = UIController.loginMenu();
>>>>>>> 1cf2e54ca6c94db96986efc090fe7902accd5c83
            User loggedUser = userMap.get(login[0]);
            loginValidation(login[1], loggedUser);
        }
    }
<<<<<<< HEAD
    private static void loginValidation(String passwordTest, User userTest) throws Exception {
        if(userTest != null && (Arrays.equals(passwordHashing(userTest.salt, passwordTest), userTest.password))){
            if(userTest instanceof Customer)
                ((Customer)userTest).chooseOption();
            else if(userTest instanceof Administrator)
=======
    private void loginValidation(String passwordTest, User userTest){
        byte[] passwordTestHashed = passwordHashing(passwordTest);
        if(userTest != null && (passwordTestHashed == userTest.password)){
            if(userTest instanceof Customer)
                ((Customer)userTest).chooseOption();
            else(userTest instanceof Administrator{
>>>>>>> 1cf2e54ca6c94db96986efc090fe7902accd5c83
                ((Administrator)userTest).chooseOption();
        } else System.out.println("Error: Invalid user or password.");
    }
    private static byte[] passwordHashing(byte[] salt, String passwordToHash) throws Exception {
        KeySpec spec = new PBEKeySpec(passwordToHash.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hashedPassword = factory.generateSecret(spec).getEncoded();
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
            System.out.println(user.name + " - " + user.email + " - " + user.ID + " - " + user.password + " - " + user.salt);
        }
    }
}
