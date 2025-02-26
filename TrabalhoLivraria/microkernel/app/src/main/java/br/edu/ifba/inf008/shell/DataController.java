package br.edu.ifba.inf008.shell;

import java.util.HashMap;
import java.util.ArrayList;

import br.edu.ifba.inf008.interfaces.IDataController;
import br.edu.ifba.inf008.models.*;

class DataController implements IDataController{
    public static ArrayList<User> userList = new ArrayList<User>();
    public static HashMap<String,Book> library = new HashMap<String,Book>();
    public static ArrayList<Loan> allCurrentLoans = new ArrayList<Loan>();
}