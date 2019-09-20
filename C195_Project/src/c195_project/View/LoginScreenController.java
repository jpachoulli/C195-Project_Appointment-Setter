/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_project.View;

import c195_project.MainApp;
import c195_project.Model.DB_Connection;
import c195_project.Model.User;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author JJ_2
 */
public class LoginScreenController implements Initializable {
    
// --------------------------- Global Variables --------------------------------
    private MainApp mainApp;
    private Stage dialogueStage = new Stage();
    public AppointmentScreenController asc;
    public static String guiUserName;
    public static String guiPassword;
    private PreparedStatement psLogin;
    private ResultSet rsLogin;
    private static Connection dbConn;
    private static File log = new File("c195_project_login_report.txt");
    private DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh:mm");
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm");
    
//    private ObservableList<User> loggedInUser = FXCollections.observableArrayList();
    private User user;
    public User loggedInUser;
    
// ------------------------------------- End -----------------------------------
    
    

// ----------------------------------- Alerts ----------------------------------
    private Alert alertI = new Alert(Alert.AlertType.INFORMATION);
    private Alert alertE = new Alert(Alert.AlertType.ERROR);
    private Alert alertC = new Alert(Alert.AlertType.CONFIRMATION);
// ------------------------------------- End -----------------------------------

    
    
// -------------------------------- Screen Elements ----------------------------
    @FXML // fx:id="LoginScreen"
    private AnchorPane loginScreen;
    @FXML // fx:id="userNameField"
    private TextField userNameField;
    @FXML // fx:id="passwordField"
    private PasswordField passwordField;
    @FXML // fx:id="loginButton"
    private Button loginButton;
// ------------------------------------- End -----------------------------------
    @FXML
    private Label gcLoginText;

    
    
// ------------------------------- Sreen Methods -------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DB_Connection.init();
//        dbConn = DB_Connection.getConn();
        DB_Connection.getConn();
        user = new User();
    }

    // References MainApp
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
// ------------------------------------- End -----------------------------------
    
    
// --------------------- Query the Database ------------------------------------
    // ----------------- comment out to develop logic offline -----------------
    public boolean queryDb() {
        
        try {
        // need to surround the variables with single quotes ' '
            String sql = "SELECT * FROM user WHERE userName = '" +
                guiUserName + "' AND password = '" + guiPassword + "'";
        // for example, the actual SQL statement would look like:
        // "SELECT * FROM user WHERE userName = 'guiUserName' AND password = 'guiPassword';"

            psLogin = DB_Connection.getConn().prepareStatement(sql);
            rsLogin = psLogin.executeQuery();

            while(rsLogin.next()) {
                user.userIdProperty().setValue(rsLogin.getInt("userId"));
                user.userNameProperty().setValue(rsLogin.getString("userName"));
                user.passwordProperty().setValue(rsLogin.getString("password"));
                user.activeProperty().setValue(rsLogin.getInt("active"));
                user.createByProperty().setValue(rsLogin.getString("createBy"));
                user.createDateProperty().setValue(rsLogin.getDate("createDate").toLocalDate());
                user.lastUpdateProperty().setValue(rsLogin.getDate("lastUpdate").toLocalDate());
                user.lastUpdatedByProperty().setValue(rsLogin.getString("lastUpdatedBy"));
                
                user.activeProperty().setValue(1);
            }
            

        } catch (SQLException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL Error in 'queryDb()'");
            alert.setHeaderText("There is an error in your SQL statement.");
            alert.setContentText("Check your SQL.  You may need to rewrite \n" + 
                                 "your SQL statement. \n\n" +
                                 e.getMessage());
            alert.showAndWait();

            e.printStackTrace();
            return false;
        }
        
        return true;
    }
// ------------------------------------- End -----------------------------------
    
// ---------------------------------- DB_Picker --------------------------------
    private void DB_Picker() {
        
        
    }
// ------------------------------------- End -----------------------------------
    
// --------------------------------- Submit Login ------------------------------
    // --- comment out the DB code to develop the rest of the program offline --
    @FXML // onAction=#submitLogin
    private void submitLogin() {
        
        validateInput();
        
        guiUserName = userNameField.getText().trim();
        guiPassword = passwordField.getText().trim();

        if (queryDb()) {
            
            alertC.setTitle("Login Success");
            alertC.setHeaderText("Logging in user " + guiUserName + "...");
            alertC.setContentText("Please be patient whilst the system logs you in.");
            alertC.showAndWait();
            
            try {
                
                writeLog(log, true);
                
            } catch (IOException e) {
                
                e.getMessage();
                e.printStackTrace();
            }
            
            System.out.println("Logged in user: " + user.userNameProperty().getValueSafe() + "\n\n");
            mainApp.showAppointmentScreen();
                
        } else {
            alertE.setTitle("Login Failure");
            alertE.setHeaderText("The login failed due to invalid credentials.");
            alertE.setContentText("Please check the username/password combination"  + "\n" +
                                  "you are entering and ensure they are correct...");
            alertE.showAndWait();
            
            try {
                
                writeLog(log, false);
                
            } catch (IOException e) {
                
                e.getMessage();
                e.printStackTrace();
            }
            
            Platform.exit();
        }
        
    }
// ------------------------------------- End -----------------------------------
    
    
    
// ---------------------------- Validate the input -----------------------------
    public void validateInput() {

        if (userNameField.getText() == null) {
            alertE.setTitle("Username Field Error");
            alertE.setHeaderText("There seems to be an issue with the userName field.");
            alertE.setContentText("It does not seem to be holding any values." + "\n"
                    + "It will return null.  Double-check your code.");
            alertE.showAndWait();

        } else if (userNameField.getText().equalsIgnoreCase("")) {
            alertE.setTitle("Username Error");
            alertE.setHeaderText("The Username field is blank...");
            alertE.setContentText("You must input a username into the field." + "\n"
                    + "Please input only letters and/or numbers" + "\n"
                    + "and re-attempt your login submission.");
            alertE.showAndWait();

        } else if (userNameField.getText().equals("[^a-zA-Z0-9]")) {
            alertE.setTitle("Username Error");
            alertE.setHeaderText("The Username field contains invalid input...");
            alertE.setContentText("Only letters and numbers are allowed for the" + "\n"
                    + "username. Please input only letters and/or numbers" + "\n"
                    + "and re-attempt your login submission.");
            alertE.showAndWait();
        }

        if (passwordField.getText() == null) {
            alertE.setTitle("Password Field Error");
            alertE.setHeaderText("There seems to be an issue with the password field.");
            alertE.setContentText("It does not seem to be holding any values." + "\n"
                    + "It will return null.  Double-check your code.");
            alertE.showAndWait();

        } else if (passwordField.getText().equalsIgnoreCase("")) {
            alertE.setTitle("Password Error");
            alertE.setHeaderText("The Password field is blank...");
            alertE.setContentText("You must input a password into the field." + "\n"
                    + "Please input only letters and/or numbers" + "\n"
                    + "and re-attempt your login submission.");
            alertE.showAndWait();

        } else if (passwordField.getText().equals("[^a-zA-Z0-9]")) {
            alertE.setTitle("Password Error");
            alertE.setHeaderText("The Password field contains invalid input...");
            alertE.setContentText("Only letters and numbers are allwed for the" + "\n"
                    + "password. Please input only letters and/or numbers" + "\n"
                    + "and re-attempt your login submission.");
            alertE.showAndWait();
        }
    }
// ------------------------------------- End -----------------------------------
    
// ----------------------------- Setters and Getters ---------------------------
// ------------------------------------- End -----------------------------------

    
// ------------------------------ Test Input Fields ----------------------------    
    // ----- Maintenance code for when text fields were dishing out null values
    private void testInputFields() {
        // Textfields are not accepting or returning data.  They are reporting null...
        userNameField.setText("test");
        passwordField.setText("test");

        if (!userNameField.isVisible() || !passwordField.isVisible()) {
            alertE.setTitle("No Visible Fields");
            alertE.setHeaderText("The textfields are not visible!");
            alertE.setContentText("One or more of the input fields are not" + "\n"
                    + "visible to the program's code.  Please" + "\n"
                    + "make the fields visible and try again...");
            alertE.showAndWait();
        }

        if (userNameField.isDisabled() || passwordField.isDisable()) {

            alertE.setTitle("Textfield Error");
            alertE.setHeaderText("There is a problem with the textfields...");
            alertE.setContentText("Your input fields are disabled." + "\n"
                    + "Re-enable the fields for them to work.");
            alertE.showAndWait();

        } else if (guiUserName == null || guiPassword == null) {

            alertE.setTitle("NullPointerException");
            alertE.setHeaderText("There is no data in the fields.");
            alertE.setContentText("The input fields contain no data..." + "\n"
                    + "Find out why the fields are not holding data" + "\n"
                    + "and fix your code.");
            alertE.showAndWait();

        } else if (userNameField.getText() != null && passwordField.getText() != null) {

            alertI.setTitle("SUCCESS!!");
            alertI.setHeaderText("The textfields are working correctly...");
            alertI.setContentText("The 'Username' field returns " + guiUserName + "\n"
                    + "The 'Password' field returns " + guiPassword + "\n" + "\n"
                    + "The input fields are working correctly, congratulations!");
            alertI.showAndWait();

        }
    }
// ------------------------------------- End -----------------------------------


// ------------------------------ Get loggedInUser -----------------------------

    
    
// ------------------------------------- End -----------------------------------

// -------------------------- File Writing Handling Methods --------------------
private static void writeLog( File destination, Boolean status) throws IOException {
    
        String msgS = " Login success: \t" + status + "\n" +
                      " Timestamp: \t\t" + LocalDateTime.now() + "\n" +
                      " \n Successful Login!!\n\n" +
                      " User: \t\t" + guiUserName + "\n" + 
                      " Password: \t\t" + guiPassword;
        
        String msgF = " Login success: \t" + status + "\n" +
                      " Timestamp: \t\t" + LocalDateTime.now() + "\n" +
                      " \n Failed Login...\n\n" +
                      " User: \t\t" + guiUserName + "\n" + 
                      " Password: \t\t" + guiPassword;
        
        RandomAccessFile logF = new RandomAccessFile(log, "rw");
        
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
        
        Path filePath = Paths.get("c195_project_login_report.txt");
        
        if(status) {
                
                if(log.exists()) {
                    
                    logF.seek(0);
                    logF.writeUTF(msgS + "\n\n...allegedly this log existed and was appended... \n" +
                                         "...is that true?  Do you see more than one entry? \n\n" + 
                                         "Yes [ ]   Yes = working code \n\n" +
                                         "No  [ ]   No  = broken code");
                    logF.close();
                    
                    System.out.println("Success! Found the file and wrote " + 
                                       " the successful login attempt to it!!");
                    
                } else {
                    
                    log.createNewFile();
                    logF.seek(0);
                    logF.writeUTF(msgS + "\n\n...allegedly this log didn't exist and was created!");
                    logF.close();
                    
                    System.out.println("Success! Created the file and wrote " + 
                                       " the successful login attempt to it!!");
                    
                }
                
            } else {
                
                if(log.exists()) {
                    
                    writer.append(msgF  + "\n\n...allegedly this log existed and was appended... \n" +
                                         "...is that true?  Do you see more than one entry? \n\n" + 
                                         "Yes [ ]   Yes = working code \n\n" +
                                         "No  [ ]   No  = broken code");
                    writer.newLine();
                    writer.close();
                    System.out.println("Success! Found the file and wrote " + 
                                       " the failed login attempt to it!!");
                    
                } else {
                    
                    log.createNewFile();
                    writer.write(msgF);
                    writer.newLine();
                    writer.close();
                    System.out.println("Success! Created the file and wrote " + 
                                       " the failed login attempt to it!!");
                }
            }
        
    } catch (IOException e) {
        
        e.getMessage();
        e.printStackTrace();
    } 
}
    
// ------------------------------------- End -----------------------------------



}
