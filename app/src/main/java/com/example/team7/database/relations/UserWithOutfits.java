package com.example.team7.database.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.team7.database.entities.Outfit;
import com.example.team7.database.entities.User;

import java.util.List;

public class UserWithOutfits {
    @Embedded
    protected User user;

    @Relation(parentColumn = "userId",
              entityColumn = "userId"
    )
    protected List<Outfit> outfits;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Outfit> getOutfits() {
        return outfits;
    }

    public void setOutfits(List<Outfit> outfits) {
        this.outfits = outfits;
    }
}
