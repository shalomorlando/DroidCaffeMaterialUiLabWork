package com.shalom.materialuilabwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DessertActivity extends AppCompatActivity {

    private TextView mDessertTitle;
    private TextView mDessertDescription;
    private TextView mHowToPrepare;
    private ImageView mDessertImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert);

        Intent intent = getIntent();
        mDessertTitle = findViewById(R.id.dessert_title);
        mDessertDescription = findViewById(R.id.dessert_description);
        mHowToPrepare = findViewById(R.id.how_to_prepare);
        mDessertImage = findViewById(R.id.dessert_image);

        Glide.with(this).load(intent.getIntExtra("dessertImage", 0)).into(mDessertImage);
        mDessertTitle.setText(intent.getStringExtra("dessertTitle"));
        mDessertDescription.setText(intent.getStringExtra("dessertDescription"));
        String prepDetails = "How to prepare " + intent.getStringExtra("dessertTitle");
        mHowToPrepare.setText(prepDetails);
    }
}
