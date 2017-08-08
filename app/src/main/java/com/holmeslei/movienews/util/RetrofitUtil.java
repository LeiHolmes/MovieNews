package com.holmeslei.movienews.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description:   Retrofit工具类
 * author         xulei
 * Date           2017/8/8
 */

public class RetrofitUtil {
    private static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final int DEFAULT_TIMEOUT = 5;

    private RetrofitUtil() {
    }

    public static Retrofit getRetrofit() {
        return SingleHolder.retrofit;
    }

    private static class SingleHolder {
        private static final Retrofit retrofit = getInstance();

        private static Retrofit getInstance() {
            OkHttpClient.Builder client = new OkHttpClient.Builder().connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            return new Retrofit.Builder()
                    .client(client.build())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }
}
