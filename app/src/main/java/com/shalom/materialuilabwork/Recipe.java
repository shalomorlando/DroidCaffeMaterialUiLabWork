package com.shalom.materialuilabwork;

public class Recipe {

    private final int mRecipeImage;
    private String mRecipeTitle;
    private String mRecipeDescription;

    public Recipe(int recipeImage, String recipeTitle, String recipeDescription) {
        mRecipeImage = recipeImage;
        mRecipeTitle = recipeTitle;
        mRecipeDescription = recipeDescription;
    }

    public int getRecipeImage() {
        return mRecipeImage;
    }
    public String getRecipeTitle() {
        return mRecipeTitle;
    }
    public String getRecipeDescription() {
        return mRecipeDescription;
    }

}
