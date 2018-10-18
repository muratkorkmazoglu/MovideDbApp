package com.murat.moviedbapp.Utils;

import android.content.Context;
import android.util.Log;

import com.murat.moviedbapp.Interface.ApiListener;
import com.murat.moviedbapp.Models.DiscoverModel;
import com.murat.moviedbapp.Models.MovieModel;
import com.murat.moviedbapp.R;


import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiResponse {
    private static String TAG = ApiResponse.class.getSimpleName();

    public static <T> void callRetrofit(Call<T> call, final ApiName strApiName, final Context context, final ApiListener apiListener) {

        final SweetAlertDialog loadingProgress = DialogUtil.showProgressDialog(context, "Loading...", R.color.colorAccent);

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {

                    if (loadingProgress != null && loadingProgress.isShowing())
                        loadingProgress.dismiss();

                    switch (strApiName) {
                        case getDiscoverCall:
                            DiscoverModel discoverModel = (DiscoverModel) response.body(); // use the user object for the other fields
                            apiListener.success(strApiName, discoverModel);
                            break;

                        case getMovieCall:
                            MovieModel movieModel = (MovieModel) response.body(); // use the user object for the other fields
                            apiListener.success(strApiName, movieModel);
                            break;

                    }

                } else {
                    if (loadingProgress != null && loadingProgress.isShowing())
                        loadingProgress.dismiss();
                   Log.e("UNSCUCCES","UNSCUCESS");


                }


            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {

                apiListener.failure(strApiName, t.toString());
                Log.e("FAİLURE","FAİLURE");

                if (loadingProgress != null && loadingProgress.isShowing())
                    loadingProgress.dismiss();
            }
        });
    }
}