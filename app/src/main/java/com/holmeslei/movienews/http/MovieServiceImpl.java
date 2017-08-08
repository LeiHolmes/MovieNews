package com.holmeslei.movienews.http;

import com.holmeslei.movienews.util.RetrofitUtil;

/**
 * Description:   
 * author         xulei
 * Date           2017/8/8
 */

public class MovieServiceImpl {
    private MovieServiceImpl() {
    }

    public static MovieService getInstance() {
        return createMovieService.movieService;
    }

    private static class createMovieService {
        private static final MovieService movieService = RetrofitUtil.getRetrofit().create(MovieService.class);
    }
}
