package com.example.galdino.filmespopulares.telas.detalhesDoFilme;

import com.example.galdino.filmespopulares.dominio.filmeDetalhe.FilmeDetalhe;
import com.example.galdino.filmespopulares.utilities.FilmeApiMvpHelper;

import io.reactivex.Single;

/**
 * Created by Galdino on 01/08/2017.
 */

public class FilmeDetalheModel implements FilmeDetalheMvpModel
{
    private FilmeApiMvpHelper mvpHelper;

    public FilmeDetalheModel(FilmeApiMvpHelper mvpHelper) {
        this.mvpHelper = mvpHelper;
    }

    @Override
    public Single<FilmeDetalhe> getFilmeDetalhe(int idFilme) {
        return mvpHelper.getFilmeDetalhe(idFilme).singleOrError();
    }
}
