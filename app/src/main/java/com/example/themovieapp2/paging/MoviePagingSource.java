package com.example.themovieapp2.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.themovieapp2.api.APIClient;
import com.example.themovieapp2.model.Movie;
import com.example.themovieapp2.model.MoviesResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

// lay paging source tu api
public class MoviePagingSource extends RxPagingSource<Integer, Movie> {
    @Nullable
    @Override
    // xac dinh khoa nao nen dc su dung de tai du lieu khi co 1 thao tac lam moi xay ra
    public Integer getRefreshKey(@NonNull PagingState<Integer, Movie> pagingState) {
        return null;
    }

    @NonNull
    @Override
    //  call api de lay paging source tuong ung tra ve ket qua pager
    public Single<LoadResult<Integer, Movie>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        try {
            // lay khoa cua trang hien tai neu khoa la null thi no mac dinh la trang 1
            int page = loadParams.getKey() != null ? loadParams.getKey() : 1;

            // tra ve call api
            return APIClient.getApiInterface()
                    .getMoviesByPage(page) // lay data cua trang tuong ung
                    .subscribeOn(Schedulers.io()) // chi dinh call api thuc hien tren luong IO
                    .map(MoviesResponse::getResults) // anh xa tu response api de lay list movies
                    .map(movies -> toLoadResult(movies, page)) // anh xa list movies vao doi tuong  LoadResult
                    .onErrorReturn(LoadResult.Error::new); // xu ly loi neu xay ra trong khi call api
        } catch (Exception e){
            return Single.just(new LoadResult.Error<>(e));
        }
    }

    // chuyen list movies va current page thanh doi tuong LoadResult.Page
    private LoadResult<Integer, Movie> toLoadResult(List<Movie> movies, int page) {
        // tra ve 1 doi tuong LoadResult.Page
        return new LoadResult.Page<>(movies, page == 1 ? null : page - 1, page + 1);
    }
}