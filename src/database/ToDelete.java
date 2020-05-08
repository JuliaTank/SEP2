package database;

import shared.transferObjects.Profile;

import java.sql.SQLException;

public class ToDelete
{
  public static void main(String[] args) throws SQLException
  {
    ProfilesData pd = ProfilesData.getInstance();
   Profile p = pd.getProfile("Roksana");
    System.out.println(p.getPassword());

  }
}
