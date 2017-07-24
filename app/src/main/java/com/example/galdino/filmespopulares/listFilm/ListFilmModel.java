package com.example.galdino.filmespopulares.listFilm;

import com.example.galdino.filmespopulares.Dominio.Filme;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Galdino on 19/07/2017.
 */

public class ListFilmModel implements ListFilmMvpModel
{
    private FilmeApiMvpHelper mHelper;
    @Override
    public Single<List<Filme>> getPopularMovies() {
        return mHelper.getPopular()
                .singleOrError();
    }

    @Override
    public Single<List<Filme>> getTopRatedMovies() {
        return null;
    }

    @Override
    public Single<Filme> getMovie(String movieId) {
        return null;
    }

    @Override
    public Single<String> getMoviePosterUrl(int width, String movieId) {
        return null;
    }

    @Override
    public Single<String> getMovieTitle(String movieId) {
        return null;
    }

    @Override
    public Single<Filme> getMovieSummary(String movieId) {
        return null;
    }

    @Override
    public void saveMovieOnCache(Filme movie) {

    }
}
