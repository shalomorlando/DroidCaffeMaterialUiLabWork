package com.shalom.materialuilabwork;

public class Store {
    private final int mStoreImage;
    private String mStoreName;
    private String mStoreDescription;

    public Store(int storeImage, String storeName, String storeDescription) {
        mStoreImage = storeImage;
        mStoreName = storeName;
        mStoreDescription = storeDescription;
    }

    public int getStoreImage() {
        return mStoreImage;
    }
    public String getStoreName() {
        return mStoreName;
    }
    public String getStoreDescription() {
        return mStoreDescription;
    }
}
