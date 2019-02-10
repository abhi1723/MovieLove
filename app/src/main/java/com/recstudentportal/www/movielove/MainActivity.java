package com.recstudentportal.www.movielove;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        ItemViewModel itemViewModel= ViewModelProviders.of(this).get(ItemViewModel.class);
        final MovieListAdapter movieListAdapter=new MovieListAdapter(this);
          itemViewModel.resultsPagedList.observe(this, new Observer<PagedList<Results>>() {
              @Override
              public void onChanged(@Nullable PagedList<Results> results) {
                  movieListAdapter.submitList(results);
              }
          });
          recyclerView.setAdapter(movieListAdapter);
    }
}