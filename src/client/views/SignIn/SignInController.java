package client.views.SignIn;

import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SignInController implements ViewController
{
  @FXML
  private Label errorLabel;
  @FXML
  private PasswordField passwordRepeatField;
  @FXML
  private PasswordField passwordCreationField;
  @FXML
  private TextField usernameCreationField;
  @FXML
  private TextArea descriptionArea;
  @FXML
  private ImageView userPic;

  public void onChooseFileButton(ActionEvent actionEvent)
  {
  }

  public void onSignUp2Button(ActionEvent actionEvent)
  {
  }

  public void onCancelButton(ActionEvent actionEvent)
  {
  }

  @Override public void init()
  {
    Image image = new Image("rabbit.jpg");
    userPic.setImage(image);
  }
}
