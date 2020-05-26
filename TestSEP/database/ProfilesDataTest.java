package database;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
//i'm not sure so please read it and correct it

class ProfilesDataTest {
    ProfilesData profilesData;

    {
        try {
            profilesData = new ProfilesData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //create-zero
    @Test
    void createZero() throws IOException, SQLException {
         boolean created=profilesData.create(null,null,null,null,null,null);
         assertEquals(false,created);
       // assertThrows(NullPointerException.class, () -> profilesData.create(null, null, null, null, null, null));
    }

    //create-one
    @Test
    void createOne() throws IOException, SQLException {
       // Connection connection=ConnectionInstance.getConnection();
        profilesData.create("Toms", "Julia", null, null, "Julia<333333", null);
        assertEquals("Toms", profilesData.getProfile("Toms"));
    }

    //create-many
    @Test
    void createMany() throws IOException, SQLException {
        profilesData.create("Javier", "gineapig", null, null, "i love guinea pigs", null);
        profilesData.create("Marek", "imbirowy", null, null, "i dont like julia and roksana", null);

        assertEquals("Javier", profilesData.getProfile("Javier"));
        assertEquals("Marek", profilesData.getProfile("Marek"));
    }

    //create- boundary
    @Test
    void createBoundary() {
        //not necessary, i think
    }

    //create- exception
    @Test
    void createException() {
        //username is too short, password is too short
        assertThrows(IllegalArgumentException.class, () ->
                profilesData.create("a", "b", null, null, null, null));

        //username already exists
        assertThrows(IllegalArgumentException.class, () -> profilesData.create("Julia", "kotyyy", null, null, null, null));
    }

    @Test
    void getProfile() {
        //already tested in create, i think
    }

    //we have to test this method? serioooooooooo???
    @Test
    void getProfiles() {
    }

    //update-zero
    @Test
    void updateZero() throws SQLException, IOException {
        profilesData.update("Julia", "Julia", null, null, null, null, null);
        assertEquals("Julia", profilesData.getProfile("Julia"));
    }

    //update- one
    @Test
    void updateOne() throws IOException, SQLException {
        profilesData.update("Julia", "JuliaRons", "kot", null, null, null, null);
        assertEquals("JuliaRons", profilesData.getProfile("JuliaRons"));
    }

    //update- many
    @Test
    void updateMany() throws IOException, SQLException {
        profilesData.update("Julia", "JuliaRons", "kot", null, null, null, null);
        profilesData.update("Roksana", "RoksanaJeBanana", "kotkot", null, null, null, null);
        assertEquals("JuliaRons", profilesData.getProfile("JuliaRons"));
        assertEquals("RoksanaJeBanana", profilesData.getProfile("RoksanaJeBanana"));
    }

    //update-boundary
    @Test
    void updateBoundary() {
        //not necessary, i think, like all our boundary methods XDDD
    }

    //update-exception
    @Test
    void updateException() {
        //username already exists
        assertThrows(IllegalArgumentException.class, () -> profilesData.update("Roksana", "Julia", "kotkot", null, null, null, null));

    }

    //delete-zero
    @Test
    void deleteZero() throws SQLException {
        profilesData.delete(null);

    }
}