package database;

import java.sql.SQLException;

public class toDelete {
    public static void main(String[] args) throws SQLException {
        ProfilesData data =   new ProfilesData();
        data.getProfile("Roksana");
    }
}
