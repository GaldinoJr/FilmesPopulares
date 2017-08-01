package com.example.galdino.filmespopulares.mvp.di.modules;

import com.example.galdino.filmespopulares.detalhesDoFilme.FilmeDetalheMvpModel;
import com.example.galdino.filmespopulares.detalhesDoFilme.FilmeDetalhePresenter;
import com.example.galdino.filmespopulares.listaDeFilmes.ListFilmMvpModel;
import com.example.galdino.filmespopulares.listaDeFilmes.ListFilmMvpPresenter;
import com.example.galdino.filmespopulares.listaDeFilmes.ListFilmPresenter;
import com.example.galdino.filmespopulares.mvp.schedulerprovider.SchedulerProvider;
import com.example.galdino.filmespopulares.mvp.schedulerprovider.AppSchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    public SchedulerProvider providesSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    public ListFilmMvpPresenter providesListFilmPresenter(SchedulerProvider schedulerProvider, ListFilmMvpModel dataManager) {
        return new ListFilmPresenter(schedulerProvider, dataManager);
    }

    @Provides
    public FilmeDetalhePresenter providesFilmeDetalhePresenter(SchedulerProvider schedulerProvider, FilmeDetalheMvpModel mMvpModel) {
        return new FilmeDetalhePresenter(schedulerProvider,mMvpModel);
    }

}
