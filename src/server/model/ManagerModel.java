package server.model;

import shared.transferObjects.Notification;
import shared.transferObjects.Recipe;
import shared.transferObjects.Report;
import shared.util.Subject;

public interface ManagerModel extends Subject {
    Report sendReport(Report report);
    Recipe addRecipe(Recipe recipe);
    Notification sendNotification(Notification notification);
}
