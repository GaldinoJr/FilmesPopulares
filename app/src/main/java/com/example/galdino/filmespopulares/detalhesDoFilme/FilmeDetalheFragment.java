package com.example.galdino.filmespopulares.detalhesDoFilme;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.databinding.FragmentFilmeDetalheBinding;
import com.example.galdino.filmespopulares.databinding.IncludeCapaFilmeBinding;
import com.example.galdino.filmespopulares.filmeDetalhe.FilmeDetalhe;
import com.example.galdino.filmespopulares.mvp.di.AppComponent;
import com.example.galdino.filmespopulares.mvp.di.DaggerAppComponent;
import com.example.galdino.filmespopulares.mvp.di.modules.ModelModule;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import static com.example.galdino.filmespopulares.detalhesDoFilme.FilmeDetalheActivity.EXTRA_ID_FILME;

public class FilmeDetalheFragment extends Fragment implements FilmeDetalheMvpView{
    private FragmentFilmeDetalheBinding mBinding;
    private FilmeDetalheMvpPresenter mPresenter;
    private String idFilme;

    public static FilmeDetalheFragment newInstance(Bundle bundle)
    {
        FilmeDetalheFragment fragment = new FilmeDetalheFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments != null)
        {
            if(!arguments.containsKey(EXTRA_ID_FILME))
            {
                Toast.makeText(getContext().getApplicationContext(),getResources().getString(R.string.erro_id_filme),Toast.LENGTH_LONG).show();
                getActivity().finish();
                return;
            }
            idFilme = arguments.getString(EXTRA_ID_FILME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentFilmeDetalheBinding.inflate(inflater,container, false);

        // Dagger
        AppComponent appComponent = DaggerAppComponent.builder()
                .modelModule(new ModelModule(getContext().getApplicationContext()))
                .build();
        appComponent.inject(this);

        onFilmeDetalheBuscarInformacoes(idFilme);
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
    public void onFilmeDetalheBuscarInformacoes(String idFilme) {
        mBinding.pbLoading.setVisibility(View.VISIBLE);
        mPresenter.getFilmeDetalhe(idFilme);
    }

    @Override
    public void onFilmeDetalheFalhaAoBuscarInformacoes() {
        Toast.makeText(getContext(),getString(R.string.erro_buscar_detalhe_filme),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFilmeDetalhePreparado(FilmeDetalhe filmeDetalhe) {
        if(filmeDetalhe != null && mBinding != null)
        {
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
        }
        mBinding.pbLoading.setVisibility(View.INVISIBLE);
    }

}
