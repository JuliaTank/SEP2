package shared.transferObjects;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    private String title;
    private String description;
    private Profile profile;
    private ArrayList<String>ingredients=new ArrayList<>();
    private File picFile;

    public Recipe(String title, String description, Profile profile, ArrayList<String> ingredients, File picFile)
    {
        this.title=title;
        this.description=description;
        this.profile=profile;
        this.ingredients=ingredients;
        this.picFile=picFile;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Profile getProfile() {
        return profile;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public File getPicFile() {
        return picFile;
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

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public Profile getProfile()
    {
        return profile;
    }

    public ArrayList<String> getIngredients()
    {
        return ingredients;
    }

    public File getPicFile()
    {
        return picFile;
    }
}
