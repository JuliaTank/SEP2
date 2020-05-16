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
import java.sql.SQLException;

public class NotificationVM {

    private StringProperty titleLabelProperty;
    private StringProperty contentLabelProperty;

    private VegSearchModel model;
    private ViewHandler vh = ViewHandler.getInstance();


    public NotificationVM() throws IOException, NotBoundException, SQLException
    {
        this.titleLabelProperty= new SimpleStringProperty();
        this.contentLabelProperty =  new SimpleStringProperty();
        this.model = ModelFactory.getInstance().getModel();
    }
    public void see(String recipeTitle) throws IOException, SQLException
    {
       vh.openRecipeView(model.getRecipeByTitle(recipeTitle));
    }
    public void cancel(){
        vh.closeNotification();
    }

    public StringProperty getTitleLabelProperty() {
        return titleLabelProperty;
    }

    public StringProperty getContentLabelProperty()
    {
        return contentLabelProperty;
    }
}
