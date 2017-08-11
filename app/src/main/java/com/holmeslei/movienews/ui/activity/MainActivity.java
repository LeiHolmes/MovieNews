package com.holmeslei.movienews.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.holmeslei.movienews.R;
import com.holmeslei.movienews.mvp.model.entity.ShowingMovies;
import com.holmeslei.movienews.mvp.presenter.BasePresenter;
import com.holmeslei.movienews.mvp.presenter.MainPresenter;
import com.holmeslei.movienews.mvp.view.MainView;
import com.holmeslei.movienews.ui.adapter.MainViewPagerAdapter;
import com.holmeslei.movienews.ui.adapter.ShowingMoviesAdapter;
import com.holmeslei.movienews.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:   入口Activity
 * author         xulei
 * Date           2017/8/7 16:20
 */
public class MainActivity extends BaseActivity implements MainView {
    //    @BindView(R.id.rv_Main)
//    RecyclerView rvMain;
    @BindView(R.id.dl_main)
    DrawerLayout dlMain;
    @BindView(R.id.tb_base)
    Toolbar tbBase;
    @BindView(R.id.tl_base)
    TabLayout tlBase;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.nav_main)
    NavigationView navMain;
    private ActionBarDrawerToggle toggle;
    private MainPresenter mainPresenter;
    //    private ShowingMoviesAdapter adapter;
    private MenuItem firstMenuItem; //侧滑菜单第一个MenuItem
    private MainViewPagerAdapter adapter;
    private String[] titles = new String[]{"正在热映", "即将上映", "Top250", "口碑榜", "北美新票房榜", "新片榜"};

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
        initToolBar();
        initTabAndViewPager();
        initNavigationView();
//        initRecyclerView();
        initData();
    }

    /**
     * 初始化ToolBar
     */
    private void initToolBar() {
        tbBase.setTitle("影讯");
        tbBase.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(tbBase);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用 
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //加返回箭头
        toggle = new ActionBarDrawerToggle(this, dlMain, tbBase, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        toggle.syncState();
        dlMain.setDrawerListener(toggle);
    }

    private void initTabAndViewPager() {
        adapter = new MainViewPagerAdapter(getSupportFragmentManager(), this, titles);
        vpMain.setAdapter(adapter);
        tlBase.setupWithViewPager(vpMain);
    }

    /**
     * 初始化侧滑菜单NavigationView
     */
    private void initNavigationView() {
        navMain.setItemIconTintList(null); //设置图标为彩色，否则都为灰色
        firstMenuItem = navMain.getMenu().findItem(R.id.menu_nav_news);
        navMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true); //设置选中
                dlMain.closeDrawer(GravityCompat.START); //关侧滑
                tbBase.setTitle(item.getTitle().toString()); //设置ToolBar标题
                //解决点击其他Menu后，第一个Menu选中状态不消失问题
                if (item.getItemId() != firstMenuItem.getItemId()) {
                    firstMenuItem.setChecked(false);
                }
                switch (item.getItemId()) {
                    case R.id.menu_nav_news:
                        break;
                    case R.id.menu_nav_comments:
                        break;
                    case R.id.menu_nav_actors:
                        break;
                    case R.id.menu_nav_search:
                        break;
                    case R.id.menu_nav_setting:
                        break;
                    case R.id.menu_nav_about:
                        break;
                }
                return true;
            }
        });
    }

//    /**
//     * 初始化RecyclerView
//     */
//    private void initRecyclerView() {
//        adapter = new ShowingMoviesAdapter(this);
//        rvMain.setAdapter(adapter);
//        rvMain.setHasFixedSize(true);
//        rvMain.setLayoutManager(new LinearLayoutManager(this));
//    }

    @Override
    public void showToast(String toastString) {
        Toast.makeText(this, toastString, Toast.LENGTH_SHORT).show();
    }

    private void initData() {
        //请求北京正在热映电影的数据
        mainPresenter.requestShowingMovies("北京");
    }

    /**
     * 获取正在上映电影数据
     */
    @Override
    public void getShowingMoviesData(ShowingMovies showingMovies) {
        Log.i("getShowingMoviesData", showingMovies.toString());
//        adapter.setData(showingMovies.getSubjects());
    }

    /**
     * 获取正在上映电影数据失败
     */
    @Override
    public void getShowingMoviesError(String errorMessage) {
        Log.i("getShowingMoviesError", errorMessage);
    }
}
