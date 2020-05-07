package shared.transferObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    private String title;
    private String description;
    private Profile profile;
    private ArrayList<String>ingredients=new ArrayList<>();
    private String picFile;

    public Recipe(String title, String description, Profile profile, ArrayList<String> ingredients, String picFile)
    {
        this.title=title;
        this.description=description;
        this.profile=profile;
        this.ingredients=ingredients;
        this.picFile=picFile;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", profile=" + profile +
                ", ingredients=" + ingredients +
                ", picFile='" + picFile + '\'' +
                '}';
    }
}
