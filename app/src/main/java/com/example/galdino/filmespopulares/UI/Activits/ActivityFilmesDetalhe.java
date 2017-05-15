package com.example.galdino.filmespopulares.UI.Activits;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.galdino.filmespopulares.Dominio.Result;

import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.Utilities.NetworkUtils;
import com.example.galdino.filmespopulares.VideoDetalhe.FilmeDetalhe;
import com.example.galdino.filmespopulares.databinding.ActivityFilmesDetalheBinding;
import com.example.galdino.filmespopulares.databinding.IncludeCapaFilmeBinding;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

public class ActivityFilmesDetalhe extends AppCompatActivity
{
    public static final String KEY_ID = "com.example.galdino.filmespopulares.UI.Activits.KEY_ID";
    //
    private ActivityFilmesDetalheBinding mBinding;
    private FilmeDetalhe mFilmeDetalhe;
    private NetworkUtils mNetworkUtils;
    private Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes_detalhe);
        carregarControles();
    }

    private void carregarControles()
    {
        //
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_filmes_detalhe);
        Intent intent = getIntent();
        mNetworkUtils = new NetworkUtils();
        mActivity = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        //
        int idFilme = intent.getIntExtra(KEY_ID,0);
        if(idFilme == 0)
        {
            Toast.makeText(this,getResources().getString(R.string.erro_id_filme),Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        //
        new atCarregarDetalheFilme(idFilme).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_filmes_detalhe,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.im_filmes_detalhe_favorito)
        {
            if(mFilmeDetalhe.isFgFavorito())
            {
                item.setIcon(ContextCompat.getDrawable(this,R.drawable.ic_favorito_vazio_azul));
                mFilmeDetalhe.setFgFavorito(false);
            }
            else
            {
                item.setIcon(ContextCompat.getDrawable(this,R.drawable.ic_favorito_preenchido_azul));
                mFilmeDetalhe.setFgFavorito(true);
            }
        }
        else {
            finish();
        }
        return true;
    }

    private class atCarregarDetalheFilme extends AsyncTask<String, String, String> {
        private final int idFilme;

        public atCarregarDetalheFilme(int idFilme) {
            this.idFilme = idFilme;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mBinding.pbLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            if (idFilme >0) {
                //mListFilmes = new LinkedList<>();
                try {
                    URL url = mNetworkUtils.buildUrlVideoDetalhe(mActivity, Integer.toString(idFilme));
                    String retorno = mNetworkUtils.getResponseFromHttpUrl(url);
                    mFilmeDetalhe = mNetworkUtils.getFilmeDetalhe(retorno);
                    //mListFilmes = mNetworkUtils.getList(retorno);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(mFilmeDetalhe != null && mBinding != null)
            {
                mBinding.inclCapaFilme.getRoot().setVisibility(View.VISIBLE);
                String urlCapa = getResources().getString(R.string.url_images_500) + mFilmeDetalhe.getPosterPath();
                IncludeCapaFilmeBinding includeCapaFilmeBinding = mBinding.inclCapaFilme;
                Picasso.with(mBinding.getRoot().getContext()).load(urlCapa).into(includeCapaFilmeBinding.ivCapaFilme);
                mBinding.tvNomeFilme.setText(mFilmeDetalhe.getTitle());
                if(mFilmeDetalhe.getReleaseDate()!= null)
                {
                    String ano = mFilmeDetalhe.getReleaseDate().substring(0,mFilmeDetalhe.getReleaseDate().indexOf("-"));
                    mBinding.tvAno.setText(ano);
                }
                if(mFilmeDetalhe.getRuntime() != null)
                {
                    String tempo = Integer.toString(mFilmeDetalhe.getRuntime());
                    mBinding.tvTempoFilme.setText(tempo + "min");
                }
                if(mFilmeDetalhe.getVoteAverage() != null)
                {
                    String nota =  Double.toString(mFilmeDetalhe.getVoteAverage()) + "/10";
                    mBinding.tvNotaFilme.setText(nota);
                }
                mBinding.tvDescricaoFilme.setText(mFilmeDetalhe.getOverview());
            }
            mBinding.pbLoading.setVisibility(View.INVISIBLE);
        }
    }
}
