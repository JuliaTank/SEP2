package client.model;

import shared.util.Subject;

import java.rmi.RemoteException;

public interface VegSearchModel extends Subject {
    void report(String txt) throws RemoteException;
    String getNumberOfSubscriptions() throws RemoteException;
    void saveUsername(String username);
    void addRecipe(String recipe) throws RemoteException;

}
