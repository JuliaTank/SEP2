package shared.transferObjects;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Profile implements Serializable {
    private String username;
    private String password;
    private File picFile;
    private String description;
    private ArrayList<Profile>subs;

    public Profile(String username, String password, File picFile, String description, List<Profile> subs)
    {
        this.username=username;
        this.password=password;
        this.picFile=picFile;
        this.description=description;
        this.subs=(ArrayList<Profile>)subs;
    }
    public void applyNotify(String recipeName, String author)
    {
        System.out.println("Author "+author +"recipe name "+recipeName);
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public File getPicFile() {
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
