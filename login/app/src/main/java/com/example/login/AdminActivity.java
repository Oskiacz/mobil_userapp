package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {


    ArrayList<User> users = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    int selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        users = (ArrayList<User>) getIntent().getSerializableExtra("user_list");
        for(User user: users) {
            names.add(user.getName() + " " + user.getSurname());
        }

        Button exitButton = findViewById(R.id.buttonExit);
        Button addButton = findViewById(R.id.buttonAdd);
        Button deleteButton = findViewById(R.id.buttonDelete);
        Button editButton = findViewById(R.id.buttonEdit);
        ListView listView = findViewById(R.id.listViewUser);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        EditText etName = findViewById(R.id.editTextName);
        EditText etSurname = findViewById(R.id.editTextSurname);
        EditText etLogin = findViewById(R.id.editTextLogin);
        EditText etPassword = findViewById(R.id.editTextPassword);
        Switch switchAdmin = findViewById(R.id.switchAdmin);



        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                intent.putExtra("user_list", users);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User newUser = new User(
                        etLogin.getText().toString(),
                        etPassword.getText().toString(),
                        etName.getText().toString(),
                        etSurname.getText().toString(),
                        false
                );
                users.add(newUser);
                names.add(newUser.getName() + " " + newUser.getSurname());
                adapter.notifyDataSetChanged();

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!users.get(selectedUser).getName().equals("admin")) {
                    names.remove(selectedUser);
                    users.remove(selectedUser);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!users.get(selectedUser).getName().equals("admin")) {
                    users.get(selectedUser).setName(etName.getText().toString());
                    users.get(selectedUser).setSurname(etSurname.getText().toString());
                    users.get(selectedUser).setLogin(etLogin.getText().toString());
                    users.get(selectedUser).setPassword(etPassword.getText().toString());
                    users.get(selectedUser).setAdmin(switchAdmin.isChecked());
                }

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedUser = position;
                etName.setText(users.get(selectedUser).getName());
                etSurname.setText(users.get(selectedUser).getSurname());
                etLogin.setText(users.get(selectedUser).getLogin());
                etPassword.setText(users.get(selectedUser).getPassword());
                switchAdmin.setChecked(users.get(selectedUser).isAdmin());


            }
        });
    }
}