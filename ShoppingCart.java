import java.util.HashMap;

class ShoppingCart{
    private HashMap<Product, int> shoppingCart = new HashMap<Product, int>();
    private float totalPrice = 0;

    public ShoppingCart(){
    }

    public void addProductIfPossible(Product product, int quantity){
        int totalQuantity = quantity + shoppingCart.getOrDefault(product, 0);
        if(shoppingCart.containsKey(product) && product.isThereEnoughStock(totalQuantity)){
                shoppingCart.replace(product, totalQuantity);
                totalPrice += quantity*product.getPrice();
        } else if(product.isThereEnoughStock(quantity)){
                shoppingCart.put(product, quantity);
                totalPrice += quantity*product.getPrice();
        } else System.out.println("Error: the desired quantity is above the product's stock.");
    }

    public void display(){
        System.out.println("SHOPPING CART:");
        for(Product product, int quantity : shoppingCart.entryset()){
            product.displayForCart();
            System.out.println(" | Amount of units: " + quantity);
        }
        System.out.println("\nTotal Price: " + String.format("%.2f", totalPrice) + "R$");
    }
}