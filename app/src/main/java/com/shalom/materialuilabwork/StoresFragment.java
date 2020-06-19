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
public class StoresFragment extends Fragment {

    private View mRootView;
    private RecyclerView mRecyclerView;
    private ArrayList<Store> mStoreData;
    private StoreAdapter mStoreAdapter;

    public StoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_stores, container, false);

        mRecyclerView = mRootView.findViewById(R.id.recycler_stores);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mStoreData = new ArrayList<>();
        mStoreAdapter = new StoreAdapter(getContext(), mStoreData);
        mRecyclerView.setAdapter(mStoreAdapter);

        initializeDisplayData();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT|ItemTouchHelper.UP|ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT
        ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(mStoreData,from, to);
                mStoreAdapter.notifyItemMoved(from, to);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mStoreData.remove(viewHolder.getAdapterPosition());
                mStoreAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        return mRootView;
    }

    private void initializeDisplayData() {
        String [] storeNames = getResources().getStringArray(R.array.store_names);
        String [] storeDescriptions = getResources().getStringArray(R.array.store_descriptions);
        TypedArray storeImages = getResources().obtainTypedArray(R.array.store_images);

        mStoreData.clear();

        for (int i = 0; i < storeDescriptions.length; i++){
            Store store = new Store(storeImages.getResourceId(i, 0), storeNames [i],
                    storeDescriptions [i]);
            mStoreData.add(store);
        }
        storeImages.recycle();
        mStoreAdapter.notifyDataSetChanged();
    }
}
