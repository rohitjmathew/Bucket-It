package com.example.rohitmathew.bucket_it.Network;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rohitmathew.bucket_it.Auth.OnLoginListener;
import com.example.rohitmathew.bucket_it.BucketList.BucketListInteracter;
import com.example.rohitmathew.bucket_it.models.Bucket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Eesh on 06/11/16.
 */

public class NetworkAPI {

    RequestQueue requestQueue = null;
    static NetworkAPI instance = null;
    final String baseURL = "http://www.filtershots.com:8080";
    final String buckets = "/bucket";
    final String items = "/item";
    final String login = "/googleLogin";
    String accessToken = "";

    NetworkAPI (Context context) {
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
    }

    public static NetworkAPI getInstance(Context context) {
        if(instance == null) {
            instance = new NetworkAPI(context);
        }
        SharedPreferences preferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE);
        instance.accessToken = preferences.getString("accessToken", "");
        return instance;
    }

    public void getBuckets(final BucketListInteracter.OnListFetchedListener listener) {

        final Map<String, String> headers = new HashMap<>();
        headers.put("accessToken", accessToken);

        JsonObjectRequest request = new JsonObjectRequest(baseURL + buckets, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                processBucketResponse(response, listener);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFetchFail();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };

        requestQueue.add(request);
    }

    private void processBucketResponse(JSONObject response, BucketListInteracter.OnListFetchedListener listener) {

        try {
            boolean success = response.getBoolean("success");
            if(!success) {
                listener.onFetchFail();
            } else {
                parseBuckets(response.getJSONArray("buckets"), listener);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onFetchFail();
        }
    }

    private void parseBuckets(JSONArray bucketsArray, BucketListInteracter.OnListFetchedListener listener) {

        List<Bucket> buckets = new ArrayList<>();
        try {
            for(int idx = 0; idx < bucketsArray.length(); idx++) {
                buckets.add(new Bucket(bucketsArray.getJSONObject(idx)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listener.onFetchSuccess(buckets);
    }

    public void getAccessToken(String idToken, final OnLoginListener listener) {

        final Map<String, String> params = new HashMap<>();
        params.put("tokenId", idToken);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, baseURL + login, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                processLoginresponse(response, listener);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        requestQueue.add(request);
    }

    private void processLoginresponse(JSONObject response, OnLoginListener listener) {

        try {
            boolean success = response.getBoolean("success");
            if(success) {
                String accessToken = response.getString("accessToken");
                listener.onSuccess(accessToken);
            } else listener.onFail();
        } catch (Exception e) {
            e.printStackTrace();
            listener.onFail();
        }
    }
}
