package com.example.team7.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.team7.database.entities.Clothing;

import java.util.List;

@Dao
public interface ClothingDao {
    @Insert
    Void insertClothing(Clothing item);

    @Query("SELECT * FROM clothes WHERE userId = :userId")
    List<Clothing> getClothingForUser(int userId);

    @Query("DELETE FROM clothes WHERE clothingId = :clothingId")
    void deleteClothing(int clothingId);
}
