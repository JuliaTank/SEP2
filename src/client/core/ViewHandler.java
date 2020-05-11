package client.core;

import client.views.MainPage.MainPageController;
import client.views.Recipe.RecipeController;
import client.views.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shared.transferObjects.Profile;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.Queue;

public class ViewHandler {

    private Scene logInScene;
    private Scene signInScene;
    private Scene mainPageScene;
    private Scene inboxScene;
    private Scene profileScene;
    private Scene reportUserScene;
    private Scene reportAdmScene;
    private Stage stage;
    private Stage stageNotification;
    private ViewModelFactory vmf;
    private Queue<ViewModelFactory> vmfQueue;
    //what is it for guys??
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
    public void start()
    {
        stage = new Stage();
        openLogIn();
    }

    public void openLogIn() {

                try {
                Parent root = loadFXML("../views/logIn/logIn.fxml",null);

                stage.setTitle("Log in ");
                logInScene = new Scene(root);
            } catch (IOException | NotBoundException | SQLException e) {
                e.printStackTrace();
            }

        stage.setScene(logInScene);
        stage.show();
    }

    public void openSignIn() {
        if (signInScene == null) {
            try {
                Parent root = loadFXML("../views/SignIn/signIn.fxml",null);

                stage.setTitle("Sign in ");
                signInScene = new Scene(root);
            } catch (IOException | NotBoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(signInScene);
        stage.show();
    }
    public void closeProfile()
    {

    }
    public void openMainPage() {
        if (mainPageScene == null) {
            try {
                Parent root = loadFXML("../views/MainPage/mainPage.fxml",null);

                stage.setTitle("Main Page");
                mainPageScene = new Scene(root);
            } catch (IOException | NotBoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(mainPageScene);
        stage.show();
    }

    public void openInbox() {
        if (inboxScene == null) {
            try {
                Parent root = loadFXML("../views/Inbox/inbox.fxml",null);

                stage.setTitle("Inbox");
                inboxScene = new Scene(root);
            } catch (IOException | NotBoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(inboxScene);
        stage.show();
    }
    public void openProfile(Profile profile) {

            try {
                Parent root = loadFXML("../views/Profile/profile.fxml",profile);

                stage.setTitle("Profile");
                profileScene = new Scene(root);
            } catch (IOException | NotBoundException | SQLException e) {
                e.printStackTrace();
            }
        stage.setScene(profileScene);
        stage.show();
    }
    public void openReportUser() {
        if (logInScene == null) {
            try {
                Parent root = loadFXML("../views/ReportUser/reportUser.fxml",null);

                stage.setTitle("Report recipe");
                logInScene = new Scene(root);
            } catch (IOException | NotBoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(reportUserScene);
        stage.show();
    } public void openReportAdm() {
        if (logInScene == null) {
            try {
                Parent root = loadFXML("../views/ReportAdm/reportAdm.fxml",null);

                stage.setTitle("New Report");
                logInScene = new Scene(root);
            } catch (IOException | NotBoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(reportAdmScene);
        stage.show();
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

    public RecipeController getRecipeDisplayPanel()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/Recipe/recipe.fxml"));
        Parent root = null;
        try
        {
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        RecipeController controller  = loader.getController();
        controller.init(root);
        return  controller;
    }



}
