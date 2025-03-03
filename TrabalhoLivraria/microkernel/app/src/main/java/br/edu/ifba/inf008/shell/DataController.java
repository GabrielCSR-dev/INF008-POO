package br.edu.ifba.inf008.shell;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.ArrayList;

import br.edu.ifba.inf008.interfaces.IDataController;
import br.edu.ifba.inf008.models.*;

class DataController implements IDataController{
    public HashMap<Integer,Object> userBase = new HashMap<Integer,Object>();
    public TreeMap<String,Object> library = new TreeMap<String,Object>();
    public ArrayList<Object> loanHistory = new ArrayList<Object>();

    public HashMap<Integer,Object> getUserbase(){
        return userBase;
    }
    public TreeMap<String,Object> getLibrary(){
        return library;
    }
    public ArrayList<Object> getLoanHistory(){
        return loanHistory;
    }
    public void load(HashMap<Integer,Object> userBase){
        this.userBase = userBase;
        User.init(userBase.size());
    }
    public void load(TreeMap<String,Object> library){
        this.library = library;
        Book.init(library.size());
    }
    public void load(ArrayList<Object> loanHistory){
        this.loanHistory = loanHistory;
        for(Object obj : loanHistory)
            ((Loan)obj).calculateFine();
        Loan.init(loanHistory.size());
    }
    
    public ArrayList<Object> getOpenLoans(){
        return Loan.getOpenLoans(loanHistory);
    }
    public ArrayList<Object> getExpiredLoans(){
        return Loan.getExpiredLoans(loanHistory);
    }
}