package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import javafx.scene.control.MenuItem;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class Book implements IPlugin, Serializable
{
    private int ISBN;
    private String title;
    private String author;
    private int publicationYear;
    private String genre;
    private static int numberOfBooks = 0;

    public boolean init() {

        IUIController uiController = ICore.getInstance().getUIController();

        MenuItem menuItem = uiController.createMenuItem("Book Menu", "Create Book");
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("I'm a book!");
            }
        });

        // uiController.createTab("new tab", new Rectangle(200,200, Color.LIGHTSTEELBLUE));

        return true;
    }
}
