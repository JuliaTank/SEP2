package client.views.logIn;

import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class LogInController implements ViewController
{
 private LogInVM vm = ViewModelFactory.getInstance().getLogInVM();
  @FXML
  private TextField usernameField;
  @FXML
  private Label errorLabel;
  @FXML
  private PasswordField passwordField;

  public LogInController() throws IOException, NotBoundException
  {
  }

  public void init()
  {
    usernameField.textProperty().bindBidirectional(vm.getUsernameField());
    errorLabel.textProperty().bind(vm.getErrorLabel());
    passwordField.textProperty().bindBidirectional(vm.getPasswordField());
  }

  public void onLoginButton(ActionEvent actionEvent)
      throws RemoteException, SQLException
  {
    vm.logIn();
  }
  public void onJoinButton(ActionEvent actionEvent)
  {
    vm.join();
  }
}
