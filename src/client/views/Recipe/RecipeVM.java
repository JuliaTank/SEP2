package client.views.Recipe;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import javax.print.DocFlavor;

public class RecipeVM
{
  private ListProperty<String>  ingredients;
  private StringProperty userLink;
  private StringProperty likeLabel;
  private StringProperty commentField;
  private StringProperty reportField;
  private ListProperty<String> comments;

public  RecipeVM(){

  ingredients =  new SimpleListProperty<>();
  userLink = new SimpleStringProperty();
  likeLabel = new SimpleStringProperty();
  commentField =  new SimpleStringProperty();
  reportField = new SimpleStringProperty();
  comments =new SimpleListProperty<>();
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
}
