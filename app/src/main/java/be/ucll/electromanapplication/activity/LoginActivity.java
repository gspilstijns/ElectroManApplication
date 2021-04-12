package be.ucll.electromanapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import be.ucll.electromanapplication.R;
import be.ucll.electromanapplication.model.Todo;
import be.ucll.electromanapplication.model.User;
import be.ucll.electromanapplication.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        final Button btnLogin = findViewById(R.id.btnLogin);
        final EditText username = findViewById(R.id.txtUserName);
        final EditText txtpassword = findViewById(R.id.txtPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Search the user in the Room Database
                User user = userViewModel.findUserByUserName(username.getText().toString());
                //String password = txtpassword.getText().toString();

                //Validate the user login
                if (txtpassword.getText().toString().equals(user.getPassword())){
                    Intent intent = new Intent(getApplicationContext(), TodoActivity.class);
                    intent.putExtra("UserName", user.getUsername());
                    startActivity(intent);
                }




            }
        });
    }
}