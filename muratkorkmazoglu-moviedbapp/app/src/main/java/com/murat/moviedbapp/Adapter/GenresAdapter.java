package com.murat.moviedbapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.murat.moviedbapp.Models.MovieModel;
import com.murat.moviedbapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {

    @BindView(R.id.genres)
    TextView genresText;
    Context context;
    ArrayList<MovieModel.Genre> modelList;


    public GenresAdapter(Context context, ArrayList<MovieModel.Genre> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public GenresAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.genres_item, parent, false);
        ButterKnife.bind(this, v);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GenresAdapter.ViewHolder holder, int position) {
       genresText.setText(modelList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
