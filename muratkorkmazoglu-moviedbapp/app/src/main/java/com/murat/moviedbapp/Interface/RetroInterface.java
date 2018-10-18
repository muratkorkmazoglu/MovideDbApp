package com.murat.moviedbapp.Interface;

import com.murat.moviedbapp.Models.DiscoverModel;
import com.murat.moviedbapp.Models.MovieModel;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface RetroInterface {

    @GET("discover/movie?")
    Call<DiscoverModel> getDiscover(@Query("api_key") String apikey,
                                    @Query("sort_by") String sortBy, @Query("page") Integer page);

    @GET("movie/{movie_id}?")
    Call<MovieModel> getMovie(@Path("movie_id") int movieId, @Query("api_key") String apikey
    );


}
