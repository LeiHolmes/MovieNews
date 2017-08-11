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
import com.holmeslei.movienews.mvp.model.entity.ShowingMovies;
import com.holmeslei.movienews.mvp.presenter.GeneralFragPresenter;
import com.holmeslei.movienews.mvp.view.GeneralFragView;
import com.holmeslei.movienews.ui.adapter.ShowingMoviesAdapter;
import com.holmeslei.movienews.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * author         xulei
 * Date           2017/8/11
 */

public class GeneralMovieFragment extends BaseFragment<GeneralFragPresenter> implements GeneralFragView {
    public static final String FUNCTION = "function";
    public static final String PARAM = "param";
    @BindView(R.id.rv_general_movie)
    RecyclerView rvGeneralMovie;
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
        switch (function) {
            case "影讯":
                switch (param) {
                    case "in_theaters": //正在热映
                        //请求北京正在热映电影的数据
                        generalFragPresenter.requestShowingMovies("北京");
                        break;
                    case "coming_soon": //即将上映
                        break;
                    case "top250": //Top250
                        break;
                    case "us_box": //口碑榜
                        break;
                    case "weekly": //北美票房榜
                        break;
                    case "new_movies": //新片榜
                        break;
                }
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
        adapter = new ShowingMoviesAdapter(getActivity());
        rvGeneralMovie.setAdapter(adapter);
        rvGeneralMovie.setHasFixedSize(true);
        rvGeneralMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected GeneralFragPresenter initPresenter() {
        generalFragPresenter = new GeneralFragPresenter(this);
        return generalFragPresenter;
    }

    @Override
    public void getShowingMoviesData(ShowingMovies showingMovies) {
        Log.i("getShowingMoviesData", showingMovies.toString());
        adapter.setData(showingMovies.getSubjects());
    }

    @Override
    public void getShowingMoviesError(String errorMessage) {
        Log.i("getShowingMoviesError", errorMessage);
    }
}
