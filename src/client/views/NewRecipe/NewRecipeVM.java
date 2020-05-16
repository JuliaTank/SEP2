package client.views.NewRecipe;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.model.VegSearchModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferObjects.Notification;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewRecipeVM {
  private StringProperty errorLabel;
  private VegSearchModel model= ModelFactory.getInstance().getModel();
  private ViewHandler vh = ViewHandler.getInstance();

  public NewRecipeVM() throws IOException, NotBoundException, SQLException
  {
    errorLabel = new SimpleStringProperty();
    model.addListener("NewNotification",this::onNewNotification);
  }

  private void onNewNotification(PropertyChangeEvent propertyChangeEvent)
  {
    try{
      vh.openNotification((Notification)propertyChangeEvent.getNewValue());
    }
    catch (IOException|SQLException e)
    {
      e.printStackTrace();
    }

  }

  public StringProperty getErrorLabel()
  {
    return errorLabel;
  }

  public void save(String title, String description, ArrayList<String> ingredients, File picfile)
      throws RemoteException
  {
    if ( model.addRecipe(title,description,ingredients,picfile))
    {
      errorLabel.setValue("Title already exists :( , please choose different one");
    }

  }
  public void cancel()
  {
    vh.openProfile(model.getLoggedProfile());
  }
}
