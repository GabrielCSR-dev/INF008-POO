import java.util.Scanner;

class Administrator extends User{

    public Administrator(String name, String email, String password) throws Exception{
        super(name, email, password);
    }
    public void enter() throws Exception{
        Scanner scanner = new Scanner(System.in);
        while(true){
            super.displayName();
            System.out.println(", welcome!");
            UIController.adminHomeUI();
            int menuChoice = scanner.nextInt();
            switch(menuChoice){
                case 1: createNewProduct(); break;
                case 2: createNewUser(); break;
                case 3: Order.displayMostExpensive(); break;
                case 4: Product.displayLeastStocked(); break;
                case 5: return;
                default: System.out.println("Invalid option."); break;
           }
        }
    }
    private void createNewProduct(){
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String[] atributesName = {"name", "description", "price", "inventory amount", "category"};
        String[] atribute = new String[atributesName.length];
        for(int i = 0; i < atributesName.length; i++){
            System.out.println("Insert the product's " + atributesName[i] + ":");
            atribute[i] = scanner.nextLine();
        }
        new Product(atribute[0], atribute[1], Float.parseFloat(atribute[2]), Integer.parseInt(atribute[3]), atribute[4]);
        scanner.close();
    }
    private void createNewUser() throws Exception{
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String[] atributesName = {"name", "email", "password", "adress"};
        int type = selectType();
        String[] atribute = type == 2 ? new String[atributesName.length] : new String[atributesName.length-1];
        for(int i = 0; i < atribute.length; i++){
            System.out.println("Insert the user's " + atributesName[i] + ":");
            atribute[i] = scanner.nextLine();
        }
        if(type == 1) new Administrator(atribute[0], atribute[1], atribute[2]);
        else new Customer(atribute[0], atribute[1], atribute[2], atribute[3]);
        scanner.close();
    }
    private int selectType(){
        Scanner scanner = new Scanner(System.in);
        int type = 0;
        while(true){
            System.out.println("Select the user's type:\n1 - Administrator\n2 - Customer");
            type = scanner.nextInt();
            if(type != 1 && type != 2) System.out.println("Invalid option.");
            else break;
        }
        scanner.nextLine();
        scanner.close();
        return type;
    }
}