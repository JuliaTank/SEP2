package server.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Manager implements ManagerModel{

    private PropertyChangeSupport support= new PropertyChangeSupport(this);

    @Override
    public String report(String txt) {
        support.firePropertyChange("NewNotification",null,txt);
        return txt;
    }

    @Override
    public String addRecipe(String recipe) {
        support.firePropertyChange("NewNotification",null,recipe);
        return recipe;
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
