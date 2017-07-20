package com.example.galdino.filmespopulares.listFilm;

import com.example.galdino.filmespopulares.Dominio.Filme;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Galdino on 20/07/2017.
 */

public interface FilmeApiMvpHelper
{
    Observable<List<Filme>> getPopular();

    Observable<List<Filme>> getTopRated();

    Observable<Filme> getMovie(String movieId);

    Observable<Filme> getMovieSummary(String movieId);
}
