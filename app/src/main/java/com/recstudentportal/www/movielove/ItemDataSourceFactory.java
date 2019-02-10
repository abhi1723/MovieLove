package com.recstudentportal.www.movielove;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer,Results>> liveDataSource=new MutableLiveData<>();
    @Override
    public DataSource create() {
        com.recstudentportal.www.movielove.DataSource dataSource=new com.recstudentportal.www.movielove.DataSource();
        liveDataSource.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Results>> getLiveDataSource() {
        return liveDataSource;
    }
}
