package com.example.team7;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.team7.database.WardrobeRepository;
import com.example.team7.databinding.ActivityAddClothesBinding;

import java.io.File;

public class AddClothes extends AppCompatActivity {
    private ActivityAddClothesBinding binding;
    private static final String TAG = "DAC_FITTRACKER";

    private ActivityResultLauncher<Intent> cameraLauncher;
    private ActivityResultLauncher<Intent> galleryLauncher;
    String mUsername = "";
    private ActivityResultLauncher<String> cameraPermissionLauncher;

    private Uri selectedImageUri = null;
    private Uri cameraImageUri = null;

    private WardrobeRepository repository;

    public static Intent mainIntentFactory(Context applicationContext, int userId) {
        Intent intent = new Intent(applicationContext, AddClothes.class);
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userId);
        editor.apply();
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddClothesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = WardrobeRepository.getRepository(getApplication());

        ArrayAdapter<CharSequence> options = ArrayAdapter.createFromResource(this, R.array.clothing_types, android.R.layout.simple_spinner_item);
        options.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.clothingType.setAdapter(options);

        mUsername = getIntent().getStringExtra("username");
        if (mUsername == null) mUsername = "User";

        cameraPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        openCamera();
                    } else {
                        new AlertDialog.Builder(this)
                                .setTitle("Permission Denied")
                                .setMessage("Camera permission is required to take photos.")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                }
        );

        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        selectedImageUri = cameraImageUri;
                        binding.picture.setImageURI(selectedImageUri);
                    }
                }
        );

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        if (imageUri != null) {
                            selectedImageUri = imageUri;
                            binding.picture.setImageURI(selectedImageUri);
                        }
                    }
                }
        );

        binding.picture.setOnClickListener(v -> {
            String[] cameraOptions = {"Take Photo", "Choose from Gallery"};

            new AlertDialog.Builder(this)
                    .setTitle("Add Picture")
                    .setItems(cameraOptions, (dialog, which) -> {
                        if (which == 0) {
                            cameraPermissionLauncher.launch(Manifest.permission.CAMERA);
                        } else {
                            openGallery();
                        }
                    })
                    .show();
        });

        binding.save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                save();
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    public void openCamera() {
        File imageFile = createImageFile();

        if (imageFile == null) return;

        cameraImageUri = FileProvider.getUriForFile(this, "com.example.team7.fileprovider", imageFile);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraImageUri);
        cameraLauncher.launch(cameraIntent);
    }

    public File createImageFile() {
        String imageFileName = "JPEG_" + System.currentTimeMillis() + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        try {
            return File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        galleryLauncher.launch(galleryIntent);
    }

    private void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);

        String name = binding.nameEditText.getText().toString().trim();
        String type = binding.clothingType.getSelectedItem().toString();
        String imageUri = selectedImageUri != null ? selectedImageUri.toString() : "";

        if (userId == -1) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("User not found")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        if (name.isEmpty() || imageUri.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Missing info")
                    .setMessage("Enter a name and choose an image")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

            repository.addClothing(userId, name, type, imageUri);
            back();
    }

    private void back() {
        Intent intent = new Intent(this, ActivityLanding.class);
     //   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("username", mUsername);
        startActivity(intent);
    }
}