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


}
