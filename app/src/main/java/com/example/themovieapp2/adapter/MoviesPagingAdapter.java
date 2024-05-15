package com.example.themovieapp2.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.themovieapp2.databinding.SingleItemMovieBinding;
import com.example.themovieapp2.model.Movie;

public class MoviesPagingAdapter extends PagingDataAdapter<Movie, MoviesPagingAdapter.MovieViewHolder> {
    public final static int LOAD_ITEM = 0;
    public final static int MOVIE_ITEM = 1;
    RequestManager glide;

    public MoviesPagingAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback, RequestManager glide) {
        super(diffCallback);
        this.glide = glide;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(SingleItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie currentMovie = getItem(position);

        if (currentMovie != null){
            glide.load("https://image.tmdb.org/t/p/w500" + currentMovie.getPosterPath()).into(holder.movieItemBinding.movieItemImg);
            holder.movieItemBinding.movieItemRating.setText(String.valueOf(currentMovie.getVoteAverage()));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount()){
            return MOVIE_ITEM;
        }
        return LOAD_ITEM;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        SingleItemMovieBinding movieItemBinding;
        public MovieViewHolder(@NonNull SingleItemMovieBinding movieItemBinding) {
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;
        }
    }
}
