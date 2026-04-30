package com.example.team7.database.relations;

import androidx.room.Embedded;
import androidx.room.Relation;
import androidx.room.Junction;

import com.example.team7.database.entities.Outfit;
import com.example.team7.database.entities.Clothing;

import java.util.List;

public class OutfitWithClothing {
    @Embedded
    Outfit outfit;

    @Relation(parentColumn = "outfitId",
              entityColumn = "clothingId",
              associateBy = @Junction(OutfitClothingCrossRef.class)
    )
    List<Clothing> clothingItems;

    public Outfit getOutfit() {
        return outfit;
    }

    public void setOutfit(Outfit outfit) {
        this.outfit = outfit;
    }

    public List<Clothing> getClothingItems() {
        return clothingItems;
    }

    public void setClothingItems(List<Clothing> clothingItems) {
        this.clothingItems = clothingItems;
    }
}
