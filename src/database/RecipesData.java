package database;

import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import java.sql.*;
import java.util.ArrayList;

public class RecipesData {

    private ProfilesData profilesData;
    private static RecipesData instance;

    private RecipesData() throws SQLException
    {
        DriverManager.registerDriver(new org.postgresql.Driver());
        profilesData = ProfilesData.getInstance();
    }
    public static synchronized RecipesData getInstance() throws SQLException
    {
        if(instance == null)
        {
            instance = new RecipesData();
        }
        return instance;
    }
    private Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                "Roksanka2601");
    }
    public Recipe create(String title, String description, String username, ArrayList<String> ingredients, String picFile) throws SQLException
    {
        try (Connection connection = getConnection())
        {
            PreparedStatement statement= connection.prepareStatement("INSERT INTO \"VegSearch\".Recipe(title,description,username,ingredients,picFile) VALUES (?,?,?,?,?);");
            statement.setString(1,title);
            statement.setString(2,description);
            statement.setString(3,username);
            Array array  = connection.createArrayOf("varchar",ingredients.toArray());
            statement.setArray(4, array);
            statement.setString(5, picFile);

            statement.executeUpdate();
            return new Recipe(title,description,profilesData.getProfile(username),ingredients,picFile);
        }
    }

    public ArrayList<Recipe> getRecipesByIngredient(String searchedTitle) throws SQLException
    {
        ArrayList<Recipe> result=new ArrayList<>();
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"VegSearch\".Recipe WHERE title LIKE ?");
            statement.setString(1, "%" +searchedTitle+"%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                String title  = resultSet.getString("title");
                String description = resultSet.getString("description");
                String username = resultSet.getString("profile");
                String picFile = resultSet.getString("picFile");
                Array ingredients = resultSet.getArray("ingredients");
                String[] ing = (String[])ingredients.getArray();
                ArrayList<String> ingredientsArray = new ArrayList<>();
                for (int i = 0; i < ing.length; i++) {
                    ingredientsArray.add(ing[0]);
                }

                result.add(new Recipe(title,description,profilesData.getProfile(username),ingredientsArray,picFile));

            }
            return result;
        }
    }

    public ArrayList<Recipe> getRecipesByTitle(String searchedTitle) throws SQLException
    {
        ArrayList<Recipe> result=new ArrayList<>();
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"VegSearch\".Recipe WHERE ingredient LIKE ?");
            statement.setString(1, "%" +searchedTitle+"%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                String title  = resultSet.getString("title");
                String description = resultSet.getString("description");
                String username = resultSet.getString("profile");
                String picFile = resultSet.getString("picFile");
                Array ingredients = resultSet.getArray("ingredients");
                String[] ing = (String[])ingredients.getArray();
                ArrayList<String> ingredientsArray = new ArrayList<>();
                for (int i = 0; i < ing.length; i++) {
                    ingredientsArray.add(ing[0]);
                }

                result.add(new Recipe(title,description,profilesData.getProfile(username),ingredientsArray,picFile));
            }
            return result;
        }
    }

    public ArrayList<Recipe> getRecipesByAuthor(String author) throws SQLException
    {
        ArrayList<Recipe> result=new ArrayList<>();
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"VegSearch\".Recipe WHERE username LIKE ?");
            statement.setString(1, "%"+author+"%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                String title  = resultSet.getString("title");
                String description = resultSet.getString("description");
                String username = resultSet.getString("profile");
                String picFile = resultSet.getString("picFile");
                Array ingredients = resultSet.getArray("ingredients");
                String[] ing = (String[])ingredients.getArray();
                ArrayList<String> ingredientsArray = new ArrayList<>();
                for (int i = 0; i < ing.length; i++) {
                    ingredientsArray.add(ing[0]);
                }

                result.add(new Recipe(title,description,profilesData.getProfile(username),ingredientsArray,picFile));

            }
            return result;
        }
    }



}
