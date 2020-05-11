package client.views.ReportAdm;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import shared.networking.RMIServer;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import java.awt.*;
import java.io.IOException;
import java.rmi.NotBoundException;

public class ReportAdmController implements ViewController {
    private ReportAdmVM vm= ViewModelFactory.getInstance().getReportAdmVM();
    private Recipe recipe;
    private ViewHandler viewHandler;
    @FXML
    private Text reportFromUser;
    @FXML
    private TextArea textArea;

    public ReportAdmController() throws IOException, NotBoundException {
    }

    @Override
    public void init(Profile profile) {
        textArea.textProperty().bindBidirectional(vm.getAreaProperty());
        textArea.setWrapText(true);
        reportFromUser.setText(profile.getUsername());
    }
   /* public void onRecipeButton() {
        vm.openRecipe(recipe);
    }*/


}
