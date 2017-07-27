package com.example.galdino.filmespopulares.listFilm;

import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.dominio.Result;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Galdino on 20/07/2017.
 */

public interface ListFilmMvpModel
{
    Single<List<Result>> getPopularMovies();

    Single<List<Filme>> getTopRatedMovies();

    Single<Filme> getMovie(String movieId);

    Single<String> getMoviePosterUrl(int width, String movieId);

    Single<String> getMovieTitle(String movieId);

    Single<Filme> getMovieSummary(String movieId);

    void saveMovieOnCache(Filme movie);
}
