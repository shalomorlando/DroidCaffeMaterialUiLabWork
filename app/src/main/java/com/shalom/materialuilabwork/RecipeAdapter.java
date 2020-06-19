package com.shalom.materialuilabwork;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


//create the RecipeAdapter by extending the RecyclerView.Adapter super class that uses the RecyclerView.ViewHolder
//class
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mLayoutInflater;
    private ArrayList<Recipe> mRecipeArrayList;

    public RecipeAdapter(ArrayList<Recipe> recipeArrayList, Context context) {
        mContext = context;
        mRecipeArrayList = recipeArrayList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = mLayoutInflater.inflate(R.layout.recipe_list_item, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Recipe currentRecipe = mRecipeArrayList.get(position);
        Glide.with(mContext).load(currentRecipe.getRecipeImage()).into(holder.mRecipeImage);
        holder.mRecipeTitle.setText(currentRecipe.getRecipeTitle());
        holder.mRecipeDescription.setText(currentRecipe.getRecipeDescription());

    }

    @Override
    public int getItemCount() {
        return mRecipeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mRecipeImage;
        public TextView mRecipeTitle;
        public TextView mRecipeDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mRecipeImage = (ImageView) itemView.findViewById(R.id.recipe_image);
            mRecipeTitle = (TextView) itemView.findViewById(R.id.recipe_title);
            mRecipeDescription = (TextView) itemView.findViewById(R.id.recipe_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int dessertPosition = getAdapterPosition();
                    Recipe currentRecipe = mRecipeArrayList.get(dessertPosition);
                    Intent intent = new Intent(mContext, DessertActivity.class);

                    intent.putExtra("dessertTitle", currentRecipe.getRecipeTitle());
                    intent.putExtra("dessertImage", currentRecipe.getRecipeImage());
                    intent.putExtra("dessertDescription", currentRecipe.getRecipeDescription());

                    mContext.startActivity(intent);
                }
            });
        }
    }
}
