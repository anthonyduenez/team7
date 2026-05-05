package com.example.team7.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.team7.database.entities.User;
import com.example.team7.database.relations.UserWithOutfits;

@Dao
public interface UserDao {
    @Insert
    long insertUser(User user);

    @Query("SELECT * FROM users WHERE userId = :userId")
    User getUserById(int userId);



    @Transaction
    @Query("SELECT * FROM users WHERE userId = :userId")
    UserWithOutfits getUserWithOutfits(int userId);

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    LiveData<User> getUserByUsername(String username);

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    User getUserByUsernameSync(String username);
}
