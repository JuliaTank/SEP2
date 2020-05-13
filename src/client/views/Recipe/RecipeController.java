package client.views.Recipe;

import client.core.ViewModelFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import shared.transferObjects.Recipe;

import java.io.IOException;
import java.rmi.NotBoundException;

public class RecipeController
{
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


  private RecipeVM vm= ViewModelFactory.getInstance().getRecipeVM();

  public RecipeController() throws IOException, NotBoundException
  {
  }

  public void onUserLink(ActionEvent actionEvent)
  {
  }

  public void onLikeButton(ActionEvent actionEvent)
  {
  }

  public void onCommentButton(ActionEvent actionEvent)
  {
  }

  public void onReportButton(ActionEvent actionEvent)
  {
  }

  public void init(Recipe recipe)
  {
    userLink.textProperty().bind(vm.userLinkProperty());
    ingredientList.cellFactoryProperty().bindBidirectional(vm.ingredientsProperty());
    likeLabel.textProperty().bind(vm.likeLabelProperty());
    commentField.textProperty().bindBidirectional(vm.commentFieldProperty());
    reportField.textProperty().bindBidirectional(vm.reportFieldProperty());
    commentsList.cellFactoryProperty().bind(vm.commentsProperty());

    userLink.setText(recipe.getProfile().getUsername());

    Image image = new Image(recipe.getProfile().getPicFile().toURI().toString());
    Image image2 = new Image("file:heart.png");
    Image image3 = new Image(recipe.getPicFile().toURI().toString());
    userPic.setImage(image);
    likeButton.setEffect(new ImageInput(image2));
    recipePic.setImage(image3);
  }

}
