package com.example.rohitmathew.bucket_it.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Eesh on 05/11/16.
 */

public class Bucket extends RealmObject {

    @PrimaryKey
    public String bucketId;

    public String bucketName;

    public String uid;

    public RealmList<BucketItem> getBucketItemList() {
        return bucketItemList;
    }

    public void setBucketItemList(RealmList<BucketItem> bucketItemList) {
        this.bucketItemList = bucketItemList;
    }

    public String getBucketId() {
        return bucketId;
    }

    public void setBucketId(String bucketId) {
        this.bucketId = bucketId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public RealmList<BucketItem> bucketItemList;

    public String createdAt;

    public String last_updated;

    public Bucket() { }

    public Bucket(JSONObject object) {

        JSONArray itemsJSONArray;
        try {
            bucketId = object.getString("bucketId");
            bucketName = object.getString("bucketName");
            uid = object.getString("uid");
            //createdAt = object.getString("createdAt");
            //last_updated = object.getString("last_updated");
            bucketItemList = new RealmList<>();
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
