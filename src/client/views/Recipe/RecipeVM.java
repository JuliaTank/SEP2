package client.views.Recipe;

import client.core.ModelFactory;
import client.core.ViewHandler;
import client.model.VegSearchModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferObjects.Profile;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class RecipeVM
{
  private ListProperty<String>  ingredients;
  private StringProperty userLink;
  private StringProperty likeLabel;
  private StringProperty commentField;
  private StringProperty reportField;
  private ListProperty<String> comments;

  private ViewHandler vh =  ViewHandler.getInstance();
  private VegSearchModel model = ModelFactory.getInstance().getModel();

public  RecipeVM() throws IOException, NotBoundException, SQLException
{

  ingredients =  new SimpleListProperty<>();
  userLink = new SimpleStringProperty();
  likeLabel = new SimpleStringProperty();
  commentField =  new SimpleStringProperty();
  reportField = new SimpleStringProperty();
  comments =new SimpleListProperty<>();

}

  public void report(String title, String message)
      throws RemoteException, SQLException
  {
  model.report(title,message);
}
  public ListProperty<String> ingredientsProperty()
  {
    return ingredients;
  }

  public StringProperty userLinkProperty()
  {
    return userLink;
  }

  public StringProperty likeLabelProperty()
  {
    return likeLabel;
  }

  public StringProperty commentFieldProperty()
  {
    return commentField;
  }

  public StringProperty reportFieldProperty()
  {
    return reportField;
  }

  public ListProperty<String> commentsProperty()
  {
    return comments;
  }

  public void onUserLink()
          throws IOException, SQLException {
    vh.openProfile(model.getProfile(userLink.getValue()));
  }
  public void onMainPage()
  {
    vh.openMainPage();
  }
  public void onProfile()
  {
    vh.openProfile(model.getLoggedProfile());
  }

  public Profile getLoggedProfile(){
    return model.getLoggedProfile();
  }
}
