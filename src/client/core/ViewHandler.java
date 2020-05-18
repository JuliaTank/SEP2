package client.core;

import client.views.MainPage.MainPageController;
import client.views.Notification.NotificationController;
import client.views.ProfileDemo.ProfileDemoController;
import client.views.Recipe.RecipeController;
import client.views.RecipeDemo.RecipeDemoController;
import client.views.ViewController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Popup;
import javafx.stage.Stage;
import shared.transferObjects.Notification;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Queue;

public class ViewHandler {

    private Scene logInScene;
    private Scene signInScene;
    private Scene mainPageScene;
    private Scene inboxScene;
    private Scene profileScene;
    private Scene notificationScene;
    private Stage stage;
    private Stage stageNotification;

    private Scene recipeScene;
    private Scene newRecipeScene;
    private ViewModelFactory vmf;
    private static ViewHandler instance;

    private ViewHandler()
    {
    }
    public static ViewHandler getInstance()
    {
        if(instance==null)
        {
            instance=new ViewHandler();
        }
        return instance;

    }
    public void start() throws IOException, SQLException, NotBoundException
    {
        stage = new Stage();
        stage.getIcons().add(new Image("file:carrotIcon.png"));
        openLogIn();
    }

    public void openLogIn() throws NotBoundException, SQLException, IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/logIn/logIn.fxml"));
        Parent root = loader.load();

        ViewController ctrl = loader.getController();
        ctrl.init(null);
        stage.setTitle("Log in");

        logInScene =  new Scene(root);
        stage.setScene(logInScene);
        stage.show();
            /*Parent root = loadFXML("../views/logIn/logIn.fxml",null);
            logInScene = new Scene(root);

        stage.setTitle("Log in");
        stage.setScene(logInScene);
        stage.show();*/
    }

    public void openSignIn(Profile profile) {
            try {
                Parent root = loadFXML("../views/SignIn/signIn.fxml",profile);


                signInScene = new Scene(root);
            } catch (IOException | NotBoundException | SQLException e) {
                e.printStackTrace();
            }
        stage.setTitle(":)");
        stage.setScene(signInScene);
        stage.show();
    }
    public void openMainPage() {

            try {
                Parent root = loadFXML("../views/MainPage/mainPage.fxml",null);


                mainPageScene = new Scene(root);
            } catch (IOException | NotBoundException | SQLException e) {
                e.printStackTrace();
            }

        stage.setTitle("Main Page");
        stage.setScene(mainPageScene);
        stage.show();
    }

    public void openProfile(Profile profile) {
        System.out.println("profiled opened again!");
            try {
                Parent root = loadFXML("../views/Profile/profile.fxml",profile);
                profileScene = new Scene(root);
            } catch (IOException | NotBoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("catch in open profile");
            }
            stage.setTitle(profile.getUsername());
        stage.setScene(profileScene);
        stage.show();
    }

    public void openNotification(Notification notification) throws IOException, SQLException
    {
        Platform.runLater(() -> {
            stageNotification = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/Notification/notification.fxml"));
            Parent root = null;
            try
            {
                root = loader.load();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            NotificationController ctrl = loader.getController();
            try
            {
                ctrl.init(notification);
            }
            catch (FileNotFoundException | RemoteException | SQLException e)
            {
                e.printStackTrace();
            }
            stageNotification.setTitle("");

            notificationScene =  new Scene(root);
            stageNotification.setScene(notificationScene);
            stageNotification.show();

        });

    }
    public void closeNotification()
    {
        stageNotification.close();
    }

    private Parent loadFXML(String path,Profile profile)
        throws IOException, NotBoundException, SQLException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        //
        ViewController ctrl = loader.getController();
        ctrl.init(profile);
        return root;
    }

    public RecipeDemoController getRecipeDisplayPanel(Recipe recipe)
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/RecipeDemo/recipeDemo.fxml"));
        Parent root = null;
        try
        {
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        RecipeDemoController controller  = loader.getController();

        controller.init(root,recipe);
        return controller;
    }
    public ProfileDemoController getProfileDisplayPanel(Profile profile)
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/ProfileDemo/profileDemo.fxml"));
        Parent root = null;
        try
        {
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        ProfileDemoController controller  = loader.getController();
        controller.init(root,profile);
        return  controller;
    }
   public void openRecipeView(Recipe recipe) throws IOException
   {
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(getClass().getResource("../views/Recipe/recipe.fxml"));
       Parent root = loader.load();

       RecipeController ctrl = loader.getController();
       ctrl.init(recipe);
       stage.setTitle(recipe.getTitle());
       recipeScene =  new Scene(root);
       stage.setScene(recipeScene);
       stage.show();
   }
    public void openNewRecipeView() throws IOException, SQLException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/NewRecipe/newRecipe.fxml"));
        Parent root = loader.load();

        ViewController ctrl = loader.getController();
        ctrl.init(null);
        stage.setTitle("New recipe");

        newRecipeScene =  new Scene(root);
        stage.setScene(newRecipeScene);
        stage.show();
    }


}
