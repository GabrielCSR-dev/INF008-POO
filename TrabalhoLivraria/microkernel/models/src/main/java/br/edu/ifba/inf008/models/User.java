package br.edu.ifba.inf008.models;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import javafx.scene.layout.Region;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.Serializable;

import java.util.ArrayList;

public class User implements Serializable
{
    private int ID;
    private String name;
    private ArrayList<Object> currentLoans = new ArrayList<Object>();
    private static int numberOfUsers = 0;


    public User(String name){
        this.name = name;
        ID = numberOfUsers++;
        ICore.getInstance().getDataController().getUserbase().put(ID,this);
    }

    public static void registerUserScreen(){
        IUIController uiController = ICore.getInstance().getUIController();

        Label IDLabel = new Label("ID:");
        Label IDCPreview = new Label(String.valueOf(numberOfUsers));
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Button registerButton = new Button("Register");
        Button returnButton = new Button("Return");

        Region[] gridColumn0 = {IDLabel,nameLabel,returnButton};
        Region[] gridColumn1 = {IDCPreview,nameField,registerButton};
        GridPane grid = uiController.createGridPane(gridColumn0, gridColumn1);

        Label screenLabel = new Label("New User");
        screenLabel.setAlignment(Pos.CENTER);
        screenLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setAlignment(Pos.CENTER);
        
        Region[] VBoxRegions = {screenLabel,errorLabel,grid};
        uiController.createVBox(VBoxRegions);

        registerButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                String name = nameField.getText().trim();
                if(name.isEmpty()){
                    errorLabel.setText("Error: The user must have a name.");
                }else{
                    new User(name);
                    registerUserScreen();
                }
            }
        });
        returnButton.setOnAction(event -> uiController.startScreen());
    }
    public static void init(int numberOfUsers){
        User.numberOfUsers = numberOfUsers;
    }
    public void homeScreen(){
        IUIController uiController = ICore.getInstance().getUIController();

        Label welcomeLabel = new Label("Welcome, " + name + "!");
        welcomeLabel.setAlignment(Pos.CENTER);
        welcomeLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setAlignment(Pos.CENTER);
        Button loanBookButton = new Button("Loan Book");
        Button returnLoanButton = new Button("Return Loan");
        Button logOutButton = new Button("Log Out");

        Region[] regions = {welcomeLabel,errorLabel,loanBookButton,returnLoanButton,logOutButton};
        
        uiController.createVBox(regions);
        loanBookButton.setOnAction(event -> Loan.makeLoanScreen(this, new ArrayList<Object>()));
        returnLoanButton.setOnAction(event -> Loan.makeReturnLoanScreen(this,currentLoans));
        logOutButton.setOnAction(event -> uiController.startScreen());
    }
    public void addLoan(Loan loan){
        currentLoans.add(loan);
    }
    @Override 
    public String toString(){
        return new String("User ID: " + ID + "\tUser name: " + name);
    }
}
