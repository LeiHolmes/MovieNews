package com.holmeslei.movienews.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.holmeslei.movienews.R;
import com.holmeslei.movienews.mvp.presenter.BasePresenter;
import com.holmeslei.movienews.mvp.presenter.MainPresenter;
import com.holmeslei.movienews.mvp.view.MainView;
import com.holmeslei.movienews.ui.base.BaseActivity;

import butterknife.BindView;

/**
 * Description:   入口Activity
 * author         xulei
 * Date           2017/8/7 16:20
 */
public class MainActivity extends BaseActivity implements MainView {
    @BindView(R.id.rv_Main)
    RecyclerView rvMain;
    private MainPresenter mainPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter initPresenter() {
        mainPresenter = new MainPresenter(this);
        return mainPresenter;
    }

    @Override
    protected void onCreateActivity(Bundle savedInstanceState) {

    }

    @Override
    public void showToast(String toastMessage) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }
}
