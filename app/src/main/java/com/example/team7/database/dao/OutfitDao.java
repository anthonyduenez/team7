package com.example.team7.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.team7.database.entities.Outfit;
import com.example.team7.database.entities.OutfitClothingCrossRef;
import com.example.team7.database.relations.OutfitWithClothing;

import java.util.List;

@Dao
public interface OutfitDao {
    @Insert
    Long insertOutfit(Outfit outfit);

    @Insert
    void addClothingToOutfit(OutfitClothingCrossRef crossRef);

    @Insert
    void addClothingToOutfit(List<OutfitClothingCrossRef> crossRefs);

    @Query("DELETE FROM outfit_clothing_cross_ref WHERE outfitId = :outfitId and clothingId = :clothingId")
    void removeClothingFromOutfit(int outfitId, int clothingId);

    @Transaction
    @Query("SELECT * FROM outfits WHERE outfitId = :outfitId")
    OutfitWithClothing getOutfitWithClothing(int outfitId);
}
