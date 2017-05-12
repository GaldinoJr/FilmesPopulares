package com.example.galdino.filmespopulares.UI.Activits;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.galdino.filmespopulares.Dominio.Result;
import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.databinding.ActivityFilmesDetalheBinding;
import com.squareup.picasso.Picasso;

public class ActivityFilmesDetalhe extends AppCompatActivity
{
    public static final String KEY_RESULT = "com.example.galdino.filmespopulares.UI.Activits.KEY_RESULT";
    public static final String KEY_TITULO = "com.example.galdino.filmespopulares.UI.Activits.KEY_TITULO";
    //
    private ActivityFilmesDetalheBinding mBinding;
    private Result mResult;
    private String mTituloTela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes_detalhe);
        carregarControles();
    }

    private void carregarControles()
    {
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_filmes_detalhe);
        Intent intent = getIntent();
        mResult = (Result) intent.getSerializableExtra(KEY_RESULT);
        mTituloTela = intent.getStringExtra(KEY_TITULO);
        if(mResult != null && mBinding != null)
        {
            setTitle(mTituloTela);

            String urlCapa = getResources().getString(R.string.url_images_500) + mResult.getPosterPath();
            Picasso.with(this).load(urlCapa).into(mBinding.ivCapaFilme);
            mBinding.tvNomeFilme.setText(mResult.getTitle());
            if(mResult.getReleaseDate()!= null)
            {
                String ano = mResult.getReleaseDate().substring(0,mResult.getReleaseDate().indexOf("-"));
                mBinding.tvAno.setText(ano);
            }
            //mBinding.tvTempoFilme.setText(mResult.ge);
            if(mResult.getVoteAverage() != null)
            {
                String nota =  Double.toString(mResult.getVoteAverage()) + "/10";
                mBinding.tvNotaFilme.setText(nota);
            }
            mBinding.tvDescricaoFilme.setText(mResult.getOverview());
        }
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
            if(mResult.isFgFavorito())
            {
                item.setIcon(ContextCompat.getDrawable(this,R.drawable.ic_favorito_vazio));
                mResult.setFgFavorito(false);
            }
            else
            {
                item.setIcon(ContextCompat.getDrawable(this,R.drawable.ic_favorito_preenchido));
                mResult.setFgFavorito(true);
            }
        }
        else {
            finish();
        }
        return true;//super.onOptionsItemSelected(item);
    }
}
