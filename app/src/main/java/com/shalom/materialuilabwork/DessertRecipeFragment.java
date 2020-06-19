package com.shalom.materialuilabwork;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class DessertRecipeFragment extends Fragment {

    public RecyclerView mRecyclerView;
    private ArrayList<Recipe> mRecipeData;
    private RecipeAdapter mRecipeAdapter;
    private View mRootView;

    public DessertRecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mRootView = inflater.inflate(R.layout.fragment_dessert_recipe, container, false);

        mRecyclerView = mRootView.findViewById(R.id.recycler_dessert);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecipeData = new ArrayList<>();
        mRecipeAdapter = new RecipeAdapter(mRecipeData, getContext());
        mRecyclerView.setAdapter(mRecipeAdapter);
        initializeData();

        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT|ItemTouchHelper.DOWN|ItemTouchHelper.UP,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {

                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(mRecipeData, from, to);
                mRecipeAdapter.notifyItemMoved(from, to);

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mRecipeData.remove(viewHolder.getAdapterPosition());
                mRecipeAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        touchHelper.attachToRecyclerView(mRecyclerView);

        return mRootView;
    }

    private void initializeData() {
        String [] dessertTitle = getResources().getStringArray(R.array.dessert_title);
        String [] dessertDescription = getResources().getStringArray(R.array.dessert_description);
        TypedArray dessertImages = getResources().obtainTypedArray(R.array.desserts_images);

        mRecipeData.clear();

        for (int i = 0; i < dessertTitle.length; i++){
            Recipe recipe = new Recipe(dessertImages.getResourceId(i, 0),
                    dessertTitle[i], dessertDescription[i]);
            mRecipeData.add(recipe);
        }

        dessertImages.recycle();
        mRecipeAdapter.notifyDataSetChanged();
    }
}
