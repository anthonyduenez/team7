package com.example.team7.AdminClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team7.databinding.ActivityAdminSettingsBinding;

public class AdminSettings extends AppCompatActivity {

    private ActivityAdminSettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


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
}