package com.example.galdino.filmespopulares.listFilm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.galdino.filmespopulares.adapter.AdapterListFilmes;
import com.example.galdino.filmespopulares.dominio.Result;
import com.example.galdino.filmespopulares.UI.activits.ActivityFilmesDetalhe;
import com.example.galdino.filmespopulares.databinding.FragmentFragListFilmBinding;
import com.example.galdino.filmespopulares.mvp.di.AppComponent;
import com.example.galdino.filmespopulares.mvp.di.DaggerAppComponent;
import com.example.galdino.filmespopulares.mvp.di.modules.ModelModule;

import java.util.List;

import javax.inject.Inject;

public class ListFilmFragment extends Fragment implements ListFilmMvpView, SwipeRefreshLayout.OnRefreshListener{
    FragmentFragListFilmBinding mBinding;
    ListFilmMvpPresenter listFilmPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentFragListFilmBinding.inflate(inflater, container, false);

        mBinding.rvFilmes.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mBinding.rvFilmes.setLayoutManager(gridLayoutManager);

        AppComponent appComponent = DaggerAppComponent.builder()
                .modelModule(new ModelModule(getContext().getApplicationContext()))
                .build();
        appComponent.inject(this);

        onRefresh();

        return mBinding.getRoot();
    }

    @Inject
    public void setPresenter(ListFilmMvpPresenter listFilmMvpPresenter) {
        this.listFilmPresenter = listFilmMvpPresenter;
        // Vincula a view ao presenter
        listFilmMvpPresenter.attach(this);
    }

    @Override
    public void onMoviesReady(List<Result> listaFilmes) {
        if (listaFilmes != null)
        {
//            int posterWidth = getResources().getDimensionPixelSize(R.dimen.movie_poster_size_median);
            AdapterListFilmes adapterListFilmes = new AdapterListFilmes(listaFilmes);
            if (mListener != null)
            {
                adapterListFilmes.setListFilmesClickListener(mListener);
            }
            mBinding.rvFilmes.setAdapter(adapterListFilmes);
        }
        mBinding.rvFilmes.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetMoviesFailed() {

    }

    @Override
    public void onGettingMovies(boolean isGetting) {

    }

    @Override
    public void onGetMovies() {
        listFilmPresenter.getPopularMovies();
    }

    @Override
    public void onRefresh() {
        onGetMovies();
    }

    private AdapterListFilmes.ListenerAdapter mListener = new AdapterListFilmes.ListenerAdapter() {
        @Override
        public void onClickList(Result result)
        {
            Intent intent = new Intent();
            if(result.getId() != null)
                intent.putExtra(ActivityFilmesDetalhe.KEY_ID,result.getId());
            chamarProximaTela(ActivityFilmesDetalhe.class, intent);
        }
    };
    private void chamarProximaTela(Class classe, Intent intent) {
        if (intent == null)
            intent = new Intent();

        intent.setClass(getActivity(), classe);

        startActivity(intent);
    }
}
