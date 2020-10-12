package com.aj.android.shopwithme;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Item.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {

    private static ItemDatabase instance;

    public abstract ItemDao ItemDao();

    public static ItemDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (ItemDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext()
                            , ItemDatabase.class, "shop_items")
                            .fallbackToDestructiveMigration()
                            .addCallback(fileItemCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback fileItemCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };

}
