package com.example.rohitmathew.bucket_it;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    final DataModels model = DataModels.getInstance();
    StringRequest request = new StringRequest(Request.Method.GET,"filtershots.com:8080/bucket", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            JSONObject object, buckets, member;
            JSONArray members;
            try {
                object = new JSONObject(response);
                buckets = object.getJSONObject("bucket");
                model.setFamily(buckets);
                members = object.getJSONArray("members");
                for(int i=0; i < members.length(); i++) {
                    member = members.getJSONObject(i);
                    model.add(member);
                }
                appContext.sendBroadcast(new Intent("datasender.patientsReceived"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            };
            }
        });
}
