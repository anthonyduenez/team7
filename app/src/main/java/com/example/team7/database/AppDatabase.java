package com.example.team7.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

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
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract OutfitDao outfitDao();
    public abstract ClothingDao clothingDao();
}
