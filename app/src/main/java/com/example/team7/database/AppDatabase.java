package com.example.team7.database;

import androidx.room.Database;

import com.example.team7.database.dao.OutfitDao;
import com.example.team7.database.dao.ClothingDao;
import com.example.team7.database.dao.UserDao;
import com.example.team7.database.entities.Outfit;
import com.example.team7.database.entities.Clothing;
import com.example.team7.database.entities.OutfitClothingCrossRef;
import com.example.team7.database.entities.User;

@Database(entities = {User.class,
                      Outfit.class,
                      Clothing.class,
                      OutfitClothingCrossRef.class},
          version = 1,
          exportSchema = false
)
public abstract class AppDatabase {
    abstract UserDao userDao();
    abstract OutfitDao outfitDao();
    abstract ClothingDao clothingDao();
}
