package com.example.galdino.filmespopulares.utilities;

import com.example.galdino.filmespopulares.Dominio.Filme;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Galdino on 23/07/2017.
 */

public interface MovieDbMvpApiHelper
{
    Observable<List<Filme>> getPopular();

    Observable<List<Filme>> getTopRated();

    Observable<Filme> getMovie(String movieId);

    Observable<Filme> getMovieSummary(String movieId);
}