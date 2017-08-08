package com.holmeslei.movienews.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.holmeslei.movienews.R;
import com.holmeslei.movienews.mvp.model.entity.ShowingMovies;
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
//    @BindView(R.id.rv_Main)
//    RecyclerView rvMain;
    @BindView(R.id.tv_main)
    TextView tvMain;
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
        mainPresenter.getShowingMovies("北京");
    }

    @Override
    public void showToast(String toastString) {
        Toast.makeText(this, toastString, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取正在上映电影数据
     */
    @Override
    public void getShowingMoviesData(ShowingMovies showingMovies) {
        Log.e("getShowingMoviesData", showingMovies.toString());
        tvMain.setText(showingMovies.toString());
    }

    /**
     * 获取正在上映电影数据失败
     */
    @Override
    public void getShowingMoviesError(String errorMessage) {
        Log.e("getShowingMoviesError", errorMessage);
        tvMain.setText(errorMessage);
    }
}
