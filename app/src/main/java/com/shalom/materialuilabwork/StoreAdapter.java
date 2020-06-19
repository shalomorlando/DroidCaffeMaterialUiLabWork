package com.shalom.materialuilabwork;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mLayoutInflater;
    private ArrayList<Store> mStoreArrayList;

    public StoreAdapter(Context context, ArrayList<Store> storeArrayList) {
        mContext = context;
        mStoreArrayList = storeArrayList;
        mLayoutInflater = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.stores_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Store currentStore = mStoreArrayList.get(position);
        Glide.with(mContext).load(currentStore.getStoreImage()).into(holder.storeImage);
        holder.storeName.setText(currentStore.getStoreName());
        holder.storeDescription.setText(currentStore.getStoreDescription());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return mStoreArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView storeImage;
        private TextView storeName;
        private TextView storeDescription;
        private TextView mLike;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storeImage = (ImageView) itemView.findViewById(R.id.store_image);
            storeName = (TextView) itemView.findViewById(R.id.store_title);
            storeDescription = (TextView) itemView.findViewById(R.id.store_description);
            mLike = (TextView) itemView.findViewById(R.id.store_like);
            mLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Left, top, right, bottom drawables.
                    Drawable[] compoundDrawables = mLike.getCompoundDrawables();
                    // This is the left drawable.
                    Drawable startCompoundDrawable = compoundDrawables[0];
                    DrawableCompat.setTint(startCompoundDrawable, ContextCompat.getColor(mContext, R.color.colorAccent));

                    Toast.makeText(mContext, "You have liked " + storeName.getText().toString() + " Mall",
                            Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
