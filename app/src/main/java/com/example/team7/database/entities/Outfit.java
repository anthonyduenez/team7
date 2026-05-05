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

//TODO: Add static array of size 3 for clothing ids, and add methods to add/remove clothing from outfit

public class Outfit {
    @PrimaryKey(autoGenerate = true)
    private int outfitId = 0;
    private int userId;
    private Long dateCreated;

    public Outfit(int userId) {
        this.userId = userId;
        this.dateCreated = System.currentTimeMillis();
    }

    public int getOutfitId() {
        return outfitId;
    }

    public void setOutfitId(int outfitId) {
        this.outfitId = outfitId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }
}
