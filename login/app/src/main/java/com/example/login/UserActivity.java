package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    ArrayList<User> users = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        users = (ArrayList<User>) getIntent().getSerializableExtra("users_list");
        User user = (User) getIntent().getSerializableExtra("user");

        TextView welcomeText = findViewById(R.id.textViewWelcome);
        welcomeText.setText("Witaj " + user.getName() + " " + user.getSurname());

        Button exitButton = findViewById(R.id.buttonExitUser);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, MainActivity.class);
                intent.putExtra("user_list", users);
                startActivity(intent);
            }
        });



    }
}