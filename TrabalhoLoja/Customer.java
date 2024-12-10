import java.util.ArrayList;
import java.util.Scanner;

class Customer extends User{
    private String deliveryAdress;
    private ArrayList<Order> orderHistory = new ArrayList<Order>(); 

    public Customer(String name, String email, String password, String deliveryAdress) throws Exception{
        super(name, email, password);
        if(super.isEmailValid())
            this.deliveryAdress = deliveryAdress;
    }

    public void chooseOption() throws Exception{
        Scanner scanner = new Scanner(System.in);
        while(true){
            super.displayName();
            System.out.println(", welcome!");
            UIController.customerMenu();
            int menuChoice = scanner.nextInt();
            switch(menuChoice){
                case 1: makeOrder(); break;
                case 2: scanner.close(); return;
                case 3: displayHistory(); break; //For testing
                default: System.out.println("Invalid option."); break;
           }
        }
    }
    private void makeOrder() throws Exception{
        Order newOrder = new Order();
        newOrder.startOperation(this);
        orderHistory.add(newOrder);
    }
    public void display(){
        super.display();
        System.out.println(" | Adress: " + deliveryAdress);
    }
    public void displayHistory(){ //For testing
        System.out.println("USER'S ORDER HISTORY: ");
        for(Order order : orderHistory){
            System.out.println(orderHistory.indexOf(order) + ")");
            order.display();
        }
    }
}