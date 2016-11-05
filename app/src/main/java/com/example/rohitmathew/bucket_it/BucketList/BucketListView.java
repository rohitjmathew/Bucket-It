package com.example.rohitmathew.bucket_it.BucketList;

import com.example.rohitmathew.bucket_it.models.Bucket;

import java.util.List;

/**
 * Created by Eesh on 05/11/16.
 */

public interface BucketListView {

    void showNetworkError();

    void showList(List<Bucket> buckets);
}
