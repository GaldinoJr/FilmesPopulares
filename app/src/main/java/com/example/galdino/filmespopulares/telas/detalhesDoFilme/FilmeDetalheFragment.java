package com.example.galdino.filmespopulares.telas.detalhesDoFilme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.adapter.AdapterListComentario;
import com.example.galdino.filmespopulares.adapter.AdapterListTrailers;
import com.example.galdino.filmespopulares.dataBase.AppDataBase;
import com.example.galdino.filmespopulares.databinding.FragmentFilmeDetalheBinding;
import com.example.galdino.filmespopulares.databinding.IncludeCapaFilmeBinding;
import com.example.galdino.filmespopulares.dominio.AnimationControler;
import com.example.galdino.filmespopulares.dominio.Filme;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Comentarios;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Result;
import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Videos;
import com.example.galdino.filmespopulares.mvp.di.AppComponent;
import com.example.galdino.filmespopulares.mvp.di.DaggerAppComponent;
import com.example.galdino.filmespopulares.mvp.di.modules.ModelModule;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class FilmeDetalheFragment extends Fragment implements FilmeDetalheMvpView, View.OnClickListener {
    private FragmentFilmeDetalheBinding mBinding;
    private FilmeDetalheMvpPresenter mPresenter;
    private Integer mIdFilme;
    private Filme mFilme;
    private boolean mFgListaTrailerAberta;
//    private Filme filme;
    private AppDataBase db;
    private Videos mVideos;

    private AdapterListTrailers.ListenerAdapter mListener = new AdapterListTrailers.ListenerAdapter() {
        @Override
        public void onItenClickListener(Result result) {
//            Toast.makeText(getContext().getApplicationContext(),"teste",Toast.LENGTH_SHORT).show();
            String urlVideo = "vnd.youtube:"+result.getKey();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlVideo));
            intent.putExtra("VIDEO_ID",result.getKey());
            startActivity(intent);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentFilmeDetalheBinding.inflate(inflater,container, false);
        mFgListaTrailerAberta = false;
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
    public void onFilmeDetalhePreparado(Filme filme) {
        if(filme != null && mBinding != null)
        {
            mBinding.labelTrailer.setOnClickListener(this);
            mBinding.labelComentarios.setOnClickListener(this);
            mBinding.includeListTrailers.tvFecharListaTrailers.setOnClickListener(this);
            mBinding.labelTrailer.setVisibility(View.VISIBLE);
            mBinding.labelComentarios.setVisibility(View.VISIBLE);

            mFilme = filme;
            db = AppDataBase.getInstance(getContext().getApplicationContext());

            mBinding.inclCapaFilme.getRoot().setVisibility(View.VISIBLE);
            String urlCapa = getResources().getString(R.string.url_images_500) + filme.getPosterPath();
            IncludeCapaFilmeBinding includeCapaFilmeBinding = mBinding.inclCapaFilme;
            Picasso.with(mBinding.getRoot().getContext()).load(urlCapa).into(includeCapaFilmeBinding.ivCapaFilme);
            mBinding.tvNomeFilme.setText(filme.getTitle());
            if(filme.getReleaseDate()!= null)
            {
                String ano = filme.getReleaseDate().substring(0, filme.getReleaseDate().indexOf("-"));
                mBinding.tvAno.setText(ano);
            }
            if(filme.getRuntime() != null)
            {
                String tempo = Integer.toString(filme.getRuntime());
                tempo += "min";
                mBinding.tvTempoFilme.setText(tempo);
            }
            if(filme.getVoteAverage() != null)
            {
                String nota =  Double.toString(filme.getVoteAverage()) + "/10";
                mBinding.tvNotaFilme.setText(nota);
            }
            mBinding.tvDescricaoFilme.setText(filme.getOverview());

            if(filme.getVideos() != null)
            {
                mVideos = filme.getVideos();
                openTrailers(filme.getVideos());
            }
        }
        mBinding.pbLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onComentariosPreparado(List<Result> comentarios) {
        openComentarios(comentarios);
        mFgListaTrailerAberta = true;
        AnimationControler.upShowView(mBinding.includeListTrailers.constraintTrailer,getContext().getApplicationContext());
        mBinding.includeListTrailers.tvFecharListaTrailers.setFocusable(true);
        mBinding.pbLoading.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onComentarioFalhaAoBuscarInformacoes() {
        Toast.makeText(getContext().getApplicationContext(),getString(R.string.erro_busca_comentario), Toast.LENGTH_SHORT).show();
        fecharListaTrailer();
        mBinding.pbLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.im_filmes_detalhe_favorito)
        {
            if(mFilme != null) {
                if (mFilme.getFgFavorito() == 1) {
                    item.setIcon(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.ic_favorito_vazio_azul));
                    mFilme.setFgFavorito(0);
                    db.filmeDAO().deleteAll(mFilme);
                    if (mFilme.getVideos() != null && mFilme.getVideos().getResults() != null && mFilme.getVideos().getResults().size() > 0) {
                        db.resultDAO().deleteById(mFilme.getVideos().getResults());
                    }
                } else {
                    item.setIcon(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.ic_favorito_preenchido_azul));
                    mFilme.setFgFavorito(1);
                    if (mFilme.getUid() == 0) {
                        db.filmeDAO().insertAll(mFilme);
                        if (mFilme.getVideos() != null && mFilme.getVideos().getResults() != null && mFilme.getVideos().getResults().size() > 0) {
                            db.resultDAO().insertAll(mFilme.getVideos().getResults());
                        }
                    }
                }
            }
        }
        else
        {
            if(mFgListaTrailerAberta)
            {
                fecharListaTrailer();
            }
            else
            {
                getActivity().finish();
            }
        }
        return true;
    }

    @Override
    public void onClick(View v)
    {
        if(v == mBinding.includeListTrailers.tvFecharListaTrailers)
        {
            fecharListaTrailer();
        }
        else if(v == mBinding.labelComentarios)
        {
            mBinding.pbLoading.setVisibility(View.VISIBLE);
            mPresenter.getComentarios(mFilme.getId());
        }
        else if (v == mBinding.labelTrailer)
        {
            if(mVideos != null)
            {
                openTrailers(mVideos);
                mFgListaTrailerAberta = true;
                //            AnimationControler.translateShow(mBinding.ivSombra,getContext().getApplicationContext());
                AnimationControler.upShowView(mBinding.includeListTrailers.constraintTrailer, getContext().getApplicationContext());
                mBinding.includeListTrailers.tvFecharListaTrailers.setFocusable(true);
            }
        }

    }

    public boolean listaTrailerAberta()
    {
        return mFgListaTrailerAberta;
    }

    public void fecharListaTrailer()
    {
        mFgListaTrailerAberta = false;
//            AnimationControler.translateHide(mBinding.ivSombra,getContext().getApplicationContext());
        AnimationControler.downHideView(mBinding.includeListTrailers.constraintTrailer,getContext().getApplicationContext());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_filmes_detalhe,menu);
        if(mFilme != null && mFilme.getUid() > 0)
        {
            mFilme.setFgFavorito(0); // Passa false pra ficar true
            onOptionsItemSelected(menu.findItem(R.id.im_filmes_detalhe_favorito));
        }
    }

    private void openComentarios(List<Result> comentarios)
    {
        AdapterListComentario adapterListComentarios = new AdapterListComentario(comentarios);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());

        mBinding.includeListTrailers.rvTrailers.setAdapter(adapterListComentarios);
        mBinding.includeListTrailers.rvTrailers.setLayoutManager(linearLayoutManager);
        mBinding.includeListTrailers.rvTrailers.setNestedScrollingEnabled(false);
    }

    private void openTrailers(Videos videos)
    {
        AdapterListTrailers adapterListTrailers = new AdapterListTrailers(videos.getResults());
        if(mListener != null) {
            adapterListTrailers.setListener(mListener);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());

        mBinding.includeListTrailers.rvTrailers.setAdapter(adapterListTrailers);
        mBinding.includeListTrailers.rvTrailers.setLayoutManager(linearLayoutManager);
        mBinding.includeListTrailers.rvTrailers.setNestedScrollingEnabled(false);
    }
}
