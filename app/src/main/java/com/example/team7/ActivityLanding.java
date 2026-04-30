package com.example.team7;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team7.database.WardrobeRepository;
import com.example.team7.databinding.ActivityLandingBinding;



public class ActivityLanding extends AppCompatActivity {
    private ActivityLandingBinding binding;

    private WardrobeRepository repository;

    private static final String TAG = "DAC_FITTRACKER";
    String mUsername = "";


    public static Intent mainIntentFactory(Context applicationContext, int userId) {
        Intent intent = new Intent(applicationContext, ActivityLanding.class);
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userId);
        editor.apply();
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = WardrobeRepository.getRepository(getApplication());

        mUsername = getIntent().getStringExtra("username");
        if (mUsername == null) mUsername = "User";
        binding.Welcome.setText("Welcome " + mUsername + "!");

        binding.pastOutfits.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                showPast();
            }
        });

        binding.createOutfit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showCreate();
            }

        });

        binding.addClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showNewClothe(); }
        });

        binding.logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showLogOutDialog();
            }
        });
    }

    private void showCreate(){
        Intent intent = new Intent(this, CreateOutfits.class);
        intent.putExtra("username", mUsername);
        startActivity(intent);
    }

    private void showPast(){
        Intent intent = new Intent(this, PastOutfits.class);
        intent.putExtra("username", mUsername);
        startActivity(intent);
    }

    private void showNewClothe(){
        Intent intent = new Intent(this, addClothes.class);
        intent.putExtra("username", mUsername);
        startActivity(intent);
    }

    private void showLogOutDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ActivityLanding.this);
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
