package com.example.team7.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.team7.database.dao.OutfitDao;
import com.example.team7.database.dao.ClothingDao;
import com.example.team7.database.dao.UserDao;
import com.example.team7.database.entities.Clothing;
import com.example.team7.database.entities.Outfit;
import com.example.team7.database.entities.OutfitClothingCrossRef;
import com.example.team7.database.entities.User;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class WardrobeRepository {
    private final UserDao userDao;
    private final OutfitDao outfitDao;
    private final ClothingDao clothingDao;
    private static WardrobeRepository repository;

    private WardrobeRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.userDao = db.userDao();
        this.outfitDao = db.outfitDao();
        this.clothingDao = db.clothingDao();
    }

    public static WardrobeRepository getRepository(Application application) {
        if (repository == null) {
            repository = new WardrobeRepository(application);
        }
        return repository;
    }

    //methods for outfits and clothing below

    public long addClothing(int userId, String name, String type, String image) {
        Clothing item = new Clothing(userId, name, type, image);
        return clothingDao.insertClothing(item);
    }

    public void removeClothing(int clothingId) {
        clothingDao.deleteClothing(clothingId);
    }

    public long addOutfit(int userId) {
        Outfit outfit = new Outfit(userId);
        return outfitDao.insertOutfit(outfit);
    }

    public void addClothingToOutfit(int outfitId, int clothingId) {
        OutfitClothingCrossRef crossRef = new OutfitClothingCrossRef(outfitId, clothingId);
        outfitDao.addClothingToOutfit(crossRef);
    }

    public void removeClothingFromOutfit(int outfitId, int clothingId) {
        outfitDao.removeClothingFromOutfit(outfitId, clothingId);
    }

    public void removeOutfit(int outfitId) {
        List<Clothing> clothingItems = outfitDao.getOutfitWithClothing(outfitId).getClothingItems();
        for (Clothing item : clothingItems) {
            outfitDao.removeClothingFromOutfit(outfitId, item.getClothingId());
        }

        outfitDao.deleteOutfit(outfitId);
    }

    public Outfit getOutfitWithClothing(int outfitId) {
        return outfitDao.getOutfitWithClothing(outfitId).getOutfit();
    }

    public List<Clothing> getClothingForOutfit(int outfitId) {
        return outfitDao.getOutfitWithClothing(outfitId).getClothingItems();
    }

    public List<Outfit> getOutfitsForUser(int userId) {
        return outfitDao.getOutfitsForUser(userId);
    }

    public List<Clothing> getClothingForUser(int userId) {
        return clothingDao.getClothingForUser(userId);
    }

    //methods for users below

    public LiveData<User> findUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
}
