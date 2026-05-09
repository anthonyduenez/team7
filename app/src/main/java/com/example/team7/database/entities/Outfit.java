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

    public String getUri1() {
        return uri1;
    }

    public void setUri1(String uri1) {
        this.uri1 = uri1;
    }

    public String getUri2() {
        return uri2;
    }

    public void setUri2(String uri2) {
        this.uri2 = uri2;
    }

    public String getUri3() {
        return uri3;
    }

    public void setUri3(String uri3) {
        this.uri3 = uri3;
    }

    private String uri1;
    private String uri2;
    private String uri3;


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

    public String[] getUris(){
        return new String[]{uri1, uri2, uri3};
    }

    public void setUris(String u1, String u2, String u3){
        this.uri1 = u1;
        this.uri2 = u2;
        this.uri3 = u3;
    }
}
