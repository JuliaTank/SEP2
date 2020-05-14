package client.views.Notification;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.model.VegSearchModel;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferObjects.Recipe;

import java.io.IOException;
import java.rmi.NotBoundException;

public class NotificationVM {

    private StringProperty textAreaProperty;
    private VegSearchModel model;
    private ViewHandler vh = ViewHandler.getInstance();

    public NotificationVM() throws IOException, NotBoundException {
        this.textAreaProperty= new SimpleStringProperty();
        this.model = ModelFactory.getInstance().getModel();
    }
    public void see() throws IOException {
       //vh.openRecipeView(model.getRecipesByTitle());
    }
    public void cancel(){
        vh.closeNotification();
    }

    public StringProperty getAreaProperty() {
        return textAreaProperty;
    }
}
