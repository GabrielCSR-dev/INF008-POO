import java.util.Scanner;

class UIController{
    private static Scanner scanner = new Scanner(System.in);
    
    public static String[] loginUI() throws Exception{
        scanner = new Scanner(System.in);
        String[] login = new String[2];
        System.out.println("[Login Menu]\nInsert your e-mail:");
        login[0] = scanner.nextLine();
        System.out.println("Insert your password:");
        login[1] = scanner.nextLine();
        return login;
    }
    public static int adminHomeUI(){
        System.out.println("[Administrator Menu]");
        System.out.println("Please, select an option:");
        System.out.println("1 - Create new product\n2 - Create new user\n3 - Report most expensive order\n4 - Report product with lowest inventory\n5 - Exit\n");
        return scanner.nextInt();
    }
    public static String[] adminProductCreationUI(){
        scanner.nextLine();
        String[] atributesName = {"name", "description", "price", "inventory amount", "category"};
        String[] atribute = new String[atributesName.length];
        for(int i = 0; i < atributesName.length; i++){
            System.out.println("Insert the product's " + atributesName[i] + ":");
            atribute[i] = scanner.nextLine();
        }
        return atribute;
    }
    public static String[] adminUserCreationUI(int type){
        scanner.nextLine();
        String[] atributesName = {"name", "email", "password", "adress"};
        String[] atribute = type == 2 ? new String[atributesName.length] : new String[atributesName.length-1];
        for(int i = 0; i < atribute.length; i++){
            System.out.println("Insert the user's " + atributesName[i] + ":");
            atribute[i] = scanner.nextLine();
        }
        return atribute;
    }
    public static int adminSelectTypeUI(){
        int type = 0;
        while(true){
            System.out.println("Select the user's type:\n1 - Administrator\n2 - Customer");
            type = scanner.nextInt();
            if(type != 1 && type != 2) System.out.println("Invalid option.");
            else break;
        }
        return type;
    }
    public static int customerHomeUI(){
        // User.displayName();
        // System.out.println(", welcome!");
        System.out.println("[Customer Menu]");
        System.out.println("Please, select an option:");
        System.out.println("1 - Start new order\n2 - Exit\n");
        return scanner.nextInt();
    }
    public static int orderUI(){
        System.out.println("[Order Menu]");
        System.out.println("1 - Add product\n2 - View shopping cart\n3 - Finish order\n");
        return scanner.nextInt(); 
    }
    public static int orderProductSelectionUI(){
        Product.displayAll();
        System.out.println("Insert the product's ID (type -1 to stop): ");
        return scanner.nextInt();
    }
    public static int orderQuantitySelectionUI(){
        System.out.println("Insert the amount of units: ");
        return scanner.nextInt();
    }
}