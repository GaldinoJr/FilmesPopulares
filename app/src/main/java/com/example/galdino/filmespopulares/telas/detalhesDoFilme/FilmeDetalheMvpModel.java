package com.example.galdino.filmespopulares.telas.detalhesDoFilme;

import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Result;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Galdino on 01/08/2017.
 */

public interface FilmeDetalheMvpModel
{
    Single<Filme> getFilmeDetalhe(int idFilme);

    Single<List<Result>> getComentarioFilme(int idFilme);

}
