package com.aj.android.shopwithme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements ItemRecyclerView.onClick {
    private RecyclerView recyclerView;
    private ItemRecyclerView itemRecyclerView;
    private ItemViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.item_list);
        itemRecyclerView= new ItemRecyclerView(this);
        recyclerView.setLayoutManager( new GridLayoutManager(this,2));
        recyclerView.setAdapter(itemRecyclerView);
        viewModel= new ItemViewModel(getApplication());
        viewModel.getListLiveData().observe(this, items -> {
            itemRecyclerView.submitList(items);
        });

    }

    @Override
    public void onShareClick(Item item) {
        Intent builder = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Share "+item.getItemName())
                .getIntent();
        startActivity(builder);
    }
}