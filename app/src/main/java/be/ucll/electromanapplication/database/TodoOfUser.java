package be.ucll.electromanapplication.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import be.ucll.electromanapplication.model.Todo;
import be.ucll.electromanapplication.model.User;

public class TodoOfUser {

    @Embedded
    public User user;
    @Relation(parentColumn = "id",entityColumn = "userid")
    public List<Todo> todos;
}
