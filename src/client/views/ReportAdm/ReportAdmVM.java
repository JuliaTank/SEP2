package client.views.ReportAdm;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.model.VegSearchModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferObjects.Recipe;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.rmi.NotBoundException;

public class ReportAdmVM {
    private StringProperty textAreaProperty;
    private VegSearchModel model;
    private StringProperty usernameLabel;
    private ViewHandler vh = ViewHandler.getInstance();

    public ReportAdmVM() throws IOException, NotBoundException {
        this.textAreaProperty=new SimpleStringProperty();
        this.usernameLabel = new SimpleStringProperty();
        this.model= ModelFactory.getInstance().getModel();
        model.addListener("NewReport",this::receiveReport);
        model.addListener("NewUsername",this::setUsername);
    }
   /* public void openRecipe(Recipe recipe)
    {
       model.see(recipe);
    }*/
    public void setUsername(PropertyChangeEvent evt)
    {
        if(usernameLabel.get()!=null)
            usernameLabel.setValue(usernameLabel.get()+"\n"+evt.getNewValue());
        else
            usernameLabel.setValue(evt.getNewValue()+"");
    }
    public void receiveReport(PropertyChangeEvent evt)
    {
        if(textAreaProperty.get()!=null)
           textAreaProperty.setValue(textAreaProperty.get()+"\n"+evt.getNewValue());
        else
            textAreaProperty.setValue(evt.getNewValue()+"");
    }

    public StringProperty getUsernameLabel() {
        return usernameLabel;
    }

    public StringProperty getAreaProperty() {
        return textAreaProperty;
    }
}
