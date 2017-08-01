package com.example.galdino.filmespopulares.listaDeFilmes;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.databinding.ActivityFilmesListBinding;

public class ListFilmesActivity extends AppCompatActivity {
    private ActivityFilmesListBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_filmes_activity);

        mBinding = DataBindingUtil.setContentView(this,R.layout.list_filmes_activity);
        if (mBinding != null) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
            setSupportActionBar(toolbar);
            setTitle("");
        }

        inflarFragment(ListFilmFragment.DF_FILMES_POPULARES);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_filmes_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        switch (id) {
            case R.id.item_menu_mais_popular:
                mBinding.tvLabelTiposFilmes.setText(getResources().getString(R.string.filmes_populares));
                inflarFragment(ListFilmFragment.DF_FILMES_POPULARES);
                break;
            case R.id.item_menu_melhor_avaliado:
                mBinding.tvLabelTiposFilmes.setText(getResources().getString(R.string.filmes_melhor_avaliado));
                inflarFragment(ListFilmFragment.DF_FILMES_MELHOR_AVALIADO);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void inflarFragment(int tipoPesuisaFilmes)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(ListFilmFragment.EXTRA_TIPO_PESQUISA,tipoPesuisaFilmes);

        ListFilmFragment fragment = ListFilmFragment.newInstance(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_frament_list, fragment).commit();
    }
}
