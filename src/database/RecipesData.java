package database;

import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

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
                "JJuu11@@");
    }
    public void create(String title, String description, String username, ArrayList<String> ingredients, File picFile) throws SQLException, FileNotFoundException {
        FileInputStream fis  = new FileInputStream(picFile);
        try (Connection connection = getConnection())
        {
            PreparedStatement statement= connection.prepareStatement("INSERT INTO \"VegSearch\".Recipe(title,description,username,ingredients,picFile) VALUES (?,?,?,?,?);");
            statement.setString(1,title);
            statement.setString(2,description);
            statement.setString(3,username);
            Array array  = connection.createArrayOf("varchar",ingredients.toArray());
            statement.setArray(4, array);
            statement.setBinaryStream(5,fis,(int)picFile.length());

            statement.executeUpdate();

        }
        //return new Recipe(title,description,profilesData.getProfile(username),ingredients,picFile);
    }

    public ArrayList<Recipe> getRecipesByIngredient(String searchedIngredient) throws SQLException
    {
        ArrayList<Recipe> result=new ArrayList<>();
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT title, description, username, picfile, ingredients::text::text[] as ingredients FROM \"VegSearch\".Recipe WHERE ingredients LIKE ?");
            statement.setString(1, "%" +searchedIngredient+"%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                String title  = resultSet.getString("title");
                String description = resultSet.getString("description");
                String username = resultSet.getString("username");
                byte[] imgBytes = resultSet.getBytes(4);
                //using private method from below
                File picFile = getPicFile(imgBytes,title);
                Array ingredients = resultSet.getArray("ingredients");
                String[] ing = (String[])ingredients.getArray();
                ArrayList<String> ingredientsArray = new ArrayList<>();
                for (int i = 0; i < ing.length; i++) {
                    ingredientsArray.add(ing[i]);
                }

                result.add(new Recipe(title,description,profilesData.getProfile(username),ingredientsArray,imgBytes ,picFile));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    private File getPicFile(byte[] imgBytes, String title) throws IOException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(imgBytes);
        BufferedImage image = ImageIO.read(bais);

        if(new File(title+"pic.jpg").createNewFile())
        {
            System.out.println("new file created");
        }
        File picFile =  new File(title+"pic.jpg");

        if(image!=null)
            ImageIO.write(image,"jpg",picFile);
        return picFile;
    }
    public ArrayList<Recipe> getAllRecipes() throws SQLException
    {
        ArrayList<Recipe> result = new ArrayList<>();

        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT title, description, username, picfile, ingredients::text::text[] as ingredients FROM \"VegSearch\".Recipe");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String username = resultSet.getString("username");
                byte[] imgBytes = resultSet.getBytes(4);
                //using private method from below
                File picFile = getPicFile(imgBytes, title);

                Array ingredients = resultSet.getArray("ingredients");

                String[] ing = (String[]) ingredients.getArray();

                ArrayList<String> ingredientsArray = new ArrayList<>();

                result.add(new Recipe(title,description,profilesData.getProfile(username), getIng(ingredients), imgBytes,picFile)) ;
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;
    }
    public ArrayList<Recipe> getRecipesByTitle(String searchedTitle) throws SQLException
    {
        ArrayList<Recipe> result = new ArrayList<>();

        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT title, description, username, picfile, ingredients::text::text[] as ingredients FROM \"VegSearch\".Recipe WHERE title LIKE ?");
            statement.setString(1, "%"+searchedTitle+"%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String username = resultSet.getString("username");
                byte[] imgBytes = resultSet.getBytes(4);
                //using private method from below
                File picFile = getPicFile(imgBytes, title);

                Array ingredients = resultSet.getArray("ingredients");
                String[] ing = (String[]) ingredients.getArray();
               ArrayList<String> ingredientsArray = new ArrayList<>();

                result.add(new Recipe(title, description, profilesData.getProfile(username),
                    getIng(ingredients),imgBytes, picFile)) ;
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;
    }
    public Recipe getRecipeByTitle(String searchedTitle) throws SQLException
    {
        Recipe result = null;

        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT title, description, username, picfile, ingredients::text::text[] as ingredients FROM \"VegSearch\".Recipe WHERE title LIKE ?");
            statement.setString(1, searchedTitle);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                String title = resultSet.getString("title");
                System.out.println(title);
                String description = resultSet.getString("description");
                System.out.println(description);
                String username = resultSet.getString("username");
                System.out.println(username);
                byte[] imgBytes = resultSet.getBytes(4);
                //using private method from below
                File picFile = getPicFile(imgBytes, title);

                Array ingredients = resultSet.getArray("ingredients");
                System.out.println(ingredients);

                String[] ing = (String[]) ingredients.getArray();
                System.out.println(ingredients.getArray());

                System.out.println(Arrays.toString(ing));
                ArrayList<String> ingredientsArray = new ArrayList<>();

                result = new Recipe(title, description, profilesData.getProfile(username),
                    getIng(ingredients), imgBytes,picFile);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;
    }
    public void deleteRecipe(String username) throws SQLException
    {
        try (Connection connection = getConnection())
        {
            PreparedStatement statement= connection.prepareStatement("DELETE FROM \"VegSearch\".Recipe WHERE username =? ");
            statement.setString(1,username);
            statement.executeUpdate();
        }
    }

    private ArrayList<String> getIng(Array array) throws SQLException
    {
        String[] ings =(String[]) array.getArray();
        ArrayList<String> ingss = new ArrayList<>();
        for (int i = 0; i < ings.length; i++)
        {
            ingss.add(ings[i]);
        }
        return ingss;
    }
    public ArrayList<Recipe> getRecipesByAuthor(String aUsername) throws SQLException
    {
        ArrayList<Recipe> result=new ArrayList<>();
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT title, description, username, picfile, ingredients::text::text[] as ingredients FROM \"VegSearch\".Recipe WHERE username LIKE ?");
            statement.setString(1, "%"+aUsername+"%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                String title  = resultSet.getString("title");
                String description = resultSet.getString("description");
                String username = resultSet.getString("username");
                byte[] imgBytes = resultSet.getBytes(4);
                //using private method from below
                File picFile = getPicFile(imgBytes,title);
                Array ingredients = resultSet.getArray("ingredients");


                String[] ing = (String[])ingredients.getArray();

                ArrayList<String> ingredientsArray = new ArrayList<>();

                for (int i = 0; i < ing.length; i++) {
                    ingredientsArray.add(ing[i]);
                }
                result.add(new Recipe(title,description,profilesData.getProfile(username),ingredientsArray,imgBytes,picFile));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
public void update(String title,String newTitle,String description, Profile profile,ArrayList<String> ingredients,File file)
    throws FileNotFoundException, SQLException
{
    FileInputStream fis = new FileInputStream(file);
    try(Connection connection  = getConnection())
    {
        PreparedStatement statement = connection.prepareStatement("UPDATE \"VegSearch\".Recipe SET  username = ?, description  =?,title = ?,ingredients = ?,picfile = ? WHERE title = ?" );
        statement.setString(1,profile.getUsername());
        statement.setString(2,description);
        statement.setString(3,newTitle);
        Array array = connection.createArrayOf("varchar", ingredients.toArray());
        statement.setArray(4, array);
        statement.setBinaryStream(5,fis,(int)file.length());
        statement.setString(6,title );

        statement.executeUpdate();
        //return new Recipe(newTitle, description,profile, ingredients,file);
    }
}

}
