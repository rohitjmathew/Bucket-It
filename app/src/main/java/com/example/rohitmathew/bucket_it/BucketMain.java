package com.example.rohitmathew.bucket_it;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class BucketMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make new checkbox layout thing
            }
        });

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
        final TextView textView = (TextView) findViewById(R.id.Id);
        if(checkBox.isChecked()) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
            });
        }
        else{
            textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
        final TextView textView2 = (TextView) findViewById(R.id.user_profile_name);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setCursorVisible(true);
                textView2.setFocusableInTouchMode(true);
                textView2.setInputType(InputType.TYPE_CLASS_TEXT);
                textView2.requestFocus();
            }
        });
    }

}
