package client.views.Profile;


import client.core.ModelFactory;
import client.model.VegSearchModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfileVM {

    private ObservableList<RecipeDisplay> recipeDisplays;

    private VegSearchModel model;
    private StringProperty subsLabel;

    public ProfileVM() throws IOException, NotBoundException {
        this.model = ModelFactory.getInstance().getModel();
        this.subsLabel=new SimpleStringProperty();
        recipeDisplays = FXCollections.observableArrayList();
    }

    public void addRecipeDisplay()
    {
        RecipeDisplay rd = new RecipeDisplay();
        recipeDisplays.add(rd);
    }
    public ObservableList<RecipeDisplay> getRecipeDisplays()
    {
        return recipeDisplays;
    }

  /*  public void newRecipe()
    {
        //ArrayList<Recipe> recipes =  new ArrayList<>();
    }*/

    public StringProperty getSubsLabel() {
        return subsLabel;
    }

    public void subscribe() throws RemoteException {

        subsLabel.set(model.getNumberOfSubscriptions());
    }
    public void unsubscribe() throws RemoteException {

        subsLabel.set(model.getNumberOfSubscriptions());
    }
    public Profile getLoggedProfile()
    {
       return model.getLoggedProfile();
    }

    public Profile getProfile(String username)
        throws FileNotFoundException, SQLException, RemoteException
    {
        return  model.getProfile(username);
    }

    public class RecipeDisplay{
        private StringProperty userLink = new SimpleStringProperty();

        public StringProperty getUserLink()
        {
            return userLink;
        }
    }
}
