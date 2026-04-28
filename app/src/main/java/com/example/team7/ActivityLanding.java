package com.example.team7;



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

        binding.pastOutfits.setOnClickListener(new View.OnClickListener() {
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
}
