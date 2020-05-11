package client.views.MainPage;

import client.core.ViewHandler;
import client.model.VegSearchModel;
import client.views.Notification.NotificationController;
import client.views.ReportUser.ReportUserController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Popup;
import shared.transferObjects.Profile;


import java.io.IOException;
import java.rmi.NotBoundException;

public class MainPageVM {
  private VegSearchModel model;
  private ViewHandler vh= ViewHandler.getInstance();


  public void logOut()
  {
    vh.openLogIn();

  }
  public void viewMyProfile(Profile profile)
  {
    vh.openProfile(profile);
  }
  public void showNotification() throws IOException, NotBoundException {
    if()
    {
      Popup popup = new Popup();
      NotificationController controller = new NotificationController();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Notification/notification.fxml"));
      loader.setController(controller);
      popup.getContent().add((Parent)loader.load());
    }
  }
  public void report() throws IOException, NotBoundException {
    Popup popup = new Popup();
    ReportUserController controller = new ReportUserController();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ReportUser/reportUser.fxml"));
    loader.setController(controller);
    popup.getContent().add((Parent)loader.load());
  }
}
