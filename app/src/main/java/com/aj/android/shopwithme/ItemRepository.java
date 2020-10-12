package com.aj.android.shopwithme;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Random;

class ItemRepository {
    private static final String TAG = "ItemRepository";
    private ItemDao itemDao;
    private LiveData<List<Item>> itemLiveData;
    ItemRepository(Context context){
        ItemDatabase database = ItemDatabase.getInstance(context);
        itemDao=database.ItemDao();
        itemLiveData=itemDao.getAllFilesItems();
    }

    LiveData<List<Item>> getItemLiveData(){
        return itemLiveData;
    }
     void updateList(){
        Random random= new Random();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<=100;i++){
                    Item item= new Item("item"+random.nextInt(10000),random.nextInt(1000),(i+1)*1000);
                    item.setRating(random.nextInt(50)/10.0f);
                    itemDao.insert(item);
                }
            }
        }).start();
        //TODO: download data and save it in database;
    }
}
