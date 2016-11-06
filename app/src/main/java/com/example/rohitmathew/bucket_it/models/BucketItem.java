package com.example.rohitmathew.bucket_it.models;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;

public class BucketItem extends RealmObject {

    String itemId;

    String content;

    boolean checked;

    public BucketItem() {
        checked = false;
        itemId = "";
        content = "";
    }

    public BucketItem(JSONObject itemObject) {
        try {
            checked = itemObject.getBoolean("checked");
            itemId = itemObject.getString("itemId");
            content = itemObject.getString("content");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
