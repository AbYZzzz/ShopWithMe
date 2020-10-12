package com.aj.android.shopwithme;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

class ItemViewModel extends AndroidViewModel {
    private ItemRepository itemRepository;
    private LiveData<List<Item>> listLiveData;
    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemRepository= new ItemRepository(application);
        listLiveData= itemRepository.getItemLiveData();
        updateList();
    }

    public LiveData<List<Item>> getListLiveData() {
        return listLiveData;
    }

    public void updateList(){
        itemRepository.updateList();
    }
}
