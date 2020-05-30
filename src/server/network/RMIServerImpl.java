package server.network;


import database.ProfilesData;
import database.RecipesData;
import server.model.Manager;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.transferObjects.Notification;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.activation.ActivationGroup_Stub;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class RMIServerImpl implements RMIServer {

    private Manager manager;
    private ArrayList<ClientCallBack> clients;
    private ProfilesData profilesData;
    private RecipesData recipesData;


    public RMIServerImpl(Manager manager) throws SQLException
    {
        this.manager = manager;
        clients = new ArrayList<>();
        profilesData = ProfilesData.getInstance();
        recipesData = RecipesData.getInstance();
    }

    public void startServer()
        throws IOException, AlreadyBoundException, SQLException
    {
      if(profilesData.getProfile("Administrator")==null)
      {
        profilesData.create("Administrator","admin",new File("rabbit.jpg"),null,"I'm Administrator of VegSearch",new ArrayList<>());
      }
        UnicastRemoteObject.exportObject(this,0);
        Registry registry= LocateRegistry.createRegistry(1099);
        registry.bind("Server",this);
    }

    @Override
    public void registerClient(ClientCallBack client)  {
        this.clients.add(client);
        System.out.println("Client was added");
    }

  //............................................................TEST...........................................................
    @Override
    public boolean logIn(String username, String password)
        throws SQLException
    {
      Profile profile  = profilesData.getProfile(username);
        return profile!= null && profile.getPassword().equals(password);
    }
  //............................................................TEST...........................................................
    @Override
    public boolean addRecipe(String title, String description,String username, ArrayList<String> ingredients, File picfile,byte[] bytes)
        throws IOException, SQLException
    {
      if(profilesData.getProfile(username)==null)
      {
        throw new IllegalStateException("username does not exist");
      }
      if (recipesData.getRecipeByTitle(title)==null)
      {
        if(title==null || description==null || username==null || ingredients==null || picfile==null)
          return  false;
        recipesData.create(title, description, username, ingredients, picfile,bytes);
        Notification notification  = new Notification(username,"New recipe",title);
        sendNotification(notification);
        return true;
      }
      else
      return false;
    }
  //............................................................TEST...........................................................
    @Override
    public boolean report(String title, String username, String message)
        throws RemoteException, SQLException
    {
      if(profilesData.getProfile(username)==null)
      {
        throw new IllegalStateException("username doesn't exist");
      }
      if(title==null || username==null || message.length()>70 || recipesData.getRecipeByTitle(title)==null)
      {
        return  false;
      }
      Notification notification = new Notification(username,"New report: "+message,title);
      manager.sendNotification(notification);
      for (int i = 0; i < clients.size(); i++)
      {
        if (clients.get(i).getUsername().equals("Administrator"))
        {
          clients.get(i).receiveNotification(notification);
          break;
        }
      }
      return true;
    }

  //............................................................TEST...........................................................
    @Override public boolean signUp(String username, String password,
        File picFile,byte[] bytes, String description)
        throws SQLException, IOException
    {
      if(username.length()>20)
      {
        return false;
      }
        if(ProfilesData.getInstance().getProfile(username)==null)
        {
          ProfilesData.getInstance()
              .create(username, password, picFile, bytes, description, new ArrayList<>());
          if (ProfilesData.getInstance().getProfile(username).getUsername().equals(username))
          {
            return true;
          }
        }
      return false;
    }
  //............................................................TEST...........................................................
  @Override public boolean editProfile(String oldUsername,String newUsername, String password,
      File picFile,byte[] bytes, String description,ArrayList<Profile> subs)
      throws SQLException, IOException
  {
    if(newUsername.length()>20)
    {
      return false;
    }
    if(profilesData.getProfile(oldUsername)==null)
    {
      throw new IllegalArgumentException("username does not exist");
    }
    //here i'm checking if username which user wants as new one doesn't belong to any other user already
    if (!oldUsername.equals(newUsername) && profilesData.getProfile(newUsername) != null)
    {
    return false;
    }
   else {
      return profilesData.update(oldUsername,newUsername,password,picFile,bytes,description,subs);
   }
  }
  //............................................................TEST...........................................................
  @Override public Profile getProfile(String username)
        throws SQLException
    {
        return profilesData.getProfile(username);
    }
  //............................................................TEST...........................................................
  @Override public ArrayList<Profile> getProfiles(String username)
      throws SQLException, FileNotFoundException, RemoteException
  {
    return profilesData.getProfiles(username);
  }
  //............................................................TEST...........................................................
  private void sendNotification(Notification notification) throws RemoteException, SQLException
  {
    manager.sendNotification(notification);
   Profile profile = profilesData.getProfile(notification.getUsername());
    ArrayList<String> subscribers = new ArrayList<>();
    if (profile != null)
    {
      ArrayList<Profile> subscribersProfiles = profile.getSubs();

      for (Profile subscribersProfile : subscribersProfiles)
      {
        subscribers.add(subscribersProfile.getUsername());
      }
      for (ClientCallBack client : clients)
      {
        for (String subscriber : subscribers)
        {
          if (client.getUsername().equals(subscriber))
          {
            System.out.println("notification send to "+subscriber);
            client.receiveNotification(notification);
          }
        }
      }
    }

  }

  //............................................................TEST...........................................................
  @Override
    public void subscribe(String user, Profile subscriber)
      throws SQLException, IOException
  {

    Profile subscribedProfile = profilesData.getProfile(user);
        ArrayList<Profile>subs=subscribedProfile.getSubs();
        subs.add(profilesData.getProfile(subscriber.getUsername()));
       profilesData.update(subscribedProfile.getUsername(),subscribedProfile.getUsername(),subscribedProfile.getPassword(),
               subscribedProfile.getPicFile(),null,subscribedProfile.getDescription(),subs);
    }
  //............................................................TEST...........................................................
    @Override
    public void unsubscribe(String user, Profile unsubscriber)
        throws IOException, SQLException {
      Profile unsubscribedProfile = profilesData.getProfile(user);
      ArrayList<Profile>subs=unsubscribedProfile.getSubs();
      subs.remove(profilesData.getProfile(unsubscriber.getUsername()));
      profilesData.update(unsubscribedProfile.getUsername(),unsubscribedProfile.getUsername(),unsubscribedProfile.getPassword(),
              unsubscribedProfile.getPicFile(),null,unsubscribedProfile.getDescription(),subs);

    }
//............................................................TEST...........................................................
  @Override public boolean doIsubscribeIt(String user, Profile subscriber)
      throws RemoteException, FileNotFoundException, SQLException
  {
    Profile profile = profilesData.getProfile(user);
    if(profile==null)
    {
      return false;
    }
    ArrayList<Profile>subs=profile.getSubs();

    boolean doI = false;
    for (Profile sub : subs)
    {
      if (sub.equals(subscriber))
      {
        doI = true;
        break;
      }
    }
    System.out.println("server: does "+subscriber.getUsername()+" subscribe "+user+"? :"+doI);
    return doI;
  }
  //............................................................TEST...........................................................
  @Override public boolean delete(String username) throws SQLException
    {
      if(username==null)
      {
        return false;
      }
      else if(profilesData.getProfile(username) ==null)
      {
        throw  new IllegalArgumentException("username does not exist");
      }
        profilesData.delete(username);
        return true;
    }
  //............................................................TEST...........................................................
    @Override public ArrayList<Recipe> getRecipesByIngredient(String ingredient)
        throws SQLException
    {
        return recipesData.getRecipesByIngredient(ingredient);
    }
  //............................................................TEST...........................................................
    @Override public Recipe getRecipeByTitle(String title) throws SQLException
    {
        return recipesData.getRecipeByTitle(title);
    }
  //............................................................TEST...........................................................
    @Override public ArrayList<Recipe> getRecipesByUsername(String username)
        throws SQLException
    {
      if(username==null|| profilesData.getProfile(username)==null)
      {
        throw new IllegalArgumentException("user does not exist");
      }
        return recipesData.getRecipesByAuthor(username);
    }
  //............................................................TEST...........................................................
    @Override public ArrayList<Recipe> getRecipesByTitle(String title)
        throws SQLException
    {
        return recipesData.getRecipesByTitle(title);
    }
  //............................................................TEST...........................................................
  @Override public ArrayList<Recipe> getAllRecipes() throws SQLException
  {
    return recipesData.getAllRecipes();
  }

}
