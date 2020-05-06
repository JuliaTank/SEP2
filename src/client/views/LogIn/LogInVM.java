package client.views.LogIn;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

public class LogInVM {

  private StringProperty usernameField;
  private StringProperty passwordField;
  private StringProperty errorLabel;

  public LogInVM()
  {
    usernameField = new SimpleStringProperty();
    passwordField = new SimpleStringProperty();
    errorLabel = new SimpleStringProperty();

  }
  public StringProperty getUsernameField()
  {
    return usernameField;
  }

  public StringProperty getErrorLabel()
  {
    return errorLabel;
  }

  public StringProperty getPasswordField()
  {
    return passwordField;
  }

  public void logIn()
  {

  }
  public void join()
  {

  }
}
