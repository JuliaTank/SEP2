package client.core;

import client.model.VegSearchModel;
import client.model.VegSearchModelManager;
import client.network.RMIClient;

import java.io.IOException;
import java.rmi.NotBoundException;

public class ModelFactory {

    private static ModelFactory instance;

    private VegSearchModel vegsearchModel;

    private ModelFactory()
    {
    }
    public static synchronized ModelFactory getInstance()
    {
        if(instance== null)
        instance = new ModelFactory();
        return instance;
    }

   public VegSearchModel getModel() throws IOException, NotBoundException
    {
        if(vegsearchModel==null)
            vegsearchModel = new VegSearchModelManager(ClientFactory.getInstance().getClient());
        return vegsearchModel;
    }

}
