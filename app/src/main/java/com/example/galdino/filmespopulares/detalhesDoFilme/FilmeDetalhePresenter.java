package com.example.galdino.filmespopulares.detalhesDoFilme;

import com.example.galdino.filmespopulares.filmeDetalhe.FilmeDetalhe;
import com.example.galdino.filmespopulares.listaDeFilmes.ListFilmMvpPresenter;
import com.example.galdino.filmespopulares.listaDeFilmes.ListFilmMvpView;
import com.example.galdino.filmespopulares.mvp.BasePresenter;
import com.example.galdino.filmespopulares.mvp.schedulerprovider.SchedulerProvider;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Galdino on 01/08/2017.
 */

public class FilmeDetalhePresenter extends BasePresenter<FilmeDetalheMvpView> implements FilmeDetalheMvpPresenter
{
    private FilmeDetalheMvpView mMvpView;
    private FilmeDetalheMvpModel mMvpModel;

    public FilmeDetalhePresenter(SchedulerProvider schedulerProvider, FilmeDetalheMvpModel mMvpModel) {
        super(schedulerProvider);
        this.mMvpModel = mMvpModel;
    }


    @Override
    public void attach(FilmeDetalheMvpView filmeDetalheMvpView) {
        this.mMvpView = filmeDetalheMvpView;
    }

    @Override
    public void detachView() {
        mMvpView = getEmptyView();
    }

    @Override
    public void getFilmeDetalhe(int idFilme) {
        SchedulerProvider schedulerProvider = getSchedulerProvider();
        mMvpModel.getFilmeDetalhe(idFilme)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(getFilmeObserver());
    }

    private SingleObserver<FilmeDetalhe> getFilmeObserver() {
        return new SingleObserver<FilmeDetalhe>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull FilmeDetalhe filmeDetalhe) {
                mMvpView.onFilmeDetalhePreparado(filmeDetalhe);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mMvpView.onFilmeDetalheFalhaAoBuscarInformacoes();
            }
        };
    }

    private FilmeDetalheMvpView getEmptyView() {
        return new FilmeDetalheMvpView() {

            @Override
            public void onFilmeDetalheBuscarInformacoes(int idFilme) {

            }

            @Override
            public void onFilmeDetalheFalhaAoBuscarInformacoes() {

            }

            @Override
            public void onFilmeDetalhePreparado(FilmeDetalhe filmeDetalhe) {

            }
        };
    }
}
