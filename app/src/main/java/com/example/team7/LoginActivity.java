package com.example.team7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.team7.AdminClasses.AdminLanding;
import com.example.team7.database.WardrobeRepository;
import com.example.team7.database.entities.User;
import com.example.team7.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private WardrobeRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = WardrobeRepository.getRepository(getApplication());

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyUser();
            }
        });
    }

    protected void verifyUser() {
        String username = binding.usernameLoginEditText.getText().toString();

        if(username.isEmpty()) {
            toastMaker("Username cannot be empty");
            return;
        }

        LiveData<User> userObserver = repository.findUserByUsername(username);
        userObserver.observe(this, user -> {
            if(user != null) {
                String password = binding.passwordLoginEditText.getText().toString();
                if(password.equals(user.getUserPassword())) {
                    SharedPreferences sharedPreferences = getApplication()
                            .getSharedPreferences("prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("userId", user.getUserId());
                    editor.apply();
                    Intent intent;
                    if(user.isAdmin()) {
                        intent = new Intent(this, AdminLanding.class);
                    } else {
                        intent = new Intent(this, ActivityLanding.class);
                    }
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {
                    toastMaker("Incorrect password");
                    binding.passwordLoginEditText.setSelection(0);                }

            } else {
                toastMaker("Username not found");
                binding.usernameLoginEditText.setSelection(0);
            }
        });

    }

    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public static Intent loginIntentFactory(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}