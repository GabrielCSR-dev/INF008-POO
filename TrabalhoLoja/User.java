import java.io.Serializable;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.util.Arrays;

abstract class User implements Serializable{
    private int ID;
    private static int numberOfUsers = 0;
    private String name;
    private String email = null;
    private byte[] salt = new byte[16];
    private byte[] password;

    public abstract void enterMenu() throws Exception;
    public User(String name, String email, String password) throws Exception {
        if(DataController.userMap.putIfAbsent(email, this) == null){
            this.ID = numberOfUsers++;
            this.name = name;
            this.email = email;
            SecureRandom random = new SecureRandom();
            random.nextBytes(this.salt);
            this.password = passwordHashing(salt, password);
        }else System.out.println("This email has already been registered!");
    }

    public final static void login() throws Exception {
            String[] login = UIController.loginUI();
            User loggedUser = DataController.userMap.get(login[0]);
            loginValidation(login[1], loggedUser);
    }
    private static void loginValidation(String passwordTest, User userTest) throws Exception {
        if(userTest != null && (Arrays.equals(passwordHashing(userTest.salt, passwordTest), userTest.password))){
            userTest.enterMenu();
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

    public void display(){
        System.out.print("ID " + ID + ") Name: " + name + " | Email: " + email);
    }
    public static void load(Object register) throws Exception{
        DataController.userMap.putIfAbsent(((User)register).email, ((User)register));
        numberOfUsers++;
    }
}