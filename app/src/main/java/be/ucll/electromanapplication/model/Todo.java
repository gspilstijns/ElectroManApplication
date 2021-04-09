package be.ucll.electromanapplication.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Todo {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Ignore
    public Todo(int id, int userid, String description) {
        this.id = id;
        this.userid = userid;
        this.description = description;
    }

    public Todo(int userid, String description) {
        this.userid = userid;
        this.description = description;
    }
}
