package client.core;

import client.model.VegSearchModel;
import client.model.VegSearchModelManager;
import client.network.RMIClient;

import java.io.IOException;
import java.rmi.NotBoundException;

public class ModelFactory {
    private final ClientFactory cf;
    private VegSearchModel vegsearchModel;

    public ModelFactory(ClientFactory cf)
    {
        this.cf = cf;
    }

    public VegSearchModel getChatModel() throws IOException, NotBoundException
    {
        if(vegsearchModel==null)
            vegsearchModel = new VegSearchModelManager((RMIClient)cf.getClient());
        return vegsearchModel;
    }
}
