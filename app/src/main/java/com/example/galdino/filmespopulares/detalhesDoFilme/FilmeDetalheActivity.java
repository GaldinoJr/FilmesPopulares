package com.example.galdino.filmespopulares.detalhesDoFilme;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.databinding.FilmeDetalheActivityBinding;

public class FilmeDetalheActivity extends AppCompatActivity {
    public static final String EXTRA_ID_FILME = "EXTRA_ID_FILME";
    private FilmeDetalheActivityBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filme_detalhe_activity);

        mBinding = DataBindingUtil.setContentView(this,R.layout.filme_detalhe_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");

        FilmeDetalheFragment fragment = FilmeDetalheFragment.newInstance(new Bundle());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_container_detalhe,fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_filmes_detalhe,menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.im_filmes_detalhe_favorito)
//        {
//            if(mFilmeDetalhe.isFgFavorito())
//            {
//                item.setIcon(ContextCompat.getDrawable(this,R.drawable.ic_favorito_vazio_azul));
//                mFilmeDetalhe.setFgFavorito(false);
//            }
//            else
//            {
//                item.setIcon(ContextCompat.getDrawable(this,R.drawable.ic_favorito_preenchido_azul));
//                mFilmeDetalhe.setFgFavorito(true);
//            }
//        }
//        else {
//            finish();
//        }
//        return true;
//    }
}
