package client.model;

import client.network.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class VegSearchModelManager implements VegSearchModel {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Client client;
    private String username;

    public VegSearchModelManager(Client client) throws RemoteException, NotBoundException {
        this.client=client;

        client.startClient();
        //client.addListener("");
    }

    @Override
    public void report(String txt) throws RemoteException {
        client.report(txt);
    }

    @Override
    public String getNumberOfSubscriptions() throws RemoteException {
        return client.getNumberOfSubscriptions();
    }

    @Override
    public void saveUsername(String username) {
        this.username=username;
    }

    @Override
    public void addRecipe(String recipe) throws RemoteException {
        client.addRecipe(recipe);
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
