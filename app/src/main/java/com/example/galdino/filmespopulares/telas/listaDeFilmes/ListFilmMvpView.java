package com.example.galdino.filmespopulares.telas.listaDeFilmes;

import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.mvp.MvpView;

import java.util.List;

/**
 * Created by Galdino on 20/07/2017.
 */

public interface ListFilmMvpView extends MvpView
{
    void onFilmesPreparados(List<Filme> movies);

    void onFalhaBuscandoFilmes();

    void onBuscandoFilmes(boolean isGetting);

    void onBuscarFilmes();
}
