package com.holmeslei.movienews.mvp.model;

import com.holmeslei.movienews.entity.http.MovieServiceImpl;
import com.holmeslei.movienews.entity.bean.ShowingMovies;
import com.holmeslei.movienews.entity.listener.GetShowingMoviesListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * author         xulei
 * Date           2017/8/11
 */

public class GeneralFragModel {
    /**
     * 请求获取正在上映、即将上映、TOP250电影
     */
    public Disposable requestShowingMovies(String movieParam, String city, int start, int count, final GetShowingMoviesListener getShowingMoviesListener) {
        return MovieServiceImpl.getInstance().getMoviesNewsByParam(movieParam, city, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShowingMovies>() {
                    @Override
                    public void accept(ShowingMovies showingMovies) throws Exception {
                        getShowingMoviesListener.onSuccess(showingMovies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getShowingMoviesListener.onError(throwable.getMessage());
                    }
                });
    }
}
