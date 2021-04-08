package be.ucll.electromanapplication.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import be.ucll.electromanapplication.model.Todo;

@Dao
public interface TodoDao {
    @Insert
    void insertTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);

    @Query("SELECT * FROM todo")
    @Transaction
    LiveData<List<TodoOfUser>> getTodoWithUser();
}
