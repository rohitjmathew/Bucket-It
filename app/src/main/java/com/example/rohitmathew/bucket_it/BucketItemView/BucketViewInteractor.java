package com.example.rohitmathew.bucket_it.BucketItemView;

import com.example.rohitmathew.bucket_it.models.BucketItem;

import io.realm.RealmList;

/**
 * Created by Eesh on 06/11/16.
 */

public interface BucketViewInteractor {

    void fetchItems(String bucketId);

    public interface OnViewFetchedListener {

        void onFetchSuccess(RealmList<BucketItem> items);

        void onFetchFail();
    }
}
