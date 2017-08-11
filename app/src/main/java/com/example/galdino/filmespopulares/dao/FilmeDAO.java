package com.example.galdino.filmespopulares.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.galdino.filmespopulares.entity.Filme;



/**
 * Created by Galdino on 11/08/2017.
 */

@Dao
public interface FilmeDAO
{
    @Query("SELECT * FROM tb_filme WHERE id LIKE :id")
    Filme selectById(int id);

    @Insert
    long[] InsertAll(Filme... filmes);

    @Query("SELECT COUNT(*) FROM tb_filme")
    int countUsers();

    @Update
    void updateFilme(Filme... filmes);
}
