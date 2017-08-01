package com.example.galdino.filmespopulares.detalhesDoFilme;

import android.os.Bundle;
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
    private Integer mIdFilme;
    private FilmeDetalhe mFilmeDetalhe;

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
    }

    @Override
    public void onFilmeDetalhePreparado(FilmeDetalhe filmeDetalhe) {
        if(filmeDetalhe != null && mBinding != null)
        {
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
}
