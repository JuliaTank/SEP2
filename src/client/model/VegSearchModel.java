package client.model;

import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;
import shared.util.Subject;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface VegSearchModel extends Subject {
    void report(String txt) throws RemoteException;
    String getNumberOfSubscriptions(Profile profile) throws RemoteException;
    void saveUsername(String username);
    void signUp(String username, String password, String picFile, String description);
    void addRecipe(String recipe) throws RemoteException;
    void subscribe(Profile subscriber, Profile profile);
    void unsubscribe(Profile subscriber, Profile profile);
    boolean logIn(String username, String password) throws RemoteException, SQLException;
    void logOut();
    void search(String searchWord);
    boolean save(String title, String description, Profile profile, ArrayList<String> ingredients, String picFile);
    void see(Recipe recipe);

}
