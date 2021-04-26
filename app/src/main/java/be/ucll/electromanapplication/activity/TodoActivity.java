package be.ucll.electromanapplication.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import be.ucll.electromanapplication.R;
import be.ucll.electromanapplication.adapter.ClickListener;
import be.ucll.electromanapplication.adapter.TodoAdapter;
import be.ucll.electromanapplication.database.TodoOfUser;
import be.ucll.electromanapplication.model.Todo;
import be.ucll.electromanapplication.model.User;
import be.ucll.electromanapplication.viewmodel.TodoViewModel;
import be.ucll.electromanapplication.viewmodel.UserViewModel;

public class TodoActivity extends AppCompatActivity {

    private TodoViewModel mTodoViewModel;
    private TextView txtUserName;
    private Button btnLogout;
    TodoAdapter todoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        txtUserName = findViewById(R.id.txtUserName_Todo);
        btnLogout = findViewById(R.id.btnLogout);

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        //Retreive username to display basic user information
        Intent intent = getIntent();
        String name = intent.getStringExtra("UserName");
        User user = userViewModel.findUserByUserName(getIntent().getStringExtra("UserName"));
        txtUserName.setText("Welcome " + user.getFirstName() + " " + user.getLastName());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        todoAdapter = new TodoAdapter(this, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {

            }


        });

        recyclerView.setAdapter(todoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mTodoViewModel=new ViewModelProvider(this).get(TodoViewModel.class);

        //test to take only users specific todo's
        mTodoViewModel.getUserTodos(user.getUsername()).observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todoOfUsers) {
                todoAdapter.setTodos(todoOfUsers);
            }
        });

    }

}