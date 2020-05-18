package client.network;

import shared.transferObjects.Notification;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;
import shared.transferObjects.Report;
import shared.util.Subject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Client extends Subject {
    void startClient() throws RemoteException, NotBoundException;
    void sendReport(String title, String username, String message) throws RemoteException;
    boolean addRecipe(String title, String description,String username, ArrayList<String> ingredients, File picfile)
        throws RemoteException, FileNotFoundException, SQLException;
    boolean logIn(String username, String password)
        throws RemoteException, SQLException;
    boolean signUp(String username, String password, File picFile, String description)
        throws FileNotFoundException, SQLException, RemoteException;
    boolean editProfile(String oldUsername,String newUsername, String password,
        File picFile, String description,ArrayList<Profile> subs)
        throws FileNotFoundException, SQLException, RemoteException;
    Profile getProfile(String username) throws IOException, SQLException;
    ArrayList<Profile> getProfiles(String username) throws IOException, SQLException;
    void subscribe(String user, Profile subscriber) throws RemoteException, FileNotFoundException, SQLException;
    void unsubscribe(String user, Profile subscriber) throws RemoteException, FileNotFoundException, SQLException;
    boolean doIsubscribeIt(String user, Profile subscriber) throws RemoteException, FileNotFoundException, SQLException;
    void delete(String username) throws SQLException, RemoteException;
    Recipe getRecipeByTitle(String title) throws SQLException, IOException;
    ArrayList<Recipe> getRecipesByUsername(String username)
            throws SQLException, IOException;
    ArrayList<Recipe> getRecipesByTitle(String title)
            throws SQLException, IOException;
    ArrayList<Recipe> getRecipesByIngredient(String ingredient) throws IOException, SQLException;
    ArrayList<Recipe> getAllRecipes() throws SQLException, IOException;
    File getPicFile(byte[] imgBytes, String username) throws IOException, SQLException;
   // File getPicFileForRecipe(byte[] imgBytes, String username) throws IOException, SQLException;
}
