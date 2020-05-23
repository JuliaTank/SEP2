package client.views.ProfileDemo;

import client.core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import shared.transferObjects.Profile;

import java.io.IOException;

public class ProfileDemoController
{
  @FXML
  public Hyperlink profileLink;
  @FXML
  private ImageView profilePic;

  private Parent root;
  private ViewHandler vh= ViewHandler.getInstance();
  private Profile profile;


  public void init(Parent root, Profile profile)
  {
    this.profile = profile;
    this.root = root;
    root.setStyle("-fx-border-width: 2;" +
        "-fx-border-radius: 5;"+
        "-fx-border-color: black;");
    profileLink.setText(profile.getUsername());
    Image image  =  new Image(profile.getPicFile().toURI().toString());
    profilePic.setImage(image);
  }
  public Parent getRoot()
  {
    return root;
  }

  public void onProfileLink(ActionEvent actionEvent) throws IOException
  {
    vh.openProfile(profile);
  }

}
