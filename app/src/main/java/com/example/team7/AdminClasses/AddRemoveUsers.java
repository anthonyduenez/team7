package com.example.team7.AdminClasses;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.team7.UserAdapter;
import com.example.team7.database.WardrobeRepository;
import com.example.team7.database.entities.User;
import com.example.team7.databinding.ActivityAddRemoveUsersBinding;
import com.example.team7.databinding.ActivityAdminSettingsBinding;

import java.util.ArrayList;

public class AddRemoveUsers extends AppCompatActivity {

    private ActivityAddRemoveUsersBinding binding;
    private WardrobeRepository repository;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddRemoveUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = WardrobeRepository.getRepository(getApplication());
        adapter = new UserAdapter(repository);
        binding.userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.userRecyclerView.setAdapter(adapter);
        repository.getAllUsers().observe(this, users -> {
            if(users != null)
                adapter.setUsers(users);
        });

        binding.createUserButton.setOnClickListener(v -> showCreateUserDialogue());
        binding.back.setOnClickListener(v -> finish());

    }

    public void showCreateUserDialogue() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40,20,40,0);

        EditText usernameInput = new EditText(this);
        usernameInput.setHint("Username");
        EditText passwordInput = new EditText(this);
        passwordInput.setHint("Password");

        layout.addView(usernameInput);
        layout.addView(passwordInput);

        new AlertDialog.Builder(this)
                .setTitle("Create New User")
                .setView(layout)
                .setPositiveButton("Create", ((dialog, which) -> {
                    String username = usernameInput.getText().toString().trim();
                    String password = passwordInput.getText().toString().trim();
                    if(username.isEmpty() || password.isEmpty()) {
                        Toast.makeText(this, "Username and Password Required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    User newUser = new User(username, password);
                    repository.insertUser(newUser);

                    Toast.makeText(this, "User Created!", Toast.LENGTH_SHORT).show();
                }))
                .setNegativeButton("Cancel", null)
                .show();

    }
}