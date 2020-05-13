package client.core;

import client.views.Recipe.RecipeVM;
import client.views.ReportAdm.ReportAdmVM;
import client.views.ReportUser.ReportUserVM;
import client.views.logIn.LogInVM;
import client.views.MainPage.MainPageVM;
import client.views.NewRecipe.NewRecipeVM;
import client.views.Notification.NotificationVM;
import client.views.Profile.ProfileVM;
import client.views.SignIn.SignInVM;

import java.io.IOException;
import java.rmi.NotBoundException;

public class ViewModelFactory {
  private static ViewModelFactory instance;

  private LogInVM logInVM;
  private MainPageVM mainPageVM;
  private NewRecipeVM newRecipeVM;
  private NotificationVM notificationVM;
  private ProfileVM profileVM;
  private SignInVM signInVM;
  private ReportAdmVM reportAdmVM;
  private ReportUserVM reportUserVM;
  private RecipeVM recipeVM;

  private ViewModelFactory() throws IOException, NotBoundException
  {
    logInVM = new LogInVM();
    mainPageVM =new MainPageVM();
    newRecipeVM = new NewRecipeVM();
    notificationVM = new NotificationVM();
    profileVM = new ProfileVM();
    signInVM  = new SignInVM();
    reportAdmVM = new ReportAdmVM();
    reportUserVM = new ReportUserVM();
    recipeVM = new RecipeVM();

  }

  public static synchronized ViewModelFactory getInstance()
      throws IOException, NotBoundException
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

  public ReportAdmVM getReportAdmVM() {
    return reportAdmVM;
  }

  public ReportUserVM getReportUserVM() {
    return reportUserVM;
  }

  public RecipeVM getRecipeVM()
  {
    return recipeVM;
  }
}
