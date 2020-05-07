package shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface RMIServer extends Remote {
    void registerClient(ClientCallBack client) throws RemoteException;
    void unregisterClient(ClientCallBack client) throws RemoteException; //dodac do class diagramu
    boolean logIn(String username, String password)
        throws RemoteException, SQLException;
    void logOut() throws RemoteException;
    String getNumberOfSubscriptions() throws RemoteException;
    void addRecipe(String recipe,ClientCallBack client) throws RemoteException;
    void report(String txt,ClientCallBack client) throws RemoteException;

}
