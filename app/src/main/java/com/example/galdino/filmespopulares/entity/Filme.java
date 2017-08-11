package com.example.galdino.filmespopulares.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Galdino on 10/08/2017.
 */

@Entity(tableName = "tb_filme")
public class Filme
{
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "fg_favorito")
    private int fgFavorito;

    // gets
    public int getUid() {
        return uid;
    }

    public int getId() {
        return id;
    }

    public int getFgFavorito() {
        return fgFavorito;
    }

    // sets

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFgFavorito(int fgFavorito) {
        this.fgFavorito = fgFavorito;
    }
}
