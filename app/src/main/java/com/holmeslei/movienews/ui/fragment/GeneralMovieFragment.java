package com.holmeslei.movienews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.holmeslei.movienews.R;
import com.holmeslei.movienews.constant.SwipeRequestType;
import com.holmeslei.movienews.mvp.model.entity.ShowingMovies;
import com.holmeslei.movienews.mvp.presenter.GeneralFragPresenter;
import com.holmeslei.movienews.mvp.view.GeneralFragView;
import com.holmeslei.movienews.ui.adapter.ShowingMoviesAdapter;
import com.holmeslei.movienews.ui.base.BaseFragment;
import com.holmeslei.movienews.ui.widget.swipe.SwipeRefreshLayout;
import com.holmeslei.movienews.ui.widget.swipe.SwipeRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:   通用的电影信息ViewPager的Fragment
 * author         xulei
 * Date           2017/8/11
 */

public class GeneralMovieFragment extends BaseFragment<GeneralFragPresenter> implements GeneralFragView {
    public static final String FUNCTION = "function";
    public static final String PARAM = "param";
    private String function; //功能
    private String param; //请求参数
    private int start = 0;
    private int startCache = 0; //缓存页数，防止请求错误
    private int count = 20;
    @BindView(R.id.rv_general_movie)
    RecyclerView rvGeneralMovie;
    @BindView(R.id.srl_general_movie)
    SwipeRefreshLayout srlGeneralMovie;
    private GeneralFragPresenter generalFragPresenter;
    private ShowingMoviesAdapter adapter;
    private List<ShowingMovies.SubjectsEntity> data;

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
        View view = inflater.inflate(R.layout.fragment_general_movie, container, false);
        ButterKnife.bind(this, view);
        initData();
        initRecyclerView();
        return view;
    }

    private void initData() {
        function = getArguments().getString(FUNCTION);
        param = getArguments().getString(PARAM);
        data = new ArrayList<>();
        requestData(SwipeRequestType.TYPE_DOWN, start, count);
    }

    /**
     * 请求网络数据
     */
    private void requestData(int type, int start, int count) {
        switch (function) {
            case "影讯":
                generalFragPresenter.requestShowingMovies(param, "北京", start, count, type);
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
        adapter = new ShowingMoviesAdapter(getActivity(), data);
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
                        start = 0;
                        requestData(SwipeRequestType.TYPE_DOWN, start, count);
                        break;
                    case BOTTOM:
                        startCache = start;
                        start = start + count;
                        requestData(SwipeRequestType.TYPE_UP, start, count);
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
    public void getShowingMoviesData(ShowingMovies showingMovies, int type) {
        Log.i("getShowingMoviesData", showingMovies.toString());
        if (type == SwipeRequestType.TYPE_DOWN) { //下拉
            data.clear();
            data = showingMovies.getSubjects();
        } else if (type == SwipeRequestType.TYPE_UP) { //上拉
            data.addAll(showingMovies.getSubjects());
            if (showingMovies.getSubjects().size() == 0) {
                showToast("没有更多了");
            }
        }
        adapter.setData(data);
        setSwipeRefreshLoadedState();
    }

    /**
     * 获得电影数据请求错误日志
     */
    @Override
    public void getShowingMoviesError(String errorMessage, int type) {
        Log.i("getShowingMoviesError", errorMessage);
        setSwipeRefreshLoadedState();
        if (type == SwipeRequestType.TYPE_UP) {
            start = startCache;
        }
    }
}
