import java.io.Serializable;
import java.time.LocalDate; 

class Order implements Serializable{
    private int ID;
    private Customer buyer; 
    private LocalDate Date;
    private float totalPrice = 0;
    private ShoppingCart cart = new ShoppingCart();
    private static int numberOfOrders = 0;

    public Order(){
    }

    public void startOperation(Customer buyer) throws Exception{
        do{
           int menuChoice = UIController.orderUI();
           switch(menuChoice){
                case 1: cart = addProduct(); break;
                case 2: cart.display(); System.out.println("\nTotal Price: " + String.format("%.2f", totalPrice) + "R$"); break;
                case 3: finishOrder(buyer); return;
                default: System.out.println("Invalid option."); break;
           }
        }while(true);
    }

    private void finishOrder(Customer buyer){
        if(!cart.isEmpty()){
            ID = numberOfOrders++;
            this.buyer = buyer;
            Date = LocalDate.now();
            cart.takeStock();
            DataController.orderHistory.add(this);
            buyer.saveOrder(this);
            if(DataController.mostExpensiveOrder != null && totalPrice > DataController.mostExpensiveOrder.totalPrice)
                DataController.mostExpensiveOrder = this;
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
        if(DataController.orderHistory.isEmpty()){
            System.out.println("Order history is empty."); return;
        } else if(DataController.mostExpensiveOrder == null){
            DataController.mostExpensiveOrder = DataController.orderHistory.get(0);
            for(Order order : DataController.orderHistory)
                if(order.totalPrice > DataController.mostExpensiveOrder.totalPrice)
                    DataController.mostExpensiveOrder = order;
        }
        System.out.println("Most expensive order:");
        DataController.mostExpensiveOrder.display();
        System.out.print("Buyer: ");
        DataController.mostExpensiveOrder.buyer.display();
    }
    public void display(){
        System.out.println("<ID " + ID + ">\nDate: " + Date);
        cart.display();
        System.out.println("Total Price: " + String.format("%.2f", totalPrice) + "R$");
    }

    public static void load(Object register) throws Exception{
        DataController.orderHistory.add(((Order)register));
        numberOfOrders++;
    }
}