package be.ucll.electromanapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import be.ucll.electromanapplication.model.Todo;
import be.ucll.electromanapplication.model.User;

@Database(entities = {User.class, Todo.class},version = 1, exportSchema = false)
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
                            .allowMainThreadQueries()
                            .build();
                    AppDatabase.databaseWriteExecutor.execute(() -> {
                        INSTANCE.clearAllTables();
                       // String firstName, String lastName, String username, String password
                        INSTANCE.userDao().insertUsers(new User("Gert-Jan","Spilstijns","gespi","test"));
                    });

                }
            }
        }
        return INSTANCE;
    }
}
