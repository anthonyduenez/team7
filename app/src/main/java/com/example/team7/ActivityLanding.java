package com.example.team7;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import com.example.team7.databinding.ActivityLandingBinding;



public class ActivityLanding extends Activity {
    ActivityLandingBinding binding;

    private static final String TAG = "DAC_FITTRACKER";
    String mUsername = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mUsername = getIntent().getStringExtra("username");
        if (mUsername == null) mUsername = "User";
        binding.Welcome.setText("Welcome " + mUsername + "!");

        binding.pastOutfits.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
            }
        });

        binding.createOutfit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            }

        });


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
        startActivity(new Intent(ActivityLanding.this, MainActivity.class));
        SharedPreferences sharedPreferences = getSharedPreferences("????", MODE_PRIVATE);
        startActivity(new Intent(ActivityLanding.this, MainActivity.class));
    }
}
