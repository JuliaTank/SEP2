package client.network;

import shared.transferObjects.Notification;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;
import shared.transferObjects.Report;
import shared.util.Subject;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Client extends Subject {
    void startClient() throws RemoteException, NotBoundException;
    void sendReport(String title, String username, String message) throws RemoteException;
    void sendNotification(Notification notification) throws RemoteException;
    boolean addRecipe(String title, String description,String username, ArrayList<String> ingredients, File picfile) throws RemoteException;
    boolean logIn(String username, String password)
        throws RemoteException, SQLException;
    boolean signUp(String username, String password,
        File picFile, String description)
        throws FileNotFoundException, SQLException, RemoteException;
    Profile getProfile(String username) throws FileNotFoundException, SQLException, RemoteException;
    ArrayList<Profile> getProfiles(String username) throws FileNotFoundException, SQLException, RemoteException;
    void subscribe(String user, Profile subscriber) throws RemoteException, FileNotFoundException, SQLException;
    void unsubscribe(String user, Profile subscriber) throws RemoteException, FileNotFoundException, SQLException;
    void delete(String username) throws SQLException, RemoteException;
    Recipe getRecipeByTitle(String title) throws SQLException, RemoteException;
    ArrayList<Recipe> getRecipesByUsername(String username)
        throws SQLException, RemoteException;
    ArrayList<Recipe> getRecipesByTitle(String title)
        throws SQLException, RemoteException;
    ArrayList<Recipe> getRecipesByIngredient(String ingredient) throws RemoteException, SQLException;
    ArrayList<Recipe> getAllRecipes() throws SQLException, RemoteException;
}
