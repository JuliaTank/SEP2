package client.network;

import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;
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
    void sendReport(String title, String username, String message)
        throws RemoteException, SQLException;
    boolean addRecipe(String title, String description,String username, ArrayList<String> ingredients, File picfile,byte[] bytes)
        throws IOException, SQLException;
    boolean logIn(String username, String password)
        throws RemoteException, SQLException;
    boolean signUp(String username, String password, File picFile,byte[] bytes,String description)
        throws IOException, SQLException;
    boolean editProfile(String oldUsername,String newUsername, String password,
        File picFile, byte[] bytes,String description,ArrayList<Profile> subs)
        throws IOException, SQLException;
    Profile getProfile(String username) throws IOException, SQLException;
    ArrayList<Profile> getProfiles(String username) throws IOException, SQLException;
    void subscribe(String user, Profile subscriber)
        throws IOException, SQLException;
    void unsubscribe(String user, Profile subscriber)
        throws IOException, SQLException;
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
}
