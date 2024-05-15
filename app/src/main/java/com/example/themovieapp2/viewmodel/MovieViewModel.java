package com.example.themovieapp2.viewmodel;

import androidx.lifecycle.CoroutineLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.themovieapp2.model.Movie;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MovieViewModel extends ViewModel {
    // flowable la 1 dang dong` du lieu phan ung tu RxJava de quan ly du lieu paging cua doi tuong Movie
    public Flowable<PagingData<Movie>> moviePagingDataFlowable;

    public MovieViewModel() {
        init();
    }

    private void init() {
        // dinh nghia paging source
        MoviePagingSource moviePagingSource = new MovieViewModel(); // lay  du lieu trang tu data source

        // cau hinh pager
        // kich thuoc moi trang, kich thuoc tai ban dau, khong su dung placeholder, khoang cach tai trc, kich thuoc toi da cua bo nho cache
        Pager<Integer, Movie> pager = new Pager<Integer, Movie>(
                new PagingConfig(20, 20, false, 20, 20 * 499),
                () -> moviePagingSource);

        // flowable
        // tao 1 flowable tu pager cho phep du lieu truyen theo dong de xu ly phan ung
        moviePagingDataFlowable = PagingRx.getFlowable(pager);
        // lay coroutine tu viewmodel de quan ly vong doi cua coroutine
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(moviePagingDataFlowable, coroutineScope); // luu tru du lieu paging trong pham vi cua viewmodel dam bao du lieu khong bi reset moi lan thay doi cau hnh
    }
}
