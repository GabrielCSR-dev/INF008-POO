import java.util.Scanner;

class UIController{
<<<<<<< HEAD
    private static Scanner scanner = new Scanner(System.in);
    public static String[] loginMenu(){
        String[] login = new String[2];
=======
    private Scanner scanner = new Scanner(System.in);
    public static String[] loginMenu(){
        String[2] login = new String[2]();
>>>>>>> 1cf2e54ca6c94db96986efc090fe7902accd5c83
        System.out.println("[Login Menu]\nInsert your e-mail:");
        login[0] = scanner.nextLine();
        System.out.println("Insert your password:");
        login[1] = scanner.nextLine();
        return login;
    }
<<<<<<< HEAD
    public static void adminMenu(){
        System.out.println("[Administrator Menu]");
        System.out.println("Please, select an option:");
        System.out.println("1 - Create new product\n2 - Create new user\n3 - Report most expensive order\n4 - Report product with lowest inventory\n5 - Exit");
        System.out.println();
    }
=======
>>>>>>> 1cf2e54ca6c94db96986efc090fe7902accd5c83
    public static void customerMenu(){
        System.out.println("[Customer Menu]");
        System.out.println("Please, select an option:");
        System.out.println("1 - Start new order\n2 - Exit\n");
        System.out.println();
    }
    public static void orderMenu(){
        System.out.println("[Order Menu]");
        System.out.println("1 - Add product\n2 - View shopping cart\n3 - Finish order");
        System.out.println();
    }
}
