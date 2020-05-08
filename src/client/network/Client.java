package client.network;

import shared.transferObjects.Profile;
import shared.util.Subject;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface Client extends Subject {
    void startClient() throws RemoteException, NotBoundException;
    void report(String txt) throws RemoteException;
    String getNumberOfSubscriptions(Profile profile) throws RemoteException;
    void addRecipe(String recipe) throws RemoteException;
    boolean logIn(String username, String password)
        throws RemoteException, SQLException;
}
