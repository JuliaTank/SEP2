package server.model;

import shared.transferObjects.Notification;
import shared.transferObjects.Recipe;
import shared.transferObjects.Report;
import shared.util.Subject;

public interface ManagerModel extends Subject {
    Notification sendReport(Notification notification);
    Notification addRecipe(Notification notification);
    Notification sendNotification(Notification notification);
}
