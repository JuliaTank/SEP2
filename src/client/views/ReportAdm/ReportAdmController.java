package client.views.ReportAdm;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import shared.networking.RMIServer;
import shared.transferObjects.Recipe;

import java.awt.*;
import java.io.IOException;
import java.rmi.NotBoundException;

public class ReportAdmController implements ViewController {
    private ReportAdmVM vm= ViewModelFactory.getInstance().getReportAdmVM();
    private Recipe recipe;
    private ViewHandler viewHandler;
    @FXML
    private TextArea textArea;

    public ReportAdmController() throws IOException, NotBoundException {
    }

    @Override
    public void init() {
        textArea.textProperty().bindBidirectional(vm.getAreaProperty());
        textArea.setWrapText(true);
    }
    public void onRecipeButton() {
        vm.openRecipe(recipe);
    }


}
