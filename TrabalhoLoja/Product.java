import java.io.Serializable;

class Product implements Serializable{
    private int ID;
    private String name;
    private String description;
    private float price;
    private int stockQuantity;
    private String category;
    private static int numberOfProducts = 0;

    public Product(String name, String description, float price, int stockQuantity, String category){
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        updateLeastStocked();
        this.category = category;
        this.ID = numberOfProducts++;
        DataController.registeredProducts.add(this);
    }

    public void display(){
        System.out.print("ID " + ID + ") Name: " + name + " | Category: " + category +  " | Description: " + description + " | Price: " + String.format("%.2f", price) + "R$");
    }
    public static void displayAll(){
        System.out.println("List of products: ");
        for(Product product : DataController.registeredProducts){
            product.display();
            System.out.println(" | Quantity available: " + product.stockQuantity);
        }
    }
    public static void displayLeastStocked(){
        if(DataController.registeredProducts.isEmpty()){
            System.out.println("There are no registered products."); return;
        } else if(DataController.leastStockedProduct == null){
            DataController.leastStockedProduct = DataController.registeredProducts.get(0);
            for(Product product : DataController.registeredProducts)
                if(product.stockQuantity < DataController.leastStockedProduct.stockQuantity)
                    DataController.leastStockedProduct = product;
        }
        System.out.println("Least stocked product:");
        DataController.leastStockedProduct.display();
        System.out.println(" | Quantity available: " + DataController.leastStockedProduct.stockQuantity);
    }

    public boolean hasStock(int desiredQuantity){
        if(desiredQuantity <= stockQuantity){
            return true;
        } else return false;
    }
    public void consume(int boughtQuantity){
        stockQuantity -= boughtQuantity;
        updateLeastStocked();
    }
    private void updateLeastStocked(){
        if(DataController.leastStockedProduct != null && stockQuantity < DataController.leastStockedProduct.stockQuantity)
            DataController.leastStockedProduct = this;
    }
    public static Product get(int index){
        if(index < 0 || index >= numberOfProducts)
            return null;
        else return DataController.registeredProducts.get(index);
    }

    public float getPrice(){
        return price;
    }

    public static void load(Object register) throws Exception{
        DataController.registeredProducts.add(((Product)register));
        numberOfProducts++;
    }
}