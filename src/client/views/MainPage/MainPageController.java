package client.views.MainPage;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.model.VegSearchModel;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import shared.transferObjects.Profile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class MainPageController implements ViewController
{
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

  public void onLikeButton(ActionEvent actionEvent)
  {
  }

  public void onCommentButton(ActionEvent actionEvent)
  {
  }

  public void onReportButton(ActionEvent actionEvent) throws IOException, NotBoundException {
    //vm.report();
  }

  public void onSearchButton(ActionEvent actionEvent)
  {
  }

  public void onViewProfileButton(ActionEvent actionEvent)
  {
    vm.viewMyProfile();
  }

  public void onLogoutButton(ActionEvent actionEvent)
  {
    vm.logOut();
  }

  @Override public void init(Profile profile)
  {
    Image image1 = new Image("file:look.png");

    searchButton.setEffect(new ImageInput(image1));

  }
}
