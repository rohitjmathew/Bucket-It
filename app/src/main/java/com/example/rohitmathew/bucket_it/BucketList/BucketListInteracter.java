package com.example.rohitmathew.bucket_it.BucketList;

import com.example.rohitmathew.bucket_it.models.Bucket;

import io.realm.RealmResults;

/**
 * Created by Eesh on 05/11/16.
 */

public interface BucketListInteracter {


    void fetchFromNetwork(OnListFetchedListener listener);

    void fetchFromModel(OnListFetchedListener listener);

    void deleteBucket(String bucketId);

    public interface OnListFetchedListener {

        void onFetchSuccess(RealmResults<Bucket> buckets);

        void onFetchFail();
    }
}
