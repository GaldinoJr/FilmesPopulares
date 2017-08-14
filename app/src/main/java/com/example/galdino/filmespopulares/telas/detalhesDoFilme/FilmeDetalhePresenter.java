package com.example.galdino.filmespopulares.telas.detalhesDoFilme;

import com.example.galdino.filmespopulares.dominio.Filme;
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

    private SingleObserver<Filme> getFilmeObserver() {
        return new SingleObserver<Filme>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Filme filme) {
                mMvpView.onFilmeDetalhePreparado(filme);
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
            public void onFilmeDetalhePreparado(Filme filme) {

            }
        };
    }
}
