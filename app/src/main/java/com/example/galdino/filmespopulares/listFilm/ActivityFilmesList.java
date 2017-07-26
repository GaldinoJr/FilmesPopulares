package com.example.galdino.filmespopulares.listFilm;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.galdino.filmespopulares.R;
import com.example.galdino.filmespopulares.mvp.MvpView;

public class ActivityFilmesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes_list);

    }
}
