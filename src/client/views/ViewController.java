package client.views;

import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ViewController {
  void init(Profile profile)
          throws IOException, SQLException;
}
