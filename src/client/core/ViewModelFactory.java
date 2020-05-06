package client.core;

import client.views.LogIn.LogInVM;
import client.views.MainPage.MainPageVM;
import client.views.NewRecipe.NewRecipeVM;
import client.views.Notification.NotificationVM;
import client.views.Profile.ProfileVM;
import client.views.SignIn.SignInVM;

public class ViewModelFactory {
  private static ViewModelFactory instance;

  private LogInVM logInVM;
  private MainPageVM mainPageVM;
  private NewRecipeVM newRecipeVM;
  private NotificationVM notificationVM;
  private ProfileVM profileVM;
  private SignInVM signInVM;

  private ViewModelFactory()
  {
    logInVM = new LogInVM();
    mainPageVM =new MainPageVM();
    newRecipeVM = new NewRecipeVM();
    notificationVM = new NotificationVM();
    profileVM = new ProfileVM();
    signInVM  = new SignInVM();

  }

  public static synchronized ViewModelFactory getInstance()
  {
    if(instance==null)
    {
      instance = new ViewModelFactory();
    }
    return instance;
  }

  public LogInVM getLogInVM()
  {
    return logInVM;
  }

  public MainPageVM getMainPageVM()
  {
    return mainPageVM;
  }

  public NewRecipeVM getNewRecipeVM()
  {
    return newRecipeVM;
  }

  public NotificationVM getNotificationVM()
  {
    return notificationVM;
  }

  public ProfileVM getProfileVM()
  {
    return profileVM;
  }

  public SignInVM getSignInVM()
  {
    return signInVM;
  }
}
