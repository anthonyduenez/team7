package com.example.team7.AdminClasses;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team7.R;
import com.example.team7.database.WardrobeRepository;
import com.example.team7.databinding.ActivityAdminSettingsBinding;

public class AdminSettings extends AppCompatActivity {

    private ActivityAdminSettingsBinding binding;
    private WardrobeRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = WardrobeRepository.getRepository(getApplication());


        binding.addRemoveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminSettings.this, AddRemoveUsers.class);
                startActivity(intent);
            }
        });

        binding.clearLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClearLogDialog();
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminSettings.this, AdminLanding.class);
                startActivity(intent);
            }
        });
    }

    private void showClearLogDialog() {
        final EditText usernameInput = new EditText(this);
        usernameInput.setHint(R.string.username);

        new AlertDialog.Builder(this)
                .setTitle(R.string.clear_user_log)
                .setMessage(R.string.clear_log_prompt)
                .setView(usernameInput)
                .setPositiveButton(R.string.clear, (dialog, which) -> {
                    String username = usernameInput.getText().toString().trim();
                    if (username.isEmpty()) {
                        toastMaker(getString(R.string.clear_log_username_required));
                        return;
                    }
                    showConfirmClearDialog(username);
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    private void showConfirmClearDialog(String username) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.clear_user_log)
                .setMessage(getString(R.string.clear_log_confirm_message, username))
                .setPositiveButton(R.string.clear, (dialog, which) -> clearUserLog(username))
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    private void clearUserLog(String username) {
        repository.clearUserLogByUsername(username, (success, isAdminUser) -> runOnUiThread(() -> {
            if (success) {
                toastMaker(getString(R.string.clear_log_success, username));
            } else if (isAdminUser) {
                toastMaker(getString(R.string.clear_log_admin_protected));
            } else {
                toastMaker(getString(R.string.clear_log_user_not_found));
            }
        }));
    }

    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}