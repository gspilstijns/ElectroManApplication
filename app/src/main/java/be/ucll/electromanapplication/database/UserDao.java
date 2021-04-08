package be.ucll.electromanapplication.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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
}
