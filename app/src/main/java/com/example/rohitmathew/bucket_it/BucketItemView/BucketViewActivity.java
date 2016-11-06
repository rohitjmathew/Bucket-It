package com.example.rohitmathew.bucket_it.BucketItemView;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.rohitmathew.bucket_it.BucketList.BucketListActivity;
import com.example.rohitmathew.bucket_it.BucketList.BucketListPresenterImpl;
import com.example.rohitmathew.bucket_it.BucketMain;
import com.example.rohitmathew.bucket_it.R;

/**
 * Created by Eesh on 06/11/16.
 */

public class BucketViewActivity extends AppCompatActivity implements BucketView {

    BucketViewPresenter presenter = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_list);
        setupRecyclerView();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(BucketViewActivity.this,BucketMain.class);
                startActivity(add);
            }
        });
        presenter = new BucketViewPresenterImpl(this, getApplicationContext());
    }

    private void setupRecyclerView() {
    }


    @Override
    public void showError() {

    }
}
