package com.example.team7.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "clothes",
        foreignKeys = {
                @ForeignKey(entity = Outfit.class,
                        parentColumns = "userId",
                        childColumns = "userId",
                        onDelete = androidx.room.ForeignKey.CASCADE
                )},
        indices = {@Index(value = "outfitId")})
public class Clothing {
    @PrimaryKey(autoGenerate = true)
    private int clothingId = 0;
    private int userId;
    private long dateWorn;
    private String clothingName;
    private String clothingType;
    private String clothingImage;

    public Clothing(int userId, String name, String type, String image) {
        this.userId = userId;
        this.clothingName = name;
        this.clothingType = type;
        this.clothingImage = image;
        this.dateWorn = System.currentTimeMillis();
    }
}
