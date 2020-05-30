package database;

import org.junit.jupiter.api.Test;
import shared.transferObjects.Profile;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProfilesDataTest {
    ProfilesData profilesData = ProfilesData.getInstance();

    {
        try {
            profilesData = new ProfilesData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ProfilesDataTest() throws SQLException
    {
    }

    //create-zero
    @Test
    void createZero() throws IOException, SQLException {
         boolean created=profilesData.create(null,null,null,null,null,null);
         assertEquals(false,created);
    }

    @Test
    void getProfile() throws SQLException
    {
       assertEquals("Julia", profilesData.getProfile("Julia").getUsername());
    }

    //update-exception
    @Test
    void updateException() {

        assertThrows(org.postgresql.util.PSQLException.class, () -> profilesData.update("roksana", "Julia", "kotkot", new File("roksanapic.jpg"), null, "jestem roksanka!",profilesData.getProfile("roksana").getSubs()));
    }

    //delete-zero
    @Test
    void deleteZero() throws SQLException {
       //already tested in server(server calls method from here and prevents throwing exceptions)
    }

    //delete - exceptions
    @Test
    void deleteExceptions()
    {
        //already tested in server(server calls method from here and prevents throwing exceptions)
    }
}