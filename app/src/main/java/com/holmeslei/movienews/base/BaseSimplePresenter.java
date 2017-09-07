package com.holmeslei.movienews.base;

/**
 * Description:   基于RxJava的Presenter基本接口
 * author         xulei
 * Date           2017/9/7 09:30
 */
public interface BaseSimplePresenter<T extends BaseView> {
    /**
     * 绑定Presenter
     */
    void attachView(T view);

    /**
     * 解绑Presenter
     */
    void detachView();

    /**
     * Presenter创建回调
     */
    void onPresenterCreated();

    /**
     * Presenter销毁回调
     */
    void onPresenterDestroy();
}
