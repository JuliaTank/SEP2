package client.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RecipeDisplay
{
    private StringProperty userLink = new SimpleStringProperty();
    public StringProperty getUserLink()
    {
      return userLink;
    }

}
