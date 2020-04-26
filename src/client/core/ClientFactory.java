package client.core;

import client.network.Client;
import client.network.RMIClient;

import java.rmi.RemoteException;

public class ClientFactory {
    private Client client;
    private  ClientFactory()
    {

    }
    public Client getClient() throws RemoteException
    {
        if(client ==null)
        {
            client = new RMIClient();
        }
        return client;
    }
}
