package server.network;

import client.network.Client;
import database.ProfilesData;
import database.RecipesData;
import server.model.Manager;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.transferObjects.Notification;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;
import shared.transferObjects.Report;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class RMIServerImpl implements RMIServer {

    private Manager manager;
    private ArrayList<ClientCallBack> clients;
    private int numberOfClients=0;
    private ProfilesData profilesData;
    private RecipesData recipesData;


    public RMIServerImpl(Manager manager) throws SQLException
    {
        this.manager = manager;
        clients=new ArrayList<>();
        profilesData = ProfilesData.getInstance();
        recipesData = RecipesData.getInstance();
    }
    public void startServer() throws RemoteException, AlreadyBoundException {
        UnicastRemoteObject.exportObject(this,0);
        Registry registry= LocateRegistry.createRegistry(1099);
        registry.bind("Server",this);
    }

    @Override
    public void registerClient(ClientCallBack client)  {
        this.clients.add(client);
        System.out.println("Client was added");
    }

   /* @Override
    public void unregisterClient(ClientCallBack client)  {
        this.clients.remove(client);
        System.out.println("Client was removed");
    }*/

    @Override
    public boolean logIn(String username, String password)
        throws SQLException
    {
      Profile profile  = profilesData.getProfile(username);

        return profile!= null && profile.getPassword().equals(password);
    }

   /* @Override
    public void logOut() throws RemoteException {
       // numberOfClients--;
    }

*/
    @Override
    public boolean addRecipe(String title, String description,String username, ArrayList<String> ingredients, File picfile)
        throws FileNotFoundException, SQLException
    {
      if (recipesData.getRecipeByTitle(title)==null)
      {
        recipesData.create(title, description, username, ingredients, picfile);
        return true;
      }
      else
      return false;
    }

    @Override
    public void report(Report report, ClientCallBack client)  {
        //
        manager.sendReport(report);

    }

    @Override public boolean signUp(String username, String password,
        File picFile, String description)
        throws SQLException, FileNotFoundException
    {
        if(ProfilesData.getInstance().getProfile(username)!=null)
        {
            return  false;
        }
        ProfilesData.getInstance().create(username,password,picFile,description,new ArrayList<>());
       if(ProfilesData.getInstance().getProfile(username).getUsername().equals(username))
       {
           return  true;
       }
       else return false;
    }

    @Override public Profile getProfile(String username)
        throws SQLException
    {
        return profilesData.getProfile(username);
    }

    @Override
    public void subscribe(Profile subscriber, Profile profile){
       profile.getSubs().add(subscriber);
    }

    @Override
    public void unsubscribe(Profile subscriber, Profile profile) {
        profile.getSubs().remove(subscriber);
    }

    @Override
    public void sendNotification(Notification notification, ClientCallBack subscriber)  {
        manager.sendNotification(notification);
        updateSubscribers(notification,subscriber);

    }

    @Override public void delete(String username) throws SQLException
    {
        profilesData.delete(username);
    }

    @Override public ArrayList<Recipe> getRecipesByIngredient(String ingredient)
        throws SQLException
    {
        return recipesData.getRecipesByIngredient(ingredient);
    }

    @Override public Recipe getRecipeByTitle(String title) throws SQLException
    {
        return recipesData.getRecipeByTitle(title);
    }

    @Override public ArrayList<Recipe> getRecipesByUsername(String username)
        throws SQLException
    {
      System.out.println("server works "+ recipesData.getRecipesByAuthor("Julia"));
     // System.out.println("server works "+ recipesData.getRecipesByAuthor(username).toString());
        return recipesData.getRecipesByAuthor(username);
    }

    @Override public ArrayList<Recipe> getRecipesByTitle(String title)
        throws SQLException
    {
        return recipesData.getRecipesByTitle(title);
    }

    private void updateSubscribers(Notification notification, ClientCallBack unsubscriber)
    {
        for(ClientCallBack client: clients)
        {
            if(client.equals(unsubscriber)) continue;
            try
            {
                client.updateNotification(notification);
            }
            catch (RemoteException e)
            {
                e.printStackTrace();
            }
        }
    }

}
