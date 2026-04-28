package com.example.team7;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.team7.database.WardrobeRepository;
import com.example.team7.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private WardrobeRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = WardrobeRepository.getRepository(getApplication());
    }
}