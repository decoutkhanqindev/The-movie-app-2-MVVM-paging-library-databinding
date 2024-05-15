package com.example.themovieapp2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themovieapp2.R;
import com.example.themovieapp2.databinding.LoadStateItemBinding;

// adapter dung de quan ly trang thai tai data cua list movies
public class MovieLoadStateAdapter extends LoadStateAdapter<MovieLoadStateAdapter.MovieLoadStateViewHolder> {
    private final View.OnClickListener movieRetryCalled; // xu ly su kien khi ng dung nhan nut retry

    public MovieLoadStateAdapter(View.OnClickListener movieRetryCalled) {
        this.movieRetryCalled = movieRetryCalled;
    }

    // dc goi khi muon gan trang thai data vao MovieLoadStateViewHolder
    @Override
    public void onBindViewHolder(@NonNull MovieLoadStateViewHolder movieLoadStateViewHolder, @NonNull LoadState loadState) {
        movieLoadStateViewHolder.bind(loadState);
    }

    // dc goi khi muon tao 1 MovieLoadStateViewHolder moi
    @NonNull
    @Override
    public MovieLoadStateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, @NonNull LoadState loadState) {
        return new MovieLoadStateViewHolder(viewGroup, movieRetryCalled);
    }

    // hien thi trang thai tai data cua list movies
    public static class MovieLoadStateViewHolder extends RecyclerView.ViewHolder {
        private final ProgressBar movieProgressBar;
        private final TextView movieErrorMsg;
        private final Button movieBtnRetry;

        public MovieLoadStateViewHolder(@NonNull ViewGroup parent, @NonNull View.OnClickListener retryCallBack) {
            // bind data voi layout load_state_item.xml
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.load_state_item, parent, false));

            LoadStateItemBinding loadStateItemBinding = LoadStateItemBinding.bind(itemView);
            movieProgressBar = loadStateItemBinding.progressBar;
            movieErrorMsg = loadStateItemBinding.errorMsg;
            movieBtnRetry = loadStateItemBinding.btn;
            movieBtnRetry.setOnClickListener(retryCallBack);
        }

        // cap nhat giao dien user dua tren current load state
        public void bind(LoadState loadState) {
            // neu co error thi thong bao error se hien thi tren movieErrorMsg
            if (loadState instanceof LoadState.Error) {
                LoadState.Error loadStateError = (LoadState.Error) loadState;
                movieErrorMsg.setText(loadStateError.getError().getLocalizedMessage());
            }

            // neu dang tai thi movieProgressBar se hien thi nguoc lai se khong co gi ca
            movieProgressBar.setVisibility(loadState instanceof LoadState.Loading ? View.VISIBLE : View.GONE);
            movieErrorMsg.setVisibility(loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE);
            movieBtnRetry.setVisibility(loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE);
        }
    }
}
