package client.views.LogIn;

import client.core.ModelFactory;
import client.model.VegSearchModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

import java.io.IOException;
import java.rmi.NotBoundException;

public class LogInVM {

  private StringProperty usernameField;
  private StringProperty passwordField;
  private StringProperty errorLabel;
  private  VegSearchModel model;

  public LogInVM() throws IOException, NotBoundException
  {
    usernameField = new SimpleStringProperty();
    passwordField = new SimpleStringProperty();
    errorLabel = new SimpleStringProperty();
    this.model = ModelFactory.getInstance().getModel();

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
    if(usernameField.toString().equals("") || passwordField.toString().equals(""))
    {
      errorLabel.setValue("Type in your username and password");
    }
    else if(!model.logIn(usernameField.toString(),passwordField.toString()))
    {
      errorLabel.setValue("Wrong password or username");
      passwordField.setValue("");
    }
  }

  public void join()
  {

  }
}
