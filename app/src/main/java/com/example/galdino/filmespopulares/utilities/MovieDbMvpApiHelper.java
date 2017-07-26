package com.example.galdino.filmespopulares.utilities;

import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.dominio.Result;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Galdino on 23/07/2017.
 */

public interface MovieDbMvpApiHelper
{
    Observable<List<Result>> getPopular();

    Observable<List<Result>> getTopRated();

    Observable<Filme> getMovie(String movieId);

    Observable<Filme> getMovieSummary(String movieId);
}
