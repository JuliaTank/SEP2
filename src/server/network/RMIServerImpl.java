package server.network;

import database.ProfilesData;
import database.RecipesData;
import server.model.Manager;
import shared.networking.ClientCallBack;
import shared.networking.RMIServer;
import shared.transferObjects.Profile;

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
    public void registerClient(ClientCallBack client) throws RemoteException {
        this.clients.add(client);
        System.out.println("Client was added");
    }

    @Override
    public void unregisterClient(ClientCallBack client) throws RemoteException {
        this.clients.remove(client);
        System.out.println("Client was removed");
    }

    @Override
    public boolean logIn(String username, String password)
        throws RemoteException, SQLException
    {
      Profile profile  = profilesData.getProfile(username);

        return profile!= null && profile.getPassword().equals(password);
    }

    @Override
    public void logOut() throws RemoteException {
       // numberOfClients--;
    }

    @Override
    public String getNumberOfSubscriptions(Profile profile) throws RemoteException, SQLException {

        return profilesData.getProfile(profile.getUsername()).getSubs().size()+"";
    }

    @Override
    public void addRecipe(String recipe, ClientCallBack client) throws RemoteException {

    }

    @Override
    public void report(String txt, ClientCallBack client) throws RemoteException {
        //
        manager.report(txt);

    }

    @Override public boolean signUp(String username, String password,
        File picFile, String description)
        throws SQLException, FileNotFoundException,RemoteException
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
        throws SQLException, FileNotFoundException, RemoteException
    {
        return profilesData.getProfile(username);
    }
}
