/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_project.Model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JJ_2
 */
public class Appointment {
    
    public Appointment() {
        // no arg constructor
    }
    
    public Appointment(int apptId, int custId, String title, String desc, String loc,
                       String contact, String url, LocalTime start, LocalTime end,
                       LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate,
                       String lastUpdateBy, LocalDate apptDate) {
        
        this.apptId.setValue(apptId);
        this.custId.setValue(custId);
        this.title.setValue(title);
        this.desc.setValue(desc);
        this.loc.setValue(loc);
        this.contact.setValue(contact);
        this.url.setValue(url);
        this.start.setValue(start);
        this.end.setValue(end);
        this.createDate.setValue(createDate);
        this.createdBy.setValue(createdBy);
        this.lastUpdate.setValue(lastUpdate);
        this.lastUpdateBy.setValue(lastUpdateBy);
        this.apptDate.setValue(apptDate);
        
    }
    
    private SimpleIntegerProperty apptId = new SimpleIntegerProperty(0);
        public SimpleIntegerProperty apptIdProperty = apptId;
        
    private SimpleIntegerProperty custId = new SimpleIntegerProperty(0);
        public SimpleIntegerProperty custIdProperty = custId;
    
    private SimpleStringProperty title = new SimpleStringProperty("");
        public SimpleStringProperty titleProperty = title;
    
    private SimpleStringProperty desc = new SimpleStringProperty("");
        public SimpleStringProperty descProperty = desc;
    
    private SimpleStringProperty loc = new SimpleStringProperty("");
        public SimpleStringProperty locProperty = loc;
    
    private SimpleStringProperty contact = new SimpleStringProperty("");
        public SimpleStringProperty contactProperty = contact;
    
    private SimpleStringProperty url = new SimpleStringProperty("");
        public SimpleStringProperty urlProperty = url;
    
    private SimpleObjectProperty<LocalTime> start = new SimpleObjectProperty(null);
        public SimpleObjectProperty<LocalTime> startProperty = start;
    
    private SimpleObjectProperty<LocalTime> end = new SimpleObjectProperty(null);
        public SimpleObjectProperty<LocalTime> endProperty = end;
    
    private SimpleObjectProperty<LocalDateTime> createDate = new SimpleObjectProperty(null);
        public SimpleObjectProperty<LocalDateTime> createDateProperty = createDate;
    
    private SimpleStringProperty createdBy = new SimpleStringProperty("");
        public SimpleStringProperty createdByProperty = createdBy;
    
    private SimpleObjectProperty<LocalDateTime> lastUpdate = new SimpleObjectProperty(null);
        public SimpleObjectProperty<LocalDateTime> lastUpdateProperty = lastUpdate;
        
    private SimpleStringProperty lastUpdateBy = new SimpleStringProperty("");
        public SimpleStringProperty lastUpdateByProperty = lastUpdateBy;

    private SimpleObjectProperty<LocalDate> apptDate = new SimpleObjectProperty(null);
        public SimpleObjectProperty<LocalDate> apptDateProperty = apptDate;
        
    public static Appointment selectedAppointment;
        
// ---------------------------- Setters and Getters ----------------------------
    // @return apptId
    public int getApptId() {
        return apptId.getValue();
    }
    public void setApptId(int apptId) {
        this.apptId.setValue(apptId);
    }
    public SimpleIntegerProperty apptIdProperty() {
        return apptId;
    }
    
    
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
    
    // @return title
    public String getTitle() {
        return title.getValue();
    }
    public void setTitle(String title) {
        this.title.setValue(title);
    }
    public SimpleStringProperty titleProperty() {
        return title;
    }
    
    // @return desc
    public String getDesc() {
        return desc.getValue();
    }
    public void setDesc(String desc) {
        this.desc.setValue(desc);
    }
    public SimpleStringProperty descProperty() {
        return desc;
    }
    
    // @return loc
    public String getLoc() {
        return loc.getValue();
    }
    public void setLoc(String loc) {
        this.loc.setValue(loc);
    }
    public SimpleStringProperty locProperty() {
        return loc;
    }
    
    // @return contact
    public String getContact() {
        return contact.getValue();
    }
    public void setContact(String contact) {
        this.contact.setValue(contact);
    }
    public SimpleStringProperty contactProperty() {
        return contact;
    }
    
    // @return url
    public String getUrl() {
        return url.getValue();
    }
    public void setUrl(String url) {
        this.url.setValue(url);
    }
    public SimpleStringProperty urlProperty() {
        return url;
    }
    
    // @return start
    public LocalTime getStart() {
        return start.getValue();
    }
    public void setStart(LocalTime start) {
        this.start.setValue(start);
    }
    public SimpleObjectProperty<LocalTime> startProperty() {
        return start;
    }
    
    // @return end
    public LocalTime getEnd() {
        return end.getValue();
    }
    public void setEnd(LocalTime end) {
        this.end.setValue(end);
    }
    public SimpleObjectProperty<LocalTime> endProperty() {
        return end;
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
    public String getCreatedBy()  {
        return createdBy.getValue();
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
        return lastUpdateBy.getValue();
    }
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy.setValue(lastUpdateBy);
    }
    public SimpleStringProperty lastUpdateByProperty() {
        return lastUpdateBy;
    }
    
    // @return apptDate
    public LocalDate getApptDate() {
        return apptDate.getValue();
    }
    public void setApptDate(LocalDate apptDate) {
        this.apptDate.setValue(apptDate);
    }
    public SimpleObjectProperty<LocalDate> apptDateProperty() {
        return apptDate;
    }
    
    // @return selectedAppointment
    public Appointment getSelectedAppointment() {
        return Appointment.selectedAppointment;
    }
    public void setSelectedAppointment(Appointment selAppt) {
        Appointment.selectedAppointment = selAppt;
    }
// --------------------------------- End ---------------------------------------
    
// ------------------------- Data Processing Methods ---------------------------
    private LocalDateTime dbDateTimeConvert(Timestamp dbTimestamp) {
        
//        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern();
        String formatString = "11-27-1971 11:27";
        LocalDateTime ldt = dbTimestamp.toLocalDateTime();
        String txtDbDateTime = ldt.toString();
        
    //        
        System.out.println("\n\n\n\t\t Date Time Conversion:\n" +
                           "Here's the string you're working with:\t" +
                           txtDbDateTime + "\n\n\n");
    //        LocalDateTime convertedDateTime = LocalDateTime.parse(txtDbDateTime, dtFormatter);

        return ldt;     // convertedDateTime;
    }

    private LocalDate dbDateConvert(Timestamp dbTimestamp) {
        DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("mm-dd-yyyy");
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
    
// --------------------------------- End ---------------------------------------
    
    
    
}
