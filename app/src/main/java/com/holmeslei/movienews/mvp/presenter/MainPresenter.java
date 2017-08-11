package com.holmeslei.movienews.mvp.presenter;

import com.holmeslei.movienews.mvp.model.MainModel;
import com.holmeslei.movienews.mvp.model.entity.ShowingMovies;
import com.holmeslei.movienews.mvp.model.listener.GetShowingMoviesListener;
import com.holmeslei.movienews.mvp.view.MainView;

/**
 * Description:
 * author         xulei
 * Date           2017/8/8
 */

public class MainPresenter extends BasePresenter<MainView> {
    private MainModel mainModel;

    public MainPresenter(MainView mainView) {
        super(mainView);
        mainModel = new MainModel();
    }

    @Override
    public void onCreate() {
    }

//    /**
//     * v,m交互，请求获取正在上映电影
//     */
//    public void requestShowingMovies(String city) {
//        addDisposable(mainModel.requestShowingMovies(city, new GetShowingMoviesListener() {
//            @Override
//            public void onSuccess(ShowingMovies showingMovies) {
//                view.getShowingMoviesData(showingMovies);
//            }
//
//            @Override
//            public void onError(String errorMessage) {
//                view.getShowingMoviesError(errorMessage);
//            }
//        }));
//    }
}
