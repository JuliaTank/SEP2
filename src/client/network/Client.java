package client.network;

import shared.transferObjects.Notification;
import shared.transferObjects.Profile;
import shared.transferObjects.Report;
import shared.util.Subject;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface Client extends Subject {
    void startClient() throws RemoteException, NotBoundException;
    void sendReport(Report report) throws RemoteException;
    void sendNotification(Notification notification) throws RemoteException;
    String getNumberOfSubscriptions(Profile profile) throws RemoteException;
    void addRecipe(String recipe) throws RemoteException;
    boolean logIn(String username, String password)
        throws RemoteException, SQLException;
    boolean signUp(String username, String password,
        File picFile, String description)
        throws FileNotFoundException, SQLException, RemoteException;
    Profile getProfile(String username) throws FileNotFoundException, SQLException, RemoteException;
    void subscribe(Profile subscriber, Profile profile) throws RemoteException;
    void unsubscribe(Profile subscriber, Profile profile) throws RemoteException;
    void delete(String username) throws SQLException;
}
