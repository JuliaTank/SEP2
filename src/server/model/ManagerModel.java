package server.model;

import shared.transferObjects.Notification;
import shared.util.Subject;

public interface ManagerModel extends Subject {
    Notification sendNotification(Notification notification);
}
