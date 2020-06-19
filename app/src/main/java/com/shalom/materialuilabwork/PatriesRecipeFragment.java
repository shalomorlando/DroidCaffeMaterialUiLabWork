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
public class PatriesRecipeFragment extends Fragment {

    public RecyclerView mRecyclerView;
    private ArrayList<Recipe> mPastriesData;
    private RecipeAdapter mRecipeAdapter;

    public PatriesRecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_patries_recipe, container, false);
        mRecyclerView = rootView.findViewById(R.id.recycler_pastries);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPastriesData = new ArrayList<>();
        mRecipeAdapter = new RecipeAdapter(mPastriesData, getContext());
        mRecyclerView.setAdapter(mRecipeAdapter);

        initializePastriesData();

        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {

                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(mPastriesData, from, to);
                mRecipeAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mPastriesData.remove(viewHolder.getAdapterPosition());
                mRecipeAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        touchHelper.attachToRecyclerView(mRecyclerView);
        return rootView;
    }

    private void initializePastriesData() {
        String [] pastriesTitle = getResources().getStringArray(R.array.pastries_title);
        String [] pastriesDescription = getResources().getStringArray(R.array.pastries_description);
        TypedArray pastriesImages = getResources().obtainTypedArray(R.array.pastries_images);

        mPastriesData.clear();

        for (int i = 0; i < pastriesTitle.length; i++){
            Recipe pastryRecipe = new Recipe(pastriesImages.getResourceId(i, 0), pastriesTitle [i],
                    pastriesDescription [i]);
            mPastriesData.add(pastryRecipe);
        }

        pastriesImages.recycle();
        mRecipeAdapter.notifyDataSetChanged();
    }
}
