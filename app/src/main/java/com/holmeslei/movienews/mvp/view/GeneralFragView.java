package com.holmeslei.movienews.mvp.view;

import com.holmeslei.movienews.mvp.model.entity.ShowingMovies;

/**
 * Description:
 * author         xulei
 * Date           2017/8/11
 */

public interface GeneralFragView extends BaseView {
    void getShowingMoviesData(ShowingMovies showingMovies);

    void getShowingMoviesError(String errorMessage);
}