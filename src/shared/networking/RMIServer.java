package shared.networking;

import shared.transferObjects.Notification;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;
import shared.transferObjects.Report;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RMIServer extends Remote {
    void registerClient(ClientCallBack client) throws RemoteException;
   // void unregisterClient(ClientCallBack client) throws RemoteException;
    boolean logIn(String username, String password)
        throws RemoteException, SQLException;
    //void logOut() throws RemoteException;
    boolean addRecipe(String title, String description,String username, ArrayList<String> ingredients, File picfile)
        throws RemoteException, FileNotFoundException, SQLException;
    void report(String title, String username, String message) throws RemoteException;
    boolean signUp(String username, String password,File picFile, String description) throws SQLException, FileNotFoundException, RemoteException;
    Profile getProfile(String username)throws SQLException, FileNotFoundException, RemoteException;
    ArrayList<Profile> getProfiles(String username)throws SQLException, FileNotFoundException, RemoteException;
    void delete(String username) throws SQLException,RemoteException;
    ArrayList<Recipe> getRecipesByIngredient(String ingredient)
        throws SQLException,RemoteException;
    Recipe getRecipeByTitle(String title) throws SQLException,RemoteException;
    ArrayList<Recipe> getRecipesByUsername(String username) throws SQLException,RemoteException;
    ArrayList<Recipe> getRecipesByTitle(String title) throws SQLException,RemoteException;
    ArrayList<Recipe> getAllRecipes() throws SQLException,RemoteException;
    void subscribe(String subscriber, Profile profile) throws RemoteException, SQLException, FileNotFoundException;
    void unsubscribe(String subscriber, Profile profile) throws RemoteException, FileNotFoundException, SQLException;





}
