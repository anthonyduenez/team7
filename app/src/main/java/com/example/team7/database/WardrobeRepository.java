package com.example.team7.database;

import com.example.team7.database.dao.OutfitDao;
import com.example.team7.database.dao.ClothingDao;
import com.example.team7.database.dao.UserDao;
import com.example.team7.database.entities.Clothing;
import com.example.team7.database.entities.Outfit;
import com.example.team7.database.entities.OutfitClothingCrossRef;
import com.example.team7.database.entities.User;

import java.util.List;

public class WardrobeRepository {
    private UserDao userDao;
    private OutfitDao outfitDao;
    private ClothingDao clothingDao;

    int createUser(String username, String password) {
        return Math.toIntExact(userDao.insertUser(new User(username, password)));
    }

    User getUser(int userId) {
        return userDao.getUserById(userId);
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
}
