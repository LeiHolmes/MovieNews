package com.holmeslei.movienews.mvp.model;

import android.util.Log;

import com.holmeslei.movienews.http.MovieServiceImpl;
import com.holmeslei.movienews.mvp.model.entity.ShowingMovies;
import com.holmeslei.movienews.mvp.model.listener.GetShowingMoviesListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * author         xulei
 * Date           2017/8/8
 */

public class MainModel {
    /**
     * 请求获取正在上映电影
     */
    public void getShowingMoviesRequest(String city, final GetShowingMoviesListener getShowingMoviesListener) {
        MovieServiceImpl.getInstance().getShowingMovies(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShowingMovies>() {
                    @Override
                    public void accept(ShowingMovies showingMovies) throws Exception {
                        Log.e("accept_success", showingMovies.toString());
                        getShowingMoviesListener.onSuccess(showingMovies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("accept_Error", throwable.getMessage());
                        getShowingMoviesListener.onError(throwable.getMessage());
                    }
                });
    }
}
