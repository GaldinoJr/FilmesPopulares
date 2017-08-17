package com.example.galdino.filmespopulares.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.galdino.filmespopulares.dominio.filmeDetalhe.Result;

import java.util.List;

/**
 * Created by Galdino on 14/08/2017.
 */

@Dao
public interface ResultDAO
{
    @Query("SELECT * FROM tb_result")
    public List<Result> getAllTrailers();

//    @Insert
//    long[] insertAll(Result... results);

//    @Delete
//    int deleteById(Result... result);

    @Insert
    void insertAll(List<Result> results);

    @Delete
    int deleteById(List<Result> results);
}
