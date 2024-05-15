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

// adapter hien thi list movies trong 1 recyclerview su dung paging3
public class MoviesPagingAdapter extends PagingDataAdapter<Movie, MoviesPagingAdapter.MovieViewHolder> {
    public final static int LOAD_ITEM = 0; // 1 process load data cho item
    public final static int MOVIE_ITEM = 1; // hien thi thong tin 1 movie
    RequestManager glide; // dung de load img

    public MoviesPagingAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback, RequestManager glide) {
        super(diffCallback);
        this.glide = glide;
    }

    // dc goi khi no can 1 view holder moi, no se inflate layout cho 1 movie item va tra ve doi tuong MovieViewHolder
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(SingleItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    // dc goi de hien thi data tai vi tri va gan data vao doi tuong MovieViewHolder
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie currentMovie = getItem(position);

        if (currentMovie != null){
            glide.load("https://image.tmdb.org/t/p/w500" + currentMovie.getPosterPath()).into(holder.movieItemBinding.movieItemImg);
            holder.movieItemBinding.movieItemRating.setText(String.valueOf(currentMovie.getVoteAverage()));
        }
    }

    // xac dinh loai view nao se dc hien thi tai 1 vi tri nhat dinh
    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount()){
            return MOVIE_ITEM;
        }
        return LOAD_ITEM;
    }

    // giu view cho 1 movie item
    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        SingleItemMovieBinding movieItemBinding; // 1 lop binding dc tao ra do databinding trong layout cua 1 movie item
        public MovieViewHolder(@NonNull SingleItemMovieBinding movieItemBinding) {
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;
        }
    }
}
