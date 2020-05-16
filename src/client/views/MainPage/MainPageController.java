package client.views.MainPage;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.VegSearchModel;
import client.views.Profile.ProfileVM;
import client.views.ProfileDemo.ProfileDemoController;
import client.views.ProfileDemo.ProfileDemoVM;
import client.views.RecipeDemo.RecipeDemoController;
import client.views.RecipeDemo.RecipeDemoVM;
import client.views.ViewController;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainPageController implements ViewController
{
  @FXML
  private Label errorLabel;
  @FXML
  private VBox recipeContainer;
  @FXML
  private Button searchButton;
  @FXML
  private TextField searchField;


  private MainPageVM vm= ViewModelFactory.getInstance().getMainPageVM();
  private ViewHandler vh = ViewHandler.getInstance();
 // private VegSearchModel model = ModelFactory.getInstance().getModel();


  public MainPageController()
      throws IOException, NotBoundException, SQLException
  {
  }

  public void onSearchButton(ActionEvent actionEvent)
      throws NotBoundException, SQLException, IOException
  {
    if(searchField.getText().isEmpty())
    {
      errorLabel.setText("No results found");
    }
    else
    {
      recipeContainer.getChildren().clear();
      vm.search(searchField.getText());
      ArrayList<Profile> profiles = vm.getProfilesForSearch(searchField.getText());
      for (Profile profile : profiles)
      {
        ProfileDemoVM vm = new ProfileDemoVM(profile);
        ProfileDemoController ctrl = vh.getProfileDisplayPanel(vm.getProfile());
        vm.getProfileLink().bind(ctrl.profileLink.textProperty());

        Parent root = ctrl.getRoot();
        recipeContainer.getChildren().add(root);
      }
    }
  }

  private  void OnRecipeAdded(ListChangeListener.Change<? extends RecipeDemoVM> change)
  {
    if(change.next())
    {
      RecipeDemoVM vm = change.getAddedSubList().get(0);

      RecipeDemoController recipeController = vh.getRecipeDisplayPanel(vm.getRecipe());

      vm.getRecipeLink().bind(recipeController.recipeLink.textProperty());

      Parent recipeDisplayPanel  =  recipeController.getRoot();
      recipeContainer.getChildren().add(recipeDisplayPanel);
    }
  }
  public void onViewProfileButton(ActionEvent actionEvent)
  {
    vm.viewMyProfile();
  }

  public void onLogoutButton(ActionEvent actionEvent)
      throws NotBoundException, SQLException, IOException
  {
    vm.logOut();
  }

  @Override public void init(Profile profile)
      throws SQLException, RemoteException
  {
    errorLabel.textProperty().bindBidirectional(vm.getErrorLabel());
    Image image1 = new Image("file:look.png");
    searchButton.setEffect(new ImageInput(image1));
    vm.getRecipeDemoVMS().addListener(this::OnRecipeAdded);

    for (Recipe recipe :vm.getRecipes() )
    {
      vm.addRecipeDisplay(recipe);
    }

  }
}
