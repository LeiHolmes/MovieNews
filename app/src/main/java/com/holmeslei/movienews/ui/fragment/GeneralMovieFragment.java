package com.holmeslei.movienews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.holmeslei.movienews.R;
import com.holmeslei.movienews.mvp.model.entity.ShowingMovies;
import com.holmeslei.movienews.mvp.presenter.GeneralFragPresenter;
import com.holmeslei.movienews.mvp.view.GeneralFragView;
import com.holmeslei.movienews.ui.adapter.ShowingMoviesAdapter;
import com.holmeslei.movienews.ui.base.BaseFragment;
import com.holmeslei.movienews.ui.widget.swipe.SwipeRefreshLayout;
import com.holmeslei.movienews.ui.widget.swipe.SwipeRefreshLayoutDirection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:   通用的电影信息列表Fragment
 * author         xulei
 * Date           2017/8/11
 */

public class GeneralMovieFragment extends BaseFragment<GeneralFragPresenter> implements GeneralFragView {
    public static final String FUNCTION = "function";
    public static final String PARAM = "param";
    @BindView(R.id.rv_general_movie)
    RecyclerView rvGeneralMovie;
    @BindView(R.id.srl_general_movie)
    SwipeRefreshLayout srlGeneralMovie;
    private View view;
    private String function; //功能
    private String param; //参数
    private GeneralFragPresenter generalFragPresenter;
    private ShowingMoviesAdapter adapter;

    public static GeneralMovieFragment newInstance(String function, String param) {
        Bundle bundle = new Bundle();
        bundle.putString(FUNCTION, function);
        bundle.putString(PARAM, param);
        GeneralMovieFragment fragment = new GeneralMovieFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void showToast(String toastMessage) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_general_movie, container, false);
        ButterKnife.bind(this, view);
        initData();
        initRecyclerView();
        return view;
    }

    private void initData() {
        function = getArguments().getString(FUNCTION);
        param = getArguments().getString(PARAM);
        requestData();
    }

    /**
     * 请求网络数据
     */
    private void requestData() {
        switch (function) {
            case "影讯":
                generalFragPresenter.requestShowingMovies(param, "北京");
                break;
            case "影评":
                break;
            case "演员":
                break;
            case "电影搜索":
                break;
        }
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        //RecyclerView配置
        adapter = new ShowingMoviesAdapter(getActivity());
        rvGeneralMovie.setAdapter(adapter);
        rvGeneralMovie.setHasFixedSize(true);
        rvGeneralMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置初始为刷新状态
        srlGeneralMovie.post(new Runnable() {
            @Override
            public void run() {
                setSwipeRefreshLoadingState();
            }
        });
        srlGeneralMovie.setDirection(SwipeRefreshLayoutDirection.BOTH);//同时支持上拉和下拉
        srlGeneralMovie.setColorSchemeResources(R.color.colorAccent, R.color.bg_blue, R.color.orange_light,
                R.color.red_light);
        srlGeneralMovie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipeRefreshLayoutDirection direction) {
                setSwipeRefreshLoadingState();
                switch (direction) {
                    case TOP:
                        requestData();
                        break;
                    case BOTTOM:
                        break;
                }
            }
        });
    }

    /**
     * 设置顶部正在加载的状态
     */
    private void setSwipeRefreshLoadingState() {
        if (srlGeneralMovie != null) {
            srlGeneralMovie.setRefreshing(true);
            // 防止多次重复刷新
            srlGeneralMovie.setEnabled(false);
        }
    }

    /**
     * 设置顶部加载完毕的状态
     */
    private void setSwipeRefreshLoadedState() {
        if (srlGeneralMovie != null) {
            srlGeneralMovie.setRefreshing(false);
            srlGeneralMovie.setEnabled(true);
        }
    }

    @Override
    protected GeneralFragPresenter initPresenter() {
        generalFragPresenter = new GeneralFragPresenter(this);
        return generalFragPresenter;
    }

    /**
     * 获得电影数据
     */
    @Override
    public void getShowingMoviesData(ShowingMovies showingMovies) {
        Log.i("getShowingMoviesData", showingMovies.toString());
        adapter.setData(showingMovies.getSubjects());
        setSwipeRefreshLoadedState();
    }

    /**
     * 获得电影数据请求错误日志
     */
    @Override
    public void getShowingMoviesError(String errorMessage) {
        Log.i("getShowingMoviesError", errorMessage);
        setSwipeRefreshLoadedState();
    }
}
