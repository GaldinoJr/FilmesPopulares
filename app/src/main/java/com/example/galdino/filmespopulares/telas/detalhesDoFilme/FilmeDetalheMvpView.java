package com.example.galdino.filmespopulares.telas.detalhesDoFilme;

import com.example.galdino.filmespopulares.dominio.filmeDetalhe.FilmeDetalhe;
import com.example.galdino.filmespopulares.mvp.MvpView;

/**
 * Created by Galdino on 01/08/2017.
 */

public interface FilmeDetalheMvpView extends MvpView
{
    void onFilmeDetalheBuscarInformacoes(int idFilme);
    void onFilmeDetalheFalhaAoBuscarInformacoes();
    void onFilmeDetalhePreparado(FilmeDetalhe filmeDetalhe);
}
