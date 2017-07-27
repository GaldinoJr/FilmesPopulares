package com.example.galdino.filmespopulares.mvp.di.modules;

import com.example.galdino.filmespopulares.listFilm.ListFilmMvpModel;
import com.example.galdino.filmespopulares.listFilm.ListFilmMvpPresenter;
import com.example.galdino.filmespopulares.listFilm.ListFilmPresenter;
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

}
