package be.ucll.electromanapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import be.ucll.electromanapplication.database.AppRepository;
import be.ucll.electromanapplication.database.TodoOfUser;
import be.ucll.electromanapplication.model.Todo;
import be.ucll.electromanapplication.model.User;

public class TodoViewModel extends AndroidViewModel {

    private AppRepository appRepository;

    private LiveData<List<TodoOfUser>> userTodos;

    public TodoViewModel(Application application) {
        super(application);
        appRepository = new AppRepository(application);
        userTodos = appRepository.getUserTodos();
    }

    public LiveData<List<TodoOfUser>> getUserTodos() {return userTodos;}

    public void insertTodo(Todo todo, User user) {
        appRepository.insertTodo(todo,user);
    }

    public void updateTodo(Todo todo) {
        appRepository.updateTodo(todo);
    }



}