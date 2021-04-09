package be.ucll.electromanapplication.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import be.ucll.electromanapplication.model.Todo;
import be.ucll.electromanapplication.model.User;

public class AppRepository {
    private UserDao userDao;
    private TodoDao todoDao;
    private LiveData<List<TodoOfUser>> userTodos;
    private LiveData<List<Todo>> todos;

    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        userDao = db.userDao();
        todoDao = db.todoDao();
        userTodos = todoDao.getTodoWithUser();
        todos = todoDao.getAllTodos();
    }

    public LiveData<List<TodoOfUser>> getUserTodos() {
        return userTodos;
    }
    public LiveData<List<Todo>> getAllTodos() {
        return todos;
    }

    public User findUserByUserName(String username) {
        return userDao.findByUserName(username);

    }

    public void insertUser(User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insertUsers(user);
        });
    }

    public void insertTodo(Todo todo, User user) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todo.setUserid(user.getId());
            todoDao.insertTodo(todo);
        });
    }

    public void updateTodo(Todo todo) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            todoDao.updateTodo(todo);
        });
    }
}
