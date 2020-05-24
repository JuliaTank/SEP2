package database;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ProfilesDataTest {
    ProfilesData profilesData;


    @Test
    void create() {
        File picFile = new File("carrotLogo.png");
        profilesData.create("Kot","kotkot",picFile, picFile.);
    }

    @Test
    void getProfile() {
    }

    @Test
    void getProfiles() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        profilesData.delete();
    }
}