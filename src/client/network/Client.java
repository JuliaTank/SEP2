package client.network;

import shared.util.Subject;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface Client extends Subject {
    void startClient() throws RemoteException, NotBoundException;
    void report(String txt) throws RemoteException;
    String getNumberOfSubscriptions() throws RemoteException;
    void addRecipe(String recipe) throws RemoteException;
}
