package com.example.galdino.filmespopulares.detalhesDoFilme;

import com.example.galdino.filmespopulares.filmeDetalhe.FilmeDetalhe;
import com.example.galdino.filmespopulares.mvp.MvpView;

import java.util.List;

/**
 * Created by Galdino on 01/08/2017.
 */

public interface FilmeDetalheMvpView extends MvpView
{
    void onFilmeDetalheBuscarInformacoes(String idFilme);
    void onFilmeDetalheFalhaAoBuscarInformacoes();
    void onFilmeDetalhePreparado(FilmeDetalhe filmeDetalhe);
}
