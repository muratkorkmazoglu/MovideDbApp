package com.murat.moviedbapp.Interface;

import com.murat.moviedbapp.Utils.ApiName;

public interface ApiListener {
    void success(ApiName strApiName, Object response);

    void error(ApiName strApiName, String error);

    void failure(ApiName strApiName, String message);
}