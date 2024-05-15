package com.example.themovieapp2.dependencyinjection;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.themovieapp2.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

// day la 1 module cua hilt su dung de cung cap dependency cho app
@Module
@InstallIn(SingletonComponent.class) // danh dau module se dc cai dat trong SingletonComponent
public class AppModule {
    @Provides  // danh dau 1 method de cung cap dependency
    @Singleton // danh dau se chi co 1 instance duy nhat trong suot vong doi cua app
    // cung cap 1 doi tuong RequestManager tu Glide Library
    public RequestManager getGlide(@ApplicationContext Context context){
        // tra ve 1 doi tuong RequestManger de quan ly viec tai anh tu Glide Library
        return Glide.with(context)
                // thiet lap cac options mac dinh cho all request tai img bang glide
                .applyDefaultRequestOptions(new RequestOptions().error(R.drawable.ic_image).placeholder(R.drawable.ic_image));
    }
}
