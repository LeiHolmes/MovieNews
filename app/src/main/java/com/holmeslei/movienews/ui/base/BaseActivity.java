package com.holmeslei.movienews.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.holmeslei.movienews.mvp.presenter.BasePresenter;
import com.holmeslei.movienews.mvp.view.BaseView;

import butterknife.ButterKnife;

/**
 * Description:   入口Activity
 * author         xulei
 * Date           2017/8/7 16:20
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    private T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //创建Presenter
        presenter = initPresenter();
        //绑定presenter
        presenter.onAttach(this);
        //初始化Activity
        onCreateActivity(savedInstanceState);
        //初始化Presenter
        presenter.onCreate();
    }

    /**
     * layout资源
     */
    protected abstract int getLayoutId();

    /**
     * 创建prensenter
     *
     * @return <T extends BasePresenter> 返回必须是BasePresenter的子类
     */
    protected abstract T initPresenter();

    /**
     * 子类必须实现,并初始化Activity
     */
    protected abstract void onCreateActivity(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        presenter.onDetach();
        super.onDestroy();
    }
}
