import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate; 
import java.util.Scanner;

class Order implements Serializable{
    private int ID;
    private Customer buyer; 
    private LocalDate Date;
    private float totalPrice = 0;
    private ShoppingCart cart = new ShoppingCart();
    private static int numberOfOrders = 0;
    private static ArrayList<Order> orderHistory = new ArrayList<Order>();

    public Order(){
    }

    public void startOperation(Customer buyer) throws Exception{
        Scanner scanner = new Scanner(System.in);
        do{
           UIController.orderUI();
           int menuChoice = scanner.nextInt();
           switch(menuChoice){
                case 1: cart = addProduct(); break;
                case 2: cart.display(); System.out.println("\nTotal Price: " + String.format("%.2f", totalPrice) + "R$"); break;
                case 3: finishOrder(buyer); scanner.close(); return;
                case 4: displayHistory(); break; //For testing
                default: System.out.println("Invalid option."); break;
           }
        }while(true);
    }

    private void finishOrder(Customer buyer){
        if(!cart.isEmpty()){
            this.ID = numberOfOrders++;
            this.buyer = buyer;
            this.Date = LocalDate.now();
            orderHistory.add(this);
        }
    }
    private ShoppingCart addProduct(){
        Scanner scanner = new Scanner(System.in);
        do{
            Product.displayAll();
            System.out.println("Insert the product's ID (type -1 to stop): ");
            int chosenProductID = scanner.nextInt();
            if(chosenProductID == -1) break;
            Product product = Product.get(chosenProductID);
            if(product != null){
                System.out.println("Insert the amount of units: ");
                int chosenQuantity = scanner.nextInt();
                if(cart.addProductIfPossible(product, chosenQuantity))
                    totalPrice += chosenQuantity*product.getPrice();
            }else System.out.println("This ID doesn't exist.");
        }while(true);
        scanner.close();
        return cart;
    }

    public static void displayMostExpensive(){
        if(orderHistory.isEmpty()){
            System.out.println("Order history is empty."); return;
        }
        Order biggestOrder = orderHistory.get(0);
        for(Order order : orderHistory)
            if(order.totalPrice > biggestOrder.totalPrice) biggestOrder = order;
        System.out.println("Most expensive order:");
        biggestOrder.display();
        System.out.print("Buyer: ");
        biggestOrder.buyer.display();
    }
    public void display(){
        System.out.println("Date: " + Date);
        cart.display();
        System.out.println("Total Price: " + String.format("%.2f", totalPrice) + "R$");
    }
    public static void displayHistory(){ //For testing
        System.out.println("ORDER HISTORY: ");
        for(Order order : orderHistory){
            System.out.println("<Order " + order.ID + ">");
            order.display();
            System.out.print("Buyer: ");
            order.buyer.display();
            System.out.println();
        }
    }

    public static void save() throws Exception{
        for(Order order : orderHistory)
            Archive.write(order);
    }
    public static void load(Object register) throws Exception{
        orderHistory.add(((Order)register));
        numberOfOrders++;
    }
}