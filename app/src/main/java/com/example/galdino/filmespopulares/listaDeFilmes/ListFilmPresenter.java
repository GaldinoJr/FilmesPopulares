package com.example.galdino.filmespopulares.listaDeFilmes;

import com.example.galdino.filmespopulares.dominio.Result;
import com.example.galdino.filmespopulares.mvp.BasePresenter;
import com.example.galdino.filmespopulares.mvp.schedulerprovider.SchedulerProvider;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Galdino on 19/07/2017.
 */

public class ListFilmPresenter extends BasePresenter<ListFilmMvpView> implements ListFilmMvpPresenter
{
    private ListFilmMvpView mMvpView;
    private ListFilmMvpModel mFilmeMvpDataManager;

    public ListFilmPresenter(SchedulerProvider schedulerProvider, ListFilmMvpModel dataManager) {
        super(schedulerProvider);
        this.mFilmeMvpDataManager = dataManager;
    }

    @Override
    public void getFilmesPopulares() {
        SchedulerProvider schedulerProvider = getSchedulerProvider();
        mFilmeMvpDataManager.getFilmesPopulares()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(getFilmesObserver());
    }

    @Override
    public void getFilmesMelhorAvaliados() {
        SchedulerProvider schedulerProvider = getSchedulerProvider();
        mFilmeMvpDataManager.getFilmesMelhorAvaliados()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(getFilmesObserver());
    }

    private SingleObserver<List<Result>> getFilmesObserver() {
        return new SingleObserver<List<Result>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d)
            {
                mMvpView.onBuscandoFilmes(true);
            }

            @Override
            public void onSuccess(@NonNull List<Result> movies)
            {
                mMvpView.onFilmesPreparados(movies);
                mMvpView.onBuscandoFilmes(false);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mMvpView.onFalhaBuscandoFilmes();
                mMvpView.onBuscandoFilmes(false);
            }
        };
    }

    @Override
    public void attach(ListFilmMvpView listFilmMvpView) {
        mMvpView = listFilmMvpView;
    }

    @Override
    public void detachView() {
        mMvpView = getEmptyView();
    }
    private ListFilmMvpView getEmptyView() {
        return new ListFilmMvpView() {
            @Override
            public void onFilmesPreparados(List<Result> movies) {
            }
            @Override
            public void onFalhaBuscandoFilmes() {
            }
            @Override
            public void onBuscandoFilmes(boolean isGetting) {
            }
            @Override
            public void onBuscarFilmes() {
            }
        };
    }
}
