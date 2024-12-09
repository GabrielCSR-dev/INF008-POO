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

    public void display(){
        System.out.print("ID " + ID + ") Name: " + name + " | Description: " + description + " | Category: " + category + " | Price: " + String.format("%.2f", price) + "R$");
    }
    public static void displayAll(){
        System.out.println("List of products: ");
        for(Product product : registeredProducts){
            product.display();
            System.out.println(" | Quantity available: " + product.stockQuantity);
        }
    }
    public static void displayLeastStocked(){
        if(registeredProducts.isEmpty()){
            System.out.println("There are no registered products."); return;
        }
        Product leastStocked = registeredProducts.get(0);
        for(Product product : registeredProducts)
            if(product.stockQuantity < leastStocked.stockQuantity)
                leastStocked = product;
        System.out.println("Least stocked product:");
        leastStocked.display();
        System.out.println(" | Quantity available: " + leastStocked.stockQuantity);
    }

    public boolean consumeIfAvailable(int desiredQuantity){
        if(desiredQuantity <= stockQuantity){
            stockQuantity -= desiredQuantity;
            return true;
        } else return false;
    }

    public static Product get(int index){
        if(index < 0 || index >= numberOfProducts)
            return null;
        else return registeredProducts.get(index);
    }

    public float getPrice(){
        return price;
    }
}