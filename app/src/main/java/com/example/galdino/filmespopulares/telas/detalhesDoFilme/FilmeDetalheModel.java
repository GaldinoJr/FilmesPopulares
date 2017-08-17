package com.example.galdino.filmespopulares.telas.detalhesDoFilme;

import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Result;
import com.example.galdino.filmespopulares.utilities.FilmeApiMvpHelper;

import java.util.List;

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
    public Single<Filme> getFilmeDetalhe(int idFilme) {
        return mvpHelper.getFilmeDetalhe(idFilme).singleOrError();
    }

    @Override
    public Single<List<Result>> getComentarioFilme(int idFilme) {
        return mvpHelper.getComentario(idFilme).singleOrError();
    }


}
