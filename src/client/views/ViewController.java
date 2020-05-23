package client.views;

import shared.transferObjects.Profile;
import java.io.IOException;
import java.sql.SQLException;

public interface ViewController {
  void init(Profile profile)
          throws IOException, SQLException;
}
