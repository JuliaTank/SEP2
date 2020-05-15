package shared.transferObjects;

import java.io.Serializable;

public class Notification implements Serializable {
    private String username;
    private String message;
    private String recipeTitle;

    public Notification(String username, String message,String recipeTitle) {
        this.username=username;
        this.message = message;
        this.recipeTitle = recipeTitle;
    }
    public String getMessage()
    {
        return message;
    }

    public String getUsername()
    {
        return username;
    }

    public String getRecipeTitle()
    {
        return recipeTitle;
    }

    @Override
    public String toString() {
        return "Report{" +
                "username='" + username + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
