package client.views.ReportUser;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ReportUserController implements ViewController {

    private ReportUserVM vm = ViewModelFactory.getInstance().getReportUserVM();
    private ViewHandler viewHandler;
    @FXML
    private TextArea textArea;

    public ReportUserController() throws IOException, NotBoundException {
    }

    public void onReportButton() throws RemoteException {
        vm.report(textArea.getText());
        textArea.clear();
    }

    @Override
    public void init() {
        textArea.textProperty().bindBidirectional(vm.getAreaProperty());
    }
}
