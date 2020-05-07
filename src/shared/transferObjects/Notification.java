package shared.transferObjects;

import java.io.Serializable;

public class Notification implements Serializable {
    private String username;
    private String message;

    public Notification(String username, String message) {
        this.username=username;

        this.message = message;
    }

    @Override
    public String toString() {
        return "Report{" +
                "username='" + username + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
