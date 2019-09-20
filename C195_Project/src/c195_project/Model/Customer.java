/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_project.Model;

import java.time.LocalDateTime;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JJ_2
 */
public class Customer {
    
    public Customer() {
        // no argument constructor
    }
    
    public Customer(int custId, String custName, String custAddy, String custAddy2, String custCity, 
                    String country, int postal, String custPhone, int addyId,
                    int active, LocalDateTime createDate, String createdBy,
                    LocalDateTime lastUpdate, String lastUpdateBy) {
        
        this.custId.setValue(custId);
        this.custName.setValue(custName);
        this.custAddy.setValue(custAddy);
        this.custAddy2.setValue(custAddy2);
        this.custPhone.setValue(custPhone);
        this.addyId.setValue(addyId);
        this.active.setValue(active);
        this.createDate.setValue(createDate);
        this.createdBy.setValue(createdBy);
        this.lastUpdate.setValue(lastUpdate);
        this.lastUpdateBy.setValue(lastUpdateBy);
        
    }
    
    private SimpleIntegerProperty custId = new SimpleIntegerProperty(0);
        public SimpleIntegerProperty custIdProperty = custId;
    
    private SimpleStringProperty custName = new SimpleStringProperty("");
        public SimpleStringProperty custNameProperty = custName;
        
    private SimpleIntegerProperty addyId = new SimpleIntegerProperty(0);
        public SimpleIntegerProperty addyIdProperty = addyId;
        
    private SimpleStringProperty custAddy = new SimpleStringProperty("");
        public SimpleStringProperty custAddyProperty = custAddy;
    
    private SimpleStringProperty custAddy2 = new SimpleStringProperty("");
        public SimpleStringProperty custAddy2Property = custAddy2;
        
    private SimpleStringProperty custCity = new SimpleStringProperty("");
        public SimpleStringProperty custCityProperty = custCity;
        
    private SimpleStringProperty custCountry = new SimpleStringProperty("");
        public SimpleStringProperty custCountryProperty = custCountry;
        
    private SimpleIntegerProperty custPostal = new SimpleIntegerProperty(0);
        public SimpleIntegerProperty custPostalProperty = custPostal;
        
    private SimpleStringProperty custPhone = new SimpleStringProperty("");
        public SimpleStringProperty custPhoneProperty = custPhone;        
    
    private SimpleIntegerProperty active = new SimpleIntegerProperty(0);
        public SimpleIntegerProperty activeProperty = active;
    
    private SimpleObjectProperty<LocalDateTime> createDate = new SimpleObjectProperty(null);
        public SimpleObjectProperty<LocalDateTime> createDateProperty = createDate;
    
    private SimpleStringProperty createdBy = new SimpleStringProperty("");
        public SimpleStringProperty createdByProperty = createdBy;
    
    private SimpleObjectProperty<LocalDateTime> lastUpdate = new SimpleObjectProperty(null);
        public SimpleObjectProperty<LocalDateTime> lastUpdateProperty = lastUpdate;
    
    private SimpleStringProperty lastUpdateBy = new SimpleStringProperty("");
        public SimpleStringProperty lastUpdateByProperty = lastUpdateBy;
        
        
    public static Customer selectedCustomer;
        
// ---------------------------- Setters and Getters ----------------------------
    // @return custId
    public int getCustId() {
        return custId.getValue();
    }
    public void setCustId(int custId) {
        this.custId.setValue(custId);
    }
    public SimpleIntegerProperty custIdProperty() {
        return custId;
    }
    
    // @return custName
    public String getCustName() {
        return custName.getValueSafe();
    }
    public void setCustName(String custName) {
        this.custName.setValue(custName);
    }
    public SimpleStringProperty custNameProperty() {
        return custName;
    }

    // @return custAddy
    public String getCustAddy() {
        return custAddy.getValueSafe();
    }
    public void setCustAddy(String custAddy) {
        this.custAddy.setValue(custAddy);
    }
    public SimpleStringProperty custAddyProperty() {
        return custAddy;
    }
    
    // @return custAddy2
    public String getCustAddy2() {
        return custAddy2.getValueSafe();
    }
    public void setCustAddy2(String custAddy2) {
        this.custAddy2.setValue(custAddy2);
    }
    public SimpleStringProperty custAddy2Property() {
        return custAddy2;
    }
    
    // @return custCity
    public String getCustCity() {
        return custCity.getValueSafe();
    }
    public void setCustCity(String custCity) {
            this.custCity.setValue(custCity);
    }
    public SimpleStringProperty custCityProperty() {
        return custCity;
    }
    
    // @return custCountry
    public String getCustCountry() {
        return custCountry.getValueSafe();
    }
    public void setCustCountry(String custCountry) {
        this.custCountry.setValue(custCountry);
    }
    public SimpleStringProperty custCountryProperty() {
        return custCountry;
    }
    
    // @return custPostal
    public int getCustPostal() {
        return custPostal.getValue();
    }
    public void setCustPostal(int custPostal) {
        this.custPostal.setValue(custPostal);
    }
    public SimpleIntegerProperty custPostalProperty() {
        return custPostal;
    }
    
    // @return custPhone
    public String getCustPhone() {
        return custPhone.getValueSafe();
    }
    public void setCustPhone(String custPhone) {
        this.custPhone.setValue(formatPhone(custPhone));
    }
    public SimpleStringProperty custPhoneProperty() {
        return custPhone;
    }
    // @return addyId
    public int getAddyId() {
        return addyId.getValue();
    }
    public void setAddyId(int addyId) {
        this.addyId.setValue(addyId);
    }
    public SimpleIntegerProperty addyIdProperty() {
        return addyId;
    }

    // @return active
    public int getActive() {
        return active.getValue();
    }
    public void setActive(int active) {
        this.active.setValue(active);
    }
    public SimpleIntegerProperty activeProperty() {
        return active;
    }
    
    // @return createDate
    public LocalDateTime getCreateDate() {
        return createDate.getValue();
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate.setValue(createDate);
    }
    public SimpleObjectProperty<LocalDateTime> createDateProperty() {
        return createDate;
    }
    
    // @return createdBy
    public String createdBy() {
        return createdBy.getValueSafe();
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy.setValue(createdBy);
    }
    public SimpleStringProperty createdByProperty() {
        return createdBy;
    }
    
    // @return lastUpdate
    public LocalDateTime getLastUpdate() {
        return lastUpdate.getValue();
    }
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate.setValue(lastUpdate);
    }
    public SimpleObjectProperty<LocalDateTime> lastUpdateProperty() {
        return lastUpdate;
    }
    
    // @return lastUpdateBy
    public String getLastUpdateBy() {
        return lastUpdateBy.getValueSafe();
    }
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy.setValue(lastUpdateBy);
    }
    public SimpleStringProperty lastUpdateByProperty() {
        return lastUpdateBy;
    }

    
    // @return selectedCustomer
    public Customer getSelectedCustomer() {
        return Customer.selectedCustomer;
    }
    public void setSelectedCustomer(Customer selCust) {
        Customer.selectedCustomer = selCust;
    }
    
// ---------------------------------- End  -------------------------------------
    
    
// ------------------------ Data Processing Methods ----------------------------
   public String formatPhone(String custPhone) {
       
       custPhone = String.valueOf(custPhone).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
       
       return custPhone;
   } 
    
    
// ---------------------------------- End  -------------------------------------
    
}
