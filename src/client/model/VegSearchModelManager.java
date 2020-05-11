package client.model;

import client.network.Client;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class VegSearchModelManager implements VegSearchModel {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Client client;
    private String username;
    private Profile loggedProfile;

    public VegSearchModelManager(Client client) throws RemoteException, NotBoundException {
        this.client=client;

        client.startClient();
        //client.addListener();
    }

    @Override
    public void report(String txt) throws RemoteException {
        client.report(txt);
    }

    @Override
    public String getNumberOfSubscriptions() throws RemoteException {
        return null;//client.getNumberOfSubscriptions();
    }

    @Override
    public void saveUsername(String username) {
        this.username=username;
    }

    @Override
    public void addRecipe(String recipe) throws RemoteException {
        client.addRecipe(recipe);
    }

    @Override public boolean logIn(String username, String password)
        throws RemoteException, SQLException, FileNotFoundException
    {
        boolean logged = client.logIn(username,password);
        if(logged)
        {
            setLoggedProfile(client.getProfile(username));
        }
        return logged;
    }

    public Profile getLoggedProfile()
    {
        return loggedProfile;
    }


    @Override public Profile getProfile(String username)
        throws FileNotFoundException, SQLException, RemoteException
    {
        return client.getProfile(username);
    }

    @Override public void setRecipes(ArrayList<Recipe> recipes)
    {

    }

    private void setLoggedProfile(Profile profile)
    {
        loggedProfile = profile;
    }
    @Override public boolean signUp(String username, String password,
        File picFile, String description)
        throws FileNotFoundException, SQLException, RemoteException
    {
       return client.signUp(username, password, picFile,description);
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
