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
        clients = new ArrayList<>();
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




    @Override
    public boolean addRecipe(String title, String description,String username, ArrayList<String> ingredients, File picfile)
        throws FileNotFoundException, SQLException, RemoteException
    {
      if (recipesData.getRecipeByTitle(title)==null)
      {
        recipesData.create(title, description, username, ingredients, picfile);
        Notification notification  = new Notification(username,"New recipe",title);
        sendNotification(notification);
        manager.addRecipe(notification);
        return true;
      }
      else
      return false;
    }

    @Override
    public void report(String title, String username, String message)  {
      Notification notification = new Notification(username,"New report :"+message,title);
      manager.sendReport(notification);
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

  @Override public boolean editProfile(String oldUsername,String newUsername, String password,
      File picFile, String description,ArrayList<Profile> subs)
      throws SQLException, FileNotFoundException, RemoteException
  {
    //here i'm checking if username which user wants as new one doesn't belong to any other user already
    if(!oldUsername.equals(newUsername) && profilesData.getProfile(newUsername)!=null)
      return  false;
   else {
      profilesData.update(oldUsername,newUsername,password,picFile,description,subs);
      return true;
   }
  }

  @Override public Profile getProfile(String username)
        throws SQLException
    {
        return profilesData.getProfile(username);
    }

  @Override public ArrayList<Profile> getProfiles(String username)
      throws SQLException, FileNotFoundException, RemoteException
  {
    return profilesData.getProfiles(username);
  }

  private void sendNotification(Notification notification) throws RemoteException, SQLException
  {
   Profile profile = profilesData.getProfile(notification.getUsername());
    ArrayList<String> subscribers = new ArrayList<>();
    ArrayList<Profile> subscribersProfiles = profile.getSubs();
    for (Profile subscribersProfile : subscribersProfiles)
    {
      subscribers.add(subscribersProfile.getUsername());
    }
    for (ClientCallBack client : clients)
    {
      for (String s : subscribers)
      {
        if (client.getUsername().equals(s))
        {
          client.receiveNotification(notification);
        }
      }
    }
  }


  @Override
    public void subscribe(String user, Profile subscriber) throws SQLException, FileNotFoundException {

    Profile subscribedProfile = profilesData.getProfile(user);
        ArrayList<Profile>subs=subscribedProfile.getSubs();
        subs.add(profilesData.getProfile(subscriber.getUsername()));
       profilesData.update(subscribedProfile.getUsername(),subscribedProfile.getUsername(),subscribedProfile.getPassword(),subscribedProfile.getPicFile(),subscribedProfile.getDescription(),subs);
    }

    @Override
    public void unsubscribe(String user, Profile unsubscriber) throws FileNotFoundException, SQLException {
      Profile unsubscribedProfile = profilesData.getProfile(user);
      ArrayList<Profile>subs=unsubscribedProfile.getSubs();
      subs.remove(profilesData.getProfile(unsubscriber.getUsername()));
      profilesData.update(unsubscribedProfile.getUsername(),unsubscribedProfile.getUsername(),unsubscribedProfile.getPassword(),unsubscribedProfile.getPicFile(),unsubscribedProfile.getDescription(),subs);

    }

  @Override public boolean doIsubscribeIt(String user, Profile subscriber)
      throws RemoteException, FileNotFoundException, SQLException
  {
    Profile profile = profilesData.getProfile(user);
    ArrayList<Profile>subs=profile.getSubs();
    System.out.println(subs.size()+" "+subs.toString());
    boolean bbb = false;
    for (int i = 0; i < subs.size(); i++)
    {
      if (subs.get(i).equals(subscriber))
      {
        bbb = true;
      }
    }
    System.out.println("server does "+subscriber.getUsername()+" subscribe "+user+"? :"+bbb);
    return bbb;
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

  @Override public ArrayList<Recipe> getAllRecipes() throws SQLException
  {
    return recipesData.getAllRecipes();
  }

}
