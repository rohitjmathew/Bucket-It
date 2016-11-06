package com.example.rohitmathew.bucket_it.BucketList;

import android.content.Context;

import com.example.rohitmathew.bucket_it.models.Bucket;

import io.realm.RealmResults;

/**
 * Created by Eesh on 05/11/16.
 */

public class BucketListPresenterImpl implements BucketListPresenter, BucketListInteracter.OnListFetchedListener {

    BucketListView view = null;
    BucketListInteracter interacter = null;

    BucketListPresenterImpl(BucketListView view, Context applicationContext) {
        this.view = view;
        interacter = new BucketListInteracterImpl(applicationContext);
    }

    @Override
    public void onFetchSuccess(RealmResults<Bucket> buckets) {
        view.showList(buckets);
    }

    @Override
    public void onFetchFail() {
        view.showNetworkError();
    }

    @Override
    public void onResume() {
        interacter.fetchFromNetwork(this);
    }
}
