import java.io.Serializable;
import java.util.HashMap;

class ShoppingCart implements Serializable{
    private HashMap<Product, Integer> shoppingCart = new HashMap<Product, Integer>();

    public ShoppingCart(){
    }

    public boolean addProductIfPossible(Product product, int quantity){
        if(shoppingCart.containsKey(product) && product.consumeIfAvailable(quantity)){
                quantity += shoppingCart.get(product);
                shoppingCart.replace(product, quantity);
        } else if(product.consumeIfAvailable(quantity)){
                shoppingCart.put(product, quantity);
        } else{
            System.out.println("Error: the desired quantity is above the product's stock.");
            return false;
        }
        return true;
    }
    public boolean isEmpty(){
        return shoppingCart.isEmpty();
    }
    public void display(){ 
        for(Product product : shoppingCart.keySet()){
            product.display();
            System.out.println(" | Selected amount: " + shoppingCart.get(product));
        }
    }
}