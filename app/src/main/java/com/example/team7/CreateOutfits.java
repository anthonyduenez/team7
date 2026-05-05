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

    int idx1 = 0;
    int idx2 = 0;
    int idx3 = 0;


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
        userObserver.observe(this, user -> {


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
                    case "legs":
                        bottom_clothes.add(c);
                        break;
                }
            }



            update_images();

        }  });
        binding.back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                back();
            }
        });

        binding.headRight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                idx1= update_idx(idx1, head_clothes, true);
                update_images();
            }
        });

        binding.headLeft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                idx1= update_idx(idx1, head_clothes, false);
                update_images();
            }
        });

        binding.torsoRight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                idx2 = update_idx(idx2, torso_clothes, true);
                update_images();
            }
        });

        binding.torsoLeft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                idx2 = update_idx(idx2, torso_clothes, false);
                update_images();
            }
        });


        binding.bottomRight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                idx3= update_idx(idx3, bottom_clothes, true);
                update_images();
            }
        });

        binding.bottomLeft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                idx3 = update_idx(idx3, bottom_clothes, false);
                update_images();
            }
        });


    }

    private int update_idx(int idx, List<Clothing> lst, boolean increase){
        if (increase && idx < lst.size()-1)
            idx++;
        else if (increase){
            idx = 0;
        }else if (idx > 0){
            idx--;
        }else
            idx = lst.size()-1;
        return idx;
    }


    private void update_images(){
        if (!head_clothes.isEmpty())
            binding.headImage.setImageURI(Uri.parse(head_clothes.get(idx1).getClothingImage()));

        if (!torso_clothes.isEmpty())
            binding.torsoImage.setImageURI(Uri.parse(torso_clothes.get(idx2).getClothingImage()));

        if (!bottom_clothes.isEmpty())
            binding.bottomImage.setImageURI(Uri.parse(bottom_clothes.get(idx3).getClothingImage()));

    }

    private void back(){
        Intent intent = new Intent(this, ActivityLanding.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("username", mUsername);
        startActivity(intent);
    }
}
