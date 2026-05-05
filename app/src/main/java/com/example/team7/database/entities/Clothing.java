package com.example.team7.database.entities;

import android.provider.MediaStore;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Entity(tableName = "clothes",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "userId",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE
                )},
        indices = {@Index(value = "userId")})
public class Clothing {
    @PrimaryKey(autoGenerate = true)
    private int clothingId = 0;
    private int userId;
    private long dateWorn;
    private String clothingName;
    private String clothingType;
    private String clothingImage;

    public Clothing(int userId, String clothingName, String clothingType, String clothingImage) {
        this.userId = userId;
        this.clothingName = clothingName;
        this.clothingType = clothingType;
        this.clothingImage = clothingImage;
        this.dateWorn = System.currentTimeMillis();
    }

    public int getClothingId() {
        return clothingId;
    }

    public void setClothingId(int clothingId) {
        this.clothingId = clothingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getDateWorn() {
        return dateWorn;
    }

    public void setDateWorn(long dateWorn) {
        this.dateWorn = dateWorn;
    }

    public String getClothingName() {
        return clothingName;
    }

    public void setClothingName(String clothingName) {
        this.clothingName = clothingName;
    }

    public String getClothingType() {
        return clothingType;
    }

    public void setClothingType(String clothingType) {
        this.clothingType = clothingType;
    }

    public String getClothingImage() {
        return clothingImage;
    }

    public void setClothingImage(String clothingImage) {
        this.clothingImage = clothingImage;
    }
}
