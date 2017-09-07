package com.holmeslei.movienews.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description:   无MVP的基本Activity
 * author         xulei
 * Date           2017/9/7 09:30
 */

public abstract class BaseSimpleActivity extends AppCompatActivity {
    protected Activity mContext;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //初始化ButterKnife
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        //初始化视图
        onViewCreated(savedInstanceState);
        //初始化状态与数据
        initStateAndData();
        //初始化监听
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    protected abstract int getLayoutId();

    protected abstract void onViewCreated(Bundle savedInstanceState);

    protected abstract void initStateAndData();

    protected abstract void initListener();
}
