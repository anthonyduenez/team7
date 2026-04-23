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
                        onDelete = androidx.room.ForeignKey.CASCADE
                ),
                @ForeignKey(entity = Clothing.class,
                        parentColumns = "clothingId",
                        childColumns = "clothingId",
                        onDelete = androidx.room.ForeignKey.CASCADE
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
}
