package client.views.Notification;

import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import shared.transferObjects.Profile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class NotificationController implements ViewController {
    private NotificationVM vm= ViewModelFactory.getInstance().getNotificationVM();
    @FXML
    TextArea textArea;
    public NotificationController()
        throws IOException, NotBoundException, SQLException
    {
    }

    public void onSeeButton(ActionEvent actionEvent) {
        vm.see();
    }

    public void onCancelButton(ActionEvent actionEvent) {
        vm.cancel();
    }


    @Override
    public void init(Profile profile) throws FileNotFoundException, SQLException, RemoteException {
        textArea.textProperty().bindBidirectional(vm.getAreaProperty());
        textArea.setWrapText(true);
    }
}
