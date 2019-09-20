/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_project.View;

import c195_project.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JJ_2
 */
public class AddAppointmentScreenController implements Initializable {
    
        private Stage dialogueStage;
        private MainApp mainApp;
        
//  ----------------------------- Screen Methods -------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
    }
        
    public void SetDialogueStage(Stage dialogueStage) {
        this.dialogueStage = dialogueStage;
    }
    
    // references mainApp
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
// ----------------------------------- Alerts ----------------------------------
    private Alert alertI = new Alert(Alert.AlertType.INFORMATION);
    private Alert alertE = new Alert(Alert.AlertType.ERROR);
    private Alert alertC = new Alert(Alert.AlertType.CONFIRMATION);
// ------------------------------------- End -----------------------------------


        
// ----------------------------------- End -------------------------------------        
    @FXML // fx:id="apptGrid"
    private GridPane apptGrid;
    @FXML // fx:id="apptDetailLbl"
    private Label apptDetailLbl;
    @FXML // fx:id="descTextArea"
    private TextArea descTextArea;
    @FXML // fx:id="custIdLbl"
    private Label custIdLbl;
    @FXML // fx:id="custIdField"
    private TextField custIdField;
    @FXML // fx:id="apptDateLbl"
    private Label apptDateLbl;
    @FXML // fx:id="apptLocLbl"
    private Label apptLocLbl;
    @FXML // fx:id="apptLocField"
    private TextField apptLocField;
    @FXML // fx:id="apptContLbl"
    private Label apptContLbl;
    @FXML // fx:id="apptContField"
    private TextField apptContField;
    @FXML // fx:id="createdByLbl"
    private Label createdByLbl;
    @FXML // fx:id="createdByField"
    private TextField createdByField;
    @FXML // fx:id="lastUpdateLbl"
    private Label lastUpdateLbl;
    @FXML // fx:id="lastUpdateField"
    private TextField lastUpdateField;
    @FXML // fx:id="apptIdLbl"
    private Label apptIdLbl;
    @FXML // fx:id="apptIdField"
    private TextField apptIdField;
    @FXML // fx:id="startTimeLbl"
    private Label startTimeLbl;
    @FXML // fx:id="startTimeField"
    private TextField startTimeField;
    @FXML // fx:id="endTimeLbl"
    private Label endTimeLbl;
    @FXML // fx:id="endTimeField"
    private TextField endTimeField;
    @FXML // fx:id="apptDescLbl"
    private Label apptDescLbl;
    @FXML // fx:id="lastUpdateByField"
    private TextField lastUpdateByField;
    @FXML // fx:id="lastUpdateByLbl"
    private Label lastUpdateByLbl;
    @FXML // fx:id="apptDateField"
    private TextField apptDateField;
// ------------------------------------ End ------------------------------------
    
// -------------------------- Data Processing Methods --------------------------
   @FXML // onAction=#handleSave
   private void handleSave() {
       alertI.setTitle("No Method Found");
       alertI.setHeaderText("There is currently no method for saving...");
       alertI.setContentText("Please define a method for saving the data and\n" +
                             "updating the DB...\n\n" +
                             "Then try to create a new appointment...");
       alertI.showAndWait();
       dialogueStage.close();
       
   }
   
   @FXML // onAction=#handleCancel
   private void handleCancel() {
       alertC.setTitle("Outta Here");
       alertC.setHeaderText("You have chosen not to set a new appointment.");
       alertC.setContentText("You have chosen not to schedule a new appointment.\n" +
                             "We hope you have a wonderful day!  :)");
       alertC.showAndWait().ifPresent(response -> {
                            if(response == ButtonType.OK) {
                                alertC.close();
                                dialogueStage.close();
                            } else if(response == ButtonType.CANCEL) {
                                alertC.close();
                            }
       });
   }
    
// ------------------------------------ End ------------------------------------
}
