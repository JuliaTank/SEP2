package client.views.MainPage;

import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainPageController implements ViewController
{
  @FXML
  private Button searchButton;
  @FXML
  private TextField searchField;
  @FXML
  private ListView ingredientList;
  @FXML
  private ImageView recipePic;
  @FXML
  private Button likeButton;
  @FXML
  private Label likeLabel;
  @FXML
  private TextField commentField;
  @FXML
  private TextField reportField;
  @FXML
  private ListView CommentsList;
  @FXML
  private ImageView userPic;
  @FXML
  private Hyperlink userLink;


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

  public void onSearchButton(ActionEvent actionEvent)
  {
  }

  public void onViewProfileButton(ActionEvent actionEvent)
  {
  }

  public void onLogoutButton(ActionEvent actionEvent)
  {
  }

  @Override public void init()
  {
    Image image = new Image("rabbit.jpg");
    Image image1 = new Image("look.png");
    Image image2 = new Image("heart.png");
    Image image3 = new Image("carrotLogo.png");
    userPic.setImage(image);
    searchButton.setEffect(new ImageInput(image1));
    likeButton.setEffect(new ImageInput(image2));
    recipePic.setImage(image3);

  }
}
