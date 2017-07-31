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

public class ListFilmFragment extends Fragment implements ListFilmMvpView, SwipeRefreshLayout.OnRefreshListener
{
    public static final String EXTRA_TIPO_PESQUISA = "EXTRA_TIPO_PESQUISA";
    public static final int DF_FILMES_POPULARES = 1001;
    public static final int DF_FILMES_MELHOR_AVALIADO = 1002;
    private FragmentFragListFilmBinding mBinding;
    private ListFilmMvpPresenter listFilmPresenter;
    private int mTipoPesquisa;

    public static ListFilmFragment newInstance(Bundle data) {
        ListFilmFragment fragment = new ListFilmFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTipoPesquisa = arguments.getInt(EXTRA_TIPO_PESQUISA, DF_FILMES_POPULARES);
        }
    }


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
    public void onFilmesPreparados(List<Result> listaFilmes) {
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
        mBinding.pbLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFalhaBuscandoFilmes() {

    }

    @Override
    public void onBuscandoFilmes(boolean isGetting) {

    }

    @Override
    public void onGetFilmes()
    {
        mBinding.pbLoading.setVisibility(View.VISIBLE);
        if(mTipoPesquisa == DF_FILMES_POPULARES) {
            listFilmPresenter.getFilmesPopulares();
        }
        else
        {
            listFilmPresenter.getFilmesMelhorAvaliados();
        }
    }

    @Override
    public void onRefresh() {
        onGetFilmes();
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
