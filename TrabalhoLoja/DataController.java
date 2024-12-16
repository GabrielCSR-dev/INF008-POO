import java.util.HashMap;
import java.util.ArrayList;

class DataController{
    public static HashMap<String, User> userMap = new HashMap<String, User>();
    public static ArrayList<Product> registeredProducts = new ArrayList<Product>();
    public static ArrayList<Order> orderHistory = new ArrayList<Order>();
    public static Product leastStockedProduct = null;
    public static Order mostExpensiveOrder = null;
}