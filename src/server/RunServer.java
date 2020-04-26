package server;

import server.model.Manager;
import server.network.RMIServerImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer {
    public static void main(String[] args) throws AlreadyBoundException, RemoteException {
        RMIServerImpl ss = new RMIServerImpl(new Manager());
        ss.startServer();
    }
}
