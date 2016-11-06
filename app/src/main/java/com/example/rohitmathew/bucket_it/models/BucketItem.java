package com.example.rohitmathew.bucket_it.models;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmObject;

public class BucketItem extends RealmObject {
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

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
