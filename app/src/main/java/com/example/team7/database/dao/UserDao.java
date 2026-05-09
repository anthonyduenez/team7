package com.example.team7.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.team7.database.entities.User;
import com.example.team7.database.relations.UserWithOutfits;
import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE userId = :userId")
    User getUserById(int userId);

    @Query("SELECT * FROM users ORDER BY username ASC")
    LiveData<List<User>> getAllUsers();

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Transaction
    @Query("SELECT * FROM users WHERE userId = :userId")
    UserWithOutfits getUserWithOutfits(int userId);

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    LiveData<User> getUserByUsername(String username);

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    User getUserByUsernameSync(String username);
}
