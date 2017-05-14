package com.example.galdino.filmespopulares.UI.Activits;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.galdino.filmespopulares.Adapter.AdapterListFilmes;
import com.example.galdino.filmespopulares.Dominio.Result;
import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.Utilities.NetworkUtils;
import com.example.galdino.filmespopulares.databinding.ActivityFilmesPopularesBinding;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class ActivityFilmesPopulares extends AppCompatActivity {
    private ActivityFilmesPopularesBinding mBinding;
    //
    private List<Result> mListFilmes;
    private Context activity;
    private NetworkUtils mNetworkUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes_populares);
        carregarControles();
    }

    private void carregarControles() {
        activity = this;
        mNetworkUtils = new NetworkUtils();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_filmes_populares);
        //
        mBinding.rvFilmes.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 2);
        mBinding.rvFilmes.setLayoutManager(gridLayoutManager);
        //
        if (mBinding != null)
        {
            Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
            setSupportActionBar(toolbar);
            setTitle("");
            new atCarregarFilmes().execute(getResources().getString(R.string.metodo_popular));
        }
    }

    private class atCarregarFilmes extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mBinding.pbLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            if (params[0] != null) {
                mListFilmes = new LinkedList<>();
                try {
                    URL url = mNetworkUtils.buildUrl(activity, params[0], false);
                    String retorno = mNetworkUtils.getResponseFromHttpUrl(url);
                    mListFilmes = mNetworkUtils.getList(retorno);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (mListFilmes != null && mListFilmes.size() > 0) {
                AdapterListFilmes adapterListFilmes = new AdapterListFilmes(mListFilmes);

                if (mListener != null)
                    adapterListFilmes.setListFilmesClickListener(mListener);
                mBinding.rvFilmes.setAdapter(adapterListFilmes);
            }
            mBinding.pbLoading.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_filmes_populares, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_menu_mais_popular:
                mBinding.tvLabelTiposFilmes.setText(getResources().getString(R.string.filmes_populares));
                new atCarregarFilmes().execute(getResources().getString(R.string.metodo_popular));
                break;
            case R.id.item_menu_melhor_avaliado:
                mBinding.tvLabelTiposFilmes.setText(getResources().getString(R.string.filmes_melhor_avaliado));
                new atCarregarFilmes().execute(getResources().getString(R.string.metodo_melhor_avaliado));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private AdapterListFilmes.ListenerAdapter mListener = new AdapterListFilmes.ListenerAdapter() {
        @Override
        public void onClickList(Result result)
        {
            Intent intent = new Intent();
            if(result.getId() != null)
                intent.putExtra(ActivityFilmesDetalhe.KEY_ID,result.getId());
            //intent.putExtra(ActivityFilmesDetalhe.KEY_TITULO,getTitle());
            chamarProximaTela(ActivityFilmesDetalhe.class, intent);
        }
    };

    private void chamarProximaTela(Class classe, Intent intent) {
        if (intent == null)
            intent = new Intent();

        intent.setClass(this, classe);

        startActivity(intent);
    }

}
