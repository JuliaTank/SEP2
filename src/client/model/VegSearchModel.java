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
    String getNumberOfSubscriptions() throws RemoteException;
    void saveUsername(String username);
    void subscribe(Profile subscriber, Profile profile) throws RemoteException;
    void unsubscribe(Profile subscriber, Profile profile) throws RemoteException;
    void addRecipe(String recipe) throws RemoteException;
    boolean logIn(String username, String password)
        throws RemoteException, SQLException, FileNotFoundException;
    boolean signUp(String username, String password, File picFile,String description)
        throws FileNotFoundException, SQLException, RemoteException;
    Profile getLoggedProfile();
    Recipe getRecipesByTitle(String searchedTitle) throws  RemoteException;
    Recipe getRecipesByAuthor(String searchedUsername) throws  RemoteException;
    Recipe getRecipesByIngredient(String searchedIngredient) throws  RemoteException;
    Profile getProfile(String username)
        throws FileNotFoundException, SQLException, RemoteException;
    void setRecipes(ArrayList<Recipe> recipes);

}
