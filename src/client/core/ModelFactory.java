package client.core;

import client.model.VegSearchModel;
import client.model.VegSearchModelManager;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;

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

   public VegSearchModel getModel()
       throws IOException, NotBoundException, SQLException
   {
        if(vegsearchModel==null)
            vegsearchModel = new VegSearchModelManager(ClientFactory.getInstance().getClient());
        return vegsearchModel;
    }

}
