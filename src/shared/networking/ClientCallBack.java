package shared.networking;

import shared.transferObjects.Notification;
import shared.transferObjects.Profile;
import shared.transferObjects.Report;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallBack extends Remote {
    void receiveNotification(Notification notification) throws RemoteException;
    String getUsername() throws RemoteException;

}
