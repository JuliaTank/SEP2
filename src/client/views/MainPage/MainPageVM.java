package client.views.MainPage;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.model.VegSearchModel;
import client.views.RecipeDemo.RecipeDemoVM;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferObjects.Notification;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainPageVM {

  private ViewHandler vh= ViewHandler.getInstance();
  private VegSearchModel model = ModelFactory.getInstance().getModel();

  private ObservableList<RecipeDemoVM> recipeDemoVMS;
  private StringProperty errorLabel;


  public MainPageVM() throws IOException, NotBoundException, SQLException
  {
    recipeDemoVMS = FXCollections.observableArrayList();
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

  public ArrayList<Recipe> getRecipes() throws SQLException, IOException
  {
    return model.getAllRecipes();
  }

  public void search(String text)
          throws SQLException, IOException
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
  //searching profiles and recipes is handled separately and later displayed as 'RecipeDemo's and 'ProfileDemo's in VBox on the Main Page
  public ArrayList<Profile> getProfilesForSearch(String text)
          throws IOException, SQLException {
    ArrayList<Profile> profiles = model.getProfiles(text);
    return  profiles;
  }

  public StringProperty getErrorLabel()
  {
    return errorLabel;
  }
}
