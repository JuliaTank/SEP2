package client.model;

import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;
import shared.transferObjects.Report;
import shared.util.Subject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface VegSearchModel extends Subject {
    void report(String title, String message)
        throws RemoteException, SQLException;
    void saveUsername(String username);
    void subscribe(String user) throws RemoteException, FileNotFoundException, SQLException;
    void unsubscribe(String user) throws RemoteException, FileNotFoundException, SQLException;
    boolean doIsubscribeIt(String user) throws RemoteException, FileNotFoundException, SQLException;
    boolean addRecipe(String title, String description, ArrayList<String> ingredients, File picfile)
        throws RemoteException, FileNotFoundException, SQLException;
    boolean logIn(String username, String password)
            throws IOException, SQLException;
    boolean signUp(String username, String password, File picFile,String description)
        throws FileNotFoundException, SQLException, RemoteException;
    boolean editProfile(String oldUsername,String newUsername, String password,
        File picFile, String description,ArrayList<Profile> subs)
        throws FileNotFoundException, SQLException, RemoteException;
    Profile getLoggedProfile();
    Profile getProfile(String username) throws IOException, SQLException;
    ArrayList<Profile> getProfiles(String username) throws IOException, SQLException;
    void setRecipes(ArrayList<Recipe> recipes);
    void delete() throws SQLException, RemoteException;

    ArrayList<Recipe> getRecipesByIngredient(String ingredient)
            throws SQLException, IOException;
    Recipe getRecipeByTitle(String title) throws SQLException, IOException;
    ArrayList<Recipe> getRecipesByUsername(String username)
            throws SQLException, IOException;
    ArrayList<Recipe> getRecipesByTitle(String title)
            throws SQLException, IOException;
    ArrayList<Recipe> getAllRecipes() throws SQLException, IOException;


}
