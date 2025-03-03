package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;
import br.edu.ifba.inf008.interfaces.IDataController;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.geometry.Pos;

import java.util.ArrayList;

public class LoanReportPlugin implements IPlugin
{
    public boolean init(VBox vbox){
        IUIController uiController = ICore.getInstance().getUIController();
    
        Button reportButton = new Button("Open Loans Report");
        vbox.getChildren().add(reportButton);
        reportButton.prefWidthProperty().bind(vbox.widthProperty().multiply(0.4));
        reportButton.prefHeightProperty().bind(vbox.heightProperty().divide(9));

        reportButton.setOnAction(event -> LoanReportScreen());

        return true;
    }

    public void LoanReportScreen(){
        IUIController uiController = ICore.getInstance().getUIController();

        Label titleLabel = new Label("Open Loans");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        ArrayList<Object> openLoans = ICore.getInstance().getDataController().getOpenLoans();
        ScrollPane loanScrollPane = uiController.makeScrollableList(openLoans);
        Button returnButton = new Button("Return");

        Region[] regions = {titleLabel,loanScrollPane,returnButton};
        VBox mainBox = uiController.createVBox(regions);

        loanScrollPane.setFitToWidth(true);
        loanScrollPane.prefHeightProperty().bind(mainBox.heightProperty().divide(2));

        returnButton.setOnAction(event -> uiController.startScreen());
    }
}
