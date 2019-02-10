package com.recstudentportal.www.movielove;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieListAdapter extends PagedListAdapter<Results,MovieListAdapter.ItemViewHolder> {

    Context context;
    protected MovieListAdapter(Context c) {
        super(DIFF_CALL_BACK);
        context=c;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context= viewGroup.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        boolean shouldAttachToParent=false;
        View view=inflater.inflate(R.layout.list_item,viewGroup,shouldAttachToParent);
        ItemViewHolder viewHolder=new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        Results results=getItem(i);
        if(results!=null){
            Glide.with(context).load("http://image.tmdb.org/t/p/w780"+results.getPoster_path()).into(itemViewHolder.title_image);
            itemViewHolder.title_movie.setText(results.getOriginal_title());
        }
    }
     public static DiffUtil.ItemCallback<Results> DIFF_CALL_BACK=new DiffUtil.ItemCallback<Results>() {
         @Override
         public boolean areItemsTheSame(@NonNull Results results, @NonNull Results t1) {
             return results.getId()==t1.getId();
         }

         @Override
         public boolean areContentsTheSame(@NonNull Results results, @NonNull Results t1) {
             return results==t1;
         }
     };
    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView title_image;
        TextView title_movie;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title_image=(ImageView)itemView.findViewById(R.id.title_image);
            title_movie=(TextView)itemView.findViewById(R.id.title);
        }
    }
}
