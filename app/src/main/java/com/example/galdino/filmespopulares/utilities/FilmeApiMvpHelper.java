package com.example.galdino.filmespopulares.utilities;

import com.example.galdino.filmespopulares.dominio.ObjetoListaFilmes;
import com.example.galdino.filmespopulares.dominio.Filme;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Galdino on 20/07/2017.
 */

public interface FilmeApiMvpHelper
{
    Observable<List<Filme>> getPopular();

    Observable<List<Filme>> getMelhorAvaliado();

    Observable<Filme> getFilmeDetalhe(int movieId);

    Observable<ObjetoListaFilmes> getMovieSummary(String movieId);
}
