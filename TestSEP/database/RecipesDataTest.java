package database;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RecipesDataTest {
    RecipesData recipesData;

    //create-zero
    @Test
    void createZero() throws IOException, SQLException {
        recipesData.create(null,null,null,null,null,null);
        assertEquals(null,recipesData.getRecipeByTitle(null));
        assertEquals(null,recipesData.getRecipesByIngredient(null));
        assertEquals(null,recipesData.getRecipesByAuthor(null));
    }

    //create-one
    @Test
    void createOne() throws SQLException, IOException {
        recipesData.create("Apple pie","bake cake","Roksana",null,null,null);
        assertEquals("Apple pie",recipesData.getRecipeByTitle("Apple pie"));
        assertEquals(null,recipesData.getRecipesByIngredient(null));
        assertEquals("Roksana",recipesData.getRecipesByAuthor("Roksana"));
    }

    //create-many
    @Test
    void createMany() throws SQLException, IOException {
        recipesData.create("Cherry pie","bake cake","Roksana",null,null,null);
        recipesData.create("lasagne","bake lasagne","Julia",null,null,null);

        assertEquals("Cherry pie",recipesData.getRecipeByTitle("Cherry pie"));
        assertEquals(null,recipesData.getRecipesByIngredient(null));
        assertEquals("Roksana",recipesData.getRecipesByAuthor("Roksana"));

        assertEquals("lasagne",recipesData.getRecipeByTitle("lasagne"));
        assertEquals("Julia",recipesData.getRecipesByAuthor("Julia"));
    }

    //create-boundary
    @Test
    void createBoundary()
    {
        //??????????
    }

    //create-exception
    @Test
    void createException()
    {
        //recipe title already exists:(
        assertThrows(IllegalArgumentException.class, ()->recipesData.create("Fries","frytki","Julia",null,null,null));

    }
    @Test
    void getRecipesByIngredient() {
        //already tested in create
    }

    @Test
    void getAllRecipes() {
    }

    @Test
    void getRecipesByTitle() {
        //already tested in create
    }

    //get recipe by title-zero
    @Test
    void getRecipeByTitleZero() throws SQLException, RemoteException {
        assertEquals(null,recipesData.getRecipeByTitle(null));
    }
    //get recipe by title-one
    @Test
    void getRecipeByTitleOne() throws SQLException, RemoteException {
        assertEquals("Fries",recipesData.getRecipeByTitle("Fries"));
    }

    //get recipe by title-many
    @Test
    void getRecipeByTitleMany() throws SQLException, RemoteException {
        assertEquals("Fries",recipesData.getRecipeByTitle("Fries"));
        assertEquals("",recipesData.getRecipesByTitle(""));
    }
    //get recipe by title- boundary
    @Test
    void getRecipeByTitleBoundary()
    {
        //??????????/
    }

    //get recipe by title - exception
    @Test
    void getRecipeByTitleException()
    {
        //this recipe title does not exist:(
        assertThrows(IllegalArgumentException.class,()->recipesData.getRecipesByTitle("toasts"));
    }

    @Test
    void deleteRecipe() {
        //javier's part:)
    }

    @Test
    void getRecipesByAuthor() {
        //already tested in create
    }

    @Test
    void update() {
        //javier's part:)
    }
}