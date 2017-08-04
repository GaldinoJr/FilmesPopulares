package com.example.galdino.filmespopulares.telas.detalhesDoFilme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.adapter.AdapterListFilmes;
import com.example.galdino.filmespopulares.adapter.AdapterListTrailers;
import com.example.galdino.filmespopulares.databinding.FragmentFilmeDetalheBinding;
import com.example.galdino.filmespopulares.databinding.IncludeCapaFilmeBinding;
import com.example.galdino.filmespopulares.dominio.AnimationControler;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.FilmeDetalhe;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Result;
import com.example.galdino.filmespopulares.mvp.di.AppComponent;
import com.example.galdino.filmespopulares.mvp.di.DaggerAppComponent;
import com.example.galdino.filmespopulares.mvp.di.modules.ModelModule;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class FilmeDetalheFragment extends Fragment implements FilmeDetalheMvpView, View.OnClickListener {
    private FragmentFilmeDetalheBinding mBinding;
    private FilmeDetalheMvpPresenter mPresenter;
    private Integer mIdFilme;
    private FilmeDetalhe mFilmeDetalhe;

    private AdapterListTrailers.ListenerAdapter mListener = new AdapterListTrailers.ListenerAdapter() {
        @Override
        public void onItenClickListener(Result result) {
            Toast.makeText(getContext().getApplicationContext(),"teste",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentFilmeDetalheBinding.inflate(inflater,container, false);
        // Habilita o menu no fragment
        setHasOptionsMenu(true);

        // Dagger
        AppComponent appComponent = DaggerAppComponent.builder()
                .modelModule(new ModelModule(getContext().getApplicationContext()))
                .build();
        appComponent.inject(this);

        if (mIdFilme != null) {
            onFilmeDetalheBuscarInformacoes(mIdFilme);
        }

        return mBinding.getRoot();
    }

    @Inject
    public void setPresenter(FilmeDetalheMvpPresenter presenter)
    {
        mPresenter = presenter;
        // Vincula a view ao presenter
        presenter.attach(this);
    }

    @Override
    public void onFilmeDetalheBuscarInformacoes(int idFilme) {
        mIdFilme = idFilme;
        if (mBinding != null) {
            mBinding.pbLoading.setVisibility(View.VISIBLE);
            mPresenter.getFilmeDetalhe(idFilme);
        }
    }

    @Override
    public void onFilmeDetalheFalhaAoBuscarInformacoes() {
        Toast.makeText(getContext(),getString(R.string.erro_buscar_detalhe_filme),Toast.LENGTH_SHORT).show();
        mBinding.pbLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFilmeDetalhePreparado(FilmeDetalhe filmeDetalhe) {
        if(filmeDetalhe != null && mBinding != null)
        {
            mBinding.labelTrailer.setOnClickListener(this);
            mBinding.includeListTrailers.tvFecharListaTrailers.setOnClickListener(this);

            mFilmeDetalhe = filmeDetalhe;
            mBinding.inclCapaFilme.getRoot().setVisibility(View.VISIBLE);
            String urlCapa = getResources().getString(R.string.url_images_500) + filmeDetalhe.getPosterPath();
            IncludeCapaFilmeBinding includeCapaFilmeBinding = mBinding.inclCapaFilme;
            Picasso.with(mBinding.getRoot().getContext()).load(urlCapa).into(includeCapaFilmeBinding.ivCapaFilme);
            mBinding.tvNomeFilme.setText(filmeDetalhe.getTitle());
            if(filmeDetalhe.getReleaseDate()!= null)
            {
                String ano = filmeDetalhe.getReleaseDate().substring(0,filmeDetalhe.getReleaseDate().indexOf("-"));
                mBinding.tvAno.setText(ano);
            }
            if(filmeDetalhe.getRuntime() != null)
            {
                String tempo = Integer.toString(filmeDetalhe.getRuntime());
                tempo += "min";
                mBinding.tvTempoFilme.setText(tempo);
            }
            if(filmeDetalhe.getVoteAverage() != null)
            {
                String nota =  Double.toString(filmeDetalhe.getVoteAverage()) + "/10";
                mBinding.tvNotaFilme.setText(nota);
            }
            mBinding.tvDescricaoFilme.setText(filmeDetalhe.getOverview());

            if(filmeDetalhe.getVideos() != null)
            {
                AdapterListTrailers adapterListTrailers = new AdapterListTrailers(filmeDetalhe.getVideos().getResults());
                if(mListener != null) {
                    adapterListTrailers.setListener(mListener);
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());

                mBinding.includeListTrailers.rvTrailers.setAdapter(adapterListTrailers);
                mBinding.includeListTrailers.rvTrailers.setLayoutManager(linearLayoutManager);
                mBinding.includeListTrailers.rvTrailers.setNestedScrollingEnabled(false);
            }
        }
        mBinding.pbLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.im_filmes_detalhe_favorito)
        {
            if(mFilmeDetalhe.isFgFavorito())
            {
                item.setIcon(ContextCompat.getDrawable(getContext().getApplicationContext(),R.drawable.ic_favorito_vazio_azul));
                mFilmeDetalhe.setFgFavorito(false);
            }
            else
            {
                item.setIcon(ContextCompat.getDrawable(getContext().getApplicationContext(),R.drawable.ic_favorito_preenchido_azul));
                mFilmeDetalhe.setFgFavorito(true);
            }
        }
        else {
            getActivity().finish();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == mBinding.labelTrailer)
        {
//            AnimationControler.translateShow(mBinding.ivSombra,getContext().getApplicationContext());
            AnimationControler.upShowView(mBinding.includeListTrailers.constraintTrailer,getContext().getApplicationContext());
            mBinding.includeListTrailers.tvFecharListaTrailers.setFocusable(true);
        }
        else if(v == mBinding.includeListTrailers.tvFecharListaTrailers)
        {
//            AnimationControler.translateHide(mBinding.ivSombra,getContext().getApplicationContext());
            AnimationControler.downHideView(mBinding.includeListTrailers.constraintTrailer,getContext().getApplicationContext());
        }
    }
}
