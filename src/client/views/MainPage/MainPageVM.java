package client.views.MainPage;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.VegSearchModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferObjects.Profile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class MainPageVM {

  private ViewHandler vh= ViewHandler.getInstance();
  private VegSearchModel model = ModelFactory.getInstance().getModel();

  private StringProperty userLink;

  public MainPageVM() throws IOException, NotBoundException
  {
    userLink =  new SimpleStringProperty();
  }

  public StringProperty getUserLink()
  {
    return userLink;
  }

  public void logOut()
  {
    vh.openLogIn();

  }
  public void viewMyProfile()
  {
    vh.openProfile(model.getLoggedProfile());
  }
  public void openProfile()
      throws FileNotFoundException, SQLException, RemoteException
  {
    Profile profile = model.getProfile(userLink.getValue());
    vh.openProfile(profile);
  }
}
