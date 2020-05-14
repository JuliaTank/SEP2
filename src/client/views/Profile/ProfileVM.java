package client.views.Profile;


import client.core.ModelFactory;
import client.model.VegSearchModel;
import client.views.RecipeDemo.RecipeDemoVM;
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

    private ObservableList<RecipeDemoVM> recipeDemoVMS;

    private VegSearchModel model;
    private StringProperty subsLabel;
    private StringProperty username;

    public ProfileVM() throws IOException, NotBoundException {
        this.model = ModelFactory.getInstance().getModel();
        this.subsLabel=new SimpleStringProperty();
        this.username = new SimpleStringProperty();
        recipeDemoVMS = FXCollections.observableArrayList();

    }

    public void addRecipeDisplay(Recipe recipe)

    {
        RecipeDemoVM rd = new RecipeDemoVM(recipe);
        recipeDemoVMS.add(rd);
    }
    public ObservableList<RecipeDemoVM> getRecipeDemoVMS()
    {
        return recipeDemoVMS;
    }
    public ArrayList<Recipe> getRecipes(String username) throws SQLException, RemoteException
    {
        //System.out.println(model.getRecipesByUsername(username.getValue()).get(0).getTitle());
        return model.getRecipesByUsername(username);
    }

    public void newRecipe()
    {
        //ArrayList<Recipe> recipes =  new ArrayList<>();
    }

    public StringProperty getSubsLabel() {
        return subsLabel;
    }

    public StringProperty getUsername()
    {
        return username;
    }

    public void subscribe() throws RemoteException {

    }
    public void unsubscribe() throws RemoteException {


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
    public void delete() throws SQLException, RemoteException
    {
        model.delete();
    }

}
