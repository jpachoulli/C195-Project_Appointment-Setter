/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_project.View;

import c195_project.MainApp;
import c195_project.Model.DB_Connection;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JJ_2
 */
public class NewCustomerScreenController implements Initializable {

    private Stage dialogueStage;
    private MainApp mainApp;
    
// -------------------------- Screen Methods -----------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DB_Connection.getConn();
        getCountries();
        countryBox.setItems(countryList);
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
    @FXML // fx:id="addNewCustLbl"
    private Label addNewCustLbl;
    @FXML // fx:id="nameLbl"
    private Label nameLbl;
    @FXML // fx:id="addyLbl"
    private Label addyLbl;
    @FXML // fx:id="addy2Lbl"
    private Label addy2Lbl;
    @FXML // fx:id="cityLbl"
    private Label cityLbl;
    @FXML // fx:id="zipLbl"
    private Label zipLbl;
    @FXML // fx:id="countryLbl"
    private Label countryLbl;
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
    @FXML // fx:id="zipField"
    private TextField zipField;
    @FXML // fx:id="phoneField"
    private TextField phoneField;

    // --------------------------------------------------------------- ChoiceBox
    @FXML // fx:id="countryBox"
    private ChoiceBox countryBox;
    private ObservableList<String> countryList = FXCollections.observableArrayList();
    // ----------------------------------------------------------------- Buttons
    @FXML // fx:id="saveBtn"
    private Button saveBtn;
    @FXML // fx:id="cancelBtn"
    private Button cancelBtn;
// ----------------------------------- End -------------------------------------
    
    
// ----------------------------- Global Variables ------------------------------
    private String name;
    private String addy;
    private String addy2;
    private String city;
    private String postal;
    private String phone;
    private LocalDateTime createDate = LocalDateTime.now();
    private String country;
    private LocalDateTime lastUpdate = LocalDateTime.now();
    
// ----------------------------------- End -------------------------------------
    
    
// -------------------------- Data Processing Methods --------------------------
    @FXML // onAction=#handleSave
    private void handleSave() {
        
        addCustomerData();
        
//        alertE.setTitle("No Method() Found");
//        alertE.setHeaderText("No method() can be found for this object.");
//        alertE.setContentText("Please define the handleSave() method in order" + "\n" +
//                              "for this button to work correctly.");
//        alertE.showAndWait();
    }
    
    @FXML // onAction=#handleCancel
    private void handleCancel() {
        alertC.setTitle("BYE!");
        alertC.setHeaderText("C-Ya!!");
        alertC.setContentText("See you later alligator!");
        alertC.showAndWait();
        dialogueStage.close();
        
    }
    
    private void setCustData() {
        
        
        name = nameField.getText();
        addy = addyField.getText();
        addy2 = addy2Field.getText();
        city = cityField.getText();
        postal = zipField.getText();
        countryBox.getSelectionModel().getSelectedItem();
        phone = phoneField.getText();
    }
    
    private ObservableList<String> getCountries() {
            try {
                
                String sql = "SELECT * FROM country";
                
                PreparedStatement ps = DB_Connection.getConn().prepareStatement(sql);
                try (ResultSet rs = ps.executeQuery(sql)) {
                    while(rs.next()) {
                        country = rs.getString("country");
                        countryList.add(country);
                    }
                }
                
            } catch (SQLException e) {
                
                alertE.setTitle("SQL Error in getCountries()");
                alertE.setHeaderText("There is an issue with the SQL statement...");
                alertE.setContentText("Please review the following error message\n" +
                                      "and make the necessary corrections to retrieve\n" +
                                      "the recorded countries from the database:\n\n" +
                                      e.getMessage());
                alertE.showAndWait();
                e.printStackTrace();
                
            }
            
            return countryList;
    }
    
    private void addCustomerData() {
        try {
            String insertCust = "INSERT INTO customer(customerId, customerName, addressId, active, createDate, createBy, lastUpdate, lastUpdateBy) VALUES(?, ?, ?, ?, ?, ?, ?, ?);\nsd" +            
                               "INSERT INTO address(addressId, address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) " +
                               "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?); " +
                               "INSERT INTO city(cityId, city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy) " +
                               "VALUES(?, ?, ?, ?, ?, ?, ?); " +
                               "INSERT INTO country(countryId, country, createDate, createdBy, lastUpdate, lastUpdateBy) " +
                               "VALUES(?, ?, ?, ?, ?, ?); " +
                               "COMMIT;";
            
//            String sql = "INSERT INTO customer c, address a, ci.city, co.country " +
//                         "(" +
//                          "a.addressId, a.address, a.address2, a.cityId, a.postalCode, a.phone, a.createDate, a.createdBy, a.lastUpdate, a.lastUpdateBy, " +
//                          "ci.cityId, ci.city, ci.countryId, ci.createDate, ci.createdBy, ci.lastUpdate, ci.lastUpdateBy, " +
//                          "co.countryId, co.country, co.createDate, co.createdBy, co.lastUpdate, co.lastUpdateBy) " +
//                          "WHERE c.addressId=a.addressId AND a.cityId=ci.cityId AND ci.countryId=co.countryId " +
//                          "AND c.createDate=a.createDate AND a.createDate=ci.createDate AND ci.createDate=co.createDate " +
//                          "AND c.createdBy=a.createdBy AND a.createdBy=ci.createdBy AND ci.createdBy=co.createdBy " +
//                          "AND c.lastUpdate=a.lastUpdate AND a.lastUpdate=ci.lastUpdate AND ci.lastUpdate=co.lastUpdate " +
//                          "AND c.lastUpdateBy=a.lastUpdateBy AND a.lastUpdateBy=ci.lastUpdateBy AND ci.lastUpdateBy=co.lastUpdateBy " +                      
//                          "VALUES " +
//                          "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        
            PreparedStatement ps;
            ps = DB_Connection.getConn().prepareStatement(insertCust);
            // ----------------------- Customer Data ---------------------------
                ps.setInt(1, 0);            // customerId (AUTO_INCREMENT)
                ps.setString(2, name);      // customerName
                ps.setInt(3, 1);            // addressId
                ps.setInt(4, 0);            // active
                ps.setTimestamp(5, java.sql.Timestamp.valueOf(createDate)); // Supplies the value for 'createDate'
                ps.setString(6, "JJ");      // createdBy
                ps.setTimestamp(7, java.sql.Timestamp.valueOf(lastUpdate)); // Supplies the value for 'lastUpdate'
                ps.setString(8, "JJ");      // lastUpdateBy
            
            // ------------------------ Address Data ---------------------------
                ps.setInt(9, 0);            // addressId (AUTO_INCREMENT)
                ps.setString(10, addy);     // address
                ps.setString(11, addy2);    // address2
                ps.setInt(12, 0);           // cityId
                ps.setString(13, postal);   // postalCode
                ps.setString(14, phone);    // phone
                ps.setTimestamp(15, java.sql.Timestamp.valueOf(createDate)); // Supplies the value for 'createDate');
                ps.setString(16, "JJ");     // createdBy
                ps.setTimestamp(17, java.sql.Timestamp.valueOf(lastUpdate)); // Supplies the value for 'lastUpdate');
                ps.setString(18, "JJ");     // lastUpdateBy

            // -------------------------- City Data ----------------------------
                ps.setInt(19, 0);           // cityId (AUTO_INCREMENT)
                ps.setString(20, city);     // city
                ps.setInt(21, 0);           // countryId
                ps.setTimestamp(22, java.sql.Timestamp.valueOf(createDate)); // Supplies the value for 'createDate');
                ps.setString(23, "JJ");     // createdBy
                ps.setTimestamp(24, java.sql.Timestamp.valueOf(lastUpdate)); // Supplies the value for 'lastUpdate');
                ps.setString(25, "JJ");     // lastUpdateBy
                
            // -------------------------- Country Data -------------------------
                ps.setInt(26, 0);           // countryId (AUTO_INCREMENT)
                ps.setString(27, country);
                ps.setTimestamp(28, java.sql.Timestamp.valueOf(createDate)); // Supplies the value for 'createDate');
                ps.setString(29, "JJ");     // createdBy
                ps.setTimestamp(30, java.sql.Timestamp.valueOf(lastUpdate)); // Supplies the value for 'lastUpdate');
                ps.setString(31, "JJ");     // lastUpdateBy
                
                ps.executeUpdate(insertCust);
                DB_Connection.getConn().commit();
                ps.close();
                
        
        } catch (SQLException e) {
            
            alertE.setTitle("SQL Statement Error in addCustomerData()");
            alertE.setHeaderText("There is an issue with the SQL statement...");
            alertE.setContentText("Please review the following error message and\n" +
                                  "make the necessary corrections to populate the\n" +
                                  "database with the new customer data:\n\n" +
                                  e.getMessage());
            alertE.showAndWait();
            e.printStackTrace();
        }
    }
    
 // -------------------------- Time and Zone Handling --------------------------
    // Begin LocalDateTime at now()
    LocalDateTime ldtStart = LocalDateTime.now();
    String ldtStartTxt = ldtStart.toString();
    

    //Convert to a ZonedDate Time in UTC
    ZoneId zid = ZoneId.systemDefault(); // grabs default TZ & ZID
    
    
    
// ----------------------------------- End -------------------------------------
    
// ----------------------------------- End -------------------------------------
}
