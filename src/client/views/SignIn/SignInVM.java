package client.views.SignIn;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.model.VegSearchModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferObjects.Profile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SignInVM {

  private StringProperty errorLabel;
  private StringProperty passwordRepeatField;
  private StringProperty passwordCreationField;
  private StringProperty usernameCreationField;
  private StringProperty descriptionArea;
  private StringProperty picApprovedLabel;



  private VegSearchModel model = ModelFactory.getInstance().getModel();

  public SignInVM() throws IOException, NotBoundException, SQLException
  {
    errorLabel = new SimpleStringProperty();
    passwordRepeatField = new SimpleStringProperty();
    passwordCreationField = new SimpleStringProperty();
    usernameCreationField = new SimpleStringProperty();
    descriptionArea = new SimpleStringProperty();
    picApprovedLabel = new SimpleStringProperty();
  }

  public void signUp(File picFile)
      throws IOException, SQLException, NotBoundException
  {
    if(model.signUp(usernameCreationField.getValue(),passwordCreationField.getValue(),picFile, getDescriptionArea().getValue()))
    {
      ViewHandler.getInstance().openLogIn();
    }
    else
    {
      errorLabel.setValue("Your profile couldn't be created, change username");
    }
  }

  public StringProperty getErrorLabel()
  {
    return errorLabel;
  }

  public StringProperty getPasswordRepeatField()
  {
    return passwordRepeatField;
  }

  public StringProperty getDescriptionArea()
  {
    return descriptionArea;
  }

  public StringProperty getPasswordCreationField()
  {
    return passwordCreationField;
  }

  public boolean save(String oldUsername,String newUsername, String password,
      File picFile, String description, ArrayList<Profile> subs)
      throws FileNotFoundException, SQLException, RemoteException
  {
    return model.editProfile(oldUsername, newUsername, password, picFile, description, subs);
  }
  public StringProperty getUsernameCreationField()
  {
    return usernameCreationField;
  }
}
