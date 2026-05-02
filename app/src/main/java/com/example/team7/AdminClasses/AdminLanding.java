package com.example.team7.AdminClasses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team7.CreateOutfits;
import com.example.team7.LoginActivity;
import com.example.team7.PastOutfits;
import com.example.team7.database.WardrobeRepository;
import com.example.team7.databinding.ActivityAdminLandingBinding;

public class AdminLanding extends AppCompatActivity {
    private ActivityAdminLandingBinding binding;
    private WardrobeRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = WardrobeRepository.getRepository(getApplication());
        String Username = getIntent().getStringExtra("username");
        if (Username == null) Username = "User";
        binding.titleLoginTextView.setText("Welcome " + Username + "!");


        binding.pastOutfits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLanding.this, PastOutfits.class);
                startActivity(intent);
            }
        });

        binding.createOutfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLanding.this, CreateOutfits.class);
                startActivity(intent);
            }
        });

        binding.adminSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLanding.this, AdminSettings.class);
                startActivity(intent);
            }
        });

        binding.logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showLogOutDialog();
            }
        });



    }

    private void showLogOutDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(AdminLanding.this);
        alertBuilder.setMessage("Logout");

        alertBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }

        });
        alertBuilder.create().show();
    }

    private void logout(){
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", -1);
        editor.apply();
        startActivity(LoginActivity.loginIntentFactory(getApplicationContext()));
    }
}


