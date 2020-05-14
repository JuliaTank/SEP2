package client.views.MainPage;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.model.VegSearchModel;
import client.views.Notification.NotificationController;
import client.views.Profile.ProfileVM;
import client.views.RecipeDemo.RecipeDemoVM;
import client.views.ReportUser.ReportUserController;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Popup;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class MainPageVM {

  private ViewHandler vh= ViewHandler.getInstance();
  private VegSearchModel model = ModelFactory.getInstance().getModel();
  private ObservableList<RecipeDemoVM> recipeDemoVMS;
  private StringProperty userLink;


  public MainPageVM() throws IOException, NotBoundException
  {
    userLink =  new SimpleStringProperty();
    recipeDemoVMS = FXCollections.observableArrayList();
  }
  public void addRecipeDisplay()
  {
   // RecipeDemoVM rd= new RecipeDemoVM();
    //recipeDemoVMS.add(rd);
  }

  public ObservableList<RecipeDemoVM> getRecipeDemoVMS()
  {
    return recipeDemoVMS;
  }


  public void logOut()
  {
    vh.openLogIn();
  }

  public void viewMyProfile()
  {
    vh.openProfile(model.getLoggedProfile());
  }
  public void openProfile()
      throws FileNotFoundException, SQLException, RemoteException
  {
    Profile profile = model.getProfile(userLink.getValue());
    vh.openProfile(profile);
  }
  public void showNotification() throws IOException, NotBoundException, SQLException {
    {
      Popup popup = new Popup();
      NotificationController controller = new NotificationController();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Notification/notification.fxml"));
      loader.setController(controller);
      popup.getContent().add((Parent)loader.load());
    }
  }
  public void report() throws IOException, NotBoundException, SQLException {
    Popup popup = new Popup();
    ReportUserController controller = new ReportUserController();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ReportUser/reportUser.fxml"));
    loader.setController(controller);
    popup.getContent().add((Parent)loader.load());
  }
}
