package com.example.rohitmathew.bucket_it.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eesh on 05/11/16.
 */

public class Bucket {

    public String bucketId;
    public String bucketName;
    public String uid;
    public List<BucketItem> bucketItemList;
    public String created_at;
    public String last_updated;

    Bucket() { }

    public Bucket(JSONObject object) {

        JSONArray itemsJSONArray;
        try {
            bucketId = object.getString("bucketId");
            bucketName = object.getString("bucketName");
            uid = object.getString("uid");
            //created_at = object.getString("created_at");
            //last_updated = object.getString("last_updated");
            bucketItemList = new ArrayList<>();
            itemsJSONArray = object.getJSONArray("items");
            for(int idx = 0; idx < itemsJSONArray.length(); idx++) {
                try {
                    bucketItemList.add(new BucketItem(itemsJSONArray.getJSONObject(idx)));
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
