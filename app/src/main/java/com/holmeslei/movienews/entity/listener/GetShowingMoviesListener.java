package com.holmeslei.movienews.entity.listener;

import com.holmeslei.movienews.entity.bean.ShowingMovies;

/**
 * Description:   获取正在上映电影请求结果状态回调
 * author         xulei
 * Date           2017/8/8
 */

public interface GetShowingMoviesListener {
    void onSuccess(ShowingMovies showingMovies);

    void onError(String errorMessage);
}
