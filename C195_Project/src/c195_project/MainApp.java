    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_project;

// ----------------------------------- Imports ---------------------------------
import c195_project.View.AddAppointmentScreenController;
import c195_project.View.LoginScreenController;
import c195_project.View.AppointmentScreenController;
import c195_project.View.ModAppointmentScreenController;
import c195_project.View.ModCustomerScreenController;
import c195_project.View.NewCustomerScreenController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
// ------------------------------------- End -----------------------------------
/**
 *
 * @author JJ_2
 */
public class MainApp extends Application {
    
// ------------------------------- Global Variables ----------------------------
    private double initX;
    private double initY;
    private boolean isSaveClicked;
    private Stage primaryStage;
    private BorderPane rootLayout;
    private LoginScreenController lsc;
    private MainApp mainApp;
// ------------------------------------- End -----------------------------------
    
    
// ------------------------------- Screen Methods ------------------------------
    public MainApp() {
    } 
    
    private void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setDialogueStage(Stage dialogueStage) {
        // Set primaryStage to dialogueStage
        this.primaryStage = dialogueStage;
        // Show the stage and wait for user input.
        dialogueStage.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) {
        
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Global Consulting, LLC.");
        
        initRootLayout();
        showLoginScreen();
//        showAppointmentScreen();
//        showNewCustomerScreen();
    }
// ------------------------------------- End -----------------------------------

    
    
// ------------------------ Initialize the Root Layout -------------------------
    public void initRootLayout() {
        try {
            // Create a new FXML Loader.
            FXMLLoader loader = new FXMLLoader();
            // Load rootLayout from fxml file.
            loader.setLocation(MainApp.class.getResource("View/RootLayout.fxml"));
            // Load  the RootLayout fxml file into the BorderPane.
            rootLayout = (BorderPane) loader.load();
            
            // Create the scene for the AnchorPane, 'rootLayout'.
            Scene scene = new Scene(rootLayout);
            // Set its background to transparent
//            scene.setFill(Color.TRANSPARENT);
            // Set the scene for primaryStage.
            primaryStage.setScene(scene);
            // Show the stage.
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Root Layout Error");
            alert.setHeaderText("The root layout contained in 'RootLayout.fxml' failed to load...");
            alert.setContentText("Check the pathing to ensure it's pointing to the correct file... \n\n" +
                                 e.getMessage());
            alert.show();
        }
    }
// ------------------------------------- End -----------------------------------
    
    
    
// ------------------------------- Show Login Screen ---------------------------
    public void showLoginScreen() {
        
        try {
            // Create a new FXMLLoader...
            FXMLLoader loader = new FXMLLoader();
            // Set the location of the fxml file the loader will load.
            loader.setLocation(MainApp.class.getResource("View/LoginScreen.fxml"));
            // Load LoginScreen.fxml into the AnchorPane.
            AnchorPane loginScreen = (AnchorPane) loader.load();
            
            // set the LoginScreen in the center of the scene
            rootLayout.setCenter(loginScreen);
            
            // Give the controller access to the Login scene
           LoginScreenController controllerLIS = loader.getController();
           // set LoginScreen as main app
           controllerLIS.setMainApp(this);            
            
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Load Error...");
            alert.setHeaderText("LoginScreen failed to load!!");
            alert.setContentText("The login screen failed to load." + "\n" +
                                "Your code is broken. You should fix it... \n\n" +
                                e.getMessage());
            alert.show();
        }
    }
// ------------------------------------- End -----------------------------------

    

// --------------------------- Show Appointment Screen -------------------------
    public void showAppointmentScreen() {

        try {
            // Create a new FXML Loader.
            FXMLLoader loader = new FXMLLoader();
            // Load AddAppointment from fxml file.
            loader.setLocation(MainApp.class.getResource("View/AppointmentScreen.fxml"));
            // Load the AddAppointment fxml file into the AnchorPane.
            AnchorPane appointmentScreen = (AnchorPane) loader.load();
            
            // set the AppointmentScreen in the center of the scene
            rootLayout.setCenter(appointmentScreen);

            // give the controller access to the scene
            AppointmentScreenController controllerAS = loader.getController();
            // set AppointmentScreen as MainApp.
            controllerAS.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error...");
            alert.setHeaderText("Error loading screen...");
            alert.setContentText("There was an error loading the screen.\n\n\n" + 
                                 "Error message: " +
                                 e.getMessage());
            alert.showAndWait();
        }
    }
// ------------------------------------- End -----------------------------------
    
// --------------------------- Show New Customer Screen ------------------------
public void showNewCustomerScreen() {
    try {
            // Create a new FXML Loader.
            FXMLLoader loader = new FXMLLoader();
            // Set the location of the fxml file to load.
            loader.setLocation(MainApp.class.getResource("View/NewCustomerScreen.fxml"));
            // Load the NewCustomerScreen fxml file into the AnchorPane.
            AnchorPane newCustomerScreen = (AnchorPane) loader.load();

            // give the controller access to the scene.
            NewCustomerScreenController controllerNCS = loader.getController();
            // set NewCustomerScreen as MainApp.
            controllerNCS.setMainApp(this);
            // create the dialogue stage.
            Stage dialogueStage = new Stage();
//            dialogueStage.initStyle(StageStyle.UNDECORATED);
            // set the stage title.
            dialogueStage.setTitle("Add New Customer Screen");
            // set the window modality
            dialogueStage.initModality(Modality.WINDOW_MODAL);
            // set primaryStage as the owner of the dialogueStage (links them)
            dialogueStage.initOwner(primaryStage);
            // create the scene for the new customer screen
            Scene sceneNCS = new Scene(newCustomerScreen);
            // set the scene for the dialogueStage
            dialogueStage.setScene(sceneNCS);
            
            // give the controller access to the New Customer Screen
//            NewCustomerScreenController controllerNCS2 = loader.getController();
            // set the stage for controllerNCS
            controllerNCS.SetDialogueStage(dialogueStage);
//            // sets New Customer Screen as mainApp
//            controllerNCS.setMainApp(this);
            // show the stage and wait for the user input
            dialogueStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error...");
            alert.setHeaderText("Error loading screen...");
            alert.setContentText("There was an error loading the screen. \n" + 
                                 "Check the Stack Trace for details... \n\n" +
                                 e.getMessage());
            alert.showAndWait();
        }
}
// ------------------------------------- End -----------------------------------
// --------------------------- Show New Customer Screen ------------------------
public void showModCustomerScreen() {
    try {
        // Create a new FXML Loader
        FXMLLoader loader = new FXMLLoader();
        // Load ModCustomerScreen from fxml file
        loader.setLocation(MainApp.class.getResource("View/ModCustomerScreen.fxml"));
        // Load the ModCustomerScreen.fxml file into the AnchorPane
        AnchorPane modCustomerScreen = (AnchorPane) loader.load();
        
        // Give the controller access to the scene
        ModCustomerScreenController controllerMCS = loader.getController();
        // Set ModCustomerScreen as mainApp
        controllerMCS.setMainApp(this);
        // Create the dialogueStage
        Stage dialogueStage = new Stage();
        // Set the title of the dialogueStage
        dialogueStage.setTitle("Modify Customer Screen");
        // Set the window modality
        dialogueStage.initModality(Modality.WINDOW_MODAL);
        // Set primaryStage as the owner of the window (links them)
        dialogueStage.initOwner(primaryStage);
        // Create the scene for the ModCustomer screen
        Scene sceneMCS = new Scene(modCustomerScreen);
        // Set the scene for the dialogueStage
        dialogueStage.setScene(sceneMCS);
        // Set the stage for controllerMCS
        controllerMCS.SetDialogueStage(dialogueStage);
        // Show the dialogueStage and wait for user input.
        dialogueStage.showAndWait();
        
    } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error...");
            alert.setHeaderText("Error loading screen...");
            alert.setContentText("There was an error loading the screen. \n" + 
                                 "Check the Stack Trace for details... \n\n" +
                                 e.getMessage());
            alert.showAndWait();
        }
}
// ------------------------------------- End -----------------------------------

// -------------------------- Show Add Appointment Screen ----------------------
public void showAddAppointmentScreen() {
    try {
        // Create a new FXML Loader
        FXMLLoader loader = new FXMLLoader();
        // Load AddAppointmentScreen from fxml file
        loader.setLocation(MainApp.class.getResource("View/AddAppointmentScreen.fxml"));
        // Load AddAppointmentScreen.fxml file into the AnchorPane
        AnchorPane addAppointmentScreen = (AnchorPane) loader.load();
        
        // Give the controller access to the scene
        AddAppointmentScreenController controllerAAS = loader.getController();
        // Set AddAppointmentScreen as mainApp
        controllerAAS.setMainApp(this);
        // Create the dialogueStage
        Stage dialogueStage = new Stage();
        // Set the title of the dialogueStage
        dialogueStage.setTitle("New Appointment Screen");
        // Set the window modality
        dialogueStage.initModality(Modality.WINDOW_MODAL);
        // Set primaryStage as the owner of the window (links them)
        dialogueStage.initOwner(primaryStage);
        // Create the scene for the AddAppointment screen
        Scene sceneAAS = new Scene(addAppointmentScreen);
        // Set the scene for the dialogueStage
        dialogueStage.setScene(sceneAAS);
        // Set the stage for controllerAAS
        controllerAAS.SetDialogueStage(dialogueStage);
        // Show the dialogueStage and wait for user input
        dialogueStage.showAndWait();
        
    } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error...");
            alert.setHeaderText("Error loading screen...");
            alert.setContentText("There was an error loading the screen. \n" + 
                                 "Check the Stack Trace for details... \n\n" +
                                 e.getMessage());
            alert.showAndWait();
        }
}
// ------------------------------------- End -----------------------------------

// -------------------------- Show Mod Appointment Screen ----------------------
public void showModAppointmentScreen() {
    try {
        // Create a new FXML Loader
        FXMLLoader loader = new FXMLLoader();
        // Load ModAppointmentScreen from fxml file
        loader.setLocation(MainApp.class.getResource("View/ModAppointmentScreen.fxml"));
        // Load ModAppointmentScreen.fxml file into the AnchorPane
        AnchorPane modAppointmentScreen = (AnchorPane) loader.load();
        
        // Give the controller access to the scene
        ModAppointmentScreenController controllerDB_P = loader.getController();
        // Set ModAppointmentScreen as mainApp
        controllerDB_P.setMainApp(this);
        // Create the dialogueStage
        Stage dialogueStage = new Stage();
        // Set the title of the dialogueStage
        dialogueStage.setTitle("Modify Appointment Screen");
        // Set the window modality
        dialogueStage.initModality(Modality.WINDOW_MODAL);
        // Set primaryStage as the owner of the window (links them)
        dialogueStage.initOwner(primaryStage);
        // Create the scene for the ModAppointment screen
        Scene sceneAAS = new Scene(modAppointmentScreen);
        // Set the scene for the dialogueStage
        dialogueStage.setScene(sceneAAS);
        // Set the stage for controllerDB_P
        controllerDB_P.setDialogueStage(dialogueStage);
        // Show the dialogueStage and wait for user input
        dialogueStage.showAndWait();
        
    } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error...");
            alert.setHeaderText("Error loading screen...");
            alert.setContentText("There was an error loading the screen. \n" + 
                                 "Check the Stack Trace for details... \n\n" +
                                 e.getMessage());
            alert.showAndWait();
        }
}
// ------------------------------------- End -----------------------------------

// -------------------------- Show Mod Appointment Screen ----------------------
    private void showDB_Picker() {
        try {
            // Create a new FXML Loader
            FXMLLoader loader = new FXMLLoader();
            // Load DB_Picker from fxml file
            loader.setLocation(MainApp.class.getResource("View/DB_Picker.fxml"));
            // Load DB_Picker.fxml file into the AnchorPane
            AnchorPane DB_Picker = (AnchorPane) loader.load();

            // Give the controller access to the scene
            ModAppointmentScreenController controllerDB_P = loader.getController();
            // Set ModAppointmentScreen as mainApp
            controllerDB_P.setMainApp(this);
            // Create the dialogueStage
            Stage dialogueStage = new Stage();
            // Set the title of the dialogueStage
            dialogueStage.setTitle("Please Choose a Database");
            // Set the window modality
            dialogueStage.initModality(Modality.WINDOW_MODAL);
            // Set primaryStage as the owner of the window (links them)
            dialogueStage.initOwner(primaryStage);
            // Create the scene for the ModAppointment screen
            Scene sceneDB_P = new Scene(DB_Picker);
            // Set the scene for the dialogueStage
            dialogueStage.setScene(sceneDB_P);
            // Set the stage for controllerDB_P
            controllerDB_P.setDialogueStage(dialogueStage);
            // Show the dialogueStage and wait for user input
            dialogueStage.showAndWait();

    } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error...");
            alert.setHeaderText("Error loading screen...");
            alert.setContentText("There was an error loading the screen. \n" + 
                                 "Check the Stack Trace for details... \n\n" +
                                 e.getMessage());
            alert.showAndWait();
        }
}

// ------------------------------------- End -----------------------------------

    
}
