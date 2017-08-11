package com.holmeslei.movienews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.holmeslei.movienews.R;
import com.holmeslei.movienews.mvp.presenter.GeneralFragPresenter;
import com.holmeslei.movienews.mvp.view.GeneralFragView;
import com.holmeslei.movienews.ui.base.BaseFragment;

/**
 * Description:
 * author         xulei
 * Date           2017/8/11
 */

public class GeneralMovieFragment extends BaseFragment<GeneralFragPresenter> implements GeneralFragView {
    public static final String ARGS_PAGE = "args_page";
    private GeneralFragPresenter generalFragPresenter;
    private View view;
    private int pageIndex;

    public static GeneralMovieFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, page);
        GeneralMovieFragment fragment = new GeneralMovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showToast(String toastMessage) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_general_movie, container, false);
        pageIndex = getArguments().getInt(ARGS_PAGE);
        
        return view;
    }

    @Override
    protected GeneralFragPresenter initPresenter() {
        generalFragPresenter = new GeneralFragPresenter(this);
        return generalFragPresenter;
    }
}
