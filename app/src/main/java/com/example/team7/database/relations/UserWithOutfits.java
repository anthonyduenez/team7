package com.example.team7.database.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.team7.database.entities.Outfit;
import com.example.team7.database.entities.User;

import java.util.List;

public class UserWithOutfits {
    @Embedded
    User user;

    @Relation(parentColumn = "userid",
              entityColumn = "userId"
    )
    List<Outfit> outfits;
}
