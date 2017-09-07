package com.holmeslei.movienews.mvp.contract;

import com.holmeslei.movienews.base.BaseSimplePresenter;
import com.holmeslei.movienews.base.BaseView;
import com.holmeslei.movienews.mvp.model.entity.ShowingMovies;

/**
 * Description:   通用Fragment的Contract
 * author         xulei
 * Date           2017/8/11
 */

public interface GeneralFragContract {
    interface View extends BaseView {
        void getShowingMoviesData(ShowingMovies showingMovies, int type);

        void getShowingMoviesError(String errorMessage, int type);
    }

    interface Presenter extends BaseSimplePresenter<View> {
        void requestShowingMovies(String movieParam, String city, int start, int count, final int type);
    }
}
