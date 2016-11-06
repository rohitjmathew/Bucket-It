package com.example.rohitmathew.bucket_it.BucketItemView;

import android.content.Context;

import com.example.rohitmathew.bucket_it.models.BucketItem;

import io.realm.RealmList;

/**
 * Created by Eesh on 06/11/16.
 */
//
public class BucketViewPresenterImpl implements BucketViewPresenter, BucketViewInteractor.OnViewFetchedListener{


    BucketView view = null;
    BucketViewInteractor interactor = null;

    BucketViewPresenterImpl(BucketView view, Context context) {
        this.view = view;
        interactor = new BucketViewInteractorImpl(context);
    }

    @Override
    public void onResume() {
        //interactor.fetchItems("RmQJVC1PbA", this);
    }

    @Override
    public void getItems(String bucketId) {

    }


    @Override
    public void onFetchSuccess(RealmList<BucketItem> items) {

    }

    @Override
    public void onFetchFail() {

    }
}
