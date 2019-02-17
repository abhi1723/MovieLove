package com.recstudentportal.www.movielove;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    Toolbar toolbar_main_without_search,toolbar_main_with_search;
    ImageView seach_icon,back_btn;
    boolean pressDoubleToExitApp=false;
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
          toolbar_main_with_search=(Toolbar)findViewById(R.id.main_tool_bar_search);
          toolbar_main_without_search=(Toolbar)findViewById(R.id.main_tool_bar);
          seach_icon=(ImageView)findViewById(R.id.seach_icon);
          seach_icon.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  toolbar_main_without_search.setVisibility(View.GONE);
                  toolbar_main_with_search.setVisibility(View.VISIBLE);

              }
          });
          back_btn=(ImageView)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar_main_with_search.setVisibility(View.GONE);
                toolbar_main_without_search.setVisibility(View.VISIBLE);
            }
        });

    }
    @Override
    public void onBackPressed() {
        if(pressDoubleToExitApp) {
            super.onBackPressed();
            return;
        }
        pressDoubleToExitApp=true;
        toolbar_main_with_search.setVisibility(View.GONE);
        toolbar_main_without_search.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pressDoubleToExitApp=false;
            }
        },2000);
    }
}