package com.example.team7.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int userId;
    private String username;
    private String userPassword;

    public User(String username, String password) {
        this.username = username;
        this.userPassword = password;
    }
}
