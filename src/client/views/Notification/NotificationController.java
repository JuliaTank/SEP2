package client.views.Notification;

import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import shared.transferObjects.Notification;
import shared.transferObjects.Profile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class NotificationController {
    private NotificationVM vm= ViewModelFactory.getInstance().getNotificationVM();
    private  Notification notification;
    @FXML
    Label titleLabel;

    @FXML
    Label contentLabel;

    public NotificationController()
        throws IOException, NotBoundException, SQLException
    {
    }

    public void onSeeButton(ActionEvent actionEvent)
        throws IOException, SQLException
    {
        vm.see(notification.getRecipeTitle());
    }

    public void onCancelButton(ActionEvent actionEvent) {
        vm.cancel();
    }

    public void init(Notification notification) throws FileNotFoundException, SQLException, RemoteException {
        this.notification = notification;

        titleLabel.textProperty().bindBidirectional(vm.getTitleLabelProperty());
        contentLabel.textProperty().bindBidirectional(vm.getContentLabelProperty());

        titleLabel.setText("New notification from "+notification.getUsername()+": "+notification.getRecipeTitle());

          //  titleLabel.setText("New report from"+notification.getUsername()+": "+notification.getRecipeTitle());
        System.out.println(titleLabel.getText());

          contentLabel.setText(notification.getMessage() );
    }
}
