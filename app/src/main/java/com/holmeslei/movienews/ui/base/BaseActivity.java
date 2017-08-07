package com.holmeslei.movienews.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.holmeslei.movienews.R;

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
     * 子类必须实现,并初始化Activity,比如setContentView()
     */
    protected abstract void onCreateActivity(Bundle savedInstanceState);

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        presenter.onDetach();
        super.onDestroy();
    }
}
