package com.example.team7;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.team7.database.WardrobeRepository;
import com.example.team7.database.entities.User;
import com.example.team7.databinding.CreateOutfitsBinding;
import com.example.team7.databinding.PastOutfitsBinding;


public class PastOutfits extends AppCompatActivity {
    private PastOutfitsBinding binding;

    private WardrobeRepository repository;

    private static final String TAG = "DAC_FITTRACKER";
    String mUsername = "";


    public static Intent mainIntentFactory(Context applicationContext, int userId) {
        Intent intent = new Intent(applicationContext, PastOutfits.class);
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userId);
        editor.apply();
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PastOutfitsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = WardrobeRepository.getRepository(getApplication());

        mUsername = getIntent().getStringExtra("username");
        if (mUsername == null) mUsername = "User";



        binding.back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                back();
            }
        });


    }


    private void back(){
            Intent intent = new Intent(this, ActivityLanding.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("username", mUsername);
            startActivity(intent);
    }
}
