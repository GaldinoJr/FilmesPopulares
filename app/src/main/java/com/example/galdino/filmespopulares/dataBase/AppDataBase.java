package com.example.galdino.filmespopulares.dataBase;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.galdino.filmespopulares.dao.FilmeDAO;
import com.example.galdino.filmespopulares.entity.Filme;

/**
 * Created by Galdino on 11/08/2017.
 */

@Database(entities = {Filme.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase
{
    private static AppDataBase INSTANCE;

    // DAO
    public abstract FilmeDAO filmeDAO();

    public static AppDataBase getInstance(Context context)
    {
        if(INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class, "user-database")
                    // permitir que rode na thread principal
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }

    public static void destroyInstance()
    {
        INSTANCE = null;
    }
}
