package database;

import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToDelete
{
  public static void main(String[] args)
      throws SQLException, FileNotFoundException
  {


 /* ArrayList<Profile> subs =  new ArrayList<>();
    subs.add(ProfilesData.getInstance().getProfile("Julia"));
    subs.add(ProfilesData.getInstance().getProfile("Roksanka"));
    File file =  new File("Tomspic.jpg");

    ProfilesData.getInstance().update("Toms","Toms","kotkidwa",file,"jestem toms",subs);*/

    System.out.println(ProfilesData.getInstance().getProfile("Julia").getDescription());

    /*  for (int i = 0; i < profile.getSubs().size() ; i++)
    {
      System.out.println(profile.getSubs().get(i).getUsername());
    }


/*
    ArrayList<String> ingredients =  new ArrayList<>();
    ingredients.add("solly lolly");
    */


   //RecipesData.getInstance().update("solly lolly","solly lolly","freeze solly lolly",ProfilesData.getInstance().getProfile("Julia"),ingredients,file);

/*
    for (int i = 0; i <profiles.size() ; i++)
    {
      System.out.println(profiles.get(i).getUsername());
    }*/

  }
}
