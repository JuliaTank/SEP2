package database;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RecipesDataTest {
    RecipesData recipesData = RecipesData.getInstance();

    RecipesDataTest() throws SQLException
    {
    }

    //create-zero
    @Test
    void createZero() throws IOException, SQLException {
        boolean isCreated = recipesData.create(null,null,null,null,null,null);
        assertEquals(false,isCreated);
    }

    //create-boundary
    @Test
    void createBoundary()
    {
        //already tested in server(server calls method from here and prevents throwing exceptions)
    }

    //create-exception
    @Test
    void createException()
    {
        assertThrows(org.postgresql.util.PSQLException.class, ()->recipesData.create("Friessssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss","frytki","Julia",new ArrayList<>(),new File("carrotLogo.png"),null));
    }
    @Test
    void getRecipesByIngredient() throws SQLException
    {
        assertEquals(1, recipesData.getRecipesByIngredient("potatoes").size());
    }

    @Test
    void getRecipesByTitle() throws SQLException
    {
        assertEquals(1,recipesData.getRecipesByTitle("Fries").size());
    }

    //get recipe by title-zero
    @Test
    void getRecipeByTitle() throws SQLException, RemoteException {
        assertEquals("Fries",recipesData.getRecipeByTitle("Fries").getTitle());
    }


    @Test
    void deleteRecipe() {
        //javier's part:)
    }

    @Test
    void getRecipesByAuthor() throws SQLException
    {
        assertEquals(0, recipesData.getRecipesByAuthor("Imbirowy").size());
    }

    @Test
    void update() {
        //javier's part:)
    }
}