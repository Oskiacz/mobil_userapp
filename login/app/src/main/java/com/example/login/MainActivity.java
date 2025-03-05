package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public User findUserByLogin(ArrayList<User> users, String login) {
        if (users == null || login == null) return null;

        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }
    ArrayList<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginButton = findViewById(R.id.buttonLogin);
        EditText user = findViewById(R.id.textLogin);
        EditText password = findViewById(R.id.textPassword);

        users.add(new User("admin", "mobil", "admin", "admin", true));

        if(getIntent().hasExtra("user_list")) {
            users = (ArrayList<User>) getIntent().getSerializableExtra("user_list");
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!user.getText().toString().equals("") && !password.getText().toString().equals("")) {

                    User foundUser = findUserByLogin(users, user.getText().toString());

                    if(foundUser == null) {
                        User newUser = new User(
                                user.getText().toString(),
                                password.getText().toString(),
                                user.getText().toString().length() > 6 ? TextUtils.substring(user.getText().toString(), 0, 3) : "imie",
                                user.getText().toString().length() > 6 ? TextUtils.substring(user.getText().toString(), 3, 6) : "nazwisko",
                                false
                        );
                        users.add(newUser);
                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                        intent.putExtra("user_list", users);
                        intent.putExtra("user", newUser);
                        startActivity(intent);

                    }
                    else {
                        if(foundUser.isAdmin()){
                            Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                            intent.putExtra("user_list", users);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(MainActivity.this, UserActivity.class);
                            intent.putExtra("user_list", users);
                            intent.putExtra("user", foundUser);
                            startActivity(intent);

                        }
                    }

               }
            }
        });

    }
}