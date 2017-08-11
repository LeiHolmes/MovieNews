package com.holmeslei.movienews.mvp.model;

import com.holmeslei.movienews.http.MovieServiceImpl;
import com.holmeslei.movienews.mvp.model.entity.ShowingMovies;
import com.holmeslei.movienews.mvp.model.listener.GetShowingMoviesListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * author         xulei
 * Date           2017/8/8
 */

public class MainModel {
//    /**
//     * 请求获取正在上映电影
//     */
//    public Disposable requestShowingMovies(String city, final GetShowingMoviesListener getShowingMoviesListener) {
//        return MovieServiceImpl.getInstance().getMoviesNewsByParam(city)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<ShowingMovies>() {
//                    @Override
//                    public void accept(ShowingMovies showingMovies) throws Exception {
//                        getShowingMoviesListener.onSuccess(showingMovies);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        getShowingMoviesListener.onError(throwable.getMessage());
//                    }
//                });
//    }
}
