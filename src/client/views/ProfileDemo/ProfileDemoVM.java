package client.views.ProfileDemo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferObjects.Profile;
import shared.transferObjects.Recipe;

public class ProfileDemoVM
{
  private StringProperty profileLink;
  private Profile profile;

  public ProfileDemoVM(Profile profile)
  {
    this.profile= profile;
    profileLink = new SimpleStringProperty();
    profileLink.setValue(profile.getUsername());
  }

  public Profile getProfile()
  {
    return profile;
  }

  public StringProperty getProfileLink()
    {
      return profileLink;
    }

}
