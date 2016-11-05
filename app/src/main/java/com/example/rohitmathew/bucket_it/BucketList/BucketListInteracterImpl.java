package com.example.rohitmathew.bucket_it.BucketList;

import android.content.Context;

import com.example.rohitmathew.bucket_it.Network.NetworkAPI;

/**
 * Created by Eesh on 05/11/16.
 */

public class BucketListInteracterImpl implements BucketListInteracter {

    NetworkAPI networkAPI = null;

    BucketListInteracterImpl(Context context) {

        networkAPI = NetworkAPI.getInstance(context);
    }

    @Override
    public void fetchFromNetwork(OnListFetchedListener listener) {

        networkAPI.getBuckets(listener);
    }

    @Override
    public void fetchFromModel(OnListFetchedListener listener) {

    }
}
