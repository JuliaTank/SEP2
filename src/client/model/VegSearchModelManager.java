package client.model;

import client.network.Client;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;
import shared.transferObjects.Report;

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
    private Recipe recipe;

    public VegSearchModelManager(Client client) throws RemoteException, NotBoundException {
        this.client=client;

        client.startClient();
        //client.addListener();
    }

    @Override
    public void report(Report report) throws RemoteException {
        client.sendReport(report);
    }

    @Override
    public void saveUsername(String username) {
        this.username=username;
    }

    @Override
    public void subscribe(String subscriber) throws FileNotFoundException, RemoteException, SQLException {
        client.subscribe(subscriber,loggedProfile);
    }

    @Override
    public void unsubscribe(String subscriber) throws RemoteException, FileNotFoundException, SQLException {
        client.unsubscribe(subscriber,loggedProfile);
    }

    @Override
    public boolean addRecipe(String title, String description, ArrayList<String> ingredients, File picfile) throws RemoteException {
       return client.addRecipe(title,description,loggedProfile.getUsername(),ingredients,picfile);
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


    @Override
    public Profile getProfile(String username)
        throws FileNotFoundException, SQLException, RemoteException
    {
        return client.getProfile(username);
    }

    @Override
    public void setRecipes(ArrayList<Recipe> recipes) {

    }

    @Override public void delete() throws SQLException, RemoteException
    {
        client.delete(loggedProfile.getUsername());
    }

    @Override public ArrayList<Recipe> getRecipesByIngredient(String ingredient)
        throws SQLException, RemoteException
    {
        return client.getRecipesByIngredient(ingredient);
    }

    @Override public Recipe getRecipeByTitle(String title)
        throws SQLException, RemoteException
    {
        return client.getRecipeByTitle(title);
    }

    @Override public ArrayList<Recipe> getRecipesByUsername(String username)
        throws SQLException, RemoteException
    {
        return client.getRecipesByUsername(username);
    }

    @Override public ArrayList<Recipe> getRecipesByTitle(String title)
        throws SQLException, RemoteException
    {
        return client.getRecipesByTitle(title);
    }

    @Override
    public void see() {

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
