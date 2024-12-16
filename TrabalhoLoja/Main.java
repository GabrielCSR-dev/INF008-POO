public class Main{
    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {
        if(!SerializationController.load()){
            Administrator admin = new Administrator("admin", "admin", "admin");
        }
        User.login();
        SerializationController.save();
        System.exit(0);
    }
}