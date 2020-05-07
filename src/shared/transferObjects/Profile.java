package shared.transferObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable {
    private String username;
    private String password;
    private String picFile;
    private String description;
    private ArrayList<Profile>subs;

    public Profile(String username, String password, String picFile, String description, ArrayList<Profile> subs)
    {
        this.username=username;
        this.password=password;
        this.picFile=picFile;
        this.description=description;
        this.subs=new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPicFile() {
        return picFile;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Profile> getSubs() {
        return subs;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", picFile='" + picFile + '\'' +
                ", description='" + description + '\'' +
                ", subs=" + subs +
                '}';
    }
}
