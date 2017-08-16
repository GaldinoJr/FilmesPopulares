package com.example.galdino.filmespopulares.telas.listaDeFilmes;

import android.content.Context;

import com.example.galdino.filmespopulares.dominio.ObjetoListaFilmes;
import com.example.galdino.filmespopulares.dominio.Filme;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Galdino on 20/07/2017.
 */

public interface ListFilmMvpModel
{
    Single<List<Filme>> getFilmesPopulares();

    Single<List<Filme>> getFilmesMelhorAvaliados();

    Single<List<Filme>> getFilmesFavoritos();

    Single<ObjetoListaFilmes> getMovie(String movieId);

    Single<String> getMoviePosterUrl(int width, String movieId);

    Single<String> getMovieTitle(String movieId);

    Single<ObjetoListaFilmes> getMovieSummary(String movieId);
}
