package com.holmeslei.movienews.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.holmeslei.movienews.R;
import com.holmeslei.movienews.mvp.model.entity.ShowingMovies;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:   首页正在热映电影列表适配器
 * author         xulei
 * Date           2017/8/8
 */

public class ShowingMoviesAdapter extends RecyclerView.Adapter<ShowingMoviesAdapter.MyViewHolder> {
    private Context context;
    private List<ShowingMovies.SubjectsEntity> data = new ArrayList<>();

    public ShowingMoviesAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ShowingMovies.SubjectsEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_showing_movies, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context)
//                .applyDefaultRequestOptions(new RequestOptions().placeholder())) //设置占位，尺寸，填充方式等
                .load(data.get(position).getImages().getMedium())
                .transition(new DrawableTransitionOptions().crossFade(1000)) //淡入淡出1s
                .into(holder.ivHeater);
        holder.tvMovieName.setText(data.get(position).getTitle());
        holder.tvMovieDate.setText("上映时间：" + data.get(position).getYear());
        holder.tvMovieStar.setText("豆瓣评分：" + data.get(position).getRating().getAverage());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_header_sm)
        ImageView ivHeater;
        @BindView(R.id.tv_movie_name_sm)
        TextView tvMovieName;
        @BindView(R.id.tv_movie_date_sm)
        TextView tvMovieDate;
        @BindView(R.id.tv_movie_star_sm)
        TextView tvMovieStar;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}