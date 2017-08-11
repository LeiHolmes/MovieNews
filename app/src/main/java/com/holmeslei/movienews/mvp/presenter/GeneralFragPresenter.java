package com.holmeslei.movienews.mvp.presenter;

import com.holmeslei.movienews.mvp.model.GeneralFragModel;
import com.holmeslei.movienews.mvp.model.entity.ShowingMovies;
import com.holmeslei.movienews.mvp.model.listener.GetShowingMoviesListener;
import com.holmeslei.movienews.mvp.view.GeneralFragView;

/**
 * Description:
 * author         xulei
 * Date           2017/8/11
 */

public class GeneralFragPresenter extends BasePresenter<GeneralFragView> {
    private GeneralFragModel generalFragModel;

    public GeneralFragPresenter(GeneralFragView view) {
        super(view);
        generalFragModel = new GeneralFragModel();
    }

    @Override
    public void onCreate() {
    }

    /**
     * v,m交互，请求获取正在上映电影
     */
    public void requestShowingMovies(String city) {
        addDisposable(generalFragModel.requestShowingMovies(city, new GetShowingMoviesListener() {
            @Override
            public void onSuccess(ShowingMovies showingMovies) {
                view.getShowingMoviesData(showingMovies);
            }

            @Override
            public void onError(String errorMessage) {
                view.getShowingMoviesError(errorMessage);
            }
        }));
    }
}
