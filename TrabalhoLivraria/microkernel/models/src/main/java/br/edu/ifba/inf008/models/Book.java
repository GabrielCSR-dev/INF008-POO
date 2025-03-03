package br.edu.ifba.inf008.models;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import javafx.scene.layout.Region;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;

import javax.naming.NameAlreadyBoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class Book implements Serializable
{
    private int ISBN;
    private String title;
    private String author;
    private int publicationYear;
    private String genre;
    private boolean wasBorrowed;
    private static int numberOfBooks = 0;

    public Book(String title, String author, int publicationYear, String genre){
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
        wasBorrowed = false;
        ISBN = numberOfBooks++;
        ICore.getInstance().getDataController().getLibrary().put(title, this);
    }
    public static void init(int numberOfBooks){
        Book.numberOfBooks = numberOfBooks;
    }

    public static void registerBookScreen(){ 
        ArrayList<Region> gridColumn0 = new ArrayList<Region>();
        ArrayList<Region> gridColumn1 = new ArrayList<Region>();

        gridColumn0.add(new Label("ISBN:"));
        gridColumn1.add(new Label(String.valueOf(numberOfBooks)));
        String[] labelTexts = {"Title", "Author", "Publication Year", "Genre"};
        
        for(String labelText : labelTexts){
            gridColumn0.add(new Label(labelText));
            gridColumn1.add(new TextField());
        }
        Button returnButton = new Button("Return");
        gridColumn0.add(returnButton);
        Button registerButton = new Button("Register");
        gridColumn1.add(registerButton);

        setUpBookRegisterScreen(gridColumn0.toArray(new Region[0]), gridColumn1.toArray(new Region[0]), registerButton, returnButton);
    }
    private static void setUpBookRegisterScreen(Region[] gridColumn0, Region[] gridColumn1, Button registerButton, Button returnButton){
        IUIController uiController = ICore.getInstance().getUIController();
        GridPane grid = uiController.createGridPane(gridColumn0,gridColumn1);

        Label screenLabel = new Label("New Book");
        screenLabel.setAlignment(Pos.CENTER);
        screenLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.setAlignment(Pos.CENTER);
        
        Region[] vBoxControls = {screenLabel,errorLabel,grid};
        uiController.createVBox(vBoxControls);

        registerButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent a){
                String title = ((TextField)gridColumn1[1]).getText().trim();
                String author = ((TextField)gridColumn1[2]).getText().trim();
                String genre = ((TextField)gridColumn1[4]).getText().trim();
                Integer publicationYear = 0;
                try{
                    publicationYear = Integer.valueOf(((TextField)gridColumn1[3]).getText());
                    if(title.isEmpty() || author.isEmpty() || genre.isEmpty())
                        throw new NullPointerException();
                    else if(searchBook(title) != null)
                        throw new NameAlreadyBoundException();
                    else if(publicationYear <= 0)
                        throw new NumberFormatException();
                    new Book(title, author, publicationYear, genre);
                    registerBookScreen();
                }catch(NumberFormatException e){
                    errorLabel.setText("Error: Invalid publication year.");
                }catch(NullPointerException e){
                    errorLabel.setText("Error: You must fill all the fields.");
                }catch(NameAlreadyBoundException e){
                    errorLabel.setText("Error: There is already a book with this title.");
                }
            }
        });
        returnButton.setOnAction(event -> uiController.startScreen());
    }
    public static Book searchBook(String title){
        return (Book)ICore.getInstance().getDataController().getLibrary().get(title);
    }
    public static Book searchAvailableBook(String title){
        Book book = (Book)ICore.getInstance().getDataController().getLibrary().get(title);
        if(book == null || book.wasBorrowed) return null;
        else return book;
    }
    public String getTitle(){
        return title;
    }

    @Override
    public String toString(){
        return new String("ISBN: " + ISBN + "\nTitle: " + title + "\tPub. Year: " + publicationYear + "\nAuthor: " + author + "\tGenre: " + genre);
    }
    public void borrowBook(){
        wasBorrowed = true;
    }
    public void returnBook(){
        wasBorrowed = false;
    }
    public static ArrayList<Object> getAvailableBooks(){
        ArrayList<Object> availableBooks = new ArrayList<Object>();
        TreeMap<String,Object> library = ICore.getInstance().getDataController().getLibrary();
        for(String title : library.keySet()){
            Object book = library.get(title);
            if(((Book)book).wasBorrowed == false) availableBooks.add(book);
        }
        return availableBooks;
    }
}
