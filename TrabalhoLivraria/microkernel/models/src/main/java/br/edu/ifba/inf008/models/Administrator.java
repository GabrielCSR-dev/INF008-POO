package br.edu.ifba.inf008.models;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import javafx.scene.layout.Region;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.Serializable;

public class Administrator implements Serializable{

    public static void logInAsUserScreen(){
        IUIController uiController = ICore.getInstance().getUIController();

        Label screenLabel = new Label("Log In");
        screenLabel.setAlignment(Pos.CENTER);
        screenLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setAlignment(Pos.CENTER);
        Label IDLabel = new Label("Insert ID:");
        TextField IDField = new TextField();
        Button logInButton = new Button("Submit");
        Button returnButton = new Button("Return");

        Region[] gridColumn0 = {IDLabel,returnButton};
        Region[] gridColumn1 = {IDField,logInButton};
        GridPane grid = uiController.createGridPane(gridColumn0,gridColumn1);
        Region[] vBoxRegions = {screenLabel,errorLabel,grid};
        uiController.createVBox(vBoxRegions);

        logInButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent a){
                Integer ID = 0;
                try{
                    ID = Integer.valueOf(IDField.getText());
                    if(ID < 0)
                        throw new NumberFormatException();
                    User loggedUser = searchID(ID);
                    if(loggedUser == null)
                        errorLabel.setText("Error: ID not found.");
                    else loggedUser.homeScreen();
                }catch(NumberFormatException e){
                    errorLabel.setText("Error: Invalid ID.");
                }
            }
        });
        returnButton.setOnAction(event -> uiController.startScreen());
    }

    private static User searchID(int ID){
        return (User)ICore.getInstance().getDataController().getUserbase().get(ID);
    }
}