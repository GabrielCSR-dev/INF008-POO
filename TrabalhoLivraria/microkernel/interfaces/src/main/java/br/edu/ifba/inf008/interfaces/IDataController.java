package br.edu.ifba.inf008.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public interface IDataController
{
    public abstract HashMap<Integer,Object> getUserbase();
    public abstract TreeMap<String,Object> getLibrary();
    public abstract ArrayList<Object> getLoanHistory();
    public abstract void load(HashMap<Integer,Object> userBase);
    public abstract void load(TreeMap<String,Object> library);
    public abstract void load(ArrayList<Object> loanHistory);
    public abstract ArrayList<Object> getOpenLoans();
    public abstract ArrayList<Object> getExpiredLoans();
}