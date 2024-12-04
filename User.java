import java.util.HashMap;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

class User{
    private int ID;
    private static int numberOfUsers = 0;
    private String name;
    private String email = null;
    private static HashMap<String, User> userMap = new HashMap<String, User>();
    private byte[] password;

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

    public void display(){
        System.out.println(name + " - " + email + " - " + ID);
    }

    public static void displayAll(){
        for(String indexEmail : userMap.keySet()){
            User user = userMap.get(indexEmail);
            System.out.println(user.name + " - " + user.email + " - " + user.ID);
        }
    }
}