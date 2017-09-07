package com.holmeslei.movienews.entity.http;

import com.holmeslei.movienews.entity.bean.ShowingMovies;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Description:
 * author         xulei
 * Date           2017/8/8
 */

public interface MovieService {
    //正在上映、即将上映、Top250
    @GET("v2/movie/{param1}")
    Observable<ShowingMovies> getMoviesNewsByParam(@Path("param1") String param1, @Query("city") String city,
                                                   @Query("start") int start, @Query("count") int count);
}
