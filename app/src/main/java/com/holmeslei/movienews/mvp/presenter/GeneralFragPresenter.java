package com.holmeslei.movienews.mvp.presenter;

import com.holmeslei.movienews.mvp.model.GeneralFragModel;
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
}
