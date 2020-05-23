package server.model;

import shared.transferObjects.Notification;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Manager implements ManagerModel{

    private PropertyChangeSupport support= new PropertyChangeSupport(this);


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
