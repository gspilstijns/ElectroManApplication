package be.ucll.electromanapplication.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import be.ucll.electromanapplication.model.Todo;
import be.ucll.electromanapplication.model.User;

@Dao
public interface UserDao {

    @Insert
    void insertUsers(User... users);

    @Query("delete from user")
    void deleteUsers();

    @Update
    void updateUsers(User... users);

    @Query("Select * from user Where username = :userName")
    User findByUserName(String userName);

    @Transaction
    @Query("select Todo.* from User inner join Todo on User.id=Todo.userid where username = :userName")
    LiveData<List<Todo>> getTodoOfUser(String userName);

    @Query("SELECT * FROM user")
    @Transaction
    LiveData<List<TodoOfUser>> getUsersWithTodo();
}
