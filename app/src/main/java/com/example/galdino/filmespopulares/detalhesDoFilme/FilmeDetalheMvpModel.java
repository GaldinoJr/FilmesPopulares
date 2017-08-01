package com.example.galdino.filmespopulares.detalhesDoFilme;

import com.example.galdino.filmespopulares.filmeDetalhe.FilmeDetalhe;
import com.example.galdino.filmespopulares.mvp.MvpView;

import io.reactivex.Single;

/**
 * Created by Galdino on 01/08/2017.
 */

public interface FilmeDetalheMvpModel
{
    Single<FilmeDetalhe> getFilmeDetalhe(int idFilme);
}
