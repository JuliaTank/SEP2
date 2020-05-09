package client.model;

import shared.transferObjects.Profile;
import shared.util.Subject;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface VegSearchModel extends Subject {
    void report(String txt) throws RemoteException;
    String getNumberOfSubscriptions() throws RemoteException;
    void saveUsername(String username);
    void addRecipe(String recipe) throws RemoteException;
    boolean logIn(String username, String password)
        throws RemoteException, SQLException, FileNotFoundException;
    boolean signUp(String username, String password, File picFile,String description)
        throws FileNotFoundException, SQLException, RemoteException;
    Profile getLoggedProfile();
    Profile getProfile(String username)
        throws FileNotFoundException, SQLException, RemoteException;

}
