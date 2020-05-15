package client.views.MainPage;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.model.VegSearchModel;
import client.views.Notification.NotificationController;
import client.views.Profile.ProfileVM;
import client.views.RecipeDemo.RecipeDemoVM;
import client.views.ReportUser.ReportUserController;
import client.views.ViewController;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainPageVM {

  private ViewHandler vh= ViewHandler.getInstance();
  private VegSearchModel model = ModelFactory.getInstance().getModel();

  private ObservableList<RecipeDemoVM> recipeDemoVMS;
  private StringProperty errorLabel;


  public MainPageVM() throws IOException, NotBoundException
  {
    recipeDemoVMS = FXCollections.observableArrayList();
    errorLabel = new SimpleStringProperty();
  }
  public void addRecipeDisplay(Recipe recipe)
  {
    RecipeDemoVM rd= new RecipeDemoVM(recipe);
    recipeDemoVMS.add(rd);
  }

  public ObservableList<RecipeDemoVM> getRecipeDemoVMS()
  {
    return recipeDemoVMS;
  }

  public void logOut() throws IOException, SQLException, NotBoundException
  {
    vh.openLogIn();
  }

  public void viewMyProfile()
  {
    vh.openProfile(model.getLoggedProfile());
  }

  public ArrayList<Recipe> getRecipes() throws SQLException, RemoteException
  {
    return model.getAllRecipes();
  }

  public void showNotification() throws IOException, NotBoundException, SQLException {
    {
      Popup popup = new Popup();
      NotificationController controller = new NotificationController();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Notification/notification.fxml"));
      loader.setController(controller);
      popup.getContent().add((Parent)loader.load());
    }
  }
  public void report() throws IOException, NotBoundException, SQLException {
   vh.openNotification();
  }
  public void search(String text)
      throws FileNotFoundException, SQLException, RemoteException
  {
    ArrayList<Recipe> recipes  = model.getRecipesByTitle(text);
    ArrayList<Recipe> recipesByIngredients = model.getRecipesByIngredient(text);
    for (Recipe recipe : recipes)
    {
      RecipeDemoVM rd = new RecipeDemoVM(recipe);
      recipeDemoVMS.add(rd);
    }
    for (Recipe recipe : recipesByIngredients)
    {
      RecipeDemoVM rd = new RecipeDemoVM(recipe);
      recipeDemoVMS.add(rd);
    }

  }
  public ArrayList<Profile> getProfilesForSearch(String text)
      throws FileNotFoundException, SQLException, RemoteException
  {
    ArrayList<Profile> profiles = model.getProfiles(text);
    return  profiles;
  }
  public StringProperty getErrorLabel()
  {
    return errorLabel;
  }
}
