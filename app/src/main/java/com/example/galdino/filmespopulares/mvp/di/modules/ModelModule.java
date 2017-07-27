package com.example.galdino.filmespopulares.mvp.di.modules;

import android.content.Context;

import com.example.galdino.filmespopulares.utilities.FilmeApiMvpHelper;
import com.example.galdino.filmespopulares.listFilm.ListFilmModel;
import com.example.galdino.filmespopulares.listFilm.ListFilmMvpModel;
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
}
