package com.example.galdino.filmespopulares.UI.Activits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.galdino.filmespopulares.R;

public class ActivityFilmesDetalhe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes_detalhe);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
