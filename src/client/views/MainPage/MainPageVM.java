package client.views.MainPage;

import client.core.ViewHandler;

public class MainPageVM {

  private ViewHandler vh= ViewHandler.getInstance();


  public void logOut()
  {
    vh.openLogIn();
  }
  public void viewMyProfile()
  {
    vh.openProfile();
  }
}
