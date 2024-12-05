import java.util.ArrayList;
import java.time.LocalDate; 
import java.util.Scanner;

class Order{
    private int ID;
    private Customer buyer; 
    private LocalDate Date;
    // private int totalPrice;
    private ShoppingCart cart = new ShoppingCart();
    private static int numberOfOrders = 0;
    private static ArrayList<Order> orderHistory = new ArrayList<Order>();
    private Scanner scanner = new Scanner(System.in);

    public Order(Customer buyer){
        this.ID = numberOfOrders++;
        this.buyer = buyer;
        this.Date = LocalDate.now();
    }

    public void startOperation() throws Exception{
        do{
           UIController.orderMenu();
           int menuChoice = scanner.nextInt();
           switch(menuChoice){
                case 1: cart = addProduct(); break;
                case 2: cart.display(); break;
                case 3: orderHistory.add(this); return;
                default: System.out.println("Invalid option."); break;
           }
        }while(true);
    }

    private ShoppingCart addProduct(){
        do{
            Product.displayAll();
            System.out.println("Insert the product's ID (type -1 to stop): ");
            int chosenProductID = scanner.nextInt();
            if(chosenProductID == -1) break;
            Product product = Product.get(chosenProductID);
            if(product != null){
                System.out.println("Insert the amount of units: ");
                int chosenQuantity = scanner.nextInt();
                cart.addProductIfPossible(product, chosenQuantity);
            }else System.out.println("This ID doesn't exist.");
        }while(true);
        return cart;
    }

    public void display(){
        System.out.println("Date: " + Date);
        cart.display();
    }
    public static void displayHistory(){ //For testing
        System.out.println("ORDER HISTORY: ");
        for(Order order : orderHistory){
            System.out.println("ID " + order.ID + ")");
            order.display();
            System.out.print("Buyer: ");
            order.buyer.display();
        }
    }
}