package com.example.galdino.filmespopulares.telas.listaDeFilmes;

import android.content.Context;

import com.example.galdino.filmespopulares.dataBase.AppDataBase;
import com.example.galdino.filmespopulares.dominio.ObjetoListaFilmes;
import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.utilities.FilmeApiMvpHelper;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Galdino on 19/07/2017.
 */

public class ListFilmModel implements ListFilmMvpModel
{
    private FilmeApiMvpHelper mHelper;

    public ListFilmModel(FilmeApiMvpHelper helper)
    {
        mHelper = helper;
    }

    @Override
    public Single<List<Filme>> getFilmesPopulares() {
        return mHelper.getPopular()
                .singleOrError();
    }

    @Override
    public Single<List<Filme>> getFilmesMelhorAvaliados() {
        return mHelper.getMelhorAvaliado()
                .singleOrError();
    }

    @Override
    public Single<List<Filme>> getFilmesFavoritos() {
        return mHelper.getFavorito().singleOrError();
    }

    @Override
    public Single<ObjetoListaFilmes> getMovie(String movieId) {
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
    public Single<ObjetoListaFilmes> getMovieSummary(String movieId) {
        return null;
    }
}
