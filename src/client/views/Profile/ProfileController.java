package client.views.Profile;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.Recipe.RecipeController;
import client.views.RecipeDemo.RecipeDemoController;
import client.views.ViewController;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

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

  public ProfileController() throws IOException, NotBoundException
  {
  }
  public void onEditButton(ActionEvent actionEvent)
  {
  }
  public void onMainPage(ActionEvent actionEvent) {
    vh.openMainPage();
  }

  public void onNewRecipe(ActionEvent actionEvent) {
    vm.addRecipeDisplay();
  }
 private void OnRecipeAdded(ListChangeListener.Change<? extends ProfileVM.RecipeDisplay> change)
 {
   if(change.next())
   {
     ProfileVM.RecipeDisplay rd = change.getAddedSubList().get(0);

     //delete it later(recipes need to come from DB)
     Recipe recipe = new Recipe("pierogi","miaaaaaaaaaaaaaaaaaaaaaaal",null,new ArrayList<>(),new File("file:carrotLogo.png"));
     //end of delete section
     RecipeDemoController recipeController =vh.getRecipeDisplayPanel(recipe);

     rd.getUserLink().bind(recipeController.recipeLink.textProperty());

     Parent recipeDisplayPanel  =  recipeController.getRoot();
     recipeContainer.getChildren().add(recipeDisplayPanel);
   }
 }

  public void onDeleteButton(ActionEvent actionEvent) {
  }

  public void onSubscribeButton(ActionEvent actionEvent) {
  }
  public void onUnsubscribeButton(ActionEvent actionEvent) {
  }
  @Override public void init(Profile profile)
      throws FileNotFoundException, SQLException, RemoteException
  {
    vm.getRecipeDisplays().addListener(this::OnRecipeAdded);



    username.setText(profile.getUsername());

    Image image = new Image(profile.getPicFile().toURI().toString());
    imgView.setImage(image);
    subsLabel.setText(" "+profile.getSubs().size());
    descriptionArea.setText(profile.getDescription());

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
