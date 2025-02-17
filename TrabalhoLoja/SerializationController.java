import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;

class SerializationController{
    private static FileOutputStream fos;
    private static ObjectOutputStream oos;
    private static FileInputStream fis;
    private static ObjectInputStream ois;


    public SerializationController() throws Exception{
    }

    public static boolean load() throws Exception{
        if(new File("storeRegister.dat").isFile() == false)
            return false;
        fis = new FileInputStream("storeRegister.dat");
        ois = new ObjectInputStream(fis);
        Object register = ois.readObject();
        while((register = ois.readObject()).equals("(END)") == false)
            register.load(register);
        fis.close();
        ois.close();
        return true;
    }
    public static void save() throws Exception{
        fos = new FileOutputStream("storeRegister.dat");
        oos = new ObjectOutputStream(fos);
        oos.writeObject("(Begin)");
        save(DataController.userMap.values());
        save(DataController.registeredProducts);
        save(DataController.orderHistory);
        oos.writeObject("(END)");
        fos.close();
        oos.close();
    }
    public static void save(Collection<?> list) throws Exception{
        for(Object object : list)
            oos.writeObject(object);
    }
}
