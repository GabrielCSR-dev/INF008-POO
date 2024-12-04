import java.util.ArrayList;
import java.time.LocalDate; 
import java.util.Scanner;

class Order{
    private int ID;
    private Customer buyer; 
    private Localdate Date;
    // private int totalPrice;
    private ShoppingCart cart = new ShoppingCart();
    private static int numberOfOrders = 0;
    private static ArrayList<Order> orderHistory = ArrayList<Order>();
    private Scanner scanner = new Scanner(System.in);

    public Order(Customer buyer){
        this.ID = numberOfOrders++;
        this.buyer = buyer;
        this.Date = LocalDate.now();
    }

    public void startOperation() throws Exception{
        do{
           UIController.orderMenu();
           int menuChoice = scanner.nextint();
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
            System.out.println("Insert the product's ID (type -1 to stop): ");
            chosenProductID = scanner.nextint();
            if(chosenProductID == -1) break;
            Product product = Product.get(index);
            if(product != null){
                System.out.println("Insert the amount of units: ");
                int chosenQuantity = scanner.nextint();
                cart.addProductIfPossible(product, chosenQuantity);
            }else System.out.println("This ID doesn't exist.");
        }while(true);
        return cart;
    }
}