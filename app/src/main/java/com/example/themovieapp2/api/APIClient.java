package com.example.themovieapp2.api;

import static com.example.themovieapp2.utility.Utils.API_KEY;
import static com.example.themovieapp2.utility.Utils.BASE_URL;

import com.example.themovieapp2.model.MoviesResponse;

import io.reactivex.rxjava3.core.Single;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class APIClient {
    public static APIInterface apiInterface;

    // retrofit instance
    public static APIInterface getApiInterface(){
        if (apiInterface == null){
            OkHttpClient.Builder client = new OkHttpClient().newBuilder();

            // them interceptor vao client de chan va sua doi cac request http
            client.addInterceptor(chain -> {
                Request original = chain.request(); // lay request ban dau
                HttpUrl originalHttpUrl = original.url(); // lay url ban dau
                HttpUrl newHttpUrl = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", API_KEY).build(); // tao 1 url moi tu url ban dau bang cach them tham so truy van api_key
                Request.Builder requestBuilder = original.newBuilder().url(newHttpUrl); // lay request moi dc tao tu url moi
                Request request = requestBuilder.build(); // tao 1 request moi voi url moi

                return chain.proceed(request);
            });
            // tao 1 the hien cua retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // url goc cho tat cac request
                    .addConverterFactory(GsonConverterFactory.create()) // chuyen doi data json sang doi tuong java
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // retrofit su dung RxJava de xu ly bat dong bo
                    .build();

            // tao 1 instance cua APIInterface, retrofit se su dung thong tin tu APIInterface de thuc hien request http
            apiInterface = retrofit.create(APIInterface.class);
        }
        return apiInterface;
    }

    // end point cua url
    public interface APIInterface{
        @GET("movie/popular")
        Single<MoviesResponse> getMoviesByPage(@Query("page") Integer page);
    }
}
