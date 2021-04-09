package be.ucll.electromanapplication.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import be.ucll.electromanapplication.R;
import be.ucll.electromanapplication.adapter.TodoAdapter;
import be.ucll.electromanapplication.database.TodoOfUser;
import be.ucll.electromanapplication.model.Todo;
import be.ucll.electromanapplication.viewmodel.TodoViewModel;

public class TodoActivity extends AppCompatActivity {

    private TodoViewModel mTodoViewModel;
    TodoAdapter todoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        todoAdapter = new TodoAdapter(this);

        recyclerView.setAdapter(todoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mTodoViewModel=new ViewModelProvider(this).get(TodoViewModel.class);

        mTodoViewModel.getTodos().observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(@Nullable final List<Todo> todos) {
                todoAdapter.setTodos(todos);
            }
        });

    }
}