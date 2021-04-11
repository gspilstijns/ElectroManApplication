package be.ucll.electromanapplication.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Todo {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String city;
    public String device;
    public String problemCode;
    public String customerName;
    public Boolean processed;
    public String note;

    public int userid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getProblemCode() {
        return problemCode;
    }

    public void setProblemCode(String problemCode) {
        this.problemCode = problemCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Ignore
    public Todo(int id, String city, String device, String problemCode, String customerName, Boolean processed, int userid, String note) {
        this.id = id;
        this.city = city;
        this.device = device;
        this.problemCode = problemCode;
        this.customerName = customerName;
        this.processed = processed;
        this.userid = userid;
        this.note = note;
    }

    public Todo(String city, String device, String problemCode, String customerName, Boolean processed, int userid, String note) {
        this.city = city;
        this.device = device;
        this.problemCode = problemCode;
        this.customerName = customerName;
        this.processed = processed;
        this.userid = userid;
        this.note = note;
    }

}
