package com.example.galdino.filmespopulares.UI.Activits;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.galdino.filmespopulares.Adapter.AdapterListFilmes;
import com.example.galdino.filmespopulares.Dominio.Filme;
import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.databinding.ActivityFilmesPopularesBinding;

import java.util.LinkedList;
import java.util.List;

public class ActivityFilmesPopulares extends AppCompatActivity
{
    private Integer[] mIdsFilmes = {R.drawable.ft_capa_rocky ,R.drawable.foto_capa_em_busca_felicidade, R.drawable.foto_capa_exterminador_5, R.drawable.ft_capa_harry_potter, R.drawable.ft_capa_rambo};
    //
    private ActivityFilmesPopularesBinding mBinding;
    //
    private List<Filme> mListFilmes;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes_populares);
        carregarControles();
    }

    private void carregarControles()
    {
        mBinding =  DataBindingUtil.setContentView(this,R.layout.activity_filmes_populares);
        if(mBinding != null)
        {
            carregarFilmes();
            if(mListFilmes != null && mListFilmes.size() > 0) {
                mBinding.rvFilmes.setHasFixedSize(true);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                AdapterListFilmes adapterListFilmes = new AdapterListFilmes(mListFilmes);

                mBinding.rvFilmes.setLayoutManager(gridLayoutManager);
                mBinding.rvFilmes.setAdapter(adapterListFilmes);
            }
        }
    }

    private void carregarFilmes()
    {
        mListFilmes = new LinkedList<>();
        for(int i = 0; i < mIdsFilmes.length; i++)
        {
            Filme f = new Filme();
            f.setIdFotoCapaFilme(mIdsFilmes[i]);
            mListFilmes.add(f);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_filmes_populares,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.item_menu_mais_popular:
                Toast.makeText(this,"mais popular", Toast.LENGTH_LONG).show();
            case R.id.item_menu_melhor_avaliado:
                Toast.makeText(this,"melhor avaliado", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
