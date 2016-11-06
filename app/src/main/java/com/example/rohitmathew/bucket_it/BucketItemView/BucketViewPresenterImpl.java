package com.example.rohitmathew.bucket_it.BucketItemView;

import android.content.Context;

/**
 * Created by Eesh on 06/11/16.
 */
//
public class BucketViewPresenterImpl implements BucketViewPresenter{


    BucketView view = null;
    BucketViewInteractor interactor = null;

    BucketViewPresenterImpl(BucketView view, Context context) {
        this.view = view;
        interactor = new BucketViewInteractorImpl(context);
    }

    @Override
    public void onResume() {

    }
}
