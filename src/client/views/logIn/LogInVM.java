package client.views.logIn;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.model.VegSearchModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class LogInVM {

  private StringProperty usernameField;
  private StringProperty passwordField;
  private StringProperty errorLabel;
  private  VegSearchModel model;
  private ViewHandler vh = ViewHandler.getInstance();

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

  public void logIn() throws RemoteException, SQLException
  {
    System.out.println(usernameField.toString()+"  "+passwordField.toString());
    if(usernameField.toString().equals("") || passwordField.toString().equals(""))
    {
      errorLabel.setValue("Type in your username and password");
    }
    else if(!model.logIn(usernameField.getValue(), passwordField.getValue()))
    {

      errorLabel.setValue("Wrong password or username");
      passwordField.setValue("");
    }
    else
    {
      vh.openMainPage();
    }
  }

  public void join()
  {
   vh.openSignIn();
  }
}
