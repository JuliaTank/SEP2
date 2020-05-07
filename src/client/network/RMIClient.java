package client.network;

import shared.networking.ClientCallBack;
import shared.networking.RMIServer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIClient implements Client, ClientCallBack {
    private PropertyChangeSupport support= new PropertyChangeSupport(this);
    private RMIServer server;
    private String message = "";
    public RMIClient() throws RemoteException
    {
        UnicastRemoteObject.exportObject(this,0);
    }
    @Override
    public void startClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost",1099);
        server=(RMIServer)registry.lookup("Server");
        server.registerClient(this);
    }

    @Override
    public void report(String txt) throws RemoteException {
        try
        {
            server.report(txt, null);
        }
        catch (RemoteException e)
        {
            throw new RemoteException("couldn't contact client");
        }
    }

    @Override
    public String getNumberOfSubscriptions() throws RemoteException {
        return server.getNumberOfSubscriptions();
    }

    @Override
    public void addRecipe(String recipe) throws RemoteException {
        try
        {
            server.addRecipe(recipe, null);
        }
        catch (RemoteException e)
        {
            throw new RemoteException("couldn't contact client");
        }
    }

    @Override public boolean logIn(String username, String password)
    {
        return server.logIn(username,password);
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
