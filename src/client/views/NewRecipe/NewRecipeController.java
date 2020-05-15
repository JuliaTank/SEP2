package client.views.NewRecipe;

import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import shared.transferObjects.Profile;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewRecipeController implements ViewController
{
  @FXML
  private TextField ingredientField;
  @FXML
  private TextArea descriptionArea;
  @FXML
  private TextField titleField;
  @FXML
  private CheckBox vegetarianCheck;
  @FXML
  private ListView ingredientsList;
  @FXML
  private Label errorLabel;
  @FXML
  private Button onChooseButton;
  @FXML
  private CheckBox veganCheck;
  @FXML
  private CheckBox glutenCheck;
  private File picFile;
  ObservableList<String> ingredients =  FXCollections.observableArrayList();

  private NewRecipeVM vm = ViewModelFactory.getInstance().getNewRecipeVM();

  public NewRecipeController()
      throws NotBoundException, SQLException, IOException
  {
  }

  public void onChooseFileButton(ActionEvent actionEvent)
  {
    JFileChooser fc =  new JFileChooser();
    fc.setDialogTitle("Choose your profile picture");
    fc.showDialog(null,"Select");
    fc.setVisible(true);

    if(fc.getSelectedFile()!=null)
    {
      picFile = fc.getSelectedFile();
    }
    else
    {
      picFile = new File("file:carrotLogo.png");
    }
  }

  public void onSaveButton(ActionEvent actionEvent) throws RemoteException
  {
    if(titleField.getText().length()<2 || descriptionArea.getText().length()<3)
    {
      errorLabel.setText("Title or instruction are too short");
    }
    else
    {
      ArrayList<String> ingredients = new ArrayList<>();
      for (int i = 0; i <ingredientsList.getItems().size(); i++)
      {
        ingredients.add((String)ingredientsList.getItems().get(i));
      }
      vm.save(titleField.getText(),descriptionArea.getText(),ingredients,picFile);
      vm.cancel();
    }
  }

  public void onCancelButton(ActionEvent actionEvent)
  {
    vm.cancel();
  }

  @Override public void init(Profile profile)
      throws FileNotFoundException, SQLException, RemoteException
  {
    errorLabel.textProperty().bindBidirectional(vm.getErrorLabel());

  }

  public void onAddButton(ActionEvent actionEvent)
  {
    if(!ingredientField.getText().isEmpty())
    {
      ingredients.add(ingredientField.getText());
      ingredientsList.setItems(ingredients);
    }
    else
    {
      System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }
  }
}
