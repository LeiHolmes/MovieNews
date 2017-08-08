package com.holmeslei.movienews.mvp.presenter;

import com.holmeslei.movienews.mvp.view.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Description:   MVP中的基本view接口类
 * author         xulei
 * Date           2017/8/7
 */

public abstract class BasePresenter<T extends BaseView> {
    protected T view;
    protected CompositeDisposable compositeDisposable; //用于取消订阅

    public BasePresenter(T view) {
        this.view = view;
    }

    /**
     * 绑定View
     */
    public void onAttach(T view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    /**
     * 做初始化的操作,需要在V的视图初始化完成之后才能调用
     * presenter进行初始化.
     */
    public abstract void onCreate();

    /**
     * 在这里结束异步操作
     */
    public void onDestroy() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

    /**
     * 在V销毁的时候调用，解除绑定
     */
    public void onDetach() {
        view = null;
    }

    protected void addDisposable(Disposable disposable) {
        if (disposable != null && compositeDisposable != null) {
            compositeDisposable.add(disposable);
        }
    }
}
