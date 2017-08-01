package com.example.galdino.filmespopulares.telas.detalhesDoFilme;

import com.example.galdino.filmespopulares.dominio.filmeDetalhe.FilmeDetalhe;

import io.reactivex.Single;

/**
 * Created by Galdino on 01/08/2017.
 */

public interface FilmeDetalheMvpModel
{
    Single<FilmeDetalhe> getFilmeDetalhe(int idFilme);
}
