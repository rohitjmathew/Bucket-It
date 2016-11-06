package com.example.rohitmathew.bucket_it.BucketList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
        final ImageView img = (ImageView) findViewById(R.id.img_thumbnail);
        Glide.with(this).load("https://www.instagram.com/p/BINggopjIgUOxwiHkGfNKUitshNHbef0lcBaPE0/").fitCenter().into(img);
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

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
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
        adapter.notifyDataSetChanged();
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
            Glide.with(BucketListActivity.this).load(R.drawable.a).into(holder.imageView);
            // holder.titleTV.setText(course.title);
        }

        @Override
        public int getItemCount() {
            return (buckets == null)?0:buckets.size();
        }


        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView titleTV;
            ImageView imageView;

            CustomViewHolder(View itemView) {

                super(itemView);
                titleTV = (TextView) itemView.findViewById(R.id.tv_movie);
                imageView = (ImageView) itemView.findViewById(R.id.img_thumbnail);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                Toast.makeText(BucketListActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BucketListActivity.this, BucketMain.class));
            }
        }
    }
}
