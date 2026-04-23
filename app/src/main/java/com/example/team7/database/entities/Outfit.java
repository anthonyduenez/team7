package com.example.team7.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "outfits",
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "userId",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE
                )},
        indices = {@Index(value = "userId")}
)
public class Outfit {
    @PrimaryKey(autoGenerate = true)
    private int outfitId = 0;
    private int userId;
    private Long dateCreated;

    public Outfit(int userId, String name) {
        this.userId = userId;
        this.dateCreated = System.currentTimeMillis();
    }
}
