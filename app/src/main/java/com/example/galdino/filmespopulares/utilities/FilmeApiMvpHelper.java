package com.example.galdino.filmespopulares.utilities;

import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.dominio.Result;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Galdino on 20/07/2017.
 */

public interface FilmeApiMvpHelper
{
    Observable<List<Result>> getPopular();

    Observable<List<Result>> getMelhorAvaliado();

    Observable<Filme> getMovie(String movieId);

    Observable<Filme> getMovieSummary(String movieId);
}
