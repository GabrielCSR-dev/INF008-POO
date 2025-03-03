package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IIOController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

@SuppressWarnings("unchecked")
public class IOController implements IIOController{
    private static FileOutputStream fos;
    private static ObjectOutputStream oos;
    private static FileInputStream fis;
    private static ObjectInputStream ois;

    public void load(){
        try{
            fis = new FileInputStream("libraryRegister.dat");
            ois = new ObjectInputStream(fis);

            Object collection = (Object)ois.readObject();
            while((collection = (Object)ois.readObject()).equals("(END)") == false){
                if(collection instanceof HashMap)
                    ICore.getInstance().getDataController().load((HashMap<Integer,Object>)collection);
                else if(collection instanceof TreeMap)
                    ICore.getInstance().getDataController().load((TreeMap<String,Object>)collection);
                else if(collection instanceof ArrayList)
                    ICore.getInstance().getDataController().load((ArrayList<Object>)collection);
            }

            fis.close();
            ois.close();
        } catch(IOException | ClassNotFoundException e){
            System.out.println("Error:" + e.getMessage());
        }
    }

    public void save(){
        try{
            fos = new FileOutputStream("libraryRegister.dat");
            oos = new ObjectOutputStream(fos);

            oos.writeObject("(Begin)");
            oos.writeObject(ICore.getInstance().getDataController().getUserbase());
            oos.writeObject(ICore.getInstance().getDataController().getLibrary());
            oos.writeObject(ICore.getInstance().getDataController().getLoanHistory());
            oos.writeObject("(END)");

            fos.close();
            oos.close();
        } catch(IOException e){
            System.out.println("Error:" + e.getMessage());
        }
    }
}
