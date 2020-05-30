package client.views.logIn;

import client.core.ViewModelFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LogInControllerTest
{
  private LogInVM viewModel= ViewModelFactory.getInstance().getLogInVM();

  LogInControllerTest() throws NotBoundException, SQLException, IOException
  {
  }

  @Test void onLoginButtonWrongPassword() throws IOException, SQLException
  {
    viewModel.getUsernameField().set("Julia");
    viewModel.getPasswordField().set("WrongPassword");
    viewModel.logIn();
    assertEquals("Wrong password or username",viewModel.getErrorLabel().getValue());
  }
  @Test void onLoginButtonEmptyFields() throws IOException, SQLException
  {
    viewModel.logIn();
    assertEquals("Type in your username and password",viewModel.getErrorLabel().getValue());
  }

}