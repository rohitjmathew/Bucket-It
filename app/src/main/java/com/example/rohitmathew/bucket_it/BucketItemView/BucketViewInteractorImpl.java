package com.example.rohitmathew.bucket_it.BucketItemView;

import android.content.Context;

import com.example.rohitmathew.bucket_it.Network.NetworkAPI;

/**
 * Created by Eesh on 06/11/16.
 */

public class BucketViewInteractorImpl implements BucketViewInteractor {
    NetworkAPI networkAPI = null;

    BucketViewInteractorImpl(Context context) {

        networkAPI = NetworkAPI.getInstance(context);
    }

    @Override
    public void fetchItems(String bucketId) {

    }
}
