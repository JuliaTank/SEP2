package client.model;

import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;
import shared.transferObjects.Report;
import shared.util.Subject;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface VegSearchModel extends Subject {
    void report(Report report) throws RemoteException;
    void saveUsername(String username);
    void subscribe(String subscriber) throws RemoteException, FileNotFoundException, SQLException;
    void unsubscribe(String subscriber) throws RemoteException, FileNotFoundException, SQLException;
    boolean addRecipe(String title, String description, ArrayList<String> ingredients, File picfile) throws RemoteException;
    boolean logIn(String username, String password)
        throws RemoteException, SQLException, FileNotFoundException;
    boolean signUp(String username, String password, File picFile,String description)
        throws FileNotFoundException, SQLException, RemoteException;
    Profile getLoggedProfile();
    Profile getProfile(String username)
        throws FileNotFoundException, SQLException, RemoteException;
    void setRecipes(ArrayList<Recipe> recipes);
    void delete() throws SQLException, RemoteException;

    ArrayList<Recipe> getRecipesByIngredient(String ingredient)
        throws SQLException, RemoteException;
    Recipe getRecipeByTitle(String title) throws SQLException, RemoteException;
    ArrayList<Recipe> getRecipesByUsername(String username)
        throws SQLException, RemoteException;
    ArrayList<Recipe> getRecipesByTitle(String title)
        throws SQLException, RemoteException;

    void see();
}
