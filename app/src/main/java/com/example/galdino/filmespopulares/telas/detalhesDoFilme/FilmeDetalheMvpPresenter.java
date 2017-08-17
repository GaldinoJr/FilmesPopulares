package com.example.galdino.filmespopulares.telas.detalhesDoFilme;

import com.example.galdino.filmespopulares.mvp.MvpPresenter;

/**
 * Created by Galdino on 01/08/2017.
 */

public interface FilmeDetalheMvpPresenter extends MvpPresenter<FilmeDetalheMvpView>{
    void getFilmeDetalhe(int idFilme);
    void getComentarios(int idFilme);
}
