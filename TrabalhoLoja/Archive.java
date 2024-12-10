import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

class Archive{
    private static FileOutputStream fos;
    private static ObjectOutputStream oos;
    private static FileInputStream fis;
    private static ObjectInputStream ois;


    public Archive() throws Exception{
    }

    public static void load() throws Exception{
        fis = new FileInputStream("storeRegister.dat");
        ois = new ObjectInputStream(fis);
        Object register = ois.readObject();
        while((register = ois.readObject()).equals("(END)") == false){
            if(register instanceof User){
                User.load(register);
            }else if(register instanceof Product){
                Product.load(register);
            }else if(register instanceof Order){
                Order.load(register);
            }
        }
        fis.close();
        ois.close();
    }
    public static void save() throws Exception{
        fos = new FileOutputStream("storeRegister.dat");
        oos = new ObjectOutputStream(fos);
        oos.writeObject("(Begin)");
        User.save();
        Product.save();
        Order.save();
        oos.writeObject("(END)");
        fos.close();
        oos.close();
    }
    public static void write(Object register) throws Exception{
        oos.writeObject(register);
    }
}