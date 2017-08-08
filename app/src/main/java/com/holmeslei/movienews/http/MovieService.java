package com.holmeslei.movienews.http;

import com.holmeslei.movienews.mvp.model.entity.ShowingMovies;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Description:
 * author         xulei
 * Date           2017/8/8
 */

public interface MovieService {
    //正在上映
    @GET("in_theaters")
    Observable<ShowingMovies> getShowingMovies(@Query("city") String city);
}
