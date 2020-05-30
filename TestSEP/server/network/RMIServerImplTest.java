package server.network;

import org.junit.jupiter.api.Test;
import server.model.Manager;
import shared.networking.RMIServer;
import shared.transferObjects.Profile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class RMIServerImplTest
{
    RMIServer server = new RMIServerImpl(new Manager());

    RMIServerImplTest() throws SQLException
    {
    }

    //Log in-zero
    @Test void logInZero()
        throws RemoteException, SQLException, FileNotFoundException
    {
        boolean isLogged = server.logIn(null, null);
        assertEquals(false, isLogged);
    }

    //log in-one
    @Test void logInOne()
        throws RemoteException, SQLException, FileNotFoundException
    {
        boolean isLogged = server.logIn("roksana", "kotkot");
        // boolean isLogged = server.logIn("roksana", "kotkot");
        assertEquals(true, isLogged);

    }

    //log in-many
    @Test void logInMany()
        throws RemoteException, SQLException, FileNotFoundException
    {
        boolean isLoggedRoksana = server.logIn("roksana", "kotkot");
        boolean isLoggedJulia = server.logIn("Julia", "kot");
        assertEquals(true, isLoggedJulia);
        assertEquals(true, isLoggedRoksana);
    }

    //log in-boundary
    @Test void logInBoundary()
    {
        //no need to test it
    }

    //log in-exceptions
    @Test
    void logInExceptions()
    {
       // not need to test it
    }

    //add recipe-zero
    @Test
    void addRecipeZero() throws IOException, SQLException
    {
        boolean isAdded = server.addRecipe(null, null, null, null, null, null);
        assertEquals(false, isAdded);

    }

    //add recipe-one
    @Test void addRecipeOne() throws IOException, SQLException
    {
        ArrayList<String> ingredients = new ArrayList<>();
        boolean isAdded = server.addRecipe("kotlety", "usmaz kotlety", "Julia", ingredients, new File("carrotLogo.png"), null);

        assertEquals(true, isAdded);
    }

    //add recipe-many
    @Test void addRecipeMany() throws IOException, SQLException
    {
        //noo need
    }

    //add recipe-boundary
    @Test void addRecipeBoundary()
    {
        //it's not necessary here, i think but im not sure!!
    }

    //add recipe-exception
    @Test void addRecipeException()
    {
        //Recipe 'Fries' already exists
        assertThrows(IllegalStateException.class, () -> server.addRecipe("Fries", "frytki", "blablabla", null, null, null));
    }

    //report-zero
    @Test
    void reportZero() throws RemoteException, SQLException
    {
        boolean isReported = server.report(null, "Julia", null);
        assertEquals(false, isReported);
    }

    //report - one
    @Test void reportOne() throws RemoteException, SQLException
    {
        boolean isReported = server.report("Fries", "Julia", "report");
        assertEquals(true, isReported);
    }

    //report - many
    @Test void reportMany() throws RemoteException, SQLException
    {
        boolean isReported1 = server.report("Fries", "Julia", "report");
        boolean isReported2 = server.report("RecipeThatDoesNotExist", "Julia", "report");

        assertEquals(true, isReported1);
        assertEquals(false, isReported2);
    }

    //report - boundary
    @Test void reportBoundary() throws RemoteException, SQLException
    {
        assertEquals(false,server.report("Fries","Julia","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    //report - exception
    @Test void reportException()
    {
        //username does not exist
        assertThrows(IllegalStateException.class,
            () -> server.report("kotki", "kotyyyyyy", "lubie koty"));
    }

    //sign up-zero
    @Test void signUpZero() throws IOException, SQLException
    {
        boolean isSignedUp = server.signUp(null, null, null, null, null);
        assertEquals(false, isSignedUp);
    }

    //sign up-one
    @Test void signUpOne() throws IOException, SQLException
    {
        boolean isSignedUp = server
            .signUp("Imbirowy", "Imbir", new File("rabbit.jpg"), null, "lubie imbir");
        assertEquals(true, isSignedUp);
    }

    //sign up-many
    @Test void signUpMany() throws IOException, SQLException
    {
       //no need
    }

    //sign up-boundary
    @Test void signUpBoundary() throws IOException, SQLException
    {
        boolean isSignedUp = server.signUp("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaa",null,null,"ooo");
        assertEquals(false, isSignedUp);
    }

    //sign up-exception
    @Test void signUpException()
    {
        //no need
    }

    //edit profile-zero
    @Test void editProfileZero() throws IOException, SQLException
    {
        boolean isEdited = server
            .editProfile(
                "",
                "",
                null,
                null,
                null,
                null,
                null);
        assertEquals(false, isEdited);
    }

    //edit profile - one
    @Test void editProfileOne() throws IOException, SQLException
    {
        boolean isEdited = server.editProfile("Julia", "Julia", "kot", new File("Juliapic.jpg"), null, "HellooOOooOoOoooOOoooOOOOoooooOo", new ArrayList<>());
        assertEquals(true, isEdited);
    }

    //edit profile - many
    @Test void editProfileMany() throws IOException, SQLException
    {
        //no need
    }

    //edit profile - boundary
    @Test void editProfileBoundary() throws IOException, SQLException
    {
        boolean isEdited = server.editProfile("Julia","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","mmm",null,null,"ooo",new ArrayList<>());
        assertEquals(false, isEdited);
    }

    //edit profile - exception
    @Test void editProfileException()
    {
        assertThrows(IllegalArgumentException.class, () -> server.editProfile("UsernameThatDoeasntExist", "julia123", "kotyy", null, null, null, null));
    }

    @Test void getProfile()
        throws FileNotFoundException, SQLException, RemoteException
    {
        String test =  server.getProfile("Julia").getUsername();
        assertEquals("Julia",test);
    }

    @Test void getProfiles()
        throws FileNotFoundException, SQLException, RemoteException
    {
        String test =  server.getProfile("Julia").getPassword();
        assertEquals("kot",test);
    }

    @Test
    void doIsubscribeItZero()
        throws FileNotFoundException, RemoteException, SQLException
    {
        boolean isSubscribed = server.doIsubscribeIt(null, null);
        assertEquals(false, isSubscribed);
    }

    //doisubscribeit - one
    @Test
    void doIsubscribeItOne()
        throws FileNotFoundException, RemoteException, SQLException
    {
        boolean isSubscribed = server.doIsubscribeIt("roksana",server.getProfile("Julia"));
        assertEquals(true,isSubscribed);
    }

    //delete - zero
    @Test
    void deleteZero() throws SQLException, RemoteException
    {
        boolean isDeleted = server.delete(null);
        assertEquals(false, isDeleted);
    }

    //delete - one
    @Test
    void deleteOne() throws SQLException, RemoteException
    {
        boolean isDeleted = server.delete("Julia");
        assertEquals(true,isDeleted);
    }

    //delete- many
    @Test
    void deleteMany() throws SQLException, RemoteException
    {
        boolean isDeleted = server.delete("janusz");
        boolean isDeleted2 = server.delete("Toms2");
        assertEquals(true,isDeleted);
        assertEquals(true,isDeleted2);
    }

    //delete - exception
    @Test
    void deleteException()
    {
        //username does not exist
        assertThrows(IllegalArgumentException.class,()->server.delete("kotek"));
    }
    //get recipe by title-zero
    @Test
    void getRecipeByTitleZero() throws SQLException, RemoteException {

        assertEquals(null, server.getRecipeByTitle(null));
    }

    //get recipe by title-one
    @Test
    void getRecipeByTitleOne() throws SQLException, RemoteException {
        assertEquals("Fries", server.getRecipeByTitle("Fries").getTitle());
    }
    //get recipes by username- exception
    @Test
    void getRecipesByUsername() {
        //there is no user "kotek" in the database
        assertThrows(IllegalArgumentException.class, ()->server.getRecipesByUsername("kotek"));
    }
}