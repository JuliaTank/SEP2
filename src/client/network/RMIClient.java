package client.network;

import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.transferObjects.Notification;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;
import shared.transferObjects.Report;

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
import java.util.ArrayList;
import java.util.List;

public class RMIClient implements Client, ClientCallBack {
    private PropertyChangeSupport support= new PropertyChangeSupport(this);
    private RMIServer server;
    private Profile profile;
    private Notification notification;
    private Report report;
    private String username;

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
    public void sendReport(String title, String username, String message) throws RemoteException {
        server.report(title, username, message);
    }

    @Override
    public boolean addRecipe(String title, String description,String username, ArrayList<String> ingredients, File picfile) throws RemoteException {
        try
        {
          return   server.addRecipe(title, description, username, ingredients, picfile);
        }
        catch (RemoteException | FileNotFoundException | SQLException e)
        {
            throw new RemoteException();
        }
    }

    @Override public boolean logIn(String username, String password)
        throws RemoteException, SQLException
    {
      boolean temp = server.logIn(username,password);
      if(temp)
      {
        this.username = username;
      }
      return  temp;
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

    @Override public ArrayList<Profile> getProfiles(String username)
        throws FileNotFoundException, SQLException, RemoteException
    {
        return server.getProfiles(username);
    }

    @Override
    public void subscribe(String user, Profile subscriber) throws RemoteException, FileNotFoundException, SQLException {
        server.subscribe(user,subscriber);
    }

    @Override
    public void unsubscribe(String user, Profile subscriber) throws RemoteException, FileNotFoundException, SQLException {
        server.unsubscribe(user,subscriber);
    }

    @Override public void delete(String username)
        throws SQLException, RemoteException
    {
        server.delete(username);
    }

    @Override public ArrayList<Recipe> getRecipesByIngredient(String ingredient)
        throws SQLException, RemoteException
    {
        return server.getRecipesByIngredient(ingredient);
    }

    @Override public ArrayList<Recipe> getAllRecipes()
        throws SQLException, RemoteException
    {
        return server.getAllRecipes();
    }

    @Override public Recipe getRecipeByTitle(String title)
        throws SQLException, RemoteException
    {
        return server.getRecipeByTitle(title);
    }

    @Override public ArrayList<Recipe> getRecipesByUsername(String username)
        throws SQLException, RemoteException
    {
        System.out.println("client works "+server.getRecipesByUsername(username).toString());
        System.out.println(server.getRecipesByUsername("Julia"));
        return server.getRecipesByUsername(username);
    }

    @Override
    public ArrayList<Recipe> getRecipesByTitle(String title)
        throws SQLException, RemoteException
    {
        return server.getRecipesByTitle(title);
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }

    @Override
    public void sendNotification(Notification notification) throws RemoteException {

    }

  @Override public String getUsername()
  {
    return username;
  }

}
