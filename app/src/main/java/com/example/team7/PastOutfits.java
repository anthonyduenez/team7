package com.example.team7;



import static androidx.core.view.ViewGroupKt.setMargins;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.LiveData;

import com.example.team7.database.WardrobeRepository;
import com.example.team7.database.entities.Outfit;
import com.example.team7.database.entities.User;
import com.example.team7.databinding.CreateOutfitsBinding;
import com.example.team7.databinding.PastOutfitsBinding;

import java.util.ArrayList;
import java.util.List;


public class PastOutfits extends AppCompatActivity {
    private PastOutfitsBinding binding;

    private WardrobeRepository repository;

    private static final String TAG = "DAC_FITTRACKER";
    String mUsername = "";

    private static int uid = 0;

    public static Intent mainIntentFactory(Context applicationContext, int userId) {
        uid = userId;

        Intent intent = new Intent(applicationContext, PastOutfits.class);
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userId);
        editor.apply();
        return intent;
    }

    // Convenience intent factory that carries the username as an extra.
    public static Intent intentFactory(Context context, String username) {
        Intent intent = new Intent(context, PastOutfits.class);
        intent.putExtra("username", username);
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



        List<Outfit> outfitList = new ArrayList<>();

        outfitList = repository.getOutfitsForUser(uid);

        String[] uris = outfitList.get(0).getUris();


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                300
        );
        params.setMargins(16, 16, 16, 16);

        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setImageURI(Uri.parse(uris[i]));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            binding.main.addView(imageView);
        }

        binding.back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                back();
            }
        });


    }


    private void back(){
            finish();
    }
}
