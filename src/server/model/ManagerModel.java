package server.model;

import shared.transferObjects.Notification;
import shared.transferObjects.Report;
import shared.util.Subject;

public interface ManagerModel extends Subject {
    Report sendReport(Report report);
    String addRecipe(String recipe);
    Notification sendNotification(Notification notification);
}
