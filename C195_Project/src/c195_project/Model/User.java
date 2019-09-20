/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_project.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JJ_2
 */
public class User {
    
    public User() {
    }
    
    public User(int userId, String userName, String password, int active, String createBy,
                LocalDate createDate, LocalDate lastUpdate, String lastUpdatedBy) {
        
        this.userId.setValue(userId);
        this.userName.setValue(userName);
        this.password.setValue(password);
        this.active.setValue(active);
        this.createBy.setValue(createBy);
        this.createDate.setValue(createDate);
        this.lastUpdate.setValue(lastUpdate);
        this.lastUpdatedBy.setValue(lastUpdatedBy);        
    }
    
    
    private SimpleIntegerProperty userId = new SimpleIntegerProperty(0);
        public SimpleIntegerProperty userIdProperty = userId;
    
    private SimpleStringProperty userName = new SimpleStringProperty("");
        public SimpleStringProperty userNameProperty = userName;
    
    private SimpleStringProperty password = new SimpleStringProperty("");
        public SimpleStringProperty passwordProperty = password;
    
    private SimpleIntegerProperty active = new SimpleIntegerProperty(0);
        public SimpleIntegerProperty activeProperty = active;
    
    private SimpleStringProperty createBy = new SimpleStringProperty("");
        public SimpleStringProperty createByProperty = createBy;
    
    private SimpleObjectProperty<LocalDate> createDate = new SimpleObjectProperty(null);
        public SimpleObjectProperty<LocalDate> createDateProperty = createDate;
    
    private SimpleObjectProperty<LocalDate> lastUpdate = new SimpleObjectProperty(null);
        public SimpleObjectProperty<LocalDate> lastUpdateProperty = lastUpdate;
    
    private SimpleStringProperty lastUpdatedBy = new SimpleStringProperty("");
        public SimpleStringProperty lastUpdatedByProperty = lastUpdatedBy;
        
        
// ---------------------------- Setters and Getters ----------------------------
    // @return userID
    public int getUserId() {
        return userId.getValue();
    }
    public void setUserId(int userId) {
        this.userId.setValue(userId);
    }
    public SimpleIntegerProperty userIdProperty() {
        return userId;
    }
    
    // @return userName
    public String getUserName() {
        return userName.getValueSafe();
    }
    public void setUserName(String userName) {
        this.userName.setValue(userName);
    }
    public SimpleStringProperty userNameProperty() {
        return userName;
    }
    
    // @return password
    public String getPassword() {
        return password.getValueSafe();
    }
    public void setPassword(String password) {
        this.password.setValue(password);
    } 
    public SimpleStringProperty passwordProperty() {
        return password;
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
    
    // @return createBy
    public String getCreateBy() {
        return createBy.getValueSafe();
    }
    public void setCreateBy(String createBy) {
        this.createBy.setValue(createBy);
    }
    public SimpleStringProperty createByProperty() {
        return createBy;
    }
    
    // @return createDate
    public LocalDate getCreateDate() {
        return createDate.getValue();
    }
    public void setCreateDate(LocalDate createDate) {
        this.createDate.setValue(createDate);
    }
    public SimpleObjectProperty<LocalDate> createDateProperty() {
        return createDate;
    }
    
    // @return lastUpdate
    public LocalDate getLastUpdate() {
        return lastUpdate.getValue();
    }
    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate.setValue(lastUpdate);
    }
    public SimpleObjectProperty<LocalDate> lastUpdateProperty() {
        return lastUpdate;
    }
    
    // @return lastUpdatedBy
    public String getLastUpdatedBy() {
        return lastUpdatedBy.getValueSafe();
    }
    public void setLastUpdatedBy() {
        this.lastUpdatedBy.getValueSafe();
    }
    public SimpleStringProperty lastUpdatedByProperty() {
        return lastUpdatedBy;
    }
// ----------------------- End of Setters and Getters --------------------------
    
    
    
    
}
