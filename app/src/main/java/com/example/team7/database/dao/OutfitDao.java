package com.example.team7.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.team7.database.entities.Outfit;
import com.example.team7.database.relations.OutfitClothingCrossRef;
import com.example.team7.database.relations.OutfitWithClothing;

import java.util.List;

@Dao
public interface OutfitDao {
    @Insert
    Long insertOutfit(Outfit outfit);

    @Insert
    void addClothingToOutfit(OutfitClothingCrossRef crossRef);

    @Query("DELETE FROM outfit_clothing_cross_ref WHERE outfitId = :outfitId and clothingId = :clothingId")
    void removeClothingFromOutfit(int outfitId, int clothingId);

    @Query("SELECT * FROM outfits WHERE userId = :userId")
    List<Outfit> getOutfitsForUser(int userId);

    @Transaction
    @Query("SELECT * FROM outfits WHERE outfitId = :outfitId")
    OutfitWithClothing getOutfitWithClothing(int outfitId);

    @Query("DELETE FROM outfits WHERE outfitId = :outfitId")
    void deleteOutfit(int outfitId);

    @Query("DELETE FROM outfits WHERE userId = :userId")
    void deleteOutfitsForUser(int userId);
}
