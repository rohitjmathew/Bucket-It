package com.example.rohitmathew.bucket_it.BucketList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rohitmathew.bucket_it.BucketMain;
import com.example.rohitmathew.bucket_it.R;
import com.example.rohitmathew.bucket_it.models.Bucket;

import java.util.List;

/**
 * Created by Eesh on 05/11/16.
 */

public class BucketListActivity extends AppCompatActivity implements BucketListView {

    List<Bucket> buckets = null;
    CustomRecyclerAdapter adapter = null;
    RecyclerView recyclerView = null;
    BucketListPresenter presenter = null;

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupRecyclerView();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(BucketListActivity.this,BucketMain.class);
                startActivity(add);
            }
        });

        presenter = new BucketListPresenterImpl(this, getApplicationContext());
    }


    private void setupRecyclerView() {

        adapter = new CustomRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(this, "No network connectivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showList(List<Bucket> buckets) {
        this.buckets = buckets;
    }

    class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.CustomViewHolder> {


        public CustomRecyclerAdapter() {

        }

        @Override
        public CustomRecyclerAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View itemView = inflater.inflate(R.layout.content_bucket_list, parent, false);
            return new CustomRecyclerAdapter.CustomViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(CustomRecyclerAdapter.CustomViewHolder holder, int position) {
            Bucket bucket = buckets.get(position);
            holder.titleTV.setText(bucket.bucketName);
            // holder.titleTV.setText(course.title);
        }

        @Override
        public int getItemCount() {
            return buckets.size();
        }


        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView titleTV;
            CustomViewHolder(View itemView) {

                super(itemView);
                titleTV = (TextView) itemView.findViewById(R.id.title_view);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                Toast.makeText(BucketListActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
