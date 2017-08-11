package com.holmeslei.movienews.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.holmeslei.movienews.mvp.presenter.BasePresenter;
import com.holmeslei.movienews.mvp.view.BaseView;

/**
 * Description:
 * author         xulei
 * Date           2017/8/8
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    protected T presenter;
    protected Context context;

    /**
     * 绑定Activity
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /**
     * fragment进行回退
     */
    public void onBack() {
        getFragmentManager().popBackStack();
    }

    /**
     * onAttach之后回调
     * 接受别人传递过来的参数,实例化对象
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建Presenter
        presenter = initPresenter();
    }

    /**
     * onCreate之后回调
     * 生成View视图
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, savedInstanceState);
    }

    /**
     * onCreateView之后回调
     * 加载数据
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //由于fragment生命周期比较复杂,所以Presenter在onCreateView创建视图之后再进行绑定,不然会报空指针异常
        presenter.onAttach(this);
        presenter.onCreate();
    }

    @Override
    public void onDestroyView() {
        presenter.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        presenter.onDetach();
        super.onDetach();
    }

    /**
     * 初始化Fragment的视图
     */
    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 创建prensenter
     *
     * @return <T extends BasePresenter> 返回必须是BasePresenter的子类
     */
    protected abstract T initPresenter();

    @Override
    public Context getContext() {
        return context;
    }

    public BaseFragment getFragment() {
        return this;
    }
}
