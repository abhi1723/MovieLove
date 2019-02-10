package com.recstudentportal.www.movielove;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("popular")
    Call<ApiResponsePOJO> getMovieList(
            @Query("api_key") String api_key,
            @Query("page") int page
    );
}
