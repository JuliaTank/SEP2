package client.views.RecipeDemo;

import client.core.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import shared.transferObjects.Recipe;

import java.io.IOException;

public class RecipeDemoController
{
  @FXML
  public Hyperlink recipeLink;
  @FXML
  private ImageView recipePic;

  private Parent root;
  private ViewHandler vh= ViewHandler.getInstance();
  private Recipe recipe;


  public void init(Parent root, Recipe recipe)
  {
    this.recipe =  recipe;
    this.root = root;
    root.setStyle("-fx-border-width: 2;" +
        "-fx-border-radius: 5;"+
        "-fx-border-color: black;");
    recipeLink.setText(recipe.getTitle());
    Image image  =  new Image(recipe.getPicFile().toURI().toString());
    recipePic.setImage(image);
  }
  public Parent getRoot()
  {
    return root;
  }

  public void onRecipeLink(ActionEvent actionEvent) throws IOException
  {
    vh.openRecipeView(recipe);
  }

  public Hyperlink getRecipeLink()
  {
    return recipeLink;
  }
}
