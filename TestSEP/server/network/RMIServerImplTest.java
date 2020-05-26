package server.network;

import org.junit.jupiter.api.Test;
import shared.networking.RMIServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class RMIServerImplTest {
    RMIServer server;
    //Log in-zero
    @Test
    void logInZero() throws RemoteException, SQLException, FileNotFoundException {
        server.logIn(null, null);
       assertEquals(null,server.getProfile(null));
    }

    //log in-one
    @Test
    void logInOne() throws RemoteException, SQLException, FileNotFoundException {
        server.logIn("Roksana", "kotkot");
        assertEquals("Roksana",server.getProfile("Roksana"));

    }

    //log in-many
    @Test
    void logInMany() throws RemoteException, SQLException, FileNotFoundException {
        server.logIn("Roksana", "kotkot");
        server.logIn("Julia","Kot");
        assertEquals("Julia",server.getProfile("Julia"));
        assertEquals("Roksana",server.getProfile("Roksana"));
    }
    //log in-boundary
    @Test
    void logInBoundary()
    {
        //it is not necessary in this method
    }

    //log in- exceptions
    @Test
    void logInExceptions()
    {
        //this username is not exist:(
        assertThrows(IllegalArgumentException.class,()->server.logIn("toms","kotyyy"));
    }
    //add recipe-zero
    @Test
    void addRecipeZero() throws IOException, SQLException {
        server.addRecipe(null,null,null,null,null,null);
        assertEquals(null,server.getRecipesByTitle(null));
        assertEquals(null, server.getRecipesByIngredient(null));
        assertEquals(null, server.getRecipesByUsername(null));
    }
    //add recipe-one
    @Test
    void addRecipeOne() throws IOException, SQLException {
        ArrayList<String> ingredients = new ArrayList<>();
        server.addRecipe("kotlety","usmaz kotlety","kotki",ingredients,null,null);
        assertEquals("kotlety",server.getRecipesByTitle("kotlety"));
        assertEquals(ingredients.get(0),server.getRecipesByIngredient(ingredients.get(0)));
        assertEquals("kotki",server.getRecipesByUsername("kotki"));
    }
    //add recipe-many
    @Test
    void addRecipeMany() throws IOException, SQLException {
        ArrayList<String> ingredients1 = new ArrayList<>();
        ArrayList<String> ingredients2 = new ArrayList<>();
        server.addRecipe("kotlety","usmaz kotlety","kotki",ingredients1,null,null);
        server.addRecipe("pierogi","ugotuj pierogi","pieski",ingredients2,null,null);

        assertEquals("kotlety",server.getRecipesByTitle("kotlety"));
        assertEquals(ingredients1.get(0),server.getRecipesByIngredient(ingredients1.get(0)));
        assertEquals("kotki",server.getRecipesByUsername("kotki"));

        assertEquals("pierogi",server.getRecipesByTitle("pierogi"));
        assertEquals(ingredients2.get(0),server.getRecipesByIngredient(ingredients2.get(0)));
        assertEquals("pieski",server.getRecipesByUsername("pieski"));
    }

    //add recipe-boundary
    @Test
    void addRecipeBoundary()
    {
        //it's not necessary here, i think
    }
    //add recipe-exception
    @Test
    void addRecipeException()
    {
        //recipe title already exists:(
        assertThrows(IllegalArgumentException.class, ()->server.addRecipe("Fries","frytki","Julia",null,null,null));
    }
    //report-zero
    @Test
    void reportZero() throws RemoteException, SQLException {
        server.report(null,null,null);
       // assertEquals(null, server.);
    }

    //sign up-zero
    @Test
    void signUpZero() throws IOException, SQLException {
        server.signUp(null,null,null,null,null);
        assertEquals(null,server.getProfile(null));
    }
    //sign up-one
    @Test
    void signUpOne() throws IOException, SQLException {
        server.signUp("Marek","Imbir",null,null,"lubie imbir");
        assertEquals("Marek",server.getProfile("Marek"));
    }
    //sign up-many
    @Test
    void signUpMany() throws IOException, SQLException {
        server.signUp("Jan","janjan",null,null,"lubie weganskie jedzenie");
        server.signUp("Gustaw","pingwin",null,null,"nie lubie roksany i julii");

        assertEquals("Jan",server.getProfile("Jan"));
        assertEquals("Gustaw",server.getProfile("Gustaw"));
    }

    //sign up-boundary
    @Test
    void signUpBoundary()
    {
        //not necessary?????????????
    }

    //sign up-exception
    @Test
    void signUpException()
    {
        //username already exists:(
        assertThrows(IllegalArgumentException.class,()->server.signUp("Julia","kotyyy",null,null,null));
    }

    //edit profile-zero
    @Test
    void editProfileZero() {
      //  server.editProfile(null,null,null,null,null,null,null);
      //  assertEquals(null,server.);
    }

    @Test
    void getProfile() {
        //already tested in sign up, i think
    }

    @Test
    void getProfiles() {
    }

    @Test
    void subscribe() {

    }

    @Test
    void unsubscribe() {
    }

    @Test
    void doIsubscribeIt() {
    }

    @Test
    void delete() {
    }

    @Test
    void getRecipesByIngredient() {
        //already tested in add Recipe, i think
    }

    //get recipe by title-zero
    @Test
    void getRecipeByTitleZero() throws SQLException, RemoteException {
        assertEquals(null,server.getRecipeByTitle(null));
    }
    //get recipe by title-one
    @Test
    void getRecipeByTitleOne() throws SQLException, RemoteException {
        assertEquals("Fries",server.getRecipeByTitle("Fries"));
    }

    //get recipe by title-many
    @Test
    void getRecipeByTitleMany() throws SQLException, RemoteException {
        assertEquals("Fries",server.getRecipeByTitle("Fries"));
        assertEquals("",server.getRecipesByTitle(""));
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
        assertThrows(IllegalArgumentException.class,()->server.getRecipesByTitle("toasts"));
    }
    @Test
    void getRecipesByUsername() {
        //already tested in add Recipe, i think
    }

    @Test
    void getRecipesByTitle() {
        //already tested in add Recipe, i think
    }

    @Test
    void getAllRecipes() {

    }
}