package com.example.galdino.filmespopulares.mvp.di;

import com.example.galdino.filmespopulares.listFilm.ListFilmFragment;
import com.example.galdino.filmespopulares.mvp.di.modules.ModelModule;
import com.example.galdino.filmespopulares.mvp.di.modules.PresenterModule;

import dagger.Component;

@Component(modules = {PresenterModule.class, ModelModule.class})
public interface AppComponent {
    void inject(ListFilmFragment listFilmFragment);
}
