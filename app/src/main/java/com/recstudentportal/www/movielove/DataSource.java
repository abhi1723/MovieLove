package com.recstudentportal.www.movielove;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataSource extends PageKeyedDataSource<Integer,Results> {
    int PAGE_SIZE=10;
    int FIRST_PAGE=1;
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Results> callback) {
             RetrofitClient.getInstance().getApi().getMovieList("fb11cd6ac05c44b23052dc8434b6c72c",FIRST_PAGE)
                     .enqueue(new Callback<ApiResponsePOJO>() {
                         @Override
                         public void onResponse(Call<ApiResponsePOJO> call, Response<ApiResponsePOJO> response) {
                             if(response.body()!=null){
                                 callback.onResult( response.body().getResults(),null,FIRST_PAGE+1);
                             }
                         }

                         @Override
                         public void onFailure(Call<ApiResponsePOJO> call, Throwable t) {

                         }
                     });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Results> callback) {

         final int key;
             if(params.key>1){key=params.key-1;} else key=1;
              RetrofitClient.getInstance().getApi().getMovieList("fb11cd6ac05c44b23052dc8434b6c72c",key)
                      .enqueue(new Callback<ApiResponsePOJO>() {
                          @Override
                          public void onResponse(Call<ApiResponsePOJO> call, Response<ApiResponsePOJO> response) {
                              callback.onResult(response.body().getResults(),key);
                          }

                          @Override
                          public void onFailure(Call<ApiResponsePOJO> call, Throwable t) {

                          }
                      });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Results> callback) {
RetrofitClient.getInstance().getApi().getMovieList("fb11cd6ac05c44b23052dc8434b6c72c",params.key+1)
        .enqueue(new Callback<ApiResponsePOJO>() {
            @Override
            public void onResponse(Call<ApiResponsePOJO> call, Response<ApiResponsePOJO> response) {
                Integer key;
                if(response.body().getTotal_pages()==Integer.toString(params.key))key=null; else key=params.key+1;
                callback.onResult(response.body().getResults(),key);
            }

            @Override
            public void onFailure(Call<ApiResponsePOJO> call, Throwable t) {

            }
        });
    }
}
