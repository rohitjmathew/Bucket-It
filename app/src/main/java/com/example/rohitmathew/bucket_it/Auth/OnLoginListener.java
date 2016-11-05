package com.example.rohitmathew.bucket_it.Auth;

/**
 * Created by Eesh on 06/11/16.
 */

public interface OnLoginListener {

    void onSuccess(String accessToken);

    void onFail();
}
