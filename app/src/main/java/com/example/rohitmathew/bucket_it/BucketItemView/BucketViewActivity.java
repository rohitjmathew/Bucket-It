package com.example.rohitmathew.bucket_it.BucketItemView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.rohitmathew.bucket_it.R;
import com.example.rohitmathew.bucket_it.models.Bucket;
import com.example.rohitmathew.bucket_it.models.BucketItem;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class BucketViewActivity extends AppCompatActivity implements BucketView {


    BucketViewPresenter presenter = null;
    RecyclerView recyclerView = null;
    CustomRecyclerAdapter adapter = null;
    Realm realm = null;
    Bucket bucket = null;
    List<BucketItem> items = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        setupRecyclerView();

        realm = Realm.getDefaultInstance();
        Intent intent = getIntent();
        String bucketId = intent.getStringExtra("bucketId");
        RealmResults<Bucket> results = realm.where(Bucket.class).equalTo("bucketId", bucketId).findAll();
        bucket = results.first();
        Log.e("bucketName", " "+bucket.bucketName);
        /*
        Log.e("bucketitems", " "+ bucket.bucketItemList.size());
        items = bucket.bucketItemList;*/
        populate();
        adapter.notifyDataSetChanged();
        presenter = new BucketViewPresenterImpl(this, getApplicationContext());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make new checkbox layout thing
            }
        });

        TextView titleTV = (TextView) findViewById(R.id.user_profile_name);
        titleTV.setText(bucket.bucketName);
    }

    private void setupRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new CustomRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError() {

    }

    class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.CustomViewHolder> {


        public CustomRecyclerAdapter() {

        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View itemView = inflater.inflate(R.layout.content_bucket_main, parent, false);
            return new CustomRecyclerAdapter.CustomViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(CustomRecyclerAdapter.CustomViewHolder holder, int position) {
            BucketItem item = items.get(position);
            holder.contentTV.setText(item.getContent());
            holder.checkBox.setChecked(item.isChecked());
            if(!holder.checkBox.isChecked()) {
                holder.contentTV.setPaintFlags(holder.contentTV.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        }

        @Override
        public int getItemCount() {
            return (items == null)?0:items.size();
        }


        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

            TextView contentTV;
            CheckBox checkBox;

            CustomViewHolder(View itemView) {

                super(itemView);
                contentTV = (TextView) itemView.findViewById(R.id.Id);
                checkBox = (CheckBox) itemView.findViewById(R.id.checkBox1);
                itemView.setOnClickListener(this);
                itemView.setOnLongClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()) {
                    contentTV.setPaintFlags(contentTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    contentTV.setCursorVisible(true);
                    contentTV.setFocusableInTouchMode(true);
                    contentTV.setInputType(InputType.TYPE_CLASS_TEXT);
                    contentTV.requestFocus();
                    checkBox.setChecked(false);
                }
            }

            @Override
            public boolean onLongClick(View view) {
                int position = getLayoutPosition();
                Bucket bucket = buckets.get(position);

               return false;
            }
        }
    }

    void populate() {

        items = new ArrayList<>();
        for(int i = 1 ; i <= 3; i++) {

            BucketItem bucketItem = new BucketItem();
            bucketItem.setChecked(false);
            bucketItem.setContent("Test " + i);
            items.add(bucketItem);
        }
    }
}
