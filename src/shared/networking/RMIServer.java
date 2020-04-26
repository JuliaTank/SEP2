package shared.networking;

import client.network.Client;

import java.rmi.RemoteException;

public interface RMIServer {
    void registerClient(Client client) throws RemoteException;
    void unregisterClient(Client client) throws RemoteException; //dodac do class diagramu
    void loginClient() throws RemoteException;
    void logoutClient() throws RemoteException;
    void getNumberOfSubscriptions() throws RemoteException;
    void addRecipe(String recipe) throws RemoteException;

}
