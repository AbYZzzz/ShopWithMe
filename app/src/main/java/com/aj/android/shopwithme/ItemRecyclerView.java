package com.aj.android.shopwithme;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

class ItemRecyclerView extends ListAdapter<Item, ItemRecyclerView.itemViewHolder> {
    private Activity activity;
    ItemRecyclerView(Activity activity) {
        super(itemCallback);
        this.activity=activity;
    }

    private static final DiffUtil.ItemCallback<Item> itemCallback = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getUID() == newItem.getUID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getDateModified() == newItem.getDateModified();
        }
    };

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemRecyclerView.itemViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {
        holder.setItem(getItem(position),activity);
        holder.share.setOnClickListener(v -> {
            ((onClick)activity).onShareClick(getItem(position));
        });
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.shop_items;
    }

    static class itemViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView itemName,itemPrice;
        private RatingBar itemRating;
        private ImageButton share;
        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_img);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_cost);
            itemRating = itemView.findViewById(R.id.item_rating);
            share = itemView.findViewById(R.id.item_share);
        }
        @SuppressLint("UseCompatLoadingForDrawables")
        public void setItem(Item item, Activity context){
            itemName.setText(item.getItemName());
            Glide.with(context)
                    .load(context.getDrawable(R.drawable.ic_launcher_foreground))
                    .fitCenter()
                    .into(itemImage);
            itemPrice.setText(String.format("₹%s", item.getCost()));
            itemRating.setRating(item.getRating());
        }
    }
    public interface onClick{
        void onShareClick(Item item);
    }
}
