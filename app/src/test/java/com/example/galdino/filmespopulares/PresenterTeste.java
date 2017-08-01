package com.example.galdino.filmespopulares;

import com.example.galdino.filmespopulares.dominio.Result;
import com.example.galdino.filmespopulares.listaDeFilmes.ListFilmMvpModel;
import com.example.galdino.filmespopulares.listaDeFilmes.ListFilmMvpPresenter;
import com.example.galdino.filmespopulares.listaDeFilmes.ListFilmMvpView;
import com.example.galdino.filmespopulares.listaDeFilmes.ListFilmPresenter;
import com.example.galdino.filmespopulares.mvp.schedulerprovider.SchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by galdino on 27/07/17.
 */

public class PresenterTeste
{
    ListFilmMvpPresenter mvpPresenter;
    private TestScheduler mTestScheduler;

    @Mock
    ListFilmMvpView mvpView;
    @Mock
    private ListFilmMvpModel dataManager;
    @Mock
    private List<Result> mResults;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        when(dataManager.getFilmesPopulares()).thenReturn(Single.just(mResults));

        mTestScheduler = new TestScheduler();
        SchedulerProvider schedulerProvider = new SchedulerProvider() {
            @Override
            public Scheduler io() {
                return mTestScheduler;
            }

            @Override
            public Scheduler ui() {
                return mTestScheduler;
            }
        };
        mvpPresenter = new ListFilmPresenter(schedulerProvider, dataManager);
        mvpPresenter.attach(mvpView);
    }


    @Test
    public void getPopular_modelError_false()
    {
        when(dataManager.getFilmesPopulares()).thenReturn(Single.<List<Result>>error(new Exception("Mocked error")));
        mvpPresenter.getFilmesPopulares();
        mTestScheduler.triggerActions();

        verify(mvpView).onFalhaBuscandoFilmes();
//        Nunca cahamnou uma lista com filmes
        verify(mvpView, never()).onFilmesPreparados(anyListOf(Result.class));
        verify(mvpView).onBuscandoFilmes(false);
    }
}
