package com.shalom.materialuilabwork;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
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

import java.io.File;
import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mLayoutInflater;
    private ArrayList<Store> mStoreArrayList;
    private Store mCurrentStore;

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

        mCurrentStore = mStoreArrayList.get(position);
        Glide.with(mContext).load(mCurrentStore.getStoreImage()).into(holder.storeImage);
        holder.storeName.setText(mCurrentStore.getStoreName());
        holder.storeDescription.setText(mCurrentStore.getStoreDescription());
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
        private TextView mComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storeImage = itemView.findViewById(R.id.store_image);
            storeName = itemView.findViewById(R.id.store_title);
            storeDescription = itemView.findViewById(R.id.store_description);
            mLike = itemView.findViewById(R.id.store_like);
            mLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Left, top, right, bottom drawables.
                    Drawable[] compoundDrawables = mLike.getCompoundDrawables();
                    // This is the left drawable.
                    Drawable startCompoundDrawable = compoundDrawables[0];
                    startCompoundDrawable.mutate();
                    DrawableCompat.setTint(startCompoundDrawable, ContextCompat.getColor(mContext, R.color.colorAccent));

                    Toast.makeText(mContext, "You have liked " + storeName.getText().toString() + " Mall",
                            Toast.LENGTH_SHORT).show();

                }
            });

            mComment = itemView.findViewById(R.id.store_comment);
            mComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable [] compoundDrawable = mComment.getCompoundDrawables();
                    Drawable startCompoundDrawable = compoundDrawable[0];
                    startCompoundDrawable.mutate();
                    DrawableCompat.setTint(startCompoundDrawable, ContextCompat.getColor(mContext, R.color.colorAccent));

                    Intent commentIntent = new Intent(Intent.ACTION_SEND);
                    String subject = "Write comment on " + storeName.getText().toString() + " Mall";
                    commentIntent.setType("text/plain");
                    commentIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    commentIntent.putExtra(Intent.EXTRA_TEXT, "Write comment here ...");
                    mContext.startActivity(Intent.createChooser(commentIntent, "Share Via"));
                }
            });

            final TextView share = itemView.findViewById(R.id.store_share);
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable [] compoundDrawables = share.getCompoundDrawables();
                    Drawable startCompoundDrawable = compoundDrawables[0];
                    startCompoundDrawable.mutate();
                    DrawableCompat.setTint(startCompoundDrawable, ContextCompat.getColor(mContext, R.color.colorAccent));

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    String subject = storeName.getText().toString();
                    shareIntent.setType("image/*");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, storeDescription.getText().toString());
                    mContext.startActivity(Intent.createChooser(shareIntent, "Share Via"));
                }
            });

        }
    }
}
