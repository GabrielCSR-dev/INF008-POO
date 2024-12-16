import java.io.Serializable;
import java.util.HashMap;

class ShoppingCart implements Serializable{
    private HashMap<Product, Integer> shoppingCart = new HashMap<Product, Integer>();

    public ShoppingCart(){
    }

    public boolean addProductIfPossible(Product product, int quantity){
        if(shoppingCart.containsKey(product) && product.hasStock(shoppingCart.get(product)+quantity)){
                quantity += shoppingCart.get(product);
                shoppingCart.replace(product, quantity);
        } else if(!shoppingCart.containsKey(product) && product.hasStock(quantity)){
                shoppingCart.put(product, quantity);
        } else{
            System.out.println("Error: the desired quantity is above the product's stock.");
            return false;
        }
        return true;
    }
    public void takeStock(){ 
        for(Product product : shoppingCart.keySet())
            product.consume(shoppingCart.get(product));
    }
    public float getTotalPrice(){
        float totalPrice = 0;
        for(Product product : shoppingCart.keySet())
            totalPrice += product.getPrice() * shoppingCart.get(product);
        return totalPrice;
    }
    public boolean isEmpty(){
        return shoppingCart.isEmpty();
    }
    public void display(){ 
        for(Product product : shoppingCart.keySet()){
            product.display();
            System.out.println(" | Amount of units: " + shoppingCart.get(product));
        }
    }
}