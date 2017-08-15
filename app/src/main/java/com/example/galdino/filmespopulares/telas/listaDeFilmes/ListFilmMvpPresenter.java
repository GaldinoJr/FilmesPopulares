package com.example.galdino.filmespopulares.telas.listaDeFilmes;

import android.content.Context;

import com.example.galdino.filmespopulares.mvp.MvpPresenter;

/**
 * Created by Galdino on 20/07/2017.
 */

public interface ListFilmMvpPresenter extends MvpPresenter<ListFilmMvpView>
{
    void getFilmesPopulares();

    void getFilmesMelhorAvaliados();

    void getFilmesFavoritos(Context context);
}
