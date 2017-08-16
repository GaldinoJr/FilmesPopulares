package com.example.galdino.filmespopulares.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.galdino.filmespopulares.dominio.Filme;

import java.util.List;


/**
 * Created by Galdino on 11/08/2017.
 */

@Dao
public interface FilmeDAO
{
    @Delete
    int deleteAll(Filme... filmes);

    @Query("SELECT * FROM tb_filme")
    List<Filme> getAll();

    @Query("SELECT * FROM tb_filme WHERE id LIKE :id")
    Filme selectById(int id);

    @Insert
    long[] insertAll(Filme... filmes);

    @Query("SELECT COUNT(*) FROM tb_filme")
    int countUsers();

    @Update
    void updateFilme(Filme... filmes);
}
