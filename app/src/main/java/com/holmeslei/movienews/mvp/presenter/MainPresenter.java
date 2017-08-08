package com.holmeslei.movienews.mvp.presenter;

import com.holmeslei.movienews.mvp.model.MainModel;
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

}
