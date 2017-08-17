package com.example.galdino.filmespopulares.telas.detalhesDoFilme;

import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Comentarios;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Result;
import com.example.galdino.filmespopulares.mvp.MvpView;

import java.util.List;

/**
 * Created by Galdino on 01/08/2017.
 */

public interface FilmeDetalheMvpView extends MvpView
{
    void onFilmeDetalheBuscarInformacoes(int idFilme);
    void onFilmeDetalheFalhaAoBuscarInformacoes();
    void onFilmeDetalhePreparado(Filme filme);
    void onComentariosPreparado(List<Result> comentarios);
    void onComentarioFalhaAoBuscarInformacoes();

}

