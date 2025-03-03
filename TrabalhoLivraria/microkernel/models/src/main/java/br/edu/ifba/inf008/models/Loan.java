package br.edu.ifba.inf008.models;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;

import java.io.Serializable;

import java.util.ArrayList;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan implements Serializable{
    private int ID;
    private User user;
    private ArrayList<Object> books = new ArrayList<>();
    private LocalDate borrowDate;
    private LocalDate expirationDate;
    private boolean isActive;
    private boolean isExpired;
    private double fine = 0; 
    private static int numberOfLoans = 0;

    public Loan(User user, LocalDate borrowDate, ArrayList<Object> books){
        this.user = user;
        this.borrowDate = borrowDate;
        this.books = books;
        expirationDate = borrowDate.plusDays(14);
        isActive = true;
        isExpired = LocalDate.now().isAfter(expirationDate) ? true : false;
        calculateFine();
        ID = numberOfLoans++;

        user.addLoan(this);
        ICore.getInstance().getDataController().getLoanHistory().add(this);
    }
    public static void init(int numberOfLoans){
        Loan.numberOfLoans = numberOfLoans;
    }
    public void calculateFine(){
        LocalDate today = LocalDate.now();
        if(today.isAfter(expirationDate)){
            double daysDifference = ChronoUnit.DAYS.between(expirationDate, today);
            fine = daysDifference*0.5;
        }else fine = 0;
    }
    public static ArrayList<Object> getOpenLoans(ArrayList<Object> loanHistory){
        ArrayList<Object> openLoans = new ArrayList<Object>();
        for(Object loan : loanHistory){
            ((Loan)loan).calculateFine();
            if(((Loan)loan).isActive == true){
                openLoans.add(loan);
            }
        }
        return openLoans;
    }
    public static ArrayList<Object> getExpiredLoans(ArrayList<Object> loanHistory){
        ArrayList<Object> expiredLoans = new ArrayList<Object>();
        for(Object loan : loanHistory){
            ((Loan)loan).calculateFine();
            if(((Loan)loan).isActive == true && ((Loan)loan).isExpired == true)
                expiredLoans.add(loan);
        }
        return expiredLoans;
    }
    
    public static void makeLoanScreen(User user, ArrayList<Object> booksToLoan){
        IUIController uiController = ICore.getInstance().getUIController();

        ArrayList<Object> availableBooks = Book.getAvailableBooks();
        Label searchLabel = new Label("Search book by title");
        searchLabel.setAlignment(Pos.CENTER);
        searchLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setAlignment(Pos.CENTER);
        TextField searchField = new TextField();
        Button searchButton = new Button("Submit"); 
        Button returnButton = new Button("Finish");

        ScrollPane scrollPane = uiController.makeScrollableList(availableBooks);

        Region[] regions = {scrollPane,searchLabel,errorLabel,searchField,searchButton,returnButton};
        VBox mainBox = uiController.createVBox(regions);
        scrollPane.setFitToWidth(true);
        scrollPane.prefHeightProperty().bind(mainBox.heightProperty().divide(4));

        searchButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent a){
                String title = searchField.getText().trim();
                Book book = Book.searchAvailableBook(title);
                if(title.isEmpty()){
                    errorLabel.setText("Error: Search field empty.");
                }else if(book == null){
                    errorLabel.setText("Error: Book not found.");
                }else if(booksToLoan.size() == 5){
                    errorLabel.setText("Error: The loan already has\nthe max of 5 books.");
                }else{
                    book.borrowBook();
                    booksToLoan.add(book);
                    makeLoanScreen(user,booksToLoan);
                }                
            }
        });
        returnButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent a){
                if(booksToLoan.isEmpty() == false){
                    LocalDate borrowDate = LocalDate.now();
                    new Loan(user,borrowDate,booksToLoan);
                }
                user.homeScreen();
            }
        });
    }
    
    @Override 
    public String toString(){
        String str = new String("Loan ID: " + ID + "\n");
        for(Object book : books)
            str += ((Book)book).toString() + '\n';
        str += user.toString() + "\nBorrow Date: " + borrowDate + "\tExpiration Date: " + expirationDate + "\nFine: " + fine + "R$";
        return str;
    }

    public static void makeReturnLoanScreen(User user, ArrayList<Object> userLoans){
        IUIController uiController = ICore.getInstance().getUIController();

        ScrollPane scrollPane = uiController.makeScrollableList(userLoans);
        Label searchLabel = new Label("Return loan by ID");
        searchLabel.setAlignment(Pos.CENTER);
        searchLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setAlignment(Pos.CENTER);
        TextField searchField = new TextField();
        Button searchButton = new Button("Submit"); 
        Button returnButton = new Button("Finish");

        Region[] regions = {scrollPane,searchLabel,errorLabel,searchField,searchButton,returnButton};
        VBox mainBox = uiController.createVBox(regions);
        scrollPane.setFitToWidth(true);
        scrollPane.prefHeightProperty().bind(mainBox.heightProperty().divide(4));

        returnButton.setOnAction(event -> user.homeScreen());
        searchButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent a){
                Integer ID = 0;
                try{
                    ID = Integer.valueOf(searchField.getText().trim());
                    Loan loan = null;
                    for(Object obj : userLoans){
                        if(((Loan)obj).ID == ID)
                            loan = (Loan)obj;
                    }
                    if(ID < 0){
                        throw new NumberFormatException();
                    }else if(userLoans.isEmpty()){
                        errorLabel.setText("Error: The user doesn't have any open loans.");
                    }else if(loan == null){
                        errorLabel.setText("Error: Loan not found.");
                    }else{
                        userLoans.remove(loan);
                        loan.isActive = false;
                        for(Object book : loan.books)
                            ((Book)book).returnBook();
                        makeReturnLoanScreen(user,userLoans);
                    } 
                }catch(NumberFormatException e){
                    errorLabel.setText("Error: Invalid ID value.");
                }               
            }
        });
    }
}