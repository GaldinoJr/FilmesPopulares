package com.example.galdino.filmespopulares.listFilm;

import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.mvp.BasePresenter;
import com.example.galdino.filmespopulares.mvp.SchedulerProvider;

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
        mFilmeMvpDataManager = dataManager;
    }

    @Override
    public void getPopularMovies() {
        SchedulerProvider schedulerProvider = getSchedulerProvider();
        mFilmeMvpDataManager.getPopularMovies()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(getFilmesObserver());
    }

    @Override
    public void getTopRatedMovies() {

    }

    private SingleObserver<List<Filme>> getFilmesObserver() {
        return new SingleObserver<List<Filme>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mMvpView.onGettingMovies(true);
            }

            @Override
            public void onSuccess(@NonNull List<Filme> movies) {
                mMvpView.onMoviesReady(movies);
                mMvpView.onGettingMovies(false);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mMvpView.onGetMoviesFailed();
                mMvpView.onGettingMovies(false);
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
            public void onMoviesReady(List<Filme> movies) {
            }
            @Override
            public void onGetMoviesFailed() {
            }
            @Override
            public void onGettingMovies(boolean isGetting) {
            }
            @Override
            public void onGetMovies() {
            }
        };
    }
}
