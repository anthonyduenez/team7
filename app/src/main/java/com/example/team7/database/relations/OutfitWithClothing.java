package com.example.team7.database.relations;

import androidx.room.Embedded;
import androidx.room.Relation;
import androidx.room.Junction;

import com.example.team7.database.entities.Outfit;
import com.example.team7.database.entities.Clothing;
import com.example.team7.database.entities.OutfitClothingCrossRef;

import java.util.List;

public class OutfitWithClothing {
    @Embedded
    Outfit outfit;

    @Relation(parentColumn = "outfitId",
              entityColumn = "clothingId",
              associateBy = @Junction(OutfitClothingCrossRef.class)
    )
    List<Clothing> clothingItems;
}
