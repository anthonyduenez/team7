package com.example.team7;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.team7.database.WardrobeRepository;
import com.example.team7.database.entities.Clothing;
import com.example.team7.database.entities.User;
import com.example.team7.databinding.CreateOutfitsBinding;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;


public class CreateOutfits extends AppCompatActivity {
    private CreateOutfitsBinding binding;

    private WardrobeRepository repository;

    private static final String TAG = "DAC_FITTRACKER";
    String mUsername = "";

    List<Clothing> bottom_clothes;
    List<Clothing> torso_clothes;
    List<Clothing> head_clothes;


    public static Intent mainIntentFactory(Context applicationContext, int userId) {
        Intent intent = new Intent(applicationContext, CreateOutfits.class);
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userId);
        editor.apply();
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CreateOutfitsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = WardrobeRepository.getRepository(getApplication());

        mUsername = getIntent().getStringExtra("username");
        if (mUsername == null)
            mUsername = "User";

        LiveData<User> userObserver = repository.findUserByUsername(mUsername);
        AtomicReference<User> user_holder = new AtomicReference<>();
        userObserver.observe(this, u -> {
            user_holder.set(u);
        });

        User user = user_holder.get();


        if (user != null) {
            List<Clothing> all_clothes = repository.getClothingForUser(user.getUserId());
            head_clothes = new ArrayList<>();
            torso_clothes = new ArrayList<>();
            bottom_clothes = new ArrayList<>();


            for (Clothing c : all_clothes) {
                switch (c.getClothingType().toLowerCase()) {
                    case "head":
                        head_clothes.add(c);
                        break;
                    case "torso":
                        torso_clothes.add(c);
                        break;
                    case "bottom":
                        bottom_clothes.add(c);
                        break;
                }
            }

            if (!head_clothes.isEmpty())
                binding.headImage.setImageURI(Uri.parse(head_clothes.get(0).getClothingImage()));


        }
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
