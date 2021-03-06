package client.views.logIn;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.model.VegSearchModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;

public class LogInVM {

  private StringProperty usernameField;
  private StringProperty passwordField;
  private StringProperty errorLabel;
  private VegSearchModel model;
  private ViewHandler vh = ViewHandler.getInstance();

  public LogInVM() throws IOException, NotBoundException, SQLException
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
          throws IOException, SQLException {
    if(usernameField.getValue()==null|| passwordField.getValue()==null)
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
      errorLabel.setValue("");
      usernameField.setValue("");
      passwordField.setValue("");
    }
  }
  public void join()
  {
   vh.openSignIn(null);
    errorLabel.setValue("");
    usernameField.setValue("");
    passwordField.setValue("");
  }
}
