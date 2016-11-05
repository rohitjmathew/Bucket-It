package com.example.rohitmathew.bucket_it;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BucketList extends AppCompatActivity {
    CustomRecyclerAdapter adapter;
    RecyclerView recyclerView;
    CardView card;
    @Override
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
                Intent add = new Intent(BucketList.this,BucketMain.class);
                startActivity(add);
            }
        });
    }



    private void setupRecyclerView() {

        adapter = new CustomRecyclerAdapter(DataModel.getInstance().courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.CustomViewHolder> implements View.OnClickListener {

        ArrayList<Course> courseList;
        ArrayList<Integer> colours = new ArrayList<>();

        public CustomRecyclerAdapter(ArrayList<Course> courses) {
            courseList = courses;
            colours.add(Color.RED);
            colours.add(Color.BLUE);
            colours.add(Color.YELLOW);
            colours.add(Color.GREEN);
            colours.add(Color.GRAY);
            colours.add(Color.DKGRAY);
            colours.add(Color.LTGRAY);
            Log.e("Size", ""+courseList.size());
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View itemView = inflater.inflate(R.layout.listitem_att, parent, false);
            itemView.setOnClickListener(this);
            return new CustomViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            Course course = courseList.get(position);
            double att = 100;
            String percentageString = "";
            if(!course.percentage.contains("-")) {
                att = Double.parseDouble(course.percentage);
                percentageString = percentageString + att + "%";
            } else percentageString = "--";
            if (att > 80) {
                holder.cardView.setCardBackgroundColor(Color.GREEN);
            } else if(att >= 75 && att <= 80 ) {
                holder.cardView.setCardBackgroundColor(Color.YELLOW);
            } else {
                holder.cardView.setCardBackgroundColor(Color.RED);
            }
            if(course.slot.equalsIgnoreCase("lab"))
            {
                holder.titleTV.setText(course.title+" Lab");
            }
            else
            {
                holder.titleTV.setText(course.title);
            }
            holder.codeTV.setText(course.code);
            holder.percentageTV.setText(percentageString);
            // holder.titleTV.setText(course.title);
        }

        @Override
        public int getItemCount() {
            return courseList.size();
        }

        @Override
        public void onClick(View v) {

        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {

            TextView codeTV, percentageTV, titleTV;
            CardView cardView;
            View colourView;
            FrameLayout ptage;
            public CustomViewHolder(View itemView) {
                super(itemView);

                cardView = (CardView) itemView.findViewById(R.id.card_view);
                codeTV = (TextView) itemView.findViewById(R.id.codeTV);
                percentageTV = (TextView) itemView.findViewById(R.id.percentageTV);
                titleTV = (TextView) itemView.findViewById(R.id.titleTV);
                //colourView = itemView.findViewById(R.id.colourView);
                //ptage = (FrameLayout) itemView.findViewById(R.id.ptage);
            }
        }
    }
}
