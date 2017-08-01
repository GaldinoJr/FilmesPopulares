package com.example.galdino.filmespopulares.detalhesDoFilme;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.databinding.FilmeDetalheActivityBinding;

public class FilmeDetalheActivity extends AppCompatActivity {
    public static final String EXTRA_ID_FILME = "EXTRA_ID_FILME";
    private FilmeDetalheActivityBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.filme_detalhe_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");

        Intent intent = getIntent();
        Integer idFilme = null;
        if(intent != null)
        {
            idFilme = intent.getIntExtra(EXTRA_ID_FILME, -1);
        }

        if(idFilme == null || idFilme == -1)
        {
            Toast.makeText(this,getResources().getString(R.string.erro_id_filme),Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FilmeDetalheFragment filmeDetalheFragment =
                (FilmeDetalheFragment) fragmentManager.findFragmentById(R.id.frag_container_detalhe);
        filmeDetalheFragment.onFilmeDetalheBuscarInformacoes(idFilme);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_filmes_detalhe,menu);
        return true;
    }
}
