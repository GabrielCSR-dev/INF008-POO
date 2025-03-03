package br.edu.ifba.inf008.interfaces;

import javafx.scene.control.ScrollPane;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public interface IUIController
{
    public void startScreen();
    public abstract VBox createVBox(Region[] controls);
    public abstract GridPane createGridPane(Region[] regionColumn0, Region[] regionColumn1);
    public abstract ScrollPane makeScrollableList(ArrayList<Object> objects);
    public abstract void makeLoanScreen(ArrayList<Object> books);
}
