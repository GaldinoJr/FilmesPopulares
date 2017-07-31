package com.example.galdino.filmespopulares.listFilm;

import com.example.galdino.filmespopulares.dominio.Result;
import com.example.galdino.filmespopulares.mvp.MvpView;

import java.util.List;

/**
 * Created by Galdino on 20/07/2017.
 */

public interface ListFilmMvpView extends MvpView
{
    void onFilmesPreparados(List<Result> movies);

    void onFalhaBuscandoFilmes();

    void onBuscandoFilmes(boolean isGetting);

    void onGetFilmes();
}
