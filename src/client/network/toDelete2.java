package client.network;

import shared.transferObjects.Profile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class toDelete2 {
    public static void main(String[] args) throws IOException, NotBoundException, SQLException {
        Client client = new RMIClient();
        client.startClient();
        Profile profile = client.getProfile("Julia");
       profile.setPicFile(client.getPicFile(profile.getImgBytes(),profile.getUsername()));
    }
}
