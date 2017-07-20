package com.example.galdino.filmespopulares.listFilm;

import com.example.galdino.filmespopulares.mvp.MvpPresenter;

/**
 * Created by Galdino on 20/07/2017.
 */

public interface ListFilmMvpPresenter extends MvpPresenter<ListFilmMvpView>
{
    void getPopularMovies();

    void getTopRatedMovies();
}
