package org.openjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.layout.Priority;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        label.setAlignment(Pos.CENTER);
        BorderPane border = new BorderPane();
        border.setBottom(label);
        border.setCenter(addVBox());

        var scene = new Scene(border, 640, 480);
        stage.setTitle("Welcome to JavaFX!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);   // Gap between nodes
        hbox.setStyle("-fx-background-color: #336699;");

        ToggleButton buttonCurrent = new ToggleButton("Current");
        //buttonCurrent.setPrefSize(200, 20);
        buttonCurrent.setMaxWidth(Double.MAX_VALUE);
        buttonCurrent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("OnAction " + event);
            }
        });

        Button buttonProjected = new Button("Projected");
        //buttonProjected.setPrefSize(100, 20);
        buttonProjected.setMaxWidth(Double.MAX_VALUE);

        HBox.setHgrow(buttonCurrent, Priority.ALWAYS);
        HBox.setHgrow(buttonProjected, Priority.ALWAYS);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);

        return hbox;
    }

    private VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(15, 12, 15, 12));
        vbox.setSpacing(10);   // Gap between nodes
        vbox.setStyle("-fx-background-color: #336699;");

        ToggleButton buttonCurrent = new ToggleButton("Current");
        //buttonCurrent.setPrefSize(200, 20);
        buttonCurrent.setMaxWidth(Double.MAX_VALUE);
        buttonCurrent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("OnAction " + event);
            }
        });

        Button buttonProjected = new Button("Projected");
        //buttonProjected.setPrefSize(100, 20);
        buttonProjected.setMaxWidth(Double.MAX_VALUE);

        HBox.setHgrow(buttonCurrent, Priority.ALWAYS);
        HBox.setHgrow(buttonProjected, Priority.ALWAYS);
        vbox.getChildren().addAll(buttonCurrent, buttonProjected);

        return vbox;
    }

}
