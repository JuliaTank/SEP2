package client.views.logIn;

import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import shared.transferObjects.Profile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class LogInController implements ViewController
{
  private LogInVM vm = ViewModelFactory.getInstance().getLogInVM();
  @FXML
  private ImageView VSlogo;
  @FXML
  private TextField usernameField;
  @FXML
  private Label errorLabel;
  @FXML
  private PasswordField passwordField;

  public LogInController() throws IOException, NotBoundException, SQLException
  {
  }
  //................................................................TEST.........................................
  public void onLoginButton(ActionEvent actionEvent)
          throws IOException, SQLException {
    vm.logIn();
  }

  public void onJoinButton(ActionEvent actionEvent)
  {
    vm.join();
  }

  @Override public void init(Profile profile)
      throws FileNotFoundException, SQLException, RemoteException
  {
    Image image3 = new Image("file:carrotLogo.png");
    VSlogo.setImage(image3);

    usernameField.textProperty().bindBidirectional(vm.getUsernameField());
    errorLabel.textProperty().bindBidirectional(vm.getErrorLabel());
    passwordField.textProperty().bindBidirectional(vm.getPasswordField());
  }
}
