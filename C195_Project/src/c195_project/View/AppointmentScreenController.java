/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_project.View;

import c195_project.MainApp;
import c195_project.Model.Customer;
import c195_project.Model.Appointment;
import c195_project.Model.DB_Connection;
import c195_project.Model.User;
import java.awt.event.WindowListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JJ_2
 */
public class AppointmentScreenController implements Initializable {

// --------------------------- Global Variables (Globals) ----------------------    
    private LoginScreenController lsc;
    private MainApp mainApp;
    private Stage dialogueStage;
    private Customer cust;
    private Customer selectedCustomer;
    private Appointment appt;
    private Appointment selectedAppointment;
    private ResultSet rsApptDetails;
    private ResultSet rsCustDetails;
    private PreparedStatement psCust;
    private PreparedStatement psAppt;
    private PreparedStatement psCustDetails;
    private static Connection conn;
    private String loggedInUser;
    
    private ObservableList<Customer> custDetails = FXCollections.observableArrayList();
    private ObservableList<Appointment> apptDetails = FXCollections.observableArrayList();
    private ObservableList<Appointment> custAppts = FXCollections.observableArrayList();
    private ObservableList<Customer> debugListCust = FXCollections.observableArrayList();
    private ObservableList<Appointment> debugListAppt = FXCollections.observableArrayList();
    

    private WindowListener listener;
// ------------------------------------- End -----------------------------------

    
// ----------------------------------- Alerts ----------------------------------
    private Alert alertI = new Alert(Alert.AlertType.INFORMATION);
    private Alert alertE = new Alert(Alert.AlertType.ERROR);
    private Alert alertC = new Alert(Alert.AlertType.CONFIRMATION);
// ------------------------------------- End -----------------------------------
    
    
    
// -------------------------------- Screen Methods -----------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DB_Connection.getConn();
//        connectToDB();
//        checkCustTable();
        queryDbForCustomers();
        queryDbForAppointments();
        populateCustTable();
//        popCustFields();
//        popApptFields();
//        populateApptTable();
        
//        customerTable.getSelectionModel().getSelectedItem();
//        appointmentTable.getSelectionModel().getSelectedItem();
        loggedInUserLbl.setText("Logged in: " + loggedInUser);
    }
    
    @FXML
    public void SetDialogueStage(Stage dialogueStage) {
        this.dialogueStage = dialogueStage;
    }
    
    // references mainApp
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
// ------------------------------------- End -----------------------------------
    
    
    
 // ----------------------------- Screen Elements ------------------------------
    
    // ------------------------------------------------------------------ Labels
    // ***************************************** BOTTOM OF PANE
    @FXML // fx:id="GCLLC_Lbl"
    private Label GCLLC_Lbl;
    @FXML // fx:id="loggedInUserLbl"
    private Label loggedInUserLbl;
    // ***************************************** TOP OF PANE
    @FXML // fx:id="header2Label"
    private Label custDetailLbl;  // Customer Details Header Label
    
    // ***************************************** CUSTOMER DETAIL GRID PANE COL 0
    @FXML // fx:id="custNameLbl"
    private Label custNameLbl;
    @FXML // fx:id="custCityLbl"
    private Label custCityLbl;
    @FXML // fx:id="custCountryLbl"
    private Label custCountryLbl;
    @FXML // fx:id="custPhoneLbl"
    private Label custPhoneLbl;
    
    // ***************************************** CUSTOMER DETAIL GRID PANE COL 3
    @FXML // fx:id="custAddyLbl"
    private Label custAddyLbl;
    @FXML // fx:id="custZipLbl"
    private Label custZipLbl;
    
    // ***************************************** APPOINTMENT DETAIL GRID PANE
    @FXML // fx:id="header2Label"
    private Label apptDetailLbl;  // Appointment Details Header Label
    
    // ************************************** APPOINTMENT DETAIL GRID PANE COL 0
    @FXML // fx:id="apptLocLbl"
    private Label apptLocLbl;
    @FXML // fx:id="apptContLbl"
    private Label apptContLbl;
    @FXML // fx:id="createdByLbl"
    private Label createdByLbl;
    @FXML // fx:id="lastUpdateLbl"
    private Label lastUpdateLbl;
    @FXML // fx:id="lastUpdateByLbl"
    private Label lastUpdateByLbl;
    
    // ************************************** APPOINTMENT DETAIL GRID PANE COL 2
    @FXML // fx:id="apptDateLbl"
    private Label apptDateLbl;
    @FXML // fx:id="startTimeLbl"
    private Label startTimeLbl;
    @FXML // fx:id="apptDescLbl"
    private Label apptDescLbl;
    
    // ************************************** APPOINTMENT DETAIL GRID PANE COL 3
    @FXML // fx:id="endTimeLbl"
    private Label endTimeLbl;
    
    // ************************************** APPOINTMENT DETAIL GRID PANE COL 4
    @FXML // fx:id="custIdLbl"
    private Label custIdLbl;
    @FXML // fx:id="apptIdLbl"
    private Label apptIdLbl;
    
    // -------------------------------------------------------------- TextFields
    // ***************************************** CUSTOMER DETAIL GRID PANE
    // ***************************************** CUSTOMER DETAIL GRID PANE COL 1
    @FXML // fx:id="custNameField"
    private TextField custNameField;
    @FXML // fx:id="custAddyField"
    private TextField custAddyField;
    @FXML // fx:id='custCityField"
    private TextField custCityField;
    @FXML // fx:id='custCountryField"
    private TextField custCountryField;
    @FXML // fx:id="custTimezoneField"
    private TextField custTimezoneField;
    @FXML // fx:id="custZipField"
    private TextField custZipField;
    @FXML // fx:id="custPhoneField"
    private TextField custPhoneField;
    
    // ***************************************** APPOINTMENT DETAIL GRID PANE
    // ************************************** APPOINTMENT DETAIL GRID PANE COL 1
    @FXML // fx:id="apptLocField"
    private TextField apptLocField;
    @FXML // fx:id="apptContField"
    private TextField apptContField;
    @FXML // fx:id="createdByField"
    private TextField createdByField;
    @FXML // fx:id="lastUpdateField"
    private TextField lastUpdateField;
    @FXML // fx:id="lastUpdatebyField"
    private TextField lastUpdateByField;
    
    // ************************************** APPOINTMENT DETAIL GRID PANE COL 3
    @FXML // fx:id="apptDateField"
    private TextField apptDateField;
    @FXML // fx:id="startTimeField"
    private TextField startTimeField;
    @FXML // fx:id="endTimeField"
    private TextField endTimeField;
    @FXML // fx:id="descTextArea"
    private TextArea descTextArea;
    
    // ************************************** APPOINTMENT DETAIL GRID PANE COL 4
    @FXML // fx:id="custIdField"
    private TextField custIdField;
    @FXML // fx:id="apptIdField"
    private TextField apptIdField;    
    // ----------------------------------------------------------------- Buttons
    // ***************************************** CUSTOMER DETAIL GRID PANE
    @FXML // fx:id="addCustBtn"
    private Button addCustBtn;
    @FXML // fx:id="modCustBtn"
    private Button modCustBtn;
    @FXML // fx:id="saveCustBtn"
    private Button saveCustBtn;
    @FXML // fx:id="delCustBtn"
    private Button delCustBtn;
    // ***************************************** APPOINTMENT DETAIL GRID PANE
    @FXML // fx:id="addApptBtn"
    private Button addApptBtn;
    @FXML // fx:id="modApptBtn"
    private Button modApptBtn;
    @FXML // fx:id="delApptBtn"
    private Button delApptBtn;
    
// ------------------------------------- End -----------------------------------    

    
// -------------------------------- customer table -----------------------------
    @FXML // fx:id="custGrid"
    private GridPane custGrid;
    @FXML // fx:id="customerTable"
    private TableView<Customer> customerTable;
    @FXML // fx:id="custNameCol"
    private TableColumn<Customer, String> custNameCol;
    @FXML // fx:id="custAddyCol"
    private TableColumn<Customer, String> custAddyCol;
    @FXML // fx:id="custPhoneCol"
    private TableColumn<Customer, String> custPhoneCol;
// ------------------------------------- End -----------------------------------
    
    
// ------------------------------ appointment table ----------------------------
    @FXML // fx:id=apptGrid"
    private GridPane apptGrid;
    @FXML // fx:id="appointmentTable"
    private TableView<Appointment> appointmentTable;
    @FXML // fx:id="apptDateCol"
    private TableColumn<Appointment, LocalDate> apptDateCol;
    @FXML // fx:id="startCol"
    private TableColumn<Appointment, LocalTime> startCol;
    @FXML // fx:id="endCol"
    private TableColumn<Appointment, LocalTime> endCol;
    @FXML // fx:id="locationCol"
    private TableColumn<Appointment, String> locationCol;
    @FXML // fx:id="contactCol"
    private TableColumn<Appointment, String> contactCol;
    @FXML // fx:id="descCol"
    private TableColumn<Appointment, String> descCol;
    @FXML // fx:id="createdByCol"
    private TableColumn<Appointment, String> createdByCol;
    @FXML // fx:id="lastUpdateCol"
    private TableColumn<Appointment, LocalDateTime> lastUpdateCol;
// ------------------------------------- End -----------------------------------
    


// --------------------- Query the DB and Populate the Tables ------------------
    
    public boolean checkCustTable() {
        
        boolean result;
        
        if(!customerTable.getColumns().isEmpty()) {
            result = true;
            
        } else {
            alertE.setTitle("Customer Error");
            alertE.setHeaderText("No customers exist.");
            alertE.setContentText("There are currently no customers in the database." + "\n" +
                                  "Please create a new customer in order to create appointments.");
            
            mainApp.showNewCustomerScreen();
            result = false;
        }
        
        return result;
    }
    
    private void queryDbForCustomers() {
        try {             
            
             String sqlCustDetails = "SELECT c1.customerId, c1.addressId, c1.customerName, c1.active, c1.createDate, c1.createdBy, c1.lastUpdate, c1.lastUpdateBy, a1.address, a1.postalCode, a1.phone, c2.city, c3.country FROM customer c1, city c2, country c3, address a1 WHERE c1.addressId=a1.addressId AND a1.cityId=c2.cityId AND c2.countryId=c3.countryId AND phone IS NOT NULL";
             psCustDetails = DB_Connection.getConn().prepareStatement(sqlCustDetails);
             rsCustDetails = psCustDetails.executeQuery();

             while(rsCustDetails.next())  {
                
                cust = new Customer();
                appt = new Appointment();
                
                // set customer properties
                cust.setCustAddy(rsCustDetails.getString("address"));
                cust.setCustCity(rsCustDetails.getString("city"));
                cust.setCustCountry(rsCustDetails.getString("country"));
                cust.setCustPostal(rsCustDetails.getInt("postalCode"));
                cust.setCustPhone(rsCustDetails.getString("phone"));
                cust.setCustId(rsCustDetails.getInt("customerId"));
                cust.setCustName(rsCustDetails.getString("customerName"));
                cust.setAddyId(Integer.parseInt(rsCustDetails.getString("addressId")));
                cust.setActive(Integer.parseInt(rsCustDetails.getString("active")));
                cust.setCreateDate(dbDateTimeConvert(rsCustDetails.getTimestamp("createDate")));
                cust.setCreatedBy(rsCustDetails.getString("createdBy"));
                cust.setLastUpdate(dbDateTimeConvert(rsCustDetails.getTimestamp("lastUpdate")));
                cust.setLastUpdateBy(rsCustDetails.getString("lastUpdateBy"));
                
                // add customer details to the 'observableList<Customer> custDetails'
                custDetails.add(cust);                
                
                
            System.out.println(
               "Customer ID:\t\t" + cust.getCustId()+ "\n" +
               "Active:\t\t\t" + cust.getActive()+ "\n" +
               "Customer Name:\t\t" + cust.getCustName()+ "\n" +
               "Customer Address:\t" + cust.getCustAddy() + "\n" +
               "Customer City:\t\t" + cust.getCustCity() + "\n" +
               "Customer Country:\t" + cust.getCustCountry() + "\n" +
               "Customer Postal Code:\t" + cust.getCustPostal() + "\n" +
               "Customer Phone:\t\t" + cust.getCustPhone() + "\n" +
               "Create Date:\t\t" + cust.getCreateDate() + "\n" +
               "Last Update:\t\t" + cust.getLastUpdate() + "\n" +
               "Last Update By:\t\t" + cust.getLastUpdateBy() + "\n\n\n");// +
             }
             

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
            alertE.setTitle("SQL Error queryDbForCustomers()");
            alertE.setHeaderText("Something is wrong with your SQL statement AGAIN!!!");
            alertE.setContentText("Please check your code for incorrect syntax" + "\n" +
                                  "and make the necessary corrections." + "\n\n" +
                                  e.getMessage());
            alertE.showAndWait();
            
        } finally {
     
        }            
    }
    
    private void queryDbForAppointments() {        
        try {
            
             String sqlApptDetails = "SELECT a.appointmentId, a.customerId, a.title, a.description, a.location, a.contact, a.url, a.start, a.end, a.createDate, a.createdBy, a.lastUpdate, a.lastUpdateBy FROM appointment a, customer c WHERE a.customerId=c.customerId";
             psAppt = DB_Connection.getConn().prepareStatement(sqlApptDetails);
             rsApptDetails = psAppt.executeQuery();
             
              while(rsApptDetails.next()) {
                
                appt = new Appointment();
                
                // variables
                int apptId = rsApptDetails.getInt("a.appointmentId");
                int custId = rsApptDetails.getInt("a.customerId");
                String title = rsApptDetails.getString("a.title");
                String desc = rsApptDetails.getString("a.description");
                String loc = rsApptDetails.getString("a.location");
                String contact = rsApptDetails.getString("a.contact");
                String url = rsApptDetails.getString("a.url");
                LocalTime start = dbTimeConvert(rsApptDetails.getTimestamp("a.start"));
                LocalTime end = dbTimeConvert(rsApptDetails.getTimestamp("a.end"));
                LocalDateTime createDate = dbDateTimeConvert(rsApptDetails.getTimestamp("a.createDate"));
                String createdBy = rsApptDetails.getString("a.createdBy");
                LocalDateTime lastUpdate = dbDateTimeConvert(rsApptDetails.getTimestamp("a.lastUpdate"));
                String lastUpdateBy = rsApptDetails.getString("a.lastUpdateBy");
                
                // set appointment properties
                appt.setApptId(apptId);
                appt.setCustId(custId);
                appt.setTitle(title);
                appt.setDesc(desc);
                appt.setLoc(loc);
                appt.setContact(contact);
                appt.setUrl(url);
                appt.setStart(start);
                appt.setEnd(end);
                appt.setCreateDate(createDate);
                appt.setCreatedBy(createdBy);
                appt.setLastUpdate(lastUpdate);
                appt.setLastUpdateBy(lastUpdateBy);
                appt.setApptDate(LocalDate.now());
//                appt.apptDateProperty().setValue(LocalDate.now());

             // add appointment details to the 'observableList<Appointment> apptDetails'
                apptDetails.add(appt);

                System.out.println(
                   "Appointment ID:\t\t" + appt.getApptId() + "\n" +
                   "Customer ID:\t\t" + appt.getCustId() + "\n" +
                   "Title:\t\t\t" + appt.getTitle() + "\n" +
                   "Description\t\t" + appt.getDesc() + "\n" +
                   "Location\t\t" + appt.getLoc() + "\n" +
                   "Contact\t\t\t" + appt.getContact() + "\n" +
                   "URL\t\t\t" + appt.getUrl() + "\n" +
                   "Start\t\t\t" + appt.getStart() + "\n" +
                   "End\t\t\t" + appt.getEnd() + "\n" +
                   "Create Date\t\t" + appt.getCreateDate() + "\n" +
                   "Created By\t\t" + appt.getCreatedBy() + "\n" +
                   "Last Update\t\t" + appt.getLastUpdate() + "\n" +
                   "Last Updated By:\t" + appt.getLastUpdateBy() + "\n");
             }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
            alertE.setTitle("SQL Error queryDbForAppointments()");
            alertE.setHeaderText("Something is wrong with your SQL statement AGAIN!!!");
            alertE.setContentText("Please check your code for incorrect syntax" + "\n" +
                                  "and make the necessary corrections." + "\n\n" +
                                  e.getMessage());
            alertE.showAndWait();
        }
    }
    
    private LocalDateTime dbDateTimeConvert(Timestamp dbTimestamp) {
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy kk:mm");
        LocalDateTime ldt = dbTimestamp.toLocalDateTime();
        String txtDbDateTime = ldt.toString().replace("T", " ");
//        String txtDbDateTime = 
        System.out.println("\n\n\n\t\t Date Time Conversion:\n" +
                           "Here's the string you're working with:\t" +
                           txtDbDateTime + "\n\n\n");
//        LocalDateTime convertedDateTime = LocalDateTime.parse(txtDbDateTime, dtFormatter);
                
        return ldt;     // convertedDateTime;
    }
    
    private LocalDate dbDateConvert(Timestamp dbTimestamp) {
        DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDateTime ldt = dbTimestamp.toLocalDateTime();
        String txtDbDate = ldt.toString();
        LocalDate convertedDate = LocalDate.parse(txtDbDate.substring(0, 10), dFormatter);
        
        return convertedDate;
    }
    
    private LocalTime dbTimeConvert(Timestamp dbTimestamp) {
        DateTimeFormatter tFormatter = DateTimeFormatter.ofPattern("kk:mm");
        LocalDateTime ldt = dbTimestamp.toLocalDateTime();
        String txtDbTime = ldt.toString();
        LocalTime convertedTime = LocalTime.parse(txtDbTime.substring(11), tFormatter);
        
        return convertedTime;
    }
    
    private void populateCustTable() {
        
        
            // Set the contents of customerTable            
            customerTable.setItems(custDetails); 
            

            // Populate the individual columns
                custNameCol.setCellValueFactory(cellData -> cellData.getValue().custNameProperty());
                custAddyCol.setCellValueFactory(cellData -> cellData.getValue().custAddyProperty());
               custPhoneCol.setCellValueFactory(cellData -> cellData.getValue().custPhoneProperty());
        
        
    }
    
    private void populateApptTable() {
        
        custAppts.clear();
        
        int custIdCount = 0;
        
        for(Appointment apt : apptDetails) {
            
            int selCustId = selectedCustomer.custIdProperty().getValue();
            int apptCustId = apt.custIdProperty().getValue();
            
            if(selCustId==apptCustId) {
                
                custAppts.add(apt);
                
                custIdCount++;
    
                popApptFields();

                System.out.println("Appointment for " + selectedCustomer.custNameProperty().getValueSafe() + ": " + "\n" +
                        "Customer ID: " +
                        selectedCustomer.custIdProperty().getValue() + "\n" +
                                "Matching Appt Cust ID: " +
                        apt.custIdProperty().getValue() + "\n" +
                                "Appointment ID: " +
                        apt.apptIdProperty().getValue() + "\n" +
                                "Appointment Date: " +
                        apt.apptDateProperty().getValue() + "\n" +
                                "Description: " +
                        apt.descProperty().getValueSafe() + "\n" +
                                "Contact: " +
                        apt.contactProperty().getValueSafe() + "\n" +
                                "Website: " +
                        apt.urlProperty().getValueSafe() + "\n" +
                                "Location: " +
                        apt.locProperty().getValueSafe() + "\n" +
                                "Title: " +
                        apt.titleProperty().getValueSafe() + "\n" +
                                "Last Update: " +
                        apt.lastUpdateProperty().getValue() + "\n" +
                                "Last Update By: " +
                        apt.lastUpdateByProperty().getValueSafe() + "\n\n");
            }            
        }
        
        if(custIdCount == 0){

            alertE.setTitle("Matching Error");
            alertE.setHeaderText("Cannot find customer/appointment match...");
            alertE.setContentText("There seems to be an error.  A matching appointment \n" +
                                  "for " + selectedCustomer.custNameProperty().getValueSafe() + " " +
                                  "cannot be found. \n\n" + 
                                  "If no appointments for this customer exist, please create one. \n" +
                                  "Otherwise, if appointments for this customer DO exist, contact \n" + 
                                  "your System Administrator as there may be an error in the system.");
            alertE.showAndWait();

        System.out.println("There is no matching appointment for " +
                selectedCustomer.custNameProperty().getValueSafe());
        }
        
                // Set the contents of appointmentTable
                appointmentTable.setItems(custAppts);

                // Populate the individual columns
                    apptDateCol.setCellValueFactory(cellData -> cellData.getValue().apptDateProperty());
                       startCol.setCellValueFactory(cellData -> cellData.getValue().startProperty());
                         endCol.setCellValueFactory(cellData -> cellData.getValue().endProperty());
                    locationCol.setCellValueFactory(cellData -> cellData.getValue().locProperty());
                     contactCol.setCellValueFactory(cellData -> cellData.getValue().contactProperty());
                        descCol.setCellValueFactory(cellData -> cellData.getValue().descProperty());
                   createdByCol.setCellValueFactory(cellData -> cellData.getValue().createdByProperty());
                  lastUpdateCol.setCellValueFactory(cellData -> cellData.getValue().lastUpdateProperty());
              
    } 
// ------------------------------------- End -----------------------------------


// ----------------------- Populate Customer Text Fields -----------------------
    @FXML // onAction=#popTextFields
    private void popCustFields() {
        try {
            selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

            if(selectedCustomer != null) {
                custNameField.setText(selectedCustomer.custNameProperty().getValueSafe());
                custAddyField.setText(selectedCustomer.custAddyProperty().getValueSafe());
                custCityField.setText(selectedCustomer.custCityProperty().getValueSafe());
            custTimezoneField.setText("Placeholder for now...");
             custCountryField.setText(selectedCustomer.custCountryProperty().getValueSafe());
                 custZipField.setText(selectedCustomer.custPostalProperty().getValue().toString());
               custPhoneField.setText(selectedCustomer.getCustPhone());

                populateApptTable();
            }
            
        } catch (NullPointerException e){
                alertE.setTitle("Null Data Error");
                alertE.setHeaderText("The object 'selectedCustomer' is null. \n" +
                                     "Please examine the following message to ascertain \n" +
                                     "the nature of the issue: \n\n" +
                                     e.getMessage());
                e.printStackTrace();
                alertE.showAndWait();
                
        }
    }
// ------------------------------------- End -----------------------------------

// ----------------------- Populate Appointment Text Fields --------------------
    
    @FXML // onAction=#popApptFields
    private void popApptFields() {
        try {
        
            selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();

            if(selectedAppointment != null) {

                apptLocField.setText(selectedAppointment.locProperty().getValueSafe());
               apptContField.setText(selectedAppointment.contactProperty().getValueSafe());
              createdByField.setText(selectedAppointment.createdByProperty().getValueSafe());
             lastUpdateField.setText(selectedAppointment.lastUpdateProperty().getValue().toString());
           lastUpdateByField.setText(selectedAppointment.lastUpdateByProperty().getValueSafe());
               apptDateField.setText(selectedAppointment.apptDateProperty().getValue().toString());
              startTimeField.setText(selectedAppointment.startProperty().getValue().toString());
                endTimeField.setText(selectedAppointment.endProperty().getValue().toString());
                 custIdField.setText(selectedAppointment.custIdProperty().getValue().toString());
                 apptIdField.setText(selectedAppointment.apptIdProperty().getValue().toString());
                descTextArea.setText(selectedAppointment.descProperty().getValueSafe());
            }       
            
        } catch (NullPointerException e){
                alertE.setTitle("Null Data Error");
                alertE.setHeaderText("The object 'selectedAppointment' is null. \n" +
                                     "Please examine the following message to ascertain \n" +
                                     "the nature of the issue: \n\n" +
                                     e.getMessage());
                e.printStackTrace();
                alertE.showAndWait();
                
        }
        
    }
    
// ------------------------------------- End -----------------------------------
    
    
// ------------------------------ Add/Modify Methods ---------------------------
    
    @FXML // onAction=#handleAddCust
    private void handleAddCust() {
        mainApp.showNewCustomerScreen();
    }
    
    @FXML // onAction=#handleModCust
    private void handleModCust() {
        Customer.selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        mainApp.showModCustomerScreen();
//        alertI.setTitle("AfterEffect");
//        alertI.setHeaderText("Success!");
//        alertI.setContentText("This message fired after the ModCustomerScreen \n" +
//                              "was closed.  You can now run queryDbForCustomers'('')'.");
//        alertI.showAndWait();
        custDetails.clear();
        queryDbForCustomers();
    }
    
    @FXML // onAction=#handleAddAppt
    private void handleAddAppt() {
        if(selectedCustomer != null) {        
            mainApp.showAddAppointmentScreen();        
        } else {
            alertI.setTitle("Selection Error");
            alertI.setHeaderText("No customer selected");
            alertI.setContentText("Please select a customer for whom to\n" +
                                  "create a new appointment and try again.");
            alertI.showAndWait();
        }
    }
    
    @FXML // onAction=#handleModAppt
    private void handleModAppt() {
        Appointment.selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        mainApp.showModAppointmentScreen();
    }
// ------------------------------------- End -----------------------------------
 
    
// ------------------------ Close the Database Connection ----------------------

// ------------------------------------- End -----------------------------------

    
// ----------------- DB_Connection for AppointmentScreen Testing ---------------
     // ----------------- comment out to develop logic offline -----------------
    public void connectToDB() {

        //Connection String
        //Server name:  52.206.157.109
        //Database name:  U01KRH
        //Username:  U01KRH
        //Password:  53687479064
        
        // JDBC driver name and database URL
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

        //  Online Database credentials
        final String DB_URL = "jdbc:mysql://52.206.157.109/U01KRH";
        final String DBUSER = "U01KRH";
        final String DBPASS = "53687479064";
        
        // Offline Database Credentials
        final String DB_URL2 = "jdbc:mysql://localhost/C195_offlinedb";
        final String DBUSER2 = "root";
        final String DBPASS2 = "";

        try {

            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            
            conn = DriverManager.getConnection(DB_URL, DBUSER, DBPASS);
            System.out.println(conn);
//            alertC.setTitle("Connection");
//            alertC.setHeaderText("Connection Stats");
//            alertC.setContentText(conn.toString());
//            alertC.showAndWait();

        } catch (ClassNotFoundException cnfe) {

            alertE.setTitle("Class Error");
            alertE.setHeaderText("The class 'java.sql.Driver' could not be found.");
            alertE.setContentText("Check the CLASSPATH to ensure you have included" + "\n"
                    + " the " + JDBC_DRIVER + " driver.");
            alertE.showAndWait();

        } catch (SQLException sqle) {

            alertE.setTitle("SQL Error");
            alertE.setHeaderText("There is something wrong with your statement...");
            alertE.setContentText("Please check your SQL statement for errors." + "\n"
                    + "Something has gone horribly wrong.  I fear" + "\n"
                    + "you may have to rewrite your SQL statement...");
            alertE.showAndWait();
        }
    }
// ------------------------------------- End -----------------------------------
}
