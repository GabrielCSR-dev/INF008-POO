import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate; 

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
        do{
           int menuChoice = UIController.orderUI();
           switch(menuChoice){
                case 1: cart = addProduct(); break;
                case 2: cart.display(); System.out.println("\nTotal Price: " + String.format("%.2f", totalPrice) + "R$"); break;
                case 3: finishOrder(buyer); return;
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
            buyer.saveOrder(this);
        }
    }
    private ShoppingCart addProduct(){
        do{
            int chosenProductID = UIController.orderProductSelectionUI();
            if(chosenProductID == -1) break;
            Product product = Product.get(chosenProductID);
            if(product != null){
                int chosenQuantity = UIController.orderQuantitySelectionUI();
                if(cart.addProductIfPossible(product, chosenQuantity))
                    totalPrice += chosenQuantity*product.getPrice();
            }else System.out.println("This ID doesn't exist.");
        }while(true);
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
            SerializationController.write(order);
    }
    public static void load(Object register) throws Exception{
        orderHistory.add(((Order)register));
        numberOfOrders++;
    }
}