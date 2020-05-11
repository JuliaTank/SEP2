package server.model;

import shared.transferObjects.Notification;
import shared.transferObjects.Report;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Manager implements ManagerModel{

    private PropertyChangeSupport support= new PropertyChangeSupport(this);

    @Override
    public Report sendReport(Report report) {
        support.firePropertyChange("NewReport",null,report);
        return report;
    }

    @Override
    public String addRecipe(String recipe) {
        support.firePropertyChange("NewRecipe",null,recipe);
        return recipe;
    }

    @Override
    public Notification sendNotification(Notification notification) {
        support.firePropertyChange("NewNotification",null, notification);
        return notification;
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
