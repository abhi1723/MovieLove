package com.recstudentportal.www.movielove;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

public class ItemViewModel extends ViewModel {
     LiveData<PagedList<Results>> resultsPagedList;
    private LiveData<PageKeyedDataSource<Integer,Results>> liveDataSource;
    ItemViewModel(){
        ItemDataSourceFactory itemDataSourceFactory =new ItemDataSourceFactory();
       liveDataSource= itemDataSourceFactory.getLiveDataSource();
       PagedList.Config config=(new PagedList.Config.Builder())
               .setEnablePlaceholders(false)
               .setPageSize(10)
               .build();
       resultsPagedList=(new LivePagedListBuilder(itemDataSourceFactory,config)).build();
    }
}
