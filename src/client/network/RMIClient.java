package client.network;

import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.transferObjects.Profile;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

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

    @Override public String getNumberOfSubscriptions(Profile profile)
        throws RemoteException
    {
        return null;
    }

   /* @Override
    public String getNumberOfSubscriptions() throws RemoteException {
        return server.getNumberOfSubscriptions();
    }
*/
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
        throws RemoteException, SQLException
    {
        return server.logIn(username,password);
    }

    @Override public boolean signUp(String username, String password,
        File picFile, String description)
        throws FileNotFoundException, SQLException, RemoteException
    {
        return server.signUp(username,password,picFile,description);
    }

    @Override public Profile getProfile(String username)
        throws FileNotFoundException, SQLException, RemoteException
    {
        return server.getProfile(username);
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
