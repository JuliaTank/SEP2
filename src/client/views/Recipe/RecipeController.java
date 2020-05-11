package client.views.Recipe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import shared.transferObjects.Recipe;

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
  public ListView CommentsList;


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

  }

}
