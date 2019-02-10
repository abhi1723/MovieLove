package com.recstudentportal.www.movielove;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL="http://api.themoviedb.org/3/movie/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;
    private RetrofitClient() {
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient getInstance() {
        if(mInstance==null) {
            mInstance=new RetrofitClient();
        }
        return mInstance;
    }
    public API getApi() {
        return retrofit.create(API.class);
    }
}
