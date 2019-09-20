/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_project.View;

import c195_project.MainApp;
import c195_project.Model.Customer;
import c195_project.Model.DB_Connection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JJ_2
 */
public class ModCustomerScreenController implements Initializable {

    private Stage dialogueStage;
    private MainApp mainApp;
// -------------------------- Screen Methods -----------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DB_Connection.getConn();
        popModTextFields();        
    }
    
    public void SetDialogueStage(Stage dialogueStage) {
        this.dialogueStage = dialogueStage;
    }
    
    // references mainApp
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
// ------------------------------------- End -----------------------------------

// ----------------------------------- Alerts ----------------------------------
    private Alert alertI = new Alert(Alert.AlertType.INFORMATION);
    private Alert alertE = new Alert(Alert.AlertType.ERROR);
    private Alert alertC = new Alert(Alert.AlertType.CONFIRMATION);
// ------------------------------------- End -----------------------------------
    
    
// ------------------------------- Screen Elements -----------------------------
    // ------------------------------------------------------------------ Labels
    @FXML // fx:id="modCustLbl"
    private Label modCustLbl;
    @FXML // fx:id="nameLbl"
    private Label nameLbl;
    @FXML // fx:id="addyLbl"
    private Label addyLbl;
    @FXML // fx:id="addy2Lbl"
    private Label addy2Lbl;
    @FXML // fx:id="cityLbl"
    private Label cityLbl;
    @FXML // fx:id="countryLbl"
    private Label countryLbl;
    @FXML // fx:id="zipLbl"
    private Label zipLbl;
    @FXML // fx:id="phoneLbl"
    private Label phoneLbl;
    
    // -------------------------------------------------------------- TextFields
    @FXML // fx:id="nameField"
    private TextField nameField;
    @FXML // fx:id="addyField"
    private TextField addyField;
    @FXML // fx:id="addy2Field"
    private TextField addy2Field;
    @FXML // fx:id="cityField"
    private TextField cityField;
//    @FXML // fx:id="countryField"
//    private TextField countryField;
    @FXML // fx:id="postalField"
    private TextField postalField;
    @FXML // fx:id="phoneField"
    private TextField phoneField;
            
    @FXML // fx:id="countryBox"
    private ChoiceBox countryBox;
    // ----------------------------------------------------------------- Buttons
    @FXML // fx:id="saveBtn"
    private Button saveBtn;
    @FXML // fx:id="cancelBtn"
    private Button cancelBtn;
   
// ----------------------------------- End -------------------------------------
    
// -------------------------- Data Processing Variables ------------------------
    private AppointmentScreenController asc;
    
    private Customer selectedCustomer = Customer.selectedCustomer;
    // ---------------------- Customer Data Fields -------------------------
    private String custName = selectedCustomer.custNameProperty().getValueSafe();
    private String custAddy = selectedCustomer.custAddyProperty().getValueSafe();
    private String custAddy2 = selectedCustomer.custAddy2Property().getValueSafe();
    private String custCity = selectedCustomer.custCityProperty().getValueSafe();
    private String custCountry = selectedCustomer.custCountryProperty().getValueSafe();
    private String custPostal = selectedCustomer.custPostalProperty().getValue().toString();
    private String custPhone = selectedCustomer.custPhoneProperty().getValueSafe();
    // ------------------------------- End ---------------------------------
    
    // ------------------------- Auditing Fields ---------------------------
    private int custId = selectedCustomer.custIdProperty().getValue();
    private int addyId = selectedCustomer.addyIdProperty().getValue();
    private int active = selectedCustomer.activeProperty().getValue();
    private LocalDateTime createDate = selectedCustomer.createDateProperty().getValue();
    private String createdBy = selectedCustomer.createdByProperty().getValueSafe();
    private LocalDateTime lastUpdate = LocalDateTime.now();
    private String lastUpdatedBy = selectedCustomer.lastUpdateByProperty().getValueSafe();
    // ------------------------------- End ---------------------------------
    
// ----------------------------------- End -------------------------------------
    
    
// -------------------------- Populate the Textfields --------------------------
    private void popModTextFields() {
        
        nameField.setText(custName);
        addyField.setText(custAddy);
        addy2Field.setText(custAddy2);
        cityField.setText(custCity);
//        countryBox.setItems(value);
        postalField.setText(custPostal);
        phoneField.setText(custPhone);

    }
    
    private void getModTextFields() {
        custName = nameField.getText();
        custId = selectedCustomer.custIdProperty().getValue();
    }
    
    
// ----------------------------------- End -------------------------------------
    
// -------------------------- Data Processing Methods --------------------------sd
    @FXML // onAction=#handleSave
    private void handleSave() {
        
//        selectedCustomer = Customer.selectedCustomer;        
        
        String nameFieldText = nameField.getText();
        String addyFieldText = addyField.getText();
        String addy2FieldText = addy2Field.getText();
        String cityFieldText = cityField.getText();
//        String countryFieldText = countryField.getText();
        String postalFieldText = postalField.getText();
        String phoneFieldText = phoneField.getText();
        
        selectedCustomer.setCustName(nameFieldText);
        selectedCustomer.setCustAddy(addyFieldText);
        selectedCustomer.setCustAddy2(addy2FieldText);
        selectedCustomer.setCustCity(cityFieldText);
//        selectedCustomer.setCustCountry(countryFieldText);
        selectedCustomer.setCustPostal(Integer.parseInt(postalFieldText));
        selectedCustomer.setCustPhone(phoneFieldText);
        
        selectedCustomer.setCustId(custId);
        selectedCustomer.setAddyId(addyId);
        selectedCustomer.setActive(active);
        selectedCustomer.setCreateDate(createDate);
        selectedCustomer.setCreatedBy(createdBy);
        selectedCustomer.setLastUpdate(lastUpdate);
        selectedCustomer.setLastUpdateBy(lastUpdatedBy);
        
        
        
        // Checks to see if any textual changes have been made
        noWayJosé(); // to the textfields. If no changes, alerts user, then closes.
        
        String sqlAddCust; // Create the statement to alter the database table and update the values...
        
//        alertE.setTitle("No Method() Found");
//        alertE.setHeaderText("No method() can be found for this object.");
//        alertE.setContentText("Please define the handleSave() method in order" + "\n" +
//                              "for this button to work correctly.");
//        alertE.showAndWait();
    }
    
    @FXML // onAction=#handleCancel
    private void handleCancel() {
        alertI.setTitle("BYE!");
        alertI.setHeaderText("C-Ya!!");
        alertI.setContentText("See you later alligator!");
        alertI.showAndWait();
        dialogueStage.close();
    }
    
    private void noWayJosé() {
        
        String nameFieldText = nameField.getText();
        String addyFieldText = addyField.getText();
        String addy2FieldText = addy2Field.getText();
        String cityFieldText = cityField.getText();
//        String countryFieldText = countryField.getText();
        String postalFieldText = postalField.getText();
        String phoneFieldText = phoneField.getText();

        if(
                custName.equals(nameFieldText) &&
                custAddy.equals(addyFieldText) &&
                custAddy2.equals(addy2FieldText) &&
                custCity.equals(cityFieldText) &&
//                custCountry.equals(countryFieldText) &&
                custPostal.equals(postalFieldText) &&
                custPhone.equals(phoneFieldText)) {
            
            alertC.setTitle("Nothing To Save");
            alertC.setHeaderText("Nothing has been changed...");
            alertC.setContentText("No customer data has been changed.  No customer\n" +
                                  "data will be altered. ");
            alertC.showAndWait().ifPresent(response -> {
                                            if(response == ButtonType.OK) {
                                                alertI.close();
                                                dialogueStage.close();
                                            } else if(response == ButtonType.CANCEL) {
                                                alertI.close();
                                            }
                                        });            
        } else {
            
            alertI.setTitle("Review Modified Data");
            alertI.setHeaderText("Please review the modified customer information.\n\n" +
                                "Customer Name: \t\t\t" + nameField.getText() + "\n" +
                             "Customer Address: \t\t\t" + addyField.getText() + "\n" +
                             "Customer Address 2: \t\t" + addy2Field.getText() + "\n" +
                                "Customer City: \t\t\t" + cityField.getText() + "\n" + 
//                             "Customer Country: \t\t\t" + countryField.getText() + "\n" +
                             "Customer Postal Code: \t\t" + postalField.getText() + "\n" + 
                               "Customer Phone: \t\t\t" + phoneField.getText() + "\n\n" +
                             "please make any necessary corrections before submitting.");
        alertI.showAndWait().ifPresent(response -> {
                                            if(response == ButtonType.OK) {
                                    addCustomerData(selectedCustomer); // Updates the DB
                                                alertI.close();
                                                dialogueStage.close();
                                            } else if(response == ButtonType.CANCEL) {
                                                alertI.close();
                                            }
                                        });
        
        
        }
    }
    
    private void addCustomerData(Customer customer) {
            
            PreparedStatement custStmt;
            
            try {
                
                lastUpdate = LocalDateTime.now();
                
                // only place single quotes around string literals!!!
                String sql = "UPDATE customer SET customerName=? WHERE customerId=?";
                sql = "UPDATE customer SET customerName=? WHERE customerId=1";
                
                custStmt = DB_Connection.getConn().prepareStatement(sql);
                            custStmt.setString(1, nameField.getText());
//                            custStmt.setString(2, custAddy);
//                            custStmt.setString(3, custAddy2);
//                            custStmt.setString(4, custCity);
//                            custStmt.setString(5, custPhone);
//                            custStmt.setString(6, custCountry);
//                            custStmt.setString(7, custPostal);
//                               custStmt.setInt(1, selectedCustomer.custIdProperty().getValue());
                
                
                int res = custStmt.executeUpdate(sql);
                custStmt.executeUpdate(sql);
                DB_Connection.getConn().commit();
                
                if (res >= 2) {//one row was affected; namely the one that was inserted!
                    System.out.println("YAY!");
                } else {
                    System.out.println("BOO!");
                }
                
                alertI.setTitle("Confirmation");
                alertI.setHeaderText("Database update successful.");
                alertI.setContentText("Congratulations!! \n" +
                                      "Customer '" + customer.custNameProperty().getValueSafe() +"' " +
                                      "was successfully added to the database.");
                alertI.showAndWait();
                
            } catch (SQLException e) {
                
                alertE.setTitle("Database Update Error in addCustomerData()");
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
}
