package shared.networking;

import shared.transferObjects.Notification;
import shared.transferObjects.Profile;
import shared.transferObjects.Report;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface RMIServer extends Remote {
    void registerClient(ClientCallBack client) throws RemoteException;
    void unregisterClient(ClientCallBack client) throws RemoteException;
    boolean logIn(String username, String password)
        throws RemoteException, SQLException;
    void logOut() throws RemoteException;
    void addRecipe(String recipe,ClientCallBack client) throws RemoteException;
    void report(Report report, ClientCallBack client) throws RemoteException;
    boolean signUp(String username, String password,File picFile, String description) throws SQLException, FileNotFoundException, RemoteException;
    Profile getProfile(String username)throws SQLException, FileNotFoundException, RemoteException;
    void subscribe(Profile subscriber, Profile profile) throws RemoteException;
    void unsubscribe(Profile subscriber, Profile profile) throws RemoteException;
    void sendNotification(Notification notification, ClientCallBack subscriber) throws RemoteException;
    void delete(String username) throws SQLException;



}
