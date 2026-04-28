package com.example.team7.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "outfit_clothing_cross_ref",
        primaryKeys = {"outfitId", "clothingId"},
        foreignKeys = {
                @ForeignKey(entity = Outfit.class,
                        parentColumns = "outfitId",
                        childColumns = "outfitId",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(entity = Clothing.class,
                        parentColumns = "clothingId",
                        childColumns = "clothingId",
                        onDelete = ForeignKey.CASCADE
                )
        },
        indices = {@Index(value = "outfitId"), @Index(value = "clothingId")}
)
public class OutfitClothingCrossRef {
    private int outfitId;
    private int clothingId;

    public OutfitClothingCrossRef(int outfitId, int clothingId) {
        this.outfitId = outfitId;
        this.clothingId = clothingId;
    }

    public int getOutfitId() {
        return outfitId;
    }

    public void setOutfitId(int outfitId) {
        this.outfitId = outfitId;
    }

    public int getClothingId() {
        return clothingId;
    }

    public void setClothingId(int clothingId) {
        this.clothingId = clothingId;
    }
}
