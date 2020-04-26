package server.network;

import client.network.Client;
import server.model.Manager;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIServerImpl implements RMIServer {

    private Manager manager;
    private ArrayList<ClientCallBack> chatClients;
    private int numberOfClients=0;


    public RMIServerImpl(Manager manager)
    {
        this.manager = manager;
        chatClients=new ArrayList<>();
    }
    public void startServer() throws RemoteException, AlreadyBoundException {
        UnicastRemoteObject.exportObject(this,0);
        Registry registry= LocateRegistry.createRegistry(1099);
        registry.bind("Server",this);
    }

    @Override
    public void registerClient(Client client) throws RemoteException {

    }

    @Override
    public void unregisterClient(Client client) throws RemoteException {

    }

    @Override
    public void loginClient() throws RemoteException {

    }

    @Override
    public void logoutClient() throws RemoteException {

    }

    @Override
    public void getNumberOfSubscriptions() throws RemoteException {

    }

    @Override
    public void addRecipe(String recipe) throws RemoteException {

    }
}
