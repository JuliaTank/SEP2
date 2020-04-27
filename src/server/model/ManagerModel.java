package server.model;

import shared.util.Subject;

public interface ManagerModel extends Subject {
    String report(String txt);
    String addRecipe(String recipe);
}
