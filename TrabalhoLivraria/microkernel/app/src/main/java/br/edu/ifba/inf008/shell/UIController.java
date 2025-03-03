package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.IUIController;

import java.util.ArrayList;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.models.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;

public class UIController extends Application implements IUIController
{
    private static UIController uiController;
    private static Stage stage;

    public UIController() {
    }

    @Override
    public void init() {
        uiController = this;
    }

    public static UIController getInstance() {
        return uiController;
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        ICore.getInstance().getIOController().load();
        startScreen();
    }
    @Override
    public void stop(){
        ICore.getInstance().getIOController().save();
    }

    public void startScreen(){
        Button logInAsUserButton = new Button("Log In as User");
        Button registerUserButton = new Button("Register User");
        Button registerBookButton = new Button("Register Book");

        logInAsUserButton.setOnAction(event -> Administrator.logInAsUserScreen());
        registerUserButton.setOnAction(event -> User.registerUserScreen());
        registerBookButton.setOnAction(event -> Book.registerBookScreen());

        Region[] buttons = {logInAsUserButton,registerUserButton,registerBookButton};
        VBox vBox = createVBox(buttons);
        ICore.getInstance().getPluginController().init(vBox);
    }

    public VBox createVBox(Region[] regions){       
        VBox vbox = new VBox(10,regions); 
        vbox.setAlignment(Pos.CENTER); 
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 960, 600);
        stage.setTitle("Cabral Library");
        stage.setScene(scene);
        stage.show();

        vbox.prefWidthProperty().bind(scene.widthProperty());
        vbox.prefHeightProperty().bind(scene.heightProperty());

        for(Region region : regions){
            region.prefWidthProperty().bind(vbox.widthProperty().multiply(0.4));
            region.prefHeightProperty().bind(vbox.heightProperty().divide(9));
        }

        return vbox;
    }

    public GridPane createGridPane(Region[] regionColumn0, Region[] regionColumn1){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        int i;
        for(i = 0; i < regionColumn0.length; i++){
            grid.add(regionColumn0[i],0,i+1);
            grid.add(regionColumn1[i],1,i+1);
            
            regionColumn0[i].prefWidthProperty().bind(grid.widthProperty().multiply(0.2));
            regionColumn0[i].prefHeightProperty().bind(grid.heightProperty().divide(9));
            regionColumn1[i].prefWidthProperty().bind(grid.widthProperty().multiply(0.2));
            regionColumn1[i].prefHeightProperty().bind(grid.heightProperty().divide(9));
        }

        Scene scene = new Scene(grid, 960, 600);
        stage.setTitle("Cabral Library");
        stage.setScene(scene);
        stage.show();

        grid.prefWidthProperty().bind(scene.widthProperty());
        grid.prefHeightProperty().bind(scene.heightProperty());

        return grid;
    }

    public ScrollPane makeScrollableList(ArrayList<Object> objects){
        ObservableList<Object> observableBooks = FXCollections.observableArrayList(objects);
        ListView<Object> listView = new ListView<>(observableBooks);
        listView.setCellFactory(param -> new ListCell<Object>() {
            @Override
            protected void updateItem(Object object, boolean empty) {
                super.updateItem(object, empty);
                if (empty || object == null) {
                    setText(null);
                } else {
                    setText(object.toString());
                }
            }
        });

        ScrollPane scrollPane = new ScrollPane(listView);
        scrollPane.setFitToWidth(true);
        scrollPane.prefHeightProperty().bind(stage.heightProperty().divide(2));

        return scrollPane;
    }

    public void makeLoanScreen(ArrayList<Object> books){
        ObservableList<Object> observableBooks = FXCollections.observableArrayList(books);
        ListView<Object> listView = new ListView<>(observableBooks);
        listView.setCellFactory(param -> new ListCell<Object>() {
            @Override
            protected void updateItem(Object book, boolean empty) {
                super.updateItem(book, empty);
                if (empty || book == null) {
                    setText(null);
                } else {
                    setText(book.toString());
                }
            }
        });

        ScrollPane scrollPane = new ScrollPane(listView);
        scrollPane.setFitToWidth(true);

        Label searchLabel = new Label("Search book by title:");
        TextField searchField = new TextField();
        Button submitButton = new Button("Submit");

        submitButton.setOnAction(event -> {
            String searchText = searchField.getText().trim();
            if (!searchText.isEmpty()) {
                System.out.println("Certo!");
            } else {
                System.out.println("Error: Please enter a book title.");
            }
        });

        VBox searchBox = new VBox(10, searchLabel, searchField, submitButton);
        searchBox.setAlignment(Pos.CENTER);

        VBox mainBox = new VBox(20, scrollPane, searchBox);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setPadding(new Insets(20));

        scrollPane.prefHeightProperty().bind(stage.heightProperty().divide(2));

        Scene scene = new Scene(mainBox, 400, 400);
        stage.setTitle("Book Search Screen");
        stage.setScene(scene);
        stage.show();
    }
}
