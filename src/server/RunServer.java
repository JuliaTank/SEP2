package server;

import server.model.Manager;
import server.network.RMIServerImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class RunServer {
    public static void main(String[] args)
        throws AlreadyBoundException, RemoteException, SQLException
    {
        RMIServerImpl ss = new RMIServerImpl(new Manager());
        ss.startServer();
    }
}
