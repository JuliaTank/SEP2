package client.model;

import client.network.Client;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class VegSearchModelManager implements VegSearchModel {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Client client;
    private String username;

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
    public String getNumberOfSubscriptions(Profile profile) throws RemoteException {
        return client.getNumberOfSubscriptions(profile);
    }

    @Override
    public void saveUsername(String username) {
        this.username=username;
    }

    @Override
    public void signUp(String username, String password, String picFile, String description) {

    }

    @Override
    public void addRecipe(String recipe) throws RemoteException {
        client.addRecipe(recipe);
    }

    @Override
    public void subscribe(Profile subscriber, Profile profile) {

    }

    @Override
    public void unsubscribe(Profile subscriber, Profile profile) {

    }

    @Override public boolean logIn(String username, String password)
        throws RemoteException, SQLException
    {
        return client.logIn(username,password);
    }

    @Override
    public void logOut() {

    }

    @Override
    public void search(String searchWord) {

    }

    @Override
    public boolean save(String title, String description, Profile profile, ArrayList<String> ingredients, String picFile) {
        return false;
    }

    @Override
    public void see(Recipe recipe) {

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
