package com.example.galdino.filmespopulares.mvp.di;

import com.example.galdino.filmespopulares.detalhesDoFilme.FilmeDetalheFragment;
import com.example.galdino.filmespopulares.listaDeFilmes.ListFilmFragment;
import com.example.galdino.filmespopulares.mvp.di.modules.ModelModule;
import com.example.galdino.filmespopulares.mvp.di.modules.PresenterModule;

import dagger.Component;

@Component(modules = {PresenterModule.class, ModelModule.class})
public interface AppComponent {
    void inject(ListFilmFragment listFilmFragment);
    void inject(FilmeDetalheFragment filmeDetalheFragment);
}
