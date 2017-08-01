package com.example.galdino.filmespopulares.mvp.di.modules;

import android.content.Context;

import com.example.galdino.filmespopulares.detalhesDoFilme.FilmeDetalheModel;
import com.example.galdino.filmespopulares.detalhesDoFilme.FilmeDetalheMvpModel;
import com.example.galdino.filmespopulares.listaDeFilmes.ListFilmModel;
import com.example.galdino.filmespopulares.listaDeFilmes.ListFilmMvpModel;
import com.example.galdino.filmespopulares.utilities.FilmeApiMvpHelper;
import com.example.galdino.filmespopulares.utilities.MovieDbApiHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by galdino on 27/07/17.
 */

@Module
public class ModelModule {

    private Context mContext;

    public ModelModule(Context context)
    {
        this.mContext = context;
    }

    @Provides
    public Context providesContext()
    {
        return mContext;
    }

    @Provides
    public FilmeApiMvpHelper providesFilmeApiMvpHelper(Context context)
    {
        return new MovieDbApiHelper(context);
    }

    @Provides
    public ListFilmMvpModel providesListFilmMvpModel(FilmeApiMvpHelper mHelper)
    {
        return new ListFilmModel(mHelper);
    }

    @Provides
    public FilmeDetalheMvpModel providesFilmeDetalheMvpModel(FilmeApiMvpHelper mHelper)
    {
        return new FilmeDetalheModel(mHelper);
    }
}
