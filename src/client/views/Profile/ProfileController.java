package client.views.Profile;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.Recipe.RecipeController;
import client.views.RecipeDemo.RecipeDemoController;
import client.views.RecipeDemo.RecipeDemoVM;
import client.views.ViewController;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ProfileController implements ViewController
{
  @FXML
  private VBox recipeContainer;
  @FXML
  private Button subscribeButton;
  @FXML
  private TextArea descriptionArea;
  @FXML
  private Button newRecipeButton;
  @FXML
  private Button deleteButton;
  @FXML
  private Button editButton;
  @FXML
  private Text username;
  @FXML
  private Label subsLabel;
  @FXML
  private ImageView imgView;


  private ProfileVM vm = ViewModelFactory.getInstance().getProfileVM();
  private Profile profile;
  private ViewHandler vh = ViewHandler.getInstance();

  public ProfileController() throws IOException, NotBoundException, SQLException
  {
  }
  public void onEditButton(ActionEvent actionEvent)
  {
    vh.openSignIn(profile);
  }
  public void onMainPage(ActionEvent actionEvent) {
    vh.openMainPage();
  }

  public void onNewRecipe(ActionEvent actionEvent)
      throws IOException, SQLException
  {
    vm.newRecipe();
  }
 private void OnRecipeAdded(ListChangeListener.Change<? extends RecipeDemoVM> change)
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
  public void onDeleteButton(ActionEvent actionEvent)
      throws SQLException, IOException, NotBoundException
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Are you sure?");
    alert.setHeaderText("Are you sure you want to delete your profile? All data related to it will be gone");

    Optional<ButtonType> result = alert.showAndWait();
    if(result.get() ==ButtonType.OK)
    {
      vm.delete();
      vh.openLogIn();
    }
    else
    {
      alert.close();
    }

  }
  public void onSubscribeButton(ActionEvent actionEvent) throws RemoteException, FileNotFoundException, SQLException {
   if(subscribeButton.getText().equals("Subscribe"))
   {
     vm.subscribe();
     subscribeButton.setText("Unsubscribe");
   }
   else
   {
     vm.unsubscribe();
     subscribeButton.setText("Subscribe");
   }
  }
  @Override public void init(Profile profile)
          throws IOException, SQLException {
    this.profile = profile;
    username.textProperty().bindBidirectional(vm.getUsername());
    vm.getRecipeDemoVMS().addListener(this::OnRecipeAdded);

    for (Recipe recipe :vm.getRecipes(profile.getUsername()) )
    {
      vm.addRecipeDisplay(recipe);
      System.out.println("recipe added: "+ recipe.getTitle());
    }

    username.setText(profile.getUsername());

    Image image = new Image(profile.getPicFile().toURI().toString());
    imgView.setImage(image);
    subsLabel.setText(" "+profile.getSubs().size());
    descriptionArea.setText(profile.getDescription());
    if(vm.doIsubscribeIt())
    {
      subscribeButton.setText("Unsubscribe");
    }

    if(vm.getLoggedProfile().getUsername().equals(profile.getUsername()))
    {
      subscribeButton.setVisible(false);
    }
    else
    {
      deleteButton.setVisible(false);
      editButton.setVisible(false);
      newRecipeButton.setVisible(false);
    }
  }
}
