package client.views.Recipe;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import shared.transferObjects.Recipe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class RecipeController
{
  @FXML
  public Label errorLabel;
  @FXML
  public Label titleLabel;
  @FXML
  public ImageView recipePic;
  @FXML
  public ListView ingredientList;
  @FXML
  public ImageView userPic;
  @FXML
  public Hyperlink userLink;
  @FXML
  public Button likeButton;
  @FXML
  public Label likeLabel;
  @FXML
  public TextField commentField;
  @FXML
  public TextField reportField;
  @FXML
  public ListView commentsList;
  @FXML
  private TextArea descriptionArea;

  private RecipeVM vm= ViewModelFactory.getInstance().getRecipeVM();

  public RecipeController() throws IOException, NotBoundException, SQLException
  {
  }

  public void onUserLink(ActionEvent actionEvent)
      throws FileNotFoundException, SQLException, RemoteException
  {
    vm.onUserLink();
  }

  public void onLikeButton(ActionEvent actionEvent)
  {

  }

  public void onCommentButton(ActionEvent actionEvent)
  {

  }

  public void onReportButton(ActionEvent actionEvent) throws RemoteException
  {
    if(!reportField.getText().equals(null))
    {
      vm.report(titleLabel.getText(), userLink.getText(), reportField.getText());
    }
    else
    {
      errorLabel.setText("you cannot send empty report, please write something interesting;))");
    }
  }

  public void init(Recipe recipe)
  {
    userLink.textProperty().bindBidirectional(vm.userLinkProperty());
    ingredientList.cellFactoryProperty().bindBidirectional(vm.ingredientsProperty());
    likeLabel.textProperty().bind(vm.likeLabelProperty());
    commentField.textProperty().bindBidirectional(vm.commentFieldProperty());
    reportField.textProperty().bindBidirectional(vm.reportFieldProperty());
    commentsList.cellFactoryProperty().bindBidirectional(vm.commentsProperty());
    userLink.setText(recipe.getProfile().getUsername());
    titleLabel.setText(recipe.getTitle());
    ObservableList<String> ol = FXCollections.observableArrayList();

    for (int i = 0; i < recipe.getIngredients().size(); i++)
    {
      ol.add(recipe.getIngredients().get(i));
    }

    ingredientList.setItems(ol);
     descriptionArea.setText(recipe.getDescription());
    Image image = new Image(recipe.getProfile().getPicFile().toURI().toString());
    Image image2 = new Image("file:heart.png");
    Image image3 = new Image(recipe.getPicFile().toURI().toString());
    userPic.setImage(image);
    likeButton.setEffect(new ImageInput(image2));
    recipePic.setImage(image3);
  }

  public void onMyProfile(ActionEvent actionEvent)
  {
    vm.onProfile();
  }

  public void onMainPage(ActionEvent actionEvent)
  {
   vm.onMainPage();
  }
}
