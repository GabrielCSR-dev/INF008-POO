import java.util.Scanner;

class UIController{
    private Scanner scanner = new Scanner(System.in);
    public static String[] loginMenu(){
        String[2] login = new String[2]();
        System.out.println("[Login Menu]\nInsert your e-mail:");
        login[0] = scanner.nextLine();
        System.out.println("Insert your password:");
        login[1] = scanner.nextLine();
        return login;
    }
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
