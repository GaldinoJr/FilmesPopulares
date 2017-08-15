package com.example.galdino.filmespopulares.telas.listaDeFilmes;

import android.content.Context;

import com.example.galdino.filmespopulares.dataBase.AppDataBase;
import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.mvp.BasePresenter;
import com.example.galdino.filmespopulares.mvp.schedulerprovider.SchedulerProvider;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static java.security.AccessController.getContext;

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

    @Override
    public void getFilmesFavoritos(Context context) {
        SchedulerProvider schedulerProvider = getSchedulerProvider();
        mFilmeMvpDataManager.getFilmesFavoritos(context)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(getFilmesObserver());
    }

    private SingleObserver<List<Filme>> getFilmesObserver() {
        return new SingleObserver<List<Filme>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d)
            {
                mMvpView.onBuscandoFilmes(true);
            }

            @Override
            public void onSuccess(@NonNull List<Filme> movies)
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
            public void onFilmesPreparados(List<Filme> movies) {
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
