package com.example.themovieapp2.utility;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.themovieapp2.model.Movie;

import java.util.Objects;

// so sanh cac doi tuong movie de quan ly list movies trong adapter
public class MovieComparator extends DiffUtil.ItemCallback<Movie> {
    @Override
    // kiem tra xem 2 doi tuong movies co cung ID hay khong, neu co thi chung la 1 phan tu
    public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    @Override
    // kiem tra xem noi dung cua 2 doi tuong movies co giong nhau hay khong, neu giong thi chung co chung id
    public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
        return Objects.equals(oldItem.getId(), newItem.getId());
    }
}
