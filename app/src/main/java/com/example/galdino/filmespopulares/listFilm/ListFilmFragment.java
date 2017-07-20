package com.example.galdino.filmespopulares.listFilm;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.galdino.filmespopulares.Dominio.Filme;
import com.example.galdino.filmespopulares.R;

import java.util.List;

public class ListFilmFragment extends Fragment implements ListFilmMvpView{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_list_film, container, false);
    }

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
}
