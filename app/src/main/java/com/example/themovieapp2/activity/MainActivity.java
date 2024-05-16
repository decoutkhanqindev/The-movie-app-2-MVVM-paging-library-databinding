package com.example.themovieapp2.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.RequestManager;
import com.example.themovieapp2.R;
import com.example.themovieapp2.adapter.MovieLoadStateAdapter;
import com.example.themovieapp2.adapter.MoviesPagingAdapter;
import com.example.themovieapp2.databinding.ActivityMainBinding;
import com.example.themovieapp2.utility.GridSpace;
import com.example.themovieapp2.utility.MovieComparator;
import com.example.themovieapp2.viewmodel.MovieViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint // danh dau main activity la diem nhap cho Hilt them cac phu thuoc cua no
public class MainActivity extends AppCompatActivity {
    MovieViewModel movieViewModel; // la 1 viewmodel chua list movies
    ActivityMainBinding mainBinding; // la 1 bien dc tao ra boi Databinding dc su dung de truy cap cac thanh phan trong main layout
    MoviesPagingAdapter moviesPagingAdapter; // la 1 adapter su dung de hien thi list movies va trang thai load list movies
    @Inject
    RequestManager requestManager; // su dung de quan ly yeu cau img

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater()); // main databinding
        setContentView(mainBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // tao 1 doi tuong moviesPagingAdapter voi dau vao la so sanh 2 movie, img
        moviesPagingAdapter = new MoviesPagingAdapter(new MovieComparator(), requestManager);

        // tao 1 doi tuong movieViewModel
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        // khoi tao recyclerView va Adapter
        initRecyclerviewAndAdapter();

        // dang ky 1 observer de nhan paging data tu movieViewModel va cap nhat du lieu cho moviePagingAdapter
        movieViewModel.moviePagingDataFlowable.subscribe(moviePagingData -> {
            moviesPagingAdapter.submitData(getLifecycle(), moviePagingData);
        });
    }

    private void initRecyclerviewAndAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2); // tao 1 layout luoi voi 2 cot
        mainBinding.recyclerview.setLayoutManager(gridLayoutManager); // dat grid layout lam layout cho recyclerview
        mainBinding.recyclerview.addItemDecoration(new GridSpace(2, 50, true)); // config khoang cach trong grid layout
        // dat adapter cho recyclerview va them 1 load state footer
        mainBinding.recyclerview.setAdapter(moviesPagingAdapter.withLoadStateFooter(
                new MovieLoadStateAdapter(view -> {
                    moviesPagingAdapter.retry();
                })
        ));

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // quy dinh so cot cua moi item trong recyclerview
            @Override
            public int getSpanSize(int position) {
                return moviesPagingAdapter.getItemViewType(position) == MoviesPagingAdapter.LOAD_ITEM ? 1 : 2; // doi voi muc loading thi se co 1 item con lai se la 2
            }
        });
    }
}