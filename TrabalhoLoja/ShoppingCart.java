import java.util.HashMap;

class ShoppingCart{
    private HashMap<Product, Integer> shoppingCart = new HashMap<Product, Integer>();
    private float totalPrice = 0;

    public ShoppingCart(){
    }

    public void addProductIfPossible(Product product, int quantity){
        if(shoppingCart.containsKey(product) && product.consumeIfAvailable(quantity)){
                totalPrice += quantity*product.getPrice();
                quantity += shoppingCart.get(product);
                shoppingCart.replace(product, quantity);
        } else if(product.consumeIfAvailable(quantity)){
                totalPrice += quantity*product.getPrice();
                shoppingCart.put(product, quantity);
        } else System.out.println("Error: the desired quantity is above the product's stock.");
    }

    public void display(){ 
        for(Product product : shoppingCart.keySet()){
            product.display();
            System.out.println(" | Amount of units: " + shoppingCart.get(product));
        }
        System.out.println("\nTotal Price: " + String.format("%.2f", totalPrice) + "R$");
    }
}