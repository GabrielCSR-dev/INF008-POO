package br.edu.ifba.inf008.models;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import javafx.scene.control.MenuItem;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.io.Serializable;

import java.util.ArrayList;

public class User implements Serializable
{
    private int ID;
    private String name;
    private ArrayList<Loan> currentLoans = new ArrayList<Loan>();
    private static int numberOfUsers = 0;


    public User(String name){
        this.name = name;
        this.ID = numberOfUsers++;
    }

    public static boolean init() {

        IUIController uiController = ICore.getInstance().getUIController();

        MenuItem menuItem = uiController.createMenuItem("User Menu", "Create User");
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("I'm a user!");
            }
        });

        // uiController.createTab("new tab", new Rectangle(200,200, Color.LIGHTSTEELBLUE));

        return true;
    }
    
    public void openLoan(){
        Loan newLoan = null;
        newLoan = Loan.openLoan(this);
    }
    public void returnLoan(Loan loan){
        currentLoans.remove(loan);
    }
}
