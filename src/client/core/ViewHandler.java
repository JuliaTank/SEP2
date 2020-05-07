package client.core;

import client.views.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.Queue;

public class ViewHandler {

    private Scene logInScene;
    private Scene signInScene;
    private Scene mainPageScene;
    private Scene inboxScene;
    private Scene profileScene;
    private Stage stage;
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
        if (logInScene == null) {
            try {
                Parent root = loadFXML("../views/LogIn/LogIn.fxml");

                stage.setTitle("Log in ");
                logInScene = new Scene(root);
            } catch (IOException | NotBoundException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(logInScene);
        stage.show();
    }

    public void openSignIn() {
        if (signInScene == null) {
            try {
                Parent root = loadFXML("../views/SignIn/SignIn.fxml");

                stage.setTitle("Sign in ");
                signInScene = new Scene(root);
            } catch (IOException | NotBoundException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(signInScene);
        stage.show();
    }
    public void mainPage() {
        if (mainPageScene == null) {
            try {
                Parent root = loadFXML("../views/MainPage/mainPage.fxml");

                stage.setTitle("Main Page");
                mainPageScene = new Scene(root);
            } catch (IOException | NotBoundException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(mainPageScene);
        stage.show();
    }

    public void openInbox() {
        if (inboxScene == null) {
            try {
                Parent root = loadFXML("../views/Inbox/inbox.fxml");

                stage.setTitle("Inbox");
                inboxScene = new Scene(root);
            } catch (IOException | NotBoundException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(inboxScene);
        stage.show();
    }
    public void openProfile() {
        if (profileScene == null) {
            try {
                Parent root = loadFXML("../views/Profile/profile.fxml");

                stage.setTitle("Profile");
                profileScene = new Scene(root);
            } catch (IOException | NotBoundException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(profileScene);
        stage.show();
    }

    private Parent loadFXML(String path) throws IOException, NotBoundException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        ViewController ctrl = loader.getController();
        ctrl.init();
        return root;
    }




}
