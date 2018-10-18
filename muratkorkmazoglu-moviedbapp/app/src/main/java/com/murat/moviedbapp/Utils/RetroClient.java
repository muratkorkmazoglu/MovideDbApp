package com.murat.moviedbapp.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by muratkorkmazoglu on 28.12.2017.
 */

public class RetroClient {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}