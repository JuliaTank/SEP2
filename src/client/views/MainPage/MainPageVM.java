package client.views.MainPage;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.model.VegSearchModel;
import client.views.Notification.NotificationController;
import client.views.Profile.ProfileVM;
import client.views.RecipeDisplay;
import client.views.ReportUser.ReportUserController;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Popup;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class MainPageVM {

  private ViewHandler vh= ViewHandler.getInstance();
  private VegSearchModel model = ModelFactory.getInstance().getModel();
  private ObservableList<RecipeDisplay> recipeDisplays;
  private StringProperty userLink;


  public MainPageVM() throws IOException, NotBoundException
  {
    userLink =  new SimpleStringProperty();
    recipeDisplays = FXCollections.observableArrayList();
  }
  public void addRecipeDisplay()
  {
    RecipeDisplay rd= new  RecipeDisplay();
    recipeDisplays.add(rd);
  }

  public ObservableList<RecipeDisplay> getRecipeDisplays()
  {
    return recipeDisplays;
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
