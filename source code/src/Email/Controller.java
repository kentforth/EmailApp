package Email;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;




public class Controller implements Initializable {

  //initialize fx:id fields
  @FXML
  private TextField name, lastName, company, department;
  @FXML
  private TextField displayName, displayEmail, displayPassword;
   @FXML
  private Label errorName, errorLastName, errorCompany, errorDepartment;

  //Method that doesn't show result for all items when clicking a button
  private void displayNone() {
    displayName.setText("");
    displayEmail.setText("");
    displayPassword.setText("");

  }

  //initialize variables for password
  private String password;
  private int defaultPasswordLength = 12;



  //Generate a random password
  private String randomPassword(int length) {
    String passwordSet = "abcdefghiklmnopqrstuwxyz0123456789";
    char[] password = new char[length];
    for (int i = 0; i < defaultPasswordLength; i++) {
      int rand = (int) (Math.random() * passwordSet.length());
      password[i] = passwordSet.charAt(rand);
    }
    return new String (password);
  }

  public String getPassword() {return password;}

  //Method that make all logical operations and shows result by clicking the button
  @FXML
  private void showResult(ActionEvent event) {


    //Take input from text fields
    String names = name.getText();
    String lastnames = lastName.getText();
    String companies = company.getText();
    String departments = department.getText();



    //adding the method checkFields to every field, just to simplify the code
    checkFields(name, errorName);
    checkFields(lastName, errorLastName);
    checkFields(company, errorCompany);

    //check if the Department field is empty
    if (department.getText() == null || department.getText().trim().isEmpty())
    {
      displayNone();
    }
    else {
      department.getStyleClass().removeAll("error-field");
      errorDepartment.setText("");
    }

    //create massive that includes all departments and numbers for an each department
    String[] depChoice = new String[6];
    depChoice[0] = "";
    depChoice[1] = "sales";
    depChoice[2] = "dev";
    depChoice[3] = "acc";
    depChoice[4] = "man";
    depChoice[5] = "adm";

    //catching exception NumberOfFormat exception. if user print nothing in textfield
    try {


      //parsing text from Department field to integer
      int parsedInt = Integer.parseInt(department.getText());
      //Checking if user  entered a number in the department textfield  0-5
      if (0 <= parsedInt && parsedInt < 6) {


        //if the number is correct display result in textarea
        displayName.setText("Name: " + names + " " + lastnames);
        displayEmail.setText("Email: " + names.toLowerCase() + "." + lastnames.toLowerCase() + "@" + depChoice[parsedInt] + "." + companies.toLowerCase() + ".ru");
        displayPassword.setText("Password: " + password.toLowerCase());

        //calling method Checkfield
        checkFields(name, errorName);
        checkFields(lastName, errorLastName);
        checkFields(company, errorCompany);
        checkFields(department, errorDepartment);
      } else {
        //if a number is not correct - don't show anything in text area and highlight the department field
        displayNone();
        errorDepartment.setText("Only 0-5 number is accepted!");
        department.getStyleClass().add("error-field");

      }
    } catch(NumberFormatException nfe) {
      errorDepartment.setText("Only 0-5 number is accepted!");
      department.getStyleClass().add("error-field");
    }

  }
  //Method that checks all fields except Department field
  private void checkFields(TextField lastName, Label errorLastName) {
    if (lastName.getText() == null || lastName.getText().trim().isEmpty())  {
      displayNone();
      errorLastName.setText("Field is empty!");
      lastName.getStyleClass().add("error-field");
    } else {
      lastName.getStyleClass().removeAll("error-field");
      errorLastName.setText("");
    }
  }

  //Method that provides access to FXML variables
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //Call a method that returns a random password
    this.password = randomPassword(defaultPasswordLength);
  }

}
