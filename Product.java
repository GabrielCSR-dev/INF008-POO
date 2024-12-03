import java.util.ArrayList;

class Product{
    private int ID;
    private String name;
    private String description;
    private float price;
    private int stockQuantity;
    private String category;
    private static int numberOfProducts = 0;
    private static ArrayList<Product> registeredProducts = new ArrayList<Product>();

    public Product(String name, String description, float price, int stockQuantity, String category){
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.ID = numberOfProducts++;
        registeredProducts.add(this);
    }

    public static void displayForCart(){
        System.out.print("ID " + product.ID + ") Name: " + product.name + " | Price: " + product.price);
    }
    
    public static void displayAll(){
        System.out.println("List of products: ");
        for(Product product : registeredProducts)
            System.out.println("ID " + product.ID + ") Name: " + product.name + " | Quantity available: " + product.stockQuantity + " | Price: " + product.price);
    }

    public boolean isThereEnoughStock(int desiredQuantity){
        return desiredQuantity <= stockQuantity;
    }
    public boolean isThereEnoughStock(){
        return stockQuantity == 0;
    }

    public void consumeStock(int desiredQuantity){
        if(isThereEnoughStock(desiredQuantity))
            stockQuantity -= desiredQuantity;
        else System.out.println("There is not enough stock for this product.");
    }

    public static Product get(int index){
        if(index < 0 || index >= size())
            return null;
        else return registeredProducts.get(index);
    }

    public int getPrice(){
        return this.price;
    }
}