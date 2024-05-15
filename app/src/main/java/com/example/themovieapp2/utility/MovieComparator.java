package com.example.themovieapp2.utility;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.themovieapp2.model.Movie;

import java.util.Objects;

public class MovieComparator extends DiffUtil.ItemCallback<Movie> {
    @Override
    public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }
}
