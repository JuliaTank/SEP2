package client.views.Profile;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import shared.transferObjects.Profile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class ProfileController implements ViewController
{
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
