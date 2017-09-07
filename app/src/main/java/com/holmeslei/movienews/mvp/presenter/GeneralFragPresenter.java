package com.holmeslei.movienews.mvp.presenter;

import com.holmeslei.movienews.base.BasePresenter;
import com.holmeslei.movienews.mvp.contract.GeneralFragContract;
import com.holmeslei.movienews.mvp.model.GeneralFragModel;
import com.holmeslei.movienews.entity.bean.ShowingMovies;
import com.holmeslei.movienews.entity.listener.GetShowingMoviesListener;

/**
 * Description:   通用Fragment的Presenter
 * author         xulei
 * Date           2017/8/11
 */

public class GeneralFragPresenter extends BasePresenter<GeneralFragContract.View>
        implements GeneralFragContract.Presenter {
    private GeneralFragModel generalFragModel;

    @Override
    public void onPresenterCreated() {
        generalFragModel = new GeneralFragModel();
    }

    @Override
    public void onPresenterDestroy() {

    }

    /**
     * v,m交互，请求获取正在上映、即将上映、Top250电影
     */
    @Override
    public void requestShowingMovies(String movieParam, String city, int start, int count, final int type) {
        addSubscribe(generalFragModel.requestShowingMovies(movieParam, city, start, count, new GetShowingMoviesListener() {
            @Override
            public void onSuccess(ShowingMovies showingMovies) {
                mView.getShowingMoviesData(showingMovies, type);
            }

            @Override
            public void onError(String errorMessage) {
                mView.getShowingMoviesError(errorMessage, type);
            }
        }));
    }
}
