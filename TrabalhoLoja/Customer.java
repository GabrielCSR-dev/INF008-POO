import java.util.ArrayList;

class Customer extends User{
    private String deliveryAdress;
    private ArrayList<Order> orderHistory = new ArrayList<Order>(); 

    public Customer(String name, String email, String password, String deliveryAdress) throws Exception{
        super(name, email, password);
        if(super.isEmailValid())
            this.deliveryAdress = deliveryAdress;
    }

    public void enterMenu() throws Exception{
        while(true){
            int menuChoice = UIController.customerHomeUI();
            switch(menuChoice){
                case 1: startOrder(); break;
                case 2: return;
                default: System.out.println("Invalid option."); break;
           }
        }
    }
    private void startOrder() throws Exception{
        Order newOrder = new Order();
        newOrder.startOperation(this);
    }
    public void saveOrder(Order newOrder){
        orderHistory.add(newOrder);
    }
    public void display(){
        super.display();
        System.out.println(" | Adress: " + deliveryAdress);
    }
}