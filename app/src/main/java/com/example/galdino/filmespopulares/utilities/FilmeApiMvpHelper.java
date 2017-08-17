package com.example.galdino.filmespopulares.utilities;

import android.content.Context;

import com.example.galdino.filmespopulares.dominio.ObjetoListaFilmes;
import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Comentarios;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Result;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Galdino on 20/07/2017.
 */

public interface FilmeApiMvpHelper
{
    Observable<List<Filme>> getPopular();

    Observable<List<Filme>> getMelhorAvaliado();

    Observable<List<Filme>> getFavorito();

    Observable<Filme> getFilmeDetalhe(int idFilme);

    Observable<List<Result>> getComentario(int idFilme);
}
