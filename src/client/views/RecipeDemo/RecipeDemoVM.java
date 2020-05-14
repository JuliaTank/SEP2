package client.views.RecipeDemo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferObjects.Recipe;


public class RecipeDemoVM
{
  private StringProperty recipeLink;
  private Recipe recipe;

  public RecipeDemoVM(Recipe recipe)
  {
    this.recipe= recipe;
    recipeLink = new SimpleStringProperty();
    recipeLink.setValue(recipe.getTitle());
  }

  public Recipe getRecipe()
  {
    return recipe;
  }

  public StringProperty getRecipeLink()
    {
      return recipeLink;
    }

}
