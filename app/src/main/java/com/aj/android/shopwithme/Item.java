package com.aj.android.shopwithme;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;


@Entity (tableName = "Shop_Items")
public class Item {
    @NonNull
    private String itemName;
    private float cost;
    private float rating;
    private String disptn;
    private long dateModified;
    @PrimaryKey
    private int UID;
    private String imgUrl;
    @Ignore
    private Status status;

    public Item(@NonNull String itemName, float cost,int UID) {
        this.itemName = itemName;
        this.cost = cost;
        this.UID = UID;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setDisptn(String disptn) {
        this.disptn = disptn;
    }

    public void setDateModified(long dateModified) {
        this.dateModified = dateModified;
    }

    public void setImgUrl(String url) {
        this.imgUrl = url;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getUID(){
        return UID;
    }

    @NotNull
    public String getItemName() {
        return itemName;
    }

    @NotNull
    public float getCost() {
        return cost;
    }

    public float getRating() {
        return rating;
    }

    public String getDisptn() {
        return disptn;
    }

    public long getDateModified() {
        return dateModified;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
