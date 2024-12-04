import java.util.ArrayList;

class Customer extends User{
    private String deliveryAdress;
    private ArrayList<Order> orderHistory = new ArrayList<Order>();

    public Customer(String name, String email, String password, String deliveryAdress) throws Exception{
        super(name, email, password);
        if(super.isEmailValid())
            this.deliveryAdress = deliveryAdress;
    }

    

}