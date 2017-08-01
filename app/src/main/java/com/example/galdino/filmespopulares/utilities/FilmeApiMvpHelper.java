package com.example.galdino.filmespopulares.utilities;

import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.dominio.Result;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.FilmeDetalhe;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Galdino on 20/07/2017.
 */

public interface FilmeApiMvpHelper
{
    Observable<List<Result>> getPopular();

    Observable<List<Result>> getMelhorAvaliado();

    Observable<FilmeDetalhe> getFilmeDetalhe(int movieId);

    Observable<Filme> getMovieSummary(String movieId);
}
