package com.example.rohitmathew.bucket_it.BucketItemView;

import com.example.rohitmathew.bucket_it.models.Bucket;

import java.util.List;

/**
 * Created by Eesh on 06/11/16.
 */

public interface BucketViewInteractor {



    public interface OnViewFetchedListener {

        void onFetchSuccess(List<Bucket> buckets);

        void onFetchFail();
    }
}
