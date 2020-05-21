package client.views.SignIn;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import shared.transferObjects.Profile;

import javax.swing.*;
import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class SignInController implements ViewController
{
  @FXML
  private Label joinUsLabel;
  @FXML
  private Button saveButton;
  @FXML
  private Button signUpButton;
  @FXML
  private Label picApprovedLabel;
  @FXML
  private Label errorLabel;
  @FXML
  private PasswordField passwordRepeatField;
  @FXML
  private PasswordField passwordCreationField;
  @FXML
  private TextField usernameCreationField;
  @FXML
  private TextArea descriptionArea;
  @FXML
  private ImageView userPic;

  private SignInVM vm = ViewModelFactory.getInstance().getSignInVM();

  private File picFile;
  private byte[] bytes;

  private Profile profile;

  public SignInController() throws IOException, NotBoundException, SQLException
  {
  }

  public void onChooseFileButton(ActionEvent actionEvent) throws IOException
  {
    JFileChooser fc =  new JFileChooser();
    fc.setDialogTitle("Choose your profile picture");
    fc.showDialog(null,"Select");
    fc.setVisible(true);

      if(fc.getSelectedFile()!=null)
      {
        FileInputStream fis = new FileInputStream(fc.getSelectedFile());
        bytes = fis.readAllBytes();

        picFile =fc.getSelectedFile() ;
        picApprovedLabel.setText("Your pic was added :)");
      }
      else
      {
        if(profile==null)
        {
          picFile = new File("rabbit.jpg");
        }
        else
        {
          picFile = profile.getPicFile();
        }
      }

  }

  public void onSignUp2Button(ActionEvent actionEvent)
      throws IOException, SQLException, NotBoundException
  {
    if(picFile==null)
    picFile = new File("rabbit.jpg");
    if(usernameCreationField.getText().isEmpty() || passwordCreationField.getText().isEmpty())
    {
      errorLabel.setText("Type in the username and password");
    }
    else if(usernameCreationField.getText().length()<3|| passwordCreationField.getText().length()<3)
    {
      errorLabel.setText("Username and password have to contain at least 3 signs ");
    }
    else if(!passwordCreationField.getText().equals(passwordRepeatField.getText()) )
    {
      errorLabel.setText("Passwords don't match :( , try to type them again");
      passwordCreationField.clear();
      passwordRepeatField.clear();
    }
    else
    vm.signUp(picFile,bytes);
  }

  public void onCancelButton(ActionEvent actionEvent)
      throws IOException, SQLException, NotBoundException
  {
    if(profile==null)
      ViewHandler.getInstance().openLogIn();
    else
      ViewHandler.getInstance().openProfile(profile);
    vm.cancel();
  }

  @Override public void init(Profile profile)
  {
    this.profile = profile;
    errorLabel.textProperty().bindBidirectional(vm.getErrorLabel());
    passwordCreationField.textProperty().bindBidirectional(vm.getPasswordCreationField());
    usernameCreationField.textProperty().bindBidirectional(vm.getUsernameCreationField());
    descriptionArea.textProperty().bindBidirectional(vm.getDescriptionArea());
    if(profile==null)
    {
      Image image = new Image("file:rabbit.jpg");
      userPic.setImage(image);
      saveButton.setVisible(false);
    }
    else
    {
      userPic.setImage(new Image(profile.getPicFile().toURI().toString()));
      usernameCreationField.setText(profile.getUsername());
      descriptionArea.setText(profile.getDescription());
      signUpButton.setVisible(false);
      joinUsLabel.setText("Edit your profile");
    }
  }

  public void onSaveButton(ActionEvent actionEvent)
      throws IOException, SQLException
  {
    if(picFile==null)
    picFile = profile.getPicFile();

    if(usernameCreationField.getText().isEmpty() || passwordCreationField.getText().isEmpty())
    {
      errorLabel.setText("Type in the username and password");
    }
    else if(usernameCreationField.getText().length()<3|| passwordCreationField.getText().length()<3)
    {
      errorLabel.setText("Username and password have to contain at least 3 signs ");
    }
    else if(!passwordCreationField.getText().equals(passwordRepeatField.getText()) )
    {
      errorLabel.setText("Passwords don't match :( , try to type them again");
      passwordCreationField.clear();
      passwordRepeatField.clear();
    }
    else if(!vm.save(profile.getUsername(),usernameCreationField.getText(),passwordCreationField.getText(),picFile,bytes,descriptionArea.getText(),profile.getSubs()))
    {
      errorLabel.setText("Username already exists!");
    }

  }
}
