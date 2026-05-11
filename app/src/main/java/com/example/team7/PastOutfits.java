package com.example.team7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.team7.database.WardrobeRepository;
import com.example.team7.database.entities.Outfit;
import com.example.team7.databinding.PastOutfitsBinding;

import java.util.List;


public class PastOutfits extends AppCompatActivity {
    private PastOutfitsBinding binding;

    private WardrobeRepository repository;

    public static Intent mainIntentFactory(Context applicationContext, int userId) {
        Intent intent = new Intent(applicationContext, PastOutfits.class);
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userId);
        editor.apply();
        return intent;
    }

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

        int userId = getSharedPreferences("prefs", MODE_PRIVATE).getInt("userId", -1);
        showPastOutfits(userId);

        binding.back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                back();
            }
        });
    }

    private void showPastOutfits(int userId) {
        binding.outfitContainer.removeAllViews();

        if (userId < 0) {
            binding.emptyState.setVisibility(View.VISIBLE);
            binding.emptyState.setText(R.string.no_user_found);
            return;
        }

        List<Outfit> outfitList = repository.getOutfitsForUser(userId);
        if (outfitList == null || outfitList.isEmpty()) {
            binding.emptyState.setVisibility(View.VISIBLE);
            binding.emptyState.setText(R.string.no_past_outfits);
            return;
        }

        binding.emptyState.setVisibility(View.GONE);

        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        imageParams.setMargins(0, 16, 0, 0);

        for (Outfit outfit : outfitList) {
            addOutfitHeader(outfit);
            String[] uris = outfit.getUris();
            for (String uri : uris) {
                if (uri == null || uri.isEmpty()) {
                    continue;
                }

                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(imageParams);
                imageView.setAdjustViewBounds(true);
                imageView.setImageURI(Uri.parse(uri));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                binding.outfitContainer.addView(imageView);
            }
        }
    }

    private void addOutfitHeader(Outfit outfit) {
        TextView headerView = new TextView(this);
        headerView.setText(getString(R.string.outfit_number, outfit.getOutfitId()));
        headerView.setTextSize(20);

        LinearLayout.LayoutParams headerParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        headerParams.setMargins(0, 24, 0, 0);
        headerView.setLayoutParams(headerParams);

        binding.outfitContainer.addView(headerView);
    }


    private void back(){
            finish();
    }
}
