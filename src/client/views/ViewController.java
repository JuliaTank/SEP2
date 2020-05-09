package client.views;

import shared.transferObjects.Profile;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ViewController {
  void init(Profile profile)
      throws FileNotFoundException, SQLException, RemoteException;
}
