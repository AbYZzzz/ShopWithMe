package com.aj.android.shopwithme;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Item item);
    @Delete
    void delete(Item item);
    @Update
    void update(Item item);
    @Query("DELETE FROM shop_items")
    void deleteAll();
    @Query("SELECT * FROM shop_items ORDER BY dateModified DESC")
    LiveData<List<Item>> getAllFilesItems();

    @Query("SELECT * FROM Shop_Items WHERE itemName LIKE %:S% ORDER BY dateModified DESC")
    LiveData<List<Item>> getSearchList(String S);

}
