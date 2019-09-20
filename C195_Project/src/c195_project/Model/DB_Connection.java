/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_project.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author JJ_2
 */
public class DB_Connection {
    
    
    private static Connection dbConn;  // if static, no object needed in main method
    
    // ----------------------------------- Alerts ----------------------------------
    private static Alert alertI = new Alert(Alert.AlertType.INFORMATION);
    private static Alert alertE = new Alert(Alert.AlertType.ERROR);
    private static Alert alertC = new Alert(Alert.AlertType.CONFIRMATION);
// ------------------------------------- End -----------------------------------
    
    public DB_Connection() {} // no argument constructor
        
    public static void init() { // method becomes static when connection variable does
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
            
            dbConn = DriverManager.getConnection(DB_URL, DBUSER, DBPASS);
            dbConn.setAutoCommit(false);
            System.out.println(dbConn);
//            alertC.setTitle("Connection");
//            alertC.setHeaderText("Connection Stats");
//            alertC.setContentText(conn.toString());
//            alertC.showAndWait();

        } catch (ClassNotFoundException e) {

            alertE.setTitle("Class Error");
            alertE.setHeaderText("The class 'java.sql.Driver' could not be found.");
            alertE.setContentText("Check the CLASSPATH to ensure you have included" + "\n" + 
                                  " the " + JDBC_DRIVER + " driver. \n\n" + 
                                  e.getMessage());
            alertE.showAndWait();

        } catch (SQLException e) {

            alertE.setTitle("SQL Error");
            alertE.setHeaderText("There is something wrong with your statement...");
            alertE.setContentText("Please check your SQL statement for errors. \n" + 
                                  "Something has gone horribly wrong.  I fear \n" + 
                                  "you may have to rewrite your SQL statement... \n\n" +
                                  e.getMessage());
            alertE.showAndWait();
        }
    }
    
    public static Connection getConn() {
        return dbConn;
    }
    
    public static void closeConn() {
        
        try {
            dbConn.close();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            
        } finally {
            System.out.println("Connection closed.");
        }
    }
    
}
