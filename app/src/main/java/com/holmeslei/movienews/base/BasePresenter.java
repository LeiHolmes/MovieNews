package com.holmeslei.movienews.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Description:   基于RxJava的Presenter封装,控制订阅的生命周期
 * author         xulei
 * Date           2017/8/7
 */

public abstract class BasePresenter<T extends BaseView> implements BaseSimplePresenter<T> {
    protected T mView;
    protected CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    /**
     * 解绑Presenter
     */
    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }

    /**
     * 添加RxJava订阅
     */
    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    /**
     * 取消RxJava订阅
     */
    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
