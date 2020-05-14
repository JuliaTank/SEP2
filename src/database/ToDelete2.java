package database;

import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToDelete2 {
    public static void main(String[] args)
            throws SQLException, FileNotFoundException
    {
   /* ArrayList<Profile> subs =  new ArrayList<>();
    subs.add(ProfilesData.getInstance().getProfile("Julia"));
    subs.add(ProfilesData.getInstance().getProfile("Roksanka"));
    //ProfilesData.getInstance().update("Toms","Toms","kotkidwa",file,"jestem toms",subs);
   Profile profile = ProfilesData.getInstance().getProfile("Toms");
    for (int i = 0; i < profile.getSubs().size() ; i++)
    {
      System.out.println(profile.getSubs().get(i).getUsername());
    }*/
        ArrayList<String> ingredients =  new ArrayList<>();
        ingredients.add("churror package");
        File file =  new File("foooodpic.jpg");
      //  RecipesData.getInstance().update("fooood","fooood","cook food",ProfilesData.getInstance().getProfile("roxi"),ingredients,file);
        RecipesData.getInstance().create("churros","fry churros","roxi",ingredients,file);

        Profile profile = ProfilesData.getInstance().getProfile("Toms");
        Recipe recipe =  RecipesData.getInstance().getRecipesByTitle("churros");
        for (int i = 0; i < recipe.getIngredients().size(); i++)
        {
            System.out.println(recipe.getIngredients().get(i));
        }
    }

}
