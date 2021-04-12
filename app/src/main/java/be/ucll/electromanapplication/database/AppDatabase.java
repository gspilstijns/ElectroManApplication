package be.ucll.electromanapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import be.ucll.electromanapplication.model.Todo;
import be.ucll.electromanapplication.model.User;

@Database(entities = {User.class, Todo.class},version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract TodoDao todoDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "electroman_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                    AppDatabase.databaseWriteExecutor.execute(() -> {
                        INSTANCE.clearAllTables();
                        // Create initial users
                        List<User> userlist = new ArrayList<>();
                        userlist.add(new User("Gert-Jan", "Spilstijns", "gespi", "gespi"));
                        userlist.add(new User("Koen", "Serneels", "koser", "koser"));
                        for (User user:userlist) {
                            INSTANCE.userDao().insertUsers(user);
                        }


                        //Create some initial todo's in the Room database
                        INSTANCE.todoDao().insertTodo(new Todo("Leuven","Scherm","No input","Gert-Jan Spilstijns",false,INSTANCE.userDao().findByUserName("koser").getId(),null) );
                        INSTANCE.todoDao().insertTodo(new Todo("Leuven","Keyboard","Broken keys","Gert-Jan Spilstijns",false,INSTANCE.userDao().findByUserName("koser").getId(),null) );
                        INSTANCE.todoDao().insertTodo(new Todo("Leuven","Laptop","ERR506","Gert-Jan Spilstijns",false,INSTANCE.userDao().findByUserName("gespi").getId(),null) );
                        INSTANCE.todoDao().insertTodo(new Todo("Leuven","Printer","PRINT505","Gert-Jan Spilstijns",false,INSTANCE.userDao().findByUserName("gespi").getId(),null) );

                    });

                }
            }
        }
        return INSTANCE;
    }
}
