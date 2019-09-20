/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_project.View;

import c195_project.MainApp;
import c195_project.Model.Appointment;
import c195_project.Model.DB_Connection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class ModAppointmentScreenController implements Initializable {
    
        
        private Stage dialogueStage;
        private MainApp mainApp;
        
//  ----------------------------- Screen Methods -------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        popModTextFields();
    }
        
    public void setDialogueStage(Stage dialogueStage) {
        dialogueStage = this.dialogueStage;
    }
    
    // references mainApp
    public void setMainApp(MainApp mainApp) {
        mainApp = this.mainApp;
    }
// ------------------------------------ End ------------------------------------
    
// ----------------------------------- Alerts ----------------------------------
private Alert alertI = new Alert(Alert.AlertType.INFORMATION);
private Alert alertE = new Alert(Alert.AlertType.ERROR);
private Alert alertC = new Alert(Alert.AlertType.CONFIRMATION);
// ------------------------------------- End -----------------------------------

    
    
// ------------------------------- Screen Elements -----------------------------
    
    @FXML // fx:id="apptGrid"
    private GridPane apptGrid;
    
    // ------------------------------- Labels ------------------------------    
    @FXML // fx:id="apptDetailLbl"
    private Label apptDetailLbl;
    @FXML // fx:id="custIdLbl"
    private Label custIdLbl;
    @FXML // fx:id="apptLocLbl"
    private Label apptLocLbl;
    @FXML // fx:id="apptContLbl"
    private Label apptContLbl;
    @FXML // fx:id="createdByLbl"
    private Label createdByLbl;
    @FXML // fx:id="lastUpdateLbl"
    private Label lastUpdateLbl;
    @FXML // fx:id="apptIdLbl"
    private Label apptIdLbl;
    @FXML // fx:id="startTimeLbl"
    private Label startTimeLbl;
    @FXML // fx:id="endTimeLbl"
    private Label endTimeLbl;
    @FXML // fx:id="apptDescLbl"
    private Label apptDescLbl;
    @FXML // fx:id="lastUpdateByLbl"
    private Label lastUpdateByLbl;
    @FXML // fx:id="apptDateLbl"
    private Label apptDateLbl;
    // -------------------------------- End --------------------------------
    
    // ---------------------------- Text Fields ----------------------------    
    @FXML // fx:id="custIdField"
    private TextField custIdField;
    @FXML // fx:id="apptLocField"
    private TextField apptLocField;
    @FXML // fx:id="apptContField"
    private TextField apptContField;
    @FXML // fx:id="createdByField"
    private TextField createdByField;
    @FXML // fx:id="lastUpdateField"
    private TextField lastUpdateField;
    @FXML // fx:id="apptIdField"
    private TextField apptIdField;
    @FXML // fx:id="startTimeField"
    private TextField startTimeField;
    @FXML // fx:id="endTimeField"
    private TextField endTimeField;
    @FXML // fx:id="lastUpdateByField"
    private TextField lastUpdateByField;
    @FXML // fx:id="apptDateField"
    private TextField apptDateField;
    @FXML // fx:id="descTextArea"
    private TextArea descTextArea;
    // -------------------------------- End --------------------------------
    
    @FXML // fx:id="saveBtn"
    private Button saveBtn;
    
    @FXML // fx:id="cancelBtn"
    private Button cancelBtn;

// ----------------------------------- End -------------------------------------
    
// -------------------------- Data Processing Variables ------------------------
    private AppointmentScreenController asc;
    
    private Appointment selectedAppointment = Appointment.selectedAppointment;
    // ---------------------- Appointment Data Fields ----------------------
    // ---------- (Yeah, I know. There's auditing fields mixed in... -------
//    private String title = selectedAppointment.titleProperty().getValueSafe();
    private String loc = selectedAppointment.locProperty().getValueSafe();
    private String contact = selectedAppointment.contactProperty().getValueSafe();
    private String createdBy = selectedAppointment.createdByProperty().getValueSafe();
    private LocalDateTime lastUpdate = selectedAppointment.lastUpdateProperty().getValue();
    private String lastUpdateBy = selectedAppointment.lastUpdateByProperty().getValueSafe();
    private LocalDate apptDate = selectedAppointment.apptDateProperty().getValue();
    private LocalTime start = selectedAppointment.startProperty().getValue();
    private LocalTime end = selectedAppointment.endProperty().getValue();
    private String desc = selectedAppointment.descProperty().getValueSafe();
    private int custID = selectedAppointment.custIdProperty().getValue();
    private int apptID = selectedAppointment.apptIdProperty().getValue();
    // -------------------------------- End --------------------------------    
    
    // -------------------------- Populate the Textfields --------------------------
    private void popModTextFields() {
        
        apptLocField.setText(loc);
        apptContField.setText(contact);
        createdByField.setText(createdBy);
        lastUpdateField.setText(lastUpdate.toString());
        lastUpdateByField.setText(lastUpdateBy);
        apptDateField.setText(apptDate.toString());
        startTimeField.setText(start.toString());        
        endTimeField.setText(end.toString());
        descTextArea.setText(desc);
        custIdField.setText(Integer.toString(custID));
        apptIdField.setText(Integer.toString(apptID));
    }
// ----------------------------------- End -------------------------------------

    
    private void noWayJosé() {
        
        String locText = apptLocField.getText();
        String contactText = apptContField.getText();
        String createdByText = createdByField.getText();
        String lastUpdateText = lastUpdateField.getText();
        String lastUpdateByText = lastUpdateByField.getText();
        String apptDateText = apptDateField.getText();
        String apptStartTimeText = startTimeField.getText();
        String apptEndTimeText = endTimeField.getText();
        String descAreaText = descTextArea.getText();
           int custId = Integer.parseInt(custIdField.getText());
           int apptId = Integer.parseInt(apptIdField.getText());
        
        

        if(
                                     loc.equals(locText) &&
                             contact.equals(contactText) &&
                         createdBy.equals(createdByText) &&
            lastUpdate.toString().equals(lastUpdateText) &&
                   lastUpdateBy.equals(lastUpdateByText) &&
                apptDate.toString().equals(apptDateText) &&
              start.toString().equals(apptStartTimeText) &&
                  end.toString().equals(apptEndTimeText) &&
                               desc.equals(descAreaText) &&
                                        custID == custId &&
                                        apptID == apptId  )
                {
            
            alertC.setTitle("Nothing To Save");
            alertC.setHeaderText("Nothing has been changed...");
            alertC.setContentText("No customer data has been changed.  No customer\n" +
                                  "data will be altered. ");
            alertC.showAndWait();
            
            dialogueStage.close();            
            
        } else {
            
            alertC.setTitle("Review Modified Data");
            alertC.setHeaderText("Please review the modified customer information.\n\n" +
                                 "Appointment Location: \t\t\t" + apptLocField.getText() + "\n" +
                                  "Appointment Contact: \t\t\t" + apptContField.getText() + "\n" +
                                             "Created By: \t\t" + createdByField.getText() + "\n" +
                                          "Last Update: \t\t\t" + lastUpdateField.getText() + "\n" + 
                                       "Last Update By: \t\t\t" + lastUpdateByField.getText() + "\n" +
                                         "Appointment Date: \t" + apptDateField.getText() + "\n" + 
                               "Appointment Start Time: \t\t\t" + startTimeField.getText() + "\n\n" +
                                 "Appointment End Time: \t\t\t" + endTimeField.getText() + "\n\n" +
                              "Appointment Description: \t\t\t" + descTextArea.getText() + "\n\n" +
                                          "Customer ID: \t\t\t" + custIdField.getText() + "\n\n" +
                                       "Appointment ID: \t\t\t" + apptIdField.getText() + "\n\n" +
                                       "please make any necessary corrections before submitting.");
        alertC.showAndWait().ifPresent(response -> {
                                            if(response == ButtonType.OK) {
                                    addAppointmentData(selectedAppointment); // Updates the DB
                                                dialogueStage.close();
                                            } else if(response == ButtonType.CANCEL) {
                                                alertC.close();
                                            }
                                        });
                }
    }        
        
        
         
    private void addAppointmentData(Appointment appt) {
            
            PreparedStatement custDetails;
            
            try {
                
                lastUpdate = LocalDateTime.now();
                
                String sql = "UPDATE customer cu, address ad, city ci, country co " +
                             "SET cu.customerName=?, ad.address=?, ad.address2=?, ci.city=?, ad.phone=?, co.country=?, ad.postalCode=? " + 
                             "WHERE cu.customerId=? AND cu.addressId=ad.addressId AND ad.cityId=ci.cityId AND ci.countryId=co.countryId";
                
                custDetails = DB_Connection.getConn().prepareStatement(sql);
                
                DB_Connection.getConn().commit();
//                custDetails.setString(1, custName);
//                custDetails.setString(2, custAddy);
//                custDetails.setString(3, custAddy2);
//                custDetails.setString(4, custCity);
//                custDetails.setString(5, custPhone);
//                custDetails.setString(6, custCountry);
//                custDetails.setString(7, custPostal);
//                   custDetails.setInt(8, custId);
                
                
                int res = custDetails.executeUpdate();
                
                if (res == 1) {//one row was affected; namely the one that was inserted!
                    System.out.println("YAY!");
                } else {
                    System.out.println("BOO!");
                }
                
                alertC.setTitle("Confirmation");
                alertC.setHeaderText("Database update successful.");
                alertC.setContentText("Congratulations!! \n" +
                                      "The appointment was successfully updated \n" +
                                      "in the database.");
                alertC.showAndWait();
                
            } catch (SQLException e) {
                
                alertE.setTitle("Database Update Error");
                alertE.setHeaderText("There was a problem updating the database...");
                alertE.setContentText("Please review the error message and make the \n" +
                                      "necessary corrections to update the database. \n\n" +
                                      e.getMessage());
                alertE.showAndWait();
                
                e.printStackTrace();
            } finally {
                
            }
    }
    
// ----------------------------------- End -------------------------------------
            
    
    
// -------------------------- Data Processing Methods --------------------------
   @FXML // onAction=#handleSave
   private void handleSave() {
       
   }
   
    @FXML // onAction=#handleCancel
    private void handleCancel() {
        dialogueStage.close();
        alertC.setTitle("BYE!");
        alertC.setHeaderText("C-Ya!!");
        alertC.setContentText("See you later alligator!");
        alertC.showAndWait();
    }
    
// ------------------------------------ End ------------------------------------
    
    
}
