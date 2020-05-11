package client.views.RecipeDemo;

import client.core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import shared.transferObjects.Recipe;

public class RecipeDemoController
{
  @FXML
  public Hyperlink recipeLink;
  @FXML
  private ImageView recipePic;

  private Parent root;
  private ViewHandler vh= ViewHandler.getInstance();


  public void init(Parent root, Recipe recipe)
  {
    this.root = root;
    root.setStyle("-fx-border-width: 2;" +
        "-fx-border-radius: 5;"+
        "-fx-border-color: black;");
    recipeLink.setText(recipe.getTitle());
    recipePic.setImage(new Image(recipe.getPicFile().toURI().getPath()));
  }
  public Parent getRoot()
  {
    return root;
  }
  public void onRecipeLink(ActionEvent actionEvent)
  {

  }
}
