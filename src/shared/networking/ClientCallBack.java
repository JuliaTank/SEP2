package shared.networking;

import shared.transferObjects.Notification;
import shared.transferObjects.Profile;
import shared.transferObjects.Report;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientCallBack extends Remote {
    void sendNotification(Notification notification) throws RemoteException;
    void updateReport(Report report) throws RemoteException;
}
