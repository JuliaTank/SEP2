package database;

import shared.transferObjects.Profile;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class ProfilesData {

    private static ProfilesData instance;

    private ProfilesData() throws SQLException
    {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized ProfilesData getInstance() throws SQLException {
        if(instance==null)
        {
            instance = new ProfilesData();
        }
        return instance;
    }
    private Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                "Roksanka2601");
    }
    public Profile create(String username, String password, String picFile, String description, ArrayList<Profile>subscriptions) throws SQLException
    {
        try (Connection connection = getConnection())
        {
            PreparedStatement statement= connection.prepareStatement("INSERT INTO \"VegSearch\".Profile(username,password,picFile,description,subscriptions) VALUES (?,?,?,?,?);");
            statement.setString(1,username);
            statement.setString(2,password);
            statement.setString(3,picFile);
            statement.setString(4,description);
            Array array  = connection.createArrayOf("varchar",getSubsForDB(subscriptions));
            statement.setArray(5, array);

            statement.executeUpdate();
            return new Profile(username,password,picFile,description,subscriptions);
        }
    }

    public Profile getProfile(String searchedUsername) throws SQLException
    {
        Profile result = null;
        try(Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"VegSearch\".Profile WHERE username LIKE ?");
            statement.setString(1, searchedUsername);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                String username  = resultSet.getString("username");
                String password = resultSet.getString("password");
                String picFile = resultSet.getString("picFile");
                String description = resultSet.getString("description");
                Array subscriptions = resultSet.getArray("subscriptions");
                String[] subs = (String[])subscriptions.getArray();

                Profile profile = new Profile(username,password,picFile,description, getSubs(subs));
                result = profile;
            }
            return result;
        }
    }
    //here I'm transforming array of subscribers usernames(read from database) into ArrayList of Profiles
    private ArrayList<Profile> getSubs(String[] subs) throws SQLException {
        ArrayList<Profile> subscribers = new ArrayList<>();
        for (int i = 0; i < subs.length ;i++) {
            subscribers.add(getProfile(subs[i]));
        }
        return  subscribers;
    }
    //here I'm transforming ArrayList of Profiles into Array of usernames of subscribers
    // I will need it to put subscribers data into database
    private String[] getSubsForDB(ArrayList<Profile> subs)
    {
        String[] usernames = new String[subs.size()];
        for (int i = 0; i <subs.size() ; i++) {
            usernames[i] = subs.get(i).getUsername();
        }
        return  usernames;
    }
    public Profile update(String username, String password, String picFile, String description, ArrayList<Profile>subscriptions) throws SQLException
    {
        try (Connection connection = getConnection())
        {
            PreparedStatement statement= connection.prepareStatement("UPDATE \"VegSearch\".Profile SET username=?, password=?, picFile=?, description=?, subscriptions=? WHERE username=?, password=?, picFile=?, description=?, subscriptions=?;");
            statement.setString(1,username);
            statement.setString(2,password);
            statement.setString(3,picFile);
            statement.setString(4,description);
            statement.setObject(5, subscriptions);

            statement.executeUpdate();
            return new Profile(username,password,picFile,description,subscriptions);
        }
    }
}

